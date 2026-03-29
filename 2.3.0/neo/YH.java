package neo;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class YH extends Yg {
   public byte note;
   public boolean previousRedstoneState;

   public YH() {
   }

   public QQ writeToNBT(QQ compound) {
      super.writeToNBT(compound);
      compound.setByte("note", this.note);
      compound.setBoolean("powered", this.previousRedstoneState);
      return compound;
   }

   public void readFromNBT(QQ compound) {
      super.readFromNBT(compound);
      this.note = compound.getByte("note");
      this.note = (byte)MathHelper.clamp(this.note, 0, 24);
      this.previousRedstoneState = compound.getBoolean("powered");
   }

   public void changePitch() {
      this.note = (byte)((this.note + 1) % 25);
      this.markDirty();
   }

   public void triggerNote(bij worldIn, BlockPos posIn) {
      if (worldIn.getBlockState(posIn.up()).getMaterial() == hM.AIR) {
         in iblockstate = worldIn.getBlockState(posIn.down());
         hM material = iblockstate.getMaterial();
         int i = 0;
         if (material == hM.ROCK) {
            i = 1;
         }

         if (material == hM.SAND) {
            i = 2;
         }

         if (material == hM.GLASS) {
            i = 3;
         }

         if (material == hM.WOOD) {
            i = 4;
         }

         co block = iblockstate.getBlock();
         if (block == Nk.CLAY) {
            i = 5;
         }

         if (block == Nk.GOLD_BLOCK) {
            i = 6;
         }

         if (block == Nk.WOOL) {
            i = 7;
         }

         if (block == Nk.PACKED_ICE) {
            i = 8;
         }

         if (block == Nk.BONE_BLOCK) {
            i = 9;
         }

         worldIn.addBlockEvent(posIn, Nk.NOTEBLOCK, i, this.note);
      }

   }
}
