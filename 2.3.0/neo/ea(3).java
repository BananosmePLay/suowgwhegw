package neo;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;

public class ea extends en {
   public ea(Om color) {
      super(hM.ROCK, hK.getBlockColor(color));
      this.setHardness(1.4F);
      this.setSoundType(ia.STONE);
      String s = color.getTranslationKey();
      if (s.length() > 1) {
         String s1 = s.substring(0, 1).toUpperCase() + s.substring(1, s.length());
         this.setTranslationKey("glazedTerracotta" + s1);
      }

      this.setCreativeTab(EN.DECORATIONS);
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{FACING});
   }

   /** @deprecated */
   public in withRotation(in state, Rotation rot) {
      return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
   }

   /** @deprecated */
   public in withMirror(in state, Mirror mirrorIn) {
      return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
   }

   public in getStateForPlacement(bij worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, Iw placer) {
      return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
   }

   public int getMetaFromState(in state) {
      int i = 0;
      i |= ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();
      return i;
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(FACING, EnumFacing.byHorizontalIndex(meta));
   }

   /** @deprecated */
   public hJ getPushReaction(in state) {
      return hJ.PUSH_ONLY;
   }
}
