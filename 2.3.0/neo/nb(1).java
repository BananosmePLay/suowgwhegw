package neo;

import net.minecraft.util.math.MathHelper;

class nb<T extends ne> {
   private final T toast;
   private long animationTime;
   private long visibleTime;
   private nd visibility;
   // $FF: synthetic field
   final nc this$0;

   private nb(nc this$0, ne toastIn) {
      this.this$0 = this$0;
      this.animationTime = -1L;
      this.visibleTime = -1L;
      this.visibility = nd.SHOW;
      this.toast = toastIn;
   }

   public T getToast() {
      return this.toast;
   }

   private float getVisibility(long p_193686_1_) {
      float f = MathHelper.clamp((float)(p_193686_1_ - this.animationTime) / 600.0F, 0.0F, 1.0F);
      f *= f;
      return this.visibility == nd.HIDE ? 1.0F - f : f;
   }

   public boolean render(int p_193684_1_, int p_193684_2_) {
      long i = nC.getSystemTime();
      if (this.animationTime == -1L) {
         this.animationTime = i;
         this.visibility.playSound(nc.access$100(this.this$0).getSoundHandler());
      }

      if (this.visibility == nd.SHOW && i - this.animationTime <= 600L) {
         this.visibleTime = i;
      }

      yh.pushMatrix();
      yh.translate((float)p_193684_1_ - 160.0F * this.getVisibility(i), (float)(p_193684_2_ * 32), (float)(500 + p_193684_2_));
      nd itoast$visibility = this.toast.draw(this.this$0, i - this.visibleTime);
      yh.popMatrix();
      if (itoast$visibility != this.visibility) {
         this.animationTime = i - (long)((int)((1.0F - this.getVisibility(i)) * 600.0F));
         this.visibility = itoast$visibility;
         this.visibility.playSound(nc.access$100(this.this$0).getSoundHandler());
      }

      return this.visibility == nd.HIDE && i - this.animationTime > 600L;
   }

   // $FF: synthetic method
   nb(nc x0, ne x1, Object x2) {
      this(x0, x1);
   }
}
