package neo;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;

public class UR implements Sz<Ts> {
   private int entityId;
   private UUID uniqueId;
   private double x;
   private double y;
   private double z;
   private byte yaw;
   private byte pitch;
   private Rv watcher;
   private List<Ru<?>> dataManagerEntries;

   public UR() {
   }

   public UR(ME player) {
      this.entityId = player.getEntityId();
      this.uniqueId = player.getGameProfile().getId();
      this.x = player.posX;
      this.y = player.posY;
      this.z = player.posZ;
      this.yaw = (byte)((int)(player.rotationYaw * 256.0F / 360.0F));
      this.pitch = (byte)((int)(player.rotationPitch * 256.0F / 360.0F));
      this.watcher = player.getDataManager();
   }

   public void readPacketData(SA buf) throws IOException {
      this.entityId = buf.readVarInt();
      this.uniqueId = buf.readUniqueId();
      this.x = buf.readDouble();
      this.y = buf.readDouble();
      this.z = buf.readDouble();
      this.yaw = buf.readByte();
      this.pitch = buf.readByte();
      this.dataManagerEntries = Rv.readEntries(buf);
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeVarInt(this.entityId);
      buf.writeUniqueId(this.uniqueId);
      buf.writeDouble(this.x);
      buf.writeDouble(this.y);
      buf.writeDouble(this.z);
      buf.writeByte(this.yaw);
      buf.writeByte(this.pitch);
      this.watcher.writeEntries(buf);
   }

   public void processPacket(Ts handler) {
      handler.handleSpawnPlayer(this);
   }

   @Nullable
   public List<Ru<?>> getDataManagerEntries() {
      return this.dataManagerEntries;
   }

   public int getEntityID() {
      return this.entityId;
   }

   public UUID getUniqueId() {
      return this.uniqueId;
   }

   public double getX() {
      return this.x;
   }

   public double getY() {
      return this.y;
   }

   public double getZ() {
      return this.z;
   }

   public byte getYaw() {
      return this.yaw;
   }

   public byte getPitch() {
      return this.pitch;
   }

   public String toString() {
      return "SPacketSpawnPlayer{entityId=" + this.entityId + ", uniqueId=" + this.uniqueId + ", x=" + this.x + ", y=" + this.y + ", z=" + this.z + ", yaw=" + this.yaw + ", pitch=" + this.pitch + ", watcher=" + this.watcher + ", dataManagerEntries=" + this.dataManagerEntries + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
