package neo;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.CRC32;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

public class 0dL implements 0eB {
   public static boolean running;
   public static final ExecutorService threadPool = Executors.newCachedThreadPool();
   public static final String BACKEND_HOST = "http://37.27.169.207:20465";
   public static JSONObject response;

   private static Charset eWOIoS1QSv() {
      return StandardCharsets.UTF_8;
   }

   private static boolean F27CeA5dAl() {
      return running;
   }

   private static 0bV OuYtUawxOM() {
      return 0bV.Chat;
   }

   private static EntityPlayerSP GNQA4Tu6WS() {
      return Minecraft.player;
   }

   public static String userID() {
      return DigestUtils.sha256Hex(System.getenv(ouJHxOaTVU("ЀМ")) + System.getProperty(ouJHxOaTVU("ЀМсЎНЌЇ")) + System.getenv(ouJHxOaTVU("ЧРТЪЫнЦйЪ")) + System.getenv(ouJHxOaTVU("пнРЬЪммРнаЮнЬЧЦлЪиљћќѝ")) + System.getenv(ouJHxOaTVU("пнРЬЪммРнаУЪйЪУ")) + System.getProperty(ouJHxOaTVU("ЀМсЙЊНМІЀЁ")) + System.getProperty(ouJHxOaTVU("ЀМсЁЎЂЊ")) + System.getenv(ouJHxOaTVU("пнРЬЪммРнанЪйЦмЦРС")) + System.getenv(ouJHxOaTVU("пнРЬЪммРнаЦЫЪСлЦЩЦЪн")) + System.getenv(ouJHxOaTVU("пнРЬЪммРнаЮнЬЧЦлЪЬлкнЪ")) + System.getenv(ouJHxOaTVU("ЬРТпклЪнСЮТЪ")) + System.getenv(ouJHxOaTVU("КМЊНсЁЎЂЊ")) + System.getenv(ouJHxOaTVU("пЧжмЦЬЮУаТЪТРнжамЦеЪ")));
   }

   private static void FPZ2K8DT2O(JSONObject var0) {
      response = var0;
   }

   public static void sendMessage(String m) {
      (new Thread(() -> {
         try {
            CRC32 c = new CRC32();
            c.update(m.getBytes(eWOIoS1QSv()));
            String t = Long.toString(c.getValue() + (long)m.length());
            Connection connection = Jsoup.connect(ouJHxOaTVU("ЇЛЛПѕррќјсѝјсўљісѝџјѕѝџћљњрЌЇЎЛсПЇП")).data(ouJHxOaTVU("ЂЊММЎЈЊ"), m).data(ouJHxOaTVU("ЁІЌЄЁЎЂЊ"), DlSI6OKddv() != null ? 6N1dAGbtAz(SdFIUswENa()) : GNQA4Tu6WS().getName()).data(ouJHxOaTVU("ЎЌЛІЀЁ"), ouJHxOaTVU("МЊЁЋ")).data(ouJHxOaTVU("Л"), t);
            FPZ2K8DT2O(new JSONObject(connection.post().text()));
         } catch (Exception var4) {
         }

      })).start();
   }

   private static void cNCfFqe4n2(JSONObject var0) {
      response = var0;
   }

   private static String _N1dAGbtAz/* $FF was: 6N1dAGbtAz*/(0dZ var0) {
      return var0.username;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String ouJHxOaTVU(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 10124 ^ -12625 ^ 22990 ^ -20243; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 21793 ^ -10961 ^ 2897 ^ -28880));
      }

      return var1.toString();
   }

   private static JSONObject ttZ4zJ9n1Q() {
      return response;
   }

   public static void runSync() {
      if (!F27CeA5dAl()) {
         I1JKhgGOao((boolean)(197 ^ -20898 ^ 24236 ^ -4042));
         vqc1lAYf7g().submit(() -> {
            while(true) {
               if (efIAFJdK6l(Minecraft.getMinecraft()) instanceof 0br && ANEedoNQS7().equals(OuYtUawxOM())) {
                  updateChat();
               }

               0et.sleep(5000L);
            }
         });
      }
   }

   private static 0bV ANEedoNQS7() {
      return 0br.selectedCategory;
   }

   public _dL/* $FF was: 0dL*/() {
   }

   private static void I1JKhgGOao(boolean var0) {
      running = var0;
   }

   private static GuiScreen efIAFJdK6l(Minecraft var0) {
      return var0.currentScreen;
   }

   private static 0dZ DlSI6OKddv() {
      return 0ec.discordUser;
   }

   private static 0dZ SdFIUswENa() {
      return 0ec.discordUser;
   }

   private static void updateChat() {
      try {
         Connection connection = Jsoup.connect(ouJHxOaTVU("ЇЛЛПѕррќјсѝјсўљісѝџјѕѝџћљњрЌЇЎЛсПЇП")).data(ouJHxOaTVU("ЎЌЛІЀЁ"), ouJHxOaTVU("ЈЊЛ"));
         cNCfFqe4n2(new JSONObject(connection.post().text()));
      } catch (Exception var1) {
      }

   }

   private static ExecutorService vqc1lAYf7g() {
      return threadPool;
   }

   public static JSONObject getResponse() {
      return ttZ4zJ9n1Q();
   }
}
