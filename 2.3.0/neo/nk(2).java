package neo;

import javax.annotation.Nullable;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;

public class nk implements ne {
   private final nj icon;
   private final String title;
   private final String subtitle;
   private nd visibility;
   private long lastDelta;
   private float displayedProgress;
   private float currentProgress;
   private final boolean hasProgressBar;

   public nk(nj iconIn, ITextComponent titleComponent, @Nullable ITextComponent subtitleComponent, boolean drawProgressBar) {
      this.visibility = nd.SHOW;
      this.icon = iconIn;
      this.title = titleComponent.getFormattedText();
      this.subtitle = subtitleComponent == null ? null : subtitleComponent.getFormattedText();
      this.hasProgressBar = drawProgressBar;
   }

   public nd draw(nc toastGui, long delta) {
      toastGui.getMinecraft().getTextureManager().bindTexture(TEXTURE_TOASTS);
      yh.color(1.0F, 1.0F, 1.0F);
      toastGui.drawTexturedModalRect(0, 0, 0, 96, 160, 32);
      this.icon.draw(toastGui, 6, 6);
      if (this.subtitle == null) {
         toastGui.getMinecraft().fontRenderer.drawString(this.title, 30, 12, -11534256);
      } else {
         toastGui.getMinecraft().fontRenderer.drawString(this.title, 30, 7, -11534256);
         toastGui.getMinecraft().fontRenderer.drawString(this.subtitle, 30, 18, -16777216);
      }

      if (this.hasProgressBar) {
         jI.drawRect(3, 28, 157, 29, -1);
         float f = (float)MathHelper.clampedLerp((double)this.displayedProgress, (double)this.currentProgress, (double)((float)(delta - this.lastDelta) / 100.0F));
         int i;
         if (this.currentProgress >= this.displayedProgress) {
            i = -16755456;
         } else {
            i = -11206656;
         }

         jI.drawRect(3, 28, (int)(3.0F + 154.0F * f), 29, i);
         this.displayedProgress = f;
         this.lastDelta = delta;
      }

      return this.visibility;
   }

   public void hide() {
      this.visibility = nd.HIDE;
   }

   public void setProgress(float progress) {
      this.currentProgress = progress;
   }
}
