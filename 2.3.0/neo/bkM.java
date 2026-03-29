package neo;

public class bkM extends bkn {
   public bkM() {
      super(Kc.class, "guardian", 0.5F);
   }

   public nH makeModel() {
      return new od();
   }

   public ow getModelRenderer(nH model, String modelPart) {
      if (!(model instanceof od)) {
         return null;
      } else {
         od modelguardian = (od)model;
         if (modelPart.equals("body")) {
            return (ow)bnK.getFieldValue(modelguardian, bnK.ModelGuardian_body);
         } else if (modelPart.equals("eye")) {
            return (ow)bnK.getFieldValue(modelguardian, bnK.ModelGuardian_eye);
         } else {
            String s = "spine";
            if (modelPart.startsWith(s)) {
               ow[] amodelrenderer1 = (ow[])((ow[])bnK.getFieldValue(modelguardian, bnK.ModelGuardian_spines));
               if (amodelrenderer1 == null) {
                  return null;
               } else {
                  String s3 = modelPart.substring(s.length());
                  int j = XH.parseInt(s3, -1);
                  --j;
                  return j >= 0 && j < amodelrenderer1.length ? amodelrenderer1[j] : null;
               }
            } else {
               String s1 = "tail";
               if (modelPart.startsWith(s1)) {
                  ow[] amodelrenderer = (ow[])((ow[])bnK.getFieldValue(modelguardian, bnK.ModelGuardian_tail));
                  if (amodelrenderer == null) {
                     return null;
                  } else {
                     String s2 = modelPart.substring(s1.length());
                     int i = XH.parseInt(s2, -1);
                     --i;
                     return i >= 0 && i < amodelrenderer.length ? amodelrenderer[i] : null;
                  }
               } else {
                  return null;
               }
            }
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"body", "eye", "spine1", "spine2", "spine3", "spine4", "spine5", "spine6", "spine7", "spine8", "spine9", "spine10", "spine11", "spine12", "tail1", "tail2", "tail3"};
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      wn renderguardian = new wn(rendermanager);
      renderguardian.mainModel = modelBase;
      renderguardian.shadowSize = shadowSize;
      return renderguardian;
   }
}
