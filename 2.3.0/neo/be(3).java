package neo;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

public class be {
   public static final be ANY = new be();
   private final OL item;
   private final Integer data;
   private final bm count;
   private final bm durability;
   private final O[] enchantments;
   private final Wf potion;
   private final bp nbt;

   public be() {
      this.item = null;
      this.data = null;
      this.potion = null;
      this.count = bm.UNBOUNDED;
      this.durability = bm.UNBOUNDED;
      this.enchantments = new O[0];
      this.nbt = bp.ANY;
   }

   public be(@Nullable OL item, @Nullable Integer data, bm count, bm durability, O[] enchantments, @Nullable Wf potion, bp nbt) {
      this.item = item;
      this.data = data;
      this.count = count;
      this.durability = durability;
      this.enchantments = enchantments;
      this.potion = potion;
      this.nbt = nbt;
   }

   public boolean test(Qy item) {
      if (this.item != null && item.getItem() != this.item) {
         return false;
      } else if (this.data != null && item.getMetadata() != this.data) {
         return false;
      } else if (!this.count.test((float)item.getCount())) {
         return false;
      } else if (this.durability != bm.UNBOUNDED && !item.isItemStackDamageable()) {
         return false;
      } else if (!this.durability.test((float)(item.getMaxDamage() - item.getItemDamage()))) {
         return false;
      } else if (!this.nbt.test(item)) {
         return false;
      } else {
         Map<Fa, Integer> map = Ft.getEnchantments(item);

         for(int i = 0; i < this.enchantments.length; ++i) {
            if (!this.enchantments[i].test(map)) {
               return false;
            }
         }

         Wf potiontype = Wg.getPotionFromItem(item);
         if (this.potion != null && this.potion != potiontype) {
            return false;
         } else {
            return true;
         }
      }
   }

   public static be deserialize(@Nullable JsonElement element) {
      if (element != null && !element.isJsonNull()) {
         JsonObject jsonobject = JsonUtils.getJsonObject(element, "item");
         bm minmaxbounds = bm.deserialize(jsonobject.get("count"));
         bm minmaxbounds1 = bm.deserialize(jsonobject.get("durability"));
         Integer integer = jsonobject.has("data") ? JsonUtils.getInt(jsonobject, "data") : null;
         bp nbtpredicate = bp.deserialize(jsonobject.get("nbt"));
         OL item = null;
         if (jsonobject.has("item")) {
            ResourceLocation resourcelocation = new ResourceLocation(JsonUtils.getString(jsonobject, "item"));
            item = (OL)OL.REGISTRY.getObject(resourcelocation);
            if (item == null) {
               throw new JsonSyntaxException("Unknown item id '" + resourcelocation + "'");
            }
         }

         O[] aenchantmentpredicate = O.deserializeArray(jsonobject.get("enchantments"));
         Wf potiontype = null;
         if (jsonobject.has("potion")) {
            ResourceLocation resourcelocation1 = new ResourceLocation(JsonUtils.getString(jsonobject, "potion"));
            if (!Wf.REGISTRY.containsKey(resourcelocation1)) {
               throw new JsonSyntaxException("Unknown potion '" + resourcelocation1 + "'");
            }

            potiontype = (Wf)Wf.REGISTRY.getObject(resourcelocation1);
         }

         return new be(item, integer, minmaxbounds, minmaxbounds1, aenchantmentpredicate, potiontype, nbtpredicate);
      } else {
         return ANY;
      }
   }

   public static be[] deserializeArray(@Nullable JsonElement element) {
      if (element != null && !element.isJsonNull()) {
         JsonArray jsonarray = JsonUtils.getJsonArray(element, "items");
         be[] aitempredicate = new be[jsonarray.size()];

         for(int i = 0; i < aitempredicate.length; ++i) {
            aitempredicate[i] = deserialize(jsonarray.get(i));
         }

         return aitempredicate;
      } else {
         return new be[0];
      }
   }
}
