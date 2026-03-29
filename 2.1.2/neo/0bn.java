package neo;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.io.IOException;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.input.Keyboard;

public final class 0bn extends GuiScreen {
   public GuiTextField username;
   public 0bq password;
   public final GuiScreen previousScreen;
   public 0bc thread;

   private static GuiTextField pl52B6xTnz(0bn var0) {
      return var0.username;
   }

   private static FontRenderer _vTsuDifNL/* $FF was: 7vTsuDifNL*/(Minecraft var0) {
      return var0.fontRenderer;
   }

   private static Minecraft _nGq3tEduW/* $FF was: 4nGq3tEduW*/(0bn var0) {
      return var0.mc;
   }

   private static int uPBAjvICPl(0bn var0) {
      return var0.height;
   }

   private static int ShVASOy7rM(0bn var0) {
      return var0.width;
   }

   private static int wBFbzWPbZd(0bn var0) {
      return var0.width;
   }

   private static FontRenderer kgL2gjowrA(Minecraft var0) {
      return var0.fontRenderer;
   }

   private static List bDDBEebVV1(0bn var0) {
      return var0.buttonList;
   }

   private static List v0rcnnK62q(0bn var0) {
      return var0.buttonList;
   }

   private static GuiTextField gMe9hF8QMT(0bn var0) {
      return var0.username;
   }

   private static 0bq lakOvLbi2q(0bn var0) {
      return var0.password;
   }

   private static GuiTextField KofCG4TfZv(0bn var0) {
      return var0.username;
   }

   private static GuiTextField Di7iekdxkb(0bn var0) {
      return var0.username;
   }

   public void updateScreen() {
      7SkC1zh4in(this).updateCursorCounter();
      Jqw1l7GU0f(this).updateCursorCounter();
   }

   protected void keyTyped(char character, int key) {
      try {
         super.keyTyped(character, key);
      } catch (IOException var4) {
         IOException e = var4;
         e.printStackTrace();
      }

      if (character == (23815 ^ -10761 ^ 12960 ^ -17831)) {
         if (!6BJsE29vUS(this).isFocused() && !j4Ablq1Qe9(this).isFocused()) {
            pl52B6xTnz(this).setFocused((boolean)(30667 ^ -10661 ^ 15672 ^ -25431));
         } else {
            rt11J6XYB9(this).setFocused(qd4XiA2HAg(this).isFocused());
            S1qyBOiRe6(this).setFocused((boolean)(!gMe9hF8QMT(this).isFocused() ? 11253 ^ -20308 ^ 2151 ^ -27841 : 32412 ^ -19635 ^ 18235 ^ -29974));
         }
      }

      if (character == (2586 ^ -23706 ^ 20606 ^ -1777)) {
         this.actionPerformed((GuiButton)bDDBEebVV1(this).get(25775 ^ -11618 ^ 16901 ^ -3020));
      }

      VgfJdVi8Ds(this).textboxKeyTyped(character, key);
      FXLZlTnMl7(this).textboxKeyTyped(character, key);
   }

   private static void L8v0Qx1aYj(0bn var0, 0bc var1) {
      var0.thread = var1;
   }

   private static 0bq Jqw1l7GU0f(0bn var0) {
      return var0.password;
   }

   private static FontRenderer RQstUzSjtb(Minecraft var0) {
      return var0.fontRenderer;
   }

   private static int fVqgtfbGt8(0bn var0) {
      return var0.width;
   }

   private static 0bq eLoOsoLODN(0bn var0) {
      return var0.password;
   }

   protected void mouseClicked(int x, int y, int button) {
      try {
         super.mouseClicked(x, y, button);
      } catch (IOException var5) {
         IOException e = var5;
         e.printStackTrace();
      }

      yWrvvyR48E(this).mouseClicked(x, y, button);
      JdZoCBjh9F(this).mouseClicked(x, y, button);
   }

   private static GuiTextField yWrvvyR48E(0bn var0) {
      return var0.username;
   }

   private static List bLgky67y4q(0bn var0) {
      return var0.buttonList;
   }

