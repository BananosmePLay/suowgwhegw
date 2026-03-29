package neo;

import java.io.IOException;
import java.util.UUID;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class UQ implements Sz<Ts> {
   private int entityID;
   private UUID uniqueId;
   private BlockPos position;
   private EnumFacing facing;
   private String title;

   public UQ() {
   }

   public UQ(Jq painting) {
      this.entityID = painting.getEntityId();
      this.uniqueId = painting.getUniqueID();
      this.position = painting.getHangingPosition();
      this.facing = painting.facingDirection;
      this.title = painting.art.title;
   }

   public void readPacketData(SA buf) throws IOException {
      this.entityID = buf.readVarInt();
      this.uniqueId = buf.readUniqueId();
      this.title = buf.readString(Jp.MAX_NAME_LENGTH);
      this.position = buf.readBlockPos();
      this.facing = EnumFacing.byHorizontalIndex(buf.readUnsignedByte());
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeVarInt(this.entityID);
      buf.writeUniqueId(this.uniqueId);
      buf.writeString(this.title);
      buf.writeBlockPos(this.position);
      buf.writeByte(this.facing.getHorizontalIndex());
   }

   public void processPacket(Ts handler) {
      handler.handleSpawnPainting(this);
   }

   public int getEntityID() {
      return this.entityID;
   }

   public UUID getUniqueId() {
      return this.uniqueId;
   }

   public BlockPos getPosition() {
      return this.position;
   }

   public EnumFacing getFacing() {
      return this.facing;
   }

   public String getTitle() {
      return this.title;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
