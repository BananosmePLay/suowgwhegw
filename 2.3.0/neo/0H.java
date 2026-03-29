package neo;

import java.util.ArrayList;
import net.minecraft.util.math.BlockPos;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;

public class 0H {
   private static String _ _;

   public _H/* $FF was: 0H*/() {
   }

   public static BlockPos[] method_jN(String q, int r) {
      String var2 = q.toLowerCase();
      int var3 = -26256 ^ -9601 ^ 17440 ^ -1840;
      switch (var2.hashCode()) {
         case 3321844:
            if (var2.equals(method_jO("ѤѡѦѭ"))) {
               var3 = 29156 ^ -4149 ^ 30033 ^ -5250;
            }
            break;
         case 44880289:
            if (var2.equals(method_jO("ХѤѡѦѭ"))) {
               var3 = 16739 ^ -24824 ^ 26616 ^ -18030;
            }
      }

      switch (var3) {
         case 0:
            BlockPos[] b = new BlockPos[r];

            for(int a = 3483 ^ -21641 ^ 18082 ^ -8114; a < r; ++a) {
               b[a] = new BlockPos(a, 10213 ^ -18500 ^ 2178 ^ -26405, 5248 ^ -2913 ^ 20471 ^ -20504);
            }

            return b;
         case 1:
            BlockPos[] d = new BlockPos[r];

            for(int c = 15894 ^ -32509 ^ 18336 ^ -1867; c < r; ++c) {
               d[c] = new BlockPos(26477 ^ -31553 ^ 27377 ^ -30429, 10341 ^ -18998 ^ 9291 ^ -17948, c);
            }

            return d;
         default:
            try {
               String o;
               if (!q.startsWith(method_jO("ѠѼѼѸѻвЧЧ")) && !q.startsWith(method_jO("ѠѼѼѸвЧЧ"))) {
                  o = 0ee.readFile(0ed.method_bFf(method_jO("ЧцѭѧџѩѺѭЧѮѡѯѽѺѭѻЧ") + q + method_jO("ЦѢѻѧѦ")));
               } else {
                  o = Jsoup.connect(q).ignoreContentType((boolean)(2296 ^ -15714 ^ 28499 ^ -23244)).get().text();
               }

               if (!o.isEmpty()) {
                  JSONObject j = new JSONObject(o);
                  int k = j.getInt(method_jO("ѫѰ"));
                  int l = j.getInt(method_jO("ѫѱ"));
                  JSONArray m = j.getJSONArray(method_jO("ѥѩѼѺѡѰ"));
                  ArrayList<BlockPos> n = new ArrayList();

                  for(int i = 4235 ^ -15866 ^ 9308 ^ -2351; i < m.length(); ++i) {
                     String g = m.getString(i);
                     char[] h = g.toCharArray();

                     for(int f = 23381 ^ -3468 ^ 21062 ^ -1177; f < h.length; ++f) {
                        if (h[f] == (30244 ^ -1770 ^ 13946 ^ -18069)) {
                           n.add(new BlockPos(f - k, 29803 ^ -3778 ^ 6564 ^ -25359, i - l));
                        }
                     }
                  }

                  return (BlockPos[])n.toArray(new BlockPos[24 ^ -24469 ^ 2454 ^ -22043]);
               }
            } catch (Exception var16) {
               var16.printStackTrace();
            }

            return null;
      }
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_jO(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 4292 ^ -25005 ^ 26191 ^ -5928; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 26405 ^ -30274 ^ 26048 ^ -28845));
      }

      return var1.toString();
   }
}
