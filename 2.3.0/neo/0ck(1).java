package neo;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Session;
import net.minecraft.util.StringUtils;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class 0ck extends lg {
   public 0cd field_d = null;
   public 0ci field_e;
   public lE field_c;
   public ResourceLocation field_g;
   public 0ce field_h;
   public String field_a;
   public float field_b;
   public 0ci field_f;
   private static int _DSC GG NEOWARECLIENT _;

   protected void keyTyped(char b, int c) {
      method_bei(this).textboxKeyTyped(b, c);
      if ((b == (6951 ^ -25527 ^ 20128 ^ -13881) || b == (7088 ^ -5324 ^ 23095 ^ -21826)) && method_bej(this).isFocused()) {
         method_bek(this).setFocused((boolean)(!method_bel(this).isFocused() ? 23362 ^ -29492 ^ 1275 ^ -11404 : 396 ^ -26635 ^ 730 ^ -27485));
      }

      try {
         super.keyTyped(b, c);
      } catch (IOException var4) {
         IOException a = var4;
         a.printStackTrace();
      }

   }

   private static jH method_bdA(nC var0) {
      return var0.fontRenderer;
   }

   private static int method_bdU(0ck var0) {
      return var0.height;
   }

   private static lE method_bej(0ck var0) {
      return var0.field_c;
   }

   private static int method_bdC(0ck var0) {
      return var0.width;
   }

   private static ResourceLocation method_bdq(0ck var0) {
      return var0.field_g;
   }

   private static nC method_bcD(0ck var0) {
      return var0.mc;
   }

   private static float method_bdi(0ck var0) {
      return var0.field_b;
   }

   private static ResourceLocation method_bdu(0ck var0) {
      return var0.field_g;
   }

   private static nC method_bcE(0ck var0) {
      return var0.mc;
   }

   private static void method_bcQ(0ck var0, float var1) {
      var0.field_b = var1;
   }

   private static float method_bet(0ck var0) {
      return var0.field_b;
   }

   private static float method_bdD(0ck var0) {
      return var0.field_b;
   }

   private static float method_bdz(0ck var0) {
      return var0.field_b;
   }

   private static nC method_bcp(0ck var0) {
      return var0.mc;
   }

   private static int method_bdE(0ck var0) {
      return var0.eventButton;
   }

   private static float method_bdw(0ck var0) {
      return var0.field_b;
   }

   private static 0cd method_bew(0ck var0) {
      return var0.field_d;
   }

   private static float method_bcN(0ck var0) {
      return var0.field_b;
   }

   private static nC method_bcF(0ck var0) {
      return var0.mc;
   }

   private static float method_bev(0ck var0) {
      return var0.field_b;
   }

   private static void method_bcu(0ck var0, 0ce var1) {
      var0.field_h = var1;
   }

   private static nC method_bcz(0ck var0) {
      return var0.mc;
   }

   private static nC method_bcB(0ck var0) {
      return var0.mc;
   }

   private static int method_bdT(0ck var0) {
      return var0.width;
   }

   private static void method_bcZ(0ci var0, boolean var1) {
      var0.enabled = var1;
   }

   private static int method_bcU(0ck var0) {
      return var0.width;
   }

   private static jH method_bdG(nC var0) {
      return var0.fontRenderer;
   }

   private static void method_bdl(0ck var0, float var1) {
      var0.field_b = var1;
   }

   private static lE method_beB(0ck var0) {
      return var0.field_c;
   }

   private static int method_bdy(0ck var0) {
      return var0.width;
   }

   private static jH method_bdx(nC var0) {
      return var0.fontRenderer;
   }

   private static float method_bcJ(0ck var0) {
      return var0.field_b;
   }

   private static void method_bdJ(0ck var0, lE var1) {
      var0.field_c = var1;
   }

   private static int method_bdX(0ck var0) {
      return var0.height;
   }

   private static void method_bdj(0ck var0, float var1) {
      var0.field_b = var1;
   }

   private static int method_bdI(0ck var0) {
      return var0.height;
   }

   private static int method_bec(0ck var0) {
      return var0.width;
   }

   private static int method_bdM(0ck var0) {
      return var0.height;
   }

   private static int method_bcI(nC var0) {
      return var0.displayHeight;
   }

   private static int method_bdP(0ck var0) {
      return var0.width;
   }

   private static void method_bdf(0ci var0, boolean var1) {
      var0.enabled = var1;
   }

   private static ResourceLocation method_bds(0ck var0) {
      return var0.field_g;
   }

   private boolean method_bcm(double a, double b, double c) {
      return (boolean)(a >= (double)((float)method_beo(this) / Float.intBitsToFloat('\ue947' ^ '膾' ^ 11434 ^ 831917650 ^ '\uf896' ^ 241357 ^ '￤' ^ 1905648574) - Float.intBitsToFloat('鹯' ^ 520740 ^ '\ud8b1' ^ 207857808 ^ 18409 ^ 242912 ^ 12552 ^ 1318968939)) && b >= c - Double.longBitsToDouble(-1459931534167323660L ^ -6076121152222082060L) && a <= (double)method_bep(this) / Double.longBitsToDouble(4806605558127656073L ^ 9028730208787496073L) && b <= c + Double.longBitsToDouble(-7013461017085923917L ^ -2405152698379063885L) && a >= Double.longBitsToDouble(754740200995060691L ^ 754740200995060691L) && b >= Double.longBitsToDouble(-1642563691065749207L ^ -6236094573495299799L) && a <= (double)method_beq(this) && b <= (double)(method_ber(this) - (25924 ^ -23595 ^ 13307 ^ -2728)) ? 3422 ^ -3479 ^ 29240 ^ -29426 : 29145 ^ -4614 ^ 25388 ^ -241);
   }

   private static int method_ben(0ck var0) {
      return var0.height;
   }

   private static int method_beq(0ck var0) {
      return var0.width;
   }

   private static int method_bep(0ck var0) {
      return var0.width;
   }

   private List<0cd> method_bcn() {
      List<0cd> b = new ArrayList();
      Iterator c = method_bez().iterator();

      while(true) {
         0cd a;
         do {
            if (!c.hasNext()) {
               return b;
            }

            a = (0cd)c.next();
         } while(!method_beA(this).getText().isEmpty() && !a.method_XC().toLowerCase().contains(method_beB(this).getText().toLowerCase()) && !a.method_XG().toLowerCase().contains(method_beC(this).getText().toLowerCase()));

         b.add(a);
      }
   }

   private static void method_bdh(0ck var0, float var1) {
      var0.field_b = var1;
   }

   private static float method_bcP(0ck var0) {
      return var0.field_b;
   }

   private static void method_bcy(0ck var0, 0cd var1) {
      var0.field_d = var1;
   }

   private static void method_bdb(0ci var0, boolean var1) {
      var0.enabled = var1;
   }

   private static 0cd method_bcv(0ck var0) {
      return var0.field_d;
   }

   private static void method_bdd(0ci var0, boolean var1) {
      var0.enabled = var1;
   }

   private static List method_bdO(0ck var0) {
      return var0.buttonList;
   }

   private static int method_bcq(jK var0) {
      return var0.id;
   }

   private static lE method_bel(0ck var0) {
      return var0.field_c;
   }

   private static List method_bdK(0ck var0) {
      return var0.buttonList;
   }

   private static lE method_bes(0ck var0) {
      return var0.field_c;
   }

   private static void method_bey(0ck var0, 0cd var1) {
      var0.field_d = var1;
   }

   private static float method_bcL(0ck var0) {
      return var0.field_b;
   }

   private static lE method_bek(0ck var0) {
      return var0.field_c;
   }

   private static 0cc method_bdB() {
      return 0cc.field_e;
   }

   private static void method_bef(0ci var0, boolean var1) {
      var0.enabled = var1;
   }

   private static void method_bcC(nC var0, Session var1) {
      var0.session = var1;
   }

   public void initGui() {
      method_bdJ(this, new lE(method_bdE(this), method_bdG(method_bdF(this)), method_bdH(this) / (16960 ^ -24284 ^ 6312 ^ -1074) + (22830 ^ -569 ^ 17166 ^ -6253), method_bdI(this) - (23971 ^ -28497 ^ 23000 ^ -27454), 23 ^ -2896 ^ 1200 ^ -4001, 23791 ^ -13509 ^ 14626 ^ -20762));
      List var10000 = method_bdK(this);
      0ci var10003 = new 0ci(18182 ^ -9470 ^ 30903 ^ -6990, method_bdL(this) / (30163 ^ -8663 ^ 23080 ^ -3632) - (29077 ^ -19406 ^ 17236 ^ -31095), method_bdM(this) - (24448 ^ -8041 ^ 359 ^ -16832), 2502 ^ -2232 ^ 20545 ^ -20749, 4426 ^ -25620 ^ 11472 ^ -22942, method_bco("Öõýóô"));
      method_bdN(this, var10003);
      var10000.add(var10003);
      var10000 = method_bdO(this);
      var10003 = new 0ci(16402 ^ -12034 ^ 16371 ^ -20707, method_bdP(this) / (23341 ^ -7025 ^ 30932 ^ -14476) + (2958 ^ -31588 ^ 15296 ^ -19242) + (29819 ^ -20517 ^ 21532 ^ -28687), method_bdQ(this) - (6906 ^ -18097 ^ 31785 ^ -8276), 20948 ^ -25108 ^ 20348 ^ -31892, 27905 ^ -17104 ^ 7298 ^ -13145, method_bco("Èÿ÷õìÿ"));
      method_bdR(this, var10003);
      var10000.add(var10003);
      method_bdS(this).add(new 0ci(17868 ^ -12314 ^ 27911 ^ -6354, method_bdT(this) / (2091 ^ -3300 ^ 5037 ^ -5992) + (4751 ^ -8278 ^ 11063 ^ -6634) - (15906 ^ -18044 ^ 9211 ^ -23455), method_bdU(this) - (13370 ^ -25783 ^ 32091 ^ -11752), 32149 ^ -16200 ^ 5580 ^ -22368, 6094 ^ -10363 ^ 6876 ^ -9597, method_bco("ÛþþºÛöî")));
      method_bdV(this).add(new 0ci(12405 ^ -28732 ^ 3956 ^ -20288, method_bdW(this) / (2641 ^ -13963 ^ 19589 ^ -28765) + (31547 ^ -26198 ^ 10643 ^ -13555), method_bdX(this) - (12140 ^ -11583 ^ 12585 ^ -13132), 16549 ^ -16481 ^ 704 ^ -570, 16115 ^ -18756 ^ 17586 ^ -13079, method_bco("Èûôþõ÷")));
      method_bdY(this).add(new 0ci(14582 ^ -30298 ^ 29668 ^ -15693, method_bdZ(this) / (31114 ^ -5048 ^ 22682 ^ -12966) - (17014 ^ -7534 ^ 9778 ^ -31060), method_bea(this) - (6 ^ -3527 ^ 14878 ^ -14279), 22889 ^ 15829 ^ 25602 ^ 75, 4482 ^ -9791 ^ 11944 ^ -6401, method_bco("Øûùñ")));
      method_beb(this).add(new 0ci(27216 ^ -1362 ^ 15997 ^ -20853, method_bec(this) / (16804 ^ -12295 ^ 13598 ^ -17599) + (27137 ^ -19832 ^ 5565 ^ -13008) + (25956 ^ -26416 ^ 3655 ^ -3185), method_bed(this) - (24677 ^ -9835 ^ 6407 ^ -24338), 21763 ^ -19374 ^ 25117 ^ -31888, 10448 ^ -16149 ^ 1142 ^ -5031, method_bco("ÙöÿûèºÛöö")));
      method_bef(method_bee(this), (boolean)(90 ^ -25302 ^ 29860 ^ -5676));
      method_beh(method_beg(this), (boolean)(28182 ^ -2111 ^ 4051 ^ -27132));
   }

   private static 0ce method_bct(0ck var0) {
      return var0.field_h;
   }

   private static 0cd method_bcX(0ck var0) {
      return var0.field_d;
   }

   private static int method_bdZ(0ck var0) {
      return var0.width;
   }

   private static void method_bcO(0ck var0, float var1) {
      var0.field_b = var1;
   }

   private static int method_bdv(0ck var0) {
      return var0.width;
   }

   private static lE method_beC(0ck var0) {
      return var0.field_c;
   }

   private static List method_bdS(0ck var0) {
      return var0.buttonList;
   }

   private static 0cd method_bcr(0ck var0) {
      return var0.field_d;
   }

   private static int method_bdQ(0ck var0) {
      return var0.height;
   }

   private static int method_bcW(0ck var0) {
      return var0.height;
   }

   private static 0ci method_bda(0ck var0) {
      return var0.field_f;
   }

   private static 0ci method_beg(0ck var0) {
      return var0.field_f;
   }

   private static int method_ber(0ck var0) {
      return var0.height;
   }

   private static int method_bea(0ck var0) {
      return var0.height;
   }

   private static List method_bdV(0ck var0) {
      return var0.buttonList;
   }

   private void method_bck(ResourceLocation a, String b) {
      zf c = method_bcp(this).getTextureManager();
      c.getTexture(a);
      String var10003 = method_bco("òîîêé µµ÷óôõîûè´ôÿîµûìûîûèµ¿éµ¬®´êôý");
      Object[] var10004 = new Object[10085 ^ -16145 ^ 25158 ^ -31283];
      var10004[20912 ^ -25983 ^ 16060 ^ -2675] = StringUtils.stripControlCodes(b);
      zm d = new zm((File)null, String.format(var10003, var10004), Ap.getDefaultSkin(jf.getOfflineUUID(b)), new yj());
      c.loadTexture(a, d);
   }

   private static void method_bdr(0ck var0, ResourceLocation var1) {
      var0.field_g = var1;
   }

   private static Session method_bcS(nC var0) {
      return var0.session;
   }

   private static Session method_bdn(nC var0) {
      return var0.session;
   }

   private static jH method_bcT(nC var0) {
      return var0.fontRenderer;
   }

   private static 0ci method_bcY(0ck var0) {
      return var0.field_e;
   }

   private static nC method_bdm(0ck var0) {
      return var0.mc;
   }

   private static float method_bdk(0ck var0) {
      return var0.field_b;
   }

   private static 0ci method_bdc(0ck var0) {
      return var0.field_e;
   }

   private static int method_bcG(nC var0) {
      return var0.displayWidth;
   }

   private static void method_bdN(0ck var0, 0ci var1) {
      var0.field_e = var1;
   }

   private static ArrayList method_bez() {
      return 0cf.field_a;
   }

   private static nC method_bcR(0ck var0) {
      return var0.mc;
   }

   public _ck/* $FF was: 0ck*/() {
      this.field_a = TextFormatting.DARK_GRAY + method_bco("²") + TextFormatting.GRAY + 0cf.field_a.size() + TextFormatting.DARK_GRAY + method_bco("³");
   }

   public void drawScreen(int f, int g, float h) {
      0en.method_bGt(Float.intBitsToFloat(111103 ^ 124529 ^ 1436 ^ -269305436 ^ 110688 ^ 19774 ^ 107627 ^ -269307261), Float.intBitsToFloat(109157 ^ 31949 ^ 118617 ^ 123773186 ^ 20204 ^ 21264 ^ 10867 ^ 123768700), (float)method_bcG(method_bcF(this)), (float)method_bcI(method_bcH(this)), new Color(23198 ^ -11624 ^ 31170 ^ -3627, 11248 ^ -23904 ^ 30213 ^ -188, 20024 ^ -11035 ^ 8089 ^ -31403));
      super.drawScreen(f, g, h);
      if (Mouse.hasWheel()) {
         int a = Mouse.getDWheel();
         if (a < 0) {
            method_bcK(this, (float)((double)method_bcJ(this) + Double.longBitsToDouble(-3167410544049917012L ^ -7768400513362299988L)));
            if ((double)method_bcL(this) < Double.longBitsToDouble(2396178992251535125L ^ 2396178992251535125L)) {
               method_bcM(this, Float.intBitsToFloat(121724 ^ '肩' ^ 118083 ^ 1392145802 ^ 127739 ^ 105148 ^ 23653 ^ 1392170814));
            }
         } else if (a > 0) {
            method_bcO(this, (float)((double)method_bcN(this) - Double.longBitsToDouble(3051043745739899177L ^ 7668922213654921513L)));
            if ((double)method_bcP(this) < Double.longBitsToDouble(2981309286956472502L ^ 2981309286956472502L)) {
               method_bcQ(this, Float.intBitsToFloat(17678 ^ 128662 ^ 26021 ^ 1346558880 ^ 18409 ^ 121263 ^ 28666 ^ 1346567201));
            }
         }
      }

      String i = method_bco("Ôû÷ÿ º") + method_bcS(method_bcR(this)).getUsername();
      yh.pushMatrix();
      yh.disableBlend();
      yh.popMatrix();
      method_bcT(nC.getMinecraft()).drawCenteredString(i, (double)((float)method_bcU(this) / Float.intBitsToFloat(17365 ^ 212905 ^ 'ﻹ' ^ -2089179177 ^ '膿' ^ 222513 ^ 11362 ^ -1015419458)), Double.longBitsToDouble(3324908090082006888L ^ 7926461009347811176L), -21859 ^ -5507 ^ 27404 ^ -11245);
      yh.pushMatrix();
      0en.method_bGf(Float.intBitsToFloat('뎍' ^ 22326 ^ '껢' ^ 1425416532 ^ '읹' ^ 'ꎶ' ^ 107 ^ 1425412009), Float.intBitsToFloat('\uf5fa' ^ '齋' ^ 23069 ^ -529245090 ^ 936 ^ 9620 ^ 705 ^ -1569699825), (float)method_bcV(this), (double)(method_bcW(this) - (22034 ^ -25792 ^ 21375 ^ -25015)));
      GL11.glEnable(24408 ^ -8131 ^ 32570 ^ -13234);
      int j = 6838 ^ -30972 ^ 9627 ^ -18417;
      Iterator<0cd> k = this.method_bcn().iterator();

      while(k.hasNext()) {
         0cd b = (0cd)k.next();
         if (this.method_bcl(j)) {
            String c = b.method_XC().equals(method_bco("")) ? b.method_XG() : b.method_XC();
            if (c.equalsIgnoreCase(method_bdn(method_bdm(this)).getUsername())) {
               c = method_bco("=û") + c;
            }

            String d = b.method_XA().equals(method_bdo()) ? method_bco("=ù") : (b.method_XA().equals(method_bdp()) ? method_bco("=÷") : method_bco(""));
            c = d + c + method_bco("=èº");
            String e = b.method_XE().equals(method_bco("")) ? method_bco("=ùÔõîºÖóùÿôéÿ") : method_bco("=ûÖóùÿôéÿ");
            yh.pushMatrix();
            yh.color(Float.intBitsToFloat(120421 ^ 112255 ^ 15193 ^ 186818233 ^ 2772 ^ 101487 ^ 122748 ^ 883072061), Float.intBitsToFloat(124970 ^ 841 ^ 125397 ^ 709162188 ^ 112744 ^ 106415 ^ 20952 ^ 365199461), Float.intBitsToFloat(10031 ^ 4170692 ^ 29473 ^ 90176290 ^ 9091 ^ 4160083 ^ '긖' ^ 987757358), Float.intBitsToFloat('\ueae7' ^ '趫' ^ 15708 ^ 409272876 ^ '푀' ^ 12150 ^ '\ue123' ^ 669336105));
            if (method_bdq(this) == null) {
               method_bdr(this, jf.getLocationSkin(c));
               this.method_bck(method_bds(this), c);
            } else {
               method_bdt(this).getTextureManager().bindTexture(method_bdu(this));
               yh.enableTexture2D();
               jI.drawScaledCustomSizeModalRect((int)((float)method_bdv(this) / Float.intBitsToFloat(27251 ^ 104564 ^ 13975 ^ -1049836520 ^ 9020 ^ 125966 ^ 14327 ^ -2123564979) - Float.intBitsToFloat(32205 ^ 217376 ^ '힅' ^ -27928710 ^ 27258 ^ 31766 ^ 4300 ^ -1131795790)), (int)((float)j - method_bdw(this)), Float.intBitsToFloat(23426 ^ 118429 ^ 11002 ^ -649022707 ^ 32325 ^ 110005 ^ 1744 ^ -1739531832), Float.intBitsToFloat(32634 ^ 232551 ^ 257707 ^ -53632143 ^ 243407 ^ 221619 ^ 7522 ^ -1110608679), 25821 ^ -12801 ^ 12174 ^ -31068, 26198 ^ -19133 ^ 8703 ^ -3358, 12838 ^ -21040 ^ 29337 ^ -4746, 2387 ^ -32538 ^ 2088 ^ -32380, Float.intBitsToFloat(20428 ^ 485211 ^ 521080 ^ -1316265681 ^ 24470 ^ 17143 ^ 14282 ^ -217345941), Float.intBitsToFloat('\uda1d' ^ '虿' ^ 7029 ^ -847037622 ^ 'ꕸ' ^ '뻕' ^ 17087 ^ -1895619249));
            }

            yh.popMatrix();
            method_bdx(nC.getMinecraft()).drawString(c, (int)((float)method_bdy(this) / Float.intBitsToFloat(11835 ^ '\ue220' ^ 5710 ^ 530471796 ^ 6107 ^ '\uf4fe' ^ 18106 ^ 1604199614) - Float.intBitsToFloat('Ꙍ' ^ 19055 ^ '쭙' ^ 942538897 ^ 'ꁴ' ^ 237678 ^ '폠' ^ 2056123409)), (int)((double)j - (double)method_bdz(this) + Double.longBitsToDouble(829950001802619967L ^ 5445013719950535743L)), -29571 ^ -27534 ^ 19300 ^ -21356);
            method_bdA(nC.getMinecraft()).drawString((b.method_XA().equals(method_bdB()) ? method_bco("=÷") : method_bco("")) + e, (int)((float)method_bdC(this) / Float.intBitsToFloat(116658 ^ 92945 ^ 13120 ^ -349014614 ^ 100922 ^ 126045 ^ 13835 ^ -1422738907) - Float.intBitsToFloat(15079 ^ 23914 ^ 3804 ^ -1059609847 ^ 6624 ^ 999816 ^ 1048561 ^ -2106102335)), (int)((double)j - (double)method_bdD(this) + Double.longBitsToDouble(9030543896105358126L ^ 4430679826699817774L)), (new Color(18712 ^ -32552 ^ 1753 ^ -12425, 14789 ^ -8757 ^ 5128 ^ -3992, 9555 ^ -2890 ^ 27480 ^ -17709)).getRGB());
            j += 40;
         }
      }

      GL11.glDisable('螭' ^ -28110 ^ '왎' ^ -8256);
      GL11.glPopMatrix();
      if (method_bcX(this) == null) {
         method_bcZ(method_bcY(this), (boolean)(18797 ^ -22185 ^ 8545 ^ -16037));
         method_bdb(method_bda(this), (boolean)(8597 ^ -4383 ^ 10302 ^ -6326));
      } else {
         method_bdd(method_bdc(this), (boolean)(9495 ^ -31971 ^ 3591 ^ -22516));
         method_bdf(method_bde(this), (boolean)(30865 ^ -22506 ^ 20734 ^ -32648));
      }

      if (Keyboard.isKeyDown(7145 ^ -13807 ^ 7350 ^ -12922)) {
         method_bdh(this, (float)((double)method_bdg(this) - Double.longBitsToDouble(8011791335295335772L ^ 3392786967473470812L)));
      } else if (Keyboard.isKeyDown(24395 ^ -14164 ^ 20834 ^ -14763)) {
         method_bdj(this, (float)((double)method_bdi(this) + Double.longBitsToDouble(7250958030242388011L ^ 2637583161954736171L)));
      }

      if ((double)method_bdk(this) < Double.longBitsToDouble(-7831130029387139805L ^ -7831130029387139805L)) {
         method_bdl(this, Float.intBitsToFloat(8860 ^ 95379 ^ 127896 ^ 962885303 ^ 125074 ^ 112717 ^ 5194 ^ 962893749));
      }

   }

   private static void method_bdR(0ck var0, 0ci var1) {
      var0.field_f = var1;
   }

   private static void method_bcx(0ck var0, String var1) {
      var0.field_a = var1;
   }

   private static lE method_bei(0ck var0) {
      return var0.field_c;
   }

   private static int method_beo(0ck var0) {
      return var0.width;
   }

   public void actionPerformed(jK b) {
      switch (method_bcq(b)) {
         case 0:
         default:
            break;
         case 1:
            0ce var10002 = new 0ce(method_bcr(this));
            method_bcs(this, var10002);
            var10002.start();
            break;
         case 2:
            if (method_bct(this) != null) {
               method_bcu(this, (0ce)null);
            }

            0cf.method_Yy(method_bcv(this));
            method_bcx(this, method_bcw() + method_bco("Èÿ÷õìÿþ´"));
            method_bcy(this, (0cd)null);
            break;
         case 3:
            method_bcz(this).displayGuiScreen(new 0ch(this));
            break;
         case 4:
            method_bcA(this).displayGuiScreen(new 0cj(this));
            break;
         case 5:
            String a = 0ec.randomString(0ec.intRandom(19727 ^ -1488 ^ 23895 ^ -5523, 30104 ^ -13861 ^ 25646 ^ -10137));
            0cf.method_Yx(new 0cd(a, method_bco("")));
            method_bcC(method_bcB(this), new Session(a, method_bco(""), method_bco(""), method_bco("÷õðûôý")));
            break;
         case 6:
            method_bcD(this).displayGuiScreen(new 0cl(this));
            break;
         case 7:
            method_bcE(this).displayGuiScreen(new 0cx());
            break;
         case 8:
            0cf.method_Yz();
      }

   }

   private static lE method_beA(0ck var0) {
      return var0.field_c;
   }

   private static 0ci method_bee(0ck var0) {
      return var0.field_e;
   }

   private static nC method_bcH(0ck var0) {
      return var0.mc;
   }

   private static 0cc method_bdo() {
      return 0cc.field_d;
   }

   private boolean method_bcl(int a) {
      return (boolean)((float)a - method_bem(this) <= (float)(method_ben(this) - (2118 ^ -24316 ^ 7236 ^ -19148)) ? 18835 ^ -7717 ^ 16040 ^ -26911 : 27750 ^ -27930 ^ 2368 ^ -2112);
   }

   private static int method_bed(0ck var0) {
      return var0.height;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_bco(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 5013 ^ -17967 ^ 23592 ^ -2452; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 1940 ^ -20713 ^ 6307 ^ -20294));
      }

      return var1.toString();
   }

   private static nC method_bdt(0ck var0) {
      return var0.mc;
   }

   private static void method_bcM(0ck var0, float var1) {
      var0.field_b = var1;
   }

   private static void method_bcs(0ck var0, 0ce var1) {
      var0.field_h = var1;
   }

   private static int method_bdL(0ck var0) {
      return var0.width;
   }

   private static List method_beb(0ck var0) {
      return var0.buttonList;
   }

   private static int method_bcV(0ck var0) {
      return var0.width;
   }

   private static nC method_bcA(0ck var0) {
      return var0.mc;
   }

   private static List method_bdY(0ck var0) {
      return var0.buttonList;
   }

   private static int method_bdW(0ck var0) {
      return var0.width;
   }

   private static float method_bem(0ck var0) {
      return var0.field_b;
   }

   private static float method_bdg(0ck var0) {
      return var0.field_b;
   }

   private static 0ci method_bde(0ck var0) {
      return var0.field_f;
   }

   private static 0cc method_bdp() {
      return 0cc.field_e;
   }

   private static TextFormatting method_bcw() {
      return TextFormatting.GREEN;
   }

   protected void mouseClicked(int d, int e, int f) {
      method_bes(this).mouseClicked(d, e, f);
      if (method_bet(this) < Float.intBitsToFloat(29428 ^ '驡' ^ 8415 ^ 421220782 ^ 9927 ^ '\udaa3' ^ 23887 ^ 421214415)) {
         method_beu(this, Float.intBitsToFloat('\ua83c' ^ 104641 ^ '횥' ^ -1031485280 ^ '舦' ^ 95856 ^ 16283 ^ -1031477963));
      }

      double g = (double)(Float.intBitsToFloat(121864 ^ 102698 ^ 32404 ^ 2045432098 ^ 108486 ^ 100905 ^ 29466 ^ 1005763681) - method_bev(this));

      for(Iterator<0cd> b = this.method_bcn().iterator(); b.hasNext(); g += Double.longBitsToDouble(-7357010195063871578L ^ -2764464475052808282L)) {
         0cd a = (0cd)b.next();
         if (this.method_bcm((double)d, (double)e, g)) {
            if (a == method_bew(this)) {
               this.actionPerformed(method_bex(this));
               return;
            }

            method_bey(this, a);
         }
      }

      try {
         super.mouseClicked(d, e, f);
      } catch (IOException var8) {
         IOException c = var8;
         c.printStackTrace();
      }

   }

   private static 0ci method_bex(0ck var0) {
      return var0.field_e;
   }

   private static void method_bcK(0ck var0, float var1) {
      var0.field_b = var1;
   }

   private static void method_beh(0ci var0, boolean var1) {
      var0.enabled = var1;
   }

   private static nC method_bdF(0ck var0) {
      return var0.mc;
   }

   private static void method_beu(0ck var0, float var1) {
      var0.field_b = var1;
   }

   private static int method_bdH(0ck var0) {
      return var0.width;
   }
}
