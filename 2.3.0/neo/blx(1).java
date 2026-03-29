package neo;

public class blx extends bks {
   public blx() {
      super(Lc.class, "vex", 0.3F);
   }

   public ow getModelRenderer(nH model, String modelPart) {
      if (!(model instanceof oK)) {
         return null;
      } else {
         ow modelrenderer = super.getModelRenderer(model, modelPart);
         if (modelrenderer != null) {
            return modelrenderer;
         } else {
            oK modelvex = (oK)model;
            if (modelPart.equals("left_wing")) {
               return (ow)bnK.getFieldValue(modelvex, bnK.ModelVex_leftWing);
            } else {
               return modelPart.equals("right_wing") ? (ow)bnK.getFieldValue(modelvex, bnK.ModelVex_rightWing) : null;
            }
         }
      }
   }

   public String[] getModelRendererNames() {
      String[] astring = super.getModelRendererNames();
      astring = (String[])((String[])XH.addObjectsToArray(astring, new String[]{"left_wing", "right_wing"}));
      return astring;
   }

   public nH makeModel() {
      return new oK();
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      xi rendervex = new xi(rendermanager);
      rendervex.mainModel = modelBase;
      rendervex.shadowSize = shadowSize;
      return rendervex;
   }
}
