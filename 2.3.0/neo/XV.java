package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;

public class XV {
   protected static final Map<String, XQ> ID_TO_STAT_MAP = Maps.newHashMap();
   public static final List<XQ> ALL_STATS = Lists.newArrayList();
   public static final List<XQ> BASIC_STATS = Lists.newArrayList();
   public static final List<XS> USE_ITEM_STATS = Lists.newArrayList();
   public static final List<XS> MINE_BLOCK_STATS = Lists.newArrayList();
   public static final XQ LEAVE_GAME = (new XR("stat.leaveGame", new TextComponentTranslation("stat.leaveGame", new Object[0]))).initIndependentStat().registerStat();
   public static final XQ PLAY_ONE_MINUTE;
   public static final XQ TIME_SINCE_DEATH;
   public static final XQ SNEAK_TIME;
   public static final XQ WALK_ONE_CM;
   public static final XQ CROUCH_ONE_CM;
   public static final XQ SPRINT_ONE_CM;
   public static final XQ SWIM_ONE_CM;
   public static final XQ FALL_ONE_CM;
   public static final XQ CLIMB_ONE_CM;
   public static final XQ FLY_ONE_CM;
   public static final XQ DIVE_ONE_CM;
   public static final XQ MINECART_ONE_CM;
   public static final XQ BOAT_ONE_CM;
   public static final XQ PIG_ONE_CM;
   public static final XQ HORSE_ONE_CM;
   public static final XQ AVIATE_ONE_CM;
   public static final XQ JUMP;
   public static final XQ DROP;
   public static final XQ DAMAGE_DEALT;
   public static final XQ DAMAGE_TAKEN;
   public static final XQ DEATHS;
   public static final XQ MOB_KILLS;
   public static final XQ ANIMALS_BRED;
   public static final XQ PLAYER_KILLS;
   public static final XQ FISH_CAUGHT;
   public static final XQ TALKED_TO_VILLAGER;
   public static final XQ TRADED_WITH_VILLAGER;
   public static final XQ CAKE_SLICES_EATEN;
   public static final XQ CAULDRON_FILLED;
   public static final XQ CAULDRON_USED;
   public static final XQ ARMOR_CLEANED;
   public static final XQ BANNER_CLEANED;
   public static final XQ BREWINGSTAND_INTERACTION;
   public static final XQ BEACON_INTERACTION;
   public static final XQ DROPPER_INSPECTED;
   public static final XQ HOPPER_INSPECTED;
   public static final XQ DISPENSER_INSPECTED;
   public static final XQ NOTEBLOCK_PLAYED;
   public static final XQ NOTEBLOCK_TUNED;
   public static final XQ FLOWER_POTTED;
   public static final XQ TRAPPED_CHEST_TRIGGERED;
   public static final XQ ENDERCHEST_OPENED;
   public static final XQ ITEM_ENCHANTED;
   public static final XQ RECORD_PLAYED;
   public static final XQ FURNACE_INTERACTION;
   public static final XQ CRAFTING_TABLE_INTERACTION;
   public static final XQ CHEST_OPENED;
   public static final XQ SLEEP_IN_BED;
   public static final XQ OPEN_SHULKER_BOX;
   private static final XQ[] BLOCKS_STATS;
   private static final XQ[] CRAFTS_STATS;
   private static final XQ[] OBJECT_USE_STATS;
   private static final XQ[] OBJECT_BREAK_STATS;
   private static final XQ[] OBJECTS_PICKED_UP_STATS;
   private static final XQ[] OBJECTS_DROPPED_STATS;

   public XV() {
   }

   @Nullable
   public static XQ getBlockStats(co blockIn) {
      return BLOCKS_STATS[co.getIdFromBlock(blockIn)];
   }

   @Nullable
   public static XQ getCraftStats(OL itemIn) {
      return CRAFTS_STATS[OL.getIdFromItem(itemIn)];
   }

   @Nullable
   public static XQ getObjectUseStats(OL itemIn) {
      return OBJECT_USE_STATS[OL.getIdFromItem(itemIn)];
   }

   @Nullable
   public static XQ getObjectBreakStats(OL itemIn) {
      return OBJECT_BREAK_STATS[OL.getIdFromItem(itemIn)];
   }

