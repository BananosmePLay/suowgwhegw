package neo;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.util.ResourceLocation;

public class bki {
   private static boolean active = false;
   private static Map<Class, vI> originalEntityRenderMap = null;
   private static Map<Class, zF> originalTileEntityRenderMap = null;

   public bki() {
   }

   public static void update() {
      Map<Class, vI> map = getEntityRenderMap();
      Map<Class, zF> map1 = getTileEntityRenderMap();
      if (map == null) {
         XH.warn("Entity render map not found, custom entity models are DISABLED.");
      } else if (map1 == null) {
         XH.warn("Tile entity render map not found, custom entity models are DISABLED.");
      } else {
         active = false;
         map.clear();
         map1.clear();
         map.putAll(originalEntityRenderMap);
         map1.putAll(originalTileEntityRenderMap);
         if (XH.isCustomEntityModels()) {
            ResourceLocation[] aresourcelocation = getModelLocations();

            for(int i = 0; i < aresourcelocation.length; ++i) {
               ResourceLocation resourcelocation = aresourcelocation[i];
               XH.dbg("CustomEntityModel: " + resourcelocation.getPath());
               bkm ientityrenderer = parseEntityRender(resourcelocation);
               if (ientityrenderer != null) {
                  Class oclass = ientityrenderer.getEntityClass();
                  if (oclass != null) {
                     if (ientityrenderer instanceof vI) {
                        map.put(oclass, (vI)ientityrenderer);
                     } else if (ientityrenderer instanceof zF) {
                        map1.put(oclass, (zF)ientityrenderer);
                     } else {
                        XH.warn("Unknown renderer type: " + ientityrenderer.getClass().getName());
                     }

                     active = true;
                  }
               }
            }
         }
      }

   }

   private static Map<Class, vI> getEntityRenderMap() {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      Map<Class, vI> map = rendermanager.getEntityRenderMap();
      if (map == null) {
         return null;
      } else {
         if (originalEntityRenderMap == null) {
            originalEntityRenderMap = new HashMap(map);
         }

         return map;
      }
   }

   private static Map<Class, zF> getTileEntityRenderMap() {
      Map<Class, zF> map = zz.instance.renderers;
      if (originalTileEntityRenderMap == null) {
         originalTileEntityRenderMap = new HashMap(map);
      }

      return map;
   }

   private static ResourceLocation[] getModelLocations() {
      String s = "optifine/cem/";
      String s1 = ".jem";
      List<ResourceLocation> list = new ArrayList();
      String[] astring = bkk.getModelNames();

      for(int i = 0; i < astring.length; ++i) {
         String s2 = astring[i];
         String s3 = s + s2 + s1;
         ResourceLocation resourcelocation = new ResourceLocation(s3);
         if (XH.hasResource(resourcelocation)) {
            list.add(resourcelocation);
         }
      }

      ResourceLocation[] aresourcelocation = (ResourceLocation[])((ResourceLocation[])list.toArray(new ResourceLocation[list.size()]));
      return aresourcelocation;
   }

   private static bkm parseEntityRender(ResourceLocation location) {
      try {
         JsonObject jsonobject = bkh.loadJson(location);
         bkm ientityrenderer = parseEntityRender(jsonobject, location.getPath());
         return ientityrenderer;
      } catch (IOException var3) {
         IOException ioexception = var3;
         XH.error("" + ioexception.getClass().getName() + ": " + ioexception.getMessage());
         return null;
      } catch (JsonParseException var4) {
         JsonParseException jsonparseexception = var4;
         XH.error("" + jsonparseexception.getClass().getName() + ": " + jsonparseexception.getMessage());
         return null;
      } catch (Exception var5) {
         Exception exception = var5;
         exception.printStackTrace();
         return null;
      }
   }

