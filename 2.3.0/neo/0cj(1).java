package neo;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.io.IOException;
import java.util.List;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.input.Keyboard;

public final class 0cj extends lg {
   public lE field_d;
   public final lg field_c;
   public 0ce field_a;
   public 0cm field_b;
   private static int _DSC GG NEOWARECLIENT _;

   private static TextFormatting method_bbv() {
      return TextFormatting.GRAY;
   }

   private static 0ce method_bbw(0cj var0) {
      return var0.field_a;
   }

   private static lE method_bcc(0cj var0) {
      return var0.field_d;
   }

   private static int method_bbT(0cj var0) {
      return var0.width;
   }

   private static lE method_bby(0cj var0) {
      return var0.field_d;
   }

   private static void method_bbh(0cj var0, 0ce var1) {
      var0.field_a = var1;
   }

   public void onGuiClosed() {
      Keyboard.enableRepeatEvents((boolean)(24716 ^ -733 ^ 29110 ^ -5095));
   }

   private static 0cm method_bbg(0cj var0) {
      return var0.field_b;
   }

   private static 0cm method_bcf(0cj var0) {
      return var0.field_b;
   }

   private static DataFlavor method_bbk() {
      return DataFlavor.stringFlavor;
   }

   private static List method_bbH(0cj var0) {
      return var0.buttonList;
   }

   private static nC method_bbi(0cj var0) {
      return var0.mc;
   }

   private static jH method_bbA(nC var0) {
      return var0.fontRenderer;
   }

   private static lE method_bcg(0cj var0) {
      return var0.field_d;
   }

   private static 0cm method_bbX(0cj var0) {
      return var0.field_b;
   }

   private static lE method_bbp(0cj var0) {
      return var0.field_d;
   }

   private static jH method_bbt(nC var0) {
      return var0.fontRenderer;
   }

   private static lE method_bbl(0cj var0) {
      return var0.field_d;
   }

   private static nC method_bbN(0cj var0) {
      return var0.mc;
   }

   private static void method_bbU(0cj var0, 0cm var1) {
      var0.field_b = var1;
   }

   private static int method_bbB(0cj var0) {
      return var0.width;
   }

   private static lg method_bbj(0cj var0) {
      return var0.field_c;
   }

   private static List method_bcd(0cj var0) {
      return var0.buttonList;
   }

   private static 0cm method_bch(0cj var0) {
      return var0.field_b;
   }

   private static int method_bbe(jK var0) {
      return var0.id;
   }

   public void drawScreen(int a, int b, float c) {
      0en.method_bGt(Float.intBitsToFloat(32344 ^ 11448 ^ 18154 ^ -2138013073 ^ 27215 ^ 97233 ^ 127983 ^ -2138016748), Float.intBitsToFloat(28760 ^ 254553 ^ 17414 ^ 1119733955 ^ 10629 ^ 253170 ^ 6674 ^ 1119744417), (float)method_bbn(this), (float)method_bbo(this), new Color(9560 ^ -31446 ^ 6759 ^ -17917, 21224 ^ -14264 ^ 12784 ^ -21690, 21666 ^ -1585 ^ 7851 ^ -19504, 29843 ^ -20997 ^ 20183 ^ -26816));
      method_bbp(this).drawTextBox();
      method_bbq(this).method_bfZ();
      method_bbr(nC.getMinecraft()).drawStringWithShadow(method_bbd("٥وِ\u0604٨ًكٍي"), (float)method_bbs(this) / Float.intBitsToFloat(28534 ^ 88896 ^ '\ud978' ^ -1559439706 ^ 18877 ^ 115811 ^ 2660 ^ -485704622) - Float.intBitsToFloat(32532 ^ 'ꡩ' ^ 1925 ^ 565996030 ^ 19037 ^ 20312 ^ 14872 ^ 1620870683), Float.intBitsToFloat(31560 ^ 478401 ^ 522735 ^ 409690605 ^ 32661 ^ 'ꒆ' ^ 15121 ^ 1506496393), -20941 ^ -29732 ^ 11495 ^ -2313);
      method_bbt(nC.getMinecraft()).drawStringWithShadow(method_bbu(this) == null ? method_bbv() + method_bbd("٥وِٗ؊؊؊") : method_bbw(this).method_XQ(), (float)method_bbx(this) / Float.intBitsToFloat(9667 ^ '릢' ^ 13126 ^ 1760455126 ^ 128521 ^ 31533 ^ 124739 ^ 686730390), Float.intBitsToFloat(16773 ^ '荛' ^ 4323 ^ 765226248 ^ 103064 ^ 32442 ^ 129366 ^ 1819585089), -16513 ^ -23860 ^ 3239 ^ -4373);
      if (method_bby(this).getText().isEmpty() && !method_bbz(this).isFocused()) {
         method_bbA(nC.getMinecraft()).drawStringWithShadow(method_bbd("ٱٗفٖيمىف\u0604؋\u0604١؉٩مٍو"), (float)(method_bbB(this) / (25364 ^ -17655 ^ 16361 ^ -6154) - (7919 ^ -3934 ^ 18928 ^ -22563)), Float.intBitsToFloat(32678 ^ 100301 ^ 10349 ^ -1060177904 ^ 32308 ^ 101214 ^ 4565 ^ -2109027159), 32710 ^ '聒' ^ 21407 ^ -7854973);
      }

      if (method_bbC(this).method_bfL().isEmpty() && !method_bbD(this).method_bgi()) {
         method_bbE(nC.getMinecraft()).drawStringWithShadow(method_bbd("ٴمًٖٗٗٓـ"), (float)(method_bbF(this) / (31078 ^ -3466 ^ 15054 ^ -20004) - (11831 ^ -11495 ^ 11387 ^ -11979)), Float.intBitsToFloat(14767 ^ 18979 ^ 10854 ^ 2099256480 ^ '턦' ^ 'ꢳ' ^ 4045 ^ 1072968466), 20543 ^ '鰦' ^ 14733 ^ -7832292);
      }

      super.drawScreen(a, b, c);
   }

