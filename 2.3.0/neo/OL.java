package neo;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.IRegistry;
import net.minecraft.util.registry.RegistryNamespaced;
import net.minecraft.util.registry.RegistrySimple;
import net.minecraft.util.text.translation.I18n;

public class OL {
   public static final RegistryNamespaced<ResourceLocation, OL> REGISTRY = new RegistryNamespaced();
   private static final Map<co, OL> BLOCK_TO_ITEM = Maps.newHashMap();
   private static final Oo DAMAGED_GETTER = new Oo() {
      public float apply(Qy stack, @Nullable bij worldIn, @Nullable Iw entityIn) {
         return stack.isItemDamaged() ? 1.0F : 0.0F;
      }
   };
   private static final Oo DAMAGE_GETTER = new Oo() {
      public float apply(Qy stack, @Nullable bij worldIn, @Nullable Iw entityIn) {
         return MathHelper.clamp((float)stack.getItemDamage() / (float)stack.getMaxDamage(), 0.0F, 1.0F);
      }
   };
   private static final Oo LEFTHANDED_GETTER = new Oo() {
      public float apply(Qy stack, @Nullable bij worldIn, @Nullable Iw entityIn) {
         return entityIn != null && entityIn.getPrimaryHand() != EnumHandSide.RIGHT ? 1.0F : 0.0F;
      }
   };
   private static final Oo COOLDOWN_GETTER = new Oo() {
      public float apply(Qy stack, @Nullable bij worldIn, @Nullable Iw entityIn) {
         return entityIn instanceof ME ? ((ME)entityIn).getCooldownTracker().getCooldown(stack.getItem(), 0.0F) : 0.0F;
      }
   };
   private final IRegistry<ResourceLocation, Oo> properties = new RegistrySimple();
   protected static final UUID ATTACK_DAMAGE_MODIFIER = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
   protected static final UUID ATTACK_SPEED_MODIFIER = UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3");
   private EN tabToDisplayOn;
   protected static Random itemRand = new Random();
   protected int maxStackSize = 64;
   private int maxDamage;
   protected boolean bFull3D;
   protected boolean hasSubtypes;
   private OL containerItem;
   private String translationKey;

   public static int getIdFromItem(OL itemIn) {
      return itemIn == null ? 0 : REGISTRY.getIDForObject(itemIn);
   }

   public static OL getItemById(int id) {
      return (OL)REGISTRY.getObjectById(id);
   }

   public static OL getItemFromBlock(co blockIn) {
      OL item = (OL)BLOCK_TO_ITEM.get(blockIn);
      return item == null ? NK.AIR : item;
   }

   @Nullable
   public static OL getByNameOrId(String id) {
      OL item = (OL)REGISTRY.getObject(new ResourceLocation(id));
      if (item == null) {
         try {
            return getItemById(Integer.parseInt(id));
         } catch (NumberFormatException var3) {
         }
      }

      return item;
   }

   public final void addPropertyOverride(ResourceLocation key, Oo getter) {
      this.properties.putObject(key, getter);
   }

   public float getStrVsBlock(Qy stack, in state) {
      return 1.0F;
   }

   @Nullable
   public Oo getPropertyGetter(ResourceLocation key) {
      return (Oo)this.properties.getObject(key);
   }

   public boolean hasCustomProperties() {
      return !this.properties.getKeys().isEmpty();
   }

   public boolean updateItemStackNBT(QQ nbt) {
      return false;
   }

   public OL() {
      this.addPropertyOverride(new ResourceLocation("lefthanded"), LEFTHANDED_GETTER);
      this.addPropertyOverride(new ResourceLocation("cooldown"), COOLDOWN_GETTER);
   }

   public OL setMaxStackSize(int maxStackSize) {
      this.maxStackSize = maxStackSize;
      return this;
   }

   public EnumActionResult onItemUse(ME player, bij worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      return EnumActionResult.PASS;
   }

   public float getDestroySpeed(Qy stack, in state) {
      return 1.0F;
   }

   public ActionResult<Qy> onItemRightClick(bij worldIn, ME playerIn, EnumHand handIn) {
      return new ActionResult(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
   }

   public Qy onItemUseFinish(Qy stack, bij worldIn, Iw entityLiving) {
      return stack;
   }

   public int getItemStackLimit() {
      return this.maxStackSize;
   }

   public int getMetadata(int damage) {
      return 0;
   }

   public boolean getHasSubtypes() {
      return this.hasSubtypes;
   }

   protected OL setHasSubtypes(boolean hasSubtypes) {
      this.hasSubtypes = hasSubtypes;
      return this;
   }

   public int getMaxDamage() {
      return this.maxDamage;
   }

   protected OL setMaxDamage(int maxDamageIn) {
      this.maxDamage = maxDamageIn;
      if (maxDamageIn > 0) {
         this.addPropertyOverride(new ResourceLocation("damaged"), DAMAGED_GETTER);
         this.addPropertyOverride(new ResourceLocation("damage"), DAMAGE_GETTER);
      }

      return this;
   }

   public boolean isDamageable() {
      return this.maxDamage > 0 && (!this.hasSubtypes || this.maxStackSize == 1);
   }

   public boolean hitEntity(Qy stack, Iw target, Iw attacker) {
      return false;
   }

   public boolean onBlockDestroyed(Qy stack, bij worldIn, in state, BlockPos pos, Iw entityLiving) {
      return false;
   }

   public boolean canHarvestBlock(in blockIn) {
      return false;
   }

   public boolean itemInteractionForEntity(Qy stack, ME playerIn, Iw target, EnumHand hand) {
      return false;
   }

   public OL setFull3D() {
      this.bFull3D = true;
      return this;
   }

   public boolean isFull3D() {
      return this.bFull3D;
   }

   public boolean shouldRotateAroundWhenRendering() {
      return false;
   }

   public OL setTranslationKey(String key) {
      this.translationKey = key;
      return this;
   }

   public String getUnlocalizedNameInefficiently(Qy stack) {
      return I18n.translateToLocal(this.getTranslationKey(stack));
   }

   public String getTranslationKey() {
      return "item." + this.translationKey;
   }

   public String getTranslationKey(Qy stack) {
      return "item." + this.translationKey;
   }

   public OL setContainerItem(OL containerItem) {
      this.containerItem = containerItem;
      return this;
   }

   public boolean getShareTag() {
      return true;
   }

   @Nullable
   public OL getContainerItem() {
      return this.containerItem;
   }

   public boolean hasContainerItem() {
      return this.containerItem != null;
   }

   public void onUpdate(Qy stack, bij worldIn, Ig entityIn, int itemSlot, boolean isSelected) {
   }

   public void onCreated(Qy stack, bij worldIn, ME playerIn) {
   }

   public boolean isMap() {
      return false;
   }

   public Ol getItemUseAction(Qy stack) {
      return Ol.NONE;
   }

   public int getMaxItemUseDuration(Qy stack) {
      return 0;
   }

   public void onPlayerStoppedUsing(Qy stack, bij worldIn, Iw entityLiving, int timeLeft) {
   }

   public void addInformation(Qy stack, @Nullable bij worldIn, List<String> tooltip, BJ flagIn) {
   }

   public String getItemStackDisplayName(Qy stack) {
      return I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name").trim();
   }

   public boolean hasEffect(Qy stack) {
      return stack.isItemEnchanted();
   }

   public On getRarity(Qy stack) {
      return stack.isItemEnchanted() ? On.RARE : On.COMMON;
   }

   public boolean isEnchantable(Qy stack) {
      return this.getItemStackLimit() == 1 && this.isDamageable();
   }

   protected RayTraceResult rayTrace(bij worldIn, ME playerIn, boolean useLiquids) {
      float f = playerIn.rotationPitch;
      float f1 = playerIn.rotationYaw;
      double d0 = playerIn.posX;
      double d1 = playerIn.posY + (double)playerIn.getEyeHeight();
      double d2 = playerIn.posZ;
      Vec3d vec3d = new Vec3d(d0, d1, d2);
      float f2 = MathHelper.cos(-f1 * 0.017453292F - 3.1415927F);
      float f3 = MathHelper.sin(-f1 * 0.017453292F - 3.1415927F);
      float f4 = -MathHelper.cos(-f * 0.017453292F);
      float f5 = MathHelper.sin(-f * 0.017453292F);
      float f6 = f3 * f4;
      float f7 = f2 * f4;
      double d3 = 5.0;
      Vec3d vec3d1 = vec3d.add((double)f6 * 5.0, (double)f5 * 5.0, (double)f7 * 5.0);
      return worldIn.rayTraceBlocks(vec3d, vec3d1, useLiquids, !useLiquids, false);
   }

   public int getItemEnchantability() {
      return 0;
   }

   public void getSubItems(EN tab, NonNullList<Qy> items) {
      if (this.isInCreativeTab(tab)) {
         items.add(new Qy(this));
      }

   }

   protected boolean isInCreativeTab(EN targetTab) {
      EN creativetabs = this.getCreativeTab();
      return creativetabs != null && (targetTab == EN.SEARCH || targetTab == creativetabs);
   }

   @Nullable
   public EN getCreativeTab() {
      return this.tabToDisplayOn;
   }

   public OL setCreativeTab(EN tab) {
      this.tabToDisplayOn = tab;
      return this;
   }

   public boolean canItemEditBlocks() {
      return false;
   }

   public boolean getIsRepairable(Qy toRepair, Qy repair) {
      return false;
   }

   public Multimap<String, FW> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
      return HashMultimap.create();
   }

