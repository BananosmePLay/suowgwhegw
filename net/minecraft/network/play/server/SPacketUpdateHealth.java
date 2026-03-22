package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class SPacketUpdateHealth implements Packet<INetHandlerPlayClient> {
   private float health;
   private int foodLevel;
   private float saturationLevel;

   public SPacketUpdateHealth() {
   }

   public SPacketUpdateHealth(float healthIn, int foodLevelIn, float saturationLevelIn) {
      this.health = healthIn;
      this.foodLevel = foodLevelIn;
      this.saturationLevel = saturationLevelIn;
   }

   public void readPacketData(PacketBuffer buf) throws IOException {
      this.health = buf.readFloat();
      this.foodLevel = buf.readVarInt();
      this.saturationLevel = buf.readFloat();
   }

   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeFloat(this.health);
      buf.writeVarInt(this.foodLevel);
      buf.writeFloat(this.saturationLevel);
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleUpdateHealth(this);
   }

   public float getHealth() {
      return this.health;
   }

   public int getFoodLevel() {
      return this.foodLevel;
   }

   public float getSaturationLevel() {
      return this.saturationLevel;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(INetHandler var1) {
      this.processPacket((INetHandlerPlayClient)var1);
   }
}