   private static 0cm method_bca(0cj var0) {
      return var0.field_b;
   }

   private static lE method_bbV(0cj var0) {
      return var0.field_d;
   }

   private static lE method_bbY(0cj var0) {
      return var0.field_d;
   }

   public void updateScreen() {
      method_bci(this).updateCursorCounter();
      method_bcj(this).method_bfK();
   }

   private static List method_bbJ(0cj var0) {
      return var0.buttonList;
   }

   private static int method_bbs(0cj var0) {
      return var0.width;
   }

   private static jH method_bbE(nC var0) {
      return var0.fontRenderer;
   }

   private static 0cm method_bcj(0cj var0) {
      return var0.field_b;
   }

   private static int method_bbM(0cj var0) {
      return var0.width;
   }

   protected void mouseClicked(int b, int c, int d) {
      try {
         super.mouseClicked(b, c, d);
      } catch (IOException var5) {
         IOException a = var5;
         a.printStackTrace();
      }

      method_bcg(this).mouseClicked(b, c, d);
      method_bch(this).method_bfY(b, c, d);
   }

   private static lE method_bce(0cj var0) {
      return var0.field_d;
   }

   private static void method_bbQ(0cj var0, lE var1) {
      var0.field_d = var1;
   }

   private static int method_bbo(0cj var0) {
      return var0.height;
   }

   private static lE method_bci(0cj var0) {
      return var0.field_d;
   }

   private static 0cm method_bbq(0cj var0) {
      return var0.field_b;
   }

   private static lE method_bbf(0cj var0) {
      return var0.field_d;
   }

   private static int method_bbG(0cj var0) {
      return var0.height;
   }

   private static lE method_bbz(0cj var0) {
      return var0.field_d;
   }

   private static lE method_bbW(0cj var0) {
      return var0.field_d;
   }

   private static 0cm method_bbD(0cj var0) {
      return var0.field_b;
   }

   private static int method_bbF(0cj var0) {
      return var0.width;
   }

   private static nC method_bbR(0cj var0) {
      return var0.mc;
   }

   private static jH method_bbr(nC var0) {
      return var0.fontRenderer;
   }

   protected void actionPerformed(jK d) {
      try {
         switch (method_bbe(d)) {
            case 0:
               0ce var10002 = new 0ce(new 0cd(method_bbf(this).getText(), method_bbg(this).method_bfL()));
               method_bbh(this, var10002);
               var10002.start();
               break;
            case 1:
               method_bbi(this).displayGuiScreen(method_bbj(this));
               break;
            case 2:
               String b = (String)Toolkit.getDefaultToolkit().getSystemClipboard().getData(method_bbk());
               if (b.contains(method_bbd("؞"))) {
                  String[] a = b.split(method_bbd("؞"));
                  method_bbl(this).setText(a[18259 ^ -22794 ^ 3090 ^ -4681]);
                  method_bbm(this).method_bfM(a[12231 ^ -23445 ^ 19429 ^ -16312]);
               }
         }

      } catch (Throwable var4) {
         throw new RuntimeException();
      }
   }

   private static 0cm method_bbm(0cj var0) {
      return var0.field_b;
   }

   private static int method_bbP(0cj var0) {
      return var0.width;
   }

   private static int method_bbn(0cj var0) {
      return var0.width;
   }

