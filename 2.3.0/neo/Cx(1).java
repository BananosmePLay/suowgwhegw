package neo;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class Cx extends Ch {
   public Cx() {
   }

   public String getName() {
      return "gamemode";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getUsage(DB sender) {
      return "commands.gamemode.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (args.length <= 0) {
         throw new Ej("commands.gamemode.usage", new Object[0]);
      } else {
         bbb gametype = this.getGameModeFromCommand(sender, args[0]);
         ME entityplayer = args.length >= 2 ? getPlayer(server, sender, args[1]) : getCommandSenderAsPlayer(sender);
         ((ME)entityplayer).setGameType(gametype);
         ITextComponent itextcomponent = new TextComponentTranslation("gameMode." + gametype.getName(), new Object[0]);
         if (sender.getEntityWorld().getGameRules().getBoolean("sendCommandFeedback")) {
            ((ME)entityplayer).sendMessage(new TextComponentTranslation("gameMode.changed", new Object[]{itextcomponent}));
         }

         if (entityplayer == sender) {
            notifyCommandListener(sender, this, 1, "commands.gamemode.success.self", new Object[]{itextcomponent});
         } else {
            notifyCommandListener(sender, this, 1, "commands.gamemode.success.other", new Object[]{((ME)entityplayer).getName(), itextcomponent});
         }

      }
   }

   protected bbb getGameModeFromCommand(DB sender, String gameModeString) throws Ct, DD {
      bbb gametype = bbb.parseGameTypeWithDefault(gameModeString, bbb.NOT_SET);
      return gametype == bbb.NOT_SET ? biw.getGameTypeById(parseInt(gameModeString, 0, bbb.values().length - 2)) : gametype;
   }

   public List<String> getTabCompletions(Xx server, DB sender, String[] args, @Nullable BlockPos targetPos) {
      if (args.length == 1) {
         return getListOfStringsMatchingLastWord(args, new String[]{"survival", "creative", "adventure", "spectator"});
      } else {
         return args.length == 2 ? getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames()) : Collections.emptyList();
      }
   }

   public boolean isUsernameIndex(String[] args, int index) {
      return index == 1;
   }
}
