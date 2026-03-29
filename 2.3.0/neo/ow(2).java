package neo;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.util.ResourceLocation;

public class ow {
   public float textureWidth;
   public float textureHeight;
   private int textureOffsetX;
   private int textureOffsetY;
   public float rotationPointX;
   public float rotationPointY;
   public float rotationPointZ;
   public float rotateAngleX;
   public float rotateAngleY;
   public float rotateAngleZ;
   private boolean compiled;
   private int displayList;
   public boolean mirror;
   public boolean showModel;
   public boolean isHidden;
   public List<nQ> cubeList;
   public List<ow> childModels;
   public final String boxName;
   private final nH baseModel;
   public float offsetX;
   public float offsetY;
   public float offsetZ;
   public List spriteList;
   public boolean mirrorV;
   public float scaleX;
   public float scaleY;
   public float scaleZ;
   private int countResetDisplayList;
   private ResourceLocation textureLocation;
   private String id;
   private bjV modelUpdater;
   private yy renderGlobal;

   public ow(nH model, String boxNameIn) {
      this.spriteList = new ArrayList();
      this.mirrorV = false;
      this.scaleX = 1.0F;
      this.scaleY = 1.0F;
      this.scaleZ = 1.0F;
      this.textureLocation = null;
      this.id = null;
      this.renderGlobal = XH.getRenderGlobal();
      this.textureWidth = 64.0F;
      this.textureHeight = 32.0F;
      this.showModel = true;
      this.cubeList = Lists.newArrayList();
      this.baseModel = model;
      model.boxList.add(this);
      this.boxName = boxNameIn;
      this.setTextureSize(model.textureWidth, model.textureHeight);
   }

   public ow(nH model) {
      this(model, (String)null);
   }

   public ow(nH model, int texOffX, int texOffY) {
      this(model);
      this.setTextureOffset(texOffX, texOffY);
   }

   public void addChild(ow renderer) {
      if (this.childModels == null) {
         this.childModels = Lists.newArrayList();
      }

      this.childModels.add(renderer);
   }

   public ow setTextureOffset(int x, int y) {
      this.textureOffsetX = x;
      this.textureOffsetY = y;
      return this;
   }

   public ow addBox(String partName, float offX, float offY, float offZ, int width, int height, int depth) {
      partName = this.boxName + "." + partName;
      oU textureoffset = this.baseModel.getTextureOffset(partName);
      this.setTextureOffset(textureoffset.textureOffsetX, textureoffset.textureOffsetY);
      this.cubeList.add((new nQ(this, this.textureOffsetX, this.textureOffsetY, offX, offY, offZ, width, height, depth, 0.0F)).setBoxName(partName));
      return this;
   }

   public ow addBox(float offX, float offY, float offZ, int width, int height, int depth) {
      this.cubeList.add(new nQ(this, this.textureOffsetX, this.textureOffsetY, offX, offY, offZ, width, height, depth, 0.0F));
      return this;
   }

   public ow addBox(float offX, float offY, float offZ, int width, int height, int depth, boolean mirrored) {
      this.cubeList.add(new nQ(this, this.textureOffsetX, this.textureOffsetY, offX, offY, offZ, width, height, depth, 0.0F, mirrored));
      return this;
   }

   public void addBox(float offX, float offY, float offZ, int width, int height, int depth, float scaleFactor) {
      this.cubeList.add(new nQ(this, this.textureOffsetX, this.textureOffsetY, offX, offY, offZ, width, height, depth, scaleFactor));
   }

   public void setRotationPoint(float rotationPointXIn, float rotationPointYIn, float rotationPointZIn) {
      this.rotationPointX = rotationPointXIn;
      this.rotationPointY = rotationPointYIn;
      this.rotationPointZ = rotationPointZIn;
   }

