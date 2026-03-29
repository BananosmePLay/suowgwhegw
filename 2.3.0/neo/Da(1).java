package neo;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;

public class Da extends Ch {
   public Da() {
   }

   public String getName() {
      return "weather";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getUsage(DB sender) {
      return "commands.weather.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (args.length >= 1 && args.length <= 2) {
         int i = (300 + (new Random()).nextInt(600)) * 20;
         if (args.length >= 2) {
            i = parseInt(args[1], 1, 1000000) * 20;
         }

         bij world = server.worlds[0];
         bhY worldinfo = ((bij)world).getWorldInfo();
         if ("clear".equalsIgnoreCase(args[0])) {
            worldinfo.setCleanWeatherTime(i);
            worldinfo.setRainTime(0);
            worldinfo.setThunderTime(0);
            worldinfo.setRaining(false);
            worldinfo.setThundering(false);
            notifyCommandListener(sender, this, "commands.weather.clear", new Object[0]);
         } else if ("rain".equalsIgnoreCase(args[0])) {
            worldinfo.setCleanWeatherTime(0);
            worldinfo.setRainTime(i);
            worldinfo.setThunderTime(i);
            worldinfo.setRaining(true);
            worldinfo.setThundering(false);
            notifyCommandListener(sender, this, "commands.weather.rain", new Object[0]);
         } else {
            if (!"thunder".equalsIgnoreCase(args[0])) {
               throw new Ej("commands.weather.usage", new Object[0]);
            }

            worldinfo.setCleanWeatherTime(0);
            worldinfo.setRainTime(i);
            worldinfo.setThunderTime(i);
            worldinfo.setRaining(true);
            worldinfo.setThundering(true);
            notifyCommandListener(sender, this, "commands.weather.thunder", new Object[0]);
         }

      } else {
         throw new Ej("commands.weather.usage", new Object[0]);
      }
   }

   public List<String> getTabCompletions(Xx server, DB sender, String[] args, @Nullable BlockPos targetPos) {
      return args.length == 1 ? getListOfStringsMatchingLastWord(args, new String[]{"clear", "rain", "thunder"}) : Collections.emptyList();
   }
}
