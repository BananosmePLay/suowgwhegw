package neo;

public class blr extends bkQ {
   public blr() {
      super(Md.class, "skeleton_horse", 0.75F);
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      vJ renderabstracthorse = new vJ(rendermanager);
      renderabstracthorse.mainModel = modelBase;
      renderabstracthorse.shadowSize = shadowSize;
      return renderabstracthorse;
   }
}
