package neo;

public class bkT extends bkn {
   public bkT() {
      super(Kj.class, "iron_golem", 0.5F);
   }

   public nH makeModel() {
      return new oh();
   }

   public ow getModelRenderer(nH model, String modelPart) {
      if (!(model instanceof oh)) {
         return null;
      } else {
         oh modelirongolem = (oh)model;
         if (modelPart.equals("head")) {
            return modelirongolem.ironGolemHead;
         } else if (modelPart.equals("body")) {
            return modelirongolem.ironGolemBody;
         } else if (modelPart.equals("left_arm")) {
            return modelirongolem.ironGolemLeftArm;
         } else if (modelPart.equals("right_arm")) {
            return modelirongolem.ironGolemRightArm;
         } else if (modelPart.equals("left_leg")) {
            return modelirongolem.ironGolemLeftLeg;
         } else {
            return modelPart.equals("right_leg") ? modelirongolem.ironGolemRightLeg : null;
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"head", "body", "right_arm", "left_arm", "left_leg", "right_leg"};
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      ws renderirongolem = new ws(rendermanager);
      renderirongolem.mainModel = modelBase;
      renderirongolem.shadowSize = shadowSize;
      return renderirongolem;
   }
}
