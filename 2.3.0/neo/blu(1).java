package neo;

public class blu extends bkn {
   public blu() {
      super(KW.class, "spider", 1.0F);
   }

   protected blu(Class entityClass, String name, float shadowSize) {
      super(entityClass, name, shadowSize);
   }

   public nH makeModel() {
      return new oI();
   }

   public ow getModelRenderer(nH model, String modelPart) {
      if (!(model instanceof oI)) {
         return null;
      } else {
         oI modelspider = (oI)model;
         if (modelPart.equals("head")) {
            return modelspider.spiderHead;
         } else if (modelPart.equals("neck")) {
            return modelspider.spiderNeck;
         } else if (modelPart.equals("body")) {
            return modelspider.spiderBody;
         } else if (modelPart.equals("leg1")) {
            return modelspider.spiderLeg1;
         } else if (modelPart.equals("leg2")) {
            return modelspider.spiderLeg2;
         } else if (modelPart.equals("leg3")) {
            return modelspider.spiderLeg3;
         } else if (modelPart.equals("leg4")) {
            return modelspider.spiderLeg4;
         } else if (modelPart.equals("leg5")) {
            return modelspider.spiderLeg5;
         } else if (modelPart.equals("leg6")) {
            return modelspider.spiderLeg6;
         } else if (modelPart.equals("leg7")) {
            return modelspider.spiderLeg7;
         } else {
            return modelPart.equals("leg8") ? modelspider.spiderLeg8 : null;
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"head", "neck", "body", "leg1", "leg2", "leg3", "leg4", "leg5", "leg6", "leg7", "leg8"};
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      xc renderspider = new xc(rendermanager);
      renderspider.mainModel = modelBase;
      renderspider.shadowSize = shadowSize;
      return renderspider;
   }
}
