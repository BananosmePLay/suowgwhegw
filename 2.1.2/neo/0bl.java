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

public class 0bl extends GuiScreen {
   public String status;
   private Color gradientColor2;
   public final 0bo manager;
   private Color gradientColor3;
   public GuiTextField username;
   private Color gradientColor1;
   private Color gradientColor4;
   public 0bq password;

   private static List _H3z73jZ8B/* $FF was: 2H3z73jZ8B*/(0bl var0) {
      return var0.buttonList;
   }

   private static List W7kiquXu6d(0bl var0) {
      return var0.buttonList;
   }

   private static int ScTOYwblbD(0bl var0) {
      return var0.width;
   }

   private static 0bq STF2Rbi6JG(0bl var0) {
      return var0.password;
   }

   private static 0bq _O28AOkgtz/* $FF was: 6O28AOkgtz*/(0bl var0) {
      return var0.password;
   }

   _bl/* $FF was: 0bl*/(0bo manager) {
      this.gradientColor1 = Color.WHITE;
      this.gradientColor2 = Color.WHITE;
      this.gradientColor3 = Color.WHITE;
      this.gradientColor4 = Color.WHITE;
      this.status = TextFormatting.GRAY + SRb81svhbG("ߧߊ߂ߋހހހ");
      this.manager = manager;
   }

   private static int LWF5l8NWtr(0bl var0) {
      return var0.width;
   }

   private static GuiTextField JGAWIcK9FH(0bl var0) {
      return var0.username;
   }

   private static List n0DZeVsToX(0bl var0) {
      return var0.buttonList;
   }

   private static Minecraft iSXG6Ex3Jg(0bl var0) {
      return var0.mc;
   }

   private static int _oqaAiafg8/* $FF was: 7oqaAiafg8*/(0bl var0) {
      return var0.width;
   }

   private static FontRenderer NOjvWjAwYD(Minecraft var0) {
      return var0.fontRenderer;
   }

   private static FontRenderer eKW93gmbIz(Minecraft var0) {
      return var0.fontRenderer;
   }

   private static int _LBPVaIrwO/* $FF was: 9LBPVaIrwO*/(0bl var0) {
      return var0.width;
   }

   private static List SjyW81tCog(0bl var0) {
      return var0.buttonList;
   }

   private static GuiTextField l6FcrvyoQA(0bl var0) {
      return var0.username;
   }

   private static int AuLQsBJA40(0bl var0) {
      return var0.width;
   }

   private static Minecraft IEG0XZIXI9(0bl var0) {
      return var0.mc;
   }

   private static GuiTextField wAUTifFW7Q(0bl var0) {
      return var0.username;
   }

   private static 0bq _wAeVmBjTO/* $FF was: 5wAeVmBjTO*/(0bl var0) {
      return var0.password;
   }

   private static void BocryN2Ghl(0bl var0, GuiTextField var1) {
      var0.username = var1;
   }

   private static int _TgJe6t7E6/* $FF was: 7TgJe6t7E6*/(0bl var0) {
      return var0.width;
   }

