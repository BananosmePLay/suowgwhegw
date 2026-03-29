package neo;

import java.io.File;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class 0dH {
   public final 0dF seleniumData;

   private static 0dF scmpsIeXX1(0dH var0) {
      return var0.seleniumData;
   }

   private static 0bv _SVBNOLrLW/* $FF was: 7SVBNOLrLW*/() {
      return 0cf.useProxy;
   }

   public 0dF getSeleniumData() {
      return MyvbFv9MFI(this);
   }

   private static 0dF DZLnv4w5Kb(0dH var0) {
      return var0.seleniumData;
   }

   private static 0dF dICqNcBvZ6(0dH var0) {
      return var0.seleniumData;
   }

   private static 0dF d72Dyof7yw(0dH var0) {
      return var0.seleniumData;
   }

   private static 0dF _jI9Jcp3Z4/* $FF was: 4jI9Jcp3Z4*/(0dH var0) {
      return var0.seleniumData;
   }

   public _dH/* $FF was: 0dH*/(0dF seleniumData) {
      this.seleniumData = seleniumData;
   }

   private static 0bv zxN4BQg6rT() {
      return 0cf.useProxy;
   }

   private static 0by lxJWEFgvZx() {
      return 0cf.solverType;
   }

   private static 0dF _oIahi4e8d/* $FF was: 2oIahi4e8d*/(0dH var0) {
      return var0.seleniumData;
   }

   private static 0bA dHb4OXS51O() {
      return 0cf.chromePath;
   }

   private static 0bv G12FsxOBJw() {
      return 0cf.useProxy;
   }

   private static boolean GbH22hVTb5(0bv var0) {
      return var0.value;
   }

   private static boolean Nm9AOI4zV2(0bv var0) {
      return var0.value;
   }

   public void run() {
      StackTraceElement[] var2;
      int var3;
      int var4;
      StackTraceElement element;
      String proxyServer;
      if (3JlFXtbEfO().is(9fJFbycWjn("ҝүҨҋҚ҃"))) {
         try {
            Document proxyList = Jsoup.connect(eQBttFiTe9().get() + 9fJFbycWjn("ӥҽҥҸҡӤҺҢҺӵҩҦңүҤҾӷҤүҥҽҫҸүӬҿҸҦӷ") + dICqNcBvZ6(this).getUrl() + 9fJFbycWjn("ӬҺҸҥҲҳӷ") + (GbH22hVTb5(G12FsxOBJw()) ? 4jI9Jcp3Z4(this).getProxy().getProxy() : 9fJFbycWjn("ӺӤӺӤӺӤӺ")) + 9fJFbycWjn("ӬҾҳҺүӷ") + (Nm9AOI4zV2(zxN4BQg6rT()) ? DZLnv4w5Kb(this).getProxy().getType().name().toLowerCase() : 9fJFbycWjn("ҮңҸүҩҾ")) + 9fJFbycWjn("ӬҨҥҾҤңҩҡҤҫҧүӷ") + biGSBVD5aC(this).getBotName()).ignoreHttpErrors((boolean)(25491 ^ -22548 ^ 11066 ^ -4284)).get();
            proxyServer = proxyList.text();
            if (proxyServer.contains(9fJFbycWjn("ҬҫңҦүҮ"))) {
               0dK.formatMsg(9fJFbycWjn("ҝүҨҋҚ҃ҏҸҸҥҸӰӪ") + proxyServer.split(9fJFbycWjn("ӥ"))[14635 ^ -8251 ^ 7951 ^ -1568]);
            }
         } catch (Exception var7) {
            Exception exception = var7;
            0dK.formatMsg(9fJFbycWjn("ҝүҨҝҥҸҡҏҸҸҥҸӰӪ") + exception);
            var2 = exception.getStackTrace();
            var3 = var2.length;

            for(var4 = 31765 ^ -16841 ^ 25948 ^ -22658; var4 < var3; ++var4) {
               element = var2[var4];
               0dK.defaultMsg(9fJFbycWjn("ӬҩӪҫҾӪӬҩ") + element.toString());
            }

            exception.printStackTrace();
         }
      } else if (lxJWEFgvZx().is(9fJFbycWjn("҉ҢҸҥҧү"))) {
         try {
            String neoChromePath = (new File(eOAzI4QX1N(Minecraft.getMinecraft()), 9fJFbycWjn("ӥ҄үҥҝҫҸүӥ҉ҢҸҥҧүӥ"))).getAbsolutePath();
            proxyServer = 9fJFbycWjn("");
            if (qYtFwYTYwg(7SVBNOLrLW())) {
               proxyServer = 9fJFbycWjn("ӧӧҺҸҥҲҳӧҹүҸҼүҸӷӨ") + 2oIahi4e8d(this).getProxy().getType().name().toLowerCase() + 9fJFbycWjn("Ӱӥӥ") + scmpsIeXX1(this).getProxy().getProxy() + 9fJFbycWjn("ӨӪ");
            }

            String args = 9fJFbycWjn("Ө") + dHb4OXS51O().get() + 9fJFbycWjn("ӨӪӧӧңҭҤҥҸүӧҹҹҦӧүҸҸҥҸҹҸҹӪ") + proxyServer + 9fJFbycWjn("ӧӧҮңҹҫҨҦүӧҫҺҺҦңҩҫҾңҥҤӧҩҫҩҢүӪӧӧҧүҮңҫӧҩҫҩҢүӧҹңҰүӷӻӪӧӧҮңҹҡӧҩҫҩҢүӧҹңҰүӷӻӪӧӧҤҥӧҬңҸҹҾӧҸҿҤӪӧӧҫҸҭҹӪӧӧҿҹүҸӧҮҫҾҫӧҮңҸӷӨ") + neoChromePath + 9fJFbycWjn("ӥ") + TnZOlT2qTs(this).getBotName() + 9fJFbycWjn("ӨӪӨ") + d72Dyof7yw(this).getUrl() + 9fJFbycWjn("Ө");
            Process p = Runtime.getRuntime().exec(args);
            p.waitFor();
            int var14 = p.exitValue();
         } catch (RuntimeException | IOException | InterruptedException var6) {
            0dK.formatMsg(9fJFbycWjn("҉ҢҸҥҧүҏҸҸҥҸӰӪ") + var6);
            var2 = ((Exception)var6).getStackTrace();
            var3 = var2.length;

            for(var4 = 21472 ^ -30011 ^ 25737 ^ -16980; var4 < var3; ++var4) {
               element = var2[var4];
               0dK.defaultMsg(9fJFbycWjn("ӬҩӪҫҾӪӬҩ") + element.toString());
            }
         }
      }

   }

   private static 0dF biGSBVD5aC(0dH var0) {
      return var0.seleniumData;
   }

   private static 0bA eQBttFiTe9() {
      return 0cf.webApi;
   }

   private static File eOAzI4QX1N(Minecraft var0) {
      return var0.gameDir;
   }

   private static 0by _JlFXtbEfO/* $FF was: 3JlFXtbEfO*/() {
      return 0cf.solverType;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String _fJFbycWjn/* $FF was: 9fJFbycWjn*/(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 32054 ^ -12071 ^ 5591 ^ -18376; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 27114 ^ -26520 ^ 782 ^ -2490));
      }

      return var1.toString();
   }

   private static 0dF TnZOlT2qTs(0dH var0) {
      return var0.seleniumData;
   }

   private static boolean qYtFwYTYwg(0bv var0) {
      return var0.value;
   }

   private static 0dF MyvbFv9MFI(0dH var0) {
      return var0.seleniumData;
   }
}