   private static bkm parseEntityRender(JsonObject obj, String path) {
      bkj customentityrenderer = bkh.parseEntityRender(obj, path);
      String s = customentityrenderer.getName();
      bkn modeladapter = bkk.getModelAdapter(s);
      checkNull(modeladapter, "Entity not found: " + s);
      Class oclass = modeladapter.getEntityClass();
      checkNull(oclass, "Entity class not found: " + s);
      bkm ientityrenderer = makeEntityRender(modeladapter, customentityrenderer);
      if (ientityrenderer == null) {
         return null;
      } else {
         ientityrenderer.setEntityClass(oclass);
         return ientityrenderer;
      }
   }

   private static bkm makeEntityRender(bkn modelAdapter, bkj cer) {
      ResourceLocation resourcelocation = cer.getTextureLocation();
      bkl[] acustommodelrenderer = cer.getCustomModelRenderers();
      float f = cer.getShadowSize();
      if (f < 0.0F) {
         f = modelAdapter.getShadowSize();
      }

      nH modelbase = modelAdapter.makeModel();
      if (modelbase == null) {
         return null;
      } else {
         bjU modelresolver = new bjU(modelAdapter, modelbase, acustommodelrenderer);
         if (!modifyModel(modelAdapter, modelbase, acustommodelrenderer, modelresolver)) {
            return null;
         } else {
            bkm ientityrenderer = modelAdapter.makeEntityRender(modelbase, f);
            if (ientityrenderer == null) {
               throw new JsonParseException("Entity renderer is null, model: " + modelAdapter.getName() + ", adapter: " + modelAdapter.getClass().getName());
            } else {
               if (resourcelocation != null) {
                  ientityrenderer.setLocationTextureCustom(resourcelocation);
               }

               return ientityrenderer;
            }
         }
      }
   }

   private static boolean modifyModel(bkn modelAdapter, nH model, bkl[] modelRenderers, bjU mr) {
      for(int i = 0; i < modelRenderers.length; ++i) {
         bkl custommodelrenderer = modelRenderers[i];
         if (!modifyModel(modelAdapter, model, custommodelrenderer, mr)) {
            return false;
         }
      }

      return true;
   }

   private static boolean modifyModel(bkn modelAdapter, nH model, bkl customModelRenderer, bjU modelResolver) {
      String s = customModelRenderer.getModelPart();
      ow modelrenderer = modelAdapter.getModelRenderer(model, s);
      if (modelrenderer == null) {
         XH.warn("Model part not found: " + s + ", model: " + model);
         return false;
      } else {
         if (!customModelRenderer.isAttach()) {
            if (modelrenderer.cubeList != null) {
               modelrenderer.cubeList.clear();
            }

            if (modelrenderer.spriteList != null) {
               modelrenderer.spriteList.clear();
            }

            if (modelrenderer.childModels != null) {
               ow[] amodelrenderer = modelAdapter.getModelRenderers(model);
               Set<ow> set = Collections.newSetFromMap(new IdentityHashMap());
               set.addAll(Arrays.asList(amodelrenderer));
               List<ow> list = modelrenderer.childModels;
               Iterator iterator = list.iterator();

               while(iterator.hasNext()) {
                  ow modelrenderer1 = (ow)iterator.next();
                  if (!set.contains(modelrenderer1)) {
                     iterator.remove();
                  }
               }
            }
         }

         modelrenderer.addChild(customModelRenderer.getModelRenderer());
         bjV modelupdater = customModelRenderer.getModelUpdater();
         if (modelupdater != null) {
            modelResolver.setThisModelRenderer(customModelRenderer.getModelRenderer());
            modelResolver.setPartModelRenderer(modelrenderer);
            if (!modelupdater.initialize(modelResolver)) {
               return false;
            }

            customModelRenderer.getModelRenderer().setModelUpdater(modelupdater);
         }

         return true;
      }
   }

   private static void checkNull(Object obj, String msg) {
      if (obj == null) {
         throw new JsonParseException(msg);
      }
   }

   public static boolean isActive() {
      return active;
   }
}
