package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class eb extends co {
   public eb(hM materialIn) {
      super(materialIn);
      this.setCreativeTab(EN.BUILDING_BLOCKS);
   }

   public int quantityDroppedWithBonus(int fortune, Random random) {
      return MathHelper.clamp(this.quantityDropped(random) + random.nextInt(fortune + 1), 1, 4);
   }

   public int quantityDropped(Random random) {
      return 2 + random.nextInt(3);
   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return NK.GLOWSTONE_DUST;
   }

   /** @deprecated */
   public hK getMapColor(in state, bfZ worldIn, BlockPos pos) {
      return hK.SAND;
   }
}
