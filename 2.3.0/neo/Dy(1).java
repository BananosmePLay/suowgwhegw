package neo;

import java.util.List;
import net.minecraft.util.math.BlockPos;

public interface Dy extends Comparable<Dy> {
   String getName();

   String getUsage(DB var1);

   List<String> getAliases();

   void execute(Xx var1, DB var2, String[] var3) throws Ct;

   boolean checkPermission(Xx var1, DB var2);

   List<String> getTabCompletions(Xx var1, DB var2, String[] var3, BlockPos var4);

   boolean isUsernameIndex(String[] var1, int var2);
}
