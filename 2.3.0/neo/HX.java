package neo;

import java.util.List;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class HX extends HY {
   private int lightningState;
   public long boltVertex;
   private int boltLivingTime;
   private final boolean effectOnly;

   public HX(bij worldIn, double x, double y, double z, boolean effectOnlyIn) {
      super(worldIn);
      this.setLocationAndAngles(x, y, z, 0.0F, 0.0F);
      this.lightningState = 2;
      this.boltVertex = this.rand.nextLong();
      this.boltLivingTime = this.rand.nextInt(3) + 1;
      this.effectOnly = effectOnlyIn;
      BlockPos blockpos = new BlockPos(this);
      if (!effectOnlyIn && !worldIn.isRemote && worldIn.getGameRules().getBoolean("doFireTick") && (worldIn.getDifficulty() == baV.NORMAL || worldIn.getDifficulty() == baV.HARD) && worldIn.isAreaLoaded(blockpos, 10)) {
         if (worldIn.getBlockState(blockpos).getMaterial() == hM.AIR && Nk.FIRE.canPlaceBlockAt(worldIn, blockpos)) {
            worldIn.setBlockState(blockpos, Nk.FIRE.getDefaultState());
         }

         for(int i = 0; i < 4; ++i) {
            BlockPos blockpos1 = blockpos.add(this.rand.nextInt(3) - 1, this.rand.nextInt(3) - 1, this.rand.nextInt(3) - 1);
            if (worldIn.getBlockState(blockpos1).getMaterial() == hM.AIR && Nk.FIRE.canPlaceBlockAt(worldIn, blockpos1)) {
               worldIn.setBlockState(blockpos1, Nk.FIRE.getDefaultState());
            }
         }
      }

   }

   public SoundCategory getSoundCategory() {
      return SoundCategory.WEATHER;
   }

   public void onUpdate() {
      super.onUpdate();
      if (this.lightningState == 2) {
         this.world.playSound((ME)null, this.posX, this.posY, this.posZ, NO.ENTITY_LIGHTNING_THUNDER, SoundCategory.WEATHER, 10000.0F, 0.8F + this.rand.nextFloat() * 0.2F);
         this.world.playSound((ME)null, this.posX, this.posY, this.posZ, NO.ENTITY_LIGHTNING_IMPACT, SoundCategory.WEATHER, 2.0F, 0.5F + this.rand.nextFloat() * 0.2F);
      }

      --this.lightningState;
      if (this.lightningState < 0) {
         if (this.boltLivingTime == 0) {
            this.setDead();
         } else if (this.lightningState < -this.rand.nextInt(10)) {
            --this.boltLivingTime;
            this.lightningState = 1;
            if (!this.effectOnly && !this.world.isRemote) {
               this.boltVertex = this.rand.nextLong();
               BlockPos blockpos = new BlockPos(this);
               if (this.world.getGameRules().getBoolean("doFireTick") && this.world.isAreaLoaded(blockpos, 10) && this.world.getBlockState(blockpos).getMaterial() == hM.AIR && Nk.FIRE.canPlaceBlockAt(this.world, blockpos)) {
                  this.world.setBlockState(blockpos, Nk.FIRE.getDefaultState());
               }
            }
         }
      }

      if (this.lightningState >= 0) {
         if (this.world.isRemote) {
            this.world.setLastLightningBolt(2);
         } else if (!this.effectOnly) {
            double d0 = 3.0;
            List<Ig> list = this.world.getEntitiesWithinAABBExcludingEntity(this, new AxisAlignedBB(this.posX - 3.0, this.posY - 3.0, this.posZ - 3.0, this.posX + 3.0, this.posY + 6.0 + 3.0, this.posZ + 3.0));

            for(int i = 0; i < list.size(); ++i) {
               Ig entity = (Ig)list.get(i);
               entity.onStruckByLightning(this);
            }
         }
      }

   }

   protected void entityInit() {
   }

   protected void readEntityFromNBT(QQ compound) {
   }

   protected void writeEntityToNBT(QQ compound) {
   }
}
