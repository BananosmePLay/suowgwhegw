package neo;

public class bkU extends bkn {
   public bkU() {
      super(Ip.class, "lead_knot", 0.0F);
   }

   public nH makeModel() {
      return new oj();
   }

   public ow getModelRenderer(nH model, String modelPart) {
      if (!(model instanceof oj)) {
         return null;
      } else {
         oj modelleashknot = (oj)model;
         return modelPart.equals("knot") ? modelleashknot.knotRenderer : null;
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"knot"};
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      wu renderleashknot = new wu(rendermanager);
      if (!bnK.RenderLeashKnot_leashKnotModel.exists()) {
         XH.warn("Field not found: RenderLeashKnot.leashKnotModel");
         return null;
      } else {
         bnK.setFieldValue(renderleashknot, bnK.RenderLeashKnot_leashKnotModel, modelBase);
         renderleashknot.shadowSize = shadowSize;
         return renderleashknot;
      }
   }
}
