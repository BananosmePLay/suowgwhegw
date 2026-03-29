package neo;

import javax.annotation.Nullable;

public interface bar {
   @Nullable
   bam getLoadedChunk(int var1, int var2);

   bam provideChunk(int var1, int var2);

   boolean tick();

   String makeString();

   boolean isChunkGeneratedAt(int var1, int var2);
}
