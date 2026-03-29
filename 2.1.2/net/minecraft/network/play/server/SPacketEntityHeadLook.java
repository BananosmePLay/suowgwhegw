package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.entity.Entity;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.world.World;

public class SPacketEntityHeadLook implements Packet<INetHandlerPlayClient> {
   private int entityId;
   private byte yaw;

   public SPacketEntityHeadLook() {
   }

   public SPacketEntityHeadLook(Entity entityIn, byte yawIn) {
      this.entityId = entityIn.getEntityId();
      this.yaw = yawIn;
   }

   public void readPacketData(PacketBuffer buf) throws IOException {
      this.entityId = buf.readVarInt();
      this.yaw = buf.readByte();
   }

   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeVarInt(this.entityId);
      buf.writeByte(this.yaw);
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleEntityHeadLook(this);
   }

   public Entity getEntity(World worldIn) {
      return worldIn.getEntityByID(this.entityId);
   }

   public byte getYaw() {
      return this.yaw;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(INetHandler var1) {
      this.processPacket((INetHandlerPlayClient)var1);
   }
}
