package neo;

import java.io.IOException;

public class UH implements Sz<Ts> {
   private float experienceBar;
   private int totalExperience;
   private int level;

   public UH() {
   }

   public UH(float experienceBarIn, int totalExperienceIn, int levelIn) {
      this.experienceBar = experienceBarIn;
      this.totalExperience = totalExperienceIn;
      this.level = levelIn;
   }

   public void readPacketData(SA buf) throws IOException {
      this.experienceBar = buf.readFloat();
      this.level = buf.readVarInt();
      this.totalExperience = buf.readVarInt();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeFloat(this.experienceBar);
      buf.writeVarInt(this.level);
      buf.writeVarInt(this.totalExperience);
   }

   public void processPacket(Ts handler) {
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

   public String toString() {
      return "SPacketSetExperience{experienceBar=" + this.experienceBar + ", totalExperience=" + this.totalExperience + ", level=" + this.level + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
