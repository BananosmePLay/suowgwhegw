package neo;

import net.minecraft.util.math.Vec3d;

public class oS {
   public Vec3d vector3D;
   public float texturePositionX;
   public float texturePositionY;

   public oS(float p_i1158_1_, float p_i1158_2_, float p_i1158_3_, float p_i1158_4_, float p_i1158_5_) {
      this(new Vec3d((double)p_i1158_1_, (double)p_i1158_2_, (double)p_i1158_3_), p_i1158_4_, p_i1158_5_);
   }

   public oS setTexturePosition(float p_78240_1_, float p_78240_2_) {
      return new oS(this, p_78240_1_, p_78240_2_);
   }

   public oS(oS textureVertex, float texturePositionXIn, float texturePositionYIn) {
      this.vector3D = textureVertex.vector3D;
      this.texturePositionX = texturePositionXIn;
      this.texturePositionY = texturePositionYIn;
   }

   public oS(Vec3d p_i47091_1_, float p_i47091_2_, float p_i47091_3_) {
      this.vector3D = p_i47091_1_;
      this.texturePositionX = p_i47091_2_;
      this.texturePositionY = p_i47091_3_;
   }
}
