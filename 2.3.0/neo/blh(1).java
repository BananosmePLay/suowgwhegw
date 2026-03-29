package neo;

public abstract class blh extends bkn {
   public blh(Class entityClass, String name, float shadowSize) {
      super(entityClass, name, shadowSize);
   }

   public ow getModelRenderer(nH model, String modelPart) {
      if (!(model instanceof ou)) {
         return null;
      } else {
         ou modelquadruped = (ou)model;
         if (modelPart.equals("head")) {
            return modelquadruped.head;
         } else if (modelPart.equals("body")) {
            return modelquadruped.body;
         } else if (modelPart.equals("leg1")) {
            return modelquadruped.leg1;
         } else if (modelPart.equals("leg2")) {
            return modelquadruped.leg2;
         } else if (modelPart.equals("leg3")) {
            return modelquadruped.leg3;
         } else {
            return modelPart.equals("leg4") ? modelquadruped.leg4 : null;
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"head", "body", "leg1", "leg2", "leg3", "leg4"};
   }
}