   public void drawScreen(int i, int j, float f) {
      0ew.drawRect(Float.intBitsToFloat(18374 ^ 128878 ^ 7642 ^ 70432632 ^ 9557 ^ 120544 ^ 30164 ^ 70425707), Float.intBitsToFloat(10521 ^ '葡' ^ 13075 ^ -1650499978 ^ 4192016 ^ '蓲' ^ 4185260 ^ -1650495661), (float)HetN6hrQdN(cqbAfqayH0(this)), (float)c5LTDoTbL9(IEG0XZIXI9(this)), new Color(26704 ^ -17583 ^ 30555 ^ -23477, 17441 ^ -27354 ^ 10850 ^ -1164, 10703 ^ -28179 ^ 11002 ^ -27959));
      wAUTifFW7Q(this).drawTextBox();
      6O28AOkgtz(this).drawTextBox();
      jS4fD1w488(Minecraft.getMinecraft()).drawCenteredString(SRb81svhbG("߯ߊߊގ߯ߍߍ߁ߛ߀ߚ"), (double)((float)9LBPVaIrwO(this) / Float.intBitsToFloat(236681 ^ 249220 ^ 27975 ^ -112342928 ^ 246044 ^ 244447 ^ 6567 ^ -1186097570)), Double.longBitsToDouble(5682149967197956152L ^ 1077782298165045304L), -11206 ^ -3874 ^ 25617 ^ -16630);
      if (T07is1lFAn(this).getText().isEmpty() && !JGAWIcK9FH(this).isFocused()) {
         MSOZS1N0rm(Minecraft.getMinecraft()).drawStringWithShadow(SRb81svhbG("\u07fbߝߋߜ߀ߏ߃ߋގށގ߫ރߣߏ߇߂"), (float)(vA1way8NVa(this) / (26333 ^ -29381 ^ 12584 ^ -9524) - (2534 ^ -18766 ^ 25335 ^ -8765)), Float.intBitsToFloat(9242 ^ 2057803 ^ 2092379 ^ 481261655 ^ 497818 ^ 16177 ^ 500252 ^ 1579930346), 5798 ^ '諐' ^ 20749 ^ -7846413);
      }

      if (hGPjBFSjDD(this).getText().isEmpty() && !LF6yvAY8rF(this).isFocused()) {
         ArKsV972qt(Minecraft.getMinecraft()).drawStringWithShadow(SRb81svhbG("߾ߏߝߝߙ߁ߜߊ"), (float)(LWF5l8NWtr(this) / (8341 ^ -18055 ^ 7946 ^ -31004) - (29297 ^ -27785 ^ 27876 ^ -29310)), Float.intBitsToFloat(243899 ^ 251297 ^ 31342 ^ 1999235589 ^ 232156 ^ 253380 ^ 11380 ^ 905807389), 8885 ^ '\uec56' ^ 7387 ^ -7841104);
      }

      eKW93gmbIz(Minecraft.getMinecraft()).drawCenteredString(9wywcV9JVl(this), (double)((float)AuLQsBJA40(this) / Float.intBitsToFloat(259642 ^ 245140 ^ 1108 ^ 1810997655 ^ 237639 ^ 260102 ^ 1515 ^ 737260487)), Double.longBitsToDouble(-1988061135771517557L ^ -6604813703779697269L), -5887 ^ -23319 ^ 4055 ^ -16960);
      super.drawScreen(i, j, f);
   }

   private static GuiTextField _i4XXAE42N/* $FF was: 3i4XXAE42N*/(0bl var0) {
      return var0.username;
   }

   private static int vA1way8NVa(0bl var0) {
      return var0.width;
   }

   private static 0bq wBfqw4rLHp(0bl var0) {
      return var0.password;
   }

   private static 0bo _JqVwybT4Z/* $FF was: 1JqVwybT4Z*/(0bl var0) {
      return var0.manager;
   }

   private static int HetN6hrQdN(Minecraft var0) {
      return var0.displayWidth;
   }

   private static FontRenderer MSOZS1N0rm(Minecraft var0) {
      return var0.fontRenderer;
   }

   private static GuiTextField efQ74Q3BW7(0bl var0) {
      return var0.username;
   }

   private static GuiTextField e41vc1MihQ(0bl var0) {
      return var0.username;
   }

   protected void mouseClicked(int par1, int par2, int par3) {
      try {
         super.mouseClicked(par1, par2, par3);
      } catch (IOException var5) {
         IOException var5 = var5;
         var5.printStackTrace();
      }

      YeSef5Boig(this).mouseClicked(par1, par2, par3);
      vIEnBTZoDH(this).mouseClicked(par1, par2, par3);
   }

   private static int J9eTYS9dBa(0bl var0) {
      return var0.height;
   }

   private static GuiTextField YeSef5Boig(0bl var0) {
      return var0.username;
   }

