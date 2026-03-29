package neo;

import com.jhlabs.image.GaussianFilter;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Timer;
import net.minecraft.util.math.AxisAlignedBB;
import org.lwjgl.opengl.GL11;

public class 0ew implements 0eB {
   public static final HashMap<Double, Integer> shadowCache = new HashMap();

   private static double IFqJ4UJAnD(AxisAlignedBB var0) {
      return var0.maxX;
   }

   public static void color(int color) {
      color(color, (float)(color >> (17793 ^ -29407 ^ 17092 ^ -30084) & (17138 ^ -26468 ^ 17846 ^ -24793)) / Float.intBitsToFloat(1024595 ^ '걲' ^ 1034546 ^ 287799997 ^ 28649 ^ 89309 ^ 122104 ^ 1381519970));
   }

   private static double _ggTMVyDbg/* $FF was: 3ggTMVyDbg*/(AxisAlignedBB var0) {
      return var0.minX;
   }

   public _ew/* $FF was: 0ew*/() {
   }

   private static double RAYYFjB4aT() {
      return RenderManager.renderPosX;
   }

   private static double BAmFdktGJa(AxisAlignedBB var0) {
      return var0.maxX;
   }

   private static double lUJ05k2WyT(AxisAlignedBB var0) {
      return var0.maxX;
   }

   private static double o87iIH3Rqy(AxisAlignedBB var0) {
      return var0.maxZ;
   }

   private static double Tropgd1vIA(AxisAlignedBB var0) {
      return var0.maxY;
   }

   private static VertexFormat tnen1W7wjL() {
      return DefaultVertexFormats.POSITION_TEX;
   }

   private static double BbEaPpkBnk(AxisAlignedBB var0) {
      return var0.maxY;
   }

   public static void horizontalGradient(double x1, double y1, double x2, double y2, int startColor, int endColor) {
      x2 += x1;
      y2 += y1;
      float f = (float)(startColor >> (28117 ^ -13008 ^ 20800 ^ -3651) & (3476 ^ -7969 ^ 18002 ^ -21530)) / Float.intBitsToFloat('虪' ^ 102266 ^ '陸' ^ 1008784280 ^ 7026 ^ 26320 ^ 14233 ^ 2136868555);
      float f1 = (float)(startColor >> (6800 ^ -22092 ^ 7597 ^ -20839) & (26100 ^ -21173 ^ 2218 ^ -16150)) / Float.intBitsToFloat('ﺷ' ^ 1024869 ^ '\ue2e3' ^ -81264853 ^ 1784 ^ 24538 ^ 298 ^ -1202251758);
      float f2 = (float)(startColor >> (3705 ^ -32687 ^ 18423 ^ -13865) & (6256 ^ -15842 ^ 3979 ^ -10982)) / Float.intBitsToFloat('먍' ^ 4157954 ^ 10906 ^ -2019567030 ^ 19113 ^ 109727 ^ 10221 ^ -992020220);
      float f3 = (float)(startColor & (21754 ^ -27651 ^ 26763 ^ -20621)) / Float.intBitsToFloat(105092 ^ 106771 ^ 11415 ^ -2124635359 ^ 507221 ^ 480468 ^ 10629 ^ -1037863899);
      float f4 = (float)(endColor >> (4310 ^ -32188 ^ 28191 ^ -875) & (6051 ^ -32684 ^ 21357 ^ -15259)) / Float.intBitsToFloat(126651 ^ '듺' ^ 126807 ^ -1111459402 ^ 8357173 ^ 8346120 ^ 18494 ^ -21013597);
      float f5 = (float)(endColor >> (31934 ^ -3243 ^ 14642 ^ -18743) & (20364 ^ -8261 ^ 7690 ^ -28990)) / Float.intBitsToFloat('쎺' ^ 16230 ^ 'ﰐ' ^ -1252830969 ^ 14406 ^ 11009 ^ 1014 ^ -164871814);
      float f6 = (float)(endColor >> (10346 ^ -24379 ^ 22745 ^ -12162) & (5028 ^ -18351 ^ 10713 ^ -32045)) / Float.intBitsToFloat(3498 ^ 109585 ^ 129114 ^ 1306041721 ^ 1037089 ^ 1004492 ^ 13310 ^ 245856139);
      float f7 = (float)(endColor & (16304 ^ -15163 ^ 21021 ^ -22121)) / Float.intBitsToFloat(5828 ^ '詪' ^ '\ueb6f' ^ 40377659 ^ '섍' ^ 100445 ^ '\udbe7' ^ 1092024397);
      GlStateManager.disableTexture2D();
      GlStateManager.enableBlend();
      GlStateManager.disableAlpha();
      GlStateManager.tryBlendFuncSeparate(gDD6tHlOiB(), kaWgakOKjN(), CEtAFwFqlI(), y24RwEtndI());
      GlStateManager.shadeModel(21338 ^ -39203 ^ '퐏' ^ -887);
      Tessellator tessellator = Tessellator.getInstance();
      BufferBuilder bufferbuilder = tessellator.getBuffer();
      bufferbuilder.begin(8605 ^ -31062 ^ 23411 ^ -957, HaEfLbslL2());
      bufferbuilder.pos(x1, y1, Double.longBitsToDouble(-118902688232124115L ^ -118902688232124115L)).color(f1, f2, f3, f).endVertex();
      bufferbuilder.pos(x1, y2, Double.longBitsToDouble(5343003874416420244L ^ 5343003874416420244L)).color(f1, f2, f3, f).endVertex();
      bufferbuilder.pos(x2, y2, Double.longBitsToDouble(7551401727112823113L ^ 7551401727112823113L)).color(f5, f6, f7, f4).endVertex();
      bufferbuilder.pos(x2, y1, Double.longBitsToDouble(-2883655012454190162L ^ -2883655012454190162L)).color(f5, f6, f7, f4).endVertex();
      tessellator.draw();
      GlStateManager.shadeModel('钒' ^ -37253 ^ 2660 ^ -4723);
      GlStateManager.disableBlend();
      GlStateManager.enableAlpha();
      GlStateManager.enableTexture2D();
   }

   private static double _PfC3Y9GwA/* $FF was: 9PfC3Y9GwA*/(AxisAlignedBB var0) {
      return var0.maxX;
   }

   private static double LnL4eTdQh1(AxisAlignedBB var0) {
      return var0.maxZ;
   }

   public static void glHexColor(int hex, int alpha) {
      float red = (float)(hex >> (16915 ^ -21746 ^ 32360 ^ -26779) & (2056 ^ -28578 ^ 31795 ^ -7014)) / Float.intBitsToFloat(124715 ^ '뿷' ^ 123639 ^ -284530219 ^ 3049 ^ 228687 ^ 248208 ^ -1401590584);
      float green = (float)(hex >> (28765 ^ -1009 ^ 16791 ^ -12851) & (7546 ^ -24358 ^ 13283 ^ -28996)) / Float.intBitsToFloat(517071 ^ 516652 ^ 21229 ^ -1693736695 ^ 115791 ^ 117459 ^ 14422 ^ -663436083);
      float blue = (float)(hex & (29930 ^ -3315 ^ 318 ^ -31194)) / Float.intBitsToFloat(23727 ^ 8341218 ^ '풃' ^ -935340457 ^ 21008 ^ 125829 ^ 26136 ^ -1958820076);
      GlStateManager.color(red, green, blue, (float)alpha / Float.intBitsToFloat('정' ^ 81137 ^ 23968 ^ 758577748 ^ '잢' ^ '볘' ^ 2022 ^ 1850224524));
   }

   private static Minecraft a8a4XInjUT() {
      return mc;
   }

   private static GlStateManager.DestFactor pzf1qqxoli() {
      return GlStateManager.DestFactor.ZERO;
   }

   private static double qcVVGoHUL1(AxisAlignedBB var0) {
      return var0.minX;
   }

   private static double SeYt1qfSmx(AxisAlignedBB var0) {
      return var0.maxZ;
   }

   private static Minecraft nqTxCywU4e() {
      return mc;
   }

   private static double ctAqCSYr3j(AxisAlignedBB var0) {
      return var0.minX;
   }

   private static double dDze2u0Tnq(AxisAlignedBB var0) {
      return var0.maxY;
   }

   private static double im1ZNdkeTp(AxisAlignedBB var0) {
      return var0.minZ;
   }

   private static double WCCVHXk0bQ(AxisAlignedBB var0) {
      return var0.maxZ;
   }

   private static double Ai2iU3jyQD(AxisAlignedBB var0) {
      return var0.maxZ;
   }

   public static void drawRect(float x, float y, float width, float height, Color color) {
      int colorRGB = color.getRGB();
      width += x;
      height += y;
      float f = (float)(colorRGB >> (18670 ^ -25482 ^ 14279 ^ -7353) & (14645 ^ -30576 ^ 25940 ^ -11250)) / Float.intBitsToFloat(520662 ^ 498210 ^ 15329 ^ -1371826015 ^ 5559 ^ '뚒' ^ 4140 ^ -314278979);
      float f1 = (float)(colorRGB >> (18982 ^ -6654 ^ 21580 ^ -1928) & (25396 ^ -7951 ^ 26585 ^ -6941)) / Float.intBitsToFloat(5746 ^ 518189 ^ 3136 ^ -1880892726 ^ 7017 ^ 110458 ^ 18740 ^ -862267918);
      float f2 = (float)(colorRGB >> (14434 ^ -8125 ^ 16663 ^ -26306) & (7239 ^ -11315 ^ 2395 ^ -14802)) / Float.intBitsToFloat(3655 ^ '쬪' ^ 21120 ^ -1095822845 ^ 31772 ^ '륫' ^ 7693 ^ -36675948);
      float f3 = (float)(colorRGB & (12210 ^ -9770 ^ 32053 ^ -29778)) / Float.intBitsToFloat('\ue1b1' ^ 204046 ^ 27804 ^ -570818588 ^ 7659 ^ '\uec8d' ^ '씕' ^ -1635427404);
      GlStateManager.enableBlend();
      GlStateManager.disableTexture2D();
      GlStateManager.tryBlendFuncSeparate(16091 ^ -16550 ^ 13293 ^ -20114, 9342 ^ -5369 ^ 15784 ^ -3630, 14244 ^ -18602 ^ 75 ^ -32584, 8321 ^ -7205 ^ 2266 ^ -13440);
      color((double)f1, (double)f2, (double)f3, (double)f);
      Tessellator tessellator = Tessellator.getInstance();
      BufferBuilder worldrenderer = tessellator.getBuffer();
      worldrenderer.begin(1049 ^ -8769 ^ 21231 ^ -29874, AX3UBea2VQ());
      worldrenderer.pos((double)x, (double)height, Double.longBitsToDouble(-5471501048659630636L ^ -5471501048659630636L)).endVertex();
      worldrenderer.pos((double)width, (double)height, Double.longBitsToDouble(314788256865723046L ^ 314788256865723046L)).endVertex();
      worldrenderer.pos((double)width, (double)y, Double.longBitsToDouble(2100123872915090009L ^ 2100123872915090009L)).endVertex();
      worldrenderer.pos((double)x, (double)y, Double.longBitsToDouble(-1214551733298153199L ^ -1214551733298153199L)).endVertex();
      tessellator.draw();
      GlStateManager.enableTexture2D();
      GlStateManager.disableBlend();
   }

   private static double NwL9NxU6GN(AxisAlignedBB var0) {
      return var0.minX;
   }

   private static double yBwM1LwIqO(AxisAlignedBB var0) {
      return var0.maxY;
   }

   private static double WQTlN5ysrW(AxisAlignedBB var0) {
      return var0.minY;
   }

   public static void scissorRect(float x, float y, float width, double height) {
      ScaledResolution sr = new ScaledResolution(wrerzEwUYx());
      int factor = sr.getScaleFactor();
      GL11.glScissor((int)(x * (float)factor), (int)(((double)((float)sr.getScaledHeight()) - height) * (double)((float)factor)), (int)((width - x) * (float)factor), (int)((height - (double)y) * (double)((float)factor)));
   }

   private static double aGggB8wB4a(AxisAlignedBB var0) {
      return var0.maxZ;
   }

