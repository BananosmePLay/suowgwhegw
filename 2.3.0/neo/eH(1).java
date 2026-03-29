package neo;

import java.util.Random;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;

public class eH extends dd {
   protected eH() {
      super(hM.ROCK);
   }

   public Yg createNewTileEntity(bij worldIn, int meta) {
      return new YG();
   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return NK.AIR;
   }

   public int quantityDropped(Random random) {
      return 0;
   }

   public void dropBlockAsItemWithChance(bij worldIn, BlockPos pos, in state, float chance, int fortune) {
      super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
      int i = 15 + worldIn.rand.nextInt(15) + worldIn.rand.nextInt(15);
      this.dropXpOnBlockBreak(worldIn, pos, i);
   }

   /** @deprecated */
   public boolean isOpaqueCube(in state) {
      return false;
   }

   /** @deprecated */
   public EnumBlockRenderType getRenderType(in state) {
      return EnumBlockRenderType.MODEL;
   }

   public BlockRenderLayer getRenderLayer() {
      return BlockRenderLayer.CUTOUT;
   }

   public Qy getItem(bij worldIn, BlockPos pos, in state) {
      return Qy.EMPTY;
   }
}
