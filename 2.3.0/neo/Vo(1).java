package neo;

import net.minecraft.util.text.ITextComponent;

public class Vo implements DB {
   private final StringBuffer buffer = new StringBuffer();
   private final Xx server;

   public Vo(Xx serverIn) {
      this.server = serverIn;
   }

   public String getName() {
      return "Rcon";
   }

   public void sendMessage(ITextComponent component) {
      this.buffer.append(component.getUnformattedText());
   }

   public boolean canUseCommand(int permLevel, String commandName) {
      return true;
   }

   public bij getEntityWorld() {
      return this.server.getEntityWorld();
   }

   public boolean sendCommandFeedback() {
      return true;
   }

   public Xx getServer() {
      return this.server;
   }
}
