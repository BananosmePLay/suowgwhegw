package neo;

import java.awt.Color;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiWorldSelection;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.settings.GameSettings;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class 0bE extends GuiScreen {
   public static final long time = System.currentTimeMillis();
   public static 0ev backgroundShader = new 0ev(i2PYGcrO6q("֩ק\u05f5\u05f5ףײ\u05f5֩\u05ebׯרףץ״קנײ֩רףשױק״ף֩\u05f5\u05eeקעף״\u05f5֩פקץ\u05edס״ש׳רע֨נ\u05f5\u05ee"));
   public ArrayList<0bD> buttons;

   private static 0eg vVaI1CCbmI() {
      return 0eh.mnstb_16;
   }

   private static Minecraft _HgnNtPwlb/* $FF was: 5HgnNtPwlb*/(0bE var0) {
      return var0.mc;
   }

   public _bE/* $FF was: 0bE*/() {
   }

   private void openWebLink(URI url) {
      try {
         Class<?> oclass = Class.forName(i2PYGcrO6q("\u05ecקװק֨קױײׂ֨ף\u05f5\u05edײש\u05f6"));
         Object object = oclass.getMethod(i2PYGcrO6q("סףײׂף\u05f5\u05edײש\u05f6")).invoke((Object)null);
         String var10001 = i2PYGcrO6q("פ״שױ\u05f5ף");
         Class[] var10002 = new Class[18836 ^ -15445 ^ 15022 ^ -20336];
         var10002[7125 ^ -32319 ^ 8850 ^ -18298] = URI.class;
         Method var10000 = oclass.getMethod(var10001, var10002);
         Object[] var6 = new Object[32607 ^ -25825 ^ 21457 ^ -18544];
         var6[9831 ^ -23750 ^ 24518 ^ -9573] = url;
         var10000.invoke(object, var6);
      } catch (Throwable var4) {
         Throwable throwable1 = var4;
         throwable1.printStackTrace();
      }

   }

   private static Minecraft ViG9frO6E9(0bE var0) {
      return var0.mc;
   }

   private static Minecraft nf4AV7JqAQ(0bE var0) {
      return var0.mc;
   }

   private static String ysa9iVw4Nh() {
      return 0bK.VERSION_TYPE;
   }

   private static 0ev jvKGVMNgW5() {
      return backgroundShader;
   }

   private static ArrayList _jbbd0hLHQ/* $FF was: 9jbbd0hLHQ*/(0bE var0) {
      return var0.buttons;
   }

   private static ArrayList vBd5ij9y5a(0bE var0) {
      return var0.buttons;
   }

   private static void _yJAPXXzWG/* $FF was: 1yJAPXXzWG*/(0bE var0, ArrayList var1) {
      var0.buttons = var1;
   }

   private static ArrayList UQyJgObbo6(0bE var0) {
      return var0.buttons;
   }

   private static ArrayList _etIeAuBz2/* $FF was: 6etIeAuBz2*/(0bE var0) {
      return var0.buttons;
   }

   private static ArrayList Kq2VBMh9HF(0bE var0) {
      return var0.buttons;
   }

   private static ArrayList _hUj4FbOvi/* $FF was: 6hUj4FbOvi*/(0bE var0) {
      return var0.buttons;
   }

   public void initGui() {
      ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
      1yJAPXXzWG(this, new ArrayList());
      AGAbS4y7pF(this).add(new 0bD(12974 ^ -6201 ^ 21436 ^ -31020, sr.getScaledWidth() / (14424 ^ -1609 ^ 3451 ^ -13162), sr.getScaledHeight() / (13817 ^ -6576 ^ 2653 ^ -9738) - (32293 ^ -31601 ^ 24760 ^ -26049), 7260 ^ -20689 ^ 10592 ^ -25993, 31173 ^ -17783 ^ 31251 ^ -18097, i2PYGcrO6q("ƘƲƾƻƸǁƻƶǉ֦ƾƵǆƶ")));
      NuIvF9NzAe(this).add(new 0bD(30942 ^ -8052 ^ 17465 ^ -9111, sr.getScaledWidth() / (13898 ^ -29359 ^ 25620 ^ -8435), sr.getScaledHeight() / (4961 ^ -25648 ^ 16011 ^ -18888) - (29207 ^ -27847 ^ 16631 ^ -24076) + (13161 ^ -1664 ^ 27915 ^ -22538), 10194 ^ -32683 ^ 3260 ^ -21665, 17826 ^ -10889 ^ 26464 ^ -2139, i2PYGcrO6q("ƧƳǄƳƴƶǉ֦ƾƵǆƶ")));
      6hUj4FbOvi(this).add(new 0bD(2596 ^ -17743 ^ 27390 ^ -9624, sr.getScaledWidth() / (26336 ^ -31543 ^ 16120 ^ -9005), sr.getScaledHeight() / (7610 ^ -5798 ^ 12534 ^ -15340) - (26716 ^ -22527 ^ 20593 ^ -28671) + (11221 ^ -12549 ^ 7678 ^ -1800), 27077 ^ -17289 ^ 21346 ^ -31052, 5195 ^ -28427 ^ 23650 ^ -10036, i2PYGcrO6q("ƖƼƼƶǅƻǄǍ")));
      vBd5ij9y5a(this).add(new 0bD(12920 ^ -3340 ^ 11559 ^ -4689, sr.getScaledWidth() / (9472 ^ -31743 ^ 20086 ^ -4235), sr.getScaledHeight() / (9076 ^ -31280 ^ 11148 ^ -29398) - (29448 ^ -32203 ^ 2995 ^ -1373) + (13532 ^ -26343 ^ 17356 ^ -4555), 12864 ^ -3908 ^ 8179 ^ -8853, 18755 ^ -8678 ^ 32446 ^ -5641, i2PYGcrO6q("ƛƶǇǄǆƸƿƼƾ")));
      9jbbd0hLHQ(this).add(new 0bD(7756 ^ -27787 ^ 18095 ^ -13421, sr.getScaledWidth() / (25648 ^ -1983 ^ 31177 ^ -6726), sr.getScaledHeight() / (25920 ^ -3136 ^ 12115 ^ -17967) - (5850 ^ -11668 ^ 24315 ^ -26016) + (19610 ^ -20474 ^ 27862 ^ -28646), 18756 ^ -9583 ^ 19030 ^ -9753, 17625 ^ -31487 ^ 8710 ^ -7218, i2PYGcrO6q("ƚƸƻƾǄƸǆƾƻƵ")));
      6etIeAuBz2(this).add(new 0bD(12717 ^ -10039 ^ 8672 ^ -14206, sr.getScaledWidth() / (25728 ^ -4719 ^ 9829 ^ -20618), sr.getScaledHeight() / (14396 ^ -7499 ^ 22921 ^ -31998) - (31509 ^ -7405 ^ 26008 ^ -589) + (24449 ^ -14239 ^ 25599 ^ -2957), 20308 ^ -27277 ^ 20955 ^ -29800, 26403 ^ -12022 ^ 31783 ^ -13794, i2PYGcrO6q("ׂׯ\u05f5ץש״ע")));
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String i2PYGcrO6q(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 27048 ^ -21474 ^ 7557 ^ -10189; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 14367 ^ -15621 ^ 9704 ^ -9590));
      }

      return var1.toString();
   }

   private static long v6Rx1P4rvv() {
      return time;
   }

   private static ArrayList NuIvF9NzAe(0bE var0) {
      return var0.buttons;
   }

   private static Minecraft AuBoEGGjll(0bE var0) {
      return var0.mc;
   }

   private static ArrayList AGAbS4y7pF(0bE var0) {
      return var0.buttons;
   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
      jvKGVMNgW5().useShader(sr.getScaledWidth(), sr.getScaledHeight(), (float)mouseX, (float)mouseY, (float)(System.currentTimeMillis() - v6Rx1P4rvv()) / Float.intBitsToFloat(521143 ^ 489274 ^ 14364 ^ 1793292737 ^ '\ue9db' ^ 1031674 ^ '\ue3ab' ^ 777515738));
      GL11.glBegin(24675 ^ -14335 ^ 16015 ^ -26902);
      GL11.glVertex2f(Float.intBitsToFloat(9755 ^ 496233 ^ 508911 ^ -985418896 ^ 523009 ^ 495345 ^ 13786 ^ 2059633863), Float.intBitsToFloat(127436 ^ 20761 ^ 121952 ^ 1212852067 ^ 104838 ^ 103680 ^ 29463 ^ -137714617));
      GL11.glVertex2f(Float.intBitsToFloat(109624 ^ '髬' ^ 112274 ^ 1796628577 ^ 129301 ^ 18815 ^ 122941 ^ -728321936), Float.intBitsToFloat(8733 ^ 99610 ^ 7582 ^ 155364203 ^ 20747 ^ 129707 ^ 1188 ^ 918732534));
      GL11.glVertex2f(Float.intBitsToFloat('限' ^ 99372 ^ 'ﮕ' ^ 991755814 ^ '頕' ^ 101531 ^ '빺' ^ 77380923), Float.intBitsToFloat('铁' ^ 2527 ^ '鰲' ^ 1008742520 ^ 28089 ^ 18320 ^ 13177 ^ 60827652));
      GL11.glVertex2f(Float.intBitsToFloat(7494 ^ 515662 ^ 7172 ^ 470547203 ^ 19450 ^ 9259 ^ 16445 ^ 596380643), Float.intBitsToFloat('鸟' ^ 122401 ^ '쌻' ^ 511752839 ^ 29985 ^ 82795 ^ '\uf8dd' ^ -1593772267));
      GL11.glEnd();
      GL20.glUseProgram(29961 ^ -16978 ^ 29154 ^ -18107);
      GlStateManager.disableCull();
      GlStateManager.pushMatrix();
      0ex.drawGradientRound((float)(sr.getScaledWidth() / (7089 ^ -9743 ^ 1500 ^ -14434) - (32066 ^ -1731 ^ 494 ^ -31280)), (float)(sr.getScaledHeight() / (13631 ^ -25030 ^ 28127 ^ -14632) - (22946 ^ -7982 ^ 1358 ^ -17298)), Float.intBitsToFloat('ﰤ' ^ '跠' ^ 8359 ^ 1034826170 ^ '胝' ^ 480308 ^ 20079 ^ 2125199967), Float.intBitsToFloat(110200 ^ 86414 ^ 1328 ^ 1407340093 ^ 110748 ^ 97292 ^ 10905 ^ 281170674), Float.intBitsToFloat(18487 ^ '됏' ^ 9678 ^ 477393075 ^ 19396 ^ '\ue123' ^ 21719 ^ 1567905653), 0br.getC(20093 ^ -20871 ^ 12420 ^ -12160), 0br.getC(28467 ^ -6420 ^ 11865 ^ -22660), 0br.getC(10503 ^ -27119 ^ 27024 ^ -11160), 0br.getC(10211 ^ -14976 ^ 4984 ^ -3341));
      0ex.drawRound((float)(sr.getScaledWidth() / (9910 ^ -1035 ^ 31861 ^ -24268) - (29884 ^ -19660 ^ 23098 ^ -25101) + (8280 ^ -24725 ^ 23999 ^ -7538)), (float)(sr.getScaledHeight() / (2828 ^ -24445 ^ 13170 ^ -26369) - (23714 ^ -3352 ^ 11000 ^ -31518) + (16121 ^ -13348 ^ 4012 ^ -1397)), Float.intBitsToFloat(8208 ^ 230148 ^ 3435 ^ -1728715546 ^ '\ue3d4' ^ 229034 ^ 8686 ^ -636881399), Float.intBitsToFloat(125121 ^ 99823 ^ 3121 ^ -1586981602 ^ 104206 ^ 118436 ^ 24555 ^ -495652288), Float.intBitsToFloat(3622 ^ '阤' ^ '\ued24' ^ 665786314 ^ '냳' ^ '麇' ^ 3944 ^ 1722762224), new Color(28480 ^ -11737 ^ 15364 ^ -32398, 22226 ^ -17810 ^ 31723 ^ -26810, 26611 ^ -14813 ^ 17155 ^ -7486, 7652 ^ -17243 ^ 10092 ^ -31011));
      vVaI1CCbmI().drawCenteredGradientThemeString(i2PYGcrO6q("\u05c8ףשבק״ף֦ׅתׯףרײ"), (float)(sr.getScaledWidth() / (2790 ^ -17743 ^ 29158 ^ -15949)), (float)(sr.getScaledHeight() / (19507 ^ -8731 ^ 25242 ^ -3250) - (7650 ^ -24441 ^ 7025 ^ -22958)));
      44eo74p8nO().drawString(i2PYGcrO6q("װ") + ysa9iVw4Nh(), (float)(sr.getScaledWidth() / (14841 ^ -3510 ^ 32342 ^ -18969) + (28813 ^ -19683 ^ 1209 ^ -14583)), (float)(sr.getScaledHeight() / (8793 ^ -31873 ^ 7715 ^ -16633) - (22732 ^ -7838 ^ 25714 ^ -8811)), (new Color(15152 ^ -22787 ^ 21002 ^ -12429, 21193 ^ -1702 ^ 19665 ^ -6154, 7126 ^ -28817 ^ 24996 ^ -2647)).getRGB());
      UQyJgObbo6(this).forEach((mainButton) -> {
         mainButton.render(mouseX, mouseY);
      });
      GlStateManager.popMatrix();
      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
      Iterator var4 = Kq2VBMh9HF(this).iterator();

      while(var4.hasNext()) {
         0bD button = (0bD)var4.next();
         if (button.isHovered(mouseX, mouseY)) {
            switch (button.getId()) {
               case 1:
                  ViG9frO6E9(this).displayGuiScreen(new GuiWorldSelection(this));
                  break;
               case 2:
                  AuBoEGGjll(this).displayGuiScreen(new GuiMultiplayer(this));
                  break;
               case 3:
                  5HgnNtPwlb(this).displayGuiScreen(new 0bo());
                  break;
               case 4:
                  nf4AV7JqAQ(this).displayGuiScreen(new GuiOptions(this, vjt53oOpOJ()));
               case 5:
               default:
                  break;
               case 6:
                  this.openWebLink(URI.create(i2PYGcrO6q("\u05eeײײ\u05f6\u05f5ּ֩֩עׯ\u05f5ץש״ע֨ססְֲ֩ר\u05ceד\u05ebס\u05ebׂׅ")));
            }
         }
      }

   }

   private static GameSettings vjt53oOpOJ() {
      return Minecraft.gameSettings;
   }

   private static 0eg _4eo74p8nO/* $FF was: 44eo74p8nO*/() {
      return 0eh.mnstb_12;
   }
}
