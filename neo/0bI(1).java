package neo;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.GlStateManager;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.lwjgl.input.Keyboard;

public class 0bI extends GuiScreen {
   public GuiTextField passwordField;
   private final int offset = 26657 ^ -8197 ^ 31318 ^ -12904;
   public static int type = 14290 ^ -3509 ^ 6892 ^ -8330;
   public GuiTextField proxyField;
   public static String username = 2SgTjbqnii("");
   public static String proxy = 2SgTjbqnii("");
   public final Minecraft mc = Minecraft.getMinecraft();
   public static String password = 2SgTjbqnii("");
   public GuiTextField usernameField;

   private static int aTFgLd76d3(0bI var0) {
      return var0.width;
   }

   private static List FgnEdFo9dv(0bI var0) {
      return var0.buttonList;
   }

   private static GuiTextField _7yiJy1BIZ/* $FF was: 57yiJy1BIZ*/(0bI var0) {
      return var0.passwordField;
   }

   private static GuiTextField GOweNvGtNi(0bI var0) {
      return var0.passwordField;
   }

   private static int OBpN0Iargb(0bI var0) {
      return var0.height;
   }

   private static GuiTextField YLkYSNzkfe(0bI var0) {
      return var0.proxyField;
   }

   private static int rQ5q2yvqAG() {
      return type;
   }

   private static int NdE7St5Myo(0bI var0) {
      return var0.width;
   }

   private static GuiTextField elKbFG9OV7(0bI var0) {
      return var0.passwordField;
   }

   public static String keepNumbersAndColonOnly(String input) {
      return input.replaceAll(2SgTjbqnii("ʆʃʁʹ˧˳ʀ"), 2SgTjbqnii(""));
   }

   protected void keyTyped(char typedChar, int keyCode) throws IOException {
      super.keyTyped(typedChar, keyCode);
      if (keyCode == (11554 ^ -30301 ^ 251 ^ -23450)) {
         this.actionPerformed((GuiButton)g0itn6ULsY(this).get(30034 ^ -19255 ^ 28338 ^ -20696));
      }

      ouoLpZ4Nag(this).textboxKeyTyped(typedChar, keyCode);
      2a2C4VVtdw(this).textboxKeyTyped(typedChar, keyCode);
      Ztxxe2nltS(this).textboxKeyTyped(typedChar, keyCode);
   }

   private static GuiTextField _rmADg3FTJ/* $FF was: 2rmADg3FTJ*/(0bI var0) {
      return var0.proxyField;
   }

   private static List tAYQwDyOVw(0bI var0) {
      return var0.buttonList;
   }

   private static List _xJP5a7Apc/* $FF was: 2xJP5a7Apc*/(0bI var0) {
      return var0.buttonList;
   }

   private static GuiTextField vFjGSaj9Ib(0bI var0) {
      return var0.proxyField;
   }

   private static int dHOSaeJEuE(GuiButton var0) {
      return var0.id;
   }

