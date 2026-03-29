package neo;

import java.util.UUID;
import net.minecraft.util.text.ITextComponent;

public abstract class bae {
   private final UUID uniqueId;
   protected ITextComponent name;
   protected float percent;
   protected bac color;
   protected bad overlay;
   protected boolean darkenSky;
   protected boolean playEndBossMusic;
   protected boolean createFog;

   public bae(UUID uniqueIdIn, ITextComponent nameIn, bac colorIn, bad overlayIn) {
      this.uniqueId = uniqueIdIn;
      this.name = nameIn;
      this.color = colorIn;
      this.overlay = overlayIn;
      this.percent = 1.0F;
   }

   public UUID getUniqueId() {
      return this.uniqueId;
   }

   public ITextComponent getName() {
      return this.name;
   }

   public void setName(ITextComponent nameIn) {
      this.name = nameIn;
   }

   public float getPercent() {
      return this.percent;
   }

   public void setPercent(float percentIn) {
      this.percent = percentIn;
   }

   public bac getColor() {
      return this.color;
   }

   public void setColor(bac colorIn) {
      this.color = colorIn;
   }

   public bad getOverlay() {
      return this.overlay;
   }

   public void setOverlay(bad overlayIn) {
      this.overlay = overlayIn;
   }

   public boolean shouldDarkenSky() {
      return this.darkenSky;
   }

   public bae setDarkenSky(boolean darkenSkyIn) {
      this.darkenSky = darkenSkyIn;
      return this;
   }

   public boolean shouldPlayEndBossMusic() {
      return this.playEndBossMusic;
   }

   public bae setPlayEndBossMusic(boolean playEndBossMusicIn) {
      this.playEndBossMusic = playEndBossMusicIn;
      return this;
   }

   public bae setCreateFog(boolean createFogIn) {
      this.createFog = createFogIn;
      return this;
   }

   public boolean shouldCreateFog() {
      return this.createFog;
   }
}
