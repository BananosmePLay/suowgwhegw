package neo;

import com.mojang.authlib.GameProfile;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;

public class DI extends Ch {
   public DI() {
   }

   public String getName() {
      return "ban";
   }

   public int getRequiredPermissionLevel() {
      return 3;
   }

   public String getUsage(DB sender) {
      return "commands.ban.usage";
   }

   public boolean checkPermission(Xx server, DB sender) {
      return server.getPlayerList().getBannedPlayers().isLanServer() && super.checkPermission(server, sender);
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (args.length >= 1 && args[0].length() > 0) {
         GameProfile gameprofile = server.getPlayerProfileCache().getGameProfileForUsername(args[0]);
         if (gameprofile == null) {
            throw new Ct("commands.ban.failed", new Object[]{args[0]});
         } else {
            String s = null;
            if (args.length >= 2) {
               s = getChatComponentFromNthArg(sender, args, 1).getUnformattedText();
            }

            Xl userlistbansentry = new Xl(gameprofile, (Date)null, sender.getName(), (Date)null, s);
            server.getPlayerList().getBannedPlayers().addEntry(userlistbansentry);
            MG entityplayermp = server.getPlayerList().getPlayerByUsername(args[0]);
            if (entityplayermp != null) {
               entityplayermp.connection.disconnect(new TextComponentTranslation("multiplayer.disconnect.banned", new Object[0]));
            }

            notifyCommandListener(sender, this, "commands.ban.success", new Object[]{args[0]});
         }
      } else {
         throw new Ej("commands.ban.usage", new Object[0]);
      }
   }

   public List<String> getTabCompletions(Xx server, DB sender, String[] args, @Nullable BlockPos targetPos) {
      return args.length >= 1 ? getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames()) : Collections.emptyList();
   }
}
