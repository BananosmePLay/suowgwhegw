package neo;

import java.util.Iterator;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

public class Hp extends Ho {
   public Hp(Ik p_i47413_1_, double p_i47413_2_) {
      super(p_i47413_1_, p_i47413_2_);
   }

   @Nullable
   protected Vec3d getPosition() {
      Vec3d vec3d = null;
      if (this.entity.isInWater() || this.entity.isOverWater()) {
         vec3d = HA.getLandPos(this.entity, 15, 15);
      }

      if (this.entity.getRNG().nextFloat() >= this.probability) {
         vec3d = this.getTreePos();
      }

      return vec3d == null ? super.getPosition() : vec3d;
   }

   @Nullable
   private Vec3d getTreePos() {
      BlockPos blockpos = new BlockPos(this.entity);
      BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
      BlockPos.MutableBlockPos blockpos$mutableblockpos1 = new BlockPos.MutableBlockPos();
      Iterable<BlockPos.MutableBlockPos> iterable = BlockPos.MutableBlockPos.getAllInBoxMutable(MathHelper.floor(this.entity.posX - 3.0), MathHelper.floor(this.entity.posY - 6.0), MathHelper.floor(this.entity.posZ - 3.0), MathHelper.floor(this.entity.posX + 3.0), MathHelper.floor(this.entity.posY + 6.0), MathHelper.floor(this.entity.posZ + 3.0));
      Iterator iterator = iterable.iterator();

      BlockPos blockpos1;
      boolean flag;
      do {
         do {
            if (!iterator.hasNext()) {
               return null;
            }

            blockpos1 = (BlockPos)iterator.next();
         } while(blockpos.equals(blockpos1));

         co block = this.entity.world.getBlockState(blockpos$mutableblockpos1.setPos((Vec3i)blockpos1).move(EnumFacing.DOWN)).getBlock();
         flag = block instanceof ew || block == Nk.LOG || block == Nk.LOG2;
      } while(!flag || !this.entity.world.isAirBlock(blockpos1) || !this.entity.world.isAirBlock(blockpos$mutableblockpos.setPos((Vec3i)blockpos1).move(EnumFacing.UP)));

      return new Vec3d((double)blockpos1.getX(), (double)blockpos1.getY(), (double)blockpos1.getZ());
   }
}
