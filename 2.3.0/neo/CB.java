package neo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;

public class CB extends Ch {
   private static final String[] SEARGE_SAYS = new String[]{"Yolo", "Ask for help on twitter", "/deop @p", "Scoreboard deleted, commands blocked", "Contact helpdesk for help", "/testfornoob @p", "/trigger warning", "Oh my god, it's full of stats", "/kill @p[name=!Searge]", "Have you tried turning it off and on again?", "Sorry, no help today"};
   private final Random rand = new Random();

   public CB() {
   }

   public String getName() {
      return "help";
   }

   public int getRequiredPermissionLevel() {
      return 0;
   }

   public String getUsage(DB sender) {
      return "commands.help.usage";
   }

   public List<String> getAliases() {
      return Arrays.asList("?");
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (sender instanceof XZ) {
         sender.sendMessage((new TextComponentString("Searge says: ")).appendText(SEARGE_SAYS[this.rand.nextInt(SEARGE_SAYS.length) % SEARGE_SAYS.length]));
      } else {
         List<Dy> list = this.getSortedPossibleCommands(sender, server);
         int i = true;
         int j = (list.size() - 1) / 7;
         int k = false;

         int k;
         try {
            k = args.length == 0 ? 0 : parseInt(args[0], 1, j + 1) - 1;
         } catch (DD var13) {
            DD numberinvalidexception = var13;
            Map<String, Dy> map = this.getCommandMap(server);
            Dy icommand = (Dy)map.get(args[0]);
            if (icommand != null) {
               throw new Ej(icommand.getUsage(sender), new Object[0]);
            }

            if (MathHelper.getInt(args[0], -1) == -1 && MathHelper.getInt(args[0], -2) == -2) {
               throw new CE();
            }

            throw numberinvalidexception;
         }

         int l = Math.min((k + 1) * 7, list.size());
         TextComponentTranslation textcomponenttranslation1 = new TextComponentTranslation("commands.help.header", new Object[]{k + 1, j + 1});
         textcomponenttranslation1.getStyle().setColor(TextFormatting.DARK_GREEN);
         sender.sendMessage(textcomponenttranslation1);

         for(int i1 = k * 7; i1 < l; ++i1) {
            Dy icommand1 = (Dy)list.get(i1);
            TextComponentTranslation textcomponenttranslation = new TextComponentTranslation(icommand1.getUsage(sender), new Object[0]);
            textcomponenttranslation.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/" + icommand1.getName() + " "));
            sender.sendMessage(textcomponenttranslation);
         }

         if (k == 0) {
            TextComponentTranslation textcomponenttranslation2 = new TextComponentTranslation("commands.help.footer", new Object[0]);
            textcomponenttranslation2.getStyle().setColor(TextFormatting.GREEN);
            sender.sendMessage(textcomponenttranslation2);
         }
      }

   }

   protected List<Dy> getSortedPossibleCommands(DB sender, Xx server) {
      List<Dy> list = server.getCommandManager().getPossibleCommands(sender);
      Collections.sort(list);
      return list;
   }

   protected Map<String, Dy> getCommandMap(Xx server) {
      return server.getCommandManager().getCommands();
   }

   public List<String> getTabCompletions(Xx server, DB sender, String[] args, @Nullable BlockPos targetPos) {
      if (args.length == 1) {
         Set<String> set = this.getCommandMap(server).keySet();
         return getListOfStringsMatchingLastWord(args, (String[])((String[])set.toArray(new String[set.size()])));
      } else {
         return Collections.emptyList();
      }
   }
}
