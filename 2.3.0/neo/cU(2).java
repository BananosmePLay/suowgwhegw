package neo;

import java.util.Iterator;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class cU extends co {
   public static final hZ AGE = hZ.create("age", 0, 5);

   protected cU() {
      super(hM.PLANTS, hK.PURPLE);
      this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, 0));
      this.setCreativeTab(EN.DECORATIONS);
      this.setTickRandomly(true);
   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return NK.AIR;
   }

   public void updateTick(bij worldIn, BlockPos pos, in state, Random rand) {
      if (!this.canSurvive(worldIn, pos)) {
         worldIn.destroyBlock(pos, true);
      } else {
         BlockPos blockpos = pos.up();
         if (worldIn.isAirBlock(blockpos) && blockpos.getY() < 256) {
            int i = (Integer)state.getValue(AGE);
            if (i < 5 && rand.nextInt(1) == 0) {
               boolean flag = false;
               boolean flag1 = false;
               in iblockstate = worldIn.getBlockState(pos.down());
               co block = iblockstate.getBlock();
               int j;
               if (block == Nk.END_STONE) {
                  flag = true;
               } else if (block != Nk.CHORUS_PLANT) {
                  if (iblockstate.getMaterial() == hM.AIR) {
                     flag = true;
                  }
               } else {
                  j = 1;

                  int i1;
                  for(i1 = 0; i1 < 4; ++i1) {
                     co block1 = worldIn.getBlockState(pos.down(j + 1)).getBlock();
                     if (block1 != Nk.CHORUS_PLANT) {
                        if (block1 == Nk.END_STONE) {
                           flag1 = true;
                        }
                        break;
                     }

                     ++j;
                  }

                  i1 = 4;
                  if (flag1) {
                     ++i1;
                  }

                  if (j < 2 || rand.nextInt(i1) >= j) {
                     flag = true;
                  }
               }

               if (flag && areAllNeighborsEmpty(worldIn, blockpos, (EnumFacing)null) && worldIn.isAirBlock(pos.up(2))) {
                  worldIn.setBlockState(pos, Nk.CHORUS_PLANT.getDefaultState(), 2);
                  this.placeGrownFlower(worldIn, blockpos, i);
               } else if (i < 4) {
                  j = rand.nextInt(4);
                  boolean flag2 = false;
                  if (flag1) {
                     ++j;
                  }

                  for(int j1 = 0; j1 < j; ++j1) {
                     EnumFacing enumfacing = EnumFacing.Plane.HORIZONTAL.random(rand);
                     BlockPos blockpos1 = pos.offset(enumfacing);
                     if (worldIn.isAirBlock(blockpos1) && worldIn.isAirBlock(blockpos1.down()) && areAllNeighborsEmpty(worldIn, blockpos1, enumfacing.getOpposite())) {
                        this.placeGrownFlower(worldIn, blockpos1, i + 1);
                        flag2 = true;
                     }
                  }

                  if (flag2) {
                     worldIn.setBlockState(pos, Nk.CHORUS_PLANT.getDefaultState(), 2);
                  } else {
                     this.placeDeadFlower(worldIn, pos);
                  }
               } else if (i == 4) {
                  this.placeDeadFlower(worldIn, pos);
               }
            }
         }
      }

   }

   private void placeGrownFlower(bij worldIn, BlockPos pos, int age) {
      worldIn.setBlockState(pos, this.getDefaultState().withProperty(AGE, age), 2);
      worldIn.playEvent(1033, pos, 0);
   }

   private void placeDeadFlower(bij worldIn, BlockPos pos) {
      worldIn.setBlockState(pos, this.getDefaultState().withProperty(AGE, 5), 2);
      worldIn.playEvent(1034, pos, 0);
   }

   private static boolean areAllNeighborsEmpty(bij worldIn, BlockPos pos, EnumFacing excludingSide) {
      Iterator var3 = EnumFacing.Plane.HORIZONTAL.iterator();

      EnumFacing enumfacing;
      do {
         if (!var3.hasNext()) {
            return true;
         }

         enumfacing = (EnumFacing)var3.next();
      } while(enumfacing == excludingSide || worldIn.isAirBlock(pos.offset(enumfacing)));

      return false;
   }

   /** @deprecated */
   public boolean isFullCube(in state) {
      return false;
   }

   /** @deprecated */
   public boolean isOpaqueCube(in state) {
      return false;
   }

   public boolean canPlaceBlockAt(bij worldIn, BlockPos pos) {
      return super.canPlaceBlockAt(worldIn, pos) && this.canSurvive(worldIn, pos);
   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      if (!this.canSurvive(worldIn, pos)) {
         worldIn.scheduleUpdate(pos, this, 1);
      }

   }

   public boolean canSurvive(bij worldIn, BlockPos pos) {
      in iblockstate = worldIn.getBlockState(pos.down());
      co block = iblockstate.getBlock();
      if (block != Nk.CHORUS_PLANT && block != Nk.END_STONE) {
         if (iblockstate.getMaterial() == hM.AIR) {
            int i = 0;
            Iterator var6 = EnumFacing.Plane.HORIZONTAL.iterator();

            while(var6.hasNext()) {
               EnumFacing enumfacing = (EnumFacing)var6.next();
               in iblockstate1 = worldIn.getBlockState(pos.offset(enumfacing));
               co block1 = iblockstate1.getBlock();
               if (block1 == Nk.CHORUS_PLANT) {
                  ++i;
               } else if (iblockstate1.getMaterial() != hM.AIR) {
                  return false;
               }
            }

            return i == 1;
         } else {
            return false;
         }
      } else {
         return true;
      }
   }

   public void harvestBlock(bij worldIn, ME player, BlockPos pos, in state, @Nullable Yg te, Qy stack) {
      super.harvestBlock(worldIn, player, pos, state, te, stack);
      spawnAsEntity(worldIn, pos, new Qy(OL.getItemFromBlock(this)));
   }

   protected Qy getSilkTouchDrop(in state) {
      return Qy.EMPTY;
   }

   public BlockRenderLayer getRenderLayer() {
      return BlockRenderLayer.CUTOUT;
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

   public static void generatePlant(bij worldIn, BlockPos pos, Random rand, int p_185603_3_) {
      worldIn.setBlockState(pos, Nk.CHORUS_PLANT.getDefaultState(), 2);
      growTreeRecursive(worldIn, pos, rand, pos, p_185603_3_, 0);
   }

   private static void growTreeRecursive(bij worldIn, BlockPos p_185601_1_, Random rand, BlockPos p_185601_3_, int p_185601_4_, int p_185601_5_) {
      int i = rand.nextInt(4) + 1;
      if (p_185601_5_ == 0) {
         ++i;
      }

      for(int j = 0; j < i; ++j) {
         BlockPos blockpos = p_185601_1_.up(j + 1);
         if (!areAllNeighborsEmpty(worldIn, blockpos, (EnumFacing)null)) {
            return;
         }

         worldIn.setBlockState(blockpos, Nk.CHORUS_PLANT.getDefaultState(), 2);
      }

      boolean flag = false;
      if (p_185601_5_ < 4) {
         int l = rand.nextInt(4);
         if (p_185601_5_ == 0) {
            ++l;
         }

         for(int k = 0; k < l; ++k) {
            EnumFacing enumfacing = EnumFacing.Plane.HORIZONTAL.random(rand);
            BlockPos blockpos1 = p_185601_1_.up(i).offset(enumfacing);
            if (Math.abs(blockpos1.getX() - p_185601_3_.getX()) < p_185601_4_ && Math.abs(blockpos1.getZ() - p_185601_3_.getZ()) < p_185601_4_ && worldIn.isAirBlock(blockpos1) && worldIn.isAirBlock(blockpos1.down()) && areAllNeighborsEmpty(worldIn, blockpos1, enumfacing.getOpposite())) {
               flag = true;
               worldIn.setBlockState(blockpos1, Nk.CHORUS_PLANT.getDefaultState(), 2);
               growTreeRecursive(worldIn, blockpos1, rand, p_185601_3_, p_185601_4_, p_185601_5_ + 1);
            }
         }
      }

      if (!flag) {
         worldIn.setBlockState(p_185601_1_.up(i), Nk.CHORUS_FLOWER.getDefaultState().withProperty(AGE, 5), 2);
      }

   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return ib.UNDEFINED;
   }
}