   public static void registerItems() {
      registerItemBlock(Nk.AIR, new OM(Nk.AIR));
      registerItemBlock(Nk.STONE, (new Qa(Nk.STONE, Nk.STONE, new PZ() {
         public String apply(Qy p_apply_1_) {
            return gY.byMetadata(p_apply_1_.getMetadata()).getTranslationKey();
         }
      })).setTranslationKey("stone"));
      registerItemBlock(Nk.GRASS, new Pm(Nk.GRASS, false));
      registerItemBlock(Nk.DIRT, (new Qa(Nk.DIRT, Nk.DIRT, new PZ() {
         public String apply(Qy p_apply_1_) {
            return di.byMetadata(p_apply_1_.getMetadata()).getTranslationKey();
         }
      })).setTranslationKey("dirt"));
      registerItemBlock(Nk.COBBLESTONE);
      registerItemBlock(Nk.PLANKS, (new Qa(Nk.PLANKS, Nk.PLANKS, new PZ() {
         public String apply(Qy p_apply_1_) {
            return fk.byMetadata(p_apply_1_.getMetadata()).getTranslationKey();
         }
      })).setTranslationKey("wood"));
      registerItemBlock(Nk.SAPLING, (new Qa(Nk.SAPLING, Nk.SAPLING, new PZ() {
         public String apply(Qy p_apply_1_) {
            return fk.byMetadata(p_apply_1_.getMetadata()).getTranslationKey();
         }
      })).setTranslationKey("sapling"));
      registerItemBlock(Nk.BEDROCK);
      registerItemBlock(Nk.SAND, (new Qa(Nk.SAND, Nk.SAND, new PZ() {
         public String apply(Qy p_apply_1_) {
            return gj.byMetadata(p_apply_1_.getMetadata()).getTranslationKey();
         }
      })).setTranslationKey("sand"));
      registerItemBlock(Nk.GRAVEL);
      registerItemBlock(Nk.GOLD_ORE);
      registerItemBlock(Nk.IRON_ORE);
      registerItemBlock(Nk.COAL_ORE);
      registerItemBlock(Nk.LOG, (new Qa(Nk.LOG, Nk.LOG, new PZ() {
         public String apply(Qy p_apply_1_) {
            return fk.byMetadata(p_apply_1_.getMetadata()).getTranslationKey();
         }
      })).setTranslationKey("log"));
      registerItemBlock(Nk.LOG2, (new Qa(Nk.LOG2, Nk.LOG2, new PZ() {
         public String apply(Qy p_apply_1_) {
            return fk.byMetadata(p_apply_1_.getMetadata() + 4).getTranslationKey();
         }
      })).setTranslationKey("log"));
      registerItemBlock(Nk.LEAVES, (new PQ(Nk.LEAVES)).setTranslationKey("leaves"));
      registerItemBlock(Nk.LEAVES2, (new PQ(Nk.LEAVES2)).setTranslationKey("leaves"));
      registerItemBlock(Nk.SPONGE, (new Qa(Nk.SPONGE, Nk.SPONGE, new PZ() {
         public String apply(Qy p_apply_1_) {
            return (p_apply_1_.getMetadata() & 1) == 1 ? "wet" : "dry";
         }
      })).setTranslationKey("sponge"));
      registerItemBlock(Nk.GLASS);
      registerItemBlock(Nk.LAPIS_ORE);
      registerItemBlock(Nk.LAPIS_BLOCK);
      registerItemBlock(Nk.DISPENSER);
      registerItemBlock(Nk.SANDSTONE, (new Qa(Nk.SANDSTONE, Nk.SANDSTONE, new PZ() {
         public String apply(Qy p_apply_1_) {
            return gl.byMetadata(p_apply_1_.getMetadata()).getTranslationKey();
         }
      })).setTranslationKey("sandStone"));
      registerItemBlock(Nk.NOTEBLOCK);
      registerItemBlock(Nk.GOLDEN_RAIL);
      registerItemBlock(Nk.DETECTOR_RAIL);
      registerItemBlock(Nk.STICKY_PISTON, new Qd(Nk.STICKY_PISTON));
      registerItemBlock(Nk.WEB);
      registerItemBlock(Nk.TALLGRASS, (new Pm(Nk.TALLGRASS, true)).setSubtypeNames(new String[]{"shrub", "grass", "fern"}));
      registerItemBlock(Nk.DEADBUSH);
      registerItemBlock(Nk.PISTON, new Qd(Nk.PISTON));
      registerItemBlock(Nk.WOOL, (new Pk(Nk.WOOL)).setTranslationKey("cloth"));
      registerItemBlock(Nk.YELLOW_FLOWER, (new Qa(Nk.YELLOW_FLOWER, Nk.YELLOW_FLOWER, new PZ() {
         public String apply(Qy p_apply_1_) {
            return dR.getType(dP.YELLOW, p_apply_1_.getMetadata()).getTranslationKey();
         }
      })).setTranslationKey("flower"));
      registerItemBlock(Nk.RED_FLOWER, (new Qa(Nk.RED_FLOWER, Nk.RED_FLOWER, new PZ() {
         public String apply(Qy p_apply_1_) {
            return dR.getType(dP.RED, p_apply_1_.getMetadata()).getTranslationKey();
         }
      })).setTranslationKey("rose"));
      registerItemBlock(Nk.BROWN_MUSHROOM);
      registerItemBlock(Nk.RED_MUSHROOM);
      registerItemBlock(Nk.GOLD_BLOCK);
      registerItemBlock(Nk.IRON_BLOCK);
      registerItemBlock(Nk.STONE_SLAB, (new Qr(Nk.STONE_SLAB, Nk.STONE_SLAB, Nk.DOUBLE_STONE_SLAB)).setTranslationKey("stoneSlab"));
      registerItemBlock(Nk.BRICK_BLOCK);
      registerItemBlock(Nk.TNT);
      registerItemBlock(Nk.BOOKSHELF);
      registerItemBlock(Nk.MOSSY_COBBLESTONE);
      registerItemBlock(Nk.OBSIDIAN);
      registerItemBlock(Nk.TORCH);
      registerItemBlock(Nk.END_ROD);
      registerItemBlock(Nk.CHORUS_PLANT);
      registerItemBlock(Nk.CHORUS_FLOWER);
      registerItemBlock(Nk.PURPUR_BLOCK);
      registerItemBlock(Nk.PURPUR_PILLAR);
      registerItemBlock(Nk.PURPUR_STAIRS);
      registerItemBlock(Nk.PURPUR_SLAB, (new Qr(Nk.PURPUR_SLAB, Nk.PURPUR_SLAB, Nk.PURPUR_DOUBLE_SLAB)).setTranslationKey("purpurSlab"));
      registerItemBlock(Nk.MOB_SPAWNER);
      registerItemBlock(Nk.OAK_STAIRS);
      registerItemBlock(Nk.CHEST);
      registerItemBlock(Nk.DIAMOND_ORE);
      registerItemBlock(Nk.DIAMOND_BLOCK);
      registerItemBlock(Nk.CRAFTING_TABLE);
      registerItemBlock(Nk.FARMLAND);
      registerItemBlock(Nk.FURNACE);
      registerItemBlock(Nk.LADDER);
      registerItemBlock(Nk.RAIL);
      registerItemBlock(Nk.STONE_STAIRS);
      registerItemBlock(Nk.LEVER);
      registerItemBlock(Nk.STONE_PRESSURE_PLATE);
      registerItemBlock(Nk.WOODEN_PRESSURE_PLATE);
      registerItemBlock(Nk.REDSTONE_ORE);
      registerItemBlock(Nk.REDSTONE_TORCH);
      registerItemBlock(Nk.STONE_BUTTON);
      registerItemBlock(Nk.SNOW_LAYER, new Qs(Nk.SNOW_LAYER));
      registerItemBlock(Nk.ICE);
      registerItemBlock(Nk.SNOW);
      registerItemBlock(Nk.CACTUS);
      registerItemBlock(Nk.CLAY);
      registerItemBlock(Nk.JUKEBOX);
      registerItemBlock(Nk.OAK_FENCE);
      registerItemBlock(Nk.SPRUCE_FENCE);
      registerItemBlock(Nk.BIRCH_FENCE);
      registerItemBlock(Nk.JUNGLE_FENCE);
      registerItemBlock(Nk.DARK_OAK_FENCE);
      registerItemBlock(Nk.ACACIA_FENCE);
      registerItemBlock(Nk.PUMPKIN);
      registerItemBlock(Nk.NETHERRACK);
      registerItemBlock(Nk.SOUL_SAND);
      registerItemBlock(Nk.GLOWSTONE);
      registerItemBlock(Nk.LIT_PUMPKIN);
      registerItemBlock(Nk.TRAPDOOR);
      registerItemBlock(Nk.MONSTER_EGG, (new Qa(Nk.MONSTER_EGG, Nk.MONSTER_EGG, new PZ() {
         public String apply(Qy p_apply_1_) {
            return gA.byMetadata(p_apply_1_.getMetadata()).getTranslationKey();
         }
      })).setTranslationKey("monsterStoneEgg"));
      registerItemBlock(Nk.STONEBRICK, (new Qa(Nk.STONEBRICK, Nk.STONEBRICK, new PZ() {
         public String apply(Qy p_apply_1_) {
            return ha.byMetadata(p_apply_1_.getMetadata()).getTranslationKey();
         }
      })).setTranslationKey("stonebricksmooth"));
      registerItemBlock(Nk.BROWN_MUSHROOM_BLOCK);
      registerItemBlock(Nk.RED_MUSHROOM_BLOCK);
      registerItemBlock(Nk.IRON_BARS);
      registerItemBlock(Nk.GLASS_PANE);
      registerItemBlock(Nk.MELON_BLOCK);
      registerItemBlock(Nk.VINE, new Pm(Nk.VINE, false));
      registerItemBlock(Nk.OAK_FENCE_GATE);
      registerItemBlock(Nk.SPRUCE_FENCE_GATE);
      registerItemBlock(Nk.BIRCH_FENCE_GATE);
      registerItemBlock(Nk.JUNGLE_FENCE_GATE);
      registerItemBlock(Nk.DARK_OAK_FENCE_GATE);
      registerItemBlock(Nk.ACACIA_FENCE_GATE);
      registerItemBlock(Nk.BRICK_STAIRS);
      registerItemBlock(Nk.STONE_BRICK_STAIRS);
      registerItemBlock(Nk.MYCELIUM);
      registerItemBlock(Nk.WATERLILY, new PR(Nk.WATERLILY));
      registerItemBlock(Nk.NETHER_BRICK);
      registerItemBlock(Nk.NETHER_BRICK_FENCE);
      registerItemBlock(Nk.NETHER_BRICK_STAIRS);
      registerItemBlock(Nk.ENCHANTING_TABLE);
      registerItemBlock(Nk.END_PORTAL_FRAME);
      registerItemBlock(Nk.END_STONE);
      registerItemBlock(Nk.END_BRICKS);
      registerItemBlock(Nk.DRAGON_EGG);
      registerItemBlock(Nk.REDSTONE_LAMP);
      registerItemBlock(Nk.WOODEN_SLAB, (new Qr(Nk.WOODEN_SLAB, Nk.WOODEN_SLAB, Nk.DOUBLE_WOODEN_SLAB)).setTranslationKey("woodSlab"));
      registerItemBlock(Nk.SANDSTONE_STAIRS);
      registerItemBlock(Nk.EMERALD_ORE);
      registerItemBlock(Nk.ENDER_CHEST);
      registerItemBlock(Nk.TRIPWIRE_HOOK);
      registerItemBlock(Nk.EMERALD_BLOCK);
      registerItemBlock(Nk.SPRUCE_STAIRS);
      registerItemBlock(Nk.BIRCH_STAIRS);
      registerItemBlock(Nk.JUNGLE_STAIRS);
      registerItemBlock(Nk.COMMAND_BLOCK);
      registerItemBlock(Nk.BEACON);
      registerItemBlock(Nk.COBBLESTONE_WALL, (new Qa(Nk.COBBLESTONE_WALL, Nk.COBBLESTONE_WALL, new PZ() {
         public String apply(Qy p_apply_1_) {
            return hy.byMetadata(p_apply_1_.getMetadata()).getTranslationKey();
         }
      })).setTranslationKey("cobbleWall"));
      registerItemBlock(Nk.WOODEN_BUTTON);
      registerItemBlock(Nk.ANVIL, (new ON(Nk.ANVIL)).setTranslationKey("anvil"));
      registerItemBlock(Nk.TRAPPED_CHEST);
      registerItemBlock(Nk.LIGHT_WEIGHTED_PRESSURE_PLATE);
      registerItemBlock(Nk.HEAVY_WEIGHTED_PRESSURE_PLATE);
      registerItemBlock(Nk.DAYLIGHT_DETECTOR);
      registerItemBlock(Nk.REDSTONE_BLOCK);
      registerItemBlock(Nk.QUARTZ_ORE);
      registerItemBlock(Nk.HOPPER);
      registerItemBlock(Nk.QUARTZ_BLOCK, (new Qa(Nk.QUARTZ_BLOCK, Nk.QUARTZ_BLOCK, new String[]{"default", "chiseled", "lines"})).setTranslationKey("quartzBlock"));
      registerItemBlock(Nk.QUARTZ_STAIRS);
      registerItemBlock(Nk.ACTIVATOR_RAIL);
      registerItemBlock(Nk.DROPPER);
      registerItemBlock(Nk.STAINED_HARDENED_CLAY, (new Pk(Nk.STAINED_HARDENED_CLAY)).setTranslationKey("clayHardenedStained"));
      registerItemBlock(Nk.BARRIER);
      registerItemBlock(Nk.IRON_TRAPDOOR);
      registerItemBlock(Nk.HAY_BLOCK);
      registerItemBlock(Nk.CARPET, (new Pk(Nk.CARPET)).setTranslationKey("woolCarpet"));
      registerItemBlock(Nk.HARDENED_CLAY);
      registerItemBlock(Nk.COAL_BLOCK);
      registerItemBlock(Nk.PACKED_ICE);
      registerItemBlock(Nk.ACACIA_STAIRS);
      registerItemBlock(Nk.DARK_OAK_STAIRS);
      registerItemBlock(Nk.SLIME_BLOCK);
      registerItemBlock(Nk.GRASS_PATH);
      registerItemBlock(Nk.DOUBLE_PLANT, (new Qa(Nk.DOUBLE_PLANT, Nk.DOUBLE_PLANT, new PZ() {
         public String apply(Qy p_apply_1_) {
            return dq.byMetadata(p_apply_1_.getMetadata()).getTranslationKey();
         }
      })).setTranslationKey("doublePlant"));
      registerItemBlock(Nk.STAINED_GLASS, (new Pk(Nk.STAINED_GLASS)).setTranslationKey("stainedGlass"));
      registerItemBlock(Nk.STAINED_GLASS_PANE, (new Pk(Nk.STAINED_GLASS_PANE)).setTranslationKey("stainedGlassPane"));
      registerItemBlock(Nk.PRISMARINE, (new Qa(Nk.PRISMARINE, Nk.PRISMARINE, new PZ() {
         public String apply(Qy p_apply_1_) {
            return fu.byMetadata(p_apply_1_.getMetadata()).getTranslationKey();
         }
      })).setTranslationKey("prismarine"));
      registerItemBlock(Nk.SEA_LANTERN);
      registerItemBlock(Nk.RED_SANDSTONE, (new Qa(Nk.RED_SANDSTONE, Nk.RED_SANDSTONE, new PZ() {
         public String apply(Qy p_apply_1_) {
            return fS.byMetadata(p_apply_1_.getMetadata()).getTranslationKey();
         }
      })).setTranslationKey("redSandStone"));
      registerItemBlock(Nk.RED_SANDSTONE_STAIRS);
      registerItemBlock(Nk.STONE_SLAB2, (new Qr(Nk.STONE_SLAB2, Nk.STONE_SLAB2, Nk.DOUBLE_STONE_SLAB2)).setTranslationKey("stoneSlab2"));
      registerItemBlock(Nk.REPEATING_COMMAND_BLOCK);
      registerItemBlock(Nk.CHAIN_COMMAND_BLOCK);
      registerItemBlock(Nk.MAGMA);
      registerItemBlock(Nk.NETHER_WART_BLOCK);
      registerItemBlock(Nk.RED_NETHER_BRICK);
      registerItemBlock(Nk.BONE_BLOCK);
      registerItemBlock(Nk.STRUCTURE_VOID);
      registerItemBlock(Nk.OBSERVER);
      registerItemBlock(Nk.WHITE_SHULKER_BOX, new Qn(Nk.WHITE_SHULKER_BOX));
      registerItemBlock(Nk.ORANGE_SHULKER_BOX, new Qn(Nk.ORANGE_SHULKER_BOX));
      registerItemBlock(Nk.MAGENTA_SHULKER_BOX, new Qn(Nk.MAGENTA_SHULKER_BOX));
      registerItemBlock(Nk.LIGHT_BLUE_SHULKER_BOX, new Qn(Nk.LIGHT_BLUE_SHULKER_BOX));
      registerItemBlock(Nk.YELLOW_SHULKER_BOX, new Qn(Nk.YELLOW_SHULKER_BOX));
      registerItemBlock(Nk.LIME_SHULKER_BOX, new Qn(Nk.LIME_SHULKER_BOX));
      registerItemBlock(Nk.PINK_SHULKER_BOX, new Qn(Nk.PINK_SHULKER_BOX));
      registerItemBlock(Nk.GRAY_SHULKER_BOX, new Qn(Nk.GRAY_SHULKER_BOX));
      registerItemBlock(Nk.SILVER_SHULKER_BOX, new Qn(Nk.SILVER_SHULKER_BOX));
      registerItemBlock(Nk.CYAN_SHULKER_BOX, new Qn(Nk.CYAN_SHULKER_BOX));
      registerItemBlock(Nk.PURPLE_SHULKER_BOX, new Qn(Nk.PURPLE_SHULKER_BOX));
      registerItemBlock(Nk.BLUE_SHULKER_BOX, new Qn(Nk.BLUE_SHULKER_BOX));
      registerItemBlock(Nk.BROWN_SHULKER_BOX, new Qn(Nk.BROWN_SHULKER_BOX));
      registerItemBlock(Nk.GREEN_SHULKER_BOX, new Qn(Nk.GREEN_SHULKER_BOX));
      registerItemBlock(Nk.RED_SHULKER_BOX, new Qn(Nk.RED_SHULKER_BOX));
      registerItemBlock(Nk.BLACK_SHULKER_BOX, new Qn(Nk.BLACK_SHULKER_BOX));
      registerItemBlock(Nk.WHITE_GLAZED_TERRACOTTA);
      registerItemBlock(Nk.ORANGE_GLAZED_TERRACOTTA);
      registerItemBlock(Nk.MAGENTA_GLAZED_TERRACOTTA);
      registerItemBlock(Nk.LIGHT_BLUE_GLAZED_TERRACOTTA);
      registerItemBlock(Nk.YELLOW_GLAZED_TERRACOTTA);
      registerItemBlock(Nk.LIME_GLAZED_TERRACOTTA);
      registerItemBlock(Nk.PINK_GLAZED_TERRACOTTA);
      registerItemBlock(Nk.GRAY_GLAZED_TERRACOTTA);
      registerItemBlock(Nk.SILVER_GLAZED_TERRACOTTA);
      registerItemBlock(Nk.CYAN_GLAZED_TERRACOTTA);
      registerItemBlock(Nk.PURPLE_GLAZED_TERRACOTTA);
      registerItemBlock(Nk.BLUE_GLAZED_TERRACOTTA);
      registerItemBlock(Nk.BROWN_GLAZED_TERRACOTTA);
      registerItemBlock(Nk.GREEN_GLAZED_TERRACOTTA);
      registerItemBlock(Nk.RED_GLAZED_TERRACOTTA);
      registerItemBlock(Nk.BLACK_GLAZED_TERRACOTTA);
      registerItemBlock(Nk.CONCRETE, (new Pk(Nk.CONCRETE)).setTranslationKey("concrete"));
      registerItemBlock(Nk.CONCRETE_POWDER, (new Pk(Nk.CONCRETE_POWDER)).setTranslationKey("concrete_powder"));
      registerItemBlock(Nk.STRUCTURE_BLOCK);
      registerItem(256, (String)"iron_shovel", (new Qv(OK.IRON)).setTranslationKey("shovelIron"));
      registerItem(257, (String)"iron_pickaxe", (new Qc(OK.IRON)).setTranslationKey("pickaxeIron"));
      registerItem(258, (String)"iron_axe", (new OU(OK.IRON)).setTranslationKey("hatchetIron"));
      registerItem(259, (String)"flint_and_steel", (new PH()).setTranslationKey("flintAndSteel"));
      registerItem(260, (String)"apple", (new PI(4, 0.3F, false)).setTranslationKey("apple"));
      registerItem(261, (String)"bow", (new Pd()).setTranslationKey("bow"));
      registerItem(262, (String)"arrow", (new OT()).setTranslationKey("arrow"));
      registerItem(263, (String)"coal", (new Pl()).setTranslationKey("coal"));
      registerItem(264, (String)"diamond", (new OL()).setTranslationKey("diamond").setCreativeTab(EN.MATERIALS));
      registerItem(265, (String)"iron_ingot", (new OL()).setTranslationKey("ingotIron").setCreativeTab(EN.MATERIALS));
      registerItem(266, (String)"gold_ingot", (new OL()).setTranslationKey("ingotGold").setCreativeTab(EN.MATERIALS));
      registerItem(267, (String)"iron_sword", (new Qz(OK.IRON)).setTranslationKey("swordIron"));
      registerItem(268, (String)"wooden_sword", (new Qz(OK.WOOD)).setTranslationKey("swordWood"));
      registerItem(269, (String)"wooden_shovel", (new Qv(OK.WOOD)).setTranslationKey("shovelWood"));
      registerItem(270, (String)"wooden_pickaxe", (new Qc(OK.WOOD)).setTranslationKey("pickaxeWood"));
      registerItem(271, (String)"wooden_axe", (new OU(OK.WOOD)).setTranslationKey("hatchetWood"));
      registerItem(272, (String)"stone_sword", (new Qz(OK.STONE)).setTranslationKey("swordStone"));
      registerItem(273, (String)"stone_shovel", (new Qv(OK.STONE)).setTranslationKey("shovelStone"));
      registerItem(274, (String)"stone_pickaxe", (new Qc(OK.STONE)).setTranslationKey("pickaxeStone"));
      registerItem(275, (String)"stone_axe", (new OU(OK.STONE)).setTranslationKey("hatchetStone"));
      registerItem(276, (String)"diamond_sword", (new Qz(OK.DIAMOND)).setTranslationKey("swordDiamond"));
      registerItem(277, (String)"diamond_shovel", (new Qv(OK.DIAMOND)).setTranslationKey("shovelDiamond"));
      registerItem(278, (String)"diamond_pickaxe", (new Qc(OK.DIAMOND)).setTranslationKey("pickaxeDiamond"));
      registerItem(279, (String)"diamond_axe", (new OU(OK.DIAMOND)).setTranslationKey("hatchetDiamond"));
      registerItem(280, (String)"stick", (new OL()).setFull3D().setTranslationKey("stick").setCreativeTab(EN.MATERIALS));
      registerItem(281, (String)"bowl", (new OL()).setTranslationKey("bowl").setCreativeTab(EN.MATERIALS));
      registerItem(282, (String)"mushroom_stew", (new Qu(6)).setTranslationKey("mushroomStew"));
      registerItem(283, (String)"golden_sword", (new Qz(OK.GOLD)).setTranslationKey("swordGold"));
      registerItem(284, (String)"golden_shovel", (new Qv(OK.GOLD)).setTranslationKey("shovelGold"));
      registerItem(285, (String)"golden_pickaxe", (new Qc(OK.GOLD)).setTranslationKey("pickaxeGold"));
      registerItem(286, (String)"golden_axe", (new OU(OK.GOLD)).setTranslationKey("hatchetGold"));
      registerItem(287, (String)"string", (new OY(Nk.TRIPWIRE)).setTranslationKey("string").setCreativeTab(EN.MATERIALS));
      registerItem(288, (String)"feather", (new OL()).setTranslationKey("feather").setCreativeTab(EN.MATERIALS));
      registerItem(289, (String)"gunpowder", (new OL()).setTranslationKey("sulphur").setCreativeTab(EN.MATERIALS));
      registerItem(290, (String)"wooden_hoe", (new PN(OK.WOOD)).setTranslationKey("hoeWood"));
      registerItem(291, (String)"stone_hoe", (new PN(OK.STONE)).setTranslationKey("hoeStone"));
      registerItem(292, (String)"iron_hoe", (new PN(OK.IRON)).setTranslationKey("hoeIron"));
      registerItem(293, (String)"diamond_hoe", (new PN(OK.DIAMOND)).setTranslationKey("hoeDiamond"));
      registerItem(294, (String)"golden_hoe", (new PN(OK.GOLD)).setTranslationKey("hoeGold"));
      registerItem(295, (String)"wheat_seeds", (new Qj(Nk.WHEAT, Nk.FARMLAND)).setTranslationKey("seeds"));
      registerItem(296, (String)"wheat", (new OL()).setTranslationKey("wheat").setCreativeTab(EN.MATERIALS));
      registerItem(297, (String)"bread", (new PI(5, 0.6F, false)).setTranslationKey("bread"));
      registerItem(298, (String)"leather_helmet", (new OR(OQ.LEATHER, 0, EntityEquipmentSlot.HEAD)).setTranslationKey("helmetCloth"));
      registerItem(299, (String)"leather_chestplate", (new OR(OQ.LEATHER, 0, EntityEquipmentSlot.CHEST)).setTranslationKey("chestplateCloth"));
      registerItem(300, (String)"leather_leggings", (new OR(OQ.LEATHER, 0, EntityEquipmentSlot.LEGS)).setTranslationKey("leggingsCloth"));
      registerItem(301, (String)"leather_boots", (new OR(OQ.LEATHER, 0, EntityEquipmentSlot.FEET)).setTranslationKey("bootsCloth"));
      registerItem(302, (String)"chainmail_helmet", (new OR(OQ.CHAIN, 1, EntityEquipmentSlot.HEAD)).setTranslationKey("helmetChain"));
      registerItem(303, (String)"chainmail_chestplate", (new OR(OQ.CHAIN, 1, EntityEquipmentSlot.CHEST)).setTranslationKey("chestplateChain"));
      registerItem(304, (String)"chainmail_leggings", (new OR(OQ.CHAIN, 1, EntityEquipmentSlot.LEGS)).setTranslationKey("leggingsChain"));
      registerItem(305, (String)"chainmail_boots", (new OR(OQ.CHAIN, 1, EntityEquipmentSlot.FEET)).setTranslationKey("bootsChain"));
      registerItem(306, (String)"iron_helmet", (new OR(OQ.IRON, 2, EntityEquipmentSlot.HEAD)).setTranslationKey("helmetIron"));
      registerItem(307, (String)"iron_chestplate", (new OR(OQ.IRON, 2, EntityEquipmentSlot.CHEST)).setTranslationKey("chestplateIron"));
      registerItem(308, (String)"iron_leggings", (new OR(OQ.IRON, 2, EntityEquipmentSlot.LEGS)).setTranslationKey("leggingsIron"));
      registerItem(309, (String)"iron_boots", (new OR(OQ.IRON, 2, EntityEquipmentSlot.FEET)).setTranslationKey("bootsIron"));
      registerItem(310, (String)"diamond_helmet", (new OR(OQ.DIAMOND, 3, EntityEquipmentSlot.HEAD)).setTranslationKey("helmetDiamond"));
      registerItem(311, (String)"diamond_chestplate", (new OR(OQ.DIAMOND, 3, EntityEquipmentSlot.CHEST)).setTranslationKey("chestplateDiamond"));
      registerItem(312, (String)"diamond_leggings", (new OR(OQ.DIAMOND, 3, EntityEquipmentSlot.LEGS)).setTranslationKey("leggingsDiamond"));
      registerItem(313, (String)"diamond_boots", (new OR(OQ.DIAMOND, 3, EntityEquipmentSlot.FEET)).setTranslationKey("bootsDiamond"));
      registerItem(314, (String)"golden_helmet", (new OR(OQ.GOLD, 4, EntityEquipmentSlot.HEAD)).setTranslationKey("helmetGold"));
      registerItem(315, (String)"golden_chestplate", (new OR(OQ.GOLD, 4, EntityEquipmentSlot.CHEST)).setTranslationKey("chestplateGold"));
      registerItem(316, (String)"golden_leggings", (new OR(OQ.GOLD, 4, EntityEquipmentSlot.LEGS)).setTranslationKey("leggingsGold"));
      registerItem(317, (String)"golden_boots", (new OR(OQ.GOLD, 4, EntityEquipmentSlot.FEET)).setTranslationKey("bootsGold"));
      registerItem(318, (String)"flint", (new OL()).setTranslationKey("flint").setCreativeTab(EN.MATERIALS));
      registerItem(319, (String)"porkchop", (new PI(3, 0.3F, true)).setTranslationKey("porkchopRaw"));
      registerItem(320, (String)"cooked_porkchop", (new PI(8, 0.8F, true)).setTranslationKey("porkchopCooked"));
      registerItem(321, (String)"painting", (new PL(Jq.class)).setTranslationKey("painting"));
      registerItem(322, (String)"golden_apple", (new OO(4, 1.2F, false)).setAlwaysEdible().setTranslationKey("appleGold"));
      registerItem(323, (String)"sign", (new Qo()).setTranslationKey("sign"));
      registerItem(324, (String)"wooden_door", (new Pp(Nk.OAK_DOOR)).setTranslationKey("doorOak"));
      OL item = (new Pe(Nk.AIR)).setTranslationKey("bucket").setMaxStackSize(16);
      registerItem(325, (String)"bucket", item);
      registerItem(326, (String)"water_bucket", (new Pe(Nk.FLOWING_WATER)).setTranslationKey("bucketWater").setContainerItem(item));
      registerItem(327, (String)"lava_bucket", (new Pe(Nk.FLOWING_LAVA)).setTranslationKey("bucketLava").setContainerItem(item));
      registerItem(328, (String)"minecart", (new PW(Jb.RIDEABLE)).setTranslationKey("minecart"));
      registerItem(329, (String)"saddle", (new Qh()).setTranslationKey("saddle"));
      registerItem(330, (String)"iron_door", (new Pp(Nk.IRON_DOOR)).setTranslationKey("doorIron"));
      registerItem(331, (String)"redstone", (new Qg()).setTranslationKey("redstone"));
      registerItem(332, (String)"snowball", (new Qt()).setTranslationKey("snowball"));
      registerItem(333, (String)"boat", new OZ(IQ.OAK));
      registerItem(334, (String)"leather", (new OL()).setTranslationKey("leather").setCreativeTab(EN.MATERIALS));
      registerItem(335, (String)"milk_bucket", (new Pf()).setTranslationKey("milk").setContainerItem(item));
      registerItem(336, (String)"brick", (new OL()).setTranslationKey("brick").setCreativeTab(EN.MATERIALS));
      registerItem(337, (String)"clay_ball", (new OL()).setTranslationKey("clay").setCreativeTab(EN.MATERIALS));
      registerItem(338, (String)"reeds", (new OY(Nk.REEDS)).setTranslationKey("reeds").setCreativeTab(EN.MATERIALS));
      registerItem(339, (String)"paper", (new OL()).setTranslationKey("paper").setCreativeTab(EN.MISC));
      registerItem(340, (String)"book", (new Pa()).setTranslationKey("book").setCreativeTab(EN.MISC));
      registerItem(341, (String)"slime_ball", (new OL()).setTranslationKey("slimeball").setCreativeTab(EN.MISC));
      registerItem(342, (String)"chest_minecart", (new PW(Jb.CHEST)).setTranslationKey("minecartChest"));
      registerItem(343, (String)"furnace_minecart", (new PW(Jb.FURNACE)).setTranslationKey("minecartFurnace"));
      registerItem(344, (String)"egg", (new Pr()).setTranslationKey("egg"));
      registerItem(345, (String)"compass", (new Po()).setTranslationKey("compass").setCreativeTab(EN.TOOLS));
      registerItem(346, (String)"fishing_rod", (new PG()).setTranslationKey("fishingRod"));
      registerItem(347, (String)"clock", (new Pj()).setTranslationKey("clock").setCreativeTab(EN.TOOLS));
      registerItem(348, (String)"glowstone_dust", (new OL()).setTranslationKey("yellowDust").setCreativeTab(EN.MATERIALS));
      registerItem(349, (String)"fish", (new PE(false)).setTranslationKey("fish").setHasSubtypes(true));
      registerItem(350, (String)"cooked_fish", (new PE(true)).setTranslationKey("fish").setHasSubtypes(true));
      registerItem(351, (String)"dye", (new Pq()).setTranslationKey("dyePowder"));
      registerItem(352, (String)"bone", (new OL()).setTranslationKey("bone").setFull3D().setCreativeTab(EN.MISC));
      registerItem(353, (String)"sugar", (new OL()).setTranslationKey("sugar").setCreativeTab(EN.MATERIALS));
      registerItem(354, (String)"cake", (new OY(Nk.CAKE)).setMaxStackSize(1).setTranslationKey("cake").setCreativeTab(EN.FOOD));
      registerItem(355, (String)"bed", (new OW()).setMaxStackSize(1).setTranslationKey("bed"));
      registerItem(356, (String)"repeater", (new OY(Nk.UNPOWERED_REPEATER)).setTranslationKey("diode").setCreativeTab(EN.REDSTONE));
      registerItem(357, (String)"cookie", (new PI(2, 0.1F, false)).setTranslationKey("cookie"));
      registerItem(358, (String)"filled_map", (new PT()).setTranslationKey("map"));
      registerItem(359, (String)"shears", (new Qk()).setTranslationKey("shears"));
      registerItem(360, (String)"melon", (new PI(2, 0.3F, false)).setTranslationKey("melon"));
      registerItem(361, (String)"pumpkin_seeds", (new Qj(Nk.PUMPKIN_STEM, Nk.FARMLAND)).setTranslationKey("seeds_pumpkin"));
      registerItem(362, (String)"melon_seeds", (new Qj(Nk.MELON_STEM, Nk.FARMLAND)).setTranslationKey("seeds_melon"));
      registerItem(363, (String)"beef", (new PI(3, 0.3F, true)).setTranslationKey("beefRaw"));
      registerItem(364, (String)"cooked_beef", (new PI(8, 0.8F, true)).setTranslationKey("beefCooked"));
      registerItem(365, (String)"chicken", (new PI(2, 0.3F, true)).setPotionEffect(new VZ(NL.HUNGER, 600, 0), 0.3F).setTranslationKey("chickenRaw"));
      registerItem(366, (String)"cooked_chicken", (new PI(6, 0.6F, true)).setTranslationKey("chickenCooked"));
      registerItem(367, (String)"rotten_flesh", (new PI(4, 0.1F, true)).setPotionEffect(new VZ(NL.HUNGER, 600, 0), 0.8F).setTranslationKey("rottenFlesh"));
      registerItem(368, (String)"ender_pearl", (new Py()).setTranslationKey("enderPearl"));
      registerItem(369, (String)"blaze_rod", (new OL()).setTranslationKey("blazeRod").setCreativeTab(EN.MATERIALS).setFull3D());
      registerItem(370, (String)"ghast_tear", (new OL()).setTranslationKey("ghastTear").setCreativeTab(EN.BREWING));
      registerItem(371, (String)"gold_nugget", (new OL()).setTranslationKey("goldNugget").setCreativeTab(EN.MATERIALS));
      registerItem(372, (String)"nether_wart", (new Qj(Nk.NETHER_WART, Nk.SOUL_SAND)).setTranslationKey("netherStalkSeeds"));
      registerItem(373, (String)"potion", (new Qe()).setTranslationKey("potion"));
      OL item1 = (new PK()).setTranslationKey("glassBottle");
      registerItem(374, (String)"glass_bottle", item1);
      registerItem(375, (String)"spider_eye", (new PI(2, 0.8F, false)).setPotionEffect(new VZ(NL.POISON, 100, 0), 1.0F).setTranslationKey("spiderEye"));
      registerItem(376, (String)"fermented_spider_eye", (new OL()).setTranslationKey("fermentedSpiderEye").setCreativeTab(EN.BREWING));
      registerItem(377, (String)"blaze_powder", (new OL()).setTranslationKey("blazePowder").setCreativeTab(EN.BREWING));
      registerItem(378, (String)"magma_cream", (new OL()).setTranslationKey("magmaCream").setCreativeTab(EN.BREWING));
      registerItem(379, (String)"brewing_stand", (new OY(Nk.BREWING_STAND)).setTranslationKey("brewingStand").setCreativeTab(EN.BREWING));
      registerItem(380, (String)"cauldron", (new OY(Nk.CAULDRON)).setTranslationKey("cauldron").setCreativeTab(EN.BREWING));
      registerItem(381, (String)"ender_eye", (new Px()).setTranslationKey("eyeOfEnder"));
      registerItem(382, (String)"speckled_melon", (new OL()).setTranslationKey("speckledMelon").setCreativeTab(EN.BREWING));
      registerItem(383, (String)"spawn_egg", (new PX()).setTranslationKey("monsterPlacer"));
      registerItem(384, (String)"experience_bottle", (new Pz()).setTranslationKey("expBottle"));
      registerItem(385, (String)"fire_charge", (new PA()).setTranslationKey("fireball"));
      registerItem(386, (String)"writable_book", (new QC()).setTranslationKey("writingBook").setCreativeTab(EN.MISC));
      registerItem(387, (String)"written_book", (new QD()).setTranslationKey("writtenBook").setMaxStackSize(16));
      registerItem(388, (String)"emerald", (new OL()).setTranslationKey("emerald").setCreativeTab(EN.MATERIALS));
      registerItem(389, (String)"item_frame", (new PL(IZ.class)).setTranslationKey("frame"));
      registerItem(390, (String)"flower_pot", (new OY(Nk.FLOWER_POT)).setTranslationKey("flowerPot").setCreativeTab(EN.DECORATIONS));
      registerItem(391, (String)"carrot", (new Qi(3, 0.6F, Nk.CARROTS, Nk.FARMLAND)).setTranslationKey("carrots"));
      registerItem(392, (String)"potato", (new Qi(1, 0.3F, Nk.POTATOES, Nk.FARMLAND)).setTranslationKey("potato"));
      registerItem(393, (String)"baked_potato", (new PI(5, 0.6F, false)).setTranslationKey("potatoBaked"));
      registerItem(394, (String)"poisonous_potato", (new PI(2, 0.3F, false)).setPotionEffect(new VZ(NL.POISON, 100, 0), 0.6F).setTranslationKey("potatoPoisonous"));
      registerItem(395, (String)"map", (new Pu()).setTranslationKey("emptyMap"));
      registerItem(396, (String)"golden_carrot", (new PI(6, 1.2F, false)).setTranslationKey("carrotGolden").setCreativeTab(EN.BREWING));
      registerItem(397, (String)"skull", (new Qq()).setTranslationKey("skull"));
      registerItem(398, (String)"carrot_on_a_stick", (new Pg()).setTranslationKey("carrotOnAStick"));
      registerItem(399, (String)"nether_star", (new Qp()).setTranslationKey("netherStar").setCreativeTab(EN.MATERIALS));
      registerItem(400, (String)"pumpkin_pie", (new PI(8, 0.3F, false)).setTranslationKey("pumpkinPie").setCreativeTab(EN.FOOD));
      registerItem(401, (String)"fireworks", (new PB()).setTranslationKey("fireworks"));
      registerItem(402, (String)"firework_charge", (new PC()).setTranslationKey("fireworksCharge").setCreativeTab(EN.MISC));
      registerItem(403, (String)"enchanted_book", (new Pv()).setMaxStackSize(1).setTranslationKey("enchantedBook"));
      registerItem(404, (String)"comparator", (new OY(Nk.UNPOWERED_COMPARATOR)).setTranslationKey("comparator").setCreativeTab(EN.REDSTONE));
      registerItem(405, (String)"netherbrick", (new OL()).setTranslationKey("netherbrick").setCreativeTab(EN.MATERIALS));
      registerItem(406, (String)"quartz", (new OL()).setTranslationKey("netherquartz").setCreativeTab(EN.MATERIALS));
      registerItem(407, (String)"tnt_minecart", (new PW(Jb.TNT)).setTranslationKey("minecartTnt"));
      registerItem(408, (String)"hopper_minecart", (new PW(Jb.HOPPER)).setTranslationKey("minecartHopper"));
      registerItem(409, (String)"prismarine_shard", (new OL()).setTranslationKey("prismarineShard").setCreativeTab(EN.MATERIALS));
      registerItem(410, (String)"prismarine_crystals", (new OL()).setTranslationKey("prismarineCrystals").setCreativeTab(EN.MATERIALS));
      registerItem(411, (String)"rabbit", (new PI(3, 0.3F, true)).setTranslationKey("rabbitRaw"));
      registerItem(412, (String)"cooked_rabbit", (new PI(5, 0.6F, true)).setTranslationKey("rabbitCooked"));
      registerItem(413, (String)"rabbit_stew", (new Qu(10)).setTranslationKey("rabbitStew"));
      registerItem(414, (String)"rabbit_foot", (new OL()).setTranslationKey("rabbitFoot").setCreativeTab(EN.BREWING));
      registerItem(415, (String)"rabbit_hide", (new OL()).setTranslationKey("rabbitHide").setCreativeTab(EN.MATERIALS));
      registerItem(416, (String)"armor_stand", (new OS()).setTranslationKey("armorStand").setMaxStackSize(16));
      registerItem(417, (String)"iron_horse_armor", (new OL()).setTranslationKey("horsearmormetal").setMaxStackSize(1).setCreativeTab(EN.MISC));
      registerItem(418, (String)"golden_horse_armor", (new OL()).setTranslationKey("horsearmorgold").setMaxStackSize(1).setCreativeTab(EN.MISC));
      registerItem(419, (String)"diamond_horse_armor", (new OL()).setTranslationKey("horsearmordiamond").setMaxStackSize(1).setCreativeTab(EN.MISC));
      registerItem(420, (String)"lead", (new PP()).setTranslationKey("leash"));
      registerItem(421, (String)"name_tag", (new Qb()).setTranslationKey("nameTag"));
      registerItem(422, (String)"command_block_minecart", (new PW(Jb.COMMAND_BLOCK)).setTranslationKey("minecartCommandBlock").setCreativeTab((EN)null));
      registerItem(423, (String)"mutton", (new PI(2, 0.3F, true)).setTranslationKey("muttonRaw"));
      registerItem(424, (String)"cooked_mutton", (new PI(6, 0.8F, true)).setTranslationKey("muttonCooked"));
      registerItem(425, (String)"banner", (new OV()).setTranslationKey("banner"));
      registerItem(426, (String)"end_crystal", new Pw());
      registerItem(427, (String)"spruce_door", (new Pp(Nk.SPRUCE_DOOR)).setTranslationKey("doorSpruce"));
      registerItem(428, (String)"birch_door", (new Pp(Nk.BIRCH_DOOR)).setTranslationKey("doorBirch"));
      registerItem(429, (String)"jungle_door", (new Pp(Nk.JUNGLE_DOOR)).setTranslationKey("doorJungle"));
      registerItem(430, (String)"acacia_door", (new Pp(Nk.ACACIA_DOOR)).setTranslationKey("doorAcacia"));
      registerItem(431, (String)"dark_oak_door", (new Pp(Nk.DARK_OAK_DOOR)).setTranslationKey("doorDarkOak"));
      registerItem(432, (String)"chorus_fruit", (new Ph(4, 0.3F)).setAlwaysEdible().setTranslationKey("chorusFruit").setCreativeTab(EN.MATERIALS));
      registerItem(433, (String)"chorus_fruit_popped", (new OL()).setTranslationKey("chorusFruitPopped").setCreativeTab(EN.MATERIALS));
      registerItem(434, (String)"beetroot", (new PI(1, 0.6F, false)).setTranslationKey("beetroot"));
      registerItem(435, (String)"beetroot_seeds", (new Qj(Nk.BEETROOTS, Nk.FARMLAND)).setTranslationKey("beetroot_seeds"));
      registerItem(436, (String)"beetroot_soup", (new Qu(6)).setTranslationKey("beetroot_soup"));
      registerItem(437, (String)"dragon_breath", (new OL()).setCreativeTab(EN.BREWING).setTranslationKey("dragon_breath").setContainerItem(item1));
      registerItem(438, (String)"splash_potion", (new Qx()).setTranslationKey("splash_potion"));
      registerItem(439, (String)"spectral_arrow", (new Qw()).setTranslationKey("spectral_arrow"));
      registerItem(440, (String)"tipped_arrow", (new QA()).setTranslationKey("tipped_arrow"));
      registerItem(441, (String)"lingering_potion", (new PS()).setTranslationKey("lingering_potion"));
      registerItem(442, (String)"shield", (new Qm()).setTranslationKey("shield"));
      registerItem(443, (String)"elytra", (new Pt()).setTranslationKey("elytra"));
      registerItem(444, (String)"spruce_boat", new OZ(IQ.SPRUCE));
      registerItem(445, (String)"birch_boat", new OZ(IQ.BIRCH));
      registerItem(446, (String)"jungle_boat", new OZ(IQ.JUNGLE));
      registerItem(447, (String)"acacia_boat", new OZ(IQ.ACACIA));
      registerItem(448, (String)"dark_oak_boat", new OZ(IQ.DARK_OAK));
      registerItem(449, (String)"totem_of_undying", (new OL()).setTranslationKey("totem").setMaxStackSize(1).setCreativeTab(EN.COMBAT));
      registerItem(450, (String)"shulker_shell", (new OL()).setTranslationKey("shulkerShell").setCreativeTab(EN.MATERIALS));
      registerItem(452, (String)"iron_nugget", (new OL()).setTranslationKey("ironNugget").setCreativeTab(EN.MATERIALS));
      registerItem(453, (String)"knowledge_book", (new PO()).setTranslationKey("knowledgeBook"));
      registerItem(2256, (String)"record_13", (new Qf("13", NO.RECORD_13)).setTranslationKey("record"));
      registerItem(2257, (String)"record_cat", (new Qf("cat", NO.RECORD_CAT)).setTranslationKey("record"));
      registerItem(2258, (String)"record_blocks", (new Qf("blocks", NO.RECORD_BLOCKS)).setTranslationKey("record"));
      registerItem(2259, (String)"record_chirp", (new Qf("chirp", NO.RECORD_CHIRP)).setTranslationKey("record"));
      registerItem(2260, (String)"record_far", (new Qf("far", NO.RECORD_FAR)).setTranslationKey("record"));
      registerItem(2261, (String)"record_mall", (new Qf("mall", NO.RECORD_MALL)).setTranslationKey("record"));
      registerItem(2262, (String)"record_mellohi", (new Qf("mellohi", NO.RECORD_MELLOHI)).setTranslationKey("record"));
      registerItem(2263, (String)"record_stal", (new Qf("stal", NO.RECORD_STAL)).setTranslationKey("record"));
      registerItem(2264, (String)"record_strad", (new Qf("strad", NO.RECORD_STRAD)).setTranslationKey("record"));
      registerItem(2265, (String)"record_ward", (new Qf("ward", NO.RECORD_WARD)).setTranslationKey("record"));
      registerItem(2266, (String)"record_11", (new Qf("11", NO.RECORD_11)).setTranslationKey("record"));
      registerItem(2267, (String)"record_wait", (new Qf("wait", NO.RECORD_WAIT)).setTranslationKey("record"));
   }

   private static void registerItemBlock(co blockIn) {
      registerItemBlock(blockIn, new OX(blockIn));
   }

   protected static void registerItemBlock(co blockIn, OL itemIn) {
      registerItem(co.getIdFromBlock(blockIn), (ResourceLocation)co.REGISTRY.getNameForObject(blockIn), itemIn);
      BLOCK_TO_ITEM.put(blockIn, itemIn);
   }

   private static void registerItem(int id, String textualID, OL itemIn) {
      registerItem(id, new ResourceLocation(textualID), itemIn);
   }

   private static void registerItem(int id, ResourceLocation textualID, OL itemIn) {
      REGISTRY.register(id, textualID, itemIn);
   }

   public Qy getDefaultInstance() {
      return new Qy(this);
   }
}
