package neo;

public class blo extends bkn {
   public blo() {
      super(YQ.class, "sign", 0.0F);
   }

   public nH makeModel() {
      return new oC();
   }

   public ow getModelRenderer(nH model, String modelPart) {
      if (!(model instanceof oC)) {
         return null;
      } else {
         oC modelsign = (oC)model;
         if (modelPart.equals("board")) {
            return modelsign.signBoard;
         } else {
            return modelPart.equals("stick") ? modelsign.signStick : null;
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"board", "stick"};
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      zz tileentityrendererdispatcher = zz.instance;
      zF tileentityspecialrenderer = tileentityrendererdispatcher.getRenderer(YQ.class);
      if (!(tileentityspecialrenderer instanceof zC)) {
         return null;
      } else {
         if (((zF)tileentityspecialrenderer).getEntityClass() == null) {
            tileentityspecialrenderer = new zC();
            ((zF)tileentityspecialrenderer).setRendererDispatcher(tileentityrendererdispatcher);
         }

         if (!bnK.TileEntitySignRenderer_model.exists()) {
            XH.warn("Field not found: TileEntitySignRenderer.model");
            return null;
         } else {
            bnK.setFieldValue(tileentityspecialrenderer, bnK.TileEntitySignRenderer_model, modelBase);
            return (bkm)tileentityspecialrenderer;
         }
      }
   }
}
