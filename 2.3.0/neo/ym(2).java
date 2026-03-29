package neo;

import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;

public class ym {
   private final Map<Integer, sD> simpleShapes = Maps.newHashMap();
   private final Map<Integer, sc> simpleShapesCache = Maps.newHashMap();
   private final Map<OL, yl> shapers = Maps.newHashMap();
   private final sC modelManager;

   public ym(sC modelManager) {
      this.modelManager = modelManager;
   }

   public zd getParticleIcon(OL item) {
      return this.getParticleIcon(item, 0);
   }

   public zd getParticleIcon(OL item, int meta) {
      return this.getItemModel(new Qy(item, 1, meta)).getParticleTexture();
   }

   public sc getItemModel(Qy stack) {
      OL item = stack.getItem();
      sc ibakedmodel = this.getItemModel(item, this.getMetadata(stack));
      if (ibakedmodel == null) {
         yl itemmeshdefinition = (yl)this.shapers.get(item);
         if (itemmeshdefinition != null) {
            ibakedmodel = this.modelManager.getModel(itemmeshdefinition.getModelLocation(stack));
         }
      }

      if (ibakedmodel == null) {
         ibakedmodel = this.modelManager.getMissingModel();
      }

      return ibakedmodel;
   }

   protected int getMetadata(Qy stack) {
      return stack.getMaxDamage() > 0 ? 0 : stack.getMetadata();
   }

   @Nullable
   protected sc getItemModel(OL item, int meta) {
      return (sc)this.simpleShapesCache.get(this.getIndex(item, meta));
   }

   private int getIndex(OL item, int meta) {
      return OL.getIdFromItem(item) << 16 | meta;
   }

   public void register(OL item, int meta, sD location) {
      this.simpleShapes.put(this.getIndex(item, meta), location);
      this.simpleShapesCache.put(this.getIndex(item, meta), this.modelManager.getModel(location));
   }

   public void register(OL item, yl definition) {
      this.shapers.put(item, definition);
   }

   public sC getModelManager() {
      return this.modelManager;
   }

   public void rebuildCache() {
      this.simpleShapesCache.clear();
      Iterator var1 = this.simpleShapes.entrySet().iterator();

      while(var1.hasNext()) {
         Map.Entry<Integer, sD> entry = (Map.Entry)var1.next();
         this.simpleShapesCache.put(entry.getKey(), this.modelManager.getModel((sD)entry.getValue()));
      }

   }
}
