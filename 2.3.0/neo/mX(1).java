package neo;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

class mX implements mQ {
   private final int direction;
   private final boolean enabled;

   public mX(int p_i45495_1_, boolean p_i45495_2_) {
      this.direction = p_i45495_1_;
      this.enabled = p_i45495_2_;
   }

   public void selectItem(mY menu) {
      mY.access$102(menu, mY.access$100(menu) + this.direction);
   }

   public ITextComponent getSpectatorName() {
      return this.direction < 0 ? new TextComponentTranslation("spectatorMenu.previous_page", new Object[0]) : new TextComponentTranslation("spectatorMenu.next_page", new Object[0]);
   }

   public void renderIcon(float brightness, int alpha) {
      nC.getMinecraft().getTextureManager().bindTexture(lB.SPECTATOR_WIDGETS);
      if (this.direction < 0) {
         jI.drawModalRectWithCustomSizedTexture(0, 0, 144.0F, 0.0F, 16, 16, 256.0F, 256.0F);
      } else {
         jI.drawModalRectWithCustomSizedTexture(0, 0, 160.0F, 0.0F, 16, 16, 256.0F, 256.0F);
      }

   }

   public boolean isEnabled() {
      return this.enabled;
   }
}
