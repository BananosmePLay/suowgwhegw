package neo;

public class bla extends blh {
   public bla() {
      super(LL.class, "mooshroom", 0.7F);
   }

   public nH makeModel() {
      return new nT();
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      wF rendermooshroom = new wF(rendermanager);
      rendermooshroom.mainModel = modelBase;
      rendermooshroom.shadowSize = shadowSize;
      return rendermooshroom;
   }
}
