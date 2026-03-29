package neo;

import java.awt.Color;
import java.awt.Font;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import org.lwjgl.opengl.GL11;

public class 0eg extends 0ef {
   public float[] charWidthFloat = new float[2880 ^ -15385 ^ 10902 ^ -7375];
   protected 0ee[] boldItalicChars = new 0ee[6718 ^ -57585 ^ '鸏' ^ -24728];
   protected 0ee[] boldChars = new 0ee[27995 ^ -21899 ^ 9327 ^ -6377];
   protected 0ee[] italicChars = new 0ee[19063 ^ -5526 ^ 6190 ^ -17307];
   public final byte[] glyphWidth = new byte[6851 ^ 121949 ^ 4713 ^ '퓷'];
   public final int[] colorCode = new int[16873 ^ -3639 ^ 29754 ^ -15302];

   private static 0ee[] l4Ajb62S1G(0eg var0) {
      return var0.charData;
   }

   public void drawStringWithOutline(String text, double x, double y, int color) {
      this.drawString(text, x - Double.longBitsToDouble(839067991457240312L ^ 3766407749248062712L), y, f61Jb57JTB().getRGB(), (boolean)(27560 ^ -30112 ^ 15383 ^ -8737));
      this.drawString(text, x + Double.longBitsToDouble(-8720283699483274727L ^ -5108396798332136935L), y, tIPG4aPG4Y().getRGB(), (boolean)(13491 ^ -1063 ^ 22963 ^ -26919));
      this.drawString(text, x, y - Double.longBitsToDouble(-8503995570330320984L ^ -5324454233406750808L), gHNwDEk8BV().getRGB(), (boolean)(14337 ^ -1887 ^ 19908 ^ -29340));
      this.drawString(text, x, y + Double.longBitsToDouble(8950740080045148587L ^ 4888493216156961195L), quwqgDoB9p().getRGB(), (boolean)(3619 ^ -7704 ^ 6356 ^ -2273));
      this.drawString(text, x, y, color, (boolean)(7857 ^ -28534 ^ 8391 ^ -20740));
   }

