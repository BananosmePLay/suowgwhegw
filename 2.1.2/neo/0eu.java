package neo;

import com.jhlabs.image.GaussianFilter;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Objects;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureUtil;
import org.lwjgl.opengl.GL11;

public class 0eu {
   public static final HashMap<Integer, Integer> shadowCache = new HashMap();

   private static GlStateManager.DestFactor L3lZ68XPD3() {
      return GlStateManager.DestFactor.ZERO;
   }

   private static HashMap eXw9WjZP7o() {
      return shadowCache;
   }

   public static void drawGlow(float x, float y, float width, float height, int glowRadius, Color color) {
      BufferedImage original = null;
      GaussianFilter op = null;
      GL11.glPushMatrix();
      GlStateManager.alphaFunc(11750 ^ -18030 ^ 23028 ^ -12412, Float.intBitsToFloat(4168288 ^ 4173883 ^ 30977 ^ 262111488 ^ 2526 ^ 497330 ^ 8069 ^ 867927481));
      width += (float)(glowRadius * (31481 ^ -16262 ^ 17088 ^ -1983));
      height += (float)(glowRadius * (7132 ^ -4751 ^ 20608 ^ -22993));
      x -= (float)glowRadius;
      y -= (float)glowRadius;
      float _X = x - Float.intBitsToFloat(17830 ^ 234755 ^ 4558 ^ 272225638 ^ 28888 ^ 205469 ^ '\ueac0' ^ 783920264);
      float _Y = y + Float.intBitsToFloat('铓' ^ 101196 ^ '锝' ^ 941940362 ^ '鳱' ^ 29388 ^ '늙' ^ 111478956);
      int identifier = String.valueOf(width * height + width + (float)((12698 ^ 107635 ^ 114945 ^ 999988968) * glowRadius) + (float)glowRadius).hashCode();
      GL11.glEnable(30383 ^ -21177 ^ 14085 ^ -7924);
      GL11.glDisable(19755 ^ -17168 ^ 23149 ^ -24334);
      GL11.glEnable(6086 ^ -30544 ^ 26876 ^ -950);
      GlStateManager.enableBlend();
      int texId = -22425 ^ -13152 ^ 9278 ^ -16634;
      if (weglokH8CB().containsKey(identifier)) {
         texId = (Integer)gZgQDNhrrq().get(identifier);
         GlStateManager.bindTexture(texId);
      } else {
         if (width <= Float.intBitsToFloat('쭺' ^ '跮' ^ 26351 ^ -356760643 ^ '\ued56' ^ '쉀' ^ 1412 ^ -356759212)) {
            width = Float.intBitsToFloat('찙' ^ 217849 ^ 14117 ^ 1892282753 ^ 1618 ^ '두' ^ '쫅' ^ 1330258051);
         }

         if (height <= Float.intBitsToFloat(5798 ^ 2080126 ^ 18336 ^ 965732776 ^ 16085 ^ 2086979 ^ 30356 ^ 965711314)) {
            height = Float.intBitsToFloat(1043 ^ 493336 ^ 515448 ^ -665075875 ^ 502091 ^ 500408 ^ 19710 ^ -405035485);
         }

         original = new BufferedImage((int)width, (int)height, 13319 ^ -11785 ^ 28205 ^ -29730);
         Graphics g = original.getGraphics();
         g.setColor(Vk3gsjR47K());
         g.fillRect(glowRadius, glowRadius, (int)(width - (float)(glowRadius * (14851 ^ -21078 ^ 17393 ^ -11174))), (int)(height - (float)(glowRadius * (1198 ^ -28497 ^ 17068 ^ -10577))));
         g.dispose();
         op = new GaussianFilter((float)glowRadius);
         BufferedImage blurred = op.filter(original, (BufferedImage)null);
         texId = TextureUtil.uploadTextureImageAllocate(TextureUtil.glGenTextures(), blurred, (boolean)(22983 ^ -10656 ^ 6326 ^ -26864), (boolean)(19146 ^ -28724 ^ 2431 ^ -13191));
         Y6kE6dxVFH().put(identifier, texId);
      }

      0em.glColor(color);
      GL11.glBegin(6817 ^ -18733 ^ 13847 ^ -26014);
      GL11.glTexCoord2f(Float.intBitsToFloat(17753 ^ '\udbc2' ^ 19230 ^ 973492784 ^ 29801 ^ '\uf60f' ^ 15175 ^ 973487764), Float.intBitsToFloat(1032989 ^ 1039810 ^ 24230 ^ -1121469816 ^ 1035135 ^ 1033717 ^ 11350 ^ -1121462227));
      GL11.glVertex2f(_X, _Y);
      GL11.glTexCoord2f(Float.intBitsToFloat(113545 ^ 111055 ^ 28844 ^ -1010400492 ^ 109545 ^ 123670 ^ 24358 ^ -1010373081), Float.intBitsToFloat('鈋' ^ '둽' ^ 14098 ^ 382872492 ^ 22926 ^ 247888 ^ 23963 ^ 693237389));
      GL11.glVertex2f(_X, _Y + height);
      GL11.glTexCoord2f(Float.intBitsToFloat(127983 ^ 112572 ^ 1845 ^ 2041610347 ^ 130110 ^ 31886 ^ 116374 ^ 1177584939), Float.intBitsToFloat(25392 ^ '隿' ^ 18318 ^ -1398855152 ^ 20135 ^ '赝' ^ 30454 ^ -1826673379));
      GL11.glVertex2f(_X + width, _Y + height);
      GL11.glTexCoord2f(Float.intBitsToFloat('\udf2d' ^ 489460 ^ 9074 ^ 2049698551 ^ 3553 ^ 474867 ^ '롁' ^ 1168894479), Float.intBitsToFloat(19096 ^ 87089 ^ '\uf0d0' ^ -2052535631 ^ '밿' ^ '螐' ^ 2803 ^ -2052516460));
      GL11.glVertex2f(_X + width, _Y);
      GL11.glEnd();
      GlStateManager.enableTexture2D();
      GlStateManager.disableBlend();
      GlStateManager.resetColor();
      GL11.glEnable(1410 ^ -24262 ^ 5125 ^ -17415);
      GL11.glPopMatrix();
   }

