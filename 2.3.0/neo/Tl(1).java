package neo;

import java.io.IOException;

public class Tl implements Sz<Tt> {
   private boolean left;
   private boolean right;

   public Tl() {
   }

   public Tl(boolean p_i46873_1_, boolean p_i46873_2_) {
      this.left = p_i46873_1_;
      this.right = p_i46873_2_;
   }

   public void readPacketData(SA buf) throws IOException {
      this.left = buf.readBoolean();
      this.right = buf.readBoolean();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeBoolean(this.left);
      buf.writeBoolean(this.right);
   }

   public void processPacket(Tt handler) {
      handler.processSteerBoat(this);
   }

   public boolean getLeft() {
      return this.left;
   }

   public boolean getRight() {
      return this.right;
   }

   public String toString() {
      return "CPacketSteerBoat{left=" + this.left + ", right=" + this.right + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Tt)var1);
   }
}
