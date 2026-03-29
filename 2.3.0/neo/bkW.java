package neo;

public class bkW extends bkn {
   public bkW() {
      super(Kk.class, "magma_cube", 0.5F);
   }

   public nH makeModel() {
      return new om();
   }

   public ow getModelRenderer(nH model, String modelPart) {
      if (!(model instanceof om)) {
         return null;
      } else {
         om modelmagmacube = (om)model;
         if (modelPart.equals("core")) {
            return (ow)bnK.getFieldValue(modelmagmacube, bnK.ModelMagmaCube_core);
         } else {
            String s = "segment";
            if (modelPart.startsWith(s)) {
               ow[] amodelrenderer = (ow[])((ow[])bnK.getFieldValue(modelmagmacube, bnK.ModelMagmaCube_segments));
               if (amodelrenderer == null) {
                  return null;
               } else {
                  String s1 = modelPart.substring(s.length());
                  int i = XH.parseInt(s1, -1);
                  --i;
                  return i >= 0 && i < amodelrenderer.length ? amodelrenderer[i] : null;
               }
            } else {
               return null;
            }
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"core", "segment1", "segment2", "segment3", "segment4", "segment5", "segment6", "segment7", "segment8"};
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      wB rendermagmacube = new wB(rendermanager);
      rendermagmacube.mainModel = modelBase;
      rendermagmacube.shadowSize = shadowSize;
      return rendermagmacube;
   }
}
