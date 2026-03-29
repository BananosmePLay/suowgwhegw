package neo;

import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.HttpUtil;
import net.minecraft.util.math.BlockPos;

public class cA extends dd {
   public cA() {
      super(hM.GLASS, hK.DIAMOND);
      this.setHardness(3.0F);
      this.setCreativeTab(EN.MISC);
   }

   public Yg createNewTileEntity(bij worldIn, int meta) {
      return new Yj();
   }

   public boolean onBlockActivated(bij worldIn, BlockPos pos, in state, ME playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      if (worldIn.isRemote) {
         return true;
      } else {
         Yg tileentity = worldIn.getTileEntity(pos);
         if (tileentity instanceof Yj) {
            playerIn.displayGUIChest((Yj)tileentity);
            playerIn.addStat(XV.BEACON_INTERACTION);
         }

         return true;
      }
   }

   /** @deprecated */
   public boolean isOpaqueCube(in state) {
      return false;
   }

   /** @deprecated */
   public boolean isFullCube(in state) {
      return false;
   }

   /** @deprecated */
   public EnumBlockRenderType getRenderType(in state) {
      return EnumBlockRenderType.MODEL;
   }

   public void onBlockPlacedBy(bij worldIn, BlockPos pos, in state, Iw placer, Qy stack) {
      super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
      if (stack.hasDisplayName()) {
         Yg tileentity = worldIn.getTileEntity(pos);
         if (tileentity instanceof Yj) {
            ((Yj)tileentity).setName(stack.getDisplayName());
         }
      }

   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      Yg tileentity = worldIn.getTileEntity(pos);
      if (tileentity instanceof Yj) {
         ((Yj)tileentity).updateBeacon();
         worldIn.addBlockEvent(pos, this, 1, 0);
      }

   }

   public BlockRenderLayer getRenderLayer() {
      return BlockRenderLayer.CUTOUT;
   }

   public static void updateColorAsync(final bij worldIn, final BlockPos glassPos) {
      HttpUtil.DOWNLOADER_EXECUTOR.submit(new Runnable() {
         public void run() {
            bam chunk = worldIn.getChunk(glassPos);

            for(int i = glassPos.getY() - 1; i >= 0; --i) {
               final BlockPos blockpos = new BlockPos(glassPos.getX(), i, glassPos.getZ());
               if (!chunk.canSeeSky(blockpos)) {
                  break;
               }

               in iblockstate = worldIn.getBlockState(blockpos);
               if (iblockstate.getBlock() == Nk.BEACON) {
                  ((bis)worldIn).addScheduledTask(new Runnable() {
                     public void run() {
                        Yg tileentity = worldIn.getTileEntity(blockpos);
                        if (tileentity instanceof Yj) {
                           ((Yj)tileentity).updateBeacon();
                           worldIn.addBlockEvent(blockpos, Nk.BEACON, 1, 0);
                        }

                     }
                  });
               }
            }

         }
      });
   }
}
