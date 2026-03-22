package neo;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ChatAllowedCharacters;
import org.lwjgl.opengl.GL11;

public class 0bq extends Gui {
   public int enabledColor = 119708 ^ 129480 ^ 5750 ^ 14736578;
   public final FontRenderer fontRenderer;
   public boolean enableBackgroundDrawing = 6156 ^ -24594 ^ 22276 ^ -12057;
   public String text = N2kapAIfB4("");
   private final int disabledColor = 1111 ^ '촟' ^ 'ꎈ' ^ 7346864;
   public final int height;
   private final boolean isEnabled = 18046 ^ -14287 ^ 19342 ^ -14912;
   public int maxStringLength = 586 ^ -28646 ^ 10946 ^ -18272;
   public final int xPos;
   public int cursorPosition = 4373 ^ -18584 ^ 5791 ^ -20254;
   public int selectionEnd = 13581 ^ -8697 ^ 14128 ^ -9158;
   public boolean field_73823_s = 9544 ^ -11374 ^ 729 ^ -3070;
   public boolean isFocused = 8984 ^ -16912 ^ 30130 ^ -5286;
   public boolean canLoseFocus = 18060 ^ -30902 ^ 7498 ^ -9075;
   public final int yPos;
   public int field_73816_n = 9170 ^ -16891 ^ 31179 ^ -7140;
   public final int width;
   public int cursorCounter;

   public String getSelectedtext() {
      int var1 = T9TbNxU6UV(this) < YdWJ9vgYna(this) ? YLvgig91TE(this) : Ingr9e1cJN(this);
      int var2 = G4Ig6GH561(this) < tIFHeFVHVB(this) ? eVJNG6WpRD(this) : cBG9BjWWd5(this);
      return greF6XPSSa(this).substring(var1, var2);
   }

   private static void hSbIr1VZVW(0bq var0, boolean var1) {
      var0.enableBackgroundDrawing = var1;
   }

   private static int sF32GaQ4tu(0bq var0) {
      return var0.maxStringLength;
   }

   private static int P67NVajIVW(0bq var0) {
      return var0.field_73816_n;
   }

   public int func_73798_a(int par1, int par2, boolean par3) {
      int var4 = par2;
      boolean var5 = par1 < 0 ? 223 ^ -6987 ^ 8178 ^ -1127 : 10401 ^ -7750 ^ 27910 ^ -23523;
      int var6 = Math.abs(par1);

      for(int var7 = 5047 ^ -25314 ^ 10885 ^ -23508; var7 < var6; ++var7) {
         if (var5 == 0) {
            int var8 = qYBqBqTnqo(this).length();
            var4 = 2boWDWIIwq(this).indexOf(13916 ^ -10320 ^ 24754 ^ -32386, var4);
            if (var4 == (-9446 ^ -3803 ^ 6411 ^ -13109)) {
               var4 = var8;
            } else {
               while(par3 && var4 < var8 && 27cTY2GMqD(this).charAt(var4) == (7203 ^ -24045 ^ 279 ^ -16633)) {
                  ++var4;
               }
            }
         } else {
            while(par3 && var4 > 0 && wGA6tNibJ6(this).charAt(var4 - (13922 ^ -27072 ^ 29499 ^ -11496)) == (3687 ^ -1084 ^ 1124 ^ -3609)) {
               --var4;
            }

            while(var4 > 0 && qY6f6t193V(this).charAt(var4 - (20947 ^ -32716 ^ 1198 ^ -10936)) != (8977 ^ -24432 ^ 4442 ^ -27909)) {
               --var4;
            }
         }
      }

      return var4;
   }

   private static boolean FEzAfPkEDA(0bq var0) {
      return var0.canLoseFocus;
   }

   private static int YdWJ9vgYna(0bq var0) {
      return var0.selectionEnd;
   }

   private static String yS4WgjN0vF(0bq var0) {
      return var0.text;
   }

   private static int mr9To44bjC(0bq var0) {
      return var0.selectionEnd;
   }

   public void func_73784_d(int par1) {
      this.setCursorPosition(lygawN9i1j(this) + par1);
   }

   private static void thLa2WGLUa(0bq var0, boolean var1) {
      var0.canLoseFocus = var1;
   }

   private static int r9BByQMaAs(0bq var0) {
      return var0.yPos;
   }

   private static String HdGY2ye1gw(0bq var0) {
      return var0.text;
   }

   private static int _ea2LgvNGl/* $FF was: 2ea2LgvNGl*/(0bq var0) {
      return var0.xPos;
   }

   private static int lxGdGo1quW(0bq var0) {
      return var0.cursorPosition;
   }

   private static int DXzTblj1oW(0bq var0) {
      return var0.field_73816_n;
   }

   private static int BeYhMEyToi(0bq var0) {
      return var0.xPos;
   }

   private static FontRenderer AA7R7SHFID(0bq var0) {
      return var0.fontRenderer;
   }

   private static int vEWlcu2ant(0bq var0) {
      return var0.width;
   }

   public _bq/* $FF was: 0bq*/(FontRenderer par1FontRenderer, int par2, int par3, int par4, int par5) {
      this.fontRenderer = par1FontRenderer;
      this.xPos = par2;
      this.yPos = par3;
      this.width = par4;
      this.height = par5;
   }

