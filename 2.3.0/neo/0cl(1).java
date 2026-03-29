package neo;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
import net.minecraft.util.text.TextFormatting;

public class 0cl extends lg {
   public lE field_c;
   public 0cm field_b;
   public String field_a;
   public final 0ck field_d;
   private static String _ _;

   public void drawScreen(int a, int b, float c) {
      new mC(method_beO(this));
      0en.method_bGt(Float.intBitsToFloat('ꮏ' ^ 17967 ^ '\uec77' ^ -926275407 ^ 4964 ^ 234618 ^ 17819 ^ -926290973), Float.intBitsToFloat(6026 ^ 4148243 ^ 4175710 ^ -304132945 ^ 6789 ^ 22917 ^ 28495 ^ -304116697), (float)method_beP(this), (float)method_beQ(this), new Color(31413 ^ -3645 ^ 18054 ^ -12831, 27381 ^ -28859 ^ 25201 ^ -30768, 30041 ^ -28224 ^ 24238 ^ -17882, 9519 ^ -9434 ^ 28966 ^ -28720));
      method_beS(method_beR(this)).drawStringWithShadow(method_beD("̢̧̯̎̿ͫ̊̿"), (float)method_beT(this) / Float.intBitsToFloat(261097 ^ 236559 ^ 11064 ^ -274886332 ^ 254840 ^ 8525 ^ 253424 ^ -1348615585), Float.intBitsToFloat(29036 ^ '閺' ^ 15137 ^ -573479052 ^ 31378 ^ 22350 ^ 5772 ^ -1661893677), -25307 ^ -18997 ^ 16748 ^ -27011);
      method_beV(method_beU(this)).drawStringWithShadow(method_beW(this), (float)method_beX(this) / Float.intBitsToFloat(21946 ^ 235526 ^ 28178 ^ 396731659 ^ 26810 ^ 238230 ^ 21076 ^ 1470471901), Float.intBitsToFloat(247945 ^ 31478 ^ 253095 ^ -581860193 ^ 257035 ^ 231459 ^ 21238 ^ -1661870951), -580 ^ -13793 ^ 26521 ^ -20539);
      method_beY(this).drawTextBox();
      method_beZ(this).method_bfZ();
      if (method_bfa(this).getText().isEmpty() && !method_bfb(this).isFocused()) {
         this.drawString(method_bfd(method_bfc(this)), method_beD("̪̦̮̅"), method_bfe(this) / (17593 ^ -8259 ^ 32562 ^ -7116) - (16875 ^ -21027 ^ 13045 ^ -8541), 30437 ^ -24549 ^ 21408 ^ -31460, 6611 ^ '똻' ^ 23484 ^ -7832356);
      }

      if (method_bff(this).method_bfL().isEmpty() && !method_bfg(this).method_bgi()) {
         this.drawString(method_bfi(method_bfh(this)), method_beD("̸̸̛̪̼̤̹̯"), method_bfj(this) / (15648 ^ -16408 ^ 19730 ^ -12328) - (23257 ^ -8527 ^ 6538 ^ -25214), 22745 ^ -29322 ^ 32422 ^ -21661, 28876 ^ '쭢' ^ 15475 ^ -7860395);
      }

      super.drawScreen(a, b, c);
   }

   private static 0cm method_beZ(0cl var0) {
      return var0.field_b;
   }

   protected void keyTyped(char a, int b) {
      method_bfz(this).textboxKeyTyped(a, b);
      method_bfA(this).method_bfX(a, b);
      if (a == (30205 ^ -3745 ^ 21709 ^ -12186) && (method_bfB(this).isFocused() || method_bfC(this).method_bgi())) {
         method_bfD(this).setFocused((boolean)(!method_bfE(this).isFocused() ? 7259 ^ -6326 ^ 15782 ^ -14666 : 32472 ^ -4053 ^ 27774 ^ -7539));
         method_bfF(this).method_bgj((boolean)(!method_bfG(this).method_bgi() ? 26618 ^ -24036 ^ 14071 ^ -3312 : 13008 ^ -20072 ^ 7963 ^ -25517));
      }

      if (a == (23996 ^ -31587 ^ 13759 ^ -4973)) {
         this.actionPerformed((jK)method_bfH(this).get(6055 ^ -6051 ^ 13676 ^ -13674));
      }

   }

