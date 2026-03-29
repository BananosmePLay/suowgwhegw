package neo;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;

public class We {
   private static final List<Wd<Wf>> POTION_TYPE_CONVERSIONS = Lists.newArrayList();
   private static final List<Wd<OL>> POTION_ITEM_CONVERSIONS = Lists.newArrayList();
   private static final List<NS> POTION_ITEMS = Lists.newArrayList();
   private static final Predicate<Qy> IS_POTION_ITEM = new Predicate<Qy>() {
      public boolean apply(Qy p_apply_1_) {
         Iterator var2 = We.POTION_ITEMS.iterator();

         NS ingredient;
         do {
            if (!var2.hasNext()) {
               return false;
            }

            ingredient = (NS)var2.next();
         } while(!ingredient.apply(p_apply_1_));

         return true;
      }

      // $FF: synthetic method
      // $FF: bridge method
      public boolean apply(Object var1) {
         return this.apply((Qy)var1);
      }
   };

   public We() {
   }

   public static boolean isReagent(Qy stack) {
      return isItemConversionReagent(stack) || isTypeConversionReagent(stack);
   }

   protected static boolean isItemConversionReagent(Qy stack) {
      int i = 0;

      for(int j = POTION_ITEM_CONVERSIONS.size(); i < j; ++i) {
         if (((Wd)POTION_ITEM_CONVERSIONS.get(i)).reagent.apply(stack)) {
            return true;
         }
      }

      return false;
   }

   protected static boolean isTypeConversionReagent(Qy stack) {
      int i = 0;

      for(int j = POTION_TYPE_CONVERSIONS.size(); i < j; ++i) {
         if (((Wd)POTION_TYPE_CONVERSIONS.get(i)).reagent.apply(stack)) {
            return true;
         }
      }

      return false;
   }

   public static boolean hasConversions(Qy input, Qy reagent) {
      if (!IS_POTION_ITEM.apply(input)) {
         return false;
      } else {
         return hasItemConversions(input, reagent) || hasTypeConversions(input, reagent);
      }
   }

   protected static boolean hasItemConversions(Qy input, Qy reagent) {
      OL item = input.getItem();
      int i = 0;

      for(int j = POTION_ITEM_CONVERSIONS.size(); i < j; ++i) {
         Wd<OL> mixpredicate = (Wd)POTION_ITEM_CONVERSIONS.get(i);
         if (mixpredicate.input == item && mixpredicate.reagent.apply(reagent)) {
            return true;
         }
      }

      return false;
   }

   protected static boolean hasTypeConversions(Qy input, Qy reagent) {
      Wf potiontype = Wg.getPotionFromItem(input);
      int i = 0;

      for(int j = POTION_TYPE_CONVERSIONS.size(); i < j; ++i) {
         Wd<Wf> mixpredicate = (Wd)POTION_TYPE_CONVERSIONS.get(i);
         if (mixpredicate.input == potiontype && mixpredicate.reagent.apply(reagent)) {
            return true;
         }
      }

      return false;
   }

   public static Qy doReaction(Qy reagent, Qy potionIn) {
      if (!potionIn.isEmpty()) {
         Wf potiontype = Wg.getPotionFromItem(potionIn);
         OL item = potionIn.getItem();
         int i = 0;

         int k;
         Wd mixpredicate1;
         for(k = POTION_ITEM_CONVERSIONS.size(); i < k; ++i) {
            mixpredicate1 = (Wd)POTION_ITEM_CONVERSIONS.get(i);
            if (mixpredicate1.input == item && mixpredicate1.reagent.apply(reagent)) {
               return Wg.addPotionToItemStack(new Qy((OL)mixpredicate1.output), potiontype);
            }
         }

         i = 0;

         for(k = POTION_TYPE_CONVERSIONS.size(); i < k; ++i) {
            mixpredicate1 = (Wd)POTION_TYPE_CONVERSIONS.get(i);
            if (mixpredicate1.input == potiontype && mixpredicate1.reagent.apply(reagent)) {
               return Wg.addPotionToItemStack(new Qy(item), (Wf)mixpredicate1.output);
            }
         }
      }

      return potionIn;
   }

