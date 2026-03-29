package neo;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import net.minecraft.util.BlockRenderLayer;

public class bjp {
   private static BlockRenderLayer[] renderLayers = null;
   public static boolean active = false;

   public bjp() {
   }

   public static BlockRenderLayer getRenderLayer(in blockState) {
      if (renderLayers == null) {
         return null;
      } else if (blockState.isOpaqueCube()) {
         return null;
      } else if (!(blockState instanceof ie)) {
         return null;
      } else {
         ie blockstatebase = (ie)blockState;
         int i = blockstatebase.getBlockId();
         return i > 0 && i < renderLayers.length ? renderLayers[i] : null;
      }
   }

   public static void update() {
      renderLayers = null;
      active = false;
      List<BlockRenderLayer> list = new ArrayList();
      String s = "optifine/block.properties";
      Properties properties = bqN.readProperties(s, "CustomBlockLayers");
      if (properties != null) {
         readLayers(s, properties, list);
      }

      if (XH.isShaders()) {
         bqL propertiesordered = boe.getBlockLayerPropertes();
         if (propertiesordered != null) {
            String s1 = "shaders/block.properties";
            readLayers(s1, propertiesordered, list);
         }
      }

      if (!list.isEmpty()) {
         renderLayers = (BlockRenderLayer[])((BlockRenderLayer[])list.toArray(new BlockRenderLayer[list.size()]));
         active = true;
      }

   }

   private static void readLayers(String pathProps, Properties props, List<BlockRenderLayer> list) {
      XH.dbg("CustomBlockLayers: " + pathProps);
      readLayer("solid", BlockRenderLayer.SOLID, props, list);
      readLayer("cutout", BlockRenderLayer.CUTOUT, props, list);
      readLayer("cutout_mipped", BlockRenderLayer.CUTOUT_MIPPED, props, list);
      readLayer("translucent", BlockRenderLayer.TRANSLUCENT, props, list);
   }

   private static void readLayer(String name, BlockRenderLayer layer, Properties props, List<BlockRenderLayer> listLayers) {
      String s = "layer." + name;
      String s1 = props.getProperty(s);
      if (s1 != null) {
         biS connectedparser = new biS("CustomBlockLayers");
         biZ[] amatchblock = connectedparser.parseMatchBlocks(s1);
         if (amatchblock != null) {
            for(int i = 0; i < amatchblock.length; ++i) {
               biZ matchblock = amatchblock[i];
               int j = matchblock.getBlockId();
               if (j > 0) {
                  while(listLayers.size() < j + 1) {
                     listLayers.add((Object)null);
                  }

                  if (listLayers.get(j) != null) {
                     XH.warn("CustomBlockLayers: Block layer is already set, block: " + j + ", layer: " + name);
                  }

                  listLayers.set(j, layer);
               }
            }
         }
      }

   }

   public static boolean isActive() {
      return active;
   }
}
