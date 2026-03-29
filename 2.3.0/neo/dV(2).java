package neo;

import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;

public class dV extends dd {
   public static final hZ LEGACY_DATA = hZ.create("legacy_data", 0, 15);
   public static final hX<dU> CONTENTS = hX.create("contents", dU.class);
   protected static final AxisAlignedBB FLOWER_POT_AABB = new AxisAlignedBB(0.3125, 0.0, 0.3125, 0.6875, 0.375, 0.6875);

   public dV() {
      super(hM.CIRCUITS);
      this.setDefaultState(this.blockState.getBaseState().withProperty(CONTENTS, dU.EMPTY).withProperty(LEGACY_DATA, 0));
   }

   public String getLocalizedName() {
      return I18n.translateToLocal("item.flowerPot.name");
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      return FLOWER_POT_AABB;
   }

   /** @deprecated */
   public boolean isOpaqueCube(in state) {
      return false;
   }

   /** @deprecated */
   public EnumBlockRenderType getRenderType(in state) {
      return EnumBlockRenderType.MODEL;
   }

   /** @deprecated */
   public boolean isFullCube(in state) {
      return false;
   }

   public boolean onBlockActivated(bij worldIn, BlockPos pos, in state, ME playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      Qy itemstack = playerIn.getHeldItem(hand);
      Yz tileentityflowerpot = this.getTileEntity(worldIn, pos);
      if (tileentityflowerpot == null) {
         return false;
      } else {
         Qy itemstack1 = tileentityflowerpot.getFlowerItemStack();
         if (itemstack1.isEmpty()) {
            if (!this.canBePotted(itemstack)) {
               return false;
            }

            tileentityflowerpot.setItemStack(itemstack);
            playerIn.addStat(XV.FLOWER_POTTED);
            if (!playerIn.capabilities.isCreativeMode) {
               itemstack.shrink(1);
            }
         } else {
            if (itemstack.isEmpty()) {
               playerIn.setHeldItem(hand, itemstack1);
            } else if (!playerIn.addItemStackToInventory(itemstack1)) {
               playerIn.dropItem(itemstack1, false);
            }

            tileentityflowerpot.setItemStack(Qy.EMPTY);
         }

         tileentityflowerpot.markDirty();
         worldIn.notifyBlockUpdate(pos, state, state, 3);
         return true;
      }
   }

   private boolean canBePotted(Qy stack) {
      co block = co.getBlockFromItem(stack.getItem());
      if (block != Nk.YELLOW_FLOWER && block != Nk.RED_FLOWER && block != Nk.CACTUS && block != Nk.BROWN_MUSHROOM && block != Nk.RED_MUSHROOM && block != Nk.SAPLING && block != Nk.DEADBUSH) {
         int i = stack.getMetadata();
         return block == Nk.TALLGRASS && i == hj.FERN.getMeta();
      } else {
         return true;
      }
   }

   public Qy getItem(bij worldIn, BlockPos pos, in state) {
      Yz tileentityflowerpot = this.getTileEntity(worldIn, pos);
      if (tileentityflowerpot != null) {
         Qy itemstack = tileentityflowerpot.getFlowerItemStack();
         if (!itemstack.isEmpty()) {
            return itemstack;
         }
      }

      return new Qy(NK.FLOWER_POT);
   }

   public boolean canPlaceBlockAt(bij worldIn, BlockPos pos) {
      return super.canPlaceBlockAt(worldIn, pos) && worldIn.getBlockState(pos.down()).isTopSolid();
   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      if (!worldIn.getBlockState(pos.down()).isTopSolid()) {
         this.dropBlockAsItem(worldIn, pos, state, 0);
         worldIn.setBlockToAir(pos);
      }

   }

   public void breakBlock(bij worldIn, BlockPos pos, in state) {
      Yz tileentityflowerpot = this.getTileEntity(worldIn, pos);
      if (tileentityflowerpot != null && tileentityflowerpot.getFlowerPotItem() != null) {
         spawnAsEntity(worldIn, pos, new Qy(tileentityflowerpot.getFlowerPotItem(), 1, tileentityflowerpot.getFlowerPotData()));
      }

      super.breakBlock(worldIn, pos, state);
   }

   public void onBlockHarvested(bij worldIn, BlockPos pos, in state, ME player) {
      super.onBlockHarvested(worldIn, pos, state, player);
      if (player.capabilities.isCreativeMode) {
         Yz tileentityflowerpot = this.getTileEntity(worldIn, pos);
         if (tileentityflowerpot != null) {
            tileentityflowerpot.setItemStack(Qy.EMPTY);
         }
      }

   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return NK.FLOWER_POT;
   }

   @Nullable
   private Yz getTileEntity(bij worldIn, BlockPos pos) {
      Yg tileentity = worldIn.getTileEntity(pos);
      return tileentity instanceof Yz ? (Yz)tileentity : null;
   }

