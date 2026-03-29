package neo;

import java.io.IOException;
import javax.annotation.Nullable;

public interface baC {
   @Nullable
   bam loadChunk(bij var1, int var2, int var3) throws IOException;

   void saveChunk(bij var1, bam var2) throws bgf, IOException;

   void saveExtraChunkData(bij var1, bam var2) throws IOException;

   void chunkTick();

   void flush();

   boolean isChunkGeneratedAt(int var1, int var2);
}
