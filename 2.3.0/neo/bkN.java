package neo;

public class bkN extends bkn {
   public bkN() {
      super(YR.class, "head_dragon", 0.0F);
   }

   public nH makeModel() {
      return new nW(0.0F);
   }

   public ow getModelRenderer(nH model, String modelPart) {
      if (!(model instanceof nW)) {
         return null;
      } else {
         nW modeldragonhead = (nW)model;
         if (modelPart.equals("head")) {
            return (ow)bnK.getFieldValue(modeldragonhead, bnK.ModelDragonHead_head);
         } else {
            return modelPart.equals("jaw") ? (ow)bnK.getFieldValue(modeldragonhead, bnK.ModelDragonHead_jaw) : null;
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"head", "jaw"};
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

         if (!bnK.TileEntitySkullRenderer_dragonHead.exists()) {
            XH.warn("Field not found: TileEntitySkullRenderer.dragonHead");
            return null;
         } else {
            bnK.setFieldValue(tileentityspecialrenderer, bnK.TileEntitySkullRenderer_dragonHead, modelBase);
            return (bkm)tileentityspecialrenderer;
         }
      }
   }
}
