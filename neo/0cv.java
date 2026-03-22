package neo;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Objects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.util.ResourceLocation;

public class 0cv extends 0cB {
   public final 0bv hideIP = new 0bv(wT7FgV3fwh("ۈ۩ۤۥڠۉې"), (boolean)(1923 ^ -1360 ^ 30086 ^ -30539), () -> {
      return ed2lY4WNNl(IqeYgqypri(this));
   });
   public final 0bv lagDetector = new 0bv(wT7FgV3fwh("یۡۧۄۥ۴ۥۣ۴ۯ۲"), (boolean)(20990 ^ -30719 ^ 12886 ^ -5208));
   public final 0bv board = new 0bv(wT7FgV3fwh("ۂۯۡ۲ۤ"), (boolean)(19753 ^ -32685 ^ 24662 ^ -21203));

   private static FontRenderer tqcw3rQwyF(Minecraft var0) {
      return var0.fontRenderer;
   }

   private static boolean _Fody49BSj/* $FF was: 2Fody49BSj*/(0bv var0) {
      return var0.value;
   }

   private static ArrayList V77bqGTSuI() {
      return 0bY.records;
   }

   private static 0bv _ghPA8azVo/* $FF was: 8ghPA8azVo*/(0cv var0) {
      return var0.lagDetector;
   }

   private static 0eg W4gjNtCyHy() {
      return 0eh.mnstb_15;
   }

   private static EntityPlayerSP G5x44dFf3k() {
      return Minecraft.player;
   }

   private static 0bv IqeYgqypri(0cv var0) {
      return var0.board;
   }

   private static FontRenderer eb5qNjYopD(Minecraft var0) {
      return var0.fontRenderer;
   }

   private static FontRenderer _ZwuVkfdW2/* $FF was: 9ZwuVkfdW2*/(Minecraft var0) {
      return var0.fontRenderer;
   }

