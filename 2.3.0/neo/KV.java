package neo;

import java.util.Random;

public class KV implements ID {
   public VW effect;

   public KV() {
   }

   public void setRandomEffect(Random rand) {
      int i = rand.nextInt(5);
      if (i <= 1) {
         this.effect = NL.SPEED;
      } else if (i <= 2) {
         this.effect = NL.STRENGTH;
      } else if (i <= 3) {
         this.effect = NL.REGENERATION;
      } else if (i <= 4) {
         this.effect = NL.INVISIBILITY;
      }

   }
}