   public float drawGradientString(String text, double x, double y, int[] gradientColors, boolean shadow) {
      try {
         if (text == null) {
            return Float.intBitsToFloat(9191 ^ 86661 ^ 115252 ^ -1179884481 ^ 102767 ^ 15356 ^ 111627 ^ -1179858447);
         }

         x -= Double.longBitsToDouble(8166551002963565963L ^ 5667053209772940683L);
         text = this.fixStr(text);
         GL11.glColor4f(Float.intBitsToFloat('贴' ^ 4183591 ^ '쒒' ^ 715110365 ^ 25343 ^ 18181 ^ 1871 ^ 354421481), Float.intBitsToFloat(12326 ^ 18323 ^ 4951 ^ 178888961 ^ 9460 ^ 244809 ^ 251448 ^ 891915110), Float.intBitsToFloat(243432 ^ 249453 ^ 1863 ^ 891912539 ^ 244276 ^ 240076 ^ 14411 ^ 178902314), Float.intBitsToFloat('限' ^ 221491 ^ 19040 ^ 137225534 ^ '龜' ^ 227180 ^ 14929 ^ 934145032));
         GlStateManager.color(Float.intBitsToFloat('슗' ^ 30488 ^ '퓲' ^ 1229270992 ^ 7728 ^ '褜' ^ '앉' ^ 1992629448), Float.intBitsToFloat(240709 ^ 20597 ^ 260090 ^ -1610768259 ^ 1892 ^ '뱖' ^ 20963 ^ -1602403994), Float.intBitsToFloat('\ue593' ^ 2042332 ^ 29059 ^ -1496326059 ^ '샬' ^ '뮂' ^ 40 ^ -1722800417), Float.intBitsToFloat(20844 ^ 518608 ^ 28816 ^ -2042378637 ^ 9071 ^ 492925 ^ 26800 ^ -1178353411));
         GL11.glEnable(14319 ^ 12836 ^ 1878 ^ 2431);
         if (text == null) {
            return Float.intBitsToFloat('\ue6b6' ^ '蛟' ^ 8581 ^ 460728361 ^ '츭' ^ '청' ^ 2179 ^ 460743622);
         }

         float alpha = (float)(gradientColors[11315 ^ -1289 ^ 18389 ^ -28399] >> (27156 ^ -15666 ^ 11282 ^ -31536) & (12442 ^ -1696 ^ 2068 ^ -16111)) / Float.intBitsToFloat(31987 ^ '蚟' ^ '\udf1b' ^ 1552488897 ^ '誯' ^ '针' ^ 13983 ^ 536220686);
         x *= Double.longBitsToDouble(1019083447385346084L ^ 5630769465812733988L);
         y = (y - Double.longBitsToDouble(4434413323355551391L ^ 9043847541969254047L)) * Double.longBitsToDouble(8395820199557972745L ^ 3784134181130584841L);
         GL11.glPushMatrix();
         GlStateManager.scale(Float.intBitsToFloat(109782 ^ 116203 ^ 24391 ^ -1202660067 ^ 106969 ^ '鹍' ^ 112693 ^ -2024772410), Float.intBitsToFloat(22158 ^ 126223 ^ 12385 ^ -825716995 ^ 9830 ^ 104442 ^ 20853 ^ -238492172), Float.intBitsToFloat(1037195 ^ 1024470 ^ 10397 ^ -770201991 ^ 1022298 ^ 1047576 ^ 11590 ^ -317213507));
         GlStateManager.enableBlend();
         GlStateManager.blendFunc(9223 ^ -14434 ^ 30378 ^ -27087, 1769 ^ -7761 ^ 8660 ^ -14959);
         GlStateManager.enableTexture2D();
         GlStateManager.bindTexture(VLMtl93aiL(this).getGlTextureId());
         GL11.glBindTexture(18414 ^ -30527 ^ 10107 ^ -6731, e2JyLudjph(this).getGlTextureId());
         BDabDVyfCS(this).begin(32253 ^ -29514 ^ 18772 ^ -18405, 7AYvjuLNT1());

         for(int i = 4589 ^ -30774 ^ 20270 ^ -9975; i < text.length(); ++i) {
            char character = text.charAt(i);
            int colorIndex;
            int colorIndex;
            if (String.valueOf(character).equals(BVOsiAOl9G("֓"))) {
               colorIndex = text.charAt(i + (19587 ^ -12142 ^ 4624 ^ -29184));
               colorIndex = BVOsiAOl9G("ԄԅԆԇԀԁԂԃԌԍՕՖ\u0557ՐՑՒ՟\u0558ՙ՚՛Ն").indexOf(colorIndex);
               if (colorIndex == (18182 ^ -6728 ^ 12666 ^ -27722)) {
                  GlStateManager.color((float)(gradientColors[9290 ^ -20241 ^ 28316 ^ -1479] >> (12142 ^ -26404 ^ 13019 ^ -31367) & (12756 ^ -14195 ^ 5683 ^ -4203)) / Float.intBitsToFloat('Ꚗ' ^ '\ue827' ^ 25163 ^ 31540251 ^ '\ud95a' ^ '\uef12' ^ 14970 ^ 1117676755), (float)(gradientColors[14712 ^ -12069 ^ 18162 ^ -20655] >> (4694 ^ -5167 ^ 29212 ^ -29805) & (4848 ^ -14907 ^ 9649 ^ -3461)) / Float.intBitsToFloat('\ueb03' ^ 10761 ^ '\uda72' ^ 1437550978 ^ '\ue4a3' ^ '鋤' ^ 25752 ^ 382746661), (float)(gradientColors[16342 ^ -24559 ^ 24270 ^ -16119] & (23046 ^ -9382 ^ 6355 ^ -26256)) / Float.intBitsToFloat(103108 ^ 100353 ^ 11482 ^ 206096108 ^ 3846 ^ 1048188 ^ 1043109 ^ 1329064748), alpha);
               } else if (colorIndex < (20617 ^ -22658 ^ 31568 ^ -29513)) {
                  GlStateManager.bindTexture(2T94Vr0N6n(this).getGlTextureId());
                  if (colorIndex < 0) {
                     colorIndex = 30129 ^ -2501 ^ 19940 ^ -12703;
                  }

                  if (shadow) {
                     colorIndex += 16;
                  }

                  int colorcode = i7B2yubk2G(this)[colorIndex];
                  GlStateManager.color((float)(colorcode >> (6355 ^ -5683 ^ 1989 ^ -2357) & (31261 ^ -3600 ^ 25214 ^ -5780)) / Float.intBitsToFloat(24407 ^ 31375 ^ 15151 ^ -1391719551 ^ 28116 ^ 118346 ^ 24425 ^ -294459007), (float)(colorcode >> (314 ^ -5612 ^ 17936 ^ -21194) & (1748 ^ -15119 ^ 12492 ^ -3562)) / Float.intBitsToFloat(4648 ^ '騕' ^ 15657 ^ 1168781625 ^ 8946 ^ '\udbcc' ^ 12842 ^ 114642745), (float)(colorcode & (21551 ^ -20781 ^ 26566 ^ -25147)) / Float.intBitsToFloat('쾣' ^ '蜙' ^ 9759 ^ 601502216 ^ '\uf877' ^ '\ue5c1' ^ 5073 ^ 1621447370), alpha);
               }

               ++i;
            } else if (character < grD1g6UAKT(this).length) {
               colorIndex = Math.min(i, gradientColors.length - (8772 ^ -22202 ^ 4242 ^ -25711));
               colorIndex = gradientColors[colorIndex];
               float charAlpha = (float)(colorIndex >> (8825 ^ -27764 ^ 22833 ^ -5924) & (25897 ^ -4852 ^ 18588 ^ -16314)) / Float.intBitsToFloat(32418 ^ '뾻' ^ 10598 ^ 1160144394 ^ 27932 ^ 'ꮮ' ^ 9782 ^ 106524401);
               GlStateManager.color((float)(colorIndex >> (20806 ^ -31689 ^ 28282 ^ -17637) & (9207 ^ -32387 ^ 11386 ^ -29169)) / Float.intBitsToFloat(5588 ^ '\ue857' ^ 14602 ^ -1649391236 ^ 28250 ^ '鴜' ^ 11266 ^ -556838223), (float)(colorIndex >> (20522 ^ -28748 ^ 9157 ^ -941) & (16198 ^ -27131 ^ 1506 ^ -21410)) / Float.intBitsToFloat(509653 ^ 515763 ^ 7254 ^ -221517439 ^ 111832 ^ 121127 ^ 20255 ^ -1313550511), (float)(colorIndex & ('耠' ^ -18110 ^ '\ue889' ^ -12012)) / Float.intBitsToFloat(230676 ^ '\ue840' ^ 240389 ^ 186470914 ^ 9244 ^ '뢆' ^ 26536 ^ 1214410593), charAlpha);
               this.drawChar(99sbc2yi2Y(this), character, (float)x, (float)y);
               x += (double)(wDfI2jByc4(uVnh8INDwu(this)[character]) - (15792 ^ -13985 ^ 28447 ^ -25608) + wvcSTlD49t(this));
            }
         }

         NOJIQ14CJB(this).draw();
         GL11.glHint(5019 ^ -19509 ^ 31993 ^ -12038, 27636 ^ -19480 ^ 12086 ^ -6614);
         GL11.glPopMatrix();
      } catch (Exception var14) {
         Exception e = var14;
         e.printStackTrace();
      }

      return (float)x / Float.intBitsToFloat(130636 ^ 86188 ^ 757 ^ -1604816084 ^ 8389 ^ 87170 ^ 128205 ^ -531079245);
   }

