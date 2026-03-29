package neo;

public class bkB extends bkn {
   public bkB() {
      super(JB.class, "creeper", 0.5F);
   }

   public nH makeModel() {
      return new nU();
   }

   public ow getModelRenderer(nH model, String modelPart) {
      if (!(model instanceof nU)) {
         return null;
      } else {
         nU modelcreeper = (nU)model;
         if (modelPart.equals("head")) {
            return modelcreeper.head;
         } else if (modelPart.equals("armor")) {
            return modelcreeper.creeperArmor;
         } else if (modelPart.equals("body")) {
            return modelcreeper.body;
         } else if (modelPart.equals("leg1")) {
            return modelcreeper.leg1;
         } else if (modelPart.equals("leg2")) {
            return modelcreeper.leg2;
         } else if (modelPart.equals("leg3")) {
            return modelcreeper.leg3;
         } else {
            return modelPart.equals("leg4") ? modelcreeper.leg4 : null;
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"head", "armor", "body", "leg1", "leg2", "leg3", "leg4"};
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      vV rendercreeper = new vV(rendermanager);
      rendercreeper.mainModel = modelBase;
      rendercreeper.shadowSize = shadowSize;
      return rendercreeper;
   }
}