   private static int method_beX(0cl var0) {
      return var0.width;
   }

   private static lE method_bfB(0cl var0) {
      return var0.field_c;
   }

   private static nC method_bfh(0cl var0) {
      return var0.mc;
   }

   private static 0ck method_beI(0cl var0) {
      return var0.field_d;
   }

   private static nC method_bfc(0cl var0) {
      return var0.mc;
   }

   private static int method_bfq(0cl var0) {
      return var0.eventButton;
   }

   private static void method_bfy(0cl var0, 0cm var1) {
      var0.field_b = var1;
   }

   private static int method_bft(0cl var0) {
      return var0.width;
   }

   private static jH method_bfd(nC var0) {
      return var0.fontRenderer;
   }

   private static List method_bfH(0cl var0) {
      return var0.buttonList;
   }

   private static int method_bfl(0cl var0) {
      return var0.width;
   }

   private static int method_bfe(0cl var0) {
      return var0.width;
   }

   public void actionPerformed(jK a) {
      switch (method_beE(a)) {
         case 0:
            method_beG(method_beF(this)).method_XD(method_beH(this).getText());
            method_beJ(method_beI(this)).method_XF(method_beK(this).method_bfL());
            method_beL(this, method_beD("̢̯̮̯̎̿ͪ"));
            break;
         case 1:
            method_beM(this).displayGuiScreen(method_beN(this));
      }

   }

   private static 0cm method_bfC(0cl var0) {
      return var0.field_b;
   }

   private static jH method_bfi(nC var0) {
      return var0.fontRenderer;
   }

   private static int method_bfx(0cl var0) {
      return var0.width;
   }

   private static List method_bfk(0cl var0) {
      return var0.buttonList;
   }

   private static int method_beE(jK var0) {
      return var0.id;
   }

   private static 0ck method_beF(0cl var0) {
      return var0.field_d;
   }

   private static nC method_beR(0cl var0) {
      return var0.mc;
   }

   private static lE method_bfD(0cl var0) {
      return var0.field_c;
   }

   private static lE method_beH(0cl var0) {
      return var0.field_c;
   }

   private static nC method_beO(0cl var0) {
      return var0.mc;
   }

   private static int method_bfp(0cl var0) {
      return var0.height;
   }

   private static List method_bfn(0cl var0) {
      return var0.buttonList;
   }

   private static jH method_beS(nC var0) {
      return var0.fontRenderer;
   }

   private static 0ck method_beN(0cl var0) {
      return var0.field_d;
   }

   private static nC method_bfr(0cl var0) {
      return var0.mc;
   }

   private static String method_beW(0cl var0) {
      return var0.field_a;
   }

   private static jH method_beV(nC var0) {
      return var0.fontRenderer;
   }

   protected void mouseClicked(int b, int c, int d) {
      try {
         super.mouseClicked(b, c, d);
      } catch (IOException var5) {
         IOException a = var5;
         a.printStackTrace();
      }

      method_bfI(this).mouseClicked(b, c, d);
      method_bfJ(this).method_bfY(b, c, d);
   }

   private static nC method_bfv(0cl var0) {
      return var0.mc;
   }

   private static lE method_bfE(0cl var0) {
      return var0.field_c;
   }

   private static jH method_bfs(nC var0) {
      return var0.fontRenderer;
   }

   private static void method_beL(0cl var0, String var1) {
      var0.field_a = var1;
   }

   private static nC method_beU(0cl var0) {
      return var0.mc;
   }

   private static 0cm method_bfG(0cl var0) {
      return var0.field_b;
   }

   private static nC method_beM(0cl var0) {
      return var0.mc;
   }

