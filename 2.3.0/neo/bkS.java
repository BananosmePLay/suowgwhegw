package neo;

public abstract class bkS extends bkn {
   public bkS(Class entityClass, String name, float shadowSize) {
      super(entityClass, name, shadowSize);
   }

   public bkS(Class entityClass, String name, float shadowSize, String[] aliases) {
      super(entityClass, name, shadowSize, aliases);
   }

   public ow getModelRenderer(nH model, String modelPart) {
      if (!(model instanceof og)) {
         return null;
      } else {
         og modelillager = (og)model;
         if (modelPart.equals("head")) {
            return modelillager.head;
         } else if (modelPart.equals("body")) {
            return modelillager.body;
         } else if (modelPart.equals("arms")) {
            return modelillager.arms;
         } else if (modelPart.equals("left_leg")) {
            return modelillager.leg1;
         } else if (modelPart.equals("right_leg")) {
            return modelillager.leg0;
         } else if (modelPart.equals("nose")) {
            return modelillager.nose;
         } else if (modelPart.equals("left_arm")) {
            return modelillager.leftArm;
         } else {
            return modelPart.equals("right_arm") ? modelillager.rightArm : null;
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"head", "body", "arms", "right_leg", "left_leg", "nose", "right_arm", "left_arm"};
   }
}
