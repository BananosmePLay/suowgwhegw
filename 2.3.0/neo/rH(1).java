package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;

public class rH {
   private final Map<String, rI> cacheMap = Maps.newLinkedHashMap();
   private final ResourceLocation cacheResourceLocation;
   private final String cacheResourceBase;
   private final String cacheId;

   public rH(String id, ResourceLocation baseResource, String resourcePath) {
      this.cacheId = id;
      this.cacheResourceLocation = baseResource;
      this.cacheResourceBase = resourcePath;
   }

   @Nullable
   public ResourceLocation getResourceLocation(String id, List<XW> patternList, List<Om> colorList) {
      if (id.isEmpty()) {
         return null;
      } else {
         id = this.cacheId + id;
         rI bannertextures$cacheentry = (rI)this.cacheMap.get(id);
         if (bannertextures$cacheentry == null) {
            if (this.cacheMap.size() >= 256 && !this.freeCacheSlot()) {
               return rJ.BANNER_BASE_TEXTURE;
            }

            List<String> list = Lists.newArrayList();
            Iterator var6 = patternList.iterator();

            while(var6.hasNext()) {
               XW bannerpattern = (XW)var6.next();
               list.add(this.cacheResourceBase + bannerpattern.getFileName() + ".png");
            }

            bannertextures$cacheentry = new rI();
            bannertextures$cacheentry.textureLocation = new ResourceLocation(id);
            nC.getMinecraft().getTextureManager().loadTexture(bannertextures$cacheentry.textureLocation, new yU(this.cacheResourceLocation, list, colorList));
            this.cacheMap.put(id, bannertextures$cacheentry);
         }

         bannertextures$cacheentry.lastUseMillis = System.currentTimeMillis();
         return bannertextures$cacheentry.textureLocation;
      }
   }

   private boolean freeCacheSlot() {
      long i = System.currentTimeMillis();
      Iterator<String> iterator = this.cacheMap.keySet().iterator();

      rI bannertextures$cacheentry;
      do {
         if (!iterator.hasNext()) {
            return this.cacheMap.size() < 256;
         }

         String s = (String)iterator.next();
         bannertextures$cacheentry = (rI)this.cacheMap.get(s);
      } while(i - bannertextures$cacheentry.lastUseMillis <= 5000L);

      nC.getMinecraft().getTextureManager().deleteTexture(bannertextures$cacheentry.textureLocation);
      iterator.remove();
      return true;
   }
}
