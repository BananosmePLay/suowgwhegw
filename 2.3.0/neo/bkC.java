package neo;

public class bkC extends bkQ {
   public bkC() {
      super(LC.class, "donkey", 0.75F);
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      vJ renderabstracthorse = new vJ(rendermanager);
      renderabstracthorse.mainModel = modelBase;
      renderabstracthorse.shadowSize = shadowSize;
      return renderabstracthorse;
   }
}
