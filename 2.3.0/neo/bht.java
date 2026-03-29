package neo;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.util.Iterator;
import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bht {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final Gson GSON_INSTANCE = (new GsonBuilder()).registerTypeAdapter(bhC.class, new bhB()).registerTypeAdapter(bhn.class, new bhm()).registerTypeAdapter(bhp.class, new bho()).registerTypeHierarchyAdapter(bhi.class, new bhh()).registerTypeHierarchyAdapter(bgI.class, new bgJ()).registerTypeHierarchyAdapter(bgv.class, new bgw()).registerTypeHierarchyAdapter(bhf.class, new bhe()).create();
   private final LoadingCache<ResourceLocation, bhp> registeredLootTables = CacheBuilder.newBuilder().build(new bhs(this));
   private final File baseFolder;

   public bht(@Nullable File folder) {
      this.baseFolder = folder;
      this.reloadLootTables();
   }

   public bhp getLootTableFromLocation(ResourceLocation ressources) {
      return (bhp)this.registeredLootTables.getUnchecked(ressources);
   }

   public void reloadLootTables() {
      this.registeredLootTables.invalidateAll();
      Iterator var1 = bhq.getAll().iterator();

      while(var1.hasNext()) {
         ResourceLocation resourcelocation = (ResourceLocation)var1.next();
         this.getLootTableFromLocation(resourcelocation);
      }

   }

   // $FF: synthetic method
   static Logger access$100() {
      return LOGGER;
   }

   // $FF: synthetic method
   static File access$200(bht x0) {
      return x0.baseFolder;
   }

   // $FF: synthetic method
   static Gson access$300() {
      return GSON_INSTANCE;
   }
}
