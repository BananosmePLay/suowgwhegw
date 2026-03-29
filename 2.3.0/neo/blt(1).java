package neo;

public class blt extends bkn {
   public blt() {
      super(KO.class, "snow_golem", 0.5F);
   }

   public nH makeModel() {
      return new oH();
   }

   public ow getModelRenderer(nH model, String modelPart) {
      if (!(model instanceof oH)) {
         return null;
      } else {
         oH modelsnowman = (oH)model;
         if (modelPart.equals("body")) {
            return modelsnowman.body;
         } else if (modelPart.equals("body_bottom")) {
            return modelsnowman.bottomBody;
         } else if (modelPart.equals("head")) {
            return modelsnowman.head;
         } else if (modelPart.equals("left_hand")) {
            return modelsnowman.leftHand;
         } else {
            return modelPart.equals("right_hand") ? modelsnowman.rightHand : null;
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"body", "body_bottom", "head", "right_hand", "left_hand"};
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      xa rendersnowman = new xa(rendermanager);
      rendersnowman.mainModel = modelBase;
      rendersnowman.shadowSize = shadowSize;
      return rendersnowman;
   }
}
