package neo;

import com.google.common.cache.CacheLoader;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.google.gson.JsonParseException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import javax.annotation.Nullable;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

class bhs extends CacheLoader<ResourceLocation, bhp> {
   // $FF: synthetic field
   final bht this$0;

   private bhs(bht this$0) {
      this.this$0 = this$0;
   }

   public bhp load(ResourceLocation p_load_1_) throws Exception {
      if (p_load_1_.getPath().contains(".")) {
         bht.access$100().debug("Invalid loot table name '{}' (can't contain periods)", p_load_1_);
         return bhp.EMPTY_LOOT_TABLE;
      } else {
         bhp loottable = this.loadLootTable(p_load_1_);
         if (loottable == null) {
            loottable = this.loadBuiltinLootTable(p_load_1_);
         }

         if (loottable == null) {
            loottable = bhp.EMPTY_LOOT_TABLE;
            bht.access$100().warn("Couldn't find resource table {}", p_load_1_);
         }

         return loottable;
      }
   }

   @Nullable
   private bhp loadLootTable(ResourceLocation resource) {
      if (bht.access$200(this.this$0) == null) {
         return null;
      } else {
         File file1 = new File(new File(bht.access$200(this.this$0), resource.getNamespace()), resource.getPath() + ".json");
         if (file1.exists()) {
            if (file1.isFile()) {
               String s;
               try {
                  s = Files.toString(file1, StandardCharsets.UTF_8);
               } catch (IOException var6) {
                  IOException ioexception = var6;
                  bht.access$100().warn("Couldn't load loot table {} from {}", resource, file1, ioexception);
                  return bhp.EMPTY_LOOT_TABLE;
               }

               try {
                  return (bhp)JsonUtils.gsonDeserialize(bht.access$300(), s, bhp.class);
               } catch (JsonParseException | IllegalArgumentException var5) {
                  RuntimeException jsonparseexception = var5;
                  bht.access$100().error("Couldn't load loot table {} from {}", resource, file1, jsonparseexception);
                  return bhp.EMPTY_LOOT_TABLE;
               }
            } else {
               bht.access$100().warn("Expected to find loot table {} at {} but it was a folder.", resource, file1);
               return bhp.EMPTY_LOOT_TABLE;
            }
         } else {
            return null;
         }
      }
   }

   @Nullable
   private bhp loadBuiltinLootTable(ResourceLocation resource) {
      URL url = bht.class.getResource("/assets/" + resource.getNamespace() + "/loot_tables/" + resource.getPath() + ".json");
      if (url != null) {
         String s;
         try {
            s = Resources.toString(url, StandardCharsets.UTF_8);
         } catch (IOException var6) {
            IOException ioexception = var6;
            bht.access$100().warn("Couldn't load loot table {} from {}", resource, url, ioexception);
            return bhp.EMPTY_LOOT_TABLE;
         }

         try {
            return (bhp)JsonUtils.gsonDeserialize(bht.access$300(), s, bhp.class);
         } catch (JsonParseException var5) {
            JsonParseException jsonparseexception = var5;
            bht.access$100().error("Couldn't load loot table {} from {}", resource, url, jsonparseexception);
            return bhp.EMPTY_LOOT_TABLE;
         }
      } else {
         return null;
      }
   }

   // $FF: synthetic method
   // $FF: bridge method
   public Object load(Object var1) throws Exception {
      return this.load((ResourceLocation)var1);
   }

   // $FF: synthetic method
   bhs(bht x0, Object x1) {
      this(x0);
   }
}
