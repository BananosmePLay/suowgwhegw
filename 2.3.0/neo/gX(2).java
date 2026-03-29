package neo;

import java.util.Iterator;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class gX extends cI implements hH {
   public static final hZ AGE = hZ.create("age", 0, 7);
   public static final hW FACING;
   private final co crop;
   protected static final AxisAlignedBB[] STEM_AABB;

   protected gX(co crop) {
      this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, 0).withProperty(FACING, EnumFacing.UP));
      this.crop = crop;
      this.setTickRandomly(true);
      this.setCreativeTab((EN)null);
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      return STEM_AABB[(Integer)state.getValue(AGE)];
   }

   public in getActualState(in state, bfZ worldIn, BlockPos pos) {
      int i = (Integer)state.getValue(AGE);
      state = state.withProperty(FACING, EnumFacing.UP);
      Iterator var5 = EnumFacing.Plane.HORIZONTAL.iterator();

      while(var5.hasNext()) {
         EnumFacing enumfacing = (EnumFacing)var5.next();
         if (worldIn.getBlockState(pos.offset(enumfacing)).getBlock() == this.crop && i == 7) {
            state = state.withProperty(FACING, enumfacing);
            break;
         }
      }

      return state;
   }

   protected boolean canSustainBush(in state) {
      return state.getBlock() == Nk.FARMLAND;
   }

   public void updateTick(bij worldIn, BlockPos pos, in state, Random rand) {
      super.updateTick(worldIn, pos, state, rand);
      if (worldIn.getLightFromNeighbors(pos.up()) >= 9) {
         float f = de.getGrowthChance(this, worldIn, pos);
         if (rand.nextInt((int)(25.0F / f) + 1) == 0) {
            int i = (Integer)state.getValue(AGE);
            if (i < 7) {
               state = state.withProperty(AGE, i + 1);
               worldIn.setBlockState(pos, state, 2);
            } else {
               Iterator var7 = EnumFacing.Plane.HORIZONTAL.iterator();

               while(var7.hasNext()) {
                  EnumFacing enumfacing = (EnumFacing)var7.next();
                  if (worldIn.getBlockState(pos.offset(enumfacing)).getBlock() == this.crop) {
                     return;
                  }
               }

               pos = pos.offset(EnumFacing.Plane.HORIZONTAL.random(rand));
               co block = worldIn.getBlockState(pos.down()).getBlock();
               if (worldIn.getBlockState(pos).getBlock().material == hM.AIR && (block == Nk.FARMLAND || block == Nk.DIRT || block == Nk.GRASS)) {
                  worldIn.setBlockState(pos, this.crop.getDefaultState());
               }
            }
         }
      }

   }

   public void growStem(bij worldIn, BlockPos pos, in state) {
      int i = (Integer)state.getValue(AGE) + MathHelper.getInt((Random)worldIn.rand, 2, 5);
      worldIn.setBlockState(pos, state.withProperty(AGE, Math.min(7, i)), 2);
   }

   public void dropBlockAsItemWithChance(bij worldIn, BlockPos pos, in state, float chance, int fortune) {
      super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
      if (!worldIn.isRemote) {
         OL item = this.getSeedItem();
         if (item != null) {
            int i = (Integer)state.getValue(AGE);

            for(int j = 0; j < 3; ++j) {
               if (worldIn.rand.nextInt(15) <= i) {
                  spawnAsEntity(worldIn, pos, new Qy(item));
               }
            }
         }
      }

   }

   @Nullable
   protected OL getSeedItem() {
      if (this.crop == Nk.PUMPKIN) {
         return NK.PUMPKIN_SEEDS;
      } else {
         return this.crop == Nk.MELON_BLOCK ? NK.MELON_SEEDS : null;
      }
   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return NK.AIR;
   }

   public Qy getItem(bij worldIn, BlockPos pos, in state) {
      OL item = this.getSeedItem();
      return item == null ? Qy.EMPTY : new Qy(item);
   }

   public boolean canGrow(bij worldIn, BlockPos pos, in state, boolean isClient) {
      return (Integer)state.getValue(AGE) != 7;
   }

   public boolean canUseBonemeal(bij worldIn, Random rand, BlockPos pos, in state) {
      return true;
   }

   public void grow(bij worldIn, Random rand, BlockPos pos, in state) {
      this.growStem(worldIn, pos, state);
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(AGE, meta);
   }

   public int getMetaFromState(in state) {
      return (Integer)state.getValue(AGE);
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{AGE, FACING});
   }

   static {
      FACING = ho.FACING;
      STEM_AABB = new AxisAlignedBB[]{new AxisAlignedBB(0.375, 0.0, 0.375, 0.625, 0.125, 0.625), new AxisAlignedBB(0.375, 0.0, 0.375, 0.625, 0.25, 0.625), new AxisAlignedBB(0.375, 0.0, 0.375, 0.625, 0.375, 0.625), new AxisAlignedBB(0.375, 0.0, 0.375, 0.625, 0.5, 0.625), new AxisAlignedBB(0.375, 0.0, 0.375, 0.625, 0.625, 0.625), new AxisAlignedBB(0.375, 0.0, 0.375, 0.625, 0.75, 0.625), new AxisAlignedBB(0.375, 0.0, 0.375, 0.625, 0.875, 0.625), new AxisAlignedBB(0.375, 0.0, 0.375, 0.625, 1.0, 0.625)};
   }
}
