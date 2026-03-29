package neo;

import java.util.Iterator;
import java.util.Random;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class Fm extends Fa {
   public Fm(EZ rarityIn, EntityEquipmentSlot... slots) {
      super(rarityIn, FS.ARMOR_FEET, slots);
      this.setName("frostWalker");
   }

   public int getMinEnchantability(int enchantmentLevel) {
      return enchantmentLevel * 10;
   }

   public int getMaxEnchantability(int enchantmentLevel) {
      return this.getMinEnchantability(enchantmentLevel) + 15;
   }

   public boolean isTreasureEnchantment() {
      return true;
   }

   public int getMaxLevel() {
      return 2;
   }

   public static void freezeNearby(Iw living, bij worldIn, BlockPos pos, int level) {
      if (living.onGround) {
         float f = (float)Math.min(16, 2 + level);
         BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(0, 0, 0);
         Iterator var6 = BlockPos.getAllInBoxMutable(pos.add((double)(-f), -1.0, (double)(-f)), pos.add((double)f, -1.0, (double)f)).iterator();

         while(var6.hasNext()) {
            BlockPos.MutableBlockPos blockpos$mutableblockpos1 = (BlockPos.MutableBlockPos)var6.next();
            if (blockpos$mutableblockpos1.distanceSqToCenter(living.posX, living.posY, living.posZ) <= (double)(f * f)) {
               blockpos$mutableblockpos.setPos(blockpos$mutableblockpos1.getX(), blockpos$mutableblockpos1.getY() + 1, blockpos$mutableblockpos1.getZ());
               in iblockstate = worldIn.getBlockState(blockpos$mutableblockpos);
               if (iblockstate.getMaterial() == hM.AIR) {
                  in iblockstate1 = worldIn.getBlockState(blockpos$mutableblockpos1);
                  if (iblockstate1.getMaterial() == hM.WATER && (Integer)iblockstate1.getValue(eB.LEVEL) == 0 && worldIn.mayPlace(Nk.FROSTED_ICE, blockpos$mutableblockpos1, false, EnumFacing.DOWN, (Ig)null)) {
                     worldIn.setBlockState(blockpos$mutableblockpos1, Nk.FROSTED_ICE.getDefaultState());
                     worldIn.scheduleUpdate(blockpos$mutableblockpos1.toImmutable(), Nk.FROSTED_ICE, MathHelper.getInt((Random)living.getRNG(), 60, 120));
                  }
               }
            }
         }
      }

   }

   public boolean canApplyTogether(Fa ench) {
      return super.canApplyTogether(ench) && ench != NJ.DEPTH_STRIDER;
   }
}
