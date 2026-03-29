package neo;

public class bkD extends bkn {
   public bkD() {
      super(HS.class, "dragon", 0.5F);
   }

   public nH makeModel() {
      return new nV(0.0F);
   }

   public ow getModelRenderer(nH model, String modelPart) {
      if (!(model instanceof nV)) {
         return null;
      } else {
         nV modeldragon = (nV)model;
         if (modelPart.equals("head")) {
            return (ow)bnK.getFieldValue(modeldragon, bnK.ModelDragon_ModelRenderers, 0);
         } else if (modelPart.equals("spine")) {
            return (ow)bnK.getFieldValue(modeldragon, bnK.ModelDragon_ModelRenderers, 1);
         } else if (modelPart.equals("jaw")) {
            return (ow)bnK.getFieldValue(modeldragon, bnK.ModelDragon_ModelRenderers, 2);
         } else if (modelPart.equals("body")) {
            return (ow)bnK.getFieldValue(modeldragon, bnK.ModelDragon_ModelRenderers, 3);
         } else if (modelPart.equals("rear_leg")) {
            return (ow)bnK.getFieldValue(modeldragon, bnK.ModelDragon_ModelRenderers, 4);
         } else if (modelPart.equals("front_leg")) {
            return (ow)bnK.getFieldValue(modeldragon, bnK.ModelDragon_ModelRenderers, 5);
         } else if (modelPart.equals("rear_leg_tip")) {
            return (ow)bnK.getFieldValue(modeldragon, bnK.ModelDragon_ModelRenderers, 6);
         } else if (modelPart.equals("front_leg_tip")) {
            return (ow)bnK.getFieldValue(modeldragon, bnK.ModelDragon_ModelRenderers, 7);
         } else if (modelPart.equals("rear_foot")) {
            return (ow)bnK.getFieldValue(modeldragon, bnK.ModelDragon_ModelRenderers, 8);
         } else if (modelPart.equals("front_foot")) {
            return (ow)bnK.getFieldValue(modeldragon, bnK.ModelDragon_ModelRenderers, 9);
         } else if (modelPart.equals("wing")) {
            return (ow)bnK.getFieldValue(modeldragon, bnK.ModelDragon_ModelRenderers, 10);
         } else {
            return modelPart.equals("wing_tip") ? (ow)bnK.getFieldValue(modeldragon, bnK.ModelDragon_ModelRenderers, 11) : null;
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"head", "spine", "jaw", "body", "rear_leg", "front_leg", "rear_leg_tip", "front_leg_tip", "rear_foot", "front_foot", "wing", "wing_tip"};
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      vW renderdragon = new vW(rendermanager);
      renderdragon.mainModel = modelBase;
      renderdragon.shadowSize = shadowSize;
      return renderdragon;
   }
}
