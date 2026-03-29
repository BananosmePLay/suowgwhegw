package neo;

public class blm extends bkn {
   public blm() {
      super(YN.class, "shulker_box", 0.0F);
   }

   public nH makeModel() {
      return new oA();
   }

   public ow getModelRenderer(nH model, String modelPart) {
      if (!(model instanceof oA)) {
         return null;
      } else {
         oA modelshulker = (oA)model;
         if (modelPart.equals("head")) {
            return modelshulker.head;
         } else if (modelPart.equals("base")) {
            return modelshulker.base;
         } else {
            return modelPart.equals("lid") ? modelshulker.lid : null;
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"base", "lid", "head"};
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      zz tileentityrendererdispatcher = zz.instance;
      zF tileentityspecialrenderer = tileentityrendererdispatcher.getRenderer(YN.class);
      if (!(tileentityspecialrenderer instanceof zB)) {
         return null;
      } else {
         if (((zF)tileentityspecialrenderer).getEntityClass() == null) {
            tileentityspecialrenderer = new zB((oA)modelBase);
            ((zF)tileentityspecialrenderer).setRendererDispatcher(tileentityrendererdispatcher);
         }

         if (!bnK.TileEntityShulkerBoxRenderer_model.exists()) {
            XH.warn("Field not found: TileEntityShulkerBoxRenderer.model");
            return null;
         } else {
            bnK.setFieldValue(tileentityspecialrenderer, bnK.TileEntityShulkerBoxRenderer_model, modelBase);
            return (bkm)tileentityspecialrenderer;
         }
      }
   }
}
