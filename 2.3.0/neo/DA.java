package neo;

import java.util.List;
import java.util.Map;
import net.minecraft.util.math.BlockPos;

public interface DA {
   int executeCommand(DB var1, String var2);

   List<String> getTabCompletions(DB var1, String var2, BlockPos var3);

   List<Dy> getPossibleCommands(DB var1);

   Map<String, Dy> getCommands();
}
