package neo;

public class bkJ extends bkS {
   public bkJ() {
      super(JR.class, "evocation_illager", 0.5F);
   }

   public nH makeModel() {
      return new og(0.0F, 0.0F, 64, 64);
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      wf renderevoker = new wf(rendermanager);
      renderevoker.mainModel = modelBase;
      renderevoker.shadowSize = shadowSize;
      return renderevoker;
   }
}
