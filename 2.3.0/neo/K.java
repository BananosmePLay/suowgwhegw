package neo;

import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import java.util.Map;
import net.minecraft.util.ResourceLocation;

public class K implements ci<I> {
   private static final ResourceLocation ID = new ResourceLocation("effects_changed");
   private final Map<cl, J> listeners = Maps.newHashMap();

   public K() {
   }

   public ResourceLocation getId() {
      return ID;
   }

   public void addListener(cl playerAdvancementsIn, ch<I> listener) {
      J effectschangedtrigger$listeners = (J)this.listeners.get(playerAdvancementsIn);
      if (effectschangedtrigger$listeners == null) {
         effectschangedtrigger$listeners = new J(playerAdvancementsIn);
         this.listeners.put(playerAdvancementsIn, effectschangedtrigger$listeners);
      }

      effectschangedtrigger$listeners.add(listener);
   }

   public void removeListener(cl playerAdvancementsIn, ch<I> listener) {
      J effectschangedtrigger$listeners = (J)this.listeners.get(playerAdvancementsIn);
      if (effectschangedtrigger$listeners != null) {
         effectschangedtrigger$listeners.remove(listener);
         if (effectschangedtrigger$listeners.isEmpty()) {
            this.listeners.remove(playerAdvancementsIn);
         }
      }

   }

   public void removeAllListeners(cl playerAdvancementsIn) {
      this.listeners.remove(playerAdvancementsIn);
   }

   public I deserializeInstance(JsonObject json, JsonDeserializationContext context) {
      bo mobeffectspredicate = bo.deserialize(json.get("effects"));
      return new I(mobeffectspredicate);
   }

   public void trigger(MG player) {
      J effectschangedtrigger$listeners = (J)this.listeners.get(player.getAdvancements());
      if (effectschangedtrigger$listeners != null) {
         effectschangedtrigger$listeners.trigger(player);
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
