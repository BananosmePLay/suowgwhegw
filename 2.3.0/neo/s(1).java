package neo;

import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import java.util.Map;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

public class s implements ci<q> {
   private static final ResourceLocation ID = new ResourceLocation("brewed_potion");
   private final Map<cl, r> listeners = Maps.newHashMap();

   public s() {
   }

   public ResourceLocation getId() {
      return ID;
   }

   public void addListener(cl playerAdvancementsIn, ch<q> listener) {
      r brewedpotiontrigger$listeners = (r)this.listeners.get(playerAdvancementsIn);
      if (brewedpotiontrigger$listeners == null) {
         brewedpotiontrigger$listeners = new r(playerAdvancementsIn);
         this.listeners.put(playerAdvancementsIn, brewedpotiontrigger$listeners);
      }

      brewedpotiontrigger$listeners.addListener(listener);
   }

   public void removeListener(cl playerAdvancementsIn, ch<q> listener) {
      r brewedpotiontrigger$listeners = (r)this.listeners.get(playerAdvancementsIn);
      if (brewedpotiontrigger$listeners != null) {
         brewedpotiontrigger$listeners.removeListener(listener);
         if (brewedpotiontrigger$listeners.isEmpty()) {
            this.listeners.remove(playerAdvancementsIn);
         }
      }

   }

   public void removeAllListeners(cl playerAdvancementsIn) {
      this.listeners.remove(playerAdvancementsIn);
   }

   public q deserializeInstance(JsonObject json, JsonDeserializationContext context) {
      Wf potiontype = null;
      if (json.has("potion")) {
         ResourceLocation resourcelocation = new ResourceLocation(JsonUtils.getString(json, "potion"));
         if (!Wf.REGISTRY.containsKey(resourcelocation)) {
            throw new JsonSyntaxException("Unknown potion '" + resourcelocation + "'");
         }

         potiontype = (Wf)Wf.REGISTRY.getObject(resourcelocation);
      }

      return new q(potiontype);
   }

   public void trigger(MG player, Wf potionIn) {
      r brewedpotiontrigger$listeners = (r)this.listeners.get(player.getAdvancements());
      if (brewedpotiontrigger$listeners != null) {
         brewedpotiontrigger$listeners.trigger(potionIn);
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
