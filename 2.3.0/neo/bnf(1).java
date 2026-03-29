package neo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.util.EnumFacing;

public class bnf {
   public bnf() {
   }

   public static void dbgModel(sc model) {
      if (model != null) {
         XH.dbg("Model: " + model + ", ao: " + model.isAmbientOcclusion() + ", gui3d: " + model.isGui3d() + ", builtIn: " + model.isBuiltInRenderer() + ", particle: " + model.getParticleTexture());
         EnumFacing[] aenumfacing = EnumFacing.VALUES;

         for(int i = 0; i < aenumfacing.length; ++i) {
            EnumFacing enumfacing = aenumfacing[i];
            List list = model.getQuads((in)null, enumfacing, 0L);
            dbgQuads(enumfacing.getName(), list, "  ");
         }

         List list1 = model.getQuads((in)null, (EnumFacing)null, 0L);
         dbgQuads("General", list1, "  ");
      }

   }

   private static void dbgQuads(String name, List quads, String prefix) {
      Iterator var3 = quads.iterator();

      while(var3.hasNext()) {
         Object bakedquad = var3.next();
         dbgQuad(name, (rK)bakedquad, prefix);
      }

   }

   public static void dbgQuad(String name, rK quad, String prefix) {
      XH.dbg(prefix + "Quad: " + quad.getClass().getName() + ", type: " + name + ", face: " + quad.getFace() + ", tint: " + quad.getTintIndex() + ", sprite: " + quad.getSprite());
      dbgVertexData(quad.getVertexData(), "  " + prefix);
   }

   public static void dbgVertexData(int[] vd, String prefix) {
      int i = vd.length / 4;
      XH.dbg(prefix + "Length: " + vd.length + ", step: " + i);

      for(int j = 0; j < 4; ++j) {
         int k = j * i;
         float f = Float.intBitsToFloat(vd[k + 0]);
         float f1 = Float.intBitsToFloat(vd[k + 1]);
         float f2 = Float.intBitsToFloat(vd[k + 2]);
         int l = vd[k + 3];
         float f3 = Float.intBitsToFloat(vd[k + 4]);
         float f4 = Float.intBitsToFloat(vd[k + 5]);
         XH.dbg(prefix + j + " xyz: " + f + "," + f1 + "," + f2 + " col: " + l + " u,v: " + f3 + "," + f4);
      }

   }

   public static sc duplicateModel(sc model) {
      List list = duplicateQuadList(model.getQuads((in)null, (EnumFacing)null, 0L));
      EnumFacing[] aenumfacing = EnumFacing.VALUES;
      Map<EnumFacing, List<rK>> map = new HashMap();

      for(int i = 0; i < aenumfacing.length; ++i) {
         EnumFacing enumfacing = aenumfacing[i];
         List list1 = model.getQuads((in)null, enumfacing, 0L);
         List list2 = duplicateQuadList(list1);
         map.put(enumfacing, list2);
      }

      tc simplebakedmodel = new tc(list, map, model.isAmbientOcclusion(), model.isGui3d(), model.getParticleTexture(), model.getItemCameraTransforms(), model.getOverrides());
      return simplebakedmodel;
   }

   public static List duplicateQuadList(List list) {
      List<rK> list2 = new ArrayList();
      Iterator var2 = list.iterator();

      while(var2.hasNext()) {
         Object bakedquad = var2.next();
         rK bakedquad1 = duplicateQuad((rK)bakedquad);
         list2.add(bakedquad1);
      }

      return list2;
   }

   public static rK duplicateQuad(rK quad) {
      rK bakedquad = new rK((int[])((int[])quad.getVertexData().clone()), quad.getTintIndex(), quad.getFace(), quad.getSprite());
      return bakedquad;
   }
}
