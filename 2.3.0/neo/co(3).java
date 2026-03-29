package neo;

import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ObjectIntIdentityMap;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.RegistryNamespacedDefaultedByKey;
import net.minecraft.util.text.translation.I18n;

public class co {
   private static final ResourceLocation AIR_ID = new ResourceLocation("air");
   public static final RegistryNamespacedDefaultedByKey<ResourceLocation, co> REGISTRY;
   public static final ObjectIntIdentityMap<in> BLOCK_STATE_IDS;
   public static final AxisAlignedBB FULL_BLOCK_AABB;
   @Nullable
   public static final AxisAlignedBB NULL_AABB;
   private EN displayOnCreativeTab;
   protected boolean fullBlock;
   protected int lightOpacity;
   protected boolean translucent;
   protected int lightValue;
   protected boolean useNeighborBrightness;
   protected float blockHardness;
   protected float blockResistance;
   protected boolean enableStats;
   protected boolean needsRandomTick;
   protected boolean hasTileEntity;
   protected ia blockSoundType;
   public float blockParticleGravity;
   protected final hM material;
   protected final hK blockMapColor;
   public float slipperiness;
   protected final ii blockState;
   private in defaultBlockState;
   private String translationKey;

   public static int getIdFromBlock(co blockIn) {
      return REGISTRY.getIDForObject(blockIn);
   }

   public static int getStateId(in state) {
      co block = state.getBlock();
      return getIdFromBlock(block) + (block.getMetaFromState(state) << 12);
   }

   public static co getBlockById(int id) {
      return (co)REGISTRY.getObjectById(id);
   }

   public static in getStateById(int id) {
      int i = id & 4095;
      int j = id >> 12 & 15;
      return getBlockById(i).getStateFromMeta(j);
   }

   public static co getBlockFromItem(@Nullable OL itemIn) {
      return itemIn instanceof OX ? ((OX)itemIn).getBlock() : Nk.AIR;
   }

   @Nullable
   public static co getBlockFromName(String name) {
      ResourceLocation resourcelocation = new ResourceLocation(name);
      if (REGISTRY.containsKey(resourcelocation)) {
         return (co)REGISTRY.getObject(resourcelocation);
      } else {
         try {
            return (co)REGISTRY.getObjectById(Integer.parseInt(name));
         } catch (NumberFormatException var3) {
            return null;
         }
      }
   }

   /** @deprecated */
   @Deprecated
   public boolean isTopSolid(in state) {
      return state.getMaterial().isOpaque() && state.isFullCube();
   }

   /** @deprecated */
   @Deprecated
   public boolean isFullBlock(in state) {
      return this.fullBlock;
   }

   /** @deprecated */
   @Deprecated
   public boolean canEntitySpawn(in state, Ig entityIn) {
      return true;
   }

   /** @deprecated */
   @Deprecated
   public int getLightOpacity(in state) {
      return this.lightOpacity;
   }

   /** @deprecated */
   @Deprecated
   public boolean isTranslucent(in state) {
      return this.translucent;
   }

   /** @deprecated */
   @Deprecated
   public int getLightValue(in state) {
      return this.lightValue;
   }

   /** @deprecated */
   @Deprecated
   public boolean getUseNeighborBrightness(in state) {
      return this.useNeighborBrightness;
   }

   /** @deprecated */
   @Deprecated
   public hM getMaterial(in state) {
      return this.material;
   }

   /** @deprecated */
   @Deprecated
   public hK getMapColor(in state, bfZ worldIn, BlockPos pos) {
      return this.blockMapColor;
   }

   /** @deprecated */
   @Deprecated
   public in getStateFromMeta(int meta) {
      return this.getDefaultState();
   }

   public int getMetaFromState(in state) {
      if (state.getPropertyKeys().isEmpty()) {
         return 0;
      } else {
         throw new IllegalArgumentException("Don't know how to convert " + state + " back into data...");
      }
   }

   /** @deprecated */
   @Deprecated
   public in getActualState(in state, bfZ worldIn, BlockPos pos) {
      return state;
   }

   /** @deprecated */
   @Deprecated
   public in withRotation(in state, Rotation rot) {
      return state;
   }

   /** @deprecated */
   @Deprecated
   public in withMirror(in state, Mirror mirrorIn) {
      return state;
   }

   public co(hM blockMaterialIn, hK blockMapColorIn) {
      this.enableStats = true;
      this.blockSoundType = ia.STONE;
      this.blockParticleGravity = 1.0F;
      this.slipperiness = 0.6F;
      this.material = blockMaterialIn;
      this.blockMapColor = blockMapColorIn;
      this.blockState = this.createBlockState();
      this.setDefaultState(this.blockState.getBaseState());
      this.fullBlock = this.getDefaultState().isOpaqueCube();
      this.lightOpacity = this.fullBlock ? 255 : 0;
      this.translucent = !blockMaterialIn.blocksLight();
   }

   protected co(hM materialIn) {
      this(materialIn, materialIn.getMaterialMapColor());
   }

   protected co setSoundType(ia sound) {
      this.blockSoundType = sound;
      return this;
   }

   protected co setLightOpacity(int opacity) {
      this.lightOpacity = opacity;
      return this;
   }

   protected co setLightLevel(float value) {
      this.lightValue = (int)(15.0F * value);
      return this;
   }

   protected co setResistance(float resistance) {
      this.blockResistance = resistance * 3.0F;
      return this;
   }

   protected static boolean isExceptionBlockForAttaching(co attachBlock) {
      return attachBlock instanceof gr || attachBlock instanceof ew || attachBlock instanceof hr || attachBlock == Nk.BEACON || attachBlock == Nk.CAULDRON || attachBlock == Nk.GLASS || attachBlock == Nk.GLOWSTONE || attachBlock == Nk.ICE || attachBlock == Nk.SEA_LANTERN || attachBlock == Nk.STAINED_GLASS;
   }

   protected static boolean isExceptBlockForAttachWithPiston(co attachBlock) {
      return isExceptionBlockForAttaching(attachBlock) || attachBlock == Nk.PISTON || attachBlock == Nk.STICKY_PISTON || attachBlock == Nk.PISTON_HEAD;
   }

   /** @deprecated */
   @Deprecated
   public boolean isBlockNormalCube(in state) {
      return state.getMaterial().blocksMovement() && state.isFullCube();
   }

   /** @deprecated */
   @Deprecated
   public boolean isNormalCube(in state) {
      return state.getMaterial().isOpaque() && state.isFullCube() && !state.canProvidePower();
   }

   /** @deprecated */
   @Deprecated
   public boolean causesSuffocation(in state) {
      return this.material.blocksMovement() && this.getDefaultState().isFullCube();
   }

   /** @deprecated */
   @Deprecated
   public boolean isFullCube(in state) {
      return true;
   }

   /** @deprecated */
   @Deprecated
   public boolean hasCustomBreakingProgress(in state) {
      return false;
   }

   public boolean isPassable(bfZ worldIn, BlockPos pos) {
      return !this.material.blocksMovement();
   }

   /** @deprecated */
   @Deprecated
   public EnumBlockRenderType getRenderType(in state) {
      return EnumBlockRenderType.MODEL;
   }

   public boolean isReplaceable(bfZ worldIn, BlockPos pos) {
      return false;
   }

   protected co setHardness(float hardness) {
      this.blockHardness = hardness;
      if (this.blockResistance < hardness * 5.0F) {
         this.blockResistance = hardness * 5.0F;
      }

      return this;
   }

   protected co setBlockUnbreakable() {
      this.setHardness(-1.0F);
      return this;
   }

   /** @deprecated */
   @Deprecated
   public float getBlockHardness(in blockState, bij worldIn, BlockPos pos) {
      return this.blockHardness;
   }

   protected co setTickRandomly(boolean shouldTick) {
      this.needsRandomTick = shouldTick;
      return this;
   }

   public boolean getTickRandomly() {
      return this.needsRandomTick;
   }

   public boolean hasTileEntity() {
      return this.hasTileEntity;
   }

