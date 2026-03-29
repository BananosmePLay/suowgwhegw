package neo;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import net.minecraft.util.ResourceLocation;

public abstract class bhx<T extends bhy> {
   private final ResourceLocation name;
   private final Class<T> propertyClass;

   protected bhx(ResourceLocation nameIn, Class<T> propertyClassIn) {
      this.name = nameIn;
      this.propertyClass = propertyClassIn;
   }

   public ResourceLocation getName() {
      return this.name;
   }

   public Class<T> getPropertyClass() {
      return this.propertyClass;
   }

   public abstract JsonElement serialize(T var1, JsonSerializationContext var2);

   public abstract T deserialize(JsonElement var1, JsonDeserializationContext var2);
}
