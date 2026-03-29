package neo;

public class bkF extends bkn {
   public bkF() {
      this("end_crystal");
   }

   protected bkF(String name) {
      super(IS.class, name, 0.5F);
   }

   public nH makeModel() {
      return new nY(0.0F, true);
   }

   public ow getModelRenderer(nH model, String modelPart) {
      if (!(model instanceof nY)) {
         return null;
      } else {
         nY modelendercrystal = (nY)model;
         if (modelPart.equals("cube")) {
            return (ow)bnK.getFieldValue(modelendercrystal, bnK.ModelEnderCrystal_ModelRenderers, 0);
         } else if (modelPart.equals("glass")) {
            return (ow)bnK.getFieldValue(modelendercrystal, bnK.ModelEnderCrystal_ModelRenderers, 1);
         } else {
            return modelPart.equals("base") ? (ow)bnK.getFieldValue(modelendercrystal, bnK.ModelEnderCrystal_ModelRenderers, 2) : null;
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"cube", "glass", "base"};
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      vI render = (vI)rendermanager.getEntityRenderMap().get(IS.class);
      if (!(render instanceof vZ)) {
         XH.warn("Not an instance of RenderEnderCrystal: " + render);
         return null;
      } else {
         vZ renderendercrystal = (vZ)render;
         if (!bnK.RenderEnderCrystal_modelEnderCrystal.exists()) {
            XH.warn("Field not found: RenderEnderCrystal.modelEnderCrystal");
            return null;
         } else {
            bnK.setFieldValue(renderendercrystal, bnK.RenderEnderCrystal_modelEnderCrystal, modelBase);
            renderendercrystal.shadowSize = shadowSize;
            return renderendercrystal;
         }
      }
   }
}
