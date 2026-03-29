package neo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class bpD implements blZ {
   private Map<String, blU> mapExpressions = new HashMap();

   public bpD(Map<String, blU> map) {
      this.registerExpressions();
      Iterator var2 = map.keySet().iterator();

      while(var2.hasNext()) {
         String s = (String)var2.next();
         blU iexpression = (blU)map.get(s);
         this.registerExpression(s, iexpression);
      }

   }

   private void registerExpressions() {
      bpH[] ashaderparameterfloat = bpH.values();

      for(int i = 0; i < ashaderparameterfloat.length; ++i) {
         bpH shaderparameterfloat = ashaderparameterfloat[i];
         this.addParameterFloat(this.mapExpressions, shaderparameterfloat);
      }

      bpF[] ashaderparameterbool = bpF.values();

      for(int k = 0; k < ashaderparameterbool.length; ++k) {
         bpF shaderparameterbool = ashaderparameterbool[k];
         this.mapExpressions.put(shaderparameterbool.getName(), shaderparameterbool);
      }

      Iterator var10 = Zi.REGISTRY.iterator();

      while(var10.hasNext()) {
         Zi biome = (Zi)var10.next();
         String s = biome.getBiomeName().trim();
         s = "BIOME_" + s.toUpperCase().replace(' ', '_');
         int j = Zi.getIdForBiome(biome);
         blU iexpression = new blI((float)j);
         this.registerExpression(s, iexpression);
      }

   }

   private void addParameterFloat(Map<String, blU> map, bpH spf) {
      String[] astring = spf.getIndexNames1();
      if (astring == null) {
         map.put(spf.getName(), new bpI(spf));
      } else {
         for(int i = 0; i < astring.length; ++i) {
            String s = astring[i];
            String[] astring1 = spf.getIndexNames2();
            if (astring1 == null) {
               map.put(spf.getName() + "." + s, new bpI(spf, i));
            } else {
               for(int j = 0; j < astring1.length; ++j) {
                  String s1 = astring1[j];
                  map.put(spf.getName() + "." + s + "." + s1, new bpI(spf, i, j));
               }
            }
         }
      }

   }

   public boolean registerExpression(String name, blU expr) {
      if (this.mapExpressions.containsKey(name)) {
         bpx.warning("Expression already defined: " + name);
         return false;
      } else {
         this.mapExpressions.put(name, expr);
         return true;
      }
   }

   public blU getExpression(String name) {
      return (blU)this.mapExpressions.get(name);
   }

   public boolean hasExpression(String name) {
      return this.mapExpressions.containsKey(name);
   }
}
