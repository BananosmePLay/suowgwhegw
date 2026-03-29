package neo;

import java.io.IOException;

public class SS implements Sz<Tt> {
   private float strafeSpeed;
   private float forwardSpeed;
   private boolean jumping;
   private boolean sneaking;

   public SS() {
   }

   public SS(float strafeSpeedIn, float forwardSpeedIn, boolean jumpingIn, boolean sneakingIn) {
      this.strafeSpeed = strafeSpeedIn;
      this.forwardSpeed = forwardSpeedIn;
      this.jumping = jumpingIn;
      this.sneaking = sneakingIn;
   }

   public void readPacketData(SA buf) throws IOException {
      this.strafeSpeed = buf.readFloat();
      this.forwardSpeed = buf.readFloat();
      byte b0 = buf.readByte();
      this.jumping = (b0 & 1) > 0;
      this.sneaking = (b0 & 2) > 0;
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeFloat(this.strafeSpeed);
      buf.writeFloat(this.forwardSpeed);
      byte b0 = 0;
      if (this.jumping) {
         b0 = (byte)(b0 | 1);
      }

      if (this.sneaking) {
         b0 = (byte)(b0 | 2);
      }

      buf.writeByte(b0);
   }

   public void processPacket(Tt handler) {
      handler.processInput(this);
   }

   public float getStrafeSpeed() {
      return this.strafeSpeed;
   }

   public float getForwardSpeed() {
      return this.forwardSpeed;
   }

   public boolean isJumping() {
      return this.jumping;
   }

   public boolean isSneaking() {
      return this.sneaking;
   }

   public String toString() {
      return "CPacketInput{strafeSpeed=" + this.strafeSpeed + ", forwardSpeed=" + this.forwardSpeed + ", jumping=" + this.jumping + ", sneaking=" + this.sneaking + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Tt)var1);
   }
}
