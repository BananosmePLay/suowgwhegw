package neo;

import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class hk extends cI implements hH {
   public static final hX<hj> TYPE = hX.create("type", hj.class);
   protected static final AxisAlignedBB TALL_GRASS_AABB = new AxisAlignedBB(0.09999999403953552, 0.0, 0.09999999403953552, 0.8999999761581421, 0.800000011920929, 0.8999999761581421);

   protected hk() {
      super(hM.VINE);
      this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, hj.DEAD_BUSH));
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      return TALL_GRASS_AABB;
   }

   public boolean canBlockStay(bij worldIn, BlockPos pos, in state) {
      return this.canSustainBush(worldIn.getBlockState(pos.down()));
   }

   public boolean isReplaceable(bfZ worldIn, BlockPos pos) {
      return true;
   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return rand.nextInt(8) == 0 ? NK.WHEAT_SEEDS : NK.AIR;
   }

   public int quantityDroppedWithBonus(int fortune, Random random) {
      return 1 + random.nextInt(fortune * 2 + 1);
   }

   public void harvestBlock(bij worldIn, ME player, BlockPos pos, in state, @Nullable Yg te, Qy stack) {
      if (!worldIn.isRemote && stack.getItem() == NK.SHEARS) {
         player.addStat(XV.getBlockStats(this));
         spawnAsEntity(worldIn, pos, new Qy(Nk.TALLGRASS, 1, ((hj)state.getValue(TYPE)).getMeta()));
      } else {
         super.harvestBlock(worldIn, player, pos, state, te, stack);
      }

   }

   public Qy getItem(bij worldIn, BlockPos pos, in state) {
      return new Qy(this, 1, state.getBlock().getMetaFromState(state));
   }

   public void getSubBlocks(EN itemIn, NonNullList<Qy> items) {
      for(int i = 1; i < 3; ++i) {
         items.add(new Qy(this, 1, i));
      }

   }

   public boolean canGrow(bij worldIn, BlockPos pos, in state, boolean isClient) {
      return state.getValue(TYPE) != hj.DEAD_BUSH;
   }

   public boolean canUseBonemeal(bij worldIn, Random rand, BlockPos pos, in state) {
      return true;
   }

   public void grow(bij worldIn, Random rand, BlockPos pos, in state) {
      dq blockdoubleplant$enumplanttype = dq.GRASS;
      if (state.getValue(TYPE) == hj.FERN) {
         blockdoubleplant$enumplanttype = dq.FERN;
      }

      if (Nk.DOUBLE_PLANT.canPlaceBlockAt(worldIn, pos)) {
         Nk.DOUBLE_PLANT.placeAt(worldIn, pos, blockdoubleplant$enumplanttype, 2);
      }

   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(TYPE, hj.byMetadata(meta));
   }

   public int getMetaFromState(in state) {
      return ((hj)state.getValue(TYPE)).getMeta();
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{TYPE});
   }

   public cn getOffsetType() {
      return cn.XYZ;
   }
}
