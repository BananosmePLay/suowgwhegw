package neo;

import com.google.common.base.Predicate;
import javax.annotation.Nullable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public abstract class dS extends cI {
   protected hX<dR> type;

   protected dS() {
      this.setDefaultState(this.blockState.getBaseState().withProperty(this.getTypeProperty(), this.getBlockType() == dP.RED ? dR.POPPY : dR.DANDELION));
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      return super.getBoundingBox(state, source, pos).offset(state.getOffset(source, pos));
   }

   public int damageDropped(in state) {
      return ((dR)state.getValue(this.getTypeProperty())).getMeta();
   }

   public void getSubBlocks(EN itemIn, NonNullList<Qy> items) {
      dR[] var3 = dR.getTypes(this.getBlockType());
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         dR blockflower$enumflowertype = var3[var5];
         items.add(new Qy(this, 1, blockflower$enumflowertype.getMeta()));
      }

   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(this.getTypeProperty(), dR.getType(this.getBlockType(), meta));
   }

   public abstract dP getBlockType();

   public hT<dR> getTypeProperty() {
      if (this.type == null) {
         this.type = hX.create("type", dR.class, new Predicate<dR>() {
            public boolean apply(@Nullable dR p_apply_1_) {
               return p_apply_1_.getBlockType() == dS.this.getBlockType();
            }

            // $FF: synthetic method
            // $FF: bridge method
            public boolean apply(@Nullable Object var1) {
               return this.apply((dR)var1);
            }
         });
      }

      return this.type;
   }

   public int getMetaFromState(in state) {
      return ((dR)state.getValue(this.getTypeProperty())).getMeta();
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{this.getTypeProperty()});
   }

   public cn getOffsetType() {
      return cn.XZ;
   }
}
