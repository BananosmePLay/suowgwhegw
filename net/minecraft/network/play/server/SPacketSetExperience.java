package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class SPacketSetExperience implements Packet<INetHandlerPlayClient> {
   private float experienceBar;
   private int totalExperience;
   private int level;

   public SPacketSetExperience() {
   }

   public SPacketSetExperience(float experienceBarIn, int totalExperienceIn, int levelIn) {
      this.experienceBar = experienceBarIn;
      this.totalExperience = totalExperienceIn;
      this.level = levelIn;
   }

   public void readPacketData(PacketBuffer buf) throws IOException {
      this.experienceBar = buf.readFloat();
      this.level = buf.readVarInt();
      this.totalExperience = buf.readVarInt();
   }

   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeFloat(this.experienceBar);
      buf.writeVarInt(this.level);
      buf.writeVarInt(this.totalExperience);
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleSetExperience(this);
   }

   public float getExperienceBar() {
      return this.experienceBar;
   }

   public int getTotalExperience() {
      return this.totalExperience;
   }

   public int getLevel() {
      return this.level;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(INetHandler var1) {
      this.processPacket((INetHandlerPlayClient)var1);
   }
}