   @0X
   public void onRender2D(0P event) {
      try {
         ScaledResolution scaledresolution = event.getResolution();
         if (4mxjbvTBa3(SHOdiT4tjr(this))) {
            Runtime runtime = Runtime.getRuntime();
            String lag = 0el.getLagFormatColor() + (0el.getLagPackets() - 700L < 0L ? 0L : 0el.getLagPackets() - 700L);
            CAgQVnClf1(Minecraft.getMinecraft()).drawString(wT7FgV3fwh("اۤا۬ێۥۯاۦا۬ۗۡ۲ۥڠ") + voSBdaqs7W(), 29365 ^ -22693 ^ 13936 ^ -7276, 25152 ^ -15733 ^ 4700 ^ -19725, -26694 ^ -1261 ^ 7831 ^ -29247);
            91AuRqeYKb(Minecraft.getMinecraft()).drawString(wT7FgV3fwh("اۦا۬ۆ۰۳ںڠاۤا۬") + Minecraft.getDebugFPS(), 19712 ^ -23101 ^ 30083 ^ -25270, 8904 ^ -8529 ^ 23054 ^ -23033, -7137 ^ -31991 ^ 16955 ^ -9518);
            dv2SXycAac(Minecraft.getMinecraft()).drawString(wT7FgV3fwh("اۦا۬ۉېںڠاۤا۬") + (VYqyQvyZl8(VGDd6vIvfz(this)) ? wT7FgV3fwh("ۈ۩ۤۤۥۮ") : (Wo5k1boM6A().isSingleplayer() ? wT7FgV3fwh("یۯۣۡ۬ۨۯ۳۴") : baxYnIPyH7((ServerData)Objects.requireNonNull(Minecraft.getMinecraft().getCurrentServerData())))), 24879 ^ -18066 ^ 19030 ^ -28131, 23687 ^ -6329 ^ 19254 ^ -3954, -32556 ^ -12321 ^ 31985 ^ -13307);
            FontRenderer var10000 = 4wDW4jQW9g(Minecraft.getMinecraft());
            StringBuilder var10001 = (new StringBuilder()).append(wT7FgV3fwh("اۦا۬۔ېۓںڠاۤا۬"));
            String var10002 = wT7FgV3fwh("ڥڮڲۦ");
            Object[] var10003 = new Object[23918 ^ -18602 ^ 31738 ^ -28221];
            var10003[21029 ^ -15031 ^ 13390 ^ -23774] = 0el.getTickRate();
            var10000.drawString(var10001.append(String.format(var10002, var10003)).toString(), 16565 ^ -1384 ^ 13760 ^ -28697, 3359 ^ -21167 ^ 12208 ^ -28804, -4098 ^ -15824 ^ 2740 ^ -10107);
            tqcw3rQwyF(Minecraft.getMinecraft()).drawString(wT7FgV3fwh("اۦا۬ۂ۲ۡۮۤںڠاۤا۬") + (tFJxQC22OT().getServerBrand() != null && wUbO8G0wYO().getServerBrand().split(wT7FgV3fwh("ڠ")).length >= (6441 ^ -9562 ^ 5299 ^ -10434) ? G5x44dFf3k().getServerBrand().split(wT7FgV3fwh("ڠ"))[17341 ^ -18560 ^ 22667 ^ -21322] : woWiiT7gyS().getServerBrand()), 9242 ^ -5280 ^ 31528 ^ -19368, 24403 ^ -30635 ^ 2166 ^ -8196, -2476 ^ -20051 ^ 2856 ^ -19666);
            9ZwuVkfdW2(Minecraft.getMinecraft()).drawString(wT7FgV3fwh("اۦا۬یۡۧڠۄۥ۴ۥۣ۴ۯ۲ںڠ") + (ldq5Oa9l2r().isSingleplayer() ? wT7FgV3fwh("اۡا۬ڰ") : lag) + wT7FgV3fwh("ۭ۳"), 13443 ^ -17026 ^ 27109 ^ -8174, 5637 ^ -25002 ^ 22918 ^ -11965, -11071 ^ -1798 ^ 22504 ^ -31700);
            7qVZ2dHq12(Minecraft.getMinecraft()).drawString(wT7FgV3fwh("اۦا۬یۯۡۤۥۤڠې۲ۯ۸۩ۥ۳ںڠاۤا۬") + 0bL.getInstance().getProxyManager().getProxyList().size(), 21927 ^ -8168 ^ 25268 ^ -10495, 28937 ^ -16906 ^ 32416 ^ -19713, -17127 ^ -15109 ^ 14994 ^ -17265);
            Ent2anCes2(Minecraft.getMinecraft()).drawString(wT7FgV3fwh("اۦا۬ۂۯ۴۳ڠۃۯۮۮۥۣ۴ۥۤںڠاۤا۬") + 0cC.getOnline().size() + wT7FgV3fwh("گ") + 0cC.getBotList().size(), 9500 ^ -9632 ^ 25810 ^ -25692, 18331 ^ -16576 ^ 14231 ^ -12314, -25800 ^ -19591 ^ 5175 ^ -15479);
            eb5qNjYopD(Minecraft.getMinecraft()).drawString(wT7FgV3fwh("اۦا۬ےہۍڠە۳ۡۧۥںڠاۤا۬") + (runtime.totalMemory() - runtime.freeMemory()) / 1048576L + wT7FgV3fwh("گ") + runtime.totalMemory() / 1048576L, 21003 ^ -1951 ^ 26686 ^ -15778, 31412 ^ -20452 ^ 16583 ^ -29989, -29782 ^ -25882 ^ 18785 ^ -22574);
            Iyx1YTYSWb(Minecraft.getMinecraft()).drawString(wT7FgV3fwh("اۦا۬ۍۡۮ۵ۡ۬ۈۥ۬۰ۥ۲ںڠاۤا۬") + 0bL.getInstance().getCaptchaManager().getHelperSize(), 10213 ^ -12840 ^ 22420 ^ -16989, 15692 ^ -30267 ^ 27961 ^ -9970, -12002 ^ -24318 ^ 10641 ^ -22926);
            1oinM3mG6T(Minecraft.getMinecraft()).drawString(wT7FgV3fwh("اۦا۬ےۥۣۯ۲ۤۥ۲ںڠاۤا۬") + V77bqGTSuI().size(), 27843 ^ -16763 ^ 6985 ^ -14075, 6947 ^ -10016 ^ 4239 ^ -11388, -27858 ^ -7143 ^ 32489 ^ -2527);
            S6BYj7SViV(Minecraft.getMinecraft()).drawString(wT7FgV3fwh("اۦا۬۔ۨ۲ۥۡۤ۳ںڠاۤا۬") + Thread.activeCount(), 30999 ^ -31012 ^ 9627 ^ -9638, 7429 ^ -26952 ^ 2277 ^ -31862, -15785 ^ -17304 ^ 24662 ^ -7786);
         }

         if (2Fody49BSj(8ghPA8azVo(this)) && !giBU1Il0G9().isSingleplayer() && 0el.getLagPackets() > 1500L) {
            0ew.drawImage(new ResourceLocation(wT7FgV3fwh("ۮۥۯ۷ۡ۲ۥگ۩ۭۡۧۥ۳گ۬ۯ۳۴ۣ۟ۯۮۮۥۣ۴۩ۯۮڮ۰ۮۧ")), (float)(scaledresolution.getScaledWidth() / (29384 ^ -28307 ^ 6136 ^ -2977) - (17464 ^ -27075 ^ 30540 ^ -23287)), (float)(scaledresolution.getScaledHeight() / (6357 ^ -7678 ^ 13581 ^ -12328) - (29646 ^ -23061 ^ 6766 ^ -13181)), Float.intBitsToFloat(11122 ^ 'ꣃ' ^ 9431 ^ -973920485 ^ 23899 ^ '퓏' ^ 5889 ^ -2030887192), Float.intBitsToFloat(99583 ^ 121967 ^ 19558 ^ 1218628416 ^ 106191 ^ 111550 ^ 31463 ^ 195201056), this.getColor());
            W4gjNtCyHy().drawCenteredString(wT7FgV3fwh("ا۬ڠڠʟʾ˂ʵˀˏʽʾڠˁʾʵʴʸʽʵʽʸʵڠˁڠˁʵˀʲʵˀʾʼ"), (float)(scaledresolution.getScaledWidth() / (24800 ^ -23007 ^ 7798 ^ -10059)), (float)(scaledresolution.getScaledHeight() / (12013 ^ -21176 ^ 20004 ^ -12925) - (18502 ^ -21566 ^ 5874 ^ -2761)), this.getColor().getRGB());
         }
      } catch (Exception var5) {
      }

   }

