package neo;

public class bko extends bks {
   public bko() {
      super(IN.class, "armor_stand", 0.0F);
   }

   public nH makeModel() {
      return new nE();
   }

   public ow getModelRenderer(nH model, String modelPart) {
      if (!(model instanceof nE)) {
         return null;
      } else {
         nE modelarmorstand = (nE)model;
         if (modelPart.equals("right")) {
            return modelarmorstand.standRightSide;
         } else if (modelPart.equals("left")) {
            return modelarmorstand.standLeftSide;
         } else if (modelPart.equals("waist")) {
            return modelarmorstand.standWaist;
         } else {
            return modelPart.equals("base") ? modelarmorstand.standBase : super.getModelRenderer(modelarmorstand, modelPart);
         }
      }
   }

   public String[] getModelRendererNames() {
      String[] astring = super.getModelRendererNames();
      astring = (String[])((String[])XH.addObjectsToArray(astring, new String[]{"right", "left", "waist", "base"}));
      return astring;
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      vM renderarmorstand = new vM(rendermanager);
      renderarmorstand.mainModel = modelBase;
      renderarmorstand.shadowSize = shadowSize;
      return renderarmorstand;
   }
}
