package neo;

import net.minecraft.util.ResourceLocation;

public interface ne {
   ResourceLocation TEXTURE_TOASTS = new ResourceLocation("textures/gui/toasts.png");
   Object NO_TOKEN = new Object();

   nd draw(nc var1, long var2);

   default Object getType() {
      return NO_TOKEN;
   }
}
