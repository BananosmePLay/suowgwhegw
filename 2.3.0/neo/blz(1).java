package neo;

public class blz extends bkS {
   public blz() {
      super(Lf.class, "vindication_illager", 0.5F);
   }

   public nH makeModel() {
      return new og(0.0F, 0.0F, 64, 64);
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      xl rendervindicator = new xl(rendermanager);
      rendervindicator.mainModel = modelBase;
      rendervindicator.shadowSize = shadowSize;
      return rendervindicator;
   }
}
