package neo;

public class bhG {
   private final bhF type;
   private byte x;
   private byte y;
   private byte rotation;

   public bhG(bhF typeIn, byte xIn, byte yIn, byte rotationIn) {
      this.type = typeIn;
      this.x = xIn;
      this.y = yIn;
      this.rotation = rotationIn;
   }

   public byte getImage() {
      return this.type.getIcon();
   }

   public bhF getType() {
      return this.type;
   }

   public byte getX() {
      return this.x;
   }

   public byte getY() {
      return this.y;
   }

   public byte getRotation() {
      return this.rotation;
   }

   public boolean renderOnFrame() {
      return this.type.isRenderedOnFrame();
   }

   public boolean equals(Object p_equals_1_) {
      if (this == p_equals_1_) {
         return true;
      } else if (!(p_equals_1_ instanceof bhG)) {
         return false;
      } else {
         bhG mapdecoration = (bhG)p_equals_1_;
         if (this.type != mapdecoration.type) {
            return false;
         } else if (this.rotation != mapdecoration.rotation) {
            return false;
         } else if (this.x != mapdecoration.x) {
            return false;
         } else {
            return this.y == mapdecoration.y;
         }
      }
   }

   public int hashCode() {
      int i = this.type.getIcon();
      i = 31 * i + this.x;
      i = 31 * i + this.y;
      i = 31 * i + this.rotation;
      return i;
   }
}