   protected void actionPerformed(GuiButton button) throws IOException {
      if (2aDeYTJEbm(button) == 0) {
         String fieldText = keepNumbersAndColonOnly(YLkYSNzkfe(this).getText());
         if (fieldText.contains(2SgTjbqnii("˧")) && fieldText.split(2SgTjbqnii("˧")).length == (248 ^ -2726 ^ 1502 ^ -3970)) {
            if (JDmyetjTsn() == (30161 ^ -15735 ^ 14268 ^ -32537)) {
               hWmOT8W7Sr(11684 ^ -5922 ^ 1221 ^ -15938);
            }

            l4iWNuYaSd(fieldText);
            j9iW28H3Br(eGzqQbL6xn(this).getText());
            jwqqSohjqr(57yiJy1BIZ(this).getText());
         } else if (CJOySogjUz() != (17977 ^ -3979 ^ 417 ^ -18450)) {
            9ikRwaxbUZ(22256 ^ -12705 ^ 7387 ^ -31625);
            if (wRqWt4XitN(this).getText().length() != 0) {
               2tHltB0NvT(2SgTjbqnii("ɺʾʟʼʹ˽ʍʯʲʥʤ˽ʛʲʯʰʼʩʸ"));
            }
         }

         saveConfig();
         deYxX6yabr(this).displayGuiScreen(new GuiMultiplayer(new 0bE()));
      } else if (tO2Qb3VDVT(button) == (19577 ^ -2618 ^ 8748 ^ -25710)) {
         3LtjYS4rlb(this).setText(2SgTjbqnii(""));
         2GsBNqneD6(2SgTjbqnii(""));
         2M3m66Er4H(2SgTjbqnii(""));
         O3SdgtWy1S(2SgTjbqnii(""));
         o7CrWFoa05(30266 ^ -4655 ^ 20928 ^ -13784);
      } else if (dHOSaeJEuE(button) == (8969 ^ -2806 ^ 9927 ^ -3897)) {
         lgnDq7kdWG(wjFV9x6h7n() + (6483 ^ -6749 ^ 20190 ^ -19921));
         if (wyin7jZkTw() > (20058 ^ -22501 ^ 8049 ^ -1742)) {
            SpIpePTNw4(16119 ^ -32690 ^ 26584 ^ -9887);
         }
      } else if (y48gQOJQif(button) == (23048 ^ -10517 ^ 402 ^ -29323)) {
         0eq proxyInfo = 0bL.getInstance().getProxyManager().getProxy();
         if (proxyInfo != null) {
            mFMvi41v9D(getIdByType(proxyInfo.getType()));
            UYorJLbyNi(proxyInfo.getProxy());
            Ms2x17UdnY(proxyInfo.getUsername());
            RqcbMWLWJK(proxyInfo.getPassword());
            V9tGZb4HlJ(this).setText(AFwhFrA4Nn());
            BB6a25r7Lf(this).setText(proxyInfo.getUsername());
            fVgOc8YfTk(this).setText(proxyInfo.getPassword());
         }
      }

   }

   private static 0ep HgbiaeALVQ() {
      return 0ep.NO_PROXY;
   }

   private static GuiTextField ouoLpZ4Nag(0bI var0) {
      return var0.proxyField;
   }

   private static GuiTextField BB6a25r7Lf(0bI var0) {
      return var0.usernameField;
   }

   private static GuiTextField OEeDaSeOiF(0bI var0) {
      return var0.proxyField;
   }

   private static String QNohaSAkTJ() {
      return proxy;
   }

   private static String oQu1NSIoYm() {
      return username;
   }

   private static GuiTextField jbDNi7OQGm(0bI var0) {
      return var0.usernameField;
   }

   private static int u2PQeVM5ao(0bI var0) {
      return var0.height;
   }

   private static GuiTextField fVgOc8YfTk(0bI var0) {
      return var0.passwordField;
   }

   public static 0ep getTypeById(int id) {
      if (id == 0) {
         return D2iEwQfIQr();
      } else if (id == (11985 ^ -21053 ^ 20605 ^ -11410)) {
         return oBh5wlWRw9();
      } else if (id == (13994 ^ -29886 ^ 875 ^ -16767)) {
         return lAii1LBIPT();
      } else {
         return id == (17993 ^ -2396 ^ 29987 ^ -14899) ? 6zw0IkQcBQ() : HgbiaeALVQ();
      }
   }

   private static GuiTextField GvDVVaqwr3(0bI var0) {
      return var0.usernameField;
   }

   private static 0ep lAii1LBIPT() {
      return 0ep.HTTP;
   }

   private static GuiTextField Ztxxe2nltS(0bI var0) {
      return var0.passwordField;
   }

   private static 0ep _zw0IkQcBQ/* $FF was: 6zw0IkQcBQ*/() {
      return 0ep.NO_PROXY;
   }

   private static GuiTextField FjTtyeZQVG(0bI var0) {
      return var0.passwordField;
   }

   private static GuiTextField _a2C4VVtdw/* $FF was: 2a2C4VVtdw*/(0bI var0) {
      return var0.usernameField;
   }

   private static 0ep NWKTGtcT4g() {
      return 0ep.NO_PROXY;
   }

   private static FontRenderer jGjf4vdhO6(0bI var0) {
      return var0.fontRenderer;
   }

