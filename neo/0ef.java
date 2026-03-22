package neo;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;

public class 0ef {
   public DynamicTexture tex;
   public boolean fractionalMetrics;
   protected final Tessellator tessellator = Tessellator.getInstance();
   public boolean antiAlias;
   private final int imageSize;
   public final BufferBuilder bufferbuilder;
   public int charOffset;
   public 0ee[] charData;
   public int fontHeight;
   public Font font;

   private static void n2ZtMLFAGG(0ee var0, int var1) {
      var0.height = var1;
   }

   private static BufferBuilder Y7Yjs76V7F(0ef var0) {
      return var0.bufferbuilder;
   }

   private static int SLElMvvr2A(0ee var0) {
      return var0.height;
   }

   public Font getFont() {
      return ONuq8UQO5l(this);
   }

   private static BufferBuilder tn9dLhowoz(0ef var0) {
      return var0.bufferbuilder;
   }

   public void setAntiAlias(boolean antiAlias) {
      if (VxWGDfIaVd(this) != antiAlias) {
         k7Z6TwoIw9(this, antiAlias);
         oWQ0tB1NWn(this, this.setupTexture(ggMEHeIwma(this), antiAlias, gniDNaJA33(this), l7FTNleN79(this)));
      }

   }

   private static boolean _NBV9JL6Ll/* $FF was: 5NBV9JL6Ll*/(0ef var0) {
      return var0.antiAlias;
   }

   public void setFractionalMetrics(boolean fractionalMetrics) {
      if (w3HEClNAEv(this) != fractionalMetrics) {
         cIu92qG7Zo(this, fractionalMetrics);
         586mByLYgq(this, this.setupTexture(fQoha0BJ43(this), xENvC1JG1d(this), fractionalMetrics, nLOSbGgG7u(this)));
      }

   }

   private static void _5fy9wQ14Z/* $FF was: 05fy9wQ14Z*/(0ef var0, int var1) {
      var0.fontHeight = var1;
   }

   private static Font fQoha0BJ43(0ef var0) {
      return var0.font;
   }

   private static 0ee[] IQ7VRhoTjJ(0ef var0) {
      return var0.charData;
   }

   private static RenderingHints.Key nMqjxwOMo4() {
      return RenderingHints.KEY_TEXT_ANTIALIASING;
   }

   private static boolean w3HEClNAEv(0ef var0) {
      return var0.fractionalMetrics;
   }

   private static int jTjgo0j3ZU(0ee var0) {
      return var0.width;
   }

   private static boolean gniDNaJA33(0ef var0) {
      return var0.fractionalMetrics;
   }

   private static int eiQBCgkYFm(0ef var0) {
      return var0.fontHeight;
   }

   private static int zdrmWbSzaO(0ee var0) {
      return var0.storedX;
   }

   private static void cIu92qG7Zo(0ef var0, boolean var1) {
      var0.fractionalMetrics = var1;
   }

   private static RenderingHints.Key LpSwtitVIL() {
      return RenderingHints.KEY_ANTIALIASING;
   }

   protected DynamicTexture setupTexture(Font font, boolean antiAlias, boolean fractionalMetrics, 0ee[] chars) {
      BufferedImage img = this.generateFontImage(font, antiAlias, fractionalMetrics, chars);

      try {
         return new DynamicTexture(img);
      } catch (Exception var7) {
         Exception e = var7;
         e.printStackTrace();
         return null;
      }
   }

   private static int LQlehONrbk(0ee var0) {
      return var0.width;
   }

   private static Object OxnhBjcuxo() {
      return RenderingHints.VALUE_ANTIALIAS_ON;
   }

   private static Color EV6yYhTtY0() {
      return Color.WHITE;
   }

   private static 0ee[] nLOSbGgG7u(0ef var0) {
      return var0.charData;
   }

   private static int _V6mMIO825/* $FF was: 6V6mMIO825*/(0ef var0) {
      return var0.fontHeight;
   }

   private static int SGn09le9of(0ee var0) {
      return var0.width;
   }

   private static void _XhV0HYFG6/* $FF was: 6XhV0HYFG6*/(0ee var0, int var1) {
      var0.storedX = var1;
   }

