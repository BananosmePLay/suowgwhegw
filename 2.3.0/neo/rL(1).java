package neo;

import java.util.Arrays;

public class rL extends rK {
   private final zd texture;
   private final zd spriteOld;

   public rL(rK quad, zd textureIn) {
      super(Arrays.copyOf(quad.getVertexData(), quad.getVertexData().length), quad.tintIndex, sb.getFacingFromVertexData(quad.getVertexData()), textureIn, quad.applyDiffuseLighting, quad.format);
      this.texture = textureIn;
      this.format = quad.format;
      this.applyDiffuseLighting = quad.applyDiffuseLighting;
      this.spriteOld = quad.getSprite();
      this.remapQuad();
      this.fixVertexData();
   }

   private void remapQuad() {
      for(int i = 0; i < 4; ++i) {
         int j = this.format.getIntegerSize() * i;
         int k = this.format.getUvOffsetById(0) / 4;
         this.vertexData[j + k] = Float.floatToRawIntBits(this.texture.getInterpolatedU((double)this.spriteOld.getUnInterpolatedU(Float.intBitsToFloat(this.vertexData[j + k]))));
         this.vertexData[j + k + 1] = Float.floatToRawIntBits(this.texture.getInterpolatedV((double)this.spriteOld.getUnInterpolatedV(Float.intBitsToFloat(this.vertexData[j + k + 1]))));
      }

   }

   public zd getSprite() {
      return this.texture;
   }
}
