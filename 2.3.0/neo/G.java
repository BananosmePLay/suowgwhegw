package neo;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.JsonUtils;

public class G {
   public static G ANY = new G();
   private final Boolean isProjectile;
   private final Boolean isExplosion;
   private final Boolean bypassesArmor;
   private final Boolean bypassesInvulnerability;
   private final Boolean bypassesMagic;
   private final Boolean isFire;
   private final Boolean isMagic;
   private final V directEntity;
   private final V sourceEntity;

   public G() {
      this.isProjectile = null;
      this.isExplosion = null;
      this.bypassesArmor = null;
      this.bypassesInvulnerability = null;
      this.bypassesMagic = null;
      this.isFire = null;
      this.isMagic = null;
      this.directEntity = V.ANY;
      this.sourceEntity = V.ANY;
   }

   public G(@Nullable Boolean isProjectile, @Nullable Boolean isExplosion, @Nullable Boolean bypassesArmor, @Nullable Boolean bypassesInvulnerability, @Nullable Boolean bypassesMagic, @Nullable Boolean isFire, @Nullable Boolean isMagic, V directEntity, V sourceEntity) {
      this.isProjectile = isProjectile;
      this.isExplosion = isExplosion;
      this.bypassesArmor = bypassesArmor;
      this.bypassesInvulnerability = bypassesInvulnerability;
      this.bypassesMagic = bypassesMagic;
      this.isFire = isFire;
      this.isMagic = isMagic;
      this.directEntity = directEntity;
      this.sourceEntity = sourceEntity;
   }

   public boolean test(MG player, DamageSource source) {
      if (this == ANY) {
         return true;
      } else if (this.isProjectile != null && this.isProjectile != source.isProjectile()) {
         return false;
      } else if (this.isExplosion != null && this.isExplosion != source.isExplosion()) {
         return false;
      } else if (this.bypassesArmor != null && this.bypassesArmor != source.isUnblockable()) {
         return false;
      } else if (this.bypassesInvulnerability != null && this.bypassesInvulnerability != source.canHarmInCreative()) {
         return false;
      } else if (this.bypassesMagic != null && this.bypassesMagic != source.isDamageAbsolute()) {
         return false;
      } else if (this.isFire != null && this.isFire != source.isFireDamage()) {
         return false;
      } else if (this.isMagic != null && this.isMagic != source.isMagicDamage()) {
         return false;
      } else {
         return !this.directEntity.test(player, source.getImmediateSource()) ? false : this.sourceEntity.test(player, source.getTrueSource());
      }
   }

   public static G deserialize(@Nullable JsonElement element) {
      if (element != null && !element.isJsonNull()) {
         JsonObject jsonobject = JsonUtils.getJsonObject(element, "damage type");
         Boolean obool = optionalBoolean(jsonobject, "is_projectile");
         Boolean obool1 = optionalBoolean(jsonobject, "is_explosion");
         Boolean obool2 = optionalBoolean(jsonobject, "bypasses_armor");
         Boolean obool3 = optionalBoolean(jsonobject, "bypasses_invulnerability");
         Boolean obool4 = optionalBoolean(jsonobject, "bypasses_magic");
         Boolean obool5 = optionalBoolean(jsonobject, "is_fire");
         Boolean obool6 = optionalBoolean(jsonobject, "is_magic");
         V entitypredicate = V.deserialize(jsonobject.get("direct_entity"));
         V entitypredicate1 = V.deserialize(jsonobject.get("source_entity"));
         return new G(obool, obool1, obool2, obool3, obool4, obool5, obool6, entitypredicate, entitypredicate1);
      } else {
         return ANY;
      }
   }

   @Nullable
   private static Boolean optionalBoolean(JsonObject object, String memberName) {
      return object.has(memberName) ? JsonUtils.getBoolean(object, memberName) : null;
   }
}
