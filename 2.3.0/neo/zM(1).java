package neo;

import java.nio.ByteBuffer;

public class zM {
   private int glBufferId;
   private final zO vertexFormat;
   private int count;
   private boc vboRegion;
   private bob vboRange;
   private int drawMode;

   public zM(zO vertexFormatIn) {
      this.vertexFormat = vertexFormatIn;
      this.glBufferId = ys.glGenBuffers();
   }

   public void bindBuffer() {
      ys.glBindBuffer(ys.GL_ARRAY_BUFFER, this.glBufferId);
   }

   public void bufferData(ByteBuffer data) {
      if (this.vboRegion != null) {
         this.vboRegion.bufferData(data, this.vboRange);
      } else {
         this.bindBuffer();
         ys.glBufferData(ys.GL_ARRAY_BUFFER, data, 35044);
         this.unbindBuffer();
         this.count = data.limit() / this.vertexFormat.getSize();
      }

   }

   public void drawArrays(int mode) {
      if (this.drawMode > 0) {
         mode = this.drawMode;
      }

      if (this.vboRegion != null) {
         this.vboRegion.drawArrays(mode, this.vboRange);
      } else {
         yh.glDrawArrays(mode, 0, this.count);
      }

   }

   public void unbindBuffer() {
      ys.glBindBuffer(ys.GL_ARRAY_BUFFER, 0);
   }

   public void deleteGlBuffers() {
      if (this.glBufferId >= 0) {
         ys.glDeleteBuffers(this.glBufferId);
         this.glBufferId = -1;
      }

   }

   public void setVboRegion(boc p_setVboRegion_1_) {
      if (p_setVboRegion_1_ != null) {
         this.deleteGlBuffers();
         this.vboRegion = p_setVboRegion_1_;
         this.vboRange = new bob();
      }

   }

   public boc getVboRegion() {
      return this.vboRegion;
   }

   public bob getVboRange() {
      return this.vboRange;
   }

   public int getDrawMode() {
      return this.drawMode;
   }

   public void setDrawMode(int p_setDrawMode_1_) {
      this.drawMode = p_setDrawMode_1_;
   }
}
