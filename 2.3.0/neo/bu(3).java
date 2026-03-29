package neo;

import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;

public class bu extends m {
   private final co block;
   private final Map<hT<?>, Object> properties;
   private final bl location;
   private final be item;

   public bu(@Nullable co block, @Nullable Map<hT<?>, Object> propertiesIn, bl locationIn, be itemIn) {
      super(bw.access$000());
      this.block = block;
      this.properties = propertiesIn;
      this.location = locationIn;
      this.item = itemIn;
   }

   public boolean test(in state, BlockPos pos, bis world, Qy item) {
      if (this.block != null && state.getBlock() != this.block) {
         return false;
      } else {
         if (this.properties != null) {
            Iterator var5 = this.properties.entrySet().iterator();

            while(var5.hasNext()) {
               Map.Entry<hT<?>, Object> entry = (Map.Entry)var5.next();
               if (state.getValue((hT)entry.getKey()) != entry.getValue()) {
                  return false;
               }
            }
         }

         return !this.location.test(world, (float)pos.getX(), (float)pos.getY(), (float)pos.getZ()) ? false : this.item.test(item);
      }
   }
}
