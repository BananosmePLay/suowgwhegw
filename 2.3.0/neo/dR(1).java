package neo;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import java.util.Collection;
import javax.annotation.Nullable;
import net.minecraft.util.IStringSerializable;

public enum dR implements IStringSerializable {
   DANDELION(dP.YELLOW, 0, "dandelion"),
   POPPY(dP.RED, 0, "poppy"),
   BLUE_ORCHID(dP.RED, 1, "blue_orchid", "blueOrchid"),
   ALLIUM(dP.RED, 2, "allium"),
   HOUSTONIA(dP.RED, 3, "houstonia"),
   RED_TULIP(dP.RED, 4, "red_tulip", "tulipRed"),
   ORANGE_TULIP(dP.RED, 5, "orange_tulip", "tulipOrange"),
   WHITE_TULIP(dP.RED, 6, "white_tulip", "tulipWhite"),
   PINK_TULIP(dP.RED, 7, "pink_tulip", "tulipPink"),
   OXEYE_DAISY(dP.RED, 8, "oxeye_daisy", "oxeyeDaisy");

   private static final dR[][] TYPES_FOR_BLOCK = new dR[dP.values().length][];
   private final dP blockType;
   private final int meta;
   private final String name;
   private final String translationKey;

   private dR(dP blockType, int meta, String name) {
      this(blockType, meta, name, name);
   }

   private dR(dP blockType, int meta, String name, String unlocalizedName) {
      this.blockType = blockType;
      this.meta = meta;
      this.name = name;
      this.translationKey = unlocalizedName;
   }

   public dP getBlockType() {
      return this.blockType;
   }

   public int getMeta() {
      return this.meta;
   }

   public static dR getType(dP blockType, int meta) {
      dR[] ablockflower$enumflowertype = TYPES_FOR_BLOCK[blockType.ordinal()];
      if (meta < 0 || meta >= ablockflower$enumflowertype.length) {
         meta = 0;
      }

      return ablockflower$enumflowertype[meta];
   }

   public static dR[] getTypes(dP flowerColor) {
      return TYPES_FOR_BLOCK[flowerColor.ordinal()];
   }

   public String toString() {
      return this.name;
   }

   public String getName() {
      return this.name;
   }

   public String getTranslationKey() {
      return this.translationKey;
   }

   static {
      dP[] var0 = dP.values();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         final dP blockflower$enumflowercolor = var0[var2];
         Collection<dR> collection = Collections2.filter(Lists.newArrayList(values()), new Predicate<dR>() {
            public boolean apply(@Nullable dR p_apply_1_) {
               return p_apply_1_.getBlockType() == blockflower$enumflowercolor;
            }

            // $FF: synthetic method
            // $FF: bridge method
            public boolean apply(@Nullable Object var1) {
               return this.apply((dR)var1);
            }
         });
         TYPES_FOR_BLOCK[blockflower$enumflowercolor.ordinal()] = (dR[])((dR[])collection.toArray(new dR[collection.size()]));
      }

   }
}
