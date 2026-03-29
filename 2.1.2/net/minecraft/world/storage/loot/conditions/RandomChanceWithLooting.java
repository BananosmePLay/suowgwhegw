package net.minecraft.world.storage.loot.conditions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Random;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;

public class RandomChanceWithLooting implements LootCondition {
   private final float chance;
   private final float lootingMultiplier;

   public RandomChanceWithLooting(float chanceIn, float lootingMultiplierIn) {
      this.chance = chanceIn;
      this.lootingMultiplier = lootingMultiplierIn;
   }

   public boolean testCondition(Random rand, LootContext context) {
      int i = 0;
      if (context.getKiller() instanceof EntityLivingBase) {
         i = EnchantmentHelper.getLootingModifier((EntityLivingBase)context.getKiller());
      }

      return rand.nextFloat() < this.chance + (float)i * this.lootingMultiplier;
   }

   public static class Serializer extends LootCondition.Serializer<RandomChanceWithLooting> {
      protected Serializer() {
         super(new ResourceLocation("random_chance_with_looting"), RandomChanceWithLooting.class);
      }

      public void serialize(JsonObject json, RandomChanceWithLooting value, JsonSerializationContext context) {
         json.addProperty("chance", value.chance);
         json.addProperty("looting_multiplier", value.lootingMultiplier);
      }

      public RandomChanceWithLooting deserialize(JsonObject json, JsonDeserializationContext context) {
         return new RandomChanceWithLooting(JsonUtils.getFloat(json, "chance"), JsonUtils.getFloat(json, "looting_multiplier"));
      }

      // $FF: synthetic method
      // $FF: bridge method
      public LootCondition deserialize(JsonObject var1, JsonDeserializationContext var2) {
         return this.deserialize(var1, var2);
      }

      // $FF: synthetic method
      // $FF: bridge method
      public void serialize(JsonObject var1, LootCondition var2, JsonSerializationContext var3) {
         this.serialize(var1, (RandomChanceWithLooting)var2, var3);
      }
   }
}
