package neo;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.util.ResourceLocation;

public interface ci<T extends cg> {
   ResourceLocation getId();

   void addListener(cl var1, ch<T> var2);

   void removeListener(cl var1, ch<T> var2);

   void removeAllListeners(cl var1);

   T deserializeInstance(JsonObject var1, JsonDeserializationContext var2);
}
