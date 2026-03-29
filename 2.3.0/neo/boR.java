package neo;

public class boR extends jK {
   private bou shaderOption = null;

   public boR(int buttonId, int x, int y, int widthIn, int heightIn, bou shaderOption, String text) {
      super(buttonId, x, y, widthIn, heightIn, text);
      this.shaderOption = shaderOption;
   }

   public bou getShaderOption() {
      return this.shaderOption;
   }

   public void valueChanged() {
   }

   public boolean isSwitchable() {
      return true;
   }
}
