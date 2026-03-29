package neo;

import java.util.Iterator;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;

public abstract class eE extends gi {
   public static final hX<eD> LOG_AXIS = hX.create("axis", eD.class);

   public eE() {
      super(hM.WOOD);
      this.setCreativeTab(EN.BUILDING_BLOCKS);
      this.setHardness(2.0F);
      this.setSoundType(ia.WOOD);
   }

   public void breakBlock(bij worldIn, BlockPos pos, in state) {
      int i = true;
      int j = true;
      if (worldIn.isAreaLoaded(pos.add(-5, -5, -5), pos.add(5, 5, 5))) {
         Iterator var6 = BlockPos.getAllInBox(pos.add(-4, -4, -4), pos.add(4, 4, 4)).iterator();

         while(var6.hasNext()) {
            BlockPos blockpos = (BlockPos)var6.next();
            in iblockstate = worldIn.getBlockState(blockpos);
            if (iblockstate.getMaterial() == hM.LEAVES && !(Boolean)iblockstate.getValue(ew.CHECK_DECAY)) {
               worldIn.setBlockState(blockpos, iblockstate.withProperty(ew.CHECK_DECAY, true), 4);
            }
         }
      }

   }

   public in getStateForPlacement(bij worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, Iw placer) {
      return this.getStateFromMeta(meta).withProperty(LOG_AXIS, eD.fromFacingAxis(facing.getAxis()));
   }

   /** @deprecated */
   public in withRotation(in state, Rotation rot) {
      switch (rot) {
         case COUNTERCLOCKWISE_90:
         case CLOCKWISE_90:
            switch ((eD)state.getValue(LOG_AXIS)) {
               case X:
                  return state.withProperty(LOG_AXIS, eD.Z);
               case Z:
                  return state.withProperty(LOG_AXIS, eD.X);
               default:
                  return state;
            }
         default:
            return state;
      }
   }
}
