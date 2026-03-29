package neo;

public class 0V implements 0cB {
   private static int _DSC GG NEOWARECLIENT _;

   public static void register(String a, String b) {
      0M c = BtZmW0mRvG().method_bwi().method_kT(a);
      if (c == null) {
         IOp2v4TXYT().method_bwi().method_kS(new 0O(a, b));
      }
   }

   public static String format(String a) {
      return tr02CEYOAZ().method_bwi().method_kR(a);
   }

   public static void unregister(String a) {
      0M b = DTlqhJJ2RT().method_bwi().method_kT(a);
      if (b != null) {
         if (b instanceof 0O) {
            y815rJ5gUr().method_bwi().remove(b);
         }
      }
   }

   private static 0cG y815rJ5gUr() {
      return botManager;
   }

   public _V/* $FF was: 0V*/() {
   }

   private static 0cG tr02CEYOAZ() {
      return botManager;
   }

   private static 0cG IOp2v4TXYT() {
      return botManager;
   }

   private static 0cG BtZmW0mRvG() {
      return botManager;
   }

   private static 0cG DTlqhJJ2RT() {
      return botManager;
   }
}
