package neo;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;

public class eS extends dd {
   private static final List<SoundEvent> INSTRUMENTS;

   public eS() {
      super(hM.WOOD);
      this.setCreativeTab(EN.REDSTONE);
   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      boolean flag = worldIn.isBlockPowered(pos);
      Yg tileentity = worldIn.getTileEntity(pos);
      if (tileentity instanceof YH) {
         YH tileentitynote = (YH)tileentity;
         if (tileentitynote.previousRedstoneState != flag) {
            if (flag) {
               tileentitynote.triggerNote(worldIn, pos);
            }

            tileentitynote.previousRedstoneState = flag;
         }
      }

   }

   public boolean onBlockActivated(bij worldIn, BlockPos pos, in state, ME playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      if (worldIn.isRemote) {
         return true;
      } else {
         Yg tileentity = worldIn.getTileEntity(pos);
         if (tileentity instanceof YH) {
            YH tileentitynote = (YH)tileentity;
            tileentitynote.changePitch();
            tileentitynote.triggerNote(worldIn, pos);
            playerIn.addStat(XV.NOTEBLOCK_TUNED);
         }

         return true;
      }
   }

   public void onBlockClicked(bij worldIn, BlockPos pos, ME playerIn) {
      if (!worldIn.isRemote) {
         Yg tileentity = worldIn.getTileEntity(pos);
         if (tileentity instanceof YH) {
            ((YH)tileentity).triggerNote(worldIn, pos);
            playerIn.addStat(XV.NOTEBLOCK_PLAYED);
         }
      }

   }

   public Yg createNewTileEntity(bij worldIn, int meta) {
      return new YH();
   }

   private SoundEvent getInstrument(int eventId) {
      if (eventId < 0 || eventId >= INSTRUMENTS.size()) {
         eventId = 0;
      }

      return (SoundEvent)INSTRUMENTS.get(eventId);
   }

   /** @deprecated */
   public boolean eventReceived(in state, bij worldIn, BlockPos pos, int id, int param) {
      float f = (float)Math.pow(2.0, (double)(param - 12) / 12.0);
      worldIn.playSound((ME)null, pos, this.getInstrument(id), SoundCategory.RECORDS, 3.0F, f);
      worldIn.spawnParticle(EnumParticleTypes.NOTE, (double)pos.getX() + 0.5, (double)pos.getY() + 1.2, (double)pos.getZ() + 0.5, (double)param / 24.0, 0.0, 0.0);
      return true;
   }

   /** @deprecated */
   public EnumBlockRenderType getRenderType(in state) {
      return EnumBlockRenderType.MODEL;
   }

   static {
      INSTRUMENTS = Lists.newArrayList(new SoundEvent[]{NO.BLOCK_NOTE_HARP, NO.BLOCK_NOTE_BASEDRUM, NO.BLOCK_NOTE_SNARE, NO.BLOCK_NOTE_HAT, NO.BLOCK_NOTE_BASS, NO.BLOCK_NOTE_FLUTE, NO.BLOCK_NOTE_BELL, NO.BLOCK_NOTE_GUITAR, NO.BLOCK_NOTE_CHIME, NO.BLOCK_NOTE_XYLOPHONE});
   }
}
