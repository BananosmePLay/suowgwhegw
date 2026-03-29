package neo;

public class baj implements bap {
   public baj() {
   }

   public int idFor(in state) {
      int i = co.BLOCK_STATE_IDS.get(state);
      return i == -1 ? 0 : i;
   }

   public in getBlockState(int indexKey) {
      in iblockstate = (in)co.BLOCK_STATE_IDS.getByValue(indexKey);
      return iblockstate == null ? Nk.AIR.getDefaultState() : iblockstate;
   }

   public void read(SA buf) {
      buf.readVarInt();
   }

   public void write(SA buf) {
      buf.writeVarInt(0);
   }

   public int getSerializedSize() {
      return SA.getVarIntSize(0);
   }
}
