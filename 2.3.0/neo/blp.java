package neo;

public class blp extends bkn {
   public blp() {
      super(KG.class, "silverfish", 0.3F);
   }

   public nH makeModel() {
      return new oD();
   }

   public ow getModelRenderer(nH model, String modelPart) {
      if (!(model instanceof oD)) {
         return null;
      } else {
         oD modelsilverfish = (oD)model;
         String s = "body";
         if (modelPart.startsWith(s)) {
            ow[] amodelrenderer1 = (ow[])((ow[])bnK.getFieldValue(modelsilverfish, bnK.ModelSilverfish_bodyParts));
            if (amodelrenderer1 == null) {
               return null;
            } else {
               String s3 = modelPart.substring(s.length());
               int j = XH.parseInt(s3, -1);
               --j;
               return j >= 0 && j < amodelrenderer1.length ? amodelrenderer1[j] : null;
            }
         } else {
            String s1 = "wing";
            if (modelPart.startsWith(s1)) {
               ow[] amodelrenderer = (ow[])((ow[])bnK.getFieldValue(modelsilverfish, bnK.ModelSilverfish_wingParts));
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
      return new String[]{"body1", "body2", "body3", "body4", "body5", "body6", "body7", "wing1", "wing2", "wing3"};
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      wV rendersilverfish = new wV(rendermanager);
      rendersilverfish.mainModel = modelBase;
      rendersilverfish.shadowSize = shadowSize;
      return rendersilverfish;
   }
}
