package neo;

import net.minecraft.util.ResourceLocation;

public interface biG<T> {
   T get();

   ResourceLocation name();

   Class<T> type();
}
