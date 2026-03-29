package neo;

import java.io.IOException;

public class Tf implements Sz<Tt> {
   private Te purpose;
   private NT recipe;
   private boolean isGuiOpen;
   private boolean filteringCraftable;

   public Tf() {
   }

   public Tf(NT p_i47518_1_) {
      this.purpose = Te.SHOWN;
      this.recipe = p_i47518_1_;
   }

   public Tf(boolean p_i47424_1_, boolean p_i47424_2_) {
      this.purpose = Te.SETTINGS;
      this.isGuiOpen = p_i47424_1_;
      this.filteringCraftable = p_i47424_2_;
   }

   public void readPacketData(SA buf) throws IOException {
      this.purpose = (Te)buf.readEnumValue(Te.class);
      if (this.purpose == Te.SHOWN) {
         this.recipe = NP.getRecipeById(buf.readInt());
      } else if (this.purpose == Te.SETTINGS) {
         this.isGuiOpen = buf.readBoolean();
         this.filteringCraftable = buf.readBoolean();
      }

   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeEnumValue(this.purpose);
      if (this.purpose == Te.SHOWN) {
         buf.writeInt(NP.getIDForRecipe(this.recipe));
      } else if (this.purpose == Te.SETTINGS) {
         buf.writeBoolean(this.isGuiOpen);
         buf.writeBoolean(this.filteringCraftable);
      }

   }

   public void processPacket(Tt handler) {
      handler.handleRecipeBookUpdate(this);
   }

   public Te getPurpose() {
      return this.purpose;
   }

   public NT getRecipe() {
      return this.recipe;
   }

   public boolean isGuiOpen() {
      return this.isGuiOpen;
   }

   public boolean isFilteringCraftable() {
      return this.filteringCraftable;
   }

   public String toString() {
      return "CPacketRecipeInfo{purpose=" + this.purpose + ", recipe=" + this.recipe + ", isGuiOpen=" + this.isGuiOpen + ", filteringCraftable=" + this.filteringCraftable + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Tt)var1);
   }
}
