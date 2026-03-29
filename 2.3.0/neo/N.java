package neo;

import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import java.util.Map;
import net.minecraft.util.ResourceLocation;

public class N implements ci<L> {
   private static final ResourceLocation ID = new ResourceLocation("enchanted_item");
   private final Map<cl, M> listeners = Maps.newHashMap();

   public N() {
   }

   public ResourceLocation getId() {
      return ID;
   }

   public void addListener(cl playerAdvancementsIn, ch<L> listener) {
      M enchanteditemtrigger$listeners = (M)this.listeners.get(playerAdvancementsIn);
      if (enchanteditemtrigger$listeners == null) {
         enchanteditemtrigger$listeners = new M(playerAdvancementsIn);
         this.listeners.put(playerAdvancementsIn, enchanteditemtrigger$listeners);
      }

      enchanteditemtrigger$listeners.add(listener);
   }

   public void removeListener(cl playerAdvancementsIn, ch<L> listener) {
      M enchanteditemtrigger$listeners = (M)this.listeners.get(playerAdvancementsIn);
      if (enchanteditemtrigger$listeners != null) {
         enchanteditemtrigger$listeners.remove(listener);
         if (enchanteditemtrigger$listeners.isEmpty()) {
            this.listeners.remove(playerAdvancementsIn);
         }
      }

   }

   public void removeAllListeners(cl playerAdvancementsIn) {
      this.listeners.remove(playerAdvancementsIn);
   }

   public L deserializeInstance(JsonObject json, JsonDeserializationContext context) {
      be itempredicate = be.deserialize(json.get("item"));
      bm minmaxbounds = bm.deserialize(json.get("levels"));
      return new L(itempredicate, minmaxbounds);
   }

   public void trigger(MG player, Qy item, int levelsSpent) {
      M enchanteditemtrigger$listeners = (M)this.listeners.get(player.getAdvancements());
      if (enchanteditemtrigger$listeners != null) {
         enchanteditemtrigger$listeners.trigger(item, levelsSpent);
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
