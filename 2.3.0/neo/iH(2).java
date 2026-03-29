package neo;

import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;

public class iH extends iG {
   private final Jc minecart;
   private float distance = 0.0F;

   public iH(Jc minecartIn) {
      super(NO.ENTITY_MINECART_RIDING, SoundCategory.NEUTRAL);
      this.minecart = minecartIn;
      this.repeat = true;
      this.repeatDelay = 0;
   }

   public void update() {
      if (this.minecart.isDead) {
         this.donePlaying = true;
      } else {
         this.xPosF = (float)this.minecart.posX;
         this.yPosF = (float)this.minecart.posY;
         this.zPosF = (float)this.minecart.posZ;
         float f = MathHelper.sqrt(this.minecart.motionX * this.minecart.motionX + this.minecart.motionZ * this.minecart.motionZ);
         if ((double)f >= 0.01) {
            this.distance = MathHelper.clamp(this.distance + 0.0025F, 0.0F, 1.0F);
            this.volume = 0.0F + MathHelper.clamp(f, 0.0F, 0.5F) * 0.7F;
         } else {
            this.distance = 0.0F;
            this.volume = 0.0F;
         }
      }

   }
}
