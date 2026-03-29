package neo;

public class Yk extends Yg {
   private Om color;

   public Yk() {
      this.color = Om.RED;
   }

   public void setItemValues(Qy p_193051_1_) {
      this.setColor(Om.byMetadata(p_193051_1_.getMetadata()));
   }

   public void readFromNBT(QQ compound) {
      super.readFromNBT(compound);
      if (compound.hasKey("color")) {
         this.color = Om.byMetadata(compound.getInteger("color"));
      }

   }

   public QQ writeToNBT(QQ compound) {
      super.writeToNBT(compound);
      compound.setInteger("color", this.color.getMetadata());
      return compound;
   }

   public QQ getUpdateTag() {
      return this.writeToNBT(new QQ());
   }

   public Vg getUpdatePacket() {
      return new Vg(this.pos, 11, this.getUpdateTag());
   }

   public Om getColor() {
      return this.color;
   }

   public void setColor(Om color) {
      this.color = color;
      this.markDirty();
   }

   public boolean isHeadPiece() {
      return cC.isHeadPiece(this.getBlockMetadata());
   }

   public Qy getItemStack() {
      return new Qy(NK.BED, 1, this.color.getMetadata());
   }
}
