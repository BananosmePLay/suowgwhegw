package neo;

import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;

public class bel {
   private static final beg[] PRIMARY_COMPONENTS = new beg[]{new beg(bej.class, 30, 0, true), new beg(beb.class, 10, 4), new beg(bdZ.class, 10, 4), new beg(beh.class, 10, 3), new beg(bek.class, 5, 2), new beg(bed.class, 5, 1)};
   private static final beg[] SECONDARY_COMPONENTS = new beg[]{new beg(bdY.class, 25, 0, true), new beg(bea.class, 15, 5), new beg(bdV.class, 5, 10), new beg(bdU.class, 5, 10), new beg(bdW.class, 10, 3, true), new beg(bdX.class, 7, 2), new beg(bee.class, 5, 2)};

   public bel() {
   }

   public static void registerNetherFortressPieces() {
      bdt.registerStructureComponent(beb.class, "NeBCr");
      bdt.registerStructureComponent(bec.class, "NeBEF");
      bdt.registerStructureComponent(bej.class, "NeBS");
      bdt.registerStructureComponent(bdW.class, "NeCCS");
      bdt.registerStructureComponent(bdX.class, "NeCTB");
      bdt.registerStructureComponent(bed.class, "NeCE");
      bdt.registerStructureComponent(bea.class, "NeSCSC");
      bdt.registerStructureComponent(bdU.class, "NeSCLT");
      bdt.registerStructureComponent(bdY.class, "NeSC");
      bdt.registerStructureComponent(bdV.class, "NeSCRT");
      bdt.registerStructureComponent(bee.class, "NeCSR");
      bdt.registerStructureComponent(bek.class, "NeMT");
      bdt.registerStructureComponent(bdZ.class, "NeRC");
      bdt.registerStructureComponent(beh.class, "NeSR");
      bdt.registerStructureComponent(bei.class, "NeStart");
   }

   private static bef findAndCreateBridgePieceFactory(beg p_175887_0_, List<bdB> p_175887_1_, Random p_175887_2_, int p_175887_3_, int p_175887_4_, int p_175887_5_, EnumFacing p_175887_6_, int p_175887_7_) {
      Class<? extends bef> oclass = p_175887_0_.weightClass;
      bef structurenetherbridgepieces$piece = null;
      if (oclass == bej.class) {
         structurenetherbridgepieces$piece = bej.createPiece(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
      } else if (oclass == beb.class) {
         structurenetherbridgepieces$piece = beb.createPiece(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
      } else if (oclass == bdZ.class) {
         structurenetherbridgepieces$piece = bdZ.createPiece(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
      } else if (oclass == beh.class) {
         structurenetherbridgepieces$piece = beh.createPiece(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_7_, p_175887_6_);
      } else if (oclass == bek.class) {
         structurenetherbridgepieces$piece = bek.createPiece(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_7_, p_175887_6_);
      } else if (oclass == bed.class) {
         structurenetherbridgepieces$piece = bed.createPiece(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
      } else if (oclass == bdY.class) {
         structurenetherbridgepieces$piece = bdY.createPiece(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
      } else if (oclass == bdV.class) {
         structurenetherbridgepieces$piece = bdV.createPiece(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
      } else if (oclass == bdU.class) {
         structurenetherbridgepieces$piece = bdU.createPiece(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
      } else if (oclass == bdW.class) {
         structurenetherbridgepieces$piece = bdW.createPiece(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
      } else if (oclass == bdX.class) {
         structurenetherbridgepieces$piece = bdX.createPiece(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
      } else if (oclass == bea.class) {
         structurenetherbridgepieces$piece = bea.createPiece(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
      } else if (oclass == bee.class) {
         structurenetherbridgepieces$piece = bee.createPiece(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
      }

      return (bef)structurenetherbridgepieces$piece;
   }

   // $FF: synthetic method
   static bef access$000(beg x0, List x1, Random x2, int x3, int x4, int x5, EnumFacing x6, int x7) {
      return findAndCreateBridgePieceFactory(x0, x1, x2, x3, x4, x5, x6, x7);
   }

   // $FF: synthetic method
   static beg[] access$100() {
      return PRIMARY_COMPONENTS;
   }

   // $FF: synthetic method
   static beg[] access$200() {
      return SECONDARY_COMPONENTS;
   }
}
