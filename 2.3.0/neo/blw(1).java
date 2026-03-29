package neo;

public class blw extends bks {
   public blw() {
      super(KX.class, "stray", 0.7F);
   }

   public nH makeModel() {
      return new oE();
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      xe renderstray = new xe(rendermanager);
      renderstray.mainModel = modelBase;
      renderstray.shadowSize = shadowSize;
      return renderstray;
   }
}
