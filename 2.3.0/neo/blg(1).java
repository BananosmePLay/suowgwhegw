package neo;

public class blg extends blh {
   public blg() {
      super(Kv.class, "polar_bear", 0.7F);
   }

   public nH makeModel() {
      return new ot();
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      wN renderpolarbear = new wN(rendermanager);
      renderpolarbear.mainModel = modelBase;
      renderpolarbear.shadowSize = shadowSize;
      return renderpolarbear;
   }
}
