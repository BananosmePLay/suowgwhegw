package neo;

public class blf extends bks {
   public blf() {
      super(Ko.class, "zombie_pigman", 0.5F);
   }

   public nH makeModel() {
      return new oP();
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      wL renderpigzombie = new wL(rendermanager);
      renderpigzombie.mainModel = modelBase;
      renderpigzombie.shadowSize = shadowSize;
      return renderpigzombie;
   }
}
