package neo;

import java.awt.Color;
import java.awt.image.BufferedImage;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.math.MathHelper;

public class 0em {
   public static Color TwoColorEffect(Color cl1, Color cl2, double speed, int index) {
      int angle = (int)(((double)System.currentTimeMillis() / speed + (double)index) % Double.longBitsToDouble(-7980553968338207809L ^ -3365912462655358017L));
      angle = (angle >= (18043 ^ -22067 ^ 8405 ^ -12329) ? (27150 ^ -14936 ^ 11515 ^ -32203) - angle : angle) * (25223 ^ -14897 ^ 19652 ^ -5234);
      return interpolateColorC(cl1, cl2, (float)angle / Float.intBitsToFloat('邨' ^ 489401 ^ 12102 ^ -92016018 ^ 27092 ^ 2050249 ^ '\uf1cd' ^ -1188042007));
   }

   public static int rainbow(int delay, double speed) {
      double rainbow = Math.ceil((double)(System.currentTimeMillis() + (long)delay) / speed);
      return Color.getHSBColor((float)(-(rainbow % Double.longBitsToDouble(-4087398642646600653L ^ -8705417848049978317L) / Double.longBitsToDouble(-1989396505331452806L ^ -6624022734360759174L))), Float.intBitsToFloat(100249 ^ 27801 ^ 121449 ^ -194079646 ^ 1190 ^ 30608 ^ 8181 ^ -888623186), Float.intBitsToFloat('鯻' ^ '\uec0c' ^ 11553 ^ -318167585 ^ '\ue4f9' ^ '땷' ^ 19131 ^ -762747844)).getRGB();
   }

   public static Color fade(String s, String s1, String s2, String s3) {
      return null;
   }

   private static int bitChangeColor(int color, int bitChange) {
      return color >> bitChange & ('肁' ^ -1119 ^ '롗' ^ -15480);
   }

   public static float[] rgbToHSL(Color rgb) {
      float red = (float)rgb.getRed() / Float.intBitsToFloat(119459 ^ 124910 ^ 17352 ^ -13065298 ^ 3351 ^ 1034779 ^ 1021190 ^ -1136163039);
      float green = (float)rgb.getGreen() / Float.intBitsToFloat(233608 ^ '诋' ^ 235091 ^ -1796113137 ^ 114767 ^ 105018 ^ 26390 ^ -678507140);
      float blue = (float)rgb.getBlue() / Float.intBitsToFloat('튯' ^ 2869 ^ '\udf05' ^ 1061700150 ^ '\uded0' ^ 85557 ^ 11859 ^ 2083977759);
      float max = Math.max(Math.max(red, green), blue);
      float min2 = Math.min(Math.min(red, green), blue);
      float c = (max + min2) / Float.intBitsToFloat('ꇏ' ^ 2082022 ^ '\ue1aa' ^ -1947222113 ^ '鎎' ^ '豖' ^ 11772 ^ -873461448);
      float[] var10000 = new float[23128 ^ -17111 ^ 27329 ^ -29261];
      var10000[2310 ^ -29157 ^ 29160 ^ -2315] = c;
      var10000[21567 ^ -17422 ^ 25641 ^ -29723] = c;
      var10000[758 ^ -5707 ^ 24404 ^ -19435] = c;
      float[] hsl = var10000;
      if (max == min2) {
         hsl[12697 ^ -22534 ^ 21278 ^ -14980] = Float.intBitsToFloat(8169 ^ '骸' ^ 12995 ^ 1146371239 ^ 16985 ^ '뎿' ^ 3480 ^ 1146386251);
         hsl[24103 ^ -15755 ^ 22163 ^ -13631] = Float.intBitsToFloat('\uf46d' ^ '铽' ^ 11720 ^ -1214700085 ^ '\ud976' ^ '랐' ^ 25286 ^ -1214683981);
      } else {
         float d = max - min2;
         float f = hsl[5189 ^ -22924 ^ 4211 ^ -23997] = (double)hsl[4378 ^ -19993 ^ 4160 ^ -20289] > Double.longBitsToDouble(-4284053680758150812L ^ -329893207926855324L) ? d / (Float.intBitsToFloat(4175419 ^ 20863 ^ 4178172 ^ 2132777897 ^ 4177878 ^ 4161372 ^ 5517 ^ 1059023126) - max - min2) : d / (max + min2);
         if (max == red) {
            hsl[22793 ^ -18228 ^ 5835 ^ -2290] = (green - blue) / d + (float)(green < blue ? 26 ^ -23775 ^ 5465 ^ -18844 : 5964 ^ -1357 ^ 19906 ^ -24515);
         } else if (max == blue) {
            hsl[30625 ^ -6216 ^ 8721 ^ -19960] = (blue - red) / d + Float.intBitsToFloat('ꈦ' ^ '\ueba4' ^ 24232 ^ -1564907952 ^ '鱩' ^ '튲' ^ 23105 ^ -491166240);
         } else if (max == green) {
            hsl[10012 ^ -22848 ^ 17823 ^ -15293] = (red - green) / d + Float.intBitsToFloat(22078 ^ 235110 ^ 16100 ^ -1735270835 ^ '껠' ^ 250292 ^ '뻢' ^ -669916857);
         }

         hsl[31218 ^ -11385 ^ 24294 ^ -2925] = hsl[27833 ^ -6707 ^ 26321 ^ -4187] / Float.intBitsToFloat(232566 ^ '鰡' ^ 248454 ^ 357918669 ^ 232908 ^ 222517 ^ 11572 ^ 1435859153);
      }

      return hsl;
   }

