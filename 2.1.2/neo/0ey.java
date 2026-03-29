package neo;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class 0ey {
   private final String roundedRectGradient;
   public final int programID;
   private final String roundedOutlinedRect;
   public static Framebuffer framebuffer = new Framebuffer(6175 ^ -15946 ^ 10941 ^ -3307, 13660 ^ -20935 ^ 23562 ^ -14482, (boolean)(15846 ^ -14978 ^ 4725 ^ -5395));
   public static 0ey shader = new 0ey(aUd8csOi7r("ĀċāęďĜċŁĝĆďĊċĜĝŁęĆćĚċŀĈĜďĉ"));
   private String roundedRect;

   private int createShader(InputStream inputStream, int shaderType) {
      int shader = GL20.glCreateShader(shaderType);
      GL20.glShaderSource(shader, readInputStream(inputStream));
      GL20.glCompileShader(shader);
      if (GL20.glGetShaderi(shader, 30668 ^ '줣' ^ 6274 ^ 11756) == 0) {
         JxucitiwVx().println(GL20.glGetShaderInfoLog(shader, '閿' ^ -17098 ^ '\uab18' ^ -27759));
         String var10002 = aUd8csOi7r("ĽĆďĊċĜŎņŋĝŇŎĈďćĂċĊŎĚāŎčāăĞćĂċŏ");
         Object[] var10003 = new Object[31413 ^ -20442 ^ 19649 ^ -31149];
         var10003[9122 ^ -10222 ^ 13403 ^ -12309] = shaderType;
         throw new IllegalStateException(String.format(var10002, var10003));
      } else {
         return shader;
      }
   }

   private static int Wqj9KnTklL(0ey var0) {
      return var0.programID;
   }

   private static 0ey _ofS2i0UCG/* $FF was: 4ofS2i0UCG*/() {
      return shader;
   }

   private static Minecraft gm9gIdrmTT() {
      return 0eB.mc;
   }

   public int getUniform(String name) {
      return GL20.glGetUniformLocation(ol5YFOv15z(this), name);
   }

   private static void OJgMgMW2sr(Framebuffer var0) {
      framebuffer = var0;
   }

   private static Framebuffer rgDi8WW4VU() {
      return framebuffer;
   }

   private static Framebuffer TNjY6lLbtO() {
      return framebuffer;
   }

   private static Minecraft dZqSkNewiy() {
      return 0eB.mc;
   }

   public static void renderColor(float force) {
      GlStateManager.enableBlend();
      GlStateManager.color(Float.intBitsToFloat(12071 ^ 109112 ^ 21934 ^ 779800997 ^ 14516 ^ 126575 ^ 14228 ^ 301654107), Float.intBitsToFloat(7105 ^ 214472 ^ 260369 ^ -950440551 ^ 261226 ^ 213795 ^ 4559 ^ -119970809), Float.intBitsToFloat(28260 ^ '靭' ^ 14256 ^ -698857524 ^ 30222 ^ '覭' ^ 5383 ^ -371694639), Float.intBitsToFloat(26827 ^ 9936 ^ 32624 ^ -2078959185 ^ 26470 ^ 234837 ^ 247536 ^ -1147823097));
      OpenGlHelper.glBlendFunc(11721 ^ -12269 ^ 1371 ^ -1149, 19058 ^ -2537 ^ 21700 ^ -5214, 28758 ^ -17232 ^ 10392 ^ -7041, 24254 ^ -22000 ^ 17087 ^ -18927);
      OJgMgMW2sr(0ew.createFrameBuffer(aH3j2tyK6T()));
      WgQQ76l9hc().framebufferClear();
      TNjY6lLbtO().bindFramebuffer((boolean)(17437 ^ -21257 ^ 27533 ^ -31898));
      yVO6rTDH1O().init();
      setupUniforms2(force);
      0ew.bindTexture(tsvo26SahA(dZqSkNewiy().getFramebuffer()));
      drawQuads();
      jOlQwXD6j9().unbindFramebuffer();
      4ofS2i0UCG().unload();
      gm9gIdrmTT().getFramebuffer().bindFramebuffer((boolean)(10528 ^ -4247 ^ 2292 ^ -12612));
      9OOjReIFRl().init();
      setupUniforms2(force);
      0ew.bindTexture(x6S15oIdVM(rgDi8WW4VU()));
      drawQuads();
      19gm972nwT().unload();
      GlStateManager.resetColor();
      GlStateManager.bindTexture(5375 ^ -15464 ^ 22956 ^ -28981);
   }

   private static int tsvo26SahA(Framebuffer var0) {
      return var0.framebufferTexture;
   }

   public void setUniformf(String name, float... args) {
      int loc = GL20.glGetUniformLocation(jwrSq4JFTO(this), name);
      switch (args.length) {
         case 1:
            GL20.glUniform1f(loc, args[30908 ^ -28155 ^ 5073 ^ -1688]);
            break;
         case 2:
            GL20.glUniform2f(loc, args[19413 ^ -30717 ^ 30674 ^ -19452], args[26589 ^ -12972 ^ 24340 ^ -2660]);
            break;
         case 3:
            GL20.glUniform3f(loc, args[24430 ^ -14118 ^ 22958 ^ -12774], args[19170 ^ -32640 ^ 31831 ^ -18892], args[19099 ^ -3039 ^ 26740 ^ -10548]);
            break;
         case 4:
            GL20.glUniform4f(loc, args[32230 ^ -4620 ^ 24498 ^ -12384], args[23561 ^ -21957 ^ 29369 ^ -31606], args[1550 ^ -25090 ^ 12494 ^ -21700], args[20339 ^ -27725 ^ 14262 ^ -5259]);
      }

   }

   public void setUniformi(String name, int... args) {
      int loc = GL20.glGetUniformLocation(QtHMj9ImGt(this), name);
      if (args.length > (2899 ^ -22014 ^ 21401 ^ -3383)) {
         GL20.glUniform2i(loc, args[9032 ^ -20994 ^ 4481 ^ -24777], args[6429 ^ -20392 ^ 6043 ^ -16673]);
      } else {
         GL20.glUniform1i(loc, args[25361 ^ -16166 ^ 32306 ^ -8711]);
      }

   }

   public void init() {
      GL20.glUseProgram(Wqj9KnTklL(this));
   }

   public static void drawQuads() {
      ScaledResolution sr = new ScaledResolution(VlO6UI1Y1M());
      float width = (float)sr.getScaledWidth_double();
      float height = (float)sr.getScaledHeight_double();
      GL11.glBegin(12735 ^ -4725 ^ 21076 ^ -29081);
      GL11.glTexCoord2f(Float.intBitsToFloat('鴟' ^ 513646 ^ '뵵' ^ 1859904979 ^ 3832 ^ 516594 ^ 15798 ^ 1859895659), Float.intBitsToFloat(3744 ^ 14796 ^ 1646 ^ 371639133 ^ 122988 ^ 127232 ^ 19152 ^ 698788323));
      GL11.glVertex2f(Float.intBitsToFloat('ﺱ' ^ '駬' ^ 1239 ^ 75838069 ^ 2070 ^ '\ue294' ^ '颩' ^ 75834324), Float.intBitsToFloat(10588 ^ 4185754 ^ 19101 ^ -1260405080 ^ 11176 ^ 4165618 ^ 7222 ^ -1260405857));
      GL11.glTexCoord2f(Float.intBitsToFloat('髅' ^ '齒' ^ 4445 ^ 230239761 ^ '襀' ^ 105856 ^ '첩' ^ 230220466), Float.intBitsToFloat(2073602 ^ 2055817 ^ 3019 ^ -1471195138 ^ 2079409 ^ 27874 ^ 2095819 ^ -1471182298));
      GL11.glVertex2f(Float.intBitsToFloat(19470 ^ 487332 ^ 508176 ^ 420170711 ^ 25032 ^ 485344 ^ 513784 ^ 420182461), height);
      GL11.glTexCoord2f(Float.intBitsToFloat(24503 ^ 89332 ^ '용' ^ -1149184107 ^ 19677 ^ 99560 ^ 29124 ^ -2080330866), Float.intBitsToFloat(30438 ^ 198222 ^ '\uf192' ^ 1591314229 ^ 3083 ^ 203996 ^ '謐' ^ 1591320008));
      GL11.glVertex2f(width, height);
      GL11.glTexCoord2f(Float.intBitsToFloat(3766 ^ 27619 ^ 5846 ^ -437439622 ^ 3221 ^ 30529 ^ 8887 ^ -630384230), Float.intBitsToFloat(29811 ^ '︓' ^ 18717 ^ -1815130710 ^ 22699 ^ '\uf315' ^ 7112 ^ -1404092767));
      GL11.glVertex2f(width, Float.intBitsToFloat(1011 ^ '\ue9e7' ^ '\udcd7' ^ 397755301 ^ 'ꍦ' ^ '텖' ^ 6962 ^ 397745252));
      GL11.glEnd();
   }

   private static 0ey CQDtwnSzWF() {
      return shader;
   }

   private static 0ey yVO6rTDH1O() {
      return shader;
   }

   public static String readInputStream(InputStream inputStream) {
      StringBuilder stringBuilder = new StringBuilder();

      try {
         BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

         String line;
         while((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line).append((char)('ᴎ' ^ '턷' ^ '筐' ^ '띣'));
         }
      } catch (Exception var4) {
         Exception e = var4;
         e.printStackTrace();
      }

      return stringBuilder.toString();
   }

   private static 0ey _9gm972nwT/* $FF was: 19gm972nwT*/() {
      return shader;
   }

   private static 0ey _OOjReIFRl/* $FF was: 9OOjReIFRl*/() {
      return shader;
   }

   private static Minecraft VlO6UI1Y1M() {
      return 0eB.mc;
   }

   private static int ol5YFOv15z(0ey var0) {
      return var0.programID;
   }

   private static int x6S15oIdVM(Framebuffer var0) {
      return var0.framebufferTexture;
   }

   public _ey/* $FF was: 0ey*/(String fragmentShaderLoc) {
      this(fragmentShaderLoc, aUd8csOi7r("ĀċāęďĜċŁĝĆďĊċĜĝŁĘċĜĚċĖŀĘĝĆ"));
   }

   private static 0ey _zQTRcP6N2/* $FF was: 6zQTRcP6N2*/() {
      return shader;
   }

   private static PrintStream JxucitiwVx() {
      return System.out;
   }

   public static void drawQuads(float x, float y, float width, float height) {
      GL11.glBegin(15167 ^ -3579 ^ 45 ^ -14064);
      GL11.glTexCoord2f(Float.intBitsToFloat('괵' ^ '鰔' ^ 8127 ^ 394200399 ^ 23672 ^ 115790 ^ 2592 ^ 394181063), Float.intBitsToFloat('ꏢ' ^ 29444 ^ '\ud93d' ^ 461976305 ^ 20812 ^ 111339 ^ 9861 ^ 461962760));
      GL11.glVertex2f(x, y);
      GL11.glTexCoord2f(Float.intBitsToFloat(235296 ^ '홿' ^ 246256 ^ 1648695464 ^ 20133 ^ '鲬' ^ 26249 ^ 1648700551), Float.intBitsToFloat('\udb63' ^ '놆' ^ 17983 ^ 164518942 ^ '핿' ^ '\udc9e' ^ 12184 ^ 911103677));
      GL11.glVertex2f(x, y + height);
      GL11.glTexCoord2f(Float.intBitsToFloat('뿑' ^ 248148 ^ 'ﵘ' ^ 993902512 ^ 3627 ^ '鲲' ^ '뺀' ^ 79566964), Float.intBitsToFloat(2092273 ^ 2045616 ^ 27687 ^ 695170553 ^ 2087590 ^ 2075691 ^ 13690 ^ 384794728));
      GL11.glVertex2f(x + width, y + height);
      GL11.glTexCoord2f(Float.intBitsToFloat(30337 ^ 97934 ^ '\uf881' ^ -533803496 ^ '钬' ^ 108341 ^ '챽' ^ -542195342), Float.intBitsToFloat(248734 ^ 22588 ^ 250224 ^ -1430933697 ^ 246473 ^ 241363 ^ 14860 ^ -1430930437));
      GL11.glVertex2f(x + width, y);
      GL11.glEnd();
   }

   private static Framebuffer aH3j2tyK6T() {
      return framebuffer;
   }

   private static Framebuffer WgQQ76l9hc() {
      return framebuffer;
   }

   private static int jwrSq4JFTO(0ey var0) {
      return var0.programID;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String aUd8csOi7r(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 8290 ^ -30132 ^ 23068 ^ -4046; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 28717 ^ -22533 ^ 13376 ^ -7432));
      }

      return var1.toString();
   }

   public void unload() {
      GL20.glUseProgram(9307 ^ -19718 ^ 13111 ^ -23146);
   }

   public static void setupUniforms2(float force) {
      0ey var10000 = CQDtwnSzWF();
      String var10001 = aUd8csOi7r("ĚċĖĚěĜċħĀ");
      int[] var10002 = new int[16291 ^ -7284 ^ 1610 ^ -9628];
      var10002[2128 ^ -26141 ^ 15791 ^ -21476] = 5016 ^ -21158 ^ 29493 ^ -12809;
      var10000.setUniformi(var10001, var10002);
      var10000 = 6zQTRcP6N2();
      var10001 = aUd8csOi7r("ĈāĜčċ");
      float[] var1 = new float[4885 ^ -9737 ^ 24215 ^ -27532];
      var1[4358 ^ -16963 ^ 29224 ^ -8557] = force;
      var10000.setUniformf(var10001, var1);
   }

   private static Framebuffer jOlQwXD6j9() {
      return framebuffer;
   }

   public _ey/* $FF was: 0ey*/(String fragmentShaderLoc, String vertexShaderLoc) {
      this.roundedRectGradient = aUd8csOi7r("ōĘċĜĝćāĀŎşŜŞŤŤěĀćĈāĜăŎĘċčŜŎĂāčďĚćāĀłŎĜċčĚĽćĔċŕŤěĀćĈāĜăŎĘċčŚŎčāĂāĜşłŎčāĂāĜŜłŎčāĂāĜŝłŎčāĂāĜŚŕŤěĀćĈāĜăŎĈĂāďĚŎĜďĊćěĝŕŤŤōĊċĈćĀċŎĠġħĽīŎŀśŁŜśśŀŞŤŤĈĂāďĚŎĜāěĀĊĽĪĨņĘċčŜŎĞłŎĘċčŜŎČłŎĈĂāďĚŎĜŇŎĕŤŎŎŎŎĜċĚěĜĀŎĂċĀĉĚĆņăďĖņďČĝņĞŇŎŃŎČŎłŎŞŀŞŇŇŎŃŎĜŕŤēŤŤĘċčŝŎčĜċďĚċĩĜďĊćċĀĚņĘċčŜŎčāāĜĊĝłŎĘċčŝŎčāĂāĜşłŎĘċčŝŎčāĂāĜŜłŎĘċčŝŎčāĂāĜŝłŎĘċčŝŎčāĂāĜŚŇĕŤŎŎŎŎĘċčŝŎčāĂāĜŎœŎăćĖņăćĖņčāĂāĜşŀĜĉČłŎčāĂāĜŜŀĜĉČłŎčāāĜĊĝŀėŇłŎăćĖņčāĂāĜŝŀĜĉČłŎčāĂāĜŚŀĜĉČłŎčāāĜĊĝŀėŇłŎčāāĜĊĝŀĖŇŕŤŎŎŎŎŁŁĪćĚĆċĜćĀĉŎĚĆċŎčāĂāĜŤŎŎŎŎŁŁŎĈĜāăŎĆĚĚĞĝŔŁŁĝĆďĊċĜŃĚěĚāĜćďĂŀĊċĘŁďĊĘďĀčċĊŁčāĂāĜŃČďĀĊćĀĉŃĊćĚĆċĜćĀĉŁŤŎŎŎŎčāĂāĜŎŅœŎăćĖņĠġħĽīłŎŃĠġħĽīłŎĈĜďčĚņĝćĀņĊāĚņčāāĜĊĝŀĖėłŎĘċčŜņşŜŀŗŖŗŖłŎřŖŀŜŝŝŇŇŇŎńŎŚŝřśŖŀśŚśŝŇŇŕŤŎŎŎŎĜċĚěĜĀŎčāĂāĜŕŤēŤŤĘāćĊŎăďćĀņŇŎĕŤŎŎŎŎĘċčŜŎĝĚŎœŎĉĂıĺċĖĭāāĜĊĵŞĳŀĝĚŕŤŎŎŎŎĘċčŜŎĆďĂĈĽćĔċŎœŎĜċčĚĽćĔċŎńŎŀśŕŤŎŎŎŎŤŎŎŎŎĈĂāďĚŎĝăāāĚĆċĊįĂĞĆďŎœŎŎņşŀŞŃĝăāāĚĆĝĚċĞņŞŀŞłŎŜŀłŎĜāěĀĊĽĪĨņĆďĂĈĽćĔċŎŃŎņĉĂıĺċĖĭāāĜĊĵŞĳŀĝĚŎńŎĜċčĚĽćĔċŇłŎĆďĂĈĽćĔċŎŃŎĜďĊćěĝŎŃŎşŀłŎĜďĊćěĝŇŇŇŎńŎčāĂāĜşŀďŕŤŎŎŎŎĉĂıĨĜďĉĭāĂāĜŎœŎĘċčŚņčĜċďĚċĩĜďĊćċĀĚņĝĚłŎčāĂāĜşŀĜĉČłŎčāĂāĜŜŀĜĉČłŎčāĂāĜŝŀĜĉČłŎčāĂāĜŚŀĜĉČŇłŎĝăāāĚĆċĊįĂĞĆďŇŕŤē");
      this.roundedRect = aUd8csOi7r("ōĘċĜĝćāĀŎşŜŞŤŤěĀćĈāĜăŎĘċčŜŎĂāčďĚćāĀłŎĜċčĚĽćĔċŕŤěĀćĈāĜăŎĘċčŚŎčāĂāĜŕŤěĀćĈāĜăŎĈĂāďĚŎĜďĊćěĝŕŤěĀćĈāĜăŎČāāĂŎČĂěĜŕŤŤĈĂāďĚŎĜāěĀĊĽĪĨņĘċčŜŎĞłŎĘċčŜŎČłŎĈĂāďĚŎĜŇŎĕŤŎŎŎŎĜċĚěĜĀŎĂċĀĉĚĆņăďĖņďČĝņĞŇŎŃŎČłŎŞŀŞŇŇŎŃŎĜŕŤēŤŤŤĘāćĊŎăďćĀņŇŎĕŤŎŎŎŎĘċčŜŎĜċčĚĦďĂĈŎœŎĜċčĚĽćĔċŎńŎŀśŕŤŎŎŎŎŁŁŎĽăāāĚĆŎĚĆċŎĜċĝěĂĚŎņĈĜċċŎďĀĚćďĂćďĝćĀĉŇŀŤŎŎŎŎĈĂāďĚŎĝăāāĚĆċĊįĂĞĆďŎœŎŎņşŀŞŃĝăāāĚĆĝĚċĞņŞŀŞłŎşŀŞłŎĜāěĀĊĽĪĨņĜċčĚĦďĂĈŎŃŎņĉĂıĺċĖĭāāĜĊĵŞĳŀĝĚŎńŎĜċčĚĽćĔċŇłŎĜċčĚĦďĂĈŎŃŎĜďĊćěĝŎŃŎşŀłŎĜďĊćěĝŇŇŇŎńŎčāĂāĜŀďŕŤŎŎŎŎĉĂıĨĜďĉĭāĂāĜŎœŎĘċčŚņčāĂāĜŀĜĉČłŎĝăāāĚĆċĊįĂĞĆďŇŕŁŁŎăćĖņğěďĊĭāĂāĜłŎĝĆďĊāęĭāĂāĜłŎŞŀŞŇŕŤŤē");
      this.roundedOutlinedRect = aUd8csOi7r("ōĘċĜĝćāĀŎşŜŞŤŤěĀćĈāĜăŎĘċčŜŎĂāčďĚćāĀłŎĝćĔċŕŤěĀćĈāĜăŎĘċčŚŎčāĂāĜłŎāěĚĂćĀċĭāĂāĜŕŤěĀćĈāĜăŎĈĂāďĚŎĜďĊćěĝłŎĚĆćčąĀċĝĝŕŤŤĈĂāďĚŎĜāěĀĊċĊņĘċčŜŎčċĀĚċĜľāĝłŎĘċčŜŎĝćĔċłŎĈĂāďĚŎĜďĊćěĝŇŎĕŤŎŎŎŎĜċĚěĜĀŎĂċĀĉĚĆņăďĖņďČĝņčċĀĚċĜľāĝŇŎŃŎĝćĔċŎŅŎĜďĊćěĝłŎŞŀŞŇŇŎŃŎĜďĊćěĝŕŤēŤŤĘāćĊŎăďćĀņŇŤĕŤŎŎŎŎĈĂāďĚŎĜĽĪĨŎœŎĜāěĀĊċĊņĉĂıĨĜďĉĭāāĜĊŀĖėŎŃŎĂāčďĚćāĀŎŃŎņĝćĔċŁŜŇłŎņĝćĔċŁŜŇŎŅŎņĚĆćčąĀċĝĝŁŜŇŎŃŎşŀŞłŎĜďĊćěĝŇŕŤŤŎŎŎŎĈĂāďĚŎČĂċĀĊįăāěĀĚŎœŎĝăāāĚĆĝĚċĞņŞŀŞłŎŜŀŞłŎďČĝņĜĽĪĨŇŎŃŎņĚĆćčąĀċĝĝŁŜŇŇŕŤŤŎŎŎŎĘċčŚŎćĀĝćĊċĭāĂāĜŎœŎņĜĽĪĨŎŒŎŞŀŞŇŎőŎčāĂāĜŎŔŎĘċčŚņāěĚĂćĀċĭāĂāĜŀĜĉČłŎŎŞŀŞŇŕŤŤŎŎŎŎĉĂıĨĜďĉĭāĂāĜŎœŎăćĖņāěĚĂćĀċĭāĂāĜłŎćĀĝćĊċĭāĂāĜłŎČĂċĀĊįăāěĀĚŇŕŤē");
      int program = GL20.glCreateProgram();

      int fragmentShaderID;
      try {
         int var6 = -29915 ^ -18131 ^ 23746 ^ -28363;
         switch (fragmentShaderLoc.hashCode()) {
            case -1706591143:
               if (fragmentShaderLoc.equals(aUd8csOi7r("ĜāěĀĊċĊġěĚĂćĀċļċčĚ"))) {
                  var6 = 32169 ^ -3328 ^ 15644 ^ -19532;
               }
               break;
            case -493757311:
               if (fragmentShaderLoc.equals(aUd8csOi7r("ĜāěĀĊċĊļċčĚĩĜďĊćċĀĚ"))) {
                  var6 = 31708 ^ -6676 ^ 12857 ^ -21493;
               }
               break;
            case -72859087:
               if (fragmentShaderLoc.equals(aUd8csOi7r("ĜāěĀĊċĊļċčĚ"))) {
                  var6 = 3074 ^ -32290 ^ 16486 ^ -12870;
               }
         }

         switch (var6) {
            case 0:
               fragmentShaderID = this.createShader(new ByteArrayInputStream(this.roundedRect.getBytes()), 24460 ^ '큓' ^ 26045 ^ 24914);
               break;
            case 1:
               fragmentShaderID = this.createShader(new ByteArrayInputStream(aUd8csOi7r("ōĘċĜĝćāĀŎşŜŞŤŤěĀćĈāĜăŎĘċčŜŎĂāčďĚćāĀłŎĝćĔċŕŤěĀćĈāĜăŎĘċčŚŎčāĂāĜłŎāěĚĂćĀċĭāĂāĜŕŤěĀćĈāĜăŎĈĂāďĚŎĜďĊćěĝłŎĚĆćčąĀċĝĝŕŤŤĈĂāďĚŎĜāěĀĊċĊņĘċčŜŎčċĀĚċĜľāĝłŎĘċčŜŎĝćĔċłŎĈĂāďĚŎĜďĊćěĝŇŎĕŤŎŎŎŎĜċĚěĜĀŎĂċĀĉĚĆņăďĖņďČĝņčċĀĚċĜľāĝŇŎŃŎĝćĔċŎŅŎĜďĊćěĝłŎŞŀŞŇŇŎŃŎĜďĊćěĝŕŤēŤŤĘāćĊŎăďćĀņŇŤĕŤŎŎŎŎĈĂāďĚŎĜĽĪĨŎœŎĜāěĀĊċĊņĉĂıĨĜďĉĭāāĜĊŀĖėŎŃŎĂāčďĚćāĀŎŃŎņĝćĔċŁŜŇłŎņĝćĔċŁŜŇŎŅŎņĚĆćčąĀċĝĝŁŜŇŎŃŎşŀŞłŎĜďĊćěĝŇŕŤŤŎŎŎŎĈĂāďĚŎČĂċĀĊįăāěĀĚŎœŎĝăāāĚĆĝĚċĞņŞŀŞłŎŜŀŞłŎďČĝņĜĽĪĨŇŎŃŎņĚĆćčąĀċĝĝŁŜŇŇŕŤŤŎŎŎŎĘċčŚŎćĀĝćĊċĭāĂāĜŎœŎņĜĽĪĨŎŒŎŞŀŞŇŎőŎčāĂāĜŎŔŎĘċčŚņāěĚĂćĀċĭāĂāĜŀĜĉČłŎŎŞŀŞŇŕŤŤŎŎŎŎĉĂıĨĜďĉĭāĂāĜŎœŎăćĖņāěĚĂćĀċĭāĂāĜłŎćĀĝćĊċĭāĂāĜłŎČĂċĀĊįăāěĀĚŇŕŤē").getBytes()), 27334 ^ 'ﶽ' ^ 1269 ^ 6334);
               break;
            case 2:
               fragmentShaderID = this.createShader(new ByteArrayInputStream(aUd8csOi7r("ōĘċĜĝćāĀŎşŜŞŤŤěĀćĈāĜăŎĘċčŜŎĂāčďĚćāĀłŎĜċčĚĽćĔċŕŤěĀćĈāĜăŎĘċčŚŎčāĂāĜşłŎčāĂāĜŜłŎčāĂāĜŝłŎčāĂāĜŚŕŤěĀćĈāĜăŎĈĂāďĚŎĜďĊćěĝŕŤŤōĊċĈćĀċŎĠġħĽīŎŀśŁŜśśŀŞŤŤĈĂāďĚŎĜāěĀĊĽĪĨņĘċčŜŎĞłŎĘċčŜŎČłŎĈĂāďĚŎĜŇŎĕŤŎŎŎŎĜċĚěĜĀŎĂċĀĉĚĆņăďĖņďČĝņĞŇŎŃŎČŎłŎŞŀŞŇŇŎŃŎĜŕŤēŤŤĘċčŝŎčĜċďĚċĩĜďĊćċĀĚņĘċčŜŎčāāĜĊĝłŎĘċčŝŎčāĂāĜşłŎĘċčŝŎčāĂāĜŜłŎĘċčŝŎčāĂāĜŝłŎĘċčŝŎčāĂāĜŚŇĕŤŎŎŎŎĘċčŝŎčāĂāĜŎœŎăćĖņăćĖņčāĂāĜşŀĜĉČłŎčāĂāĜŜŀĜĉČłŎčāāĜĊĝŀėŇłŎăćĖņčāĂāĜŝŀĜĉČłŎčāĂāĜŚŀĜĉČłŎčāāĜĊĝŀėŇłŎčāāĜĊĝŀĖŇŕŤŎŎŎŎŁŁĪćĚĆċĜćĀĉŎĚĆċŎčāĂāĜŤŎŎŎŎŁŁŎĈĜāăŎĆĚĚĞĝŔŁŁĝĆďĊċĜŃĚěĚāĜćďĂŀĊċĘŁďĊĘďĀčċĊŁčāĂāĜŃČďĀĊćĀĉŃĊćĚĆċĜćĀĉŁŤŎŎŎŎčāĂāĜŎŅœŎăćĖņĠġħĽīłŎŃĠġħĽīłŎĈĜďčĚņĝćĀņĊāĚņčāāĜĊĝŀĖėłŎĘċčŜņşŜŀŗŖŗŖłŎřŖŀŜŝŝŇŇŇŎńŎŚŝřśŖŀśŚśŝŇŇŕŤŎŎŎŎĜċĚěĜĀŎčāĂāĜŕŤēŤŤĘāćĊŎăďćĀņŇŎĕŤŎŎŎŎĘċčŜŎĝĚŎœŎĉĂıĺċĖĭāāĜĊĵŞĳŀĝĚŕŤŎŎŎŎĘċčŜŎĆďĂĈĽćĔċŎœŎĜċčĚĽćĔċŎńŎŀśŕŤŎŎŎŎŤŎŎŎŎĈĂāďĚŎĝăāāĚĆċĊįĂĞĆďŎœŎŎņşŀŞŃĝăāāĚĆĝĚċĞņŞŀŞłŎŜŀłŎĜāěĀĊĽĪĨņĆďĂĈĽćĔċŎŃŎņĉĂıĺċĖĭāāĜĊĵŞĳŀĝĚŎńŎĜċčĚĽćĔċŇłŎĆďĂĈĽćĔċŎŃŎĜďĊćěĝŎŃŎşŀłŎĜďĊćěĝŇŇŇŎńŎčāĂāĜşŀďŕŤŎŎŎŎĉĂıĨĜďĉĭāĂāĜŎœŎĘċčŚņčĜċďĚċĩĜďĊćċĀĚņĝĚłŎčāĂāĜşŀĜĉČłŎčāĂāĜŜŀĜĉČłŎčāĂāĜŝŀĜĉČłŎčāĂāĜŚŀĜĉČŇłŎĝăāāĚĆċĊįĂĞĆďŇŕŤē").getBytes()), 14190 ^ 7894 ^ 9346 ^ '蘊');
               break;
            default:
               fragmentShaderID = this.createShader(0eB.mc.getResourceManager().getResource(new ResourceLocation(fragmentShaderLoc)).getInputStream(), 7870 ^ '聥' ^ 3404 ^ 6311);
         }

         GL20.glAttachShader(program, fragmentShaderID);
         int vertexShaderID = this.createShader(0eB.mc.getResourceManager().getResource(new ResourceLocation(vertexShaderLoc)).getInputStream(), 15608 ^ 90832 ^ 111268 ^ 26557);
         GL20.glAttachShader(program, vertexShaderID);
      } catch (IOException var7) {
         IOException e = var7;
         e.printStackTrace();
      }

      GL20.glLinkProgram(program);
      fragmentShaderID = GL20.glGetProgrami(program, 9149 ^ 101052 ^ 108679 ^ '訄');
      if (fragmentShaderID == 0) {
         throw new IllegalStateException(aUd8csOi7r("ĽĆďĊċĜŎĈďćĂċĊŎĚāŎĂćĀąŏ"));
      } else {
         this.programID = program;
      }
   }

   private static int QtHMj9ImGt(0ey var0) {
      return var0.programID;
   }
}
