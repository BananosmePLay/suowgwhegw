package neo;

public class bku extends bkn {
   public bku() {
      super(IR.class, "boat", 0.5F);
   }

   public nH makeModel() {
      return new nO();
   }

   public ow getModelRenderer(nH model, String modelPart) {
      if (!(model instanceof nO)) {
         return null;
      } else {
         nO modelboat = (nO)model;
         if (modelPart.equals("bottom")) {
            return modelboat.boatSides[0];
         } else if (modelPart.equals("back")) {
            return modelboat.boatSides[1];
         } else if (modelPart.equals("front")) {
            return modelboat.boatSides[2];
         } else if (modelPart.equals("right")) {
            return modelboat.boatSides[3];
         } else if (modelPart.equals("left")) {
            return modelboat.boatSides[4];
         } else if (modelPart.equals("paddle_left")) {
            return modelboat.paddles[0];
         } else if (modelPart.equals("paddle_right")) {
            return modelboat.paddles[1];
         } else {
            return modelPart.equals("bottom_no_water") ? modelboat.noWater : null;
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"bottom", "back", "front", "right", "left", "paddle_left", "paddle_right", "bottom_no_water"};
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      vR renderboat = new vR(rendermanager);
      if (!bnK.RenderBoat_modelBoat.exists()) {
         XH.warn("Field not found: RenderBoat.modelBoat");
         return null;
      } else {
         bnK.setFieldValue(renderboat, bnK.RenderBoat_modelBoat, modelBase);
         renderboat.shadowSize = shadowSize;
         return renderboat;
      }
   }
}