   @Nullable
   public static XQ getObjectsPickedUpStats(OL itemIn) {
      return OBJECTS_PICKED_UP_STATS[OL.getIdFromItem(itemIn)];
   }

   @Nullable
   public static XQ getDroppedObjectStats(OL itemIn) {
      return OBJECTS_DROPPED_STATS[OL.getIdFromItem(itemIn)];
   }

   public static void init() {
      initMiningStats();
      initStats();
      initItemDepleteStats();
      initCraftableStats();
      initPickedUpAndDroppedStats();
   }

   private static void initCraftableStats() {
      Set<OL> set = Sets.newHashSet();
      Iterator var1 = NP.REGISTRY.iterator();

      while(var1.hasNext()) {
         NT irecipe = (NT)var1.next();
         Qy itemstack = irecipe.getRecipeOutput();
         if (!itemstack.isEmpty()) {
            set.add(irecipe.getRecipeOutput().getItem());
         }
      }

      var1 = NQ.instance().getSmeltingList().values().iterator();

      while(var1.hasNext()) {
         Qy itemstack1 = (Qy)var1.next();
         set.add(itemstack1.getItem());
      }

      var1 = set.iterator();

      while(var1.hasNext()) {
         OL item = (OL)var1.next();
         if (item != null) {
            int i = OL.getIdFromItem(item);
            String s = getItemName(item);
            if (s != null) {
               CRAFTS_STATS[i] = (new XS("stat.craftItem.", s, new TextComponentTranslation("stat.craftItem", new Object[]{(new Qy(item)).getTextComponent()}), item)).registerStat();
            }
         }
      }

      replaceAllSimilarBlocks(CRAFTS_STATS);
   }

   private static void initMiningStats() {
      Iterator var0 = co.REGISTRY.iterator();

      while(var0.hasNext()) {
         co block = (co)var0.next();
         OL item = OL.getItemFromBlock(block);
         if (item != NK.AIR) {
            int i = co.getIdFromBlock(block);
            String s = getItemName(item);
            if (s != null && block.getEnableStats()) {
               BLOCKS_STATS[i] = (new XS("stat.mineBlock.", s, new TextComponentTranslation("stat.mineBlock", new Object[]{(new Qy(block)).getTextComponent()}), item)).registerStat();
               MINE_BLOCK_STATS.add((XS)BLOCKS_STATS[i]);
            }
         }
      }

      replaceAllSimilarBlocks(BLOCKS_STATS);
   }

   private static void initStats() {
      Iterator var0 = OL.REGISTRY.iterator();

      while(var0.hasNext()) {
         OL item = (OL)var0.next();
         if (item != null) {
            int i = OL.getIdFromItem(item);
            String s = getItemName(item);
            if (s != null) {
               OBJECT_USE_STATS[i] = (new XS("stat.useItem.", s, new TextComponentTranslation("stat.useItem", new Object[]{(new Qy(item)).getTextComponent()}), item)).registerStat();
               if (!(item instanceof OX)) {
                  USE_ITEM_STATS.add((XS)OBJECT_USE_STATS[i]);
               }
            }
         }
      }

      replaceAllSimilarBlocks(OBJECT_USE_STATS);
   }

   private static void initItemDepleteStats() {
      Iterator var0 = OL.REGISTRY.iterator();

      while(var0.hasNext()) {
         OL item = (OL)var0.next();
         if (item != null) {
            int i = OL.getIdFromItem(item);
            String s = getItemName(item);
            if (s != null && item.isDamageable()) {
               OBJECT_BREAK_STATS[i] = (new XS("stat.breakItem.", s, new TextComponentTranslation("stat.breakItem", new Object[]{(new Qy(item)).getTextComponent()}), item)).registerStat();
            }
         }
      }

      replaceAllSimilarBlocks(OBJECT_BREAK_STATS);
   }

   private static void initPickedUpAndDroppedStats() {
      Iterator var0 = OL.REGISTRY.iterator();

      while(var0.hasNext()) {
         OL item = (OL)var0.next();
         if (item != null) {
            int i = OL.getIdFromItem(item);
            String s = getItemName(item);
            if (s != null) {
               OBJECTS_PICKED_UP_STATS[i] = (new XS("stat.pickup.", s, new TextComponentTranslation("stat.pickup", new Object[]{(new Qy(item)).getTextComponent()}), item)).registerStat();
               OBJECTS_DROPPED_STATS[i] = (new XS("stat.drop.", s, new TextComponentTranslation("stat.drop", new Object[]{(new Qy(item)).getTextComponent()}), item)).registerStat();
            }
         }
      }

      replaceAllSimilarBlocks(OBJECT_BREAK_STATS);
   }

