package neo;

import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import java.util.Map;
import net.minecraft.util.ResourceLocation;

public class bC implements ci<bA> {
   private final ResourceLocation id;
   private final Map<cl, bB> listeners = Maps.newHashMap();

   public bC(ResourceLocation id) {
      this.id = id;
   }

   public ResourceLocation getId() {
      return this.id;
   }

   public void addListener(cl playerAdvancementsIn, ch<bA> listener) {
      bB positiontrigger$listeners = (bB)this.listeners.get(playerAdvancementsIn);
      if (positiontrigger$listeners == null) {
         positiontrigger$listeners = new bB(playerAdvancementsIn);
         this.listeners.put(playerAdvancementsIn, positiontrigger$listeners);
      }

      positiontrigger$listeners.add(listener);
   }

   public void removeListener(cl playerAdvancementsIn, ch<bA> listener) {
      bB positiontrigger$listeners = (bB)this.listeners.get(playerAdvancementsIn);
      if (positiontrigger$listeners != null) {
         positiontrigger$listeners.remove(listener);
         if (positiontrigger$listeners.isEmpty()) {
            this.listeners.remove(playerAdvancementsIn);
         }
      }

   }

   public void removeAllListeners(cl playerAdvancementsIn) {
      this.listeners.remove(playerAdvancementsIn);
   }

   public bA deserializeInstance(JsonObject json, JsonDeserializationContext context) {
      bl locationpredicate = bl.deserialize(json);
      return new bA(this.id, locationpredicate);
   }

   public void trigger(MG player) {
      bB positiontrigger$listeners = (bB)this.listeners.get(player.getAdvancements());
      if (positiontrigger$listeners != null) {
         positiontrigger$listeners.trigger(player.getServerWorld(), player.posX, player.posY, player.posZ);
      }

   }

   // $FF: synthetic method
   // $FF: bridge method
   public cg deserializeInstance(JsonObject var1, JsonDeserializationContext var2) {
      return this.deserializeInstance(var1, var2);
   }
}