   private static Color tIPG4aPG4Y() {
      return Color.BLACK;
   }

   public float drawString(String text, float x, float y, int color) {
      return this.drawString(text, (double)x, (double)y, color, (boolean)(4222 ^ -2822 ^ 16312 ^ -9412));
   }

   private static 0ee[] QnggwA3qPy(0eg var0) {
      return var0.charData;
   }

   private static float[] U7ePmOBdIy(0eg var0) {
      return var0.charWidthFloat;
   }

   private static Color i0CiU211nd() {
      return Color.BLACK;
   }

   private static float[] warJgbuEVf(0eg var0) {
      return var0.charWidthFloat;
   }

   private static Color gHNwDEk8BV() {
      return Color.BLACK;
   }

   private static Color OQo9vdw2xq() {
      return Color.BLACK;
   }

   private static int wvcSTlD49t(0eg var0) {
      return var0.charOffset;
   }

   private static VertexFormat _AYvjuLNT1/* $FF was: 7AYvjuLNT1*/() {
      return DefaultVertexFormats.POSITION_TEX;
   }

   public float drawString(String text, double x, double y, int color, boolean shadow) {
      try {
         if (text == null) {
            return Float.intBitsToFloat(21359 ^ 512583 ^ 16401 ^ 1941685358 ^ 6290 ^ 495355 ^ 20931 ^ 1941684989);
         }

         x -= Double.longBitsToDouble(4558935116261261516L ^ 50831889263395020L);
         text = this.fixStr(text);
         GL11.glColor4f(Float.intBitsToFloat('랷' ^ 'ꕈ' ^ 211 ^ -909234743 ^ '谲' ^ 215938 ^ 11101 ^ -162671864), Float.intBitsToFloat('\udc6c' ^ 26039 ^ '픝' ^ 1179131460 ^ 'ﱍ' ^ 17263 ^ '\uf87d' ^ 2043164125), Float.intBitsToFloat('씵' ^ 13558 ^ '\ue321' ^ -1710078399 ^ 13004 ^ 233302 ^ 1893 ^ -1517162916), Float.intBitsToFloat('뺥' ^ 243424 ^ '\ue084' ^ -2087860188 ^ 24288 ^ 213726 ^ '\ue76c' ^ -1139942473));
         GlStateManager.color(Float.intBitsToFloat('뭰' ^ '껳' ^ 2859 ^ 217344686 ^ '\uebe3' ^ 'ｌ' ^ 27751 ^ 863243470), Float.intBitsToFloat(256860 ^ 12009 ^ 258716 ^ -796798016 ^ 262130 ^ 23564 ^ 255178 ^ -285105187), Float.intBitsToFloat(123069 ^ 85324 ^ 11587 ^ -38649121 ^ 126521 ^ '裄' ^ 130568 ^ -1036887400), Float.intBitsToFloat(1179 ^ 'ꖊ' ^ '蜉' ^ -976200316 ^ '쒬' ^ '늺' ^ 25811 ^ -95393447));
         GL11.glEnable('脚' ^ -9234 ^ '쾣' ^ -24907);
         if (text == null) {
            return Float.intBitsToFloat(101798 ^ 31001 ^ 125397 ^ -1932744798 ^ 25548 ^ 97021 ^ 119148 ^ -1932758379);
         }

         if (color == (33547970 ^ 33541553 ^ 21264 ^ 553617308)) {
            color = 33548043 ^ 33524992 ^ 2606 ^ 16752602;
         }

         if ((color & (20669 ^ 134200733 ^ 4286 ^ -67109474)) == 0) {
            color |= 4224 ^ 33526393 ^ 14545 ^ -16795096;
         }

         if (shadow) {
            color = (color & (115450 ^ 111929 ^ 6741 ^ 16552298)) >> (21001 ^ -23406 ^ 2091 ^ -334) | color & (new Color(15479 ^ -18923 ^ 24346 ^ -10900, 27686 ^ -31069 ^ 19371 ^ -24262, 12341 ^ -30454 ^ 27866 ^ -10767, 14007 ^ -3162 ^ 14400 ^ -615)).getRGB();
         }

         float alpha = (float)(color >> (30939 ^ -14650 ^ 21785 ^ -5348) & (7874 ^ -344 ^ 14832 ^ -9883)) / Float.intBitsToFloat(16467 ^ 77259 ^ '\ud7fc' ^ 1642376054 ^ 3117 ^ 234192 ^ 10035 ^ 580495580);
         x *= Double.longBitsToDouble(117565624284170190L ^ 4729251642711558094L);
         y = (y - Double.longBitsToDouble(-8824338793603855661L ^ -4214904574990153005L)) * Double.longBitsToDouble(80857928469091835L ^ 4692543946896479739L);
         GL11.glPushMatrix();
         GlStateManager.scale(Float.intBitsToFloat('趂' ^ '錙' ^ 27075 ^ 1826954119 ^ '\udfac' ^ '闠' ^ 15147 ^ 1407524280), Float.intBitsToFloat('髢' ^ 474621 ^ 3052 ^ 277590968 ^ '類' ^ 517165 ^ '띔' ^ 797693036), Float.intBitsToFloat(8747 ^ '韓' ^ 'ꁝ' ^ -366866734 ^ 11701 ^ 244108 ^ 30312 ^ -719193818));
         GlStateManager.enableBlend();
         GlStateManager.blendFunc(19123 ^ -22592 ^ 25983 ^ -29938, 25408 ^ -6908 ^ 8239 ^ -23192);
         GlStateManager.color((float)(color >> (19699 ^ -19927 ^ 29840 ^ -30118) & ('耆' ^ -28961 ^ '壘' ^ -2196)) / Float.intBitsToFloat(27208 ^ '芴' ^ 16343 ^ 747661941 ^ 2073946 ^ 3663 ^ 2074431 ^ 1877979508), (float)(color >> (13909 ^ -2703 ^ 11721 ^ -4379) & (31096 ^ -26873 ^ 21012 ^ -17260)) / Float.intBitsToFloat(247050 ^ 202339 ^ 16785 ^ 730422722 ^ 101377 ^ 104371 ^ 19330 ^ 1760993034), (float)(color & (15408 ^ -5659 ^ 29155 ^ -23351)) / Float.intBitsToFloat(118477 ^ 83482 ^ 2521 ^ -1990567144 ^ 257916 ^ 212091 ^ 25460 ^ -903517595), alpha);
         GlStateManager.enableTexture2D();
         GlStateManager.bindTexture(uLoXlbcvG3(this).getGlTextureId());
         GL11.glBindTexture(30700 ^ 13200 ^ 18508 ^ 465, T6izACswEr(this).getGlTextureId());
         2nn6d1ZqSJ(this).begin(32331 ^ -6449 ^ 9420 ^ -17332, zSjQthYq0X());

         for(int i = 12184 ^ -29482 ^ 29995 ^ -10651; i < text.length(); ++i) {
            char character = text.charAt(i);
            if (String.valueOf(character).equals(BVOsiAOl9G("֓"))) {
               char next = text.charAt(i + (29172 ^ -27470 ^ 4922 ^ -2435));
               int colorIndex = BVOsiAOl9G("ԄԅԆԇԀԁԂԃԌԍՕՖ\u0557ՐՑՒ՟\u0558ՙ՚՛Ն").indexOf(next);
               if (next == (19290 ^ -26766 ^ 20902 ^ -29188)) {
                  GlStateManager.color((float)(color >> (27571 ^ -25146 ^ 13748 ^ -15407) & (8661 ^ -8545 ^ 1146 ^ -1073)) / Float.intBitsToFloat(20858 ^ 86487 ^ '셕' ^ -234561927 ^ '逘' ^ 234474 ^ '흑' ^ -1317418206), (float)(color >> (24569 ^ -25235 ^ 19593 ^ -29163) & (6874 ^ -8536 ^ 22155 ^ -28154)) / Float.intBitsToFloat(12558 ^ '\udf7b' ^ 20232 ^ 1570514752 ^ 31515 ^ '阋' ^ 7310 ^ 518223779), (float)(color & (27510 ^ -31053 ^ 22008 ^ -18238)) / Float.intBitsToFloat(1888 ^ 489906 ^ 499527 ^ 49510419 ^ 102097 ^ 67990 ^ 989 ^ 1099701532), alpha);
               } else if (colorIndex < (23891 ^ -11675 ^ 32462 ^ -3608)) {
                  GlStateManager.bindTexture(5lo3OwBFaY(this).getGlTextureId());
                  if (colorIndex < 0) {
                     colorIndex = 11145 ^ -23718 ^ 25316 ^ -5576;
                  }

                  if (shadow) {
                     colorIndex += 16;
                  }

                  int colorcode = UkqVn2Tj5a(this)[colorIndex];
                  GlStateManager.color((float)(colorcode >> (3584 ^ -26454 ^ 10805 ^ -17265) & (26957 ^ -27833 ^ 25002 ^ -25761)) / Float.intBitsToFloat(29495 ^ 16815 ^ 5823 ^ -929329765 ^ 6877 ^ 89602 ^ 106947 ^ -1947972448), (float)(colorcode >> (1704 ^ -9153 ^ 32142 ^ -22767) & (23565 ^ 32221 ^ 8534 ^ 121)) / Float.intBitsToFloat(5059 ^ 2068330 ^ 2085717 ^ 1676640580 ^ 7283 ^ 31782 ^ 9415 ^ 546343466), (float)(colorcode & (11143 ^ -28281 ^ 17655 ^ -504)) / Float.intBitsToFloat(32454 ^ 92019 ^ '쳋' ^ -1292832516 ^ 950 ^ 26764 ^ 19554 ^ -242351398), alpha);
               }

               ++i;
            } else if (character < 6FHvjiLRs8(this).length) {
               this.drawChar(WEncqqNQq6(this), character, (float)x, (float)y);
               x += (double)(gQqlPofBAi(wyAYRwJ6dS(this)[character]) - (10686 ^ -12122 ^ 26457 ^ -25015) + xOUliG5w1A(this));
            }
         }

         60LSRLSeni(this).draw();
         GL11.glHint(6317 ^ -51430 ^ '鴁' ^ -16667, '鎜' ^ 9517 ^ 'ꍮ' ^ 1247);
         GL11.glPopMatrix();
      } catch (Exception var14) {
         Exception e = var14;
         e.printStackTrace();
      }

      return (float)x / Float.intBitsToFloat(110137 ^ 16659 ^ 125899 ^ 664056610 ^ 3549 ^ 109804 ^ 100765 ^ 1737793391);
   }

