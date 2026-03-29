package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class fa extends co {
   public fa() {
      this(hM.ROCK.getMaterialMapColor());
   }

   public fa(hK color) {
      super(hM.ROCK, color);
      this.setCreativeTab(EN.BUILDING_BLOCKS);
   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      if (this == Nk.COAL_ORE) {
         return NK.COAL;
      } else if (this == Nk.DIAMOND_ORE) {
         return NK.DIAMOND;
      } else if (this == Nk.LAPIS_ORE) {
         return NK.DYE;
      } else if (this == Nk.EMERALD_ORE) {
         return NK.EMERALD;
      } else {
         return this == Nk.QUARTZ_ORE ? NK.QUARTZ : OL.getItemFromBlock(this);
      }
   }

   public int quantityDropped(Random random) {
      return this == Nk.LAPIS_ORE ? 4 + random.nextInt(5) : 1;
   }

   public int quantityDroppedWithBonus(int fortune, Random random) {
      if (fortune > 0 && OL.getItemFromBlock(this) != this.getItemDropped((in)this.getBlockState().getValidStates().iterator().next(), random, fortune)) {
         int i = random.nextInt(fortune + 2) - 1;
         if (i < 0) {
            i = 0;
         }

         return this.quantityDropped(random) * (i + 1);
      } else {
         return this.quantityDropped(random);
      }
   }

   public void dropBlockAsItemWithChance(bij worldIn, BlockPos pos, in state, float chance, int fortune) {
      super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
      if (this.getItemDropped(state, worldIn.rand, fortune) != OL.getItemFromBlock(this)) {
         int i = 0;
         if (this == Nk.COAL_ORE) {
            i = MathHelper.getInt((Random)worldIn.rand, 0, 2);
         } else if (this == Nk.DIAMOND_ORE) {
            i = MathHelper.getInt((Random)worldIn.rand, 3, 7);
         } else if (this == Nk.EMERALD_ORE) {
            i = MathHelper.getInt((Random)worldIn.rand, 3, 7);
         } else if (this == Nk.LAPIS_ORE) {
            i = MathHelper.getInt((Random)worldIn.rand, 2, 5);
         } else if (this == Nk.QUARTZ_ORE) {
            i = MathHelper.getInt((Random)worldIn.rand, 2, 5);
         }

         this.dropXpOnBlockBreak(worldIn, pos, i);
      }

   }

   public Qy getItem(bij worldIn, BlockPos pos, in state) {
      return new Qy(this);
   }

   public int damageDropped(in state) {
      return this == Nk.LAPIS_ORE ? Om.BLUE.getDyeDamage() : 0;
   }
}
