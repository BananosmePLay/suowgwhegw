package neo;

public class bja {
   public bja() {
   }

   public static boolean block(ie blockStateBase, biZ[] matchBlocks) {
      if (matchBlocks == null) {
         return true;
      } else {
         for(int i = 0; i < matchBlocks.length; ++i) {
            biZ matchblock = matchBlocks[i];
            if (matchblock.matches(blockStateBase)) {
               return true;
            }
         }

         return false;
      }
   }

   public static boolean block(int blockId, int metadata, biZ[] matchBlocks) {
      if (matchBlocks == null) {
         return true;
      } else {
         for(int i = 0; i < matchBlocks.length; ++i) {
            biZ matchblock = matchBlocks[i];
            if (matchblock.matches(blockId, metadata)) {
               return true;
            }
         }

         return false;
      }
   }

   public static boolean blockId(int blockId, biZ[] matchBlocks) {
      if (matchBlocks == null) {
         return true;
      } else {
         for(int i = 0; i < matchBlocks.length; ++i) {
            biZ matchblock = matchBlocks[i];
            if (matchblock.getBlockId() == blockId) {
               return true;
            }
         }

         return false;
      }
   }

   public static boolean metadata(int metadata, int[] metadatas) {
      if (metadatas == null) {
         return true;
      } else {
         for(int i = 0; i < metadatas.length; ++i) {
            if (metadatas[i] == metadata) {
               return true;
            }
         }

         return false;
      }
   }

   public static boolean sprite(zd sprite, zd[] sprites) {
      if (sprites == null) {
         return true;
      } else {
         for(int i = 0; i < sprites.length; ++i) {
            if (sprites[i] == sprite) {
               return true;
            }
         }

         return false;
      }
   }

   public static boolean biome(Zi biome, Zi[] biomes) {
      if (biomes == null) {
         return true;
      } else {
         for(int i = 0; i < biomes.length; ++i) {
            if (biomes[i] == biome) {
               return true;
            }
         }

         return false;
      }
   }
}
