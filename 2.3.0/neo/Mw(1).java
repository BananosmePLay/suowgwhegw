package neo;

import javax.annotation.Nullable;

public enum Mw {
   NONE(0),
   IRON(5, "iron", "meo"),
   GOLD(7, "gold", "goo"),
   DIAMOND(11, "diamond", "dio");

   private final String textureName;
   private final String hash;
   private final int protection;

   private Mw(int armorStrengthIn) {
      this.protection = armorStrengthIn;
      this.textureName = null;
      this.hash = "";
   }

   private Mw(int armorStrengthIn, String p_i46800_4_, String p_i46800_5_) {
      this.protection = armorStrengthIn;
      this.textureName = "textures/entity/horse/armor/horse_armor_" + p_i46800_4_ + ".png";
      this.hash = p_i46800_5_;
   }

   public int getOrdinal() {
      return this.ordinal();
   }

   public String getHash() {
      return this.hash;
   }

   public int getProtection() {
      return this.protection;
   }

   @Nullable
   public String getTextureName() {
      return this.textureName;
   }

   public static Mw getByOrdinal(int ordinal) {
      return values()[ordinal];
   }

   public static Mw getByItemStack(Qy stack) {
      return stack.isEmpty() ? NONE : getByItem(stack.getItem());
   }

   public static Mw getByItem(OL itemIn) {
      if (itemIn == NK.IRON_HORSE_ARMOR) {
         return IRON;
      } else if (itemIn == NK.GOLDEN_HORSE_ARMOR) {
         return GOLD;
      } else {
         return itemIn == NK.DIAMOND_HORSE_ARMOR ? DIAMOND : NONE;
      }
   }

   public static boolean isHorseArmor(OL itemIn) {
      return getByItem(itemIn) != NONE;
   }
}
