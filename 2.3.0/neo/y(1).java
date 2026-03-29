package neo;

import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import java.util.Map;
import net.minecraft.util.ResourceLocation;

public class y implements ci<w> {
   private static final ResourceLocation ID = new ResourceLocation("construct_beacon");
   private final Map<cl, x> listeners = Maps.newHashMap();

   public y() {
   }

   public ResourceLocation getId() {
      return ID;
   }

   public void addListener(cl playerAdvancementsIn, ch<w> listener) {
      x constructbeacontrigger$listeners = (x)this.listeners.get(playerAdvancementsIn);
      if (constructbeacontrigger$listeners == null) {
         constructbeacontrigger$listeners = new x(playerAdvancementsIn);
         this.listeners.put(playerAdvancementsIn, constructbeacontrigger$listeners);
      }

      constructbeacontrigger$listeners.add(listener);
   }

   public void removeListener(cl playerAdvancementsIn, ch<w> listener) {
      x constructbeacontrigger$listeners = (x)this.listeners.get(playerAdvancementsIn);
      if (constructbeacontrigger$listeners != null) {
         constructbeacontrigger$listeners.remove(listener);
         if (constructbeacontrigger$listeners.isEmpty()) {
            this.listeners.remove(playerAdvancementsIn);
         }
      }

   }

   public void removeAllListeners(cl playerAdvancementsIn) {
      this.listeners.remove(playerAdvancementsIn);
   }

   public w deserializeInstance(JsonObject json, JsonDeserializationContext context) {
      bm minmaxbounds = bm.deserialize(json.get("level"));
      return new w(minmaxbounds);
   }

   public void trigger(MG player, Yj beacon) {
      x constructbeacontrigger$listeners = (x)this.listeners.get(player.getAdvancements());
      if (constructbeacontrigger$listeners != null) {
         constructbeacontrigger$listeners.trigger(beacon);
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
