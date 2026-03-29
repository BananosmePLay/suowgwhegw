package neo;

import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import java.util.Map;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;

public class bs implements ci<bq> {
   private static final ResourceLocation ID = new ResourceLocation("nether_travel");
   private final Map<cl, br> listeners = Maps.newHashMap();

   public bs() {
   }

   public ResourceLocation getId() {
      return ID;
   }

   public void addListener(cl playerAdvancementsIn, ch<bq> listener) {
      br nethertraveltrigger$listeners = (br)this.listeners.get(playerAdvancementsIn);
      if (nethertraveltrigger$listeners == null) {
         nethertraveltrigger$listeners = new br(playerAdvancementsIn);
         this.listeners.put(playerAdvancementsIn, nethertraveltrigger$listeners);
      }

      nethertraveltrigger$listeners.add(listener);
   }

   public void removeListener(cl playerAdvancementsIn, ch<bq> listener) {
      br nethertraveltrigger$listeners = (br)this.listeners.get(playerAdvancementsIn);
      if (nethertraveltrigger$listeners != null) {
         nethertraveltrigger$listeners.remove(listener);
         if (nethertraveltrigger$listeners.isEmpty()) {
            this.listeners.remove(playerAdvancementsIn);
         }
      }

   }

   public void removeAllListeners(cl playerAdvancementsIn) {
      this.listeners.remove(playerAdvancementsIn);
   }

   public bq deserializeInstance(JsonObject json, JsonDeserializationContext context) {
      bl locationpredicate = bl.deserialize(json.get("entered"));
      bl locationpredicate1 = bl.deserialize(json.get("exited"));
      H distancepredicate = H.deserialize(json.get("distance"));
      return new bq(locationpredicate, locationpredicate1, distancepredicate);
   }

   public void trigger(MG player, Vec3d enteredNetherPosition) {
      br nethertraveltrigger$listeners = (br)this.listeners.get(player.getAdvancements());
      if (nethertraveltrigger$listeners != null) {
         nethertraveltrigger$listeners.trigger(player.getServerWorld(), enteredNetherPosition, player.posX, player.posY, player.posZ);
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
