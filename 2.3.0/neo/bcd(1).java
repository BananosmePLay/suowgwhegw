package neo;

import java.util.Iterator;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class bcd extends bbE {
   private boolean crystalInvulnerable;
   private bcc spike;
   private BlockPos beamTarget;

   public bcd() {
   }

   public void setSpike(bcc p_186143_1_) {
      this.spike = p_186143_1_;
   }

   public void setCrystalInvulnerable(boolean p_186144_1_) {
      this.crystalInvulnerable = p_186144_1_;
   }

   public boolean generate(bij worldIn, Random rand, BlockPos position) {
      if (this.spike == null) {
         throw new IllegalStateException("Decoration requires priming with a spike");
      } else {
         int i = this.spike.getRadius();
         Iterator var5 = BlockPos.getAllInBoxMutable(new BlockPos(position.getX() - i, 0, position.getZ() - i), new BlockPos(position.getX() + i, this.spike.getHeight() + 10, position.getZ() + i)).iterator();

         while(true) {
            while(var5.hasNext()) {
               BlockPos.MutableBlockPos blockpos$mutableblockpos = (BlockPos.MutableBlockPos)var5.next();
               if (blockpos$mutableblockpos.distanceSq((double)position.getX(), (double)blockpos$mutableblockpos.getY(), (double)position.getZ()) <= (double)(i * i + 1) && blockpos$mutableblockpos.getY() < this.spike.getHeight()) {
                  this.setBlockAndNotifyAdequately(worldIn, blockpos$mutableblockpos, Nk.OBSIDIAN.getDefaultState());
               } else if (blockpos$mutableblockpos.getY() > 65) {
                  this.setBlockAndNotifyAdequately(worldIn, blockpos$mutableblockpos, Nk.AIR.getDefaultState());
               }
            }

            if (this.spike.isGuarded()) {
               for(int j = -2; j <= 2; ++j) {
                  for(int k = -2; k <= 2; ++k) {
                     if (MathHelper.abs(j) == 2 || MathHelper.abs(k) == 2) {
                        this.setBlockAndNotifyAdequately(worldIn, new BlockPos(position.getX() + j, this.spike.getHeight(), position.getZ() + k), Nk.IRON_BARS.getDefaultState());
                        this.setBlockAndNotifyAdequately(worldIn, new BlockPos(position.getX() + j, this.spike.getHeight() + 1, position.getZ() + k), Nk.IRON_BARS.getDefaultState());
                        this.setBlockAndNotifyAdequately(worldIn, new BlockPos(position.getX() + j, this.spike.getHeight() + 2, position.getZ() + k), Nk.IRON_BARS.getDefaultState());
                     }

                     this.setBlockAndNotifyAdequately(worldIn, new BlockPos(position.getX() + j, this.spike.getHeight() + 3, position.getZ() + k), Nk.IRON_BARS.getDefaultState());
                  }
               }
            }

            IS entityendercrystal = new IS(worldIn);
            entityendercrystal.setBeamTarget(this.beamTarget);
            entityendercrystal.setEntityInvulnerable(this.crystalInvulnerable);
            entityendercrystal.setLocationAndAngles((double)((float)position.getX() + 0.5F), (double)(this.spike.getHeight() + 1), (double)((float)position.getZ() + 0.5F), rand.nextFloat() * 360.0F, 0.0F);
            worldIn.spawnEntity(entityendercrystal);
            this.setBlockAndNotifyAdequately(worldIn, new BlockPos(position.getX(), this.spike.getHeight(), position.getZ()), Nk.BEDROCK.getDefaultState());
            return true;
         }
      }
   }

   public void setBeamTarget(@Nullable BlockPos pos) {
      this.beamTarget = pos;
   }
}
