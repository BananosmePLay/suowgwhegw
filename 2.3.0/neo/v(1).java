package neo;

import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import java.util.Map;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

public class v implements ci<t> {
   private static final ResourceLocation ID = new ResourceLocation("changed_dimension");
   private final Map<cl, u> listeners = Maps.newHashMap();

   public v() {
   }

   public ResourceLocation getId() {
      return ID;
   }

   public void addListener(cl playerAdvancementsIn, ch<t> listener) {
      u changedimensiontrigger$listeners = (u)this.listeners.get(playerAdvancementsIn);
      if (changedimensiontrigger$listeners == null) {
         changedimensiontrigger$listeners = new u(playerAdvancementsIn);
         this.listeners.put(playerAdvancementsIn, changedimensiontrigger$listeners);
      }

      changedimensiontrigger$listeners.add(listener);
   }

   public void removeListener(cl playerAdvancementsIn, ch<t> listener) {
      u changedimensiontrigger$listeners = (u)this.listeners.get(playerAdvancementsIn);
      if (changedimensiontrigger$listeners != null) {
         changedimensiontrigger$listeners.remove(listener);
         if (changedimensiontrigger$listeners.isEmpty()) {
            this.listeners.remove(playerAdvancementsIn);
         }
      }

   }

   public void removeAllListeners(cl playerAdvancementsIn) {
      this.listeners.remove(playerAdvancementsIn);
   }

   public t deserializeInstance(JsonObject json, JsonDeserializationContext context) {
      baM dimensiontype = json.has("from") ? baM.byName(JsonUtils.getString(json, "from")) : null;
      baM dimensiontype1 = json.has("to") ? baM.byName(JsonUtils.getString(json, "to")) : null;
      return new t(dimensiontype, dimensiontype1);
   }

   public void trigger(MG player, baM from, baM to) {
      u changedimensiontrigger$listeners = (u)this.listeners.get(player.getAdvancements());
      if (changedimensiontrigger$listeners != null) {
         changedimensiontrigger$listeners.trigger(from, to);
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
