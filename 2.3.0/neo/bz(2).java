package neo;

import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import java.util.Map;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;

public class bz implements ci<bx> {
   private static final ResourceLocation ID = new ResourceLocation("player_hurt_entity");
   private final Map<cl, by> listeners = Maps.newHashMap();

   public bz() {
   }

   public ResourceLocation getId() {
      return ID;
   }

   public void addListener(cl playerAdvancementsIn, ch<bx> listener) {
      by playerhurtentitytrigger$listeners = (by)this.listeners.get(playerAdvancementsIn);
      if (playerhurtentitytrigger$listeners == null) {
         playerhurtentitytrigger$listeners = new by(playerAdvancementsIn);
         this.listeners.put(playerAdvancementsIn, playerhurtentitytrigger$listeners);
      }

      playerhurtentitytrigger$listeners.add(listener);
   }

   public void removeListener(cl playerAdvancementsIn, ch<bx> listener) {
      by playerhurtentitytrigger$listeners = (by)this.listeners.get(playerAdvancementsIn);
      if (playerhurtentitytrigger$listeners != null) {
         playerhurtentitytrigger$listeners.remove(listener);
         if (playerhurtentitytrigger$listeners.isEmpty()) {
            this.listeners.remove(playerAdvancementsIn);
         }
      }

   }

   public void removeAllListeners(cl playerAdvancementsIn) {
      this.listeners.remove(playerAdvancementsIn);
   }

   public bx deserializeInstance(JsonObject json, JsonDeserializationContext context) {
      F damagepredicate = F.deserialize(json.get("damage"));
      V entitypredicate = V.deserialize(json.get("entity"));
      return new bx(damagepredicate, entitypredicate);
   }

   public void trigger(MG player, Ig entityIn, DamageSource source, float amountDealt, float amountTaken, boolean blocked) {
      by playerhurtentitytrigger$listeners = (by)this.listeners.get(player.getAdvancements());
      if (playerhurtentitytrigger$listeners != null) {
         playerhurtentitytrigger$listeners.trigger(player, entityIn, source, amountDealt, amountTaken, blocked);
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
