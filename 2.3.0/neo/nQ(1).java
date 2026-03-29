package neo;

public class nQ {
   private final oS[] vertexPositions;
   private final oT[] quadList;
   public final float posX1;
   public final float posY1;
   public final float posZ1;
   public final float posX2;
   public final float posY2;
   public final float posZ2;
   public String boxName;

   public nQ(ow renderer, int texU, int texV, float x, float y, float z, int dx, int dy, int dz, float delta) {
      this(renderer, texU, texV, x, y, z, dx, dy, dz, delta, renderer.mirror);
   }

   public nQ(ow p_i3_1_, int[][] p_i3_2_, float p_i3_3_, float p_i3_4_, float p_i3_5_, float p_i3_6_, float p_i3_7_, float p_i3_8_, float p_i3_9_, boolean p_i3_10_) {
      this.posX1 = p_i3_3_;
      this.posY1 = p_i3_4_;
      this.posZ1 = p_i3_5_;
      this.posX2 = p_i3_3_ + p_i3_6_;
      this.posY2 = p_i3_4_ + p_i3_7_;
      this.posZ2 = p_i3_5_ + p_i3_8_;
      this.vertexPositions = new oS[8];
      this.quadList = new oT[6];
      float f = p_i3_3_ + p_i3_6_;
      float f1 = p_i3_4_ + p_i3_7_;
      float f2 = p_i3_5_ + p_i3_8_;
      p_i3_3_ -= p_i3_9_;
      p_i3_4_ -= p_i3_9_;
      p_i3_5_ -= p_i3_9_;
      f += p_i3_9_;
      f1 += p_i3_9_;
      f2 += p_i3_9_;
      if (p_i3_10_) {
         float f3 = f;
         f = p_i3_3_;
         p_i3_3_ = f3;
      }

      oS positiontexturevertex7 = new oS(p_i3_3_, p_i3_4_, p_i3_5_, 0.0F, 0.0F);
      oS positiontexturevertex = new oS(f, p_i3_4_, p_i3_5_, 0.0F, 8.0F);
      oS positiontexturevertex1 = new oS(f, f1, p_i3_5_, 8.0F, 8.0F);
      oS positiontexturevertex2 = new oS(p_i3_3_, f1, p_i3_5_, 8.0F, 0.0F);
      oS positiontexturevertex3 = new oS(p_i3_3_, p_i3_4_, f2, 0.0F, 0.0F);
      oS positiontexturevertex4 = new oS(f, p_i3_4_, f2, 0.0F, 8.0F);
      oS positiontexturevertex5 = new oS(f, f1, f2, 8.0F, 8.0F);
      oS positiontexturevertex6 = new oS(p_i3_3_, f1, f2, 8.0F, 0.0F);
      this.vertexPositions[0] = positiontexturevertex7;
      this.vertexPositions[1] = positiontexturevertex;
      this.vertexPositions[2] = positiontexturevertex1;
      this.vertexPositions[3] = positiontexturevertex2;
      this.vertexPositions[4] = positiontexturevertex3;
      this.vertexPositions[5] = positiontexturevertex4;
      this.vertexPositions[6] = positiontexturevertex5;
      this.vertexPositions[7] = positiontexturevertex6;
      this.quadList[0] = this.makeTexturedQuad(new oS[]{positiontexturevertex4, positiontexturevertex, positiontexturevertex1, positiontexturevertex5}, p_i3_2_[4], false, p_i3_1_.textureWidth, p_i3_1_.textureHeight);
      this.quadList[1] = this.makeTexturedQuad(new oS[]{positiontexturevertex7, positiontexturevertex3, positiontexturevertex6, positiontexturevertex2}, p_i3_2_[5], false, p_i3_1_.textureWidth, p_i3_1_.textureHeight);
      this.quadList[2] = this.makeTexturedQuad(new oS[]{positiontexturevertex4, positiontexturevertex3, positiontexturevertex7, positiontexturevertex}, p_i3_2_[1], true, p_i3_1_.textureWidth, p_i3_1_.textureHeight);
      this.quadList[3] = this.makeTexturedQuad(new oS[]{positiontexturevertex1, positiontexturevertex2, positiontexturevertex6, positiontexturevertex5}, p_i3_2_[0], true, p_i3_1_.textureWidth, p_i3_1_.textureHeight);
      this.quadList[4] = this.makeTexturedQuad(new oS[]{positiontexturevertex, positiontexturevertex7, positiontexturevertex2, positiontexturevertex1}, p_i3_2_[2], false, p_i3_1_.textureWidth, p_i3_1_.textureHeight);
      this.quadList[5] = this.makeTexturedQuad(new oS[]{positiontexturevertex3, positiontexturevertex4, positiontexturevertex5, positiontexturevertex6}, p_i3_2_[3], false, p_i3_1_.textureWidth, p_i3_1_.textureHeight);
      if (p_i3_10_) {
         oT[] var22 = this.quadList;
         int var23 = var22.length;

         for(int var24 = 0; var24 < var23; ++var24) {
            oT texturedquad = var22[var24];
            texturedquad.flipFace();
         }
      }

   }

