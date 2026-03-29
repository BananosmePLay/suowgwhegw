package neo;

import net.minecraft.util.SoundEvent;

public class ia {
   public static final ia WOOD;
   public static final ia GROUND;
   public static final ia PLANT;
   public static final ia STONE;
   public static final ia METAL;
   public static final ia GLASS;
   public static final ia CLOTH;
   public static final ia SAND;
   public static final ia SNOW;
   public static final ia LADDER;
   public static final ia ANVIL;
   public static final ia SLIME;
   public final float volume;
   public final float pitch;
   private final SoundEvent breakSound;
   private final SoundEvent stepSound;
   private final SoundEvent placeSound;
   private final SoundEvent hitSound;
   private final SoundEvent fallSound;

   public ia(float volumeIn, float pitchIn, SoundEvent breakSoundIn, SoundEvent stepSoundIn, SoundEvent placeSoundIn, SoundEvent hitSoundIn, SoundEvent fallSoundIn) {
      this.volume = volumeIn;
      this.pitch = pitchIn;
      this.breakSound = breakSoundIn;
      this.stepSound = stepSoundIn;
      this.placeSound = placeSoundIn;
      this.hitSound = hitSoundIn;
      this.fallSound = fallSoundIn;
   }

   public float getVolume() {
      return this.volume;
   }

   public float getPitch() {
      return this.pitch;
   }

   public SoundEvent getBreakSound() {
      return this.breakSound;
   }

   public SoundEvent getStepSound() {
      return this.stepSound;
   }

   public SoundEvent getPlaceSound() {
      return this.placeSound;
   }

   public SoundEvent getHitSound() {
      return this.hitSound;
   }

   public SoundEvent getFallSound() {
      return this.fallSound;
   }

   static {
      WOOD = new ia(1.0F, 1.0F, NO.BLOCK_WOOD_BREAK, NO.BLOCK_WOOD_STEP, NO.BLOCK_WOOD_PLACE, NO.BLOCK_WOOD_HIT, NO.BLOCK_WOOD_FALL);
      GROUND = new ia(1.0F, 1.0F, NO.BLOCK_GRAVEL_BREAK, NO.BLOCK_GRAVEL_STEP, NO.BLOCK_GRAVEL_PLACE, NO.BLOCK_GRAVEL_HIT, NO.BLOCK_GRAVEL_FALL);
      PLANT = new ia(1.0F, 1.0F, NO.BLOCK_GRASS_BREAK, NO.BLOCK_GRASS_STEP, NO.BLOCK_GRASS_PLACE, NO.BLOCK_GRASS_HIT, NO.BLOCK_GRASS_FALL);
      STONE = new ia(1.0F, 1.0F, NO.BLOCK_STONE_BREAK, NO.BLOCK_STONE_STEP, NO.BLOCK_STONE_PLACE, NO.BLOCK_STONE_HIT, NO.BLOCK_STONE_FALL);
      METAL = new ia(1.0F, 1.5F, NO.BLOCK_METAL_BREAK, NO.BLOCK_METAL_STEP, NO.BLOCK_METAL_PLACE, NO.BLOCK_METAL_HIT, NO.BLOCK_METAL_FALL);
      GLASS = new ia(1.0F, 1.0F, NO.BLOCK_GLASS_BREAK, NO.BLOCK_GLASS_STEP, NO.BLOCK_GLASS_PLACE, NO.BLOCK_GLASS_HIT, NO.BLOCK_GLASS_FALL);
      CLOTH = new ia(1.0F, 1.0F, NO.BLOCK_CLOTH_BREAK, NO.BLOCK_CLOTH_STEP, NO.BLOCK_CLOTH_PLACE, NO.BLOCK_CLOTH_HIT, NO.BLOCK_CLOTH_FALL);
      SAND = new ia(1.0F, 1.0F, NO.BLOCK_SAND_BREAK, NO.BLOCK_SAND_STEP, NO.BLOCK_SAND_PLACE, NO.BLOCK_SAND_HIT, NO.BLOCK_SAND_FALL);
      SNOW = new ia(1.0F, 1.0F, NO.BLOCK_SNOW_BREAK, NO.BLOCK_SNOW_STEP, NO.BLOCK_SNOW_PLACE, NO.BLOCK_SNOW_HIT, NO.BLOCK_SNOW_FALL);
      LADDER = new ia(1.0F, 1.0F, NO.BLOCK_LADDER_BREAK, NO.BLOCK_LADDER_STEP, NO.BLOCK_LADDER_PLACE, NO.BLOCK_LADDER_HIT, NO.BLOCK_LADDER_FALL);
      ANVIL = new ia(0.3F, 1.0F, NO.BLOCK_ANVIL_BREAK, NO.BLOCK_ANVIL_STEP, NO.BLOCK_ANVIL_PLACE, NO.BLOCK_ANVIL_HIT, NO.BLOCK_ANVIL_FALL);
      SLIME = new ia(1.0F, 1.0F, NO.BLOCK_SLIME_BREAK, NO.BLOCK_SLIME_STEP, NO.BLOCK_SLIME_PLACE, NO.BLOCK_SLIME_HIT, NO.BLOCK_SLIME_FALL);
   }
}