   public void setFractionalMetrics(boolean fractionalMetrics) {
      super.setFractionalMetrics(fractionalMetrics);
   }

   private static Tessellator _0LSRLSeni/* $FF was: 60LSRLSeni*/(0eg var0) {
      return var0.tessellator;
   }

   private static byte[] RjlYQ62f90(0eg var0) {
      return var0.glyphWidth;
   }

   public _eg/* $FF was: 0eg*/(Font font, boolean antiAlias, boolean fractionalMetrics) {
      super(font, antiAlias, fractionalMetrics);

      for(int index = 7570 ^ -11606 ^ 27404 ^ -23500; index < (9686 ^ -10818 ^ 13585 ^ -15015); ++index) {
         int noClue = (index >> (8828 ^ -11191 ^ 14203 ^ -16051) & (5466 ^ -23657 ^ 32551 ^ -13845)) * (11414 ^ -14439 ^ 23246 ^ -20076);
         int red = (index >> (4201 ^ -1801 ^ 16701 ^ -22111) & (25087 ^ -25288 ^ 266 ^ -564)) * (18408 ^ -31772 ^ 5908 ^ -11342) + noClue;
         int green = (index >> (31107 ^ -17028 ^ 22937 ^ -25241) & (10314 ^ -15967 ^ 25303 ^ -29891)) * (24875 ^ -2446 ^ 29664 ^ -7149) + noClue;
         int blue = (index & (4242 ^ -20718 ^ 28281 ^ -11784)) * (12293 ^ -20976 ^ 278 ^ -24663) + noClue;
         if (index == (9165 ^ -13617 ^ 18035 ^ -20617)) {
            red += 85;
         }

         if (index >= (31060 ^ -12204 ^ 9266 ^ -29406)) {
            red /= 24787 ^ -30203 ^ 9217 ^ -12589;
            green /= 9007 ^ -248 ^ 25096 ^ -16853;
            blue /= 21397 ^ -16663 ^ 17533 ^ -22267;
         }

         this.colorCode[index] = (red & (21832 ^ -4280 ^ 20375 ^ -2712)) << (5079 ^ -18811 ^ 2972 ^ -20770) | (green & (1455 ^ -22905 ^ 4853 ^ -20190)) << (14018 ^ -27920 ^ 32124 ^ -9914) | blue & (21505 ^ -12060 ^ 15209 ^ -16525);
      }

   }