   private oT makeTexturedQuad(oS[] p_makeTexturedQuad_1_, int[] p_makeTexturedQuad_2_, boolean p_makeTexturedQuad_3_, float p_makeTexturedQuad_4_, float p_makeTexturedQuad_5_) {
      if (p_makeTexturedQuad_2_ == null) {
         return null;
      } else {
         return p_makeTexturedQuad_3_ ? new oT(p_makeTexturedQuad_1_, p_makeTexturedQuad_2_[2], p_makeTexturedQuad_2_[3], p_makeTexturedQuad_2_[0], p_makeTexturedQuad_2_[1], p_makeTexturedQuad_4_, p_makeTexturedQuad_5_) : new oT(p_makeTexturedQuad_1_, p_makeTexturedQuad_2_[0], p_makeTexturedQuad_2_[1], p_makeTexturedQuad_2_[2], p_makeTexturedQuad_2_[3], p_makeTexturedQuad_4_, p_makeTexturedQuad_5_);
      }
   }

   public nQ(ow renderer, int texU, int texV, float x, float y, float z, int dx, int dy, int dz, float delta, boolean mirror) {
      this.posX1 = x;
      this.posY1 = y;
      this.posZ1 = z;
      this.posX2 = x + (float)dx;
      this.posY2 = y + (float)dy;
      this.posZ2 = z + (float)dz;
      this.vertexPositions = new oS[8];
      this.quadList = new oT[6];
      float f = x + (float)dx;
      float f1 = y + (float)dy;
      float f2 = z + (float)dz;
      x -= delta;
      y -= delta;
      z -= delta;
      f += delta;
      f1 += delta;
      f2 += delta;
      if (mirror) {
         float f3 = f;
         f = x;
         x = f3;
      }

      oS positiontexturevertex7 = new oS(x, y, z, 0.0F, 0.0F);
      oS positiontexturevertex = new oS(f, y, z, 0.0F, 8.0F);
      oS positiontexturevertex1 = new oS(f, f1, z, 8.0F, 8.0F);
      oS positiontexturevertex2 = new oS(x, f1, z, 8.0F, 0.0F);
      oS positiontexturevertex3 = new oS(x, y, f2, 0.0F, 0.0F);
      oS positiontexturevertex4 = new oS(f, y, f2, 0.0F, 8.0F);
      oS positiontexturevertex5 = new oS(f, f1, f2, 8.0F, 8.0F);
      oS positiontexturevertex6 = new oS(x, f1, f2, 8.0F, 0.0F);
      this.vertexPositions[0] = positiontexturevertex7;
      this.vertexPositions[1] = positiontexturevertex;
      this.vertexPositions[2] = positiontexturevertex1;
      this.vertexPositions[3] = positiontexturevertex2;
      this.vertexPositions[4] = positiontexturevertex3;
      this.vertexPositions[5] = positiontexturevertex4;
      this.vertexPositions[6] = positiontexturevertex5;
      this.vertexPositions[7] = positiontexturevertex6;
      this.quadList[0] = new oT(new oS[]{positiontexturevertex4, positiontexturevertex, positiontexturevertex1, positiontexturevertex5}, texU + dz + dx, texV + dz, texU + dz + dx + dz, texV + dz + dy, renderer.textureWidth, renderer.textureHeight);
      this.quadList[1] = new oT(new oS[]{positiontexturevertex7, positiontexturevertex3, positiontexturevertex6, positiontexturevertex2}, texU, texV + dz, texU + dz, texV + dz + dy, renderer.textureWidth, renderer.textureHeight);
      this.quadList[2] = new oT(new oS[]{positiontexturevertex4, positiontexturevertex3, positiontexturevertex7, positiontexturevertex}, texU + dz, texV, texU + dz + dx, texV + dz, renderer.textureWidth, renderer.textureHeight);
      this.quadList[3] = new oT(new oS[]{positiontexturevertex1, positiontexturevertex2, positiontexturevertex6, positiontexturevertex5}, texU + dz + dx, texV + dz, texU + dz + dx + dx, texV, renderer.textureWidth, renderer.textureHeight);
      this.quadList[4] = new oT(new oS[]{positiontexturevertex, positiontexturevertex7, positiontexturevertex2, positiontexturevertex1}, texU + dz, texV + dz, texU + dz + dx, texV + dz + dy, renderer.textureWidth, renderer.textureHeight);
      this.quadList[5] = new oT(new oS[]{positiontexturevertex3, positiontexturevertex4, positiontexturevertex5, positiontexturevertex6}, texU + dz + dx + dz, texV + dz, texU + dz + dx + dz + dx, texV + dz + dy, renderer.textureWidth, renderer.textureHeight);
      if (mirror) {
         oT[] var23 = this.quadList;
         int var24 = var23.length;

         for(int var25 = 0; var25 < var24; ++var25) {
            oT texturedquad = var23[var25];
            texturedquad.flipFace();
         }
      }

   }

   public void render(tN renderer, float scale) {
      oT[] var3 = this.quadList;
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         oT texturedquad = var3[var5];
         if (texturedquad != null) {
            texturedquad.draw(renderer, scale);
         }
      }

   }

   public nQ setBoxName(String name) {
      this.boxName = name;
      return this;
   }
}
