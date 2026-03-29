package neo;

import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.ITextComponent;

public class jD implements jB {
   private final nC mc;

   public jD(nC p_i47393_1_) {
      this.mc = p_i47393_1_;
   }

   public void say(ChatType chatTypeIn, ITextComponent message) {
      this.mc.ingameGUI.getChatGUI().printChatMessage(message);
   }
}
