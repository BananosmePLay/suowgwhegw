package neo;

import net.minecraft.util.EnumFacing;

enum sj {
   UP(EnumFacing.UP, 0, -1),
   DOWN(EnumFacing.DOWN, 0, 1),
   LEFT(EnumFacing.EAST, -1, 0),
   RIGHT(EnumFacing.WEST, 1, 0);

   private final EnumFacing facing;
   private final int xOffset;
   private final int yOffset;

   private sj(EnumFacing facing, int p_i46215_4_, int p_i46215_5_) {
      this.facing = facing;
      this.xOffset = p_i46215_4_;
      this.yOffset = p_i46215_5_;
   }

   public EnumFacing getFacing() {
      return this.facing;
   }

   public int getXOffset() {
      return this.xOffset;
   }

   public int getYOffset() {
      return this.yOffset;
   }

   private boolean isHorizontal() {
      return this == DOWN || this == UP;
   }

   // $FF: synthetic method
   static boolean access$000(sj x0) {
      return x0.isHorizontal();
   }
}
