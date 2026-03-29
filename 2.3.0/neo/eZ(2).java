package neo;

import com.google.common.base.Predicate;
import javax.annotation.Nullable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;

public class eZ extends eE {
   public static final hX<fk> VARIANT = hX.create("variant", fk.class, new Predicate<fk>() {
      public boolean apply(@Nullable fk p_apply_1_) {
         return p_apply_1_.getMetadata() < 4;
      }

      // $FF: synthetic method
      // $FF: bridge method
      public boolean apply(@Nullable Object var1) {
         return this.apply((fk)var1);
      }
   });

   public eZ() {
      this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, fk.OAK).withProperty(LOG_AXIS, eD.Y));
   }

   /** @deprecated */
   public hK getMapColor(in state, bfZ worldIn, BlockPos pos) {
      fk blockplanks$enumtype = (fk)state.getValue(VARIANT);
      switch ((eD)state.getValue(LOG_AXIS)) {
         case X:
         case Z:
         case NONE:
         default:
            switch (blockplanks$enumtype) {
               case OAK:
               default:
                  return fk.SPRUCE.getMapColor();
               case SPRUCE:
                  return fk.DARK_OAK.getMapColor();
               case BIRCH:
                  return hK.QUARTZ;
               case JUNGLE:
                  return fk.SPRUCE.getMapColor();
            }
         case Y:
            return blockplanks$enumtype.getMapColor();
      }
   }

   public void getSubBlocks(EN itemIn, NonNullList<Qy> items) {
      items.add(new Qy(this, 1, fk.OAK.getMetadata()));
      items.add(new Qy(this, 1, fk.SPRUCE.getMetadata()));
      items.add(new Qy(this, 1, fk.BIRCH.getMetadata()));
      items.add(new Qy(this, 1, fk.JUNGLE.getMetadata()));
   }

   public in getStateFromMeta(int meta) {
      in iblockstate = this.getDefaultState().withProperty(VARIANT, fk.byMetadata((meta & 3) % 4));
      switch (meta & 12) {
         case 0:
            iblockstate = iblockstate.withProperty(LOG_AXIS, eD.Y);
            break;
         case 4:
            iblockstate = iblockstate.withProperty(LOG_AXIS, eD.X);
            break;
         case 8:
            iblockstate = iblockstate.withProperty(LOG_AXIS, eD.Z);
            break;
         default:
            iblockstate = iblockstate.withProperty(LOG_AXIS, eD.NONE);
      }

      return iblockstate;
   }

   public int getMetaFromState(in state) {
      int i = 0;
      i |= ((fk)state.getValue(VARIANT)).getMetadata();
      switch ((eD)state.getValue(LOG_AXIS)) {
         case X:
            i |= 4;
            break;
         case Z:
            i |= 8;
            break;
         case NONE:
            i |= 12;
      }

      return i;
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{VARIANT, LOG_AXIS});
   }

   protected Qy getSilkTouchDrop(in state) {
      return new Qy(OL.getItemFromBlock(this), 1, ((fk)state.getValue(VARIANT)).getMetadata());
   }

   public int damageDropped(in state) {
      return ((fk)state.getValue(VARIANT)).getMetadata();
   }
}
