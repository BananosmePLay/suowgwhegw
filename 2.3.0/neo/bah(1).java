package neo;

import javax.annotation.Nullable;
import net.minecraft.util.IntIdentityHashBiMap;

public class bah implements bap {
   private final IntIdentityHashBiMap<in> statePaletteMap;
   private final baq paletteResizer;
   private final int bits;

   public bah(int bitsIn, baq paletteResizerIn) {
      this.bits = bitsIn;
      this.paletteResizer = paletteResizerIn;
      this.statePaletteMap = new IntIdentityHashBiMap(1 << bitsIn);
   }

   public int idFor(in state) {
      int i = this.statePaletteMap.getId(state);
      if (i == -1) {
         i = this.statePaletteMap.add(state);
         if (i >= 1 << this.bits) {
            i = this.paletteResizer.onResize(this.bits + 1, state);
         }
      }

      return i;
   }

   @Nullable
   public in getBlockState(int indexKey) {
      return (in)this.statePaletteMap.get(indexKey);
   }

   public void read(SA buf) {
      this.statePaletteMap.clear();
      int i = buf.readVarInt();

      for(int j = 0; j < i; ++j) {
         this.statePaletteMap.add(co.BLOCK_STATE_IDS.getByValue(buf.readVarInt()));
      }

   }

   public void write(SA buf) {
      int i = this.statePaletteMap.size();
      buf.writeVarInt(i);

      for(int j = 0; j < i; ++j) {
         buf.writeVarInt(co.BLOCK_STATE_IDS.get(this.statePaletteMap.get(j)));
      }

   }

   public int getSerializedSize() {
      int i = SA.getVarIntSize(this.statePaletteMap.size());

      for(int j = 0; j < this.statePaletteMap.size(); ++j) {
         i += SA.getVarIntSize(co.BLOCK_STATE_IDS.get(this.statePaletteMap.get(j)));
      }

      return i;
   }
}
