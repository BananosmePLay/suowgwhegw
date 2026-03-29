package neo;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.JsonUtils;

public class F {
   public static F ANY = new F();
   private final bm dealt;
   private final bm taken;
   private final V sourceEntity;
   private final Boolean blocked;
   private final G type;

   public F() {
      this.dealt = bm.UNBOUNDED;
      this.taken = bm.UNBOUNDED;
      this.sourceEntity = V.ANY;
      this.blocked = null;
      this.type = G.ANY;
   }

   public F(bm dealt, bm taken, V sourceEntity, @Nullable Boolean blocked, G type) {
      this.dealt = dealt;
      this.taken = taken;
      this.sourceEntity = sourceEntity;
      this.blocked = blocked;
      this.type = type;
   }

   public boolean test(MG player, DamageSource source, float dealt, float taken, boolean blocked) {
      if (this == ANY) {
         return true;
      } else if (!this.dealt.test(dealt)) {
         return false;
      } else if (!this.taken.test(taken)) {
         return false;
      } else if (!this.sourceEntity.test(player, source.getTrueSource())) {
         return false;
      } else {
         return this.blocked != null && this.blocked != blocked ? false : this.type.test(player, source);
      }
   }

   public static F deserialize(@Nullable JsonElement element) {
      if (element != null && !element.isJsonNull()) {
         JsonObject jsonobject = JsonUtils.getJsonObject(element, "damage");
         bm minmaxbounds = bm.deserialize(jsonobject.get("dealt"));
         bm minmaxbounds1 = bm.deserialize(jsonobject.get("taken"));
         Boolean obool = jsonobject.has("blocked") ? JsonUtils.getBoolean(jsonobject, "blocked") : null;
         V entitypredicate = V.deserialize(jsonobject.get("source_entity"));
         G damagesourcepredicate = G.deserialize(jsonobject.get("type"));
         return new F(minmaxbounds, minmaxbounds1, entitypredicate, obool, damagesourcepredicate);
      } else {
         return ANY;
      }
   }
}
