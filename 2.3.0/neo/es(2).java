package neo;

public class es extends Yg {
   private Qy record;

   public es() {
      this.record = Qy.EMPTY;
   }

   public void readFromNBT(QQ compound) {
      super.readFromNBT(compound);
      if (compound.hasKey("RecordItem", 10)) {
         this.setRecord(new Qy(compound.getCompoundTag("RecordItem")));
      } else if (compound.getInteger("Record") > 0) {
         this.setRecord(new Qy(OL.getItemById(compound.getInteger("Record"))));
      }

   }

   public QQ writeToNBT(QQ compound) {
      super.writeToNBT(compound);
      if (!this.getRecord().isEmpty()) {
         compound.setTag("RecordItem", this.getRecord().writeToNBT(new QQ()));
      }

      return compound;
   }

   public Qy getRecord() {
      return this.record;
   }

   public void setRecord(Qy recordStack) {
      this.record = recordStack;
      this.markDirty();
   }
}
