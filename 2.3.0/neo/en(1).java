package neo;

import com.google.common.base.Predicate;
import net.minecraft.util.EnumFacing;

public abstract class en extends co {
   public static final hW FACING;

   protected en(hM materialIn) {
      super(materialIn);
   }

   protected en(hM materialIn, hK colorIn) {
      super(materialIn, colorIn);
   }

   static {
      FACING = hW.create("facing", (Predicate)EnumFacing.Plane.HORIZONTAL);
   }
}
