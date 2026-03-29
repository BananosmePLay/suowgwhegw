package neo;

import java.io.IOException;

public class Uf implements Sz<Ts> {
   private int entityID;
   private int motionX;
   private int motionY;
   private int motionZ;

   public Uf() {
   }

   public Uf(Ig entityIn) {
      this(entityIn.getEntityId(), entityIn.motionX, entityIn.motionY, entityIn.motionZ);
   }

   public Uf(int entityIdIn, double motionXIn, double motionYIn, double motionZIn) {
      this.entityID = entityIdIn;
      double d0 = 3.9;
      if (motionXIn < -3.9) {
         motionXIn = -3.9;
      }

      if (motionYIn < -3.9) {
         motionYIn = -3.9;
      }

      if (motionZIn < -3.9) {
         motionZIn = -3.9;
      }

      if (motionXIn > 3.9) {
         motionXIn = 3.9;
      }

      if (motionYIn > 3.9) {
         motionYIn = 3.9;
      }

      if (motionZIn > 3.9) {
         motionZIn = 3.9;
      }

      this.motionX = (int)(motionXIn * 8000.0);
      this.motionY = (int)(motionYIn * 8000.0);
      this.motionZ = (int)(motionZIn * 8000.0);
   }

   public void readPacketData(SA buf) throws IOException {
      this.entityID = buf.readVarInt();
      this.motionX = buf.readShort();
      this.motionY = buf.readShort();
      this.motionZ = buf.readShort();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeVarInt(this.entityID);
      buf.writeShort(this.motionX);
      buf.writeShort(this.motionY);
      buf.writeShort(this.motionZ);
   }

   public void processPacket(Ts handler) {
      handler.handleEntityVelocity(this);
   }

   public int getEntityID() {
      return this.entityID;
   }

   public int getMotionX() {
      return this.motionX;
   }

   public int getMotionY() {
      return this.motionY;
   }

   public int getMotionZ() {
      return this.motionZ;
   }

   public String toString() {
      return "SPacketEntityVelocity{entityID=" + this.entityID + ", motionX=" + this.motionX + ", motionY=" + this.motionY + ", motionZ=" + this.motionZ + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
