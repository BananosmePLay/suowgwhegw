package neo;

import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.NonNullList;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;

public class gr extends dd {
   public static final hX<EnumFacing> FACING = hW.create("facing");
   private final Om color;

   public gr(Om colorIn) {
      super(hM.ROCK, hK.AIR);
      this.color = colorIn;
      this.setCreativeTab(EN.DECORATIONS);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.UP));
   }

   public Yg createNewTileEntity(bij worldIn, int meta) {
      return new YN(this.color);
   }

   /** @deprecated */
   public boolean isOpaqueCube(in state) {
      return false;
   }

   /** @deprecated */
   public boolean causesSuffocation(in state) {
      return true;
   }

   /** @deprecated */
   public boolean isFullCube(in state) {
      return false;
   }

   /** @deprecated */
   public boolean hasCustomBreakingProgress(in state) {
      return true;
   }

   /** @deprecated */
   public EnumBlockRenderType getRenderType(in state) {
      return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
   }

   public boolean onBlockActivated(bij worldIn, BlockPos pos, in state, ME playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      if (worldIn.isRemote) {
         return true;
      } else if (playerIn.isSpectator()) {
         return true;
      } else {
         Yg tileentity = worldIn.getTileEntity(pos);
         if (tileentity instanceof YN) {
            EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
            boolean flag;
            if (((YN)tileentity).getAnimationStatus() == YM.CLOSED) {
               AxisAlignedBB axisalignedbb = FULL_BLOCK_AABB.expand((double)(0.5F * (float)enumfacing.getXOffset()), (double)(0.5F * (float)enumfacing.getYOffset()), (double)(0.5F * (float)enumfacing.getZOffset())).contract((double)enumfacing.getXOffset(), (double)enumfacing.getYOffset(), (double)enumfacing.getZOffset());
               flag = !worldIn.collidesWithAnyBlock(axisalignedbb.offset(pos.offset(enumfacing)));
            } else {
               flag = true;
            }

            if (flag) {
               playerIn.addStat(XV.OPEN_SHULKER_BOX);
               playerIn.displayGUIChest((IInventory)tileentity);
            }

            return true;
         } else {
            return false;
         }
      }
   }

   public in getStateForPlacement(bij worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, Iw placer) {
      return this.getDefaultState().withProperty(FACING, facing);
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{FACING});
   }

   public int getMetaFromState(in state) {
      return ((EnumFacing)state.getValue(FACING)).getIndex();
   }

   public in getStateFromMeta(int meta) {
      EnumFacing enumfacing = EnumFacing.byIndex(meta);
      return this.getDefaultState().withProperty(FACING, enumfacing);
   }

   public void onBlockHarvested(bij worldIn, BlockPos pos, in state, ME player) {
      if (worldIn.getTileEntity(pos) instanceof YN) {
         YN tileentityshulkerbox = (YN)worldIn.getTileEntity(pos);
         tileentityshulkerbox.setDestroyedByCreativePlayer(player.capabilities.isCreativeMode);
         tileentityshulkerbox.fillWithLoot(player);
      }

   }

   public void dropBlockAsItemWithChance(bij worldIn, BlockPos pos, in state, float chance, int fortune) {
   }

   public void onBlockPlacedBy(bij worldIn, BlockPos pos, in state, Iw placer, Qy stack) {
      if (stack.hasDisplayName()) {
         Yg tileentity = worldIn.getTileEntity(pos);
         if (tileentity instanceof YN) {
            ((YN)tileentity).setCustomName(stack.getDisplayName());
         }
      }

   }

   public void breakBlock(bij worldIn, BlockPos pos, in state) {
      Yg tileentity = worldIn.getTileEntity(pos);
      if (tileentity instanceof YN) {
         YN tileentityshulkerbox = (YN)tileentity;
         if (!tileentityshulkerbox.isCleared() && tileentityshulkerbox.shouldDrop()) {
            Qy itemstack = new Qy(OL.getItemFromBlock(this));
            QQ nbttagcompound = new QQ();
            QQ nbttagcompound1 = new QQ();
            nbttagcompound.setTag("BlockEntityTag", ((YN)tileentity).saveToNbt(nbttagcompound1));
            itemstack.setTagCompound(nbttagcompound);
            if (tileentityshulkerbox.hasCustomName()) {
               itemstack.setStackDisplayName(tileentityshulkerbox.getName());
               tileentityshulkerbox.setCustomName("");
            }

            spawnAsEntity(worldIn, pos, itemstack);
         }

         worldIn.updateComparatorOutputLevel(pos, state.getBlock());
      }

      super.breakBlock(worldIn, pos, state);
   }

   public void addInformation(Qy stack, @Nullable bij worldIn, List<String> tooltip, BJ flagIn) {
      super.addInformation(stack, worldIn, tooltip, flagIn);
      QQ nbttagcompound = stack.getTagCompound();
      if (nbttagcompound != null && nbttagcompound.hasKey("BlockEntityTag", 10)) {
         QQ nbttagcompound1 = nbttagcompound.getCompoundTag("BlockEntityTag");
         if (nbttagcompound1.hasKey("LootTable", 8)) {
            tooltip.add("???????");
         }

         if (nbttagcompound1.hasKey("Items", 9)) {
            NonNullList<Qy> nonnulllist = NonNullList.withSize(27, Qy.EMPTY);
            ItemStackHelper.loadAllItems(nbttagcompound1, nonnulllist);
            int i = 0;
            int j = 0;
            Iterator var10 = nonnulllist.iterator();

            while(var10.hasNext()) {
               Qy itemstack = (Qy)var10.next();
               if (!itemstack.isEmpty()) {
                  ++j;
                  if (i <= 4) {
                     ++i;
                     tooltip.add(String.format("%s x%d", itemstack.getDisplayName(), itemstack.getCount()));
                  }
               }
            }

            if (j - i > 0) {
               tooltip.add(String.format(TextFormatting.ITALIC + I18n.translateToLocal("container.shulkerBox.more"), j - i));
            }
         }
      }

   }

   /** @deprecated */
   public hJ getPushReaction(in state) {
      return hJ.DESTROY;
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      Yg tileentity = source.getTileEntity(pos);
      return tileentity instanceof YN ? ((YN)tileentity).getBoundingBox(state) : FULL_BLOCK_AABB;
   }

   /** @deprecated */
   public boolean hasComparatorInputOverride(in state) {
      return true;
   }

   /** @deprecated */
   public int getComparatorInputOverride(in blockState, bij worldIn, BlockPos pos) {
      return Container.calcRedstoneFromInventory((IInventory)worldIn.getTileEntity(pos));
   }

   public Qy getItem(bij worldIn, BlockPos pos, in state) {
      Qy itemstack = super.getItem(worldIn, pos, state);
      YN tileentityshulkerbox = (YN)worldIn.getTileEntity(pos);
      QQ nbttagcompound = tileentityshulkerbox.saveToNbt(new QQ());
      if (!nbttagcompound.isEmpty()) {
         itemstack.setTagInfo("BlockEntityTag", nbttagcompound);
      }

      return itemstack;
   }

   public static Om getColorFromItem(OL itemIn) {
      return getColorFromBlock(co.getBlockFromItem(itemIn));
   }

   public static Om getColorFromBlock(co blockIn) {
      return blockIn instanceof gr ? ((gr)blockIn).getColor() : Om.PURPLE;
   }

   public static co getBlockByColor(Om colorIn) {
      switch (colorIn) {
         case WHITE:
            return Nk.WHITE_SHULKER_BOX;
         case ORANGE:
            return Nk.ORANGE_SHULKER_BOX;
         case MAGENTA:
            return Nk.MAGENTA_SHULKER_BOX;
         case LIGHT_BLUE:
            return Nk.LIGHT_BLUE_SHULKER_BOX;
         case YELLOW:
            return Nk.YELLOW_SHULKER_BOX;
         case LIME:
            return Nk.LIME_SHULKER_BOX;
         case PINK:
            return Nk.PINK_SHULKER_BOX;
         case GRAY:
            return Nk.GRAY_SHULKER_BOX;
         case SILVER:
            return Nk.SILVER_SHULKER_BOX;
         case CYAN:
            return Nk.CYAN_SHULKER_BOX;
         case PURPLE:
         default:
            return Nk.PURPLE_SHULKER_BOX;
         case BLUE:
            return Nk.BLUE_SHULKER_BOX;
         case BROWN:
            return Nk.BROWN_SHULKER_BOX;
         case GREEN:
            return Nk.GREEN_SHULKER_BOX;
         case RED:
            return Nk.RED_SHULKER_BOX;
         case BLACK:
            return Nk.BLACK_SHULKER_BOX;
      }
   }

   public Om getColor() {
      return this.color;
   }

   public static Qy getColoredItemStack(Om colorIn) {
      return new Qy(getBlockByColor(colorIn));
   }

   /** @deprecated */
   public in withRotation(in state, Rotation rot) {
      return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
   }

   /** @deprecated */
   public in withMirror(in state, Mirror mirrorIn) {
      return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      state = this.getActualState(state, worldIn, pos);
      EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
      YM tileentityshulkerbox$animationstatus = ((YN)worldIn.getTileEntity(pos)).getAnimationStatus();
      return tileentityshulkerbox$animationstatus == YM.CLOSED || tileentityshulkerbox$animationstatus == YM.OPENED && (enumfacing == face.getOpposite() || enumfacing == face) ? ib.SOLID : ib.UNDEFINED;
   }
}
