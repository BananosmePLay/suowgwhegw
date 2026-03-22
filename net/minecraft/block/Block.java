package net.minecraft.block;

import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javax.annotation.Nullable;
import neo.0m;
import neo.0y;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
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
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Block {
   private static final ResourceLocation AIR_ID = new ResourceLocation("air");
   public static final RegistryNamespacedDefaultedByKey<ResourceLocation, Block> REGISTRY;
   public static final ObjectIntIdentityMap<IBlockState> BLOCK_STATE_IDS;
   public static final AxisAlignedBB FULL_BLOCK_AABB;
   @Nullable
   public static final AxisAlignedBB NULL_AABB;
   private CreativeTabs displayOnCreativeTab;
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
   protected SoundType blockSoundType;
   public float blockParticleGravity;
   protected final Material material;
   protected final MapColor blockMapColor;
   public float slipperiness;
   protected final BlockStateContainer blockState;
   private IBlockState defaultBlockState;
   private String translationKey;

   public static int getIdFromBlock(Block blockIn) {
      return REGISTRY.getIDForObject(blockIn);
   }

   public static int getStateId(IBlockState state) {
      Block block = state.getBlock();
      return getIdFromBlock(block) + (block.getMetaFromState(state) << 12);
   }

   public static Block getBlockById(int id) {
      return (Block)REGISTRY.getObjectById(id);
   }

   public static IBlockState getStateById(int id) {
      int i = id & 4095;
      int j = id >> 12 & 15;
      return getBlockById(i).getStateFromMeta(j);
   }

   public static Block getBlockFromItem(@Nullable Item itemIn) {
      return itemIn instanceof ItemBlock ? ((ItemBlock)itemIn).getBlock() : Blocks.AIR;
   }

   @Nullable
   public static Block getBlockFromName(String name) {
      ResourceLocation resourcelocation = new ResourceLocation(name);
      if (REGISTRY.containsKey(resourcelocation)) {
         return (Block)REGISTRY.getObject(resourcelocation);
      } else {
         try {
            return (Block)REGISTRY.getObjectById(Integer.parseInt(name));
         } catch (NumberFormatException var3) {
            return null;
         }
      }
   }

   /** @deprecated */
   @Deprecated
   public boolean isTopSolid(IBlockState state) {
      return state.getMaterial().isOpaque() && state.isFullCube();
   }

   /** @deprecated */
   @Deprecated
   public boolean isFullBlock(IBlockState state) {
      return this.fullBlock;
   }

   /** @deprecated */
   @Deprecated
   public boolean canEntitySpawn(IBlockState state, Entity entityIn) {
      return true;
   }

   /** @deprecated */
   @Deprecated
   public int getLightOpacity(IBlockState state) {
      return this.lightOpacity;
   }

   /** @deprecated */
   @Deprecated
   public boolean isTranslucent(IBlockState state) {
      return this.translucent;
   }

   /** @deprecated */
   @Deprecated
   public int getLightValue(IBlockState state) {
      return this.lightValue;
   }

   /** @deprecated */
   @Deprecated
   public boolean getUseNeighborBrightness(IBlockState state) {
      return this.useNeighborBrightness;
   }

   /** @deprecated */
   @Deprecated
   public Material getMaterial(IBlockState state) {
      return this.material;
   }

   /** @deprecated */
   @Deprecated
   public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
      return this.blockMapColor;
   }

   /** @deprecated */
   @Deprecated
   public IBlockState getStateFromMeta(int meta) {
      return this.getDefaultState();
   }

   public int getMetaFromState(IBlockState state) {
      if (state.getPropertyKeys().isEmpty()) {
         return 0;
      } else {
         throw new IllegalArgumentException("Don't know how to convert " + state + " back into data...");
      }
   }

   /** @deprecated */
   @Deprecated
   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
      return state;
   }

   /** @deprecated */
   @Deprecated
   public IBlockState withRotation(IBlockState state, Rotation rot) {
      return state;
   }

   /** @deprecated */
   @Deprecated
   public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
      return state;
   }

   public Block(Material blockMaterialIn, MapColor blockMapColorIn) {
      this.enableStats = true;
      this.blockSoundType = SoundType.STONE;
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

   protected Block(Material materialIn) {
      this(materialIn, materialIn.getMaterialMapColor());
   }

   protected Block setSoundType(SoundType sound) {
      this.blockSoundType = sound;
      return this;
   }

   protected Block setLightOpacity(int opacity) {
      this.lightOpacity = opacity;
      return this;
   }

   protected Block setLightLevel(float value) {
      this.lightValue = (int)(15.0F * value);
      return this;
   }

   protected Block setResistance(float resistance) {
      this.blockResistance = resistance * 3.0F;
      return this;
   }

   protected static boolean isExceptionBlockForAttaching(Block attachBlock) {
      return attachBlock instanceof BlockShulkerBox || attachBlock instanceof BlockLeaves || attachBlock instanceof BlockTrapDoor || attachBlock == Blocks.BEACON || attachBlock == Blocks.CAULDRON || attachBlock == Blocks.GLASS || attachBlock == Blocks.GLOWSTONE || attachBlock == Blocks.ICE || attachBlock == Blocks.SEA_LANTERN || attachBlock == Blocks.STAINED_GLASS;
   }

   protected static boolean isExceptBlockForAttachWithPiston(Block attachBlock) {
      return isExceptionBlockForAttaching(attachBlock) || attachBlock == Blocks.PISTON || attachBlock == Blocks.STICKY_PISTON || attachBlock == Blocks.PISTON_HEAD;
   }

   /** @deprecated */
   @Deprecated
   public boolean isBlockNormalCube(IBlockState state) {
      return state.getMaterial().blocksMovement() && state.isFullCube();
   }

   /** @deprecated */
   @Deprecated
   public boolean isNormalCube(IBlockState state) {
      return state.getMaterial().isOpaque() && state.isFullCube() && !state.canProvidePower();
   }

   /** @deprecated */
   @Deprecated
   public boolean causesSuffocation(IBlockState state) {
      return this.material.blocksMovement() && this.getDefaultState().isFullCube();
   }

   /** @deprecated */
   @Deprecated
   public boolean isFullCube(IBlockState state) {
      0y event = new 0y(state.getBlock());
      0m.call(event);
      return !event.isCanceled();
   }

   /** @deprecated */
   @Deprecated
   public boolean hasCustomBreakingProgress(IBlockState state) {
      return false;
   }

   public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
      return !this.material.blocksMovement();
   }

   /** @deprecated */
   @Deprecated
   public EnumBlockRenderType getRenderType(IBlockState state) {
      return EnumBlockRenderType.MODEL;
   }

   public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos) {
      return false;
   }

   protected Block setHardness(float hardness) {
      this.blockHardness = hardness;
      if (this.blockResistance < hardness * 5.0F) {
         this.blockResistance = hardness * 5.0F;
      }

      return this;
   }

   protected Block setBlockUnbreakable() {
      this.setHardness(-1.0F);
      return this;
   }

   /** @deprecated */
   @Deprecated
   public float getBlockHardness(IBlockState blockState, World worldIn, BlockPos pos) {
      return this.blockHardness;
   }

   protected Block setTickRandomly(boolean shouldTick) {
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
   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      return FULL_BLOCK_AABB;
   }

   /** @deprecated */
   @Deprecated
   public int getPackedLightmapCoords(IBlockState state, IBlockAccess source, BlockPos pos) {
      int i = source.getCombinedLight(pos, state.getLightValue());
      if (i == 0 && state.getBlock() instanceof BlockSlab) {
         pos = pos.down();
         state = source.getBlockState(pos);
         return source.getCombinedLight(pos, state.getLightValue());
      } else {
         return i;
      }
   }

   /** @deprecated */
   @Deprecated
   public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
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
   public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
      return BlockFaceShape.SOLID;
   }

   /** @deprecated */
   @Deprecated
   public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
      return state.getBoundingBox(worldIn, pos).offset(pos);
   }

   /** @deprecated */
   @Deprecated
   public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState) {
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
   public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
      return blockState.getBoundingBox(worldIn, pos);
   }

   /** @deprecated */
   @Deprecated
   public boolean isOpaqueCube(IBlockState state) {
      return true;
   }

   public boolean canCollideCheck(IBlockState state, boolean hitIfLiquid) {
      return this.isCollidable();
   }

   public boolean isCollidable() {
      return true;
   }

   public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random) {
      this.updateTick(worldIn, pos, state, random);
   }

   public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
   }

   public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
   }

   public void onPlayerDestroy(World worldIn, BlockPos pos, IBlockState state) {
   }

   /** @deprecated */
   @Deprecated
   public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
   }

   public int tickRate(World worldIn) {
      return 10;
   }

   public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
   }

   public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
   }

   public int quantityDropped(Random random) {
      return 1;
   }

   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
      return Item.getItemFromBlock(this);
   }

   /** @deprecated */
   @Deprecated
   public float getPlayerRelativeBlockHardness(IBlockState state, EntityPlayer player, World worldIn, BlockPos pos) {
      float f = state.getBlockHardness(worldIn, pos);
      if (f < 0.0F) {
         return 0.0F;
      } else {
         return !player.canHarvestBlock(state) ? player.getDigSpeed(state) / f / 100.0F : player.getDigSpeed(state) / f / 30.0F;
      }
   }

   public final void dropBlockAsItem(World worldIn, BlockPos pos, IBlockState state, int fortune) {
      this.dropBlockAsItemWithChance(worldIn, pos, state, 1.0F, fortune);
   }

   public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
      if (!worldIn.isRemote) {
         int i = this.quantityDroppedWithBonus(fortune, worldIn.rand);

         for(int j = 0; j < i; ++j) {
            if (worldIn.rand.nextFloat() <= chance) {
               Item item = this.getItemDropped(state, worldIn.rand, fortune);
               if (item != Items.AIR) {
                  spawnAsEntity(worldIn, pos, new ItemStack(item, 1, this.damageDropped(state)));
               }
            }
         }
      }

   }

   public static void spawnAsEntity(World worldIn, BlockPos pos, ItemStack stack) {
      if (!worldIn.isRemote && !stack.isEmpty() && worldIn.getGameRules().getBoolean("doTileDrops")) {
         float f = 0.5F;
         double d0 = (double)(worldIn.rand.nextFloat() * 0.5F) + 0.25;
         double d1 = (double)(worldIn.rand.nextFloat() * 0.5F) + 0.25;
         double d2 = (double)(worldIn.rand.nextFloat() * 0.5F) + 0.25;
         EntityItem entityitem = new EntityItem(worldIn, (double)pos.getX() + d0, (double)pos.getY() + d1, (double)pos.getZ() + d2, stack);
         entityitem.setDefaultPickupDelay();
         worldIn.spawnEntity(entityitem);
      }

   }

   protected void dropXpOnBlockBreak(World worldIn, BlockPos pos, int amount) {
      if (!worldIn.isRemote && worldIn.getGameRules().getBoolean("doTileDrops")) {
         while(amount > 0) {
            int i = EntityXPOrb.getXPSplit(amount);
            amount -= i;
            worldIn.spawnEntity(new EntityXPOrb(worldIn, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, i));
         }
      }

   }

   public int damageDropped(IBlockState state) {
      return 0;
   }

   public float getExplosionResistance(Entity exploder) {
      return this.blockResistance / 5.0F;
   }

   /** @deprecated */
   @Deprecated
   @Nullable
   public RayTraceResult collisionRayTrace(IBlockState blockState, World worldIn, BlockPos pos, Vec3d start, Vec3d end) {
      return this.rayTrace(pos, start, end, blockState.getBoundingBox(worldIn, pos));
   }

   @Nullable
   protected RayTraceResult rayTrace(BlockPos pos, Vec3d start, Vec3d end, AxisAlignedBB boundingBox) {
      Vec3d vec3d = start.subtract((double)pos.getX(), (double)pos.getY(), (double)pos.getZ());
      Vec3d vec3d1 = end.subtract((double)pos.getX(), (double)pos.getY(), (double)pos.getZ());
      RayTraceResult raytraceresult = boundingBox.calculateIntercept(vec3d, vec3d1);
      return raytraceresult == null ? null : new RayTraceResult(raytraceresult.hitVec.add((double)pos.getX(), (double)pos.getY(), (double)pos.getZ()), raytraceresult.sideHit, pos);
   }

   public void onExplosionDestroy(World worldIn, BlockPos pos, Explosion explosionIn) {
   }

   public BlockRenderLayer getRenderLayer() {
      return BlockRenderLayer.SOLID;
   }

   public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side) {
      return this.canPlaceBlockAt(worldIn, pos);
   }

   public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
      return worldIn.getBlockState(pos).getBlock().material.isReplaceable();
   }

   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      return false;
   }

   public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
   }

   public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
      return this.getStateFromMeta(meta);
   }

   public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
   }

   public Vec3d modifyAcceleration(World worldIn, BlockPos pos, Entity entityIn, Vec3d motion) {
      return motion;
   }

   /** @deprecated */
   @Deprecated
   public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
      return 0;
   }

   /** @deprecated */
   @Deprecated
   public boolean canProvidePower(IBlockState state) {
      return false;
   }

   public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
   }

   /** @deprecated */
   @Deprecated
   public int getStrongPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
      return 0;
   }

   public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack) {
      player.addStat(StatList.getBlockStats(this));
      player.addExhaustion(0.005F);
      if (this.canSilkHarvest() && EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, stack) > 0) {
         ItemStack itemstack = this.getSilkTouchDrop(state);
         spawnAsEntity(worldIn, pos, itemstack);
      } else {
         int i = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, stack);
         this.dropBlockAsItem(worldIn, pos, state, i);
      }

   }

   protected boolean canSilkHarvest() {
      return this.getDefaultState().isFullCube() && !this.hasTileEntity;
   }

   protected ItemStack getSilkTouchDrop(IBlockState state) {
      Item item = Item.getItemFromBlock(this);
      int i = 0;
      if (item.getHasSubtypes()) {
         i = this.getMetaFromState(state);
      }

      return new ItemStack(item, 1, i);
   }

   public int quantityDroppedWithBonus(int fortune, Random random) {
      return this.quantityDropped(random);
   }

   public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
   }

   public boolean canSpawnInBlock() {
      return !this.material.isSolid() && !this.material.isLiquid();
   }

   public Block setTranslationKey(String key) {
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
   public boolean eventReceived(IBlockState state, World worldIn, BlockPos pos, int id, int param) {
      return false;
   }

   public boolean getEnableStats() {
      return this.enableStats;
   }

   protected Block disableStats() {
      this.enableStats = false;
      return this;
   }

   /** @deprecated */
   @Deprecated
   public EnumPushReaction getPushReaction(IBlockState state) {
      return this.material.getPushReaction();
   }

   /** @deprecated */
   @Deprecated
   public float getAmbientOcclusionLightValue(IBlockState state) {
      return state.isBlockNormalCube() ? 0.2F : 1.0F;
   }

   public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
      entityIn.fall(fallDistance, 1.0F);
   }

   public void onLanded(World worldIn, Entity entityIn) {
      entityIn.motionY = 0.0;
   }

   public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
      return new ItemStack(Item.getItemFromBlock(this), 1, this.damageDropped(state));
   }

   public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
      items.add(new ItemStack(this));
   }

   public CreativeTabs getCreativeTab() {
      return this.displayOnCreativeTab;
   }

   public Block setCreativeTab(CreativeTabs tab) {
      this.displayOnCreativeTab = tab;
      return this;
   }

   public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
   }

   public void fillWithRain(World worldIn, BlockPos pos) {
   }

   public boolean requiresUpdates() {
      return true;
   }

   public boolean canDropFromExplosion(Explosion explosionIn) {
      return true;
   }

   public boolean isAssociatedBlock(Block other) {
      return this == other;
   }

   public static boolean isEqualTo(Block blockIn, Block other) {
      if (blockIn != null && other != null) {
         return blockIn == other ? true : blockIn.isAssociatedBlock(other);
      } else {
         return false;
      }
   }

   /** @deprecated */
   @Deprecated
   public boolean hasComparatorInputOverride(IBlockState state) {
      return false;
   }

   /** @deprecated */
   @Deprecated
   public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
      return 0;
   }

   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, new IProperty[0]);
   }

   public BlockStateContainer getBlockState() {
      return this.blockState;
   }

   protected final void setDefaultState(IBlockState state) {
      this.defaultBlockState = state;
   }

   public final IBlockState getDefaultState() {
      return this.defaultBlockState;
   }

   public EnumOffsetType getOffsetType() {
      return Block.EnumOffsetType.NONE;
   }

   /** @deprecated */
   @Deprecated
   public Vec3d getOffset(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
      EnumOffsetType block$enumoffsettype = this.getOffsetType();
      if (block$enumoffsettype == Block.EnumOffsetType.NONE) {
         return Vec3d.ZERO;
      } else {
         long i = MathHelper.getCoordinateRandom(pos.getX(), 0, pos.getZ());
         return new Vec3d(((double)((float)(i >> 16 & 15L) / 15.0F) - 0.5) * 0.5, block$enumoffsettype == Block.EnumOffsetType.XYZ ? ((double)((float)(i >> 20 & 15L) / 15.0F) - 1.0) * 0.2 : 0.0, ((double)((float)(i >> 24 & 15L) / 15.0F) - 0.5) * 0.5);
      }
   }

   public SoundType getSoundType() {
      return this.blockSoundType;
   }

   public String toString() {
      return "Block{" + REGISTRY.getNameForObject(this) + "}";
   }

   public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
   }

   public static void registerBlocks() {
      registerBlock(0, (ResourceLocation)AIR_ID, (new BlockAir()).setTranslationKey("air"));
      registerBlock(1, (String)"stone", (new BlockStone()).setHardness(1.5F).setResistance(10.0F).setSoundType(SoundType.STONE).setTranslationKey("stone"));
      registerBlock(2, (String)"grass", (new BlockGrass()).setHardness(0.6F).setSoundType(SoundType.PLANT).setTranslationKey("grass"));
      registerBlock(3, (String)"dirt", (new BlockDirt()).setHardness(0.5F).setSoundType(SoundType.GROUND).setTranslationKey("dirt"));
      Block block = (new Block(Material.ROCK)).setHardness(2.0F).setResistance(10.0F).setSoundType(SoundType.STONE).setTranslationKey("stonebrick").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
      registerBlock(4, (String)"cobblestone", block);
      Block block1 = (new BlockPlanks()).setHardness(2.0F).setResistance(5.0F).setSoundType(SoundType.WOOD).setTranslationKey("wood");
      registerBlock(5, (String)"planks", block1);
      registerBlock(6, (String)"sapling", (new BlockSapling()).setHardness(0.0F).setSoundType(SoundType.PLANT).setTranslationKey("sapling"));
      registerBlock(7, (String)"bedrock", (new BlockEmptyDrops(Material.ROCK)).setBlockUnbreakable().setResistance(6000000.0F).setSoundType(SoundType.STONE).setTranslationKey("bedrock").disableStats().setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
      registerBlock(8, (String)"flowing_water", (new BlockDynamicLiquid(Material.WATER)).setHardness(100.0F).setLightOpacity(3).setTranslationKey("water").disableStats());
      registerBlock(9, (String)"water", (new BlockStaticLiquid(Material.WATER)).setHardness(100.0F).setLightOpacity(3).setTranslationKey("water").disableStats());
      registerBlock(10, (String)"flowing_lava", (new BlockDynamicLiquid(Material.LAVA)).setHardness(100.0F).setLightLevel(1.0F).setTranslationKey("lava").disableStats());
      registerBlock(11, (String)"lava", (new BlockStaticLiquid(Material.LAVA)).setHardness(100.0F).setLightLevel(1.0F).setTranslationKey("lava").disableStats());
      registerBlock(12, (String)"sand", (new BlockSand()).setHardness(0.5F).setSoundType(SoundType.SAND).setTranslationKey("sand"));
      registerBlock(13, (String)"gravel", (new BlockGravel()).setHardness(0.6F).setSoundType(SoundType.GROUND).setTranslationKey("gravel"));
      registerBlock(14, (String)"gold_ore", (new BlockOre()).setHardness(3.0F).setResistance(5.0F).setSoundType(SoundType.STONE).setTranslationKey("oreGold"));
      registerBlock(15, (String)"iron_ore", (new BlockOre()).setHardness(3.0F).setResistance(5.0F).setSoundType(SoundType.STONE).setTranslationKey("oreIron"));
      registerBlock(16, (String)"coal_ore", (new BlockOre()).setHardness(3.0F).setResistance(5.0F).setSoundType(SoundType.STONE).setTranslationKey("oreCoal"));
      registerBlock(17, (String)"log", (new BlockOldLog()).setTranslationKey("log"));
      registerBlock(18, (String)"leaves", (new BlockOldLeaf()).setTranslationKey("leaves"));
      registerBlock(19, (String)"sponge", (new BlockSponge()).setHardness(0.6F).setSoundType(SoundType.PLANT).setTranslationKey("sponge"));
      registerBlock(20, (String)"glass", (new BlockGlass(Material.GLASS, false)).setHardness(0.3F).setSoundType(SoundType.GLASS).setTranslationKey("glass"));
      registerBlock(21, (String)"lapis_ore", (new BlockOre()).setHardness(3.0F).setResistance(5.0F).setSoundType(SoundType.STONE).setTranslationKey("oreLapis"));
      registerBlock(22, (String)"lapis_block", (new Block(Material.IRON, MapColor.LAPIS)).setHardness(3.0F).setResistance(5.0F).setSoundType(SoundType.STONE).setTranslationKey("blockLapis").setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
      registerBlock(23, (String)"dispenser", (new BlockDispenser()).setHardness(3.5F).setSoundType(SoundType.STONE).setTranslationKey("dispenser"));
      Block block2 = (new BlockSandStone()).setSoundType(SoundType.STONE).setHardness(0.8F).setTranslationKey("sandStone");
      registerBlock(24, (String)"sandstone", block2);
      registerBlock(25, (String)"noteblock", (new BlockNote()).setSoundType(SoundType.WOOD).setHardness(0.8F).setTranslationKey("musicBlock"));
      registerBlock(26, (String)"bed", (new BlockBed()).setSoundType(SoundType.WOOD).setHardness(0.2F).setTranslationKey("bed").disableStats());
      registerBlock(27, (String)"golden_rail", (new BlockRailPowered()).setHardness(0.7F).setSoundType(SoundType.METAL).setTranslationKey("goldenRail"));
      registerBlock(28, (String)"detector_rail", (new BlockRailDetector()).setHardness(0.7F).setSoundType(SoundType.METAL).setTranslationKey("detectorRail"));
      registerBlock(29, (String)"sticky_piston", (new BlockPistonBase(true)).setTranslationKey("pistonStickyBase"));
      registerBlock(30, (String)"web", (new BlockWeb()).setLightOpacity(1).setHardness(4.0F).setTranslationKey("web"));
      registerBlock(31, (String)"tallgrass", (new BlockTallGrass()).setHardness(0.0F).setSoundType(SoundType.PLANT).setTranslationKey("tallgrass"));
      registerBlock(32, (String)"deadbush", (new BlockDeadBush()).setHardness(0.0F).setSoundType(SoundType.PLANT).setTranslationKey("deadbush"));
      registerBlock(33, (String)"piston", (new BlockPistonBase(false)).setTranslationKey("pistonBase"));
      registerBlock(34, (String)"piston_head", (new BlockPistonExtension()).setTranslationKey("pistonBase"));
      registerBlock(35, (String)"wool", (new BlockColored(Material.CLOTH)).setHardness(0.8F).setSoundType(SoundType.CLOTH).setTranslationKey("cloth"));
      registerBlock(36, (String)"piston_extension", new BlockPistonMoving());
      registerBlock(37, (String)"yellow_flower", (new BlockYellowFlower()).setHardness(0.0F).setSoundType(SoundType.PLANT).setTranslationKey("flower1"));
      registerBlock(38, (String)"red_flower", (new BlockRedFlower()).setHardness(0.0F).setSoundType(SoundType.PLANT).setTranslationKey("flower2"));
      Block block3 = (new BlockMushroom()).setHardness(0.0F).setSoundType(SoundType.PLANT).setLightLevel(0.125F).setTranslationKey("mushroom");
      registerBlock(39, (String)"brown_mushroom", block3);
      Block block4 = (new BlockMushroom()).setHardness(0.0F).setSoundType(SoundType.PLANT).setTranslationKey("mushroom");
      registerBlock(40, (String)"red_mushroom", block4);
      registerBlock(41, (String)"gold_block", (new Block(Material.IRON, MapColor.GOLD)).setHardness(3.0F).setResistance(10.0F).setSoundType(SoundType.METAL).setTranslationKey("blockGold").setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
      registerBlock(42, (String)"iron_block", (new Block(Material.IRON, MapColor.IRON)).setHardness(5.0F).setResistance(10.0F).setSoundType(SoundType.METAL).setTranslationKey("blockIron").setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
      registerBlock(43, (String)"double_stone_slab", (new BlockDoubleStoneSlab()).setHardness(2.0F).setResistance(10.0F).setSoundType(SoundType.STONE).setTranslationKey("stoneSlab"));
      registerBlock(44, (String)"stone_slab", (new BlockHalfStoneSlab()).setHardness(2.0F).setResistance(10.0F).setSoundType(SoundType.STONE).setTranslationKey("stoneSlab"));
      Block block5 = (new Block(Material.ROCK, MapColor.RED)).setHardness(2.0F).setResistance(10.0F).setSoundType(SoundType.STONE).setTranslationKey("brick").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
      registerBlock(45, (String)"brick_block", block5);
      registerBlock(46, (String)"tnt", (new BlockTNT()).setHardness(0.0F).setSoundType(SoundType.PLANT).setTranslationKey("tnt"));
      registerBlock(47, (String)"bookshelf", (new BlockBookshelf()).setHardness(1.5F).setSoundType(SoundType.WOOD).setTranslationKey("bookshelf"));
      registerBlock(48, (String)"mossy_cobblestone", (new Block(Material.ROCK)).setHardness(2.0F).setResistance(10.0F).setSoundType(SoundType.STONE).setTranslationKey("stoneMoss").setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
      registerBlock(49, (String)"obsidian", (new BlockObsidian()).setHardness(50.0F).setResistance(2000.0F).setSoundType(SoundType.STONE).setTranslationKey("obsidian"));
      registerBlock(50, (String)"torch", (new BlockTorch()).setHardness(0.0F).setLightLevel(0.9375F).setSoundType(SoundType.WOOD).setTranslationKey("torch"));
      registerBlock(51, (String)"fire", (new BlockFire()).setHardness(0.0F).setLightLevel(1.0F).setSoundType(SoundType.CLOTH).setTranslationKey("fire").disableStats());
      registerBlock(52, (String)"mob_spawner", (new BlockMobSpawner()).setHardness(5.0F).setSoundType(SoundType.METAL).setTranslationKey("mobSpawner").disableStats());
      registerBlock(53, (String)"oak_stairs", (new BlockStairs(block1.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.OAK))).setTranslationKey("stairsWood"));
      registerBlock(54, (String)"chest", (new BlockChest(BlockChest.Type.BASIC)).setHardness(2.5F).setSoundType(SoundType.WOOD).setTranslationKey("chest"));
      registerBlock(55, (String)"redstone_wire", (new BlockRedstoneWire()).setHardness(0.0F).setSoundType(SoundType.STONE).setTranslationKey("redstoneDust").disableStats());
      registerBlock(56, (String)"diamond_ore", (new BlockOre()).setHardness(3.0F).setResistance(5.0F).setSoundType(SoundType.STONE).setTranslationKey("oreDiamond"));
      registerBlock(57, (String)"diamond_block", (new Block(Material.IRON, MapColor.DIAMOND)).setHardness(5.0F).setResistance(10.0F).setSoundType(SoundType.METAL).setTranslationKey("blockDiamond").setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
      registerBlock(58, (String)"crafting_table", (new BlockWorkbench()).setHardness(2.5F).setSoundType(SoundType.WOOD).setTranslationKey("workbench"));
      registerBlock(59, (String)"wheat", (new BlockCrops()).setTranslationKey("crops"));
      Block block6 = (new BlockFarmland()).setHardness(0.6F).setSoundType(SoundType.GROUND).setTranslationKey("farmland");
      registerBlock(60, (String)"farmland", block6);
      registerBlock(61, (String)"furnace", (new BlockFurnace(false)).setHardness(3.5F).setSoundType(SoundType.STONE).setTranslationKey("furnace").setCreativeTab(CreativeTabs.DECORATIONS));
      registerBlock(62, (String)"lit_furnace", (new BlockFurnace(true)).setHardness(3.5F).setSoundType(SoundType.STONE).setLightLevel(0.875F).setTranslationKey("furnace"));
      registerBlock(63, (String)"standing_sign", (new BlockStandingSign()).setHardness(1.0F).setSoundType(SoundType.WOOD).setTranslationKey("sign").disableStats());
      registerBlock(64, (String)"wooden_door", (new BlockDoor(Material.WOOD)).setHardness(3.0F).setSoundType(SoundType.WOOD).setTranslationKey("doorOak").disableStats());
      registerBlock(65, (String)"ladder", (new BlockLadder()).setHardness(0.4F).setSoundType(SoundType.LADDER).setTranslationKey("ladder"));
      registerBlock(66, (String)"rail", (new BlockRail()).setHardness(0.7F).setSoundType(SoundType.METAL).setTranslationKey("rail"));
      registerBlock(67, (String)"stone_stairs", (new BlockStairs(block.getDefaultState())).setTranslationKey("stairsStone"));
      registerBlock(68, (String)"wall_sign", (new BlockWallSign()).setHardness(1.0F).setSoundType(SoundType.WOOD).setTranslationKey("sign").disableStats());
      registerBlock(69, (String)"lever", (new BlockLever()).setHardness(0.5F).setSoundType(SoundType.WOOD).setTranslationKey("lever"));
      registerBlock(70, (String)"stone_pressure_plate", (new BlockPressurePlate(Material.ROCK, BlockPressurePlate.Sensitivity.MOBS)).setHardness(0.5F).setSoundType(SoundType.STONE).setTranslationKey("pressurePlateStone"));
      registerBlock(71, (String)"iron_door", (new BlockDoor(Material.IRON)).setHardness(5.0F).setSoundType(SoundType.METAL).setTranslationKey("doorIron").disableStats());
      registerBlock(72, (String)"wooden_pressure_plate", (new BlockPressurePlate(Material.WOOD, BlockPressurePlate.Sensitivity.EVERYTHING)).setHardness(0.5F).setSoundType(SoundType.WOOD).setTranslationKey("pressurePlateWood"));
      registerBlock(73, (String)"redstone_ore", (new BlockRedstoneOre(false)).setHardness(3.0F).setResistance(5.0F).setSoundType(SoundType.STONE).setTranslationKey("oreRedstone").setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
      registerBlock(74, (String)"lit_redstone_ore", (new BlockRedstoneOre(true)).setLightLevel(0.625F).setHardness(3.0F).setResistance(5.0F).setSoundType(SoundType.STONE).setTranslationKey("oreRedstone"));
      registerBlock(75, (String)"unlit_redstone_torch", (new BlockRedstoneTorch(false)).setHardness(0.0F).setSoundType(SoundType.WOOD).setTranslationKey("notGate"));
      registerBlock(76, (String)"redstone_torch", (new BlockRedstoneTorch(true)).setHardness(0.0F).setLightLevel(0.5F).setSoundType(SoundType.WOOD).setTranslationKey("notGate").setCreativeTab(CreativeTabs.REDSTONE));
      registerBlock(77, (String)"stone_button", (new BlockButtonStone()).setHardness(0.5F).setSoundType(SoundType.STONE).setTranslationKey("button"));
      registerBlock(78, (String)"snow_layer", (new BlockSnow()).setHardness(0.1F).setSoundType(SoundType.SNOW).setTranslationKey("snow").setLightOpacity(0));
      registerBlock(79, (String)"ice", (new BlockIce()).setHardness(0.5F).setLightOpacity(3).setSoundType(SoundType.GLASS).setTranslationKey("ice"));
      registerBlock(80, (String)"snow", (new BlockSnowBlock()).setHardness(0.2F).setSoundType(SoundType.SNOW).setTranslationKey("snow"));
      registerBlock(81, (String)"cactus", (new BlockCactus()).setHardness(0.4F).setSoundType(SoundType.CLOTH).setTranslationKey("cactus"));
      registerBlock(82, (String)"clay", (new BlockClay()).setHardness(0.6F).setSoundType(SoundType.GROUND).setTranslationKey("clay"));
      registerBlock(83, (String)"reeds", (new BlockReed()).setHardness(0.0F).setSoundType(SoundType.PLANT).setTranslationKey("reeds").disableStats());
      registerBlock(84, (String)"jukebox", (new BlockJukebox()).setHardness(2.0F).setResistance(10.0F).setSoundType(SoundType.STONE).setTranslationKey("jukebox"));
      registerBlock(85, (String)"fence", (new BlockFence(Material.WOOD, BlockPlanks.EnumType.OAK.getMapColor())).setHardness(2.0F).setResistance(5.0F).setSoundType(SoundType.WOOD).setTranslationKey("fence"));
      Block block7 = (new BlockPumpkin()).setHardness(1.0F).setSoundType(SoundType.WOOD).setTranslationKey("pumpkin");
      registerBlock(86, (String)"pumpkin", block7);
      registerBlock(87, (String)"netherrack", (new BlockNetherrack()).setHardness(0.4F).setSoundType(SoundType.STONE).setTranslationKey("hellrock"));
      registerBlock(88, (String)"soul_sand", (new BlockSoulSand()).setHardness(0.5F).setSoundType(SoundType.SAND).setTranslationKey("hellsand"));
      registerBlock(89, (String)"glowstone", (new BlockGlowstone(Material.GLASS)).setHardness(0.3F).setSoundType(SoundType.GLASS).setLightLevel(1.0F).setTranslationKey("lightgem"));
      registerBlock(90, (String)"portal", (new BlockPortal()).setHardness(-1.0F).setSoundType(SoundType.GLASS).setLightLevel(0.75F).setTranslationKey("portal"));
      registerBlock(91, (String)"lit_pumpkin", (new BlockPumpkin()).setHardness(1.0F).setSoundType(SoundType.WOOD).setLightLevel(1.0F).setTranslationKey("litpumpkin"));
      registerBlock(92, (String)"cake", (new BlockCake()).setHardness(0.5F).setSoundType(SoundType.CLOTH).setTranslationKey("cake").disableStats());
      registerBlock(93, (String)"unpowered_repeater", (new BlockRedstoneRepeater(false)).setHardness(0.0F).setSoundType(SoundType.WOOD).setTranslationKey("diode").disableStats());
      registerBlock(94, (String)"powered_repeater", (new BlockRedstoneRepeater(true)).setHardness(0.0F).setSoundType(SoundType.WOOD).setTranslationKey("diode").disableStats());
      registerBlock(95, (String)"stained_glass", (new BlockStainedGlass(Material.GLASS)).setHardness(0.3F).setSoundType(SoundType.GLASS).setTranslationKey("stainedGlass"));
      registerBlock(96, (String)"trapdoor", (new BlockTrapDoor(Material.WOOD)).setHardness(3.0F).setSoundType(SoundType.WOOD).setTranslationKey("trapdoor").disableStats());
      registerBlock(97, (String)"monster_egg", (new BlockSilverfish()).setHardness(0.75F).setTranslationKey("monsterStoneEgg"));
      Block block8 = (new BlockStoneBrick()).setHardness(1.5F).setResistance(10.0F).setSoundType(SoundType.STONE).setTranslationKey("stonebricksmooth");
      registerBlock(98, (String)"stonebrick", block8);
      registerBlock(99, (String)"brown_mushroom_block", (new BlockHugeMushroom(Material.WOOD, MapColor.DIRT, block3)).setHardness(0.2F).setSoundType(SoundType.WOOD).setTranslationKey("mushroom"));
      registerBlock(100, (String)"red_mushroom_block", (new BlockHugeMushroom(Material.WOOD, MapColor.RED, block4)).setHardness(0.2F).setSoundType(SoundType.WOOD).setTranslationKey("mushroom"));
      registerBlock(101, (String)"iron_bars", (new BlockPane(Material.IRON, true)).setHardness(5.0F).setResistance(10.0F).setSoundType(SoundType.METAL).setTranslationKey("fenceIron"));
      registerBlock(102, (String)"glass_pane", (new BlockPane(Material.GLASS, false)).setHardness(0.3F).setSoundType(SoundType.GLASS).setTranslationKey("thinGlass"));
      Block block9 = (new BlockMelon()).setHardness(1.0F).setSoundType(SoundType.WOOD).setTranslationKey("melon");
      registerBlock(103, (String)"melon_block", block9);
      registerBlock(104, (String)"pumpkin_stem", (new BlockStem(block7)).setHardness(0.0F).setSoundType(SoundType.WOOD).setTranslationKey("pumpkinStem"));
      registerBlock(105, (String)"melon_stem", (new BlockStem(block9)).setHardness(0.0F).setSoundType(SoundType.WOOD).setTranslationKey("pumpkinStem"));
      registerBlock(106, (String)"vine", (new BlockVine()).setHardness(0.2F).setSoundType(SoundType.PLANT).setTranslationKey("vine"));
      registerBlock(107, (String)"fence_gate", (new BlockFenceGate(BlockPlanks.EnumType.OAK)).setHardness(2.0F).setResistance(5.0F).setSoundType(SoundType.WOOD).setTranslationKey("fenceGate"));
      registerBlock(108, (String)"brick_stairs", (new BlockStairs(block5.getDefaultState())).setTranslationKey("stairsBrick"));
      registerBlock(109, (String)"stone_brick_stairs", (new BlockStairs(block8.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.DEFAULT))).setTranslationKey("stairsStoneBrickSmooth"));
      registerBlock(110, (String)"mycelium", (new BlockMycelium()).setHardness(0.6F).setSoundType(SoundType.PLANT).setTranslationKey("mycel"));
      registerBlock(111, (String)"waterlily", (new BlockLilyPad()).setHardness(0.0F).setSoundType(SoundType.PLANT).setTranslationKey("waterlily"));
      Block block10 = (new BlockNetherBrick()).setHardness(2.0F).setResistance(10.0F).setSoundType(SoundType.STONE).setTranslationKey("netherBrick").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
      registerBlock(112, (String)"nether_brick", block10);
      registerBlock(113, (String)"nether_brick_fence", (new BlockFence(Material.ROCK, MapColor.NETHERRACK)).setHardness(2.0F).setResistance(10.0F).setSoundType(SoundType.STONE).setTranslationKey("netherFence"));
      registerBlock(114, (String)"nether_brick_stairs", (new BlockStairs(block10.getDefaultState())).setTranslationKey("stairsNetherBrick"));
      registerBlock(115, (String)"nether_wart", (new BlockNetherWart()).setTranslationKey("netherStalk"));
      registerBlock(116, (String)"enchanting_table", (new BlockEnchantmentTable()).setHardness(5.0F).setResistance(2000.0F).setTranslationKey("enchantmentTable"));
      registerBlock(117, (String)"brewing_stand", (new BlockBrewingStand()).setHardness(0.5F).setLightLevel(0.125F).setTranslationKey("brewingStand"));
      registerBlock(118, (String)"cauldron", (new BlockCauldron()).setHardness(2.0F).setTranslationKey("cauldron"));
      registerBlock(119, (String)"end_portal", (new BlockEndPortal(Material.PORTAL)).setHardness(-1.0F).setResistance(6000000.0F));
      registerBlock(120, (String)"end_portal_frame", (new BlockEndPortalFrame()).setSoundType(SoundType.GLASS).setLightLevel(0.125F).setHardness(-1.0F).setTranslationKey("endPortalFrame").setResistance(6000000.0F).setCreativeTab(CreativeTabs.DECORATIONS));
      registerBlock(121, (String)"end_stone", (new Block(Material.ROCK, MapColor.SAND)).setHardness(3.0F).setResistance(15.0F).setSoundType(SoundType.STONE).setTranslationKey("whiteStone").setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
      registerBlock(122, (String)"dragon_egg", (new BlockDragonEgg()).setHardness(3.0F).setResistance(15.0F).setSoundType(SoundType.STONE).setLightLevel(0.125F).setTranslationKey("dragonEgg"));
      registerBlock(123, (String)"redstone_lamp", (new BlockRedstoneLight(false)).setHardness(0.3F).setSoundType(SoundType.GLASS).setTranslationKey("redstoneLight").setCreativeTab(CreativeTabs.REDSTONE));
      registerBlock(124, (String)"lit_redstone_lamp", (new BlockRedstoneLight(true)).setHardness(0.3F).setSoundType(SoundType.GLASS).setTranslationKey("redstoneLight"));
      registerBlock(125, (String)"double_wooden_slab", (new BlockDoubleWoodSlab()).setHardness(2.0F).setResistance(5.0F).setSoundType(SoundType.WOOD).setTranslationKey("woodSlab"));
      registerBlock(126, (String)"wooden_slab", (new BlockHalfWoodSlab()).setHardness(2.0F).setResistance(5.0F).setSoundType(SoundType.WOOD).setTranslationKey("woodSlab"));
      registerBlock(127, (String)"cocoa", (new BlockCocoa()).setHardness(0.2F).setResistance(5.0F).setSoundType(SoundType.WOOD).setTranslationKey("cocoa"));
      registerBlock(128, (String)"sandstone_stairs", (new BlockStairs(block2.getDefaultState().withProperty(BlockSandStone.TYPE, BlockSandStone.EnumType.SMOOTH))).setTranslationKey("stairsSandStone"));
      registerBlock(129, (String)"emerald_ore", (new BlockOre()).setHardness(3.0F).setResistance(5.0F).setSoundType(SoundType.STONE).setTranslationKey("oreEmerald"));
      registerBlock(130, (String)"ender_chest", (new BlockEnderChest()).setHardness(22.5F).setResistance(1000.0F).setSoundType(SoundType.STONE).setTranslationKey("enderChest").setLightLevel(0.5F));
      registerBlock(131, (String)"tripwire_hook", (new BlockTripWireHook()).setTranslationKey("tripWireSource"));
      registerBlock(132, (String)"tripwire", (new BlockTripWire()).setTranslationKey("tripWire"));
      registerBlock(133, (String)"emerald_block", (new Block(Material.IRON, MapColor.EMERALD)).setHardness(5.0F).setResistance(10.0F).setSoundType(SoundType.METAL).setTranslationKey("blockEmerald").setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
      registerBlock(134, (String)"spruce_stairs", (new BlockStairs(block1.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.SPRUCE))).setTranslationKey("stairsWoodSpruce"));
      registerBlock(135, (String)"birch_stairs", (new BlockStairs(block1.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.BIRCH))).setTranslationKey("stairsWoodBirch"));
      registerBlock(136, (String)"jungle_stairs", (new BlockStairs(block1.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.JUNGLE))).setTranslationKey("stairsWoodJungle"));
      registerBlock(137, (String)"command_block", (new BlockCommandBlock(MapColor.BROWN)).setBlockUnbreakable().setResistance(6000000.0F).setTranslationKey("commandBlock"));
      registerBlock(138, (String)"beacon", (new BlockBeacon()).setTranslationKey("beacon").setLightLevel(1.0F));
      registerBlock(139, (String)"cobblestone_wall", (new BlockWall(block)).setTranslationKey("cobbleWall"));
      registerBlock(140, (String)"flower_pot", (new BlockFlowerPot()).setHardness(0.0F).setSoundType(SoundType.STONE).setTranslationKey("flowerPot"));
      registerBlock(141, (String)"carrots", (new BlockCarrot()).setTranslationKey("carrots"));
      registerBlock(142, (String)"potatoes", (new BlockPotato()).setTranslationKey("potatoes"));
      registerBlock(143, (String)"wooden_button", (new BlockButtonWood()).setHardness(0.5F).setSoundType(SoundType.WOOD).setTranslationKey("button"));
      registerBlock(144, (String)"skull", (new BlockSkull()).setHardness(1.0F).setSoundType(SoundType.STONE).setTranslationKey("skull"));
      registerBlock(145, (String)"anvil", (new BlockAnvil()).setHardness(5.0F).setSoundType(SoundType.ANVIL).setResistance(2000.0F).setTranslationKey("anvil"));
      registerBlock(146, (String)"trapped_chest", (new BlockChest(BlockChest.Type.TRAP)).setHardness(2.5F).setSoundType(SoundType.WOOD).setTranslationKey("chestTrap"));
      registerBlock(147, (String)"light_weighted_pressure_plate", (new BlockPressurePlateWeighted(Material.IRON, 15, MapColor.GOLD)).setHardness(0.5F).setSoundType(SoundType.WOOD).setTranslationKey("weightedPlate_light"));
      registerBlock(148, (String)"heavy_weighted_pressure_plate", (new BlockPressurePlateWeighted(Material.IRON, 150)).setHardness(0.5F).setSoundType(SoundType.WOOD).setTranslationKey("weightedPlate_heavy"));
      registerBlock(149, (String)"unpowered_comparator", (new BlockRedstoneComparator(false)).setHardness(0.0F).setSoundType(SoundType.WOOD).setTranslationKey("comparator").disableStats());
      registerBlock(150, (String)"powered_comparator", (new BlockRedstoneComparator(true)).setHardness(0.0F).setLightLevel(0.625F).setSoundType(SoundType.WOOD).setTranslationKey("comparator").disableStats());
      registerBlock(151, (String)"daylight_detector", new BlockDaylightDetector(false));
      registerBlock(152, (String)"redstone_block", (new BlockCompressedPowered(Material.IRON, MapColor.TNT)).setHardness(5.0F).setResistance(10.0F).setSoundType(SoundType.METAL).setTranslationKey("blockRedstone").setCreativeTab(CreativeTabs.REDSTONE));
      registerBlock(153, (String)"quartz_ore", (new BlockOre(MapColor.NETHERRACK)).setHardness(3.0F).setResistance(5.0F).setSoundType(SoundType.STONE).setTranslationKey("netherquartz"));
      registerBlock(154, (String)"hopper", (new BlockHopper()).setHardness(3.0F).setResistance(8.0F).setSoundType(SoundType.METAL).setTranslationKey("hopper"));
      Block block11 = (new BlockQuartz()).setSoundType(SoundType.STONE).setHardness(0.8F).setTranslationKey("quartzBlock");
      registerBlock(155, (String)"quartz_block", block11);
      registerBlock(156, (String)"quartz_stairs", (new BlockStairs(block11.getDefaultState().withProperty(BlockQuartz.VARIANT, BlockQuartz.EnumType.DEFAULT))).setTranslationKey("stairsQuartz"));
      registerBlock(157, (String)"activator_rail", (new BlockRailPowered()).setHardness(0.7F).setSoundType(SoundType.METAL).setTranslationKey("activatorRail"));
      registerBlock(158, (String)"dropper", (new BlockDropper()).setHardness(3.5F).setSoundType(SoundType.STONE).setTranslationKey("dropper"));
      registerBlock(159, (String)"stained_hardened_clay", (new BlockStainedHardenedClay()).setHardness(1.25F).setResistance(7.0F).setSoundType(SoundType.STONE).setTranslationKey("clayHardenedStained"));
      registerBlock(160, (String)"stained_glass_pane", (new BlockStainedGlassPane()).setHardness(0.3F).setSoundType(SoundType.GLASS).setTranslationKey("thinStainedGlass"));
      registerBlock(161, (String)"leaves2", (new BlockNewLeaf()).setTranslationKey("leaves"));
      registerBlock(162, (String)"log2", (new BlockNewLog()).setTranslationKey("log"));
      registerBlock(163, (String)"acacia_stairs", (new BlockStairs(block1.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.ACACIA))).setTranslationKey("stairsWoodAcacia"));
      registerBlock(164, (String)"dark_oak_stairs", (new BlockStairs(block1.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.DARK_OAK))).setTranslationKey("stairsWoodDarkOak"));
      registerBlock(165, (String)"slime", (new BlockSlime()).setTranslationKey("slime").setSoundType(SoundType.SLIME));
      registerBlock(166, (String)"barrier", (new BlockBarrier()).setTranslationKey("barrier"));
      registerBlock(167, (String)"iron_trapdoor", (new BlockTrapDoor(Material.IRON)).setHardness(5.0F).setSoundType(SoundType.METAL).setTranslationKey("ironTrapdoor").disableStats());
      registerBlock(168, (String)"prismarine", (new BlockPrismarine()).setHardness(1.5F).setResistance(10.0F).setSoundType(SoundType.STONE).setTranslationKey("prismarine"));
      registerBlock(169, (String)"sea_lantern", (new BlockSeaLantern(Material.GLASS)).setHardness(0.3F).setSoundType(SoundType.GLASS).setLightLevel(1.0F).setTranslationKey("seaLantern"));
      registerBlock(170, (String)"hay_block", (new BlockHay()).setHardness(0.5F).setSoundType(SoundType.PLANT).setTranslationKey("hayBlock").setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
      registerBlock(171, (String)"carpet", (new BlockCarpet()).setHardness(0.1F).setSoundType(SoundType.CLOTH).setTranslationKey("woolCarpet").setLightOpacity(0));
      registerBlock(172, (String)"hardened_clay", (new BlockHardenedClay()).setHardness(1.25F).setResistance(7.0F).setSoundType(SoundType.STONE).setTranslationKey("clayHardened"));
      registerBlock(173, (String)"coal_block", (new Block(Material.ROCK, MapColor.BLACK)).setHardness(5.0F).setResistance(10.0F).setSoundType(SoundType.STONE).setTranslationKey("blockCoal").setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
      registerBlock(174, (String)"packed_ice", (new BlockPackedIce()).setHardness(0.5F).setSoundType(SoundType.GLASS).setTranslationKey("icePacked"));
      registerBlock(175, (String)"double_plant", new BlockDoublePlant());
      registerBlock(176, (String)"standing_banner", (new BlockBanner.BlockBannerStanding()).setHardness(1.0F).setSoundType(SoundType.WOOD).setTranslationKey("banner").disableStats());
      registerBlock(177, (String)"wall_banner", (new BlockBanner.BlockBannerHanging()).setHardness(1.0F).setSoundType(SoundType.WOOD).setTranslationKey("banner").disableStats());
      registerBlock(178, (String)"daylight_detector_inverted", new BlockDaylightDetector(true));
      Block block12 = (new BlockRedSandstone()).setSoundType(SoundType.STONE).setHardness(0.8F).setTranslationKey("redSandStone");
      registerBlock(179, (String)"red_sandstone", block12);
      registerBlock(180, (String)"red_sandstone_stairs", (new BlockStairs(block12.getDefaultState().withProperty(BlockRedSandstone.TYPE, BlockRedSandstone.EnumType.SMOOTH))).setTranslationKey("stairsRedSandStone"));
      registerBlock(181, (String)"double_stone_slab2", (new BlockDoubleStoneSlabNew()).setHardness(2.0F).setResistance(10.0F).setSoundType(SoundType.STONE).setTranslationKey("stoneSlab2"));
      registerBlock(182, (String)"stone_slab2", (new BlockHalfStoneSlabNew()).setHardness(2.0F).setResistance(10.0F).setSoundType(SoundType.STONE).setTranslationKey("stoneSlab2"));
      registerBlock(183, (String)"spruce_fence_gate", (new BlockFenceGate(BlockPlanks.EnumType.SPRUCE)).setHardness(2.0F).setResistance(5.0F).setSoundType(SoundType.WOOD).setTranslationKey("spruceFenceGate"));
      registerBlock(184, (String)"birch_fence_gate", (new BlockFenceGate(BlockPlanks.EnumType.BIRCH)).setHardness(2.0F).setResistance(5.0F).setSoundType(SoundType.WOOD).setTranslationKey("birchFenceGate"));
      registerBlock(185, (String)"jungle_fence_gate", (new BlockFenceGate(BlockPlanks.EnumType.JUNGLE)).setHardness(2.0F).setResistance(5.0F).setSoundType(SoundType.WOOD).setTranslationKey("jungleFenceGate"));
      registerBlock(186, (String)"dark_oak_fence_gate", (new BlockFenceGate(BlockPlanks.EnumType.DARK_OAK)).setHardness(2.0F).setResistance(5.0F).setSoundType(SoundType.WOOD).setTranslationKey("darkOakFenceGate"));
      registerBlock(187, (String)"acacia_fence_gate", (new BlockFenceGate(BlockPlanks.EnumType.ACACIA)).setHardness(2.0F).setResistance(5.0F).setSoundType(SoundType.WOOD).setTranslationKey("acaciaFenceGate"));
      registerBlock(188, (String)"spruce_fence", (new BlockFence(Material.WOOD, BlockPlanks.EnumType.SPRUCE.getMapColor())).setHardness(2.0F).setResistance(5.0F).setSoundType(SoundType.WOOD).setTranslationKey("spruceFence"));
      registerBlock(189, (String)"birch_fence", (new BlockFence(Material.WOOD, BlockPlanks.EnumType.BIRCH.getMapColor())).setHardness(2.0F).setResistance(5.0F).setSoundType(SoundType.WOOD).setTranslationKey("birchFence"));
      registerBlock(190, (String)"jungle_fence", (new BlockFence(Material.WOOD, BlockPlanks.EnumType.JUNGLE.getMapColor())).setHardness(2.0F).setResistance(5.0F).setSoundType(SoundType.WOOD).setTranslationKey("jungleFence"));
      registerBlock(191, (String)"dark_oak_fence", (new BlockFence(Material.WOOD, BlockPlanks.EnumType.DARK_OAK.getMapColor())).setHardness(2.0F).setResistance(5.0F).setSoundType(SoundType.WOOD).setTranslationKey("darkOakFence"));
      registerBlock(192, (String)"acacia_fence", (new BlockFence(Material.WOOD, BlockPlanks.EnumType.ACACIA.getMapColor())).setHardness(2.0F).setResistance(5.0F).setSoundType(SoundType.WOOD).setTranslationKey("acaciaFence"));
      registerBlock(193, (String)"spruce_door", (new BlockDoor(Material.WOOD)).setHardness(3.0F).setSoundType(SoundType.WOOD).setTranslationKey("doorSpruce").disableStats());
      registerBlock(194, (String)"birch_door", (new BlockDoor(Material.WOOD)).setHardness(3.0F).setSoundType(SoundType.WOOD).setTranslationKey("doorBirch").disableStats());
      registerBlock(195, (String)"jungle_door", (new BlockDoor(Material.WOOD)).setHardness(3.0F).setSoundType(SoundType.WOOD).setTranslationKey("doorJungle").disableStats());
      registerBlock(196, (String)"acacia_door", (new BlockDoor(Material.WOOD)).setHardness(3.0F).setSoundType(SoundType.WOOD).setTranslationKey("doorAcacia").disableStats());
      registerBlock(197, (String)"dark_oak_door", (new BlockDoor(Material.WOOD)).setHardness(3.0F).setSoundType(SoundType.WOOD).setTranslationKey("doorDarkOak").disableStats());
      registerBlock(198, (String)"end_rod", (new BlockEndRod()).setHardness(0.0F).setLightLevel(0.9375F).setSoundType(SoundType.WOOD).setTranslationKey("endRod"));
      registerBlock(199, (String)"chorus_plant", (new BlockChorusPlant()).setHardness(0.4F).setSoundType(SoundType.WOOD).setTranslationKey("chorusPlant"));
      registerBlock(200, (String)"chorus_flower", (new BlockChorusFlower()).setHardness(0.4F).setSoundType(SoundType.WOOD).setTranslationKey("chorusFlower"));
      Block block13 = (new Block(Material.ROCK, MapColor.MAGENTA)).setHardness(1.5F).setResistance(10.0F).setSoundType(SoundType.STONE).setCreativeTab(CreativeTabs.BUILDING_BLOCKS).setTranslationKey("purpurBlock");
      registerBlock(201, (String)"purpur_block", block13);
      registerBlock(202, (String)"purpur_pillar", (new BlockRotatedPillar(Material.ROCK, MapColor.MAGENTA)).setHardness(1.5F).setResistance(10.0F).setSoundType(SoundType.STONE).setCreativeTab(CreativeTabs.BUILDING_BLOCKS).setTranslationKey("purpurPillar"));
      registerBlock(203, (String)"purpur_stairs", (new BlockStairs(block13.getDefaultState())).setTranslationKey("stairsPurpur"));
      registerBlock(204, (String)"purpur_double_slab", (new BlockPurpurSlab.Double()).setHardness(2.0F).setResistance(10.0F).setSoundType(SoundType.STONE).setTranslationKey("purpurSlab"));
      registerBlock(205, (String)"purpur_slab", (new BlockPurpurSlab.Half()).setHardness(2.0F).setResistance(10.0F).setSoundType(SoundType.STONE).setTranslationKey("purpurSlab"));
      registerBlock(206, (String)"end_bricks", (new Block(Material.ROCK, MapColor.SAND)).setSoundType(SoundType.STONE).setHardness(0.8F).setCreativeTab(CreativeTabs.BUILDING_BLOCKS).setTranslationKey("endBricks"));
      registerBlock(207, (String)"beetroots", (new BlockBeetroot()).setTranslationKey("beetroots"));
      Block block14 = (new BlockGrassPath()).setHardness(0.65F).setSoundType(SoundType.PLANT).setTranslationKey("grassPath").disableStats();
      registerBlock(208, (String)"grass_path", block14);
      registerBlock(209, (String)"end_gateway", (new BlockEndGateway(Material.PORTAL)).setHardness(-1.0F).setResistance(6000000.0F));
      registerBlock(210, (String)"repeating_command_block", (new BlockCommandBlock(MapColor.PURPLE)).setBlockUnbreakable().setResistance(6000000.0F).setTranslationKey("repeatingCommandBlock"));
      registerBlock(211, (String)"chain_command_block", (new BlockCommandBlock(MapColor.GREEN)).setBlockUnbreakable().setResistance(6000000.0F).setTranslationKey("chainCommandBlock"));
      registerBlock(212, (String)"frosted_ice", (new BlockFrostedIce()).setHardness(0.5F).setLightOpacity(3).setSoundType(SoundType.GLASS).setTranslationKey("frostedIce"));
      registerBlock(213, (String)"magma", (new BlockMagma()).setHardness(0.5F).setSoundType(SoundType.STONE).setTranslationKey("magma"));
      registerBlock(214, (String)"nether_wart_block", (new Block(Material.GRASS, MapColor.RED)).setCreativeTab(CreativeTabs.BUILDING_BLOCKS).setHardness(1.0F).setSoundType(SoundType.WOOD).setTranslationKey("netherWartBlock"));
      registerBlock(215, (String)"red_nether_brick", (new BlockNetherBrick()).setHardness(2.0F).setResistance(10.0F).setSoundType(SoundType.STONE).setTranslationKey("redNetherBrick").setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
      registerBlock(216, (String)"bone_block", (new BlockBone()).setTranslationKey("boneBlock"));
      registerBlock(217, (String)"structure_void", (new BlockStructureVoid()).setTranslationKey("structureVoid"));
      registerBlock(218, (String)"observer", (new BlockObserver()).setHardness(3.0F).setTranslationKey("observer"));
      registerBlock(219, (String)"white_shulker_box", (new BlockShulkerBox(EnumDyeColor.WHITE)).setHardness(2.0F).setSoundType(SoundType.STONE).setTranslationKey("shulkerBoxWhite"));
      registerBlock(220, (String)"orange_shulker_box", (new BlockShulkerBox(EnumDyeColor.ORANGE)).setHardness(2.0F).setSoundType(SoundType.STONE).setTranslationKey("shulkerBoxOrange"));
      registerBlock(221, (String)"magenta_shulker_box", (new BlockShulkerBox(EnumDyeColor.MAGENTA)).setHardness(2.0F).setSoundType(SoundType.STONE).setTranslationKey("shulkerBoxMagenta"));
      registerBlock(222, (String)"light_blue_shulker_box", (new BlockShulkerBox(EnumDyeColor.LIGHT_BLUE)).setHardness(2.0F).setSoundType(SoundType.STONE).setTranslationKey("shulkerBoxLightBlue"));
      registerBlock(223, (String)"yellow_shulker_box", (new BlockShulkerBox(EnumDyeColor.YELLOW)).setHardness(2.0F).setSoundType(SoundType.STONE).setTranslationKey("shulkerBoxYellow"));
      registerBlock(224, (String)"lime_shulker_box", (new BlockShulkerBox(EnumDyeColor.LIME)).setHardness(2.0F).setSoundType(SoundType.STONE).setTranslationKey("shulkerBoxLime"));
      registerBlock(225, (String)"pink_shulker_box", (new BlockShulkerBox(EnumDyeColor.PINK)).setHardness(2.0F).setSoundType(SoundType.STONE).setTranslationKey("shulkerBoxPink"));
      registerBlock(226, (String)"gray_shulker_box", (new BlockShulkerBox(EnumDyeColor.GRAY)).setHardness(2.0F).setSoundType(SoundType.STONE).setTranslationKey("shulkerBoxGray"));
      registerBlock(227, (String)"silver_shulker_box", (new BlockShulkerBox(EnumDyeColor.SILVER)).setHardness(2.0F).setSoundType(SoundType.STONE).setTranslationKey("shulkerBoxSilver"));
      registerBlock(228, (String)"cyan_shulker_box", (new BlockShulkerBox(EnumDyeColor.CYAN)).setHardness(2.0F).setSoundType(SoundType.STONE).setTranslationKey("shulkerBoxCyan"));
      registerBlock(229, (String)"purple_shulker_box", (new BlockShulkerBox(EnumDyeColor.PURPLE)).setHardness(2.0F).setSoundType(SoundType.STONE).setTranslationKey("shulkerBoxPurple"));
      registerBlock(230, (String)"blue_shulker_box", (new BlockShulkerBox(EnumDyeColor.BLUE)).setHardness(2.0F).setSoundType(SoundType.STONE).setTranslationKey("shulkerBoxBlue"));
      registerBlock(231, (String)"brown_shulker_box", (new BlockShulkerBox(EnumDyeColor.BROWN)).setHardness(2.0F).setSoundType(SoundType.STONE).setTranslationKey("shulkerBoxBrown"));
      registerBlock(232, (String)"green_shulker_box", (new BlockShulkerBox(EnumDyeColor.GREEN)).setHardness(2.0F).setSoundType(SoundType.STONE).setTranslationKey("shulkerBoxGreen"));
      registerBlock(233, (String)"red_shulker_box", (new BlockShulkerBox(EnumDyeColor.RED)).setHardness(2.0F).setSoundType(SoundType.STONE).setTranslationKey("shulkerBoxRed"));
      registerBlock(234, (String)"black_shulker_box", (new BlockShulkerBox(EnumDyeColor.BLACK)).setHardness(2.0F).setSoundType(SoundType.STONE).setTranslationKey("shulkerBoxBlack"));
      registerBlock(235, (String)"white_glazed_terracotta", new BlockGlazedTerracotta(EnumDyeColor.WHITE));
      registerBlock(236, (String)"orange_glazed_terracotta", new BlockGlazedTerracotta(EnumDyeColor.ORANGE));
      registerBlock(237, (String)"magenta_glazed_terracotta", new BlockGlazedTerracotta(EnumDyeColor.MAGENTA));
      registerBlock(238, (String)"light_blue_glazed_terracotta", new BlockGlazedTerracotta(EnumDyeColor.LIGHT_BLUE));
      registerBlock(239, (String)"yellow_glazed_terracotta", new BlockGlazedTerracotta(EnumDyeColor.YELLOW));
      registerBlock(240, (String)"lime_glazed_terracotta", new BlockGlazedTerracotta(EnumDyeColor.LIME));
      registerBlock(241, (String)"pink_glazed_terracotta", new BlockGlazedTerracotta(EnumDyeColor.PINK));
      registerBlock(242, (String)"gray_glazed_terracotta", new BlockGlazedTerracotta(EnumDyeColor.GRAY));
      registerBlock(243, (String)"silver_glazed_terracotta", new BlockGlazedTerracotta(EnumDyeColor.SILVER));
      registerBlock(244, (String)"cyan_glazed_terracotta", new BlockGlazedTerracotta(EnumDyeColor.CYAN));
      registerBlock(245, (String)"purple_glazed_terracotta", new BlockGlazedTerracotta(EnumDyeColor.PURPLE));
      registerBlock(246, (String)"blue_glazed_terracotta", new BlockGlazedTerracotta(EnumDyeColor.BLUE));
      registerBlock(247, (String)"brown_glazed_terracotta", new BlockGlazedTerracotta(EnumDyeColor.BROWN));
      registerBlock(248, (String)"green_glazed_terracotta", new BlockGlazedTerracotta(EnumDyeColor.GREEN));
      registerBlock(249, (String)"red_glazed_terracotta", new BlockGlazedTerracotta(EnumDyeColor.RED));
      registerBlock(250, (String)"black_glazed_terracotta", new BlockGlazedTerracotta(EnumDyeColor.BLACK));
      registerBlock(251, (String)"concrete", (new BlockColored(Material.ROCK)).setHardness(1.8F).setSoundType(SoundType.STONE).setTranslationKey("concrete"));
      registerBlock(252, (String)"concrete_powder", (new BlockConcretePowder()).setHardness(0.5F).setSoundType(SoundType.SAND).setTranslationKey("concretePowder"));
      registerBlock(255, (String)"structure_block", (new BlockStructure()).setBlockUnbreakable().setResistance(6000000.0F).setTranslationKey("structureBlock"));
      REGISTRY.validateKey();
      Iterator var15 = REGISTRY.iterator();

      while(true) {
         while(var15.hasNext()) {
            Block block15 = (Block)var15.next();
            if (block15.material == Material.AIR) {
               block15.useNeighborBrightness = false;
            } else {
               boolean flag = false;
               boolean flag1 = block15 instanceof BlockStairs;
               boolean flag2 = block15 instanceof BlockSlab;
               boolean flag3 = block15 == block6 || block15 == block14;
               boolean flag4 = block15.translucent;
               boolean flag5 = block15.lightOpacity == 0;
               if (flag1 || flag2 || flag3 || flag4 || flag5) {
                  flag = true;
               }

               block15.useNeighborBrightness = flag;
            }
         }

         Set<Block> set = Sets.newHashSet((Object[])((Block)REGISTRY.getObject(new ResourceLocation("tripwire"))));
         Iterator var24 = REGISTRY.iterator();

         while(true) {
            while(var24.hasNext()) {
               Block block16 = (Block)var24.next();
               if (set.contains(block16)) {
                  for(int i = 0; i < 15; ++i) {
                     int j = REGISTRY.getIDForObject(block16) << 4 | i;
                     BLOCK_STATE_IDS.put(block16.getStateFromMeta(i), j);
                  }
               } else {
                  UnmodifiableIterator unmodifiableiterator = block16.getBlockState().getValidStates().iterator();

                  while(unmodifiableiterator.hasNext()) {
                     IBlockState iblockstate = (IBlockState)unmodifiableiterator.next();
                     int k = REGISTRY.getIDForObject(block16) << 4 | block16.getMetaFromState(iblockstate);
                     BLOCK_STATE_IDS.put(iblockstate, k);
                  }
               }
            }

            return;
         }
      }
   }

   private static void registerBlock(int id, ResourceLocation textualID, Block block_) {
      REGISTRY.register(id, textualID, block_);
   }

   private static void registerBlock(int id, String textualID, Block block_) {
      registerBlock(id, new ResourceLocation(textualID), block_);
   }

   static {
      REGISTRY = new RegistryNamespacedDefaultedByKey(AIR_ID);
      BLOCK_STATE_IDS = new ObjectIntIdentityMap();
      FULL_BLOCK_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
      NULL_AABB = null;
   }

   public static enum EnumOffsetType {
      NONE,
      XZ,
      XYZ;

      private EnumOffsetType() {
      }
   }
}
