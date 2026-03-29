package neo;

public class zS extends zV {
   private zM vertexBuffer;

   public zS() {
   }

   public void draw(tN bufferBuilderIn) {
      if (bufferBuilderIn.getDrawMode() == 7 && XH.isQuadsToTriangles()) {
         bufferBuilderIn.quadsToTriangles();
         this.vertexBuffer.setDrawMode(bufferBuilderIn.getDrawMode());
      }

      this.vertexBuffer.bufferData(bufferBuilderIn.getByteBuffer());
      bufferBuilderIn.reset();
   }

   public void setVertexBuffer(zM vertexBufferIn) {
      this.vertexBuffer = vertexBufferIn;
   }
}
