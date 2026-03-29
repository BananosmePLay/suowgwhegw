package neo;

import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;

public class mo {
   private static final ResourceLocation TEXTURE_MAP_ICONS = new ResourceLocation("textures/map/map_icons.png");
   private final zf textureManager;
   private final Map<String, mn> loadedMaps = Maps.newHashMap();

   public mo(zf textureManagerIn) {
      this.textureManager = textureManagerIn;
   }

   public void updateMapTexture(bhE mapdataIn) {
      mn.access$000(this.getMapRendererInstance(mapdataIn));
   }

   public void renderMap(bhE mapdataIn, boolean noOverlayRendering) {
      mn.access$100(this.getMapRendererInstance(mapdataIn), noOverlayRendering);
   }

   private mn getMapRendererInstance(bhE mapdataIn) {
      mn mapitemrenderer$instance = (mn)this.loadedMaps.get(mapdataIn.mapName);
      if (mapitemrenderer$instance == null) {
         mapitemrenderer$instance = new mn(this, mapdataIn);
         this.loadedMaps.put(mapdataIn.mapName, mapitemrenderer$instance);
      }

      return mapitemrenderer$instance;
   }

   @Nullable
   public mn getMapInstanceIfExists(String p_191205_1_) {
      return (mn)this.loadedMaps.get(p_191205_1_);
   }

   public void clearLoadedMaps() {
      Iterator var1 = this.loadedMaps.values().iterator();

      while(var1.hasNext()) {
         mn mapitemrenderer$instance = (mn)var1.next();
         this.textureManager.deleteTexture(mn.access$300(mapitemrenderer$instance));
      }

      this.loadedMaps.clear();
   }

   @Nullable
   public bhE getData(@Nullable mn p_191207_1_) {
      return p_191207_1_ != null ? mn.access$400(p_191207_1_) : null;
   }

   // $FF: synthetic method
   static zf access$500(mo x0) {
      return x0.textureManager;
   }

   // $FF: synthetic method
   static ResourceLocation access$600() {
      return TEXTURE_MAP_ICONS;
   }
}
