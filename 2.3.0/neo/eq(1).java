package neo;

import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;

public class eq extends co {
   public static final hX<ep> VARIANT = hX.create("variant", ep.class);
   private final co smallBlock;

   public eq(hM materialIn, hK color, co smallBlockIn) {
      super(materialIn, color);
      this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, ep.ALL_OUTSIDE));
      this.smallBlock = smallBlockIn;
   }

   public int quantityDropped(Random random) {
      return Math.max(0, random.nextInt(10) - 7);
   }

   /** @deprecated */
   public hK getMapColor(in state, bfZ worldIn, BlockPos pos) {
      switch ((ep)state.getValue(VARIANT)) {
         case ALL_STEM:
            return hK.CLOTH;
         case ALL_INSIDE:
            return hK.SAND;
         case STEM:
            return hK.SAND;
         default:
            return super.getMapColor(state, worldIn, pos);
      }
   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return OL.getItemFromBlock(this.smallBlock);
   }

   public Qy getItem(bij worldIn, BlockPos pos, in state) {
      return new Qy(this.smallBlock);
   }

   public in getStateForPlacement(bij worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, Iw placer) {
      return this.getDefaultState();
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(VARIANT, ep.byMetadata(meta));
   }

   public int getMetaFromState(in state) {
      return ((ep)state.getValue(VARIANT)).getMetadata();
   }

   /** @deprecated */
   public in withRotation(in state, Rotation rot) {
      switch (rot) {
         case CLOCKWISE_180:
            switch ((ep)state.getValue(VARIANT)) {
               case STEM:
                  break;
               case NORTH_WEST:
                  return state.withProperty(VARIANT, ep.SOUTH_EAST);
               case NORTH:
                  return state.withProperty(VARIANT, ep.SOUTH);
               case NORTH_EAST:
                  return state.withProperty(VARIANT, ep.SOUTH_WEST);
               case WEST:
                  return state.withProperty(VARIANT, ep.EAST);
               case EAST:
                  return state.withProperty(VARIANT, ep.WEST);
               case SOUTH_WEST:
                  return state.withProperty(VARIANT, ep.NORTH_EAST);
               case SOUTH:
                  return state.withProperty(VARIANT, ep.NORTH);
               case SOUTH_EAST:
                  return state.withProperty(VARIANT, ep.NORTH_WEST);
               default:
                  return state;
            }
         case COUNTERCLOCKWISE_90:
            switch ((ep)state.getValue(VARIANT)) {
               case STEM:
                  break;
               case NORTH_WEST:
                  return state.withProperty(VARIANT, ep.SOUTH_WEST);
               case NORTH:
                  return state.withProperty(VARIANT, ep.WEST);
               case NORTH_EAST:
                  return state.withProperty(VARIANT, ep.NORTH_WEST);
               case WEST:
                  return state.withProperty(VARIANT, ep.SOUTH);
               case EAST:
                  return state.withProperty(VARIANT, ep.NORTH);
               case SOUTH_WEST:
                  return state.withProperty(VARIANT, ep.SOUTH_EAST);
               case SOUTH:
                  return state.withProperty(VARIANT, ep.EAST);
               case SOUTH_EAST:
                  return state.withProperty(VARIANT, ep.NORTH_EAST);
               default:
                  return state;
            }
         case CLOCKWISE_90:
            switch ((ep)state.getValue(VARIANT)) {
               case STEM:
                  break;
               case NORTH_WEST:
                  return state.withProperty(VARIANT, ep.NORTH_EAST);
               case NORTH:
                  return state.withProperty(VARIANT, ep.EAST);
               case NORTH_EAST:
                  return state.withProperty(VARIANT, ep.SOUTH_EAST);
               case WEST:
                  return state.withProperty(VARIANT, ep.NORTH);
               case EAST:
                  return state.withProperty(VARIANT, ep.SOUTH);
               case SOUTH_WEST:
                  return state.withProperty(VARIANT, ep.NORTH_WEST);
               case SOUTH:
                  return state.withProperty(VARIANT, ep.WEST);
               case SOUTH_EAST:
                  return state.withProperty(VARIANT, ep.SOUTH_WEST);
               default:
                  return state;
            }
         default:
            return state;
      }
   }

   public in withMirror(in state, Mirror mirrorIn) {
      ep blockhugemushroom$enumtype = (ep)state.getValue(VARIANT);
      switch (mirrorIn) {
         case LEFT_RIGHT:
            switch (blockhugemushroom$enumtype) {
               case NORTH_WEST:
                  return state.withProperty(VARIANT, ep.SOUTH_WEST);
               case NORTH:
                  return state.withProperty(VARIANT, ep.SOUTH);
               case NORTH_EAST:
                  return state.withProperty(VARIANT, ep.SOUTH_EAST);
               case WEST:
               case EAST:
               default:
                  return super.withMirror(state, mirrorIn);
               case SOUTH_WEST:
                  return state.withProperty(VARIANT, ep.NORTH_WEST);
               case SOUTH:
                  return state.withProperty(VARIANT, ep.NORTH);
               case SOUTH_EAST:
                  return state.withProperty(VARIANT, ep.NORTH_EAST);
            }
         case FRONT_BACK:
            switch (blockhugemushroom$enumtype) {
               case NORTH_WEST:
                  return state.withProperty(VARIANT, ep.NORTH_EAST);
               case NORTH:
               case SOUTH:
               default:
                  break;
               case NORTH_EAST:
                  return state.withProperty(VARIANT, ep.NORTH_WEST);
               case WEST:
                  return state.withProperty(VARIANT, ep.EAST);
               case EAST:
                  return state.withProperty(VARIANT, ep.WEST);
               case SOUTH_WEST:
                  return state.withProperty(VARIANT, ep.SOUTH_EAST);
               case SOUTH_EAST:
                  return state.withProperty(VARIANT, ep.SOUTH_WEST);
            }
         default:
            return super.withMirror(state, mirrorIn);
      }
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{VARIANT});
   }
}
