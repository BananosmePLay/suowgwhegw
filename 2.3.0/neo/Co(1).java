package neo;

import java.util.Iterator;
import net.minecraft.util.text.TextComponentTranslation;

public class Co extends Cx {
   public Co() {
   }

   public String getName() {
      return "defaultgamemode";
   }

   public String getUsage(DB sender) {
      return "commands.defaultgamemode.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (args.length <= 0) {
         throw new Ej("commands.defaultgamemode.usage", new Object[0]);
      } else {
         bbb gametype = this.getGameModeFromCommand(sender, args[0]);
         this.setDefaultGameType(gametype, server);
         notifyCommandListener(sender, this, "commands.defaultgamemode.success", new Object[]{new TextComponentTranslation("gameMode." + gametype.getName(), new Object[0])});
      }
   }

   protected void setDefaultGameType(bbb gameType, Xx server) {
      server.setGameType(gameType);
      if (server.getForceGamemode()) {
         Iterator var3 = server.getPlayerList().getPlayers().iterator();

         while(var3.hasNext()) {
            MG entityplayermp = (MG)var3.next();
            entityplayermp.setGameType(gameType);
         }
      }

   }
}
