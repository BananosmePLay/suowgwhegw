package neo;

import java.util.function.Supplier;

public class 0bx extends 0bC {
   public String[] lines;

   public _bx/* $FF was: 0bx*/(String... lines) {
      super(bXUXqrwbvk(""));
      this.lines = lines;
      this.setVisible(() -> {
         return Boolean.valueOf((boolean)(25492 ^ -29579 ^ 31528 ^ -27448));
      });
   }

   public _bx/* $FF was: 0bx*/(Supplier<Boolean> visible, String... lines) {
      super(bXUXqrwbvk(""));
      this.lines = lines;
      this.setVisible(visible);
   }

   private static String[] tYqwlbSLvZ(0bx var0) {
      return var0.lines;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String bXUXqrwbvk(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 10067 ^ -11482 ^ 25785 ^ -28468; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 16528 ^ -29362 ^ 27831 ^ -23074));
      }

      return var1.toString();
   }

   public String[] getLines() {
      return tYqwlbSLvZ(this);
   }
}
