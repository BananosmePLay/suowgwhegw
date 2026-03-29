package neo;

import java.io.IOException;
import net.minecraft.util.SoundCategory;
import org.apache.commons.lang3.Validate;

public class TN implements Sz<Ts> {
   private String soundName;
   private SoundCategory category;
   private int x;
   private int y = Integer.MAX_VALUE;
   private int z;
   private float volume;
   private float pitch;

   public TN() {
   }

   public TN(String soundNameIn, SoundCategory categoryIn, double xIn, double yIn, double zIn, float volumeIn, float pitchIn) {
      Validate.notNull(soundNameIn, "name", new Object[0]);
      this.soundName = soundNameIn;
      this.category = categoryIn;
      this.x = (int)(xIn * 8.0);
      this.y = (int)(yIn * 8.0);
      this.z = (int)(zIn * 8.0);
      this.volume = volumeIn;
      this.pitch = pitchIn;
   }

   public void readPacketData(SA buf) throws IOException {
      this.soundName = buf.readString(256);
      this.category = (SoundCategory)buf.readEnumValue(SoundCategory.class);
      this.x = buf.readInt();
      this.y = buf.readInt();
      this.z = buf.readInt();
      this.volume = buf.readFloat();
      this.pitch = buf.readFloat();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeString(this.soundName);
      buf.writeEnumValue(this.category);
      buf.writeInt(this.x);
      buf.writeInt(this.y);
      buf.writeInt(this.z);
      buf.writeFloat(this.volume);
      buf.writeFloat(this.pitch);
   }

   public String getSoundName() {
      return this.soundName;
   }

   public SoundCategory getCategory() {
      return this.category;
   }

   public double getX() {
      return (double)((float)this.x / 8.0F);
   }

   public double getY() {
      return (double)((float)this.y / 8.0F);
   }

   public double getZ() {
      return (double)((float)this.z / 8.0F);
   }

   public float getVolume() {
      return this.volume;
   }

   public float getPitch() {
      return this.pitch;
   }

   public void processPacket(Ts handler) {
      handler.handleCustomSound(this);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