   private static EntityPlayerSP woWiiT7gyS() {
      return Minecraft.player;
   }

   private static Minecraft ldq5Oa9l2r() {
      return mc;
   }

   private static FontRenderer CAgQVnClf1(Minecraft var0) {
      return var0.fontRenderer;
   }

   public _cv/* $FF was: 0cv*/() {
      super(wT7FgV3fwh("ۈەۄ"), 0bV.Render);
      0bC[] var10001 = new 0bC[24360 ^ -10749 ^ 6371 ^ -28213];
      var10001[18083 ^ -12327 ^ 7727 ^ -26795] = this.board;
      var10001[32009 ^ -25564 ^ 27553 ^ -30067] = this.hideIP;
      var10001[3830 ^ -11283 ^ 28295 ^ -19554] = this.lagDetector;
      this.addSetting(var10001);
   }

   private static FontRenderer dv2SXycAac(Minecraft var0) {
      return var0.fontRenderer;
   }

   private static FontRenderer Ent2anCes2(Minecraft var0) {
      return var0.fontRenderer;
   }

   private static String baxYnIPyH7(ServerData var0) {
      return var0.serverIP;
   }

   private static EntityPlayerSP tFJxQC22OT() {
      return Minecraft.player;
   }

   private static boolean ed2lY4WNNl(0bv var0) {
      return var0.value;
   }

   private static boolean VYqyQvyZl8(0bv var0) {
      return var0.value;
   }

   private Color getColor() {
      return 0em.TwoColorEffect(new Color(17231 ^ -4114 ^ 32166 ^ -11784, 29702 ^ -6965 ^ 4859 ^ -32138, 4918 ^ -21539 ^ 14335 ^ -28844), new Color(6636 ^ -16471 ^ 26393 ^ -15965, 21207 ^ -21893 ^ 1967 ^ -141, 23197 ^ -22902 ^ 11885 ^ -11766, 1896 ^ -7419 ^ 13655 ^ -11835), Double.longBitsToDouble(-5294330143406913214L ^ -679266425258997438L), 12728 ^ -26440 ^ 13563 ^ -25093);
   }

   private static Minecraft Wo5k1boM6A() {
      return mc;
   }

   private static 0bv VGDd6vIvfz(0cv var0) {
      return var0.hideIP;
   }

   private static FontRenderer _oinM3mG6T/* $FF was: 1oinM3mG6T*/(Minecraft var0) {
      return var0.fontRenderer;
   }

   private static boolean _mxjbvTBa3/* $FF was: 4mxjbvTBa3*/(0bv var0) {
      return var0.value;
   }

   private static FontRenderer _qVZ2dHq12/* $FF was: 7qVZ2dHq12*/(Minecraft var0) {
      return var0.fontRenderer;
   }

   private static FontRenderer _1AuRqeYKb/* $FF was: 91AuRqeYKb*/(Minecraft var0) {
      return var0.fontRenderer;
   }

   private static 0bv SHOdiT4tjr(0cv var0) {
      return var0.board;
   }

   private static FontRenderer Iyx1YTYSWb(Minecraft var0) {
      return var0.fontRenderer;
   }

   private static FontRenderer S6BYj7SViV(Minecraft var0) {
      return var0.fontRenderer;
   }

   private static Minecraft giBU1Il0G9() {
      return mc;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String wT7FgV3fwh(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 16750 ^ -15340 ^ 5258 ^ -28176; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 29769 ^ -27865 ^ 15916 ^ -8254));
      }

      return var1.toString();
   }

   private static String voSBdaqs7W() {
      return 0bK.VERSION_TYPE;
   }

   private static EntityPlayerSP wUbO8G0wYO() {
      return Minecraft.player;
   }

   private static FontRenderer _wDW4jQW9g/* $FF was: 4wDW4jQW9g*/(Minecraft var0) {
      return var0.fontRenderer;
   }
}
