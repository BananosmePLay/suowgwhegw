package neo;

import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;

public class P extends m {
   private final co block;
   private final Map<hT<?>, Object> properties;

   public P(@Nullable co blockIn, @Nullable Map<hT<?>, Object> propertiesIn) {
      super(R.access$000());
      this.block = blockIn;
      this.properties = propertiesIn;
   }

   public boolean test(in state) {
      if (this.block != null && state.getBlock() != this.block) {
         return false;
      } else {
         if (this.properties != null) {
            Iterator var2 = this.properties.entrySet().iterator();

            while(var2.hasNext()) {
               Map.Entry<hT<?>, Object> entry = (Map.Entry)var2.next();
               if (state.getValue((hT)entry.getKey()) != entry.getValue()) {
                  return false;
               }
            }
         }

         return true;
      }
   }
}