   private static String getItemName(OL itemIn) {
      ResourceLocation resourcelocation = (ResourceLocation)OL.REGISTRY.getNameForObject(itemIn);
      return resourcelocation != null ? resourcelocation.toString().replace(':', '.') : null;
   }

   private static void replaceAllSimilarBlocks(XQ[] stat) {
      mergeStatBases(stat, Nk.WATER, Nk.FLOWING_WATER);
      mergeStatBases(stat, Nk.LAVA, Nk.FLOWING_LAVA);
      mergeStatBases(stat, Nk.LIT_PUMPKIN, Nk.PUMPKIN);
      mergeStatBases(stat, Nk.LIT_FURNACE, Nk.FURNACE);
      mergeStatBases(stat, Nk.LIT_REDSTONE_ORE, Nk.REDSTONE_ORE);
      mergeStatBases(stat, Nk.POWERED_REPEATER, Nk.UNPOWERED_REPEATER);
      mergeStatBases(stat, Nk.POWERED_COMPARATOR, Nk.UNPOWERED_COMPARATOR);
      mergeStatBases(stat, Nk.REDSTONE_TORCH, Nk.UNLIT_REDSTONE_TORCH);
      mergeStatBases(stat, Nk.LIT_REDSTONE_LAMP, Nk.REDSTONE_LAMP);
      mergeStatBases(stat, Nk.DOUBLE_STONE_SLAB, Nk.STONE_SLAB);
      mergeStatBases(stat, Nk.DOUBLE_WOODEN_SLAB, Nk.WOODEN_SLAB);
      mergeStatBases(stat, Nk.DOUBLE_STONE_SLAB2, Nk.STONE_SLAB2);
      mergeStatBases(stat, Nk.GRASS, Nk.DIRT);
      mergeStatBases(stat, Nk.FARMLAND, Nk.DIRT);
   }

   private static void mergeStatBases(XQ[] statBaseIn, co block1, co block2) {
      int i = co.getIdFromBlock(block1);
      int j = co.getIdFromBlock(block2);
      if (statBaseIn[i] != null && statBaseIn[j] == null) {
         statBaseIn[j] = statBaseIn[i];
      } else {
         ALL_STATS.remove(statBaseIn[i]);
         MINE_BLOCK_STATS.remove(statBaseIn[i]);
         BASIC_STATS.remove(statBaseIn[i]);
         statBaseIn[i] = statBaseIn[j];
      }

   }

   public static XQ getStatKillEntity(Iq eggInfo) {
      String s = Ir.getTranslationName(eggInfo.spawnedID);
      return s == null ? null : (new XQ("stat.killEntity." + s, new TextComponentTranslation("stat.entityKill", new Object[]{new TextComponentTranslation("entity." + s + ".name", new Object[0])}))).registerStat();
   }

   public static XQ getStatEntityKilledBy(Iq eggInfo) {
      String s = Ir.getTranslationName(eggInfo.spawnedID);
      return s == null ? null : (new XQ("stat.entityKilledBy." + s, new TextComponentTranslation("stat.entityKilledBy", new Object[]{new TextComponentTranslation("entity." + s + ".name", new Object[0])}))).registerStat();
   }

   @Nullable
   public static XQ getOneShotStat(String statName) {
      return (XQ)ID_TO_STAT_MAP.get(statName);
   }

