package neo;

public class bkR extends bks {
   public bkR() {
      super(Kd.class, "husk", 0.5F);
   }

   public nH makeModel() {
      return new oP();
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      wp renderhusk = new wp(rendermanager);
      renderhusk.mainModel = modelBase;
      renderhusk.shadowSize = shadowSize;
      return renderhusk;
   }
}
