package neo;

import com.mojang.authlib.GameProfile;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.UUID;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.util.EnumHand;
import org.apache.commons.codec.digest.DigestUtils;

public class 0a extends 0cR {
   public 0g keyboard;
   public 0bi networkManager;
   public 0d mc;
   public final 0ei stimer2;
   public final 0ei spammer;
   public 0f player;
   public 0i controller;
   public 0dC proxy;
   public 0bk pinger;
   public final String host;
   public final int port;
   public final HashMap<String, Object> parameters;
   public final 0ei stimer1;
   public String windowTitle;
   public 0bl connection;
   public final GameProfile profile;
   public 0bo world;
   public final 0ei captcha;
   public final 0t botFunction;
   private static int _DSC GG NEOWARECLIENT _;

   public void stopBot() {
      0cG.method_bwd().method_bwg().method_j().method_bL(this);
      if (ZYGqWVvHtn(this) != null && IakQA1yKT3(this).isChannelOpen()) {
         rGsFTSg2ct(this).closeChannel();
      }

      IGx6UhWZ2o(this, (0i)null);
      9ygAWFiWeg(this, (0f)null);
      GzrTVp9ElS(this, (0bo)null);
      CWLqZibWoM(this, (0bl)null);
      3wymIbBlOi(this, (0d)null);
   }

   public Object getParameter(String a) {
      return fqu0NsWoDV(this).get(a);
   }

   private static RB Zl1aWSBNcN() {
      return RB.LOGIN;
   }

   private static 0d NFpCSxSnT0(0a var0) {
      return var0.mc;
   }

   private static Container p77O2R1PPo(0f var0) {
      return var0.openContainer;
   }

   private static 0cp jJbLveqSjH() {
      return 0bD.field_b;
   }

   private static HashMap CrtOwCiiLT(0a var0) {
      return var0.parameters;
   }

   private static void GzrTVp9ElS(0a var0, 0bo var1) {
      var0.world = var1;
   }

   private static 0bi yJUknan1xv(0a var0) {
      return var0.networkManager;
   }

   private static 0i UulY7diai1(0a var0) {
      return var0.controller;
   }

   private static 0cs _nAsONlAcN/* $FF was: 0nAsONlAcN*/() {
      return 0bC.field_f;
   }

   public 0t getFunction() {
      return 1ibDT1YJka(this);
   }

   private static 0dC im0GHNBYRk(0a var0) {
      return var0.proxy;
   }

   private static 0bi WVzM2IVIyi(0a var0) {
      return var0.networkManager;
   }

   private static 0f VWVPYOTtvj(0a var0) {
      return var0.player;
   }

   private static void AVFkjQo2UJ(0a var0, 0d var1) {
      var0.mc = var1;
   }

   private static int zMDv0ozKIp(0a var0) {
      return var0.port;
   }

   private static void CWLqZibWoM(0a var0, 0bl var1) {
      var0.connection = var1;
   }

   private static HashMap fqu0NsWoDV(0a var0) {
      return var0.parameters;
   }

   private static 0t _ibDT1YJka/* $FF was: 1ibDT1YJka*/(0a var0) {
      return var0.botFunction;
   }

   private static MJ fyyS7wTTwj(0f var0) {
      return var0.inventory;
   }

   private static boolean wBGMGdUZsa() {
      return 0bs.field_b;
   }

   private static 0ei TnU06ZDN7D(0a var0) {
      return var0.captcha;
   }

   private static 0bk W2Oy13PmF4(0a var0) {
      return var0.pinger;
   }

   public String getNickname() {
      return 1AclIapkDR(this).getName();
   }

   private static boolean YqNfTVTMbs() {
      return 0bs.field_b;
   }

   public GameProfile getProfile() {
      return DFHy7gQTEO(this);
   }

   public void windowClick(int a, int b, ClickType c) {
      method_byH(tAUFgu1our("҅Ҩҳ"), tAUFgu1our("ҰҮҩңҨҰ҄ҫҮҤҬӯҮҩҳӫӧҮҩҳӫӧ҄ҫҮҤҬғҾҷҢӮ"));
      if (this.isOnline()) {
         W447nlSSPa(this).windowClick(a, b, c);
      }

   }

