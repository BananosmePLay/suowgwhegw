package neo;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

public class O {
   public static final O ANY = new O();
   private final Fa enchantment;
   private final bm levels;

   public O() {
      this.enchantment = null;
      this.levels = bm.UNBOUNDED;
   }

   public O(@Nullable Fa enchantment, bm levels) {
      this.enchantment = enchantment;
      this.levels = levels;
   }

   public boolean test(Map<Fa, Integer> enchantmentsIn) {
      if (this.enchantment != null) {
         if (!enchantmentsIn.containsKey(this.enchantment)) {
            return false;
         }

         int i = (Integer)enchantmentsIn.get(this.enchantment);
         if (this.levels != null && !this.levels.test((float)i)) {
            return false;
         }
      } else if (this.levels != null) {
         Iterator var4 = enchantmentsIn.values().iterator();

         Integer integer;
         do {
            if (!var4.hasNext()) {
               return false;
            }

            integer = (Integer)var4.next();
         } while(!this.levels.test((float)integer));

         return true;
      }

      return true;
   }

   public static O deserialize(@Nullable JsonElement element) {
      if (element != null && !element.isJsonNull()) {
         JsonObject jsonobject = JsonUtils.getJsonObject(element, "enchantment");
         Fa enchantment = null;
         if (jsonobject.has("enchantment")) {
            ResourceLocation resourcelocation = new ResourceLocation(JsonUtils.getString(jsonobject, "enchantment"));
            enchantment = (Fa)Fa.REGISTRY.getObject(resourcelocation);
            if (enchantment == null) {
               throw new JsonSyntaxException("Unknown enchantment '" + resourcelocation + "'");
            }
         }

         bm minmaxbounds = bm.deserialize(jsonobject.get("levels"));
         return new O(enchantment, minmaxbounds);
      } else {
         return ANY;
      }
   }

   public static O[] deserializeArray(@Nullable JsonElement element) {
      if (element != null && !element.isJsonNull()) {
         JsonArray jsonarray = JsonUtils.getJsonArray(element, "enchantments");
         O[] aenchantmentpredicate = new O[jsonarray.size()];

         for(int i = 0; i < aenchantmentpredicate.length; ++i) {
            aenchantmentpredicate[i] = deserialize(jsonarray.get(i));
         }

         return aenchantmentpredicate;
      } else {
         return new O[0];
      }
   }
}
