package net.minecraft.client.renderer.block.model.multipart;

import com.google.common.base.Predicate;
import javax.annotation.Nullable;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;

public interface ICondition {
   ICondition TRUE = new ICondition() {
      public Predicate<IBlockState> getPredicate(BlockStateContainer blockState) {
         return new Predicate<IBlockState>() {
            public boolean apply(@Nullable IBlockState p_apply_1_) {
               return true;
            }

            // $FF: synthetic method
            // $FF: bridge method
            public boolean apply(@Nullable Object var1) {
               return this.apply((IBlockState)var1);
            }
         };
      }
   };
   ICondition FALSE = new ICondition() {
      public Predicate<IBlockState> getPredicate(BlockStateContainer blockState) {
         return new Predicate<IBlockState>() {
            public boolean apply(@Nullable IBlockState p_apply_1_) {
               return false;
            }

            // $FF: synthetic method
            // $FF: bridge method
            public boolean apply(@Nullable Object var1) {
               return this.apply((IBlockState)var1);
            }
         };
      }
   };

   Predicate<IBlockState> getPredicate(BlockStateContainer var1);
}
