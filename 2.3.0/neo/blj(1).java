package neo;

public class blj extends blh {
   public blj() {
      super(Mb.class, "sheep", 0.7F);
   }

   public nH makeModel() {
      return new oy();
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      wQ rendersheep = new wQ(rendermanager);
      rendersheep.mainModel = modelBase;
      rendersheep.shadowSize = shadowSize;
      return rendersheep;
   }
}
