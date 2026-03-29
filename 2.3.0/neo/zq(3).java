package neo;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class zq extends zF<Yk> {
   private static final ResourceLocation[] TEXTURES;
   private nJ model = new nJ();
   private int version;

   public zq() {
      this.version = this.model.getModelVersion();
   }

   public void render(Yk te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
      if (this.version != this.model.getModelVersion()) {
         this.model = new nJ();
         this.version = this.model.getModelVersion();
      }

      boolean flag = te.getWorld() != null;
      boolean flag1 = flag ? te.isHeadPiece() : true;
      Om enumdyecolor = te != null ? te.getColor() : Om.RED;
      int i = flag ? te.getBlockMetadata() & 3 : 0;
      if (destroyStage >= 0) {
         this.bindTexture(DESTROY_STAGES[destroyStage]);
         yh.matrixMode(5890);
         yh.pushMatrix();
         yh.scale(4.0F, 4.0F, 1.0F);
         yh.translate(0.0625F, 0.0625F, 0.0625F);
         yh.matrixMode(5888);
      } else {
         ResourceLocation resourcelocation = TEXTURES[enumdyecolor.getMetadata()];
         if (resourcelocation != null) {
            this.bindTexture(resourcelocation);
         }
      }

      if (flag) {
         this.renderPiece(flag1, x, y, z, i, alpha);
      } else {
         yh.pushMatrix();
         this.renderPiece(true, x, y, z, i, alpha);
         this.renderPiece(false, x, y, z - 1.0, i, alpha);
         yh.popMatrix();
      }

      if (destroyStage >= 0) {
         yh.matrixMode(5890);
         yh.popMatrix();
         yh.matrixMode(5888);
      }

   }

   private void renderPiece(boolean p_193847_1_, double x, double y, double z, int p_193847_8_, float alpha) {
      this.model.preparePiece(p_193847_1_);
      yh.pushMatrix();
      float f = 0.0F;
      float f1 = 0.0F;
      float f2 = 0.0F;
      if (p_193847_8_ == EnumFacing.NORTH.getHorizontalIndex()) {
         f = 0.0F;
      } else if (p_193847_8_ == EnumFacing.SOUTH.getHorizontalIndex()) {
         f = 180.0F;
         f1 = 1.0F;
         f2 = 1.0F;
      } else if (p_193847_8_ == EnumFacing.WEST.getHorizontalIndex()) {
         f = -90.0F;
         f2 = 1.0F;
      } else if (p_193847_8_ == EnumFacing.EAST.getHorizontalIndex()) {
         f = 90.0F;
         f1 = 1.0F;
      }

      yh.translate((float)x + f1, (float)y + 0.5625F, (float)z + f2);
      yh.rotate(90.0F, 1.0F, 0.0F, 0.0F);
      yh.rotate(f, 0.0F, 0.0F, 1.0F);
      yh.enableRescaleNormal();
      yh.pushMatrix();
      this.model.render();
      yh.popMatrix();
      yh.color(1.0F, 1.0F, 1.0F, alpha);
      yh.popMatrix();
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void render(Yg var1, double var2, double var4, double var6, float var8, int var9, float var10) {
      this.render((Yk)var1, var2, var4, var6, var8, var9, var10);
   }

   static {
      Om[] aenumdyecolor = Om.values();
      TEXTURES = new ResourceLocation[aenumdyecolor.length];
      Om[] var1 = aenumdyecolor;
      int var2 = aenumdyecolor.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         Om enumdyecolor = var1[var3];
         TEXTURES[enumdyecolor.getMetadata()] = new ResourceLocation("textures/entity/bed/" + enumdyecolor.getDyeColorName() + ".png");
      }

   }
}
