package neo;

public class blv extends bkn {
   public blv() {
      super(Mf.class, "squid", 0.7F);
   }

   public nH makeModel() {
      return new oJ();
   }

   public ow getModelRenderer(nH model, String modelPart) {
      if (!(model instanceof oJ)) {
         return null;
      } else {
         oJ modelsquid = (oJ)model;
         if (modelPart.equals("body")) {
            return (ow)bnK.getFieldValue(modelsquid, bnK.ModelSquid_body);
         } else {
            String s = "tentacle";
            if (modelPart.startsWith(s)) {
               ow[] amodelrenderer = (ow[])((ow[])bnK.getFieldValue(modelsquid, bnK.ModelSquid_tentacles));
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
      return new String[]{"body", "tentacle1", "tentacle2", "tentacle3", "tentacle4", "tentacle5", "tentacle6", "tentacle7", "tentacle8"};
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      xd rendersquid = new xd(rendermanager);
      rendersquid.mainModel = modelBase;
      rendersquid.shadowSize = shadowSize;
      return rendersquid;
   }
}
