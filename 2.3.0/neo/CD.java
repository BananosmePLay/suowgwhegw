package neo;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;

public class CD extends Ch {
   public CD() {
   }

   public String getName() {
      return "locate";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getUsage(DB sender) {
      return "commands.locate.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (args.length != 1) {
         throw new Ej("commands.locate.usage", new Object[0]);
      } else {
         String s = args[0];
         BlockPos blockpos = sender.getEntityWorld().findNearestStructure(s, sender.getPosition(), false);
         if (blockpos != null) {
            sender.sendMessage(new TextComponentTranslation("commands.locate.success", new Object[]{s, blockpos.getX(), blockpos.getZ()}));
         } else {
            throw new Ct("commands.locate.failure", new Object[]{s});
         }
      }
   }

   public List<String> getTabCompletions(Xx server, DB sender, String[] args, @Nullable BlockPos targetPos) {
      return args.length == 1 ? getListOfStringsMatchingLastWord(args, new String[]{"Stronghold", "Monument", "Village", "Mansion", "EndCity", "Fortress", "Temple", "Mineshaft"}) : Collections.emptyList();
   }
}
