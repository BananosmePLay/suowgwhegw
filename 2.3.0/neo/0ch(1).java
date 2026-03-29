package neo;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.io.IOException;
import java.util.List;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.input.Keyboard;

public class 0ch extends lg {
   public String field_d;
   public lE field_c;
   public final 0ck field_a;
   public 0cm field_b;
   private static int _DSC GG NEOWARECLIENT _;

   private static jH method_bac(nC var0) {
      return var0.fontRenderer;
   }

   private static jH method_bag(nC var0) {
      return var0.fontRenderer;
   }

   private static lE method_Zv(0ch var0) {
      return var0.field_c;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_Zn(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 10816 ^ -6220 ^ 32051 ^ -20281; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 20606 ^ -4647 ^ 8567 ^ -26078));
      }

      return var1.toString();
   }

   _ch/* $FF was: 0ch*/(0ck a) {
      this.field_d = TextFormatting.GRAY + method_Zn("ڻږڞڗۜۜۜ");
      this.field_a = a;
   }

   private static String method_ZO(0ch var0) {
      return var0.field_d;
   }

   private static int method_baa(0ch var0) {
      return var0.eventButton;
   }

   private static int method_ZV(0ch var0) {
      return var0.width;
   }

   private static void method_bai(0ch var0, 0cm var1) {
      var0.field_b = var1;
   }

   private static lE method_Zq(0ch var0) {
      return var0.field_c;
   }

   protected void mouseClicked(int b, int c, int d) {
      try {
         super.mouseClicked(b, c, d);
      } catch (IOException var5) {
         IOException a = var5;
         a.printStackTrace();
      }

      method_bas(this).mouseClicked(b, c, d);
      method_bat(this).method_bfY(b, c, d);
   }

   private static lE method_bal(0ch var0) {
      return var0.field_c;
   }

   private static 0cm method_ZK(0ch var0) {
      return var0.field_b;
   }

   private static int method_ZS(0ch var0) {
      return var0.width;
   }

   private static int method_ZY(0ch var0) {
      return var0.width;
   }

   private static jH method_ZN(nC var0) {
      return var0.fontRenderer;
   }

   private static 0cm method_Zw(0ch var0) {
      return var0.field_b;
   }

   private static List method_bar(0ch var0) {
      return var0.buttonList;
   }

   private static int method_ZP(0ch var0) {
      return var0.width;
   }

   public void drawScreen(int a, int b, float c) {
      0en.method_bGt(Float.intBitsToFloat(28759 ^ 15773 ^ 26885 ^ -962360926 ^ 20270 ^ '馉' ^ 12904 ^ -962377310), Float.intBitsToFloat(243522 ^ '땬' ^ 260498 ^ -787915989 ^ 239178 ^ '魛' ^ 248790 ^ -787917232), (float)method_Zy(method_Zx(this)), (float)method_ZA(method_Zz(this)), new Color(19614 ^ -9902 ^ 3656 ^ -25707, 27219 ^ -3388 ^ 22787 ^ -15995, 4811 ^ -30174 ^ 32368 ^ -6520));
      method_ZB(this).drawTextBox();
      method_ZC(this).method_bfZ();
      method_ZD(nC.getMinecraft()).drawCenteredString(method_Zn("ڳږږےڳڑڑڝڇڜچ"), (double)((float)method_ZE(this) / Float.intBitsToFloat(10412 ^ 113327 ^ 119381 ^ -969110299 ^ 100969 ^ 120965 ^ 21005 ^ -2042835886)), Double.longBitsToDouble(2863694819914406712L ^ 7463558889319947064L), -19540 ^ -27283 ^ 22504 ^ -28970);
      if (method_ZF(this).getText().isEmpty() && !method_ZG(this).isFocused()) {
         method_ZH(nC.getMinecraft()).drawStringWithShadow(method_Zn("ڧځڗڀڜړڟڗے\u06ddےڷ۟ڿړڛڞ"), (float)(method_ZI(this) / (11403 ^ -12206 ^ 24448 ^ -23717) - (19746 ^ -24640 ^ 11264 ^ -382)), Float.intBitsToFloat('얫' ^ 9764 ^ '\uedb0' ^ 1878475160 ^ 13781 ^ 87399 ^ '퀕' ^ 762511104), 18985 ^ '隼' ^ 19857 ^ -7857780);
      }

      if (method_ZJ(this).method_bfL().isEmpty() && !method_ZK(this).method_bgi()) {
         method_ZL(nC.getMinecraft()).drawStringWithShadow(method_Zn("ڢړځځڅڝڀږ"), (float)(method_ZM(this) / (16523 ^ -32500 ^ 1346 ^ -15161) - (14286 ^ -10124 ^ 7357 ^ -3225)), Float.intBitsToFloat('탬' ^ '\uddbc' ^ 18257 ^ -727836038 ^ 1330 ^ 21788 ^ 12622 ^ -1773522661), 27138 ^ '푃' ^ 4098 ^ -7854389);
      }

      method_ZN(nC.getMinecraft()).drawCenteredString(method_ZO(this), (double)((float)method_ZP(this) / Float.intBitsToFloat(110423 ^ '둹' ^ 118965 ^ 1816947766 ^ 27408 ^ '趷' ^ 3580 ^ 743197942)), Double.longBitsToDouble(3569902119819193645L ^ 8193410087268429101L), -15191 ^ -1708 ^ 29009 ^ -19629);
      super.drawScreen(a, b, c);
   }

   private static int method_ZE(0ch var0) {
      return var0.width;
   }

   private static 0cm method_ZJ(0ch var0) {
      return var0.field_b;
   }

   private static 0cm method_bat(0ch var0) {
      return var0.field_b;
   }

   private static 0cm method_ZC(0ch var0) {
      return var0.field_b;
   }

   private static lE method_bao(0ch var0) {
      return var0.field_c;
   }

   private static int method_bad(0ch var0) {
      return var0.width;
   }

   private static lE method_bas(0ch var0) {
      return var0.field_c;
   }

   // $FF: synthetic method
   static void method_Zm(0ch a, String b) {
      method_Zl(a, b);
   }

   private static void method_bae(0ch var0, lE var1) {
      var0.field_c = var1;
   }

   private static int method_ZA(nC var0) {
      return var0.displayHeight;
   }

   protected void keyTyped(char a, int b) {
      method_baj(this).textboxKeyTyped(a, b);
      method_bak(this).method_bfX(a, b);
      if (a == (32299 ^ -22835 ^ 31158 ^ -24231) && (method_bal(this).isFocused() || method_bam(this).method_bgi())) {
         method_ban(this).setFocused((boolean)(!method_bao(this).isFocused() ? 20249 ^ -25031 ^ 24300 ^ -28723 : 21666 ^ -26018 ^ 6316 ^ -10672));
         method_bap(this).method_bgj((boolean)(!method_baq(this).method_bgi() ? 4450 ^ -29674 ^ 22434 ^ -13609 : 1888 ^ -28365 ^ 19350 ^ -8763));
      }

      if (a == (13231 ^ -32519 ^ 17023 ^ -3804)) {
         this.actionPerformed((jK)method_bar(this).get(13956 ^ -1384 ^ 31243 ^ -18921));
      }

   }

   private static List method_ZR(0ch var0) {
      return var0.buttonList;
   }

   private static int method_Zp(jK var0) {
      return var0.id;
   }

   private static int method_Zy(nC var0) {
      return var0.displayWidth;
   }

   private static nC method_bab(0ch var0) {
      return var0.mc;
   }

   private static void method_Zo(0ch var0, String var1) {
      var0.field_d = var1;
   }

   private static 0cm method_Zr(0ch var0) {
      return var0.field_b;
   }

   private static jH method_ZD(nC var0) {
      return var0.fontRenderer;
   }

   private static lE method_ZB(0ch var0) {
      return var0.field_c;
   }

   private static int method_bah(0ch var0) {
      return var0.width;
   }

   private static int method_ZW(0ch var0) {
      return var0.height;
   }

   private static 0ck method_Zt(0ch var0) {
      return var0.field_a;
   }

   private static 0cm method_baq(0ch var0) {
      return var0.field_b;
   }

   private static nC method_Zx(0ch var0) {
      return var0.mc;
   }

   private static jH method_ZH(nC var0) {
      return var0.fontRenderer;
   }

   private static int method_ZI(0ch var0) {
      return var0.width;
   }

   private static List method_ZQ(0ch var0) {
      return var0.buttonList;
   }

   private static nC method_Zz(0ch var0) {
      return var0.mc;
   }

   protected void actionPerformed(jK f) {
      switch (method_Zp(f)) {
         case 0:
            0cg a = new 0cg(this, method_Zq(this).getText(), method_Zr(this).method_bfL());
            a.start();
            break;
         case 1:
            method_Zs(this).displayGuiScreen(method_Zt(this));
            break;
         case 2:
            String e;
            try {
               e = (String)Toolkit.getDefaultToolkit().getSystemClipboard().getData(method_Zu());
            } catch (Exception var5) {
               return;
            }

            if (e.contains(method_Zn("ۈ"))) {
               String[] d = e.split(method_Zn("ۈ"));
               method_Zv(this).setText(d[19340 ^ -8094 ^ 7389 ^ -18637]);
               method_Zw(this).method_bfM(d[9182 ^ -20457 ^ 9053 ^ -20331]);
            }
      }

   }

   private static 0cm method_bap(0ch var0) {
      return var0.field_b;
   }

   private static lE method_baj(0ch var0) {
      return var0.field_c;
   }

   private static List method_ZX(0ch var0) {
      return var0.buttonList;
   }

   private static lE method_ZF(0ch var0) {
      return var0.field_c;
   }

   private static 0cm method_bak(0ch var0) {
      return var0.field_b;
   }

   private static int method_ZM(0ch var0) {
      return var0.width;
   }

   private static int method_ZT(0ch var0) {
      return var0.height;
   }

   private static nC method_baf(0ch var0) {
      return var0.mc;
   }

   private static nC method_Zs(0ch var0) {
      return var0.mc;
   }

   private static DataFlavor method_Zu() {
      return DataFlavor.stringFlavor;
   }

   private static List method_ZU(0ch var0) {
      return var0.buttonList;
   }

   private static lE method_ban(0ch var0) {
      return var0.field_c;
   }

   private static int method_ZZ(0ch var0) {
      return var0.height;
   }

   private static jH method_ZL(nC var0) {
      return var0.fontRenderer;
   }

   private static void method_Zl(0ch a, String b) {
      method_Zo(a, b);
   }

   private static lE method_ZG(0ch var0) {
      return var0.field_c;
   }

   public void initGui() {
      Keyboard.enableRepeatEvents((boolean)(22867 ^ -1638 ^ 12835 ^ -27925));
      method_ZQ(this).clear();
      method_ZR(this).add(new 0ci(32394 ^ -23677 ^ 27294 ^ -18537, method_ZS(this) / (16821 ^ -10082 ^ 21886 ^ -13225) - (8543 ^ -2256 ^ 21003 ^ -31744), method_ZT(this) / (5139 ^ -14111 ^ 2690 ^ -10636) + (18186 ^ -19111 ^ 1012 ^ -3589) + (31792 ^ -8061 ^ 11445 ^ -20470), method_Zn("ھڝڕڛڜ")));
      method_ZU(this).add(new 0ci(15507 ^ -17057 ^ 31428 ^ -1271, method_ZV(this) / (32118 ^ -27403 ^ 8319 ^ -13826) - (23063 ^ -6558 ^ 1616 ^ -17855), method_ZW(this) / (3489 ^ -3794 ^ 12729 ^ -13006) + (4625 ^ -26995 ^ 10917 ^ -20915) + (17704 ^ -11618 ^ 11920 ^ -18134), method_Zn("ڰړڑڙ")));
      method_ZX(this).add(new 0ci(31634 ^ -3724 ^ 17705 ^ -12339, method_ZY(this) / (13039 ^ -25211 ^ 3428 ^ -24052) - (1615 ^ -15071 ^ 12500 ^ -3106), method_ZZ(this) / (10834 ^ -11380 ^ 32328 ^ -30830) + (17238 ^ -6234 ^ 32102 ^ -9782) - (11507 ^ -29810 ^ 20632 ^ -2071), method_Zn("ڻڟڂڝڀچےڧځڗڀۈڢړځځ")));
      method_bae(this, new lE(method_baa(this), method_bac(method_bab(this)), method_bad(this) / (9800 ^ -22673 ^ 10526 ^ -22469) - (26017 ^ -7941 ^ 5102 ^ -26928), 25359 ^ -23987 ^ 9240 ^ -6810, 30457 ^ -28632 ^ 18762 ^ -20653, 7745 ^ -2522 ^ 1670 ^ -4363));
      method_bai(this, new 0cm(method_bag(method_baf(this)), method_bah(this) / (9769 ^ -23451 ^ 15798 ^ -16392) - (17199 ^ -22230 ^ 8521 ^ -13528), 9853 ^ -24489 ^ 24652 ^ -6654, 4574 ^ -16490 ^ 11596 ^ -31796, 17432 ^ -23749 ^ 17686 ^ -24031));
   }

   private static 0cm method_bam(0ch var0) {
      return var0.field_b;
   }
}
