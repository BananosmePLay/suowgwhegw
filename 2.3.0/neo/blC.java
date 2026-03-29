package neo;

public class blC extends bks {
   public blC() {
      super(Lh.class, "wither_skeleton", 0.7F);
   }

   public nH makeModel() {
      return new oE();
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      xo renderwitherskeleton = new xo(rendermanager);
      renderwitherskeleton.mainModel = modelBase;
      renderwitherskeleton.shadowSize = shadowSize;
      return renderwitherskeleton;
   }
}
