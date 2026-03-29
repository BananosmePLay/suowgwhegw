package neo;

public class bds extends bhZ {
   private QQ tagCompound = new QQ();

   public bds(String name) {
      super(name);
   }

   public void readFromNBT(QQ nbt) {
      this.tagCompound = nbt.getCompoundTag("Features");
   }

   public QQ writeToNBT(QQ compound) {
      compound.setTag("Features", this.tagCompound);
      return compound;
   }

   public void writeInstance(QQ tagCompoundIn, int chunkX, int chunkZ) {
      this.tagCompound.setTag(formatChunkCoords(chunkX, chunkZ), tagCompoundIn);
   }

   public static String formatChunkCoords(int chunkX, int chunkZ) {
      return "[" + chunkX + "," + chunkZ + "]";
   }

   public QQ getTagCompound() {
      return this.tagCompound;
   }
}
