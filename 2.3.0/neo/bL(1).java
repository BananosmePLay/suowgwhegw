package neo;

import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import java.util.Map;
import net.minecraft.util.ResourceLocation;

public class bL implements ci<bJ> {
   private static final ResourceLocation ID = new ResourceLocation("tame_animal");
   private final Map<cl, bK> listeners = Maps.newHashMap();

   public bL() {
   }

   public ResourceLocation getId() {
      return ID;
   }

   public void addListener(cl playerAdvancementsIn, ch<bJ> listener) {
      bK tameanimaltrigger$listeners = (bK)this.listeners.get(playerAdvancementsIn);
      if (tameanimaltrigger$listeners == null) {
         tameanimaltrigger$listeners = new bK(playerAdvancementsIn);
         this.listeners.put(playerAdvancementsIn, tameanimaltrigger$listeners);
      }

      tameanimaltrigger$listeners.add(listener);
   }

   public void removeListener(cl playerAdvancementsIn, ch<bJ> listener) {
      bK tameanimaltrigger$listeners = (bK)this.listeners.get(playerAdvancementsIn);
      if (tameanimaltrigger$listeners != null) {
         tameanimaltrigger$listeners.remove(listener);
         if (tameanimaltrigger$listeners.isEmpty()) {
            this.listeners.remove(playerAdvancementsIn);
         }
      }

   }

   public void removeAllListeners(cl playerAdvancementsIn) {
      this.listeners.remove(playerAdvancementsIn);
   }

   public bJ deserializeInstance(JsonObject json, JsonDeserializationContext context) {
      V entitypredicate = V.deserialize(json.get("entity"));
      return new bJ(entitypredicate);
   }

   public void trigger(MG player, Ly entity) {
      bK tameanimaltrigger$listeners = (bK)this.listeners.get(player.getAdvancements());
      if (tameanimaltrigger$listeners != null) {
         tameanimaltrigger$listeners.trigger(player, entity);
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
