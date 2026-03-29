package neo;

import java.io.IOException;

public class TC implements Sz<Ts> {
   public static final String[] MESSAGE_NAMES = new String[]{"tile.bed.notValid"};
   private int state;
   private float value;

   public TC() {
   }

   public TC(int stateIn, float valueIn) {
      this.state = stateIn;
      this.value = valueIn;
   }

   public void readPacketData(SA buf) throws IOException {
      this.state = buf.readUnsignedByte();
      this.value = buf.readFloat();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeByte(this.state);
      buf.writeFloat(this.value);
   }

   public void processPacket(Ts handler) {
      handler.handleChangeGameState(this);
   }

   public int getGameState() {
      return this.state;
   }

   public float getValue() {
      return this.value;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