   protected void drawQuad(float x, float y, float width, float height, float srcX, float srcY, float srcWidth, float srcHeight) {
      float renderSRCX = srcX / Float.intBitsToFloat(261719 ^ 256757 ^ 7121 ^ -983705943 ^ 251744 ^ 211222 ^ 23224 ^ -2124583660);
      float renderSRCY = srcY / Float.intBitsToFloat(10445 ^ 129274 ^ 14540 ^ -765795699 ^ 30845 ^ 106584 ^ 12871 ^ -1772429292);
      float renderSRCWidth = srcWidth / Float.intBitsToFloat(9821 ^ 232225 ^ 7778 ^ 502391487 ^ '쾟' ^ '뜈' ^ 9744 ^ 1509035814);
      float renderSRCHeight = srcHeight / Float.intBitsToFloat(113427 ^ 115183 ^ 7434 ^ -1776249492 ^ 125825 ^ 96251 ^ 4214 ^ -769622378);
      8DPaA8PY8P(this).pos((double)(x + width), (double)y, Double.longBitsToDouble(-1412574183462533024L ^ -1412574183462533024L)).tex((double)(renderSRCX + renderSRCWidth), (double)renderSRCY).endVertex();
      gWIezokvap(this).pos((double)x, (double)y, Double.longBitsToDouble(-7699470968943935171L ^ -7699470968943935171L)).tex((double)renderSRCX, (double)renderSRCY).endVertex();
      D9H6tkFAed(this).pos((double)x, (double)(y + height), Double.longBitsToDouble(-5135313470754233460L ^ -5135313470754233460L)).tex((double)renderSRCX, (double)(renderSRCY + renderSRCHeight)).endVertex();
      Y7Yjs76V7F(this).pos((double)x, (double)(y + height), Double.longBitsToDouble(-3901181171487622552L ^ -3901181171487622552L)).tex((double)renderSRCX, (double)(renderSRCY + renderSRCHeight)).endVertex();
      3s6x6sHiih(this).pos((double)(x + width), (double)(y + height), Double.longBitsToDouble(4751017347292373781L ^ 4751017347292373781L)).tex((double)(renderSRCX + renderSRCWidth), (double)(renderSRCY + renderSRCHeight)).endVertex();
      tn9dLhowoz(this).pos((double)(x + width), (double)y, Double.longBitsToDouble(-8689878583617119679L ^ -8689878583617119679L)).tex((double)(renderSRCX + renderSRCWidth), (double)renderSRCY).endVertex();
   }

   private static void _86mByLYgq/* $FF was: 586mByLYgq*/(0ef var0, DynamicTexture var1) {
      var0.tex = var1;
   }

   private static int rrg4g4Tfjp(0ee var0) {
      return var0.width;
   }

   private static Font ggMEHeIwma(0ef var0) {
      return var0.font;
   }