   public void func_73794_g(int par1) {
      zDpVkAPQow(this, par1);
   }

   private static int BB59RrLYLS(0bq var0) {
      return var0.field_73816_n;
   }

   private static String _1RSXYwNLu/* $FF was: 41RSXYwNLu*/(0bq var0) {
      return var0.text;
   }

   private static int LDUTJ9VaC2(0bq var0) {
      return var0.selectionEnd;
   }

   private static int _nGHNGGl8a/* $FF was: 4nGHNGGl8a*/(0bq var0) {
      return var0.field_73816_n;
   }

   private static void IVVmASYQJX(0bq var0, int var1) {
      var0.maxStringLength = var1;
   }

   private static String lYJeSXVeYA(0bq var0) {
      return var0.text;
   }

   private static int Mn5AJgn7j5(0bq var0) {
      return var0.maxStringLength;
   }

   private static int weDehOym99(0bq var0) {
      return var0.field_73816_n;
   }

   private static FontRenderer _cJzbHSD18/* $FF was: 7cJzbHSD18*/(0bq var0) {
      return var0.fontRenderer;
   }

   public void drawTextBox() {
      if (this.func_73778_q()) {
         if (this.getEnableBackgroundDrawing()) {
            drawRect(2ea2LgvNGl(this) - (4988 ^ -30669 ^ 24323 ^ -15283), xaIZul5MZ4(this) - (31097 ^ -2927 ^ 22042 ^ -9229), oB1yKFta0Y(this) + jQAylxAMSA(this) + (20469 ^ -10860 ^ 18337 ^ -8767), 14dvPobR21(this) + QTD1FrDp0T(this) + (385 ^ -17570 ^ 4535 ^ -21655), 8173 ^ '\uec12' ^ 32302 ^ -6279823);
            drawRect(QdhtS297ON(this), r9BByQMaAs(this), UJ95ReogFr(this) + vEWlcu2ant(this), 27yfJRIi6W(this) + 1b4R6IPWzS(this), 25821 ^ 33533496 ^ 10956 ^ -16785367);
         }

         this.getClass();
         int var1 = GvATr7iYAq(this);
         int var2 = JJLbGy7IGd(this) - weDehOym99(this);
         int var3 = JC67xJ9rfI(this) - QIPrMLCDlg(this);
         String var4 = AA7R7SHFID(this).trimStringToWidth(OP6FarFJSb(this).substring(4nGHNGGl8a(this)), this.getWidth());
         boolean var5 = var2 >= 0 && var2 <= var4.length() ? 8807 ^ -11547 ^ 30297 ^ -31014 : 8234 ^ -30528 ^ 9839 ^ -29051;
         boolean var6 = 0NDZYIFe8J(this) && S788yb3qfw(this) / (17147 ^ -32225 ^ 30469 ^ -18457) % (24841 ^ -12586 ^ 7413 ^ -19672) == 0 && var5 != 0 ? 27796 ^ -30524 ^ 97 ^ -7120 : 29136 ^ -12475 ^ 23124 ^ -6975;
         int var7 = 1mL49DZY0t(this) ? GNNvOkNysJ(this) + (30553 ^ -32707 ^ 15593 ^ -13431) : i1bZ7E4gJe(this);
         int var8 = 1RyowVWnla(this) ? ik4h1SWEcJ(this) + (NQqw66UTc0(this) - (7597 ^ -2886 ^ 24356 ^ -18885)) / (26262 ^ -1067 ^ 30810 ^ -6885) : YS2d8gSbWr(this);
         int var9 = var7;
         if (var3 > var4.length()) {
            var3 = var4.length();
         }

         if (var4.length() > 0) {
            if (var5 != 0) {
               var4.substring(29614 ^ -7557 ^ 29086 ^ -8117, var2);
            }

            var9 = P8Awo1iJSv(Minecraft.getMinecraft()).drawStringWithShadow(pljfnaay1t(this).replaceAll(N2kapAIfB4("ؗ\u0600ٌؖؑ"), N2kapAIfB4("ؕ")), (float)var7, (float)var8, var1);
         }

         boolean var10 = yJD1pbHJTi(this) >= LXrTgQSRiB(this).length() && y1cB1Axyjj(this).length() < this.getMaxStringLength() ? 21345 ^ -23053 ^ 7284 ^ -5402 : 1465 ^ -21723 ^ 32631 ^ -11798;
         int var11 = var9;
         if (var5 == 0) {
            var11 = var2 > 0 ? var7 + SZrCsa0jo1(this) : var7;
         } else if (var10 != 0) {
            var11 = var9 - (11451 ^ -10441 ^ 29187 ^ -30322);
            --var9;
         }

         if (var4.length() > 0 && var5 != 0 && var2 < var4.length()) {
            ooQMRKc7G7(Minecraft.getMinecraft()).drawStringWithShadow(var4.substring(var2), (float)var9, (float)var8, var1);
         }

         if (var6 != 0) {
            if (var10 != 0) {
               Gui.drawRect(var11, var8 - (23950 ^ -9595 ^ 30393 ^ -3661), var11 + (22200 ^ -13715 ^ 22538 ^ -15138), var8 + (7988 ^ -9658 ^ 17627 ^ -32344) + 8TQrBSB4Vr(L1bASKV06n(this)), 119005 ^ 130705 ^ 17365 ^ -3097271);
            } else {
               LnqvwMmeNY(Minecraft.getMinecraft()).drawStringWithShadow(N2kapAIfB4("٠"), (float)var11, (float)var8, var1);
            }
         }

         if (var3 != var2) {
            int var12 = var7 + yAVSLbD7Df(this).getStringWidth(var4.substring(32287 ^ -4798 ^ 27360 ^ -1603, var3));
            this.drawCursorVertical(var11, var8 - (32279 ^ -26983 ^ 23938 ^ -19187), var12 - (9077 ^ -30711 ^ 12936 ^ -26123), var8 + (27995 ^ -30367 ^ 30774 ^ -25587) + eRcJHTJTlK(jnBsSU7068(this)));
         }
      }

   }

