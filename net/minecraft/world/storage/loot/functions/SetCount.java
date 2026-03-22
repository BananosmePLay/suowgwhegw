package net.minecraft.world.storage.loot.functions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Random;
import net.minecraft.item.ItemStack;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;

public class SetCount extends LootFunction {
   private final RandomValueRange countRange;

   public SetCount(LootCondition[] conditionsIn, RandomValueRange countRangeIn) {
      super(conditionsIn);
      this.countRange = countRangeIn;
   }

   public ItemStack apply(ItemStack stack, Random rand, LootContext context) {
      stack.setCount(this.countRange.generateInt(rand));
      return stack;
   }

   public static class Serializer extends LootFunction.Serializer<SetCount> {
      protected Serializer() {
         super(new ResourceLocation("set_count"), SetCount.class);
      }

      public void serialize(JsonObject object, SetCount functionClazz, JsonSerializationContext serializationContext) {
         object.add("count", serializationContext.serialize(functionClazz.countRange));
      }

      public SetCount deserialize(JsonObject object, JsonDeserializationContext deserializationContext, LootCondition[] conditionsIn) {
         return new SetCount(conditionsIn, (RandomValueRange)JsonUtils.deserializeClass(object, "count", deserializationContext, RandomValueRange.class));
      }

      // $FF: synthetic method
      // $FF: bridge method
      public LootFunction deserialize(JsonObject var1, JsonDeserializationContext var2, LootCondition[] var3) {
         return this.deserialize(var1, var2, var3);
      }

      // $FF: synthetic method
      // $FF: bridge method
      public void serialize(JsonObject var1, LootFunction var2, JsonSerializationContext var3) {
         this.serialize(var1, (SetCount)var2, var3);
      }
   }
}