   public float drawCenteredStringWithShadow(String text, float x, float y, int color) {
      return this.drawStringWithShadow(text, (double)(x - (float)this.getStringWidth(text) / Float.intBitsToFloat(19391 ^ 519372 ^ 19381 ^ 1813248321 ^ 31173 ^ '葂' ^ '\ue9ad' ^ 739508653)), (double)y, color);
   }

   public void setFont(Font font) {
      super.setFont(font);
   }

   public String fixStr(String input) {
      StringBuilder builder = new StringBuilder();
      char[] buffer = input.toCharArray();
      char[] var4 = buffer;
      int var5 = buffer.length;

      for(int var6 = 39 ^ -8007 ^ 10612 ^ -13846; var6 < var5; ++var6) {
         char c = var4[var6];
         if (c < l4Ajb62S1G(this).length && QnggwA3qPy(this)[c] != null) {
            builder.append(c);
         }
      }

      return builder.toString();
   }

   private static 0ee[] _FHvjiLRs8/* $FF was: 6FHvjiLRs8*/(0eg var0) {
      return var0.charData;
   }

   private static Color Qjdr8vdzqo() {
      return Color.BLACK;
   }

   public float drawStringWithShadow(String text, double x, double y, int color) {
      float shadowWidth = this.drawString(text, x + Double.longBitsToDouble(8873208699621691355L ^ 4955077023809359835L), y + Double.longBitsToDouble(-1002029420677781343L ^ -3605110005297928031L), color, (boolean)(2629 ^ -24043 ^ 22480 ^ -127));
      return Math.max(shadowWidth, this.drawString(text, x, y, color, (boolean)(14415 ^ -8473 ^ 9438 ^ -15754)));
   }

   private static Color GJRyoYMZfU() {
      return Color.BLACK;
   }

   public void setAntiAlias(boolean antiAlias) {
      super.setAntiAlias(antiAlias);
   }

