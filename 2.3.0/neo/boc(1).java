package neo;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import net.minecraft.util.BlockRenderLayer;

public class boc {
   private BlockRenderLayer layer = null;
   private int glBufferId = ys.glGenBuffers();
   private int capacity = 4096;
   private int positionTop = 0;
   private int sizeUsed;
   private bqB<bob> rangeList = new bqB();
   private bob compactRangeLast = null;
   private IntBuffer bufferIndexVertex;
   private IntBuffer bufferCountVertex;
   private int drawMode;
   private final int vertexBytes;

   public boc(BlockRenderLayer layer) {
      this.bufferIndexVertex = XH.createDirectIntBuffer(this.capacity);
      this.bufferCountVertex = XH.createDirectIntBuffer(this.capacity);
      this.drawMode = 7;
      this.vertexBytes = zK.BLOCK.getSize();
      this.layer = layer;
      this.bindBuffer();
      long i = this.toBytes(this.capacity);
      ys.glBufferData(ys.GL_ARRAY_BUFFER, i, ys.GL_STATIC_DRAW);
      this.unbindBuffer();
   }

   public void bufferData(ByteBuffer data, bob range) {
      int i = range.getPosition();
      int j = range.getSize();
      int k = this.toVertex((long)data.limit());
      if (k <= 0) {
         if (i >= 0) {
            range.setPosition(-1);
            range.setSize(0);
            this.rangeList.remove(range.getNode());
            this.sizeUsed -= j;
         }
      } else {
         if (k > j) {
            range.setPosition(this.positionTop);
            range.setSize(k);
            this.positionTop += k;
            if (i >= 0) {
               this.rangeList.remove(range.getNode());
            }

            this.rangeList.addLast(range.getNode());
         }

         range.setSize(k);
         this.sizeUsed += k - j;
         this.checkVboSize(range.getPositionNext());
         long l = this.toBytes(range.getPosition());
         this.bindBuffer();
         ys.glBufferSubData(ys.GL_ARRAY_BUFFER, l, data);
         this.unbindBuffer();
         if (this.positionTop > this.sizeUsed * 11 / 10) {
            this.compactRanges(1);
         }
      }

   }

   private void compactRanges(int countMax) {
      if (!this.rangeList.isEmpty()) {
         bob vborange = this.compactRangeLast;
         if (vborange == null || !this.rangeList.contains(vborange.getNode())) {
            vborange = (bob)this.rangeList.getFirst().getItem();
         }

         int i = vborange.getPosition();
         bob vborange1 = vborange.getPrev();
         if (vborange1 == null) {
            i = 0;
         } else {
            i = vborange1.getPositionNext();
         }

         int j = 0;

         while(vborange != null && j < countMax) {
            ++j;
            if (vborange.getPosition() == i) {
               i += vborange.getSize();
               vborange = vborange.getNext();
            } else {
               int k = vborange.getPosition() - i;
               if (vborange.getSize() <= k) {
                  this.copyVboData(vborange.getPosition(), i, vborange.getSize());
                  vborange.setPosition(i);
                  i += vborange.getSize();
                  vborange = vborange.getNext();
               } else {
                  this.checkVboSize(this.positionTop + vborange.getSize());
                  this.copyVboData(vborange.getPosition(), this.positionTop, vborange.getSize());
                  vborange.setPosition(this.positionTop);
                  this.positionTop += vborange.getSize();
                  bob vborange2 = vborange.getNext();
                  this.rangeList.remove(vborange.getNode());
                  this.rangeList.addLast(vborange.getNode());
                  vborange = vborange2;
               }
            }
         }

         if (vborange == null) {
            this.positionTop = ((bob)this.rangeList.getLast().getItem()).getPositionNext();
         }

         this.compactRangeLast = vborange;
      }

   }

   private void checkRanges() {
      int i = 0;
      int j = 0;

      for(bob vborange = (bob)this.rangeList.getFirst().getItem(); vborange != null; vborange = vborange.getNext()) {
         ++i;
         j += vborange.getSize();
         if (vborange.getPosition() < 0 || vborange.getSize() <= 0 || vborange.getPositionNext() > this.positionTop) {
            throw new RuntimeException("Invalid range: " + vborange);
         }

         bob vborange1 = vborange.getPrev();
         if (vborange1 != null && vborange.getPosition() < vborange1.getPositionNext()) {
            throw new RuntimeException("Invalid range: " + vborange);
         }

         bob vborange2 = vborange.getNext();
         if (vborange2 != null && vborange.getPositionNext() > vborange2.getPosition()) {
            throw new RuntimeException("Invalid range: " + vborange);
         }
      }

      if (i != this.rangeList.getSize()) {
         throw new RuntimeException("Invalid count: " + i + " <> " + this.rangeList.getSize());
      } else if (j != this.sizeUsed) {
         throw new RuntimeException("Invalid size: " + j + " <> " + this.sizeUsed);
      }
   }

