package neo;

public class bkX extends bkn {
   public bkX() {
      super(Jc.class, "minecart", 0.5F);
   }

   protected bkX(Class entityClass, String name, float shadow) {
      super(entityClass, name, shadow);
   }

   public nH makeModel() {
      return new on();
   }

   public ow getModelRenderer(nH model, String modelPart) {
      if (!(model instanceof on)) {
         return null;
      } else {
         on modelminecart = (on)model;
         if (modelPart.equals("bottom")) {
            return modelminecart.sideModels[0];
         } else if (modelPart.equals("back")) {
            return modelminecart.sideModels[1];
         } else if (modelPart.equals("front")) {
            return modelminecart.sideModels[2];
         } else if (modelPart.equals("right")) {
            return modelminecart.sideModels[3];
         } else if (modelPart.equals("left")) {
            return modelminecart.sideModels[4];
         } else {
            return modelPart.equals("dirt") ? modelminecart.sideModels[5] : null;
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"bottom", "back", "front", "right", "left", "dirt"};
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      wD renderminecart = new wD(rendermanager);
      if (!bnK.RenderMinecart_modelMinecart.exists()) {
         XH.warn("Field not found: RenderMinecart.modelMinecart");
         return null;
      } else {
         bnK.setFieldValue(renderminecart, bnK.RenderMinecart_modelMinecart, modelBase);
         renderminecart.shadowSize = shadowSize;
         return renderminecart;
      }
   }
}
