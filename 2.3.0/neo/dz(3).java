package neo;

import java.util.Random;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class dz extends dd {
   protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.75, 1.0);

   protected dz() {
      super(hM.ROCK, hK.RED);
      this.setLightOpacity(0);
      this.setCreativeTab(EN.DECORATIONS);
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      return AABB;
   }

   /** @deprecated */
   public boolean isFullCube(in state) {
      return false;
   }

   public void randomDisplayTick(in stateIn, bij worldIn, BlockPos pos, Random rand) {
      super.randomDisplayTick(stateIn, worldIn, pos, rand);

      for(int i = -2; i <= 2; ++i) {
         for(int j = -2; j <= 2; ++j) {
            if (i > -2 && i < 2 && j == -1) {
               j = 2;
            }

            if (rand.nextInt(16) == 0) {
               for(int k = 0; k <= 1; ++k) {
                  BlockPos blockpos = pos.add(i, k, j);
                  if (worldIn.getBlockState(blockpos).getBlock() == Nk.BOOKSHELF) {
                     if (!worldIn.isAirBlock(pos.add(i / 2, 0, j / 2))) {
                        break;
                     }

                     worldIn.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, (double)pos.getX() + 0.5, (double)pos.getY() + 2.0, (double)pos.getZ() + 0.5, (double)((float)i + rand.nextFloat()) - 0.5, (double)((float)k - rand.nextFloat() - 1.0F), (double)((float)j + rand.nextFloat()) - 0.5);
                  }
               }
            }
         }
      }

   }

   /** @deprecated */
   public boolean isOpaqueCube(in state) {
      return false;
   }

   /** @deprecated */
   public EnumBlockRenderType getRenderType(in state) {
      return EnumBlockRenderType.MODEL;
   }

   public Yg createNewTileEntity(bij worldIn, int meta) {
      return new Yv();
   }

   public boolean onBlockActivated(bij worldIn, BlockPos pos, in state, ME playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      if (worldIn.isRemote) {
         return true;
      } else {
         Yg tileentity = worldIn.getTileEntity(pos);
         if (tileentity instanceof Yv) {
            playerIn.displayGui((Yv)tileentity);
         }

         return true;
      }
   }

   public void onBlockPlacedBy(bij worldIn, BlockPos pos, in state, Iw placer, Qy stack) {
      super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
      if (stack.hasDisplayName()) {
         Yg tileentity = worldIn.getTileEntity(pos);
         if (tileentity instanceof Yv) {
            ((Yv)tileentity).setCustomName(stack.getDisplayName());
         }
      }

   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return face == EnumFacing.DOWN ? ib.SOLID : ib.UNDEFINED;
   }
}