   private static String QNt3yTONsO(0bq var0) {
      return var0.text;
   }

   private static int oB1yKFta0Y(0bq var0) {
      return var0.xPos;
   }

   private static String QVHrVtYeAm(0bq var0) {
      return var0.text;
   }

   private static int eVJNG6WpRD(0bq var0) {
      return var0.selectionEnd;
   }

   private static int _YpK8jOGfe/* $FF was: 2YpK8jOGfe*/(0bq var0) {
      return var0.selectionEnd;
   }

   private static int NxamXbv0aS(0bq var0) {
      return var0.width;
   }

   private static int T9TbNxU6UV(0bq var0) {
      return var0.cursorPosition;
   }

   private static String wGA6tNibJ6(0bq var0) {
      return var0.text;
   }

   public void setCanLoseFocus(boolean par1) {
      thLa2WGLUa(this, par1);
   }

   private static boolean PfgqHBQmbS(0bq var0) {
      return var0.field_73823_s;
   }

   private static int wbW69GiDmw(0bq var0) {
      return var0.cursorPosition;
   }

   public int getNthWordFromCursor(int par1) {
      return this.getNthWordFromPos(par1, this.getCursorPosition());
   }

   public void setCursorPositionEnd() {
      this.setCursorPosition(Ww1cfXYpqy(this).length());
   }

   private static void jcwPVHg9Up(0bq var0, int var1) {
      var0.cursorPosition = var1;
   }

   private static int tIFHeFVHVB(0bq var0) {
      return var0.selectionEnd;
   }

   private static int GNNvOkNysJ(0bq var0) {
      return var0.xPos;
   }

   private static void FyYqo2ynzc(0bq var0, String var1) {
      var0.text = var1;
   }

   private static int jQAylxAMSA(0bq var0) {
      return var0.width;
   }

   private static int QIPrMLCDlg(0bq var0) {
      return var0.field_73816_n;
   }

   private static String Ww1cfXYpqy(0bq var0) {
      return var0.text;
   }

   private static String _hxerevjh7/* $FF was: 3hxerevjh7*/(0bq var0) {
      return var0.text;
   }

   private static void eCUBfFAvFV(0bq var0, int var1) {
      var0.cursorCounter = var1;
   }

   private static int ik4h1SWEcJ(0bq var0) {
      return var0.yPos;
   }

   private static String pljfnaay1t(0bq var0) {
      return var0.text;
   }

   private static int WcIeQbe6Vc(0bq var0) {
      return var0.xPos;
   }

   private static int QTD1FrDp0T(0bq var0) {
      return var0.height;
   }

   private static void qNWanfAOhW(0bq var0, int var1) {
      var0.field_73816_n = var1;
   }

   private static int _EZoFV6aDq/* $FF was: 4EZoFV6aDq*/(0bq var0) {
      return var0.field_73816_n;
   }

   private static int DF8qnEDVTn(0bq var0) {
      return var0.cursorPosition;
   }

   private static FontRenderer aetlfxF4rJ(0bq var0) {
      return var0.fontRenderer;
   }

   private static int _cETxaD9sT/* $FF was: 2cETxaD9sT*/(0bq var0) {
      return var0.selectionEnd;
   }

   private static void YUSLCu8jiw(0bq var0, String var1) {
      var0.text = var1;
   }

   private static void d9Y8IvhA6W(0bq var0, boolean var1) {
      var0.field_73823_s = var1;
   }

   private static String qY6f6t193V(0bq var0) {
      return var0.text;
   }

   private static int IqFWhAnoI7(0bq var0) {
      return var0.field_73816_n;
   }

   private static int AoXF9eqBC0(0bq var0) {
      return var0.cursorPosition;
   }

   private static String hltiwVb0JN(0bq var0) {
      return var0.text;
   }

   private static int _Va4yBM1Vt/* $FF was: 1Va4yBM1Vt*/(0bq var0) {
      return var0.cursorPosition;
   }

   private static int eRcJHTJTlK(FontRenderer var0) {
      return var0.FONT_HEIGHT;
   }

   public boolean getEnableBackgroundDrawing() {
      return yVfwNnekT5(this);
   }

   public String getText() {
      return yS4WgjN0vF(this).replaceAll(N2kapAIfB4("؟"), N2kapAIfB4(""));
   }

   private static int gsTBzsBw7z(0bq var0) {
      return var0.cursorPosition;
   }

   private static void XfdD62sXWl(0bq var0, String var1) {
      var0.text = var1;
   }

   private static int d2RT3jipNO(0bq var0) {
      return var0.field_73816_n;
   }

