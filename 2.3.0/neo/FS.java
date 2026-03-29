package neo;

import net.minecraft.inventory.EntityEquipmentSlot;

public enum FS {
   ALL {
      public boolean canEnchantItem(OL itemIn) {
         FS[] var2 = FS.values();
         int var3 = var2.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            FS enumenchantmenttype = var2[var4];
            if (enumenchantmenttype != FS.ALL && enumenchantmenttype.canEnchantItem(itemIn)) {
               return true;
            }
         }

         return false;
      }
   },
   ARMOR {
      public boolean canEnchantItem(OL itemIn) {
         return itemIn instanceof OR;
      }
   },
   ARMOR_FEET {
      public boolean canEnchantItem(OL itemIn) {
         return itemIn instanceof OR && ((OR)itemIn).armorType == EntityEquipmentSlot.FEET;
      }
   },
   ARMOR_LEGS {
      public boolean canEnchantItem(OL itemIn) {
         return itemIn instanceof OR && ((OR)itemIn).armorType == EntityEquipmentSlot.LEGS;
      }
   },
   ARMOR_CHEST {
      public boolean canEnchantItem(OL itemIn) {
         return itemIn instanceof OR && ((OR)itemIn).armorType == EntityEquipmentSlot.CHEST;
      }
   },
   ARMOR_HEAD {
      public boolean canEnchantItem(OL itemIn) {
         return itemIn instanceof OR && ((OR)itemIn).armorType == EntityEquipmentSlot.HEAD;
      }
   },
   WEAPON {
      public boolean canEnchantItem(OL itemIn) {
         return itemIn instanceof Qz;
      }
   },
   DIGGER {
      public boolean canEnchantItem(OL itemIn) {
         return itemIn instanceof QB;
      }
   },
   FISHING_ROD {
      public boolean canEnchantItem(OL itemIn) {
         return itemIn instanceof PG;
      }
   },
   BREAKABLE {
      public boolean canEnchantItem(OL itemIn) {
         return itemIn.isDamageable();
      }
   },
   BOW {
      public boolean canEnchantItem(OL itemIn) {
         return itemIn instanceof Pd;
      }
   },
   WEARABLE {
      public boolean canEnchantItem(OL itemIn) {
         boolean flag = itemIn instanceof OX && ((OX)itemIn).getBlock() instanceof fx;
         return itemIn instanceof OR || itemIn instanceof Pt || itemIn instanceof Qq || flag;
      }
   };

   private FS() {
   }

   public abstract boolean canEnchantItem(OL var1);

   // $FF: synthetic method
   FS(Object x2) {
      this();
   }
}
