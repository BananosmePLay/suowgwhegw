package neo;

public class bkL extends bkn {
   public bkL() {
      super(JW.class, "ghast", 0.5F);
   }

   public nH makeModel() {
      return new oc();
   }

   public ow getModelRenderer(nH model, String modelPart) {
      if (!(model instanceof oc)) {
         return null;
      } else {
         oc modelghast = (oc)model;
         if (modelPart.equals("body")) {
            return (ow)bnK.getFieldValue(modelghast, bnK.ModelGhast_body);
         } else {
            String s = "tentacle";
            if (modelPart.startsWith(s)) {
               ow[] amodelrenderer = (ow[])((ow[])bnK.getFieldValue(modelghast, bnK.ModelGhast_tentacles));
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
      return new String[]{"body", "tentacle1", "tentacle2", "tentacle3", "tentacle4", "tentacle5", "tentacle6", "tentacle7", "tentacle8", "tentacle9"};
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      wk renderghast = new wk(rendermanager);
      renderghast.mainModel = modelBase;
      renderghast.shadowSize = shadowSize;
      return renderghast;
   }
}
