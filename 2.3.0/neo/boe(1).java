package neo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import net.minecraft.util.ResourceLocation;

public class boe {
   private static bod[][] blockAliases = (bod[][])((bod[][])null);
   private static bqL blockLayerPropertes = null;
   private static boolean updateOnResourcesReloaded;

   public boe() {
   }

   public static int getBlockAliasId(int blockId, int metadata) {
      if (blockAliases == null) {
         return blockId;
      } else if (blockId >= 0 && blockId < blockAliases.length) {
         bod[] ablockalias = blockAliases[blockId];
         if (ablockalias == null) {
            return blockId;
         } else {
            for(int i = 0; i < ablockalias.length; ++i) {
               bod blockalias = ablockalias[i];
               if (blockalias.matches(blockId, metadata)) {
                  return blockalias.getBlockAliasId();
               }
            }

            return blockId;
         }
      } else {
         return blockId;
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
         if (bnK.Loader_getActiveModList.exists() && nC.getMinecraft().getResourcePackRepository() == null) {
            XH.dbg("[Shaders] Delayed loading of block mappings after resources are loaded");
            updateOnResourcesReloaded = true;
         } else {
            List<List<bod>> list = new ArrayList();
            String s = "/shaders/block.properties";
            InputStream inputstream = shaderPack.getResourceAsStream(s);
            if (inputstream != null) {
               loadBlockAliases(inputstream, s, list);
            }

            loadModBlockAliases(list);
            if (list.size() > 0) {
               blockAliases = toArrays(list);
            }
         }
      }

   }

   private static void loadModBlockAliases(List<List<bod>> listBlockAliases) {
      String[] astring = bnQ.getForgeModIds();

      for(int i = 0; i < astring.length; ++i) {
         String s = astring[i];

         try {
            ResourceLocation resourcelocation = new ResourceLocation(s, "shaders/block.properties");
            InputStream inputstream = XH.getResourceStream(resourcelocation);
            loadBlockAliases(inputstream, resourcelocation.toString(), listBlockAliases);
         } catch (IOException var6) {
         }
      }

   }

   private static void loadBlockAliases(InputStream in, String path, List<List<bod>> listBlockAliases) {
      if (in != null) {
         try {
            in = boj.process(in, path);
            Properties properties = new bqL();
            ((Properties)properties).load(in);
            in.close();
            XH.dbg("[Shaders] Parsing block mappings: " + path);
            biS connectedparser = new biS("Shaders");
            Iterator var5 = ((Properties)properties).keySet().iterator();

            while(true) {
               while(var5.hasNext()) {
                  Object ss = var5.next();
                  String s = (String)ss;
                  String s1 = ((Properties)properties).getProperty(s);
                  if (s.startsWith("layer.")) {
                     if (blockLayerPropertes == null) {
                        blockLayerPropertes = new bqL();
                     }

                     blockLayerPropertes.put(s, s1);
                  } else {
                     String s2 = "block.";
                     if (!s.startsWith(s2)) {
                        XH.warn("[Shaders] Invalid block ID: " + s);
                     } else {
                        String s3 = bqP.removePrefix(s, s2);
                        int i = XH.parseInt(s3, -1);
                        if (i < 0) {
                           XH.warn("[Shaders] Invalid block ID: " + s);
                        } else {
                           biZ[] amatchblock = connectedparser.parseMatchBlocks(s1);
                           if (amatchblock != null && amatchblock.length >= 1) {
                              bod blockalias = new bod(i, amatchblock);
                              addToList(listBlockAliases, blockalias);
                           } else {
                              XH.warn("[Shaders] Invalid block ID mapping: " + s + "=" + s1);
                           }
                        }
                     }
                  }
               }

               return;
            }
         } catch (IOException var14) {
            XH.warn("[Shaders] Error reading: " + path);
         }
      }

   }

   private static void addToList(List<List<bod>> blocksAliases, bod ba) {
      int[] aint = ba.getMatchBlockIds();

      for(int i = 0; i < aint.length; ++i) {
         int j = aint[i];

         while(j >= blocksAliases.size()) {
            blocksAliases.add((Object)null);
         }

         List<bod> list = (List)blocksAliases.get(j);
         if (list == null) {
            list = new ArrayList();
            blocksAliases.set(j, list);
         }

         bod blockalias = new bod(ba.getBlockAliasId(), ba.getMatchBlocks(j));
         ((List)list).add(blockalias);
      }

   }

   private static bod[][] toArrays(List<List<bod>> listBlocksAliases) {
      bod[][] ablockalias = new bod[listBlocksAliases.size()][];

      for(int i = 0; i < ablockalias.length; ++i) {
         List<bod> list = (List)listBlocksAliases.get(i);
         if (list != null) {
            ablockalias[i] = (bod[])((bod[])list.toArray(new bod[list.size()]));
         }
      }

      return ablockalias;
   }

   public static bqL getBlockLayerPropertes() {
      return blockLayerPropertes;
   }

   public static void reset() {
      blockAliases = (bod[][])((bod[][])null);
      blockLayerPropertes = null;
   }
}
