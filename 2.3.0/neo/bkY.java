package neo;

public class bkY extends bkX {
   public bkY() {
      super(Jn.class, "spawner_minecart", 0.5F);
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      wE renderminecartmobspawner = new wE(rendermanager);
      if (!bnK.RenderMinecart_modelMinecart.exists()) {
         XH.warn("Field not found: RenderMinecart.modelMinecart");
         return null;
      } else {
         bnK.setFieldValue(renderminecartmobspawner, bnK.RenderMinecart_modelMinecart, modelBase);
         renderminecartmobspawner.shadowSize = shadowSize;
         return renderminecartmobspawner;
      }
   }
}
