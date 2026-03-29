package neo;

public class yZ implements Comparable<yZ> {
   private final zd sprite;
   private final int width;
   private final int height;
   private final int mipmapLevelHolder;
   private boolean rotated;
   private float scaleFactor = 1.0F;

   public yZ(zd theTextureIn, int mipmapLevelHolderIn) {
      this.sprite = theTextureIn;
      this.width = theTextureIn.getIconWidth();
      this.height = theTextureIn.getIconHeight();
      this.mipmapLevelHolder = mipmapLevelHolderIn;
      this.rotated = zb.access$000(this.height, mipmapLevelHolderIn) > zb.access$000(this.width, mipmapLevelHolderIn);
   }

   public zd getAtlasSprite() {
      return this.sprite;
   }

   public int getWidth() {
      int i = this.rotated ? this.height : this.width;
      return zb.access$000((int)((float)i * this.scaleFactor), this.mipmapLevelHolder);
   }

   public int getHeight() {
      int i = this.rotated ? this.width : this.height;
      return zb.access$000((int)((float)i * this.scaleFactor), this.mipmapLevelHolder);
   }

   public void rotate() {
      this.rotated = !this.rotated;
   }

   public boolean isRotated() {
      return this.rotated;
   }

   public void setNewDimension(int p_94196_1_) {
      if (this.width > p_94196_1_ && this.height > p_94196_1_) {
         this.scaleFactor = (float)p_94196_1_ / (float)Math.min(this.width, this.height);
      }

   }

   public String toString() {
      return "Holder{width=" + this.width + ", height=" + this.height + ", name=" + this.sprite.getIconName() + '}';
   }

   public int compareTo(yZ p_compareTo_1_) {
      int i;
      if (this.getHeight() == p_compareTo_1_.getHeight()) {
         if (this.getWidth() == p_compareTo_1_.getWidth()) {
            if (this.sprite.getIconName() == null) {
               return p_compareTo_1_.sprite.getIconName() == null ? 0 : -1;
            }

            return this.sprite.getIconName().compareTo(p_compareTo_1_.sprite.getIconName());
         }

         i = this.getWidth() < p_compareTo_1_.getWidth() ? 1 : -1;
      } else {
         i = this.getHeight() < p_compareTo_1_.getHeight() ? 1 : -1;
      }

      return i;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public int compareTo(Object var1) {
      return this.compareTo((yZ)var1);
   }
}
