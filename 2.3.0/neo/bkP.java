package neo;

public class bkP extends bkn {
   public bkP() {
      super(YR.class, "head_skeleton", 0.0F);
   }

   public nH makeModel() {
      return new oF(0, 0, 64, 32);
   }

   public ow getModelRenderer(nH model, String modelPart) {
      if (!(model instanceof oF)) {
         return null;
      } else {
         oF modelskeletonhead = (oF)model;
         return modelPart.equals("head") ? modelskeletonhead.skeletonHead : null;
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"head"};
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      zz tileentityrendererdispatcher = zz.instance;
      zF tileentityspecialrenderer = tileentityrendererdispatcher.getRenderer(YR.class);
      if (!(tileentityspecialrenderer instanceof zE)) {
         return null;
      } else {
         if (((zF)tileentityspecialrenderer).getEntityClass() == null) {
            tileentityspecialrenderer = new zE();
            ((zF)tileentityspecialrenderer).setRendererDispatcher(tileentityrendererdispatcher);
         }

         if (!bnK.TileEntitySkullRenderer_humanoidHead.exists()) {
            XH.warn("Field not found: TileEntitySkullRenderer.humanoidHead");
            return null;
         } else {
            bnK.setFieldValue(tileentityspecialrenderer, bnK.TileEntitySkullRenderer_humanoidHead, modelBase);
            return (bkm)tileentityspecialrenderer;
         }
      }
   }
}