   private static double Ya8LoSGgKL(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static double cRYVLYjugw(AxisAlignedBB var0) {
      return var0.maxZ;
   }

   private static double hq1nl4UwVW(AxisAlignedBB var0) {
      return var0.minZ;
   }

   private static Minecraft FNHVi6uW9F() {
      return mc;
   }

   private static double CW7V4tvtlU(AxisAlignedBB var0) {
      return var0.maxY;
   }

   private static double uBdTO7m6Nv(AxisAlignedBB var0) {
      return var0.maxZ;
   }

   private static double tsJeAqTg2O(AxisAlignedBB var0) {
      return var0.minZ;
   }

   private static double urV26yrCuV(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static double Q0aijgD9tu(AxisAlignedBB var0) {
      return var0.minZ;
   }

   private static double HBST0NeyvJ(AxisAlignedBB var0) {
      return var0.minZ;
   }

   public static Framebuffer createFrameBuffer(Framebuffer framebuffer) {
      if (framebuffer != null && rbyCBwLrbW(framebuffer) == qVvRS2FtoO(nqTxCywU4e()) && abYqgStkYY(framebuffer) == XizpnZvxG6(yaqToGXt5L())) {
         return framebuffer;
      } else {
         if (framebuffer != null) {
            framebuffer.deleteFramebuffer();
         }

         return new Framebuffer(ODZVvIrk4R(FNHVi6uW9F()), j6a5lUqTqr(oEvFoPeNKU()), (boolean)(30939 ^ -20351 ^ 7864 ^ -10525));
      }
   }

   private static Color o1WTLediHi() {
      return Color.white;
   }

   private static GlStateManager.DestFactor y24RwEtndI() {
      return GlStateManager.DestFactor.ZERO;
   }

   private static double BURL1qWdVH(AxisAlignedBB var0) {
      return var0.maxY;
   }

   private static HashMap oHjNxvny5M() {
      return shadowCache;
   }

   private static double fovaBRVGLY(AxisAlignedBB var0) {
      return var0.maxY;
   }

   private static double jdF99L2HdV(AxisAlignedBB var0) {
      return var0.maxY;
   }

   private static double AMNSi0NQVn(AxisAlignedBB var0) {
      return var0.maxZ;
   }

   private static double Fal6DiHNFB(AxisAlignedBB var0) {
      return var0.minX;
   }

   private static double V6KGnJ2W9s(AxisAlignedBB var0) {
      return var0.maxX;
   }

   private static double w6ylEtzPJq(AxisAlignedBB var0) {
      return var0.minZ;
   }

   private static double I5r24egsot(AxisAlignedBB var0) {
      return var0.minX;
   }

   public static void setColor(int color) {
      GL11.glColor4ub((byte)(color >> (1188 ^ -11440 ^ 24455 ^ -30621) & (18004 ^ 21382 ^ 5466 ^ 119)), (byte)(color >> (29485 ^ -4322 ^ 24565 ^ -15410) & (6388 ^ -8861 ^ 18090 ^ -31806)), (byte)(color & (3372 ^ -9989 ^ 30304 ^ -23736)), (byte)(color >> (11688 ^ -11281 ^ 16312 ^ -15897) & (17629 ^ -29286 ^ 23748 ^ -27268)));
   }

   public static void verticalGradient(double x1, double y1, double x2, double y2, int startColor, int endColor) {
      x2 += x1;
      y2 += y1;
      float f = (float)(startColor >> (26852 ^ -16982 ^ 29741 ^ -24197) & (10349 ^ -22149 ^ 13327 ^ -18970)) / Float.intBitsToFloat(99556 ^ 106414 ^ 28069 ^ 1069199985 ^ 260160 ^ 225542 ^ 14769 ^ 2093315177);
      float f1 = (float)(startColor >> (19292 ^ -25477 ^ 16331 ^ -5892) & (13758 ^ -27894 ^ 4901 ^ -19090)) / Float.intBitsToFloat(19473 ^ 481551 ^ 511924 ^ 537088431 ^ 31933 ^ 91803 ^ 130953 ^ 1669099178);
      float f2 = (float)(startColor >> (10078 ^ -28554 ^ 4807 ^ -23065) & (4169 ^ -10764 ^ 14078 ^ -3140)) / Float.intBitsToFloat(17407 ^ 103381 ^ 116866 ^ 1723506733 ^ 237889 ^ '麩' ^ 250672 ^ 633689181);
      float f3 = (float)(startColor & (6623 ^ -5143 ^ 27730 ^ -24933)) / Float.intBitsToFloat(1019126 ^ 1000517 ^ 27590 ^ -1168672571 ^ 122005 ^ 110289 ^ 9103 ^ -114783621);
      float f4 = (float)(endColor >> (27775 ^ -6952 ^ 14295 ^ -16536) & (12255 ^ -23193 ^ 31694 ^ -3703)) / Float.intBitsToFloat('\uf38a' ^ '蹽' ^ 17553 ^ 1146833210 ^ '킟' ^ '衰' ^ 25666 ^ 119819505);
      float f5 = (float)(endColor >> (23484 ^ -8355 ^ 10795 ^ -20774) & (25422 ^ -28206 ^ 7431 ^ -4252)) / Float.intBitsToFloat(103254 ^ 30696 ^ 108200 ^ -1289316641 ^ 259333 ^ 219577 ^ 7829 ^ -262576416);
      float f6 = (float)(endColor >> (26492 ^ -20232 ^ 2948 ^ -9208) & (27848 ^ -31516 ^ 12199 ^ -14476)) / Float.intBitsToFloat(128425 ^ 106256 ^ 12787 ^ 2015793985 ^ 675 ^ 32682 ^ 5103 ^ 995731181);
      float f7 = (float)(endColor & (13590 ^ -2596 ^ 6960 ^ -9467)) / Float.intBitsToFloat(242825 ^ '裵' ^ 248972 ^ 1142244380 ^ 111659 ^ 10989 ^ 112725 ^ 124424831);
      GlStateManager.disableTexture2D();
      GlStateManager.enableBlend();
      GlStateManager.disableAlpha();
      GlStateManager.tryBlendFuncSeparate(79CYD4sb2i(), olzXpONkAy(), RJl3KoazJr(), pzf1qqxoli());
      GlStateManager.shadeModel(10948 ^ 32447 ^ 20621 ^ 6647);
      Tessellator tessellator = Tessellator.getInstance();
      BufferBuilder bufferbuilder = tessellator.getBuffer();
      bufferbuilder.begin(24592 ^ -24074 ^ 11843 ^ -4190, aidgY9AghD());
      bufferbuilder.pos(x1, y1, Double.longBitsToDouble(-3050018166026751900L ^ -3050018166026751900L)).color(f1, f2, f3, f).endVertex();
      bufferbuilder.pos(x1, y2, Double.longBitsToDouble(2594363159088800294L ^ 2594363159088800294L)).color(f5, f6, f7, f4).endVertex();
      bufferbuilder.pos(x2, y2, Double.longBitsToDouble(4450914640290726705L ^ 4450914640290726705L)).color(f5, f6, f7, f4).endVertex();
      bufferbuilder.pos(x2, y1, Double.longBitsToDouble(-606116418468024234L ^ -606116418468024234L)).color(f1, f2, f3, f).endVertex();
      tessellator.draw();
      GlStateManager.shadeModel('饜' ^ '鹁' ^ 341 ^ 6984);
      GlStateManager.disableBlend();
      GlStateManager.enableAlpha();
      GlStateManager.enableTexture2D();
   }

   private static GlStateManager.DestFactor nNzO8rUu9T() {
      return GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA;
   }

   private static GlStateManager.DestFactor iYWhzryXN1() {
      return GlStateManager.DestFactor.ZERO;
   }

   private static double _UwbrWzYV7/* $FF was: 4UwbrWzYV7*/(AxisAlignedBB var0) {
      return var0.maxY;
   }

   public static void disableSmoothLine() {
      GL11.glEnable(11604 ^ -4222 ^ 16574 ^ -28791);
      GL11.glEnable(8528 ^ -24251 ^ 15693 ^ -18903);
      GL11.glDisable(13798 ^ -10630 ^ 30937 ^ -28505);
      GL11.glEnable(21064 ^ -30333 ^ 9023 ^ -3276);
      GL11.glDepthMask((boolean)(13822 ^ -16887 ^ 11932 ^ -23190));
      GL11.glCullFace(26092 ^ -5348 ^ 20232 ^ -14851);
      GL11.glDisable('贩' ^ -6496 ^ '\uf7fe' ^ -26793);
      GL11.glHint(25644 ^ -18365 ^ 9231 ^ -3022, 28756 ^ -15584 ^ 2130 ^ -21978);
      GL11.glHint(9519 ^ -26987 ^ 16550 ^ -177, '阉' ^ -8393 ^ 'ꇘ' ^ -1562);
   }

   private static float _xe4BLLESD/* $FF was: 2xe4BLLESD*/(Timer var0) {
      return var0.renderPartialTicks;
   }

   private static double SLXDtE0TlB(Entity var0) {
      return var0.posX;
   }

   private static VertexFormat aidgY9AghD() {
      return DefaultVertexFormats.POSITION_COLOR;
   }

   private static double G2whNJWdIJ(AxisAlignedBB var0) {
      return var0.minZ;
   }

   private static double yowWdQkjel(AxisAlignedBB var0) {
      return var0.maxX;
   }

   private static double OWReMBAlXS(Entity var0) {
      return var0.lastTickPosX;
   }

   private static double _woROz4FGn/* $FF was: 8woROz4FGn*/(AxisAlignedBB var0) {
      return var0.maxZ;
   }

   private static double uJB2ik9dbt(AxisAlignedBB var0) {
      return var0.minZ;
   }

   private static double _FJ5nyvbPS/* $FF was: 6FJ5nyvbPS*/(AxisAlignedBB var0) {
      return var0.minX;
   }

   private static double _IqB4Ig0Te/* $FF was: 8IqB4Ig0Te*/(AxisAlignedBB var0) {
      return var0.maxY;
   }

   private static double aTSYBn6aoC(AxisAlignedBB var0) {
      return var0.maxY;
   }

   private static double ov40MBVAjW(AxisAlignedBB var0) {
      return var0.minX;
   }

   private static double _itDvyaBtA/* $FF was: 5itDvyaBtA*/(AxisAlignedBB var0) {
      return var0.minZ;
   }

   private static double _rINMaghTD/* $FF was: 1rINMaghTD*/(Entity var0) {
      return var0.lastTickPosZ;
   }

   private static double I8WbHgwKri(AxisAlignedBB var0) {
      return var0.maxY;
   }

   private static double RLgAQTqbG9(Entity var0) {
      return var0.posX;
   }

   private static double VD2tgynqLE(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static double QYmHIyUWby(AxisAlignedBB var0) {
      return var0.maxX;
   }

   private static double B1DXWPtYOI(AxisAlignedBB var0) {
      return var0.maxY;
   }

   private static double VXy1b5VF7m(Entity var0) {
      return var0.lastTickPosZ;
   }

   private static GlStateManager.DestFactor kaWgakOKjN() {
      return GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA;
   }

   private static double tO5qro87P2(AxisAlignedBB var0) {
      return var0.maxY;
   }

   private static double i69SrO3wTw(AxisAlignedBB var0) {
      return var0.minZ;
   }

   private static int abYqgStkYY(Framebuffer var0) {
      return var0.framebufferHeight;
   }

   private static double OwumBKj6vP() {
      return RenderManager.renderPosZ;
   }

   private static double RqT62dfxNS(AxisAlignedBB var0) {
      return var0.minZ;
   }

   private static double dVwxYVlLiD(AxisAlignedBB var0) {
      return var0.minZ;
   }

   private static float IAgnACYdux(Timer var0) {
      return var0.renderPartialTicks;
   }

   private static double _GBcsejSD7/* $FF was: 2GBcsejSD7*/(AxisAlignedBB var0) {
      return var0.minX;
   }

   private static VertexFormat CjISw54oCj() {
      return DefaultVertexFormats.POSITION_TEX;
   }

   private static double QWGEDfWi4o(AxisAlignedBB var0) {
      return var0.minZ;
   }

   private static double dr2G04FEWO(AxisAlignedBB var0) {
      return var0.maxX;
   }

   private static double ZjTdYuJtuj(AxisAlignedBB var0) {
      return var0.maxZ;
   }

   private static GlStateManager.SourceFactor nj1hcVZSji() {
      return GlStateManager.SourceFactor.ONE;
   }

   private static double GHWogdvKlF(AxisAlignedBB var0) {
      return var0.minZ;
   }

   private static int ODZVvIrk4R(Minecraft var0) {
      return var0.displayWidth;
   }

   private static double aVe735UvW8(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static double B6YwqxFwbZ(AxisAlignedBB var0) {
      return var0.maxY;
   }

   private static double SA6OVSItDn(AxisAlignedBB var0) {
      return var0.minX;
   }

   private static double rFIiyNwvkB(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static double lZYytFvr7F(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static double vAoYA2ZaJb(AxisAlignedBB var0) {
      return var0.minX;
   }

   private static double jIRo99tNwd(AxisAlignedBB var0) {
      return var0.minZ;
   }

   private static double LkvMqBAr1n(AxisAlignedBB var0) {
      return var0.minZ;
   }

   private static double qgB3SdpDiW(AxisAlignedBB var0) {
      return var0.minX;
   }

   private static double eJWqYmlG2u(AxisAlignedBB var0) {
      return var0.maxZ;
   }

   private static double kANNbNUwtA(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static HashMap aI9DNk2gb7() {
      return shadowCache;
   }

   public static void drawGrayFilterNoBlur(Runnable data) {
      0ez.initStencilToWrite();
      data.run();
      0ez.readStencilBuffer(15035 ^ -19537 ^ 8504 ^ -22483);
      0ey.renderColor(Float.intBitsToFloat(108467 ^ 115148 ^ 6402 ^ 1749714637 ^ 109128 ^ 98688 ^ 6339 ^ 684378811));
      0ez.uninitStencilBuffer();
   }

   private static double FtWVP8EV41(Entity var0) {
      return var0.lastTickPosX;
   }

   private static VertexFormat HaEfLbslL2() {
      return DefaultVertexFormats.POSITION_COLOR;
   }

   private static double JWtJ6Taoio(AxisAlignedBB var0) {
      return var0.minZ;
   }

   private static double l6MELcFyvz(AxisAlignedBB var0) {
      return var0.maxX;
   }

   private static double wObfaJsF9F(AxisAlignedBB var0) {
      return var0.maxX;
   }

   private static double tyQN1IAbiY(AxisAlignedBB var0) {
      return var0.minX;
   }

   private static double HuonNiG4Ds(Entity var0) {
      return var0.posY;
   }

   private static double OnU2L9Jvqa(AxisAlignedBB var0) {
      return var0.minX;
   }

   private static double VbHF411I1R(AxisAlignedBB var0) {
      return var0.maxY;
   }

   private static double _FInKqeUHW/* $FF was: 0FInKqeUHW*/(AxisAlignedBB var0) {
      return var0.minX;
   }

   private static Color _AS0neA6zo/* $FF was: 0AS0neA6zo*/() {
      return Color.WHITE;
   }

   private static double _VenQsAOqo/* $FF was: 4VenQsAOqo*/(AxisAlignedBB var0) {
      return var0.maxX;
   }

   public static void color(int color, float alpha) {
      float r = (float)(color >> (217 ^ -6002 ^ 26383 ^ -28856) & (5446 ^ -10671 ^ 1669 ^ -14995)) / Float.intBitsToFloat(20460 ^ '襔' ^ 7499 ^ 1550336638 ^ 15382 ^ '\ue93f' ^ 14858 ^ 521631406);
      float g = (float)(color >> (840 ^ -25381 ^ 19594 ^ -11503) & (5046 ^ -19362 ^ 17332 ^ -7005)) / Float.intBitsToFloat(7732 ^ 236536 ^ 249764 ^ -1533440222 ^ 117302 ^ 94962 ^ 9515 ^ -404334427);
      float b = (float)(color & (10150 ^ -5451 ^ 28476 ^ -23856)) / Float.intBitsToFloat(23164 ^ 30949 ^ 10767 ^ -2064572039 ^ 17401 ^ 242820 ^ 6372 ^ -947009930);
      GlStateManager.color(r, g, b, alpha);
   }

   private static Timer DdQUxSuK4C(Minecraft var0) {
      return var0.timer;
   }

   private static double lq5YCnDz1e(AxisAlignedBB var0) {
      return var0.maxY;
   }

   private static double AwlG4gR1V2(AxisAlignedBB var0) {
      return var0.maxY;
   }

   private static double noPBlYNdDx(Entity var0) {
      return var0.lastTickPosY;
   }

   public static void renderImage(BufferedImage image, int x, int y, int width, int height) {
      Minecraft minecraft = Minecraft.getMinecraft();
      ResourceLocation resourceLocation = minecraft.getTextureManager().getDynamicTextureLocation(NtMnA66W0J("ƳƤƯƥƤƳƤƥƞƨƬƠƦƤ"), new DynamicTexture(image));
      minecraft.getTextureManager().bindTexture(resourceLocation);
      GlStateManager.pushMatrix();
      GlStateManager.enableAlpha();
      GlStateManager.enableBlend();
      GlStateManager.color(Float.intBitsToFloat(119876 ^ 82488 ^ 2152 ^ 1111513867 ^ 115800 ^ 'ꂂ' ^ 116419 ^ 2109761286), Float.intBitsToFloat(31468 ^ 110653 ^ 11531 ^ -1970086190 ^ 21869 ^ 84225 ^ '︾' ^ -1257055398), Float.intBitsToFloat('\uf6a3' ^ 9076 ^ '텭' ^ 2081772243 ^ 15794 ^ '嵐' ^ '웭' ^ 1133859863), Float.intBitsToFloat(18255 ^ 99662 ^ 12509 ^ -1816988971 ^ 25617 ^ 120303 ^ 27249 ^ -1405953146));
      Gui.drawModalRectWithCustomSizedTexture(x, y, Float.intBitsToFloat(245806 ^ 18640 ^ 249229 ^ 1535214481 ^ 230991 ^ 30984 ^ 237555 ^ 1535223382), Float.intBitsToFloat(1046702 ^ 22347 ^ 1041349 ^ 1586987025 ^ 1037489 ^ 1027879 ^ 7939 ^ 1586997412), width, height, (float)width, (float)height);
      GlStateManager.popMatrix();
      Minecraft.getMinecraft().getTextureManager().deleteTexture(resourceLocation);
   }

   private static VertexFormat _F47lLB4Iq/* $FF was: 7F47lLB4Iq*/() {
      return DefaultVertexFormats.POSITION_TEX;
   }

   public static void color(double red, double green, double blue, double alpha) {
      GL11.glColor4d(red, green, blue, alpha);
   }

   private static GlStateManager.SourceFactor RJl3KoazJr() {
      return GlStateManager.SourceFactor.ONE;
   }

   private static double Vrv1IOvFw9(AxisAlignedBB var0) {
      return var0.maxX;
   }

   private static Minecraft fOD7C1PExo() {
      return mc;
   }

   public static void drawBlurredShadowOval(float x, float y, float width, float height, int blurRadius, Color color) {
      GL11.glPushMatrix();
      GlStateManager.alphaFunc(9320 ^ -12523 ^ 8427 ^ -13934, Float.intBitsToFloat(234 ^ 109488 ^ 26842 ^ -931731484 ^ 120461 ^ 105290 ^ 25021 ^ -195764460));
      width += (float)(blurRadius * (15832 ^ -26598 ^ 16879 ^ -7121));
      height += (float)(blurRadius * (5575 ^ -25159 ^ 21585 ^ -9171));
      x -= (float)blurRadius;
      y -= (float)blurRadius;
      float _X = x - Float.intBitsToFloat(20128 ^ 109093 ^ 3810 ^ 758840069 ^ 30623 ^ 108576 ^ 190 ^ 331009635);
      float _Y = y + Float.intBitsToFloat(11067 ^ 261938 ^ 1402 ^ 274845398 ^ 32707 ^ 16332 ^ 24503 ^ 786563101);
      double identifier = (double)((int)((double)(width * height * Float.intBitsToFloat(32329 ^ 112869 ^ 16362 ^ 297325431 ^ '韸' ^ '芥' ^ 27537 ^ 1475814653)) / Math.sin((double)blurRadius)));
      GL11.glEnable(25471 ^ -43777 ^ '\ued12' ^ -10381);
      GL11.glDisable(24846 ^ -31257 ^ 31218 ^ -27041);
      GL11.glEnable(5344 ^ -22881 ^ 16533 ^ -1750);
      GlStateManager.enableBlend();
      int texId = -17496 ^ -22526 ^ 6157 ^ -2984;
      if (hTWnt2408z().containsKey(identifier)) {
         texId = (Integer)EzKQoeyE6G().get(identifier);
         GlStateManager.bindTexture(texId);
      } else {
         BufferedImage original = new BufferedImage((int)width, (int)height, 8488 ^ -20198 ^ 1884 ^ -26772);
         Graphics g = original.getGraphics();
         g.setColor(0AS0neA6zo());
         g.fillOval(blurRadius, blurRadius, (int)(width - (float)(blurRadius * (13385 ^ -22451 ^ 1022 ^ -24584))), (int)(height - (float)(blurRadius * (11052 ^ -6431 ^ 25299 ^ -20708))));
         g.dispose();
         GaussianFilter op = new GaussianFilter((float)blurRadius);
         BufferedImage blurred = op.filter(original, (BufferedImage)null);
         texId = TextureUtil.uploadTextureImageAllocate(TextureUtil.glGenTextures(), blurred, (boolean)(3711 ^ -956 ^ 8891 ^ -12159), (boolean)(24094 ^ -7877 ^ 17019 ^ -674));
         oHjNxvny5M().put(identifier, texId);
      }

      GL11.glColor4f((float)color.getRed() / Float.intBitsToFloat('鬬' ^ 231409 ^ '뀦' ^ -694553846 ^ '쓆' ^ '\uec31' ^ 15230 ^ -1780134792), (float)color.getGreen() / Float.intBitsToFloat(9558 ^ '酖' ^ '쳻' ^ 1935424177 ^ 2516 ^ 98489 ^ 39 ^ 807586560), (float)color.getBlue() / Float.intBitsToFloat(1027 ^ 234410 ^ 239715 ^ -1298297747 ^ 110801 ^ '솵' ^ 129737 ^ -236832758), (float)color.getAlpha() / Float.intBitsToFloat(231682 ^ '씕' ^ 257516 ^ 2138385168 ^ 14675 ^ 'ꍊ' ^ 20805 ^ 1007305143));
      GL11.glBegin(30938 ^ -28205 ^ 18508 ^ -24254);
      GL11.glTexCoord2f(Float.intBitsToFloat('\ue37c' ^ '蝂' ^ 9086 ^ -837152539 ^ 8459 ^ '옖' ^ '︪' ^ -837136750), Float.intBitsToFloat('蔮' ^ 32720 ^ '떱' ^ -1461249970 ^ 13026 ^ '\uf1cf' ^ '\ua6f9' ^ -1461243179));
      GL11.glVertex2f(_X, _Y);
      GL11.glTexCoord2f(Float.intBitsToFloat(27219 ^ 82981 ^ 122944 ^ 1704215697 ^ 4561 ^ '鏃' ^ 8803 ^ 1704209110), Float.intBitsToFloat('뮬' ^ 2043029 ^ 3063 ^ 1678751869 ^ '뿛' ^ 29683 ^ '\ue5c9' ^ 1536167250));
      GL11.glVertex2f(_X, _Y + height);
      GL11.glTexCoord2f(Float.intBitsToFloat(17636 ^ 24311 ^ 13139 ^ 952929455 ^ 8059 ^ 10011 ^ 5358 ^ 122456417), Float.intBitsToFloat(21858 ^ '魒' ^ 7620 ^ 1104049572 ^ 110801 ^ 71156 ^ 14006 ^ 2119054787));
      GL11.glVertex2f(_X + width, _Y + height);
      GL11.glTexCoord2f(Float.intBitsToFloat('뫢' ^ '\uab1a' ^ 29395 ^ -1830806777 ^ 'ﾑ' ^ '鸉' ^ 14438 ^ -1386208814), Float.intBitsToFloat(23902 ^ 12266 ^ 30588 ^ -2146172467 ^ 16056 ^ 523696 ^ 22404 ^ -2146201463));
      GL11.glVertex2f(_X + width, _Y);
      GL11.glEnd();
      GlStateManager.enableTexture2D();
      GlStateManager.disableBlend();
      GL11.glEnable(13980 ^ -22500 ^ 12410 ^ -23106);
      GlStateManager.resetColor();
      GL11.glPopMatrix();
   }

   private static double vhx9i1oyoG(AxisAlignedBB var0) {
      return var0.minZ;
   }

   private static Minecraft wrerzEwUYx() {
      return mc;
   }

   private static Minecraft yaqToGXt5L() {
      return mc;
   }

   private static GlStateManager.SourceFactor SwUZ9vu9rf() {
      return GlStateManager.SourceFactor.SRC_ALPHA;
   }

   public static void drawGradientRect(float f, float sY, double width, double height, int colour1, int colour2) {
      Gui.drawGradientRect1(f, sY, (double)f + width, (double)sY + height, colour1, colour2);
   }

   private static double Q4BYWJ9AR2(AxisAlignedBB var0) {
      return var0.minZ;
   }

   private static double wNy9ZG1T9K(AxisAlignedBB var0) {
      return var0.maxY;
   }

   public static void bindTexture(int texture) {
      GL11.glBindTexture(23082 ^ -2180 ^ 4604 ^ -20149, texture);
   }

   private static double NDbpR9ZZlw(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static double aojoknurF0(AxisAlignedBB var0) {
      return var0.maxZ;
   }

   private static double PTRYkNqV6v(AxisAlignedBB var0) {
      return var0.maxZ;
   }

   private static double taSUeQypSp(AxisAlignedBB var0) {
      return var0.maxX;
   }

   private static double axI8FVeMiL(AxisAlignedBB var0) {
      return var0.minZ;
   }

   private static double xNJvoe8qTP(AxisAlignedBB var0) {
      return var0.minX;
   }

   private static GlStateManager.DestFactor olzXpONkAy() {
      return GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA;
   }

   private static double q6eH1n2icn(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static Timer rrHOPNoMvV(Minecraft var0) {
      return var0.timer;
   }

   private static double J6dZ4mFDY9(AxisAlignedBB var0) {
      return var0.minZ;
   }

   private static double FROsNFdd17(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static double qmS2gKWIe8() {
      return RenderManager.renderPosY;
   }

   private static double Xw4xmtvbjw(AxisAlignedBB var0) {
      return var0.maxX;
   }

   private static double pxx6InjVoi(AxisAlignedBB var0) {
      return var0.maxX;
   }

   private static double AbbGAFeQ2d(AxisAlignedBB var0) {
      return var0.maxY;
   }

   private static double HxwInvDVMJ(AxisAlignedBB var0) {
      return var0.maxX;
   }

   private static double iuooWbxyxT(AxisAlignedBB var0) {
      return var0.minZ;
   }

   private static double iJgIAFGUtb(AxisAlignedBB var0) {
      return var0.minZ;
   }

   private static GlStateManager.SourceFactor CEtAFwFqlI() {
      return GlStateManager.SourceFactor.ONE;
   }

   private static double HjVF0yLmKH(AxisAlignedBB var0) {
      return var0.maxZ;
   }

   private static double gBI6e7lySY(AxisAlignedBB var0) {
      return var0.minX;
   }

   private static double jLbwQwgk1g(AxisAlignedBB var0) {
      return var0.minZ;
   }

   public static void drawColorBox(AxisAlignedBB axisalignedbb, float red, float green, float blue, float alpha) {
      Tessellator ts = Tessellator.getInstance();
      BufferBuilder buffer = ts.getBuffer();
      buffer.begin(19235 ^ -22978 ^ 24348 ^ -19962, XJNG8S71AB());
      buffer.pos(B6FpGqxfLN(axisalignedbb), W51D62tVBO(axisalignedbb), JWtJ6Taoio(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      buffer.pos(NwL9NxU6GN(axisalignedbb), OQjPOYh3EB(axisalignedbb), Q0aijgD9tu(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      buffer.pos(j4ztFKzbSt(axisalignedbb), oIAsTaV2OQ(axisalignedbb), DLv47LwWji(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      buffer.pos(PFT51g4hmo(axisalignedbb), wNy9ZG1T9K(axisalignedbb), TI4eq20BIJ(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      buffer.pos(QYmHIyUWby(axisalignedbb), rEBZ9qNC9L(axisalignedbb), bPvYkMKNdU(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      buffer.pos(lUJ05k2WyT(axisalignedbb), yBwM1LwIqO(axisalignedbb), LnL4eTdQh1(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      buffer.pos(0FInKqeUHW(axisalignedbb), s4g1ai5o9f(axisalignedbb), Ai2iU3jyQD(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      buffer.pos(JPVgI8GeHc(axisalignedbb), I8WbHgwKri(axisalignedbb), aGggB8wB4a(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      ts.draw();
      buffer.begin(11244 ^ -30204 ^ 22445 ^ -2494, CjISw54oCj());
      buffer.pos(rU9dGaesXN(axisalignedbb), MB1cNggxPb(axisalignedbb), dVwxYVlLiD(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      buffer.pos(17JOqq4Nq1(axisalignedbb), q6eH1n2icn(axisalignedbb), i69SrO3wTw(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      buffer.pos(s7gmOWW7Si(axisalignedbb), AwlG4gR1V2(axisalignedbb), aMY60jT3yi(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      buffer.pos(TwrqBtE1YV(axisalignedbb), F33FvQBfDl(axisalignedbb), iJgIAFGUtb(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      buffer.pos(3ggTMVyDbg(axisalignedbb), AbbGAFeQ2d(axisalignedbb), 8woROz4FGn(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      buffer.pos(KcLIqb4J59(axisalignedbb), xrelR1AQD9(axisalignedbb), aojoknurF0(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      buffer.pos(R2r5aeAKyb(axisalignedbb), gVZMOhEayv(axisalignedbb), 53Ro9mGt1z(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      buffer.pos(iYyhs2jGOi(axisalignedbb), BfY6wxJUOw(axisalignedbb), AMNSi0NQVn(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      ts.draw();
      buffer.begin(25133 ^ -17275 ^ 31131 ^ -22732, ZxdOttl4nl());
      buffer.pos(zMvoOFu1ki(axisalignedbb), 12iDjyH61d(axisalignedbb), im1ZNdkeTp(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      buffer.pos(ef1lD4qdig(axisalignedbb), gIj9TINtSZ(axisalignedbb), GHWogdvKlF(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      buffer.pos(pxx6InjVoi(axisalignedbb), Tropgd1vIA(axisalignedbb), o87iIH3Rqy(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      buffer.pos(qWXnTYQvdO(axisalignedbb), VbHF411I1R(axisalignedbb), SeYt1qfSmx(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      buffer.pos(9swoJF2waT(axisalignedbb), Qpr7adL6xn(axisalignedbb), uJB2ik9dbt(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      buffer.pos(wDTNvwbcy3(axisalignedbb), Mo8dFn20jQ(axisalignedbb), WgteL9SVOd(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      buffer.pos(4VenQsAOqo(axisalignedbb), hqy46F1tDg(axisalignedbb), uBdTO7m6Nv(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      buffer.pos(nOlwXa0C12(axisalignedbb), CW7V4tvtlU(axisalignedbb), U5v5KOoNdN(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      ts.draw();
      buffer.begin(31567 ^ -21930 ^ 4070 ^ -8456, hN5m9NAbhA());
      buffer.pos(FszdhbbliQ(axisalignedbb), rFIiyNwvkB(axisalignedbb), 4seztJaWQF(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      buffer.pos(Vrv1IOvFw9(axisalignedbb), nFZtLO0HWj(axisalignedbb), hq1nl4UwVW(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      buffer.pos(taSUeQypSp(axisalignedbb), weCJEoG22O(axisalignedbb), BG4j4aimxw(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      buffer.pos(tyQN1IAbiY(axisalignedbb), NrdPrQ841d(axisalignedbb), WCCVHXk0bQ(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      buffer.pos(jmZQkoONDP(axisalignedbb), kANNbNUwtA(axisalignedbb), AsoA9Di1XQ(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      buffer.pos(ctAqCSYr3j(axisalignedbb), aVe735UvW8(axisalignedbb), oZgggOgDno(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      buffer.pos(aNN8dfeT4d(axisalignedbb), lZYytFvr7F(axisalignedbb), amZ2ZAYnai(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      buffer.pos(yowWdQkjel(axisalignedbb), VD2tgynqLE(axisalignedbb), jLbwQwgk1g(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      ts.draw();
      buffer.begin(7538 ^ -13999 ^ 20007 ^ -26109, 7F47lLB4Iq());
      buffer.pos(QM3yRV1TFe(axisalignedbb), MUUY9VXNq9(axisalignedbb), XtftKwJeFN(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      buffer.pos(1bzAb1onKG(axisalignedbb), HddyYf9ZQs(axisalignedbb), jIRo99tNwd(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      buffer.pos(aqyY6GzB1f(axisalignedbb), cD66keb1We(axisalignedbb), HjVF0yLmKH(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      buffer.pos(xNJvoe8qTP(axisalignedbb), fovaBRVGLY(axisalignedbb), bje8zmWOgr(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      buffer.pos(l6MELcFyvz(axisalignedbb), DnZ9NN5uMV(axisalignedbb), cRYVLYjugw(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      buffer.pos(iJ36agaBlM(axisalignedbb), aTSYBn6aoC(axisalignedbb), eJWqYmlG2u(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      buffer.pos(dr2G04FEWO(axisalignedbb), Kd8VBeuEl6(axisalignedbb), fwLCpVIjat(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      buffer.pos(V6KGnJ2W9s(axisalignedbb), lq5YCnDz1e(axisalignedbb), QWGEDfWi4o(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      ts.draw();
      buffer.begin(4676 ^ -20384 ^ 10655 ^ -29764, tnen1W7wjL());
      buffer.pos(qcVVGoHUL1(axisalignedbb), BURL1qWdVH(axisalignedbb), SWM6Bg2lvU(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      buffer.pos(gBI6e7lySY(axisalignedbb), dAYt3Jbiw4(axisalignedbb), FyLtE82b4N(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      buffer.pos(IDRjFeSogr(axisalignedbb), ijdQay75jj(axisalignedbb), axI8FVeMiL(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      buffer.pos(yGF77DNref(axisalignedbb), bdorW4SQhB(axisalignedbb), RqT62dfxNS(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      buffer.pos(NzSIiqJi1a(axisalignedbb), tnprJXaeQK(axisalignedbb), vhx9i1oyoG(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      buffer.pos(DWFiEb6oiT(axisalignedbb), FROsNFdd17(axisalignedbb), 5itDvyaBtA(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      buffer.pos(BAmFdktGJa(axisalignedbb), dDze2u0Tnq(axisalignedbb), Aq7OgGTSbB(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      buffer.pos(8XC4cvtgZb(axisalignedbb), Ya8LoSGgKL(axisalignedbb), S3q3Aqt05k(axisalignedbb)).color(red, green, blue, alpha).endVertex();
      ts.draw();
   }

   private static double rU9dGaesXN(AxisAlignedBB var0) {
      return var0.maxX;
   }

   private static double rEBZ9qNC9L(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static double _OtpTEY9SB/* $FF was: 7OtpTEY9SB*/(AxisAlignedBB var0) {
      return var0.minZ;
   }

   private static double WgteL9SVOd(AxisAlignedBB var0) {
      return var0.maxZ;
   }

   private static double nfiHJ5bzNW(AxisAlignedBB var0) {
      return var0.maxZ;
   }

   private static double uRbNqvImD7(AxisAlignedBB var0) {
      return var0.maxZ;
   }

   private static double nOlwXa0C12(AxisAlignedBB var0) {
      return var0.maxX;
   }

   private static double ef1lD4qdig(AxisAlignedBB var0) {
      return var0.maxX;
   }

   private static double wpykjYbJMf(AxisAlignedBB var0) {
      return var0.maxZ;
   }

   private static double GDOVNyjjaS(AxisAlignedBB var0) {
      return var0.maxY;
   }

   private static double gIj9TINtSZ(AxisAlignedBB var0) {
      return var0.maxY;
   }

   private static double Kd8VBeuEl6(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static double aMY60jT3yi(AxisAlignedBB var0) {
      return var0.minZ;
   }

   private static double rLhUTpM72y(AxisAlignedBB var0) {
      return var0.maxX;
   }

   private static double s4g1ai5o9f(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static double wDTNvwbcy3(AxisAlignedBB var0) {
      return var0.minX;
   }

   private static double fwLCpVIjat(AxisAlignedBB var0) {
      return var0.minZ;
   }

   private static HashMap hTWnt2408z() {
      return shadowCache;
   }

   private static double iYyhs2jGOi(AxisAlignedBB var0) {
      return var0.maxX;
   }

   private static double N1N5iwNGsn(Entity var0) {
      return var0.posY;
   }

   private static int qVvRS2FtoO(Minecraft var0) {
      return var0.displayWidth;
   }

   public static void color(Color color) {
      if (color == null) {
         color = o1WTLediHi();
      }

      color((double)((float)color.getRed() / Float.intBitsToFloat('뒗' ^ '\ue250' ^ 29718 ^ -358065045 ^ '\uaa39' ^ '\ue4e3' ^ 6266 ^ -1445517286)), (double)((float)color.getGreen() / Float.intBitsToFloat(14296 ^ 112335 ^ 127285 ^ 1863365838 ^ 3545 ^ 2057410 ^ 2087119 ^ 745502008)), (double)((float)color.getBlue() / Float.intBitsToFloat(1028442 ^ 1025342 ^ 1426 ^ -2065175001 ^ 103891 ^ 106157 ^ 12347 ^ -946289516)), (double)((float)color.getAlpha() / Float.intBitsToFloat('詚' ^ 222530 ^ 26910 ^ 1800785452 ^ '\ud933' ^ 105782 ^ '\ud943' ^ 673764716)));
   }

   private static double hqy46F1tDg(AxisAlignedBB var0) {
      return var0.maxY;
   }

   private static Minecraft GdA5tKIWnB() {
      return mc;
   }

   private static double R2r5aeAKyb(AxisAlignedBB var0) {
      return var0.maxX;
   }

   private static double xO40OJj9sd(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static double bPvYkMKNdU(AxisAlignedBB var0) {
      return var0.maxZ;
   }

   private static double qWXnTYQvdO(AxisAlignedBB var0) {
      return var0.minX;
   }

   public static void glColor(Color color) {
      float red = (float)color.getRed() / Float.intBitsToFloat(2299 ^ '셂' ^ '\uf1ae' ^ -265926058 ^ '덢' ^ '졬' ^ 5552 ^ -1286005505);
      float green = (float)color.getGreen() / Float.intBitsToFloat(2140 ^ 465980 ^ 495578 ^ 45317855 ^ 128787 ^ 127647 ^ 16535 ^ 1103929470);
      float blue = (float)color.getBlue() / Float.intBitsToFloat('흦' ^ '심' ^ 123 ^ -2139526091 ^ 'ꄁ' ^ '駺' ^ 28458 ^ -1023005163);
      float alpha = (float)color.getAlpha() / Float.intBitsToFloat('흰' ^ 14701 ^ '\ue78b' ^ -259768021 ^ '\ue41b' ^ '휊' ^ 28171 ^ -1275390553);
      GlStateManager.color(red, green, blue, alpha);
   }

   private static double tnprJXaeQK(AxisAlignedBB var0) {
      return var0.maxY;
   }

   private static double DLv47LwWji(AxisAlignedBB var0) {
      return var0.minZ;
   }

   private static double nFZtLO0HWj(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static double yGF77DNref(AxisAlignedBB var0) {
      return var0.minX;
   }

   private static double zLll3MGUG5(AxisAlignedBB var0) {
      return var0.maxZ;
   }

   private static int j6a5lUqTqr(Minecraft var0) {
      return var0.displayHeight;
   }

   private static double YyNMJInEaV(AxisAlignedBB var0) {
      return var0.minZ;
   }

   private static Minecraft oEvFoPeNKU() {
      return mc;
   }

   private static double _bzAb1onKG/* $FF was: 1bzAb1onKG*/(AxisAlignedBB var0) {
      return var0.minX;
   }

   private static double l0QoJYL9dN(Entity var0) {
      return var0.posZ;
   }

   public static void drawCircle(float x, float y, float start, float end, float radius, int color, int linewidth) {
      GlStateManager.color(Float.intBitsToFloat(30104 ^ 107390 ^ 30126 ^ -1485527471 ^ 32218 ^ 99753 ^ 28923 ^ -1485533807), Float.intBitsToFloat(15701 ^ '몜' ^ 17491 ^ 736187942 ^ 29157 ^ '\ue7ed' ^ 12275 ^ 736177223), Float.intBitsToFloat('떓' ^ 27879 ^ '︅' ^ 1234247609 ^ '適' ^ 30991 ^ '\ue778' ^ 1234253526), Float.intBitsToFloat('钋' ^ '鲞' ^ 2386 ^ 939217577 ^ '酳' ^ 'ꑖ' ^ 20424 ^ 939206915));
      float i;
      if (start > end) {
         i = end;
         end = start;
         start = i;
      }

      GlStateManager.enableBlend();
      GlStateManager.disableTexture2D();
      enableSmoothLine((float)linewidth);
      GlStateManager.tryBlendFuncSeparate(16680 ^ -20372 ^ 27620 ^ -26206, 17570 ^ -24176 ^ 11988 ^ -14107, 28279 ^ -17079 ^ 11766 ^ -311, 13721 ^ -8959 ^ 25223 ^ -30177);
      GL11.glBegin(24811 ^ -3174 ^ 14227 ^ -23327);

      for(i = end; i >= start; i -= Float.intBitsToFloat(14651 ^ '\uea24' ^ 10890 ^ 1080783111 ^ 29985 ^ '\ue76c' ^ 13751 ^ 15413096)) {
         glHexColor(color, 29037 ^ -18935 ^ 18860 ^ -29129);
         float cos = (float)(Math.cos((double)i * Double.longBitsToDouble(-8665865539155344307L ^ -4056185462860224171L) / Double.longBitsToDouble(-2972471297759758582L ^ -7576979704281024758L)) * (double)radius * Double.longBitsToDouble(5376384022049130640L ^ 8461349766797920400L));
         float sin = (float)(Math.sin((double)i * Double.longBitsToDouble(4694432107353078433L ^ 84680925718501305L) / Double.longBitsToDouble(2560654789493996880L ^ 7201191993034227024L)) * (double)radius * Double.longBitsToDouble(5600373928918632172L ^ 8234979710930372332L));
         GL11.glVertex2f(x + cos, y + sin);
      }

      GL11.glEnd();
      disableSmoothLine();
      GlStateManager.enableTexture2D();
      GlStateManager.disableBlend();
   }

   private static double zMvoOFu1ki(AxisAlignedBB var0) {
      return var0.minX;
   }

   private static double xCxqeDOLGr(AxisAlignedBB var0) {
      return var0.minZ;
   }

   private static double S3q3Aqt05k(AxisAlignedBB var0) {
      return var0.maxZ;
   }

   public static void drawOutlinedBoundingBox(AxisAlignedBB aa) {
      GL11.glEnable('轑' ^ -9631 ^ 'ꉵ' ^ -857);
      GL11.glDisable(30437 ^ -3725 ^ 10847 ^ -24536);
      GL11.glBlendFunc(2129 ^ -11611 ^ 8898 ^ -1228, 29299 ^ -24118 ^ 18627 ^ -26503);
      GL11.glDisable(26550 ^ -28526 ^ 12283 ^ -11377);
      GL11.glPolygonMode(21764 ^ -21381 ^ 7666 ^ -8059, 170 ^ -30538 ^ 9181 ^ -20288);
      GL11.glDisable(15091 ^ -54787 ^ '덺' ^ -21712);
      GL11.glBegin(19572 ^ -5522 ^ 27592 ^ -12843);
      float f = (float)6FJ5nyvbPS(aa);
      float f1 = (float)GbMYm465D2(aa);
      float f2 = (float)1poue6B2Ne(aa);
      float f3 = (float)B6YwqxFwbZ(aa);
      float f4 = (float)G2whNJWdIJ(aa);
      float f5 = (float)avTyQS4I78(aa);
      GL11.glVertex3d((double)f, (double)f3, (double)f4);
      GL11.glVertex3d((double)f, (double)f3, (double)f5);
      GL11.glVertex3d((double)f, (double)f2, (double)f5);
      GL11.glVertex3d((double)f, (double)f2, (double)f4);
      GL11.glVertex3d((double)f1, (double)f3, (double)f4);
      GL11.glVertex3d((double)f1, (double)f3, (double)f5);
      GL11.glVertex3d((double)f1, (double)f2, (double)f5);
      GL11.glVertex3d((double)f1, (double)f2, (double)f4);
      GL11.glVertex3d((double)f1, (double)f3, (double)f4);
      GL11.glVertex3d((double)f1, (double)f3, (double)f5);
      GL11.glVertex3d((double)f, (double)f3, (double)f5);
      GL11.glVertex3d((double)f, (double)f3, (double)f4);
      GL11.glVertex3d((double)f1, (double)f2, (double)f4);
      GL11.glVertex3d((double)f1, (double)f2, (double)f5);
      GL11.glVertex3d((double)f, (double)f2, (double)f5);
      GL11.glVertex3d((double)f, (double)f2, (double)f4);
      GL11.glEnd();
      GL11.glPolygonMode(28639 ^ -11797 ^ 11261 ^ -28223, 23745 ^ 20934 ^ 3998 ^ 6555);
      GL11.glEnable(5557 ^ -31432 ^ 20826 ^ -13677);
      GL11.glEnable(28904 ^ -8564 ^ 18101 ^ -6864);
      GL11.glDisable(2047 ^ -30482 ^ 30779 ^ -824);
   }

   private static double NzSIiqJi1a(AxisAlignedBB var0) {
      return var0.maxX;
   }

   private static double DnZ9NN5uMV(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static double _i0q8EQZDq/* $FF was: 8i0q8EQZDq*/(AxisAlignedBB var0) {
      return var0.maxY;
   }

   private static double QM3yRV1TFe(AxisAlignedBB var0) {
      return var0.minX;
   }

   private static double U5v5KOoNdN(AxisAlignedBB var0) {
      return var0.minZ;
   }

   private static double BfY6wxJUOw(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static double aqyY6GzB1f(AxisAlignedBB var0) {
      return var0.minX;
   }

   private static double bdorW4SQhB(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static double wWRkZCQWhY(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static VertexFormat tWvi7JbWQi() {
      return DefaultVertexFormats.POSITION_COLOR;
   }

   private static double TI4eq20BIJ(AxisAlignedBB var0) {
      return var0.minZ;
   }

   private static double PFT51g4hmo(AxisAlignedBB var0) {
      return var0.maxX;
   }

   private static Timer YqZcJ2Rgm3(Minecraft var0) {
      return var0.timer;
   }

   private static double MB1cNggxPb(AxisAlignedBB var0) {
      return var0.maxY;
   }

   private static VertexFormat eAGBcreoQ1() {
      return DefaultVertexFormats.POSITION;
   }

   private static HashMap EzKQoeyE6G() {
      return shadowCache;
   }

   private static GlStateManager.SourceFactor _9CYD4sb2i/* $FF was: 79CYD4sb2i*/() {
      return GlStateManager.SourceFactor.SRC_ALPHA;
   }

   public static void drawImage(ResourceLocation resourceLocation, float x, float y, float width, float height, Color color) {
      GL11.glDisable(23801 ^ -9247 ^ 7244 ^ -28635);
      GL11.glEnable(26974 ^ -19724 ^ 24189 ^ -29131);
      GL11.glDepthMask((boolean)(4816 ^ -22499 ^ 7869 ^ -23440));
      OpenGlHelper.glBlendFunc(29994 ^ -2521 ^ 11618 ^ -21139, 18531 ^ -26922 ^ 14630 ^ -7024, 18332 ^ -16359 ^ 7714 ^ -26202, 25679 ^ -2104 ^ 30171 ^ -6564);
      setColor(color.getRGB());
      Minecraft.getMinecraft().getTextureManager().bindTexture(resourceLocation);
      Gui.drawModalRectWithCustomSizedTexture((int)x, (int)y, Float.intBitsToFloat(126408 ^ 81409 ^ 18756 ^ -1448051176 ^ 114059 ^ 121046 ^ 6503 ^ -1448044369), Float.intBitsToFloat('﹨' ^ 2075269 ^ 'ﮁ' ^ -1299188695 ^ '핮' ^ '镍' ^ 5725 ^ -1299182277), (int)width, (int)height, width, height);
      GL11.glDepthMask((boolean)(2901 ^ -29227 ^ 14097 ^ -20080));
      GL11.glDisable(15978 ^ -27824 ^ 7188 ^ -17716);
      GL11.glEnable('轇' ^ -2339 ^ '퉵' ^ -24418);
      GlStateManager.disableBlend();
   }

   private static double _yy4CirwyH/* $FF was: 3yy4CirwyH*/(AxisAlignedBB var0) {
      return var0.maxX;
   }

   private static double amZ2ZAYnai(AxisAlignedBB var0) {
      return var0.maxZ;
   }

   private static VertexFormat GPnCwS6AFc() {
      return DefaultVertexFormats.POSITION;
   }

   public static void resetColor() {
      color(Double.longBitsToDouble(1759473628506410999L ^ 2853848337957441527L), Double.longBitsToDouble(6302986310287811373L ^ 7532469008559956781L), Double.longBitsToDouble(11923971592273782L ^ 4601091991882809206L), Double.longBitsToDouble(-2151197232612493143L ^ -2461945606901057367L));
   }

   public static void drawFCircle(float x, float y, float start, float end, float radius, boolean filled, Color color) {
      color(Double.longBitsToDouble(5478039610424090069L ^ 5478039610424090069L), Double.longBitsToDouble(-8375501702444937529L ^ -8375501702444937529L), Double.longBitsToDouble(7116650696207081689L ^ 7116650696207081689L), Double.longBitsToDouble(-6940143294674109420L ^ -6940143294674109420L));
      if (start > end) {
         float endOffset = end;
         end = start;
         start = endOffset;
      }

      GlStateManager.enableBlend();
      GlStateManager.disableTexture2D();
      GlStateManager.tryBlendFuncSeparate(15257 ^ -21550 ^ 6525 ^ -30156, 32315 ^ -31083 ^ 17499 ^ -16394, 12565 ^ -1627 ^ 14563 ^ -4014, 17829 ^ -9536 ^ 481 ^ -24956);
      setColor(color.getRGB());
      GL11.glEnable(17932 ^ -22317 ^ 21374 ^ -18815);
      GL11.glLineWidth(Float.intBitsToFloat(16103 ^ 86876 ^ 121702 ^ -594910351 ^ 8652 ^ 89698 ^ 121753 ^ -1668648549));
      GL11.glBegin(25255 ^ -24766 ^ 11663 ^ -12183);

      float sin;
      float cos;
      float i;
      for(i = end; i >= start; i -= Float.intBitsToFloat(101547 ^ 82914 ^ 30198 ^ -2009177293 ^ 128596 ^ 124233 ^ 28489 ^ -927029800)) {
         cos = (float)(Math.cos((double)i * Double.longBitsToDouble(6854463038648130775L ^ 2240206745163968975L) / Double.longBitsToDouble(943446865845477566L ^ 5580324894688469182L)) * (double)radius * Double.longBitsToDouble(-1723877184097198940L ^ -2890309487586157404L));
         sin = (float)(Math.sin((double)i * Double.longBitsToDouble(57220944887335054L ^ 4666410766109042070L) / Double.longBitsToDouble(-6464818071900518837L ^ -1860309665379252661L)) * (double)radius * Double.longBitsToDouble(5811428739975166486L ^ 8022696157014080022L));
         GL11.glVertex2f(x + cos, y + sin);
      }

      GL11.glEnd();
      GL11.glDisable(22365 ^ -10298 ^ 19891 ^ -14840);
      GL11.glEnable(14255 ^ -43591 ^ '먩' ^ -11489);
      GL11.glBegin(filled ? 18068 ^ -10302 ^ 1248 ^ -27216 : 17746 ^ -22959 ^ 18579 ^ -21613);

      for(i = end; i >= start; i -= Float.intBitsToFloat(23650 ^ 521624 ^ 5444 ^ 366191893 ^ '馇' ^ 505643 ^ '\uf69a' ^ 1431552413)) {
         cos = (float)Math.cos((double)i * Double.longBitsToDouble(-947900535543485955L ^ -5561593114570586907L) / Double.longBitsToDouble(5640553346428375749L ^ 1018312016374338245L)) * radius;
         sin = (float)Math.sin((double)i * Double.longBitsToDouble(-602947727987606773L ^ -5212696910305995245L) / Double.longBitsToDouble(-4699178812161877859L ^ -94670405640611683L)) * radius;
         GL11.glVertex2f(x + cos, y + sin);
      }

      GL11.glEnd();
      GL11.glDisable(13439 ^ -40691 ^ '릚' ^ -6200);
      GlStateManager.enableTexture2D();
      GlStateManager.disableBlend();
   }

   public static void drawSelectionBoundingBox(AxisAlignedBB boundingBox) {
      Tessellator tessellator = Tessellator.getInstance();
      BufferBuilder builder = tessellator.getBuffer();
      builder.begin(14941 ^ -17256 ^ 12763 ^ -18659, GPnCwS6AFc());
      builder.pos(vAoYA2ZaJb(boundingBox), xO40OJj9sd(boundingBox), w6ylEtzPJq(boundingBox)).endVertex();
      builder.pos(3yy4CirwyH(boundingBox), 9L2hHWSf2w(boundingBox), Q4BYWJ9AR2(boundingBox)).endVertex();
      builder.pos(yAFDLSXk7n(boundingBox), Zq4JwdevlI(boundingBox), PTRYkNqV6v(boundingBox)).endVertex();
      builder.pos(ov40MBVAjW(boundingBox), 9iOFWtjOTZ(boundingBox), zLll3MGUG5(boundingBox)).endVertex();
      builder.pos(VaTrAJ3157(boundingBox), aB9Ji6MGUm(boundingBox), YyNMJInEaV(boundingBox)).endVertex();
      tessellator.draw();
      builder.begin(11009 ^ -4492 ^ 9869 ^ -7173, f2XtYdznqV());
      builder.pos(vNG4F7jy5C(boundingBox), 8i0q8EQZDq(boundingBox), HBST0NeyvJ(boundingBox)).endVertex();
      builder.pos(IFqJ4UJAnD(boundingBox), BbEaPpkBnk(boundingBox), 7OtpTEY9SB(boundingBox)).endVertex();
      builder.pos(ALfjz9WotO(boundingBox), deEd67pjJO(boundingBox), jIOW2S6gDu(boundingBox)).endVertex();
      builder.pos(I5r24egsot(boundingBox), B1DXWPtYOI(boundingBox), uRbNqvImD7(boundingBox)).endVertex();
      builder.pos(Fal6DiHNFB(boundingBox), jdF99L2HdV(boundingBox), iuooWbxyxT(boundingBox)).endVertex();
      tessellator.draw();
      builder.begin(13374 ^ -9907 ^ 7117 ^ -2369, eAGBcreoQ1());
      builder.pos(qgB3SdpDiW(boundingBox), WQTlN5ysrW(boundingBox), LkvMqBAr1n(boundingBox)).endVertex();
      builder.pos(OnU2L9Jvqa(boundingBox), 8IqB4Ig0Te(boundingBox), xCxqeDOLGr(boundingBox)).endVertex();
      builder.pos(Xw4xmtvbjw(boundingBox), NDbpR9ZZlw(boundingBox), tsJeAqTg2O(boundingBox)).endVertex();
      builder.pos(rLhUTpM72y(boundingBox), tO5qro87P2(boundingBox), wnyvel5str(boundingBox)).endVertex();
      builder.pos(wObfaJsF9F(boundingBox), urV26yrCuV(boundingBox), GHJeWnxlqD(boundingBox)).endVertex();
      builder.pos(9PfC3Y9GwA(boundingBox), 4UwbrWzYV7(boundingBox), nfiHJ5bzNW(boundingBox)).endVertex();
      builder.pos(2GBcsejSD7(boundingBox), wWRkZCQWhY(boundingBox), o4Qjahgmy7(boundingBox)).endVertex();
      builder.pos(SA6OVSItDn(boundingBox), GDOVNyjjaS(boundingBox), wpykjYbJMf(boundingBox)).endVertex();
      tessellator.draw();
   }

   private static double IDRjFeSogr(AxisAlignedBB var0) {
      return var0.minX;
   }

   private static double vNG4F7jy5C(AxisAlignedBB var0) {
      return var0.minX;
   }

   private static double Zq4JwdevlI(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static double SWM6Bg2lvU(AxisAlignedBB var0) {
      return var0.maxZ;
   }

   private static double ALfjz9WotO(AxisAlignedBB var0) {
      return var0.maxX;
   }

   private static double iJ36agaBlM(AxisAlignedBB var0) {
      return var0.maxX;
   }

   private static double _rINRyLtbJ/* $FF was: 5rINRyLtbJ*/(AxisAlignedBB var0) {
      return var0.maxY;
   }

   private static VertexFormat ZxdOttl4nl() {
      return DefaultVertexFormats.POSITION_TEX;
   }

   private static double j4ztFKzbSt(AxisAlignedBB var0) {
      return var0.maxX;
   }

   private static double yAFDLSXk7n(AxisAlignedBB var0) {
      return var0.maxX;
   }

   private static double Mo8dFn20jQ(AxisAlignedBB var0) {
      return var0.maxY;
   }

   private static double xrelR1AQD9(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static double GbMYm465D2(AxisAlignedBB var0) {
      return var0.maxX;
   }

   private static double TwrqBtE1YV(AxisAlignedBB var0) {
      return var0.minX;
   }

   private static VertexFormat hN5m9NAbhA() {
      return DefaultVertexFormats.POSITION_TEX;
   }

   private static double deEd67pjJO(AxisAlignedBB var0) {
      return var0.maxY;
   }

   private static HashMap oy459rrtQz() {
      return shadowCache;
   }

   public static void drawGradientRect(double d, double e, double e2, double g, int startColor, int endColor) {
      float f = (float)(startColor >> (8477 ^ -30567 ^ 7877 ^ -18599) & (17531 ^ -30611 ^ 14542 ^ -3033)) / Float.intBitsToFloat('镜' ^ 8336086 ^ 8560 ^ 1732230571 ^ 23549 ^ 96318 ^ '좴' ^ 608096294);
      float f1 = (float)(startColor >> (20847 ^ -21748 ^ 16902 ^ -18315) & (431 ^ -22656 ^ 10170 ^ -32406)) / Float.intBitsToFloat(17748 ^ 233500 ^ 992 ^ 169199495 ^ 25399 ^ 109776 ^ 11241 ^ 1231615265);
      float f2 = (float)(startColor >> (17847 ^ -18435 ^ 28721 ^ -32141) & (18989 ^ -23677 ^ 7273 ^ -2760)) / Float.intBitsToFloat(239654 ^ 259720 ^ 6689 ^ 815113522 ^ 124829 ^ 83990 ^ 9121 ^ 1944743319);
      float f3 = (float)(startColor & (27095 ^ -27291 ^ 28623 ^ -27774)) / Float.intBitsToFloat('달' ^ 20404 ^ '쁫' ^ -898688349 ^ 23995 ^ 30954 ^ 8837 ^ -1995431868);
      float f4 = (float)(endColor >> (20678 ^ -7980 ^ 8924 ^ -27946) & (13396 ^ -5051 ^ 4354 ^ -13844)) / Float.intBitsToFloat(8529 ^ 1024174 ^ 21576 ^ -521675921 ^ 12070 ^ 104785 ^ 31002 ^ -1550401099);
      float f5 = (float)(endColor >> (23715 ^ -30342 ^ 26665 ^ -16928) & (18347 ^ -6887 ^ 533 ^ -24488)) / Float.intBitsToFloat(2072188 ^ 2054267 ^ 5578 ^ -1968215887 ^ 27391 ^ 21144 ^ 11668 ^ -909070705);
      float f6 = (float)(endColor >> (5030 ^ -1835 ^ 28635 ^ -31584) & (25079 ^ -22434 ^ 6864 ^ -11386)) / Float.intBitsToFloat('횟' ^ 25857 ^ '쇷' ^ 1155406924 ^ '퇊' ^ '辱' ^ 7073 ^ 128003071);
      float f7 = (float)(endColor & (7117 ^ -31384 ^ 14290 ^ -22136)) / Float.intBitsToFloat(25947 ^ 104646 ^ 10453 ^ -327364232 ^ 11251 ^ 259065 ^ 711 ^ -1358831875);
      GlStateManager.disableTexture2D();
      GlStateManager.enableBlend();
      GlStateManager.disableAlpha();
      GlStateManager.tryBlendFuncSeparate(SwUZ9vu9rf(), nNzO8rUu9T(), nj1hcVZSji(), iYWhzryXN1());
      GlStateManager.shadeModel(26688 ^ -8661 ^ 6330 ^ -19504);
      Tessellator tessellator = Tessellator.getInstance();
      BufferBuilder bufferbuilder = tessellator.getBuffer();
      bufferbuilder.begin(71 ^ -7230 ^ 17055 ^ -24291, tWvi7JbWQi());
      bufferbuilder.pos(e2, e, Double.longBitsToDouble(1801626381507785765L ^ 1801626381507785765L)).color(f1, f2, f3, f).endVertex();
      bufferbuilder.pos(d, e, Double.longBitsToDouble(-6985023707396590970L ^ -6985023707396590970L)).color(f1, f2, f3, f).endVertex();
      bufferbuilder.pos(d, g, Double.longBitsToDouble(-140733341223721615L ^ -140733341223721615L)).color(f5, f6, f7, f4).endVertex();
      bufferbuilder.pos(e2, g, Double.longBitsToDouble(-4573439992073761804L ^ -4573439992073761804L)).color(f5, f6, f7, f4).endVertex();
      tessellator.draw();
      GlStateManager.shadeModel(14825 ^ -33756 ^ '뛴' ^ -4551);
      GlStateManager.disableBlend();
      GlStateManager.enableAlpha();
      GlStateManager.enableTexture2D();
   }

   private static double jIOW2S6gDu(AxisAlignedBB var0) {
      return var0.maxZ;
   }

   private static double DWFiEb6oiT(AxisAlignedBB var0) {
      return var0.maxX;
   }

   private static Minecraft tqlJgjLey2() {
      return mc;
   }

   private static double KcLIqb4J59(AxisAlignedBB var0) {
      return var0.minX;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String NtMnA66W0J(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 17240 ^ -18614 ^ 18101 ^ -19801; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 31045 ^ -16347 ^ 29093 ^ -14076));
      }

      return var1.toString();
   }

   private static double _seztJaWQF/* $FF was: 4seztJaWQF*/(AxisAlignedBB var0) {
      return var0.minZ;
   }

   private static double vdRQAIBqBQ(Entity var0) {
      return var0.posZ;
   }

   private static double etqWm1yKbQ(AxisAlignedBB var0) {
      return var0.minX;
   }

   private static double Aq7OgGTSbB(AxisAlignedBB var0) {
      return var0.maxZ;
   }

   private static double FyLtE82b4N(AxisAlignedBB var0) {
      return var0.maxZ;
   }

   private static double y7vv107lTN(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static double VaTrAJ3157(AxisAlignedBB var0) {
      return var0.minX;
   }

   private static double oZgggOgDno(AxisAlignedBB var0) {
      return var0.maxZ;
   }

   private static double kcS0OG6jqk(Entity var0) {
      return var0.posX;
   }

   private static double _7JOqq4Nq1/* $FF was: 17JOqq4Nq1*/(AxisAlignedBB var0) {
      return var0.maxX;
   }

   private static double AsoA9Di1XQ(AxisAlignedBB var0) {
      return var0.minZ;
   }

   private static double ijdQay75jj(AxisAlignedBB var0) {
      return var0.maxY;
   }

   private static double wnyvel5str(AxisAlignedBB var0) {
      return var0.minZ;
   }

   public static void drawHLine(double x, double y, double x1, double y1, float width, int color) {
      float alpha = (float)(color >> (4553 ^ -1772 ^ 4408 ^ -1539) & (18978 ^ -15278 ^ 23195 ^ -11244)) / Float.intBitsToFloat('\ue4c6' ^ '趐' ^ 2432 ^ -55107789 ^ '\uee05' ^ '閦' ^ 11744 ^ -1077401178);
      float red = (float)(color >> (26623 ^ -4799 ^ 26615 ^ -4775) & (30507 ^ -13561 ^ 4847 ^ -20932)) / Float.intBitsToFloat(4629 ^ 80155 ^ '鑃' ^ -1029770440 ^ '썚' ^ '빎' ^ 12049 ^ -2116018576);
      float green = (float)(color >> (7188 ^ -4262 ^ 17616 ^ -18538) & (30662 ^ -5597 ^ 9450 ^ -17936)) / Float.intBitsToFloat(2040 ^ '닡' ^ '윤' ^ 1121784221 ^ 3979 ^ '\uf876' ^ '\uf6c6' ^ 27419291);
      float blue = (float)(color & (28714 ^ -20227 ^ 19646 ^ -29546)) / Float.intBitsToFloat('\uf671' ^ '鈒' ^ 495 ^ 1786001447 ^ 14936 ^ '헙' ^ '\ue35d' ^ 688607607);
      GlStateManager.enableBlend();
      GlStateManager.disableTexture2D();
      GlStateManager.tryBlendFuncSeparate(14878 ^ -11266 ^ 30972 ^ -28130, 24537 ^ -7338 ^ 1905 ^ -18179, 17156 ^ -26673 ^ 19980 ^ -25914, 11728 ^ -12055 ^ 19480 ^ -20191);
      GlStateManager.color(red, green, blue, alpha);
      GL11.glPushMatrix();
      GL11.glLineWidth(width);
      GL11.glBegin(12503 ^ -10007 ^ 6377 ^ -3884);
      GL11.glVertex2d(x, y);
      GL11.glVertex2d(x1, y1);
      GL11.glEnd();
      GL11.glLineWidth(Float.intBitsToFloat(126168 ^ 108220 ^ 118 ^ 1489155730 ^ 100528 ^ 111467 ^ 11359 ^ 1732438788));
      GL11.glPopMatrix();
      GlStateManager.enableTexture2D();
      GlStateManager.disableBlend();
      GlStateManager.color(Float.intBitsToFloat(26774 ^ 99876 ^ 31582 ^ -1158497305 ^ 5154 ^ 116733 ^ 17617 ^ -2056079099), Float.intBitsToFloat(6809 ^ '\uf222' ^ 28984 ^ 893071862 ^ 23982 ^ 'ꥻ' ^ 15752 ^ 180052264), Float.intBitsToFloat(13483 ^ 81600 ^ '릻' ^ 1095548675 ^ 8623 ^ 22181 ^ 29486 ^ 2127366391), Float.intBitsToFloat(31419 ^ '뾹' ^ 7048 ^ 1036607239 ^ 23072 ^ '飭' ^ 18499 ^ 38341379));
   }

   private static double W51D62tVBO(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static double F33FvQBfDl(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static double FszdhbbliQ(AxisAlignedBB var0) {
      return var0.minX;
   }

   private static double B6FpGqxfLN(AxisAlignedBB var0) {
      return var0.minX;
   }

   private static double JPVgI8GeHc(AxisAlignedBB var0) {
      return var0.minX;
   }

   private static double weCJEoG22O(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static Minecraft AgZSG2K3LD() {
      return mc;
   }

   private static double _poue6B2Ne/* $FF was: 1poue6B2Ne*/(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static Minecraft _svbmowGQJ/* $FF was: 7svbmowGQJ*/() {
      return mc;
   }

   private static double iYVt1Fe2Jt(Entity var0) {
      return var0.lastTickPosY;
   }

   private static double BG4j4aimxw(AxisAlignedBB var0) {
      return var0.maxZ;
   }

   private static double NrdPrQ841d(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static double oIAsTaV2OQ(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static double gVZMOhEayv(AxisAlignedBB var0) {
      return var0.maxY;
   }

   private static double Qpr7adL6xn(AxisAlignedBB var0) {
      return var0.maxY;
   }

   private static double o4Qjahgmy7(AxisAlignedBB var0) {
      return var0.maxZ;
   }

   private static VertexFormat f2XtYdznqV() {
      return DefaultVertexFormats.POSITION;
   }

   public static void drawEntityBox(Entity entity, Color color, boolean fullBox, float alpha) {
      GlStateManager.pushMatrix();
      GlStateManager.blendFunc(23754 ^ -11286 ^ 28399 ^ -7475, 17478 ^ -11752 ^ 28871 ^ -6758);
      GL11.glEnable(6887 ^ -10521 ^ 3310 ^ -13556);
      GlStateManager.glLineWidth(Float.intBitsToFloat(19977 ^ 18743 ^ 7639 ^ 1831864129 ^ 21010 ^ 2072128 ^ 2030 ^ 1387255316));
      GlStateManager.disableTexture2D();
      GL11.glDisable(29818 ^ -10636 ^ 4650 ^ -17579);
      GlStateManager.depthMask((boolean)(28780 ^ -4125 ^ 20986 ^ -12683));
      double var10000 = FtWVP8EV41(entity) + (kcS0OG6jqk(entity) - OWReMBAlXS(entity)) * (double)2xe4BLLESD(rrHOPNoMvV(GdA5tKIWnB()));
      7svbmowGQJ().getRenderManager();
      double x = var10000 - RAYYFjB4aT();
      var10000 = iYVt1Fe2Jt(entity) + (HuonNiG4Ds(entity) - noPBlYNdDx(entity)) * (double)IAgnACYdux(YqZcJ2Rgm3(fOD7C1PExo()));
      tqlJgjLey2().getRenderManager();
      double y = var10000 - qmS2gKWIe8();
      var10000 = 1rINMaghTD(entity) + (vdRQAIBqBQ(entity) - VXy1b5VF7m(entity)) * (double)QAodYLvKat(DdQUxSuK4C(a8a4XInjUT()));
      AgZSG2K3LD().getRenderManager();
      double z = var10000 - OwumBKj6vP();
      AxisAlignedBB axisAlignedBB = entity.getEntityBoundingBox();
      AxisAlignedBB axisAlignedBB2 = new AxisAlignedBB(etqWm1yKbQ(axisAlignedBB) - RLgAQTqbG9(entity) + x - Double.longBitsToDouble(-7962213942434913878L ^ -5897163531306354640L), y7vv107lTN(axisAlignedBB) - N1N5iwNGsn(entity) + y, J6dZ4mFDY9(axisAlignedBB) - l0QoJYL9dN(entity) + z - Double.longBitsToDouble(-5562320529590018703L ^ -8257574855034731285L), HxwInvDVMJ(axisAlignedBB) - SLXDtE0TlB(entity) + x + Double.longBitsToDouble(7496216163404150211L ^ 6318112103876488281L), 5rINRyLtbJ(axisAlignedBB) - Gh6UFyEn9g(entity) + y + Double.longBitsToDouble(7401356538752785861L ^ 6446293453258944246L), ZjTdYuJtuj(axisAlignedBB) - yyPRDtbcFZ(entity) + z + Double.longBitsToDouble(4842721147770362910L ^ 8979426279401342340L));
      GlStateManager.glLineWidth(Float.intBitsToFloat(14628 ^ '舀' ^ 21711 ^ 1467311319 ^ 29057 ^ '넰' ^ 23501 ^ 1760890944));
      GL11.glEnable(6515 ^ -9923 ^ 10058 ^ -5084);
      GlStateManager.color((float)color.getRed() / Float.intBitsToFloat(24435 ^ 244471 ^ 3129 ^ -2030442852 ^ 21604 ^ 126406 ^ 25891 ^ -981150816), (float)color.getGreen() / Float.intBitsToFloat('馱' ^ '萎' ^ 24265 ^ 333650823 ^ '\ue661' ^ '\uf5fa' ^ 30667 ^ 1352416417), (float)color.getBlue() / Float.intBitsToFloat('잗' ^ 26235 ^ '췙' ^ 1697856299 ^ '쒘' ^ 494989 ^ '\uecbd' ^ 642511542), alpha);
      drawSelectionBoundingBox(axisAlignedBB2);
      GlStateManager.glLineWidth(Float.intBitsToFloat(30301 ^ 6951 ^ 25209 ^ -267123123 ^ 18389 ^ 496251 ^ 13633 ^ -812389983));
      GlStateManager.enableTexture2D();
      GL11.glEnable(15636 ^ -16863 ^ 29027 ^ -1753);
      GlStateManager.depthMask((boolean)(6497 ^ -19129 ^ 23424 ^ -2137));
      GlStateManager.disableBlend();
      GlStateManager.popMatrix();
   }

   public static void drawRegionESP(double x, double y, double z, double xx, double yy, double zz, float red, float green, float blue, float alpha, float lineRed, float lineGreen, float lineBlue, float lineAlpha, float lineWidth) {
      GL11.glPushMatrix();
      GL11.glEnable(14928 ^ -3052 ^ 2720 ^ -12538);
      GL11.glBlendFunc(7905 ^ -8328 ^ 23921 ^ -24598, 5564 ^ -25783 ^ 27358 ^ -6360);
      GL11.glDisable(11144 ^ -24412 ^ 28086 ^ -5253);
      GL11.glEnable(30195 ^ -17061 ^ 16382 ^ -906);
      GL11.glEnable(24998 ^ -21570 ^ 529 ^ -15496);
      GL11.glColor4f(red, green, blue, alpha);
      GL11.glLineWidth(lineWidth);
      GL11.glColor4f(lineRed, lineGreen, lineBlue, lineAlpha);
      drawOutlinedBoundingBox(new AxisAlignedBB(x, y, z, xx, yy, zz));
      GL11.glDisable(11806 ^ -31399 ^ 966 ^ -23647);
      GL11.glEnable(7272 ^ -13647 ^ 7686 ^ -15042);
      GL11.glEnable(12583 ^ -7245 ^ 538 ^ -9217);
      GL11.glDisable(21442 ^ -18477 ^ 30444 ^ -26337);
      GL11.glPopMatrix();
   }

   private static double _L2hHWSf2w/* $FF was: 9L2hHWSf2w*/(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static HashMap ntOSwqiwYw() {
      return shadowCache;
   }

   private static Color aWErb13g1V() {
      return Color.WHITE;
   }

   private static double _iOFWtjOTZ/* $FF was: 9iOFWtjOTZ*/(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static double bje8zmWOgr(AxisAlignedBB var0) {
      return var0.maxZ;
   }

   private static double HddyYf9ZQs(AxisAlignedBB var0) {
      return var0.maxY;
   }

   private static double cD66keb1We(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static int rbyCBwLrbW(Framebuffer var0) {
      return var0.framebufferWidth;
   }

   private static double s7gmOWW7Si(AxisAlignedBB var0) {
      return var0.minX;
   }

   private static double MUUY9VXNq9(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static VertexFormat AX3UBea2VQ() {
      return DefaultVertexFormats.POSITION;
   }

   private static GlStateManager.SourceFactor gDD6tHlOiB() {
      return GlStateManager.SourceFactor.SRC_ALPHA;
   }

   private static double aB9Ji6MGUm(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static double avTyQS4I78(AxisAlignedBB var0) {
      return var0.maxZ;
   }

   public static void enableSmoothLine(float width) {
      GL11.glDisable('跐' ^ -22954 ^ '\uf7f3' ^ -10315);
      GL11.glEnable(24641 ^ -25653 ^ 11079 ^ -9425);
      GL11.glBlendFunc(32011 ^ -26761 ^ 1857 ^ -4545, 4337 ^ -27147 ^ 26443 ^ -7860);
      GL11.glDisable(4369 ^ -31541 ^ 23937 ^ -14918);
      GL11.glDisable(32456 ^ -9754 ^ 21571 ^ -2020);
      GL11.glDepthMask((boolean)(31279 ^ -24776 ^ 20739 ^ -19436));
      GL11.glEnable('荗' ^ -12385 ^ '雅' ^ -11959);
      GL11.glEnable(1404 ^ -22814 ^ 25327 ^ -13743);
      GL11.glHint(32673 ^ 19359 ^ 14510 ^ 194, 29933 ^ -26617 ^ 12993 ^ -12503);
      GL11.glHint(22096 ^ -60576 ^ '퉣' ^ -25856, 26342 ^ -11542 ^ 2933 ^ -20869);
      GL11.glLineWidth(width);
   }

   private static double GHJeWnxlqD(AxisAlignedBB var0) {
      return var0.maxZ;
   }

   private static double XtftKwJeFN(AxisAlignedBB var0) {
      return var0.minZ;
   }

   private static double aNN8dfeT4d(AxisAlignedBB var0) {
      return var0.maxX;
   }

   private static double _3Ro9mGt1z/* $FF was: 53Ro9mGt1z*/(AxisAlignedBB var0) {
      return var0.maxZ;
   }

   private static int XizpnZvxG6(Minecraft var0) {
      return var0.displayHeight;
   }

   public static void drawBlurredShadow(float x, float y, float width, float height, int blurRadius, Color color) {
      GL11.glPushMatrix();
      GlStateManager.alphaFunc(22041 ^ -13837 ^ 2264 ^ -27338, Float.intBitsToFloat(248929 ^ 26482 ^ 258966 ^ -447901572 ^ '鸕' ^ 211591 ^ 847 ^ -647120850));
      width += (float)(blurRadius * (15330 ^ -28037 ^ 23167 ^ -3100));
      height += (float)(blurRadius * (15365 ^ -2929 ^ 27856 ^ -23464));
      x -= (float)blurRadius;
      y -= (float)blurRadius;
      float _X = x - Float.intBitsToFloat(3090 ^ '\uecf2' ^ 6643 ^ 1508015346 ^ 4242 ^ '댳' ^ 21588 ^ 1734504980);
      float _Y = y + Float.intBitsToFloat(99449 ^ 118824 ^ 21276 ^ -1983720804 ^ 120941 ^ 86615 ^ 8309 ^ -1220378722);
      double identifier = (double)((int)((double)(width * height * Float.intBitsToFloat('켽' ^ 102414 ^ '\ue47b' ^ -18548550 ^ 17266 ^ 24417 ^ 14241 ^ -1196746688)) / Math.sin((double)blurRadius)));
      GL11.glEnable(8050 ^ -49510 ^ '飵' ^ -19204);
      GL11.glDisable(8830 ^ -58673 ^ 'ꇡ' ^ -28140);
      GL11.glEnable(31954 ^ -28176 ^ 11572 ^ -13354);
      GlStateManager.enableBlend();
      int texId = -28525 ^ -7776 ^ 24554 ^ -11994;
      if (oy459rrtQz().containsKey(identifier)) {
         texId = (Integer)ntOSwqiwYw().get(identifier);
         GlStateManager.bindTexture(texId);
      } else {
         BufferedImage original = new BufferedImage((int)width, (int)height, 19217 ^ -19381 ^ 21939 ^ -21781);
         Graphics g = original.getGraphics();
         g.setColor(aWErb13g1V());
         g.fillRect(blurRadius, blurRadius, (int)(width - (float)(blurRadius * (5143 ^ -12112 ^ 11802 ^ -5441))), (int)(height - (float)(blurRadius * (12361 ^ -2356 ^ 17180 ^ -31333))));
         g.dispose();
         GaussianFilter op = new GaussianFilter((float)blurRadius);
         BufferedImage blurred = op.filter(original, (BufferedImage)null);
         texId = TextureUtil.uploadTextureImageAllocate(TextureUtil.glGenTextures(), blurred, (boolean)(20806 ^ -24628 ^ 31030 ^ -18499), (boolean)(19141 ^ -7430 ^ 4803 ^ -17668));
         aI9DNk2gb7().put(identifier, texId);
      }

      GL11.glColor4f((float)color.getRed() / Float.intBitsToFloat(508926 ^ 479840 ^ 1852 ^ 777221170 ^ 125581 ^ 72900 ^ 26891 ^ 1831632338), (float)color.getGreen() / Float.intBitsToFloat(14948 ^ 14670 ^ 3155 ^ 1003538260 ^ 2084928 ^ '蟆' ^ 2091723 ^ 2024764768), (float)color.getBlue() / Float.intBitsToFloat(2297 ^ 119619 ^ 102151 ^ 1465692837 ^ 504552 ^ '訉' ^ 502235 ^ 337868578), (float)color.getAlpha() / Float.intBitsToFloat(6338 ^ 123701 ^ 12641 ^ 838254633 ^ 31630 ^ 1028689 ^ 7078 ^ 1921505478));
      GL11.glBegin(32640 ^ -1900 ^ 9083 ^ -23448);
      GL11.glTexCoord2f(Float.intBitsToFloat(112137 ^ '麕' ^ 117517 ^ -823246111 ^ 113608 ^ 16240 ^ 106653 ^ -823229099), Float.intBitsToFloat('큭' ^ 232147 ^ '\ue3f2' ^ 1998973391 ^ '裏' ^ '胄' ^ 12703 ^ 1998985279));
      GL11.glVertex2f(_X, _Y);
      GL11.glTexCoord2f(Float.intBitsToFloat('\uf03a' ^ '븖' ^ 15909 ^ -1444990948 ^ '\ue405' ^ 31163 ^ '켫' ^ -1444998528), Float.intBitsToFloat(118408 ^ 90316 ^ 12205 ^ 658793420 ^ 128040 ^ 120197 ^ 4204 ^ 415552484));
      GL11.glVertex2f(_X, _Y + height);
      GL11.glTexCoord2f(Float.intBitsToFloat(111341 ^ 82652 ^ 7955 ^ -1810479757 ^ 31263 ^ '\ude10' ^ 12449 ^ -1416216833), Float.intBitsToFloat(17988 ^ 87644 ^ 121303 ^ 8941989 ^ 32569 ^ '輑' ^ 3445 ^ 1057506615));
      GL11.glVertex2f(_X + width, _Y + height);
      GL11.glTexCoord2f(Float.intBitsToFloat(26576 ^ 'ꘝ' ^ 2258 ^ 308573630 ^ 114125 ^ 26327 ^ 130755 ^ 769956216), Float.intBitsToFloat('變' ^ '\ud9fa' ^ 10220 ^ -751343569 ^ 'ꉔ' ^ 29488 ^ '렒' ^ -751368817));
      GL11.glVertex2f(_X + width, _Y);
      GL11.glEnd();
      GlStateManager.enableTexture2D();
      GlStateManager.disableBlend();
      GL11.glEnable(26442 ^ -7734 ^ 7215 ^ -28181);
      GlStateManager.resetColor();
      GL11.glPopMatrix();
   }

   private static double OQjPOYh3EB(AxisAlignedBB var0) {
      return var0.maxY;
   }

   private static float QAodYLvKat(Timer var0) {
      return var0.renderPartialTicks;
   }

   private static double _2iDjyH61d/* $FF was: 12iDjyH61d*/(AxisAlignedBB var0) {
      return var0.maxY;
   }

   private static VertexFormat XJNG8S71AB() {
      return DefaultVertexFormats.POSITION_TEX;
   }

   private static double Gh6UFyEn9g(Entity var0) {
      return var0.posY;
   }

   private static double dAYt3Jbiw4(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static double yyPRDtbcFZ(Entity var0) {
      return var0.posZ;
   }

   private static double jmZQkoONDP(AxisAlignedBB var0) {
      return var0.minX;
   }

   private static double _swoJF2waT/* $FF was: 9swoJF2waT*/(AxisAlignedBB var0) {
      return var0.minX;
   }

   private static double _XC4cvtgZb/* $FF was: 8XC4cvtgZb*/(AxisAlignedBB var0) {
      return var0.maxX;
   }
}
