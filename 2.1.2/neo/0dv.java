package neo;

import net.minecraft.client.multiplayer.ChunkProviderClient;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class 0dv {
   public static ChunkProviderClient cachedChunks;

   private static ChunkProviderClient AQVUBWS2ji() {
      return cachedChunks;
   }

   private static void ov7CkdeuGW(ChunkProviderClient var0) {
      cachedChunks = var0;
   }

   public static void createChunkProvider(World world) {
      if (GNFSvygHTo() == null) {
         ov7CkdeuGW(new ChunkProviderClient(world));
      }

   }

   public _dv/* $FF was: 0dv*/() {
   }

   private static ChunkProviderClient GNFSvygHTo() {
      return cachedChunks;
   }

   public static @NotNull ChunkProviderClient getChunkProvider() {
      return AQVUBWS2ji();
   }
}
