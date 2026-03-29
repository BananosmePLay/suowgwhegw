package neo;

import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import java.util.Map;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;

public class U implements ci<S> {
   private static final ResourceLocation ID = new ResourceLocation("entity_hurt_player");
   private final Map<cl, T> listeners = Maps.newHashMap();

   public U() {
   }

   public ResourceLocation getId() {
      return ID;
   }

   public void addListener(cl playerAdvancementsIn, ch<S> listener) {
      T entityhurtplayertrigger$listeners = (T)this.listeners.get(playerAdvancementsIn);
      if (entityhurtplayertrigger$listeners == null) {
         entityhurtplayertrigger$listeners = new T(playerAdvancementsIn);
         this.listeners.put(playerAdvancementsIn, entityhurtplayertrigger$listeners);
      }

      entityhurtplayertrigger$listeners.add(listener);
   }

   public void removeListener(cl playerAdvancementsIn, ch<S> listener) {
      T entityhurtplayertrigger$listeners = (T)this.listeners.get(playerAdvancementsIn);
      if (entityhurtplayertrigger$listeners != null) {
         entityhurtplayertrigger$listeners.remove(listener);
         if (entityhurtplayertrigger$listeners.isEmpty()) {
            this.listeners.remove(playerAdvancementsIn);
         }
      }

   }

   public void removeAllListeners(cl playerAdvancementsIn) {
      this.listeners.remove(playerAdvancementsIn);
   }

   public S deserializeInstance(JsonObject json, JsonDeserializationContext context) {
      F damagepredicate = F.deserialize(json.get("damage"));
      return new S(damagepredicate);
   }

   public void trigger(MG player, DamageSource source, float amountDealt, float amountTaken, boolean wasBlocked) {
      T entityhurtplayertrigger$listeners = (T)this.listeners.get(player.getAdvancements());
      if (entityhurtplayertrigger$listeners != null) {
         entityhurtplayertrigger$listeners.trigger(player, source, amountDealt, amountTaken, wasBlocked);
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
