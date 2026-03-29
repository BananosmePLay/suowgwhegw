package neo;

public class bkH extends bks {
   public bkH() {
      super(JJ.class, "enderman", 0.5F);
   }

   public nH makeModel() {
      return new nZ(0.0F);
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      wa renderenderman = new wa(rendermanager);
      renderenderman.mainModel = modelBase;
      renderenderman.shadowSize = shadowSize;
      return renderenderman;
   }
}
