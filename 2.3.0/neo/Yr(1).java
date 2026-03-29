package neo;

public class Yr extends Yg {
   private int outputSignal;

   public Yr() {
   }

   public QQ writeToNBT(QQ compound) {
      super.writeToNBT(compound);
      compound.setInteger("OutputSignal", this.outputSignal);
      return compound;
   }

   public void readFromNBT(QQ compound) {
      super.readFromNBT(compound);
      this.outputSignal = compound.getInteger("OutputSignal");
   }

   public int getOutputSignal() {
      return this.outputSignal;
   }

   public void setOutputSignal(int outputSignalIn) {
      this.outputSignal = outputSignalIn;
   }
}
