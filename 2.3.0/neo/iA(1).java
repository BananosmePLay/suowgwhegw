package neo;

import net.minecraft.util.SoundCategory;

public class iA extends iG {
   private final Kc guardian;

   public iA(Kc guardian) {
      super(NO.ENTITY_GUARDIAN_ATTACK, SoundCategory.HOSTILE);
      this.guardian = guardian;
      this.attenuationType = iB.NONE;
      this.repeat = true;
      this.repeatDelay = 0;
   }

   public void update() {
      if (!this.guardian.isDead && this.guardian.hasTargetedEntity()) {
         this.xPosF = (float)this.guardian.posX;
         this.yPosF = (float)this.guardian.posY;
         this.zPosF = (float)this.guardian.posZ;
         float f = this.guardian.getAttackAnimationScale(0.0F);
         this.volume = 0.0F + 1.0F * f * f;
         this.pitch = 0.7F + 0.5F * f;
      } else {
         this.donePlaying = true;
      }

   }
}