   protected void keyTyped(char par1, int par2) {
      VnVDPM2DOM(this).textboxKeyTyped(par1, par2);
      5wAeVmBjTO(this).textboxKeyTyped(par1, par2);
      if (par1 == (24069 ^ -18843 ^ 12885 ^ -9668) && (efQ74Q3BW7(this).isFocused() || FXOtaSkXBy(this).isFocused())) {
         e41vc1MihQ(this).setFocused((boolean)(!l6FcrvyoQA(this).isFocused() ? 1566 ^ -14217 ^ 10726 ^ -6258 : 25662 ^ -5690 ^ 25559 ^ -4561));
         STF2Rbi6JG(this).setFocused((boolean)(!gEDNS6Ilra(this).isFocused() ? 14881 ^ -29872 ^ 28029 ^ -9203 : 22115 ^ -3976 ^ 13339 ^ -28160));
      }

      if (par1 == (22584 ^ -9139 ^ 18935 ^ -12913)) {
         this.actionPerformed((GuiButton)QV29lUTrq3(this).get(26074 ^ -17194 ^ 5418 ^ -13274));
      }

   }

   private static int VlLsFzjyYn(GuiButton var0) {
      return var0.id;
   }

   private static FontRenderer ArKsV972qt(Minecraft var0) {
      return var0.fontRenderer;
   }

   private static int c5LTDoTbL9(Minecraft var0) {
      return var0.displayHeight;
   }

   private static void Ga1C5XFoOy(0bl var0, 0bq var1) {
      var0.password = var1;
   }

   private static 0bq FXOtaSkXBy(0bl var0) {
      return var0.password;
   }

   private static List QV29lUTrq3(0bl var0) {
      return var0.buttonList;
   }

   private static int shr0JffGID(0bl var0) {
      return var0.height;
   }

   private static Minecraft iI12eFnxfw(0bl var0) {
      return var0.mc;
   }

   protected void actionPerformed(GuiButton button) {
      switch (VlLsFzjyYn(button)) {
         case 0:
            0bk login = new 0bk(this, DdN7Q2qgg9(this).getText(), Qhb5Iedsjf(this).getText());
            login.start();
            break;
         case 1:
            iSXG6Ex3Jg(this).displayGuiScreen(1JqVwybT4Z(this));
            break;
         case 2:
            String data;
            try {
               data = (String)Toolkit.getDefaultToolkit().getSystemClipboard().getData(c391bw9KQF());
            } catch (Exception var5) {
               return;
            }

            if (data.contains(SRb81svhbG("ޔ"))) {
               String[] credentials = data.split(SRb81svhbG("ޔ"));
               3i4XXAE42N(this).setText(credentials[32228 ^ -22417 ^ 19884 ^ -26585]);
               wBfqw4rLHp(this).setText(credentials[8937 ^ -11540 ^ 9870 ^ -10614]);
            }
      }

   }

   private static FontRenderer NYAd117ego(Minecraft var0) {
      return var0.fontRenderer;
   }

   private static int qTAwabnqQV(0bl var0) {
      return var0.width;
   }

   private static GuiTextField DdN7Q2qgg9(0bl var0) {
      return var0.username;
   }

   private static Minecraft NIlACs5IAt(0bl var0) {
      return var0.mc;
   }

   private static FontRenderer jS4fD1w488(Minecraft var0) {
      return var0.fontRenderer;
   }

   private static void _etIIwFh5y/* $FF was: 6etIIwFh5y*/(0bl var0, String var1) {
      var0.status = var1;
   }

   private static 0bq gEDNS6Ilra(0bl var0) {
      return var0.password;
   }

   private static GuiTextField T07is1lFAn(0bl var0) {
      return var0.username;
   }

   private static DataFlavor c391bw9KQF() {
      return DataFlavor.stringFlavor;
   }

   private static 0bq hGPjBFSjDD(0bl var0) {
      return var0.password;
   }

   private static 0bq vIEnBTZoDH(0bl var0) {
      return var0.password;
   }

   private static void setStatus(0bl guiAddAlt, String status) {
      6etIIwFh5y(guiAddAlt, status);
   }

