package neo;

import javax.annotation.Nullable;

public class bai implements bap {
   private final in[] states;
   private final baq resizeHandler;
   private final int bits;
   private int arraySize;

   public bai(int bitsIn, baq resizeHandlerIn) {
      this.states = new in[1 << bitsIn];
      this.bits = bitsIn;
      this.resizeHandler = resizeHandlerIn;
   }

   public int idFor(in state) {
      int j;
      for(j = 0; j < this.arraySize; ++j) {
         if (this.states[j] == state) {
            return j;
         }
      }

      j = this.arraySize;
      if (j < this.states.length) {
         this.states[j] = state;
         ++this.arraySize;
         return j;
      } else {
         return this.resizeHandler.onResize(this.bits + 1, state);
      }
   }

   @Nullable
   public in getBlockState(int indexKey) {
      return indexKey >= 0 && indexKey < this.arraySize ? this.states[indexKey] : null;
   }

   public void read(SA buf) {
      this.arraySize = buf.readVarInt();

      for(int i = 0; i < this.arraySize; ++i) {
         this.states[i] = (in)co.BLOCK_STATE_IDS.getByValue(buf.readVarInt());
      }

   }

   public void write(SA buf) {
      buf.writeVarInt(this.arraySize);

      for(int i = 0; i < this.arraySize; ++i) {
         buf.writeVarInt(co.BLOCK_STATE_IDS.get(this.states[i]));
      }

   }

   public int getSerializedSize() {
      int i = SA.getVarIntSize(this.arraySize);

      for(int j = 0; j < this.arraySize; ++j) {
         i += SA.getVarIntSize(co.BLOCK_STATE_IDS.get(this.states[j]));
      }

      return i;
   }
}