   public void render(float scale) {
      if (!this.isHidden && this.showModel) {
         this.checkResetDisplayList();
         if (!this.compiled) {
            this.compileDisplayList(scale);
         }

         int i = 0;
         if (this.textureLocation != null && !this.renderGlobal.renderOverlayDamaged) {
            if (this.renderGlobal.renderOverlayEyes) {
               return;
            }

            i = yh.getBoundTexture();
            XH.getTextureManager().bindTexture(this.textureLocation);
         }

         if (this.modelUpdater != null) {
            this.modelUpdater.update();
         }

         boolean flag = this.scaleX != 1.0F || this.scaleY != 1.0F || this.scaleZ != 1.0F;
         yh.translate(this.offsetX, this.offsetY, this.offsetZ);
         int j;
         if (this.rotateAngleX == 0.0F && this.rotateAngleY == 0.0F && this.rotateAngleZ == 0.0F) {
            if (this.rotationPointX == 0.0F && this.rotationPointY == 0.0F && this.rotationPointZ == 0.0F) {
               if (flag) {
                  yh.scale(this.scaleX, this.scaleY, this.scaleZ);
               }

               yh.callList(this.displayList);
               if (this.childModels != null) {
                  for(j = 0; j < this.childModels.size(); ++j) {
                     ((ow)this.childModels.get(j)).render(scale);
                  }
               }

               if (flag) {
                  yh.scale(1.0F / this.scaleX, 1.0F / this.scaleY, 1.0F / this.scaleZ);
               }
            } else {
               yh.translate(this.rotationPointX * scale, this.rotationPointY * scale, this.rotationPointZ * scale);
               if (flag) {
                  yh.scale(this.scaleX, this.scaleY, this.scaleZ);
               }

               yh.callList(this.displayList);
               if (this.childModels != null) {
                  for(j = 0; j < this.childModels.size(); ++j) {
                     ((ow)this.childModels.get(j)).render(scale);
                  }
               }

               if (flag) {
                  yh.scale(1.0F / this.scaleX, 1.0F / this.scaleY, 1.0F / this.scaleZ);
               }

               yh.translate(-this.rotationPointX * scale, -this.rotationPointY * scale, -this.rotationPointZ * scale);
            }
         } else {
            yh.pushMatrix();
            yh.translate(this.rotationPointX * scale, this.rotationPointY * scale, this.rotationPointZ * scale);
            if (this.rotateAngleZ != 0.0F) {
               yh.rotate(this.rotateAngleZ * 57.295776F, 0.0F, 0.0F, 1.0F);
            }

            if (this.rotateAngleY != 0.0F) {
               yh.rotate(this.rotateAngleY * 57.295776F, 0.0F, 1.0F, 0.0F);
            }

            if (this.rotateAngleX != 0.0F) {
               yh.rotate(this.rotateAngleX * 57.295776F, 1.0F, 0.0F, 0.0F);
            }

            if (flag) {
               yh.scale(this.scaleX, this.scaleY, this.scaleZ);
            }

            yh.callList(this.displayList);
            if (this.childModels != null) {
               for(j = 0; j < this.childModels.size(); ++j) {
                  ((ow)this.childModels.get(j)).render(scale);
               }
            }

            yh.popMatrix();
         }

         yh.translate(-this.offsetX, -this.offsetY, -this.offsetZ);
         if (i != 0) {
            yh.bindTexture(i);
         }
      }

   }

   public void renderWithRotation(float scale) {
      if (!this.isHidden && this.showModel) {
         this.checkResetDisplayList();
         if (!this.compiled) {
            this.compileDisplayList(scale);
         }

         int i = 0;
         if (this.textureLocation != null && !this.renderGlobal.renderOverlayDamaged) {
            if (this.renderGlobal.renderOverlayEyes) {
               return;
            }

            i = yh.getBoundTexture();
            XH.getTextureManager().bindTexture(this.textureLocation);
         }

         if (this.modelUpdater != null) {
            this.modelUpdater.update();
         }

         boolean flag = this.scaleX != 1.0F || this.scaleY != 1.0F || this.scaleZ != 1.0F;
         yh.pushMatrix();
         yh.translate(this.rotationPointX * scale, this.rotationPointY * scale, this.rotationPointZ * scale);
         if (this.rotateAngleY != 0.0F) {
            yh.rotate(this.rotateAngleY * 57.295776F, 0.0F, 1.0F, 0.0F);
         }

         if (this.rotateAngleX != 0.0F) {
            yh.rotate(this.rotateAngleX * 57.295776F, 1.0F, 0.0F, 0.0F);
         }

         if (this.rotateAngleZ != 0.0F) {
            yh.rotate(this.rotateAngleZ * 57.295776F, 0.0F, 0.0F, 1.0F);
         }

         if (flag) {
            yh.scale(this.scaleX, this.scaleY, this.scaleZ);
         }

         yh.callList(this.displayList);
         if (this.childModels != null) {
            for(int j = 0; j < this.childModels.size(); ++j) {
               ((ow)this.childModels.get(j)).render(scale);
            }
         }

         yh.popMatrix();
         if (i != 0) {
            yh.bindTexture(i);
         }
      }

   }

