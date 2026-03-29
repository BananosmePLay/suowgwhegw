package neo;

import net.minecraft.util.ResourceLocation;

public interface biH<T> {
   T get();

   ResourceLocation name();

   Class<T> type();
}
