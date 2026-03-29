package neo;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.util.ResourceLocation;

public class wo extends ww<LF> {
   private static final Map<String, ResourceLocation> LAYERED_LOCATION_CACHE = Maps.newHashMap();

   public wo(wC p_i47205_1_) {
      super(p_i47205_1_, new oe(), 0.75F);
   }

   protected ResourceLocation getEntityTexture(LF entity) {
      String s = entity.getHorseTexture();
      ResourceLocation resourcelocation = (ResourceLocation)LAYERED_LOCATION_CACHE.get(s);
      if (resourcelocation == null) {
         resourcelocation = new ResourceLocation(s);
         nC.getMinecraft().getTextureManager().loadTexture(resourcelocation, new yV(entity.getVariantTexturePaths()));
         LAYERED_LOCATION_CACHE.put(s, resourcelocation);
      }

      return resourcelocation;
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((LF)var1);
   }
}
