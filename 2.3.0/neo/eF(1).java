package neo;

import java.util.Random;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;

public class eF extends co {
   public eF() {
      super(hM.ROCK);
      this.setCreativeTab(EN.BUILDING_BLOCKS);
      this.setLightLevel(0.2F);
      this.setTickRandomly(true);
   }

   /** @deprecated */
   public hK getMapColor(in state, bfZ worldIn, BlockPos pos) {
      return hK.NETHERRACK;
   }

   public void onEntityWalk(bij worldIn, BlockPos pos, Ig entityIn) {
      if (!entityIn.isImmuneToFire() && entityIn instanceof Iw && !Ft.hasFrostWalkerEnchantment((Iw)entityIn)) {
         entityIn.attackEntityFrom(DamageSource.HOT_FLOOR, 1.0F);
      }

      super.onEntityWalk(worldIn, pos, entityIn);
   }

   /** @deprecated */
   public int getPackedLightmapCoords(in state, bfZ source, BlockPos pos) {
      return 15728880;
   }

   public void updateTick(bij worldIn, BlockPos pos, in state, Random rand) {
      BlockPos blockpos = pos.up();
      in iblockstate = worldIn.getBlockState(blockpos);
      if (iblockstate.getBlock() == Nk.WATER || iblockstate.getBlock() == Nk.FLOWING_WATER) {
         worldIn.setBlockToAir(blockpos);
         worldIn.playSound((ME)null, pos, NO.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);
         if (worldIn instanceof bis) {
            ((bis)worldIn).spawnParticle(EnumParticleTypes.SMOKE_LARGE, (double)blockpos.getX() + 0.5, (double)blockpos.getY() + 0.25, (double)blockpos.getZ() + 0.5, 8, 0.5, 0.25, 0.5, 0.0);
         }
      }

   }

   /** @deprecated */
   public boolean canEntitySpawn(in state, Ig entityIn) {
      return entityIn.isImmuneToFire();
   }
}