   private static HashMap I4Id3bPnto() {
      return shadowCache;
   }

   public _eu/* $FF was: 0eu*/() {
   }

   public static void drawGlowGradientFIX(float x, float y, float width, float height, int glowRadius, int color1, int color2, int color3, int color4) {
      BufferedImage original = null;
      GaussianFilter op = null;
      GL11.glPushMatrix();
      GlStateManager.enableBlend();
      GlStateManager.disableAlpha();
      GlStateManager.tryBlendFuncSeparate(WLlOjeDvdH(), nNB307I6Og(), tuMaAN21af(), 8lTEXg6vvg());
      GlStateManager.shadeModel(31798 ^ -26919 ^ 21806 ^ -23872);
      GlStateManager.alphaFunc(21041 ^ -8263 ^ 4817 ^ -25251, Float.intBitsToFloat('킄' ^ '똇' ^ 14523 ^ -1334957503 ^ 19569 ^ 207064 ^ 254160 ^ -1941100790));
      width += (float)(glowRadius * (18138 ^ -109 ^ 32147 ^ -15144));
      height += (float)(glowRadius * (22996 ^ -8628 ^ 25394 ^ -7000));
      x -= (float)glowRadius;
      y -= (float)glowRadius;
      float _X = x - Float.intBitsToFloat(29528 ^ '\uef40' ^ 11910 ^ -187027314 ^ 25617 ^ '\uab1d' ^ 30434 ^ -900056066);
      float _Y = y + Float.intBitsToFloat('\uab10' ^ 4100 ^ '\ua8cd' ^ 1138240192 ^ 27772 ^ '듷' ^ '첟' ^ 2102930701);
      int identifier = String.valueOf((double)(width * height + width) + Math.sin((double)((float)glowRadius / width * height)) * (double)glowRadius + (double)glowRadius).hashCode();
      GL11.glEnable(18877 ^ -5107 ^ 14530 ^ -28525);
      GL11.glDisable(26305 ^ -556 ^ 16695 ^ -11930);
      GL11.glEnable(21538 ^ -2515 ^ 12496 ^ -26337);
      GlStateManager.enableBlend();
      int texId = -3759 ^ -30078 ^ 18095 ^ -15741;
      if (SdbNt3OBrl().containsKey(identifier)) {
         texId = (Integer)QoMLv39XC8().get(identifier);
         GlStateManager.bindTexture(texId);
      } else {
         if (width <= Float.intBitsToFloat('뿗' ^ 32533 ^ '\uf5d5' ^ -474409813 ^ 31881 ^ 98874 ^ 29745 ^ -474436802)) {
            width = Float.intBitsToFloat('\ued0c' ^ 105586 ^ '켲' ^ -696844579 ^ 'ﶯ' ^ 83105 ^ 10630 ^ -369700839);
         }

         if (height <= Float.intBitsToFloat(3601 ^ 78989 ^ '鈷' ^ 65600347 ^ 'ힷ' ^ 84634 ^ 13403 ^ 65600134)) {
            height = Float.intBitsToFloat(7309 ^ '茄' ^ '\ue09c' ^ -669639474 ^ 7785 ^ 233834 ^ 871 ^ -409604161);
         }

         original = new BufferedImage((int)width, (int)height, 14282 ^ -15189 ^ 593 ^ -3789);
         Graphics g = original.getGraphics();
         g.setColor(ONJi4DygYw());
         g.fillRect(glowRadius, glowRadius, (int)width - glowRadius * (20819 ^ -1602 ^ 24502 ^ -2215), (int)height - glowRadius * (19776 ^ -6830 ^ 2356 ^ -24284));
         g.dispose();
         op = new GaussianFilter((float)glowRadius);
         BufferedImage blurred = op.filter(original, (BufferedImage)null);
         texId = TextureUtil.uploadTextureImageAllocate(TextureUtil.glGenTextures(), blurred, (boolean)(21521 ^ -10692 ^ 6806 ^ -26438), (boolean)(25317 ^ -4758 ^ 18435 ^ -14452));
         eXw9WjZP7o().put(identifier, texId);
      }

      GL11.glBegin(9691 ^ -7471 ^ 32360 ^ -18075);
      0em.glColor(color1);
      GL11.glTexCoord2f(Float.intBitsToFloat('\uf738' ^ 23981 ^ '쌘' ^ -295357985 ^ '싏' ^ 96351 ^ 2470 ^ -295376028), Float.intBitsToFloat(19307 ^ '\uf4a4' ^ 3043 ^ -906020510 ^ 30546 ^ '쐛' ^ 18457 ^ -906004962));
      GL11.glVertex2f(_X, _Y);
      0em.glColor(color2);
      GL11.glTexCoord2f(Float.intBitsToFloat('ꢮ' ^ 97182 ^ 8945 ^ -323301971 ^ 25894 ^ 90326 ^ '\ud831' ^ -323296851), Float.intBitsToFloat(9251 ^ 495003 ^ 3536 ^ -983325573 ^ 582 ^ 523812 ^ 9218 ^ -85731213));
      GL11.glVertex2f(_X, _Y + height);
      0em.glColor(color4);
      GL11.glTexCoord2f(Float.intBitsToFloat(119722 ^ 114375 ^ 2908 ^ 83405614 ^ 124749 ^ 99283 ^ 12430 ^ 997759247), Float.intBitsToFloat(28522 ^ 1030129 ^ 4043 ^ -1799889041 ^ 13887 ^ 1023038 ^ 4232 ^ -1422413130));
      GL11.glVertex2f(_X + width, _Y + height);
      0em.glColor(color3);
      GL11.glTexCoord2f(Float.intBitsToFloat('킪' ^ '픘' ^ 1992 ^ 1939625242 ^ '脥' ^ 488752 ^ 21747 ^ 1276901254), Float.intBitsToFloat(10276 ^ 107096 ^ 7280 ^ -138749548 ^ '\ueefa' ^ '誢' ^ 500 ^ -138728908));
      GL11.glVertex2f(_X + width, _Y);
      GL11.glEnd();
      GlStateManager.enableTexture2D();
      GlStateManager.shadeModel('蝯' ^ -19660 ^ '팗' ^ -1460);
      GlStateManager.disableBlend();
      GlStateManager.enableAlpha();
      GlStateManager.resetColor();
      GL11.glEnable(4880 ^ 27009 ^ 29664 ^ 565);
      GL11.glPopMatrix();
   }

