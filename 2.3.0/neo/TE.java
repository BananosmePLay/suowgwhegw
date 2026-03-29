package neo;

import com.google.common.collect.Lists;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.util.math.BlockPos;

public class TE implements Sz<Ts> {
   private int chunkX;
   private int chunkZ;
   private int availableSections;
   private byte[] buffer;
   private List<QQ> tileEntityTags;
   private boolean fullChunk;

   public TE() {
   }

   public TE(bam chunkIn, int changedSectionFilter) {
      this.chunkX = chunkIn.x;
      this.chunkZ = chunkIn.z;
      this.fullChunk = changedSectionFilter == 65535;
      boolean flag = chunkIn.getWorld().provider.hasSkyLight();
      this.buffer = new byte[this.calculateChunkSize(chunkIn, flag, changedSectionFilter)];
      this.availableSections = this.extractChunkData(new SA(this.getWriteBuffer()), chunkIn, flag, changedSectionFilter);
      this.tileEntityTags = Lists.newArrayList();
      Iterator var4 = chunkIn.getTileEntityMap().entrySet().iterator();

      while(true) {
         Yg tileentity;
         int i;
         do {
            if (!var4.hasNext()) {
               return;
            }

            Map.Entry<BlockPos, Yg> entry = (Map.Entry)var4.next();
            BlockPos blockpos = (BlockPos)entry.getKey();
            tileentity = (Yg)entry.getValue();
            i = blockpos.getY() >> 4;
         } while(!this.isFullChunk() && (changedSectionFilter & 1 << i) == 0);

         QQ nbttagcompound = tileentity.getUpdateTag();
         this.tileEntityTags.add(nbttagcompound);
      }
   }

   public void readPacketData(SA buf) throws IOException {
      this.chunkX = buf.readInt();
      this.chunkZ = buf.readInt();
      this.fullChunk = buf.readBoolean();
      this.availableSections = buf.readVarInt();
      int i = buf.readVarInt();
      if (i > 2097152) {
         throw new RuntimeException("Chunk Packet trying to allocate too much memory on read.");
      } else {
         this.buffer = new byte[i];
         buf.readBytes(this.buffer);
         int j = buf.readVarInt();
         this.tileEntityTags = Lists.newArrayList();

         for(int k = 0; k < j; ++k) {
            this.tileEntityTags.add(buf.readCompoundTag());
         }

      }
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeInt(this.chunkX);
      buf.writeInt(this.chunkZ);
      buf.writeBoolean(this.fullChunk);
      buf.writeVarInt(this.availableSections);
      buf.writeVarInt(this.buffer.length);
      buf.writeBytes(this.buffer);
      buf.writeVarInt(this.tileEntityTags.size());
      Iterator var2 = this.tileEntityTags.iterator();

      while(var2.hasNext()) {
         QQ nbttagcompound = (QQ)var2.next();
         buf.writeCompoundTag(nbttagcompound);
      }

   }

   public void processPacket(Ts handler) {
      handler.handleChunkData(this);
   }

   public SA getReadBuffer() {
      return new SA(Unpooled.wrappedBuffer(this.buffer));
   }

   private ByteBuf getWriteBuffer() {
      ByteBuf bytebuf = Unpooled.wrappedBuffer(this.buffer);
      bytebuf.writerIndex(0);
      return bytebuf;
   }

   public int extractChunkData(SA buf, bam chunkIn, boolean writeSkylight, int changedSectionFilter) {
      int i = 0;
      baB[] aextendedblockstorage = chunkIn.getBlockStorageArray();
      int j = 0;

      for(int k = aextendedblockstorage.length; j < k; ++j) {
         baB extendedblockstorage = aextendedblockstorage[j];
         if (extendedblockstorage != bam.NULL_BLOCK_STORAGE && (!this.isFullChunk() || !extendedblockstorage.isEmpty()) && (changedSectionFilter & 1 << j) != 0) {
            i |= 1 << j;
            extendedblockstorage.getData().write(buf);
            buf.writeBytes(extendedblockstorage.getBlockLight().getData());
            if (writeSkylight) {
               buf.writeBytes(extendedblockstorage.getSkyLight().getData());
            }
         }
      }

      if (this.isFullChunk()) {
         buf.writeBytes(chunkIn.getBiomeArray());
      }

      return i;
   }

   protected int calculateChunkSize(bam chunkIn, boolean p_189556_2_, int p_189556_3_) {
      int i = 0;
      baB[] aextendedblockstorage = chunkIn.getBlockStorageArray();
      int j = 0;

      for(int k = aextendedblockstorage.length; j < k; ++j) {
         baB extendedblockstorage = aextendedblockstorage[j];
         if (extendedblockstorage != bam.NULL_BLOCK_STORAGE && (!this.isFullChunk() || !extendedblockstorage.isEmpty()) && (p_189556_3_ & 1 << j) != 0) {
            i += extendedblockstorage.getData().getSerializedSize();
            i += extendedblockstorage.getBlockLight().getData().length;
            if (p_189556_2_) {
               i += extendedblockstorage.getSkyLight().getData().length;
            }
         }
      }

      if (this.isFullChunk()) {
         i += chunkIn.getBiomeArray().length;
      }

      return i;
   }

   public int getChunkX() {
      return this.chunkX;
   }

   public int getChunkZ() {
      return this.chunkZ;
   }

   public int getExtractedSize() {
      return this.availableSections;
   }

   public boolean isFullChunk() {
      return this.fullChunk;
   }

   public List<QQ> getTileEntityTags() {
      return this.tileEntityTags;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
