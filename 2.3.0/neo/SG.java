package neo;

import java.io.IOException;
import net.minecraft.util.EnumHandSide;

public class SG implements Sz<Tt> {
   private String lang;
   private int view;
   private MB chatVisibility;
   private boolean enableColors;
   private int modelPartFlags;
   private EnumHandSide mainHand;

   public SG() {
   }

   public SG(String langIn, int renderDistanceIn, MB chatVisibilityIn, boolean chatColorsIn, int modelPartsIn, EnumHandSide mainHandIn) {
      this.lang = langIn;
      this.view = renderDistanceIn;
      this.chatVisibility = chatVisibilityIn;
      this.enableColors = chatColorsIn;
      this.modelPartFlags = modelPartsIn;
      this.mainHand = mainHandIn;
   }

   public void readPacketData(SA buf) throws IOException {
      this.lang = buf.readString(16);
      this.view = buf.readByte();
      this.chatVisibility = (MB)buf.readEnumValue(MB.class);
      this.enableColors = buf.readBoolean();
      this.modelPartFlags = buf.readUnsignedByte();
      this.mainHand = (EnumHandSide)buf.readEnumValue(EnumHandSide.class);
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeString(this.lang);
      buf.writeByte(this.view);
      buf.writeEnumValue(this.chatVisibility);
      buf.writeBoolean(this.enableColors);
      buf.writeByte(this.modelPartFlags);
      buf.writeEnumValue(this.mainHand);
   }

   public void processPacket(Tt handler) {
      handler.processClientSettings(this);
   }

   public String getLang() {
      return this.lang;
   }

   public MB getChatVisibility() {
      return this.chatVisibility;
   }

   public boolean isColorsEnabled() {
      return this.enableColors;
   }

   public int getModelPartFlags() {
      return this.modelPartFlags;
   }

   public EnumHandSide getMainHand() {
      return this.mainHand;
   }

   public int getView() {
      return this.view;
   }

   public String toString() {
      return "CPacketClientSettings{lang='" + this.lang + '\'' + ", view=" + this.view + ", chatVisibility=" + this.chatVisibility + ", enableColors=" + this.enableColors + ", modelPartFlags=" + this.modelPartFlags + ", mainHand=" + this.mainHand + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Tt)var1);
   }
}
