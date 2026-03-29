package neo;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Collection;
import java.util.Random;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

public class bhk extends bhi {
   protected final OL item;
   protected final bgI[] functions;

   public bhk(OL itemIn, int weightIn, int qualityIn, bgI[] functionsIn, bgv[] conditionsIn) {
      super(weightIn, qualityIn, conditionsIn);
      this.item = itemIn;
      this.functions = functionsIn;
   }

   public void addLoot(Collection<Qy> stacks, Random rand, bhg context) {
      Qy itemstack = new Qy(this.item);
      bgI[] var5 = this.functions;
      int var6 = var5.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         bgI lootfunction = var5[var7];
         if (bgx.testAllConditions(lootfunction.getConditions(), rand, context)) {
            itemstack = lootfunction.apply(itemstack, rand, context);
         }
      }

      if (!itemstack.isEmpty()) {
         if (itemstack.getCount() < this.item.getItemStackLimit()) {
            stacks.add(itemstack);
         } else {
            int i = itemstack.getCount();

            while(i > 0) {
               Qy itemstack1 = itemstack.copy();
               itemstack1.setCount(Math.min(itemstack.getMaxStackSize(), i));
               i -= itemstack1.getCount();
               stacks.add(itemstack1);
            }
         }
      }

   }

   protected void serialize(JsonObject json, JsonSerializationContext context) {
      if (this.functions != null && this.functions.length > 0) {
         json.add("functions", context.serialize(this.functions));
      }

      ResourceLocation resourcelocation = (ResourceLocation)OL.REGISTRY.getNameForObject(this.item);
      if (resourcelocation == null) {
         throw new IllegalArgumentException("Can't serialize unknown item " + this.item);
      } else {
         json.addProperty("name", resourcelocation.toString());
      }
   }

   public static bhk deserialize(JsonObject object, JsonDeserializationContext deserializationContext, int weightIn, int qualityIn, bgv[] conditionsIn) {
      OL item = JsonUtils.getItem(object, "name");
      bgI[] alootfunction;
      if (object.has("functions")) {
         alootfunction = (bgI[])((bgI[])JsonUtils.deserializeClass(object, "functions", deserializationContext, bgI[].class));
      } else {
         alootfunction = new bgI[0];
      }

      return new bhk(item, weightIn, qualityIn, alootfunction, conditionsIn);
   }
}
