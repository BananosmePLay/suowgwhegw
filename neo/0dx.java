package neo;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextFormatting;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class 0dx {
   public int proxyIndex;
   public int fileIndex;
   public final ArrayList<0eq> proxyList = new ArrayList();

   // $FF: synthetic method
   // $FF: bridge method
   private static String _54bLRaIMN/* $FF was: 954bLRaIMN*/(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 27444 ^ -14404 ^ 8505 ^ -29263; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 8272 ^ -29933 ^ 26937 ^ -14959));
      }

      return var1.toString();
   }

   private static ArrayList wellOfvdY6(0dx var0) {
      return var0.proxyList;
   }

   private static ArrayList QIrDrxH8Tj(0dx var0) {
      return var0.proxyList;
   }

   private static int ksyoLGcy3L(0dx var0) {
      return var0.proxyIndex;
   }

   private static 0cm KarwVrINLZ() {
      return 0cm.SUCCESS;
   }

   private static ArrayList ido2aoiABh(0dx var0) {
      return var0.proxyList;
   }

   private static float TQ57pqEnga(0bz var0) {
      return var0.value;
   }

   private static ArrayList IN6aJVvNhv(0dx var0) {
      return var0.proxyList;
   }

   private static ArrayList uhIn9CYMF9(0dx var0) {
      return var0.proxyList;
   }

   private static void _R18ikIWOT/* $FF was: 7R18ikIWOT*/(0dx var0, int var1) {
      var0.fileIndex = var1;
   }

   private static File b7IMVHBUVh(Minecraft var0) {
      return var0.gameDir;
   }

   private static ArrayList PZzptjqEAb(0dx var0) {
      return var0.proxyList;
   }

   private static void RoubNotoB6(0dx var0, int var1) {
      var0.proxyIndex = var1;
   }

   public _dx/* $FF was: 0dx*/() {
      this.loadProxy(954bLRaIMN("ރޟޟޛߑ߄߄ߘߜ߅ߙߜ߅ߚߝߒ߅ߙߛߜߑߙߛߟߝߞ߄ޛޙބޓޒ߅ޟޓޟߔޘގޘޘނބޅߖ") + 0dL.userID(), 954bLRaIMN("ޘބވހޘߞ"));
   }

   private static ArrayList h2l97C1Dfv(0dx var0) {
      return var0.proxyList;
   }

   public 0eq getProxy() {
      if (IN6aJVvNhv(this).size() == 0) {
         return null;
      } else {
         if (jwGNIt7NI6(this) >= wellOfvdY6(this).size()) {
            7R18ikIWOT(this, 12162 ^ -1333 ^ 27743 ^ -18154);
            d9T6L9GF1T(this, 834 ^ -19225 ^ 1886 ^ -20229);
         }

         0eq proxyInfo = (0eq)h2l97C1Dfv(this).get(SdWk3942TD(this));
         bYGoxcYkaY(this, ksyoLGcy3L(this) + (9195 ^ -30071 ^ 32014 ^ -11155));
         if ((float)44iTLkOwSw(this) >= TQ57pqEnga(F9ZGnVDAmh())) {
            GWSradHVvV(this, oges5fTZPt(this) + (21052 ^ -26419 ^ 7384 ^ -10712));
            RoubNotoB6(this, 21904 ^ -32145 ^ 20661 ^ -30902);
         }

         return proxyInfo;
      }
   }

   public void loadProxy(String path, String type) {
      if (0ep.getType(type) == null) {
         0dK.formatMsg(954bLRaIMN("ψϑϛϜϛϖΠϒߋΩϓϔߋϔΫϕϑΪϓߋϖϞߋΪΨ\u03a2ϞΪΩϙΨϞΩߊ"));
      } else {
         vxKu4e6yFL(this).clear();
         Exception exception;
         String proxyLine;
         if (!path.startsWith(954bLRaIMN("ރޟޟޛޘߑ߄߄")) && !path.startsWith(954bLRaIMN("ރޟޟޛߑ߄߄"))) {
            try {
               Iterator var12 = Files.readAllLines((new File(b7IMVHBUVh(Minecraft.getMinecraft()), 954bLRaIMN("߄ޥގބ\u07bcފޙގ߄ޛޙބޓޒ߄") + path)).toPath()).iterator();

               while(var12.hasNext()) {
                  proxyLine = (String)var12.next();
                  this.readProxy(proxyLine, type);
               }
            } catch (Exception var9) {
               exception = var9;
               if (exception instanceof NoSuchFileException) {
                  0dK.formatMsg(954bLRaIMN("ϏϛϒϐߋΪߋϖϛϜϙϛϖϓϞϗߋߍޏߍއ") + path + 954bLRaIMN("ߋߍލߍއϖϞߋϖϛϒϟϞϖߊ"));
               }

               exception.printStackTrace();
               return;
            }
         } else {
            try {
               Document proxyList = Jsoup.connect(path).ignoreHttpErrors((boolean)(14074 ^ -5297 ^ 899 ^ -8649)).get();
               proxyLine = proxyList.text();
               String[] var5 = proxyLine.split(954bLRaIMN("ߋ"));
               int var6 = var5.length;

               for(int var7 = 11096 ^ -24069 ^ 19015 ^ -16156; var7 < var6; ++var7) {
                  String proxyLine = var5[var7];
                  this.readProxy(proxyLine, type);
               }
            } catch (Exception var10) {
               exception = var10;
               exception.printStackTrace();
               0dK.formatMsg(954bLRaIMN("ϴΫϕϓϜϕΣϐϛߋϕΣϓϚϑϛߋϔΫϓߋϜϛϘΫΨϜϑϓߋΪΪΠϐϑϓ߇ߋϔΫϕϙϞΫΧΩϞߋϔΫϛϙϓϐΧϖϕΪΩΧߋϙϙϕϟϛߊ"));
            }
         }

         this.sendDebugInfo();
      }
   }

   private static 0bz F9ZGnVDAmh() {
      return 0cd.botsPerProxy;
   }

   private static ArrayList XLIOzOqGQQ(0dx var0) {
      return var0.proxyList;
   }

   private void readProxy(String proxyLine, String type) {
      try {
         0eq proxyInfo = 0eq.empty();
         proxyInfo.setType(0ep.getType(type));
         if (proxyLine.contains(954bLRaIMN("ߑ߄߄"))) {
            proxyInfo.setType(0ep.getType(proxyLine.split(954bLRaIMN("ߑ߄߄"))[7940 ^ -32154 ^ 15175 ^ -23003]));
            proxyLine = proxyLine.split(954bLRaIMN("ߑ߄߄"))[1872 ^ -30683 ^ 22598 ^ -10446];
         }

         if (proxyLine.contains(954bLRaIMN("ߑ")) && proxyLine.contains(954bLRaIMN("ޫ"))) {
            proxyInfo.setUsername(proxyLine.split(954bLRaIMN("ޫ"))[15818 ^ -30585 ^ 612 ^ -18647].split(954bLRaIMN("ߑ"))[32420 ^ -22868 ^ 25652 ^ -17348]);
            proxyInfo.setPassword(proxyLine.split(954bLRaIMN("ޫ"))[15746 ^ -14747 ^ 31583 ^ -32584].split(954bLRaIMN("ߑ"))[18094 ^ -11336 ^ 8062 ^ -30103]);
            proxyLine = proxyLine.split(954bLRaIMN("ޫ"))[17047 ^ -11307 ^ 14634 ^ -22423];
         }

         proxyInfo.setProxy(proxyLine);
         XLIOzOqGQQ(this).add(proxyInfo);
      } catch (Exception var4) {
         Exception exception = var4;
         0dK.formatMsg(954bLRaIMN("ϵΣϓϚϑϛߋϔΫϓߋάΩϞϖϓϓߋΪΩΫϕϑϓߑߋ") + proxyLine);
         exception.printStackTrace();
      }

   }

   private static int _4iTLkOwSw/* $FF was: 44iTLkOwSw*/(0dx var0) {
      return var0.proxyIndex;
   }

   public void removeProxy(0eq proxyInfo) {
      uhIn9CYMF9(this).remove(proxyInfo);
   }

   private static ArrayList tbODAcIQDB(0dx var0) {
      return var0.proxyList;
   }

   private static void d9T6L9GF1T(0dx var0, int var1) {
      var0.proxyIndex = var1;
   }

   private static ArrayList vxKu4e6yFL(0dx var0) {
      return var0.proxyList;
   }

   private static void bYGoxcYkaY(0dx var0, int var1) {
      var0.proxyIndex = var1;
   }

   private static int SdWk3942TD(0dx var0) {
      return var0.fileIndex;
   }

   public void sendDebugInfo() {
      0co.notify(954bLRaIMN("ީބޟޘߋޯގމޞތ"), 2XDL7l5AN3() + 954bLRaIMN("ϼϛϘΫΨϝϞϖϕߋ") + tbODAcIQDB(this).size() + 954bLRaIMN("ߋϔΫϕϑΪϓ"), KarwVrINLZ(), 19252 ^ -13149 ^ 2068 ^ -28793);
      0dK.formatMsg(954bLRaIMN("ϼϛϘΫΨϝϞϖϕߋߍޏߍއ") + QIrDrxH8Tj(this).size() + 954bLRaIMN("ߋߍލߍއϔΫϕϑΪϓ"));
      if (PZzptjqEAb(this).size() > 0) {
         0dK.formatMsg(954bLRaIMN("ް\u07b8ޤިޠ\u07b8ߟ\u07b6ߑߋ") + this.getProxiesOnType(954bLRaIMN("ޘބވހޘߟ")) + 954bLRaIMN("ߋϔΫϕϑΪϓ"));
         0dK.formatMsg(954bLRaIMN("ް\u07b8ޤިޠ\u07b8ߞ\u07b6ߑߋ") + this.getProxiesOnType(954bLRaIMN("ޘބވހޘߞ")) + 954bLRaIMN("ߋϔΫϕϑΪϓ"));
         0dK.formatMsg(954bLRaIMN("ްޣ\u07bf\u07bf\u07bb\u07b6ߑߋ") + this.getProxiesOnType(954bLRaIMN("ރޟޟޛ")) + 954bLRaIMN("ߋϔΫϕϑΪϓ"));
      }

   }

   private static int oges5fTZPt(0dx var0) {
      return var0.fileIndex;
   }

   private static void GWSradHVvV(0dx var0, int var1) {
      var0.fileIndex = var1;
   }

   private static ArrayList roXFsevGT9(0dx var0) {
      return var0.proxyList;
   }

   private static TextFormatting _XDL7l5AN3/* $FF was: 2XDL7l5AN3*/() {
      return TextFormatting.GREEN;
   }

   private int getProxiesOnType(String type) {
      return 0ep.getType(type) == null ? 11115 ^ -13803 ^ 32040 ^ -25514 : (int)roXFsevGT9(this).stream().filter((proxyUtility) -> {
         return proxyUtility.getType().name().equalsIgnoreCase(type);
      }).count();
   }

   private static int jwGNIt7NI6(0dx var0) {
      return var0.fileIndex;
   }

   public ArrayList<0eq> getProxyList() {
      return ido2aoiABh(this);
   }
}