   public static void drawStringWithOutline(0eg fontRenderer, String text, float x, float y, int color) {
      fontRenderer.drawString(text, x - Float.intBitsToFloat(8940 ^ 120495 ^ 826 ^ 737078532 ^ 511177 ^ '臺' ^ 509230 ^ 346250925), y, 7LljVa7FYr().getRGB());
      fontRenderer.drawString(text, x + Float.intBitsToFloat(122486 ^ 15142 ^ 130505 ^ 1070255549 ^ 19683 ^ 246913 ^ 30388 ^ 8775487), y, OQo9vdw2xq().getRGB());
      fontRenderer.drawString(text, x, y - Float.intBitsToFloat(18874 ^ '閕' ^ 28544 ^ 463610065 ^ '툞' ^ 229624 ^ '\ue300' ^ 619572821), GJRyoYMZfU().getRGB());
      fontRenderer.drawString(text, x, y + Float.intBitsToFloat(16865 ^ '阩' ^ '\ueca8' ^ 1010703114 ^ 108910 ^ 130206 ^ 32480 ^ 57853879), NZQjwLo2J2().getRGB());
      fontRenderer.drawString(text, x, y, color);
   }

   private static 0ee[] wyAYRwJ6dS(0eg var0) {
      return var0.charData;
   }

   private static 0ee[] _9sbc2yi2Y/* $FF was: 99sbc2yi2Y*/(0eg var0) {
      return var0.charData;
   }

   public float drawCenteredString(String text, float x, float y, int color, boolean shadow) {
      return this.drawString(text, (double)(x - (float)this.getStringWidth(text) / Float.intBitsToFloat(110028 ^ '騗' ^ 113116 ^ -668831949 ^ 130138 ^ 117691 ^ 7337 ^ -1742550404)), (double)y, color, shadow);
   }

   private static BufferBuilder _nn6d1ZqSJ/* $FF was: 2nn6d1ZqSJ*/(0eg var0) {
      return var0.bufferbuilder;
   }

   private static Color I4Y9HcD1X7() {
      return Color.BLACK;
   }

   private static 0ee[] uVnh8INDwu(0eg var0) {
      return var0.charData;
   }

   private static 0ee[] WEncqqNQq6(0eg var0) {
      return var0.charData;
   }

   private static Color _k1SJFoxkT/* $FF was: 8k1SJFoxkT*/() {
      return Color.BLACK;
   }

   private static VertexFormat zSjQthYq0X() {
      return DefaultVertexFormats.POSITION_TEX;
   }

   public String trimStringToWidth(String text, int width) {
      return this.trimStringToWidth(text, width, (boolean)(32151 ^ -13807 ^ 24722 ^ -10476));
   }

   private static Color quwqgDoB9p() {
      return Color.BLACK;
   }

   private static DynamicTexture VLMtl93aiL(0eg var0) {
      return var0.tex;
   }

   private static Color kRYSO0nsey() {
      return Color.BLACK;
   }

   private static 0ee[] grD1g6UAKT(0eg var0) {
      return var0.charData;
   }

   public float drawCenteredString(String text, float x, float y, int color) {
      return this.drawString(text, x - (float)this.getStringWidth(text) / Float.intBitsToFloat('ﭠ' ^ '됩' ^ 5559 ^ -185778307 ^ '螜' ^ 22707 ^ '酛' ^ -1259525129), y, color);
   }

   private static Color _LljVa7FYr/* $FF was: 7LljVa7FYr*/() {
      return Color.BLACK;
   }

   public void drawCenteredStringWithOutline(String text, float x, float y, int color) {
      this.drawCenteredString(text, x - Float.intBitsToFloat(19915 ^ 29942 ^ 9132 ^ -1137276939 ^ 11972 ^ 81537 ^ 112669 ^ -2093599428), y, 8k1SJFoxkT().getRGB());
      this.drawCenteredString(text, x + Float.intBitsToFloat('\uf6b1' ^ '뙨' ^ 22832 ^ 1127640217 ^ '눪' ^ 29650 ^ '내' ^ 2083914812), y, 2IL7Mc1inN().getRGB());
      this.drawCenteredString(text, x, y - Float.intBitsToFloat(13950 ^ '곰' ^ '쓛' ^ 1395072360 ^ '툮' ^ 'ꗬ' ^ 5783 ^ 1814504040), kRYSO0nsey().getRGB());
      this.drawCenteredString(text, x, y + Float.intBitsToFloat('鸮' ^ 1035711 ^ '퓭' ^ -409494225 ^ 1371 ^ 1036658 ^ 15588 ^ -661130594), 3nJdNlGHru().getRGB());
      this.drawCenteredString(text, x, y, color);
   }

   private static Color _IL7Mc1inN/* $FF was: 2IL7Mc1inN*/() {
      return Color.BLACK;
   }

   private static DynamicTexture T6izACswEr(0eg var0) {
      return var0.tex;
   }

   private static DynamicTexture e2JyLudjph(0eg var0) {
      return var0.tex;
   }

   private static DynamicTexture _T94Vr0N6n/* $FF was: 2T94Vr0N6n*/(0eg var0) {
      return var0.tex;
   }

   private static Color NZQjwLo2J2() {
      return Color.BLACK;
   }

