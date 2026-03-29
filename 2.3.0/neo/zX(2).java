package neo;

public class zX {
   private final int frameIndex;
   private final int frameTime;

   public zX(int frameIndexIn) {
      this(frameIndexIn, -1);
   }

   public zX(int frameIndexIn, int frameTimeIn) {
      this.frameIndex = frameIndexIn;
      this.frameTime = frameTimeIn;
   }

   public boolean hasNoTime() {
      return this.frameTime == -1;
   }

   public int getFrameTime() {
      return this.frameTime;
   }

   public int getFrameIndex() {
      return this.frameIndex;
   }
}
