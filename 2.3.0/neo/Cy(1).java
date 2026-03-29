package neo;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class Cy extends Ch {
   public Cy() {
   }

   public String getName() {
      return "gamerule";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getUsage(DB sender) {
      return "commands.gamerule.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      bba gamerules = this.getOverWorldGameRules(server);
      String s = args.length > 0 ? args[0] : "";
      String s1 = args.length > 1 ? buildString(args, 1) : "";
      switch (args.length) {
         case 0:
            sender.sendMessage(new TextComponentString(joinNiceString(gamerules.getRules())));
            break;
         case 1:
            if (!gamerules.hasRule(s)) {
               throw new Ct("commands.gamerule.norule", new Object[]{s});
            }

            String s2 = gamerules.getString(s);
            sender.sendMessage((new TextComponentString(s)).appendText(" = ").appendText(s2));
            sender.setCommandStat(CK.QUERY_RESULT, gamerules.getInt(s));
            break;
         default:
            if (gamerules.areSameType(s, baZ.BOOLEAN_VALUE) && !"true".equals(s1) && !"false".equals(s1)) {
               throw new Ct("commands.generic.boolean.invalid", new Object[]{s1});
            }

            gamerules.setOrCreateGameRule(s, s1);
            notifyGameRuleChange(gamerules, s, server);
            notifyCommandListener(sender, this, "commands.gamerule.success", new Object[]{s, s1});
      }

   }

   public static void notifyGameRuleChange(bba rules, String p_184898_1_, Xx server) {
      if ("reducedDebugInfo".equals(p_184898_1_)) {
         byte b0 = (byte)(rules.getBoolean(p_184898_1_) ? 22 : 23);
         Iterator var4 = server.getPlayerList().getPlayers().iterator();

         while(var4.hasNext()) {
            MG entityplayermp = (MG)var4.next();
            entityplayermp.connection.sendPacket(new Ud(entityplayermp, b0));
         }
      }

   }

   public List<String> getTabCompletions(Xx server, DB sender, String[] args, @Nullable BlockPos targetPos) {
      if (args.length == 1) {
         return getListOfStringsMatchingLastWord(args, this.getOverWorldGameRules(server).getRules());
      } else {
         if (args.length == 2) {
            bba gamerules = this.getOverWorldGameRules(server);
            if (gamerules.areSameType(args[0], baZ.BOOLEAN_VALUE)) {
               return getListOfStringsMatchingLastWord(args, new String[]{"true", "false"});
            }

            if (gamerules.areSameType(args[0], baZ.FUNCTION)) {
               return getListOfStringsMatchingLastWord(args, server.getFunctionManager().getFunctions().keySet());
            }
         }

         return Collections.emptyList();
      }
   }

   private bba getOverWorldGameRules(Xx server) {
      return server.getWorld(0).getGameRules();
   }
}