   public boolean isFocused() {
      return zLeMxrxaPs(this);
   }

   private static int Dqznb2CgDO(0bq var0) {
      return var0.yPos;
   }

   private static int ByqWWhIW4f(0bq var0) {
      return var0.cursorPosition;
   }

   private static int kQOUZdNb8f(0bq var0) {
      return var0.field_73816_n;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String N2kapAIfB4(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 3760 ^ -10193 ^ 13617 ^ -7250; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 31267 ^ -28660 ^ 22910 ^ -19090));
      }

      return var1.toString();
   }

   private static int _VYdIRIoaw/* $FF was: 5VYdIRIoaw*/(0bq var0) {
      return var0.selectionEnd;
   }

   public void deleteFromCursor(int par1) {
      if (teLeXRPi4B(this).length() != 0) {
         if (2YpK8jOGfe(this) != ubflZQZZvb(this)) {
            this.writeText(N2kapAIfB4(""));
         } else {
            boolean var2 = par1 < 0 ? 343 ^ -27470 ^ 14395 ^ -21025 : 31905 ^ -20159 ^ 6615 ^ -11209;
            int var3 = var2 != 0 ? 1Va4yBM1Vt(this) + par1 : gsTBzsBw7z(this);
            int var4 = var2 != 0 ? bRlvpt6AwF(this) : ByqWWhIW4f(this) + par1;
            String var5 = N2kapAIfB4("");
            if (var3 >= 0) {
               var5 = oBDTWsIAMw(this).substring(9664 ^ -29640 ^ 29500 ^ -9532, var3);
            }

            if (var4 < tDWi4Nr7XS(this).length()) {
               var5 = var5 + vIaLUiVQNw(this).substring(var4);
            }

            FyYqo2ynzc(this, var5);
            if (var2 != 0) {
               this.func_73784_d(par1);
            }
         }
      }

   }

   private static String oBDTWsIAMw(0bq var0) {
      return var0.text;
   }

   private static boolean _mL49DZY0t/* $FF was: 1mL49DZY0t*/(0bq var0) {
      return var0.enableBackgroundDrawing;
   }

   private static int NR6cy2Hgfg(0bq var0) {
      return var0.selectionEnd;
   }

   private static String _boWDWIIwq/* $FF was: 2boWDWIIwq*/(0bq var0) {
      return var0.text;
   }

   private static FontRenderer Sh1IEwrdpQ(0bq var0) {
      return var0.fontRenderer;
   }

   private static FontRenderer LnqvwMmeNY(Minecraft var0) {
      return var0.fontRenderer;
   }

   private static String OP6FarFJSb(0bq var0) {
      return var0.text;
   }

   public void updateCursorCounter() {
      FyrdpCtYeN(this, avMeUw9SJG(this) + (23295 ^ -19637 ^ 16996 ^ -21551));
   }

   private static String vIaLUiVQNw(0bq var0) {
      return var0.text;
   }

   private static String _xn24twn6L/* $FF was: 2xn24twn6L*/(0bq var0) {
      return var0.text;
   }

   private static FontRenderer FBbywiVsVW(0bq var0) {
      return var0.fontRenderer;
   }

   private static void jBPWIB2TwY(0bq var0, int var1) {
      var0.field_73816_n = var1;
   }

   private static int IBJ9WXFiir(0bq var0) {
      return var0.field_73816_n;
   }

   private static int NZa8sTxTLL(0bq var0) {
      return var0.field_73816_n;
   }

   private static void AtnClSQEyw(0bq var0, int var1) {
      var0.field_73816_n = var1;
   }

   public int getMaxStringLength() {
      return 66lCoFeV66(this);
   }

   private static FontRenderer kF9RQArvzb(0bq var0) {
      return var0.fontRenderer;
   }

   public void mouseClicked(int par1, int par2, int par3) {
      boolean var4 = par1 >= WcIeQbe6Vc(this) && par1 < XvuqZYtlnP(this) + Bt2EukbwJS(this) && par2 >= je4Fkz24gV(this) && par2 < Dqznb2CgDO(this) + LOlESYVybS(this) ? 6727 ^ -9170 ^ 29719 ^ -19841 : 4394 ^ -22578 ^ 4525 ^ -22711;
      if (FEzAfPkEDA(this)) {
         this.getClass();
         this.setFocused((boolean)(var4 != 0 ? 2141 ^ -4934 ^ 29284 ^ -27006 : 21633 ^ -16472 ^ 20990 ^ -17705));
      }

      if (VNCG5fS6Te(this) && par3 == 0) {
         int var5 = par1 - BeYhMEyToi(this);
         if (BiJCj68Qme(this)) {
            var5 -= 4;
         }

         String var6 = kF9RQArvzb(this).trimStringToWidth(41RSXYwNLu(this).substring(IqFWhAnoI7(this)), this.getWidth());
         this.setCursorPosition(7cJzbHSD18(this).trimStringToWidth(var6, var5).length() + kQOUZdNb8f(this));
      }

   }

   private static int bRlvpt6AwF(0bq var0) {
      return var0.cursorPosition;
   }

   private static int Bt2EukbwJS(0bq var0) {
      return var0.width;
   }

   private static int _TQrBSB4Vr/* $FF was: 8TQrBSB4Vr*/(FontRenderer var0) {
      return var0.FONT_HEIGHT;
   }

   private static int yJD1pbHJTi(0bq var0) {
      return var0.cursorPosition;
   }

   private static String JiLVnGkZlS(0bq var0) {
      return var0.text;
   }

   private static int S788yb3qfw(0bq var0) {
      return var0.cursorCounter;
   }

   private static int UJ95ReogFr(0bq var0) {
      return var0.xPos;
   }

   private static FontRenderer P8Awo1iJSv(Minecraft var0) {
      return var0.fontRenderer;
   }

   private static int w7N1SaK4BZ(0bq var0) {
      return var0.cursorPosition;
   }

   private static FontRenderer L1bASKV06n(0bq var0) {
      return var0.fontRenderer;
   }

   private static int lygawN9i1j(0bq var0) {
      return var0.selectionEnd;
   }

   public void writeText(String par1Str) {
      String var2 = N2kapAIfB4("");
      String var3 = ChatAllowedCharacters.filterAllowedCharacters(par1Str);
      int var4 = w7N1SaK4BZ(this) < 2cETxaD9sT(this) ? 2aDr64T3lG(this) : NR6cy2Hgfg(this);
      int var5 = wbW69GiDmw(this) < MrPpdFck0j(this) ? mr9To44bjC(this) : g6Hl2VNEgJ(this);
      int var6 = Mn5AJgn7j5(this) - lYJeSXVeYA(this).length() - (var4 - 5VYdIRIoaw(this));
      boolean var7 = 17137 ^ -2506 ^ 2814 ^ -16839;
      if (QNt3yTONsO(this).length() > 0) {
         var2 = var2 + j0bsynVALi(this).substring(5778 ^ -24091 ^ 31543 ^ -13248, var4);
      }

      int var8;
      if (var6 < var3.length()) {
         var2 = var2 + var3.substring(4445 ^ -30077 ^ 18194 ^ -9012, var6);
         var8 = var6;
      } else {
         var2 = var2 + var3;
         var8 = var3.length();
      }

      if (hltiwVb0JN(this).length() > 0 && var5 < JiLVnGkZlS(this).length()) {
         var2 = var2 + jWqvidjlT2(this).substring(var5);
      }

      qQJdvGL5rQ(this, var2.replaceAll(N2kapAIfB4("؟"), N2kapAIfB4("")));
      this.func_73784_d(var4 - BooqAfgF1g(this) + var8);
   }

   private static void N81oRffXlX(0bq var0, int var1) {
      var0.cursorPosition = var1;
   }

   private static boolean _RyowVWnla/* $FF was: 1RyowVWnla*/(0bq var0) {
      return var0.enableBackgroundDrawing;
   }

   private static int YLvgig91TE(0bq var0) {
      return var0.cursorPosition;
   }

   private static String _7cTY2GMqD/* $FF was: 27cTY2GMqD*/(0bq var0) {
      return var0.text;
   }

   public void setCursorPosition(int par1) {
      WYxBchd2yO(this, par1);
      int var2 = 3hxerevjh7(this).length();
      if (8EJ2MoGRvt(this) < 0) {
         jcwPVHg9Up(this, 31951 ^ -14349 ^ 24208 ^ -6740);
      }

      if (SD9vVVA01k(this) > var2) {
         N81oRffXlX(this, var2);
      }

      this.func_73800_i(lxGdGo1quW(this));
   }

   private static void bkPg0aJGy4(0bq var0, boolean var1) {
      var0.isFocused = var1;
   }

   private void drawCursorVertical(int par1, int par2, int par3, int par4) {
      int var5;
      if (par1 < par3) {
         var5 = par1;
         par1 = par3;
         par3 = var5;
      }

      if (par2 < par4) {
         var5 = par2;
         par2 = par4;
         par4 = var5;
      }

      Tessellator var6 = Tessellator.getInstance();
      BufferBuilder var7 = var6.getBuffer();
      GL11.glColor4f(Float.intBitsToFloat(22015 ^ 21804 ^ 12084 ^ -753725946 ^ 3783 ^ '鱃' ^ '뢰' ^ -753726507), Float.intBitsToFloat(103059 ^ 117503 ^ 1966 ^ -1820032786 ^ 119203 ^ '金' ^ 126292 ^ -1820036598), Float.intBitsToFloat(9025 ^ 236697 ^ 16243 ^ 710527700 ^ '즮' ^ '땚' ^ 16014 ^ 1764034565), Float.intBitsToFloat(235762 ^ 230532 ^ 32323 ^ -1421503208 ^ 122591 ^ 110343 ^ 12533 ^ -398805504));
      GL11.glDisable(25042 ^ 26539 ^ 741 ^ 2429);
      GL11.glEnable(25520 ^ -14636 ^ 32061 ^ -11349);
      GL11.glLogicOp(13233 ^ 25652 ^ 19217 ^ 2463);
      var7.begin(7456 ^ -6485 ^ 2064 ^ -3172, DUgHmDTO6a());
      var7.pos((double)par1, (double)par4, Double.longBitsToDouble(7057511396166276752L ^ 7057511396166276752L)).endVertex();
      var7.pos((double)par3, (double)par4, Double.longBitsToDouble(-2676880093482694016L ^ -2676880093482694016L)).endVertex();
      var7.pos((double)par3, (double)par2, Double.longBitsToDouble(-6254277659687085733L ^ -6254277659687085733L)).endVertex();
      var7.pos((double)par1, (double)par2, Double.longBitsToDouble(2021111154902545861L ^ 2021111154902545861L)).endVertex();
      var6.draw();
      GL11.glDisable(14585 ^ -13002 ^ 19682 ^ -19745);
      GL11.glEnable(25107 ^ -23803 ^ 28675 ^ -17164);
   }

   private static FontRenderer ooQMRKc7G7(Minecraft var0) {
      return var0.fontRenderer;
   }

   private static String _9xzAgEY9e/* $FF was: 69xzAgEY9e*/(0bq var0) {
      return var0.text;
   }

   public int getCursorPosition() {
      return DF8qnEDVTn(this);
   }

   private static void _w7ejWCXc1/* $FF was: 4w7ejWCXc1*/(0bq var0, int var1) {
      var0.field_73816_n = var1;
   }

   private static int GvATr7iYAq(0bq var0) {
      return var0.enabledColor;
   }

   private static String tDWi4Nr7XS(0bq var0) {
      return var0.text;
   }

   private static int _7yfJRIi6W/* $FF was: 27yfJRIi6W*/(0bq var0) {
      return var0.yPos;
   }

   private static int o5wFPlt924(0bq var0) {
      return var0.field_73816_n;
   }

   private static int LOlESYVybS(0bq var0) {
      return var0.height;
   }

   private static int arwkV4LOK9(0bq var0) {
      return var0.field_73816_n;
   }

   private static String jWqvidjlT2(0bq var0) {
      return var0.text;
   }

   private static boolean yVfwNnekT5(0bq var0) {
      return var0.enableBackgroundDrawing;
   }

   private static void qQJdvGL5rQ(0bq var0, String var1) {
      var0.text = var1;
   }

   private static boolean VNCG5fS6Te(0bq var0) {
      return var0.isFocused;
   }

   private static int SZrCsa0jo1(0bq var0) {
      return var0.width;
   }

   private static int XvuqZYtlnP(0bq var0) {
      return var0.xPos;
   }

   public boolean textboxKeyTyped(char par1, int par2) {
      this.getClass();
      if (7Fnv6y4dvL(this)) {
         switch (par1) {
            case '\u0001':
               this.setCursorPositionEnd();
               this.func_73800_i(28416 ^ -32425 ^ 3613 ^ -8118);
               return (boolean)(23459 ^ -32165 ^ 11695 ^ -2986);
            case '\u0003':
               GuiScreen.setClipboardString(this.getSelectedtext());
               return (boolean)(13447 ^ -25828 ^ 6237 ^ -18489);
            case '\u0016':
               this.writeText(GuiScreen.getClipboardString());
               return (boolean)(27346 ^ -5531 ^ 24184 ^ -8498);
            case '\u0018':
               GuiScreen.setClipboardString(this.getSelectedtext());
               this.writeText(N2kapAIfB4(""));
               return (boolean)(30004 ^ -24764 ^ 21719 ^ -16730);
            default:
               switch (par2) {
                  case 14:
                     if (GuiScreen.isCtrlKeyDown()) {
                        this.func_73779_a(-30340 ^ -223 ^ 13285 ^ -17849);
                     } else {
                        this.deleteFromCursor(-16974 ^ -1327 ^ 20915 ^ -5841);
                     }

                     return (boolean)(8242 ^ -22035 ^ 30129 ^ -913);
                  case 199:
                     if (GuiScreen.isShiftKeyDown()) {
                        this.func_73800_i(18745 ^ -10643 ^ 20473 ^ -12115);
                     } else {
                        this.setCursorPositionZero();
                     }

                     return (boolean)(24476 ^ -12751 ^ 8371 ^ -20193);
                  case 203:
                     if (GuiScreen.isShiftKeyDown()) {
                        if (GuiScreen.isCtrlKeyDown()) {
                           this.func_73800_i(this.getNthWordFromPos(-9697 ^ -14962 ^ 5894 ^ -2200, this.getSelectionEnd()));
                        } else {
                           this.func_73800_i(this.getSelectionEnd() - (10937 ^ -31083 ^ 23443 ^ -2114));
                        }
                     } else if (GuiScreen.isCtrlKeyDown()) {
                        this.setCursorPosition(this.getNthWordFromCursor(-2910 ^ -32650 ^ 26058 ^ -4383));
                     } else {
                        this.func_73784_d(-31185 ^ -11375 ^ 18849 ^ -7200);
                     }

                     return (boolean)(4489 ^ -3873 ^ 32037 ^ -25486);
                  case 205:
                     if (GuiScreen.isShiftKeyDown()) {
                        if (GuiScreen.isCtrlKeyDown()) {
                           this.func_73800_i(this.getNthWordFromPos(26971 ^ -12488 ^ 27527 ^ -12827, this.getSelectionEnd()));
                        } else {
                           this.func_73800_i(this.getSelectionEnd() + (586 ^ -4342 ^ 25207 ^ -28874));
                        }
                     } else if (GuiScreen.isCtrlKeyDown()) {
                        this.setCursorPosition(this.getNthWordFromCursor(7551 ^ -25641 ^ 12210 ^ -22245));
                     } else {
                        this.func_73784_d(15025 ^ -7219 ^ 5602 ^ -13153);
                     }

                     return (boolean)(13198 ^ -6651 ^ 27502 ^ -16668);
                  case 207:
                     if (GuiScreen.isShiftKeyDown()) {
                        this.func_73800_i(WggrqZfl0p(this).length());
                     } else {
                        this.setCursorPositionEnd();
                     }

                     return (boolean)(8587 ^ -29070 ^ 26922 ^ -14638);
                  case 211:
                     if (GuiScreen.isCtrlKeyDown()) {
                        this.func_73779_a(3430 ^ -15918 ^ 22231 ^ -26014);
                     } else {
                        this.deleteFromCursor(9769 ^ -14883 ^ 35 ^ -7210);
                     }

                     return (boolean)(9660 ^ -3647 ^ 2905 ^ -8411);
                  default:
                     if (ChatAllowedCharacters.isAllowedCharacter(par1)) {
                        this.writeText(Character.toString(par1));
                        return (boolean)(19603 ^ -11330 ^ 3660 ^ -28320);
                     } else {
                        return (boolean)(25494 ^ -31893 ^ 13613 ^ -10800);
                     }
               }
         }
      } else {
         return (boolean)(17599 ^ -12366 ^ 17654 ^ -12293);
      }
   }

   private static boolean elJkXbOOUm(0bq var0) {
      return var0.isFocused;
   }

   private static int BooqAfgF1g(0bq var0) {
      return var0.selectionEnd;
   }

   private static String WggrqZfl0p(0bq var0) {
      return var0.text;
   }

   private static void WYxBchd2yO(0bq var0, int var1) {
      var0.cursorPosition = var1;
   }

   private static void jGbBWNOjaf(0bq var0, int var1) {
      var0.field_73816_n = var1;
   }

   private static int G4Ig6GH561(0bq var0) {
      return var0.cursorPosition;
   }

   private static FontRenderer jnBsSU7068(0bq var0) {
      return var0.fontRenderer;
   }

   private static int JC67xJ9rfI(0bq var0) {
      return var0.selectionEnd;
   }

   private static int _EJ2MoGRvt/* $FF was: 8EJ2MoGRvt*/(0bq var0) {
      return var0.cursorPosition;
   }

   private static int xaIZul5MZ4(0bq var0) {
      return var0.yPos;
   }

   private static int _aDr64T3lG/* $FF was: 2aDr64T3lG*/(0bq var0) {
      return var0.cursorPosition;
   }

   private static String oIC9OTy35q(0bq var0) {
      return var0.text;
   }

   private static void FyrdpCtYeN(0bq var0, int var1) {
      var0.cursorCounter = var1;
   }

   private static int _4dvPobR21/* $FF was: 14dvPobR21*/(0bq var0) {
      return var0.yPos;
   }

   private static String IB81yCr69D(0bq var0) {
      return var0.text;
   }

   private static int SD9vVVA01k(0bq var0) {
      return var0.cursorPosition;
   }

   public void func_73800_i(int par1) {
      int var2 = 2xn24twn6L(this).length();
      if (par1 > var2) {
         par1 = var2;
      }

      if (par1 < 0) {
         par1 = 5020 ^ -27280 ^ 30921 ^ -475;
      }

      GBxe4mScZe(this, par1);
      if (aetlfxF4rJ(this) != null) {
         if (7d6ew6Ao6a(this) > var2) {
            jGbBWNOjaf(this, var2);
         }

         int var3 = this.getWidth();
         String var4 = FBbywiVsVW(this).trimStringToWidth(oIC9OTy35q(this).substring(DXzTblj1oW(this)), var3);
         int var5 = var4.length() + BB59RrLYLS(this);
         if (par1 == arwkV4LOK9(this)) {
            4w7ejWCXc1(this, QsrNOSLyou(this) - Sh1IEwrdpQ(this).trimStringToWidth(HdGY2ye1gw(this), var3, (boolean)(21258 ^ -17306 ^ 28522 ^ -32761)).length());
         }

         if (par1 > var5) {
            qNWanfAOhW(this, o5wFPlt924(this) + (par1 - var5));
         } else if (par1 <= d2RT3jipNO(this)) {
            jBPWIB2TwY(this, NZa8sTxTLL(this) - (4EZoFV6aDq(this) - par1));
         }

         if (P67NVajIVW(this) < 0) {
            AtnClSQEyw(this, 19876 ^ -10214 ^ 23470 ^ -12784);
         }

         if (IBJ9WXFiir(this) > var2) {
            yGvgpLxXe1(this, var2);
         }
      }

   }

   private static int JJLbGy7IGd(0bq var0) {
      return var0.cursorPosition;
   }

   private static VertexFormat DUgHmDTO6a() {
      return DefaultVertexFormats.POSITION;
   }

   private static boolean _Fnv6y4dvL/* $FF was: 7Fnv6y4dvL*/(0bq var0) {
      return var0.isFocused;
   }

   private static void yGvgpLxXe1(0bq var0, int var1) {
      var0.field_73816_n = var1;
   }

   public int getNthWordFromPos(int par1, int par2) {
      return this.func_73798_a(par1, this.getCursorPosition(), (boolean)(2145 ^ -20033 ^ 5744 ^ -20561));
   }

   private static int _b4R6IPWzS/* $FF was: 1b4R6IPWzS*/(0bq var0) {
      return var0.height;
   }

   private static void GBxe4mScZe(0bq var0, int var1) {
      var0.selectionEnd = var1;
   }

   private static int NQqw66UTc0(0bq var0) {
      return var0.height;
   }

   private static String greF6XPSSa(0bq var0) {
      return var0.text;
   }

   private static boolean _NDZYIFe8J/* $FF was: 0NDZYIFe8J*/(0bq var0) {
      return var0.isFocused;
   }

   private static void rJe2lb1Gnc(0bq var0, String var1) {
      var0.text = var1;
   }

   public void setCursorPositionZero() {
      this.setCursorPosition(29184 ^ -10392 ^ 2453 ^ -21251);
   }

   public int getWidth() {
      return this.getEnableBackgroundDrawing() ? hXEnlm6bNl(this) - (22734 ^ -11018 ^ 17733 ^ -13963) : NxamXbv0aS(this);
   }

   public boolean func_73778_q() {
      return PfgqHBQmbS(this);
   }

   private static int _joiAnlksJ/* $FF was: 2joiAnlksJ*/(0bq var0) {
      return var0.cursorPosition;
   }

   private static String teLeXRPi4B(0bq var0) {
      return var0.text;
   }

   private static boolean zLeMxrxaPs(0bq var0) {
      return var0.isFocused;
   }

   private static FontRenderer yAVSLbD7Df(0bq var0) {
      return var0.fontRenderer;
   }

   private static int _6lCoFeV66/* $FF was: 66lCoFeV66*/(0bq var0) {
      return var0.maxStringLength;
   }

   public int getSelectionEnd() {
      return 69Yq9ymGKc(this);
   }

   private static int _9Yq9ymGKc/* $FF was: 69Yq9ymGKc*/(0bq var0) {
      return var0.selectionEnd;
   }

   private static String LXrTgQSRiB(0bq var0) {
      return var0.text;
   }

   public void setFocused(boolean par1) {
      if (par1 && !elJkXbOOUm(this)) {
         eCUBfFAvFV(this, 2495 ^ -30759 ^ 12610 ^ -16604);
      }

      bkPg0aJGy4(this, par1);
   }

   public void func_73790_e(boolean par1) {
      d9Y8IvhA6W(this, par1);
   }

   private static String y1cB1Axyjj(0bq var0) {
      return var0.text;
   }

   public void setMaxStringLength(int par1) {
      IVVmASYQJX(this, par1);
      if (IB81yCr69D(this).length() > par1) {
         YUSLCu8jiw(this, QVHrVtYeAm(this).substring(22991 ^ -29753 ^ 7978 ^ -13022, par1));
      }

   }

   private static boolean BiJCj68Qme(0bq var0) {
      return var0.enableBackgroundDrawing;
   }

   private static int xJztllYb5N(0bq var0) {
      return var0.maxStringLength;
   }

   private static void zDpVkAPQow(0bq var0, int var1) {
      var0.enabledColor = var1;
   }

   private static int g6Hl2VNEgJ(0bq var0) {
      return var0.cursorPosition;
   }

   private static int Ingr9e1cJN(0bq var0) {
      return var0.selectionEnd;
   }

   private static int YS2d8gSbWr(0bq var0) {
      return var0.yPos;
   }

   private static int i1bZ7E4gJe(0bq var0) {
      return var0.xPos;
   }

   private static int cBG9BjWWd5(0bq var0) {
      return var0.cursorPosition;
   }

   private static int MrPpdFck0j(0bq var0) {
      return var0.selectionEnd;
   }

   public void func_73779_a(int par1) {
      if (69xzAgEY9e(this).length() != 0) {
         if (LDUTJ9VaC2(this) != 2joiAnlksJ(this)) {
            this.writeText(N2kapAIfB4(""));
         } else {
            this.deleteFromCursor(this.getNthWordFromCursor(par1) - AoXF9eqBC0(this));
         }
      }

   }

   private static int je4Fkz24gV(0bq var0) {
      return var0.yPos;
   }

   private static String qYBqBqTnqo(0bq var0) {
      return var0.text;
   }

   private static int _d6ew6Ao6a/* $FF was: 7d6ew6Ao6a*/(0bq var0) {
      return var0.field_73816_n;
   }

   private static int avMeUw9SJG(0bq var0) {
      return var0.cursorCounter;
   }

   private static int ubflZQZZvb(0bq var0) {
      return var0.cursorPosition;
   }

   private static int hXEnlm6bNl(0bq var0) {
      return var0.width;
   }

   private static int QdhtS297ON(0bq var0) {
      return var0.xPos;
   }

   public void setEnableBackgroundDrawing(boolean par1) {
      hSbIr1VZVW(this, par1);
   }

   private static String j0bsynVALi(0bq var0) {
      return var0.text;
   }

   public void setText(String par1Str) {
      if (par1Str.length() > sF32GaQ4tu(this)) {
         rJe2lb1Gnc(this, par1Str.substring(25196 ^ -23258 ^ 30680 ^ -20334, xJztllYb5N(this)));
      } else {
         XfdD62sXWl(this, par1Str);
      }

      this.setCursorPositionEnd();
   }

   private static int QsrNOSLyou(0bq var0) {
      return var0.field_73816_n;
   }
}
