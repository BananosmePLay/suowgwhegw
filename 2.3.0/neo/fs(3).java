package neo;

import java.util.Iterator;
import java.util.List;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class fs extends cx {
   public static final hV POWERED = hV.create("powered");
   private final fr sensitivity;

   protected fs(hM materialIn, fr sensitivityIn) {
      super(materialIn);
      this.setDefaultState(this.blockState.getBaseState().withProperty(POWERED, false));
      this.sensitivity = sensitivityIn;
   }

   protected int getRedstoneStrength(in state) {
      return (Boolean)state.getValue(POWERED) ? 15 : 0;
   }

   protected in setRedstoneStrength(in state, int strength) {
      return state.withProperty(POWERED, strength > 0);
   }

   protected void playClickOnSound(bij worldIn, BlockPos color) {
      if (this.material == hM.WOOD) {
         worldIn.playSound((ME)null, color, NO.BLOCK_WOOD_PRESSPLATE_CLICK_ON, SoundCategory.BLOCKS, 0.3F, 0.8F);
      } else {
         worldIn.playSound((ME)null, color, NO.BLOCK_STONE_PRESSPLATE_CLICK_ON, SoundCategory.BLOCKS, 0.3F, 0.6F);
      }

   }

   protected void playClickOffSound(bij worldIn, BlockPos pos) {
      if (this.material == hM.WOOD) {
         worldIn.playSound((ME)null, pos, NO.BLOCK_WOOD_PRESSPLATE_CLICK_OFF, SoundCategory.BLOCKS, 0.3F, 0.7F);
      } else {
         worldIn.playSound((ME)null, pos, NO.BLOCK_STONE_PRESSPLATE_CLICK_OFF, SoundCategory.BLOCKS, 0.3F, 0.5F);
      }

   }

   protected int computeRedstoneStrength(bij worldIn, BlockPos pos) {
      AxisAlignedBB axisalignedbb = PRESSURE_AABB.offset(pos);
      List list;
      switch (this.sensitivity) {
         case EVERYTHING:
            list = worldIn.getEntitiesWithinAABBExcludingEntity((Ig)null, axisalignedbb);
            break;
         case MOBS:
            list = worldIn.getEntitiesWithinAABB(Iw.class, axisalignedbb);
            break;
         default:
            return 0;
      }

      if (!list.isEmpty()) {
         Iterator var5 = list.iterator();

         while(var5.hasNext()) {
            Ig entity = (Ig)var5.next();
            if (!entity.doesEntityNotTriggerPressurePlate()) {
               return 15;
            }
         }
      }

      return 0;
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(POWERED, meta == 1);
   }

   public int getMetaFromState(in state) {
      return (Boolean)state.getValue(POWERED) ? 1 : 0;
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{POWERED});
   }
}
