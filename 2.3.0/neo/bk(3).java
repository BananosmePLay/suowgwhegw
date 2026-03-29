package neo;

import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import java.util.Map;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;

public class bk implements ci<bi> {
   private static final ResourceLocation ID = new ResourceLocation("levitation");
   private final Map<cl, bj> listeners = Maps.newHashMap();

   public bk() {
   }

   public ResourceLocation getId() {
      return ID;
   }

   public void addListener(cl playerAdvancementsIn, ch<bi> listener) {
      bj levitationtrigger$listeners = (bj)this.listeners.get(playerAdvancementsIn);
      if (levitationtrigger$listeners == null) {
         levitationtrigger$listeners = new bj(playerAdvancementsIn);
         this.listeners.put(playerAdvancementsIn, levitationtrigger$listeners);
      }

      levitationtrigger$listeners.add(listener);
   }

   public void removeListener(cl playerAdvancementsIn, ch<bi> listener) {
      bj levitationtrigger$listeners = (bj)this.listeners.get(playerAdvancementsIn);
      if (levitationtrigger$listeners != null) {
         levitationtrigger$listeners.remove(listener);
         if (levitationtrigger$listeners.isEmpty()) {
            this.listeners.remove(playerAdvancementsIn);
         }
      }

   }

   public void removeAllListeners(cl playerAdvancementsIn) {
      this.listeners.remove(playerAdvancementsIn);
   }

   public bi deserializeInstance(JsonObject json, JsonDeserializationContext context) {
      H distancepredicate = H.deserialize(json.get("distance"));
      bm minmaxbounds = bm.deserialize(json.get("duration"));
      return new bi(distancepredicate, minmaxbounds);
   }

   public void trigger(MG player, Vec3d startPos, int duration) {
      bj levitationtrigger$listeners = (bj)this.listeners.get(player.getAdvancements());
      if (levitationtrigger$listeners != null) {
         levitationtrigger$listeners.trigger(player, startPos, duration);
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
