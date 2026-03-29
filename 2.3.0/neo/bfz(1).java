package neo;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;

public class bfz {
   public bfz() {
   }

   public static void registerVillagePieces() {
      bdt.registerStructureComponent(bfn.class, "ViBH");
      bdt.registerStructureComponent(bfk.class, "ViDF");
      bdt.registerStructureComponent(bfl.class, "ViF");
      bdt.registerStructureComponent(bfv.class, "ViL");
      bdt.registerStructureComponent(bfm.class, "ViPH");
      bdt.registerStructureComponent(bfq.class, "ViSH");
      bdt.registerStructureComponent(bfy.class, "ViSmH");
      bdt.registerStructureComponent(bfj.class, "ViST");
      bdt.registerStructureComponent(bfo.class, "ViS");
      bdt.registerStructureComponent(bfu.class, "ViStart");
      bdt.registerStructureComponent(bfr.class, "ViSR");
      bdt.registerStructureComponent(bfp.class, "ViTRH");
      bdt.registerStructureComponent(bfx.class, "ViW");
   }

   public static List<bfs> getStructureVillageWeightedPieceList(Random random, int size) {
      List<bfs> list = Lists.newArrayList();
      list.add(new bfs(bfq.class, 4, MathHelper.getInt(random, 2 + size, 4 + size * 2)));
      list.add(new bfs(bfj.class, 20, MathHelper.getInt(random, 0 + size, 1 + size)));
      list.add(new bfs(bfn.class, 20, MathHelper.getInt(random, 0 + size, 2 + size)));
      list.add(new bfs(bfy.class, 3, MathHelper.getInt(random, 2 + size, 5 + size * 3)));
      list.add(new bfs(bfm.class, 15, MathHelper.getInt(random, 0 + size, 2 + size)));
      list.add(new bfs(bfk.class, 3, MathHelper.getInt(random, 1 + size, 4 + size)));
      list.add(new bfs(bfl.class, 3, MathHelper.getInt(random, 2 + size, 4 + size * 2)));
      list.add(new bfs(bfo.class, 15, MathHelper.getInt((Random)random, 0, 1 + size)));
      list.add(new bfs(bfp.class, 8, MathHelper.getInt(random, 0 + size, 3 + size * 2)));
      Iterator<bfs> iterator = list.iterator();

      while(iterator.hasNext()) {
         if (((bfs)iterator.next()).villagePiecesLimit == 0) {
            iterator.remove();
         }
      }

      return list;
   }

   private static int updatePieceWeight(List<bfs> p_75079_0_) {
      boolean flag = false;
      int i = 0;

      bfs structurevillagepieces$pieceweight;
      for(Iterator var3 = p_75079_0_.iterator(); var3.hasNext(); i += structurevillagepieces$pieceweight.villagePieceWeight) {
         structurevillagepieces$pieceweight = (bfs)var3.next();
         if (structurevillagepieces$pieceweight.villagePiecesLimit > 0 && structurevillagepieces$pieceweight.villagePiecesSpawned < structurevillagepieces$pieceweight.villagePiecesLimit) {
            flag = true;
         }
      }

      return flag ? i : -1;
   }

   private static bfw findAndCreateComponentFactory(bfu start, bfs weight, List<bdB> structureComponents, Random rand, int structureMinX, int structureMinY, int structureMinZ, EnumFacing facing, int componentType) {
      Class<? extends bfw> oclass = weight.villagePieceClass;
      bfw structurevillagepieces$village = null;
      if (oclass == bfq.class) {
         structurevillagepieces$village = bfq.createPiece(start, structureComponents, rand, structureMinX, structureMinY, structureMinZ, facing, componentType);
      } else if (oclass == bfj.class) {
         structurevillagepieces$village = bfj.createPiece(start, structureComponents, rand, structureMinX, structureMinY, structureMinZ, facing, componentType);
      } else if (oclass == bfn.class) {
         structurevillagepieces$village = bfn.createPiece(start, structureComponents, rand, structureMinX, structureMinY, structureMinZ, facing, componentType);
      } else if (oclass == bfy.class) {
         structurevillagepieces$village = bfy.createPiece(start, structureComponents, rand, structureMinX, structureMinY, structureMinZ, facing, componentType);
      } else if (oclass == bfm.class) {
         structurevillagepieces$village = bfm.createPiece(start, structureComponents, rand, structureMinX, structureMinY, structureMinZ, facing, componentType);
      } else if (oclass == bfk.class) {
         structurevillagepieces$village = bfk.createPiece(start, structureComponents, rand, structureMinX, structureMinY, structureMinZ, facing, componentType);
      } else if (oclass == bfl.class) {
         structurevillagepieces$village = bfl.createPiece(start, structureComponents, rand, structureMinX, structureMinY, structureMinZ, facing, componentType);
      } else if (oclass == bfo.class) {
         structurevillagepieces$village = bfo.createPiece(start, structureComponents, rand, structureMinX, structureMinY, structureMinZ, facing, componentType);
      } else if (oclass == bfp.class) {
         structurevillagepieces$village = bfp.createPiece(start, structureComponents, rand, structureMinX, structureMinY, structureMinZ, facing, componentType);
      }

      return (bfw)structurevillagepieces$village;
   }

