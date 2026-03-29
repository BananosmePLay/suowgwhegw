package neo;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;

public interface bcn {
   bam generateChunk(int var1, int var2);

   void populate(int var1, int var2);

   boolean generateStructures(bam var1, int var2, int var3);

   List<Zg> getPossibleCreatures(IC var1, BlockPos var2);

   @Nullable
   BlockPos getNearestStructurePos(bij var1, String var2, BlockPos var3, boolean var4);

   void recreateStructures(bam var1, int var2, int var3);

   boolean isInsideStructure(bij var1, String var2, BlockPos var3);
}
