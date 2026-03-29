package neo;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

class mW implements mQ {
   private mW() {
   }

   public void selectItem(mY menu) {
      menu.exit();
   }

   public ITextComponent getSpectatorName() {
      return new TextComponentTranslation("spectatorMenu.close", new Object[0]);
   }

   public void renderIcon(float brightness, int alpha) {
      nC.getMinecraft().getTextureManager().bindTexture(lB.SPECTATOR_WIDGETS);
      jI.drawModalRectWithCustomSizedTexture(0, 0, 128.0F, 0.0F, 16, 16, 256.0F, 256.0F);
   }

   public boolean isEnabled() {
      return true;
   }

   // $FF: synthetic method
   mW(Object x0) {
      this();
   }
}
