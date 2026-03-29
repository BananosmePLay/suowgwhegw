package neo;

public class ble extends blh {
   public ble() {
      super(LQ.class, "pig", 0.7F);
   }

   public nH makeModel() {
      return new or();
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      wJ renderpig = new wJ(rendermanager);
      renderpig.mainModel = modelBase;
      renderpig.shadowSize = shadowSize;
      return renderpig;
   }
}
