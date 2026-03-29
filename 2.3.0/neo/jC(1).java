package neo;

import com.mojang.text2speech.Narrator;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class jC implements jB {
   public static final jC INSTANCE = new jC();
   private final Narrator narrator = Narrator.getNarrator();

   public jC() {
   }

   public void say(ChatType chatTypeIn, ITextComponent message) {
      nC.getMinecraft();
      int i = nC.gameSettings.narrator;
      if (i != 0 && this.narrator.active() && (i == 1 || i == 2 && chatTypeIn == ChatType.CHAT || i == 3 && chatTypeIn == ChatType.SYSTEM)) {
         if (message instanceof TextComponentTranslation && "chat.type.text".equals(((TextComponentTranslation)message).getKey())) {
            this.narrator.say((new TextComponentTranslation("chat.type.text.narrate", ((TextComponentTranslation)message).getFormatArgs())).getUnformattedText());
         } else {
            this.narrator.say(message.getUnformattedText());
         }
      }

   }

   public void announceMode(int p_193641_1_) {
      this.narrator.clear();
      this.narrator.say((new TextComponentTranslation("options.narrator", new Object[0])).getUnformattedText() + " : " + (new TextComponentTranslation(Bj.NARRATOR_MODES[p_193641_1_], new Object[0])).getUnformattedText());
      nc guitoast = nC.getMinecraft().getToastGui();
      if (this.narrator.active()) {
         if (p_193641_1_ == 0) {
            ni.addOrUpdate(guitoast, nh.NARRATOR_TOGGLE, new TextComponentTranslation("narrator.toast.disabled", new Object[0]), (ITextComponent)null);
         } else {
            ni.addOrUpdate(guitoast, nh.NARRATOR_TOGGLE, new TextComponentTranslation("narrator.toast.enabled", new Object[0]), new TextComponentTranslation(Bj.NARRATOR_MODES[p_193641_1_], new Object[0]));
         }
      } else {
         ni.addOrUpdate(guitoast, nh.NARRATOR_TOGGLE, new TextComponentTranslation("narrator.toast.disabled", new Object[0]), new TextComponentTranslation("options.narrator.notavailable", new Object[0]));
      }

   }

   public boolean isActive() {
      return this.narrator.active();
   }

   public void clear() {
      this.narrator.clear();
   }
}
