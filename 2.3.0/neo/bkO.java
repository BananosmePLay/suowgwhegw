package neo;

public class bkO extends bkn {
   public bkO() {
      super(YR.class, "head_humanoid", 0.0F);
   }

   public nH makeModel() {
      return new of();
   }

   public ow getModelRenderer(nH model, String modelPart) {
      if (!(model instanceof of)) {
         return null;
      } else {
         of modelhumanoidhead = (of)model;
         if (modelPart.equals("head")) {
            return modelhumanoidhead.skeletonHead;
         } else if (modelPart.equals("head2")) {
            return !bnK.ModelHumanoidHead_head.exists() ? null : (ow)bnK.getFieldValue(modelhumanoidhead, bnK.ModelHumanoidHead_head);
         } else {
            return null;
         }
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
