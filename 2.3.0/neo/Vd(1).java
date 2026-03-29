package neo;

import java.io.IOException;

public class Vd implements Sz<Ts> {
   private float health;
   private int foodLevel;
   private float saturationLevel;

   public Vd() {
   }

   public Vd(float healthIn, int foodLevelIn, float saturationLevelIn) {
      this.health = healthIn;
      this.foodLevel = foodLevelIn;
      this.saturationLevel = saturationLevelIn;
   }

   public void readPacketData(SA buf) throws IOException {
      this.health = buf.readFloat();
      this.foodLevel = buf.readVarInt();
      this.saturationLevel = buf.readFloat();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeFloat(this.health);
      buf.writeVarInt(this.foodLevel);
      buf.writeFloat(this.saturationLevel);
   }

   public void processPacket(Ts handler) {
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
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
