package neo;

import java.util.Iterator;
import java.util.List;
import net.minecraft.util.math.MathHelper;

public class mZ implements ne {
   private final b advancement;
   private boolean hasPlayedSound = false;

   public mZ(b advancementIn) {
      this.advancement = advancementIn;
   }

   public nd draw(nc toastGui, long delta) {
      toastGui.getMinecraft().getTextureManager().bindTexture(TEXTURE_TOASTS);
      yh.color(1.0F, 1.0F, 1.0F);
      cb displayinfo = this.advancement.getDisplay();
      toastGui.drawTexturedModalRect(0, 0, 0, 0, 160, 32);
      if (displayinfo != null) {
         List<String> list = toastGui.getMinecraft().fontRenderer.listFormattedStringToWidth(displayinfo.getTitle().getFormattedText(), 125);
         int i = displayinfo.getFrame() == cc.CHALLENGE ? 16746751 : 16776960;
         if (list.size() == 1) {
            toastGui.getMinecraft().fontRenderer.drawString(Ax.format("advancements.toast." + displayinfo.getFrame().getName()), 30, 7, i | -16777216);
            toastGui.getMinecraft().fontRenderer.drawString(displayinfo.getTitle().getFormattedText(), 30, 18, -1);
         } else {
            int j = true;
            float f = 300.0F;
            int i1;
            if (delta < 1500L) {
               i1 = MathHelper.floor(MathHelper.clamp((float)(1500L - delta) / 300.0F, 0.0F, 1.0F) * 255.0F) << 24 | 67108864;
               toastGui.getMinecraft().fontRenderer.drawString(Ax.format("advancements.toast." + displayinfo.getFrame().getName()), 30, 11, i | i1);
            } else {
               i1 = MathHelper.floor(MathHelper.clamp((float)(delta - 1500L) / 300.0F, 0.0F, 1.0F) * 252.0F) << 24 | 67108864;
               int l = 16 - list.size() * toastGui.getMinecraft().fontRenderer.FONT_HEIGHT / 2;

               for(Iterator var11 = list.iterator(); var11.hasNext(); l += toastGui.getMinecraft().fontRenderer.FONT_HEIGHT) {
                  String s = (String)var11.next();
                  toastGui.getMinecraft().fontRenderer.drawString(s, 30, l, 16777215 | i1);
               }
            }
         }

         if (!this.hasPlayedSound && delta > 0L) {
            this.hasPlayedSound = true;
            if (displayinfo.getFrame() == cc.CHALLENGE) {
               toastGui.getMinecraft().getSoundHandler().playSound(iN.getRecord(NO.UI_TOAST_CHALLENGE_COMPLETE, 1.0F, 1.0F));
            }
         }

         yz.enableGUIStandardItemLighting();
         toastGui.getMinecraft().getRenderItem().renderItemAndEffectIntoGUI((Iw)null, displayinfo.getIcon(), 8, 8);
         return delta >= 5000L ? nd.HIDE : nd.SHOW;
      } else {
         return nd.HIDE;
      }
   }
}
