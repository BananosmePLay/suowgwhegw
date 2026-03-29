package neo;

import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import java.util.Map;
import net.minecraft.util.ResourceLocation;

public class bU implements ci<bS> {
   private static final ResourceLocation ID = new ResourceLocation("used_totem");
   private final Map<cl, bT> listeners = Maps.newHashMap();

   public bU() {
   }

   public ResourceLocation getId() {
      return ID;
   }

   public void addListener(cl playerAdvancementsIn, ch<bS> listener) {
      bT usedtotemtrigger$listeners = (bT)this.listeners.get(playerAdvancementsIn);
      if (usedtotemtrigger$listeners == null) {
         usedtotemtrigger$listeners = new bT(playerAdvancementsIn);
         this.listeners.put(playerAdvancementsIn, usedtotemtrigger$listeners);
      }

      usedtotemtrigger$listeners.add(listener);
   }

   public void removeListener(cl playerAdvancementsIn, ch<bS> listener) {
      bT usedtotemtrigger$listeners = (bT)this.listeners.get(playerAdvancementsIn);
      if (usedtotemtrigger$listeners != null) {
         usedtotemtrigger$listeners.remove(listener);
         if (usedtotemtrigger$listeners.isEmpty()) {
            this.listeners.remove(playerAdvancementsIn);
         }
      }

   }

   public void removeAllListeners(cl playerAdvancementsIn) {
      this.listeners.remove(playerAdvancementsIn);
   }

   public bS deserializeInstance(JsonObject json, JsonDeserializationContext context) {
      be itempredicate = be.deserialize(json.get("item"));
      return new bS(itempredicate);
   }

   public void trigger(MG player, Qy item) {
      bT usedtotemtrigger$listeners = (bT)this.listeners.get(player.getAdvancements());
      if (usedtotemtrigger$listeners != null) {
         usedtotemtrigger$listeners.trigger(item);
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
