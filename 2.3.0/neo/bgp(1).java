package neo;

import java.util.Random;

public class bgp implements bgv {
   private final bhy[] properties;
   private final bhf target;

   public bgp(bhy[] propertiesIn, bhf targetIn) {
      this.properties = propertiesIn;
      this.target = targetIn;
   }

   public boolean testCondition(Random rand, bhg context) {
      Ig entity = context.getEntity(this.target);
      if (entity == null) {
         return false;
      } else {
         bhy[] var4 = this.properties;
         int var5 = var4.length;

         for(int var6 = 0; var6 < var5; ++var6) {
            bhy entityproperty = var4[var6];
            if (!entityproperty.testProperty(rand, entity)) {
               return false;
            }
         }

         return true;
      }
   }

   // $FF: synthetic method
   static bhy[] access$000(bgp x0) {
      return x0.properties;
   }

   // $FF: synthetic method
   static bhf access$100(bgp x0) {
      return x0.target;
   }
}
