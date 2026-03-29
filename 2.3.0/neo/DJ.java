package neo;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class DJ extends Ch {
   public DJ() {
   }

   public String getName() {
      return "say";
   }

   public int getRequiredPermissionLevel() {
      return 1;
   }

   public String getUsage(DB sender) {
      return "commands.say.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (args.length > 0 && args[0].length() > 0) {
         ITextComponent itextcomponent = getChatComponentFromNthArg(sender, args, 0, true);
         server.getPlayerList().sendMessage(new TextComponentTranslation("chat.type.announcement", new Object[]{sender.getDisplayName(), itextcomponent}));
      } else {
         throw new Ej("commands.say.usage", new Object[0]);
      }
   }

   public List<String> getTabCompletions(Xx server, DB sender, String[] args, @Nullable BlockPos targetPos) {
      return args.length >= 1 ? getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames()) : Collections.emptyList();
   }
}
