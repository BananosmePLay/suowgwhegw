package neo;

public abstract class bhZ {
   public final String mapName;
   private boolean dirty;

   public bhZ(String name) {
      this.mapName = name;
   }

   public abstract void readFromNBT(QQ var1);

   public abstract QQ writeToNBT(QQ var1);

   public void markDirty() {
      this.setDirty(true);
   }

   public void setDirty(boolean isDirty) {
      this.dirty = isDirty;
   }

   public boolean isDirty() {
      return this.dirty;
   }
}
