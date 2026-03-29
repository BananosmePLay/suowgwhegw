package neo;

public class bkv extends bkn {
   public bkv() {
      super(Yv.class, "book", 0.0F);
   }

   public nH makeModel() {
      return new nP();
   }

   public ow getModelRenderer(nH model, String modelPart) {
      if (!(model instanceof nP)) {
         return null;
      } else {
         nP modelbook = (nP)model;
         if (modelPart.equals("cover_right")) {
            return modelbook.coverRight;
         } else if (modelPart.equals("cover_left")) {
            return modelbook.coverLeft;
         } else if (modelPart.equals("pages_right")) {
            return modelbook.pagesRight;
         } else if (modelPart.equals("pages_left")) {
            return modelbook.pagesLeft;
         } else if (modelPart.equals("flipping_page_right")) {
            return modelbook.flippingPageRight;
         } else if (modelPart.equals("flipping_page_left")) {
            return modelbook.flippingPageLeft;
         } else {
            return modelPart.equals("book_spine") ? modelbook.bookSpine : null;
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"cover_right", "cover_left", "pages_right", "pages_left", "flipping_page_right", "flipping_page_left", "book_spine"};
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      zz tileentityrendererdispatcher = zz.instance;
      zF tileentityspecialrenderer = tileentityrendererdispatcher.getRenderer(Yv.class);
      if (!(tileentityspecialrenderer instanceof zs)) {
         return null;
      } else {
         if (((zF)tileentityspecialrenderer).getEntityClass() == null) {
            tileentityspecialrenderer = new zs();
            ((zF)tileentityspecialrenderer).setRendererDispatcher(tileentityrendererdispatcher);
         }

         if (!bnK.TileEntityEnchantmentTableRenderer_modelBook.exists()) {
            XH.warn("Field not found: TileEntityEnchantmentTableRenderer.modelBook");
            return null;
         } else {
            bnK.setFieldValue(tileentityspecialrenderer, bnK.TileEntityEnchantmentTableRenderer_modelBook, modelBase);
            return (bkm)tileentityspecialrenderer;
         }
      }
   }
}
