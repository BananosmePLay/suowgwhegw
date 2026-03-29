package neo;

import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class cR extends co {
   public static final hZ LEVEL = hZ.create("level", 0, 3);
   protected static final AxisAlignedBB AABB_LEGS = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.3125, 1.0);
   protected static final AxisAlignedBB AABB_WALL_NORTH = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 0.125);
   protected static final AxisAlignedBB AABB_WALL_SOUTH = new AxisAlignedBB(0.0, 0.0, 0.875, 1.0, 1.0, 1.0);
   protected static final AxisAlignedBB AABB_WALL_EAST = new AxisAlignedBB(0.875, 0.0, 0.0, 1.0, 1.0, 1.0);
   protected static final AxisAlignedBB AABB_WALL_WEST = new AxisAlignedBB(0.0, 0.0, 0.0, 0.125, 1.0, 1.0);

   public cR() {
      super(hM.IRON, hK.STONE);
      this.setDefaultState(this.blockState.getBaseState().withProperty(LEVEL, 0));
   }

   public void addCollisionBoxToList(in state, bij worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Ig entityIn, boolean isActualState) {
      addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_LEGS);
      addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_WEST);
      addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_NORTH);
      addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_EAST);
      addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_SOUTH);
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      return FULL_BLOCK_AABB;
   }

   /** @deprecated */
   public boolean isOpaqueCube(in state) {
      return false;
   }

   /** @deprecated */
   public boolean isFullCube(in state) {
      return false;
   }

   public void onEntityCollision(bij worldIn, BlockPos pos, in state, Ig entityIn) {
      int i = (Integer)state.getValue(LEVEL);
      float f = (float)pos.getY() + (6.0F + (float)(3 * i)) / 16.0F;
      if (!worldIn.isRemote && entityIn.isBurning() && i > 0 && entityIn.getEntityBoundingBox().minY <= (double)f) {
         entityIn.extinguish();
         this.setWaterLevel(worldIn, pos, state, i - 1);
      }

   }

   public boolean onBlockActivated(bij worldIn, BlockPos pos, in state, ME playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      Qy itemstack = playerIn.getHeldItem(hand);
      if (itemstack.isEmpty()) {
         return true;
      } else {
         int i = (Integer)state.getValue(LEVEL);
         OL item = itemstack.getItem();
         if (item == NK.WATER_BUCKET) {
            if (i < 3 && !worldIn.isRemote) {
               if (!playerIn.capabilities.isCreativeMode) {
                  playerIn.setHeldItem(hand, new Qy(NK.BUCKET));
               }

               playerIn.addStat(XV.CAULDRON_FILLED);
               this.setWaterLevel(worldIn, pos, state, 3);
               worldIn.playSound((ME)null, pos, NO.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
            }

            return true;
         } else if (item == NK.BUCKET) {
            if (i == 3 && !worldIn.isRemote) {
               if (!playerIn.capabilities.isCreativeMode) {
                  itemstack.shrink(1);
                  if (itemstack.isEmpty()) {
                     playerIn.setHeldItem(hand, new Qy(NK.WATER_BUCKET));
                  } else if (!playerIn.inventory.addItemStackToInventory(new Qy(NK.WATER_BUCKET))) {
                     playerIn.dropItem(new Qy(NK.WATER_BUCKET), false);
                  }
               }

               playerIn.addStat(XV.CAULDRON_USED);
               this.setWaterLevel(worldIn, pos, state, 0);
               worldIn.playSound((ME)null, pos, NO.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
            }

            return true;
         } else {
            Qy itemstack1;
            if (item == NK.GLASS_BOTTLE) {
               if (i > 0 && !worldIn.isRemote) {
                  if (!playerIn.capabilities.isCreativeMode) {
                     itemstack1 = Wg.addPotionToItemStack(new Qy(NK.POTIONITEM), NN.WATER);
                     playerIn.addStat(XV.CAULDRON_USED);
                     itemstack.shrink(1);
                     if (itemstack.isEmpty()) {
                        playerIn.setHeldItem(hand, itemstack1);
                     } else if (!playerIn.inventory.addItemStackToInventory(itemstack1)) {
                        playerIn.dropItem(itemstack1, false);
                     } else if (playerIn instanceof MG) {
                        ((MG)playerIn).sendContainerToPlayer(playerIn.inventoryContainer);
                     }
                  }

                  worldIn.playSound((ME)null, pos, NO.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                  this.setWaterLevel(worldIn, pos, state, i - 1);
               }

               return true;
            } else if (item == NK.POTIONITEM && Wg.getPotionFromItem(itemstack) == NN.WATER) {
               if (i < 3 && !worldIn.isRemote) {
                  if (!playerIn.capabilities.isCreativeMode) {
                     itemstack1 = new Qy(NK.GLASS_BOTTLE);
                     playerIn.addStat(XV.CAULDRON_USED);
                     playerIn.setHeldItem(hand, itemstack1);
                     if (playerIn instanceof MG) {
                        ((MG)playerIn).sendContainerToPlayer(playerIn.inventoryContainer);
                     }
                  }

                  worldIn.playSound((ME)null, pos, NO.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                  this.setWaterLevel(worldIn, pos, state, i + 1);
               }

               return true;
            } else {
               if (i > 0 && item instanceof OR) {
                  OR itemarmor = (OR)item;
                  if (itemarmor.getArmorMaterial() == OQ.LEATHER && itemarmor.hasColor(itemstack) && !worldIn.isRemote) {
                     itemarmor.removeColor(itemstack);
                     this.setWaterLevel(worldIn, pos, state, i - 1);
                     playerIn.addStat(XV.ARMOR_CLEANED);
                     return true;
                  }
               }

               if (i > 0 && item instanceof OV) {
                  if (Yh.getPatterns(itemstack) > 0 && !worldIn.isRemote) {
                     itemstack1 = itemstack.copy();
                     itemstack1.setCount(1);
                     Yh.removeBannerData(itemstack1);
                     playerIn.addStat(XV.BANNER_CLEANED);
                     if (!playerIn.capabilities.isCreativeMode) {
                        itemstack.shrink(1);
                        this.setWaterLevel(worldIn, pos, state, i - 1);
                     }

                     if (itemstack.isEmpty()) {
                        playerIn.setHeldItem(hand, itemstack1);
                     } else if (!playerIn.inventory.addItemStackToInventory(itemstack1)) {
                        playerIn.dropItem(itemstack1, false);
                     } else if (playerIn instanceof MG) {
                        ((MG)playerIn).sendContainerToPlayer(playerIn.inventoryContainer);
                     }
                  }

                  return true;
               } else {
                  return false;
               }
            }
         }
      }
   }

   public void setWaterLevel(bij worldIn, BlockPos pos, in state, int level) {
      worldIn.setBlockState(pos, state.withProperty(LEVEL, MathHelper.clamp(level, 0, 3)), 2);
      worldIn.updateComparatorOutputLevel(pos, this);
   }

   public void fillWithRain(bij worldIn, BlockPos pos) {
      if (worldIn.rand.nextInt(20) == 1) {
         float f = worldIn.getBiome(pos).getTemperature(pos);
         if (worldIn.getBiomeProvider().getTemperatureAtHeight(f, pos.getY()) >= 0.15F) {
            in iblockstate = worldIn.getBlockState(pos);
            if ((Integer)iblockstate.getValue(LEVEL) < 3) {
               worldIn.setBlockState(pos, iblockstate.cycleProperty(LEVEL), 2);
            }
         }
      }

   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return NK.CAULDRON;
   }

   public Qy getItem(bij worldIn, BlockPos pos, in state) {
      return new Qy(NK.CAULDRON);
   }

   /** @deprecated */
   public boolean hasComparatorInputOverride(in state) {
      return true;
   }

   /** @deprecated */
   public int getComparatorInputOverride(in blockState, bij worldIn, BlockPos pos) {
      return (Integer)blockState.getValue(LEVEL);
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(LEVEL, meta);
   }

   public int getMetaFromState(in state) {
      return (Integer)state.getValue(LEVEL);
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{LEVEL});
   }

   public boolean isPassable(bfZ worldIn, BlockPos pos) {
      return true;
   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      if (face == EnumFacing.UP) {
         return ib.BOWL;
      } else {
         return face == EnumFacing.DOWN ? ib.UNDEFINED : ib.SOLID;
      }
   }
}
