package neo;

import com.google.common.collect.Maps;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

public class bo {
   public static final bo ANY = new bo(Collections.emptyMap());
   private final Map<VW, bn> effects;

   public bo(Map<VW, bn> effects) {
      this.effects = effects;
   }

   public boolean test(Ig entityIn) {
      if (this == ANY) {
         return true;
      } else {
         return entityIn instanceof Iw ? this.test(((Iw)entityIn).getActivePotionMap()) : false;
      }
   }

   public boolean test(Iw entityIn) {
      return this == ANY ? true : this.test(entityIn.getActivePotionMap());
   }

   public boolean test(Map<VW, VZ> potions) {
      if (this == ANY) {
         return true;
      } else {
         Iterator var2 = this.effects.entrySet().iterator();

         Map.Entry entry;
         VZ potioneffect;
         do {
            if (!var2.hasNext()) {
               return true;
            }

            entry = (Map.Entry)var2.next();
            potioneffect = (VZ)potions.get(entry.getKey());
         } while(((bn)entry.getValue()).test(potioneffect));

         return false;
      }
   }

   public static bo deserialize(@Nullable JsonElement element) {
      if (element != null && !element.isJsonNull()) {
         JsonObject jsonobject = JsonUtils.getJsonObject(element, "effects");
         Map<VW, bn> map = Maps.newHashMap();
         Iterator var3 = jsonobject.entrySet().iterator();

         while(var3.hasNext()) {
            Map.Entry<String, JsonElement> entry = (Map.Entry)var3.next();
            ResourceLocation resourcelocation = new ResourceLocation((String)entry.getKey());
            VW potion = (VW)VW.REGISTRY.getObject(resourcelocation);
            if (potion == null) {
               throw new JsonSyntaxException("Unknown effect '" + resourcelocation + "'");
            }

            bn mobeffectspredicate$instancepredicate = bn.deserialize(JsonUtils.getJsonObject((JsonElement)entry.getValue(), (String)entry.getKey()));
            map.put(potion, mobeffectspredicate$instancepredicate);
         }

         return new bo(map);
      } else {
         return ANY;
      }
   }
}
