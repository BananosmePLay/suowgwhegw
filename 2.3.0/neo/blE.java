package neo;

public class blE extends bkn {
   public blE() {
      super(Mu.class, "wolf", 0.5F);
   }

   public nH makeModel() {
      return new oO();
   }

   public ow getModelRenderer(nH model, String modelPart) {
      if (!(model instanceof oO)) {
         return null;
      } else {
         oO modelwolf = (oO)model;
         if (modelPart.equals("head")) {
            return modelwolf.wolfHeadMain;
         } else if (modelPart.equals("body")) {
            return modelwolf.wolfBody;
         } else if (modelPart.equals("leg1")) {
            return modelwolf.wolfLeg1;
         } else if (modelPart.equals("leg2")) {
            return modelwolf.wolfLeg2;
         } else if (modelPart.equals("leg3")) {
            return modelwolf.wolfLeg3;
         } else if (modelPart.equals("leg4")) {
            return modelwolf.wolfLeg4;
         } else if (modelPart.equals("tail")) {
            return (ow)bnK.getFieldValue(modelwolf, bnK.ModelWolf_tail);
         } else {
            return modelPart.equals("mane") ? (ow)bnK.getFieldValue(modelwolf, bnK.ModelWolf_mane) : null;
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"head", "body", "leg1", "leg2", "leg3", "leg4", "tail", "mane"};
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      xq renderwolf = new xq(rendermanager);
      renderwolf.mainModel = modelBase;
      renderwolf.shadowSize = shadowSize;
      return renderwolf;
   }
}
