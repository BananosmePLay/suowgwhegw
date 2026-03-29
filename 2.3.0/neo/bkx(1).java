package neo;

public class bkx extends bkn {
   public bkx() {
      super(Yn.class, "chest", 0.0F);
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
      zF tileentityspecialrenderer = tileentityrendererdispatcher.getRenderer(Yn.class);
      if (!(tileentityspecialrenderer instanceof zr)) {
         return null;
      } else {
         if (((zF)tileentityspecialrenderer).getEntityClass() == null) {
            tileentityspecialrenderer = new zr();
            ((zF)tileentityspecialrenderer).setRendererDispatcher(tileentityrendererdispatcher);
         }

         if (!bnK.TileEntityChestRenderer_simpleChest.exists()) {
            XH.warn("Field not found: TileEntityChestRenderer.simpleChest");
            return null;
         } else {
            bnK.setFieldValue(tileentityspecialrenderer, bnK.TileEntityChestRenderer_simpleChest, modelBase);
            return (bkm)tileentityspecialrenderer;
         }
      }
   }
}
