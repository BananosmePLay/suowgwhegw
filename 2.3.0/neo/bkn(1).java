package neo;

import java.util.ArrayList;
import java.util.List;

public abstract class bkn {
   private Class entityClass;
   private String name;
   private float shadowSize;
   private String[] aliases;

   public bkn(Class entityClass, String name, float shadowSize) {
      this.entityClass = entityClass;
      this.name = name;
      this.shadowSize = shadowSize;
   }

   public bkn(Class entityClass, String name, float shadowSize, String[] aliases) {
      this.entityClass = entityClass;
      this.name = name;
      this.shadowSize = shadowSize;
      this.aliases = aliases;
   }

   public Class getEntityClass() {
      return this.entityClass;
   }

   public String getName() {
      return this.name;
   }

   public String[] getAliases() {
      return this.aliases;
   }

   public float getShadowSize() {
      return this.shadowSize;
   }

   public abstract nH makeModel();

   public abstract ow getModelRenderer(nH var1, String var2);

   public abstract String[] getModelRendererNames();

   public abstract bkm makeEntityRender(nH var1, float var2);

   public ow[] getModelRenderers(nH model) {
      String[] astring = this.getModelRendererNames();
      List<ow> list = new ArrayList();

      for(int i = 0; i < astring.length; ++i) {
         String s = astring[i];
         ow modelrenderer = this.getModelRenderer(model, s);
         if (modelrenderer != null) {
            list.add(modelrenderer);
         }
      }

      ow[] amodelrenderer = (ow[])((ow[])list.toArray(new ow[list.size()]));
      return amodelrenderer;
   }
}
