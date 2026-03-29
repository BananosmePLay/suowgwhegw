package neo;

import java.util.Random;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class eM extends cI {
   public static final hZ AGE = hZ.create("age", 0, 3);
   private static final AxisAlignedBB[] NETHER_WART_AABB = new AxisAlignedBB[]{new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.3125, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.5, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.6875, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.875, 1.0)};

   protected eM() {
      super(hM.PLANTS, hK.RED);
      this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, 0));
      this.setTickRandomly(true);
      this.setCreativeTab((EN)null);
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      return NETHER_WART_AABB[(Integer)state.getValue(AGE)];
   }

   protected boolean canSustainBush(in state) {
      return state.getBlock() == Nk.SOUL_SAND;
   }

   public boolean canBlockStay(bij worldIn, BlockPos pos, in state) {
      return this.canSustainBush(worldIn.getBlockState(pos.down()));
   }

   public void updateTick(bij worldIn, BlockPos pos, in state, Random rand) {
      int i = (Integer)state.getValue(AGE);
      if (i < 3 && rand.nextInt(10) == 0) {
         state = state.withProperty(AGE, i + 1);
         worldIn.setBlockState(pos, state, 2);
      }

      super.updateTick(worldIn, pos, state, rand);
   }

   public void dropBlockAsItemWithChance(bij worldIn, BlockPos pos, in state, float chance, int fortune) {
      if (!worldIn.isRemote) {
         int i = 1;
         if ((Integer)state.getValue(AGE) >= 3) {
            i = 2 + worldIn.rand.nextInt(3);
            if (fortune > 0) {
               i += worldIn.rand.nextInt(fortune + 1);
            }
         }

         for(int j = 0; j < i; ++j) {
            spawnAsEntity(worldIn, pos, new Qy(NK.NETHER_WART));
         }
      }

   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return NK.AIR;
   }

   public int quantityDropped(Random random) {
      return 0;
   }

   public Qy getItem(bij worldIn, BlockPos pos, in state) {
      return new Qy(NK.NETHER_WART);
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(AGE, meta);
   }

   public int getMetaFromState(in state) {
      return (Integer)state.getValue(AGE);
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{AGE});
   }
}
