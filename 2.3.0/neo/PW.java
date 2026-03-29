package neo;

import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;

public class PW extends OL {
   private static final ES MINECART_DISPENSER_BEHAVIOR = new EP() {
      private final EP behaviourDefaultDispenseItem = new EP();

      public Qy dispenseStack(ET source, Qy stack) {
         EnumFacing enumfacing = (EnumFacing)source.getBlockState().getValue(dk.FACING);
         bij world = source.getWorld();
         double d0 = source.getX() + (double)enumfacing.getXOffset() * 1.125;
         double d1 = Math.floor(source.getY()) + (double)enumfacing.getYOffset();
         double d2 = source.getZ() + (double)enumfacing.getZOffset() * 1.125;
         BlockPos blockpos = source.getBlockPos().offset(enumfacing);
         in iblockstate = world.getBlockState(blockpos);
         fI blockrailbase$enumraildirection = iblockstate.getBlock() instanceof fK ? (fI)iblockstate.getValue(((fK)iblockstate.getBlock()).getShapeProperty()) : fI.NORTH_SOUTH;
         double d3;
         if (fK.isRailBlock(iblockstate)) {
            if (blockrailbase$enumraildirection.isAscending()) {
               d3 = 0.6;
            } else {
               d3 = 0.1;
            }
         } else {
            if (iblockstate.getMaterial() != hM.AIR || !fK.isRailBlock(world.getBlockState(blockpos.down()))) {
               return this.behaviourDefaultDispenseItem.dispense(source, stack);
            }

            in iblockstate1 = world.getBlockState(blockpos.down());
            fI blockrailbase$enumraildirection1 = iblockstate1.getBlock() instanceof fK ? (fI)iblockstate1.getValue(((fK)iblockstate1.getBlock()).getShapeProperty()) : fI.NORTH_SOUTH;
            if (enumfacing != EnumFacing.DOWN && blockrailbase$enumraildirection1.isAscending()) {
               d3 = -0.4;
            } else {
               d3 = -0.9;
            }
         }

         Jc entityminecart = Jc.create(world, d0, d1 + d3, d2, ((PW)stack.getItem()).minecartType);
         if (stack.hasDisplayName()) {
            entityminecart.setCustomNameTag(stack.getDisplayName());
         }

         world.spawnEntity(entityminecart);
         stack.shrink(1);
         return stack;
      }

      protected void playDispenseSound(ET source) {
         source.getWorld().playEvent(1000, source.getBlockPos(), 0);
      }
   };
   private final Jb minecartType;

   public PW(Jb typeIn) {
      this.maxStackSize = 1;
      this.minecartType = typeIn;
      this.setCreativeTab(EN.TRANSPORTATION);
      dk.DISPENSE_BEHAVIOR_REGISTRY.putObject(this, MINECART_DISPENSER_BEHAVIOR);
   }

   public EnumActionResult onItemUse(ME player, bij worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      in iblockstate = worldIn.getBlockState(pos);
      if (!fK.isRailBlock(iblockstate)) {
         return EnumActionResult.FAIL;
      } else {
         Qy itemstack = player.getHeldItem(hand);
         if (!worldIn.isRemote) {
            fI blockrailbase$enumraildirection = iblockstate.getBlock() instanceof fK ? (fI)iblockstate.getValue(((fK)iblockstate.getBlock()).getShapeProperty()) : fI.NORTH_SOUTH;
            double d0 = 0.0;
            if (blockrailbase$enumraildirection.isAscending()) {
               d0 = 0.5;
            }

            Jc entityminecart = Jc.create(worldIn, (double)pos.getX() + 0.5, (double)pos.getY() + 0.0625 + d0, (double)pos.getZ() + 0.5, this.minecartType);
            if (itemstack.hasDisplayName()) {
               entityminecart.setCustomNameTag(itemstack.getDisplayName());
            }

            worldIn.spawnEntity(entityminecart);
         }

         itemstack.shrink(1);
         return EnumActionResult.SUCCESS;
      }
   }
}
