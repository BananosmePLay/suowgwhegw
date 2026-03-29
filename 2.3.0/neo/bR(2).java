package neo;

import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import java.util.Map;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class bR implements ci<bP> {
   private static final ResourceLocation ID = new ResourceLocation("used_ender_eye");
   private final Map<cl, bQ> listeners = Maps.newHashMap();

   public bR() {
   }

   public ResourceLocation getId() {
      return ID;
   }

   public void addListener(cl playerAdvancementsIn, ch<bP> listener) {
      bQ usedendereyetrigger$listeners = (bQ)this.listeners.get(playerAdvancementsIn);
      if (usedendereyetrigger$listeners == null) {
         usedendereyetrigger$listeners = new bQ(playerAdvancementsIn);
         this.listeners.put(playerAdvancementsIn, usedendereyetrigger$listeners);
      }

      usedendereyetrigger$listeners.add(listener);
   }

   public void removeListener(cl playerAdvancementsIn, ch<bP> listener) {
      bQ usedendereyetrigger$listeners = (bQ)this.listeners.get(playerAdvancementsIn);
      if (usedendereyetrigger$listeners != null) {
         usedendereyetrigger$listeners.remove(listener);
         if (usedendereyetrigger$listeners.isEmpty()) {
            this.listeners.remove(playerAdvancementsIn);
         }
      }

   }

   public void removeAllListeners(cl playerAdvancementsIn) {
      this.listeners.remove(playerAdvancementsIn);
   }

   public bP deserializeInstance(JsonObject json, JsonDeserializationContext context) {
      bm minmaxbounds = bm.deserialize(json.get("distance"));
      return new bP(minmaxbounds);
   }

   public void trigger(MG player, BlockPos pos) {
      bQ usedendereyetrigger$listeners = (bQ)this.listeners.get(player.getAdvancements());
      if (usedendereyetrigger$listeners != null) {
         double d0 = player.posX - (double)pos.getX();
         double d1 = player.posZ - (double)pos.getZ();
         usedendereyetrigger$listeners.trigger(d0 * d0 + d1 * d1);
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