   public static void init() {
      addContainer(NK.POTIONITEM);
      addContainer(NK.SPLASH_POTION);
      addContainer(NK.LINGERING_POTION);
      addContainerRecipe(NK.POTIONITEM, NK.GUNPOWDER, NK.SPLASH_POTION);
      addContainerRecipe(NK.SPLASH_POTION, NK.DRAGON_BREATH, NK.LINGERING_POTION);
      addMix(NN.WATER, NK.SPECKLED_MELON, NN.MUNDANE);
      addMix(NN.WATER, NK.GHAST_TEAR, NN.MUNDANE);
      addMix(NN.WATER, NK.RABBIT_FOOT, NN.MUNDANE);
      addMix(NN.WATER, NK.BLAZE_POWDER, NN.MUNDANE);
      addMix(NN.WATER, NK.SPIDER_EYE, NN.MUNDANE);
      addMix(NN.WATER, NK.SUGAR, NN.MUNDANE);
      addMix(NN.WATER, NK.MAGMA_CREAM, NN.MUNDANE);
      addMix(NN.WATER, NK.GLOWSTONE_DUST, NN.THICK);
      addMix(NN.WATER, NK.REDSTONE, NN.MUNDANE);
      addMix(NN.WATER, NK.NETHER_WART, NN.AWKWARD);
      addMix(NN.AWKWARD, NK.GOLDEN_CARROT, NN.NIGHT_VISION);
      addMix(NN.NIGHT_VISION, NK.REDSTONE, NN.LONG_NIGHT_VISION);
      addMix(NN.NIGHT_VISION, NK.FERMENTED_SPIDER_EYE, NN.INVISIBILITY);
      addMix(NN.LONG_NIGHT_VISION, NK.FERMENTED_SPIDER_EYE, NN.LONG_INVISIBILITY);
      addMix(NN.INVISIBILITY, NK.REDSTONE, NN.LONG_INVISIBILITY);
      addMix(NN.AWKWARD, NK.MAGMA_CREAM, NN.FIRE_RESISTANCE);
      addMix(NN.FIRE_RESISTANCE, NK.REDSTONE, NN.LONG_FIRE_RESISTANCE);
      addMix(NN.AWKWARD, NK.RABBIT_FOOT, NN.LEAPING);
      addMix(NN.LEAPING, NK.REDSTONE, NN.LONG_LEAPING);
      addMix(NN.LEAPING, NK.GLOWSTONE_DUST, NN.STRONG_LEAPING);
      addMix(NN.LEAPING, NK.FERMENTED_SPIDER_EYE, NN.SLOWNESS);
      addMix(NN.LONG_LEAPING, NK.FERMENTED_SPIDER_EYE, NN.LONG_SLOWNESS);
      addMix(NN.SLOWNESS, NK.REDSTONE, NN.LONG_SLOWNESS);
      addMix(NN.SWIFTNESS, NK.FERMENTED_SPIDER_EYE, NN.SLOWNESS);
      addMix(NN.LONG_SWIFTNESS, NK.FERMENTED_SPIDER_EYE, NN.LONG_SLOWNESS);
      addMix(NN.AWKWARD, NK.SUGAR, NN.SWIFTNESS);
      addMix(NN.SWIFTNESS, NK.REDSTONE, NN.LONG_SWIFTNESS);
      addMix(NN.SWIFTNESS, NK.GLOWSTONE_DUST, NN.STRONG_SWIFTNESS);
      addMix(NN.AWKWARD, NS.fromStacks(new Qy(NK.FISH, 1, PD.PUFFERFISH.getMetadata())), NN.WATER_BREATHING);
      addMix(NN.WATER_BREATHING, NK.REDSTONE, NN.LONG_WATER_BREATHING);
      addMix(NN.AWKWARD, NK.SPECKLED_MELON, NN.HEALING);
      addMix(NN.HEALING, NK.GLOWSTONE_DUST, NN.STRONG_HEALING);
      addMix(NN.HEALING, NK.FERMENTED_SPIDER_EYE, NN.HARMING);
      addMix(NN.STRONG_HEALING, NK.FERMENTED_SPIDER_EYE, NN.STRONG_HARMING);
      addMix(NN.HARMING, NK.GLOWSTONE_DUST, NN.STRONG_HARMING);
      addMix(NN.POISON, NK.FERMENTED_SPIDER_EYE, NN.HARMING);
      addMix(NN.LONG_POISON, NK.FERMENTED_SPIDER_EYE, NN.HARMING);
      addMix(NN.STRONG_POISON, NK.FERMENTED_SPIDER_EYE, NN.STRONG_HARMING);
      addMix(NN.AWKWARD, NK.SPIDER_EYE, NN.POISON);
      addMix(NN.POISON, NK.REDSTONE, NN.LONG_POISON);
      addMix(NN.POISON, NK.GLOWSTONE_DUST, NN.STRONG_POISON);
      addMix(NN.AWKWARD, NK.GHAST_TEAR, NN.REGENERATION);
      addMix(NN.REGENERATION, NK.REDSTONE, NN.LONG_REGENERATION);
      addMix(NN.REGENERATION, NK.GLOWSTONE_DUST, NN.STRONG_REGENERATION);
      addMix(NN.AWKWARD, NK.BLAZE_POWDER, NN.STRENGTH);
      addMix(NN.STRENGTH, NK.REDSTONE, NN.LONG_STRENGTH);
      addMix(NN.STRENGTH, NK.GLOWSTONE_DUST, NN.STRONG_STRENGTH);
      addMix(NN.WATER, NK.FERMENTED_SPIDER_EYE, NN.WEAKNESS);
      addMix(NN.WEAKNESS, NK.REDSTONE, NN.LONG_WEAKNESS);
   }

   private static void addContainerRecipe(Qe p_193355_0_, OL p_193355_1_, Qe p_193355_2_) {
      POTION_ITEM_CONVERSIONS.add(new Wd(p_193355_0_, NS.fromItems(p_193355_1_), p_193355_2_));
   }

   private static void addContainer(Qe p_193354_0_) {
      POTION_ITEMS.add(NS.fromItems(p_193354_0_));
   }

   private static void addMix(Wf p_193357_0_, OL p_193357_1_, Wf p_193357_2_) {
      addMix(p_193357_0_, NS.fromItems(p_193357_1_), p_193357_2_);
   }

   private static void addMix(Wf p_193356_0_, NS p_193356_1_, Wf p_193356_2_) {
      POTION_TYPE_CONVERSIONS.add(new Wd(p_193356_0_, p_193356_1_, p_193356_2_));
   }
}
