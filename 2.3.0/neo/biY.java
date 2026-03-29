package neo;

import net.minecraft.util.ResourceLocation;

public class biY implements biW {
   public biY() {
   }

   public Object getObject(ResourceLocation loc) {
      OL item = OL.getByNameOrId(loc.toString());
      return item;
   }
}
