package neo;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;

public class bfh {
   private static final beV[] PIECE_WEIGHTS = new beV[]{new beV(bfe.class, 40, 0), new beV(beX.class, 5, 5), new beV(beT.class, 20, 0), new beV(beY.class, 20, 0), new beV(beZ.class, 10, 6), new beV(bfc.class, 5, 5), new beV(bfa.class, 5, 5), new beV(beS.class, 5, 4), new beV(beQ.class, 5, 4), new beV(beU.class, 10, 2) {
      public boolean canSpawnMoreStructuresOfType(int p_75189_1_) {
         return super.canSpawnMoreStructuresOfType(p_75189_1_) && p_75189_1_ > 4;
      }
   }, new beV(beW.class, 20, 1) {
      public boolean canSpawnMoreStructuresOfType(int p_75189_1_) {
         return super.canSpawnMoreStructuresOfType(p_75189_1_) && p_75189_1_ > 5;
      }
   }};
   private static List<beV> structurePieceList;
   private static Class<? extends bfg> strongComponentType;
   static int totalWeight;
   private static final bfd STRONGHOLD_STONES = new bfd();

   public bfh() {
   }

   public static void registerStrongholdPieces() {
      bdt.registerStructureComponent(beQ.class, "SHCC");
      bdt.registerStructureComponent(beR.class, "SHFC");
      bdt.registerStructureComponent(beS.class, "SH5C");
      bdt.registerStructureComponent(beT.class, "SHLT");
      bdt.registerStructureComponent(beU.class, "SHLi");
      bdt.registerStructureComponent(beW.class, "SHPR");
      bdt.registerStructureComponent(beX.class, "SHPH");
      bdt.registerStructureComponent(beY.class, "SHRT");
      bdt.registerStructureComponent(beZ.class, "SHRC");
      bdt.registerStructureComponent(bfa.class, "SHSD");
      bdt.registerStructureComponent(bfb.class, "SHStart");
      bdt.registerStructureComponent(bfe.class, "SHS");
      bdt.registerStructureComponent(bfc.class, "SHSSD");
   }

   public static void prepareStructurePieces() {
      structurePieceList = Lists.newArrayList();
      beV[] var0 = PIECE_WEIGHTS;
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         beV structurestrongholdpieces$pieceweight = var0[var2];
         structurestrongholdpieces$pieceweight.instancesSpawned = 0;
         structurePieceList.add(structurestrongholdpieces$pieceweight);
      }

