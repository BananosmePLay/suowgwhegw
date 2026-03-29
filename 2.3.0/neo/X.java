package neo;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.util.ResourceLocation;

public class X implements ci<W> {
   private static final ResourceLocation ID = new ResourceLocation("impossible");

   public X() {
   }

   public ResourceLocation getId() {
      return ID;
   }

   public void addListener(cl playerAdvancementsIn, ch<W> listener) {
   }

   public void removeListener(cl playerAdvancementsIn, ch<W> listener) {
   }

   public void removeAllListeners(cl playerAdvancementsIn) {
   }

   public W deserializeInstance(JsonObject json, JsonDeserializationContext context) {
      return new W();
   }

   // $FF: synthetic method
   // $FF: bridge method
   public cg deserializeInstance(JsonObject var1, JsonDeserializationContext var2) {
      return this.deserializeInstance(var1, var2);
   }

   // $FF: synthetic method
   static ResourceLocation access$000() {
      return ID;
   }
}
