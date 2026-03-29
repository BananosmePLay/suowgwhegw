package neo;

import java.awt.Color;
import java.util.HashMap;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Timer;
import net.minecraft.util.math.AxisAlignedBB;
import org.lwjgl.opengl.GL11;

public class 0en implements 0cD {
   public static final HashMap<String, Integer> field_a = new HashMap();
   private static String _DSC GG NEOWARECLIENT _;

   private static double method_bHz(AxisAlignedBB var0) {
      return var0.maxY;
   }

   private static double method_bHq(AxisAlignedBB var0) {
      return var0.maxZ;
   }

   private static Timer method_bGS(nC var0) {
      return var0.timer;
   }

   private static double method_bIb(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static nC method_bGD() {
      return mc;
   }

   private static double method_bIi(AxisAlignedBB var0) {
      return var0.maxZ;
   }

   private static double method_bHS(AxisAlignedBB var0) {
      return var0.maxY;
   }

   private static double method_bHu(AxisAlignedBB var0) {
      return var0.minX;
   }

   public static void method_bGm(float a) {
      GL11.glDisable(7661 ^ -14161 ^ 24700 ^ -16642);
      GL11.glEnable(22088 ^ -59543 ^ '\udfa5' ^ -27290);
      GL11.glBlendFunc(6133 ^ -30238 ^ 10057 ^ -17828, 25494 ^ -25140 ^ 24010 ^ -24429);
      GL11.glDisable(19828 ^ 26943 ^ 11309 ^ 1415);
      GL11.glDisable(32545 ^ -22268 ^ 2677 ^ -10463);
      GL11.glDepthMask((boolean)(31026 ^ -7211 ^ 17100 ^ -10197));
      GL11.glEnable(14345 ^ -7023 ^ 26811 ^ -16537);
      GL11.glEnable(7100 ^ -1054 ^ 19096 ^ -24090);
      GL11.glHint('蠎' ^ -32396 ^ '쬉' ^ -12767, 19307 ^ -358 ^ 2126 ^ -21315);
      GL11.glHint(8750 ^ -23938 ^ 6290 ^ -27503, 22712 ^ -59616 ^ '썛' ^ -25151);
      GL11.glLineWidth(a);
   }

   private static ya method_bIs() {
      return ya.ONE;
   }

   private static zO method_bHN() {
      return zK.POSITION;
   }

   private static double method_bHM(AxisAlignedBB var0) {
      return var0.minZ;
   }

   private static double method_bGW(Ig var0) {
      return var0.posX;
   }

   private static double method_bGN() {
      return wC.renderPosY;
   }

   public static void method_bGy(double a, double b, double c, double d, int e, int f) {
      c += a;
      d += b;
      float g = (float)(e >> (23513 ^ -19909 ^ 16892 ^ -22522) & (11905 ^ -27632 ^ 7255 ^ -22983)) / Float.intBitsToFloat(6387 ^ 9866 ^ 6216 ^ -46837911 ^ 6234 ^ 23012 ^ 69 ^ -1102436189);
      float h = (float)(e >> (20957 ^ -22989 ^ 3541 ^ -1493) & (17746 ^ -22022 ^ 22559 ^ -19384)) / Float.intBitsToFloat(11127 ^ 241001 ^ 261296 ^ 1345693366 ^ 120549 ^ 125191 ^ 19570 ^ 323661704);
      float i = (float)(e >> (14790 ^ -24615 ^ 29062 ^ -10351) & (2753 ^ -17084 ^ 24334 ^ -6028)) / Float.intBitsToFloat('뽣' ^ 81144 ^ 7854 ^ -1203047259 ^ '\ue7b5' ^ '踩' ^ 8954 ^ -80469258);
      float j = (float)(e & (4535 ^ -1394 ^ 26101 ^ -29133)) / Float.intBitsToFloat('轀' ^ 239925 ^ '렮' ^ -108931885 ^ 11337 ^ 86537 ^ '\ue5ca' ^ -1157836542);
      float k = (float)(f >> (24919 ^ -14683 ^ 27856 ^ -13510) & (5193 ^ -24797 ^ 22354 ^ -9017)) / Float.intBitsToFloat(14480 ^ 17864 ^ 972 ^ 1811030043 ^ '숪' ^ '\ue63c' ^ 22663 ^ 680337950);
      float l = (float)(f >> (344 ^ -17394 ^ 21982 ^ -5992) & (3557 ^ -20922 ^ 6062 ^ -19214)) / Float.intBitsToFloat(241197 ^ 232305 ^ 1994 ^ 1935268999 ^ 114716 ^ 117815 ^ 10479 ^ 807852757);
      float m = (float)(f >> (15222 ^ -12053 ^ 18815 ^ -23830) & (9972 ^ -29026 ^ 636 ^ -21783)) / Float.intBitsToFloat('\ua635' ^ 7037 ^ '뇶' ^ 1579230328 ^ 17755 ^ 259511 ^ 20950 ^ 492685820);
      float n = (float)(f & (13389 ^ -5211 ^ 17618 ^ -25659)) / Float.intBitsToFloat('뗩' ^ 125181 ^ '뺤' ^ -2031041796 ^ 23346 ^ 232300 ^ 23907 ^ -980571023);
      yh.disableTexture2D();
      yh.enableBlend();
      yh.disableAlpha();
      yh.tryBlendFuncSeparate(method_bIA(), method_bIB(), method_bIC(), method_bID());
      yh.shadeModel(22161 ^ '靶' ^ '\udf2d' ^ 971);
      yN o = yN.getInstance();
      tN p = o.getBuffer();
      p.begin(14862 ^ -25813 ^ 3215 ^ -21075, method_bIE());
      p.pos(a, b, Double.longBitsToDouble(-1789621441858040828L ^ -1789621441858040828L)).color(h, i, j, g).endVertex();
      p.pos(a, d, Double.longBitsToDouble(-7448594604771892402L ^ -7448594604771892402L)).color(l, m, n, k).endVertex();
      p.pos(c, d, Double.longBitsToDouble(-8399493651802383706L ^ -8399493651802383706L)).color(l, m, n, k).endVertex();
      p.pos(c, b, Double.longBitsToDouble(6431970073898703809L ^ 6431970073898703809L)).color(h, i, j, g).endVertex();
      o.draw();
      yh.shadeModel(20011 ^ -43899 ^ '쇄' ^ -14742);
      yh.disableBlend();
      yh.enableAlpha();
      yh.enableTexture2D();
   }

   public static void method_bGl() {
      GL11.glEnable(21858 ^ -2754 ^ 12805 ^ -24648);
      GL11.glEnable(10044 ^ 29644 ^ 21767 ^ 2694);
      GL11.glDisable('苉' ^ -29959 ^ '섻' ^ -15639);
      GL11.glEnable(1135 ^ -4146 ^ 29675 ^ -27766);
      GL11.glDepthMask((boolean)(393 ^ -17105 ^ 26531 ^ -9468));
      GL11.glCullFace(27457 ^ -16887 ^ 3700 ^ -8391);
      GL11.glDisable(20672 ^ -45335 ^ '\udef5' ^ -13316);
      GL11.glHint(19596 ^ -59343 ^ '쭦' ^ -27767, 176 ^ -1790 ^ 12114 ^ -14368);
      GL11.glHint(9744 ^ -5906 ^ 29954 ^ -18513, '龩' ^ -32408 ^ '씫' ^ -13590);
   }

   public static void method_bGi() {
      method_bGw(Double.longBitsToDouble(-8499823585557479087L ^ -5333793047516020399L), Double.longBitsToDouble(-6939205008588187136L ^ -6898672611941852672L), Double.longBitsToDouble(5336949152960888456L ^ 8493972491747606152L), Double.longBitsToDouble(-8062110224874441659L ^ -5769778014542859195L));
   }

   private static double method_bHX(AxisAlignedBB var0) {
      return var0.maxX;
   }

   private static double method_bHP(AxisAlignedBB var0) {
      return var0.minY;
   }

   public static void method_bGg() {
      GL11.glDisable(12679 ^ 10276 ^ 7712 ^ 2962);
   }

   private static zO method_bHh() {
      return zK.POSITION;
   }

   private static xR method_bID() {
      return xR.ZERO;
   }

   private static double method_bHR(AxisAlignedBB var0) {
      return var0.minX;
   }

   private static xR method_bIt() {
      return xR.ZERO;
   }

   private static xR method_bIw() {
      return xR.ONE_MINUS_SRC_ALPHA;
   }

   private static double method_bHY(AxisAlignedBB var0) {
      return var0.maxY;
   }

   private static double method_bHW(AxisAlignedBB var0) {
      return var0.minZ;
   }

   private static double method_bHc(Ig var0) {
      return var0.posX;
   }

   private static double method_bIc(AxisAlignedBB var0) {
      return var0.maxZ;
   }

   public static void method_bGh(int a) {
      GL11.glBindTexture(27568 ^ -54636 ^ '\ued87' ^ -24254, a);
   }

   private static double method_bGB(Ig var0) {
      return var0.posX;
   }

   private static double method_bHg(Ig var0) {
      return var0.posZ;
   }

   private static HashMap method_bIn() {
      return field_a;
   }

   private static double method_bGA(Ig var0) {
      return var0.lastTickPosX;
   }

   private static Timer method_bGL(nC var0) {
      return var0.timer;
   }

   private static double method_bIl(AxisAlignedBB var0) {
      return var0.maxZ;
   }

   private static double method_bHE(AxisAlignedBB var0) {
      return var0.maxX;
   }

   private static double method_bHn(AxisAlignedBB var0) {
      return var0.minZ;
   }

   private static double method_bIh(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static double method_bHU(AxisAlignedBB var0) {
      return var0.maxX;
   }

   public static void method_bGu(double a, double b, double c, double d, int e, int f) {
      float g = (float)(e >> (257 ^ -15795 ^ 21758 ^ -26710) & (15415 ^ -19050 ^ 19544 ^ -15098)) / Float.intBitsToFloat(32239 ^ 233152 ^ 251934 ^ -391013047 ^ 98985 ^ 89254 ^ 9168 ^ -1412543577);
      float h = (float)(e >> (9252 ^ -3458 ^ 29915 ^ -23919) & (12716 ^ -24742 ^ 21651 ^ -1382)) / Float.intBitsToFloat(252185 ^ 252504 ^ 20747 ^ 1851895238 ^ 100064 ^ 107038 ^ 9125 ^ 757004503);
      float i = (float)(e >> (6925 ^ -15316 ^ 25200 ^ -17063) & (29629 ^ -7972 ^ 12965 ^ -24261)) / Float.intBitsToFloat(6786 ^ 110614 ^ 118735 ^ -1773236344 ^ 230174 ^ 253830 ^ 11126 ^ -718168771);
      float j = (float)(e & (27887 ^ -17504 ^ 6086 ^ -16266)) / Float.intBitsToFloat(32581 ^ 101304 ^ 13852 ^ -83825892 ^ 27757 ^ 11594 ^ 30766 ^ -1199697676);
      float k = (float)(f >> (12550 ^ -16371 ^ 24402 ^ -20927) & (31015 ^ -29424 ^ 16713 ^ -19071)) / Float.intBitsToFloat(15386 ^ 237840 ^ 6201 ^ -1550203663 ^ 13232 ^ 94911 ^ '\uf091' ^ -521867172);
      float l = (float)(f >> (27460 ^ -8333 ^ 18169 ^ -3362) & (13659 ^ -23868 ^ 25979 ^ -3557)) / Float.intBitsToFloat('诌' ^ 2056065 ^ 13157 ^ 319802644 ^ 7626 ^ 109575 ^ 24589 ^ 1349450748);
      float m = (float)(f >> (32608 ^ -25896 ^ 29231 ^ -26721) & (7790 ^ -29420 ^ 6226 ^ -29737)) / Float.intBitsToFloat('\ue210' ^ '뭩' ^ 5837 ^ -583528835 ^ 2595 ^ 93298 ^ 'ｏ' ^ -1639524137);
      float n = (float)(f & (26784 ^ -31893 ^ 3875 ^ -7145)) / Float.intBitsToFloat(22792 ^ 1045989 ^ 5888 ^ 890748405 ^ 25733 ^ 108615 ^ 11109 ^ 1986453951);
      yh.disableTexture2D();
      yh.enableBlend();
      yh.disableAlpha();
      yh.tryBlendFuncSeparate(method_bIq(), method_bIr(), method_bIs(), method_bIt());
      yh.shadeModel(19968 ^ -12944 ^ 29299 ^ -5118);
      yN o = yN.getInstance();
      tN p = o.getBuffer();
      p.begin(24539 ^ -13765 ^ 13514 ^ -24275, method_bIu());
      p.pos(c, b, Double.longBitsToDouble(-6385865214807241347L ^ -6385865214807241347L)).color(h, i, j, g).endVertex();
      p.pos(a, b, Double.longBitsToDouble(8225635677287139137L ^ 8225635677287139137L)).color(h, i, j, g).endVertex();
      p.pos(a, d, Double.longBitsToDouble(-4636957339155511697L ^ -4636957339155511697L)).color(l, m, n, k).endVertex();
      p.pos(c, d, Double.longBitsToDouble(2828889441837464008L ^ 2828889441837464008L)).color(l, m, n, k).endVertex();
      o.draw();
      yh.shadeModel(26180 ^ '雡' ^ '\ue716' ^ 2739);
      yh.disableBlend();
      yh.enableAlpha();
      yh.enableTexture2D();
   }

   private static double method_bHj(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static double method_bHJ(AxisAlignedBB var0) {
      return var0.maxZ;
   }

   private static float method_bGM(Timer var0) {
      return var0.renderPartialTicks;
   }

   public static void method_bGs(int a) {
      GL11.glColor4ub((byte)(a >> (14774 ^ -24334 ^ 13365 ^ -21151) & (8931 ^ -29220 ^ 29382 ^ -8954)), (byte)(a >> (28537 ^ -23897 ^ 18767 ^ -31591) & (1373 ^ -1311 ^ 17934 ^ -18099)), (byte)(a & (4602 ^ -28020 ^ 6084 ^ -27571)), (byte)(a >> (22284 ^ -23269 ^ 32295 ^ -29656) & (19446 ^ -31684 ^ 13373 ^ -1272)));
   }

   private static double method_bGQ(Ig var0) {
      return var0.lastTickPosZ;
   }

   private static double method_bGC(Ig var0) {
      return var0.lastTickPosX;
   }

   private static double method_bGU() {
      return wC.renderPosZ;
   }

   private static double method_bGX(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static double method_bHf(AxisAlignedBB var0) {
      return var0.maxZ;
   }

   private static zO method_bIu() {
      return zK.POSITION_COLOR;
   }

   private static double method_bHF(AxisAlignedBB var0) {
      return var0.maxY;
   }

   public static void method_bGv(float a, float b, double c, double d, int e, int f) {
      jI.drawGradientRect1(a, b, (double)a + c, (double)b + d, e, f);
   }

   private static double method_bGP(Ig var0) {
      return var0.posZ;
   }

   private static zO method_bIz() {
      return zK.POSITION_COLOR;
   }

   public static void method_bGr(float h, float i, float j, float k, float l, boolean m, Color n) {
      method_bGw(Double.longBitsToDouble(4188552364456924334L ^ 4188552364456924334L), Double.longBitsToDouble(1036228806129915014L ^ 1036228806129915014L), Double.longBitsToDouble(1699640471156099075L ^ 1699640471156099075L), Double.longBitsToDouble(1064661508908999011L ^ 1064661508908999011L));
      if (j > k) {
         float a = k;
         k = j;
         j = a;
      }

      yh.enableBlend();
      yh.disableTexture2D();
      yh.tryBlendFuncSeparate(19198 ^ -20825 ^ 15490 ^ -9255, 11878 ^ -23066 ^ 17561 ^ -13286, 7704 ^ -9106 ^ 19249 ^ -30394, 21418 ^ -6787 ^ 26171 ^ -12052);
      method_bGs(n.getRGB());
      GL11.glEnable(13337 ^ -17995 ^ 8817 ^ -23299);
      GL11.glLineWidth(Float.intBitsToFloat('됦' ^ 216251 ^ 25447 ^ -2002912003 ^ 3728 ^ '\uf579' ^ '묛' ^ -929159179));
      GL11.glBegin(11107 ^ -9672 ^ 9573 ^ -11203);

      float g;
      float e;
      float f;
      for(g = k; g >= j; g -= Float.intBitsToFloat(14353 ^ 14287 ^ 6706 ^ 1272287041 ^ 15534 ^ 206021 ^ 239916 ^ 190131178)) {
         e = (float)(Math.cos((double)g * Double.longBitsToDouble(5437233065461187917L ^ 828045853432653909L) / Double.longBitsToDouble(-6408534434286964899L ^ -1767997230746734755L)) * (double)l * Double.longBitsToDouble(6433166127450556582L ^ 7401440047335213222L));
         f = (float)(Math.sin((double)g * Double.longBitsToDouble(6391424043491671439L ^ 1782237665231507607L) / Double.longBitsToDouble(1250920103374102225L ^ 5853176710081683153L)) * (double)l * Double.longBitsToDouble(2098679168637838085L ^ 2508506734728553221L));
         GL11.glVertex2f(h + e, i + f);
      }

      GL11.glEnd();
      GL11.glDisable('蠻' ^ -949 ^ '阶' ^ -5786);
      GL11.glEnable(32378 ^ -3088 ^ 12270 ^ -22204);
      GL11.glBegin(m ? 27931 ^ -9174 ^ 24593 ^ -11994 : 15485 ^ -24649 ^ 2561 ^ -22072);

      for(g = k; g >= j; g -= Float.intBitsToFloat(9199 ^ 26543 ^ 2695 ^ -1636460772 ^ 12782 ^ 8654 ^ 7003 ^ -554315104)) {
         e = (float)Math.cos((double)g * Double.longBitsToDouble(-2071682691654015160L ^ -6685938437664077232L) / Double.longBitsToDouble(6062686466910480651L ^ 1460429860202899723L)) * l;
         f = (float)Math.sin((double)g * Double.longBitsToDouble(1117319477549715002L ^ 5731012900001014562L) / Double.longBitsToDouble(-5705445254716949569L ^ -1102062748102526017L)) * l;
         GL11.glVertex2f(h + e, i + f);
      }

      GL11.glEnd();
      GL11.glDisable(28812 ^ -25101 ^ 20042 ^ -22507);
      yh.enableTexture2D();
      yh.disableBlend();
   }

   public static void method_bGk(AxisAlignedBB a) {
      yN b = yN.getInstance();
      tN c = b.getBuffer();
      c.begin(8914 ^ -369 ^ 27590 ^ -18536, method_bHh());
      c.pos(method_bHi(a), method_bHj(a), method_bHk(a)).endVertex();
      c.pos(method_bHl(a), method_bHm(a), method_bHn(a)).endVertex();
      c.pos(method_bHo(a), method_bHp(a), method_bHq(a)).endVertex();
      c.pos(method_bHr(a), method_bHs(a), method_bHt(a)).endVertex();
      c.pos(method_bHu(a), method_bHv(a), method_bHw(a)).endVertex();
      b.draw();
      c.begin(3265 ^ -7513 ^ 3821 ^ -8056, method_bHx());
      c.pos(method_bHy(a), method_bHz(a), method_bHA(a)).endVertex();
      c.pos(method_bHB(a), method_bHC(a), method_bHD(a)).endVertex();
      c.pos(method_bHE(a), method_bHF(a), method_bHG(a)).endVertex();
      c.pos(method_bHH(a), method_bHI(a), method_bHJ(a)).endVertex();
      c.pos(method_bHK(a), method_bHL(a), method_bHM(a)).endVertex();
      b.draw();
      c.begin(1010 ^ -17734 ^ 18221 ^ -412, method_bHN());
      c.pos(method_bHO(a), method_bHP(a), method_bHQ(a)).endVertex();
      c.pos(method_bHR(a), method_bHS(a), method_bHT(a)).endVertex();
      c.pos(method_bHU(a), method_bHV(a), method_bHW(a)).endVertex();
      c.pos(method_bHX(a), method_bHY(a), method_bHZ(a)).endVertex();
      c.pos(method_bIa(a), method_bIb(a), method_bIc(a)).endVertex();
      c.pos(method_bId(a), method_bIe(a), method_bIf(a)).endVertex();
      c.pos(method_bIg(a), method_bIh(a), method_bIi(a)).endVertex();
      c.pos(method_bIj(a), method_bIk(a), method_bIl(a)).endVertex();
      b.draw();
   }

   private static double method_bHO(AxisAlignedBB var0) {
      return var0.minX;
   }

   private static double method_bHd(AxisAlignedBB var0) {
      return var0.maxY;
   }

   private static double method_bHe(Ig var0) {
      return var0.posY;
   }

   private static double method_bHp(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static double method_bIa(AxisAlignedBB var0) {
      return var0.maxX;
   }

   private static nC method_bGK() {
      return mc;
   }

   public static void method_bGn(int a, int b) {
      float c = (float)(a >> (12962 ^ -14653 ^ 7791 ^ -5602) & (26786 ^ -1428 ^ 6357 ^ -29980)) / Float.intBitsToFloat('\ueb6c' ^ '輪' ^ 28192 ^ 1298202881 ^ 'ꌦ' ^ 'ﴁ' ^ 30707 ^ 236868686);
      float d = (float)(a >> (31993 ^ -17011 ^ 1887 ^ -14813) & (11304 ^ -13782 ^ 23071 ^ -17182)) / Float.intBitsToFloat('\uea6b' ^ '\udcbb' ^ 11375 ^ 1985766641 ^ 'ꁜ' ^ '땠' ^ 26990 ^ 891486748);
      float e = (float)(a & (15407 ^ -30651 ^ 343 ^ -19006)) / Float.intBitsToFloat(516157 ^ '艠' ^ 511947 ^ 289628477 ^ 9532 ^ 12640 ^ 4259 ^ 1379715156);
      yh.color(c, d, e, (float)b / Float.intBitsToFloat(98561 ^ 124207 ^ 26879 ^ -365369914 ^ 1043610 ^ 1016677 ^ 19209 ^ -1454912031));
   }

   private static nC method_bGz() {
      return mc;
   }

   private static double method_bHC(AxisAlignedBB var0) {
      return var0.maxY;
   }

   private static xR method_bIr() {
      return xR.ONE_MINUS_SRC_ALPHA;
   }

   private static double method_bHs(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static double method_bHt(AxisAlignedBB var0) {
      return var0.maxZ;
   }

   private static double method_bHD(AxisAlignedBB var0) {
      return var0.minZ;
   }

   private static double method_bHT(AxisAlignedBB var0) {
      return var0.minZ;
   }

   private static double method_bIk(AxisAlignedBB var0) {
      return var0.maxY;
   }

   private static double method_bHw(AxisAlignedBB var0) {
      return var0.minZ;
   }

   private static double method_bHy(AxisAlignedBB var0) {
      return var0.minX;
   }

   private static double method_bHl(AxisAlignedBB var0) {
      return var0.maxX;
   }

   private static double method_bHK(AxisAlignedBB var0) {
      return var0.minX;
   }

   private static HashMap method_bIm() {
      return field_a;
   }

   private static zO method_bHx() {
      return zK.POSITION;
   }

   private static ya method_bIx() {
      return ya.ONE;
   }

   public static void method_bGw(double a, double b, double c, double d) {
      GL11.glColor4d(a, b, c, d);
   }

   public static void method_bGq(0cW a, int b, int c, int d, int e) {
      if (method_bIm().containsKey(a.getHash())) {
         yh.bindTexture((Integer)method_bIn().get(a.getHash()));
      } else {
         method_bIo().put(a.getHash(), zk.uploadTextureImageAllocate(zk.glGenTextures(), a.getCaptcha(), (boolean)(25354 ^ -32288 ^ 20207 ^ -21499), (boolean)(32244 ^ -29420 ^ 27975 ^ -25177)));
      }

      yh.pushMatrix();
      yh.alphaFunc(3962 ^ -25910 ^ 10342 ^ -16430, Float.intBitsToFloat(11646 ^ 18960 ^ 7506 ^ -874700480 ^ 17510 ^ '議' ^ 26904 ^ -134337928));
      GL11.glEnable(19945 ^ -7946 ^ 5645 ^ -18701);
      GL11.glDisable(25489 ^ -21375 ^ 6182 ^ -9102);
      GL11.glEnable(1973 ^ -28489 ^ 26423 ^ -1035);
      yh.enableBlend();
      GL11.glBegin(10092 ^ -25536 ^ 21151 ^ -5708);
      GL11.glTexCoord2f(Float.intBitsToFloat(509826 ^ 474143 ^ 21481 ^ 511407439 ^ 517024 ^ 497648 ^ 49 ^ 511420762), Float.intBitsToFloat(2079151 ^ 'Ꞓ' ^ 2086103 ^ 1102015783 ^ 27180 ^ 2070831 ^ 2082658 ^ 1102025644));
      GL11.glVertex2f((float)b, (float)c);
      GL11.glTexCoord2f(Float.intBitsToFloat(26207 ^ 87414 ^ 127747 ^ 2078176471 ^ 19531 ^ 100065 ^ 117885 ^ 2078192170), Float.intBitsToFloat(18246 ^ 1040925 ^ 20130 ^ 878173108 ^ '镓' ^ 1008929 ^ 8234 ^ 198696981));
      GL11.glVertex2f((float)b, (float)(c + e));
      GL11.glTexCoord2f(Float.intBitsToFloat(523556 ^ '넯' ^ 516165 ^ -225220188 ^ 519491 ^ 'ꥁ' ^ 512155 ^ -854371981), Float.intBitsToFloat('虁' ^ 6056 ^ '躡' ^ 316238638 ^ '넷' ^ 81433 ^ 1675 ^ 760806851));
      GL11.glVertex2f((float)(b + d), (float)(c + e));
      GL11.glTexCoord2f(Float.intBitsToFloat(25790 ^ '脘' ^ '\uefc2' ^ 1863856936 ^ 23721 ^ '茞' ^ 'ﰍ' ^ 1352145654), Float.intBitsToFloat(1996 ^ 'ꪍ' ^ 27862 ^ 1023433053 ^ 17477 ^ 83577 ^ 116754 ^ 1023431396));
      GL11.glVertex2f((float)(b + d), (float)c);
      GL11.glEnd();
      yh.enableTexture2D();
      yh.disableBlend();
      yh.resetColor();
      GL11.glEnable(4561 ^ -29085 ^ 12484 ^ -23502);
      yh.popMatrix();
   }

   public static void method_bGo(float e, float f, float g, float h, float i, int j, int k) {
      yh.color(Float.intBitsToFloat(2388 ^ '\uf27d' ^ 24715 ^ -1260054183 ^ 3905 ^ '荎' ^ 11645 ^ -1260051575), Float.intBitsToFloat(16411 ^ 78755 ^ 115123 ^ -2018625699 ^ 8374 ^ '肿' ^ 20481 ^ -2018609826), Float.intBitsToFloat(117576 ^ '蛸' ^ 115638 ^ 1490188666 ^ 111714 ^ 99839 ^ 12841 ^ 1490220232), Float.intBitsToFloat(260639 ^ '邙' ^ 253929 ^ 1027700803 ^ 250683 ^ '聛' ^ 254932 ^ 1027702168));
      float d;
      if (g > h) {
         d = h;
         h = g;
         g = d;
      }

      yh.enableBlend();
      yh.disableTexture2D();
      method_bGm((float)k);
      yh.tryBlendFuncSeparate(5014 ^ -20324 ^ 18022 ^ -6546, 6398 ^ -7067 ^ 17505 ^ -17415, 28317 ^ -23396 ^ 4849 ^ -9999, 22682 ^ -30072 ^ 12768 ^ -7182);
      GL11.glBegin(8765 ^ -1361 ^ 11598 ^ -2593);

      for(d = h; d >= g; d -= Float.intBitsToFloat(253722 ^ 230398 ^ 18157 ^ -287707809 ^ 240611 ^ 246682 ^ 17914 ^ -1369842987)) {
         method_bGn(j, 12198 ^ -10598 ^ 28454 ^ -26907);
         float b = (float)(Math.cos((double)d * Double.longBitsToDouble(8374836865471091009L ^ 3760580707806887001L) / Double.longBitsToDouble(3550947654487563572L ^ 8151796886311591220L)) * (double)i * Double.longBitsToDouble(3168730131823488503L ^ 1443851474540588535L));
         float c = (float)(Math.sin((double)d * Double.longBitsToDouble(-6240752128680602466L ^ -1626568042714564218L) / Double.longBitsToDouble(-237670052966102558L ^ -4839926659673683486L)) * (double)i * Double.longBitsToDouble(-4223750347576444961L ^ -391187064684152865L));
         GL11.glVertex2f(e + b, f + c);
      }

      GL11.glEnd();
      method_bGl();
      yh.enableTexture2D();
      yh.disableBlend();
   }

   private static Timer method_bGE(nC var0) {
      return var0.timer;
   }

   private static zO method_bIE() {
      return zK.POSITION_COLOR;
   }

   private static xR method_bIy() {
      return xR.ZERO;
   }

   public static void method_bGp(ResourceLocation a, float b, float c, float d, float e, Color f) {
      GL11.glDisable(4359 ^ -43418 ^ '騢' ^ -10702);
      GL11.glEnable(16468 ^ -3059 ^ 525 ^ -16970);
      GL11.glDepthMask((boolean)(30417 ^ -28640 ^ 32183 ^ -25786));
      ys.glBlendFunc(10611 ^ -2235 ^ 9085 ^ -439, 18142 ^ -15855 ^ 26876 ^ -4304, 1036 ^ -3591 ^ 22640 ^ -21116, 486 ^ -22304 ^ 27686 ^ -15072);
      method_bGs(f.getRGB());
      nC.getMinecraft().getTextureManager().bindTexture(a);
      jI.drawModalRectWithCustomSizedTexture((int)b, (int)c, Float.intBitsToFloat(21474 ^ '꿅' ^ 8226 ^ 1568229085 ^ 17479 ^ '蘁' ^ 9297 ^ 1568242895), Float.intBitsToFloat(129478 ^ 80577 ^ 25516 ^ -1890552086 ^ 119824 ^ 96064 ^ 5921 ^ -1890557392), (int)d, (int)e, d, e);
      GL11.glDepthMask((boolean)(24244 ^ -32305 ^ 24775 ^ -16451));
      GL11.glDisable(5651 ^ 27213 ^ 31792 ^ 2956);
      GL11.glEnable('讯' ^ -23244 ^ '젘' ^ -4622);
      yh.disableBlend();
   }

   private static double method_bHv(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static double method_bIf(AxisAlignedBB var0) {
      return var0.maxZ;
   }

   private static double method_bHa(Ig var0) {
      return var0.posZ;
   }

   private static double method_bHQ(AxisAlignedBB var0) {
      return var0.minZ;
   }

   private static double method_bGO(Ig var0) {
      return var0.lastTickPosZ;
   }

   private static xR method_bIB() {
      return xR.ONE_MINUS_SRC_ALPHA;
   }

   private static double method_bIe(AxisAlignedBB var0) {
      return var0.maxY;
   }

   private static double method_bHH(AxisAlignedBB var0) {
      return var0.minX;
   }

   private static double method_bHB(AxisAlignedBB var0) {
      return var0.maxX;
   }

   private static double method_bGI(Ig var0) {
      return var0.posY;
   }

   private static ya method_bIA() {
      return ya.SRC_ALPHA;
   }

   private static double method_bGJ(Ig var0) {
      return var0.lastTickPosY;
   }

   public static void method_bGj(Ig a, Color b, float c) {
      yh.pushMatrix();
      yh.blendFunc(29129 ^ -37757 ^ '\uf097' ^ -4385, 2898 ^ -14853 ^ 17671 ^ -30547);
      GL11.glEnable(21940 ^ -30167 ^ 29268 ^ -22997);
      yh.glLineWidth(Float.intBitsToFloat(8798 ^ 516877 ^ 503276 ^ 355180550 ^ 519516 ^ 17374 ^ 514656 ^ 715896923));
      yh.disableTexture2D();
      GL11.glDisable(13537 ^ -23470 ^ 24380 ^ -15106);
      yh.depthMask((boolean)(7113 ^ -9182 ^ 19718 ^ -29971));
      double d = method_bGA(a) + (method_bGB(a) - method_bGC(a)) * (double)method_bGF(method_bGE(method_bGD())) - method_bGG();
      double e = method_bGH(a) + (method_bGI(a) - method_bGJ(a)) * (double)method_bGM(method_bGL(method_bGK())) - method_bGN();
      double f = method_bGO(a) + (method_bGP(a) - method_bGQ(a)) * (double)method_bGT(method_bGS(method_bGR())) - method_bGU();
      AxisAlignedBB g = a.getEntityBoundingBox();
      AxisAlignedBB h = new AxisAlignedBB(method_bGV(g) - method_bGW(a) + d - Double.longBitsToDouble(6488379950344199798L ^ 7323629977404033004L), method_bGX(g) - method_bGY(a) + e, method_bGZ(g) - method_bHa(a) + f - Double.longBitsToDouble(6396109036688957629L ^ 7451789280228899111L), method_bHb(g) - method_bHc(a) + d + Double.longBitsToDouble(-7008074424313443878L ^ -6838776607783153600L), method_bHd(g) - method_bHe(a) + e + Double.longBitsToDouble(2236271772062038940L ^ 2363235760109160623L), method_bHf(g) - method_bHg(a) + f + Double.longBitsToDouble(-7298712631271573119L ^ -6549260726106644453L));
      yh.glLineWidth(Float.intBitsToFloat(14719 ^ 497748 ^ 4815 ^ -2101627059 ^ 22184 ^ 521026 ^ 30506 ^ -1120151959));
      GL11.glEnable(22417 ^ 28588 ^ 13866 ^ 1335);
      yh.color((float)b.getRed() / Float.intBitsToFloat(22054 ^ 'ꠋ' ^ 11924 ^ -933280965 ^ 28655 ^ 95814 ^ 130418 ^ -1960806567), (float)b.getGreen() / Float.intBitsToFloat(1039125 ^ 1024498 ^ 7467 ^ -1691900622 ^ 5617 ^ 30082 ^ 14850 ^ -665282417), (float)b.getBlue() / Float.intBitsToFloat(5464 ^ 21250 ^ 13330 ^ 1523857773 ^ 12077 ^ '\ue8ce' ^ '쫾' ^ 430655032), c);
      method_bGk(h);
      yh.glLineWidth(Float.intBitsToFloat(13108 ^ 242007 ^ 2926 ^ -1256354013 ^ '薟' ^ 234058 ^ '쟯' ^ -1969367532));
      yh.enableTexture2D();
      GL11.glEnable(18051 ^ -2153 ^ 7058 ^ -24073);
      yh.depthMask((boolean)(15960 ^ -32086 ^ 25673 ^ -10054));
      yh.disableBlend();
      yh.popMatrix();
   }

   private static double method_bGZ(AxisAlignedBB var0) {
      return var0.minZ;
   }

   private static float method_bGF(Timer var0) {
      return var0.renderPartialTicks;
   }

   private static double method_bHi(AxisAlignedBB var0) {
      return var0.minX;
   }

   private static double method_bId(AxisAlignedBB var0) {
      return var0.maxX;
   }

   private static zO method_bIp() {
      return zK.POSITION;
   }

   private static double method_bIg(AxisAlignedBB var0) {
      return var0.minX;
   }

   public static void method_bGt(float a, float b, float c, float d, Color e) {
      int f = e.getRGB();
      c += a;
      d += b;
      float g = (float)(f >> (3501 ^ -32130 ^ 20870 ^ -8627) & (9639 ^ -19650 ^ 5316 ^ -32094)) / Float.intBitsToFloat(121862 ^ 120512 ^ 18070 ^ 479371035 ^ 253190 ^ 28049 ^ 261517 ^ 1609408081);
      float h = (float)(f >> (25020 ^ -9156 ^ 31457 ^ -14479) & (10807 ^ -6759 ^ 13000 ^ -615)) / Float.intBitsToFloat('뛗' ^ 85544 ^ 6352 ^ 1437915495 ^ 20496 ^ '躊' ^ 'ﬤ' ^ 382344438);
      float i = (float)(f >> (8068 ^ -25291 ^ 157 ^ -32220) & (11125 ^ -622 ^ 21657 ^ -32127)) / Float.intBitsToFloat(100000 ^ 129583 ^ 23166 ^ -825173043 ^ 2067874 ^ 2092548 ^ 32223 ^ -1917852859);
      float j = (float)(f & (5830 ^ -2317 ^ 30283 ^ -27007)) / Float.intBitsToFloat('聹' ^ '頉' ^ 14174 ^ -1553965631 ^ 'ꆎ' ^ 'ꢴ' ^ 14491 ^ -534822066);
      yh.enableBlend();
      yh.disableTexture2D();
      yh.tryBlendFuncSeparate(23014 ^ -4238 ^ 18670 ^ -648, 25099 ^ -9703 ^ 9444 ^ -24587, 13518 ^ -26319 ^ 9503 ^ -30495, 4540 ^ -2026 ^ 1443 ^ -5111);
      method_bGw((double)h, (double)i, (double)j, (double)g);
      yN k = yN.getInstance();
      tN l = k.getBuffer();
      l.begin(15315 ^ -21646 ^ 1817 ^ -26689, method_bIp());
      l.pos((double)a, (double)d, Double.longBitsToDouble(-674907865277270357L ^ -674907865277270357L)).endVertex();
      l.pos((double)c, (double)d, Double.longBitsToDouble(8759290079943108975L ^ 8759290079943108975L)).endVertex();
      l.pos((double)c, (double)b, Double.longBitsToDouble(6689137207599223720L ^ 6689137207599223720L)).endVertex();
      l.pos((double)a, (double)b, Double.longBitsToDouble(-1695888518939481190L ^ -1695888518939481190L)).endVertex();
      k.draw();
      yh.enableTexture2D();
      yh.disableBlend();
   }

   private static double method_bHb(AxisAlignedBB var0) {
      return var0.maxX;
   }

   private static double method_bHm(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static HashMap method_bIo() {
      return field_a;
   }

   private static double method_bGY(Ig var0) {
      return var0.posY;
   }

   private static double method_bHk(AxisAlignedBB var0) {
      return var0.minZ;
   }

   private static ya method_bIv() {
      return ya.SRC_ALPHA;
   }

   private static double method_bGG() {
      return wC.renderPosX;
   }

   private static ya method_bIq() {
      return ya.SRC_ALPHA;
   }

   public static void method_bGf(float a, float b, float c, double d) {
      mC e = new mC(method_bGz());
      double f = (double)e.getScaleFactor();
      double g = d * f;
      double h = (double)((float)e.getScaledHeight() - b) * f;
      double i = (double)a * f;
      double j = (double)c * f;
      GL11.glScissor((int)i, (int)(h - g), (int)j, (int)g);
      GL11.glEnable(24286 ^ -14649 ^ 3516 ^ -26188);
   }

   private static double method_bHG(AxisAlignedBB var0) {
      return var0.maxZ;
   }

   private static double method_bHo(AxisAlignedBB var0) {
      return var0.maxX;
   }

   private static ya method_bIC() {
      return ya.ONE;
   }

   private static double method_bGV(AxisAlignedBB var0) {
      return var0.minX;
   }

   private static double method_bHI(AxisAlignedBB var0) {
      return var0.maxY;
   }

   public static void method_bGx(double a, double b, double c, double d, int e, int f) {
      c += a;
      d += b;
      float g = (float)(e >> (580 ^ -22906 ^ 26381 ^ -15401) & (1660 ^ -30407 ^ 501 ^ -29105)) / Float.intBitsToFloat(15770 ^ '酠' ^ 26755 ^ 1147490281 ^ 100973 ^ '蓾' ^ 122873 ^ 119167738);
      float h = (float)(e >> (684 ^ -26567 ^ 6006 ^ -29197) & (10118 ^ -32338 ^ 23422 ^ -599)) / Float.intBitsToFloat(522258 ^ 464958 ^ 31322 ^ -686597844 ^ 6589 ^ '봮' ^ 5195 ^ -1804831870);
      float i = (float)(e >> (23064 ^ -6756 ^ 20027 ^ -3657) & (12512 ^ -18234 ^ 26210 ^ -4421)) / Float.intBitsToFloat(18067 ^ 90883 ^ '\ue55d' ^ 281473404 ^ 'ꢕ' ^ 1010823 ^ 7954 ^ 1404562097);
      float j = (float)(e & (23913 ^ -15495 ^ 15892 ^ -24325)) / Float.intBitsToFloat(13977 ^ 231720 ^ 243425 ^ 335926539 ^ 101883 ^ 126649 ^ 6582 ^ 1467655855);
      float k = (float)(f >> (3 ^ -11798 ^ 15334 ^ -5609) & (19908 ^ -24943 ^ 31413 ^ -22241)) / Float.intBitsToFloat(16982 ^ '轎' ^ 12536 ^ 222125946 ^ 112815 ^ '舍' ^ 125267 ^ 1312977259);
      float l = (float)(f >> (3341 ^ -28895 ^ 12906 ^ -20394) & (16192 ^ -20630 ^ 30543 ^ -6246)) / Float.intBitsToFloat(13769 ^ 88988 ^ '엏' ^ 630907446 ^ '\udc9c' ^ 480271 ^ 6680 ^ 1726208807);
      float m = (float)(f >> (466 ^ -24176 ^ 28998 ^ -12020) & (27242 ^ -26732 ^ 23861 ^ -24524)) / Float.intBitsToFloat(2440 ^ 91243 ^ '釋' ^ -64434424 ^ 22403 ^ 16511 ^ 11985 ^ -1084880371);
      float n = (float)(f & (12043 ^ -22015 ^ 11396 ^ -22159)) / Float.intBitsToFloat(116580 ^ 126133 ^ 22625 ^ -144405476 ^ 496790 ^ 502310 ^ 28139 ^ -1273254665);
      yh.disableTexture2D();
      yh.enableBlend();
      yh.disableAlpha();
      yh.tryBlendFuncSeparate(method_bIv(), method_bIw(), method_bIx(), method_bIy());
      yh.shadeModel(14392 ^ -11651 ^ 7860 ^ -5648);
      yN o = yN.getInstance();
      tN p = o.getBuffer();
      p.begin(14812 ^ -26257 ^ 16921 ^ -7507, method_bIz());
      p.pos(a, b, Double.longBitsToDouble(7741893963148482626L ^ 7741893963148482626L)).color(h, i, j, g).endVertex();
      p.pos(a, d, Double.longBitsToDouble(3929657534864931920L ^ 3929657534864931920L)).color(h, i, j, g).endVertex();
      p.pos(c, d, Double.longBitsToDouble(7583515831473334758L ^ 7583515831473334758L)).color(l, m, n, k).endVertex();
      p.pos(c, b, Double.longBitsToDouble(3538494155217691370L ^ 3538494155217691370L)).color(l, m, n, k).endVertex();
      o.draw();
      yh.shadeModel(27394 ^ -9702 ^ 21959 ^ -1569);
      yh.disableBlend();
      yh.enableAlpha();
      yh.enableTexture2D();
   }

   private static double method_bHA(AxisAlignedBB var0) {
      return var0.minZ;
   }

   private static double method_bHL(AxisAlignedBB var0) {
      return var0.maxY;
   }

   private static nC method_bGR() {
      return mc;
   }

   private static float method_bGT(Timer var0) {
      return var0.renderPartialTicks;
   }

   public _en/* $FF was: 0en*/() {
   }

   private static double method_bGH(Ig var0) {
      return var0.lastTickPosY;
   }

   private static double method_bHV(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static double method_bIj(AxisAlignedBB var0) {
      return var0.minX;
   }

   private static double method_bHZ(AxisAlignedBB var0) {
      return var0.minZ;
   }

   private static double method_bHr(AxisAlignedBB var0) {
      return var0.minX;
   }
}
