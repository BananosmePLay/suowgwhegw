package neo;

public class blH extends bks {
   public blH() {
      super(Ll.class, "zombie_villager", 0.5F);
   }

   public nH makeModel() {
      return new oQ();
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      xu renderzombievillager = new xu(rendermanager);
      renderzombievillager.mainModel = modelBase;
      renderzombievillager.shadowSize = shadowSize;
      return renderzombievillager;
   }
}
