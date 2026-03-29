package neo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class bnT {
   private static final List<bnI> RESOLVABLES = Collections.synchronizedList(new ArrayList());
   private static boolean resolved = false;

   public bnT() {
   }

   protected static void register(bnI resolvable) {
      if (!resolved) {
         RESOLVABLES.add(resolvable);
      } else {
         resolvable.resolve();
      }

   }

   public static void resolve() {
      if (!resolved) {
         Iterator var0 = RESOLVABLES.iterator();

         while(var0.hasNext()) {
            bnI iresolvable = (bnI)var0.next();
            iresolvable.resolve();
         }

         resolved = true;
      }

   }
}
