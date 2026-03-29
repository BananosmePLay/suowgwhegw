package neo;

import com.google.common.collect.Lists;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class As implements AA {
   private static final Logger LOGGER = LogManager.getLogger();
   protected final List<AC> resourcePacks = Lists.newArrayList();
   private final Aj frmMetadataSerializer;

   public As(Aj frmMetadataSerializerIn) {
      this.frmMetadataSerializer = frmMetadataSerializerIn;
   }

   public void addResourcePack(AC resourcePack) {
      this.resourcePacks.add(resourcePack);
   }

   public Set<String> getResourceDomains() {
      return Collections.emptySet();
   }

   public Az getResource(ResourceLocation location) throws IOException {
      this.checkResourcePath(location);
      AC iresourcepack = null;
      ResourceLocation resourcelocation = getLocationMcmeta(location);

      for(int i = this.resourcePacks.size() - 1; i >= 0; --i) {
         AC iresourcepack1 = (AC)this.resourcePacks.get(i);
         if (iresourcepack == null && iresourcepack1.resourceExists(resourcelocation)) {
            iresourcepack = iresourcepack1;
         }

         if (iresourcepack1.resourceExists(location)) {
            InputStream inputstream = null;
            if (iresourcepack != null) {
               inputstream = this.getInputStream(resourcelocation, iresourcepack);
            }

            return new AY(iresourcepack1.getPackName(), location, this.getInputStream(location, iresourcepack1), inputstream, this.frmMetadataSerializer);
         }
      }

      throw new FileNotFoundException(location.toString());
   }

   protected InputStream getInputStream(ResourceLocation location, AC resourcePack) throws IOException {
      InputStream inputstream = resourcePack.getInputStream(location);
      return (InputStream)(LOGGER.isDebugEnabled() ? new Ar(inputstream, location, resourcePack.getPackName()) : inputstream);
   }

   private void checkResourcePath(ResourceLocation p_188552_1_) throws IOException {
      if (p_188552_1_.getPath().contains("..")) {
         throw new IOException("Invalid relative path to resource: " + p_188552_1_);
      }
   }

   public List<Az> getAllResources(ResourceLocation location) throws IOException {
      this.checkResourcePath(location);
      List<Az> list = Lists.newArrayList();
      ResourceLocation resourcelocation = getLocationMcmeta(location);
      Iterator var4 = this.resourcePacks.iterator();

      while(var4.hasNext()) {
         AC iresourcepack = (AC)var4.next();
         if (iresourcepack.resourceExists(location)) {
            InputStream inputstream = iresourcepack.resourceExists(resourcelocation) ? this.getInputStream(resourcelocation, iresourcepack) : null;
            list.add(new AY(iresourcepack.getPackName(), location, this.getInputStream(location, iresourcepack), inputstream, this.frmMetadataSerializer));
         }
      }

      if (list.isEmpty()) {
         throw new FileNotFoundException(location.toString());
      } else {
         return list;
      }
   }

   static ResourceLocation getLocationMcmeta(ResourceLocation location) {
      return new ResourceLocation(location.getNamespace(), location.getPath() + ".mcmeta");
   }

   // $FF: synthetic method
   static Logger access$000() {
      return LOGGER;
   }
}
