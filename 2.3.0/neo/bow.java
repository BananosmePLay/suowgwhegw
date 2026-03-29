package neo;

import java.util.HashMap;
import java.util.Map;

public class bow implements blZ {
   private Map<String, boh> mapOptions = new HashMap();

   public bow(bou[] options) {
      for(int i = 0; i < options.length; ++i) {
         bou shaderoption = options[i];
         if (shaderoption instanceof boz) {
            boz shaderoptionswitch = (boz)shaderoption;
            boh expressionshaderoptionswitch = new boh(shaderoptionswitch);
            this.mapOptions.put(shaderoption.getName(), expressionshaderoptionswitch);
         }
      }

   }

   public blU getExpression(String name) {
      boh expressionshaderoptionswitch = (boh)this.mapOptions.get(name);
      return expressionshaderoptionswitch;
   }
}
