package neo;

import net.minecraft.util.IStringSerializable;

public enum hc implements IStringSerializable {
   STONE(0, hK.STONE, "stone"),
   SAND(1, hK.SAND, "sandstone", "sand"),
   WOOD(2, hK.WOOD, "wood_old", "wood"),
   COBBLESTONE(3, hK.STONE, "cobblestone", "cobble"),
   BRICK(4, hK.RED, "brick"),
   SMOOTHBRICK(5, hK.STONE, "stone_brick", "smoothStoneBrick"),
   NETHERBRICK(6, hK.NETHERRACK, "nether_brick", "netherBrick"),
   QUARTZ(7, hK.QUARTZ, "quartz");

   private static final hc[] META_LOOKUP = new hc[values().length];
   private final int meta;
   private final hK mapColor;
   private final String name;
   private final String translationKey;

   private hc(int p_i46381_3_, hK p_i46381_4_, String p_i46381_5_) {
      this(p_i46381_3_, p_i46381_4_, p_i46381_5_, p_i46381_5_);
   }

   private hc(int p_i46382_3_, hK p_i46382_4_, String p_i46382_5_, String p_i46382_6_) {
      this.meta = p_i46382_3_;
      this.mapColor = p_i46382_4_;
      this.name = p_i46382_5_;
      this.translationKey = p_i46382_6_;
   }

   public int getMetadata() {
      return this.meta;
   }

   public hK getMapColor() {
      return this.mapColor;
   }

   public String toString() {
      return this.name;
   }

   public static hc byMetadata(int meta) {
      if (meta < 0 || meta >= META_LOOKUP.length) {
         meta = 0;
      }

      return META_LOOKUP[meta];
   }

   public String getName() {
      return this.name;
   }

   public String getTranslationKey() {
      return this.translationKey;
   }

   static {
      hc[] var0 = values();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         hc blockstoneslab$enumtype = var0[var2];
         META_LOOKUP[blockstoneslab$enumtype.getMetadata()] = blockstoneslab$enumtype;
      }

   }
}
