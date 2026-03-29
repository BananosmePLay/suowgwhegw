package neo;

public class blF extends bks {
   public blF() {
      super(Lk.class, "zombie", 0.5F);
   }

   public nH makeModel() {
      return new oP();
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      xt renderzombie = new xt(rendermanager);
      renderzombie.mainModel = modelBase;
      renderzombie.shadowSize = shadowSize;
      return renderzombie;
   }
}
