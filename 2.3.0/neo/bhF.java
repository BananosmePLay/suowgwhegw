package neo;

import net.minecraft.util.math.MathHelper;

public enum bhF {
   PLAYER(false),
   FRAME(true),
   RED_MARKER(false),
   BLUE_MARKER(false),
   TARGET_X(true),
   TARGET_POINT(true),
   PLAYER_OFF_MAP(false),
   PLAYER_OFF_LIMITS(false),
   MANSION(true, 5393476),
   MONUMENT(true, 3830373);

   private final byte icon;
   private final boolean renderedOnFrame;
   private final int mapColor;

   private bhF(boolean p_i47343_3_) {
      this(p_i47343_3_, -1);
   }

   private bhF(boolean p_i47344_3_, int p_i47344_4_) {
      this.icon = (byte)this.ordinal();
      this.renderedOnFrame = p_i47344_3_;
      this.mapColor = p_i47344_4_;
   }

   public byte getIcon() {
      return this.icon;
   }

   public boolean isRenderedOnFrame() {
      return this.renderedOnFrame;
   }

   public boolean hasMapColor() {
      return this.mapColor >= 0;
   }

   public int getMapColor() {
      return this.mapColor;
   }

   public static bhF byIcon(byte p_191159_0_) {
      return values()[MathHelper.clamp(p_191159_0_, 0, values().length - 1)];
   }
}
