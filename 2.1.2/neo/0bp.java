package neo;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.text.TextFormatting;

public class 0bp extends GuiScreen {
   public String status;
   public final 0bo manager;
   public GuiTextField nameField;
   public 0bq pwField;

   private static String LIJBPYCmie(0bp var0) {
      return var0.status;
   }

   private static GuiTextField NqyJubhAFk(0bp var0) {
      return var0.nameField;
   }

   private static Minecraft wwFvlkX177(0bp var0) {
      return var0.mc;
   }

   private static Minecraft KAZAr1Ilqa(0bp var0) {
      return var0.mc;
   }

   public _bp/* $FF was: 0bp*/(0bo manager) {
      this.status = TextFormatting.GRAY + vDgyWS6642("ҍһҳҮҳҴҽӴӴӴ");
      this.manager = manager;
   }

   private static GuiTextField JOTepjMUig(0bp var0) {
      return var0.nameField;
   }

   private static List IrPTNLiY1V(0bp var0) {
      return var0.buttonList;
   }

   private static int z4KmVaGPkr(GuiButton var0) {
      return var0.id;
   }

   private static Minecraft IWT7J1BVh3(0bp var0) {
      return var0.mc;
   }

   private static Minecraft ZzaJsu6B2g(0bp var0) {
      return var0.mc;
   }

   private static GuiTextField hDWBeD1fbS(0bp var0) {
      return var0.nameField;
   }

   private static void _zsBjBKy4k/* $FF was: 1zsBjBKy4k*/(0bp var0, 0bq var1) {
      var0.pwField = var1;
   }

   private static int uw24JR1vul(0bp var0) {
      return var0.height;
   }

   private static void sorSQ4Hbis(0bp var0, String var1) {
      var0.status = var1;
   }

   private static 0bq D47bS2l1Zj(0bp var0) {
      return var0.pwField;
   }

   private static int oN6Yg3e4qD(0bp var0) {
      return var0.width;
   }

   private static 0bo Y4Vk0I9yBD(0bp var0) {
      return var0.manager;
   }

   protected void mouseClicked(int par1, int par2, int par3) {
      try {
         super.mouseClicked(par1, par2, par3);
      } catch (IOException var5) {
         IOException e = var5;
         e.printStackTrace();
      }

      JOTepjMUig(this).mouseClicked(par1, par2, par3);
      41Fn2dUejt(this).mouseClicked(par1, par2, par3);
   }

   private static int IFg7Xob1R8(0bp var0) {
      return var0.width;
   }

   private static Minecraft kB3bCDbqy7(0bp var0) {
      return var0.mc;
   }

   private static GuiTextField bGgW9SZaLg(0bp var0) {
      return var0.nameField;
   }

   private static int yyncajuGF4(0bp var0) {
      return var0.width;
   }

   public void actionPerformed(GuiButton button) {
      switch (z4KmVaGPkr(button)) {
         case 0:
            FtAeFTEWiB(Y4Vk0I9yBD(this)).setMask(NqyJubhAFk(this).getText());
            iteGxKJ5td(DsY3IJqzru(this)).setPassword(aM2eRd8ALG(this).getText());
            sorSQ4Hbis(this, vDgyWS6642("ҟҾҳҮҿҾӻ"));
            break;
         case 1:
            XdChFXqJT6(this).displayGuiScreen(XlnxyIVWJj(this));
      }

   }

   private static Minecraft AzN7LtYL4J(0bp var0) {
      return var0.mc;
   }

   private static int kJiKAiGeGv(0bp var0) {
      return var0.width;
   }

   private static 0bo DsY3IJqzru(0bp var0) {
      return var0.manager;
   }

   private static Minecraft jTN6LZ1Omd(0bp var0) {
      return var0.mc;
   }

   private static FontRenderer LrFPrZSKP2(Minecraft var0) {
      return var0.fontRenderer;
   }

   private static int _mBXxtwaoe/* $FF was: 8mBXxtwaoe*/(0bp var0) {
      return var0.width;
   }

   private static GuiTextField sUCWp45nrw(0bp var0) {
      return var0.nameField;
   }

   private static 0bq nyD5LBopD5(0bp var0) {
      return var0.pwField;
   }

   private static Minecraft XdChFXqJT6(0bp var0) {
      return var0.mc;
   }

   private static GuiTextField DpYfabBBRA(0bp var0) {
      return var0.nameField;
   }

   private static 0bb iteGxKJ5td(0bo var0) {
      return var0.selectedAlt;
   }

   private static int _vc72RvPT3/* $FF was: 1vc72RvPT3*/(0bp var0) {
      return var0.width;
   }

   private static int dlIeY1wIJM(0bp var0) {
      return var0.height;
   }