   public Yg createNewTileEntity(bij worldIn, int meta) {
      co block = null;
      int i = 0;
      switch (meta) {
         case 1:
            block = Nk.RED_FLOWER;
            i = dR.POPPY.getMeta();
            break;
         case 2:
            block = Nk.YELLOW_FLOWER;
            break;
         case 3:
            block = Nk.SAPLING;
            i = fk.OAK.getMetadata();
            break;
         case 4:
            block = Nk.SAPLING;
            i = fk.SPRUCE.getMetadata();
            break;
         case 5:
            block = Nk.SAPLING;
            i = fk.BIRCH.getMetadata();
            break;
         case 6:
            block = Nk.SAPLING;
            i = fk.JUNGLE.getMetadata();
            break;
         case 7:
            block = Nk.RED_MUSHROOM;
            break;
         case 8:
            block = Nk.BROWN_MUSHROOM;
            break;
         case 9:
            block = Nk.CACTUS;
            break;
         case 10:
            block = Nk.DEADBUSH;
            break;
         case 11:
            block = Nk.TALLGRASS;
            i = hj.FERN.getMeta();
            break;
         case 12:
            block = Nk.SAPLING;
            i = fk.ACACIA.getMetadata();
            break;
         case 13:
            block = Nk.SAPLING;
            i = fk.DARK_OAK.getMetadata();
      }

      return new Yz(OL.getItemFromBlock((co)block), i);
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{CONTENTS, LEGACY_DATA});
   }

   public int getMetaFromState(in state) {
      return (Integer)state.getValue(LEGACY_DATA);
   }

   public in getActualState(in state, bfZ worldIn, BlockPos pos) {
      dU blockflowerpot$enumflowertype = dU.EMPTY;
      Yg tileentity = worldIn instanceof baI ? ((baI)worldIn).getTileEntity(pos, bal.CHECK) : worldIn.getTileEntity(pos);
      if (tileentity instanceof Yz) {
         Yz tileentityflowerpot = (Yz)tileentity;
         OL item = tileentityflowerpot.getFlowerPotItem();
         if (item instanceof OX) {
            int i = tileentityflowerpot.getFlowerPotData();
            co block = co.getBlockFromItem(item);
            if (block == Nk.SAPLING) {
               switch (fk.byMetadata(i)) {
                  case OAK:
                     blockflowerpot$enumflowertype = dU.OAK_SAPLING;
                     break;
                  case SPRUCE:
                     blockflowerpot$enumflowertype = dU.SPRUCE_SAPLING;
                     break;
                  case BIRCH:
                     blockflowerpot$enumflowertype = dU.BIRCH_SAPLING;
                     break;
                  case JUNGLE:
                     blockflowerpot$enumflowertype = dU.JUNGLE_SAPLING;
                     break;
                  case ACACIA:
                     blockflowerpot$enumflowertype = dU.ACACIA_SAPLING;
                     break;
                  case DARK_OAK:
                     blockflowerpot$enumflowertype = dU.DARK_OAK_SAPLING;
                     break;
                  default:
                     blockflowerpot$enumflowertype = dU.EMPTY;
               }
            } else if (block == Nk.TALLGRASS) {
               switch (i) {
                  case 0:
                     blockflowerpot$enumflowertype = dU.DEAD_BUSH;
                     break;
                  case 2:
                     blockflowerpot$enumflowertype = dU.FERN;
                     break;
                  default:
                     blockflowerpot$enumflowertype = dU.EMPTY;
               }
            } else if (block == Nk.YELLOW_FLOWER) {
               blockflowerpot$enumflowertype = dU.DANDELION;
            } else if (block == Nk.RED_FLOWER) {
               switch (dR.getType(dP.RED, i)) {
                  case POPPY:
                     blockflowerpot$enumflowertype = dU.POPPY;
                     break;
                  case BLUE_ORCHID:
                     blockflowerpot$enumflowertype = dU.BLUE_ORCHID;
                     break;
                  case ALLIUM:
                     blockflowerpot$enumflowertype = dU.ALLIUM;
                     break;
                  case HOUSTONIA:
                     blockflowerpot$enumflowertype = dU.HOUSTONIA;
                     break;
                  case RED_TULIP:
                     blockflowerpot$enumflowertype = dU.RED_TULIP;
                     break;
                  case ORANGE_TULIP:
                     blockflowerpot$enumflowertype = dU.ORANGE_TULIP;
                     break;
                  case WHITE_TULIP:
                     blockflowerpot$enumflowertype = dU.WHITE_TULIP;
                     break;
                  case PINK_TULIP:
                     blockflowerpot$enumflowertype = dU.PINK_TULIP;
                     break;
                  case OXEYE_DAISY:
                     blockflowerpot$enumflowertype = dU.OXEYE_DAISY;
                     break;
                  default:
                     blockflowerpot$enumflowertype = dU.EMPTY;
               }
            } else if (block == Nk.RED_MUSHROOM) {
               blockflowerpot$enumflowertype = dU.MUSHROOM_RED;
            } else if (block == Nk.BROWN_MUSHROOM) {
               blockflowerpot$enumflowertype = dU.MUSHROOM_BROWN;
            } else if (block == Nk.DEADBUSH) {
               blockflowerpot$enumflowertype = dU.DEAD_BUSH;
            } else if (block == Nk.CACTUS) {
               blockflowerpot$enumflowertype = dU.CACTUS;
            }
         }
      }

      return state.withProperty(CONTENTS, blockflowerpot$enumflowertype);
   }

   public BlockRenderLayer getRenderLayer() {
      return BlockRenderLayer.CUTOUT;
   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return ib.UNDEFINED;
   }
}
