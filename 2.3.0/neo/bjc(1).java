package neo;

public class bjc implements biX {
   public bjc() {
   }

   public int parse(String str, int defVal) {
      Fa enchantment = Fa.getEnchantmentByLocation(str);
      return enchantment == null ? defVal : Fa.getEnchantmentID(enchantment);
   }
}
