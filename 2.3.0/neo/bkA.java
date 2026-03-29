package neo;

public class bkA extends blh {
   public bkA() {
      super(LB.class, "cow", 0.7F);
   }

   public nH makeModel() {
      return new nT();
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      vU rendercow = new vU(rendermanager);
      rendercow.mainModel = modelBase;
      rendercow.shadowSize = shadowSize;
      return rendercow;
   }
}
