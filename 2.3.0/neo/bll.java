package neo;

public class bll extends bkn {
   public bll() {
      super(KD.class, "shulker", 0.0F);
   }

   public nH makeModel() {
      return new oA();
   }

   public ow getModelRenderer(nH model, String modelPart) {
      if (!(model instanceof oA)) {
         return null;
      } else {
         oA modelshulker = (oA)model;
         if (modelPart.equals("head")) {
            return modelshulker.head;
         } else if (modelPart.equals("base")) {
            return modelshulker.base;
         } else {
            return modelPart.equals("lid") ? modelshulker.lid : null;
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"base", "lid", "head"};
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      wT rendershulker = new wT(rendermanager);
      rendershulker.mainModel = modelBase;
      rendershulker.shadowSize = shadowSize;
      return rendershulker;
   }
}
