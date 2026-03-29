package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class SPacketSpawnExperienceOrb implements Packet<INetHandlerPlayClient> {
   private int entityID;
   private double posX;
   private double posY;
   private double posZ;
   private int xpValue;

   public SPacketSpawnExperienceOrb() {
   }

   public SPacketSpawnExperienceOrb(EntityXPOrb orb) {
      this.entityID = orb.getEntityId();
      this.posX = orb.posX;
      this.posY = orb.posY;
      this.posZ = orb.posZ;
      this.xpValue = orb.getXpValue();
   }

   public void readPacketData(PacketBuffer buf) throws IOException {
      this.entityID = buf.readVarInt();
      this.posX = buf.readDouble();
      this.posY = buf.readDouble();
      this.posZ = buf.readDouble();
      this.xpValue = buf.readShort();
   }

   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeVarInt(this.entityID);
      buf.writeDouble(this.posX);
      buf.writeDouble(this.posY);
      buf.writeDouble(this.posZ);
      buf.writeShort(this.xpValue);
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleSpawnExperienceOrb(this);
   }

   public int getEntityID() {
      return this.entityID;
   }

   public double getX() {
      return this.posX;
   }

   public double getY() {
      return this.posY;
   }

   public double getZ() {
      return this.posZ;
   }

   public int getXPValue() {
      return this.xpValue;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(INetHandler var1) {
      this.processPacket((INetHandlerPlayClient)var1);
   }
}
