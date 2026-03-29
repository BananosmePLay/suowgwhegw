package neo;

import net.minecraft.util.ResourceLocation;

public class biT implements biW {
   public biT() {
   }

   public Object getObject(ResourceLocation loc) {
      Class oclass = Ir.getClassFromName(loc.toString());
      return oclass;
   }
}
