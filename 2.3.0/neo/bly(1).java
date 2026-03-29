package neo;

public class bly extends bkn {
   public bly() {
      super(Mq.class, "villager", 0.5F);
   }

   public nH makeModel() {
      return new oL(0.0F);
   }

   public ow getModelRenderer(nH model, String modelPart) {
      if (!(model instanceof oL)) {
         return null;
      } else {
         oL modelvillager = (oL)model;
         if (modelPart.equals("head")) {
            return modelvillager.villagerHead;
         } else if (modelPart.equals("body")) {
            return modelvillager.villagerBody;
         } else if (modelPart.equals("arms")) {
            return modelvillager.villagerArms;
         } else if (modelPart.equals("left_leg")) {
            return modelvillager.leftVillagerLeg;
         } else if (modelPart.equals("right_leg")) {
            return modelvillager.rightVillagerLeg;
         } else {
            return modelPart.equals("nose") ? modelvillager.villagerNose : null;
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"head", "body", "arms", "right_leg", "left_leg", "nose"};
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      xj rendervillager = new xj(rendermanager);
      rendervillager.mainModel = modelBase;
      rendervillager.shadowSize = shadowSize;
      return rendervillager;
   }
}
