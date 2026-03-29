package neo;

import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class 0n extends 0j {
   private static String _ _;

   public _n/* $FF was: 0n*/() {
      super(method_T("ĄľĺħĻĲĘĔą"));
   }

   public void method_a(0cW a) throws Exception {
      Connection b = Jsoup.connect(method_T("ĿģģħĤŭŸŸĶħľůŹĸĴĥŹĤħĶĴĲŸħĶĥĤĲŸľĺĶİĲ")).ignoreContentType((boolean)(29315 ^ -13332 ^ 21309 ^ -5549)).header(method_T("ĥĲıĲĥĥĲĥ"), method_T("ĿģģħĤŭŸŸĸĴĥŹĤħĶĴĲŸ")).data(method_T("ĶħľļĲĮ"), method_T("ĜůŢťŮůůŤůŦůůŮŢŠ")).data(method_T("ĵĶĤĲšţĞĺĶİĲ"), method_T("ĳĶģĶŭľĺĶİĲŸħĹİŬĵĶĤĲšţŻ") + 0o.method_ba(a.getCaptcha())).data(method_T("ĻĶĹİĢĶİĲ"), method_T("ĲĹİ")).data(method_T("ľĤĘġĲĥĻĶĮąĲĦĢľĥĲĳ"), method_T("ģĥĢĲ")).data(method_T("đľĻĲăĮħĲ"), method_T("ŹĖĢģĸ")).data(method_T("ĞĤĔĥĲĶģĲĄĲĶĥĴĿĶĵĻĲćēđ"), method_T("ıĶĻĤĲ")).data(method_T("ľĤĄĲĶĥĴĿĶĵĻĲćĳığľĳĲăĲįģěĶĮĲĥ"), method_T("ıĶĻĤĲ")).data(method_T("ĳĲģĲĴģĘĥľĲĹģĶģľĸĹ"), method_T("ıĶĻĤĲ")).data(method_T("ľĤăĶĵĻĲ"), method_T("ıĶĻĤĲ")).data(method_T("ĤĴĶĻĲ"), method_T("ģĥĢĲ")).data(method_T("ĘĔąĒĹİľĹĲ"), method_T("Ţ")).data(method_T("ĳĲģĲĴģĔĿĲĴļĵĸį"), method_T("ıĶĻĤĲ")).data(method_T("ĴĿĲĴļĵĸįăĲĺħĻĶģĲ"), method_T("ŧ"));
      Document c = b.post();
      a.sendAnswer((new JSONObject(c.text())).getJSONArray(method_T("ćĶĥĤĲĳąĲĤĢĻģĤ")).getJSONObject(15916 ^ -23986 ^ 5250 ^ -30496).getString(method_T("ćĶĥĤĲĳăĲįģ")));
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_T(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 21663 ^ -30927 ^ 13964 ^ -6878; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 31034 ^ -13540 ^ 29666 ^ -16237));
      }

      return var1.toString();
   }
}
