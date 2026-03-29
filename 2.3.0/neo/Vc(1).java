package neo;

import java.io.IOException;
import java.util.UUID;
import net.minecraft.util.text.ITextComponent;

public class Vc implements Sz<Ts> {
   private UUID uniqueId;
   private Vb operation;
   private ITextComponent name;
   private float percent;
   private bac color;
   private bad overlay;
   private boolean darkenSky;
   private boolean playEndBossMusic;
   private boolean createFog;

   public Vc() {
   }

   public Vc(Vb operationIn, bae data) {
      this.operation = operationIn;
      this.uniqueId = data.getUniqueId();
      this.name = data.getName();
      this.percent = data.getPercent();
      this.color = data.getColor();
      this.overlay = data.getOverlay();
      this.darkenSky = data.shouldDarkenSky();
      this.playEndBossMusic = data.shouldPlayEndBossMusic();
      this.createFog = data.shouldCreateFog();
   }

   public void readPacketData(SA buf) throws IOException {
      this.uniqueId = buf.readUniqueId();
      this.operation = (Vb)buf.readEnumValue(Vb.class);
      switch (this.operation) {
         case ADD:
            this.name = buf.readTextComponent();
            this.percent = buf.readFloat();
            this.color = (bac)buf.readEnumValue(bac.class);
            this.overlay = (bad)buf.readEnumValue(bad.class);
            this.setFlags(buf.readUnsignedByte());
         case REMOVE:
         default:
            break;
         case UPDATE_PCT:
            this.percent = buf.readFloat();
            break;
         case UPDATE_NAME:
            this.name = buf.readTextComponent();
            break;
         case UPDATE_STYLE:
            this.color = (bac)buf.readEnumValue(bac.class);
            this.overlay = (bad)buf.readEnumValue(bad.class);
            break;
         case UPDATE_PROPERTIES:
            this.setFlags(buf.readUnsignedByte());
      }

   }

   private void setFlags(int flags) {
      this.darkenSky = (flags & 1) > 0;
      this.playEndBossMusic = (flags & 2) > 0;
      this.createFog = (flags & 2) > 0;
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeUniqueId(this.uniqueId);
      buf.writeEnumValue(this.operation);
      switch (this.operation) {
         case ADD:
            buf.writeTextComponent(this.name);
            buf.writeFloat(this.percent);
            buf.writeEnumValue(this.color);
            buf.writeEnumValue(this.overlay);
            buf.writeByte(this.getFlags());
         case REMOVE:
         default:
            break;
         case UPDATE_PCT:
            buf.writeFloat(this.percent);
            break;
         case UPDATE_NAME:
            buf.writeTextComponent(this.name);
            break;
         case UPDATE_STYLE:
            buf.writeEnumValue(this.color);
            buf.writeEnumValue(this.overlay);
            break;
         case UPDATE_PROPERTIES:
            buf.writeByte(this.getFlags());
      }

   }

   private int getFlags() {
      int i = 0;
      if (this.darkenSky) {
         i |= 1;
      }

      if (this.playEndBossMusic) {
         i |= 2;
      }

      if (this.createFog) {
         i |= 2;
      }

      return i;
   }

   public void processPacket(Ts handler) {
      handler.handleUpdateBossInfo(this);
   }

   public UUID getUniqueId() {
      return this.uniqueId;
   }

   public Vb getOperation() {
      return this.operation;
   }

   public ITextComponent getName() {
      return this.name;
   }

   public float getPercent() {
      return this.percent;
   }

   public bac getColor() {
      return this.color;
   }

   public bad getOverlay() {
      return this.overlay;
   }

   public boolean shouldDarkenSky() {
      return this.darkenSky;
   }

   public boolean shouldPlayEndBossMusic() {
      return this.playEndBossMusic;
   }

   public boolean shouldCreateFog() {
      return this.createFog;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
