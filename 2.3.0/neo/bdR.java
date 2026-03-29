package neo;

import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;

public class bdR {
   public bdR() {
   }

   public static void registerStructurePieces() {
      bdt.registerStructureComponent(bdM.class, "MSCorridor");
      bdt.registerStructureComponent(bdN.class, "MSCrossing");
      bdt.registerStructureComponent(bdP.class, "MSRoom");
      bdt.registerStructureComponent(bdQ.class, "MSStairs");
   }

   private static bdO createRandomShaftPiece(List<bdB> p_189940_0_, Random p_189940_1_, int p_189940_2_, int p_189940_3_, int p_189940_4_, @Nullable EnumFacing p_189940_5_, int p_189940_6_, bdg p_189940_7_) {
      int i = p_189940_1_.nextInt(100);
      bdy structureboundingbox1;
      if (i >= 80) {
         structureboundingbox1 = bdN.findCrossing(p_189940_0_, p_189940_1_, p_189940_2_, p_189940_3_, p_189940_4_, p_189940_5_);
         if (structureboundingbox1 != null) {
            return new bdN(p_189940_6_, p_189940_1_, structureboundingbox1, p_189940_5_, p_189940_7_);
         }
      } else if (i >= 70) {
         structureboundingbox1 = bdQ.findStairs(p_189940_0_, p_189940_1_, p_189940_2_, p_189940_3_, p_189940_4_, p_189940_5_);
         if (structureboundingbox1 != null) {
            return new bdQ(p_189940_6_, p_189940_1_, structureboundingbox1, p_189940_5_, p_189940_7_);
         }
      } else {
         structureboundingbox1 = bdM.findCorridorSize(p_189940_0_, p_189940_1_, p_189940_2_, p_189940_3_, p_189940_4_, p_189940_5_);
         if (structureboundingbox1 != null) {
            return new bdM(p_189940_6_, p_189940_1_, structureboundingbox1, p_189940_5_, p_189940_7_);
         }
      }

      return null;
   }

   private static bdO generateAndAddPiece(bdB p_189938_0_, List<bdB> p_189938_1_, Random p_189938_2_, int p_189938_3_, int p_189938_4_, int p_189938_5_, EnumFacing p_189938_6_, int p_189938_7_) {
      if (p_189938_7_ > 8) {
         return null;
      } else if (Math.abs(p_189938_3_ - p_189938_0_.getBoundingBox().minX) <= 80 && Math.abs(p_189938_5_ - p_189938_0_.getBoundingBox().minZ) <= 80) {
         bdg mapgenmineshaft$type = ((bdO)p_189938_0_).mineShaftType;
         bdO structuremineshaftpieces$peice = createRandomShaftPiece(p_189938_1_, p_189938_2_, p_189938_3_, p_189938_4_, p_189938_5_, p_189938_6_, p_189938_7_ + 1, mapgenmineshaft$type);
         if (structuremineshaftpieces$peice != null) {
            p_189938_1_.add(structuremineshaftpieces$peice);
            structuremineshaftpieces$peice.buildComponent(p_189938_0_, p_189938_1_, p_189938_2_);
         }

         return structuremineshaftpieces$peice;
      } else {
         return null;
      }
   }

   // $FF: synthetic method
   static bdO access$000(bdB x0, List x1, Random x2, int x3, int x4, int x5, EnumFacing x6, int x7) {
      return generateAndAddPiece(x0, x1, x2, x3, x4, x5, x6, x7);
   }
}