   public void initGui() {
      int a = method_bbG(this) / (19137 ^ -18169 ^ 9266 ^ -10256) + (16185 ^ -13084 ^ 4526 ^ -7573);
      method_bbH(this).add(new 0ci(17279 ^ -4863 ^ 14905 ^ -27577, method_bbI(this) / (6794 ^ -3177 ^ 6925 ^ -3566) - (13283 ^ -21045 ^ 2579 ^ -27553), a + (5977 ^ -4310 ^ 10925 ^ -11626) + (10008 ^ -26767 ^ 9662 ^ -27173), method_bbd("٨ًكٍي")));
      method_bbJ(this).add(new 0ci(24332 ^ -26341 ^ 15116 ^ -742, method_bbK(this) / (27518 ^ -11586 ^ 24352 ^ -6430) - (9528 ^ -9903 ^ 1462 ^ -1605), a + (11581 ^ -1404 ^ 15296 ^ -5071) + (12425 ^ -11647 ^ 10447 ^ -13621) + (28484 ^ -26168 ^ 21934 ^ -23750), method_bbd("٦مهُ")));
      method_bbL(this).add(new 0ci(8039 ^ -24790 ^ 17142 ^ -15687, method_bbM(this) / (32097 ^ -25322 ^ 10185 ^ -14404) - (11461 ^ -22589 ^ 29547 ^ -2039), a + (7912 ^ -12160 ^ 14137 ^ -1767) + (708 ^ -155 ^ 24942 ^ -25405) - (30001 ^ -24840 ^ 27744 ^ -30799), method_bbd("٭ىًِٖٔ\u0604ٱٗفٖ؞ٴمٗٗ")));
      method_bbQ(this, new lE(a, method_bbO(method_bbN(this)), method_bbP(this) / (30076 ^ -25765 ^ 30202 ^ -25633) - (5166 ^ -5249 ^ 31588 ^ -31663), 4637 ^ -1235 ^ 29739 ^ -25305, 21283 ^ -11293 ^ 21606 ^ -11154, 19400 ^ -6381 ^ 19264 ^ -6257));
      method_bbU(this, new 0cm(method_bbS(method_bbR(this)), method_bbT(this) / (25274 ^ -12091 ^ 29115 ^ -15418) - (12760 ^ -28919 ^ 11780 ^ -28495), 15830 ^ -30125 ^ 9624 ^ -28039, 30845 ^ -10163 ^ 6613 ^ -18131, 12459 ^ -27365 ^ 24584 ^ -14932));
      method_bbV(this).setFocused((boolean)(2815 ^ -11733 ^ 9561 ^ -628));
      Keyboard.enableRepeatEvents((boolean)(52 ^ -18637 ^ 3482 ^ -17764));
   }

   public _cj/* $FF was: 0cj*/(lg a) {
      this.field_c = a;
   }

   private static jH method_bbO(nC var0) {
      return var0.fontRenderer;
   }

   private static int method_bbI(0cj var0) {
      return var0.width;
   }

   private static lE method_bbZ(0cj var0) {
      return var0.field_d;
   }

   protected void keyTyped(char b, int c) {
      try {
         super.keyTyped(b, c);
      } catch (IOException var4) {
         IOException a = var4;
         a.printStackTrace();
      }

      if (b == (24900 ^ -10841 ^ 2375 ^ -16979)) {
         if (!method_bbW(this).isFocused() && !method_bbX(this).method_bgi()) {
            method_bbY(this).setFocused((boolean)(13940 ^ -16341 ^ 9848 ^ -12250));
         } else {
            method_bbZ(this).setFocused(method_bca(this).method_bgi());
            method_bcb(this).method_bgj((boolean)(!method_bcc(this).isFocused() ? 4404 ^ -16183 ^ 11355 ^ -601 : 43 ^ -27433 ^ 6324 ^ -29624));
         }
      }

      if (b == (28642 ^ -13354 ^ 19729 ^ -5848)) {
         this.actionPerformed((jK)method_bcd(this).get(28812 ^ -1273 ^ 16047 ^ -19164));
      }

      method_bce(this).textboxKeyTyped(b, c);
      method_bcf(this).method_bfX(b, c);
   }

   private static 0cm method_bcb(0cj var0) {
      return var0.field_b;
   }

   private static 0ce method_bbu(0cj var0) {
      return var0.field_a;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_bbd(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 15597 ^ -2233 ^ 28891 ^ -17551; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 12564 ^ 17389 ^ 29172 ^ 1321));
      }

      return var1.toString();
   }

   private static int method_bbx(0cj var0) {
      return var0.width;
   }

   private static jH method_bbS(nC var0) {
      return var0.fontRenderer;
   }

   private static List method_bbL(0cj var0) {
      return var0.buttonList;
   }

   private static int method_bbK(0cj var0) {
      return var0.width;
   }

   private static 0cm method_bbC(0cj var0) {
      return var0.field_b;
   }
}
