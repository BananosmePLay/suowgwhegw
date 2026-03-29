package neo;

public class blA extends bkn {
   public blA() {
      super(Lg.class, "witch", 0.5F);
   }

   public nH makeModel() {
      return new oM(0.0F);
   }

   public ow getModelRenderer(nH model, String modelPart) {
      if (!(model instanceof oM)) {
         return null;
      } else {
         oM modelwitch = (oM)model;
         if (modelPart.equals("mole")) {
            return (ow)bnK.getFieldValue(modelwitch, bnK.ModelWitch_mole);
         } else if (modelPart.equals("hat")) {
            return (ow)bnK.getFieldValue(modelwitch, bnK.ModelWitch_hat);
         } else if (modelPart.equals("head")) {
            return modelwitch.villagerHead;
         } else if (modelPart.equals("body")) {
            return modelwitch.villagerBody;
         } else if (modelPart.equals("arms")) {
            return modelwitch.villagerArms;
         } else if (modelPart.equals("left_leg")) {
            return modelwitch.leftVillagerLeg;
         } else if (modelPart.equals("right_leg")) {
            return modelwitch.rightVillagerLeg;
         } else {
            return modelPart.equals("nose") ? modelwitch.villagerNose : null;
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"mole", "head", "body", "arms", "right_leg", "left_leg", "nose"};
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      xm renderwitch = new xm(rendermanager);
      renderwitch.mainModel = modelBase;
      renderwitch.shadowSize = shadowSize;
      return renderwitch;
   }
}