   public static Color TwoColorEffectStatic(Color cl1, Color cl2, int index) {
      int angle = index % (29055 ^ 7716 ^ 28214 ^ 5);
      angle = (angle >= (30944 ^ -19583 ^ 21249 ^ -26412) ? (4433 ^ -8495 ^ 1266 ^ -13798) - angle : angle) * (10340 ^ -4824 ^ 25905 ^ -24449);
      return interpolateColorC(cl1, cl2, (float)angle / Float.intBitsToFloat(126005 ^ '鉁' ^ 116133 ^ -2107478430 ^ 14400 ^ 22554 ^ 7804 ^ -1042895979));
   }

   public static Color skyRainbow(int speed, int index, float saturation) {
      int angle = (int)((System.currentTimeMillis() / (long)speed + (long)index) % 360L);
      float hue = (float)angle / Float.intBitsToFloat(517536 ^ 504800 ^ 11492 ^ -2039247574 ^ 1041199 ^ 1026827 ^ 31075 ^ -976763191);
      return Color.getHSBColor((double)((float)((double)(angle = (int)((double)angle % Double.longBitsToDouble(-887419471905613994L ^ -5487142803822799018L))) / Double.longBitsToDouble(-9116562312907594308L ^ -4535697804430023236L))) < Double.longBitsToDouble(-7690919182994242173L ^ -6150688110433532541L) ? -((float)((double)angle / Double.longBitsToDouble(8320991586290843585L ^ 3678484057913638849L))) : (float)((double)angle / Double.longBitsToDouble(5503937002551893878L ^ 871281098359562102L)), saturation, Float.intBitsToFloat('\uefb8' ^ '삵' ^ 9828 ^ -1757515359 ^ '黙' ^ '틡' ^ 17864 ^ -1463914184));
   }

   public static int getColor(int red, int green, int blue, int alpha) {
      int color = 21185 ^ -18738 ^ 20285 ^ -21710;
      color |= alpha << (3990 ^ -13224 ^ 16930 ^ -32268);
      color |= red << (17423 ^ -771 ^ 3392 ^ -19038);
      color |= green << (2254 ^ -15720 ^ 13749 ^ -21);
      return color | blue;
   }

   public static int applyOpacity(int rgb) {
      return 11188 ^ -8268 ^ 30904 ^ -29512;
   }

   public static void glColor(int hex, float alpha) {
      float red = (float)(hex >> (2582 ^ -16356 ^ 16873 ^ -29709) & (21540 ^ -24366 ^ 12599 ^ -15042)) / Float.intBitsToFloat(25664 ^ '돔' ^ 2542 ^ -1965735417 ^ 100451 ^ 11497 ^ 113154 ^ -911572235);
      float green = (float)(hex >> (18113 ^ -26190 ^ 11240 ^ -2925) & (19508 ^ -2557 ^ 19327 ^ -3657)) / Float.intBitsToFloat(26447 ^ 486198 ^ 519664 ^ -2062849097 ^ 9857 ^ 70629 ^ 109325 ^ -965471145);
      float blue = (float)(hex & (15435 ^ -25251 ^ 13206 ^ -28033)) / Float.intBitsToFloat('칦' ^ '\udf3c' ^ 1040 ^ -436313910 ^ '\uf174' ^ '춁' ^ 715 ^ -1501475906);
      GlStateManager.color(red, green, blue, alpha / Float.intBitsToFloat(1035661 ^ 24269 ^ 1039179 ^ 1809286655 ^ 11566 ^ 17542 ^ 1004 ^ 682141616));
   }

   public _em/* $FF was: 0em*/() {
   }

   public static int applyOpacity(int color, float opacity) {
      Color old = new Color(color);
      return applyOpacity(old, opacity).getRGB();
   }