   private static GlStateManager.DestFactor mUril47OS7() {
      return GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA;
   }

   private static GlStateManager.DestFactor _lTEXg6vvg/* $FF was: 8lTEXg6vvg*/() {
      return GlStateManager.DestFactor.ZERO;
   }

   private static HashMap o2LAuNdBPA() {
      return shadowCache;
   }

   public static void drawGlowHorizontal(float x, float y, float width, float height, int glowRadius, int color1, int color2) {
      BufferedImage original = null;
      GaussianFilter op = null;
      GL11.glPushMatrix();
      GlStateManager.enableBlend();
      GlStateManager.disableAlpha();
      GlStateManager.tryBlendFuncSeparate(xVgHEJiBv9(), mUril47OS7(), tYFHbNfTs2(), L3lZ68XPD3());
      GlStateManager.shadeModel(21709 ^ -54931 ^ '\ude2a' ^ -16757);
      GlStateManager.alphaFunc(2584 ^ -9196 ^ 6229 ^ -13219, Float.intBitsToFloat(24229 ^ '覗' ^ '\uf2bc' ^ 1045759519 ^ 103816 ^ 73525 ^ 49 ^ 41319959));
      width += (float)(glowRadius * (26487 ^ -32469 ^ 4562 ^ -2164));
      height += (float)(glowRadius * (681 ^ -10652 ^ 30043 ^ -24172));
      x -= (float)glowRadius;
      y -= (float)glowRadius;
      float _X = x - Float.intBitsToFloat('쯃' ^ '訌' ^ 28748 ^ 1352617695 ^ '\ue5ec' ^ '쓭' ^ 26438 ^ 1847539995);
      float _Y = y + Float.intBitsToFloat(16491 ^ 241509 ^ 250090 ^ -1997175165 ^ 237503 ^ 221076 ^ 9744 ^ -1233821860);
      Object[] var10000 = new Object[12888 ^ -23451 ^ 3671 ^ -26519];
      var10000[30856 ^ -31933 ^ 19372 ^ -20377] = width;
      var10000[13444 ^ -16084 ^ 2113 ^ -536] = height;
      var10000[9587 ^ -15788 ^ 30211 ^ -28378] = glowRadius;
      int identifier = Objects.hash(var10000);
      GL11.glEnable(10972 ^ -23109 ^ 12464 ^ -19914);
      GL11.glDisable(29682 ^ -13569 ^ 2860 ^ -18075);
      GL11.glEnable(23782 ^ -11035 ^ 3356 ^ -28961);
      GlStateManager.enableBlend();
      int texId = -3512 ^ -6038 ^ 13610 ^ -12041;
      if (o2LAuNdBPA().containsKey(identifier)) {
         texId = (Integer)itRz9AyLIq().get(identifier);
         GlStateManager.bindTexture(texId);
      } else {
         if (width <= Float.intBitsToFloat(22866 ^ 235192 ^ 22524 ^ -2114085787 ^ 27390 ^ 227020 ^ '\uedf5' ^ -2114062924)) {
            width = Float.intBitsToFloat(122738 ^ 18808 ^ 119615 ^ -1529568187 ^ 113885 ^ 78779 ^ 3127 ^ -1688968671);
         }

         if (height <= Float.intBitsToFloat(496324 ^ '붼' ^ 497287 ^ 1207657225 ^ 491736 ^ 520967 ^ 7249 ^ 1207678328)) {
            height = Float.intBitsToFloat(16409 ^ 2083050 ^ 12503 ^ -753948191 ^ 8485 ^ 2082862 ^ 7158 ^ -326114504);
         }

         original = new BufferedImage((int)width, (int)height, 2219 ^ -350 ^ 26664 ^ -25054);
         Graphics g = original.getGraphics();
         g.setColor(12vhOLq7rs());
         g.fillRect(glowRadius, glowRadius, (int)width - glowRadius * (30217 ^ -270 ^ 3793 ^ -31192), (int)height - glowRadius * (28791 ^ -26707 ^ 21053 ^ -18971));
         g.dispose();
         op = new GaussianFilter((float)glowRadius);
         BufferedImage blurred = op.filter(original, (BufferedImage)null);
         texId = TextureUtil.uploadTextureImageAllocate(TextureUtil.glGenTextures(), blurred, (boolean)(3669 ^ -24436 ^ 18658 ^ -6598), (boolean)(4369 ^ -29467 ^ 22986 ^ -15298));
         I4Id3bPnto().put(identifier, texId);
      }

      GL11.glBegin(23300 ^ -11804 ^ 17 ^ -29962);
      0em.glColor(color1);
      GL11.glTexCoord2f(Float.intBitsToFloat(21163 ^ 127482 ^ 24633 ^ 66504080 ^ 31390 ^ 129072 ^ 23142 ^ 66510384), Float.intBitsToFloat(10246 ^ '爐' ^ 4699 ^ 815813020 ^ 15284 ^ 13730 ^ 5248 ^ 815827045));
      GL11.glVertex2f(_X, _Y);
      GL11.glTexCoord2f(Float.intBitsToFloat(28272 ^ 29029 ^ 13084 ^ -942600223 ^ 27861 ^ '뉽' ^ '\ued29' ^ -942600087), Float.intBitsToFloat(10775 ^ 505700 ^ 23613 ^ -2017212471 ^ 21112 ^ 500243 ^ 24916 ^ -1203529800));
      GL11.glVertex2f(_X, _Y + height);
      0em.glColor(color2);
      GL11.glTexCoord2f(Float.intBitsToFloat(29754 ^ 241711 ^ 7973 ^ 2129252901 ^ 16269 ^ 250951 ^ 24293 ^ 1097448506), Float.intBitsToFloat('뛗' ^ 2094326 ^ '먬' ^ 997187583 ^ 21293 ^ '뺰' ^ '퍲' ^ 82843933));
      GL11.glVertex2f(_X + width, _Y + height);
      GL11.glTexCoord2f(Float.intBitsToFloat(25810 ^ '銿' ^ '\uec67' ^ 746988294 ^ 29843 ^ 28803 ^ 7474 ^ 319168558), Float.intBitsToFloat(26410 ^ 221018 ^ 248841 ^ -1735304560 ^ 26734 ^ 214024 ^ 247692 ^ -1735310077));
      GL11.glVertex2f(_X + width, _Y);
      GL11.glEnd();
      GlStateManager.enableTexture2D();
      GlStateManager.shadeModel(20098 ^ -3808 ^ 5759 ^ -19235);
      GlStateManager.disableBlend();
      GlStateManager.enableAlpha();
      GlStateManager.resetColor();
      GL11.glEnable(16041 ^ -7483 ^ 19105 ^ -25207);
      GL11.glPopMatrix();
   }