   private static int igsSt4GHgT(0bI var0) {
      return var0.height;
   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      GlStateManager.scale(Float.intBitsToFloat(13892 ^ 485117 ^ 523363 ^ 830693912 ^ 510390 ^ 483812 ^ 15604 ^ 235100772), Float.intBitsToFloat(22729 ^ 91722 ^ '杻' ^ 211608213 ^ 'ꋀ' ^ 29256 ^ '\ue49d' ^ 857543115), Float.intBitsToFloat(114023 ^ 123861 ^ 27272 ^ 933418339 ^ 7844 ^ 127712 ^ 117824 ^ 136495453));
      this.drawString(D6skH2ZljN(this), 2SgTjbqnii("ɺ˫ʞʨʯʯʸʳʩ˽ʉʤʭʸ˧˽ɺ˫ɺʱ") + getCurrentType() + 2SgTjbqnii("˽ɺ˥˰˽ɺ˪ʔʍ˧ʍʲʯʩ"), NdE7St5Myo(this) / (30206 ^ -29569 ^ 14136 ^ -12613) - (26346 ^ -28957 ^ 18670 ^ -24439), igsSt4GHgT(this) / (2567 ^ -21397 ^ 29476 ^ -10934) - (25764 ^ -3440 ^ 12104 ^ -18072) - (26358 ^ -26061 ^ 26307 ^ -26100), -29991 ^ -23556 ^ 8263 ^ -2403);
      2rmADg3FTJ(this).drawTextBox();
      if (ZqIQO8qaZA() == (24621 ^ -28060 ^ 7622 ^ -4210)) {
         if (!jbDNi7OQGm(this).getVisible()) {
            vDhKWNB84p(this).setVisible((boolean)(19341 ^ -10056 ^ 11920 ^ -16988));
         }

         if (!8xQOi7H72x(this).getVisible()) {
            0k1fzTrout(this).setVisible((boolean)(2807 ^ -20764 ^ 32556 ^ -9410));
         }

         lEIhg7Qdqn(this).drawTextBox();
         GG1YZJNjxW(this).drawTextBox();
         this.drawString(lXMojHITjg(this), 2SgTjbqnii("ɺʻʈʮʸʯʳʼʰʸ"), qd33AVaBjg(this) / (32263 ^ -15979 ^ 28800 ^ -12528) - (29370 ^ -31004 ^ 648 ^ -2376), DKt3rxK2RG(this) / (9995 ^ -22065 ^ 9848 ^ -22338) + (12584 ^ -10208 ^ 12983 ^ -9291) + (22383 ^ -12015 ^ 31594 ^ -708) - (30143 ^ -24284 ^ 20560 ^ -31521), -231 ^ -32592 ^ 1567 ^ -31159);
         this.drawString(6vYzqaYQdf(this), 2SgTjbqnii("ɺʻʍʼʮʮʪʲʯʹ"), NOF7q7XHa4(this) / (11663 ^ -9286 ^ 5263 ^ -7496) - (2914 ^ -26781 ^ 31028 ^ -6821), 71VwyeapN6(this) / (5914 ^ -32333 ^ 28389 ^ -1970) + (17358 ^ -24979 ^ 26851 ^ -19126) + (16008 ^ -18614 ^ 10231 ^ -20891) - (13862 ^ -14352 ^ 10359 ^ -9803), -8606 ^ -30562 ^ 15053 ^ -27698);
      }

      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   private static String _DqBVTFhvA/* $FF was: 1DqBVTFhvA*/() {
      return password;
   }

   private static GuiTextField lEIhg7Qdqn(0bI var0) {
      return var0.usernameField;
   }

   private static void j9iW28H3Br(String var0) {
      username = var0;
   }

   public void initGui() {
      Keyboard.enableRepeatEvents((boolean)(2621 ^ -21864 ^ 6622 ^ -18054));
      2xJP5a7Apc(this).add(new GuiButton(22637 ^ -27962 ^ 28790 ^ -17699, awFvD1idaD(this) / (28464 ^ -12372 ^ 20009 ^ -4425) - (15330 ^ -22414 ^ 5002 ^ -32642), KHr2bxPFdI(this) - (19432 ^ -20949 ^ 27856 ^ -30431), 16074 ^ -21914 ^ 1123 ^ -28665, 14158 ^ -31340 ^ 22654 ^ -5456, 2SgTjbqnii("ʙʲʳʸ")));
      FgnEdFo9dv(this).add(new GuiButton(32138 ^ -29835 ^ 25859 ^ -27651, dLNi78o6b9(this) / (31374 ^ -331 ^ 14669 ^ -17036) + (24991 ^ -10283 ^ 23396 ^ -4836), rJr68QyAZY(this) / (13804 ^ -27167 ^ 22533 ^ -2038) + (25243 ^ -26074 ^ 9831 ^ -8496) - (27545 ^ -9562 ^ 3419 ^ -17296), 25281 ^ -7901 ^ 16157 ^ -17183, 21883 ^ -28625 ^ 10804 ^ -4236, 2SgTjbqnii("ʏʸʮʸʩ")));
      q2Wx8hYsK4(this).add(new GuiButton(13399 ^ -10019 ^ 3178 ^ -7965, 8ePZePejo7(this) / (29472 ^ -31620 ^ 19867 ^ -17723) + (22088 ^ -25038 ^ 17457 ^ -29669), hhVnkNCjv4(this) / (27650 ^ -15711 ^ 18262 ^ -5641) + (6625 ^ -24988 ^ 100 ^ -30741) - (10408 ^ -25646 ^ 33 ^ -19633), 244 ^ -7876 ^ 22258 ^ -18652, 25286 ^ -29641 ^ 7176 ^ -3347, 2SgTjbqnii("ʉʤʭʸ")));
      tAYQwDyOVw(this).add(new GuiButton(25783 ^ -32765 ^ 5186 ^ -3854, Qm274zX0gS(this) / (4614 ^ -28296 ^ 32339 ^ -721) + (7259 ^ -12755 ^ 4007 ^ -8733), OBpN0Iargb(this) / (4855 ^ -16600 ^ 14134 ^ -25877) + (8192 ^ -25129 ^ 21371 ^ -4465) - (12712 ^ -1413 ^ 19212 ^ -32565), 28076 ^ -14065 ^ 16035 ^ -26049, 30866 ^ -32032 ^ 7880 ^ -6994, 2SgTjbqnii("ʛʯʸʸ˽ʍʯʲʥʤ")));
      IRC14et9Xy(this, new GuiTextField(28289 ^ -10712 ^ 17387 ^ -1216, noGl1NZDiy(this), vydDJdnDYB(this) / (1328 ^ -3233 ^ 22487 ^ -24134) - (26930 ^ -15440 ^ 21922 ^ -178), u2PQeVM5ao(this) / (1146 ^ -21024 ^ 15712 ^ -27400) + (20127 ^ -823 ^ 11995 ^ -25465) - (16612 ^ -2084 ^ 2857 ^ -17403), 1798 ^ -9840 ^ 12222 ^ -3596, 14873 ^ -19640 ^ 15187 ^ -19946));
      JwjzKpNhnF(this).setMaxStringLength(21028 ^ -5608 ^ 15193 ^ -31885);
      FxJ2HW2vo7(this).setFocused((boolean)(10548 ^ -15563 ^ 4191 ^ -1441));
      OEeDaSeOiF(this).setText(QNohaSAkTJ());
      P2MLjIfObF(this, new GuiTextField(18992 ^ -20188 ^ 14268 ^ -13142, jGjf4vdhO6(this), aTFgLd76d3(this) / (21171 ^ -31510 ^ 11408 ^ -1333) - (3800 ^ -14224 ^ 22260 ^ -28622), Oh1OU8hJBv(this) / (20055 ^ -21384 ^ 26262 ^ -31557) + (16870 ^ -3654 ^ 1552 ^ -18874) + (13024 ^ -9420 ^ 31841 ^ -27257) - (15916 ^ -14567 ^ 18388 ^ -16651), 14439 ^ -30083 ^ 2531 ^ -17627, 6246 ^ -17917 ^ 29320 ^ -12039));
      JJyE3zjvTA(this).setMaxStringLength(20446 ^ -19183 ^ 30685 ^ -29436);
      oASO7nY56e(this).setFocused((boolean)(25814 ^ -15338 ^ 25312 ^ -15840));
      GvDVVaqwr3(this).setText(oQu1NSIoYm());
      CznUei22i1(this).setVisible((boolean)(9qDPg8JJj8() == (23194 ^ -24916 ^ 27631 ^ -20520) ? 4064 ^ -7067 ^ 7090 ^ -4042 : 30240 ^ -29158 ^ 27056 ^ -28278));
      7Hvkoeo6ry(this, new GuiTextField(4423 ^ -22041 ^ 14476 ^ -32722, ULLnzVbgQk(this), bKVLBKadST(this) / (16633 ^ -6960 ^ 27893 ^ -14114) - (15418 ^ -1364 ^ 14254 ^ -3754), B2y1zqtGm4(this) / (19733 ^ -19731 ^ 32562 ^ -32568) + (23602 ^ -12463 ^ 14410 ^ -21725) + (31612 ^ -12218 ^ 14206 ^ -25570) - (9661 ^ -9775 ^ 16074 ^ -15694), 22512 ^ -29326 ^ 21130 ^ -30508, 29330 ^ -25960 ^ 20963 ^ -17923));
      GOweNvGtNi(this).setMaxStringLength(12637 ^ -4265 ^ 24541 ^ -32319);
      qY7LaGqoNb(this).setFocused((boolean)(27766 ^ -30205 ^ 6601 ^ -68));
      FjTtyeZQVG(this).setText(YQNeMNowxM());
      elKbFG9OV7(this).setVisible((boolean)(IvFkmZAOKi() == (8854 ^ -7192 ^ 10745 ^ -6010) ? 36 ^ -28558 ^ 10796 ^ -17797 : 7164 ^ -2629 ^ 19872 ^ -23577));
   }

   private static int wyin7jZkTw() {
      return type;
   }

   private static int Oh1OU8hJBv(0bI var0) {
      return var0.height;
   }

   private static int hhVnkNCjv4(0bI var0) {
      return var0.height;
   }

   private static void _Hvkoeo6ry/* $FF was: 7Hvkoeo6ry*/(0bI var0, GuiTextField var1) {
      var0.passwordField = var1;
   }

   private static String uuXDjvBa9S() {
      return proxy;
   }

   private static GuiTextField FxJ2HW2vo7(0bI var0) {
      return var0.proxyField;
   }

   private static void Ms2x17UdnY(String var0) {
      username = var0;
   }

   private static 0ep aBR4EYi2Cw() {
      return 0ep.HTTP;
   }

   private static void _M3m66Er4H/* $FF was: 2M3m66Er4H*/(String var0) {
      username = var0;
   }

   private static 0ep FDDS5QACG0() {
      return 0ep.SOCKS5;
   }

   private static void _GsBNqneD6/* $FF was: 2GsBNqneD6*/(String var0) {
      proxy = var0;
   }

   private static GuiTextField S2i8QKoZHM(0bI var0) {
      return var0.usernameField;
   }

   private static int KHr2bxPFdI(0bI var0) {
      return var0.height;
   }

   private static void hWmOT8W7Sr(int var0) {
      type = var0;
   }

   private static String AFwhFrA4Nn() {
      return proxy;
   }

   private static void SpIpePTNw4(int var0) {
      type = var0;
   }

   private static GuiTextField JwjzKpNhnF(0bI var0) {
      return var0.proxyField;
   }

   private static int qd33AVaBjg(0bI var0) {
      return var0.width;
   }

   private static String readUsingFiles(File file) {
      try {
         return new String(Files.readAllBytes(file.toPath()));
      } catch (IOException var2) {
         saveConfig();
         return null;
      }
   }

   public static void loadConfig() {
      try {
         JSONObject config = new JSONObject((String)Objects.requireNonNull(readUsingFiles(new File(uFdATLfUL2(Minecraft.getMinecraft()), 2SgTjbqnii("˲ʓʸʲʊʼʯʸ˲ʰʾʭʯʲʥʤ˳ʷʮʲʳ")))));
         1Ia4leavJ2(getIdByType(0ep.getType(config.getString(2SgTjbqnii("ʩʤʭʸ")))));
         FAatylggb1(config.getString(2SgTjbqnii("ʵʲʮʩ")));
         o8eeBCyO9z(config.getString(2SgTjbqnii("ʨʮʸʯʳʼʰʸ")));
         GpieqpF1wV(config.getString(2SgTjbqnii("ʭʼʮʮʪʲʯʹ")));
      } catch (Exception var1) {
      }

   }

   private static 0ep oBh5wlWRw9() {
      return 0ep.SOCKS5;
   }

   private static int vydDJdnDYB(0bI var0) {
      return var0.width;
   }

   private static GuiTextField qY7LaGqoNb(0bI var0) {
      return var0.passwordField;
   }

   private static 0ep jKHNfK42Wg() {
      return 0ep.SOCKS4;
   }

   private static GuiTextField CznUei22i1(0bI var0) {
      return var0.usernameField;
   }

   private static void lgnDq7kdWG(int var0) {
      type = var0;
   }

   private static void _Ia4leavJ2/* $FF was: 1Ia4leavJ2*/(int var0) {
      type = var0;
   }

   private static int awFvD1idaD(0bI var0) {
      return var0.width;
   }

   private static GuiTextField wRqWt4XitN(0bI var0) {
      return var0.proxyField;
   }

   private static void mFMvi41v9D(int var0) {
      type = var0;
   }

   private static FontRenderer ULLnzVbgQk(0bI var0) {
      return var0.fontRenderer;
   }

   private static FontRenderer _vYzqaYQdf/* $FF was: 6vYzqaYQdf*/(0bI var0) {
      return var0.fontRenderer;
   }

   private static void P2MLjIfObF(0bI var0, GuiTextField var1) {
      var0.usernameField = var1;
   }

   private static int JDmyetjTsn() {
      return type;
   }

   private static int DKt3rxK2RG(0bI var0) {
      return var0.height;
   }

   public boolean doesGuiPauseGame() {
      return (boolean)(10527 ^ -13494 ^ 24787 ^ -32121);
   }

   private static GuiTextField J4A7FNX6iQ(0bI var0) {
      return var0.passwordField;
   }

   private static void RqcbMWLWJK(String var0) {
      password = var0;
   }

   private static int _1VwyeapN6/* $FF was: 71VwyeapN6*/(0bI var0) {
      return var0.height;
   }

   private static GuiTextField eGzqQbL6xn(0bI var0) {
      return var0.usernameField;
   }

   private static GuiTextField _xQOi7H72x/* $FF was: 8xQOi7H72x*/(0bI var0) {
      return var0.passwordField;
   }

   private static void FAatylggb1(String var0) {
      proxy = var0;
   }

   private static int Qm274zX0gS(0bI var0) {
      return var0.width;
   }

   public static int getIdByType(0ep type) {
      if (type.equals(aBR4EYi2Cw())) {
         return 12195 ^ -4888 ^ 481 ^ -15704;
      } else if (type.equals(FDDS5QACG0())) {
         return 15150 ^ -25689 ^ 5782 ^ -18914;
      } else if (type.equals(jKHNfK42Wg())) {
         return 32579 ^ -2240 ^ 30311 ^ -412;
      } else {
         return type.equals(NWKTGtcT4g()) ? 16481 ^ -8518 ^ 19543 ^ -11633 : 1454 ^ -6661 ^ 17621 ^ -23421;
      }
   }

   private static int _ePZePejo7/* $FF was: 8ePZePejo7*/(0bI var0) {
      return var0.width;
   }

   private static int _aDeYTJEbm/* $FF was: 2aDeYTJEbm*/(GuiButton var0) {
      return var0.id;
   }

   private static GuiTextField V9tGZb4HlJ(0bI var0) {
      return var0.proxyField;
   }

   private static int ZqIQO8qaZA() {
      return type;
   }

   private static int dLNi78o6b9(0bI var0) {
      return var0.width;
   }

   private static void l4iWNuYaSd(String var0) {
      proxy = var0;
   }

   private static int tO2Qb3VDVT(GuiButton var0) {
      return var0.id;
   }

   private static GuiTextField _LtjYS4rlb/* $FF was: 3LtjYS4rlb*/(0bI var0) {
      return var0.proxyField;
   }

   private static List q2Wx8hYsK4(0bI var0) {
      return var0.buttonList;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String _SgTjbqnii/* $FF was: 2SgTjbqnii*/(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 23450 ^ -6058 ^ 3286 ^ -16614; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 29281 ^ -14636 ^ 26245 ^ -12051));
      }

