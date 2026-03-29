package neo;

import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;
import javax.annotation.Nullable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Ni {
   private static final Logger LOGGER = LogManager.getLogger();
   public static final FY MAX_HEALTH = (new Gc((FY)null, "generic.maxHealth", 20.0, 0.0, 1024.0)).setDescription("Max Health").setShouldWatch(true);
   public static final FY FOLLOW_RANGE = (new Gc((FY)null, "generic.followRange", 32.0, 0.0, 2048.0)).setDescription("Follow Range");
   public static final FY KNOCKBACK_RESISTANCE = (new Gc((FY)null, "generic.knockbackResistance", 0.0, 0.0, 1.0)).setDescription("Knockback Resistance");
   public static final FY MOVEMENT_SPEED = (new Gc((FY)null, "generic.movementSpeed", 0.699999988079071, 0.0, 1024.0)).setDescription("Movement Speed").setShouldWatch(true);
   public static final FY FLYING_SPEED = (new Gc((FY)null, "generic.flyingSpeed", 0.4000000059604645, 0.0, 1024.0)).setDescription("Flying Speed").setShouldWatch(true);
   public static final FY ATTACK_DAMAGE = new Gc((FY)null, "generic.attackDamage", 2.0, 0.0, 2048.0);
   public static final FY ATTACK_SPEED = (new Gc((FY)null, "generic.attackSpeed", 4.0, 0.0, 1024.0)).setShouldWatch(true);
   public static final FY ARMOR = (new Gc((FY)null, "generic.armor", 0.0, 0.0, 30.0)).setShouldWatch(true);
   public static final FY ARMOR_TOUGHNESS = (new Gc((FY)null, "generic.armorToughness", 0.0, 0.0, 20.0)).setShouldWatch(true);
   public static final FY LUCK = (new Gc((FY)null, "generic.luck", 0.0, -1024.0, 1024.0)).setShouldWatch(true);

   public Ni() {
   }

   public static QW writeBaseAttributeMapToNBT(FU map) {
      QW nbttaglist = new QW();
      Iterator var2 = map.getAllAttributes().iterator();

      while(var2.hasNext()) {
         FZ iattributeinstance = (FZ)var2.next();
         nbttaglist.appendTag(writeAttributeInstanceToNBT(iattributeinstance));
      }

      return nbttaglist;
   }

   private static QQ writeAttributeInstanceToNBT(FZ instance) {
      QQ nbttagcompound = new QQ();
      FY iattribute = instance.getAttribute();
      nbttagcompound.setString("Name", iattribute.getName());
      nbttagcompound.setDouble("Base", instance.getBaseValue());
      Collection<FW> collection = instance.getModifiers();
      if (collection != null && !collection.isEmpty()) {
         QW nbttaglist = new QW();
         Iterator var5 = collection.iterator();

         while(var5.hasNext()) {
            FW attributemodifier = (FW)var5.next();
            if (attributemodifier.isSaved()) {
               nbttaglist.appendTag(writeAttributeModifierToNBT(attributemodifier));
            }
         }

         nbttagcompound.setTag("Modifiers", nbttaglist);
      }

      return nbttagcompound;
   }

   public static QQ writeAttributeModifierToNBT(FW modifier) {
      QQ nbttagcompound = new QQ();
      nbttagcompound.setString("Name", modifier.getName());
      nbttagcompound.setDouble("Amount", modifier.getAmount());
      nbttagcompound.setInteger("Operation", modifier.getOperation());
      nbttagcompound.setUniqueId("UUID", modifier.getID());
      return nbttagcompound;
   }

   public static void setAttributeModifiers(FU map, QW list) {
      for(int i = 0; i < list.tagCount(); ++i) {
         QQ nbttagcompound = list.getCompoundTagAt(i);
         FZ iattributeinstance = map.getAttributeInstanceByName(nbttagcompound.getString("Name"));
         if (iattributeinstance == null) {
            LOGGER.warn("Ignoring unknown attribute '{}'", nbttagcompound.getString("Name"));
         } else {
            applyModifiersToAttributeInstance(iattributeinstance, nbttagcompound);
         }
      }

   }

   private static void applyModifiersToAttributeInstance(FZ instance, QQ compound) {
      instance.setBaseValue(compound.getDouble("Base"));
      if (compound.hasKey("Modifiers", 9)) {
         QW nbttaglist = compound.getTagList("Modifiers", 10);

         for(int i = 0; i < nbttaglist.tagCount(); ++i) {
            FW attributemodifier = readAttributeModifierFromNBT(nbttaglist.getCompoundTagAt(i));
            if (attributemodifier != null) {
               FW attributemodifier1 = instance.getModifier(attributemodifier.getID());
               if (attributemodifier1 != null) {
                  instance.removeModifier(attributemodifier1);
               }

               instance.applyModifier(attributemodifier);
            }
         }
      }

   }

   @Nullable
   public static FW readAttributeModifierFromNBT(QQ compound) {
      UUID uuid = compound.getUniqueId("UUID");

      try {
         return new FW(uuid, compound.getString("Name"), compound.getDouble("Amount"), compound.getInteger("Operation"));
      } catch (Exception var3) {
         Exception exception = var3;
         LOGGER.warn("Unable to create attribute: {}", exception.getMessage());
         return null;
      }
   }
}
