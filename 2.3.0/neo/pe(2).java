package neo;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public enum pe {
   ENABLED("enabled"),
   DISABLED("disabled"),
   PROMPT("prompt");

   private final ITextComponent motd;

   private pe(String name) {
      this.motd = new TextComponentTranslation("addServer.resourcePack." + name, new Object[0]);
   }

   public ITextComponent getMotd() {
      return this.motd;
   }
}
