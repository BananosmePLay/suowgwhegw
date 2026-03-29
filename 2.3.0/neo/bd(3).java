package neo;

import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import java.util.Map;
import net.minecraft.util.ResourceLocation;

public class bd implements ci<bb> {
   private static final ResourceLocation ID = new ResourceLocation("item_durability_changed");
   private final Map<cl, bc> listeners = Maps.newHashMap();

   public bd() {
   }

   public ResourceLocation getId() {
      return ID;
   }

   public void addListener(cl playerAdvancementsIn, ch<bb> listener) {
      bc itemdurabilitytrigger$listeners = (bc)this.listeners.get(playerAdvancementsIn);
      if (itemdurabilitytrigger$listeners == null) {
         itemdurabilitytrigger$listeners = new bc(playerAdvancementsIn);
         this.listeners.put(playerAdvancementsIn, itemdurabilitytrigger$listeners);
      }

      itemdurabilitytrigger$listeners.add(listener);
   }

   public void removeListener(cl playerAdvancementsIn, ch<bb> listener) {
      bc itemdurabilitytrigger$listeners = (bc)this.listeners.get(playerAdvancementsIn);
      if (itemdurabilitytrigger$listeners != null) {
         itemdurabilitytrigger$listeners.remove(listener);
         if (itemdurabilitytrigger$listeners.isEmpty()) {
            this.listeners.remove(playerAdvancementsIn);
         }
      }

   }

   public void removeAllListeners(cl playerAdvancementsIn) {
      this.listeners.remove(playerAdvancementsIn);
   }

   public bb deserializeInstance(JsonObject json, JsonDeserializationContext context) {
      be itempredicate = be.deserialize(json.get("item"));
      bm minmaxbounds = bm.deserialize(json.get("durability"));
      bm minmaxbounds1 = bm.deserialize(json.get("delta"));
      return new bb(itempredicate, minmaxbounds, minmaxbounds1);
   }

   public void trigger(MG player, Qy itemIn, int newDurability) {
      bc itemdurabilitytrigger$listeners = (bc)this.listeners.get(player.getAdvancements());
      if (itemdurabilitytrigger$listeners != null) {
         itemdurabilitytrigger$listeners.trigger(itemIn, newDurability);
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
