package neo;

public class blB extends bkn {
   public blB() {
      super(HV.class, "wither", 0.5F);
   }

   public nH makeModel() {
      return new oN(0.0F);
   }

   public ow getModelRenderer(nH model, String modelPart) {
      if (!(model instanceof oN)) {
         return null;
      } else {
         oN modelwither = (oN)model;
         String s = "body";
         if (modelPart.startsWith(s)) {
            ow[] amodelrenderer1 = (ow[])((ow[])bnK.getFieldValue(modelwither, bnK.ModelWither_bodyParts));
            if (amodelrenderer1 == null) {
               return null;
            } else {
               String s3 = modelPart.substring(s.length());
               int j = XH.parseInt(s3, -1);
               --j;
               return j >= 0 && j < amodelrenderer1.length ? amodelrenderer1[j] : null;
            }
         } else {
            String s1 = "head";
            if (modelPart.startsWith(s1)) {
               ow[] amodelrenderer = (ow[])((ow[])bnK.getFieldValue(modelwither, bnK.ModelWither_heads));
               if (amodelrenderer == null) {
                  return null;
               } else {
                  String s2 = modelPart.substring(s1.length());
                  int i = XH.parseInt(s2, -1);
                  --i;
                  return i >= 0 && i < amodelrenderer.length ? amodelrenderer[i] : null;
               }
            } else {
               return null;
            }
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"body1", "body2", "body3", "head1", "head2", "head3"};
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      xn renderwither = new xn(rendermanager);
      renderwither.mainModel = modelBase;
      renderwither.shadowSize = shadowSize;
      return renderwither;
   }
}
