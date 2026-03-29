package neo;

import java.io.IOException;

public class Uq implements Sz<Ts> {
   private int field_194314_a;
   private NT field_194315_b;

   public Uq() {
   }

   public Uq(int p_i47615_1_, NT p_i47615_2_) {
      this.field_194314_a = p_i47615_1_;
      this.field_194315_b = p_i47615_2_;
   }

   public NT func_194311_a() {
      return this.field_194315_b;
   }

   public int func_194313_b() {
      return this.field_194314_a;
   }

   public void readPacketData(SA buf) throws IOException {
      this.field_194314_a = buf.readByte();
      this.field_194315_b = NP.getRecipeById(buf.readVarInt());
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeByte(this.field_194314_a);
      buf.writeVarInt(NP.getIDForRecipe(this.field_194315_b));
   }

   public void processPacket(Ts handler) {
      handler.func_194307_a(this);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
