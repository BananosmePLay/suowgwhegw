package neo;

public class bls extends bkn {
   public bls() {
      super(KN.class, "slime", 0.25F);
   }

   public nH makeModel() {
      return new oG(16);
   }

   public ow getModelRenderer(nH model, String modelPart) {
      if (!(model instanceof oG)) {
         return null;
      } else {
         oG modelslime = (oG)model;
         if (modelPart.equals("body")) {
            return (ow)bnK.getFieldValue(modelslime, bnK.ModelSlime_ModelRenderers, 0);
         } else if (modelPart.equals("left_eye")) {
            return (ow)bnK.getFieldValue(modelslime, bnK.ModelSlime_ModelRenderers, 1);
         } else if (modelPart.equals("right_eye")) {
            return (ow)bnK.getFieldValue(modelslime, bnK.ModelSlime_ModelRenderers, 2);
         } else {
            return modelPart.equals("mouth") ? (ow)bnK.getFieldValue(modelslime, bnK.ModelSlime_ModelRenderers, 3) : null;
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"body", "left_eye", "right_eye", "mouth"};
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      wY renderslime = new wY(rendermanager);
      renderslime.mainModel = modelBase;
      renderslime.shadowSize = shadowSize;
      return renderslime;
   }
}
