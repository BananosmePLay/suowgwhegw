package neo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class bpX {
   private static sc modelLeavesCullAcacia = null;
   private static sc modelLeavesCullBirch = null;
   private static sc modelLeavesCullDarkOak = null;
   private static sc modelLeavesCullJungle = null;
   private static sc modelLeavesCullOak = null;
   private static sc modelLeavesCullSpruce = null;
   private static List generalQuadsCullAcacia = null;
   private static List generalQuadsCullBirch = null;
   private static List generalQuadsCullDarkOak = null;
   private static List generalQuadsCullJungle = null;
   private static List generalQuadsCullOak = null;
   private static List generalQuadsCullSpruce = null;
   private static sc modelLeavesDoubleAcacia = null;
   private static sc modelLeavesDoubleBirch = null;
   private static sc modelLeavesDoubleDarkOak = null;
   private static sc modelLeavesDoubleJungle = null;
   private static sc modelLeavesDoubleOak = null;
   private static sc modelLeavesDoubleSpruce = null;

   public bpX() {
   }

   public static sc getLeavesModel(sc model, in stateIn) {
      if (!XH.isTreesSmart()) {
         return model;
      } else {
         List list = model.getQuads(stateIn, (EnumFacing)null, 0L);
         if (list == generalQuadsCullAcacia) {
            return modelLeavesDoubleAcacia;
         } else if (list == generalQuadsCullBirch) {
            return modelLeavesDoubleBirch;
         } else if (list == generalQuadsCullDarkOak) {
            return modelLeavesDoubleDarkOak;
         } else if (list == generalQuadsCullJungle) {
            return modelLeavesDoubleJungle;
         } else if (list == generalQuadsCullOak) {
            return modelLeavesDoubleOak;
         } else {
            return list == generalQuadsCullSpruce ? modelLeavesDoubleSpruce : model;
         }
      }
   }

   public static boolean isSameLeaves(in state1, in state2) {
      if (state1 == state2) {
         return true;
      } else {
         co block = state1.getBlock();
         co block1 = state2.getBlock();
         if (block != block1) {
            return false;
         } else if (block instanceof eW) {
            return ((fk)state1.getValue(eW.VARIANT)).equals(state2.getValue(eW.VARIANT));
         } else {
            return block instanceof eO ? ((fk)state1.getValue(eO.VARIANT)).equals(state2.getValue(eO.VARIANT)) : false;
         }
      }
   }

   public static void updateLeavesModels() {
      List list = new ArrayList();
      modelLeavesCullAcacia = getModelCull("acacia", list);
      modelLeavesCullBirch = getModelCull("birch", list);
      modelLeavesCullDarkOak = getModelCull("dark_oak", list);
      modelLeavesCullJungle = getModelCull("jungle", list);
      modelLeavesCullOak = getModelCull("oak", list);
      modelLeavesCullSpruce = getModelCull("spruce", list);
      generalQuadsCullAcacia = getGeneralQuadsSafe(modelLeavesCullAcacia);
      generalQuadsCullBirch = getGeneralQuadsSafe(modelLeavesCullBirch);
      generalQuadsCullDarkOak = getGeneralQuadsSafe(modelLeavesCullDarkOak);
      generalQuadsCullJungle = getGeneralQuadsSafe(modelLeavesCullJungle);
      generalQuadsCullOak = getGeneralQuadsSafe(modelLeavesCullOak);
      generalQuadsCullSpruce = getGeneralQuadsSafe(modelLeavesCullSpruce);
      modelLeavesDoubleAcacia = getModelDoubleFace(modelLeavesCullAcacia);
      modelLeavesDoubleBirch = getModelDoubleFace(modelLeavesCullBirch);
      modelLeavesDoubleDarkOak = getModelDoubleFace(modelLeavesCullDarkOak);
      modelLeavesDoubleJungle = getModelDoubleFace(modelLeavesCullJungle);
      modelLeavesDoubleOak = getModelDoubleFace(modelLeavesCullOak);
      modelLeavesDoubleSpruce = getModelDoubleFace(modelLeavesCullSpruce);
      if (list.size() > 0) {
         XH.dbg("Enable face culling: " + XH.arrayToString(list.toArray()));
      }

   }

   private static List getGeneralQuadsSafe(sc model) {
      return model == null ? null : model.getQuads((in)null, (EnumFacing)null, 0L);
   }

   static sc getModelCull(String type, List updatedTypes) {
      sC modelmanager = XH.getModelManager();
      if (modelmanager == null) {
         return null;
      } else {
         ResourceLocation resourcelocation = new ResourceLocation("blockstates/" + type + "_leaves.json");
         if (XH.getDefiningResourcePack(resourcelocation) != XH.getDefaultResourcePack()) {
            return null;
         } else {
            ResourceLocation resourcelocation1 = new ResourceLocation("models/block/" + type + "_leaves.json");
            if (XH.getDefiningResourcePack(resourcelocation1) != XH.getDefaultResourcePack()) {
               return null;
            } else {
               sD modelresourcelocation = new sD(type + "_leaves", "normal");
               sc ibakedmodel = modelmanager.getModel(modelresourcelocation);
               if (ibakedmodel != null && ibakedmodel != modelmanager.getMissingModel()) {
                  List<rK> list = ibakedmodel.getQuads((in)null, (EnumFacing)null, 0L);
                  if (list.size() == 0) {
                     return ibakedmodel;
                  } else if (list.size() != 6) {
                     return null;
                  } else {
                     Iterator var8 = list.iterator();

                     while(var8.hasNext()) {
                        rK bakedquad = (rK)var8.next();
                        List list1 = ibakedmodel.getQuads((in)null, bakedquad.getFace(), 0L);
                        if (list1.size() > 0) {
                           return null;
                        }

                        list1.add(bakedquad);
                     }

                     list.clear();
                     updatedTypes.add(type + "_leaves");
                     return ibakedmodel;
                  }
               } else {
                  return null;
               }
            }
         }
      }
   }

   private static sc getModelDoubleFace(sc model) {
      if (model == null) {
         return null;
      } else if (model.getQuads((in)null, (EnumFacing)null, 0L).size() > 0) {
         XH.warn("SmartLeaves: Model is not cube, general quads: " + model.getQuads((in)null, (EnumFacing)null, 0L).size() + ", model: " + model);
         return model;
      } else {
         EnumFacing[] aenumfacing = EnumFacing.VALUES;

         for(int i = 0; i < aenumfacing.length; ++i) {
            EnumFacing enumfacing = aenumfacing[i];
            List<rK> list = model.getQuads((in)null, enumfacing, 0L);
            if (list.size() != 1) {
               XH.warn("SmartLeaves: Model is not cube, side: " + enumfacing + ", quads: " + list.size() + ", model: " + model);
               return model;
            }
         }

         sc ibakedmodel = bnf.duplicateModel(model);
         List[] alist = new List[aenumfacing.length];

         for(int k = 0; k < aenumfacing.length; ++k) {
            EnumFacing enumfacing1 = aenumfacing[k];
            List<rK> list1 = ibakedmodel.getQuads((in)null, enumfacing1, 0L);
            rK bakedquad = (rK)list1.get(0);
            rK bakedquad1 = new rK((int[])((int[])bakedquad.getVertexData().clone()), bakedquad.getTintIndex(), bakedquad.getFace(), bakedquad.getSprite());
            int[] aint = bakedquad1.getVertexData();
            int[] aint1 = (int[])((int[])(([I)aint).clone());
            int j = aint.length / 4;
            System.arraycopy(aint, 0 * j, aint1, 3 * j, j);
            System.arraycopy(aint, 1 * j, aint1, 2 * j, j);
            System.arraycopy(aint, 2 * j, aint1, 1 * j, j);
            System.arraycopy(aint, 3 * j, aint1, 0 * j, j);
            System.arraycopy(aint1, 0, aint, 0, aint1.length);
            list1.add(bakedquad1);
         }

         return ibakedmodel;
      }
   }
}
