package neo;

import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import java.util.Map;
import net.minecraft.util.ResourceLocation;

public class B implements ci<z> {
   private static final ResourceLocation ID = new ResourceLocation("consume_item");
   private final Map<cl, A> listeners = Maps.newHashMap();

   public B() {
   }

   public ResourceLocation getId() {
      return ID;
   }

   public void addListener(cl playerAdvancementsIn, ch<z> listener) {
      A consumeitemtrigger$listeners = (A)this.listeners.get(playerAdvancementsIn);
      if (consumeitemtrigger$listeners == null) {
         consumeitemtrigger$listeners = new A(playerAdvancementsIn);
         this.listeners.put(playerAdvancementsIn, consumeitemtrigger$listeners);
      }

      consumeitemtrigger$listeners.add(listener);
   }

   public void removeListener(cl playerAdvancementsIn, ch<z> listener) {
      A consumeitemtrigger$listeners = (A)this.listeners.get(playerAdvancementsIn);
      if (consumeitemtrigger$listeners != null) {
         consumeitemtrigger$listeners.remove(listener);
         if (consumeitemtrigger$listeners.isEmpty()) {
            this.listeners.remove(playerAdvancementsIn);
         }
      }

   }

   public void removeAllListeners(cl playerAdvancementsIn) {
      this.listeners.remove(playerAdvancementsIn);
   }

   public z deserializeInstance(JsonObject json, JsonDeserializationContext context) {
      be itempredicate = be.deserialize(json.get("item"));
      return new z(itempredicate);
   }

   public void trigger(MG player, Qy item) {
      A consumeitemtrigger$listeners = (A)this.listeners.get(player.getAdvancements());
      if (consumeitemtrigger$listeners != null) {
         consumeitemtrigger$listeners.trigger(item);
      }

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
