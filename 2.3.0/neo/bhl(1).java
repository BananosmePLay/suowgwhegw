package neo;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Collection;
import java.util.Random;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

public class bhl extends bhi {
   protected final ResourceLocation table;

   public bhl(ResourceLocation tableIn, int weightIn, int qualityIn, bgv[] conditionsIn) {
      super(weightIn, qualityIn, conditionsIn);
      this.table = tableIn;
   }

   public void addLoot(Collection<Qy> stacks, Random rand, bhg context) {
      bhp loottable = context.getLootTableManager().getLootTableFromLocation(this.table);
      Collection<Qy> collection = loottable.generateLootForPools(rand, context);
      stacks.addAll(collection);
   }

   protected void serialize(JsonObject json, JsonSerializationContext context) {
      json.addProperty("name", this.table.toString());
   }

   public static bhl deserialize(JsonObject object, JsonDeserializationContext deserializationContext, int weightIn, int qualityIn, bgv[] conditionsIn) {
      ResourceLocation resourcelocation = new ResourceLocation(JsonUtils.getString(object, "name"));
      return new bhl(resourcelocation, weightIn, qualityIn, conditionsIn);
   }
}
