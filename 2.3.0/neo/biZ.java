package neo;

public class biZ {
   private int blockId = -1;
   private int[] metadatas = null;

   public biZ(int blockId) {
      this.blockId = blockId;
   }

   public biZ(int blockId, int metadata) {
      this.blockId = blockId;
      if (metadata >= 0 && metadata <= 15) {
         this.metadatas = new int[]{metadata};
      }

   }

   public biZ(int blockId, int[] metadatas) {
      this.blockId = blockId;
      this.metadatas = metadatas;
   }

   public int getBlockId() {
      return this.blockId;
   }

   public int[] getMetadatas() {
      return this.metadatas;
   }

   public boolean matches(ie blockState) {
      return blockState.getBlockId() != this.blockId ? false : bja.metadata(blockState.getMetadata(), this.metadatas);
   }

   public boolean matches(int id, int metadata) {
      return id != this.blockId ? false : bja.metadata(metadata, this.metadatas);
   }

   public void addMetadata(int metadata) {
      if (this.metadatas != null && metadata >= 0 && metadata <= 15) {
         for(int i = 0; i < this.metadatas.length; ++i) {
            if (this.metadatas[i] == metadata) {
               return;
            }
         }

         this.metadatas = XH.addIntToArray(this.metadatas, metadata);
      }

   }

   public String toString() {
      return "" + this.blockId + ":" + XH.arrayToString(this.metadatas);
   }
}
