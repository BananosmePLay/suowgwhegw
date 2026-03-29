package neo;

public class bkp extends bkn {
   public bkp() {
      super(Yh.class, "banner", 0.0F);
   }

   public nH makeModel() {
      return new nG();
   }

   public ow getModelRenderer(nH model, String modelPart) {
      if (!(model instanceof nG)) {
         return null;
      } else {
         nG modelbanner = (nG)model;
         if (modelPart.equals("slate")) {
            return modelbanner.bannerSlate;
         } else if (modelPart.equals("stand")) {
            return modelbanner.bannerStand;
         } else {
            return modelPart.equals("top") ? modelbanner.bannerTop : null;
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"slate", "stand", "top"};
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      zz tileentityrendererdispatcher = zz.instance;
      zF tileentityspecialrenderer = tileentityrendererdispatcher.getRenderer(Yh.class);
      if (!(tileentityspecialrenderer instanceof zo)) {
         return null;
      } else {
         if (((zF)tileentityspecialrenderer).getEntityClass() == null) {
            tileentityspecialrenderer = new zo();
            ((zF)tileentityspecialrenderer).setRendererDispatcher(tileentityrendererdispatcher);
         }

         if (!bnK.TileEntityBannerRenderer_bannerModel.exists()) {
            XH.warn("Field not found: TileEntityBannerRenderer.bannerModel");
            return null;
         } else {
            bnK.setFieldValue(tileentityspecialrenderer, bnK.TileEntityBannerRenderer_bannerModel, modelBase);
            return (bkm)tileentityspecialrenderer;
         }
      }
   }
}
