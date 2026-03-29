package neo;

import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.ITextComponent;

public class jE implements jB {
   private final nC mc;

   public jE(nC minecraftIn) {
      this.mc = minecraftIn;
   }

   public void say(ChatType chatTypeIn, ITextComponent message) {
      this.mc.ingameGUI.setOverlayMessage(message, false);
   }
}
