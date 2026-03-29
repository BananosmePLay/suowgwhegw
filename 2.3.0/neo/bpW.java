package neo;

import java.util.BitSet;

public class bpW {
   private static boolean active;
   private static BitSet spritesRendered = new BitSet();
   private static BitSet texturesRendered = new BitSet();

   public bpW() {
   }

   public static boolean isActive() {
      return active && !bpq.isShadowPass;
   }

   public static void update() {
      active = XH.getGameSettings().ofSmartAnimations;
   }

   public static void spriteRendered(int animationIndex) {
      if (animationIndex >= 0) {
         spritesRendered.set(animationIndex);
      }

   }

   public static void spritesRendered(BitSet animationIndexes) {
      if (animationIndexes != null) {
         spritesRendered.or(animationIndexes);
      }

   }

   public static boolean isSpriteRendered(int animationIndex) {
      return animationIndex < 0 ? false : spritesRendered.get(animationIndex);
   }

   public static void resetSpritesRendered() {
      spritesRendered.clear();
   }

   public static void textureRendered(int textureId) {
      if (textureId >= 0) {
         texturesRendered.set(textureId);
      }

   }

   public static boolean isTextureRendered(int texId) {
      return texId < 0 ? false : texturesRendered.get(texId);
   }

   public static void resetTexturesRendered() {
      texturesRendered.clear();
   }
}