   private static 0cm method_bfg(0cl var0) {
      return var0.field_b;
   }

   private static 0cm method_bfF(0cl var0) {
      return var0.field_b;
   }

   private static int method_beQ(0cl var0) {
      return var0.height;
   }

   private static lE method_beY(0cl var0) {
      return var0.field_c;
   }

   private static int method_beT(0cl var0) {
      return var0.width;
   }

   private static 0cm method_bff(0cl var0) {
      return var0.field_b;
   }

   private static void method_bfu(0cl var0, lE var1) {
      var0.field_c = var1;
   }

   private static lE method_bfa(0cl var0) {
      return var0.field_c;
   }

   private static lE method_bfz(0cl var0) {
      return var0.field_c;
   }

   private static 0cm method_beK(0cl var0) {
      return var0.field_b;
   }

   private static int method_bfj(0cl var0) {
      return var0.width;
   }

   private static lE method_bfb(0cl var0) {
      return var0.field_c;
   }

   private static 0cd method_beJ(0ck var0) {
      return var0.field_d;
   }

   private static lE method_bfI(0cl var0) {
      return var0.field_c;
   }

   public _cl/* $FF was: 0cl*/(0ck a) {
      this.field_a = TextFormatting.GRAY + method_beD("̢̢̜̪̥̬̿ͥͥͥ");
      this.field_d = a;
   }

   private static int method_beP(0cl var0) {
      return var0.width;
   }

   public void initGui() {
      method_bfk(this).add(new 0ci(16481 ^ -22963 ^ 5063 ^ -2581, method_bfl(this) / (19254 ^ -23489 ^ 25572 ^ -29457) - (6525 ^ -19289 ^ 26021 ^ -14309), method_bfm(this) / (4441 ^ -4314 ^ 6481 ^ -6358) + (7834 ^ -4417 ^ 9295 ^ -11210) + (10683 ^ -22468 ^ 16085 ^ -16546), method_beD("̢̯̎̿")));
      method_bfn(this).add(new 0ci(31202 ^ -15285 ^ 6824 ^ -22784, method_bfo(this) / (1849 ^ -4173 ^ 4265 ^ -2015) - (4789 ^ -25621 ^ 30635 ^ -367), method_bfp(this) / (8082 ^ -21006 ^ 29688 ^ -15972) + (15062 ^ -18457 ^ 15798 ^ -20237) + (16799 ^ -29811 ^ 4305 ^ -9521), method_beD("̨̧̪̥̮̈")));
      method_bfu(this, new lE(method_bfq(this), method_bfs(method_bfr(this)), method_bft(this) / (10387 ^ -31461 ^ 1881 ^ -21805) - (14274 ^ -11098 ^ 15031 ^ -9801), 21884 ^ -16269 ^ 13560 ^ -24117, 15654 ^ -16081 ^ 26170 ^ -25861, 26381 ^ -2761 ^ 13612 ^ -22782));
      method_bfy(this, new 0cm(method_bfw(method_bfv(this)), method_bfx(this) / (14960 ^ -30280 ^ 5099 ^ -24543) - (13170 ^ -7421 ^ 26874 ^ -18193), 32454 ^ -19457 ^ 4036 ^ -15719, 5179 ^ -10194 ^ 20232 ^ -31787, 9019 ^ -2967 ^ 26442 ^ -20468));
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_beD(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 22388 ^ -5617 ^ 6734 ^ -22731; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 12557 ^ -5175 ^ 28852 ^ -22213));
      }

      return var1.toString();
   }

   private static jH method_bfw(nC var0) {
      return var0.fontRenderer;
   }

   private static 0cm method_bfJ(0cl var0) {
      return var0.field_b;
   }

   private static 0cm method_bfA(0cl var0) {
      return var0.field_b;
   }

   private static int method_bfo(0cl var0) {
      return var0.width;
   }

   private static int method_bfm(0cl var0) {
      return var0.height;
   }

   private static 0cd method_beG(0ck var0) {
      return var0.field_d;
   }
}
