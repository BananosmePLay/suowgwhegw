package neo;

public abstract class bks extends bkn {
   public bks(Class entityClass, String name, float shadowSize) {
      super(entityClass, name, shadowSize);
   }

   public ow getModelRenderer(nH model, String modelPart) {
      if (!(model instanceof nM)) {
         return null;
      } else {
         nM modelbiped = (nM)model;
         if (modelPart.equals("head")) {
            return modelbiped.bipedHead;
         } else if (modelPart.equals("headwear")) {
            return modelbiped.bipedHeadwear;
         } else if (modelPart.equals("body")) {
            return modelbiped.bipedBody;
         } else if (modelPart.equals("left_arm")) {
            return modelbiped.bipedLeftArm;
         } else if (modelPart.equals("right_arm")) {
            return modelbiped.bipedRightArm;
         } else if (modelPart.equals("left_leg")) {
            return modelbiped.bipedLeftLeg;
         } else {
            return modelPart.equals("right_leg") ? modelbiped.bipedRightLeg : null;
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"head", "headwear", "body", "left_arm", "right_arm", "left_leg", "right_leg"};
   }
}
