package neo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import net.minecraft.util.ResourceLocation;

public class bpb {
   private static int[] itemAliases = null;
   private static boolean updateOnResourcesReloaded;
   private static final int NO_ALIAS = Integer.MIN_VALUE;

   public bpb() {
   }

   public static int getItemAliasId(int itemId) {
      if (itemAliases == null) {
         return itemId;
      } else if (itemId >= 0 && itemId < itemAliases.length) {
         int i = itemAliases[itemId];
         return i == Integer.MIN_VALUE ? itemId : i;
      } else {
         return itemId;
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
            XH.dbg("[Shaders] Delayed loading of item mappings after resources are loaded");
            updateOnResourcesReloaded = true;
         } else {
            List<Integer> list = new ArrayList();
            String s = "/shaders/item.properties";
            InputStream inputstream = shaderPack.getResourceAsStream(s);
            if (inputstream != null) {
               loadItemAliases(inputstream, s, list);
            }

            loadModItemAliases(list);
            if (list.size() > 0) {
               itemAliases = toArray(list);
            }
         }
      }

   }

   private static void loadModItemAliases(List<Integer> listItemAliases) {
      String[] astring = bnQ.getForgeModIds();

      for(int i = 0; i < astring.length; ++i) {
         String s = astring[i];

         try {
            ResourceLocation resourcelocation = new ResourceLocation(s, "shaders/item.properties");
            InputStream inputstream = XH.getResourceStream(resourcelocation);
            loadItemAliases(inputstream, resourcelocation.toString(), listItemAliases);
         } catch (IOException var6) {
         }
      }

   }

   private static void loadItemAliases(InputStream in, String path, List<Integer> listItemAliases) {
      if (in != null) {
         try {
            in = boj.process(in, path);
            Properties properties = new bqL();
            ((Properties)properties).load(in);
            in.close();
            XH.dbg("[Shaders] Parsing item mappings: " + path);
            biS connectedparser = new biS("Shaders");
            Iterator var5 = ((Properties)properties).keySet().iterator();

            while(true) {
               while(var5.hasNext()) {
                  Object ss = var5.next();
                  String s = (String)ss;
                  String s1 = ((Properties)properties).getProperty(s);
                  String s2 = "item.";
                  if (!s.startsWith(s2)) {
                     XH.warn("[Shaders] Invalid item ID: " + s);
                  } else {
                     String s3 = bqP.removePrefix(s, s2);
                     int i = XH.parseInt(s3, -1);
                     if (i < 0) {
                        XH.warn("[Shaders] Invalid item alias ID: " + i);
                     } else {
                        int[] aint = connectedparser.parseItems(s1);
                        if (aint != null && aint.length >= 1) {
                           for(int j = 0; j < aint.length; ++j) {
                              int k = aint[j];
                              addToList(listItemAliases, k, i);
                           }
                        } else {
                           XH.warn("[Shaders] Invalid item ID mapping: " + s + "=" + s1);
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
         list.add(Integer.MIN_VALUE);
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
      itemAliases = null;
   }
}
