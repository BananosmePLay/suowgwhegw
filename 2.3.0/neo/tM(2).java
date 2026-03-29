package neo;

public class tM {
   private final int[] stateRawBuffer;
   private final zO stateVertexFormat;
   private zd[] stateQuadSprites;
   // $FF: synthetic field
   final tN this$0;

   public tM(tN this$0, int[] p_i4_2_, zO p_i4_3_, zd[] p_i4_4_) {
      this.this$0 = this$0;
      this.stateRawBuffer = p_i4_2_;
      this.stateVertexFormat = p_i4_3_;
      this.stateQuadSprites = p_i4_4_;
   }

   public tM(tN this$0, int[] buffer, zO format) {
      this.this$0 = this$0;
      this.stateRawBuffer = buffer;
      this.stateVertexFormat = format;
   }

   public int[] getRawBuffer() {
      return this.stateRawBuffer;
   }

   public int getVertexCount() {
      return this.stateRawBuffer.length / this.stateVertexFormat.getIntegerSize();
   }

   public zO getVertexFormat() {
      return this.stateVertexFormat;
   }

   // $FF: synthetic method
   static zd[] access$000(tM x0) {
      return x0.stateQuadSprites;
   }
}