   private void checkVboSize(int sizeMin) {
      if (this.capacity < sizeMin) {
         this.expandVbo(sizeMin);
      }

   }

   private void copyVboData(int posFrom, int posTo, int size) {
      long i = this.toBytes(posFrom);
      long j = this.toBytes(posTo);
      long k = this.toBytes(size);
      ys.glBindBuffer(ys.GL_COPY_READ_BUFFER, this.glBufferId);
      ys.glBindBuffer(ys.GL_COPY_WRITE_BUFFER, this.glBufferId);
      ys.glCopyBufferSubData(ys.GL_COPY_READ_BUFFER, ys.GL_COPY_WRITE_BUFFER, i, j, k);
      XH.checkGlError("Copy VBO range");
      ys.glBindBuffer(ys.GL_COPY_READ_BUFFER, 0);
      ys.glBindBuffer(ys.GL_COPY_WRITE_BUFFER, 0);
   }

   private void expandVbo(int sizeMin) {
      int i;
      for(i = this.capacity * 6 / 4; i < sizeMin; i = i * 6 / 4) {
      }

      long j = this.toBytes(this.capacity);
      long k = this.toBytes(i);
      int l = ys.glGenBuffers();
      ys.glBindBuffer(ys.GL_ARRAY_BUFFER, l);
      ys.glBufferData(ys.GL_ARRAY_BUFFER, k, ys.GL_STATIC_DRAW);
      XH.checkGlError("Expand VBO");
      ys.glBindBuffer(ys.GL_ARRAY_BUFFER, 0);
      ys.glBindBuffer(ys.GL_COPY_READ_BUFFER, this.glBufferId);
      ys.glBindBuffer(ys.GL_COPY_WRITE_BUFFER, l);
      ys.glCopyBufferSubData(ys.GL_COPY_READ_BUFFER, ys.GL_COPY_WRITE_BUFFER, 0L, 0L, j);
      XH.checkGlError("Copy VBO: " + k);
      ys.glBindBuffer(ys.GL_COPY_READ_BUFFER, 0);
      ys.glBindBuffer(ys.GL_COPY_WRITE_BUFFER, 0);
      ys.glDeleteBuffers(this.glBufferId);
      this.bufferIndexVertex = XH.createDirectIntBuffer(i);
      this.bufferCountVertex = XH.createDirectIntBuffer(i);
      this.glBufferId = l;
      this.capacity = i;
   }

   public void bindBuffer() {
      ys.glBindBuffer(ys.GL_ARRAY_BUFFER, this.glBufferId);
   }

   public void drawArrays(int drawMode, bob range) {
      if (this.drawMode != drawMode) {
         if (this.bufferIndexVertex.position() > 0) {
            throw new IllegalArgumentException("Mixed region draw modes: " + this.drawMode + " != " + drawMode);
         }

         this.drawMode = drawMode;
      }

      this.bufferIndexVertex.put(range.getPosition());
      this.bufferCountVertex.put(range.getSize());
   }

   public void finishDraw(zI vboRenderList) {
      this.bindBuffer();
      vboRenderList.setupArrayPointers();
      this.bufferIndexVertex.flip();
      this.bufferCountVertex.flip();
      yh.glMultiDrawArrays(this.drawMode, this.bufferIndexVertex, this.bufferCountVertex);
      this.bufferIndexVertex.limit(this.bufferIndexVertex.capacity());
      this.bufferCountVertex.limit(this.bufferCountVertex.capacity());
      if (this.positionTop > this.sizeUsed * 11 / 10) {
         this.compactRanges(1);
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

   private long toBytes(int vertex) {
      return (long)vertex * (long)this.vertexBytes;
   }

   private int toVertex(long bytes) {
      return (int)(bytes / (long)this.vertexBytes);
   }

   public int getPositionTop() {
      return this.positionTop;
   }
}