   public static Color fade(int speed, int index, Color color, float alpha) {
      float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), (float[])null);
      int angle = (int)((System.currentTimeMillis() / (long)speed + (long)index) % 360L);
      angle = (angle > (19249 ^ -27574 ^ 25330 ^ -17091) ? (12999 ^ -341 ^ 12660 ^ -912) - angle : angle) + (23945 ^ -33301 ^ '\udd7f' ^ -599);
      Color colorHSB = new Color(Color.HSBtoRGB(hsb[18375 ^ -27244 ^ 15032 ^ -5909], hsb[12017 ^ -29613 ^ 28826 ^ -11719], (float)angle / Float.intBitsToFloat(17944 ^ 94771 ^ 124199 ^ -1230213813 ^ 100558 ^ '趮' ^ 116802 ^ -182947483)));
      return new Color(colorHSB.getRed(), colorHSB.getGreen(), colorHSB.getBlue(), Math.max(26899 ^ -23599 ^ 32340 ^ -19306, Math.min(12560 ^ -550 ^ 26805 ^ -23424, (int)(alpha * Float.intBitsToFloat(30501 ^ 26107 ^ 10893 ^ 2127009118 ^ 28455 ^ '엘' ^ 10414 ^ 1035480924)))));
   }

   public static int setAlpha(int color, int alpha) {
      Color c = new Color(color);
      return (new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha)).getRGB();
   }

   public static Color rainbow(int speed, int index, float saturation, float brightness, float opacity) {
      int angle = (int)((System.currentTimeMillis() / (long)speed + (long)index) % 360L);
      float hue = (float)angle / Float.intBitsToFloat(16774 ^ 4168020 ^ 13794 ^ -94377599 ^ 740 ^ 509340 ^ 2092 ^ -1177302043);
      Color color = new Color(Color.HSBtoRGB(hue, saturation, brightness));
      return new Color(color.getRed(), color.getGreen(), color.getBlue(), Math.max(6074 ^ -2278 ^ 26734 ^ -30514, Math.min(17116 ^ -14958 ^ 6552 ^ -25047, (int)(opacity * Float.intBitsToFloat(9096 ^ '뉐' ^ '셖' ^ 2068448063 ^ '\udb38' ^ 'ꭅ' ^ 15943 ^ 943005067)))));
   }

   public static Color TwoColorEffect(Color cl1, Color cl2, double speed) {
      double thing = speed / Double.longBitsToDouble(7041257061891638174L ^ 2425067443836879774L) % Double.longBitsToDouble(-960588530374010111L ^ -3649237507914196223L);
      float val = MathHelper.clamp((float)Math.sin(Double.longBitsToDouble(6422046920977692784L ^ 1814221511770708386L) * thing) / Float.intBitsToFloat(30394 ^ 17000 ^ 243 ^ 109250918 ^ '궠' ^ 84536 ^ 3464 ^ 1182979927) + Float.intBitsToFloat('鷼' ^ 81817 ^ 1664 ^ -1440434718 ^ 24184 ^ 106778 ^ 10135 ^ -1792751118), Float.intBitsToFloat(6899 ^ 23777 ^ 24841 ^ -349961857 ^ '뚲' ^ 496181 ^ '캹' ^ -349975462), Float.intBitsToFloat('靐' ^ 4174783 ^ '\udec2' ^ 1700785418 ^ 31229 ^ 15140 ^ 20862 ^ 1524630656));
      return new Color(0ei.lerp((double)((float)cl1.getRed() / Float.intBitsToFloat('벒' ^ 85303 ^ 26874 ^ 902099656 ^ 7518 ^ '퉈' ^ '칒' ^ 1991928531)), (double)((float)cl2.getRed() / Float.intBitsToFloat(242795 ^ 241994 ^ 1137 ^ 1028783729 ^ 126616 ^ 115796 ^ 14673 ^ 2117004476)), (double)val), 0ei.lerp((double)((float)cl1.getGreen() / Float.intBitsToFloat(40 ^ 125076 ^ 99260 ^ 593672333 ^ 234829 ^ 230778 ^ 24272 ^ 1612553578)), (double)((float)cl2.getGreen() / Float.intBitsToFloat('\ud896' ^ 21303 ^ '쀟' ^ 1267991994 ^ '裔' ^ '걉' ^ 27317 ^ 149618732)), (double)val), 0ei.lerp((double)((float)cl1.getBlue() / Float.intBitsToFloat('ꐾ' ^ '\ude18' ^ 22805 ^ 104486353 ^ '鸣' ^ 22180 ^ '鈨' ^ 1162161229)), (double)((float)cl2.getBlue() / Float.intBitsToFloat(13213 ^ 94513 ^ '來' ^ -1799165596 ^ 1916 ^ 466598 ^ '鸻' ^ -675556092)), (double)val));
   }

   public static int getColor(int bright) {
      return getColor(bright, bright, bright, 5625 ^ -25916 ^ 201 ^ -28917);
   }

   public static void glColor(int hex) {
      float alpha = (float)(hex >> (16323 ^ -4083 ^ 27522 ^ -23468) & (3845 ^ -18915 ^ 25042 ^ -10187)) / Float.intBitsToFloat(12104 ^ 29426 ^ 10796 ^ -571333496 ^ '튭' ^ 8420 ^ '\uea99' ^ -1634907186);
      float red = (float)(hex >> (5314 ^ -32233 ^ 28691 ^ -6442) & (13611 ^ -4971 ^ 10228 ^ -331)) / Float.intBitsToFloat(127043 ^ 90700 ^ 9026 ^ -660576040 ^ 4189330 ^ 4178181 ^ 28915 ^ -1679850255);
      float green = (float)(hex >> (25478 ^ -190 ^ 22040 ^ -13612) & (11049 ^ -17353 ^ 25135 ^ -2610)) / Float.intBitsToFloat(101398 ^ '힂' ^ 123886 ^ -1193396851 ^ 31693 ^ '親' ^ 1015 ^ -73302937);
      float blue = (float)(hex & (31997 ^ -25270 ^ 20670 ^ -19978)) / Float.intBitsToFloat('\ue255' ^ '鉰' ^ 10623 ^ -81320686 ^ 'ꧧ' ^ '飮' ^ 13675 ^ -1202161622);
      GlStateManager.color(red, green, blue, alpha);
   }

   public static int[] createColorArray(int color) {
      int[] var10000 = new int[2170 ^ -19420 ^ 24270 ^ -7532];
      var10000[7732 ^ -2593 ^ 15245 ^ -12186] = bitChangeColor(color, 9066 ^ -8361 ^ 203 ^ -794);
      var10000[8008 ^ -9503 ^ 3226 ^ -14030] = bitChangeColor(color, 30974 ^ -30458 ^ 23297 ^ -21775);
      var10000[13227 ^ -31770 ^ 10874 ^ -26059] = bitChangeColor(color, 29707 ^ -9388 ^ 3064 ^ -23385);
      var10000[24398 ^ -20477 ^ 23704 ^ -19498] = bitChangeColor(color, 5963 ^ -27912 ^ 3924 ^ -29953);
      return var10000;
   }

   public static int getOppositeColor(int color) {
      int R = bitChangeColor(color, 13133 ^ -7376 ^ 17276 ^ -27903);
      int G = bitChangeColor(color, 24537 ^ -2310 ^ 12798 ^ -26411);
      int B = bitChangeColor(color, 10163 ^ -24070 ^ 4800 ^ -27495);
      int A2 = bitChangeColor(color, 16102 ^ -15641 ^ 5110 ^ -4113);
      R = (2006 ^ -26334 ^ 9579 ^ -17568) - R;
      G = (15420 ^ -21592 ^ 19464 ^ -9373) - G;
      B = (30851 ^ -20345 ^ 21228 ^ -26089) - B;
      return R + (G << (7155 ^ -13252 ^ 15324 ^ -5093)) + (B << (31711 ^ -30160 ^ 15632 ^ -13073)) + (A2 << (8769 ^ -4871 ^ 17695 ^ -29761));
   }

   public static Color astolfoRainbow(int offset) {
      float speed = Float.intBitsToFloat('줢' ^ '푔' ^ 22570 ^ 531439188 ^ 105134 ^ 11713 ^ 108047 ^ 1519831656);

      float hue;
      for(hue = (float)(System.currentTimeMillis() % (long)((int)speed) + (long)offset); hue > speed; hue -= speed) {
      }

      if ((double)(hue /= speed) > Double.longBitsToDouble(840214299128918709L ^ 3767554056919741109L)) {
         hue = Float.intBitsToFloat('\ua7da' ^ 26428 ^ '\uf7c5' ^ -1420421105 ^ 16447 ^ 220121 ^ '욇' ^ -1806306739) - (hue - Float.intBitsToFloat(17553 ^ 88852 ^ 'ퟪ' ^ -1205406732 ^ 'ꕁ' ^ 22733 ^ '\ue482' ^ -2027478379));
      }

      return Color.getHSBColor(hue + Float.intBitsToFloat('頀' ^ '黎' ^ 11775 ^ 688330502 ^ 567 ^ 107581 ^ 29189 ^ 369551416), Float.intBitsToFloat(121102 ^ 108718 ^ 4766 ^ 1407093727 ^ '蛬' ^ 218866 ^ 10580 ^ 1829887334), Float.intBitsToFloat(15922 ^ 111802 ^ 9201 ^ 682944432 ^ '\udd44' ^ 15625 ^ '\ue089' ^ 389369357));
   }

   public static Color mixColors(Color color1, Color color2, double percent) {
      double inverse_percent = Double.longBitsToDouble(-7066320659335833369L ^ -6764579484302010137L) - percent;
      int redPart = (int)((double)color1.getRed() * percent + (double)color2.getRed() * inverse_percent);
      int greenPart = (int)((double)color1.getGreen() * percent + (double)color2.getGreen() * inverse_percent);
      int bluePart = (int)((double)color1.getBlue() * percent + (double)color2.getBlue() * inverse_percent);
      return new Color(redPart, greenPart, bluePart);
   }

   public static Color fade2(int speed, int index, Color color, float alpha) {
      float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), (float[])null);
      int angle = (int)((System.currentTimeMillis() / (long)speed + (long)index) % 360L);
      angle = (angle > (24637 ^ -11025 ^ 27735 ^ -10191) ? (23770 ^ -608 ^ 27586 ^ -13360) - angle : angle) + (9047 ^ -13706 ^ 17154 ^ -21865);
      Color colorHSB = new Color(Color.HSBtoRGB(hsb[19135 ^ -7323 ^ 32242 ^ -11224], hsb[24611 ^ -4553 ^ 11840 ^ -24491], (float)angle / Float.intBitsToFloat('쩂' ^ '荳' ^ 634 ^ -1931529421 ^ '\ue4ab' ^ '譱' ^ 21203 ^ -815053455)));
      return new Color(colorHSB.getRed(), colorHSB.getGreen(), colorHSB.getBlue(), Math.max(14090 ^ -8835 ^ 28064 ^ -30761, Math.min(31639 ^ -5526 ^ 6493 ^ -30625, (int)(alpha * Float.intBitsToFloat(130785 ^ 118498 ^ 13372 ^ -1663638744 ^ 253857 ^ 232057 ^ 6335 ^ -542528912)))));
   }

   public static Color darker(Color color, float FACTOR) {
      return new Color(Math.max((int)((float)color.getRed() * FACTOR), 26906 ^ -30639 ^ 19271 ^ -22004), Math.max((int)((float)color.getGreen() * FACTOR), 15158 ^ -10040 ^ 14342 ^ -9224), Math.max((int)((float)color.getBlue() * FACTOR), 19900 ^ -1749 ^ 30751 ^ -13176), color.getAlpha());
   }

   public static void glColor(Color color) {
      float red = (float)color.getRed() / Float.intBitsToFloat(120150 ^ 25752 ^ 119977 ^ 47356260 ^ 915 ^ 258139 ^ 230958 ^ 1101892069);
      float green = (float)color.getGreen() / Float.intBitsToFloat(30769 ^ 231206 ^ 6235 ^ 1059973527 ^ '蔁' ^ 120380 ^ '鞔' ^ 2085669490);
      float blue = (float)color.getBlue() / Float.intBitsToFloat(1843 ^ 26087 ^ 8189 ^ 1334157057 ^ 122929 ^ 17010 ^ 131051 ^ 217759616);
      float alpha = (float)color.getAlpha() / Float.intBitsToFloat(5140 ^ 101961 ^ 2436 ^ 1243779918 ^ 25648 ^ 497875 ^ 24034 ^ 157000086);
      GlStateManager.color(red, green, blue, alpha);
   }

   public static Color applyOpacity(Color color, float opacity) {
      opacity = Math.min(Float.intBitsToFloat(8967 ^ 1011139 ^ '봿' ^ -1591220629 ^ 20526 ^ 1008517 ^ '쀬' ^ -1633163753), Math.max(Float.intBitsToFloat('蓕' ^ 'ꙅ' ^ 20030 ^ 425466570 ^ '\ue745' ^ '쏙' ^ 4749 ^ 425476213), opacity));
      return new Color(color.getRed(), color.getGreen(), color.getBlue(), (int)((float)color.getAlpha() * opacity));
   }

   public static int fade(Color color, int delay) {
      float[] hsb = new float[22862 ^ -16174 ^ 6944 ^ -32065];
      Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), hsb);
      float brightness = Math.abs((float)(System.currentTimeMillis() % 2000L + (long)delay) / Float.intBitsToFloat(17989 ^ 90840 ^ 120875 ^ -1687527178 ^ 13076 ^ 22306 ^ 32289 ^ -552565161) % Float.intBitsToFloat(10603 ^ 'ﯞ' ^ 'ꊔ' ^ 254029645 ^ '퀂' ^ 473797 ^ 18315 ^ 1327755808) - Float.intBitsToFloat('賓' ^ '꼼' ^ 5435 ^ 1592932523 ^ '곤' ^ '鼼' ^ 8958 ^ 1634888174));
      brightness = Float.intBitsToFloat('Ꜽ' ^ 493305 ^ '\ue304' ^ -924587489 ^ 21316 ^ 504708 ^ 7634 ^ -136063540) + Float.intBitsToFloat(30204 ^ 79035 ^ 125505 ^ -2019282168 ^ 32 ^ '\ueab4' ^ 1238 ^ -1197181364) * brightness;
      hsb[21558 ^ -3076 ^ 26309 ^ -16115] = brightness % Float.intBitsToFloat(261123 ^ 249260 ^ 26306 ^ 1917952789 ^ 237702 ^ 253997 ^ 13889 ^ 844202642);
      return Color.HSBtoRGB(hsb[18892 ^ -19869 ^ 7902 ^ -6799], hsb[26060 ^ -21357 ^ 20402 ^ -30996], hsb[24571 ^ -593 ^ 11919 ^ -29479]);
   }

   public static Color TwoColoreffect(Color cl1, Color cl2, double speed) {
      double thing = speed / Double.longBitsToDouble(-5055522485235235049L ^ -448340066435217641L) % Double.longBitsToDouble(7250382248930825827L ^ 6588353103707362915L);
      float val = MathHelper.clamp((float)Math.sin(Double.longBitsToDouble(-2713691917379926703L ^ -7321200529906097021L) * thing) / Float.intBitsToFloat(130530 ^ 130102 ^ 30175 ^ 1887607476 ^ 124258 ^ 118706 ^ 18612 ^ 813862107) + Float.intBitsToFloat(22789 ^ 93279 ^ '廉' ^ 300873803 ^ '뾬' ^ 89453 ^ 10008 ^ 787411306), Float.intBitsToFloat(3794 ^ 106493 ^ 31536 ^ -96141937 ^ 22137 ^ 102419 ^ 8601 ^ -96145309), Float.intBitsToFloat('덺' ^ 22811 ^ '뮯' ^ -695782321 ^ 2672 ^ 'ꡡ' ^ '츞' ^ -385414770));
      return new Color(0ei.lerp((double)((float)cl1.getRed() / Float.intBitsToFloat(24129 ^ 13251 ^ 26643 ^ 472980204 ^ '鵢' ^ 220396 ^ 6211 ^ 1598932656)), (double)((float)cl2.getRed() / Float.intBitsToFloat(130784 ^ 22338 ^ 122250 ^ -34294019 ^ 509909 ^ 520939 ^ 17903 ^ -1098141180)), (double)val), 0ei.lerp((double)((float)cl1.getGreen() / Float.intBitsToFloat(14349 ^ '픿' ^ 5568 ^ -225477066 ^ 12350 ^ '찎' ^ 2927 ^ -1309642341)), (double)((float)cl2.getGreen() / Float.intBitsToFloat(120203 ^ 121862 ^ 7090 ^ -728965086 ^ 510040 ^ 503646 ^ 21236 ^ -1745631249)), (double)val), 0ei.lerp((double)((float)cl1.getBlue() / Float.intBitsToFloat(102304 ^ 105170 ^ 15994 ^ 686723150 ^ 243521 ^ 242405 ^ 5444 ^ 1804708774)), (double)((float)cl2.getBlue() / Float.intBitsToFloat(511799 ^ 511881 ^ 18130 ^ -1404334133 ^ 126937 ^ 111619 ^ 206 ^ -281767245)), (double)val));
   }

   public static Color brighter(Color color, float FACTOR) {
      int r = color.getRed();
      int g2 = color.getGreen();
      int b = color.getBlue();
      int alpha = color.getAlpha();
      int i = (int)(Double.longBitsToDouble(4985100219209207596L ^ 8853692299120463660L) / (Double.longBitsToDouble(-4731972980380307206L ^ -9104968218557058822L) - (double)FACTOR));
      if (r == 0 && g2 == 0 && b == 0) {
         return new Color(i, i, i, alpha);
      } else {
         if (r > 0 && r < i) {
            r = i;
         }

         if (g2 > 0 && g2 < i) {
            g2 = i;
         }

         if (b > 0 && b < i) {
            b = i;
         }

         return new Color(Math.min((int)((float)r / FACTOR), 31329 ^ -19109 ^ 21489 ^ -25548), Math.min((int)((float)g2 / FACTOR), 22043 ^ -22170 ^ 10707 ^ -10671), Math.min((int)((float)b / FACTOR), 12628 ^ -25531 ^ 3572 ^ -24550), alpha);
      }
   }

   public static Color interpolateColorsBackAndForth(int speed, int index, Color start, Color end, boolean trueColor) {
      int angle = (int)((System.currentTimeMillis() / (long)speed + (long)index) % 360L);
      angle = (angle >= (9991 ^ -2604 ^ 31003 ^ -21636) ? (11193 ^ -28079 ^ 28536 ^ -10248) - angle : angle) * (19614 ^ -1290 ^ 25571 ^ -10871);
      return trueColor ? interpolateColorHue(start, end, (float)angle / Float.intBitsToFloat(20721 ^ 23356 ^ 1843 ^ -795016501 ^ 13687 ^ 121600 ^ 18024 ^ -1826052566)) : interpolateColorC(start, end, (float)angle / Float.intBitsToFloat(118350 ^ 96709 ^ 8318 ^ -2134483346 ^ 11898 ^ 8855 ^ 1247 ^ -1015889495));
   }

   public static Color hslToRGB(float[] hsl) {
      float red;
      float green;
      float blue;
      if (hsl[32735 ^ -17972 ^ 25925 ^ -23721] == Float.intBitsToFloat(258020 ^ 230061 ^ 5212 ^ -1944740878 ^ 239286 ^ 30309 ^ 242501 ^ -1944747663)) {
         blue = Float.intBitsToFloat(125998 ^ 24910 ^ 118454 ^ 1897826895 ^ 1558 ^ 98612 ^ 118271 ^ 1319011140);
         green = Float.intBitsToFloat('뙌' ^ 251913 ^ 'ꡰ' ^ 976611977 ^ 6901 ^ 233423 ^ 17444 ^ 95813026);
         red = Float.intBitsToFloat(5451 ^ '鎍' ^ 27040 ^ 885540361 ^ 113382 ^ 74186 ^ 86 ^ 189280789);
      } else {
         float q = (double)hsl[18613 ^ -11039 ^ 16521 ^ -8993] < Double.longBitsToDouble(770333151153680117L ^ 3841788097020358389L) ? hsl[18243 ^ -22753 ^ 3179 ^ -5067] * (Float.intBitsToFloat(25286 ^ '뫈' ^ 28266 ^ 1453138345 ^ 8294 ^ 217872 ^ 239313 ^ 1763525226) + hsl[23874 ^ -8891 ^ 5866 ^ -26900]) : hsl[11722 ^ -15847 ^ 11138 ^ -15277] + hsl[20244 ^ -17937 ^ 20205 ^ -18409] - hsl[26260 ^ -21522 ^ 13664 ^ -2024] * hsl[15208 ^ -11844 ^ 28011 ^ -30786];
         float p = Float.intBitsToFloat('뻰' ^ 13488 ^ 'ꚻ' ^ 641683013 ^ '웷' ^ 79881 ^ 29809 ^ 1715398705) * hsl[26534 ^ -19065 ^ 24739 ^ -19840] - q;
         red = hueToRGB(p, q, hsl[6556 ^ -7193 ^ 15342 ^ -15979] + Float.intBitsToFloat(4256 ^ 93347 ^ '듨' ^ -115544433 ^ 29081 ^ '\uf6d4' ^ 11405 ^ -944298225));
         green = hueToRGB(p, q, hsl[15324 ^ -22112 ^ 2473 ^ -25643]);
         blue = hueToRGB(p, q, hsl[2935 ^ -20775 ^ 16919 ^ -6215] - Float.intBitsToFloat('\ud90b' ^ '蓺' ^ 19467 ^ 501304151 ^ 504745 ^ 496344 ^ 20014 ^ 592158553));
      }

      return new Color((int)(red * Float.intBitsToFloat('ꆖ' ^ 1048489 ^ '갷' ^ -263731577 ^ 26208 ^ 83560 ^ '퀱' ^ -1288256330)), (int)(green * Float.intBitsToFloat(1361 ^ 1035924 ^ 1043114 ^ -1930968499 ^ 126525 ^ 129239 ^ 24115 ^ -812078085)), (int)(blue * Float.intBitsToFloat(11820 ^ '쭸' ^ '깍' ^ -546693806 ^ '\ue5bd' ^ 6192 ^ '\uec40' ^ -1676327034)));
   }

   public static Color averageColor(BufferedImage bi, int width, int height, int pixelStep) {
      int[] color = new int[23966 ^ -30013 ^ 10030 ^ -3984];

      int x;
      for(x = 27580 ^ -22457 ^ 3910 ^ -13123; x < width; x += pixelStep) {
         for(int y = 16735 ^ -13535 ^ 974 ^ -30288; y < height; y += pixelStep) {
            Color pixel = new Color(bi.getRGB(x, y));
            color[22707 ^ -6628 ^ 11957 ^ -28646] = color[12753 ^ -29131 ^ 23877 ^ -7519] + pixel.getRed();
            color[26440 ^ -1118 ^ 30928 ^ -7109] = color[21970 ^ -20518 ^ 24223 ^ -23402] + pixel.getGreen();
            color[2898 ^ -16749 ^ 30968 ^ -12997] = color[13580 ^ -5242 ^ 14162 ^ -5670] + pixel.getBlue();
         }
      }

      x = width * height / (pixelStep * pixelStep);
      return new Color(color[7999 ^ -6604 ^ 5349 ^ -4626] / x, color[15955 ^ -30950 ^ 12078 ^ -27034] / x, color[9496 ^ -922 ^ 32606 ^ -23006] / x);
   }

   public static Color[] getAnalogousColor(Color color) {
      Color[] colors = new Color[8844 ^ -29653 ^ 20828 ^ -7];
      float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), (float[])null);
      float degree = Float.intBitsToFloat(31186 ^ '髫' ^ 18172 ^ -1985271314 ^ 'ꩰ' ^ 122388 ^ 'ﻴ' ^ -1275024368);
      float newHueAdded = hsb[21559 ^ -14363 ^ 5976 ^ -31606] + degree;
      colors[6375 ^ -5066 ^ 12575 ^ -14898] = new Color(Color.HSBtoRGB(newHueAdded, hsb[1949 ^ -20809 ^ 29838 ^ -8795], hsb[25693 ^ -31030 ^ 28331 ^ -29634]));
      float newHueSubtracted = hsb[1764 ^ -1634 ^ 4922 ^ -5056] - degree;
      colors[7744 ^ -3797 ^ 12797 ^ -8553] = new Color(Color.HSBtoRGB(newHueSubtracted, hsb[27007 ^ -18313 ^ 30116 ^ -23379], hsb[27335 ^ -20689 ^ 12937 ^ -2205]));
      return colors;
   }

   public static Color interpolateColorHue(Color color1, Color color2, float amount) {
      amount = Math.min(Float.intBitsToFloat(128498 ^ 89768 ^ 549 ^ 1435139166 ^ 15994 ^ 94391 ^ 123266 ^ 1779071598), Math.max(Float.intBitsToFloat(259772 ^ 28661 ^ 258231 ^ 195398575 ^ 257620 ^ '雾' ^ 255884 ^ 195394935), amount));
      float[] color1HSB = Color.RGBtoHSB(color1.getRed(), color1.getGreen(), color1.getBlue(), (float[])null);
      float[] color2HSB = Color.RGBtoHSB(color2.getRed(), color2.getGreen(), color2.getBlue(), (float[])null);
      Color resultColor = Color.getHSBColor(0ei.interpolateFloat(color1HSB[13438 ^ -30603 ^ 18614 ^ -2883], color2HSB[9258 ^ -21774 ^ 20459 ^ -16077], (double)amount), 0ei.interpolateFloat(color1HSB[10694 ^ -31309 ^ 7679 ^ -20085], color2HSB[4821 ^ -15310 ^ 2258 ^ -8652], (double)amount), 0ei.interpolateFloat(color1HSB[8425 ^ -21245 ^ 32150 ^ -3970], color2HSB[27693 ^ -2849 ^ 19541 ^ -11099], (double)amount));
      return new Color(resultColor.getRed(), resultColor.getGreen(), resultColor.getBlue(), 0ei.interpolateInt(color1.getAlpha(), color2.getAlpha(), (double)amount));
   }

   public static Color interpolateColorC(Color color1, Color color2, float amount) {
      amount = Math.min(Float.intBitsToFloat(32328 ^ 20981 ^ 2187 ^ -1782505763 ^ '踤' ^ '빾' ^ 10796 ^ -1438571619), Math.max(Float.intBitsToFloat('ꚥ' ^ '釙' ^ 19936 ^ 88738368 ^ '\ue0b2' ^ '딣' ^ 2461 ^ 88747216), amount));
      return new Color(0ei.interpolateInt(color1.getRed(), color2.getRed(), (double)amount), 0ei.interpolateInt(color1.getGreen(), color2.getGreen(), (double)amount), 0ei.interpolateInt(color1.getBlue(), color2.getBlue(), (double)amount), 0ei.interpolateInt(color1.getAlpha(), color2.getAlpha(), (double)amount));
   }

   private static float getAnimationEquation(int index, int speed) {
      int angle = (int)((System.currentTimeMillis() / (long)speed + (long)index) % 360L);
      return (float)((angle > (29278 ^ -8809 ^ 22734 ^ -2125) ? (25475 ^ -21100 ^ 20719 ^ -24688) - angle : angle) + (19083 ^ -24442 ^ 24060 ^ -18619)) / Float.intBitsToFloat(14752 ^ 17471 ^ 4442 ^ 174109838 ^ 126436 ^ 76605 ^ 29014 ^ 1238659012);
   }

   public static float hueToRGB(float p, float q, float t) {
      float newT = t;
      if (newT < Float.intBitsToFloat(6198 ^ 17934 ^ 11744 ^ 1091223407 ^ 13459 ^ 82819 ^ 120701 ^ 1091202266)) {
         newT += Float.intBitsToFloat(15103 ^ '鋡' ^ '﹚' ^ -1027795803 ^ '\uec84' ^ '뭊' ^ 2969 ^ -46326090);
      }

      if (newT > Float.intBitsToFloat(10944 ^ 106029 ^ 109870 ^ -253703011 ^ 123553 ^ 13113 ^ 115043 ^ -815741531)) {
         newT -= Float.intBitsToFloat(18736 ^ 2082 ^ 19436 ^ -1987017204 ^ 12434 ^ 8124 ^ 9282 ^ -1240430690);
      }

      if (newT < Float.intBitsToFloat(24306 ^ 83168 ^ '漢' ^ 1737282391 ^ 17439 ^ '\uf117' ^ 5163 ^ 1504131210)) {
         return p + (q - p) * Float.intBitsToFloat(6162 ^ 29654 ^ 62 ^ 1233825563 ^ 120803 ^ 12904 ^ 123663 ^ 155894373) * newT;
      } else if (newT < Float.intBitsToFloat(522 ^ '뽍' ^ '\uf776' ^ -794220911 ^ '\udf87' ^ 68224 ^ 21850 ^ -274141955)) {
         return q;
      } else {
         return newT < Float.intBitsToFloat(32170 ^ 115234 ^ 32329 ^ 1256641696 ^ 21073 ^ '복' ^ 5722 ^ 1976387380) ? p + (q - p) * (Float.intBitsToFloat(20672 ^ 521184 ^ 27378 ^ -142357429 ^ 102065 ^ '鉎' ^ 121593 ^ -928093900) - newT) * Float.intBitsToFloat('闦' ^ 517638 ^ '\ude16' ^ -2099530156 ^ 31946 ^ 492951 ^ 17045 ^ -1038376854) : p;
      }
   }
}
