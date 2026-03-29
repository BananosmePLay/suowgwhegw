package neo;

import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

public class DO extends Ch {
   public DO() {
   }

   public List<String> getAliases() {
      return Arrays.asList("w", "msg");
   }

   public String getName() {
      return "tell";
   }

   public int getRequiredPermissionLevel() {
      return 0;
   }

   public String getUsage(DB sender) {
      return "commands.message.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (args.length < 2) {
         throw new Ej("commands.message.usage", new Object[0]);
      } else {
         ME entityplayer = getPlayer(server, sender, args[0]);
         if (entityplayer == sender) {
            throw new DF("commands.message.sameTarget");
         } else {
            ITextComponent itextcomponent = getChatComponentFromNthArg(sender, args, 1, !(sender instanceof ME));
            TextComponentTranslation textcomponenttranslation = new TextComponentTranslation("commands.message.display.incoming", new Object[]{sender.getDisplayName(), itextcomponent.createCopy()});
            TextComponentTranslation textcomponenttranslation1 = new TextComponentTranslation("commands.message.display.outgoing", new Object[]{((ME)entityplayer).getDisplayName(), itextcomponent.createCopy()});
            textcomponenttranslation.getStyle().setColor(TextFormatting.GRAY).setItalic(true);
            textcomponenttranslation1.getStyle().setColor(TextFormatting.GRAY).setItalic(true);
            ((ME)entityplayer).sendMessage(textcomponenttranslation);
            sender.sendMessage(textcomponenttranslation1);
         }
      }
   }

   public List<String> getTabCompletions(Xx server, DB sender, String[] args, @Nullable BlockPos targetPos) {
      return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
   }

   public boolean isUsernameIndex(String[] args, int index) {
      return index == 0;
   }
}
