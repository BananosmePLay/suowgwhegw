package neo;

import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackData;
import net.minecraft.util.math.BlockPos;

public class et extends dd {
   public static final hV HAS_RECORD = hV.create("has_record");

   public static void registerFixesJukebox(DataFixer fixer) {
      fixer.registerWalker(FixTypes.BLOCK_ENTITY, new ItemStackData(es.class, new String[]{"RecordItem"}));
   }

   protected et() {
      super(hM.WOOD, hK.DIRT);
      this.setDefaultState(this.blockState.getBaseState().withProperty(HAS_RECORD, false));
      this.setCreativeTab(EN.DECORATIONS);
   }

   public boolean onBlockActivated(bij worldIn, BlockPos pos, in state, ME playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      if ((Boolean)state.getValue(HAS_RECORD)) {
         this.dropRecord(worldIn, pos, state);
         state = state.withProperty(HAS_RECORD, false);
         worldIn.setBlockState(pos, state, 2);
         return true;
      } else {
         return false;
      }
   }

   public void insertRecord(bij worldIn, BlockPos pos, in state, Qy recordStack) {
      Yg tileentity = worldIn.getTileEntity(pos);
      if (tileentity instanceof es) {
         ((es)tileentity).setRecord(recordStack.copy());
         worldIn.setBlockState(pos, state.withProperty(HAS_RECORD, true), 2);
      }

   }

   private void dropRecord(bij worldIn, BlockPos pos, in state) {
      if (!worldIn.isRemote) {
         Yg tileentity = worldIn.getTileEntity(pos);
         if (tileentity instanceof es) {
            es blockjukebox$tileentityjukebox = (es)tileentity;
            Qy itemstack = blockjukebox$tileentityjukebox.getRecord();
            if (!itemstack.isEmpty()) {
               worldIn.playEvent(1010, pos, 0);
               worldIn.playRecord(pos, (SoundEvent)null);
               blockjukebox$tileentityjukebox.setRecord(Qy.EMPTY);
               float f = 0.7F;
               double d0 = (double)(worldIn.rand.nextFloat() * 0.7F) + 0.15000000596046448;
               double d1 = (double)(worldIn.rand.nextFloat() * 0.7F) + 0.06000000238418579 + 0.6;
               double d2 = (double)(worldIn.rand.nextFloat() * 0.7F) + 0.15000000596046448;
               Qy itemstack1 = itemstack.copy();
               IY entityitem = new IY(worldIn, (double)pos.getX() + d0, (double)pos.getY() + d1, (double)pos.getZ() + d2, itemstack1);
               entityitem.setDefaultPickupDelay();
               worldIn.spawnEntity(entityitem);
            }
         }
      }

   }

   public void breakBlock(bij worldIn, BlockPos pos, in state) {
      this.dropRecord(worldIn, pos, state);
      super.breakBlock(worldIn, pos, state);
   }

   public void dropBlockAsItemWithChance(bij worldIn, BlockPos pos, in state, float chance, int fortune) {
      if (!worldIn.isRemote) {
         super.dropBlockAsItemWithChance(worldIn, pos, state, chance, 0);
      }

   }

   public Yg createNewTileEntity(bij worldIn, int meta) {
      return new es();
   }

   /** @deprecated */
   public boolean hasComparatorInputOverride(in state) {
      return true;
   }

   /** @deprecated */
   public int getComparatorInputOverride(in blockState, bij worldIn, BlockPos pos) {
      Yg tileentity = worldIn.getTileEntity(pos);
      if (tileentity instanceof es) {
         Qy itemstack = ((es)tileentity).getRecord();
         if (!itemstack.isEmpty()) {
            return OL.getIdFromItem(itemstack.getItem()) + 1 - OL.getIdFromItem(NK.RECORD_13);
         }
      }

      return 0;
   }

   /** @deprecated */
   public EnumBlockRenderType getRenderType(in state) {
      return EnumBlockRenderType.MODEL;
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(HAS_RECORD, meta > 0);
   }

   public int getMetaFromState(in state) {
      return (Boolean)state.getValue(HAS_RECORD) ? 1 : 0;
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{HAS_RECORD});
   }
}
