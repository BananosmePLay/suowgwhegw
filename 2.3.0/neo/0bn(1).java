package neo;

import com.google.common.base.MoreObjects;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import javax.annotation.Nullable;
import net.minecraft.util.math.ChunkPos;
import org.jetbrains.annotations.NotNull;

public class 0bn implements bar {
   public final 0a bot;
   public final Long2ObjectMap<bam> loadedChunks = new 0bm(this, 1682 ^ 11166 ^ 5636 ^ 6920);
   private static String _DSC GG NEOWARECLIENT _;

   private static 0a imSvUBRwSZ(0bn var0) {
      return var0.bot;
   }

   public _bn/* $FF was: 0bn*/(0a a) {
      this.bot = a;
   }

   private static Long2ObjectMap j2nSX5F48J(0bn var0) {
      return var0.loadedChunks;
   }

   private static 0a jy3DzauJWN(0bn var0) {
      return var0.bot;
   }

   public boolean isChunkGeneratedAt(int a, int b) {
      return lbD9JWVatw(this).containsKey(ChunkPos.asLong(a, b));
   }

   private static Long2ObjectMap NgVCWNnNQS(0bn var0) {
      return var0.loadedChunks;
   }

   private static 0bo DyG2oYLI7b(0a var0) {
      return var0.world;
   }

   public boolean tick() {
      return (boolean)(17723 ^ -4576 ^ 16563 ^ -5208);
   }

   private static 0a _RrilGHFeE/* $FF was: 8RrilGHFeE*/(0bn var0) {
      return var0.bot;
   }

   private static 0a nhtGk3lwyN(0bn var0) {
      return var0.bot;
   }

   private static 0a v4NyvJ91Eo(0bn var0) {
      return var0.bot;
   }

   public @NotNull String makeString() {
      return 6DGV9dW9GS("");
   }

   private static 0a _WdTs93ybn/* $FF was: 1WdTs93ybn*/(0bn var0) {
      return var0.bot;
   }

   public void unloadChunk(int a, int b) {
      if (!8RrilGHFeE(this).isCached()) {
         bam c = this.provideChunk(a, b);
         if (!c.isEmpty()) {
            c.onUnload();
         }

         j2nSX5F48J(this).remove(ChunkPos.asLong(a, b));
      }
   }

   public void loadChunk(0bo a, int b, int c) {
      if (nhtGk3lwyN(this).isCached()) {
         0bq.method_LS(a, b, c);
      } else {
         bam d = new bam(a, b, c);
         Wvf9odoJLk(this).put(ChunkPos.asLong(b, c), d);
         d.markLoaded((boolean)(3848 ^ -25258 ^ 27541 ^ -1590));
      }
   }

   private static Long2ObjectMap Wvf9odoJLk(0bn var0) {
      return var0.loadedChunks;
   }

   @Nullable
   public bam getLoadedChunk(int a, int b) {
      return jy3DzauJWN(this).isCached() ? 0bq.method_LQ(GBfVYcOq6J(v4NyvJ91Eo(this)), a, b) : (bam)NgVCWNnNQS(this).get(ChunkPos.asLong(a, b));
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String _DGV9dW9GS/* $FF was: 6DGV9dW9GS*/(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 1920 ^ -16255 ^ 27440 ^ -21455; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 31700 ^ -2049 ^ 13702 ^ -17000));
      }

      return var1.toString();
   }

   private static 0bo GBfVYcOq6J(0a var0) {
      return var0.world;
   }

   private static Long2ObjectMap lbD9JWVatw(0bn var0) {
      return var0.loadedChunks;
   }

   public @NotNull bam provideChunk(int a, int b) {
      return imSvUBRwSZ(this).isCached() ? 0bq.method_LR(DyG2oYLI7b(1WdTs93ybn(this)), a, b) : (bam)MoreObjects.firstNonNull(this.getLoadedChunk(a, b), 0bq.method_LT());
   }
}
