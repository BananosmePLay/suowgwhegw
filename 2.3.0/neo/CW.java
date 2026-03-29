package neo;

import com.google.gson.JsonParseException;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CW extends Ch {
   private static final Logger LOGGER = LogManager.getLogger();

   public CW() {
   }

   public String getName() {
      return "title";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getUsage(DB sender) {
      return "commands.title.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (args.length < 2) {
         throw new Ej("commands.title.usage", new Object[0]);
      } else {
         if (args.length < 3) {
            if ("title".equals(args[1]) || "subtitle".equals(args[1]) || "actionbar".equals(args[1])) {
               throw new Ej("commands.title.usage.title", new Object[0]);
            }

            if ("times".equals(args[1])) {
               throw new Ej("commands.title.usage.times", new Object[0]);
            }
         }

         MG entityplayermp = getPlayer(server, sender, args[0]);
         UX spackettitle$type = UX.byName(args[1]);
         if (spackettitle$type != UX.CLEAR && spackettitle$type != UX.RESET) {
            if (spackettitle$type == UX.TIMES) {
               if (args.length != 5) {
                  throw new Ej("commands.title.usage", new Object[0]);
               }

               int i = parseInt(args[2]);
               int j = parseInt(args[3]);
               int k = parseInt(args[4]);
               UY spackettitle2 = new UY(i, j, k);
               entityplayermp.connection.sendPacket(spackettitle2);
               notifyCommandListener(sender, this, "commands.title.success", new Object[0]);
            } else {
               if (args.length < 3) {
                  throw new Ej("commands.title.usage", new Object[0]);
               }

               String s = buildString(args, 2);

               ITextComponent itextcomponent;
               try {
                  itextcomponent = ITextComponent.Serializer.jsonToComponent(s);
               } catch (JsonParseException var10) {
                  JsonParseException jsonparseexception = var10;
                  throw toSyntaxException(jsonparseexception);
               }

               UY spackettitle1 = new UY(spackettitle$type, TextComponentUtils.processComponent(sender, itextcomponent, entityplayermp));
               entityplayermp.connection.sendPacket(spackettitle1);
               notifyCommandListener(sender, this, "commands.title.success", new Object[0]);
            }
         } else {
            if (args.length != 2) {
               throw new Ej("commands.title.usage", new Object[0]);
            }

            UY spackettitle = new UY(spackettitle$type, (ITextComponent)null);
            entityplayermp.connection.sendPacket(spackettitle);
            notifyCommandListener(sender, this, "commands.title.success", new Object[0]);
         }

      }
   }

   public List<String> getTabCompletions(Xx server, DB sender, String[] args, @Nullable BlockPos targetPos) {
      if (args.length == 1) {
         return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
      } else {
         return args.length == 2 ? getListOfStringsMatchingLastWord(args, UX.getNames()) : Collections.emptyList();
      }
   }

   public boolean isUsernameIndex(String[] args, int index) {
      return index == 0;
   }
}
