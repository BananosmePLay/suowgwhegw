package neo;

import com.google.gson.JsonParseException;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentUtils;

public class DP extends Ch {
   public DP() {
   }

   public String getName() {
      return "tellraw";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getUsage(DB sender) {
      return "commands.tellraw.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (args.length < 2) {
         throw new Ej("commands.tellraw.usage", new Object[0]);
      } else {
         ME entityplayer = getPlayer(server, sender, args[0]);
         String s = buildString(args, 1);

         try {
            ITextComponent itextcomponent = ITextComponent.Serializer.jsonToComponent(s);
            ((ME)entityplayer).sendMessage(TextComponentUtils.processComponent(sender, itextcomponent, entityplayer));
         } catch (JsonParseException var7) {
            JsonParseException jsonparseexception = var7;
            throw toSyntaxException(jsonparseexception);
         }
      }
   }

   public List<String> getTabCompletions(Xx server, DB sender, String[] args, @Nullable BlockPos targetPos) {
      return args.length == 1 ? getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames()) : Collections.emptyList();
   }

   public boolean isUsernameIndex(String[] args, int index) {
      return index == 0;
   }
}
