package neo;

public class bjU implements bjS {
   private bkn modelAdapter;
   private nH model;
   private bkl[] customModelRenderers;
   private ow thisModelRenderer;
   private ow partModelRenderer;
   private bjT renderResolver;

   public bjU(bkn modelAdapter, nH model, bkl[] customModelRenderers) {
      this.modelAdapter = modelAdapter;
      this.model = model;
      this.customModelRenderers = customModelRenderers;
      Class oclass = modelAdapter.getEntityClass();
      if (Yg.class.isAssignableFrom(oclass)) {
         this.renderResolver = new bkf();
      } else {
         this.renderResolver = new bke();
      }

   }

   public blU getExpression(String name) {
      blU iexpression = this.getModelVariable(name);
      if (iexpression != null) {
         return iexpression;
      } else {
         blU iexpression1 = this.renderResolver.getParameter(name);
         return iexpression1 != null ? iexpression1 : null;
      }
   }

   public ow getModelRenderer(String name) {
      if (name == null) {
         return null;
      } else {
         ow modelrenderer2;
         if (name.indexOf(":") >= 0) {
            String[] astring = XH.tokenize(name, ":");
            ow modelrenderer3 = this.getModelRenderer(astring[0]);

            for(int j = 1; j < astring.length; ++j) {
               String s = astring[j];
               modelrenderer2 = modelrenderer3.getChildDeep(s);
               if (modelrenderer2 == null) {
                  return null;
               }

               modelrenderer3 = modelrenderer2;
            }

            return modelrenderer3;
         } else if (this.thisModelRenderer != null && name.equals("this")) {
            return this.thisModelRenderer;
         } else if (this.partModelRenderer != null && name.equals("part")) {
            return this.partModelRenderer;
         } else {
            ow modelrenderer = this.modelAdapter.getModelRenderer(this.model, name);
            if (modelrenderer != null) {
               return modelrenderer;
            } else {
               for(int i = 0; i < this.customModelRenderers.length; ++i) {
                  bkl custommodelrenderer = this.customModelRenderers[i];
                  ow modelrenderer1 = custommodelrenderer.getModelRenderer();
                  if (name.equals(modelrenderer1.getId())) {
                     return modelrenderer1;
                  }

                  modelrenderer2 = modelrenderer1.getChildDeep(name);
                  if (modelrenderer2 != null) {
                     return modelrenderer2;
                  }
               }

               return null;
            }
         }
      }
   }

   public bjW getModelVariable(String name) {
      String[] astring = XH.tokenize(name, ".");
      if (astring.length != 2) {
         return null;
      } else {
         String s = astring[0];
         String s1 = astring[1];
         ow modelrenderer = this.getModelRenderer(s);
         if (modelrenderer == null) {
            return null;
         } else {
            bjY modelvariabletype = bjY.parse(s1);
            return modelvariabletype == null ? null : new bjW(name, modelrenderer, modelvariabletype);
         }
      }
   }

   public void setPartModelRenderer(ow partModelRenderer) {
      this.partModelRenderer = partModelRenderer;
   }

   public void setThisModelRenderer(ow thisModelRenderer) {
      this.thisModelRenderer = thisModelRenderer;
   }
}
