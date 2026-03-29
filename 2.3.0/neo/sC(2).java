package neo;

import net.minecraft.util.registry.IRegistry;

public class sC implements AB {
   private IRegistry<sD, sc> modelRegistry;
   private final zj texMap;
   private final tH modelProvider;
   private sc defaultModel;

   public sC(zj textures) {
      this.texMap = textures;
      this.modelProvider = new tH(this);
   }

   public void onResourceManagerReload(AA resourceManager) {
      st modelbakery = new st(resourceManager, this.texMap, this.modelProvider);
      this.modelRegistry = modelbakery.setupModelRegistry();
      this.defaultModel = (sc)this.modelRegistry.getObject(st.MODEL_MISSING);
      this.modelProvider.reloadModels();
   }

   public sc getModel(sD modelLocation) {
      if (modelLocation == null) {
         return this.defaultModel;
      } else {
         sc ibakedmodel = (sc)this.modelRegistry.getObject(modelLocation);
         return ibakedmodel == null ? this.defaultModel : ibakedmodel;
      }
   }

   public sc getMissingModel() {
      return this.defaultModel;
   }

   public zj getTextureMap() {
      return this.texMap;
   }

   public tH getBlockModelShapes() {
      return this.modelProvider;
   }
}
