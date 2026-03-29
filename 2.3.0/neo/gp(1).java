package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class gp extends co {
   public gp(hM materialIn) {
      super(materialIn);
      this.setCreativeTab(EN.BUILDING_BLOCKS);
   }

   public int quantityDropped(Random random) {
      return 2 + random.nextInt(2);
   }

   public int quantityDroppedWithBonus(int fortune, Random random) {
      return MathHelper.clamp(this.quantityDropped(random) + random.nextInt(fortune + 1), 1, 5);
   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return NK.PRISMARINE_CRYSTALS;
   }

   /** @deprecated */
   public hK getMapColor(in state, bfZ worldIn, BlockPos pos) {
      return hK.QUARTZ;
   }

   protected boolean canSilkHarvest() {
      return true;
   }
}