      return var1.toString();
   }

   private static GuiTextField exysCstV1v(0bI var0) {
      return var0.passwordField;
   }

   private static int B2y1zqtGm4(0bI var0) {
      return var0.height;
   }

   private static FontRenderer lXMojHITjg(0bI var0) {
      return var0.fontRenderer;
   }

   private static 0ep D2iEwQfIQr() {
      return 0ep.SOCKS4;
   }

   private static FontRenderer noGl1NZDiy(0bI var0) {
      return var0.fontRenderer;
   }

   private static int IvFkmZAOKi() {
      return type;
   }

   private static File gJQUnbBG1N(Minecraft var0) {
      return var0.gameDir;
   }

   public static void saveConfig() {
      try {
         JSONObject proxySetting = new JSONObject();
         proxySetting.put(2SgTjbqnii("ʩʤʭʸ"), (Object)getCurrentType().name());
         proxySetting.put(2SgTjbqnii("ʵʲʮʩ"), (Object)uuXDjvBa9S());
         proxySetting.put(2SgTjbqnii("ʨʮʸʯʳʼʰʸ"), (Object)LBRZIbDybg());
         proxySetting.put(2SgTjbqnii("ʭʼʮʮʪʲʯʹ"), (Object)1DqBVTFhvA());
         FileUtils.writeByteArrayToFile(new File(gJQUnbBG1N(Minecraft.getMinecraft()), 2SgTjbqnii("˲ʓʸʲʊʼʯʸ˲ʰʾʭʯʲʥʤ˳ʷʮʲʳ")), proxySetting.toString().getBytes(CD9DnXnGcq()));
      } catch (Exception var1) {
         Exception e = var1;
         e.printStackTrace();
      }

   }

   private static void o8eeBCyO9z(String var0) {
      username = var0;
   }

   private static Charset CD9DnXnGcq() {
      return StandardCharsets.UTF_8;
   }

   public void onGuiClosed() {
      Keyboard.enableRepeatEvents((boolean)(15036 ^ -17262 ^ 32249 ^ -1065));
   }

   private static void _tHltB0NvT/* $FF was: 2tHltB0NvT*/(String var0) {
      proxy = var0;
   }

   private static Minecraft deYxX6yabr(0bI var0) {
      return var0.mc;
   }

   private static int bKVLBKadST(0bI var0) {
      return var0.width;
   }

   private static String YQNeMNowxM() {
      return password;
   }

   private static GuiTextField oASO7nY56e(0bI var0) {
      return var0.usernameField;
   }

   private static GuiTextField JJyE3zjvTA(0bI var0) {
      return var0.usernameField;
   }

   private static String LBRZIbDybg() {
      return username;
   }

   private static void GpieqpF1wV(String var0) {
      password = var0;
   }

   private static void UYorJLbyNi(String var0) {
      proxy = var0;
   }

   private static GuiTextField LMyaDRHQuv(0bI var0) {
      return var0.usernameField;
   }

   private static void jwqqSohjqr(String var0) {
      password = var0;
   }

   private static GuiTextField bCJgVYNI14(0bI var0) {
      return var0.proxyField;
   }

   private static int CJOySogjUz() {
      return type;
   }

   private static FontRenderer D6skH2ZljN(0bI var0) {
      return var0.fontRenderer;
   }

   private static GuiTextField _k1fzTrout/* $FF was: 0k1fzTrout*/(0bI var0) {
      return var0.passwordField;
   }

   private static int _qDPg8JJj8/* $FF was: 9qDPg8JJj8*/() {
      return type;
   }

   private static void o7CrWFoa05(int var0) {
      type = var0;
   }

   private static void _ikRwaxbUZ/* $FF was: 9ikRwaxbUZ*/(int var0) {
      type = var0;
   }

   private static int y48gQOJQif(GuiButton var0) {
      return var0.id;
   }

   private static void O3SdgtWy1S(String var0) {
      password = var0;
   }

   private static int wjFV9x6h7n() {
      return type;
   }

   private static File uFdATLfUL2(Minecraft var0) {
      return var0.gameDir;
   }

   private static int rJr68QyAZY(0bI var0) {
      return var0.height;
   }

   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);
      vFjGSaj9Ib(this).mouseClicked(mouseX, mouseY, mouseButton);
      S2i8QKoZHM(this).mouseClicked(mouseX, mouseY, mouseButton);
      exysCstV1v(this).mouseClicked(mouseX, mouseY, mouseButton);
   }

   public void updateScreen() {
      bCJgVYNI14(this).updateCursorCounter();
      LMyaDRHQuv(this).updateCursorCounter();
      J4A7FNX6iQ(this).updateCursorCounter();
      super.updateScreen();
   }

   private static void IRC14et9Xy(0bI var0, GuiTextField var1) {
      var0.proxyField = var1;
   }

   private static int NOF7q7XHa4(0bI var0) {
      return var0.width;
   }

   public static 0ep getCurrentType() {
      return getTypeById(rQ5q2yvqAG());
   }

   private static GuiTextField GG1YZJNjxW(0bI var0) {
      return var0.passwordField;
   }

   private static List g0itn6ULsY(0bI var0) {
      return var0.buttonList;
   }

   private static GuiTextField vDhKWNB84p(0bI var0) {
      return var0.usernameField;
   }

   public _bI/* $FF was: 0bI*/() {
   }
}
