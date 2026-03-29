package neo;

import java.io.IOException;
import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;

public class Tj implements Sz<Tt> {
   private Ti action;
   private ResourceLocation tab;

   public Tj() {
   }

   public Tj(Ti p_i47595_1_, @Nullable ResourceLocation p_i47595_2_) {
      this.action = p_i47595_1_;
      this.tab = p_i47595_2_;
   }

   public static Tj openedTab(b p_194163_0_) {
      return new Tj(Ti.OPENED_TAB, p_194163_0_.getId());
   }

   public static Tj closedScreen() {
      return new Tj(Ti.CLOSED_SCREEN, (ResourceLocation)null);
   }

   public void readPacketData(SA buf) throws IOException {
      this.action = (Ti)buf.readEnumValue(Ti.class);
      if (this.action == Ti.OPENED_TAB) {
         this.tab = buf.readResourceLocation();
      }

   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeEnumValue(this.action);
      if (this.action == Ti.OPENED_TAB) {
         buf.writeResourceLocation(this.tab);
      }

   }

   public void processPacket(Tt handler) {
      handler.handleSeenAdvancements(this);
   }

   public Ti getAction() {
      return this.action;
   }

   public ResourceLocation getTab() {
      return this.tab;
   }

   public String toString() {
      return "CPacketSeenAdvancements{action=" + this.action + ", tab=" + this.tab + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Tt)var1);
   }
}
