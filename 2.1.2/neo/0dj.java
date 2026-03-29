package neo;

import javax.vecmath.Vector3i;

public class 0dj {
   public 0dd scanner;
   public Vector3i startPos;
   public Vector3i finalPos;
   public boolean scanned;

   private static 0dd _In7oY5RFb/* $FF was: 6In7oY5RFb*/(0dj var0) {
      return var0.scanner;
   }

   public void scan() {
      i1ec8UQRtb(this).scan();
      BirWyFaOKI(this, (boolean)(8394 ^ -27763 ^ 15546 ^ -28676));
   }

   private static 0dd i1ec8UQRtb(0dj var0) {
      return var0.scanner;
   }

   private static void BirWyFaOKI(0dj var0, boolean var1) {
      var0.scanned = var1;
   }

   public boolean onUpdate(0cD player) {
      return (boolean)(FvBVL7z1Wy(this) != null && player != null ? 6In7oY5RFb(this).onUpdate(player) : 16293 ^ -9218 ^ 30496 ^ -27781);
   }

   public _dj/* $FF was: 0dj*/(Vector3i startPos, Vector3i finalPos, 0cC pbot) {
      this.startPos = startPos;
      this.finalPos = finalPos;
      this.scanner = new 0dc(startPos, finalPos, pbot.getWorld());
      this.scanned = (boolean)(5556 ^ -13892 ^ 22923 ^ -31357);
   }

   private static 0dd FvBVL7z1Wy(0dj var0) {
      return var0.scanner;
   }
}
