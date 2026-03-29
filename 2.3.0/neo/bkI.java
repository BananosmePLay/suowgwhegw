package neo;

public class bkI extends bkn {
   public bkI() {
      super(JK.class, "endermite", 0.3F);
   }

   public nH makeModel() {
      return new oa();
   }

   public ow getModelRenderer(nH model, String modelPart) {
      if (!(model instanceof oa)) {
         return null;
      } else {
         oa modelendermite = (oa)model;
         String s = "body";
         if (modelPart.startsWith(s)) {
            ow[] amodelrenderer = (ow[])((ow[])bnK.getFieldValue(modelendermite, bnK.ModelEnderMite_bodyParts));
            if (amodelrenderer == null) {
               return null;
            } else {
               String s1 = modelPart.substring(s.length());
               int i = XH.parseInt(s1, -1);
               --i;
               return i >= 0 && i < amodelrenderer.length ? amodelrenderer[i] : null;
            }
         } else {
            return null;
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"body1", "body2", "body3", "body4"};
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      wb renderendermite = new wb(rendermanager);
      renderendermite.mainModel = modelBase;
      renderendermite.shadowSize = shadowSize;
      return renderendermite;
   }
}
