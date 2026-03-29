package neo;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Collection;
import java.util.Random;

public class bhj extends bhi {
   public bhj(int weightIn, int qualityIn, bgv[] conditionsIn) {
      super(weightIn, qualityIn, conditionsIn);
   }

   public void addLoot(Collection<Qy> stacks, Random rand, bhg context) {
   }

   protected void serialize(JsonObject json, JsonSerializationContext context) {
   }

   public static bhj deserialize(JsonObject object, JsonDeserializationContext deserializationContext, int weightIn, int qualityIn, bgv[] conditionsIn) {
      return new bhj(weightIn, qualityIn, conditionsIn);
   }
}
