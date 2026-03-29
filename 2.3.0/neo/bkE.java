package neo;

public class bkE extends bkn {
   public bkE() {
      super(Yw.class, "ender_chest", 0.0F);
   }

   public nH makeModel() {
      return new nR();
   }

   public ow getModelRenderer(nH model, String modelPart) {
      if (!(model instanceof nR)) {
         return null;
      } else {
         nR modelchest = (nR)model;
         if (modelPart.equals("lid")) {
            return modelchest.chestLid;
         } else if (modelPart.equals("base")) {
            return modelchest.chestBelow;
         } else {
            return modelPart.equals("knob") ? modelchest.chestKnob : null;
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"lid", "base", "knob"};
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      zz tileentityrendererdispatcher = zz.instance;
      zF tileentityspecialrenderer = tileentityrendererdispatcher.getRenderer(Yw.class);
      if (!(tileentityspecialrenderer instanceof zt)) {
         return null;
      } else {
         if (((zF)tileentityspecialrenderer).getEntityClass() == null) {
            tileentityspecialrenderer = new zt();
            ((zF)tileentityspecialrenderer).setRendererDispatcher(tileentityrendererdispatcher);
         }

         if (!bnK.TileEntityEnderChestRenderer_modelChest.exists()) {
            XH.warn("Field not found: TileEntityEnderChestRenderer.modelChest");
            return null;
         } else {
            bnK.setFieldValue(tileentityspecialrenderer, bnK.TileEntityEnderChestRenderer_modelChest, modelBase);
            return (bkm)tileentityspecialrenderer;
         }
      }
   }
}
