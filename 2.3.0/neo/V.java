package neo;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import javax.annotation.Nullable;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

public class V {
   public static final V ANY;
   private final ResourceLocation type;
   private final H distance;
   private final bl location;
   private final bo effects;
   private final bp nbt;

   public V(@Nullable ResourceLocation type, H distance, bl location, bo effects, bp nbt) {
      this.type = type;
      this.distance = distance;
      this.location = location;
      this.effects = effects;
      this.nbt = nbt;
   }

   public boolean test(MG player, @Nullable Ig entity) {
      if (this == ANY) {
         return true;
      } else if (entity == null) {
         return false;
      } else if (this.type != null && !Ir.isMatchingName(entity, this.type)) {
         return false;
      } else if (!this.distance.test(player.posX, player.posY, player.posZ, entity.posX, entity.posY, entity.posZ)) {
         return false;
      } else if (!this.location.test(player.getServerWorld(), entity.posX, entity.posY, entity.posZ)) {
         return false;
      } else {
         return !this.effects.test(entity) ? false : this.nbt.test(entity);
      }
   }

   public static V deserialize(@Nullable JsonElement element) {
      if (element != null && !element.isJsonNull()) {
         JsonObject jsonobject = JsonUtils.getJsonObject(element, "entity");
         ResourceLocation resourcelocation = null;
         if (jsonobject.has("type")) {
            resourcelocation = new ResourceLocation(JsonUtils.getString(jsonobject, "type"));
            if (!Ir.isRegistered(resourcelocation)) {
               throw new JsonSyntaxException("Unknown entity type '" + resourcelocation + "', valid types are: " + Ir.getValidTypeNames());
            }
         }

         H distancepredicate = H.deserialize(jsonobject.get("distance"));
         bl locationpredicate = bl.deserialize(jsonobject.get("location"));
         bo mobeffectspredicate = bo.deserialize(jsonobject.get("effects"));
         bp nbtpredicate = bp.deserialize(jsonobject.get("nbt"));
         return new V(resourcelocation, distancepredicate, locationpredicate, mobeffectspredicate, nbtpredicate);
      } else {
         return ANY;
      }
   }

   static {
      ANY = new V((ResourceLocation)null, H.ANY, bl.ANY, bo.ANY, bp.ANY);
   }
}
