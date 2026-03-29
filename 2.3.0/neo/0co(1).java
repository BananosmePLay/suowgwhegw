package neo;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class 0co extends lg implements 0cC, 0cB {
   public float field_l;
   public boolean field_a;
   public float field_h;
   public 0dz field_g;
   public float field_c;
   public float field_b = Float.intBitsToFloat(19468 ^ 18598 ^ 14069 ^ 499849907 ^ 21292 ^ 99536 ^ 13153 ^ 1598606449);
   public float field_f;
   public float field_j = Float.intBitsToFloat(19197 ^ '貛' ^ '\ue93f' ^ 1390936208 ^ 21378 ^ 1000545 ^ '\ud9b8' ^ 306700690);
   private final float field_e = Float.intBitsToFloat(24558 ^ 4337 ^ 16606 ^ 637019643 ^ 24701 ^ 31553 ^ 6607 ^ 1719445705);
   public float field_i;
   public float field_d;
   private final float field_k = Float.intBitsToFloat(116528 ^ 121582 ^ 12671 ^ 653056267 ^ 258164 ^ 237252 ^ 9842 ^ 1700117864);
   private static String _ _;

   private static float method_blG(0co var0) {
      return var0.field_f;
   }

   private static float method_bkI(0co var0) {
      return var0.field_j;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_bjJ(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 15259 ^ -22744 ^ 6147 ^ -31568; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 25479 ^ -1239 ^ 2261 ^ -28330));
      }

      return var1.toString();
   }

   private static float method_bkM(0co var0) {
      return var0.field_b;
   }

   private static float method_bkg(0co var0) {
      return var0.field_b;
   }

   private static 0dY method_bki() {
      return 0dZ.field_a;
   }

   private void method_bjB(int a, int b) {
      0cS c = method_bjX().method_Qt();
      0eo.method_bII(method_bjY(this), method_bjZ(this), Float.intBitsToFloat('폝' ^ 67036 ^ 20895 ^ -2013739635 ^ '퀯' ^ 20400 ^ '\ue8d1' ^ -1002424995), Float.intBitsToFloat(9787 ^ 466558 ^ 'ꯉ' ^ 312728579 ^ 127895 ^ 114165 ^ 8818 ^ 1361093535), Float.intBitsToFloat('ꡳ' ^ '뺍' ^ 7134 ^ -695655093 ^ 6919 ^ 128378 ^ 31102 ^ -1777811608), c.method_byP(6629 ^ -14384 ^ 2414 ^ -10405), c.method_byP(8331 ^ -8131 ^ 11730 ^ -4706), c.method_byP(29714 ^ -482 ^ 16197 ^ -18521), c.method_byP(1185 ^ -20862 ^ 30702 ^ -8667));
      0eo.method_bIF(method_bka(this) + Float.intBitsToFloat(31240 ^ '袵' ^ 4596 ^ 889352314 ^ 23772 ^ '쪜' ^ 12292 ^ 1963079031), method_bkb(this) + Float.intBitsToFloat('\uf2f7' ^ 'ꭒ' ^ 5047 ^ 1984300475 ^ '앲' ^ '꒓' ^ 4347 ^ 910573235), Float.intBitsToFloat(6743 ^ 106699 ^ 2511 ^ -2066304762 ^ 1397 ^ 4172315 ^ 27332 ^ -950024193), Float.intBitsToFloat('ꯘ' ^ 76130 ^ 1272 ^ -1391022145 ^ 699 ^ '곈' ^ 29818 ^ -292195340), Float.intBitsToFloat(20768 ^ 16749110 ^ 16761760 ^ -1182821591 ^ 15561 ^ '펣' ^ 24534 ^ -100719837), new Color(27015 ^ -5330 ^ 4132 ^ -28011, 10550 ^ -6416 ^ 12016 ^ -7890, 14823 ^ -3925 ^ 30531 ^ -16876, 26157 ^ -2997 ^ 591 ^ -28455));
      Color d = c.method_byP(2272 ^ -18283 ^ 5963 ^ -22722);
      0eo.method_bIF(method_bkc(this) + Float.intBitsToFloat('쮆' ^ 225075 ^ 11363 ^ -1763845189 ^ 8480 ^ 241346 ^ 443 ^ -690102988), method_bkd(this) + Float.intBitsToFloat(128522 ^ 124069 ^ 6015 ^ 2037956691 ^ 15943 ^ 83478 ^ 114918 ^ 964197684), Float.intBitsToFloat(18648 ^ '떰' ^ 29848 ^ -1372254456 ^ 31875 ^ 'ￍ' ^ 29228 ^ -324175974), Float.intBitsToFloat(25050 ^ 247005 ^ 20691 ^ 798092365 ^ 1871 ^ '\ue752' ^ 23534 ^ 1812996714), Float.intBitsToFloat('蹉' ^ '혃' ^ 22983 ^ 1225150068 ^ '\udd60' ^ '逤' ^ 8306 ^ 159785679), new Color(d.getRed(), d.getGreen(), d.getBlue(), 16473 ^ -3620 ^ 12536 ^ -32311));
      0eo.method_bIF(method_bke(this) + Float.intBitsToFloat(3503 ^ 29184 ^ 13367 ^ 342203031 ^ 9691 ^ 213153 ^ 255895 ^ 1415926754), method_bkf(this) + Float.intBitsToFloat(4890 ^ 17605 ^ 3409 ^ -176482829 ^ '쫽' ^ 91511 ^ 12791 ^ -1250242304), Float.intBitsToFloat('輆' ^ 1006135 ^ 14277 ^ 791137871 ^ 4893 ^ 24759 ^ 31588 ^ 1841177717), Float.intBitsToFloat(25911 ^ '鋠' ^ 10666 ^ 1691371268 ^ 28565 ^ 229381 ^ 6902 ^ 659680287), Float.intBitsToFloat('퇯' ^ 222719 ^ 3971 ^ -1260261089 ^ '\ue216' ^ '蒚' ^ 9983 ^ -194901249), new Color(14042 ^ -30869 ^ 5941 ^ -22875, 30784 ^ -30299 ^ 207 ^ -3830, 21604 ^ -6521 ^ 23374 ^ -5755, 21761 ^ -31620 ^ 26646 ^ -18021));
      0en.method_bGp(new ResourceLocation(method_bjJ("ŃňłŚŌşňĂńŀŌŊňŞĂŃňłŚŌşňăŝŃŊ")), method_bkg(this) + Float.intBitsToFloat(11385 ^ '놑' ^ '줯' ^ -1771829441 ^ '鿎' ^ '｜' ^ 24956 ^ -681291242), method_bkh(this) + Float.intBitsToFloat(11664 ^ 232387 ^ 25051 ^ -623000328 ^ 28445 ^ 248865 ^ 6549 ^ -1677870631), Float.intBitsToFloat('젙' ^ '퀱' ^ 13462 ^ 1642683915 ^ 1674 ^ 96394 ^ '\uf678' ^ 541653709), Float.intBitsToFloat(19774 ^ 230091 ^ 255721 ^ -568484097 ^ 30534 ^ 242908 ^ 250069 ^ -1614961492), new Color(11148 ^ -3891 ^ 6146 ^ -15428, 11449 ^ -932 ^ 32147 ^ -21111, 23259 ^ -3244 ^ 2023 ^ -20841));
      method_bki().method_bEG(method_bjJ("ţňłźŌşň"), method_bkj(this) + Float.intBitsToFloat(18230 ^ '赯' ^ 6776 ^ -1706794008 ^ 25709 ^ 108766 ^ 124621 ^ -666592841), method_bkk(this) + Float.intBitsToFloat(17417 ^ '뵵' ^ 17738 ^ -1957942532 ^ 15252 ^ 'ｖ' ^ 6754 ^ -904116118));
      method_bkl().method_bED(method_bjJ("śňşăč") + 0dH.method_bDy(), method_bkm(this) + Float.intBitsToFloat('韮' ^ 492443 ^ '饞' ^ -1172571291 ^ '뮑' ^ '쌘' ^ 12239 ^ -132374264), method_bkn(this) + Float.intBitsToFloat(4145 ^ 1015363 ^ 1045755 ^ -111709238 ^ 2094520 ^ 21475 ^ 2091611 ^ -1192254141), (new Color(8352 ^ -47 ^ 12893 ^ -4653, 27712 ^ -627 ^ 11680 ^ -17262, 23499 ^ -12115 ^ 8941 ^ -22156, 25043 ^ -16528 ^ 20602 ^ -29146)).getRGB());
      this.method_bjD();
      this.method_bjC();
      0en.method_bGf(method_bko(this) + Float.intBitsToFloat(17489 ^ 110408 ^ 1658 ^ -250945723 ^ 25332 ^ '뭈' ^ '잵' ^ -1324667857), method_bkp(this) + Float.intBitsToFloat(9579 ^ '궭' ^ 23683 ^ -561296425 ^ 497759 ^ 21605 ^ 514079 ^ -1635021897) + method_bkq(this), Float.intBitsToFloat(10686 ^ 235222 ^ 258608 ^ -144073441 ^ 884 ^ 78709 ^ 103278 ^ -1260489944), Double.longBitsToDouble(-7503737678886406849L ^ -2905650420271352513L));
      switch (method_bkr()[method_bks(this).ordinal()]) {
         case 1:
            this.method_bjF(a, b);
            break;
         case 2:
            this.method_bjG(a, b);
            break;
         default:
            this.method_bjE(a, b);
      }

      0en.method_bGg();
      if (method_bkt(this) && Mouse.isButtonDown(21149 ^ -6985 ^ 12709 ^ -30833)) {
         method_bkv(this, (float)a + method_bku(this));
         method_bkx(this, (float)b + method_bkw(this));
      } else {
         method_bky(this, (boolean)(30338 ^ -18780 ^ 30393 ^ -18785));
      }

   }

   private static float method_bkp(0co var0) {
      return var0.field_j;
   }

   private static void method_bkF(0co var0, float var1) {
      var0.field_f = var1;
   }

   private static float method_bkR(0co var0) {
      return var0.field_j;
   }

   private static 0dY method_bkG() {
      return 0dZ.field_k;
   }

   private static 0dY method_bkZ() {
      return 0dZ.field_j;
   }

   private static float method_blt(0co var0) {
      return var0.field_j;
   }

   private static float method_bmi(0co var0) {
      return var0.field_b;
   }

   private static 0dY method_bkL() {
      return 0dZ.field_b;
   }

   private static float method_bkh(0co var0) {
      return var0.field_j;
   }

   private static 0dY method_blB() {
      return 0dZ.field_a;
   }

   public _co/* $FF was: 0co*/() {
      this.field_g = 0dz.field_h;
      this.field_d = Float.intBitsToFloat(233495 ^ 'ꈒ' ^ 246313 ^ 113066476 ^ 23880 ^ '먪' ^ 4936 ^ 113067498);
      this.field_f = Float.intBitsToFloat(112235 ^ 10542 ^ 107545 ^ 516871152 ^ 100461 ^ 106177 ^ 32091 ^ 516850523);
   }

   private static 0dz method_bkO(0co var0) {
      return var0.field_g;
   }

   private static 0cG method_blD() {
      return botManager;
   }

   private static float method_bmQ(0co var0) {
      return var0.field_d;
   }

   private static void method_bkx(0co var0, float var1) {
      var0.field_j = var1;
   }

   private static float method_bke(0co var0) {
      return var0.field_b;
   }

   private static 0cG method_blv() {
      return botManager;
   }

   private static float method_bkc(0co var0) {
      return var0.field_b;
   }

   private static 0dY method_blb() {
      return 0dZ.field_b;
   }

   private static float method_bmM(0co var0) {
      return var0.field_b;
   }

   private static nC method_bmK() {
      return 0cD.mc;
   }

   private static 0dY method_blC() {
      return 0dZ.field_a;
   }

   private static int method_bmL(nC var0) {
      return var0.displayHeight;
   }

   private static 0dY method_blA() {
      return 0dZ.field_a;
   }

   private static float method_bmA(0co var0) {
      return var0.field_j;
   }

   private static int method_bmJ(nC var0) {
      return var0.displayWidth;
   }

   private static float method_bkD(0co var0) {
      return var0.field_f;
   }

   private static int[] method_bmg() {
      return 0cn.field_a;
   }

   private static 0dz method_bmh(0co var0) {
      return var0.field_g;
   }

   private static 0bz method_bmW() {
      return client;
   }

   private static void method_bjN(0co var0, float var1) {
      var0.field_h = var1;
   }

   private static 0dY method_blx() {
      return 0dZ.field_c;
   }

   private static nC method_bjW(0co var0) {
      return var0.mc;
   }

   private void method_bjG(int b, int c) {
      0dG d = method_blH().method_Qt().method_byM();
      float e = method_blI(this) + Float.intBitsToFloat(30115 ^ '鑯' ^ 8982 ^ 1052466797 ^ 2064388 ^ '툍' ^ 2094339 ^ 2081373117);
      float f = method_blJ(this) + Float.intBitsToFloat(23599 ^ '곳' ^ 26508 ^ -2094850126 ^ 10759 ^ 8836 ^ 2286 ^ -1034712945) + method_blK(this);
      int g = 27672 ^ -6990 ^ 3900 ^ -30826;
      Iterator var7 = method_blL().method_Qt().iterator();

      while(var7.hasNext()) {
         0dG a = (0dG)var7.next();
         0eo.method_bIL(e, f, Float.intBitsToFloat(111602 ^ 13746 ^ 106561 ^ -1817655830 ^ 99083 ^ 98213 ^ 28996 ^ -782733823), Float.intBitsToFloat(5763 ^ '곾' ^ 22852 ^ -1967365780 ^ 22423 ^ '\ude9b' ^ 12839 ^ -924829314), Float.intBitsToFloat('럭' ^ '鲁' ^ 22542 ^ -485158120 ^ '뮦' ^ '쓹' ^ 23934 ^ -1550499237), Float.intBitsToFloat(13662 ^ 491059 ^ '\uf756' ^ 714337473 ^ 251772 ^ 221921 ^ 11215 ^ -1811065038), new Color(21358 ^ -30469 ^ 25740 ^ -16584, 3044 ^ -11423 ^ 4047 ^ -10390, 12219 ^ -14715 ^ 16777 ^ -22369, 4492 ^ -4349 ^ 19664 ^ -19793), new Color(27794 ^ -32012 ^ 1801 ^ -5798, 10662 ^ -12314 ^ 19304 ^ -21220, 14167 ^ -15393 ^ 23637 ^ -22303, 4896 ^ -6683 ^ 6719 ^ -5110));
         0en.method_bGt(e, f + Float.intBitsToFloat(30682 ^ '팪' ^ 9391 ^ -732692004 ^ 20609 ^ '鼾' ^ 24211 ^ -1782312785), Float.intBitsToFloat(14450 ^ 12470 ^ 6814 ^ 377081379 ^ 235118 ^ 231849 ^ 21763 ^ 1418303165), Float.intBitsToFloat(15917 ^ 496882 ^ 8333 ^ -585922523 ^ 6668 ^ 498340 ^ 24404 ^ -493625973), new Color(14395 ^ -10511 ^ 20893 ^ -16542, 17202 ^ -5219 ^ 25825 ^ -13190, 29367 ^ -23104 ^ 28379 ^ -18032, 417 ^ -14159 ^ 6847 ^ -11425));
         method_blM().method_bED(a.method_bDt(), e + Float.intBitsToFloat('\ue199' ^ 29541 ^ '슨' ^ 781845093 ^ '骥' ^ '뇇' ^ 19509 ^ 1872377190), f + Float.intBitsToFloat(492479 ^ 496035 ^ 13810 ^ -2144107328 ^ 29698 ^ 25037 ^ 10531 ^ -1059872830), (a.equals(d) ? d.method_bDr() : new Color(27166 ^ -9041 ^ 2149 ^ -16855, 31164 ^ -20129 ^ 11251 ^ -7187, 15519 ^ -16678 ^ 13086 ^ -20058)).getRGB());
         method_blN().method_bED(method_bjJ("ŋ"), e + Float.intBitsToFloat(14271 ^ '봸' ^ '\ufde7' ^ 1347038263 ^ 14604 ^ 229747 ^ 8189 ^ 312081621), f + Float.intBitsToFloat('\ud905' ^ '觤' ^ 25707 ^ -1359456069 ^ '\udda2' ^ '铢' ^ 15751 ^ -268953354), (new Color(24880 ^ -29306 ^ 22544 ^ -19202, 32219 ^ -22070 ^ 12971 ^ -6418, 14036 ^ -8705 ^ 19977 ^ -23216)).getRGB());
         0eo.method_bIJ(e + Float.intBitsToFloat(5756 ^ 104526 ^ 17570 ^ 1067378114 ^ '\ua958' ^ 99566 ^ '좮' ^ 2141112906), f + Float.intBitsToFloat(21479 ^ '턫' ^ 20997 ^ -1233493854 ^ 105003 ^ '鋔' ^ 127597 ^ -136688903), Float.intBitsToFloat('쌙' ^ '赟' ^ 12764 ^ 1633173322 ^ 8412 ^ 2064807 ^ 15122 ^ 598727353), Float.intBitsToFloat(6950 ^ '\ue263' ^ 17839 ^ 760559990 ^ 22508 ^ '許' ^ 15435 ^ 1867605002), Float.intBitsToFloat(17833 ^ '隭' ^ 26667 ^ 554641189 ^ 13976 ^ '\ue015' ^ 31653 ^ 1632579874), a.method_bDr(), a.method_bDr(), a.method_bDs(), a.method_bDs(), 5635 ^ -25764 ^ 2774 ^ -30858);
         0eo.method_bIJ(e + Float.intBitsToFloat(11454 ^ 1008124 ^ 1043769 ^ -643338433 ^ 1043768 ^ 1014070 ^ 3323 ^ -1717086287), f + Float.intBitsToFloat('껰' ^ 93691 ^ 6201 ^ -1933378003 ^ 27504 ^ 78682 ^ '쬹' ^ -849168884), Float.intBitsToFloat(3585 ^ '붨' ^ 1193 ^ -1014074144 ^ 124101 ^ 113209 ^ 16246 ^ -2123980182), Float.intBitsToFloat(124956 ^ '颼' ^ 123095 ^ -851807191 ^ 107972 ^ 101076 ^ 5366 ^ -1921327176), Float.intBitsToFloat(83 ^ 104742 ^ 27654 ^ 1196879811 ^ '錎' ^ 109000 ^ '텙' ^ 1196880175), a.method_bDr(), a.method_bDr(), a.method_bDs(), a.method_bDs(), 23545 ^ -13873 ^ 12006 ^ -17361);
         f += Float.intBitsToFloat(109496 ^ 101422 ^ 4863 ^ 52871707 ^ 122377 ^ 100460 ^ 15845 ^ 1101831410);
         ++g;
         if (g > method_blO().method_Qt().size() / (18263 ^ -5962 ^ 13508 ^ -25817)) {
            g = 13774 ^ -790 ^ 24627 ^ -22249;
            e = method_blP(this) + Float.intBitsToFloat('難' ^ 16429 ^ 'ﴣ' ^ -1604966508 ^ '꭫' ^ '펮' ^ 22501 ^ -488484995) + Float.intBitsToFloat('\ufbc8' ^ '\uf1ba' ^ 8923 ^ -953925280 ^ 6531 ^ 102574 ^ 3803 ^ -2078085569);
            f = method_blQ(this) + Float.intBitsToFloat('\ue1d3' ^ '쉨' ^ 25490 ^ -826729712 ^ 8628 ^ 16992 ^ 5935 ^ -1882640446) + method_blR(this);
         }
      }

   }

   private static float method_bmB(0co var0) {
      return var0.field_f;
   }

   private static void method_bme(0co var0, 0dz var1) {
      var0.field_g = var1;
   }

   private static float method_blV(0co var0) {
      return var0.field_j;
   }

   private static nC method_bmI() {
      return 0cD.mc;
   }

   private static 0dz method_bkU(0co var0) {
      return var0.field_g;
   }

   private static float method_bmq(0co var0) {
      return var0.field_b;
   }

   private static float method_blu(0co var0) {
      return var0.field_f;
   }

   private static 0dY method_blM() {
      return 0dZ.field_i;
   }

   private static void method_bkv(0co var0, float var1) {
      var0.field_b = var1;
   }

   private static float method_bkm(0co var0) {
      return var0.field_b;
   }

   private static 0cG method_bml() {
      return botManager;
   }

   private static void method_bmR(0co var0, float var1) {
      var0.field_d = var1;
   }

   private static float method_bjP(0co var0) {
      return var0.field_h;
   }

   private static 0dY method_blN() {
      return 0dZ.field_c;
   }

   private static void method_bjO(0co var0, float var1) {
      var0.field_i = var1;
   }

   private static nC method_bmH(0co var0) {
      return var0.mc;
   }

   private static 0bz method_bjX() {
      return client;
   }

   private static 0dY method_bli() {
      return 0dZ.field_e;
   }

   private static 0bz method_bkK() {
      return client;
   }

   private static float method_bms(0co var0) {
      return var0.field_f;
   }

   private static float method_bkq(0co var0) {
      return var0.field_h;
   }

   private static float method_bmk(0co var0) {
      return var0.field_f;
   }

   private static void method_bmf(0co var0, float var1) {
      var0.field_d = var1;
   }

   private static int[] method_bkr() {
      return 0cn.field_a;
   }

   private static float method_bjU(0co var0) {
      return var0.field_h;
   }

   private static 0dY method_bkW() {
      return 0dZ.field_c;
   }

   private static void method_bjM(0co var0, float var1) {
      var0.field_j = var1;
   }

   private static float method_blq(0co var0) {
      return var0.field_f;
   }

   private static 0bz method_blO() {
      return client;
   }

   private static float method_bmy(0co var0) {
      return var0.field_f;
   }

   private static 0dY method_blk() {
      return 0dZ.field_b;
   }

   private static float method_bmn(0co var0) {
      return var0.field_b;
   }

   private static float method_blF(0co var0) {
      return var0.field_j;
   }

   private static 0bz method_blH() {
      return client;
   }

   private static float method_bmE(0co var0) {
      return var0.field_b;
   }

   private static 0dz method_bmX(0co var0) {
      return var0.field_g;
   }

   public void mouseClicked(int x, int y, int z) {
      if (this.method_bjI(x, y, (double)method_blS(this), (double)method_blT(this), Double.longBitsToDouble(-432675712177439761L ^ -5077259118507885585L), Double.longBitsToDouble(8594045856451864030L ^ 3978014568071505374L))) {
         if (this.method_bjI(x, y, (double)method_blU(this), (double)method_blV(this), Double.longBitsToDouble(2465300538868937330L ^ 7080117966412231282L), Double.longBitsToDouble(-5853254534487046855L ^ -1230872466944654023L)) && z == 0) {
            method_blW(this, (boolean)(23057 ^ -26522 ^ 30899 ^ -17723));
            method_blY(this, method_blX(this) - (float)x);
            method_bma(this, method_blZ(this) - (float)y);
         }

         int b = 4358 ^ -24806 ^ 24174 ^ -12219;
         0dz[] var5 = 0dz.values();
         int j = var5.length;

         int w;
         for(w = 30907 ^ -26195 ^ 26093 ^ -31493; w < j; ++w) {
            0dz a = var5[w];
            if (this.method_bjI(x, y, (double)(method_bmb(this) + Float.intBitsToFloat('颻' ^ '迌' ^ 1326 ^ 410526679 ^ 17362 ^ 1021029 ^ 88 ^ 1498932833)), (double)(method_bmc(this) + (float)b - Float.intBitsToFloat(67099703 ^ '谾' ^ 67099145 ^ -2046859128 ^ 4191524 ^ 4163129 ^ 2631 ^ -983589934)), Double.longBitsToDouble(-242272056221658908L ^ -4833128926372458268L), Double.longBitsToDouble(-6666232564649284199L ^ -2069183245010850407L))) {
               if (method_bmd(this) != a) {
                  method_bme(this, a);
                  method_bmf(this, Float.intBitsToFloat(112238 ^ '\ue7f8' ^ 117123 ^ 1564360424 ^ 29435 ^ 68679 ^ 127654 ^ 1564355303));
               }
               break;
            }

            b += a.getOffset();
         }

         float t;
         float u;
         Iterator var20;
         switch (method_bmg()[method_bmh(this).ordinal()]) {
            case 1:
               t = method_bmi(this) + Float.intBitsToFloat(13548 ^ '腖' ^ 6663 ^ -1188939612 ^ 28406 ^ 'ꀏ' ^ 20185 ^ -74049735);
               u = method_bmj(this) + Float.intBitsToFloat(103502 ^ '켭' ^ 124376 ^ 325987177 ^ 2848 ^ 69880 ^ 104215 ^ 1377704221) + method_bmk(this);
               j = 19352 ^ -16938 ^ 29900 ^ -32126;
               var20 = method_bml().method_bwh().iterator();

               while(var20.hasNext()) {
                  0dD c = (0dD)var20.next();
                  if (this.method_bjI(x, y, (double)t, (double)u, Double.longBitsToDouble(-1511674441048535690L ^ -6099153611478807178L), Double.longBitsToDouble(-8848240007189906538L ^ -4251190687551472746L)) && z == 0) {
                     c.method_bCA();
                  }

                  u += Float.intBitsToFloat(105320 ^ 109871 ^ 17449 ^ -338255747 ^ 11895 ^ 105057 ^ 121751 ^ -1453539950);
                  ++j;
                  if (j > method_bmm().method_bwh().size() / (7007 ^ -19307 ^ 11161 ^ -31663)) {
                     j = 16504 ^ -3689 ^ 22459 ^ -6572;
                     t = method_bmn(this) + Float.intBitsToFloat(12812 ^ 214457 ^ '삒' ^ -1764361708 ^ 11304 ^ 6323 ^ 12860 ^ -731755628) + Float.intBitsToFloat(18547 ^ 19996 ^ 10562 ^ -187098656 ^ 32133 ^ 223028 ^ '惡' ^ -1210198587);
                     u = method_bmo(this) + Float.intBitsToFloat('쀻' ^ '섲' ^ 4072 ^ 1560962545 ^ 'ꅜ' ^ 21626 ^ '닫' ^ 477768925) + method_bmp(this);
                  }
               }

               return;
            case 2:
               t = method_bmq(this) + Float.intBitsToFloat(256857 ^ 236004 ^ 27342 ^ -22952023 ^ 259950 ^ 9826 ^ 246171 ^ -1139421363);
               u = method_bmr(this) + Float.intBitsToFloat(39 ^ 25052 ^ 10294 ^ 804328267 ^ '횊' ^ '銨' ^ 23227 ^ 1853965343) + method_bms(this);
               j = 2413 ^ -7072 ^ 13596 ^ -10223;
               var20 = method_bmt().method_Qt().iterator();

               while(var20.hasNext()) {
                  0dG g = (0dG)var20.next();
                  if (this.method_bjI(x, y, (double)t, (double)u, Double.longBitsToDouble(-487087836734571065L ^ -5088077806046954041L), Double.longBitsToDouble(8835846019228187091L ^ 4239218912054819283L)) && z == 0) {
                     method_bmu().method_Qt().method_byN(g);
                  }

                  u += Float.intBitsToFloat('ﰩ' ^ 112597 ^ '\udafa' ^ 836687862 ^ 4473 ^ 'ꫯ' ^ '잓' ^ 1934965493);
                  ++j;
                  if (j > method_bmv().method_Qt().size() / (26308 ^ -11861 ^ 28715 ^ -14522)) {
                     j = 10962 ^ -888 ^ 5935 ^ -16011;
                     t = method_bmw(this) + Float.intBitsToFloat('ꮼ' ^ 109951 ^ '\ue69f' ^ 909690357 ^ 4126 ^ 85425 ^ '먋' ^ 1955381773) + Float.intBitsToFloat(10003 ^ 509056 ^ 5386 ^ 910420647 ^ 30722 ^ '釰' ^ '\uf294' ^ 1967327064);
                     u = method_bmx(this) + Float.intBitsToFloat('\ud84c' ^ '긛' ^ 15141 ^ -264886026 ^ '\ue52a' ^ '좸' ^ 22754 ^ -1320808204) + method_bmy(this);
                  }
               }

               return;
            default:
               t = method_bmz(this) + Float.intBitsToFloat(110081 ^ 24673 ^ 117629 ^ -1709661994 ^ 25180 ^ '錧' ^ 5853 ^ -659799443);
               u = method_bmA(this) + Float.intBitsToFloat(18240 ^ '텹' ^ 10779 ^ -81905095 ^ 4694 ^ '\ude8c' ^ 14456 ^ -1167166791) + method_bmB(this);
               ArrayList<0dr> v = new ArrayList(method_bmC().method_Qs().method_bya(method_bmD(this)));
               w = 18222 ^ -11880 ^ 14752 ^ -20714;
               Iterator var24 = v.iterator();

               while(var24.hasNext()) {
                  0dr s = (0dr)var24.next();
                  if (this.method_bjI(x, y, (double)t, (double)u, Double.longBitsToDouble(7801637078065782591L ^ 3178129110616547135L), Double.longBitsToDouble(-2913170082603447727L ^ -7519226601496622511L))) {
                     if (z == 0) {
                        s.method_bBi();
                     } else if (z == (15181 ^ -22112 ^ 10476 ^ -17920)) {
                        s.method_bBg((boolean)(!s.method_bBf() ? 2200 ^ -10888 ^ 31108 ^ -23451 : 7290 ^ -19125 ^ 14833 ^ -28480));
                     }
                  }

                  float q = t;
                  float r = u + Float.intBitsToFloat(8060 ^ '贇' ^ 24902 ^ 747206845 ^ 5211 ^ '\ued33' ^ 3830 ^ 1833008158);
                  if (s.method_bBf()) {
                     Iterator var12 = s.method_bBd().iterator();

                     label144:
                     while(true) {
                        0cv p;
                        do {
                           if (!var12.hasNext()) {
                              break label144;
                           }

                           p = (0cv)var12.next();
                        } while(!p.method_bnY());

                        if (p instanceof 0cp) {
                           0cp k = (0cp)p;
                           if (this.method_bjI(x, y, (double)q, (double)r, Double.longBitsToDouble(-5468671378954339610L ^ -845163411505104154L), (double)k.method_bob())) {
                              k.method_bmZ((boolean)(!k.method_bna() ? 25174 ^ -25640 ^ 1432 ^ -1001 : 32267 ^ -8477 ^ 18951 ^ -5393));
                           }
                        } else if (p instanceof 0cq) {
                           0cq var26 = (0cq)p;
                        } else if (p instanceof 0cu) {
                           0cu l = (0cu)p;
                           if (this.method_bjI(x, y, (double)q, (double)r, Double.longBitsToDouble(-8252832168494921162L ^ -3664227098157807050L), Double.longBitsToDouble(-7122339943813241570L ^ -2521912924454279906L))) {
                              l.method_bnS((boolean)(!l.method_bnR() ? 3360 ^ -30135 ^ 11746 ^ -21878 : 26603 ^ -10434 ^ 28383 ^ -8694));
                           } else {
                              l.method_bnS((boolean)(13331 ^ -26861 ^ 6991 ^ -18353));
                           }
                        } else if (p instanceof 0cs) {
                           0cs o = (0cs)p;
                           if (this.method_bjI(x, y, (double)q, (double)r, Double.longBitsToDouble(2716132978205536289L ^ 7345270445188984865L), Double.longBitsToDouble(-2883409955063152408L ^ -7506354972558966552L))) {
                              o.method_bnu((boolean)(!o.method_bnt() ? 11691 ^ -24410 ^ 5369 ^ -26123 : 28739 ^ -16660 ^ 26025 ^ -21754));
                           }

                           if (o.method_bnt()) {
                              int n = 20198 ^ -20122 ^ 28875 ^ -28857;

                              for(Iterator var16 = o.method_bnv().iterator(); var16.hasNext(); n += 8) {
                                 String m = (String)var16.next();
                                 if (this.method_bjI(x, y, (double)(q + Float.intBitsToFloat(19442 ^ 22444 ^ 13173 ^ 1283949912 ^ 22069 ^ 11299 ^ 18746 ^ 249272671)), (double)(r + Float.intBitsToFloat('등' ^ 1023193 ^ '\uf4f7' ^ -1805131127 ^ 28507 ^ 22005 ^ 11507 ^ -735565813) + (float)n), Double.longBitsToDouble(-7281892830756885487L ^ -2684280561165030383L), Double.longBitsToDouble(-7008782734386335390L ^ -2408355715027373726L))) {
                                    o.method_bns(m);
                                 }
                              }
                           }
                        }

                        r += (float)(p.method_bob() + (24697 ^ -22129 ^ 30511 ^ -16676));
                     }
                  }

                  u += (float)(s.method_bAY() + (1826 ^ -26942 ^ 30243 ^ -6195));
                  ++w;
                  if (w > v.size() / (30252 ^ -28010 ^ 783 ^ -6217)) {
                     w = 2317 ^ -1666 ^ 5564 ^ -6705;
                     t = method_bmE(this) + Float.intBitsToFloat(8422 ^ 224656 ^ 248665 ^ -840350045 ^ 5270 ^ 242231 ^ 246680 ^ -1889688139) + Float.intBitsToFloat(2503 ^ 32304 ^ 24703 ^ 2045781534 ^ 17626 ^ 102057 ^ 7677 ^ 989259288);
                     u = method_bmF(this) + Float.intBitsToFloat(98535 ^ '킅' ^ 100918 ^ 653942683 ^ 7941 ^ '흡' ^ 23431 ^ 1737102892) + method_bmG(this);
                  }
               }

         }
      }
   }

   private static float method_bkS(0co var0) {
      return var0.field_f;
   }

   private static 0dz method_bks(0co var0) {
      return var0.field_g;
   }

   private static float method_blT(0co var0) {
      return var0.field_j;
   }

   private static 0bz method_bmC() {
      return client;
   }

   public void handleMouseInput() throws IOException {
      mC b = new mC(method_bmH(this));
      int c = Mouse.getEventX() * b.getScaledWidth() / method_bmJ(method_bmI());
      int d = b.getScaledHeight() - Mouse.getEventY() * b.getScaledHeight() / method_bmL(method_bmK()) - (112 ^ -10276 ^ 22691 ^ -28914);
      if (Mouse.hasWheel() && this.method_bjI(c, d, (double)(method_bmM(this) + Float.intBitsToFloat(15223 ^ 93268 ^ 125976 ^ 645818225 ^ 9614 ^ 123251 ^ 112108 ^ 1692314971)), (double)method_bmN(this), Double.longBitsToDouble(-1119838614192699836L ^ -5763859070569724348L), Double.longBitsToDouble(2069282044068032460L ^ 6685313332448391116L))) {
         int a = Mouse.getDWheel();
         if (a > 0) {
            method_bmP(this, method_bmO(this) + Float.intBitsToFloat('胧' ^ 2065708 ^ '잛' ^ -730861870 ^ '轭' ^ 67091788 ^ '헇' ^ -1778398364));
         } else if (a < 0) {
            method_bmR(this, method_bmQ(this) - Float.intBitsToFloat(28113 ^ 26928 ^ 14759 ^ -482240807 ^ 30889 ^ 205240 ^ 253791 ^ -1590612527));
         }
      }

      super.handleMouseInput();
   }

   private static boolean method_blj(0cu var0) {
      return var0.field_d;
   }

   private static 0dz method_bmV(0co var0) {
      return var0.field_g;
   }

   public boolean method_bjI(int a, int b, double c, double d, double e, double f) {
      return (boolean)((double)a >= c && (double)a <= c + e && (double)b >= d && (double)b <= d + f ? 31554 ^ -31766 ^ 28090 ^ -27373 : 29985 ^ -12790 ^ 3596 ^ -19161);
   }

   private static void method_bjL(0co var0, float var1) {
      var0.field_b = var1;
   }

   private static boolean method_blf(0cu var0) {
      return var0.field_d;
   }

   private static float method_blR(0co var0) {
      return var0.field_f;
   }

   private static 0dY method_bly() {
      return 0dZ.field_a;
   }

   private static void method_bma(0co var0, float var1) {
      var0.field_l = var1;
   }

   private static float method_bku(0co var0) {
      return var0.field_c;
   }

   private static 0bz method_bmu() {
      return client;
   }

   private static float method_blK(0co var0) {
      return var0.field_f;
   }

   private static 0dY method_bkV() {
      return 0dZ.field_i;
   }

   private static 0dY method_blh() {
      return 0dZ.field_b;
   }

   private static nC method_bjK(0co var0) {
      return var0.mc;
   }

   private static 0dY method_blw() {
      return 0dZ.field_i;
   }

   private static float method_blp(0co var0) {
      return var0.field_j;
   }

   public void drawScreen(int a, int b, float c) {
      yh.pushMatrix();
      yh.translate(Float.intBitsToFloat(236108 ^ 240827 ^ 16371 ^ 1571156349 ^ 250701 ^ 231293 ^ 28018 ^ 1571143995), method_bjP(this), Float.intBitsToFloat(513683 ^ 507436 ^ 7434 ^ 1986761047 ^ 520721 ^ 505426 ^ 8306 ^ 1986756819));
      method_bjS(this, 0ea.method_bFe(method_bjQ(this), method_bjR(this), Float.intBitsToFloat(1031241 ^ 1015807 ^ 19672 ^ -91784186 ^ 1026712 ^ 1025355 ^ 10449 ^ -1165503382)));
      this.method_bjB(a, b);
      yh.popMatrix();
      if (method_bjT(this) > Float.intBitsToFloat(24352 ^ 1038454 ^ 11419 ^ -1350062653 ^ 19715 ^ 1042005 ^ 32096 ^ -1350053832) && Math.abs(method_bjU(this) - method_bjV(this)) < Float.intBitsToFloat(8730 ^ '숨' ^ 19948 ^ 1648438566 ^ 27578 ^ '餘' ^ 13662 ^ 545867524)) {
         method_bjW(this).displayGuiScreen((lg)null);
      }

   }

   private static float method_blP(0co var0) {
      return var0.field_b;
   }

   private static 0dY method_bld() {
      return 0dZ.field_b;
   }

   private static float method_bko(0co var0) {
      return var0.field_b;
   }

   private static 0dY method_bkY() {
      return 0dZ.field_b;
   }

   private void method_bjD() {
      int b = 30484 ^ -2037 ^ 31884 ^ -3164;
      0dz[] var2 = 0dz.values();
      int var3 = var2.length;

      for(int var4 = 2020 ^ -277 ^ 29779 ^ -29348; var4 < var3; ++var4) {
         0dz a = var2[var4];
         method_bkG().method_bED(a.getIcon(), method_bkH(this) + Float.intBitsToFloat('뙳' ^ 79114 ^ 1236 ^ 2090008241 ^ '\ue61f' ^ 'ꥳ' ^ 20485 ^ 1035167349), method_bkI(this) + (float)b, (a == method_bkJ(this) ? method_bkK().method_Qt().method_byM().method_bDr() : new Color(25973 ^ -1811 ^ 29798 ^ -5784, 8475 ^ -25881 ^ 28319 ^ -10763, 4073 ^ -6456 ^ 20053 ^ -22558)).getRGB());
         method_bkL().method_bED(a.name().toUpperCase(), method_bkM(this) + Float.intBitsToFloat('\ue18f' ^ 80436 ^ 27967 ^ -1974539496 ^ '頭' ^ 100306 ^ 'ꨯ' ^ -876681140), method_bkN(this) + (float)b + Float.intBitsToFloat(9816 ^ 246843 ^ 17947 ^ 1677834136 ^ 23976 ^ 246454 ^ 21426 ^ 608296780), (a == method_bkO(this) ? new Color(12473 ^ -8337 ^ 2302 ^ -6244, 8476 ^ -9039 ^ 27681 ^ -28360, 27230 ^ -9899 ^ 2715 ^ -18140) : new Color(29417 ^ -6907 ^ 30173 ^ -7513, 12882 ^ -21250 ^ 1442 ^ -25704, 11528 ^ -32284 ^ 4910 ^ -16556)).getRGB());
         b += a.getOffset();
      }

   }

   private static float method_bkw(0co var0) {
      return var0.field_l;
   }

   private static void method_blY(0co var0, float var1) {
      var0.field_c = var1;
   }

   private static 0bz method_blr() {
      return client;
   }

   private static float method_bls(0co var0) {
      return var0.field_b;
   }

   private static float method_bjT(0co var0) {
      return var0.field_i;
   }

   private static float method_bka(0co var0) {
      return var0.field_b;
   }

   private void method_bjF(int d, int e) {
      0dG f = method_blr().method_Qt().method_byM();
      float g = method_bls(this) + Float.intBitsToFloat('벣' ^ 24384 ^ '\ue262' ^ -1023788401 ^ 'ꏾ' ^ '꿨' ^ 10972 ^ -2142364220);
      float h = method_blt(this) + Float.intBitsToFloat(12473 ^ 115992 ^ 16678 ^ 1290576847 ^ 21414 ^ 112618 ^ 1111 ^ 228390739) + method_blu(this);
      int i = 14730 ^ -2842 ^ 5348 ^ -9848;
      Iterator var7 = method_blv().method_bwh().iterator();

      while(var7.hasNext()) {
         0dD c = (0dD)var7.next();
         0eo.method_bIL(g, h, Float.intBitsToFloat(72 ^ 100706 ^ 124321 ^ 1775675825 ^ 127206 ^ 110178 ^ 3763 ^ 723948813), Float.intBitsToFloat('떠' ^ 30267 ^ '\ue733' ^ 1306527314 ^ '\ua634' ^ 517611 ^ '뭤' ^ 263969345), Float.intBitsToFloat(6757 ^ 468499 ^ 504953 ^ 1645963783 ^ 505086 ^ 522534 ^ 26437 ^ 580634773), Float.intBitsToFloat(31240 ^ 1014079 ^ 1038921 ^ -259560217 ^ '뇘' ^ 241676 ^ '윥' ^ 1310583538), new Color(29075 ^ -17777 ^ 21730 ^ -24609, 1904 ^ -25449 ^ 26918 ^ -3359, 1586 ^ -17137 ^ 26572 ^ -8999, 31191 ^ -30643 ^ 18086 ^ -18484), new Color(3738 ^ -28013 ^ 17910 ^ -9782, 30783 ^ -21923 ^ 18509 ^ -26085, 14148 ^ -12736 ^ 30376 ^ -28784, 15782 ^ -28032 ^ 1073 ^ -21529));
         0en.method_bGt(g, h + Float.intBitsToFloat(9787 ^ 242865 ^ 21286 ^ 830066936 ^ '铊' ^ 210126 ^ 3787 ^ 1894367131), Float.intBitsToFloat('쿆' ^ '늃' ^ 16637 ^ -803122176 ^ '늀' ^ '\udb41' ^ 17235 ^ -1831779542), Float.intBitsToFloat('ퟐ' ^ '\ufe6c' ^ 21116 ^ 451491547 ^ '觙' ^ 22850 ^ '鵈' ^ 627638472), new Color(26610 ^ -10545 ^ 5051 ^ -23885, 9942 ^ -2735 ^ 22039 ^ -31324, 11830 ^ -11050 ^ 22072 ^ -21276, 26468 ^ -32080 ^ 18138 ^ -23554));
         method_blw().method_bED(c.method_bCw(), g + Float.intBitsToFloat(261040 ^ 259339 ^ 19389 ^ -1581138724 ^ 257258 ^ 258967 ^ 16983 ^ -524180240), h + Float.intBitsToFloat('臁' ^ '\ue392' ^ 24735 ^ -476223211 ^ 4937 ^ 78376 ^ '됧' ^ -1552089441), (c.method_bCv() ? f.method_bDr() : new Color(22371 ^ -11459 ^ 11081 ^ -20502, 30775 ^ -27933 ^ 4566 ^ -1025, 369 ^ -9532 ^ 30041 ^ -20975)).getRGB());
         method_blx().method_bED(method_bjJ("Ŋ"), g + Float.intBitsToFloat('ꩈ' ^ '\ue674' ^ 7204 ^ 1163079714 ^ '뉤' ^ '\ue106' ^ 24903 ^ 126046751), h + Float.intBitsToFloat(19201 ^ 243079 ^ 5958 ^ 1604438251 ^ 31420 ^ 232653 ^ 30019 ^ 513909273), (new Color(19348 ^ -19163 ^ 28166 ^ -28433, 9177 ^ -14687 ^ 19098 ^ -20554, 3250 ^ -22362 ^ 32679 ^ -9279)).getRGB());
         String a = 0eg.method_bFs(method_bly(), c.method_bCz().method_bDe(), 1834 ^ -16939 ^ 21632 ^ -4541);
         String b = 0eg.method_bFs(method_blz(), c.method_bCz().method_bDf(), 2373 ^ -14565 ^ 15901 ^ -3969);
         0dY var10000 = method_blA();
         String var10001 = method_bjJ("ŞŎşńŝřńŃŊăńŃŋłăŞřŌřŘŞ");
         Object[] var10002 = new Object[16823 ^ -31273 ^ 20705 ^ -27520];
         var10002[23551 ^ -3893 ^ 17997 ^ -4743] = c.method_bCz().method_bDg().getName();
         var10000.method_bED(0cT.method_byW(var10001, var10002), g + Float.intBitsToFloat(6463 ^ 234772 ^ 18832 ^ 899531987 ^ 9413 ^ 254424 ^ 6619 ^ 1969085870), h + Float.intBitsToFloat(5331 ^ 226954 ^ 258716 ^ 469324970 ^ 12924 ^ 239093 ^ 249587 ^ 1515822357) + Float.intBitsToFloat('觶' ^ '钚' ^ 30291 ^ 1718419517 ^ '芈' ^ '\ue1ec' ^ 9802 ^ 653078060), c.method_bCz().method_bDg().getColor().getRGB());
         var10000 = method_blB();
         var10001 = method_bjJ("ŞŎşńŝřńŃŊăńŃŋłăŃŌŀň");
         var10002 = new Object[14761 ^ -29124 ^ 11152 ^ -25596];
         var10002[10035 ^ -25020 ^ 32331 ^ -14532] = a;
         var10000.method_bED(0cT.method_byW(var10001, var10002), g + Float.intBitsToFloat(105451 ^ '韑' ^ 129041 ^ -1929755385 ^ 110885 ^ '覥' ^ 127826 ^ -868582658), h + Float.intBitsToFloat('鐟' ^ 115234 ^ '벶' ^ -1616184863 ^ 29494 ^ 108120 ^ 8470 ^ -569710830) + Float.intBitsToFloat(25765 ^ 22321 ^ 7981 ^ 862721845 ^ '踤' ^ 478290 ^ 4923 ^ 1944840897) + Float.intBitsToFloat(21258 ^ 16773601 ^ 5314 ^ 478131660 ^ 27994 ^ 4170693 ^ 1595 ^ 1570753345), (new Color(26227 ^ -9545 ^ 14874 ^ -31197, 8187 ^ -5623 ^ 19006 ^ -16591, 11261 ^ -10981 ^ 8699 ^ -8224)).getRGB());
         var10000 = method_blC();
         var10001 = method_bjJ("ŞŎşńŝřńŃŊăńŃŋłăŌŘřŅłş");
         var10002 = new Object[112 ^ -20886 ^ 11091 ^ -31416];
         var10002[32195 ^ -8634 ^ 8798 ^ -32293] = b;
         var10000.method_bED(0cT.method_byW(var10001, var10002), g + Float.intBitsToFloat('뢢' ^ 95522 ^ 23102 ^ -1571883885 ^ '\uf459' ^ '葀' ^ 7742 ^ -493941494), h + Float.intBitsToFloat(119308 ^ 78576 ^ 25762 ^ 2073325359 ^ 120698 ^ 115870 ^ 25328 ^ 976525925) + Float.intBitsToFloat(113141 ^ 100783 ^ 4914 ^ 1722325631 ^ 28226 ^ 87047 ^ 118767 ^ 640175293) + Float.intBitsToFloat('ꃕ' ^ 30221 ^ '\uf2d8' ^ -449703461 ^ '聢' ^ 26974 ^ '\uf590' ^ -1533925001), (new Color(6325 ^ -19571 ^ 29714 ^ -8233, 983 ^ -11367 ^ 8663 ^ -3740, 19356 ^ -17700 ^ 3868 ^ -351)).getRGB());
         h += Float.intBitsToFloat(125085 ^ 103901 ^ 21030 ^ 493670142 ^ 119467 ^ 123179 ^ 785 ^ 1608962313);
         ++i;
         if (i > method_blD().method_bwh().size() / (4551 ^ -1618 ^ 20643 ^ -18232)) {
            i = 32357 ^ -9583 ^ 31996 ^ -10232;
            g = method_blE(this) + Float.intBitsToFloat('졵' ^ 1009882 ^ 6782 ^ -1656227070 ^ 'ﴜ' ^ 499919 ^ 'ﭛ' ^ -537139365) + Float.intBitsToFloat(10754 ^ 496339 ^ 509040 ^ -581192210 ^ 119346 ^ 126296 ^ 24023 ^ -1638093838);
            h = method_blF(this) + Float.intBitsToFloat(23259 ^ '램' ^ 3472 ^ 469656310 ^ 68 ^ '뽒' ^ 32652 ^ 1519272591) + method_blG(this);
         }
      }

   }

   private static float method_bjQ(0co var0) {
      return var0.field_h;
   }

   private static float method_bmx(0co var0) {
      return var0.field_j;
   }

   private static 0dY method_blz() {
      return 0dZ.field_a;
   }

   private static 0bz method_bmv() {
      return client;
   }

   private static float method_bkz(0co var0) {
      return var0.field_d;
   }

   private static float method_bmN(0co var0) {
      return var0.field_j;
   }

   private static nC method_bmS(0co var0) {
      return var0.mc;
   }

   private static float method_blU(0co var0) {
      return var0.field_b;
   }

   private static float method_bkf(0co var0) {
      return var0.field_j;
   }

   private static void method_bkA(0co var0, float var1) {
      var0.field_d = var1;
   }

   private static float method_bkd(0co var0) {
      return var0.field_j;
   }

   private static void method_blg(0cu var0, boolean var1) {
      var0.field_d = var1;
   }

   private static float method_bkb(0co var0) {
      return var0.field_j;
   }

   private static float method_blE(0co var0) {
      return var0.field_b;
   }

   private static 0cG method_bmm() {
      return botManager;
   }

   private static float method_bmr(0co var0) {
      return var0.field_j;
   }

   private static float method_bkk(0co var0) {
      return var0.field_j;
   }

   private static void method_bky(0co var0, boolean var1) {
      var0.field_a = var1;
   }

   private static float method_blZ(0co var0) {
      return var0.field_j;
   }

   private static 0dY method_bln() {
      return 0dZ.field_e;
   }

   private static float method_bmG(0co var0) {
      return var0.field_f;
   }

   private static float method_bjR(0co var0) {
      return var0.field_i;
   }

   private static float method_bmw(0co var0) {
      return var0.field_b;
   }

   private static 0dY method_blm() {
      return 0dZ.field_e;
   }

   private static 0dz method_bmD(0co var0) {
      return var0.field_g;
   }

   private static void method_bjS(0co var0, float var1) {
      var0.field_h = var1;
   }

   private static void method_bmY(0cu var0, boolean var1) {
      var0.field_d = var1;
   }

   private static float method_bjZ(0co var0) {
      return var0.field_j;
   }

   private static 0dY method_bkX() {
      return 0dZ.field_b;
   }

   public void keyTyped(char f, int g) throws IOException {
      mC h = new mC(method_bmS(this));
      if (g == (15370 ^ -21066 ^ 18794 ^ -10025)) {
         method_bmT(this, (float)h.getScaledHeight());
      } else {
         super.keyTyped(f, g);
         switch (method_bmU()[method_bmV(this).ordinal()]) {
            default:
               ArrayList<0dr> e = new ArrayList(method_bmW().method_Qs().method_bya(method_bmX(this)));
               Iterator var5 = e.iterator();

               label69:
               while(true) {
                  0dr d;
                  do {
                     if (!var5.hasNext()) {
                        return;
                     }

                     d = (0dr)var5.next();
                  } while(!d.method_bBf());

                  Iterator var7 = d.method_bBd().iterator();

                  while(true) {
                     while(true) {
                        0cu b;
                        do {
                           0cv c;
                           do {
                              do {
                                 if (!var7.hasNext()) {
                                    continue label69;
                                 }

                                 c = (0cv)var7.next();
                              } while(!c.method_bnY());
                           } while(!(c instanceof 0cu));

                           b = (0cu)c;
                        } while(!b.method_bnR());

                        Keyboard.enableRepeatEvents((boolean)(14083 ^ -30393 ^ 10325 ^ -27120));
                        if (Keyboard.isKeyDown(12988 ^ -8060 ^ 27052 ^ -17527) && Keyboard.isKeyDown(30598 ^ -26233 ^ 4573 ^ -13)) {
                           b.method_bnQ(b.method_bnP() + getClipboardString());
                        } else if (g != (1902 ^ -972 ^ 3949 ^ -3018) && g != (18349 ^ -7257 ^ 2728 ^ -20802)) {
                           if (g == (29129 ^ -20120 ^ 19982 ^ -29023)) {
                              String a = b.method_bnP();
                              if (a.length() > 0) {
                                 b.method_bnQ(a.substring(27032 ^ -7986 ^ 9118 ^ -21816, a.length() - (19798 ^ -24044 ^ 21946 ^ -17671)));
                                 method_bmY(b, (boolean)(4774 ^ -4942 ^ 14232 ^ -13940));
                              }
                           } else if (ChatAllowedCharacters.isAllowedCharacter(f)) {
                              b.method_bnQ(b.method_bnP() + f);
                           }
                        } else {
                           b.method_bnS((boolean)(12875 ^ -17063 ^ 26524 ^ -6002));
                        }
                     }
                  }
               }
            case 1:
            case 2:
         }
      }
   }

   private static float method_bmF(0co var0) {
      return var0.field_j;
   }

   private static 0bz method_blL() {
      return client;
   }

   private void method_bjE(int q, int r) {
      0dG s = method_bkP().method_Qt().method_byM();
      float t = method_bkQ(this) + Float.intBitsToFloat(4796 ^ 30985 ^ 6577 ^ -69193246 ^ '푋' ^ 475201 ^ 8207 ^ -1185679389);
      float u = method_bkR(this) + Float.intBitsToFloat('쫪' ^ '푍' ^ 21551 ^ 402668171 ^ '\ueaee' ^ '\uf37a' ^ 20351 ^ 1500522216) + method_bkS(this);
      ArrayList<0dr> v = new ArrayList(method_bkT().method_Qs().method_bya(method_bkU(this)));
      int w = 31862 ^ -775 ^ 8031 ^ -24624;
      Iterator var8 = v.iterator();

      while(var8.hasNext()) {
         0dr p = (0dr)var8.next();
         0eo.method_bIL(t, u, Float.intBitsToFloat(19804 ^ 220771 ^ 251281 ^ 1498510927 ^ 236524 ^ '\ua7cb' ^ 253350 ^ 463556960), (float)p.method_bAY(), Float.intBitsToFloat(523531 ^ 30900 ^ 512979 ^ -74214371 ^ 1651 ^ 498033 ^ 513884 ^ -1156347345), Float.intBitsToFloat(11487 ^ 997625 ^ 1029038 ^ -1699267952 ^ 9730 ^ 237971 ^ 5401 ^ 606967306), new Color(24655 ^ -28668 ^ 29755 ^ -31663, 6968 ^ -29100 ^ 25186 ^ -2258, 21920 ^ -22368 ^ 30648 ^ -30064, 3982 ^ -16784 ^ 20745 ^ -8185), new Color(26888 ^ -15431 ^ 17972 ^ -4944, 21415 ^ -15952 ^ 20614 ^ -15707, 23774 ^ -13353 ^ 13832 ^ -24259, 2199 ^ -27451 ^ 9407 ^ -18403));
         0en.method_bGt(t, u + Float.intBitsToFloat(8366936 ^ 8381571 ^ 7185 ^ -1556110868 ^ 2071674 ^ 2058173 ^ 32002 ^ -491817757), Float.intBitsToFloat('\udd3e' ^ 88485 ^ 4068 ^ -341907362 ^ '뤏' ^ '\ueb00' ^ 3470 ^ -1452327776), Float.intBitsToFloat(3281 ^ 255065 ^ 6912 ^ -516033956 ^ 20094 ^ 238214 ^ 25609 ^ -558003931), new Color(31387 ^ -15719 ^ 3111 ^ -19440, 27581 ^ -24671 ^ 20740 ^ -23252, 8939 ^ -15850 ^ 12041 ^ -12344, 9338 ^ -11498 ^ 26755 ^ -24801));
         method_bkV().method_bED(p.method_bAZ(), t + Float.intBitsToFloat(128397 ^ 114533 ^ 11848 ^ 1861922614 ^ 11328 ^ 88266 ^ 117969 ^ 804942797), u + Float.intBitsToFloat(3707 ^ 201140 ^ 237517 ^ 753496551 ^ 235487 ^ 233885 ^ 27939 ^ 1812568708), (p.method_bBh() ? s.method_bDr() : new Color(7541 ^ -21123 ^ 8185 ^ -20724, 7670 ^ -14362 ^ 26645 ^ -19720, 4695 ^ -32482 ^ 22251 ^ -15009)).getRGB());
         if (p.method_bBd().size() > 0) {
            method_bkW().method_bED(method_bjJ("Ň"), t + Float.intBitsToFloat(3680 ^ '솢' ^ '\ue453' ^ -250787156 ^ 2908 ^ '脿' ^ '\ued10' ^ -1277360562), u + Float.intBitsToFloat('\uda7c' ^ '\ue1a4' ^ 25354 ^ 432283688 ^ '텰' ^ '軎' ^ 2649 ^ 1489251613), (new Color(8763 ^ -1709 ^ 21351 ^ -30633, 7584 ^ -31429 ^ 31034 ^ -7691, 16228 ^ -10867 ^ 13347 ^ -8520)).getRGB());
         }

         float n = t;
         float o = u + Float.intBitsToFloat(496905 ^ 495297 ^ 15501 ^ 585357218 ^ 502238 ^ 493734 ^ 4048 ^ 1663819343);
         if (p.method_bBf()) {
            Iterator var12 = p.method_bBd().iterator();

            label130:
            while(true) {
               0cv m;
               do {
                  if (!var12.hasNext()) {
                     break label130;
                  }

                  m = (0cv)var12.next();
               } while(!m.method_bnY());

               if (m instanceof 0cp) {
                  0cp a = (0cp)m;
                  method_bkX().method_bED(a.method_boa(), n + Float.intBitsToFloat(127812 ^ 30076 ^ 125420 ^ 1842917004 ^ 11540 ^ 104599 ^ 130613 ^ 756583150), o + Float.intBitsToFloat(258839 ^ 26157 ^ 260197 ^ 1652395611 ^ 3818 ^ 223720 ^ 261053 ^ 587038651), (new Color(25896 ^ -12250 ^ 21573 ^ -7754, 17708 ^ -3885 ^ 17256 ^ -2454, 25722 ^ -32033 ^ 23121 ^ -17399)).getRGB());
                  0eo.method_bIF(n + Float.intBitsToFloat('\ud824' ^ 23979 ^ '쀣' ^ 1757873736 ^ '쓥' ^ 84682 ^ 11219 ^ 712566296), o, Float.intBitsToFloat(24808 ^ 499180 ^ 5796 ^ -989077216 ^ 16725 ^ 486771 ^ '쫦' ^ -2070156224), Float.intBitsToFloat('\ue309' ^ 4376 ^ 'ﯬ' ^ 1286562921 ^ '쳭' ^ 75703 ^ 31121 ^ 227475295), Float.intBitsToFloat('藾' ^ 21677 ^ '닁' ^ 829701283 ^ '쏟' ^ '齾' ^ 9455 ^ 1903437695), new Color(17717 ^ -26059 ^ 23920 ^ -32158, 31298 ^ -31572 ^ 15386 ^ -15645, 8575 ^ -24427 ^ 25688 ^ -6759, 4583 ^ -29362 ^ 30655 ^ -5143));
                  if (a.method_bna()) {
                     0eo.method_bIF(n + Float.intBitsToFloat(4910 ^ 117557 ^ 5828 ^ 1127793749 ^ 28580 ^ 27879 ^ 3394 ^ 37291147) + Float.intBitsToFloat(27493 ^ '胜' ^ 16589 ^ 375072946 ^ 12087 ^ '\udd88' ^ 32379 ^ 1424297730), o + Float.intBitsToFloat(15041 ^ 502204 ^ 29704 ^ -960238245 ^ 7186 ^ 506292 ^ 30717 ^ -112994187), Float.intBitsToFloat(9497 ^ 20041 ^ 9069 ^ -1956642134 ^ 13241 ^ 4176642 ^ 7386 ^ -899686666), Float.intBitsToFloat(2710 ^ 80465 ^ 'ꔋ' ^ 2098639922 ^ 24392 ^ 14851 ^ 20444 ^ 1008143209), Float.intBitsToFloat(19287 ^ 116607 ^ 29403 ^ 44232506 ^ '騁' ^ 129913 ^ '댲' ^ 1117963139), s.method_bDr());
                  } else {
                     0eo.method_bIF(n + Float.intBitsToFloat(106645 ^ 31066 ^ 116710 ^ -1575702827 ^ 31617 ^ 87013 ^ 126405 ^ -1651215011) + Float.intBitsToFloat(24907 ^ 238719 ^ 19662 ^ 1768267616 ^ '鰹' ^ 1034750 ^ '\ue196' ^ 735552203), o + Float.intBitsToFloat(20030 ^ 998962 ^ '\udba8' ^ 71807093 ^ 28452 ^ '蒙' ^ '\ue128' ^ 1002967364), Float.intBitsToFloat(19869 ^ 15051 ^ 28364 ^ 424837446 ^ 1013 ^ '뚏' ^ 17552 ^ 1481795894), Float.intBitsToFloat(28786 ^ 25901 ^ 15682 ^ -927591873 ^ 30887 ^ 227610 ^ '\uf438' ^ -1984573529), Float.intBitsToFloat('뷋' ^ 101207 ^ '넹' ^ -1116691132 ^ 19112 ^ 118110 ^ 1542 ^ -42949871), new Color(13421 ^ -28937 ^ 17266 ^ -1652, 8033 ^ -1158 ^ 20077 ^ -21998, 26528 ^ -11088 ^ 11781 ^ -25231));
                  }
               } else if (m instanceof 0cq) {
                  0cq b = (0cq)m;
                  method_bkY().method_bED(b.method_boa(), n + Float.intBitsToFloat('\uefd6' ^ '꭛' ^ 12057 ^ -782562271 ^ '\ued30' ^ '\udf12' ^ 1860 ^ -1852091693), o + Float.intBitsToFloat('\uf17f' ^ '胓' ^ 2002 ^ 1032331545 ^ '\ueb3a' ^ 1012216 ^ 15314 ^ 2097673847), (new Color(27025 ^ -5517 ^ 13780 ^ -18741, 20894 ^ -21060 ^ 10283 ^ -11020, 14692 ^ -22580 ^ 466 ^ -24697)).getRGB());
               } else {
                  int k;
                  if (m instanceof 0cr) {
                     0cr d = (0cr)m;
                     k = 15510 ^ -13011 ^ 30770 ^ -30327;
                     String[] var27 = d.method_bnl();
                     int var28 = var27.length;

                     for(int var18 = 28472 ^ -25777 ^ 19417 ^ -16466; var18 < var28; ++var18) {
                        String c = var27[var18];
                        method_bkZ().method_bED(c, n + Float.intBitsToFloat(1037552 ^ 1028471 ^ 7900 ^ 1176996631 ^ 883 ^ 1024525 ^ 1020281 ^ 115851339), o + Float.intBitsToFloat(2729 ^ '蝾' ^ 18735 ^ -127380815 ^ 29305 ^ '\ue0db' ^ 16995 ^ -1192737144) + (float)k, (new Color(20273 ^ -12034 ^ 29113 ^ -4469, 9819 ^ -27382 ^ 28762 ^ -15370, 6690 ^ -5750 ^ 28961 ^ -32140)).getRGB());
                        k += 10;
                     }
                  } else if (m instanceof 0ct) {
                     0ct g = (0ct)m;
                     method_bla().method_bED(g.method_boa(), n + Float.intBitsToFloat(128669 ^ 108850 ^ 14236 ^ 1459258192 ^ 107951 ^ 104531 ^ 26813 ^ 372948514), o + Float.intBitsToFloat(4738 ^ '뫝' ^ '\uf8fa' ^ 1697200478 ^ '쵣' ^ '컘' ^ 31931 ^ 631838459), (new Color(13126 ^ -8724 ^ 31415 ^ -27424, 4283 ^ -5925 ^ 29618 ^ -29905, 22728 ^ -32701 ^ 14243 ^ -4139)).getRGB());
                     method_blb().method_bED(String.valueOf(g.method_bnH()), n + Float.intBitsToFloat(7801 ^ 'ꒀ' ^ 13668 ^ 2041531045 ^ 1037861 ^ 1002471 ^ 10623 ^ 997683589) - (float)method_blc().method_bDO(String.valueOf(g.method_bnH())), o + Float.intBitsToFloat(1857 ^ '蛏' ^ '\uf080' ^ 994845202 ^ 12058 ^ 27121 ^ 4842 ^ 2076968733), (new Color(2871 ^ -13809 ^ 1467 ^ -15234, 21204 ^ -26479 ^ 540 ^ -14172, 788 ^ -17017 ^ 413 ^ -16397)).getRGB());
                     0eo.method_bII(n + Float.intBitsToFloat(23501 ^ 20451 ^ 13194 ^ -1278110949 ^ 27703 ^ 240510 ^ 257255 ^ -219046127), o + Float.intBitsToFloat(256286 ^ 242196 ^ 31089 ^ -1849565475 ^ 252144 ^ 234079 ^ 1529 ^ -801000464), Float.intBitsToFloat(9305 ^ 94784 ^ 129591 ^ 855533145 ^ 121246 ^ 25251 ^ 127534 ^ 1882620772), Float.intBitsToFloat('헬' ^ 'ﯾ' ^ 31389 ^ -1262342643 ^ '괨' ^ '괩' ^ 21358 ^ -1958598163), Float.intBitsToFloat(25149 ^ '뜫' ^ '\ue936' ^ -2069293543 ^ 'ꉴ' ^ '蛴' ^ 11257 ^ -995547840), s.method_bDr(), s.method_bDr(), s.method_bDs(), s.method_bDs());
                     0eo.method_bIH((float)((double)(n + Float.intBitsToFloat(22733 ^ 84740 ^ 125245 ^ -161724548 ^ 28422 ^ 22225 ^ 10820 ^ -1216565733)) + 0eb.map((double)g.method_bnH(), (double)g.method_bnI(), (double)g.method_bnJ(), Double.longBitsToDouble(-4344299691370794185L ^ -4344299691370794185L), Double.longBitsToDouble(-1114819627562146650L ^ -5702017323015707482L))), o + Float.intBitsToFloat('\ue488' ^ '촎' ^ 9649 ^ -467971708 ^ '큤' ^ 32371 ^ '\uece3' ^ -1516298425), Float.intBitsToFloat(16609 ^ 2056220 ^ 2089393 ^ -1548786127 ^ 3622 ^ '頹' ^ 29088 ^ -479248190), Float.intBitsToFloat(18539 ^ 21918 ^ 13340 ^ 2106294537 ^ 3218 ^ 16239 ^ 4375 ^ 1137133255), new Color(25386 ^ -17418 ^ 24804 ^ -18252, 3139 ^ -28432 ^ 25801 ^ -1802, 18579 ^ -30841 ^ 5315 ^ -9381), new Color(27505 ^ -25887 ^ 22321 ^ -22985, 20625 ^ -512 ^ 4293 ^ -16702, 7429 ^ -14262 ^ 5732 ^ -15427));
                     if (Mouse.isButtonDown(14450 ^ -21865 ^ 20295 ^ -8798) && this.method_bjI(q, r, (double)(n + Float.intBitsToFloat('ﲶ' ^ 101910 ^ '\ue202' ^ 237040735 ^ '蜃' ^ 95835 ^ 27571 ^ 1325465110)), (double)(o + Float.intBitsToFloat(100668 ^ '쏇' ^ 116954 ^ 1310734972 ^ 32461 ^ '갂' ^ 14735 ^ 251679517)), Double.longBitsToDouble(1251277579877014358L ^ 5837912325377153878L), Double.longBitsToDouble(5797006670146006012L ^ 1178565252277562364L))) {
                        float f = (float)q - (n + Float.intBitsToFloat(117316 ^ '녎' ^ 128951 ^ -1633394860 ^ 117147 ^ 125334 ^ 8666 ^ -544943554));
                        if (f >= Float.intBitsToFloat(254880 ^ 232694 ^ 22011 ^ 946455084 ^ 262049 ^ 232061 ^ 18665 ^ 946455988) && f <= Float.intBitsToFloat(20749 ^ 117414 ^ 25678 ^ -1310919539 ^ 16625 ^ 116866 ^ 8974 ^ -216752107)) {
                           g.method_bnG((float)((int)0eb.map((double)f, Double.longBitsToDouble(-3262953026040908469L ^ -3262953026040908469L), Double.longBitsToDouble(6901429444345989514L ^ 2278765901826886026L), (double)g.method_bnI(), (double)g.method_bnJ())));
                        }
                     }
                  } else if (m instanceof 0cu) {
                     0cu h = (0cu)m;
                     method_bld().method_bED(h.method_boa(), n + Float.intBitsToFloat(20833 ^ '\ue286' ^ 28990 ^ 1087589707 ^ 493361 ^ '谱' ^ 511035 ^ 1263785), o + Float.intBitsToFloat(245478 ^ 260170 ^ 28909 ^ -1686776539 ^ 246843 ^ 248938 ^ 29511 ^ -604661646), (new Color(15745 ^ -21857 ^ 17043 ^ -10896, 25713 ^ -19201 ^ 22597 ^ -30666, 10949 ^ -5149 ^ 13033 ^ -3278)).getRGB());
                     if (method_ble(h).hasReached(28273 ^ -10468 ^ 16579 ^ -1394)) {
                        method_blg(h, (boolean)(!method_blf(h) ? 25564 ^ -24382 ^ 6780 ^ -9885 : 12832 ^ -21851 ^ 12099 ^ -18490));
                     }

                     String i = 0eg.method_bFs(method_blh(), h.method_bnP(), 17868 ^ -16875 ^ 14838 ^ -15845);
                     0eo.method_bIL(n + Float.intBitsToFloat(26999 ^ '躗' ^ 3109 ^ 593643932 ^ 112150 ^ 85150 ^ 13823 ^ 1631481134), o, Float.intBitsToFloat(3902 ^ '閹' ^ '찈' ^ 546309477 ^ '\uf227' ^ 18871 ^ '증' ^ 1658855911), Float.intBitsToFloat(2111 ^ 1021964 ^ 1034877 ^ -533222727 ^ 1020764 ^ 1038251 ^ 23160 ^ -1585975688), Float.intBitsToFloat('ﻙ' ^ 85148 ^ 888 ^ -230887397 ^ 14797 ^ 99875 ^ 9203 ^ -1304634053), Float.intBitsToFloat(28925 ^ 117105 ^ 7894 ^ 675723141 ^ 18064 ^ '鐃' ^ 20929 ^ -1763833321), new Color(19858 ^ -9879 ^ 16053 ^ -21927, 8266 ^ -10070 ^ 8823 ^ -9599, 6653 ^ -3864 ^ 72 ^ -5821, 12 ^ -23859 ^ 20467 ^ -4670), new Color(22200 ^ -27944 ^ 24043 ^ -26178, 9379 ^ -14409 ^ 3152 ^ -4240, 21911 ^ -20492 ^ 30023 ^ -28904, 17547 ^ -9156 ^ 10233 ^ -16450));
                     method_bli().method_bED(i + (method_blj(h) && h.method_bnR() ? method_bjJ("Ų") : method_bjJ("")), n + Float.intBitsToFloat(13052 ^ 113031 ^ 4984 ^ -358180783 ^ 2511 ^ 103856 ^ 23437 ^ -1462319200), o + Float.intBitsToFloat('\ud969' ^ '꿡' ^ 20469 ^ -560445941 ^ '\ud95a' ^ '\ue3a4' ^ 24595 ^ -1640487525), (h.method_bnR() ? s.method_bDr() : new Color(1042 ^ -19002 ^ 21698 ^ -6677, 8131 ^ -15345 ^ 24502 ^ -31609, 22486 ^ -26318 ^ 4432 ^ -8375)).getRGB());
                  } else if (m instanceof 0cs) {
                     0cs l = (0cs)m;
                     method_blk().method_bED(l.method_boa(), n + Float.intBitsToFloat(26218 ^ 114106 ^ 13439 ^ 661323435 ^ 27380 ^ '蹌' ^ '\uf032' ^ 1739261326), o + Float.intBitsToFloat(8574 ^ 89647 ^ 110621 ^ -176267664 ^ 30275 ^ '\ue34a' ^ 31623 ^ -1241612366), (new Color(22164 ^ -31078 ^ 10747 ^ -1784, 6282 ^ -28069 ^ 7456 ^ -26868, 7584 ^ -26179 ^ 6956 ^ -24628)).getRGB());
                     0eo.method_bIL(n + Float.intBitsToFloat('\ue223' ^ 229892 ^ '\ud965' ^ -1267601032 ^ '\ueac6' ^ 221173 ^ 13852 ^ -164771051), o, Float.intBitsToFloat('\ue3d4' ^ 104576 ^ '휼' ^ -1712524635 ^ '\ue3ce' ^ 98360 ^ '\udb65' ^ -610474402), (float)m.method_bob(), Float.intBitsToFloat(2129 ^ 10014 ^ 6378 ^ 1021121030 ^ 21402 ^ 130901 ^ 13063 ^ 2094840427), Float.intBitsToFloat(32247 ^ '홢' ^ 30204 ^ 225803632 ^ 23838 ^ 489544 ^ '\uf030' ^ -1276383259), new Color(21384 ^ -20670 ^ 25248 ^ -24963, 15426 ^ -13383 ^ 32363 ^ -30330, 10522 ^ -19563 ^ 380 ^ -25619, 12495 ^ -14782 ^ 9486 ^ -11405), new Color(28932 ^ -17104 ^ 24425 ^ -27800, 20567 ^ -20727 ^ 21835 ^ -21983, 18946 ^ -12845 ^ 6033 ^ -28548, 4078 ^ -1487 ^ 22802 ^ -21443));
                     method_bll().method_bED(this.method_bjH(l.method_bnq()), n + Float.intBitsToFloat(13646 ^ 3868 ^ 13250 ^ 1962902321 ^ 2065243 ^ '돺' ^ 2094087 ^ 915361287), o + Float.intBitsToFloat(7677 ^ 8346734 ^ '컱' ^ -834671472 ^ '쪹' ^ 4148682 ^ 346 ^ -1897929253), (new Color(13914 ^ -25398 ^ 17916 ^ -4207, 9445 ^ -1074 ^ 2855 ^ -11023, 14084 ^ -3240 ^ 13594 ^ -3653)).getRGB());
                     method_blm().method_bED(l.method_bnt() ? method_bjJ("ś") : method_bjJ("ē"), n + Float.intBitsToFloat(125638 ^ 111960 ^ 10913 ^ -965174034 ^ 119437 ^ 99969 ^ 27206 ^ -2069833829), o + Float.intBitsToFloat(18135 ^ 128212 ^ 17019 ^ -734338577 ^ 16331 ^ 85411 ^ 'ꢢ' ^ -1801794723), (new Color(29397 ^ -26380 ^ 26375 ^ -29221, 3340 ^ -26033 ^ 7064 ^ -29658, 20698 ^ -17010 ^ 10621 ^ -15148)).getRGB());
                     if (l.method_bnt()) {
                        k = 10084 ^ -21199 ^ 5311 ^ -24858;

                        for(Iterator var16 = l.method_bnv().iterator(); var16.hasNext(); k += 8) {
                           String j = (String)var16.next();
                           method_bln().method_bED((l.method_bnq().equals(j) ? method_bjJ("Āč") : method_bjJ("")) + this.method_bjH(j), n + Float.intBitsToFloat(27302 ^ 103938 ^ 24500 ^ 211989552 ^ 17181 ^ 27828 ^ 14848 ^ 1322451593), o + Float.intBitsToFloat(23043 ^ '蛺' ^ 26059 ^ 1703427500 ^ 17958 ^ '궸' ^ 1798 ^ 623405062) + (float)k, (l.method_bnq().equals(j) ? s.method_bDr() : new Color(29175 ^ -12897 ^ 8569 ^ -25108, 3821 ^ -22701 ^ 2522 ^ -24423, 26282 ^ -19475 ^ 9673 ^ -3981)).getRGB());
                        }
                     }
                  }
               }

               o += (float)(m.method_bob() + (31407 ^ -14843 ^ 1883 ^ -17420));
            }
         }

         u += (float)(p.method_bAY() + (17952 ^ -14501 ^ 3896 ^ -29107));
         ++w;
         if (w > v.size() / (5995 ^ -10284 ^ 17786 ^ -31289)) {
            w = 18702 ^ -11279 ^ 12410 ^ -21883;
            t = method_blo(this) + Float.intBitsToFloat('\uec83' ^ 'ꋦ' ^ 10633 ^ 1465259097 ^ '밑' ^ '茛' ^ 27055 ^ 367142160) + Float.intBitsToFloat(17357 ^ 1000945 ^ 1036619 ^ 1202144058 ^ 19083 ^ 70520 ^ 121850 ^ 77600324);
            u = method_blp(this) + Float.intBitsToFloat(18516 ^ 118507 ^ 9865 ^ 859747853 ^ 31785 ^ 112660 ^ 28757 ^ 1917755987) + method_blq(this);
         }
      }

   }

   private static float method_bmo(0co var0) {
      return var0.field_j;
   }

   public void initGui() {
      mC a = new mC(method_bjK(this));
      method_bjL(this, (float)a.getScaledWidth() / Float.intBitsToFloat(22782 ^ 469071 ^ '콼' ^ 1764993238 ^ 8433 ^ 504134 ^ 19438 ^ 691260738) - Float.intBitsToFloat(249292 ^ 259830 ^ 536 ^ 1304019259 ^ 106596 ^ 103868 ^ 208 ^ 243322129));
      method_bjM(this, (float)a.getScaledHeight() / Float.intBitsToFloat(29657 ^ '\uee78' ^ 11316 ^ -968872179 ^ 28209 ^ 79295 ^ 129307 ^ -2042610675) - Float.intBitsToFloat(18811 ^ '\ue53c' ^ 6738 ^ -2016795623 ^ 30734 ^ 29266 ^ 7978 ^ -993130630));
      method_bjN(this, (float)a.getScaledHeight());
      method_bjO(this, Float.intBitsToFloat(130915 ^ 98809 ^ 13637 ^ -2124765273 ^ 126422 ^ 122258 ^ 21915 ^ -2124772953));
   }

   public void onGuiClosed() {
   }

   private static 0bz method_bkP() {
      return client;
   }

   private static float method_bmO(0co var0) {
      return var0.field_d;
   }

   private static float method_bjY(0co var0) {
      return var0.field_b;
   }

   private static float method_bmz(0co var0) {
      return var0.field_b;
   }

   private static float method_blo(0co var0) {
      return var0.field_b;
   }

   private static float method_bmj(0co var0) {
      return var0.field_j;
   }

   private void method_bjC() {
      if (method_bkz(this) >= Float.intBitsToFloat(124733 ^ 'ꐦ' ^ 116829 ^ 1752325094 ^ 11038 ^ 105888 ^ 109501 ^ 1752354211)) {
         method_bkA(this, Float.intBitsToFloat(12694 ^ '콕' ^ 11013 ^ -226599423 ^ 8497 ^ '鲡' ^ 30241 ^ -226606986));
      }

      if (method_bkB(this) < Float.intBitsToFloat(127533 ^ 84924 ^ 10262 ^ 1018329907 ^ '싮' ^ '\udcdb' ^ 25842 ^ -133623693)) {
         method_bkC(this, Float.intBitsToFloat(30066 ^ 517212 ^ 15062 ^ -236210086 ^ 17658 ^ 'ꬌ' ^ 19986 ^ 894906950));
      }

      method_bkF(this, 0ea.method_bFe(method_bkD(this), method_bkE(this), Float.intBitsToFloat('콑' ^ 14483 ^ '틈' ^ 1440827239 ^ 6028 ^ 77284 ^ '낟' ^ 367061146)));
   }

   private static float method_bkN(0co var0) {
      return var0.field_j;
   }

   private static 0dz method_bkJ(0co var0) {
      return var0.field_g;
   }

   private static float method_bjV(0co var0) {
      return var0.field_i;
   }

   private static 0ei method_ble(0cu var0) {
      return var0.field_c;
   }

   public boolean doesGuiPauseGame() {
      return (boolean)(27309 ^ -19848 ^ 5408 ^ -12811);
   }

   public void mouseReleased(int a, int b, int c) {
   }

   private static 0dY method_bll() {
      return 0dZ.field_e;
   }

   private static 0dY method_bkl() {
      return 0dZ.field_a;
   }

   private static float method_blX(0co var0) {
      return var0.field_b;
   }

   private String method_bjH(String a) {
      return a.length() > (32121 ^ -15077 ^ 21867 ^ -4858) ? a.substring(31869 ^ -7006 ^ 30113 ^ -4738, 14641 ^ -4728 ^ 25657 ^ -20337) + method_bjJ("ăă") : a;
   }

   private static float method_bkH(0co var0) {
      return var0.field_b;
   }

   private static float method_bmp(0co var0) {
      return var0.field_f;
   }

   private static boolean method_bkt(0co var0) {
      return var0.field_a;
   }

   private static void method_bmP(0co var0, float var1) {
      var0.field_d = var1;
   }

   private static float method_bmb(0co var0) {
      return var0.field_b;
   }

   private static float method_blS(0co var0) {
      return var0.field_b;
   }

   private static float method_blJ(0co var0) {
      return var0.field_j;
   }

   private static void method_bkC(0co var0, float var1) {
      var0.field_d = var1;
   }

   private static float method_bmc(0co var0) {
      return var0.field_j;
   }

   private static float method_blQ(0co var0) {
      return var0.field_j;
   }

   private static float method_bkB(0co var0) {
      return var0.field_d;
   }

   private static float method_bkj(0co var0) {
      return var0.field_b;
   }

   private static 0bz method_bmt() {
      return client;
   }

   private static float method_blI(0co var0) {
      return var0.field_b;
   }

   private static float method_bkQ(0co var0) {
      return var0.field_b;
   }

   private static float method_bkE(0co var0) {
      return var0.field_d;
   }

   private static 0dz method_bmd(0co var0) {
      return var0.field_g;
   }

   private static 0bz method_bkT() {
      return client;
   }

   private static 0dY method_blc() {
      return 0dZ.field_b;
   }

   private static 0dY method_bla() {
      return 0dZ.field_b;
   }

   private static void method_bmT(0co var0, float var1) {
      var0.field_i = var1;
   }

   private static int[] method_bmU() {
      return 0cn.field_a;
   }

   private static float method_bkn(0co var0) {
      return var0.field_j;
   }

   private static void method_blW(0co var0, boolean var1) {
      var0.field_a = var1;
   }
}
