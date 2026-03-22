package net.minecraft.world.storage.loot.functions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Random;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Smelt extends LootFunction {
   private static final Logger LOGGER = LogManager.getLogger();

   public Smelt(LootCondition[] conditionsIn) {
      super(conditionsIn);
   }

   public ItemStack apply(ItemStack stack, Random rand, LootContext context) {
      if (stack.isEmpty()) {
         return stack;
      } else {
         ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(stack);
         if (itemstack.isEmpty()) {
            LOGGER.warn("Couldn't smelt {} because there is no smelting recipe", stack);
            return stack;
         } else {
            ItemStack itemstack1 = itemstack.copy();
            itemstack1.setCount(stack.getCount());
            return itemstack1;
         }
      }
   }

   public static class Serializer extends LootFunction.Serializer<Smelt> {
      protected Serializer() {
         super(new ResourceLocation("furnace_smelt"), Smelt.class);
      }

      public void serialize(JsonObject object, Smelt functionClazz, JsonSerializationContext serializationContext) {
      }

      public Smelt deserialize(JsonObject object, JsonDeserializationContext deserializationContext, LootCondition[] conditionsIn) {
         return new Smelt(conditionsIn);
      }

      // $FF: synthetic method
      // $FF: bridge method
      public LootFunction deserialize(JsonObject var1, JsonDeserializationContext var2, LootCondition[] var3) {
         return this.deserialize(var1, var2, var3);
      }

      // $FF: synthetic method
      // $FF: bridge method
      public void serialize(JsonObject var1, LootFunction var2, JsonSerializationContext var3) {
         this.serialize(var1, (Smelt)var2, var3);
      }
   }
}
