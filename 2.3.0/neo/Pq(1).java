package neo;

import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;

public class Pq extends OL {
   public static final int[] DYE_COLORS = new int[]{1973019, 11743532, 3887386, 5320730, 2437522, 8073150, 2651799, 11250603, 4408131, 14188952, 4312372, 14602026, 6719955, 12801229, 15435844, 15790320};

   public Pq() {
      this.setHasSubtypes(true);
      this.setMaxDamage(0);
      this.setCreativeTab(EN.MATERIALS);
   }

   public String getTranslationKey(Qy stack) {
      int i = stack.getMetadata();
      return super.getTranslationKey() + "." + Om.byDyeDamage(i).getTranslationKey();
   }

   public EnumActionResult onItemUse(ME player, bij worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      Qy itemstack = player.getHeldItem(hand);
      if (!player.canPlayerEdit(pos.offset(facing), facing, itemstack)) {
         return EnumActionResult.FAIL;
      } else {
         Om enumdyecolor = Om.byDyeDamage(itemstack.getMetadata());
         if (enumdyecolor == Om.WHITE) {
            if (applyBonemeal(itemstack, worldIn, pos)) {
               if (!worldIn.isRemote) {
                  worldIn.playEvent(2005, pos, 0);
               }

               return EnumActionResult.SUCCESS;
            }
         } else if (enumdyecolor == Om.BROWN) {
            in iblockstate = worldIn.getBlockState(pos);
            co block = iblockstate.getBlock();
            if (block == Nk.LOG && iblockstate.getValue(eZ.VARIANT) == fk.JUNGLE) {
               if (facing == EnumFacing.DOWN || facing == EnumFacing.UP) {
                  return EnumActionResult.FAIL;
               }

               pos = pos.offset(facing);
               if (worldIn.isAirBlock(pos)) {
                  in iblockstate1 = Nk.COCOA.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, 0, player);
                  worldIn.setBlockState(pos, iblockstate1, 10);
                  if (!player.capabilities.isCreativeMode) {
                     itemstack.shrink(1);
                  }

                  return EnumActionResult.SUCCESS;
               }
            }

            return EnumActionResult.FAIL;
         }

         return EnumActionResult.PASS;
      }
   }

   public static boolean applyBonemeal(Qy stack, bij worldIn, BlockPos target) {
      in iblockstate = worldIn.getBlockState(target);
      if (iblockstate.getBlock() instanceof hH) {
         hH igrowable = (hH)iblockstate.getBlock();
         if (igrowable.canGrow(worldIn, target, iblockstate, worldIn.isRemote)) {
            if (!worldIn.isRemote) {
               if (igrowable.canUseBonemeal(worldIn, worldIn.rand, target, iblockstate)) {
                  igrowable.grow(worldIn, worldIn.rand, target, iblockstate);
               }

               stack.shrink(1);
            }

            return true;
         }
      }

      return false;
   }

   public static void spawnBonemealParticles(bij worldIn, BlockPos pos, int amount) {
      if (amount == 0) {
         amount = 15;
      }

      in iblockstate = worldIn.getBlockState(pos);
      if (iblockstate.getMaterial() != hM.AIR) {
         for(int i = 0; i < amount; ++i) {
            double d0 = itemRand.nextGaussian() * 0.02;
            double d1 = itemRand.nextGaussian() * 0.02;
            double d2 = itemRand.nextGaussian() * 0.02;
            worldIn.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, (double)((float)pos.getX() + itemRand.nextFloat()), (double)pos.getY() + (double)itemRand.nextFloat() * iblockstate.getBoundingBox(worldIn, pos).maxY, (double)((float)pos.getZ() + itemRand.nextFloat()), d0, d1, d2);
         }
      }

   }

   public boolean itemInteractionForEntity(Qy stack, ME playerIn, Iw target, EnumHand hand) {
      if (target instanceof Mb) {
         Mb entitysheep = (Mb)target;
         Om enumdyecolor = Om.byDyeDamage(stack.getMetadata());
         if (!entitysheep.getSheared() && entitysheep.getFleeceColor() != enumdyecolor) {
            entitysheep.setFleeceColor(enumdyecolor);
            stack.shrink(1);
         }

         return true;
      } else {
         return false;
      }
   }

   public void getSubItems(EN tab, NonNullList<Qy> items) {
      if (this.isInCreativeTab(tab)) {
         for(int i = 0; i < 16; ++i) {
            items.add(new Qy(this, 1, i));
         }
      }

   }
}