   static {
      PLAY_ONE_MINUTE = (new XR("stat.playOneMinute", new TextComponentTranslation("stat.playOneMinute", new Object[0]), XQ.timeStatType)).initIndependentStat().registerStat();
      TIME_SINCE_DEATH = (new XR("stat.timeSinceDeath", new TextComponentTranslation("stat.timeSinceDeath", new Object[0]), XQ.timeStatType)).initIndependentStat().registerStat();
      SNEAK_TIME = (new XR("stat.sneakTime", new TextComponentTranslation("stat.sneakTime", new Object[0]), XQ.timeStatType)).initIndependentStat().registerStat();
      WALK_ONE_CM = (new XR("stat.walkOneCm", new TextComponentTranslation("stat.walkOneCm", new Object[0]), XQ.distanceStatType)).initIndependentStat().registerStat();
      CROUCH_ONE_CM = (new XR("stat.crouchOneCm", new TextComponentTranslation("stat.crouchOneCm", new Object[0]), XQ.distanceStatType)).initIndependentStat().registerStat();
      SPRINT_ONE_CM = (new XR("stat.sprintOneCm", new TextComponentTranslation("stat.sprintOneCm", new Object[0]), XQ.distanceStatType)).initIndependentStat().registerStat();
      SWIM_ONE_CM = (new XR("stat.swimOneCm", new TextComponentTranslation("stat.swimOneCm", new Object[0]), XQ.distanceStatType)).initIndependentStat().registerStat();
      FALL_ONE_CM = (new XR("stat.fallOneCm", new TextComponentTranslation("stat.fallOneCm", new Object[0]), XQ.distanceStatType)).initIndependentStat().registerStat();
      CLIMB_ONE_CM = (new XR("stat.climbOneCm", new TextComponentTranslation("stat.climbOneCm", new Object[0]), XQ.distanceStatType)).initIndependentStat().registerStat();
      FLY_ONE_CM = (new XR("stat.flyOneCm", new TextComponentTranslation("stat.flyOneCm", new Object[0]), XQ.distanceStatType)).initIndependentStat().registerStat();
      DIVE_ONE_CM = (new XR("stat.diveOneCm", new TextComponentTranslation("stat.diveOneCm", new Object[0]), XQ.distanceStatType)).initIndependentStat().registerStat();
      MINECART_ONE_CM = (new XR("stat.minecartOneCm", new TextComponentTranslation("stat.minecartOneCm", new Object[0]), XQ.distanceStatType)).initIndependentStat().registerStat();
      BOAT_ONE_CM = (new XR("stat.boatOneCm", new TextComponentTranslation("stat.boatOneCm", new Object[0]), XQ.distanceStatType)).initIndependentStat().registerStat();
      PIG_ONE_CM = (new XR("stat.pigOneCm", new TextComponentTranslation("stat.pigOneCm", new Object[0]), XQ.distanceStatType)).initIndependentStat().registerStat();
      HORSE_ONE_CM = (new XR("stat.horseOneCm", new TextComponentTranslation("stat.horseOneCm", new Object[0]), XQ.distanceStatType)).initIndependentStat().registerStat();
      AVIATE_ONE_CM = (new XR("stat.aviateOneCm", new TextComponentTranslation("stat.aviateOneCm", new Object[0]), XQ.distanceStatType)).initIndependentStat().registerStat();
      JUMP = (new XR("stat.jump", new TextComponentTranslation("stat.jump", new Object[0]))).initIndependentStat().registerStat();
      DROP = (new XR("stat.drop", new TextComponentTranslation("stat.drop", new Object[0]))).initIndependentStat().registerStat();
      DAMAGE_DEALT = (new XR("stat.damageDealt", new TextComponentTranslation("stat.damageDealt", new Object[0]), XQ.divideByTen)).registerStat();
      DAMAGE_TAKEN = (new XR("stat.damageTaken", new TextComponentTranslation("stat.damageTaken", new Object[0]), XQ.divideByTen)).registerStat();
      DEATHS = (new XR("stat.deaths", new TextComponentTranslation("stat.deaths", new Object[0]))).registerStat();
      MOB_KILLS = (new XR("stat.mobKills", new TextComponentTranslation("stat.mobKills", new Object[0]))).registerStat();
      ANIMALS_BRED = (new XR("stat.animalsBred", new TextComponentTranslation("stat.animalsBred", new Object[0]))).registerStat();
      PLAYER_KILLS = (new XR("stat.playerKills", new TextComponentTranslation("stat.playerKills", new Object[0]))).registerStat();
      FISH_CAUGHT = (new XR("stat.fishCaught", new TextComponentTranslation("stat.fishCaught", new Object[0]))).registerStat();
      TALKED_TO_VILLAGER = (new XR("stat.talkedToVillager", new TextComponentTranslation("stat.talkedToVillager", new Object[0]))).registerStat();
      TRADED_WITH_VILLAGER = (new XR("stat.tradedWithVillager", new TextComponentTranslation("stat.tradedWithVillager", new Object[0]))).registerStat();
      CAKE_SLICES_EATEN = (new XR("stat.cakeSlicesEaten", new TextComponentTranslation("stat.cakeSlicesEaten", new Object[0]))).registerStat();
      CAULDRON_FILLED = (new XR("stat.cauldronFilled", new TextComponentTranslation("stat.cauldronFilled", new Object[0]))).registerStat();
      CAULDRON_USED = (new XR("stat.cauldronUsed", new TextComponentTranslation("stat.cauldronUsed", new Object[0]))).registerStat();
      ARMOR_CLEANED = (new XR("stat.armorCleaned", new TextComponentTranslation("stat.armorCleaned", new Object[0]))).registerStat();
      BANNER_CLEANED = (new XR("stat.bannerCleaned", new TextComponentTranslation("stat.bannerCleaned", new Object[0]))).registerStat();
      BREWINGSTAND_INTERACTION = (new XR("stat.brewingstandInteraction", new TextComponentTranslation("stat.brewingstandInteraction", new Object[0]))).registerStat();
      BEACON_INTERACTION = (new XR("stat.beaconInteraction", new TextComponentTranslation("stat.beaconInteraction", new Object[0]))).registerStat();
      DROPPER_INSPECTED = (new XR("stat.dropperInspected", new TextComponentTranslation("stat.dropperInspected", new Object[0]))).registerStat();
      HOPPER_INSPECTED = (new XR("stat.hopperInspected", new TextComponentTranslation("stat.hopperInspected", new Object[0]))).registerStat();
      DISPENSER_INSPECTED = (new XR("stat.dispenserInspected", new TextComponentTranslation("stat.dispenserInspected", new Object[0]))).registerStat();
      NOTEBLOCK_PLAYED = (new XR("stat.noteblockPlayed", new TextComponentTranslation("stat.noteblockPlayed", new Object[0]))).registerStat();
      NOTEBLOCK_TUNED = (new XR("stat.noteblockTuned", new TextComponentTranslation("stat.noteblockTuned", new Object[0]))).registerStat();
      FLOWER_POTTED = (new XR("stat.flowerPotted", new TextComponentTranslation("stat.flowerPotted", new Object[0]))).registerStat();
      TRAPPED_CHEST_TRIGGERED = (new XR("stat.trappedChestTriggered", new TextComponentTranslation("stat.trappedChestTriggered", new Object[0]))).registerStat();
      ENDERCHEST_OPENED = (new XR("stat.enderchestOpened", new TextComponentTranslation("stat.enderchestOpened", new Object[0]))).registerStat();
      ITEM_ENCHANTED = (new XR("stat.itemEnchanted", new TextComponentTranslation("stat.itemEnchanted", new Object[0]))).registerStat();
      RECORD_PLAYED = (new XR("stat.recordPlayed", new TextComponentTranslation("stat.recordPlayed", new Object[0]))).registerStat();
      FURNACE_INTERACTION = (new XR("stat.furnaceInteraction", new TextComponentTranslation("stat.furnaceInteraction", new Object[0]))).registerStat();
      CRAFTING_TABLE_INTERACTION = (new XR("stat.craftingTableInteraction", new TextComponentTranslation("stat.workbenchInteraction", new Object[0]))).registerStat();
      CHEST_OPENED = (new XR("stat.chestOpened", new TextComponentTranslation("stat.chestOpened", new Object[0]))).registerStat();
      SLEEP_IN_BED = (new XR("stat.sleepInBed", new TextComponentTranslation("stat.sleepInBed", new Object[0]))).registerStat();
      OPEN_SHULKER_BOX = (new XR("stat.shulkerBoxOpened", new TextComponentTranslation("stat.shulkerBoxOpened", new Object[0]))).registerStat();
      BLOCKS_STATS = new XQ[4096];
      CRAFTS_STATS = new XQ[32000];
      OBJECT_USE_STATS = new XQ[32000];
      OBJECT_BREAK_STATS = new XQ[32000];
      OBJECTS_PICKED_UP_STATS = new XQ[32000];
      OBJECTS_DROPPED_STATS = new XQ[32000];
   }
}
