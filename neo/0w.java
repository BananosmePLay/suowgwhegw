package neo;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class 0w extends 0n {
   public EnumFacing face;
   public BlockPos pos;

   public BlockPos getPos() {
      return T6AE81taWS(this);
   }

   public _w/* $FF was: 0w*/(BlockPos pos, EnumFacing face) {
      this.pos = pos;
      this.face = face;
   }

   private static void NSvFEgFWdI(0w var0, BlockPos var1) {
      var0.pos = var1;
   }

   private static BlockPos T6AE81taWS(0w var0) {
      return var0.pos;
   }

   private static EnumFacing BNmJT4IrJD(0w var0) {
      return var0.face;
   }

   public void setFace(EnumFacing face) {
      qJYzBLGoJO(this, face);
   }

   private static void qJYzBLGoJO(0w var0, EnumFacing var1) {
      var0.face = var1;
   }

   public EnumFacing getFace() {
      return BNmJT4IrJD(this);
   }

   public void setPos(BlockPos pos) {
      NSvFEgFWdI(this, pos);
   }
}
