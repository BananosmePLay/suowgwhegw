package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.entity.Entity;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.world.World;

public class SPacketEntityStatus implements Packet<INetHandlerPlayClient> {
   private int entityId;
   private byte logicOpcode;

   public SPacketEntityStatus() {
   }

   public SPacketEntityStatus(Entity entityIn, byte opcodeIn) {
      this.entityId = entityIn.getEntityId();
      this.logicOpcode = opcodeIn;
   }

   public void readPacketData(PacketBuffer buf) throws IOException {
      this.entityId = buf.readInt();
      this.logicOpcode = buf.readByte();
   }

   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeInt(this.entityId);
      buf.writeByte(this.logicOpcode);
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleEntityStatus(this);
   }

   public Entity getEntity(World worldIn) {
      return worldIn.getEntityByID(this.entityId);
   }

   public byte getOpCode() {
      return this.logicOpcode;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(INetHandler var1) {
      this.processPacket((INetHandlerPlayClient)var1);
   }
}
