package neo;

import net.minecraft.util.IStringSerializable;

public enum gA implements IStringSerializable {
   STONE(0, "stone") {
      public in getModelBlock() {
         return Nk.STONE.getDefaultState().withProperty(gZ.VARIANT, gY.STONE);
      }
   },
   COBBLESTONE(1, "cobblestone", "cobble") {
      public in getModelBlock() {
         return Nk.COBBLESTONE.getDefaultState();
      }
   },
   STONEBRICK(2, "stone_brick", "brick") {
      public in getModelBlock() {
         return Nk.STONEBRICK.getDefaultState().withProperty(hb.VARIANT, ha.DEFAULT);
      }
   },
   MOSSY_STONEBRICK(3, "mossy_brick", "mossybrick") {
      public in getModelBlock() {
         return Nk.STONEBRICK.getDefaultState().withProperty(hb.VARIANT, ha.MOSSY);
      }
   },
   CRACKED_STONEBRICK(4, "cracked_brick", "crackedbrick") {
      public in getModelBlock() {
         return Nk.STONEBRICK.getDefaultState().withProperty(hb.VARIANT, ha.CRACKED);
      }
   },
   CHISELED_STONEBRICK(5, "chiseled_brick", "chiseledbrick") {
      public in getModelBlock() {
         return Nk.STONEBRICK.getDefaultState().withProperty(hb.VARIANT, ha.CHISELED);
      }
   };

   private static final gA[] META_LOOKUP = new gA[values().length];
   private final int meta;
   private final String name;
   private final String translationKey;

   private gA(int meta, String name) {
      this(meta, name, name);
   }

   private gA(int meta, String name, String unlocalizedName) {
      this.meta = meta;
      this.name = name;
      this.translationKey = unlocalizedName;
   }

   public int getMetadata() {
      return this.meta;
   }

   public String toString() {
      return this.name;
   }

   public static gA byMetadata(int meta) {
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

   public abstract in getModelBlock();

   public static gA forModelBlock(in model) {
      gA[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         gA blocksilverfish$enumtype = var1[var3];
         if (model == blocksilverfish$enumtype.getModelBlock()) {
            return blocksilverfish$enumtype;
         }
      }

      return STONE;
   }

   // $FF: synthetic method
   gA(int x2, String x3, Object x4) {
      this(x2, x3);
   }

   // $FF: synthetic method
   gA(int x2, String x3, String x4, Object x5) {
      this(x2, x3, x4);
   }

   static {
      gA[] var0 = values();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         gA blocksilverfish$enumtype = var0[var2];
         META_LOOKUP[blocksilverfish$enumtype.getMetadata()] = blocksilverfish$enumtype;
      }

   }
}