   private static int snNC09r7GS(0bl var0) {
      return var0.width;
   }

   // $FF: synthetic method
   static void access$000(0bl x0, String x1) {
      setStatus(x0, x1);
   }

   private static GuiTextField VnVDPM2DOM(0bl var0) {
      return var0.username;
   }

   private static 0bq LF6yvAY8rF(0bl var0) {
      return var0.password;
   }

   private static int _dth95cTOH/* $FF was: 9dth95cTOH*/(0bl var0) {
      return var0.eventButton;
   }

   private static int oFDUzgruk9(0bl var0) {
      return var0.height;
   }

   private static 0bq Qhb5Iedsjf(0bl var0) {
      return var0.password;
   }

   private static String _wywcV9JVl/* $FF was: 9wywcV9JVl*/(0bl var0) {
      return var0.status;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String SRb81svhbG(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 6346 ^ -1565 ^ 28394 ^ -28733; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 30112 ^ -51273 ^ '\uf459' ^ -20000));
      }

      return var1.toString();
   }

   public void initGui() {
      Keyboard.enableRepeatEvents((boolean)(10156 ^ -6464 ^ 18032 ^ -30947));
      SjyW81tCog(this).clear();
      2H3z73jZ8B(this).add(new 0bm(16483 ^ -19987 ^ 23024 ^ -22402, 7TgJe6t7E6(this) / (25552 ^ -20512 ^ 15997 ^ -3505) - (14129 ^ -10423 ^ 10456 ^ -14140), J9eTYS9dBa(this) / (751 ^ -11647 ^ 6732 ^ -13786) + (24528 ^ -25046 ^ 5810 ^ -10476) + (30457 ^ -10586 ^ 6815 ^ -17716), SRb81svhbG("ߢ߁߉߇߀")));
      n0DZeVsToX(this).add(new 0bm(30472 ^ -14036 ^ 22267 ^ -5922, ScTOYwblbD(this) / (7182 ^ -32660 ^ 12163 ^ -19485) - (12480 ^ -29001 ^ 6835 ^ -23392), shr0JffGID(this) / (15446 ^ -14359 ^ 19933 ^ -18842) + (343 ^ -23514 ^ 18348 ^ -7511) + (32083 ^ -22053 ^ 3586 ^ -9594), SRb81svhbG("߬ߏߍ߅")));
      W7kiquXu6d(this).add(new 0bm(6071 ^ -29649 ^ 30800 ^ -7222, 7oqaAiafg8(this) / (10512 ^ -20922 ^ 4146 ^ -26778) - (17118 ^ -18386 ^ 19351 ^ -20221), oFDUzgruk9(this) / (27477 ^ -14791 ^ 622 ^ -20730) + (15734 ^ -32196 ^ 17257 ^ -897) - (22511 ^ -4625 ^ 14913 ^ -32691), SRb81svhbG("ߧ߃ߞ߁ߜߚގ\u07fbߝߋߜޔ߾ߏߝߝ")));
      BocryN2Ghl(this, new GuiTextField(9dth95cTOH(this), NOjvWjAwYD(NIlACs5IAt(this)), qTAwabnqQV(this) / (1994 ^ -6761 ^ 13147 ^ -12028) - (21591 ^ -12944 ^ 7769 ^ -30950), 3687 ^ -7292 ^ 16477 ^ -21118, 9224 ^ -18152 ^ 6816 ^ -30856, 20559 ^ -12497 ^ 31735 ^ -7037));
      Ga1C5XFoOy(this, new 0bq(NYAd117ego(iI12eFnxfw(this)), snNC09r7GS(this) / (10161 ^ -27832 ^ 23449 ^ -4254) - (14833 ^ -1908 ^ 20366 ^ -29033), 9928 ^ -2101 ^ 11607 ^ -976, 10101 ^ -24146 ^ 30640 ^ -3677, 10755 ^ -16632 ^ 17686 ^ -12279));
   }

   private static Minecraft cqbAfqayH0(0bl var0) {
      return var0.mc;
   }
}
