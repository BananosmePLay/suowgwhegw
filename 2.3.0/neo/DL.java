package neo;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class DL extends Ch {
   public DL() {
   }

   public String getName() {
      return "me";
   }

   public int getRequiredPermissionLevel() {
      return 0;
   }

   public String getUsage(DB sender) {
      return "commands.me.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (args.length <= 0) {
         throw new Ej("commands.me.usage", new Object[0]);
      } else {
         ITextComponent itextcomponent = getChatComponentFromNthArg(sender, args, 0, !(sender instanceof ME));
         server.getPlayerList().sendMessage(new TextComponentTranslation("chat.type.emote", new Object[]{sender.getDisplayName(), itextcomponent}));
      }
   }

   public List<String> getTabCompletions(Xx server, DB sender, String[] args, @Nullable BlockPos targetPos) {
      return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
   }
}
