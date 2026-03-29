package neo;

import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;

public class NQ {
   private static final NQ SMELTING_BASE = new NQ();
   private final Map<Qy, Qy> smeltingList = Maps.newHashMap();
   private final Map<Qy, Float> experienceList = Maps.newHashMap();

   public static NQ instance() {
      return SMELTING_BASE;
   }

   private NQ() {
      this.addSmeltingRecipeForBlock(Nk.IRON_ORE, new Qy(NK.IRON_INGOT), 0.7F);
      this.addSmeltingRecipeForBlock(Nk.GOLD_ORE, new Qy(NK.GOLD_INGOT), 1.0F);
      this.addSmeltingRecipeForBlock(Nk.DIAMOND_ORE, new Qy(NK.DIAMOND), 1.0F);
      this.addSmeltingRecipeForBlock(Nk.SAND, new Qy(Nk.GLASS), 0.1F);
      this.addSmelting(NK.PORKCHOP, new Qy(NK.COOKED_PORKCHOP), 0.35F);
      this.addSmelting(NK.BEEF, new Qy(NK.COOKED_BEEF), 0.35F);
      this.addSmelting(NK.CHICKEN, new Qy(NK.COOKED_CHICKEN), 0.35F);
      this.addSmelting(NK.RABBIT, new Qy(NK.COOKED_RABBIT), 0.35F);
      this.addSmelting(NK.MUTTON, new Qy(NK.COOKED_MUTTON), 0.35F);
      this.addSmeltingRecipeForBlock(Nk.COBBLESTONE, new Qy(Nk.STONE), 0.1F);
      this.addSmeltingRecipe(new Qy(Nk.STONEBRICK, 1, hb.DEFAULT_META), new Qy(Nk.STONEBRICK, 1, hb.CRACKED_META), 0.1F);
      this.addSmelting(NK.CLAY_BALL, new Qy(NK.BRICK), 0.3F);
      this.addSmeltingRecipeForBlock(Nk.CLAY, new Qy(Nk.HARDENED_CLAY), 0.35F);
      this.addSmeltingRecipeForBlock(Nk.CACTUS, new Qy(NK.DYE, 1, Om.GREEN.getDyeDamage()), 0.2F);
      this.addSmeltingRecipeForBlock(Nk.LOG, new Qy(NK.COAL, 1, 1), 0.15F);
      this.addSmeltingRecipeForBlock(Nk.LOG2, new Qy(NK.COAL, 1, 1), 0.15F);
      this.addSmeltingRecipeForBlock(Nk.EMERALD_ORE, new Qy(NK.EMERALD), 1.0F);
      this.addSmelting(NK.POTATO, new Qy(NK.BAKED_POTATO), 0.35F);
      this.addSmeltingRecipeForBlock(Nk.NETHERRACK, new Qy(NK.NETHERBRICK), 0.1F);
      this.addSmeltingRecipe(new Qy(Nk.SPONGE, 1, 1), new Qy(Nk.SPONGE, 1, 0), 0.15F);
      this.addSmelting(NK.CHORUS_FRUIT, new Qy(NK.CHORUS_FRUIT_POPPED), 0.1F);
      PD[] var1 = PD.values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         PD itemfishfood$fishtype = var1[var3];
         if (itemfishfood$fishtype.canCook()) {
            this.addSmeltingRecipe(new Qy(NK.FISH, 1, itemfishfood$fishtype.getMetadata()), new Qy(NK.COOKED_FISH, 1, itemfishfood$fishtype.getMetadata()), 0.35F);
         }
      }