   private static 0f BqpubEsVJd(0a var0) {
      return var0.player;
   }

   private static GameProfile DFHy7gQTEO(0a var0) {
      return var0.profile;
   }

   private static 0cu _4KByCL6Hf/* $FF was: 44KByCL6Hf*/() {
      return 0bD.field_h;
   }

   private static 0ei iDWfrX7wjl(0a var0) {
      return var0.spammer;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String tAUFgu1our(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 6613 ^ -14346 ^ 1349 ^ -9370; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 4206 ^ -16984 ^ 24612 ^ -14043));
      }

      return var1.toString();
   }

   private static void _wymIbBlOi/* $FF was: 3wymIbBlOi*/(0a var0, 0d var1) {
      var0.mc = var1;
   }

   private static 0cp UrR3jakWMq() {
      return 0bF.field_b;
   }

   private static 0i e766QSUUTE(0a var0) {
      return var0.controller;
   }

   private static 0f _qbXiOZu7a/* $FF was: 9qbXiOZu7a*/(0a var0) {
      return var0.player;
   }

   private static 0bi KHu14PQswg(0a var0) {
      return var0.networkManager;
   }

   private static 0ct _2cJSo3NeI/* $FF was: 02cJSo3NeI*/() {
      return 0bH.field_o;
   }

   private static 0cp _5qogGUNLB/* $FF was: 85qogGUNLB*/() {
      return 0bH.field_g;
   }

   public int getPort() {
      return gN6tsXLNvP(this);
   }

   private static 0f yTDAsF5eVw(0a var0) {
      return var0.player;
   }

   private static 0bo MaYBnl8zPr(0a var0) {
      return var0.world;
   }

   private static 0f r4SXNYjYTr(0a var0) {
      return var0.player;
   }

   private static 0bi _9BwwrqoAD/* $FF was: 79BwwrqoAD*/(0a var0) {
      return var0.networkManager;
   }

   private static Container BzgrlaebnH(0f var0) {
      return var0.openContainer;
   }

   private static 0f DhVDdYdN8F(0a var0) {
      return var0.player;
   }

   public _a/* $FF was: 0a*/(String a, 0dC b, String c, int d) {
      if (a.length() > (4366 ^ -7237 ^ 28348 ^ -25575)) {
         a = a.substring(29961 ^ -9607 ^ 18168 ^ -5752, 6301 ^ -15278 ^ 19711 ^ -28609);
      }

      this.keyboard = new 0g();
      this.parameters = new HashMap();
      this.botFunction = new 0t(this);
      this.profile = new GameProfile((UUID)null, a);
      this.proxy = b;
      this.host = c;
      this.port = d;
      this.captcha = new 0ei();
      this.spammer = new 0ei();
      this.stimer1 = new 0ei();
      this.stimer2 = new 0ei();
      this.initParameters();
      0e.add(this);
   }

   private static 0i W447nlSSPa(0a var0) {
      return var0.controller;
   }

   private static void n8Cc1GEpvO(0a var0, 0dC var1) {
      var0.proxy = var1;
   }

   private static void IGx6UhWZ2o(0a var0, 0i var1) {
      var0.controller = var1;
   }

   private static void DHNfvPnb8j(0a var0, 0bi var1) {
      var0.networkManager = var1;
   }

   public String getHost() {
      return Y6n3CabjmN(this);
   }

   private static HashMap ybh8hbrXSg(0a var0) {
      return var0.parameters;
   }

   private static 0cp gey4McPg2O() {
      return 0bH.field_j;
   }

   private static int gN6tsXLNvP(0a var0) {
      return var0.port;
   }

   private static 0cs _0yXhj8TJm/* $FF was: 90yXhj8TJm*/() {
      return 0bH.field_c;
   }

   public String getStringParameter(String a) {
      return (String)ybh8hbrXSg(this).get(a);
   }

   public void setParameter(String a, Object b) {
      if (b == null) {
         optLbPLgWv(this).remove(a);
      }

      Ow4xdpr7Jt(this).put(a, b);
   }

   private static String Y6n3CabjmN(0a var0) {
      return var0.host;
   }

   private static 0bk WwhVVBmTpE(0a var0) {
      return var0.pinger;
   }

   public void changeSlot(int a) {
      if (this.isOnline() && a >= 0 && a < (9039 ^ -32720 ^ 18412 ^ -7014)) {
         GBiGljgaYp(fyyS7wTTwj(82riYTDOzw(this)), a);
      }

   }

   private static 0bo R2xzNIrBaW(0a var0) {
      return var0.world;
   }

   private static HashMap Ow4xdpr7Jt(0a var0) {
      return var0.parameters;
   }

   private static void v4UL7IVoWA(0a var0, 0bk var1) {
      var0.pinger = var1;
   }

   private static 0f pJggrLIOx0(0a var0) {
      return var0.player;
   }

   private static 0i oZgvM5flkr(0a var0) {
      return var0.controller;
   }

   public boolean isOnline() {
      return (boolean)(1nIX1tlaTw(this) != null && KHu14PQswg(this) != null && BdfZl4gSII(this) != null && yJUknan1xv(this).isChannelOpen() ? 20342 ^ -12964 ^ 21708 ^ -10521 : 3296 ^ -10285 ^ 22369 ^ -29614);
   }

   private static 0bk ycJFTg2EfR(0a var0) {
      return var0.pinger;
   }

   private static 0f v25Lr422g7(0a var0) {
      return var0.player;
   }

   public void reconnect() {
      this.stopBot();
      if (ZiD46gDlaG().method_bnr(tAUFgu1our("҆ҡҳҢҵӧҕҢҍҨҮҩ"))) {
         this.setParameter(tAUFgu1our("ҤҦҤүҢң"), Boolean.valueOf((boolean)(22065 ^ -927 ^ 10861 ^ -32708)));
      }

      this.startBot();
   }

   private static 0bi rGsFTSg2ct(0a var0) {
      return var0.networkManager;
   }

   private static 0bi rNjv9JZTm5(0a var0) {
      return var0.networkManager;
   }

   public void disconnect() {
      this.stopBot();
      if (0bz.method_Qm().method_Qs().method_bxY(0bE.class).method_bBh()) {
         0bz.method_Qm().method_Qp().addScheduler(this::reconnect, (long)VAnYEeHAwi().method_bnH());
      }

   }

   private static 0bi IakQA1yKT3(0a var0) {
      return var0.networkManager;
   }

   private static int Q65LJ2Q74N(Container var0) {
      return var0.windowId;
   }

   public void startBot() {
      if (YqNfTVTMbs()) {
         this.setParameter(tAUFgu1our("ҤҦҷҳҤүҦңҢҳҢҤҳҢң"), Boolean.valueOf((boolean)(21129 ^ -18327 ^ 8414 ^ -13762)));
         this.setParameter(tAUFgu1our("ҫҦҴҳ҄ҦҷҳҤүҦ"), tAUFgu1our(""));
         if (90yXhj8TJm().method_bnr(tAUFgu1our("҆ҫҰҦҾҴ"))) {
            this.setParameter(tAUFgu1our("ҤҦҤүҢң"), Boolean.valueOf((boolean)(23304 ^ -7150 ^ 6680 ^ -23293)));
         }

         AVFkjQo2UJ(this, new 0d(this));
         Thread a = new Thread(() -> {
            if (85qogGUNLB().method_bna()) {
               v4UL7IVoWA(this, new 0bk(this));
               W2Oy13PmF4(this).ping();
            } else {
               this.connect();
            }

         });
         a.setName(tAUFgu1our("҅Ҩҳ҄ҨҩҩҢҤҳҨҵӪ") + this.getNickname() + tAUFgu1our("Ӫ"));
         a.start();
      }
   }

   private static EnumHand fFgjbgwdIe() {
      return EnumHand.MAIN_HAND;
   }

   public static void runBot(String a, 0dC b, String c, int d) {
      0a e = new 0a(a, b, c, d);
      e.startBot();
   }

   public void tick() {
      try {
         if (WwhVVBmTpE(this) != null) {
            ycJFTg2EfR(this).pingPendingNetworks();
         }

         if (e766QSUUTE(this) != null) {
            oZgvM5flkr(this).updateController();
         }

         if (ViniLVGbHv(this) != null) {
            AolYSm2lt4(this).method_cQ();
            if (NFpCSxSnT0(this) != null) {
               TyUAv7SZJz(this).runTickKeyboard();
               gGpg4GxvPY(this).execTasks();
            }

            if (v25Lr422g7(this).getHealth() <= Float.intBitsToFloat(2352 ^ '\udf9b' ^ '닢' ^ 1394738207 ^ '\ufaea' ^ '퀪' ^ 4970 ^ 1394760188)) {
               QAgzZyyAqj(this).respawnPlayer();
            }

            R2xzNIrBaW(this).tick();
            if (DlNYRJbPiB(this).getName().equalsIgnoreCase(this.getNickname()) && !RvqvGrSB6d(this).isElytraFlying() && !9qbXiOZu7a(this).isRiding()) {
               yTDAsF5eVw(this).onUpdate();
            } else {
               MaYBnl8zPr(this).updateEntities();
            }

            if (!this.getBooleanParameter(tAUFgu1our("ҤҦҷҳҤүҦңҢҳҢҤҳҢң")) && !this.getBooleanParameter(tAUFgu1our("ҦҲҳүҨҵҮҽҦҳҮҨҩ")) && TnU06ZDN7D(this).hasReached(28455 ^ -36680 ^ '\uecf0' ^ -3961)) {
               0cW a = 0r.method_bW(this);
               if (a != null) {
                  0cG.method_bwd().method_bwg().method_e(a);
               }
            }

            if (this.getParameter(tAUFgu1our("ҴҷҦҪҘңҢҫҦҾ")) != null && this.getParameter(tAUFgu1our("ҴҷҦҪҘҳҢҿҳ")) != null && iDWfrX7wjl(this).hasReached((Integer)this.getParameter(tAUFgu1our("ҴҷҦҪҘңҢҫҦҾ")))) {
               BqpubEsVJd(this).sendChatMessage(0V.format(this.getStringParameter(tAUFgu1our("ҴҷҦҪҘҳҢҿҳ"))));
            }

            0cG.method_bwd().method_bwh().forEach((ax) -> {
               String var10001 = tAUFgu1our("ҨҩҒҷңҦҳҢ");
               Object[] var10002 = new Object[6377 ^ -21814 ^ 9872 ^ -27470];
               var10002[23142 ^ -601 ^ 6971 ^ -17158] = this;
               ax.method_bCr(var10001, var10002);
            });
         }
      } catch (Exception var2) {
         Exception b = var2;
         b.printStackTrace();
      }

   }

   private static GameProfile _AclIapkDR/* $FF was: 1AclIapkDR*/(0a var0) {
      return var0.profile;
   }

   public boolean getBooleanParameter(String a) {
      return (Boolean)CrtOwCiiLT(this).get(a);
   }

   private static String PGMdaIMQrq(0a var0) {
      return var0.host;
   }

   private static 0bl BdfZl4gSII(0a var0) {
      return var0.connection;
   }

   private static 0f DlNYRJbPiB(0a var0) {
      return var0.player;
   }

   private static void _ygAWFiWeg/* $FF was: 9ygAWFiWeg*/(0a var0, 0f var1) {
      var0.player = var1;
   }

   private static 0f TV1nlYn9Yr(0a var0) {
      return var0.player;
   }

   private static 0ct VAnYEeHAwi() {
      return 0bE.field_a;
   }

   public void connect() {
      if (!this.getBooleanParameter(tAUFgu1our("ңҢҫҢҳҢң"))) {
         try {
            DHNfvPnb8j(this, 0bi.createNetworkManagerAndConnect(this, InetAddress.getByName(PGMdaIMQrq(this)), zMDv0ozKIp(this)));
            WVzM2IVIyi(this).setNetHandler(new 0bf(this));
            6hStS5rY4a(this).sendPacket(new RD(u64VSf6Jzb(this), VQmVorYvTK(this), Zl1aWSBNcN()));
            Thread.sleep(400L);
            if (CPjbzKmmoS(this) != null) {
               UkYULdpze6(this).sendPacket(new RK(this.getProfile()));
               if (Teey7b3txl().method_bna() && !79BwwrqoAD(this).isChannelOpen() && gey4McPg2O().method_bna() && wBGMGdUZsa()) {
                  n8Cc1GEpvO(this, 0cG.method_bwd().method_bwe().method_kg());
                  0bz.method_Qm().method_Qp().addScheduler(this::reconnect, (long)02cJSo3NeI().method_bnH());
               }
            }
         } catch (Exception var2) {
            Exception a = var2;
            a.printStackTrace();
         }

      }
   }

   private static 0cu jeN6yvrRa7() {
      return 0bD.field_c;
   }

   private static 0f rqMTHOJ0qS(0a var0) {
      return var0.player;
   }

   public void auth() {
      String a = tAUFgu1our("");
      String b = dlMVYLGfq8().method_bnq();
      int var3 = -13775 ^ -8159 ^ 835 ^ -10580;
      switch (b.hashCode()) {
         case -1517789585:
            if (b.equals(tAUFgu1our("ҩҮҤҬғҨҗҦҴҴ"))) {
               var3 = 11014 ^ -29084 ^ 1147 ^ -24293;
            }
            break;
         case -1349088399:
            if (b.equals(tAUFgu1our("ҤҲҴҳҨҪ"))) {
               var3 = 2030 ^ -2681 ^ 28533 ^ -25315;
            }
            break;
         case -938285885:
            if (b.equals(tAUFgu1our("ҵҦҩңҨҪ"))) {
               var3 = 12534 ^ -29784 ^ 28431 ^ -11183;
            }
      }

      switch (var3) {
         case 0:
            a = 0ec.randomString(15702 ^ -18190 ^ 21056 ^ -10270);
            break;
         case 1:
            a = 0V.format(jeN6yvrRa7().method_bnP());
            break;
         case 2:
            a = DigestUtils.md5Hex(this.getNickname().toLowerCase()).substring(20214 ^ -9627 ^ 8166 ^ -29835, 31391 ^ -29078 ^ 26658 ^ -25377);
      }

      if (!a.isEmpty()) {
         rqMTHOJ0qS(this).sendChatMessage(44KByCL6Hf().method_bnP().replace(tAUFgu1our("ӢҷҦҴҴ"), a));
         if (UrR3jakWMq().method_bna()) {
            String var10000 = tAUFgu1our("ҥҨҳөҦҲҳү");
            Object[] var10001 = new Object[20309 ^ -24130 ^ 23438 ^ -19100];
            var10001[5385 ^ -6723 ^ 31437 ^ -30087] = this.getNickname();
            0ek.addMessage(0cT.method_byW(var10000, var10001));
         }

         if (0nAsONlAcN().method_bnq().equalsIgnoreCase(tAUFgu1our("Ҩҩ҆Ҳҳү"))) {
            this.getFunction().method_cR(new 0E(this));
         }
      }

      b = a;
      0cG.method_bwd().method_bwh().forEach((bx) -> {
         String var10001 = tAUFgu1our("ҨҩҋҨҠҮҩ");
         Object[] var10002 = new Object[18991 ^ -18421 ^ 28892 ^ -32006];
         var10002[17747 ^ -14838 ^ 24962 ^ -7461] = this;
         var10002[30287 ^ -5890 ^ 20011 ^ -12133] = b;
         bx.method_bCr(var10001, var10002);
      });
      if (jJbLveqSjH().method_bna()) {
         0bz.method_Qm().method_Qp().addScheduler(() -> {
            0f var10000 = TV1nlYn9Yr(this);
            String var10001 = tAUFgu1our("ӨҫҨҠҮҩӧӢҴ");
            Object[] var10002 = new Object[23363 ^ -23166 ^ 5495 ^ -5193];
            var10002[17322 ^ -14770 ^ 15478 ^ -18030] = b;
            var10000.sendChatMessage(String.format(var10001, var10002));
         }, 2000L);
      }

   }

   private static 0cs dlMVYLGfq8() {
      return 0bD.field_a;
   }

   private static 0f _2riYTDOzw/* $FF was: 82riYTDOzw*/(0a var0) {
      return var0.player;
   }

   private static 0f _nIX1tlaTw/* $FF was: 1nIX1tlaTw*/(0a var0) {
      return var0.player;
   }

   public void closeWindow() {
      if (this.isOnline()) {
         VWVPYOTtvj(this).closeScreenAndDropStack();
         rNjv9JZTm5(this).sendPacket(new SJ(Q65LJ2Q74N(p77O2R1PPo(r4SXNYjYTr(this)))));
         VXrbJi10lA(BzgrlaebnH(pJggrLIOx0(this)), 28696 ^ -30576 ^ 14503 ^ -16337);
      }

   }

   private static 0bi ZYGqWVvHtn(0a var0) {
      return var0.networkManager;
   }

   private static 0d gGpg4GxvPY(0a var0) {
      return var0.mc;
   }

   public void useItem() {
      if (this.isOnline()) {
         UulY7diai1(this).processRightClick(fFgjbgwdIe());
      }

   }

   public boolean isCached() {
      return this.getBooleanParameter(tAUFgu1our("ҤҦҤүҢң"));
   }

   private static int VQmVorYvTK(0a var0) {
      return var0.port;
   }

   private static 0d TyUAv7SZJz(0a var0) {
      return var0.mc;
   }

   public void sendMessage(String a) {
      method_byH(tAUFgu1our("҅Ҩҳ"), tAUFgu1our("ҴҢҩңҊҢҴҴҦҠҢӯҔҳҵҮҩҠӮ"));
      if (this.isOnline()) {
         DhVDdYdN8F(this).sendChatMessage(a);
      }

   }

   private static String u64VSf6Jzb(0a var0) {
      return var0.host;
   }

   public void initParameters() {
      this.setParameter(tAUFgu1our("ҦҲҳүҨҵҮҽҦҳҮҨҩ"), Boolean.valueOf((boolean)(19321 ^ -1906 ^ 12084 ^ -25405)));
      this.setParameter(tAUFgu1our("ңҢҫҢҳҢң"), Boolean.valueOf((boolean)(2563 ^ -15866 ^ 7823 ^ -10614)));
      this.setParameter(tAUFgu1our("ҤҦҷҳҤүҦңҢҳҢҤҳҢң"), Boolean.valueOf((boolean)(26730 ^ -22737 ^ 19632 ^ -31755)));
      this.setParameter(tAUFgu1our("ҤҦҤүҢң"), Boolean.valueOf((boolean)(1567 ^ -25292 ^ 9467 ^ -16432)));
      this.setParameter(tAUFgu1our("ҰҢҥңҢҳҢҤҳҢң"), Boolean.valueOf((boolean)(5195 ^ -21499 ^ 8982 ^ -25768)));
      this.setParameter(tAUFgu1our("ҫҦҴҳ҄ҦҷҳҤүҦ"), tAUFgu1our(""));
   }

   private static 0bi UkYULdpze6(0a var0) {
      return var0.networkManager;
   }

   private static 0bi CPjbzKmmoS(0a var0) {
      return var0.networkManager;
   }

   private static 0bi _hStS5rY4a/* $FF was: 6hStS5rY4a*/(0a var0) {
      return var0.networkManager;
   }

   private static 0t AolYSm2lt4(0a var0) {
      return var0.botFunction;
   }

   public 0dC getProxy() {
      return im0GHNBYRk(this);
   }

   private static void VXrbJi10lA(Container var0, int var1) {
      var0.windowId = var1;
   }

   private static 0cs ZiD46gDlaG() {
      return 0bH.field_c;
   }

   private static void GBiGljgaYp(MJ var0, int var1) {
      var0.currentItem = var1;
   }

   private static 0f RvqvGrSB6d(0a var0) {
      return var0.player;
   }

   private static 0f ViniLVGbHv(0a var0) {
      return var0.player;
   }

   private static HashMap optLbPLgWv(0a var0) {
      return var0.parameters;
   }

   private static 0cp Teey7b3txl() {
      return 0bH.field_a;
   }

   private static 0f QAgzZyyAqj(0a var0) {
      return var0.player;
   }
}
