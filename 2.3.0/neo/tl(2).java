package neo;

import net.minecraft.util.ResourceLocation;

public class tl extends tr {
   public tl() {
   }

   protected sD getModelResourceLocation(in state) {
      return new sD((ResourceLocation)co.REGISTRY.getNameForObject(state.getBlock()), this.getPropertyString(state.getProperties()));
   }
}
