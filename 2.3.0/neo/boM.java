package neo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import net.minecraft.util.ResourceLocation;

public class boM {
   private static int[] entityAliases = null;
   private static boolean updateOnResourcesReloaded;

   public boM() {
   }

   public static int getEntityAliasId(int entityId) {
      if (entityAliases == null) {
         return -1;
      } else if (entityId >= 0 && entityId < entityAliases.length) {
         int i = entityAliases[entityId];
         return i;
      } else {
         return -1;
      }
   }

   public static void resourcesReloaded() {
      if (updateOnResourcesReloaded) {
         updateOnResourcesReloaded = false;
         update(bpq.getShaderPack());
      }

   }

   public static void update(bpa shaderPack) {
      reset();
      if (shaderPack != null) {
         if (bnK.Loader_getActiveModList.exists() && XH.getResourceManager() == null) {
            XH.dbg("[Shaders] Delayed loading of entity mappings after resources are loaded");
            updateOnResourcesReloaded = true;
         } else {
            List<Integer> list = new ArrayList();
            String s = "/shaders/entity.properties";
            InputStream inputstream = shaderPack.getResourceAsStream(s);
            if (inputstream != null) {
               loadEntityAliases(inputstream, s, list);
            }

            loadModEntityAliases(list);
            if (list.size() > 0) {
               entityAliases = toArray(list);
            }
         }
      }

   }

   private static void loadModEntityAliases(List<Integer> listEntityAliases) {
      String[] astring = bnQ.getForgeModIds();

      for(int i = 0; i < astring.length; ++i) {
         String s = astring[i];

         try {
            ResourceLocation resourcelocation = new ResourceLocation(s, "shaders/entity.properties");
            InputStream inputstream = XH.getResourceStream(resourcelocation);
            loadEntityAliases(inputstream, resourcelocation.toString(), listEntityAliases);
         } catch (IOException var6) {
         }
      }

   }

   private static void loadEntityAliases(InputStream in, String path, List<Integer> listEntityAliases) {
      if (in != null) {
         try {
            in = boj.process(in, path);
            Properties properties = new bqL();
            ((Properties)properties).load(in);
            in.close();
            XH.dbg("[Shaders] Parsing entity mappings: " + path);
            biS connectedparser = new biS("Shaders");
            Iterator var5 = ((Properties)properties).keySet().iterator();

            while(true) {
               while(var5.hasNext()) {
                  Object ss = var5.next();
                  String s = (String)ss;
                  String s1 = ((Properties)properties).getProperty(s);
                  String s2 = "entity.";
                  if (!s.startsWith(s2)) {
                     XH.warn("[Shaders] Invalid entity ID: " + s);
                  } else {
                     String s3 = bqP.removePrefix(s, s2);
                     int i = XH.parseInt(s3, -1);
                     if (i < 0) {
                        XH.warn("[Shaders] Invalid entity alias ID: " + i);
                     } else {
                        int[] aint = connectedparser.parseEntities(s1);
                        if (aint != null && aint.length >= 1) {
                           for(int j = 0; j < aint.length; ++j) {
                              int k = aint[j];
                              addToList(listEntityAliases, k, i);
                           }
                        } else {
                           XH.warn("[Shaders] Invalid entity ID mapping: " + s + "=" + s1);
                        }
                     }
                  }
               }

               return;
            }
         } catch (IOException var15) {
            XH.warn("[Shaders] Error reading: " + path);
         }
      }

   }

   private static void addToList(List<Integer> list, int index, int val) {
      while(list.size() <= index) {
         list.add(-1);
      }

      list.set(index, val);
   }

   private static int[] toArray(List<Integer> list) {
      int[] aint = new int[list.size()];

      for(int i = 0; i < aint.length; ++i) {
         aint[i] = (Integer)list.get(i);
      }

      return aint;
   }

   public static void reset() {
      entityAliases = null;
   }
}
