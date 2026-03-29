package neo;

public class lx extends jK {
   private float sliderPosition = 1.0F;
   public boolean isMouseDown;
   private final String name;
   private final float min;
   private final float max;
   private final kW responder;
   private lw formatHelper;

   public lx(kW guiResponder, int idIn, int x, int y, String nameIn, float minIn, float maxIn, float defaultValue, lw formatter) {
      super(idIn, x, y, 150, 20, "");
      this.name = nameIn;
      this.min = minIn;
      this.max = maxIn;
      this.sliderPosition = (defaultValue - minIn) / (maxIn - minIn);
      this.formatHelper = formatter;
      this.responder = guiResponder;
      this.displayString = this.getDisplayString();
   }

   public float getSliderValue() {
      return this.min + (this.max - this.min) * this.sliderPosition;
   }

   public void setSliderValue(float value, boolean notifyResponder) {
      this.sliderPosition = (value - this.min) / (this.max - this.min);
      this.displayString = this.getDisplayString();
      if (notifyResponder) {
         this.responder.setEntryValue(this.id, this.getSliderValue());
      }

   }

   public float getSliderPosition() {
      return this.sliderPosition;
   }

   private String getDisplayString() {
      return this.formatHelper == null ? Ax.format(this.name) + ": " + this.getSliderValue() : this.formatHelper.getText(this.id, Ax.format(this.name), this.getSliderValue());
   }

   protected int getHoverState(boolean mouseOver) {
      return 0;
   }

   protected void mouseDragged(nC mc, int mouseX, int mouseY) {
      if (this.visible) {
         if (this.isMouseDown) {
            this.sliderPosition = (float)(mouseX - (this.x + 4)) / (float)(this.width - 8);
            if (this.sliderPosition < 0.0F) {
               this.sliderPosition = 0.0F;
            }

            if (this.sliderPosition > 1.0F) {
               this.sliderPosition = 1.0F;
            }

            this.displayString = this.getDisplayString();
            this.responder.setEntryValue(this.id, this.getSliderValue());
         }

         yh.color(1.0F, 1.0F, 1.0F, 1.0F);
         this.drawTexturedModalRect(this.x + (int)(this.sliderPosition * (float)(this.width - 8)), this.y, 0, 66, 4, 20);
         this.drawTexturedModalRect(this.x + (int)(this.sliderPosition * (float)(this.width - 8)) + 4, this.y, 196, 66, 4, 20);
      }

   }

   public void setSliderPosition(float position) {
      this.sliderPosition = position;
      this.displayString = this.getDisplayString();
      this.responder.setEntryValue(this.id, this.getSliderValue());
   }

   public boolean mousePressed(nC mc, int mouseX, int mouseY) {
      if (super.mousePressed(mc, mouseX, mouseY)) {
         this.sliderPosition = (float)(mouseX - (this.x + 4)) / (float)(this.width - 8);
         if (this.sliderPosition < 0.0F) {
            this.sliderPosition = 0.0F;
         }

         if (this.sliderPosition > 1.0F) {
            this.sliderPosition = 1.0F;
         }

         this.displayString = this.getDisplayString();
         this.responder.setEntryValue(this.id, this.getSliderValue());
         this.isMouseDown = true;
         return true;
      } else {
         return false;
      }
   }

   public void mouseReleased(int mouseX, int mouseY) {
      this.isMouseDown = false;
   }
}
