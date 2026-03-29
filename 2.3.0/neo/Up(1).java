package neo;

import java.io.IOException;
import java.util.Arrays;
import net.minecraft.util.EnumParticleTypes;

public class Up implements Sz<Ts> {
   private EnumParticleTypes particleType;
   private float xCoord;
   private float yCoord;
   private float zCoord;
   private float xOffset;
   private float yOffset;
   private float zOffset;
   private float particleSpeed;
   private int particleCount;
   private boolean longDistance;
   private int[] particleArguments;

   public Up() {
   }

   public Up(EnumParticleTypes particleIn, boolean longDistanceIn, float xIn, float yIn, float zIn, float xOffsetIn, float yOffsetIn, float zOffsetIn, float speedIn, int countIn, int... argumentsIn) {
      this.particleType = particleIn;
      this.longDistance = longDistanceIn;
      this.xCoord = xIn;
      this.yCoord = yIn;
      this.zCoord = zIn;
      this.xOffset = xOffsetIn;
      this.yOffset = yOffsetIn;
      this.zOffset = zOffsetIn;
      this.particleSpeed = speedIn;
      this.particleCount = countIn;
      this.particleArguments = argumentsIn;
   }

   public void readPacketData(SA buf) throws IOException {
      this.particleType = EnumParticleTypes.getParticleFromId(buf.readInt());
      if (this.particleType == null) {
         this.particleType = EnumParticleTypes.BARRIER;
      }

      this.longDistance = buf.readBoolean();
      this.xCoord = buf.readFloat();
      this.yCoord = buf.readFloat();
      this.zCoord = buf.readFloat();
      this.xOffset = buf.readFloat();
      this.yOffset = buf.readFloat();
      this.zOffset = buf.readFloat();
      this.particleSpeed = buf.readFloat();
      this.particleCount = buf.readInt();
      int i = this.particleType.getArgumentCount();
      this.particleArguments = new int[i];

      for(int j = 0; j < i; ++j) {
         this.particleArguments[j] = buf.readVarInt();
      }

   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeInt(this.particleType.getParticleID());
      buf.writeBoolean(this.longDistance);
      buf.writeFloat(this.xCoord);
      buf.writeFloat(this.yCoord);
      buf.writeFloat(this.zCoord);
      buf.writeFloat(this.xOffset);
      buf.writeFloat(this.yOffset);
      buf.writeFloat(this.zOffset);
      buf.writeFloat(this.particleSpeed);
      buf.writeInt(this.particleCount);
      int i = this.particleType.getArgumentCount();

      for(int j = 0; j < i; ++j) {
         buf.writeVarInt(this.particleArguments[j]);
      }

   }

   public EnumParticleTypes getParticleType() {
      return this.particleType;
   }

   public boolean isLongDistance() {
      return this.longDistance;
   }

   public double getXCoordinate() {
      return (double)this.xCoord;
   }

   public double getYCoordinate() {
      return (double)this.yCoord;
   }

   public double getZCoordinate() {
      return (double)this.zCoord;
   }

   public float getXOffset() {
      return this.xOffset;
   }

   public float getYOffset() {
      return this.yOffset;
   }

   public float getZOffset() {
      return this.zOffset;
   }

   public float getParticleSpeed() {
      return this.particleSpeed;
   }

   public int getParticleCount() {
      return this.particleCount;
   }

   public int[] getParticleArgs() {
      return this.particleArguments;
   }

   public void processPacket(Ts handler) {
      handler.handleParticles(this);
   }

   public String toString() {
      return "SPacketParticles{particleType=" + this.particleType + ", xCoord=" + this.xCoord + ", yCoord=" + this.yCoord + ", zCoord=" + this.zCoord + ", xOffset=" + this.xOffset + ", yOffset=" + this.yOffset + ", zOffset=" + this.zOffset + ", particleSpeed=" + this.particleSpeed + ", particleCount=" + this.particleCount + ", longDistance=" + this.longDistance + ", particleArguments=" + Arrays.toString(this.particleArguments) + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
