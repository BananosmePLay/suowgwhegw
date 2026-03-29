package neo;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;

public class Cp extends Ch {
   public Cp() {
   }

   public String getName() {
      return "difficulty";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getUsage(DB sender) {
      return "commands.difficulty.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (args.length <= 0) {
         throw new Ej("commands.difficulty.usage", new Object[0]);
      } else {
         baV enumdifficulty = this.getDifficultyFromCommand(args[0]);
         server.setDifficultyForAllWorlds(enumdifficulty);
         notifyCommandListener(sender, this, "commands.difficulty.success", new Object[]{new TextComponentTranslation(enumdifficulty.getTranslationKey(), new Object[0])});
      }
   }

   protected baV getDifficultyFromCommand(String difficultyString) throws Ct, DD {
      if (!"peaceful".equalsIgnoreCase(difficultyString) && !"p".equalsIgnoreCase(difficultyString)) {
         if (!"easy".equalsIgnoreCase(difficultyString) && !"e".equalsIgnoreCase(difficultyString)) {
            if (!"normal".equalsIgnoreCase(difficultyString) && !"n".equalsIgnoreCase(difficultyString)) {
               return !"hard".equalsIgnoreCase(difficultyString) && !"h".equalsIgnoreCase(difficultyString) ? baV.byId(parseInt(difficultyString, 0, 3)) : baV.HARD;
            } else {
               return baV.NORMAL;
            }
         } else {
            return baV.EASY;
         }
      } else {
         return baV.PEACEFUL;
      }
   }

   public List<String> getTabCompletions(Xx server, DB sender, String[] args, @Nullable BlockPos targetPos) {
      return args.length == 1 ? getListOfStringsMatchingLastWord(args, new String[]{"peaceful", "easy", "normal", "hard"}) : Collections.emptyList();
   }
}
