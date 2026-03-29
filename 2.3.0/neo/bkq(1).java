package neo;

public class bkq extends bkn {
   public bkq() {
      super(Lz.class, "bat", 0.25F);
   }

   public nH makeModel() {
      return new nI();
   }

   public ow getModelRenderer(nH model, String modelPart) {
      if (!(model instanceof nI)) {
         return null;
      } else {
         nI modelbat = (nI)model;
         if (modelPart.equals("head")) {
            return (ow)bnK.getFieldValue(modelbat, bnK.ModelBat_ModelRenderers, 0);
         } else if (modelPart.equals("body")) {
            return (ow)bnK.getFieldValue(modelbat, bnK.ModelBat_ModelRenderers, 1);
         } else if (modelPart.equals("right_wing")) {
            return (ow)bnK.getFieldValue(modelbat, bnK.ModelBat_ModelRenderers, 2);
         } else if (modelPart.equals("left_wing")) {
            return (ow)bnK.getFieldValue(modelbat, bnK.ModelBat_ModelRenderers, 3);
         } else if (modelPart.equals("outer_right_wing")) {
            return (ow)bnK.getFieldValue(modelbat, bnK.ModelBat_ModelRenderers, 4);
         } else {
            return modelPart.equals("outer_left_wing") ? (ow)bnK.getFieldValue(modelbat, bnK.ModelBat_ModelRenderers, 5) : null;
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"head", "body", "right_wing", "left_wing", "outer_right_wing", "outer_left_wing"};
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      vO renderbat = new vO(rendermanager);
      renderbat.mainModel = modelBase;
      renderbat.shadowSize = shadowSize;
      return renderbat;
   }
}
