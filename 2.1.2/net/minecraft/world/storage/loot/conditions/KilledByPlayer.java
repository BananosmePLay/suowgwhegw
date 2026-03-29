package net.minecraft.world.storage.loot.conditions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Random;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;

public class KilledByPlayer implements LootCondition {
   private final boolean inverse;

   public KilledByPlayer(boolean inverseIn) {
      this.inverse = inverseIn;
   }

   public boolean testCondition(Random rand, LootContext context) {
      boolean flag = context.getKillerPlayer() != null;
      return flag == !this.inverse;
   }

   public static class Serializer extends LootCondition.Serializer<KilledByPlayer> {
      protected Serializer() {
         super(new ResourceLocation("killed_by_player"), KilledByPlayer.class);
      }

      public void serialize(JsonObject json, KilledByPlayer value, JsonSerializationContext context) {
         json.addProperty("inverse", value.inverse);
      }

      public KilledByPlayer deserialize(JsonObject json, JsonDeserializationContext context) {
         return new KilledByPlayer(JsonUtils.getBoolean(json, "inverse", false));
      }

      // $FF: synthetic method
      // $FF: bridge method
      public LootCondition deserialize(JsonObject var1, JsonDeserializationContext var2) {
         return this.deserialize(var1, var2);
      }

      // $FF: synthetic method
      // $FF: bridge method
      public void serialize(JsonObject var1, LootCondition var2, JsonSerializationContext var3) {
         this.serialize(var1, (KilledByPlayer)var2, var3);
      }
   }
}
