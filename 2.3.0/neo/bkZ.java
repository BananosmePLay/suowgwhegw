package neo;

public class bkZ extends bkX {
   public bkZ() {
      super(Jo.class, "tnt_minecart", 0.5F);
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      xg rendertntminecart = new xg(rendermanager);
      if (!bnK.RenderMinecart_modelMinecart.exists()) {
         XH.warn("Field not found: RenderMinecart.modelMinecart");
         return null;
      } else {
         bnK.setFieldValue(rendertntminecart, bnK.RenderMinecart_modelMinecart, modelBase);
         rendertntminecart.shadowSize = shadowSize;
         return rendertntminecart;
      }
   }
}
