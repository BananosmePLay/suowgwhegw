package neo;

public class yN {
   private final tN buffer;
   private final zV vboUploader = new zV();
   private static final yN INSTANCE = new yN(2097152);

   public static yN getInstance() {
      return INSTANCE;
   }

   public yN(int bufferSize) {
      this.buffer = new tN(bufferSize);
   }

   public void draw() {
      if (this.buffer.animatedSprites != null) {
         bpW.spritesRendered(this.buffer.animatedSprites);
      }

      this.buffer.finishDrawing();
      this.vboUploader.draw(this.buffer);
   }

   public tN getBuffer() {
      return this.buffer;
   }
}
