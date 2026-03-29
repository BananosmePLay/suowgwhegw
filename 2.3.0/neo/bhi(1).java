package neo;

import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Collection;
import java.util.Random;
import net.minecraft.util.math.MathHelper;

public abstract class bhi {
   protected final int weight;
   protected final int quality;
   protected final bgv[] conditions;

   protected bhi(int weightIn, int qualityIn, bgv[] conditionsIn) {
      this.weight = weightIn;
      this.quality = qualityIn;
      this.conditions = conditionsIn;
   }

   public int getEffectiveWeight(float luck) {
      return Math.max(MathHelper.floor((float)this.weight + (float)this.quality * luck), 0);
   }

   public abstract void addLoot(Collection<Qy> var1, Random var2, bhg var3);

   protected abstract void serialize(JsonObject var1, JsonSerializationContext var2);
}