   private static bfw generateComponent(bfu start, List<bdB> structureComponents, Random rand, int structureMinX, int structureMinY, int structureMinZ, EnumFacing facing, int componentType) {
      int i = updatePieceWeight(start.structureVillageWeightedPieceList);
      if (i <= 0) {
         return null;
      } else {
         int j = 0;

         while(j < 5) {
            ++j;
            int k = rand.nextInt(i);
            Iterator var11 = start.structureVillageWeightedPieceList.iterator();

            while(var11.hasNext()) {
               bfs structurevillagepieces$pieceweight = (bfs)var11.next();
               k -= structurevillagepieces$pieceweight.villagePieceWeight;
               if (k < 0) {
                  if (!structurevillagepieces$pieceweight.canSpawnMoreVillagePiecesOfType(componentType) || structurevillagepieces$pieceweight == start.lastPlaced && start.structureVillageWeightedPieceList.size() > 1) {
                     break;
                  }

                  bfw structurevillagepieces$village = findAndCreateComponentFactory(start, structurevillagepieces$pieceweight, structureComponents, rand, structureMinX, structureMinY, structureMinZ, facing, componentType);
                  if (structurevillagepieces$village != null) {
                     ++structurevillagepieces$pieceweight.villagePiecesSpawned;
                     start.lastPlaced = structurevillagepieces$pieceweight;
                     if (!structurevillagepieces$pieceweight.canSpawnMoreVillagePieces()) {
                        start.structureVillageWeightedPieceList.remove(structurevillagepieces$pieceweight);
                     }

                     return structurevillagepieces$village;
                  }
               }
            }
         }

         bdy structureboundingbox = bfv.findPieceBox(start, structureComponents, rand, structureMinX, structureMinY, structureMinZ, facing);
         if (structureboundingbox != null) {
            return new bfv(start, componentType, rand, structureboundingbox, facing);
         } else {
            return null;
         }
      }
   }

   private static bdB generateAndAddComponent(bfu start, List<bdB> structureComponents, Random rand, int structureMinX, int structureMinY, int structureMinZ, EnumFacing facing, int componentType) {
      if (componentType > 50) {
         return null;
      } else if (Math.abs(structureMinX - start.getBoundingBox().minX) <= 112 && Math.abs(structureMinZ - start.getBoundingBox().minZ) <= 112) {
         bdB structurecomponent = generateComponent(start, structureComponents, rand, structureMinX, structureMinY, structureMinZ, facing, componentType + 1);
         if (structurecomponent != null) {
            structureComponents.add(structurecomponent);
            start.pendingHouses.add(structurecomponent);
            return structurecomponent;
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   private static bdB generateAndAddRoadPiece(bfu start, List<bdB> p_176069_1_, Random rand, int p_176069_3_, int p_176069_4_, int p_176069_5_, EnumFacing facing, int p_176069_7_) {
      if (p_176069_7_ > 3 + start.terrainType) {
         return null;
      } else if (Math.abs(p_176069_3_ - start.getBoundingBox().minX) <= 112 && Math.abs(p_176069_5_ - start.getBoundingBox().minZ) <= 112) {
         bdy structureboundingbox = bfr.findPieceBox(start, p_176069_1_, rand, p_176069_3_, p_176069_4_, p_176069_5_, facing);
         if (structureboundingbox != null && structureboundingbox.minY > 10) {
            bdB structurecomponent = new bfr(start, p_176069_7_, rand, structureboundingbox, facing);
            p_176069_1_.add(structurecomponent);
            start.pendingRoads.add(structurecomponent);
            return structurecomponent;
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   // $FF: synthetic method
   static bdB access$000(bfu x0, List x1, Random x2, int x3, int x4, int x5, EnumFacing x6, int x7) {
      return generateAndAddRoadPiece(x0, x1, x2, x3, x4, x5, x6, x7);
   }

   // $FF: synthetic method
   static bdB access$100(bfu x0, List x1, Random x2, int x3, int x4, int x5, EnumFacing x6, int x7) {
      return generateAndAddComponent(x0, x1, x2, x3, x4, x5, x6, x7);
   }
}