   private static 0bo XlnxyIVWJj(0bp var0) {
      return var0.manager;
   }

   private static GuiTextField zuTJ5oAoDv(0bp var0) {
      return var0.nameField;
   }

   private static List v96LOqQl26(0bp var0) {
      return var0.buttonList;
   }

   private static 0bq y8yFqpt5My(0bp var0) {
      return var0.pwField;
   }

   private static GuiTextField yvyVttXl0G(0bp var0) {
      return var0.nameField;
   }

   private static int FQ3II2fWrY(0bp var0) {
      return var0.width;
   }

   public void drawScreen(int par1, int par2, float par3) {
      new ScaledResolution(ZzaJsu6B2g(this));
      0ew.drawRect(Float.intBitsToFloat(2209 ^ 493703 ^ 501071 ^ -1218735941 ^ 498959 ^ 26698 ^ 510506 ^ -1218737475), Float.intBitsToFloat(1252 ^ 246610 ^ 239006 ^ -1969385261 ^ 2435 ^ 260584 ^ 234106 ^ -1969385238), (float)8mBXxtwaoe(this), (float)Rn4Htl3zTB(this), new Color(14594 ^ -23834 ^ 2250 ^ -27841, 16640 ^ -5732 ^ 7150 ^ -19613, 3806 ^ -19635 ^ 4627 ^ -20591, 11721 ^ -5205 ^ 32160 ^ -17603));
      9wb32tM4Z0(AzN7LtYL4J(this)).drawStringWithShadow(vDgyWS6642("ҟҾҳҮӺқҶҮ"), (float)tFjlyScq6H(this) / Float.intBitsToFloat('鮋' ^ 113807 ^ '펪' ^ 1259522554 ^ 16646 ^ 32120 ^ 13901 ^ 185808743), Float.intBitsToFloat(246224 ^ 220852 ^ 12504 ^ -1796642554 ^ 9967 ^ 222609 ^ 260380 ^ -708215592), -3137 ^ -30161 ^ 26697 ^ -4570);
      YoFagawkOD(jTN6LZ1Omd(this)).drawStringWithShadow(LIJBPYCmie(this), (float)1vc72RvPT3(this) / Float.intBitsToFloat('\ue979' ^ 27911 ^ '쳍' ^ -1258481324 ^ 11033 ^ 90293 ^ '\uf082' ^ -184751415), Float.intBitsToFloat('\uea60' ^ '\ue47c' ^ 30944 ^ 844967471 ^ '\ue4f9' ^ 7354 ^ '\ue2d1' ^ 1945977409), -13286 ^ -24931 ^ 1564 ^ -21660);
      DpYfabBBRA(this).drawTextBox();
      NWSdVAASA9(this).drawTextBox();
      if (hDWBeD1fbS(this).getText().isEmpty() && !zuTJ5oAoDv(this).isFocused()) {
         this.drawString(tPxdMts2Op(KAZAr1Ilqa(this)), vDgyWS6642("Ҕһҷҿ"), CdDU3gDMvb(this) / (19890 ^ -21386 ^ 27144 ^ -29746) - (10879 ^ -19468 ^ 19141 ^ -11474), 31289 ^ -6360 ^ 967 ^ -24940, 23992 ^ '퍓' ^ 5769 ^ -7859990);
      }

      if (D47bS2l1Zj(this).getText().isEmpty() && !bewJ3ioQvD(this).isFocused()) {
         this.drawString(GTV9ta6Npl(IWT7J1BVh3(this)), vDgyWS6642("ҊһҩҩҭҵҨҾ"), IFg7Xob1R8(this) / (19352 ^ -15985 ^ 18914 ^ -15369) - (16230 ^ -28555 ^ 4416 ^ -16845), 12727 ^ -971 ^ 25648 ^ -22056, 7141 ^ 73975 ^ 102928 ^ -7855734);
      }

      super.drawScreen(par1, par2, par3);
   }

   private static int Rn4Htl3zTB(0bp var0) {
      return var0.height;
   }

   private static int tFjlyScq6H(0bp var0) {
      return var0.width;
   }

   private static List AROLRyx9YK(0bp var0) {
      return var0.buttonList;
   }

   private static FontRenderer tPxdMts2Op(Minecraft var0) {
      return var0.fontRenderer;
   }

   private static 0bq NTvkxjyYhU(0bp var0) {
      return var0.pwField;
   }

   private static FontRenderer YoFagawkOD(Minecraft var0) {
      return var0.fontRenderer;
   }

   private static int CdDU3gDMvb(0bp var0) {
      return var0.width;
   }

   private static 0bq _2nBeRFVfw/* $FF was: 12nBeRFVfw*/(0bp var0) {
      return var0.pwField;
   }

