package neo;

import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import java.util.Map;
import net.minecraft.util.ResourceLocation;

public class bX implements ci<bV> {
   private static final ResourceLocation ID = new ResourceLocation("villager_trade");
   private final Map<cl, bW> listeners = Maps.newHashMap();

   public bX() {
   }

   public ResourceLocation getId() {
      return ID;
   }

   public void addListener(cl playerAdvancementsIn, ch<bV> listener) {
      bW villagertradetrigger$listeners = (bW)this.listeners.get(playerAdvancementsIn);
      if (villagertradetrigger$listeners == null) {
         villagertradetrigger$listeners = new bW(playerAdvancementsIn);
         this.listeners.put(playerAdvancementsIn, villagertradetrigger$listeners);
      }

      villagertradetrigger$listeners.add(listener);
   }

   public void removeListener(cl playerAdvancementsIn, ch<bV> listener) {
      bW villagertradetrigger$listeners = (bW)this.listeners.get(playerAdvancementsIn);
      if (villagertradetrigger$listeners != null) {
         villagertradetrigger$listeners.remove(listener);
         if (villagertradetrigger$listeners.isEmpty()) {
            this.listeners.remove(playerAdvancementsIn);
         }
      }

   }

   public void removeAllListeners(cl playerAdvancementsIn) {
      this.listeners.remove(playerAdvancementsIn);
   }

   public bV deserializeInstance(JsonObject json, JsonDeserializationContext context) {
      V entitypredicate = V.deserialize(json.get("villager"));
      be itempredicate = be.deserialize(json.get("item"));
      return new bV(entitypredicate, itempredicate);
   }

   public void trigger(MG player, Mq villager, Qy item) {
      bW villagertradetrigger$listeners = (bW)this.listeners.get(player.getAdvancements());
      if (villagertradetrigger$listeners != null) {
         villagertradetrigger$listeners.trigger(player, villager, item);
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
