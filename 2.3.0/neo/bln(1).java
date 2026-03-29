package neo;

public class bln extends bkn {
   public bln() {
      super(MZ.class, "shulker_bullet", 0.0F);
   }

   public nH makeModel() {
      return new oB();
   }

   public ow getModelRenderer(nH model, String modelPart) {
      if (!(model instanceof oB)) {
         return null;
      } else {
         oB modelshulkerbullet = (oB)model;
         return modelPart.equals("bullet") ? modelshulkerbullet.renderer : null;
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"bullet"};
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      wU rendershulkerbullet = new wU(rendermanager);
      if (!bnK.RenderShulkerBullet_model.exists()) {
         XH.warn("Field not found: RenderShulkerBullet.model");
         return null;
      } else {
         bnK.setFieldValue(rendershulkerbullet, bnK.RenderShulkerBullet_model, modelBase);
         rendershulkerbullet.shadowSize = shadowSize;
         return rendershulkerbullet;
      }
   }
}
