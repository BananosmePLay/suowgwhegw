package neo;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AX implements Ay {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final Joiner JOINER_RESOURCE_PACKS = Joiner.on(", ");
   private final Map<String, As> domainResourceManagers = Maps.newHashMap();
   private final List<AB> reloadListeners = Lists.newArrayList();
   private final Set<String> setResourceDomains = Sets.newLinkedHashSet();
   private final Aj rmMetadataSerializer;

   public AX(Aj rmMetadataSerializerIn) {
      this.rmMetadataSerializer = rmMetadataSerializerIn;
   }

   public void reloadResourcePack(AC resourcePack) {
      As fallbackresourcemanager;
      for(Iterator var2 = resourcePack.getResourceDomains().iterator(); var2.hasNext(); fallbackresourcemanager.addResourcePack(resourcePack)) {
         String s = (String)var2.next();
         this.setResourceDomains.add(s);
         fallbackresourcemanager = (As)this.domainResourceManagers.get(s);
         if (fallbackresourcemanager == null) {
            fallbackresourcemanager = new As(this.rmMetadataSerializer);
            this.domainResourceManagers.put(s, fallbackresourcemanager);
         }
      }

   }

   public Set<String> getResourceDomains() {
      return this.setResourceDomains;
   }

   public Az getResource(ResourceLocation location) throws IOException {
      AA iresourcemanager = (AA)this.domainResourceManagers.get(location.getNamespace());
      if (iresourcemanager != null) {
         return iresourcemanager.getResource(location);
      } else {
         throw new FileNotFoundException(location.toString());
      }
   }

   public List<Az> getAllResources(ResourceLocation location) throws IOException {
      AA iresourcemanager = (AA)this.domainResourceManagers.get(location.getNamespace());
      if (iresourcemanager != null) {
         return iresourcemanager.getAllResources(location);
      } else {
         throw new FileNotFoundException(location.toString());
      }
   }

   private void clearResources() {
      this.domainResourceManagers.clear();
      this.setResourceDomains.clear();
   }

   public void reloadResources(List<AC> resourcesPacksList) {
      this.clearResources();
      LOGGER.info("Reloading ResourceManager: {}", JOINER_RESOURCE_PACKS.join(Iterables.transform(resourcesPacksList, new Function<AC, String>() {
         public String apply(@Nullable AC p_apply_1_) {
            return p_apply_1_ == null ? "<NULL>" : p_apply_1_.getPackName();
         }

         // $FF: synthetic method
         // $FF: bridge method
         public Object apply(@Nullable Object var1) {
            return this.apply((AC)var1);
         }
      })));
      Iterator var2 = resourcesPacksList.iterator();

      while(var2.hasNext()) {
         AC iresourcepack = (AC)var2.next();
         this.reloadResourcePack(iresourcepack);
      }

      this.notifyReloadListeners();
   }

   public void registerReloadListener(AB reloadListener) {
      this.reloadListeners.add(reloadListener);
      reloadListener.onResourceManagerReload(this);
   }

   private void notifyReloadListeners() {
      Iterator var1 = this.reloadListeners.iterator();

      while(var1.hasNext()) {
         AB iresourcemanagerreloadlistener = (AB)var1.next();
         iresourcemanagerreloadlistener.onResourceManagerReload(this);
      }

   }
}