   private static HashMap itRz9AyLIq() {
      return shadowCache;
   }

   private static GlStateManager.SourceFactor tYFHbNfTs2() {
      return GlStateManager.SourceFactor.ONE;
   }

   private static GlStateManager.SourceFactor xVgHEJiBv9() {
      return GlStateManager.SourceFactor.SRC_ALPHA;
   }

   private static GlStateManager.DestFactor nNB307I6Og() {
      return GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA;
   }

   private static HashMap QoMLv39XC8() {
      return shadowCache;
   }

   private static HashMap SdbNt3OBrl() {
      return shadowCache;
   }

   private static Color _2vhOLq7rs/* $FF was: 12vhOLq7rs*/() {
      return Color.white;
   }

   private static Color ONJi4DygYw() {
      return Color.white;
   }

   private static GlStateManager.SourceFactor WLlOjeDvdH() {
      return GlStateManager.SourceFactor.SRC_ALPHA;
   }

   private static HashMap Y6kE6dxVFH() {
      return shadowCache;
   }

   private static GlStateManager.SourceFactor tuMaAN21af() {
      return GlStateManager.SourceFactor.ONE;
   }

   private static Color Vk3gsjR47K() {
      return Color.white;
   }

   private static HashMap gZgQDNhrrq() {
      return shadowCache;
   }

   private static HashMap weglokH8CB() {
      return shadowCache;
   }
}
