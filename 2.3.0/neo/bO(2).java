package neo;

import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import java.util.Map;
import net.minecraft.util.ResourceLocation;

public class bO implements ci<bM> {
   public static final ResourceLocation ID = new ResourceLocation("tick");
   private final Map<cl, bN> listeners = Maps.newHashMap();

   public bO() {
   }

   public ResourceLocation getId() {
      return ID;
   }

   public void addListener(cl playerAdvancementsIn, ch<bM> listener) {
      bN ticktrigger$listeners = (bN)this.listeners.get(playerAdvancementsIn);
      if (ticktrigger$listeners == null) {
         ticktrigger$listeners = new bN(playerAdvancementsIn);
         this.listeners.put(playerAdvancementsIn, ticktrigger$listeners);
      }

      ticktrigger$listeners.add(listener);
   }

   public void removeListener(cl playerAdvancementsIn, ch<bM> listener) {
      bN ticktrigger$listeners = (bN)this.listeners.get(playerAdvancementsIn);
      if (ticktrigger$listeners != null) {
         ticktrigger$listeners.remove(listener);
         if (ticktrigger$listeners.isEmpty()) {
            this.listeners.remove(playerAdvancementsIn);
         }
      }

   }

   public void removeAllListeners(cl playerAdvancementsIn) {
      this.listeners.remove(playerAdvancementsIn);
   }

   public bM deserializeInstance(JsonObject json, JsonDeserializationContext context) {
      return new bM();
   }

   public void trigger(MG player) {
      bN ticktrigger$listeners = (bN)this.listeners.get(player.getAdvancements());
      if (ticktrigger$listeners != null) {
         ticktrigger$listeners.trigger();
      }

   }

   // $FF: synthetic method
   // $FF: bridge method
   public cg deserializeInstance(JsonObject var1, JsonDeserializationContext var2) {
      return this.deserializeInstance(var1, var2);
   }
}
