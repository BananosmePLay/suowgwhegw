package neo;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

public class 0bc extends 0ba {
   private static int _DSC GG NEOWARECLIENT _;

   public _bc/* $FF was: 0bc*/() {
      super(method_Lz("ٟپٽٺٮٷٯ"));
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_Lz(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 29168 ^ -22185 ^ 1541 ^ -8542; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 13777 ^ 19581 ^ 31862 ^ 961));
      }

      return var1.toString();
   }

   public void method_Lt(0da a) throws IOException {
      Desktop.getDesktop().browse(URI.create(a.method_bzz()));
   }
}
