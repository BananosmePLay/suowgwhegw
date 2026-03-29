package neo;

import java.io.IOException;
import net.minecraft.util.math.ChunkPos;

public class Un implements Sz<Ts> {
   public ChunkPos chunkPos;
   private Um[] changedBlocks;

   public Un() {
   }

   public Un(int p_i46959_1_, short[] p_i46959_2_, bam p_i46959_3_) {
      this.chunkPos = new ChunkPos(p_i46959_3_.x, p_i46959_3_.z);
      this.changedBlocks = new Um[p_i46959_1_];

      for(int i = 0; i < this.changedBlocks.length; ++i) {
         this.changedBlocks[i] = new Um(this, p_i46959_2_[i], p_i46959_3_);
      }

   }

   public void readPacketData(SA buf) throws IOException {
      this.chunkPos = new ChunkPos(buf.readInt(), buf.readInt());
      this.changedBlocks = new Um[buf.readVarInt()];

      for(int i = 0; i < this.changedBlocks.length; ++i) {
         this.changedBlocks[i] = new Um(this, buf.readShort(), (in)co.BLOCK_STATE_IDS.getByValue(buf.readVarInt()));
      }

   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeInt(this.chunkPos.x);
      buf.writeInt(this.chunkPos.z);
      buf.writeVarInt(this.changedBlocks.length);
      Um[] var2 = this.changedBlocks;
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         Um spacketmultiblockchange$blockupdatedata = var2[var4];
         buf.writeShort(spacketmultiblockchange$blockupdatedata.getOffset());
         buf.writeVarInt(co.BLOCK_STATE_IDS.get(spacketmultiblockchange$blockupdatedata.getBlockState()));
      }

   }

   public void processPacket(Ts handler) {
      handler.handleMultiBlockChange(this);
   }

   public Um[] getChangedBlocks() {
      return this.changedBlocks;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