   public void initGui() {
      IrPTNLiY1V(this).add(new 0bm(29266 ^ -1153 ^ 13918 ^ -16525, FQ3II2fWrY(this) / (9354 ^ -27857 ^ 28042 ^ -9683) - (7148 ^ -31472 ^ 2114 ^ -26918), dlIeY1wIJM(this) / (2791 ^ -20146 ^ 29048 ^ -13611) + (27356 ^ -10851 ^ 15905 ^ -32452) + (6197 ^ -24918 ^ 17072 ^ -15325), vDgyWS6642("ҟҾҳҮ")));
      v96LOqQl26(this).add(new 0bm(21947 ^ -4999 ^ 28057 ^ -11174, kJiKAiGeGv(this) / (12123 ^ -22528 ^ 10820 ^ -21219) - (676 ^ -27951 ^ 702 ^ -27985), uw24JR1vul(this) / (27580 ^ -12552 ^ 25877 ^ -16299) + (23525 ^ -27865 ^ 16226 ^ -2092) + (11423 ^ -3020 ^ 18635 ^ -28564), vDgyWS6642("ҙһҴҹҿҶ")));
      HbtYUYjeya(this, new GuiTextField(62eNt361RQ(this), NO9vFzHS2A(wwFvlkX177(this)), oN6Yg3e4qD(this) / (16631 ^ -18418 ^ 25761 ^ -25510) - (19651 ^ 31532 ^ 14240 ^ 43), 5876 ^ -8695 ^ 27423 ^ -23586, 7286 ^ -16132 ^ 25195 ^ -16855, 16888 ^ -14105 ^ 3185 ^ -31366));
      1zsBjBKy4k(this, new 0bq(LrFPrZSKP2(kB3bCDbqy7(this)), yyncajuGF4(this) / (823 ^ -11769 ^ 14294 ^ -6428) - (568 ^ -31942 ^ 10819 ^ -21723), 28313 ^ -25527 ^ 27764 ^ -24896, 17440 ^ -12072 ^ 20195 ^ -9517, 3634 ^ -17007 ^ 6815 ^ -22232));
   }

   private static 0bq aM2eRd8ALG(0bp var0) {
      return var0.pwField;
   }

   private static 0bq _1Fn2dUejt/* $FF was: 41Fn2dUejt*/(0bp var0) {
      return var0.pwField;
   }

   private static FontRenderer _wb32tM4Z0/* $FF was: 9wb32tM4Z0*/(Minecraft var0) {
      return var0.fontRenderer;
   }

   private static 0bq NWSdVAASA9(0bp var0) {
      return var0.pwField;
   }

   private static FontRenderer GTV9ta6Npl(Minecraft var0) {
      return var0.fontRenderer;
   }

   private static 0bb FtAeFTEWiB(0bo var0) {
      return var0.selectedAlt;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String vDgyWS6642(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 31825 ^ -4134 ^ 8650 ^ -19903; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 4121 ^ -17207 ^ 3134 ^ -23500));
      }

      return var1.toString();
   }

   protected void keyTyped(char par1, int par2) {
      yvyVttXl0G(this).textboxKeyTyped(par1, par2);
      12nBeRFVfw(this).textboxKeyTyped(par1, par2);
      if (par1 == (29747 ^ -21734 ^ 6450 ^ -14830) && (3tXYNFerlw(this).isFocused() || NTvkxjyYhU(this).isFocused())) {
         sUCWp45nrw(this).setFocused((boolean)(!bGgW9SZaLg(this).isFocused() ? 24429 ^ -7771 ^ 21670 ^ -5521 : 20940 ^ -1472 ^ 8489 ^ -30043));
         y8yFqpt5My(this).setFocused((boolean)(!nyD5LBopD5(this).isFocused() ? 30515 ^ -31268 ^ 22307 ^ -23091 : 6953 ^ -20189 ^ 16975 ^ -6075));
      }

      if (par1 == (14634 ^ -7674 ^ 15401 ^ -6392)) {
         this.actionPerformed((GuiButton)AROLRyx9YK(this).get(15320 ^ -16537 ^ 11274 ^ -22347));
      }

   }

   private static FontRenderer NO9vFzHS2A(Minecraft var0) {
      return var0.fontRenderer;
   }

   private static void HbtYUYjeya(0bp var0, GuiTextField var1) {
      var0.nameField = var1;
   }

   private static 0bq bewJ3ioQvD(0bp var0) {
      return var0.pwField;
   }

   private static GuiTextField _tXYNFerlw/* $FF was: 3tXYNFerlw*/(0bp var0) {
      return var0.nameField;
   }

   private static int _2eNt361RQ/* $FF was: 62eNt361RQ*/(0bp var0) {
      return var0.eventButton;
   }
}
