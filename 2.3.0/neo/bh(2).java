package neo;

import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import java.util.Map;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;

public class bh implements ci<bf> {
   private final Map<cl, bg> listeners = Maps.newHashMap();
   private final ResourceLocation id;

   public bh(ResourceLocation id) {
      this.id = id;
   }

   public ResourceLocation getId() {
      return this.id;
   }

   public void addListener(cl playerAdvancementsIn, ch<bf> listener) {
      bg killedtrigger$listeners = (bg)this.listeners.get(playerAdvancementsIn);
      if (killedtrigger$listeners == null) {
         killedtrigger$listeners = new bg(playerAdvancementsIn);
         this.listeners.put(playerAdvancementsIn, killedtrigger$listeners);
      }

      killedtrigger$listeners.add(listener);
   }

   public void removeListener(cl playerAdvancementsIn, ch<bf> listener) {
      bg killedtrigger$listeners = (bg)this.listeners.get(playerAdvancementsIn);
      if (killedtrigger$listeners != null) {
         killedtrigger$listeners.remove(listener);
         if (killedtrigger$listeners.isEmpty()) {
            this.listeners.remove(playerAdvancementsIn);
         }
      }

   }

   public void removeAllListeners(cl playerAdvancementsIn) {
      this.listeners.remove(playerAdvancementsIn);
   }

   public bf deserializeInstance(JsonObject json, JsonDeserializationContext context) {
      return new bf(this.id, V.deserialize(json.get("entity")), G.deserialize(json.get("killing_blow")));
   }

   public void trigger(MG player, Ig entity, DamageSource source) {
      bg killedtrigger$listeners = (bg)this.listeners.get(player.getAdvancements());
      if (killedtrigger$listeners != null) {
         killedtrigger$listeners.trigger(player, entity, source);
      }

   }

   // $FF: synthetic method
   // $FF: bridge method
   public cg deserializeInstance(JsonObject var1, JsonDeserializationContext var2) {
      return this.deserializeInstance(var1, var2);
   }
}
