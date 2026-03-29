package neo;

public class bkt extends bkn {
   public bkt() {
      super(Jz.class, "blaze", 0.5F);
   }

   public nH makeModel() {
      return new nN();
   }

   public ow getModelRenderer(nH model, String modelPart) {
      if (!(model instanceof nN)) {
         return null;
      } else {
         nN modelblaze = (nN)model;
         if (modelPart.equals("head")) {
            return (ow)bnK.getFieldValue(modelblaze, bnK.ModelBlaze_blazeHead);
         } else {
            String s = "stick";
            if (modelPart.startsWith(s)) {
               ow[] amodelrenderer = (ow[])((ow[])bnK.getFieldValue(modelblaze, bnK.ModelBlaze_blazeSticks));
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
      return new String[]{"head", "stick1", "stick2", "stick3", "stick4", "stick5", "stick6", "stick7", "stick8", "stick9", "stick10", "stick11", "stick12"};
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      vQ renderblaze = new vQ(rendermanager);
      renderblaze.mainModel = modelBase;
      renderblaze.shadowSize = shadowSize;
      return renderblaze;
   }
}
