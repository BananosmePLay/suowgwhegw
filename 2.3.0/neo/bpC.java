package neo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class bpC {
   private bpB[] uniforms;
   private blW[] expressionsCached;

   public bpC(bpB[] uniforms, Map<String, blU> mapExpressions) {
      this.uniforms = uniforms;
      List<blW> list = new ArrayList();
      Iterator var4 = mapExpressions.keySet().iterator();

      while(var4.hasNext()) {
         String s = (String)var4.next();
         blU iexpression = (blU)mapExpressions.get(s);
         if (iexpression instanceof blW) {
            blW iexpressioncached = (blW)iexpression;
            list.add(iexpressioncached);
         }
      }

      this.expressionsCached = (blW[])((blW[])list.toArray(new blW[list.size()]));
   }

   public void setProgram(int program) {
      for(int i = 0; i < this.uniforms.length; ++i) {
         bpB customuniform = this.uniforms[i];
         customuniform.setProgram(program);
      }

   }

   public void update() {
      this.resetCache();

      for(int i = 0; i < this.uniforms.length; ++i) {
         bpB customuniform = this.uniforms[i];
         customuniform.update();
      }

   }

   private void resetCache() {
      for(int i = 0; i < this.expressionsCached.length; ++i) {
         blW iexpressioncached = this.expressionsCached[i];
         iexpressioncached.reset();
      }

   }

   public void reset() {
      for(int i = 0; i < this.uniforms.length; ++i) {
         bpB customuniform = this.uniforms[i];
         customuniform.reset();
      }

   }
}
