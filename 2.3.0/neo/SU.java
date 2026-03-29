package neo;

import java.io.IOException;

public class SU implements Sz<Tt> {
   private int field_194320_a;
   private NT field_194321_b;
   private boolean field_194322_c;

   public SU() {
   }

   public SU(int p_i47614_1_, NT p_i47614_2_, boolean p_i47614_3_) {
      this.field_194320_a = p_i47614_1_;
      this.field_194321_b = p_i47614_2_;
      this.field_194322_c = p_i47614_3_;
   }

   public void readPacketData(SA buf) throws IOException {
      this.field_194320_a = buf.readByte();
      this.field_194321_b = NP.getRecipeById(buf.readVarInt());
      this.field_194322_c = buf.readBoolean();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeByte(this.field_194320_a);
      buf.writeVarInt(NP.getIDForRecipe(this.field_194321_b));
      buf.writeBoolean(this.field_194322_c);
   }

   public void processPacket(Tt handler) {
      handler.func_194308_a(this);
   }

   public int func_194318_a() {
      return this.field_194320_a;
   }

   public NT func_194317_b() {
      return this.field_194321_b;
   }

   public boolean func_194319_c() {
      return this.field_194322_c;
   }

   public String toString() {
      return "CPacketPlaceRecipe{field_194320_a=" + this.field_194320_a + ", field_194321_b=" + this.field_194321_b + ", field_194322_c=" + this.field_194322_c + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Tt)var1);
   }
}
