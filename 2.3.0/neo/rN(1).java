package neo;

import javax.annotation.Nullable;

public class rN {
   public float[] uvs;
   public final int rotation;

   public rN(@Nullable float[] uvsIn, int rotationIn) {
      this.uvs = uvsIn;
      this.rotation = rotationIn;
   }

   public float getVertexU(int p_178348_1_) {
      if (this.uvs == null) {
         throw new NullPointerException("uvs");
      } else {
         int i = this.getVertexRotated(p_178348_1_);
         return i != 0 && i != 1 ? this.uvs[2] : this.uvs[0];
      }
   }

   public float getVertexV(int p_178346_1_) {
      if (this.uvs == null) {
         throw new NullPointerException("uvs");
      } else {
         int i = this.getVertexRotated(p_178346_1_);
         return i != 0 && i != 3 ? this.uvs[3] : this.uvs[1];
      }
   }

   private int getVertexRotated(int p_178347_1_) {
      return (p_178347_1_ + this.rotation / 90) % 4;
   }

   public int getVertexRotatedRev(int p_178345_1_) {
      return (p_178345_1_ + (4 - this.rotation / 90)) % 4;
   }

   public void setUvs(float[] uvsIn) {
      if (this.uvs == null) {
         this.uvs = uvsIn;
      }

   }
}
