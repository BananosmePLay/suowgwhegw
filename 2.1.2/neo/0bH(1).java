package neo;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.network.ServerPinger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class 0bH {
   public static final ServerPinger serverPinger = new ServerPinger();
   public static final List<ServerData> servers = new CopyOnWriteArrayList();

   private static String _wWWq9LbrW/* $FF was: 1wWWq9LbrW*/(ServerData var0) {
      return var0.serverIP;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String xWSVffFROq(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 22679 ^ -10109 ^ 25673 ^ -7075; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 13731 ^ -30612 ^ 21089 ^ -5979));
      }

      return var1.toString();
   }

   public static void init() {
      Thread thread = new Thread(() -> {
         parseServers();
         Iterator var0 = mXjBlwry1Y().iterator();

         while(var0.hasNext()) {
            ServerData serverData = (ServerData)var0.next();

            try {
               MSl0Y6DLe4().ping(serverData, (boolean)(6678 ^ -14706 ^ 1633 ^ -9480));
               V21IWGq7LD().println(dybTtgADml(serverData) + xWSVffFROq("ܫܵܵܫ") + roiDHEAblw(serverData));
            } catch (Exception var3) {
               Exception e = var3;
               e.printStackTrace();
            }
         }

      });
      thread.setName(xWSVffFROq("ݘݮݹݽݮݹݛݪݹݸݮݹܦ") + 0ej.randomNumber(30410 ^ -3734 ^ 11578 ^ -21857));
      thread.start();
   }

   private static List nYGLdBcuvF() {
      return servers;
   }

   private static List WUDklr77rW() {
      return servers;
   }

   private static String _TxUKOvFJK/* $FF was: 8TxUKOvFJK*/(ServerData var0) {
      return var0.serverIP;
   }

   public static List<ServerData> getServers() {
      return (List)tFw2lbOgq7().stream().filter((serverData) -> {
         return (boolean)(zRwS5qJtFW(serverData) > 0L ? 29148 ^ -6182 ^ 25082 ^ -2051 : 7063 ^ -10563 ^ 19602 ^ -32328);
      }).collect(Collectors.toList());
   }

   private static ServerPinger MSl0Y6DLe4() {
      return serverPinger;
   }

   private static long zRwS5qJtFW(ServerData var0) {
      return var0.pingToServer;
   }

   private static List mXjBlwry1Y() {
      return servers;
   }

   private static String dybTtgADml(ServerData var0) {
      return var0.serverIP;
   }

   private static List tFw2lbOgq7() {
      return servers;
   }

   private static String roiDHEAblw(ServerData var0) {
      return var0.serverMOTD;
   }

   private static void parseServers() {
      String[] var10000 = new String[24243 ^ -20049 ^ 16967 ^ -21160];
      var10000[23815 ^ -29292 ^ 27559 ^ -17612] = xWSVffFROq("ݣݿݿݻݸܱܤܤݦݢݥݮݨݹݪݭݿݹݪݿݢݥݬܥݹݾܤݥݮݼܦݸݮݹݽݮݹݸܤܺܥܹܺܥܹ");
      var10000[19718 ^ -27676 ^ 2708 ^ -11145] = xWSVffFROq("ݣݿݿݻݸܱܤܤݦݢݥݮݨݹݪݭݿݹݪݿݢݥݬܥݹݾܤݥݮݼܦݸݮݹݽݮݹݸܤܺܥܹܺܥܹ");
      var10000[904 ^ -29018 ^ 382 ^ -29614] = xWSVffFROq("ݣݿݿݻݸܱܤܤݦݢݥݮݨݹݪݭݿݹݪݿݢݥݬܥݹݾܤݥݮݼܦݸݮݹݽݮݹݸܤ");
      String[] var0 = var10000;
      int var1 = var0.length;

      int var2;
      String url;
      Document document;
      Elements elements;
      Iterator var6;
      Element element;
      String server;
      ServerData serverData;
      for(var2 = 31947 ^ -11889 ^ 730 ^ -20578; var2 < var1; ++var2) {
         url = var0[var2];

         try {
            document = Jsoup.connect(url).get();
            elements = document.getElementsByAttributeValue(xWSVffFROq("ݨݧݪݸݸ"), xWSVffFROq("ݿݤݤݧݿݢݻ"));
            var6 = elements.iterator();

            while(var6.hasNext()) {
               element = (Element)var6.next();
               if (element.text().contains(xWSVffFROq("ܥ"))) {
                  server = element.text();
                  serverData = new ServerData(server, server, (boolean)(26329 ^ -29247 ^ 14831 ^ -11529));
                  if (!contains(serverData)) {
                     WUDklr77rW().add(serverData);
                  }
               }
            }
         } catch (Throwable var11) {
         }
      }

      var10000 = new String[29738 ^ -27774 ^ 6602 ^ -415];
      var10000[16427 ^ -9416 ^ 22893 ^ -15746] = xWSVffFROq("ݣݿݿݻݸܱܤܤݦݢݸݿݮݹݧݪݾݥݨݣݮݹܥݤݹݬܤݸݮݹݽݮݹݪܦݥݤݽݲݮܦܺܥܹܺܥܹܤ");
      var10000[3852 ^ -2736 ^ 29184 ^ -30627] = xWSVffFROq("ݣݿݿݻݸܱܤܤݦݢݸݿݮݹݧݪݾݥݨݣݮݹܥݤݹݬܤݸݮݹݽݮݹݪܦݥݤݽݲݮܦܺܥܺܽܥܾܤ");
      var10000[26716 ^ -5856 ^ 1168 ^ -31250] = xWSVffFROq("ݣݿݿݻݸܱܤܤݦݢݸݿݮݹݧݪݾݥݨݣݮݹܥݤݹݬܤݸݮݹݽݮݹݪܦݥݤݽݲݮܤ");
      var0 = var10000;
      var1 = var0.length;

      for(var2 = 22335 ^ -10212 ^ 10737 ^ -22830; var2 < var1; ++var2) {
         url = var0[var2];

         try {
            document = Jsoup.connect(url).get();
            elements = document.getElementsByAttributeValue(xWSVffFROq("ݯݪݿݪܦݿݤݬݬݧݮ"), xWSVffFROq("ݿݤݤݧݿݢݻ"));
            var6 = elements.iterator();

            while(var6.hasNext()) {
               element = (Element)var6.next();
               if (element.text().contains(xWSVffFROq("ܥ"))) {
                  server = element.text();
                  serverData = new ServerData(server, server, (boolean)(21199 ^ -23806 ^ 28927 ^ -32462));
                  if (!contains(serverData)) {
                     1wstXz4ouT().add(serverData);
                  }
               }
            }
         } catch (Throwable var10) {
         }
      }

   }

   public _bH/* $FF was: 0bH*/() {
   }

   private static PrintStream V21IWGq7LD() {
      return System.out;
   }

   private static List _wstXz4ouT/* $FF was: 1wstXz4ouT*/() {
      return servers;
   }

   public static boolean contains(ServerData server) {
      Iterator var1 = nYGLdBcuvF().iterator();

      ServerData serverData;
      do {
         if (!var1.hasNext()) {
            return (boolean)(23146 ^ -10107 ^ 9385 ^ -22970);
         }

         serverData = (ServerData)var1.next();
      } while(!8TxUKOvFJK(serverData).equalsIgnoreCase(1wWWq9LbrW(server)));

      return (boolean)(6025 ^ -32134 ^ 13149 ^ -22865);
   }
}