   /** @deprecated */
   @Deprecated
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      return FULL_BLOCK_AABB;
   }

   /** @deprecated */
   @Deprecated
   public int getPackedLightmapCoords(in state, bfZ source, BlockPos pos) {
      int i = source.getCombinedLight(pos, state.getLightValue());
      if (i == 0 && state.getBlock() instanceof gG) {
         pos = pos.down();
         state = source.getBlockState(pos);
         return source.getCombinedLight(pos, state.getLightValue());
      } else {
         return i;
      }
   }

   /** @deprecated */
   @Deprecated
   public boolean shouldSideBeRendered(in blockState, bfZ blockAccess, BlockPos pos, EnumFacing side) {
      AxisAlignedBB axisalignedbb = blockState.getBoundingBox(blockAccess, pos);
      switch (side) {
         case DOWN:
            if (axisalignedbb.minY > 0.0) {
               return true;
            }
            break;
         case UP:
            if (axisalignedbb.maxY < 1.0) {
               return true;
            }
            break;
         case NORTH:
            if (axisalignedbb.minZ > 0.0) {
               return true;
            }
            break;
         case SOUTH:
            if (axisalignedbb.maxZ < 1.0) {
               return true;
            }
            break;
         case WEST:
            if (axisalignedbb.minX > 0.0) {
               return true;
            }
            break;
         case EAST:
            if (axisalignedbb.maxX < 1.0) {
               return true;
            }
      }

      return !blockAccess.getBlockState(pos.offset(side)).isOpaqueCube();
   }

   /** @deprecated */
   @Deprecated
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return ib.SOLID;
   }

   /** @deprecated */
   @Deprecated
   public AxisAlignedBB getSelectedBoundingBox(in state, bij worldIn, BlockPos pos) {
      return state.getBoundingBox(worldIn, pos).offset(pos);
   }

   /** @deprecated */
   @Deprecated
   public void addCollisionBoxToList(in state, bij worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Ig entityIn, boolean isActualState) {
      addCollisionBoxToList(pos, entityBox, collidingBoxes, state.getCollisionBoundingBox(worldIn, pos));
   }

   /** @deprecated */
   protected static void addCollisionBoxToList(BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable AxisAlignedBB blockBox) {
      if (blockBox != NULL_AABB) {
         AxisAlignedBB axisalignedbb = blockBox.offset(pos);
         if (entityBox.intersects(axisalignedbb)) {
            collidingBoxes.add(axisalignedbb);
         }
      }

   }

   /** @deprecated */
   @Deprecated
   @Nullable
   public AxisAlignedBB getCollisionBoundingBox(in blockState, bfZ worldIn, BlockPos pos) {
      return blockState.getBoundingBox(worldIn, pos);
   }

   /** @deprecated */
   @Deprecated
   public boolean isOpaqueCube(in state) {
      return true;
   }

   public boolean canCollideCheck(in state, boolean hitIfLiquid) {
      return this.isCollidable();
   }

   public boolean isCollidable() {
      return true;
   }

   public void randomTick(bij worldIn, BlockPos pos, in state, Random random) {
      this.updateTick(worldIn, pos, state, random);
   }

   public void updateTick(bij worldIn, BlockPos pos, in state, Random rand) {
   }

   public void randomDisplayTick(in stateIn, bij worldIn, BlockPos pos, Random rand) {
   }

   public void onPlayerDestroy(bij worldIn, BlockPos pos, in state) {
   }

   /** @deprecated */
   @Deprecated
   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
   }

   public int tickRate(bij worldIn) {
      return 10;
   }

   public void onBlockAdded(bij worldIn, BlockPos pos, in state) {
   }

   public void breakBlock(bij worldIn, BlockPos pos, in state) {
   }

   public int quantityDropped(Random random) {
      return 1;
   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return OL.getItemFromBlock(this);
   }

   /** @deprecated */
   @Deprecated
   public float getPlayerRelativeBlockHardness(in state, ME player, bij worldIn, BlockPos pos) {
      float f = state.getBlockHardness(worldIn, pos);
      if (f < 0.0F) {
         return 0.0F;
      } else {
         return !player.canHarvestBlock(state) ? player.getDigSpeed(state) / f / 100.0F : player.getDigSpeed(state) / f / 30.0F;
      }
   }

   public final void dropBlockAsItem(bij worldIn, BlockPos pos, in state, int fortune) {
      this.dropBlockAsItemWithChance(worldIn, pos, state, 1.0F, fortune);
   }

   public void dropBlockAsItemWithChance(bij worldIn, BlockPos pos, in state, float chance, int fortune) {
      if (!worldIn.isRemote) {
         int i = this.quantityDroppedWithBonus(fortune, worldIn.rand);

         for(int j = 0; j < i; ++j) {
            if (worldIn.rand.nextFloat() <= chance) {
               OL item = this.getItemDropped(state, worldIn.rand, fortune);
               if (item != NK.AIR) {
                  spawnAsEntity(worldIn, pos, new Qy(item, 1, this.damageDropped(state)));
               }
            }
         }
      }

   }

   public static void spawnAsEntity(bij worldIn, BlockPos pos, Qy stack) {
      if (!worldIn.isRemote && !stack.isEmpty() && worldIn.getGameRules().getBoolean("doTileDrops")) {
         float f = 0.5F;
         double d0 = (double)(worldIn.rand.nextFloat() * 0.5F) + 0.25;
         double d1 = (double)(worldIn.rand.nextFloat() * 0.5F) + 0.25;
         double d2 = (double)(worldIn.rand.nextFloat() * 0.5F) + 0.25;
         IY entityitem = new IY(worldIn, (double)pos.getX() + d0, (double)pos.getY() + d1, (double)pos.getZ() + d2, stack);
         entityitem.setDefaultPickupDelay();
         worldIn.spawnEntity(entityitem);
      }

   }

   protected void dropXpOnBlockBreak(bij worldIn, BlockPos pos, int amount) {
      if (!worldIn.isRemote && worldIn.getGameRules().getBoolean("doTileDrops")) {
         while(amount > 0) {
            int i = Js.getXPSplit(amount);
            amount -= i;
            worldIn.spawnEntity(new Js(worldIn, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, i));
         }
      }

   }

   public int damageDropped(in state) {
      return 0;
   }

   public float getExplosionResistance(Ig exploder) {
      return this.blockResistance / 5.0F;
   }

   /** @deprecated */
   @Deprecated
   @Nullable
   public RayTraceResult collisionRayTrace(in blockState, bij worldIn, BlockPos pos, Vec3d start, Vec3d end) {
      return this.rayTrace(pos, start, end, blockState.getBoundingBox(worldIn, pos));
   }

   @Nullable
   protected RayTraceResult rayTrace(BlockPos pos, Vec3d start, Vec3d end, AxisAlignedBB boundingBox) {
      Vec3d vec3d = start.subtract((double)pos.getX(), (double)pos.getY(), (double)pos.getZ());
      Vec3d vec3d1 = end.subtract((double)pos.getX(), (double)pos.getY(), (double)pos.getZ());
      RayTraceResult raytraceresult = boundingBox.calculateIntercept(vec3d, vec3d1);
      return raytraceresult == null ? null : new RayTraceResult(raytraceresult.hitVec.add((double)pos.getX(), (double)pos.getY(), (double)pos.getZ()), raytraceresult.sideHit, pos);
   }

   public void onExplosionDestroy(bij worldIn, BlockPos pos, baX explosionIn) {
   }

   public BlockRenderLayer getRenderLayer() {
      return BlockRenderLayer.SOLID;
   }

   public boolean canPlaceBlockOnSide(bij worldIn, BlockPos pos, EnumFacing side) {
      return this.canPlaceBlockAt(worldIn, pos);
   }

   public boolean canPlaceBlockAt(bij worldIn, BlockPos pos) {
      return worldIn.getBlockState(pos).getBlock().material.isReplaceable();
   }

   public boolean onBlockActivated(bij worldIn, BlockPos pos, in state, ME playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      return false;
   }

   public void onEntityWalk(bij worldIn, BlockPos pos, Ig entityIn) {
   }

   public in getStateForPlacement(bij worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, Iw placer) {
      return this.getStateFromMeta(meta);
   }

   public void onBlockClicked(bij worldIn, BlockPos pos, ME playerIn) {
   }

   public Vec3d modifyAcceleration(bij worldIn, BlockPos pos, Ig entityIn, Vec3d motion) {
      return motion;
   }

   /** @deprecated */
   @Deprecated
   public int getWeakPower(in blockState, bfZ blockAccess, BlockPos pos, EnumFacing side) {
      return 0;
   }

   /** @deprecated */
   @Deprecated
   public boolean canProvidePower(in state) {
      return false;
   }

   public void onEntityCollision(bij worldIn, BlockPos pos, in state, Ig entityIn) {
   }

   /** @deprecated */
   @Deprecated
   public int getStrongPower(in blockState, bfZ blockAccess, BlockPos pos, EnumFacing side) {
      return 0;
   }

   public void harvestBlock(bij worldIn, ME player, BlockPos pos, in state, @Nullable Yg te, Qy stack) {
      player.addStat(XV.getBlockStats(this));
      player.addExhaustion(0.005F);
      if (this.canSilkHarvest() && Ft.getEnchantmentLevel(NJ.SILK_TOUCH, stack) > 0) {
         Qy itemstack = this.getSilkTouchDrop(state);
         spawnAsEntity(worldIn, pos, itemstack);
      } else {
         int i = Ft.getEnchantmentLevel(NJ.FORTUNE, stack);
         this.dropBlockAsItem(worldIn, pos, state, i);
      }

   }

   protected boolean canSilkHarvest() {
      return this.getDefaultState().isFullCube() && !this.hasTileEntity;
   }

   protected Qy getSilkTouchDrop(in state) {
      OL item = OL.getItemFromBlock(this);
      int i = 0;
      if (item.getHasSubtypes()) {
         i = this.getMetaFromState(state);
      }

      return new Qy(item, 1, i);
   }

   public int quantityDroppedWithBonus(int fortune, Random random) {
      return this.quantityDropped(random);
   }

   public void onBlockPlacedBy(bij worldIn, BlockPos pos, in state, Iw placer, Qy stack) {
   }

   public boolean canSpawnInBlock() {
      return !this.material.isSolid() && !this.material.isLiquid();
   }

   public co setTranslationKey(String key) {
      this.translationKey = key;
      return this;
   }

   public String getLocalizedName() {
      return I18n.translateToLocal(this.getTranslationKey() + ".name");
   }

   public String getTranslationKey() {
      return "tile." + this.translationKey;
   }

   /** @deprecated */
   @Deprecated
   public boolean eventReceived(in state, bij worldIn, BlockPos pos, int id, int param) {
      return false;
   }

   public boolean getEnableStats() {
      return this.enableStats;
   }

   protected co disableStats() {
      this.enableStats = false;
      return this;
   }

   /** @deprecated */
   @Deprecated
   public hJ getPushReaction(in state) {
      return this.material.getPushReaction();
   }

   /** @deprecated */
   @Deprecated
   public float getAmbientOcclusionLightValue(in state) {
      return state.isBlockNormalCube() ? 0.2F : 1.0F;
   }

   public void onFallenUpon(bij worldIn, BlockPos pos, Ig entityIn, float fallDistance) {
      entityIn.fall(fallDistance, 1.0F);
   }

   public void onLanded(bij worldIn, Ig entityIn) {
      entityIn.motionY = 0.0;
   }

   public Qy getItem(bij worldIn, BlockPos pos, in state) {
      return new Qy(OL.getItemFromBlock(this), 1, this.damageDropped(state));
   }

   public void getSubBlocks(EN itemIn, NonNullList<Qy> items) {
      items.add(new Qy(this));
   }

   public EN getCreativeTab() {
      return this.displayOnCreativeTab;
   }

   public co setCreativeTab(EN tab) {
      this.displayOnCreativeTab = tab;
      return this;
   }

   public void onBlockHarvested(bij worldIn, BlockPos pos, in state, ME player) {
   }

   public void fillWithRain(bij worldIn, BlockPos pos) {
   }

   public boolean requiresUpdates() {
      return true;
   }

   public boolean canDropFromExplosion(baX explosionIn) {
      return true;
   }

   public boolean isAssociatedBlock(co other) {
      return this == other;
   }

   public static boolean isEqualTo(co blockIn, co other) {
      if (blockIn != null && other != null) {
         return blockIn == other ? true : blockIn.isAssociatedBlock(other);
      } else {
         return false;
      }
   }

   /** @deprecated */
   @Deprecated
   public boolean hasComparatorInputOverride(in state) {
      return false;
   }

   /** @deprecated */
   @Deprecated
   public int getComparatorInputOverride(in blockState, bij worldIn, BlockPos pos) {
      return 0;
   }

   protected ii createBlockState() {
      return new ii(this, new hT[0]);
   }

   public ii getBlockState() {
      return this.blockState;
   }

   protected final void setDefaultState(in state) {
      this.defaultBlockState = state;
   }

   public final in getDefaultState() {
      return this.defaultBlockState;
   }

   public cn getOffsetType() {
      return cn.NONE;
   }

   /** @deprecated */
   @Deprecated
   public Vec3d getOffset(in state, bfZ worldIn, BlockPos pos) {
      cn block$enumoffsettype = this.getOffsetType();
      if (block$enumoffsettype == cn.NONE) {
         return Vec3d.ZERO;
      } else {
         long i = MathHelper.getCoordinateRandom(pos.getX(), 0, pos.getZ());
         return new Vec3d(((double)((float)(i >> 16 & 15L) / 15.0F) - 0.5) * 0.5, block$enumoffsettype == cn.XYZ ? ((double)((float)(i >> 20 & 15L) / 15.0F) - 1.0) * 0.2 : 0.0, ((double)((float)(i >> 24 & 15L) / 15.0F) - 0.5) * 0.5);
      }
   }

   public ia getSoundType() {
      return this.blockSoundType;
   }

   public String toString() {
      return "Block{" + REGISTRY.getNameForObject(this) + "}";
   }

   public void addInformation(Qy stack, @Nullable bij worldIn, List<String> tooltip, BJ flagIn) {
   }

   public static void registerBlocks() {
      registerBlock(0, (ResourceLocation)AIR_ID, (new cp()).setTranslationKey("air"));
      registerBlock(1, (String)"stone", (new gZ()).setHardness(1.5F).setResistance(10.0F).setSoundType(ia.STONE).setTranslationKey("stone"));
      registerBlock(2, (String)"grass", (new ec()).setHardness(0.6F).setSoundType(ia.PLANT).setTranslationKey("grass"));
      registerBlock(3, (String)"dirt", (new dj()).setHardness(0.5F).setSoundType(ia.GROUND).setTranslationKey("dirt"));
      co block = (new co(hM.ROCK)).setHardness(2.0F).setResistance(10.0F).setSoundType(ia.STONE).setTranslationKey("stonebrick").setCreativeTab(EN.BUILDING_BLOCKS);
      registerBlock(4, (String)"cobblestone", block);
      co block1 = (new fl()).setHardness(2.0F).setResistance(5.0F).setSoundType(ia.WOOD).setTranslationKey("wood");
      registerBlock(5, (String)"planks", block1);
      registerBlock(6, (String)"sapling", (new go()).setHardness(0.0F).setSoundType(ia.PLANT).setTranslationKey("sapling"));
      registerBlock(7, (String)"bedrock", (new dy(hM.ROCK)).setBlockUnbreakable().setResistance(6000000.0F).setSoundType(ia.STONE).setTranslationKey("bedrock").disableStats().setCreativeTab(EN.BUILDING_BLOCKS));
      registerBlock(8, (String)"flowing_water", (new dx(hM.WATER)).setHardness(100.0F).setLightOpacity(3).setTranslationKey("water").disableStats());
      registerBlock(9, (String)"water", (new gW(hM.WATER)).setHardness(100.0F).setLightOpacity(3).setTranslationKey("water").disableStats());
      registerBlock(10, (String)"flowing_lava", (new dx(hM.LAVA)).setHardness(100.0F).setLightLevel(1.0F).setTranslationKey("lava").disableStats());
      registerBlock(11, (String)"lava", (new gW(hM.LAVA)).setHardness(100.0F).setLightLevel(1.0F).setTranslationKey("lava").disableStats());
      registerBlock(12, (String)"sand", (new gk()).setHardness(0.5F).setSoundType(ia.SAND).setTranslationKey("sand"));
      registerBlock(13, (String)"gravel", (new ef()).setHardness(0.6F).setSoundType(ia.GROUND).setTranslationKey("gravel"));
      registerBlock(14, (String)"gold_ore", (new fa()).setHardness(3.0F).setResistance(5.0F).setSoundType(ia.STONE).setTranslationKey("oreGold"));
      registerBlock(15, (String)"iron_ore", (new fa()).setHardness(3.0F).setResistance(5.0F).setSoundType(ia.STONE).setTranslationKey("oreIron"));
      registerBlock(16, (String)"coal_ore", (new fa()).setHardness(3.0F).setResistance(5.0F).setSoundType(ia.STONE).setTranslationKey("oreCoal"));
      registerBlock(17, (String)"log", (new eZ()).setTranslationKey("log"));
      registerBlock(18, (String)"leaves", (new eW()).setTranslationKey("leaves"));
      registerBlock(19, (String)"sponge", (new gM()).setHardness(0.6F).setSoundType(ia.PLANT).setTranslationKey("sponge"));
      registerBlock(20, (String)"glass", (new dZ(hM.GLASS, false)).setHardness(0.3F).setSoundType(ia.GLASS).setTranslationKey("glass"));
      registerBlock(21, (String)"lapis_ore", (new fa()).setHardness(3.0F).setResistance(5.0F).setSoundType(ia.STONE).setTranslationKey("oreLapis"));
      registerBlock(22, (String)"lapis_block", (new co(hM.IRON, hK.LAPIS)).setHardness(3.0F).setResistance(5.0F).setSoundType(ia.STONE).setTranslationKey("blockLapis").setCreativeTab(EN.BUILDING_BLOCKS));
      registerBlock(23, (String)"dispenser", (new dk()).setHardness(3.5F).setSoundType(ia.STONE).setTranslationKey("dispenser"));
      co block2 = (new gm()).setSoundType(ia.STONE).setHardness(0.8F).setTranslationKey("sandStone");
      registerBlock(24, (String)"sandstone", block2);
      registerBlock(25, (String)"noteblock", (new eS()).setSoundType(ia.WOOD).setHardness(0.8F).setTranslationKey("musicBlock"));
      registerBlock(26, (String)"bed", (new cC()).setSoundType(ia.WOOD).setHardness(0.2F).setTranslationKey("bed").disableStats());
      registerBlock(27, (String)"golden_rail", (new fQ()).setHardness(0.7F).setSoundType(ia.METAL).setTranslationKey("goldenRail"));
      registerBlock(28, (String)"detector_rail", (new fN()).setHardness(0.7F).setSoundType(ia.METAL).setTranslationKey("detectorRail"));
      registerBlock(29, (String)"sticky_piston", (new ff(true)).setTranslationKey("pistonStickyBase"));
      registerBlock(30, (String)"web", (new hC()).setLightOpacity(1).setHardness(4.0F).setTranslationKey("web"));
      registerBlock(31, (String)"tallgrass", (new hk()).setHardness(0.0F).setSoundType(ia.PLANT).setTranslationKey("tallgrass"));
      registerBlock(32, (String)"deadbush", (new dg()).setHardness(0.0F).setSoundType(ia.PLANT).setTranslationKey("deadbush"));
      registerBlock(33, (String)"piston", (new ff(false)).setTranslationKey("pistonBase"));
      registerBlock(34, (String)"piston_head", (new fi()).setTranslationKey("pistonBase"));
      registerBlock(35, (String)"wool", (new cZ(hM.CLOTH)).setHardness(0.8F).setSoundType(ia.CLOTH).setTranslationKey("cloth"));
      registerBlock(36, (String)"piston_extension", new fj());
      registerBlock(37, (String)"yellow_flower", (new hG()).setHardness(0.0F).setSoundType(ia.PLANT).setTranslationKey("flower1"));
      registerBlock(38, (String)"red_flower", (new fR()).setHardness(0.0F).setSoundType(ia.PLANT).setTranslationKey("flower2"));
      co block3 = (new eI()).setHardness(0.0F).setSoundType(ia.PLANT).setLightLevel(0.125F).setTranslationKey("mushroom");
      registerBlock(39, (String)"brown_mushroom", block3);
      co block4 = (new eI()).setHardness(0.0F).setSoundType(ia.PLANT).setTranslationKey("mushroom");
      registerBlock(40, (String)"red_mushroom", block4);
      registerBlock(41, (String)"gold_block", (new co(hM.IRON, hK.GOLD)).setHardness(3.0F).setResistance(10.0F).setSoundType(ia.METAL).setTranslationKey("blockGold").setCreativeTab(EN.BUILDING_BLOCKS));
      registerBlock(42, (String)"iron_block", (new co(hM.IRON, hK.IRON)).setHardness(5.0F).setResistance(10.0F).setSoundType(ia.METAL).setTranslationKey("blockIron").setCreativeTab(EN.BUILDING_BLOCKS));
      registerBlock(43, (String)"double_stone_slab", (new ds()).setHardness(2.0F).setResistance(10.0F).setSoundType(ia.STONE).setTranslationKey("stoneSlab"));
      registerBlock(44, (String)"stone_slab", (new eg()).setHardness(2.0F).setResistance(10.0F).setSoundType(ia.STONE).setTranslationKey("stoneSlab"));
      co block5 = (new co(hM.ROCK, hK.RED)).setHardness(2.0F).setResistance(10.0F).setSoundType(ia.STONE).setTranslationKey("brick").setCreativeTab(EN.BUILDING_BLOCKS);
      registerBlock(45, (String)"brick_block", block5);
      registerBlock(46, (String)"tnt", (new hl()).setHardness(0.0F).setSoundType(ia.PLANT).setTranslationKey("tnt"));
      registerBlock(47, (String)"bookshelf", (new cF()).setHardness(1.5F).setSoundType(ia.WOOD).setTranslationKey("bookshelf"));
      registerBlock(48, (String)"mossy_cobblestone", (new co(hM.ROCK)).setHardness(2.0F).setResistance(10.0F).setSoundType(ia.STONE).setTranslationKey("stoneMoss").setCreativeTab(EN.BUILDING_BLOCKS));
      registerBlock(49, (String)"obsidian", (new eU()).setHardness(50.0F).setResistance(2000.0F).setSoundType(ia.STONE).setTranslationKey("obsidian"));
      registerBlock(50, (String)"torch", (new ho()).setHardness(0.0F).setLightLevel(0.9375F).setSoundType(ia.WOOD).setTranslationKey("torch"));
      registerBlock(51, (String)"fire", (new dN()).setHardness(0.0F).setLightLevel(1.0F).setSoundType(ia.CLOTH).setTranslationKey("fire").disableStats());
      registerBlock(52, (String)"mob_spawner", (new eH()).setHardness(5.0F).setSoundType(ia.METAL).setTranslationKey("mobSpawner").disableStats());
      registerBlock(53, (String)"oak_stairs", (new gU(block1.getDefaultState().withProperty(fl.VARIANT, fk.OAK))).setTranslationKey("stairsWood"));
      registerBlock(54, (String)"chest", (new cT(cS.BASIC)).setHardness(2.5F).setSoundType(ia.WOOD).setTranslationKey("chest"));
      registerBlock(55, (String)"redstone_wire", (new gf()).setHardness(0.0F).setSoundType(ia.STONE).setTranslationKey("redstoneDust").disableStats());
      registerBlock(56, (String)"diamond_ore", (new fa()).setHardness(3.0F).setResistance(5.0F).setSoundType(ia.STONE).setTranslationKey("oreDiamond"));
      registerBlock(57, (String)"diamond_block", (new co(hM.IRON, hK.DIAMOND)).setHardness(5.0F).setResistance(10.0F).setSoundType(ia.METAL).setTranslationKey("blockDiamond").setCreativeTab(EN.BUILDING_BLOCKS));
      registerBlock(58, (String)"crafting_table", (new hF()).setHardness(2.5F).setSoundType(ia.WOOD).setTranslationKey("workbench"));
      registerBlock(59, (String)"wheat", (new de()).setTranslationKey("crops"));
      co block6 = (new dJ()).setHardness(0.6F).setSoundType(ia.GROUND).setTranslationKey("farmland");
      registerBlock(60, (String)"farmland", block6);
      registerBlock(61, (String)"furnace", (new dY(false)).setHardness(3.5F).setSoundType(ia.STONE).setTranslationKey("furnace").setCreativeTab(EN.DECORATIONS));
      registerBlock(62, (String)"lit_furnace", (new dY(true)).setHardness(3.5F).setSoundType(ia.STONE).setLightLevel(0.875F).setTranslationKey("furnace"));
      registerBlock(63, (String)"standing_sign", (new gV()).setHardness(1.0F).setSoundType(ia.WOOD).setTranslationKey("sign").disableStats());
      registerBlock(64, (String)"wooden_door", (new do(hM.WOOD)).setHardness(3.0F).setSoundType(ia.WOOD).setTranslationKey("doorOak").disableStats());
      registerBlock(65, (String)"ladder", (new ev()).setHardness(0.4F).setSoundType(ia.LADDER).setTranslationKey("ladder"));
      registerBlock(66, (String)"rail", (new fG()).setHardness(0.7F).setSoundType(ia.METAL).setTranslationKey("rail"));
      registerBlock(67, (String)"stone_stairs", (new gU(block.getDefaultState())).setTranslationKey("stairsStone"));
      registerBlock(68, (String)"wall_sign", (new hB()).setHardness(1.0F).setSoundType(ia.WOOD).setTranslationKey("sign").disableStats());
      registerBlock(69, (String)"lever", (new ez()).setHardness(0.5F).setSoundType(ia.WOOD).setTranslationKey("lever"));
      registerBlock(70, (String)"stone_pressure_plate", (new fs(hM.ROCK, fr.MOBS)).setHardness(0.5F).setSoundType(ia.STONE).setTranslationKey("pressurePlateStone"));
      registerBlock(71, (String)"iron_door", (new do(hM.IRON)).setHardness(5.0F).setSoundType(ia.METAL).setTranslationKey("doorIron").disableStats());
      registerBlock(72, (String)"wooden_pressure_plate", (new fs(hM.WOOD, fr.EVERYTHING)).setHardness(0.5F).setSoundType(ia.WOOD).setTranslationKey("pressurePlateWood"));
      registerBlock(73, (String)"redstone_ore", (new fZ(false)).setHardness(3.0F).setResistance(5.0F).setSoundType(ia.STONE).setTranslationKey("oreRedstone").setCreativeTab(EN.BUILDING_BLOCKS));
      registerBlock(74, (String)"lit_redstone_ore", (new fZ(true)).setLightLevel(0.625F).setHardness(3.0F).setResistance(5.0F).setSoundType(ia.STONE).setTranslationKey("oreRedstone"));
      registerBlock(75, (String)"unlit_redstone_torch", (new gc(false)).setHardness(0.0F).setSoundType(ia.WOOD).setTranslationKey("notGate"));
      registerBlock(76, (String)"redstone_torch", (new gc(true)).setHardness(0.0F).setLightLevel(0.5F).setSoundType(ia.WOOD).setTranslationKey("notGate").setCreativeTab(EN.REDSTONE));
      registerBlock(77, (String)"stone_button", (new cL()).setHardness(0.5F).setSoundType(ia.STONE).setTranslationKey("button"));
      registerBlock(78, (String)"snow_layer", (new gI()).setHardness(0.1F).setSoundType(ia.SNOW).setTranslationKey("snow").setLightOpacity(0));
      registerBlock(79, (String)"ice", (new er()).setHardness(0.5F).setLightOpacity(3).setSoundType(ia.GLASS).setTranslationKey("ice"));
      registerBlock(80, (String)"snow", (new gJ()).setHardness(0.2F).setSoundType(ia.SNOW).setTranslationKey("snow"));
      registerBlock(81, (String)"cactus", (new cN()).setHardness(0.4F).setSoundType(ia.CLOTH).setTranslationKey("cactus"));
      registerBlock(82, (String)"clay", (new cW()).setHardness(0.6F).setSoundType(ia.GROUND).setTranslationKey("clay"));
      registerBlock(83, (String)"reeds", (new gg()).setHardness(0.0F).setSoundType(ia.PLANT).setTranslationKey("reeds").disableStats());
      registerBlock(84, (String)"jukebox", (new et()).setHardness(2.0F).setResistance(10.0F).setSoundType(ia.STONE).setTranslationKey("jukebox"));
      registerBlock(85, (String)"fence", (new dL(hM.WOOD, fk.OAK.getMapColor())).setHardness(2.0F).setResistance(5.0F).setSoundType(ia.WOOD).setTranslationKey("fence"));
      co block7 = (new fx()).setHardness(1.0F).setSoundType(ia.WOOD).setTranslationKey("pumpkin");
      registerBlock(86, (String)"pumpkin", block7);
      registerBlock(87, (String)"netherrack", (new eL()).setHardness(0.4F).setSoundType(ia.STONE).setTranslationKey("hellrock"));
      registerBlock(88, (String)"soul_sand", (new gK()).setHardness(0.5F).setSoundType(ia.SAND).setTranslationKey("hellsand"));
      registerBlock(89, (String)"glowstone", (new eb(hM.GLASS)).setHardness(0.3F).setSoundType(ia.GLASS).setLightLevel(1.0F).setTranslationKey("lightgem"));
      registerBlock(90, (String)"portal", (new fo()).setHardness(-1.0F).setSoundType(ia.GLASS).setLightLevel(0.75F).setTranslationKey("portal"));
      registerBlock(91, (String)"lit_pumpkin", (new fx()).setHardness(1.0F).setSoundType(ia.WOOD).setLightLevel(1.0F).setTranslationKey("litpumpkin"));
      registerBlock(92, (String)"cake", (new cO()).setHardness(0.5F).setSoundType(ia.CLOTH).setTranslationKey("cake").disableStats());
      registerBlock(93, (String)"unpowered_repeater", (new ga(false)).setHardness(0.0F).setSoundType(ia.WOOD).setTranslationKey("diode").disableStats());
      registerBlock(94, (String)"powered_repeater", (new ga(true)).setHardness(0.0F).setSoundType(ia.WOOD).setTranslationKey("diode").disableStats());
      registerBlock(95, (String)"stained_glass", (new gN(hM.GLASS)).setHardness(0.3F).setSoundType(ia.GLASS).setTranslationKey("stainedGlass"));
      registerBlock(96, (String)"trapdoor", (new hr(hM.WOOD)).setHardness(3.0F).setSoundType(ia.WOOD).setTranslationKey("trapdoor").disableStats());
      registerBlock(97, (String)"monster_egg", (new gB()).setHardness(0.75F).setTranslationKey("monsterStoneEgg"));
      co block8 = (new hb()).setHardness(1.5F).setResistance(10.0F).setSoundType(ia.STONE).setTranslationKey("stonebricksmooth");
      registerBlock(98, (String)"stonebrick", block8);
      registerBlock(99, (String)"brown_mushroom_block", (new eq(hM.WOOD, hK.DIRT, block3)).setHardness(0.2F).setSoundType(ia.WOOD).setTranslationKey("mushroom"));
      registerBlock(100, (String)"red_mushroom_block", (new eq(hM.WOOD, hK.RED, block4)).setHardness(0.2F).setSoundType(ia.WOOD).setTranslationKey("mushroom"));
      registerBlock(101, (String)"iron_bars", (new fd(hM.IRON, true)).setHardness(5.0F).setResistance(10.0F).setSoundType(ia.METAL).setTranslationKey("fenceIron"));
      registerBlock(102, (String)"glass_pane", (new fd(hM.GLASS, false)).setHardness(0.3F).setSoundType(ia.GLASS).setTranslationKey("thinGlass"));
      co block9 = (new eG()).setHardness(1.0F).setSoundType(ia.WOOD).setTranslationKey("melon");
      registerBlock(103, (String)"melon_block", block9);
      registerBlock(104, (String)"pumpkin_stem", (new gX(block7)).setHardness(0.0F).setSoundType(ia.WOOD).setTranslationKey("pumpkinStem"));
      registerBlock(105, (String)"melon_stem", (new gX(block9)).setHardness(0.0F).setSoundType(ia.WOOD).setTranslationKey("pumpkinStem"));
      registerBlock(106, (String)"vine", (new hx()).setHardness(0.2F).setSoundType(ia.PLANT).setTranslationKey("vine"));
      registerBlock(107, (String)"fence_gate", (new dM(fk.OAK)).setHardness(2.0F).setResistance(5.0F).setSoundType(ia.WOOD).setTranslationKey("fenceGate"));
      registerBlock(108, (String)"brick_stairs", (new gU(block5.getDefaultState())).setTranslationKey("stairsBrick"));
      registerBlock(109, (String)"stone_brick_stairs", (new gU(block8.getDefaultState().withProperty(hb.VARIANT, ha.DEFAULT))).setTranslationKey("stairsStoneBrickSmooth"));
      registerBlock(110, (String)"mycelium", (new eJ()).setHardness(0.6F).setSoundType(ia.PLANT).setTranslationKey("mycel"));
      registerBlock(111, (String)"waterlily", (new eA()).setHardness(0.0F).setSoundType(ia.PLANT).setTranslationKey("waterlily"));
      co block10 = (new eK()).setHardness(2.0F).setResistance(10.0F).setSoundType(ia.STONE).setTranslationKey("netherBrick").setCreativeTab(EN.BUILDING_BLOCKS);
      registerBlock(112, (String)"nether_brick", block10);
      registerBlock(113, (String)"nether_brick_fence", (new dL(hM.ROCK, hK.NETHERRACK)).setHardness(2.0F).setResistance(10.0F).setSoundType(ia.STONE).setTranslationKey("netherFence"));
      registerBlock(114, (String)"nether_brick_stairs", (new gU(block10.getDefaultState())).setTranslationKey("stairsNetherBrick"));
      registerBlock(115, (String)"nether_wart", (new eM()).setTranslationKey("netherStalk"));
      registerBlock(116, (String)"enchanting_table", (new dz()).setHardness(5.0F).setResistance(2000.0F).setTranslationKey("enchantmentTable"));
      registerBlock(117, (String)"brewing_stand", (new cH()).setHardness(0.5F).setLightLevel(0.125F).setTranslationKey("brewingStand"));
      registerBlock(118, (String)"cauldron", (new cR()).setHardness(2.0F).setTranslationKey("cauldron"));
      registerBlock(119, (String)"end_portal", (new dC(hM.PORTAL)).setHardness(-1.0F).setResistance(6000000.0F));
      registerBlock(120, (String)"end_portal_frame", (new dD()).setSoundType(ia.GLASS).setLightLevel(0.125F).setHardness(-1.0F).setTranslationKey("endPortalFrame").setResistance(6000000.0F).setCreativeTab(EN.DECORATIONS));
      registerBlock(121, (String)"end_stone", (new co(hM.ROCK, hK.SAND)).setHardness(3.0F).setResistance(15.0F).setSoundType(ia.STONE).setTranslationKey("whiteStone").setCreativeTab(EN.BUILDING_BLOCKS));
      registerBlock(122, (String)"dragon_egg", (new dv()).setHardness(3.0F).setResistance(15.0F).setSoundType(ia.STONE).setLightLevel(0.125F).setTranslationKey("dragonEgg"));
      registerBlock(123, (String)"redstone_lamp", (new fY(false)).setHardness(0.3F).setSoundType(ia.GLASS).setTranslationKey("redstoneLight").setCreativeTab(EN.REDSTONE));
      registerBlock(124, (String)"lit_redstone_lamp", (new fY(true)).setHardness(0.3F).setSoundType(ia.GLASS).setTranslationKey("redstoneLight"));
      registerBlock(125, (String)"double_wooden_slab", (new du()).setHardness(2.0F).setResistance(5.0F).setSoundType(ia.WOOD).setTranslationKey("woodSlab"));
      registerBlock(126, (String)"wooden_slab", (new ei()).setHardness(2.0F).setResistance(5.0F).setSoundType(ia.WOOD).setTranslationKey("woodSlab"));
      registerBlock(127, (String)"cocoa", (new cY()).setHardness(0.2F).setResistance(5.0F).setSoundType(ia.WOOD).setTranslationKey("cocoa"));
      registerBlock(128, (String)"sandstone_stairs", (new gU(block2.getDefaultState().withProperty(gm.TYPE, gl.SMOOTH))).setTranslationKey("stairsSandStone"));
      registerBlock(129, (String)"emerald_ore", (new fa()).setHardness(3.0F).setResistance(5.0F).setSoundType(ia.STONE).setTranslationKey("oreEmerald"));
      registerBlock(130, (String)"ender_chest", (new dA()).setHardness(22.5F).setResistance(1000.0F).setSoundType(ia.STONE).setTranslationKey("enderChest").setLightLevel(0.5F));
      registerBlock(131, (String)"tripwire_hook", (new hv()).setTranslationKey("tripWireSource"));
      registerBlock(132, (String)"tripwire", (new ht()).setTranslationKey("tripWire"));
      registerBlock(133, (String)"emerald_block", (new co(hM.IRON, hK.EMERALD)).setHardness(5.0F).setResistance(10.0F).setSoundType(ia.METAL).setTranslationKey("blockEmerald").setCreativeTab(EN.BUILDING_BLOCKS));
      registerBlock(134, (String)"spruce_stairs", (new gU(block1.getDefaultState().withProperty(fl.VARIANT, fk.SPRUCE))).setTranslationKey("stairsWoodSpruce"));
      registerBlock(135, (String)"birch_stairs", (new gU(block1.getDefaultState().withProperty(fl.VARIANT, fk.BIRCH))).setTranslationKey("stairsWoodBirch"));
      registerBlock(136, (String)"jungle_stairs", (new gU(block1.getDefaultState().withProperty(fl.VARIANT, fk.JUNGLE))).setTranslationKey("stairsWoodJungle"));
      registerBlock(137, (String)"command_block", (new da(hK.BROWN)).setBlockUnbreakable().setResistance(6000000.0F).setTranslationKey("commandBlock"));
      registerBlock(138, (String)"beacon", (new cA()).setTranslationKey("beacon").setLightLevel(1.0F));
      registerBlock(139, (String)"cobblestone_wall", (new hz(block)).setTranslationKey("cobbleWall"));
      registerBlock(140, (String)"flower_pot", (new dV()).setHardness(0.0F).setSoundType(ia.STONE).setTranslationKey("flowerPot"));
      registerBlock(141, (String)"carrots", (new cQ()).setTranslationKey("carrots"));
      registerBlock(142, (String)"potatoes", (new fp()).setTranslationKey("potatoes"));
      registerBlock(143, (String)"wooden_button", (new cM()).setHardness(0.5F).setSoundType(ia.WOOD).setTranslationKey("button"));
      registerBlock(144, (String)"skull", (new gE()).setHardness(1.0F).setSoundType(ia.STONE).setTranslationKey("skull"));
      registerBlock(145, (String)"anvil", (new cr()).setHardness(5.0F).setSoundType(ia.ANVIL).setResistance(2000.0F).setTranslationKey("anvil"));
      registerBlock(146, (String)"trapped_chest", (new cT(cS.TRAP)).setHardness(2.5F).setSoundType(ia.WOOD).setTranslationKey("chestTrap"));
      registerBlock(147, (String)"light_weighted_pressure_plate", (new ft(hM.IRON, 15, hK.GOLD)).setHardness(0.5F).setSoundType(ia.WOOD).setTranslationKey("weightedPlate_light"));
      registerBlock(148, (String)"heavy_weighted_pressure_plate", (new ft(hM.IRON, 150)).setHardness(0.5F).setSoundType(ia.WOOD).setTranslationKey("weightedPlate_heavy"));
      registerBlock(149, (String)"unpowered_comparator", (new fW(false)).setHardness(0.0F).setSoundType(ia.WOOD).setTranslationKey("comparator").disableStats());
      registerBlock(150, (String)"powered_comparator", (new fW(true)).setHardness(0.0F).setLightLevel(0.625F).setSoundType(ia.WOOD).setTranslationKey("comparator").disableStats());
      registerBlock(151, (String)"daylight_detector", new df(false));
      registerBlock(152, (String)"redstone_block", (new db(hM.IRON, hK.TNT)).setHardness(5.0F).setResistance(10.0F).setSoundType(ia.METAL).setTranslationKey("blockRedstone").setCreativeTab(EN.REDSTONE));
      registerBlock(153, (String)"quartz_ore", (new fa(hK.NETHERRACK)).setHardness(3.0F).setResistance(5.0F).setSoundType(ia.STONE).setTranslationKey("netherquartz"));
      registerBlock(154, (String)"hopper", (new em()).setHardness(3.0F).setResistance(8.0F).setSoundType(ia.METAL).setTranslationKey("hopper"));
      co block11 = (new fE()).setSoundType(ia.STONE).setHardness(0.8F).setTranslationKey("quartzBlock");
      registerBlock(155, (String)"quartz_block", block11);
      registerBlock(156, (String)"quartz_stairs", (new gU(block11.getDefaultState().withProperty(fE.VARIANT, fD.DEFAULT))).setTranslationKey("stairsQuartz"));
      registerBlock(157, (String)"activator_rail", (new fQ()).setHardness(0.7F).setSoundType(ia.METAL).setTranslationKey("activatorRail"));
      registerBlock(158, (String)"dropper", (new dw()).setHardness(3.5F).setSoundType(ia.STONE).setTranslationKey("dropper"));
      registerBlock(159, (String)"stained_hardened_clay", (new gQ()).setHardness(1.25F).setResistance(7.0F).setSoundType(ia.STONE).setTranslationKey("clayHardenedStained"));
      registerBlock(160, (String)"stained_glass_pane", (new gP()).setHardness(0.3F).setSoundType(ia.GLASS).setTranslationKey("thinStainedGlass"));
      registerBlock(161, (String)"leaves2", (new eO()).setTranslationKey("leaves"));
      registerBlock(162, (String)"log2", (new eR()).setTranslationKey("log"));
      registerBlock(163, (String)"acacia_stairs", (new gU(block1.getDefaultState().withProperty(fl.VARIANT, fk.ACACIA))).setTranslationKey("stairsWoodAcacia"));
      registerBlock(164, (String)"dark_oak_stairs", (new gU(block1.getDefaultState().withProperty(fl.VARIANT, fk.DARK_OAK))).setTranslationKey("stairsWoodDarkOak"));
      registerBlock(165, (String)"slime", (new gH()).setTranslationKey("slime").setSoundType(ia.SLIME));
      registerBlock(166, (String)"barrier", (new cw()).setTranslationKey("barrier"));
      registerBlock(167, (String)"iron_trapdoor", (new hr(hM.IRON)).setHardness(5.0F).setSoundType(ia.METAL).setTranslationKey("ironTrapdoor").disableStats());
      registerBlock(168, (String)"prismarine", (new fv()).setHardness(1.5F).setResistance(10.0F).setSoundType(ia.STONE).setTranslationKey("prismarine"));
      registerBlock(169, (String)"sea_lantern", (new gp(hM.GLASS)).setHardness(0.3F).setSoundType(ia.GLASS).setLightLevel(1.0F).setTranslationKey("seaLantern"));
      registerBlock(170, (String)"hay_block", (new ek()).setHardness(0.5F).setSoundType(ia.PLANT).setTranslationKey("hayBlock").setCreativeTab(EN.BUILDING_BLOCKS));
      registerBlock(171, (String)"carpet", (new cP()).setHardness(0.1F).setSoundType(ia.CLOTH).setTranslationKey("woolCarpet").setLightOpacity(0));
      registerBlock(172, (String)"hardened_clay", (new ej()).setHardness(1.25F).setResistance(7.0F).setSoundType(ia.STONE).setTranslationKey("clayHardened"));
      registerBlock(173, (String)"coal_block", (new co(hM.ROCK, hK.BLACK)).setHardness(5.0F).setResistance(10.0F).setSoundType(ia.STONE).setTranslationKey("blockCoal").setCreativeTab(EN.BUILDING_BLOCKS));
      registerBlock(174, (String)"packed_ice", (new fb()).setHardness(0.5F).setSoundType(ia.GLASS).setTranslationKey("icePacked"));
      registerBlock(175, (String)"double_plant", new dr());
      registerBlock(176, (String)"standing_banner", (new cu()).setHardness(1.0F).setSoundType(ia.WOOD).setTranslationKey("banner").disableStats());
      registerBlock(177, (String)"wall_banner", (new ct()).setHardness(1.0F).setSoundType(ia.WOOD).setTranslationKey("banner").disableStats());
      registerBlock(178, (String)"daylight_detector_inverted", new df(true));
      co block12 = (new fT()).setSoundType(ia.STONE).setHardness(0.8F).setTranslationKey("redSandStone");
      registerBlock(179, (String)"red_sandstone", block12);
      registerBlock(180, (String)"red_sandstone_stairs", (new gU(block12.getDefaultState().withProperty(fT.TYPE, fS.SMOOTH))).setTranslationKey("stairsRedSandStone"));
      registerBlock(181, (String)"double_stone_slab2", (new dt()).setHardness(2.0F).setResistance(10.0F).setSoundType(ia.STONE).setTranslationKey("stoneSlab2"));
      registerBlock(182, (String)"stone_slab2", (new eh()).setHardness(2.0F).setResistance(10.0F).setSoundType(ia.STONE).setTranslationKey("stoneSlab2"));
      registerBlock(183, (String)"spruce_fence_gate", (new dM(fk.SPRUCE)).setHardness(2.0F).setResistance(5.0F).setSoundType(ia.WOOD).setTranslationKey("spruceFenceGate"));
      registerBlock(184, (String)"birch_fence_gate", (new dM(fk.BIRCH)).setHardness(2.0F).setResistance(5.0F).setSoundType(ia.WOOD).setTranslationKey("birchFenceGate"));
      registerBlock(185, (String)"jungle_fence_gate", (new dM(fk.JUNGLE)).setHardness(2.0F).setResistance(5.0F).setSoundType(ia.WOOD).setTranslationKey("jungleFenceGate"));
      registerBlock(186, (String)"dark_oak_fence_gate", (new dM(fk.DARK_OAK)).setHardness(2.0F).setResistance(5.0F).setSoundType(ia.WOOD).setTranslationKey("darkOakFenceGate"));
      registerBlock(187, (String)"acacia_fence_gate", (new dM(fk.ACACIA)).setHardness(2.0F).setResistance(5.0F).setSoundType(ia.WOOD).setTranslationKey("acaciaFenceGate"));
      registerBlock(188, (String)"spruce_fence", (new dL(hM.WOOD, fk.SPRUCE.getMapColor())).setHardness(2.0F).setResistance(5.0F).setSoundType(ia.WOOD).setTranslationKey("spruceFence"));
      registerBlock(189, (String)"birch_fence", (new dL(hM.WOOD, fk.BIRCH.getMapColor())).setHardness(2.0F).setResistance(5.0F).setSoundType(ia.WOOD).setTranslationKey("birchFence"));
      registerBlock(190, (String)"jungle_fence", (new dL(hM.WOOD, fk.JUNGLE.getMapColor())).setHardness(2.0F).setResistance(5.0F).setSoundType(ia.WOOD).setTranslationKey("jungleFence"));
      registerBlock(191, (String)"dark_oak_fence", (new dL(hM.WOOD, fk.DARK_OAK.getMapColor())).setHardness(2.0F).setResistance(5.0F).setSoundType(ia.WOOD).setTranslationKey("darkOakFence"));
      registerBlock(192, (String)"acacia_fence", (new dL(hM.WOOD, fk.ACACIA.getMapColor())).setHardness(2.0F).setResistance(5.0F).setSoundType(ia.WOOD).setTranslationKey("acaciaFence"));
      registerBlock(193, (String)"spruce_door", (new do(hM.WOOD)).setHardness(3.0F).setSoundType(ia.WOOD).setTranslationKey("doorSpruce").disableStats());
      registerBlock(194, (String)"birch_door", (new do(hM.WOOD)).setHardness(3.0F).setSoundType(ia.WOOD).setTranslationKey("doorBirch").disableStats());
      registerBlock(195, (String)"jungle_door", (new do(hM.WOOD)).setHardness(3.0F).setSoundType(ia.WOOD).setTranslationKey("doorJungle").disableStats());
      registerBlock(196, (String)"acacia_door", (new do(hM.WOOD)).setHardness(3.0F).setSoundType(ia.WOOD).setTranslationKey("doorAcacia").disableStats());
      registerBlock(197, (String)"dark_oak_door", (new do(hM.WOOD)).setHardness(3.0F).setSoundType(ia.WOOD).setTranslationKey("doorDarkOak").disableStats());
      registerBlock(198, (String)"end_rod", (new dF()).setHardness(0.0F).setLightLevel(0.9375F).setSoundType(ia.WOOD).setTranslationKey("endRod"));
      registerBlock(199, (String)"chorus_plant", (new cV()).setHardness(0.4F).setSoundType(ia.WOOD).setTranslationKey("chorusPlant"));
      registerBlock(200, (String)"chorus_flower", (new cU()).setHardness(0.4F).setSoundType(ia.WOOD).setTranslationKey("chorusFlower"));
      co block13 = (new co(hM.ROCK, hK.MAGENTA)).setHardness(1.5F).setResistance(10.0F).setSoundType(ia.STONE).setCreativeTab(EN.BUILDING_BLOCKS).setTranslationKey("purpurBlock");
      registerBlock(201, (String)"purpur_block", block13);
      registerBlock(202, (String)"purpur_pillar", (new gi(hM.ROCK, hK.MAGENTA)).setHardness(1.5F).setResistance(10.0F).setSoundType(ia.STONE).setCreativeTab(EN.BUILDING_BLOCKS).setTranslationKey("purpurPillar"));
      registerBlock(203, (String)"purpur_stairs", (new gU(block13.getDefaultState())).setTranslationKey("stairsPurpur"));
      registerBlock(204, (String)"purpur_double_slab", (new fy()).setHardness(2.0F).setResistance(10.0F).setSoundType(ia.STONE).setTranslationKey("purpurSlab"));
      registerBlock(205, (String)"purpur_slab", (new fz()).setHardness(2.0F).setResistance(10.0F).setSoundType(ia.STONE).setTranslationKey("purpurSlab"));
      registerBlock(206, (String)"end_bricks", (new co(hM.ROCK, hK.SAND)).setSoundType(ia.STONE).setHardness(0.8F).setCreativeTab(EN.BUILDING_BLOCKS).setTranslationKey("endBricks"));
      registerBlock(207, (String)"beetroots", (new cD()).setTranslationKey("beetroots"));
      co block14 = (new ee()).setHardness(0.65F).setSoundType(ia.PLANT).setTranslationKey("grassPath").disableStats();
      registerBlock(208, (String)"grass_path", block14);
      registerBlock(209, (String)"end_gateway", (new dB(hM.PORTAL)).setHardness(-1.0F).setResistance(6000000.0F));
      registerBlock(210, (String)"repeating_command_block", (new da(hK.PURPLE)).setBlockUnbreakable().setResistance(6000000.0F).setTranslationKey("repeatingCommandBlock"));
      registerBlock(211, (String)"chain_command_block", (new da(hK.GREEN)).setBlockUnbreakable().setResistance(6000000.0F).setTranslationKey("chainCommandBlock"));
      registerBlock(212, (String)"frosted_ice", (new dW()).setHardness(0.5F).setLightOpacity(3).setSoundType(ia.GLASS).setTranslationKey("frostedIce"));
      registerBlock(213, (String)"magma", (new eF()).setHardness(0.5F).setSoundType(ia.STONE).setTranslationKey("magma"));
      registerBlock(214, (String)"nether_wart_block", (new co(hM.GRASS, hK.RED)).setCreativeTab(EN.BUILDING_BLOCKS).setHardness(1.0F).setSoundType(ia.WOOD).setTranslationKey("netherWartBlock"));
      registerBlock(215, (String)"red_nether_brick", (new eK()).setHardness(2.0F).setResistance(10.0F).setSoundType(ia.STONE).setTranslationKey("redNetherBrick").setCreativeTab(EN.BUILDING_BLOCKS));
      registerBlock(216, (String)"bone_block", (new cE()).setTranslationKey("boneBlock"));
      registerBlock(217, (String)"structure_void", (new hi()).setTranslationKey("structureVoid"));
      registerBlock(218, (String)"observer", (new eT()).setHardness(3.0F).setTranslationKey("observer"));
      registerBlock(219, (String)"white_shulker_box", (new gr(Om.WHITE)).setHardness(2.0F).setSoundType(ia.STONE).setTranslationKey("shulkerBoxWhite"));
      registerBlock(220, (String)"orange_shulker_box", (new gr(Om.ORANGE)).setHardness(2.0F).setSoundType(ia.STONE).setTranslationKey("shulkerBoxOrange"));
      registerBlock(221, (String)"magenta_shulker_box", (new gr(Om.MAGENTA)).setHardness(2.0F).setSoundType(ia.STONE).setTranslationKey("shulkerBoxMagenta"));
      registerBlock(222, (String)"light_blue_shulker_box", (new gr(Om.LIGHT_BLUE)).setHardness(2.0F).setSoundType(ia.STONE).setTranslationKey("shulkerBoxLightBlue"));
      registerBlock(223, (String)"yellow_shulker_box", (new gr(Om.YELLOW)).setHardness(2.0F).setSoundType(ia.STONE).setTranslationKey("shulkerBoxYellow"));
      registerBlock(224, (String)"lime_shulker_box", (new gr(Om.LIME)).setHardness(2.0F).setSoundType(ia.STONE).setTranslationKey("shulkerBoxLime"));
      registerBlock(225, (String)"pink_shulker_box", (new gr(Om.PINK)).setHardness(2.0F).setSoundType(ia.STONE).setTranslationKey("shulkerBoxPink"));
      registerBlock(226, (String)"gray_shulker_box", (new gr(Om.GRAY)).setHardness(2.0F).setSoundType(ia.STONE).setTranslationKey("shulkerBoxGray"));
      registerBlock(227, (String)"silver_shulker_box", (new gr(Om.SILVER)).setHardness(2.0F).setSoundType(ia.STONE).setTranslationKey("shulkerBoxSilver"));
      registerBlock(228, (String)"cyan_shulker_box", (new gr(Om.CYAN)).setHardness(2.0F).setSoundType(ia.STONE).setTranslationKey("shulkerBoxCyan"));
      registerBlock(229, (String)"purple_shulker_box", (new gr(Om.PURPLE)).setHardness(2.0F).setSoundType(ia.STONE).setTranslationKey("shulkerBoxPurple"));
      registerBlock(230, (String)"blue_shulker_box", (new gr(Om.BLUE)).setHardness(2.0F).setSoundType(ia.STONE).setTranslationKey("shulkerBoxBlue"));
      registerBlock(231, (String)"brown_shulker_box", (new gr(Om.BROWN)).setHardness(2.0F).setSoundType(ia.STONE).setTranslationKey("shulkerBoxBrown"));
      registerBlock(232, (String)"green_shulker_box", (new gr(Om.GREEN)).setHardness(2.0F).setSoundType(ia.STONE).setTranslationKey("shulkerBoxGreen"));
      registerBlock(233, (String)"red_shulker_box", (new gr(Om.RED)).setHardness(2.0F).setSoundType(ia.STONE).setTranslationKey("shulkerBoxRed"));
      registerBlock(234, (String)"black_shulker_box", (new gr(Om.BLACK)).setHardness(2.0F).setSoundType(ia.STONE).setTranslationKey("shulkerBoxBlack"));
      registerBlock(235, (String)"white_glazed_terracotta", new ea(Om.WHITE));
      registerBlock(236, (String)"orange_glazed_terracotta", new ea(Om.ORANGE));
      registerBlock(237, (String)"magenta_glazed_terracotta", new ea(Om.MAGENTA));
      registerBlock(238, (String)"light_blue_glazed_terracotta", new ea(Om.LIGHT_BLUE));
      registerBlock(239, (String)"yellow_glazed_terracotta", new ea(Om.YELLOW));
      registerBlock(240, (String)"lime_glazed_terracotta", new ea(Om.LIME));
      registerBlock(241, (String)"pink_glazed_terracotta", new ea(Om.PINK));
      registerBlock(242, (String)"gray_glazed_terracotta", new ea(Om.GRAY));
      registerBlock(243, (String)"silver_glazed_terracotta", new ea(Om.SILVER));
      registerBlock(244, (String)"cyan_glazed_terracotta", new ea(Om.CYAN));
      registerBlock(245, (String)"purple_glazed_terracotta", new ea(Om.PURPLE));
      registerBlock(246, (String)"blue_glazed_terracotta", new ea(Om.BLUE));
      registerBlock(247, (String)"brown_glazed_terracotta", new ea(Om.BROWN));
      registerBlock(248, (String)"green_glazed_terracotta", new ea(Om.GREEN));
      registerBlock(249, (String)"red_glazed_terracotta", new ea(Om.RED));
      registerBlock(250, (String)"black_glazed_terracotta", new ea(Om.BLACK));
      registerBlock(251, (String)"concrete", (new cZ(hM.ROCK)).setHardness(1.8F).setSoundType(ia.STONE).setTranslationKey("concrete"));
      registerBlock(252, (String)"concrete_powder", (new dc()).setHardness(0.5F).setSoundType(ia.SAND).setTranslationKey("concretePowder"));
      registerBlock(255, (String)"structure_block", (new hh()).setBlockUnbreakable().setResistance(6000000.0F).setTranslationKey("structureBlock"));
      REGISTRY.validateKey();
      Iterator var15 = REGISTRY.iterator();

      while(true) {
         while(var15.hasNext()) {
            co block15 = (co)var15.next();
            if (block15.material == hM.AIR) {
               block15.useNeighborBrightness = false;
            } else {
               boolean flag = false;
               boolean flag1 = block15 instanceof gU;
               boolean flag2 = block15 instanceof gG;
               boolean flag3 = block15 == block6 || block15 == block14;
               boolean flag4 = block15.translucent;
               boolean flag5 = block15.lightOpacity == 0;
               if (flag1 || flag2 || flag3 || flag4 || flag5) {
                  flag = true;
               }

               block15.useNeighborBrightness = flag;
            }
         }

         Set<co> set = Sets.newHashSet(new co[]{(co)REGISTRY.getObject(new ResourceLocation("tripwire"))});
         Iterator var24 = REGISTRY.iterator();

         while(true) {
            while(var24.hasNext()) {
               co block16 = (co)var24.next();
               if (set.contains(block16)) {
                  for(int i = 0; i < 15; ++i) {
                     int j = REGISTRY.getIDForObject(block16) << 4 | i;
                     BLOCK_STATE_IDS.put(block16.getStateFromMeta(i), j);
                  }
               } else {
                  UnmodifiableIterator unmodifiableiterator = block16.getBlockState().getValidStates().iterator();

                  while(unmodifiableiterator.hasNext()) {
                     in iblockstate = (in)unmodifiableiterator.next();
                     int k = REGISTRY.getIDForObject(block16) << 4 | block16.getMetaFromState(iblockstate);
                     BLOCK_STATE_IDS.put(iblockstate, k);
                  }
               }
            }

            return;
         }
      }
   }

   private static void registerBlock(int id, ResourceLocation textualID, co block_) {
      REGISTRY.register(id, textualID, block_);
   }

   private static void registerBlock(int id, String textualID, co block_) {
      registerBlock(id, new ResourceLocation(textualID), block_);
   }

   static {
      REGISTRY = new RegistryNamespacedDefaultedByKey(AIR_ID);
      BLOCK_STATE_IDS = new ObjectIntIdentityMap();
      FULL_BLOCK_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
      NULL_AABB = null;
   }
}