   private static 0bc TqCb7Jf1cH(0bn var0) {
      return var0.thread;
   }

   private static 0bc bfNtj6kUYz(0bn var0) {
      return var0.thread;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String evW2QjIElR(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 25727 ^ -4202 ^ 31772 ^ -2059; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 11791 ^ -22807 ^ 19219 ^ -14360));
      }

      return var1.toString();
   }

   private static List JoW2YQFfM7(0bn var0) {
      return var0.buttonList;
   }

   private static GuiTextField BOOoq3GHiB(0bn var0) {
      return var0.username;
   }

   private static FontRenderer tEvVhNQgIP(Minecraft var0) {
      return var0.fontRenderer;
   }

   private static GuiTextField bof3JBB3tL(0bn var0) {
      return var0.username;
   }

   private static int i1AlJeTrcI(0bn var0) {
      return var0.width;
   }

   private static int x3eWV714Tu(0bn var0) {
      return var0.width;
   }

   private static int wFLreeqwjv(GuiButton var0) {
      return var0.id;
   }

   private static GuiTextField n1QDu9B1qu(0bn var0) {
      return var0.username;
   }

   private static 0bq FXLZlTnMl7(0bn var0) {
      return var0.password;
   }

   private static int tAn5rIabJi(0bn var0) {
      return var0.width;
   }

   public void initGui() {
      int height1 = uPBAjvICPl(this) / (13320 ^ -20882 ^ 8183 ^ -31339) + (24439 ^ -27526 ^ 21091 ^ -26250);
      v0rcnnK62q(this).add(new 0bm(6459 ^ -12392 ^ 23180 ^ -29649, x3eWV714Tu(this) / (2391 ^ -8833 ^ 28312 ^ -17742) - (28811 ^ -953 ^ 27183 ^ -6521), height1 + (14836 ^ -16784 ^ 31328 ^ -596) + (1364 ^ -15362 ^ 10166 ^ -7920), evW2QjIElR("ёѲѺѴѳ")));
      bLgky67y4q(this).add(new 0bm(8024 ^ -5091 ^ 1263 ^ -2133, ShVASOy7rM(this) / (13978 ^ -9815 ^ 4646 ^ -745) - (4229 ^ -10096 ^ 14960 ^ -3583), height1 + (16239 ^ -24681 ^ 13439 ^ -27441) + (19930 ^ -2833 ^ 16286 ^ -31065) + (13897 ^ -24812 ^ 30919 ^ -11902), evW2QjIElR("џѼѾѶ")));
      JoW2YQFfM7(this).add(new 0bm(18258 ^ -15819 ^ 28792 ^ -2787, ET6Z3M7Gml(this) / (22673 ^ -19974 ^ 14219 ^ -8478) - (21557 ^ -2212 ^ 29755 ^ -10442), height1 + (16165 ^ -29586 ^ 21567 ^ -6340) + (1374 ^ -25604 ^ 26998 ^ -2088) - (12371 ^ -21057 ^ 5690 ^ -29746), evW2QjIElR("єѰѭѲѯѩншѮѸѯЧэѼѮѮ")));
      nXe9qLOmJW(this, new GuiTextField(height1, USWBNaHDSo(E9YdO7eWwv(this)), g1tyLnBnOc(this) / (16102 ^ -15437 ^ 25284 ^ -24685) - (32508 ^ -6155 ^ 23490 ^ -15697), 18424 ^ -23281 ^ 8367 ^ -15772, 16110 ^ -21065 ^ 20370 ^ -9213, 7961 ^ -18679 ^ 7542 ^ -19086));
      i1g7GKrS1U(this, new 0bq(7vTsuDifNL(4nGq3tEduW(this)), tAn5rIabJi(this) / (25558 ^ -9677 ^ 17715 ^ -812) - (11989 ^ -5973 ^ 8761 ^ -7133), 18528 ^ -17135 ^ 28968 ^ -31683, 12076 ^ -25184 ^ 1078 ^ -18830, 20832 ^ -8160 ^ 2136 ^ -18164));
      7p0mpP1AAA(this).setFocused((boolean)(32068 ^ -29820 ^ 2219 ^ -406));
      Keyboard.enableRepeatEvents((boolean)(23069 ^ -21854 ^ 5497 ^ -6713));
   }

   public void onGuiClosed() {
      Keyboard.enableRepeatEvents((boolean)(19267 ^ -15099 ^ 310 ^ -28816));
   }

   public void drawScreen(int x, int y, float z) {
      0ew.drawRect(Float.intBitsToFloat(6566 ^ '鲕' ^ 16799 ^ -1077653805 ^ 12675 ^ 'ꚙ' ^ 16700 ^ -1077650343), Float.intBitsToFloat(25336 ^ 94314 ^ 'ﶩ' ^ 1521012918 ^ 19225 ^ 93519 ^ '짟' ^ 1521012740), (float)i1AlJeTrcI(this), (float)wMQBL1EgPI(this), new Color(10722 ^ -18080 ^ 25402 ^ -3154, 10749 ^ -9214 ^ 7237 ^ -5716, 21942 ^ -19460 ^ 25014 ^ -30742, 28004 ^ -838 ^ 29003 ^ -8086));
      n1QDu9B1qu(this).drawTextBox();
      qqmOJA4Q4G(this).drawTextBox();
      tEvVhNQgIP(Minecraft.getMinecraft()).drawStringWithShadow(evW2QjIElR("ќѱѩнёѲѺѴѳ"), (float)7t2vyY8Oo1(this) / Float.intBitsToFloat('רּ' ^ 75000 ^ 23847 ^ 1583411944 ^ '껢' ^ '煮' ^ 4448 ^ 509687137) - Float.intBitsToFloat('鏦' ^ 122894 ^ '\uf5af' ^ 1502265237 ^ '颕' ^ '뎎' ^ 3283 ^ 413867546), Float.intBitsToFloat(16710 ^ '꺱' ^ 7009 ^ 697464455 ^ 12684 ^ '잮' ^ 28732 ^ 1748108303), -12082 ^ -13050 ^ 20833 ^ -19626);
      RVW1spPbND(Minecraft.getMinecraft()).drawStringWithShadow(bfNtj6kUYz(this) == null ? FSuyuM9bYy() + evW2QjIElR("ќѱѩѮггг") : TqCb7Jf1cH(this).getStatus(), (float)fVqgtfbGt8(this) / Float.intBitsToFloat(498801 ^ 'ﶔ' ^ 510087 ^ -833932759 ^ 498368 ^ 14570 ^ 503394 ^ -1907648765), Float.intBitsToFloat(8504 ^ '퇥' ^ 17595 ^ -2001980849 ^ 20828 ^ '슚' ^ 730 ^ -918284491), -16937 ^ -19916 ^ 18653 ^ -18239);
      if (BOOoq3GHiB(this).getText().isEmpty() && !KofCG4TfZv(this).isFocused()) {
         kgL2gjowrA(Minecraft.getMinecraft()).drawStringWithShadow(evW2QjIElR("шѮѸѯѳѼѰѸнвнјаѐѼѴѱ"), (float)(wBFbzWPbZd(this) / (13665 ^ -7010 ^ 15746 ^ -4993) - (30294 ^ -566 ^ 10234 ^ -21498)), Float.intBitsToFloat(3934 ^ 89563 ^ '\udb4e' ^ 1319558364 ^ '\ueb74' ^ 21866 ^ '흆' ^ 203635791), 7279 ^ '\uf506' ^ 21816 ^ -7850791);
      }

      if (ovUleE0SJa(this).getText().isEmpty() && !lakOvLbi2q(this).isFocused()) {
         RQstUzSjtb(Minecraft.getMinecraft()).drawStringWithShadow(evW2QjIElR("эѼѮѮѪѲѯѹ"), (float)(mt5VIPCuga(this) / (18229 ^ -1188 ^ 19326 ^ -2283) - (9807 ^ -16958 ^ 25624 ^ -11)), Float.intBitsToFloat(124299 ^ 84583 ^ 2703 ^ 1989176520 ^ 118061 ^ 130147 ^ 14792 ^ 876921133), 287 ^ '쨿' ^ 13839 ^ -7834201);
      }

      super.drawScreen(x, y, z);
   }

   private static 0bq S1qyBOiRe6(0bn var0) {
      return var0.password;
   }

   private static int g1tyLnBnOc(0bn var0) {
      return var0.width;
   }

   private static 0bq _QfROqETLo/* $FF was: 9QfROqETLo*/(0bn var0) {
      return var0.password;
   }

   protected void actionPerformed(GuiButton button) {
      try {
         switch (wFLreeqwjv(button)) {
            case 0:
               0bc var10002 = new 0bc(new 0bb(Di7iekdxkb(this).getText(), eLoOsoLODN(this).getText()));
               L8v0Qx1aYj(this, var10002);
               var10002.start();
               break;
            case 1:
               qBewjUq1VJ(this).displayGuiScreen(PtSeD29D7j(this));
               break;
            case 2:
               String data = (String)Toolkit.getDefaultToolkit().getSystemClipboard().getData(ZtSp6FWCV2());
               if (data.contains(evW2QjIElR("Ч"))) {
                  String[] credentials = data.split(evW2QjIElR("Ч"));
                  bof3JBB3tL(this).setText(credentials[16958 ^ -29221 ^ 18793 ^ -31092]);
                  9QfROqETLo(this).setText(credentials[23511 ^ -30215 ^ 811 ^ -12028]);
               }
         }

      } catch (Throwable var4) {
         throw new RuntimeException();
      }
   }

   private static int mt5VIPCuga(0bn var0) {
      return var0.width;
   }

   private static 0bq qqmOJA4Q4G(0bn var0) {
      return var0.password;
   }

   private static 0bq ovUleE0SJa(0bn var0) {
      return var0.password;
   }

   private static 0bq qd4XiA2HAg(0bn var0) {
      return var0.password;
   }

   private static DataFlavor ZtSp6FWCV2() {
      return DataFlavor.stringFlavor;
   }

   private static GuiTextField _BJsE29vUS/* $FF was: 6BJsE29vUS*/(0bn var0) {
      return var0.username;
   }

   private static FontRenderer USWBNaHDSo(Minecraft var0) {
      return var0.fontRenderer;
   }

   private static GuiTextField _p0mpP1AAA/* $FF was: 7p0mpP1AAA*/(0bn var0) {
      return var0.username;
   }

   private static void nXe9qLOmJW(0bn var0, GuiTextField var1) {
      var0.username = var1;
   }

   private static GuiScreen PtSeD29D7j(0bn var0) {
      return var0.previousScreen;
   }

   private static TextFormatting FSuyuM9bYy() {
      return TextFormatting.GRAY;
   }

   private static 0bq JdZoCBjh9F(0bn var0) {
      return var0.password;
   }

   public _bn/* $FF was: 0bn*/(GuiScreen previousScreen) {
      this.previousScreen = previousScreen;
   }

   private static Minecraft qBewjUq1VJ(0bn var0) {
      return var0.mc;
   }

   private static GuiTextField rt11J6XYB9(0bn var0) {
      return var0.username;
   }

   private static int wMQBL1EgPI(0bn var0) {
      return var0.height;
   }

   private static 0bq j4Ablq1Qe9(0bn var0) {
      return var0.password;
   }

   private static int _t2vyY8Oo1/* $FF was: 7t2vyY8Oo1*/(0bn var0) {
      return var0.width;
   }

   private static int ET6Z3M7Gml(0bn var0) {
      return var0.width;
   }

   private static FontRenderer RVW1spPbND(Minecraft var0) {
      return var0.fontRenderer;
   }

   private static GuiTextField VgfJdVi8Ds(0bn var0) {
      return var0.username;
   }

   private static void i1g7GKrS1U(0bn var0, 0bq var1) {
      var0.password = var1;
   }

   private static Minecraft E9YdO7eWwv(0bn var0) {
      return var0.mc;
   }

   private static GuiTextField _SkC1zh4in/* $FF was: 7SkC1zh4in*/(0bn var0) {
      return var0.username;
   }
}
