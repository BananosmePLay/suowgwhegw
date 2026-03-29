package neo;

public class bkw extends blu {
   public bkw() {
      super(JA.class, "cave_spider", 0.7F);
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      vS rendercavespider = new vS(rendermanager);
      rendercavespider.mainModel = modelBase;
      rendercavespider.shadowSize = shadowSize;
      return rendercavespider;
   }
}
