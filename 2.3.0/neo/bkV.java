package neo;

public class bkV extends blh {
   public bkV() {
      super(LK.class, "llama", 0.7F);
   }

   public nH makeModel() {
      return new ok(0.0F);
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      wz renderllama = new wz(rendermanager);
      renderllama.mainModel = modelBase;
      renderllama.shadowSize = shadowSize;
      return renderllama;
   }
}
