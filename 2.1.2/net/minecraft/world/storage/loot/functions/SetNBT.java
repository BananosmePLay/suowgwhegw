package net.minecraft.world.storage.loot.functions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSyntaxException;
import java.util.Random;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.conditions.LootCondition;

public class SetNBT extends LootFunction {
   private final NBTTagCompound tag;

   public SetNBT(LootCondition[] conditionsIn, NBTTagCompound tagIn) {
      super(conditionsIn);
      this.tag = tagIn;
   }

   public ItemStack apply(ItemStack stack, Random rand, LootContext context) {
      NBTTagCompound nbttagcompound = stack.getTagCompound();
      if (nbttagcompound == null) {
         nbttagcompound = this.tag.copy();
      } else {
         nbttagcompound.merge(this.tag);
      }

      stack.setTagCompound(nbttagcompound);
      return stack;
   }

   public static class Serializer extends LootFunction.Serializer<SetNBT> {
      public Serializer() {
         super(new ResourceLocation("set_nbt"), SetNBT.class);
      }

      public void serialize(JsonObject object, SetNBT functionClazz, JsonSerializationContext serializationContext) {
         object.addProperty("tag", functionClazz.tag.toString());
      }

      public SetNBT deserialize(JsonObject object, JsonDeserializationContext deserializationContext, LootCondition[] conditionsIn) {
         try {
            NBTTagCompound nbttagcompound = JsonToNBT.getTagFromJson(JsonUtils.getString(object, "tag"));
            return new SetNBT(conditionsIn, nbttagcompound);
         } catch (NBTException var5) {
            NBTException nbtexception = var5;
            throw new JsonSyntaxException(nbtexception);
         }
      }

      // $FF: synthetic method
      // $FF: bridge method
      public LootFunction deserialize(JsonObject var1, JsonDeserializationContext var2, LootCondition[] var3) {
         return this.deserialize(var1, var2, var3);
      }

      // $FF: synthetic method
      // $FF: bridge method
      public void serialize(JsonObject var1, LootFunction var2, JsonSerializationContext var3) {
         this.serialize(var1, (SetNBT)var2, var3);
      }
   }
}
