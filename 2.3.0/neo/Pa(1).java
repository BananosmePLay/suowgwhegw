package neo;

public class Pa extends OL {
   public Pa() {
   }

   public boolean isEnchantable(Qy stack) {
      return stack.getCount() == 1;
   }

   public int getItemEnchantability() {
      return 1;
   }
}
