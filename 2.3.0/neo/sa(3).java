package neo;

abstract class sa {
   private sa() {
   }

   public rN rotateUV(rN p_188006_1_) {
      float f = p_188006_1_.getVertexU(p_188006_1_.getVertexRotatedRev(0));
      float f1 = p_188006_1_.getVertexV(p_188006_1_.getVertexRotatedRev(0));
      float f2 = p_188006_1_.getVertexU(p_188006_1_.getVertexRotatedRev(2));
      float f3 = p_188006_1_.getVertexV(p_188006_1_.getVertexRotatedRev(2));
      return this.makeRotatedUV(f, f1, f2, f3);
   }

   abstract rN makeRotatedUV(float var1, float var2, float var3, float var4);

   // $FF: synthetic method
   sa(Object x0) {
      this();
   }
}
