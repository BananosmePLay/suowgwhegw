package neo;

import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import java.util.Map;
import net.minecraft.util.ResourceLocation;

public class p implements ci<n> {
   private static final ResourceLocation ID = new ResourceLocation("bred_animals");
   private final Map<cl, o> listeners = Maps.newHashMap();

   public p() {
   }

   public ResourceLocation getId() {
      return ID;
   }

   public void addListener(cl playerAdvancementsIn, ch<n> listener) {
      o bredanimalstrigger$listeners = (o)this.listeners.get(playerAdvancementsIn);
      if (bredanimalstrigger$listeners == null) {
         bredanimalstrigger$listeners = new o(playerAdvancementsIn);
         this.listeners.put(playerAdvancementsIn, bredanimalstrigger$listeners);
      }

      bredanimalstrigger$listeners.add(listener);
   }

   public void removeListener(cl playerAdvancementsIn, ch<n> listener) {
      o bredanimalstrigger$listeners = (o)this.listeners.get(playerAdvancementsIn);
      if (bredanimalstrigger$listeners != null) {
         bredanimalstrigger$listeners.remove(listener);
         if (bredanimalstrigger$listeners.isEmpty()) {
            this.listeners.remove(playerAdvancementsIn);
         }
      }

   }

   public void removeAllListeners(cl playerAdvancementsIn) {
      this.listeners.remove(playerAdvancementsIn);
   }

   public n deserializeInstance(JsonObject json, JsonDeserializationContext context) {
      V entitypredicate = V.deserialize(json.get("parent"));
      V entitypredicate1 = V.deserialize(json.get("partner"));
      V entitypredicate2 = V.deserialize(json.get("child"));
      return new n(entitypredicate, entitypredicate1, entitypredicate2);
   }

   public void trigger(MG player, Ly parent1, Ly parent2, Ih child) {
      o bredanimalstrigger$listeners = (o)this.listeners.get(player.getAdvancements());
      if (bredanimalstrigger$listeners != null) {
         bredanimalstrigger$listeners.trigger(player, parent1, parent2, child);
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
