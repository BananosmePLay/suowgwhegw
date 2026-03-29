package neo;

import java.io.IOException;
import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;

public class UF implements Sz<Ts> {
   @Nullable
   private ResourceLocation tab;

   public UF() {
   }

   public UF(@Nullable ResourceLocation p_i47596_1_) {
      this.tab = p_i47596_1_;
   }

   public void processPacket(Ts handler) {
      handler.handleSelectAdvancementsTab(this);
   }

   public void readPacketData(SA buf) throws IOException {
      if (buf.readBoolean()) {
         this.tab = buf.readResourceLocation();
      }

   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeBoolean(this.tab != null);
      if (this.tab != null) {
         buf.writeResourceLocation(this.tab);
      }

   }

   @Nullable
   public ResourceLocation getTab() {
      return this.tab;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
