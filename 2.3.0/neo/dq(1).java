package neo;

import net.minecraft.util.IStringSerializable;

public enum dq implements IStringSerializable {
   SUNFLOWER(0, "sunflower"),
   SYRINGA(1, "syringa"),
   GRASS(2, "double_grass", "grass"),
   FERN(3, "double_fern", "fern"),
   ROSE(4, "double_rose", "rose"),
   PAEONIA(5, "paeonia");

   private static final dq[] META_LOOKUP = new dq[values().length];
   private final int meta;
   private final String name;
   private final String translationKey;

   private dq(int meta, String name) {
      this(meta, name, name);
   }

   private dq(int meta, String name, String unlocalizedName) {
      this.meta = meta;
      this.name = name;
      this.translationKey = unlocalizedName;
   }

   public int getMeta() {
      return this.meta;
   }

   public String toString() {
      return this.name;
   }

   public static dq byMetadata(int meta) {
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
      dq[] var0 = values();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         dq blockdoubleplant$enumplanttype = var0[var2];
         META_LOOKUP[blockdoubleplant$enumplanttype.getMeta()] = blockdoubleplant$enumplanttype;
      }

   }
}
