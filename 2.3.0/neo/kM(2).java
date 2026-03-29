package neo;

import net.minecraft.util.math.MathHelper;

public class kM extends jK {
   private float sliderValue;
   public boolean dragging;
   private final Bi options;
   private final float minValue;
   private final float maxValue;

   public kM(int buttonId, int x, int y, Bi optionIn) {
      this(buttonId, x, y, optionIn, 0.0F, 1.0F);
   }

   public kM(int buttonId, int x, int y, Bi optionIn, float minValueIn, float maxValue) {
      super(buttonId, x, y, 150, 20, "");
      this.sliderValue = 1.0F;
      this.options = optionIn;
      this.minValue = minValueIn;
      this.maxValue = maxValue;
      nC minecraft = nC.getMinecraft();
      this.sliderValue = optionIn.normalizeValue(nC.gameSettings.getOptionFloatValue(optionIn));
      this.displayString = nC.gameSettings.getKeyBinding(optionIn);
   }

   protected int getHoverState(boolean mouseOver) {
      return 0;
   }

   protected void mouseDragged(nC mc, int mouseX, int mouseY) {
      if (this.visible) {
         if (this.dragging) {
            this.sliderValue = (float)(mouseX - (this.x + 4)) / (float)(this.width - 8);
            this.sliderValue = MathHelper.clamp(this.sliderValue, 0.0F, 1.0F);
            float f = this.options.denormalizeValue(this.sliderValue);
            nC.gameSettings.setOptionFloatValue(this.options, f);
            this.sliderValue = this.options.normalizeValue(f);
            this.displayString = nC.gameSettings.getKeyBinding(this.options);
         }

         mc.getTextureManager().bindTexture(BUTTON_TEXTURES);
         yh.color(1.0F, 1.0F, 1.0F, 1.0F);
         this.drawTexturedModalRect(this.x + (int)(this.sliderValue * (float)(this.width - 8)), this.y, 0, 66, 4, 20);
         this.drawTexturedModalRect(this.x + (int)(this.sliderValue * (float)(this.width - 8)) + 4, this.y, 196, 66, 4, 20);
      }

   }

   public boolean mousePressed(nC mc, int mouseX, int mouseY) {
      if (super.mousePressed(mc, mouseX, mouseY)) {
         this.sliderValue = (float)(mouseX - (this.x + 4)) / (float)(this.width - 8);
         this.sliderValue = MathHelper.clamp(this.sliderValue, 0.0F, 1.0F);
         nC.gameSettings.setOptionFloatValue(this.options, this.options.denormalizeValue(this.sliderValue));
         this.displayString = nC.gameSettings.getKeyBinding(this.options);
         this.dragging = true;
         return true;
      } else {
         return false;
      }
   }

   public void mouseReleased(int mouseX, int mouseY) {
      this.dragging = false;
   }
}
