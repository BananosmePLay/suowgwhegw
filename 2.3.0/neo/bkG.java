package neo;

public class bkG extends bkF {
   public bkG() {
      super("end_crystal_no_base");
   }

   public nH makeModel() {
      return new nY(0.0F, false);
   }

   public String[] getModelRendererNames() {
      String[] astring = super.getModelRendererNames();
      astring = (String[])((String[])XH.removeObjectFromArray(astring, "base"));
      return astring;
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      vI render = (vI)rendermanager.getEntityRenderMap().get(IS.class);
      if (!(render instanceof vZ)) {
         XH.warn("Not an instance of RenderEnderCrystal: " + render);
         return null;
      } else {
         vZ renderendercrystal = (vZ)render;
         if (!bnK.RenderEnderCrystal_modelEnderCrystalNoBase.exists()) {
            XH.warn("Field not found: RenderEnderCrystal.modelEnderCrystalNoBase");
            return null;
         } else {
            bnK.setFieldValue(renderendercrystal, bnK.RenderEnderCrystal_modelEnderCrystalNoBase, modelBase);
            renderendercrystal.shadowSize = shadowSize;
            return renderendercrystal;
         }
      }
   }
}