   public void postRender(float scale) {
      if (!this.isHidden && this.showModel) {
         this.checkResetDisplayList();
         if (!this.compiled) {
            this.compileDisplayList(scale);
         }

         if (this.rotateAngleX == 0.0F && this.rotateAngleY == 0.0F && this.rotateAngleZ == 0.0F) {
            if (this.rotationPointX != 0.0F || this.rotationPointY != 0.0F || this.rotationPointZ != 0.0F) {
               yh.translate(this.rotationPointX * scale, this.rotationPointY * scale, this.rotationPointZ * scale);
            }
         } else {
            yh.translate(this.rotationPointX * scale, this.rotationPointY * scale, this.rotationPointZ * scale);
            if (this.rotateAngleZ != 0.0F) {
               yh.rotate(this.rotateAngleZ * 57.295776F, 0.0F, 0.0F, 1.0F);
            }

            if (this.rotateAngleY != 0.0F) {
               yh.rotate(this.rotateAngleY * 57.295776F, 0.0F, 1.0F, 0.0F);
            }

            if (this.rotateAngleX != 0.0F) {
               yh.rotate(this.rotateAngleX * 57.295776F, 1.0F, 0.0F, 0.0F);
            }
         }
      }

   }

   private void compileDisplayList(float scale) {
      if (this.displayList == 0) {
         this.displayList = xE.generateDisplayLists(1);
      }

      yh.glNewList(this.displayList, 4864);
      tN bufferbuilder = yN.getInstance().getBuffer();

      int j;
      for(j = 0; j < this.cubeList.size(); ++j) {
         ((nQ)this.cubeList.get(j)).render(bufferbuilder, scale);
      }

      for(j = 0; j < this.spriteList.size(); ++j) {
         bne modelsprite = (bne)this.spriteList.get(j);
         modelsprite.render(yN.getInstance(), scale);
      }

      yh.glEndList();
      this.compiled = true;
   }

   public ow setTextureSize(int textureWidthIn, int textureHeightIn) {
      this.textureWidth = (float)textureWidthIn;
      this.textureHeight = (float)textureHeightIn;
      return this;
   }

   public void addSprite(float p_addSprite_1_, float p_addSprite_2_, float p_addSprite_3_, int p_addSprite_4_, int p_addSprite_5_, int p_addSprite_6_, float p_addSprite_7_) {
      this.spriteList.add(new bne(this, this.textureOffsetX, this.textureOffsetY, p_addSprite_1_, p_addSprite_2_, p_addSprite_3_, p_addSprite_4_, p_addSprite_5_, p_addSprite_6_, p_addSprite_7_));
   }

   public boolean getCompiled() {
      return this.compiled;
   }

   public int getDisplayList() {
      return this.displayList;
   }

   private void checkResetDisplayList() {
      if (this.countResetDisplayList != bpq.countResetDisplayLists) {
         this.compiled = false;
         this.countResetDisplayList = bpq.countResetDisplayLists;
      }

   }

   public ResourceLocation getTextureLocation() {
      return this.textureLocation;
   }

   public void setTextureLocation(ResourceLocation p_setTextureLocation_1_) {
      this.textureLocation = p_setTextureLocation_1_;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String p_setId_1_) {
      this.id = p_setId_1_;
   }

   public void addBox(int[][] p_addBox_1_, float p_addBox_2_, float p_addBox_3_, float p_addBox_4_, float p_addBox_5_, float p_addBox_6_, float p_addBox_7_, float p_addBox_8_) {
      this.cubeList.add(new nQ(this, p_addBox_1_, p_addBox_2_, p_addBox_3_, p_addBox_4_, p_addBox_5_, p_addBox_6_, p_addBox_7_, p_addBox_8_, this.mirror));
   }

   public ow getChild(String p_getChild_1_) {
      if (p_getChild_1_ == null) {
         return null;
      } else {
         if (this.childModels != null) {
            for(int i = 0; i < this.childModels.size(); ++i) {
               ow modelrenderer = (ow)this.childModels.get(i);
               if (p_getChild_1_.equals(modelrenderer.getId())) {
                  return modelrenderer;
               }
            }
         }

         return null;
      }
   }

   public ow getChildDeep(String p_getChildDeep_1_) {
      if (p_getChildDeep_1_ == null) {
         return null;
      } else {
         ow modelrenderer = this.getChild(p_getChildDeep_1_);
         if (modelrenderer != null) {
            return modelrenderer;
         } else {
            if (this.childModels != null) {
               for(int i = 0; i < this.childModels.size(); ++i) {
                  ow modelrenderer1 = (ow)this.childModels.get(i);
                  ow modelrenderer2 = modelrenderer1.getChildDeep(p_getChildDeep_1_);
                  if (modelrenderer2 != null) {
                     return modelrenderer2;
                  }
               }
            }

            return null;
         }
      }
   }

   public void setModelUpdater(bjV p_setModelUpdater_1_) {
      this.modelUpdater = p_setModelUpdater_1_;
   }

   public String toString() {
      StringBuffer stringbuffer = new StringBuffer();
      stringbuffer.append("id: " + this.id + ", boxes: " + (this.cubeList != null ? this.cubeList.size() : null) + ", submodels: " + (this.childModels != null ? this.childModels.size() : null));
      return stringbuffer.toString();
   }
}