   public static Font readFontFromFile(String fontName, float size) {
      try {
         Font font = Font.createFont(23351 ^ -24494 ^ 23816 ^ -22931, Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation(ivQWaY4WPn("ςωσϛύϞω\u0383ϊσςϘϟ\u0383") + fontName)).getInputStream());
         return font.deriveFont(size);
      } catch (Exception var3) {
         Exception e = var3;
         e.printStackTrace();
         return null;
      }
   }

   private static boolean _WMVv57Zql/* $FF was: 3WMVv57Zql*/(0ef var0) {
      return var0.fractionalMetrics;
   }

   public _ef/* $FF was: 0ef*/(Font font, boolean antiAlias, boolean fractionalMetrics) {
      this.bufferbuilder = this.tessellator.getBuffer();
      this.imageSize = 19505 ^ -14539 ^ 12832 ^ -17628;
      this.charData = new 0ee[4812 ^ 7389 ^ 2296 ^ 703];
      this.fontHeight = -3254 ^ -16217 ^ 4688 ^ -8638;
      this.charOffset = 15045 ^ -30157 ^ 5309 ^ -23477;
      this.font = font;
      this.antiAlias = antiAlias;
      this.fractionalMetrics = fractionalMetrics;
      this.tex = this.setupTexture(font, antiAlias, fractionalMetrics, this.charData);
   }

   private static void oWQ0tB1NWn(0ef var0, DynamicTexture var1) {
      var0.tex = var1;
   }

   private static int nxIvyeudBb(0ee var0) {
      return var0.height;
   }

   public int getStringWidth(String text) {
      int width = 24155 ^ -24858 ^ 30187 ^ -19114;
      char[] var3 = text.toCharArray();
      int var4 = var3.length;

      for(int var5 = 27236 ^ -25190 ^ 26415 ^ -28463; var5 < var4; ++var5) {
         char c = var3[var5];
         if (c < 66gdyJJFRk(this).length) {
            width += SGn09le9of(nQ1Q1wWSoT(this)[c]) - (8237 ^ -28046 ^ 15604 ^ -29021) + 6DXgOkO8S8(this);
         }
      }

      return width / (11962 ^ -27534 ^ 3295 ^ -18923);
   }

   public void drawChar(0ee[] chars, char c, float x, float y) throws ArrayIndexOutOfBoundsException {
      try {
         this.drawQuad(x, y, (float)jTjgo0j3ZU(chars[c]), (float)nxIvyeudBb(chars[c]), (float)zdrmWbSzaO(chars[c]), (float)w0nvGERYpv(chars[c]), (float)LQlehONrbk(chars[c]), (float)q9RkALouYT(chars[c]));
      } catch (Exception var6) {
         Exception e = var6;
         e.printStackTrace();
      }

   }

   private static BufferBuilder _s6x6sHiih/* $FF was: 3s6x6sHiih*/(0ef var0) {
      return var0.bufferbuilder;
   }

   private static int TZwW5qLA4k(0ee var0) {
      return var0.height;
   }

   public int getStringHeight(String text) {
      return this.getFontHeight();
   }

   protected BufferedImage generateFontImage(Font font, boolean antiAlias, boolean fractionalMetrics, 0ee[] chars) {
      int imgSize = 22257 ^ -31754 ^ 19549 ^ -25766;
      BufferedImage bufferedImage = new BufferedImage(imgSize, imgSize, 21914 ^ -21780 ^ 15852 ^ -15720);
      Graphics2D g = (Graphics2D)bufferedImage.getGraphics();
      g.setFont(font);
      g.setColor(new Color(12620 ^ -12971 ^ 23578 ^ -24324, 32668 ^ -3828 ^ 1092 ^ -30165, 20758 ^ -32533 ^ 11851 ^ -183, 22718 ^ -6107 ^ 3570 ^ -17047));
      g.fillRect(26 ^ -19268 ^ 22099 ^ -7435, 30001 ^ -27044 ^ 28902 ^ -27765, imgSize, imgSize);
      g.setColor(EV6yYhTtY0());
      g.setRenderingHint(1dNgzfbM4W(), JoDSK7JkUy());
      g.setRenderingHint(nMqjxwOMo4(), y10yoCdENs());
      g.setRenderingHint(LpSwtitVIL(), OxnhBjcuxo());
      FontMetrics fontMetrics = g.getFontMetrics();
      int charHeight = 14211 ^ -30040 ^ 13461 ^ -30274;
      int positionX = 715 ^ -8064 ^ 13812 ^ -10305;
      int positionY = 23525 ^ -4553 ^ 7737 ^ -21526;

      for(int i = 25847 ^ -13190 ^ 2792 ^ -23963; i < chars.length; ++i) {
         char ch = (char)i;
         if (ch <= (4819 ^ -14231 ^ 215 ^ -8605) && ch >= (7756 ^ -11843 ^ 26154 ^ -22309)) {
            chars[i] = null;
         } else {
            0ee charData = new 0ee();
            Rectangle2D dimensions = fontMetrics.getStringBounds(String.valueOf(ch), g);
            ADPlgNsIvE(charData, I2m2ttiDqT(dimensions.getBounds()) + (30467 ^ -11918 ^ 22682 ^ -285));
            n2ZtMLFAGG(charData, cdjy4Jt3Fv(dimensions.getBounds()));
            if (positionX + rrg4g4Tfjp(charData) >= imgSize) {
               positionX = 15737 ^ -17524 ^ 28416 ^ -5643;
               positionY += charHeight;
               charHeight = 18057 ^ -27938 ^ 28894 ^ -23415;
            }

            if (Vcohr2YD18(charData) > charHeight) {
               charHeight = 1THGcFtvM1(charData);
            }

            6XhV0HYFG6(charData, positionX);
            pIzurl3T7U(charData, positionY);
            if (SLElMvvr2A(charData) > eiQBCgkYFm(this)) {
               05fy9wQ14Z(this, TZwW5qLA4k(charData));
            }

            chars[i] = charData;
            g.drawString(String.valueOf(ch), positionX + (1365 ^ -7638 ^ 1103 ^ -7374), positionY + fontMetrics.getAscent());
            positionX += OJHnOxOFB8(charData);
         }
      }

      return bufferedImage;
   }

   private static int w0nvGERYpv(0ee var0) {
      return var0.storedY;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String ivQWaY4WPn(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 19085 ^ -27290 ^ 6729 ^ -14942; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 29339 ^ -31507 ^ 29426 ^ -30936));
      }

      return var1.toString();
   }

   private static int cdjy4Jt3Fv(Rectangle var0) {
      return var0.height;
   }

   private static RenderingHints.Key _dNgzfbM4W/* $FF was: 1dNgzfbM4W*/() {
      return RenderingHints.KEY_FRACTIONALMETRICS;
   }

   private static int OJHnOxOFB8(0ee var0) {
      return var0.width;
   }

   private static int Vcohr2YD18(0ee var0) {
      return var0.height;
   }

   private static Object y10yoCdENs() {
      return RenderingHints.VALUE_TEXT_ANTIALIAS_ON;
   }

   private static int q9RkALouYT(0ee var0) {
      return var0.height;
   }

   private static BufferBuilder gWIezokvap(0ef var0) {
      return var0.bufferbuilder;
   }

   private static void k7Z6TwoIw9(0ef var0, boolean var1) {
      var0.antiAlias = var1;
   }

   private static 0ee[] l7FTNleN79(0ef var0) {
      return var0.charData;
   }

   private static void lbEFr65hIl(0ef var0, DynamicTexture var1) {
      var0.tex = var1;
   }

   private static BufferBuilder D9H6tkFAed(0ef var0) {
      return var0.bufferbuilder;
   }

   private static BufferBuilder _DPaA8PY8P/* $FF was: 8DPaA8PY8P*/(0ef var0) {
      return var0.bufferbuilder;
   }

   public int getFontHeight() {
      return (6V6mMIO825(this) - (20121 ^ -23513 ^ 18225 ^ -21113)) / (19484 ^ -14282 ^ 5099 ^ -26685);
   }

   private static int _THGcFtvM1/* $FF was: 1THGcFtvM1*/(0ee var0) {
      return var0.height;
   }

   private static Object JoDSK7JkUy() {
      return RenderingHints.VALUE_FRACTIONALMETRICS_ON;
   }

   private static Font ONuq8UQO5l(0ef var0) {
      return var0.font;
   }

   private static 0ee[] _6gdyJJFRk/* $FF was: 66gdyJJFRk*/(0ef var0) {
      return var0.charData;
   }

   public void setFont(Font font) {
      pBoDYaFwco(this, font);
      lbEFr65hIl(this, this.setupTexture(font, 5NBV9JL6Ll(this), 3WMVv57Zql(this), IQ7VRhoTjJ(this)));
   }

   private static 0ee[] nQ1Q1wWSoT(0ef var0) {
      return var0.charData;
   }

   private static void ADPlgNsIvE(0ee var0, int var1) {
      var0.width = var1;
   }

   private static void pIzurl3T7U(0ee var0, int var1) {
      var0.storedY = var1;
   }

   private static int I2m2ttiDqT(Rectangle var0) {
      return var0.width;
   }

   private static void pBoDYaFwco(0ef var0, Font var1) {
      var0.font = var1;
   }

   private static boolean xENvC1JG1d(0ef var0) {
      return var0.antiAlias;
   }

   private static int _DXgOkO8S8/* $FF was: 6DXgOkO8S8*/(0ef var0) {
      return var0.charOffset;
   }

   private static boolean VxWGDfIaVd(0ef var0) {
      return var0.antiAlias;
   }
}
