package neo;

import java.io.IOException;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import org.apache.commons.lang3.Validate;

public class UL implements Sz<Ts> {
   private SoundEvent sound;
   private SoundCategory category;
   private int posX;
   private int posY;
   private int posZ;
   private float soundVolume;
   private float soundPitch;

   public UL() {
   }

   public UL(SoundEvent soundIn, SoundCategory categoryIn, double xIn, double yIn, double zIn, float volumeIn, float pitchIn) {
      Validate.notNull(soundIn, "sound", new Object[0]);
      this.sound = soundIn;
      this.category = categoryIn;
      this.posX = (int)(xIn * 8.0);
      this.posY = (int)(yIn * 8.0);
      this.posZ = (int)(zIn * 8.0);
      this.soundVolume = volumeIn;
      this.soundPitch = pitchIn;
   }

   public void readPacketData(SA buf) throws IOException {
      this.sound = (SoundEvent)SoundEvent.REGISTRY.getObjectById(buf.readVarInt());
      this.category = (SoundCategory)buf.readEnumValue(SoundCategory.class);
      this.posX = buf.readInt();
      this.posY = buf.readInt();
      this.posZ = buf.readInt();
      this.soundVolume = buf.readFloat();
      this.soundPitch = buf.readFloat();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeVarInt(SoundEvent.REGISTRY.getIDForObject(this.sound));
      buf.writeEnumValue(this.category);
      buf.writeInt(this.posX);
      buf.writeInt(this.posY);
      buf.writeInt(this.posZ);
      buf.writeFloat(this.soundVolume);
      buf.writeFloat(this.soundPitch);
   }

   public SoundEvent getSound() {
      return this.sound;
   }

   public SoundCategory getCategory() {
      return this.category;
   }

   public double getX() {
      return (double)((float)this.posX / 8.0F);
   }

   public double getY() {
      return (double)((float)this.posY / 8.0F);
   }

   public double getZ() {
      return (double)((float)this.posZ / 8.0F);
   }

   public float getVolume() {
      return this.soundVolume;
   }

   public float getPitch() {
      return this.soundPitch;
   }

   public void processPacket(Ts handler) {
      handler.handleSoundEffect(this);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