      strongComponentType = null;
   }

   private static boolean canAddStructurePieces() {
      boolean flag = false;
      totalWeight = 0;

      beV structurestrongholdpieces$pieceweight;
      for(Iterator var1 = structurePieceList.iterator(); var1.hasNext(); totalWeight += structurestrongholdpieces$pieceweight.pieceWeight) {
         structurestrongholdpieces$pieceweight = (beV)var1.next();
         if (structurestrongholdpieces$pieceweight.instancesLimit > 0 && structurestrongholdpieces$pieceweight.instancesSpawned < structurestrongholdpieces$pieceweight.instancesLimit) {
            flag = true;
         }
      }

      return flag;
   }

   private static bfg findAndCreatePieceFactory(Class<? extends bfg> clazz, List<bdB> p_175954_1_, Random p_175954_2_, int p_175954_3_, int p_175954_4_, int p_175954_5_, @Nullable EnumFacing p_175954_6_, int p_175954_7_) {
      bfg structurestrongholdpieces$stronghold = null;
      if (clazz == bfe.class) {
         structurestrongholdpieces$stronghold = bfe.createPiece(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
      } else if (clazz == beX.class) {
         structurestrongholdpieces$stronghold = beX.createPiece(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
      } else if (clazz == beT.class) {
         structurestrongholdpieces$stronghold = beT.createPiece(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
      } else if (clazz == beY.class) {
         structurestrongholdpieces$stronghold = beY.createPiece(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
      } else if (clazz == beZ.class) {
         structurestrongholdpieces$stronghold = beZ.createPiece(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
      } else if (clazz == bfc.class) {
         structurestrongholdpieces$stronghold = bfc.createPiece(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
      } else if (clazz == bfa.class) {
         structurestrongholdpieces$stronghold = bfa.createPiece(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
      } else if (clazz == beS.class) {
         structurestrongholdpieces$stronghold = beS.createPiece(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
      } else if (clazz == beQ.class) {
         structurestrongholdpieces$stronghold = beQ.createPiece(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
      } else if (clazz == beU.class) {
         structurestrongholdpieces$stronghold = beU.createPiece(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
      } else if (clazz == beW.class) {
         structurestrongholdpieces$stronghold = beW.createPiece(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
      }

      return (bfg)structurestrongholdpieces$stronghold;
   }

   private static bfg generatePieceFromSmallDoor(bfb p_175955_0_, List<bdB> p_175955_1_, Random p_175955_2_, int p_175955_3_, int p_175955_4_, int p_175955_5_, EnumFacing p_175955_6_, int p_175955_7_) {
      if (!canAddStructurePieces()) {
         return null;
      } else {
         if (strongComponentType != null) {
            bfg structurestrongholdpieces$stronghold = findAndCreatePieceFactory(strongComponentType, p_175955_1_, p_175955_2_, p_175955_3_, p_175955_4_, p_175955_5_, p_175955_6_, p_175955_7_);
            strongComponentType = null;
            if (structurestrongholdpieces$stronghold != null) {
               return structurestrongholdpieces$stronghold;
            }
         }

         int j = 0;

         while(j < 5) {
            ++j;
            int i = p_175955_2_.nextInt(totalWeight);
            Iterator var10 = structurePieceList.iterator();

            while(var10.hasNext()) {
               beV structurestrongholdpieces$pieceweight = (beV)var10.next();
               i -= structurestrongholdpieces$pieceweight.pieceWeight;
               if (i < 0) {
                  if (!structurestrongholdpieces$pieceweight.canSpawnMoreStructuresOfType(p_175955_7_) || structurestrongholdpieces$pieceweight == p_175955_0_.lastPlaced) {
                     break;
                  }

                  bfg structurestrongholdpieces$stronghold1 = findAndCreatePieceFactory(structurestrongholdpieces$pieceweight.pieceClass, p_175955_1_, p_175955_2_, p_175955_3_, p_175955_4_, p_175955_5_, p_175955_6_, p_175955_7_);
                  if (structurestrongholdpieces$stronghold1 != null) {
                     ++structurestrongholdpieces$pieceweight.instancesSpawned;
                     p_175955_0_.lastPlaced = structurestrongholdpieces$pieceweight;
                     if (!structurestrongholdpieces$pieceweight.canSpawnMoreStructures()) {
                        structurePieceList.remove(structurestrongholdpieces$pieceweight);
                     }

                     return structurestrongholdpieces$stronghold1;
                  }
               }
            }
         }

         bdy structureboundingbox = beR.findPieceBox(p_175955_1_, p_175955_2_, p_175955_3_, p_175955_4_, p_175955_5_, p_175955_6_);
         if (structureboundingbox != null && structureboundingbox.minY > 1) {
            return new beR(p_175955_7_, p_175955_2_, structureboundingbox, p_175955_6_);
         } else {
            return null;
         }
      }
   }

   private static bdB generateAndAddPiece(bfb p_175953_0_, List<bdB> p_175953_1_, Random p_175953_2_, int p_175953_3_, int p_175953_4_, int p_175953_5_, @Nullable EnumFacing p_175953_6_, int p_175953_7_) {
      if (p_175953_7_ > 50) {
         return null;
      } else if (Math.abs(p_175953_3_ - p_175953_0_.getBoundingBox().minX) <= 112 && Math.abs(p_175953_5_ - p_175953_0_.getBoundingBox().minZ) <= 112) {
         bdB structurecomponent = generatePieceFromSmallDoor(p_175953_0_, p_175953_1_, p_175953_2_, p_175953_3_, p_175953_4_, p_175953_5_, p_175953_6_, p_175953_7_ + 1);
         if (structurecomponent != null) {
            p_175953_1_.add(structurecomponent);
            p_175953_0_.pendingChildren.add(structurecomponent);
         }

         return structurecomponent;
      } else {
         return null;
      }
   }

   // $FF: synthetic method
   static bfd access$100() {
      return STRONGHOLD_STONES;
   }

   // $FF: synthetic method
   static Class access$202(Class x0) {
      strongComponentType = x0;
      return x0;
   }

   // $FF: synthetic method
   static bdB access$300(bfb x0, List x1, Random x2, int x3, int x4, int x5, EnumFacing x6, int x7) {
      return generateAndAddPiece(x0, x1, x2, x3, x4, x5, x6, x7);
   }
}
