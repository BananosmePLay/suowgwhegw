package neo;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class CN extends Ch {
   public CN() {
   }

   public String getName() {
      return "kick";
   }

   public int getRequiredPermissionLevel() {
      return 3;
   }

   public String getUsage(DB sender) {
      return "commands.kick.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (args.length > 0 && args[0].length() > 1) {
         MG entityplayermp = server.getPlayerList().getPlayerByUsername(args[0]);
         if (entityplayermp == null) {
            throw new DF("commands.generic.player.notFound", new Object[]{args[0]});
         } else {
            if (args.length >= 2) {
               ITextComponent itextcomponent = getChatComponentFromNthArg(sender, args, 1);
               entityplayermp.connection.disconnect(itextcomponent);
               notifyCommandListener(sender, this, "commands.kick.success.reason", new Object[]{entityplayermp.getName(), itextcomponent.getUnformattedText()});
            } else {
               entityplayermp.connection.disconnect(new TextComponentTranslation("multiplayer.disconnect.kicked", new Object[0]));
               notifyCommandListener(sender, this, "commands.kick.success", new Object[]{entityplayermp.getName()});
            }

         }
      } else {
         throw new Ej("commands.kick.usage", new Object[0]);
      }
   }

   public List<String> getTabCompletions(Xx server, DB sender, String[] args, @Nullable BlockPos targetPos) {
      return args.length >= 1 ? getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames()) : Collections.emptyList();
   }
}