   public void drawGradientThemeString(String text, float x, float y) {
      int offset = 2696 ^ -16988 ^ 18101 ^ -3687;
      char[] var5 = text.toCharArray();
      int var6 = var5.length;

      for(int var7 = 15630 ^ -8990 ^ 10193 ^ -14787; var7 < var6; ++var7) {
         char char1 = var5[var7];
         this.drawString(String.valueOf(char1), x + (float)offset, y, 0br.getC(offset).getRGB());
         offset += this.getStringWidth(String.valueOf(char1));
      }

   }

   private static Color _nJdNlGHru/* $FF was: 3nJdNlGHru*/() {
      return Color.BLACK;
   }

   private static Color _ZMrgzelQT/* $FF was: 6ZMrgzelQT*/() {
      return Color.BLACK;
   }

   private float getCharWidthFloat(char p_getCharWidthFloat_1_) {
      if (p_getCharWidthFloat_1_ == (21268 ^ -21707 ^ 2199 ^ -4079)) {
         return Float.intBitsToFloat('迀' ^ '쿳' ^ 13061 ^ 208673327 ^ '蹁' ^ '騚' ^ 30871 ^ -1276115499);
      } else if (p_getCharWidthFloat_1_ != (11865 ^ -3891 ^ 8795 ^ -785) && p_getCharWidthFloat_1_ != (32216 ^ -25238 ^ 25836 ^ -31490)) {
         int i = BVOsiAOl9G("״\u05f5\u05f6\u05fc\u05fe\u05ff\u05f9קנס\u05ee\u05ebחׁЫЄЅѦѧѪѫрсъܳԴԴԴԴԴԴԴԔԕԖԗԐԑԒԓԜԝԞԟԘԙԚԛԄԅԆԇԀԁԂԃԌԍԎԏԈԉԊԋմյնշհձղճռսվտոչպջդեզէՠաբգլխծկըթժիՔՕՖ\u0557ՐՑՒՓ՜՝՞՟\u0558ՙ՚՛ՄՅՆՇՀՁՂՃՌՍՎՏՈՉՊԴ׳\u05c8םזאהבדמןלכךטװױ\u05fdגײ׀ׂ׆\u05cf\u05cd\u05cbער\u05cc֗\u05ecףҦויׇ\u05ceׅץ֞֎\u058b֚֘։ֈ֕֟֏₥₦₧‶‐⁕⁖\u2062\u2061⁗\u2065\u2063\u2069\u2068\u206f․† ‘\u2028‴ \u206a\u206b\u206e\u2060⁝⁒⁔\u2064⁘⁓⁜⁐⁑\u206d\u206c\u2066\u2067 ⁞\u202c‸₼₰₸₤₴څچڧ۴ڗ۷ڈ۰ڒڬڝڀ✪✱✼✝❕օ❑❐☔☕׃❼ք✭փ✮╋ֆₔԴ").indexOf(p_getCharWidthFloat_1_);
         if (p_getCharWidthFloat_1_ > 0 && i != (-31539 ^ -13727 ^ 18966 ^ -1211)) {
            return warJgbuEVf(this)[i];
         } else if (5AF12AyGZG(this)[p_getCharWidthFloat_1_] != 0) {
            int j = RjlYQ62f90(this)[p_getCharWidthFloat_1_] & (32458 ^ -31871 ^ 9136 ^ -8700);
            int k = j >>> (5929 ^ -7308 ^ 26457 ^ -27904);
            int l = j & (9655 ^ -24321 ^ 7927 ^ -25680);
            ++l;
            return (float)((l - k) / (26176 ^ -12207 ^ 27895 ^ -9500) + (32092 ^ -22862 ^ 25249 ^ -18098));
         } else {
            return Float.intBitsToFloat(4673 ^ 32138 ^ 25923 ^ -2096912949 ^ 29016 ^ '\udf28' ^ 14860 ^ -2096940225);
         }
      } else {
         return U7ePmOBdIy(this)[9541 ^ -22444 ^ 5113 ^ -24888];
      }
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String BVOsiAOl9G(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 25682 ^ -9228 ^ 3330 ^ -19804; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 26116 ^ -20376 ^ 4620 ^ -16044));
      }

      return var1.toString();
   }

   private static DynamicTexture uLoXlbcvG3(0eg var0) {
      return var0.tex;
   }

   private static 0ee[] vEOEUjhgKw(0eg var0) {
      return var0.charData;
   }

   private static int[] UkqVn2Tj5a(0eg var0) {
      return var0.colorCode;
   }

   private static int xOUliG5w1A(0eg var0) {
      return var0.charOffset;
   }

   private static int gQqlPofBAi(0ee var0) {
      return var0.width;
   }

   private static DynamicTexture _lo3OwBFaY/* $FF was: 5lo3OwBFaY*/(0eg var0) {
      return var0.tex;
   }

   private static Color f61Jb57JTB() {
      return Color.BLACK;
   }

   private static BufferBuilder BDabDVyfCS(0eg var0) {
      return var0.bufferbuilder;
   }

   private static int[] i7B2yubk2G(0eg var0) {
      return var0.colorCode;
   }

   public void drawCenteredGradientThemeString(String text, float x, float y) {
      this.drawGradientThemeString(text, x - (float)this.getStringWidth(text) / Float.intBitsToFloat(32082 ^ 107018 ^ 3065 ^ -1430196504 ^ '韺' ^ 104709 ^ '\ud85b' ^ -356455187), y);
   }

   private static int l3D9HOpJSn(0eg var0) {
      return var0.charOffset;
   }

   private static 0ee[] GIFVJPgeWq(0eg var0) {
      return var0.charData;
   }

   public String trimStringToWidth(String text, int width, boolean reverse) {
      StringBuilder stringbuilder = new StringBuilder();
      float f = Float.intBitsToFloat(98876 ^ '\ue42f' ^ 107023 ^ 695093083 ^ 19900 ^ '\uf439' ^ 28270 ^ 695096492);
      int i = reverse ? text.length() - (9294 ^ -32733 ^ 32347 ^ -9673) : 3542 ^ -24894 ^ 11004 ^ -17944;
      int j = reverse ? -177 ^ -18927 ^ 13113 ^ -31336 : 18929 ^ -23650 ^ 15877 ^ -11157;
      boolean flag = 20717 ^ -8414 ^ 26551 ^ -6024;
      boolean flag1 = 21749 ^ -17149 ^ 5904 ^ -282;

      for(int k = i; k >= 0 && k < text.length() && f < (float)width; k += j) {
         char c0 = text.charAt(k);
         float f1 = this.getCharWidthFloat(c0);
         if (flag != 0) {
            flag = 12254 ^ -20226 ^ 27280 ^ -2640;
            if (c0 != (9108 ^ -30609 ^ 26838 ^ -15551) && c0 != (4539 ^ -18349 ^ 21745 ^ -683)) {
               if (c0 == (1414 ^ -20907 ^ 134 ^ -21721) || c0 == (17958 ^ -10362 ^ 29953 ^ -6925)) {
                  flag1 = 15803 ^ -3725 ^ 29729 ^ -18199;
               }
            } else {
               flag1 = 12369 ^ -21759 ^ 14351 ^ -23714;
            }
         } else if (f1 < Float.intBitsToFloat(116063 ^ 120685 ^ 1853 ^ -1103442431 ^ 119537 ^ 101108 ^ 7250 ^ -1103454375)) {
            flag = 16068 ^ -21523 ^ 30515 ^ -7653;
         } else {
            f += f1;
            if (flag1 != 0) {
               f += Float.intBitsToFloat(1424 ^ '蹑' ^ '뺑' ^ -1131735143 ^ 25284 ^ 18673 ^ 6077 ^ -2096423103);
            }
         }

         if (f > (float)width) {
            break;
         }

         if (reverse) {
            stringbuilder.insert(32491 ^ -5062 ^ 6387 ^ -30174, c0);
         } else {
            stringbuilder.append(c0);
         }
      }

      return stringbuilder.toString();
   }

   private static int b4atuf31uO(0ee var0) {
      return var0.width;
   }

   public void drawCenteredStringWithOutline(0eg fontRenderer, String text, float x, float y, int color) {
      this.drawCenteredString(text, x - Float.intBitsToFloat(16498 ^ 243212 ^ 2205 ^ -889335534 ^ '꭪' ^ '虠' ^ 9799 ^ -176282436), y, I4Y9HcD1X7().getRGB());
      this.drawCenteredString(text, x + Float.intBitsToFloat(4590 ^ 241049 ^ 243050 ^ 117547185 ^ 32628 ^ 228838 ^ 247962 ^ 948003748), y, i0CiU211nd().getRGB());
      this.drawCenteredString(text, x, y - Float.intBitsToFloat(14955 ^ 125989 ^ 121558 ^ 2071117869 ^ 13316 ^ 18264 ^ 3021 ^ 1156756516), Qjdr8vdzqo().getRGB());
      this.drawCenteredString(text, x, y + Float.intBitsToFloat(21751 ^ '휢' ^ 20234 ^ -289904540 ^ 29989 ^ '勒' ^ 913 ^ -784848035), 6ZMrgzelQT().getRGB());
      this.drawCenteredString(text, x, y, color);
   }

   private static Tessellator NOJIQ14CJB(0eg var0) {
      return var0.tessellator;
   }

   private void drawLine(double x, double y, double x1, double y1, float width) {
      GL11.glDisable(5621 ^ -17637 ^ 30857 ^ -9338);
      GL11.glLineWidth(width);
      GL11.glBegin(32561 ^ -20121 ^ 22969 ^ -26642);
      GL11.glVertex2d(x, y);
      GL11.glVertex2d(x1, y1);
      GL11.glEnd();
      GL11.glEnable(23651 ^ -32549 ^ 1877 ^ -10740);
   }

   private static int wDfI2jByc4(0ee var0) {
      return var0.width;
   }

   private static byte[] _AF12AyGZG/* $FF was: 5AF12AyGZG*/(0eg var0) {
      return var0.glyphWidth;
   }

   public int getStringWidth(String text) {
      int x = 18601 ^ -1560 ^ 13157 ^ -32220;
      if (text == null) {
         return 16274 ^ -31702 ^ 10969 ^ -28319;
      } else {
         text = this.fixStr(text);

         for(int i = 20028 ^ -28955 ^ 20723 ^ -28630; i < text.length(); ++i) {
            char character = text.charAt(i);
            if (character == (31913 ^ -29498 ^ 2402 ^ -1622)) {
               ++i;
            } else if (character < vEOEUjhgKw(this).length) {
               x += b4atuf31uO(GIFVJPgeWq(this)[character]) - (20745 ^ -8177 ^ 22107 ^ -6315) + l3D9HOpJSn(this);
            }
         }

         return x / (16444 ^ -27040 ^ 19502 ^ -26000);
      }
   }
}
