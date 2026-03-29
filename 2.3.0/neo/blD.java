package neo;

public class blD extends bkn {
   public blD() {
      super(Nf.class, "wither_skull", 0.0F);
   }

   public nH makeModel() {
      return new oF();
   }

   public ow getModelRenderer(nH model, String modelPart) {
      if (!(model instanceof oF)) {
         return null;
      } else {
         oF modelskeletonhead = (oF)model;
         return modelPart.equals("head") ? modelskeletonhead.skeletonHead : null;
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"head"};
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      xp renderwitherskull = new xp(rendermanager);
      if (!bnK.RenderWitherSkull_model.exists()) {
         XH.warn("Field not found: RenderWitherSkull_model");
         return null;
      } else {
         bnK.setFieldValue(renderwitherskull, bnK.RenderWitherSkull_model, modelBase);
         renderwitherskull.shadowSize = shadowSize;
         return renderwitherskull;
      }
   }
}
