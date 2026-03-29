package neo;

public class bkK extends bkn {
   public bkK() {
      super(MR.class, "evocation_fangs", 0.0F);
   }

   public nH makeModel() {
      return new ob();
   }

   public ow getModelRenderer(nH model, String modelPart) {
      if (!(model instanceof ob)) {
         return null;
      } else {
         ob modelevokerfangs = (ob)model;
         if (modelPart.equals("base")) {
            return (ow)bnK.getFieldValue(modelevokerfangs, bnK.ModelEvokerFangs_ModelRenderers, 0);
         } else if (modelPart.equals("upper_jaw")) {
            return (ow)bnK.getFieldValue(modelevokerfangs, bnK.ModelEvokerFangs_ModelRenderers, 1);
         } else {
            return modelPart.equals("lower_jaw") ? (ow)bnK.getFieldValue(modelevokerfangs, bnK.ModelEvokerFangs_ModelRenderers, 2) : null;
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"base", "upper_jaw", "lower_jaw"};
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      wg renderevokerfangs = new wg(rendermanager);
      if (!bnK.RenderEvokerFangs_model.exists()) {
         XH.warn("Field not found: RenderEvokerFangs.model");
         return null;
      } else {
         bnK.setFieldValue(renderevokerfangs, bnK.RenderEvokerFangs_model, modelBase);
         renderevokerfangs.shadowSize = shadowSize;
         return renderevokerfangs;
      }
   }
}
