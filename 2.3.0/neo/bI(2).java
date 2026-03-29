package neo;

import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import java.util.Map;
import net.minecraft.util.ResourceLocation;

public class bI implements ci<bG> {
   private static final ResourceLocation ID = new ResourceLocation("summoned_entity");
   private final Map<cl, bH> listeners = Maps.newHashMap();

   public bI() {
   }

   public ResourceLocation getId() {
      return ID;
   }

   public void addListener(cl playerAdvancementsIn, ch<bG> listener) {
      bH summonedentitytrigger$listeners = (bH)this.listeners.get(playerAdvancementsIn);
      if (summonedentitytrigger$listeners == null) {
         summonedentitytrigger$listeners = new bH(playerAdvancementsIn);
         this.listeners.put(playerAdvancementsIn, summonedentitytrigger$listeners);
      }

      summonedentitytrigger$listeners.add(listener);
   }

   public void removeListener(cl playerAdvancementsIn, ch<bG> listener) {
      bH summonedentitytrigger$listeners = (bH)this.listeners.get(playerAdvancementsIn);
      if (summonedentitytrigger$listeners != null) {
         summonedentitytrigger$listeners.remove(listener);
         if (summonedentitytrigger$listeners.isEmpty()) {
            this.listeners.remove(playerAdvancementsIn);
         }
      }

   }

   public void removeAllListeners(cl playerAdvancementsIn) {
      this.listeners.remove(playerAdvancementsIn);
   }

   public bG deserializeInstance(JsonObject json, JsonDeserializationContext context) {
      V entitypredicate = V.deserialize(json.get("entity"));
      return new bG(entitypredicate);
   }

   public void trigger(MG player, Ig entity) {
      bH summonedentitytrigger$listeners = (bH)this.listeners.get(player.getAdvancements());
      if (summonedentitytrigger$listeners != null) {
         summonedentitytrigger$listeners.trigger(player, entity);
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
