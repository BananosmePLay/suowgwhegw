package neo;

import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;

public class iI extends iG {
   private final ME player;
   private final Jc minecart;

   public iI(ME playerRiding, Jc minecart) {
      super(NO.ENTITY_MINECART_INSIDE, SoundCategory.NEUTRAL);
      this.player = playerRiding;
      this.minecart = minecart;
      this.attenuationType = iB.NONE;
      this.repeat = true;
      this.repeatDelay = 0;
   }

   public void update() {
      if (!this.minecart.isDead && this.player.isRiding() && this.player.getRidingEntity() == this.minecart) {
         float f = MathHelper.sqrt(this.minecart.motionX * this.minecart.motionX + this.minecart.motionZ * this.minecart.motionZ);
         if ((double)f >= 0.01) {
            this.volume = 0.0F + MathHelper.clamp(f, 0.0F, 1.0F) * 0.75F;
         } else {
            this.volume = 0.0F;
         }
      } else {
         this.donePlaying = true;
      }

   }
}
