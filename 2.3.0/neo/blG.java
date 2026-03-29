package neo;

public class blG extends bkQ {
   public blG() {
      super(Mv.class, "zombie_horse", 0.75F);
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      vJ renderabstracthorse = new vJ(rendermanager);
      renderabstracthorse.mainModel = modelBase;
      renderabstracthorse.shadowSize = shadowSize;
      return renderabstracthorse;
   }
}
