package neo;

import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import java.util.Map;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

public class ba implements ci<Y> {
   private static final ResourceLocation ID = new ResourceLocation("inventory_changed");
   private final Map<cl, Z> listeners = Maps.newHashMap();

   public ba() {
   }

   public ResourceLocation getId() {
      return ID;
   }

   public void addListener(cl playerAdvancementsIn, ch<Y> listener) {
      Z inventorychangetrigger$listeners = (Z)this.listeners.get(playerAdvancementsIn);
      if (inventorychangetrigger$listeners == null) {
         inventorychangetrigger$listeners = new Z(playerAdvancementsIn);
         this.listeners.put(playerAdvancementsIn, inventorychangetrigger$listeners);
      }

      inventorychangetrigger$listeners.add(listener);
   }

   public void removeListener(cl playerAdvancementsIn, ch<Y> listener) {
      Z inventorychangetrigger$listeners = (Z)this.listeners.get(playerAdvancementsIn);
      if (inventorychangetrigger$listeners != null) {
         inventorychangetrigger$listeners.remove(listener);
         if (inventorychangetrigger$listeners.isEmpty()) {
            this.listeners.remove(playerAdvancementsIn);
         }
      }

   }

   public void removeAllListeners(cl playerAdvancementsIn) {
      this.listeners.remove(playerAdvancementsIn);
   }

   public Y deserializeInstance(JsonObject json, JsonDeserializationContext context) {
      JsonObject jsonobject = JsonUtils.getJsonObject(json, "slots", new JsonObject());
      bm minmaxbounds = bm.deserialize(jsonobject.get("occupied"));
      bm minmaxbounds1 = bm.deserialize(jsonobject.get("full"));
      bm minmaxbounds2 = bm.deserialize(jsonobject.get("empty"));
      be[] aitempredicate = be.deserializeArray(json.get("items"));
      return new Y(minmaxbounds, minmaxbounds1, minmaxbounds2, aitempredicate);
   }

   public void trigger(MG player, MJ inventory) {
      Z inventorychangetrigger$listeners = (Z)this.listeners.get(player.getAdvancements());
      if (inventorychangetrigger$listeners != null) {
         inventorychangetrigger$listeners.trigger(inventory);
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
