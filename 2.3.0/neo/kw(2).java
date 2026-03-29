package neo;

public class kw extends jK {
   private boolean value;
   private final String localizationStr;
   private final kW guiResponder;

   public kw(kW responder, int buttonId, int x, int y, String localizationStrIn, boolean valueIn) {
      super(buttonId, x, y, 150, 20, "");
      this.localizationStr = localizationStrIn;
      this.value = valueIn;
      this.displayString = this.buildDisplayString();
      this.guiResponder = responder;
   }

   private String buildDisplayString() {
      return Ax.format(this.localizationStr) + ": " + Ax.format(this.value ? "gui.yes" : "gui.no");
   }

   public void setValue(boolean valueIn) {
      this.value = valueIn;
      this.displayString = this.buildDisplayString();
      this.guiResponder.setEntryValue(this.id, valueIn);
   }

   public boolean mousePressed(nC mc, int mouseX, int mouseY) {
      if (super.mousePressed(mc, mouseX, mouseY)) {
         this.value = !this.value;
         this.displayString = this.buildDisplayString();
         this.guiResponder.setEntryValue(this.id, this.value);
         return true;
      } else {
         return false;
      }
   }
}
