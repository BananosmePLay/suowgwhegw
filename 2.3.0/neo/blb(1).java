package neo;

public class blb extends bkQ {
   public blb() {
      super(LM.class, "mule", 0.75F);
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      vJ renderabstracthorse = new vJ(rendermanager);
      renderabstracthorse.mainModel = modelBase;
      renderabstracthorse.shadowSize = shadowSize;
      return renderabstracthorse;
   }
}
