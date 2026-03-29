package neo;

import com.google.common.base.Predicate;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;

public class ik {
   private final bij world;
   private final BlockPos pos;
   private final boolean forceLoad;
   private in state;
   private Yg tileEntity;
   private boolean tileEntityInitialized;

   public ik(bij worldIn, BlockPos posIn, boolean forceLoadIn) {
      this.world = worldIn;
      this.pos = posIn;
      this.forceLoad = forceLoadIn;
   }

   public in getBlockState() {
      if (this.state == null && (this.forceLoad || this.world.isBlockLoaded(this.pos))) {
         this.state = this.world.getBlockState(this.pos);
      }

      return this.state;
   }

   @Nullable
   public Yg getTileEntity() {
      if (this.tileEntity == null && !this.tileEntityInitialized) {
         this.tileEntity = this.world.getTileEntity(this.pos);
         this.tileEntityInitialized = true;
      }

      return this.tileEntity;
   }

   public BlockPos getPos() {
      return this.pos;
   }

   public static Predicate<ik> hasState(final Predicate<in> predicatesIn) {
      return new Predicate<ik>() {
         public boolean apply(@Nullable ik p_apply_1_) {
            return p_apply_1_ != null && predicatesIn.apply(p_apply_1_.getBlockState());
         }

         // $FF: synthetic method
         // $FF: bridge method
         public boolean apply(@Nullable Object var1) {
            return this.apply((ik)var1);
         }
      };
   }
}
