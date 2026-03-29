package neo;

import java.io.IOException;
import net.minecraft.util.math.BlockPos;

public class Vg implements Sz<Ts> {
   private BlockPos blockPos;
   private int tileEntityType;
   private QQ nbt;

   public Vg() {
   }

   public Vg(BlockPos blockPosIn, int tileEntityTypeIn, QQ compoundIn) {
      this.blockPos = blockPosIn;
      this.tileEntityType = tileEntityTypeIn;
      this.nbt = compoundIn;
   }

   public void readPacketData(SA buf) throws IOException {
      this.blockPos = buf.readBlockPos();
      this.tileEntityType = buf.readUnsignedByte();
      this.nbt = buf.readCompoundTag();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeBlockPos(this.blockPos);
      buf.writeByte((byte)this.tileEntityType);
      buf.writeCompoundTag(this.nbt);
   }

   public void processPacket(Ts handler) {
      handler.handleUpdateTileEntity(this);
   }

   public BlockPos getPos() {
      return this.blockPos;
   }

   public int getTileEntityType() {
      return this.tileEntityType;
   }

   public QQ getNbtCompound() {
      return this.nbt;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
