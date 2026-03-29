package neo;

public class bkr extends bkn {
   public bkr() {
      super(Yk.class, "bed", 0.0F);
   }

   public nH makeModel() {
      return new nJ();
   }

   public ow getModelRenderer(nH model, String modelPart) {
      if (!(model instanceof nJ)) {
         return null;
      } else {
         nJ modelbed = (nJ)model;
         if (modelPart.equals("head")) {
            return modelbed.headPiece;
         } else if (modelPart.equals("foot")) {
            return modelbed.footPiece;
         } else if (modelPart.equals("leg1")) {
            return modelbed.legs[0];
         } else if (modelPart.equals("leg2")) {
            return modelbed.legs[1];
         } else if (modelPart.equals("leg3")) {
            return modelbed.legs[2];
         } else {
            return modelPart.equals("leg4") ? modelbed.legs[3] : null;
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"head", "foot", "leg1", "leg2", "leg3", "leg4"};
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      zz tileentityrendererdispatcher = zz.instance;
      zF tileentityspecialrenderer = tileentityrendererdispatcher.getRenderer(Yk.class);
      if (!(tileentityspecialrenderer instanceof zq)) {
         return null;
      } else {
         if (((zF)tileentityspecialrenderer).getEntityClass() == null) {
            tileentityspecialrenderer = new zq();
            ((zF)tileentityspecialrenderer).setRendererDispatcher(tileentityrendererdispatcher);
         }

         if (!bnK.TileEntityBedRenderer_model.exists()) {
            XH.warn("Field not found: TileEntityBedRenderer.model");
            return null;
         } else {
            bnK.setFieldValue(tileentityspecialrenderer, bnK.TileEntityBedRenderer_model, modelBase);
            return (bkm)tileentityspecialrenderer;
         }
      }
   }
}
