package neo;

import java.util.Random;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;

public class hh extends dd {
   public static final hX<YU> MODE = hX.create("mode", YU.class);

   public hh() {
      super(hM.IRON, hK.SILVER);
      this.setDefaultState(this.blockState.getBaseState());
   }

   public Yg createNewTileEntity(bij worldIn, int meta) {
      return new YV();
   }

   public boolean onBlockActivated(bij worldIn, BlockPos pos, in state, ME playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      Yg tileentity = worldIn.getTileEntity(pos);
      return tileentity instanceof YV ? ((YV)tileentity).usedBy(playerIn) : false;
   }

   public void onBlockPlacedBy(bij worldIn, BlockPos pos, in state, Iw placer, Qy stack) {
      if (!worldIn.isRemote) {
         Yg tileentity = worldIn.getTileEntity(pos);
         if (tileentity instanceof YV) {
            YV tileentitystructure = (YV)tileentity;
            tileentitystructure.createdBy(placer);
         }
      }

   }

   public int quantityDropped(Random random) {
      return 0;
   }

   /** @deprecated */
   public EnumBlockRenderType getRenderType(in state) {
      return EnumBlockRenderType.MODEL;
   }

   public in getStateForPlacement(bij worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, Iw placer) {
      return this.getDefaultState().withProperty(MODE, YU.DATA);
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(MODE, YU.getById(meta));
   }

   public int getMetaFromState(in state) {
      return ((YU)state.getValue(MODE)).getModeId();
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{MODE});
   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      if (!worldIn.isRemote) {
         Yg tileentity = worldIn.getTileEntity(pos);
         if (tileentity instanceof YV) {
            YV tileentitystructure = (YV)tileentity;
            boolean flag = worldIn.isBlockPowered(pos);
            boolean flag1 = tileentitystructure.isPowered();
            if (flag && !flag1) {
               tileentitystructure.setPowered(true);
               this.trigger(tileentitystructure);
            } else if (!flag && flag1) {
               tileentitystructure.setPowered(false);
            }
         }
      }

   }

   private void trigger(YV p_189874_1_) {
      switch (p_189874_1_.getMode()) {
         case SAVE:
            p_189874_1_.save(false);
            break;
         case LOAD:
            p_189874_1_.load(false);
            break;
         case CORNER:
            p_189874_1_.unloadStructure();
         case DATA:
      }

   }
}
