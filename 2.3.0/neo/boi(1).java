package neo;

import java.util.Map;

public class boi implements blZ {
   private Map<String, String> mapMacroValues = null;

   public boi(Map<String, String> mapMacroValues) {
      this.mapMacroValues = mapMacroValues;
   }

   public blU getExpression(String name) {
      String s = "defined_";
      String s1;
      if (name.startsWith(s)) {
         s1 = name.substring(s.length());
         return this.mapMacroValues.containsKey(s1) ? new blO(blT.TRUE, (blU[])null) : new blO(blT.FALSE, (blU[])null);
      } else {
         while(this.mapMacroValues.containsKey(name)) {
            s1 = (String)this.mapMacroValues.get(name);
            if (s1 == null || s1.equals(name)) {
               break;
            }

            name = s1;
         }

         int i = XH.parseInt(name, Integer.MIN_VALUE);
         if (i == Integer.MIN_VALUE) {
            XH.warn("Unknown macro value: " + name);
            return new blI(0.0F);
         } else {
            return new blI((float)i);
         }
      }
   }
}
