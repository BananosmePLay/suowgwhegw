package neo;

import java.io.IOException;

public class TX implements Sz<Ts> {
   private int entityId;
   private byte effectId;
   private byte amplifier;
   private int duration;
   private byte flags;

   public TX() {
   }

   public TX(int entityIdIn, VZ effect) {
      this.entityId = entityIdIn;
      this.effectId = (byte)(VW.getIdFromPotion(effect.getPotion()) & 255);
      this.amplifier = (byte)(effect.getAmplifier() & 255);
      if (effect.getDuration() > 32767) {
         this.duration = 32767;
      } else {
         this.duration = effect.getDuration();
      }

      this.flags = 0;
      if (effect.getIsAmbient()) {
         this.flags = (byte)(this.flags | 1);
      }

      if (effect.doesShowParticles()) {
         this.flags = (byte)(this.flags | 2);
      }

   }

   public void readPacketData(SA buf) throws IOException {
      this.entityId = buf.readVarInt();
      this.effectId = buf.readByte();
      this.amplifier = buf.readByte();
      this.duration = buf.readVarInt();
      this.flags = buf.readByte();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeVarInt(this.entityId);
      buf.writeByte(this.effectId);
      buf.writeByte(this.amplifier);
      buf.writeVarInt(this.duration);
      buf.writeByte(this.flags);
   }

   public boolean isMaxDuration() {
      return this.duration == 32767;
   }

   public void processPacket(Ts handler) {
      handler.handleEntityEffect(this);
   }

   public int getEntityId() {
      return this.entityId;
   }

   public byte getEffectId() {
      return this.effectId;
   }

   public byte getAmplifier() {
      return this.amplifier;
   }

   public int getDuration() {
      return this.duration;
   }

   public boolean doesShowParticles() {
      return (this.flags & 2) == 2;
   }

   public boolean getIsAmbient() {
      return (this.flags & 1) == 1;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
