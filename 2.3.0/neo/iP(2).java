package neo;

import net.minecraft.util.ResourceLocation;

public class iP implements iD<iP> {
   private final ResourceLocation name;
   private final float volume;
   private final float pitch;
   private final int weight;
   private final iO type;
   private final boolean streaming;

   public iP(String nameIn, float volumeIn, float pitchIn, int weightIn, iO typeIn, boolean p_i46526_6_) {
      this.name = new ResourceLocation(nameIn);
      this.volume = volumeIn;
      this.pitch = pitchIn;
      this.weight = weightIn;
      this.type = typeIn;
      this.streaming = p_i46526_6_;
   }

   public ResourceLocation getSoundLocation() {
      return this.name;
   }

   public ResourceLocation getSoundAsOggLocation() {
      return new ResourceLocation(this.name.getNamespace(), "sounds/" + this.name.getPath() + ".ogg");
   }

   public float getVolume() {
      return this.volume;
   }

   public float getPitch() {
      return this.pitch;
   }

   public int getWeight() {
      return this.weight;
   }

   public iP cloneEntry() {
      return this;
   }

   public iO getType() {
      return this.type;
   }

   public boolean isStreaming() {
      return this.streaming;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public Object cloneEntry() {
      return this.cloneEntry();
   }
}
