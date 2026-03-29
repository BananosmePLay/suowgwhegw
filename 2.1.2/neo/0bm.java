package neo;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class 0bm extends GuiButton {
   public int opacity;

   private static int NWWmNtrjrd(0bm var0) {
      return var0.opacity;
   }

   private static int _2YWV3eLAu/* $FF was: 62YWV3eLAu*/(0bm var0) {
      return var0.height;
   }

   private static int lDIo30qXlb(0bm var0) {
      return var0.x;
   }

   private static int iadYB9CI9w(0bm var0) {
      return var0.opacity;
   }

   private static int XtQ4Ihx6pd(0bm var0) {
      return var0.y;
   }

   private static void lOl41cFzrS(0bm var0, int var1) {
      var0.opacity = var1;
   }

   private static int uQb7Ad1iy7(0bm var0) {
      return var0.opacity;
   }

   private static int _I0LgrbVsG/* $FF was: 2I0LgrbVsG*/(0bm var0) {
      return var0.x;
   }

   private static int _eiF8iXGPQ/* $FF was: 7eiF8iXGPQ*/(0bm var0) {
      return var0.opacity;
   }

   private static int NynxxnBASl(0bm var0) {
      return var0.opacity;
   }

   private static String j4kQHJQhSh(0bm var0) {
      return var0.displayString;
   }

   private static int SvTW4mGCKl(0bm var0) {
      return var0.x;
   }

   private static int XTGsdU9t6b(0bm var0) {
      return var0.x;
   }

   private static int tpdwhvM8Xl(0bm var0) {
      return var0.opacity;
   }

   private static boolean aJZuhgJTMt(0bm var0) {
      return var0.hovered;
   }

   private static int XTG9ejVlqJ(0bm var0) {
      return var0.x;
   }

   private static int rGPTXSWY4v(0bm var0) {
      return var0.y;
   }

   private static boolean _QYA86yabD/* $FF was: 8QYA86yabD*/(0bm var0) {
      return var0.visible;
   }

   private static int NLSz6T1gv1(0bm var0) {
      return var0.y;
   }

   private static int QDCv2cwIaT(0bm var0) {
      return var0.width;
   }

   private static int _grv2z1GsH/* $FF was: 5grv2z1GsH*/(0bm var0) {
      return var0.y;
   }

   public void drawButton(Minecraft mc, int mouseX, int mouseY, float mouseButton) {
      if (8QYA86yabD(this)) {
         mc.getTextureManager().bindTexture(ZWoK99SHB3());
         GlStateManager.color(Float.intBitsToFloat(234833 ^ 223353 ^ 6662 ^ 446265977 ^ 23579 ^ 212112 ^ 249066 ^ 622407990), Float.intBitsToFloat(111007 ^ 67798 ^ 15142 ^ 1035894590 ^ 109422 ^ 104692 ^ 18717 ^ 37652438), Float.intBitsToFloat(9459 ^ 240320 ^ 256283 ^ -1033521977 ^ 251547 ^ 31736 ^ 261623 ^ -35290245), Float.intBitsToFloat(27019 ^ 1047631 ^ 30372 ^ -518536073 ^ 23554 ^ 1005835 ^ '\ue952' ^ -560477364));
         AjJIJCtGQq(this, (boolean)(mouseX >= XTG9ejVlqJ(this) && mouseY >= XtQ4Ihx6pd(this) && mouseX < SvTW4mGCKl(this) + QDCv2cwIaT(this) && mouseY < 5grv2z1GsH(this) + SVbG9UDa9f(this) ? 3464 ^ -30413 ^ 497 ^ -31413 : 29658 ^ -26556 ^ 13638 ^ -8488));
         if (aJZuhgJTMt(this)) {
            if (NWWmNtrjrd(this) < (19207 ^ -24381 ^ 12753 ^ -9667)) {
               lOl41cFzrS(this, iadYB9CI9w(this) + (9852 ^ -21514 ^ 27436 ^ -6492));
            }
         } else if (NynxxnBASl(this) > (16615 ^ -17409 ^ 4655 ^ -5855)) {
            DDtYYdH4iw(this, tpdwhvM8Xl(this) - (5518 ^ -10383 ^ 28939 ^ -19466));
         }

         int var10000;
         if (mouseX >= 2I0LgrbVsG(this) && mouseY >= TlEn4QLg7T(this) && mouseX < XTGsdU9t6b(this) + wN1Jd9wAIo(this) && mouseY < 9B42fHYZwP(this) + 62YWV3eLAu(this)) {
            var10000 = 25134 ^ -25216 ^ 29667 ^ -29620;
         } else {
            var10000 = 23812 ^ -11042 ^ 25416 ^ -5486;
         }

         new Color(11144 ^ -3971 ^ 14337 ^ -7190, 12593 ^ -32135 ^ 11872 ^ -25290, 17519 ^ -12339 ^ 27796 ^ -6360, 1091 ^ -15943 ^ 18815 ^ -29579);
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(9205 ^ -554 ^ 27723 ^ -20118, 11331 ^ -14223 ^ 3011 ^ -4878, 9495 ^ -13178 ^ 6741 ^ -3131, 21189 ^ -26113 ^ 16701 ^ -30201);
         GlStateManager.blendFunc(22884 ^ -3626 ^ 30222 ^ -8770, 32578 ^ -12804 ^ 9190 ^ -28069);
         0ex.drawRound((float)ILcNUha7Q9(this), (float)NLSz6T1gv1(this), (float)P2ux3Zg4Na(this), (float)yfqZC75lIe(this), Float.intBitsToFloat(18166 ^ '\ue980' ^ 18399 ^ -17670410 ^ 104091 ^ '鞆' ^ 118253 ^ -1095599441), new Color(4rYi5YNGkT(this), 7eiF8iXGPQ(this), uQb7Ad1iy7(this), 4241 ^ -23305 ^ 904 ^ -18506));
         onoFgrJblR(Minecraft.getMinecraft()).drawCenteredString(j4kQHJQhSh(this), (double)(lDIo30qXlb(this) + lO7ZjSTQQO(this) / (28555 ^ -16635 ^ 8120 ^ -12492)), (double)(rGPTXSWY4v(this) + (deiAlvnaNk(this) - (27149 ^ -27099 ^ 18731 ^ -19199)) / (11545 ^ -17148 ^ 19268 ^ -9382)), -4881 ^ -26549 ^ 3936 ^ -31685);
         this.mouseDragged(mc, mouseX, mouseY);
      }

   }

   private static int yfqZC75lIe(0bm var0) {
      return var0.height;
   }

   public _bm/* $FF was: 0bm*/(int buttonId, int x, int y, String buttonText) {
      this(buttonId, x, y, 19366 ^ -15245 ^ 25671 ^ -5286, 9801 ^ -28339 ^ 24180 ^ -5788, buttonText);
   }

   private static int _rYi5YNGkT/* $FF was: 4rYi5YNGkT*/(0bm var0) {
      return var0.opacity;
   }

   private static void AjJIJCtGQq(0bm var0, boolean var1) {
      var0.hovered = var1;
   }

   private static FontRenderer onoFgrJblR(Minecraft var0) {
      return var0.fontRenderer;
   }

   private static int lO7ZjSTQQO(0bm var0) {
      return var0.width;
   }

   private static int TlEn4QLg7T(0bm var0) {
      return var0.y;
   }

   private static int _B42fHYZwP/* $FF was: 9B42fHYZwP*/(0bm var0) {
      return var0.y;
   }

   private static int SVbG9UDa9f(0bm var0) {
      return var0.height;
   }

   private static ResourceLocation ZWoK99SHB3() {
      return BUTTON_TEXTURES;
   }

   private static int deiAlvnaNk(0bm var0) {
      return var0.height;
   }

   private static int P2ux3Zg4Na(0bm var0) {
      return var0.width;
   }

   private static void DDtYYdH4iw(0bm var0, int var1) {
      var0.opacity = var1;
   }

   private static int wN1Jd9wAIo(0bm var0) {
      return var0.width;
   }

   public _bm/* $FF was: 0bm*/(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
      super(buttonId, x, y, widthIn, heightIn, buttonText);
      this.opacity = 12718 ^ -8005 ^ 31918 ^ -21101;
   }

   private static int ILcNUha7Q9(0bm var0) {
      return var0.x;
   }
}
