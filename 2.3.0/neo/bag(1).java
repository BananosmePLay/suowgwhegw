package neo;

import javax.annotation.Nullable;
import net.minecraft.util.BitArray;
import net.minecraft.util.math.MathHelper;

public class bag implements baq {
   private static final bap REGISTRY_BASED_PALETTE = new baj();
   protected static final in AIR_BLOCK_STATE;
   protected BitArray storage;
   protected bap palette;
   private int bits;

   public bag() {
      this.setBits(4);
   }

   private static int getIndex(int x, int y, int z) {
      return y << 8 | z << 4 | x;
   }

   private void setBits(int bitsIn) {
      if (bitsIn != this.bits) {
         this.bits = bitsIn;
         if (this.bits <= 4) {
            this.bits = 4;
            this.palette = new bai(this.bits, this);
         } else if (this.bits <= 8) {
            this.palette = new bah(this.bits, this);
         } else {
            this.palette = REGISTRY_BASED_PALETTE;
            this.bits = MathHelper.log2DeBruijn(co.BLOCK_STATE_IDS.size());
         }

         this.palette.idFor(AIR_BLOCK_STATE);
         this.storage = new BitArray(this.bits, 4096);
      }

   }

   public int onResize(int bits, in state) {
      BitArray bitarray = this.storage;
      bap iblockstatepalette = this.palette;
      this.setBits(bits);

      for(int i = 0; i < bitarray.size(); ++i) {
         in iblockstate = iblockstatepalette.getBlockState(bitarray.getAt(i));
         if (iblockstate != null) {
            this.set(i, iblockstate);
         }
      }

      return this.palette.idFor(state);
   }

   public void set(int x, int y, int z, in state) {
      this.set(getIndex(x, y, z), state);
   }

   protected void set(int index, in state) {
      int i = this.palette.idFor(state);
      this.storage.setAt(index, i);
   }

   public in get(int x, int y, int z) {
      return this.get(getIndex(x, y, z));
   }

   protected in get(int index) {
      in iblockstate = this.palette.getBlockState(this.storage.getAt(index));
      return iblockstate == null ? AIR_BLOCK_STATE : iblockstate;
   }

   public void read(SA buf) {
      int i = buf.readByte();
      if (this.bits != i) {
         this.setBits(i);
      }

      this.palette.read(buf);
      buf.readLongArray(this.storage.getBackingLongArray());
   }

   public void write(SA buf) {
      buf.writeByte(this.bits);
      this.palette.write(buf);
      buf.writeLongArray(this.storage.getBackingLongArray());
   }

   @Nullable
   public bas getDataForNBT(byte[] blockIds, bas data) {
      bas nibblearray = null;

      for(int i = 0; i < 4096; ++i) {
         int j = co.BLOCK_STATE_IDS.get(this.get(i));
         int k = i & 15;
         int l = i >> 8 & 15;
         int i1 = i >> 4 & 15;
         if ((j >> 12 & 15) != 0) {
            if (nibblearray == null) {
               nibblearray = new bas();
            }

            nibblearray.set(k, l, i1, j >> 12 & 15);
         }

         blockIds[i] = (byte)(j >> 4 & 255);
         data.set(k, l, i1, j & 15);
      }

      return nibblearray;
   }

   public void setDataFromNBT(byte[] blockIds, bas data, @Nullable bas blockIdExtension) {
      for(int i = 0; i < 4096; ++i) {
         int j = i & 15;
         int k = i >> 8 & 15;
         int l = i >> 4 & 15;
         int i1 = blockIdExtension == null ? 0 : blockIdExtension.get(j, k, l);
         int j1 = i1 << 12 | (blockIds[i] & 255) << 4 | data.get(j, k, l);
         this.set(i, (in)co.BLOCK_STATE_IDS.getByValue(j1));
      }

   }

   public int getSerializedSize() {
      return 1 + this.palette.getSerializedSize() + SA.getVarIntSize(this.storage.size()) + this.storage.getBackingLongArray().length * 8;
   }

   static {
      AIR_BLOCK_STATE = Nk.AIR.getDefaultState();
   }
}
