package neo;

import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;

class lp extends jK {
   private final SoundCategory category;
   private final String categoryName;
   public float volume;
   public boolean pressed;
   // $FF: synthetic field
   final lq this$0;

   public lp(lq this$0, int buttonId, int x, int y, SoundCategory categoryIn, boolean master) {
      super(buttonId, x, y, master ? 310 : 150, 20, "");
      this.this$0 = this$0;
      this.volume = 1.0F;
      this.category = categoryIn;
      this.categoryName = Ax.format("soundCategory." + categoryIn.getName());
      this.displayString = this.categoryName + ": " + this$0.getDisplayString(categoryIn);
      this.volume = lq.access$000(this$0).getSoundLevel(categoryIn);
   }

   protected int getHoverState(boolean mouseOver) {
      return 0;
   }

   protected void mouseDragged(nC mc, int mouseX, int mouseY) {
      if (this.visible) {
         if (this.pressed) {
            this.volume = (float)(mouseX - (this.x + 4)) / (float)(this.width - 8);
            this.volume = MathHelper.clamp(this.volume, 0.0F, 1.0F);
            nC.gameSettings.setSoundLevel(this.category, this.volume);
            nC.gameSettings.saveOptions();
            this.displayString = this.categoryName + ": " + this.this$0.getDisplayString(this.category);
         }

         yh.color(1.0F, 1.0F, 1.0F, 1.0F);
         this.drawTexturedModalRect(this.x + (int)(this.volume * (float)(this.width - 8)), this.y, 0, 66, 4, 20);
         this.drawTexturedModalRect(this.x + (int)(this.volume * (float)(this.width - 8)) + 4, this.y, 196, 66, 4, 20);
      }

   }

   public boolean mousePressed(nC mc, int mouseX, int mouseY) {
      if (super.mousePressed(mc, mouseX, mouseY)) {
         this.volume = (float)(mouseX - (this.x + 4)) / (float)(this.width - 8);
         this.volume = MathHelper.clamp(this.volume, 0.0F, 1.0F);
         nC.gameSettings.setSoundLevel(this.category, this.volume);
         nC.gameSettings.saveOptions();
         this.displayString = this.categoryName + ": " + this.this$0.getDisplayString(this.category);
         this.pressed = true;
         return true;
      } else {
         return false;
      }
   }

   public void playPressSound(iU soundHandlerIn) {
   }

   public void mouseReleased(int mouseX, int mouseY) {
      if (this.pressed) {
         this.this$0.mc.getSoundHandler().playSound(iN.getMasterRecord(NO.UI_BUTTON_CLICK, 1.0F));
      }

      this.pressed = false;
   }
}