      this.addSmeltingRecipeForBlock(Nk.COAL_ORE, new Qy(NK.COAL), 0.1F);
      this.addSmeltingRecipeForBlock(Nk.REDSTONE_ORE, new Qy(NK.REDSTONE), 0.7F);
      this.addSmeltingRecipeForBlock(Nk.LAPIS_ORE, new Qy(NK.DYE, 1, Om.BLUE.getDyeDamage()), 0.2F);
      this.addSmeltingRecipeForBlock(Nk.QUARTZ_ORE, new Qy(NK.QUARTZ), 0.2F);
      this.addSmelting(NK.CHAINMAIL_HELMET, new Qy(NK.IRON_NUGGET), 0.1F);
      this.addSmelting(NK.CHAINMAIL_CHESTPLATE, new Qy(NK.IRON_NUGGET), 0.1F);
      this.addSmelting(NK.CHAINMAIL_LEGGINGS, new Qy(NK.IRON_NUGGET), 0.1F);
      this.addSmelting(NK.CHAINMAIL_BOOTS, new Qy(NK.IRON_NUGGET), 0.1F);
      this.addSmelting(NK.IRON_PICKAXE, new Qy(NK.IRON_NUGGET), 0.1F);
      this.addSmelting(NK.IRON_SHOVEL, new Qy(NK.IRON_NUGGET), 0.1F);
      this.addSmelting(NK.IRON_AXE, new Qy(NK.IRON_NUGGET), 0.1F);
      this.addSmelting(NK.IRON_HOE, new Qy(NK.IRON_NUGGET), 0.1F);
      this.addSmelting(NK.IRON_SWORD, new Qy(NK.IRON_NUGGET), 0.1F);
      this.addSmelting(NK.IRON_HELMET, new Qy(NK.IRON_NUGGET), 0.1F);
      this.addSmelting(NK.IRON_CHESTPLATE, new Qy(NK.IRON_NUGGET), 0.1F);
      this.addSmelting(NK.IRON_LEGGINGS, new Qy(NK.IRON_NUGGET), 0.1F);
      this.addSmelting(NK.IRON_BOOTS, new Qy(NK.IRON_NUGGET), 0.1F);
      this.addSmelting(NK.IRON_HORSE_ARMOR, new Qy(NK.IRON_NUGGET), 0.1F);
      this.addSmelting(NK.GOLDEN_PICKAXE, new Qy(NK.GOLD_NUGGET), 0.1F);
      this.addSmelting(NK.GOLDEN_SHOVEL, new Qy(NK.GOLD_NUGGET), 0.1F);
      this.addSmelting(NK.GOLDEN_AXE, new Qy(NK.GOLD_NUGGET), 0.1F);
      this.addSmelting(NK.GOLDEN_HOE, new Qy(NK.GOLD_NUGGET), 0.1F);
      this.addSmelting(NK.GOLDEN_SWORD, new Qy(NK.GOLD_NUGGET), 0.1F);
      this.addSmelting(NK.GOLDEN_HELMET, new Qy(NK.GOLD_NUGGET), 0.1F);
      this.addSmelting(NK.GOLDEN_CHESTPLATE, new Qy(NK.GOLD_NUGGET), 0.1F);
      this.addSmelting(NK.GOLDEN_LEGGINGS, new Qy(NK.GOLD_NUGGET), 0.1F);
      this.addSmelting(NK.GOLDEN_BOOTS, new Qy(NK.GOLD_NUGGET), 0.1F);
      this.addSmelting(NK.GOLDEN_HORSE_ARMOR, new Qy(NK.GOLD_NUGGET), 0.1F);
      this.addSmeltingRecipe(new Qy(Nk.STAINED_HARDENED_CLAY, 1, Om.WHITE.getMetadata()), new Qy(Nk.WHITE_GLAZED_TERRACOTTA), 0.1F);
      this.addSmeltingRecipe(new Qy(Nk.STAINED_HARDENED_CLAY, 1, Om.ORANGE.getMetadata()), new Qy(Nk.ORANGE_GLAZED_TERRACOTTA), 0.1F);
      this.addSmeltingRecipe(new Qy(Nk.STAINED_HARDENED_CLAY, 1, Om.MAGENTA.getMetadata()), new Qy(Nk.MAGENTA_GLAZED_TERRACOTTA), 0.1F);
      this.addSmeltingRecipe(new Qy(Nk.STAINED_HARDENED_CLAY, 1, Om.LIGHT_BLUE.getMetadata()), new Qy(Nk.LIGHT_BLUE_GLAZED_TERRACOTTA), 0.1F);
      this.addSmeltingRecipe(new Qy(Nk.STAINED_HARDENED_CLAY, 1, Om.YELLOW.getMetadata()), new Qy(Nk.YELLOW_GLAZED_TERRACOTTA), 0.1F);
      this.addSmeltingRecipe(new Qy(Nk.STAINED_HARDENED_CLAY, 1, Om.LIME.getMetadata()), new Qy(Nk.LIME_GLAZED_TERRACOTTA), 0.1F);
      this.addSmeltingRecipe(new Qy(Nk.STAINED_HARDENED_CLAY, 1, Om.PINK.getMetadata()), new Qy(Nk.PINK_GLAZED_TERRACOTTA), 0.1F);
      this.addSmeltingRecipe(new Qy(Nk.STAINED_HARDENED_CLAY, 1, Om.GRAY.getMetadata()), new Qy(Nk.GRAY_GLAZED_TERRACOTTA), 0.1F);
      this.addSmeltingRecipe(new Qy(Nk.STAINED_HARDENED_CLAY, 1, Om.SILVER.getMetadata()), new Qy(Nk.SILVER_GLAZED_TERRACOTTA), 0.1F);
      this.addSmeltingRecipe(new Qy(Nk.STAINED_HARDENED_CLAY, 1, Om.CYAN.getMetadata()), new Qy(Nk.CYAN_GLAZED_TERRACOTTA), 0.1F);
      this.addSmeltingRecipe(new Qy(Nk.STAINED_HARDENED_CLAY, 1, Om.PURPLE.getMetadata()), new Qy(Nk.PURPLE_GLAZED_TERRACOTTA), 0.1F);
      this.addSmeltingRecipe(new Qy(Nk.STAINED_HARDENED_CLAY, 1, Om.BLUE.getMetadata()), new Qy(Nk.BLUE_GLAZED_TERRACOTTA), 0.1F);
      this.addSmeltingRecipe(new Qy(Nk.STAINED_HARDENED_CLAY, 1, Om.BROWN.getMetadata()), new Qy(Nk.BROWN_GLAZED_TERRACOTTA), 0.1F);
      this.addSmeltingRecipe(new Qy(Nk.STAINED_HARDENED_CLAY, 1, Om.GREEN.getMetadata()), new Qy(Nk.GREEN_GLAZED_TERRACOTTA), 0.1F);
      this.addSmeltingRecipe(new Qy(Nk.STAINED_HARDENED_CLAY, 1, Om.RED.getMetadata()), new Qy(Nk.RED_GLAZED_TERRACOTTA), 0.1F);
      this.addSmeltingRecipe(new Qy(Nk.STAINED_HARDENED_CLAY, 1, Om.BLACK.getMetadata()), new Qy(Nk.BLACK_GLAZED_TERRACOTTA), 0.1F);
   }

   public void addSmeltingRecipeForBlock(co input, Qy stack, float experience) {
      this.addSmelting(OL.getItemFromBlock(input), stack, experience);
   }

   public void addSmelting(OL input, Qy stack, float experience) {
      this.addSmeltingRecipe(new Qy(input, 1, 32767), stack, experience);
   }

   public void addSmeltingRecipe(Qy input, Qy stack, float experience) {
      this.smeltingList.put(input, stack);
      this.experienceList.put(stack, experience);
   }

   public Qy getSmeltingResult(Qy stack) {
      Iterator var2 = this.smeltingList.entrySet().iterator();

      Map.Entry entry;
      do {
         if (!var2.hasNext()) {
            return Qy.EMPTY;
         }

         entry = (Map.Entry)var2.next();
      } while(!this.compareItemStacks(stack, (Qy)entry.getKey()));

      return (Qy)entry.getValue();
   }

   private boolean compareItemStacks(Qy stack1, Qy stack2) {
      return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
   }

   public Map<Qy, Qy> getSmeltingList() {
      return this.smeltingList;
   }

   public float getSmeltingExperience(Qy stack) {
      Iterator var2 = this.experienceList.entrySet().iterator();

      Map.Entry entry;
      do {
         if (!var2.hasNext()) {
            return 0.0F;
         }

         entry = (Map.Entry)var2.next();
      } while(!this.compareItemStacks(stack, (Qy)entry.getKey()));

      return (Float)entry.getValue();
   }
}
