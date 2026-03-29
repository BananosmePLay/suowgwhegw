package neo;

import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class DH extends Ch {
   public static final Pattern IP_PATTERN = Pattern.compile("^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

   public DH() {
   }

   public String getName() {
      return "ban-ip";
   }

   public int getRequiredPermissionLevel() {
      return 3;
   }

   public boolean checkPermission(Xx server, DB sender) {
      return server.getPlayerList().getBannedIPs().isLanServer() && super.checkPermission(server, sender);
   }

   public String getUsage(DB sender) {
      return "commands.banip.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (args.length >= 1 && args[0].length() > 1) {
         ITextComponent itextcomponent = args.length >= 2 ? getChatComponentFromNthArg(sender, args, 1) : null;
         Matcher matcher = IP_PATTERN.matcher(args[0]);
         if (matcher.matches()) {
            this.banIp(server, sender, args[0], itextcomponent == null ? null : itextcomponent.getUnformattedText());
         } else {
            MG entityplayermp = server.getPlayerList().getPlayerByUsername(args[0]);
            if (entityplayermp == null) {
               throw new DF("commands.banip.invalid");
            }

            this.banIp(server, sender, entityplayermp.getPlayerIP(), itextcomponent == null ? null : itextcomponent.getUnformattedText());
         }

      } else {
         throw new Ej("commands.banip.usage", new Object[0]);
      }
   }

   public List<String> getTabCompletions(Xx server, DB sender, String[] args, @Nullable BlockPos targetPos) {
      return args.length == 1 ? getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames()) : Collections.emptyList();
   }

   protected void banIp(Xx server, DB sender, String ipAddress, @Nullable String banReason) {
      Xp userlistipbansentry = new Xp(ipAddress, (Date)null, sender.getName(), (Date)null, banReason);
      server.getPlayerList().getBannedIPs().addEntry(userlistipbansentry);
      List<MG> list = server.getPlayerList().getPlayersMatchingAddress(ipAddress);
      String[] astring = new String[list.size()];
      int i = 0;

      MG entityplayermp;
      for(Iterator var9 = list.iterator(); var9.hasNext(); astring[i++] = entityplayermp.getName()) {
         entityplayermp = (MG)var9.next();
         entityplayermp.connection.disconnect(new TextComponentTranslation("multiplayer.disconnect.ip_banned", new Object[0]));
      }

      if (list.isEmpty()) {
         notifyCommandListener(sender, this, "commands.banip.success", new Object[]{ipAddress});
      } else {
         notifyCommandListener(sender, this, "commands.banip.success.players", new Object[]{ipAddress, joinNiceString(astring)});
      }

   }
}
