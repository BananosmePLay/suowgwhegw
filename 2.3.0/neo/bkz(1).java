package neo;

public class bkz extends bkn {
   public bkz() {
      super(LA.class, "chicken", 0.3F);
   }

   public nH makeModel() {
      return new nS();
   }

   public ow getModelRenderer(nH model, String modelPart) {
      if (!(model instanceof nS)) {
         return null;
      } else {
         nS modelchicken = (nS)model;
         if (modelPart.equals("head")) {
            return modelchicken.head;
         } else if (modelPart.equals("body")) {
            return modelchicken.body;
         } else if (modelPart.equals("right_leg")) {
            return modelchicken.rightLeg;
         } else if (modelPart.equals("left_leg")) {
            return modelchicken.leftLeg;
         } else if (modelPart.equals("right_wing")) {
            return modelchicken.rightWing;
         } else if (modelPart.equals("left_wing")) {
            return modelchicken.leftWing;
         } else if (modelPart.equals("bill")) {
            return modelchicken.bill;
         } else {
            return modelPart.equals("chin") ? modelchicken.chin : null;
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"head", "body", "right_leg", "left_leg", "right_wing", "left_wing", "bill", "chin"};
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      vT renderchicken = new vT(rendermanager);
      renderchicken.mainModel = modelBase;
      renderchicken.shadowSize = shadowSize;
      return renderchicken;
   }
}
