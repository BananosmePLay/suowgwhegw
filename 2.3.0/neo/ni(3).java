package neo;

import javax.annotation.Nullable;
import net.minecraft.util.text.ITextComponent;

public class ni implements ne {
   private final nh type;
   private String title;
   private String subtitle;
   private long firstDrawTime;
   private boolean newDisplay;

   public ni(nh typeIn, ITextComponent titleComponent, @Nullable ITextComponent subtitleComponent) {
      this.type = typeIn;
      this.title = titleComponent.getUnformattedText();
      this.subtitle = subtitleComponent == null ? null : subtitleComponent.getUnformattedText();
   }

   public nd draw(nc toastGui, long delta) {
      if (this.newDisplay) {
         this.firstDrawTime = delta;
         this.newDisplay = false;
      }

      toastGui.getMinecraft().getTextureManager().bindTexture(TEXTURE_TOASTS);
      yh.color(1.0F, 1.0F, 1.0F);
      toastGui.drawTexturedModalRect(0, 0, 0, 64, 160, 32);
      if (this.subtitle == null) {
         toastGui.getMinecraft().fontRenderer.drawString(this.title, 18, 12, -256);
      } else {
         toastGui.getMinecraft().fontRenderer.drawString(this.title, 18, 7, -256);
         toastGui.getMinecraft().fontRenderer.drawString(this.subtitle, 18, 18, -1);
      }

      return delta - this.firstDrawTime < 5000L ? nd.SHOW : nd.HIDE;
   }

   public void setDisplayedText(ITextComponent titleComponent, @Nullable ITextComponent subtitleComponent) {
      this.title = titleComponent.getUnformattedText();
      this.subtitle = subtitleComponent == null ? null : subtitleComponent.getUnformattedText();
      this.newDisplay = true;
   }

   public nh getType() {
      return this.type;
   }

   public static void addOrUpdate(nc p_193657_0_, nh p_193657_1_, ITextComponent p_193657_2_, @Nullable ITextComponent p_193657_3_) {
      ni systemtoast = (ni)p_193657_0_.getToast(ni.class, p_193657_1_);
      if (systemtoast == null) {
         p_193657_0_.add(new ni(p_193657_1_, p_193657_2_, p_193657_3_));
      } else {
         systemtoast.setDisplayedText(p_193657_2_, p_193657_3_);
      }

   }

   // $FF: synthetic method
   // $FF: bridge method
   public Object getType() {
      return this.getType();
   }
}
