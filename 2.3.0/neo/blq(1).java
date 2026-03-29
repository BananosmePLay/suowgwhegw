package neo;

public class blq extends bks {
   public blq() {
      super(KH.class, "skeleton", 0.7F);
   }

   public nH makeModel() {
      return new oE();
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      wX renderskeleton = new wX(rendermanager);
      renderskeleton.mainModel = modelBase;
      renderskeleton.shadowSize = shadowSize;
      return renderskeleton;
   }
}
