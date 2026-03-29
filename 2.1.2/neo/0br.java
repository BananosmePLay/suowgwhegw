package neo;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class 0br extends GuiScreen {
   public static float prevY;
   public float scroll = Float.intBitsToFloat(117453 ^ 123675 ^ 26632 ^ -1665356320 ^ 119 ^ 5024 ^ 5168 ^ -1665340455);
   public final ArrayList<0bs> effectList = new ArrayList();
   public boolean chatTyping = 19156 ^ -30874 ^ 16218 ^ -3352;
   public final 0ek timer = new 0ek();
   public float openedCategoryAnim = Float.intBitsToFloat(254173 ^ 211774 ^ 23069 ^ 1274111406 ^ 409 ^ 2075001 ^ 2082064 ^ 139430304);
   public 0cB components;
   public static float width;
   public static float y;
   public boolean dragging;
   public static float x;
   public int mousex;
   public static 0eA currentTheme;
   public String currentMessage = ddMGSqgMhv("");
   public static float prevX;
   public static 0bV selectedCategory;
   public float animScroll = Float.intBitsToFloat('뾻' ^ 96745 ^ 1399 ^ -653461802 ^ 23728 ^ 26778 ^ 10313 ^ -653448816);
   public int mousey;
   public static BufferedImage colorpicker;
   public boolean cursor;
   public static float height;

   private static void tYn86jAjDs(0br var0, boolean var1) {
      var0.chatTyping = var1;
   }

   private static ArrayList awqsQzOOEa(0by var0) {
      return var0.modes;
   }

   private void renderModules(int mouseX, int mouseY, float x, float y) {
      ArrayList<0cB> allModules = rLL1I5utio(0bK.getInstance()).getModulesForCategory(d9WSxAWKYq());
      int modulesSize = allModules.size();
      ArrayList<ArrayList<0cB>> modulesl = new ArrayList();
      modulesl.add(new ArrayList(d1sdy7n51B(0bK.getInstance()).getModulesForCategory(gnOKWyHrxo()).subList(13069 ^ -14409 ^ 12799 ^ -15035, modulesSize / (32461 ^ -10763 ^ 21270 ^ -2004))));
      modulesl.add(new ArrayList(2jSQP3JvG4(0bK.getInstance()).getModulesForCategory(HDOZGQMJYN()).subList(modulesSize / (3197 ^ -2054 ^ 3785 ^ -2740), modulesSize)));
      int modulesline = 17059 ^ -19048 ^ 2793 ^ -558;
      Iterator var11 = modulesl.iterator();

      while(var11.hasNext()) {
         ArrayList<0cB> modules = (ArrayList)var11.next();
         ++modulesline;
         if (modulesline == (954 ^ -7791 ^ 22340 ^ -19091)) {
            x += Float.intBitsToFloat(11829 ^ 222654 ^ 241794 ^ -698784396 ^ 32325 ^ '샾' ^ 2376 ^ -1800853106);
         }

         int offset = 20234 ^ -19560 ^ 17085 ^ -16849;
         int offset1 = 246 ^ -11194 ^ 1120 ^ -12080;

         label211:
         for(Iterator var13 = modules.iterator(); var13.hasNext(); offset += 50) {
            0cB m = (0cB)var13.next();
            float modeHeight = Float.intBitsToFloat('\ue3e7' ^ '뾠' ^ 4262 ^ 1406540889 ^ '韵' ^ 227961 ^ 27109 ^ 299488465);
            Iterator var16;
            0bC setting;
            0by modeSetting;
            if (FIWlcjo5EG(m)) {
               var16 = m.getSetting().iterator();

               label156:
               while(true) {
                  while(true) {
                     do {
                        if (!var16.hasNext()) {
                           break label156;
                        }

                        setting = (0bC)var16.next();
                     } while(!setting.isVisible());

                     if (setting instanceof 0bv) {
                        modeHeight += Float.intBitsToFloat(11487 ^ '鈴' ^ 20582 ^ -610764474 ^ 10827 ^ '\uf6df' ^ 4351 ^ -1694999648);
                     } else if (setting instanceof 0bw) {
                        modeHeight += Float.intBitsToFloat(10836 ^ '芵' ^ 1864 ^ 1613655302 ^ 124332 ^ 26814 ^ 118717 ^ 573734912);
                     } else if (setting instanceof 0bz) {
                        modeHeight += Float.intBitsToFloat(7950 ^ 82856 ^ 122388 ^ 1133398906 ^ 4650 ^ 30304 ^ 13511 ^ 40802629);
                     } else if (setting instanceof 0bA) {
                        modeHeight += Float.intBitsToFloat(111684 ^ 108413 ^ 30265 ^ 1432782856 ^ 113900 ^ 113706 ^ 13017 ^ 337043223);
                     } else if (grpiayONt4(6LgkilQp4J()) && setting instanceof 0bx) {
                        modeHeight += (float)(((0bx)setting).getLines().length * (14844 ^ -15606 ^ 11066 ^ -11834));
                     } else if (setting instanceof 0bB) {
                        modeHeight += Float.intBitsToFloat('쉫' ^ '랂' ^ 7297 ^ 1603928421 ^ '訛' ^ 232553 ^ '鴾' ^ 510260033);
                     } else if (setting instanceof 0by) {
                        modeSetting = (0by)setting;
                        modeHeight = (float)((double)modeHeight + Double.longBitsToDouble(6923986485493900347L ^ 2316241116740461627L) + (G69jkFNwkz(modeSetting) ? Double.longBitsToDouble(-3821386396973766821L ^ -8442609689287982787L) * (double)NdpGAitVPI(modeSetting).size() + Double.longBitsToDouble(4167572530702635604L ^ 8787139848477921876L) : Double.longBitsToDouble(4005246090694211144L ^ 4005246090694211144L)));
                     }
                  }
               }
            }

            0ex.drawRoundOutline(x + Float.intBitsToFloat('썙' ^ '黙' ^ 4459 ^ 136068961 ^ '붍' ^ '\ueb36' ^ 31452 ^ 1255038957), y + Float.intBitsToFloat(21365 ^ '\ue439' ^ 6291 ^ 1203582639 ^ 19461 ^ 208135 ^ 253751 ^ 107816773) + (float)offset + ireDKvoqJS(this), Float.intBitsToFloat('衻' ^ 238096 ^ '礼' ^ -1552286146 ^ 31011 ^ '韡' ^ '줬' ^ -509867613), modeHeight, Float.intBitsToFloat(28489 ^ '뱾' ^ 11801 ^ -1175760825 ^ 21349 ^ 465445 ^ 512525 ^ -106219996), Float.intBitsToFloat('胅' ^ 2078617 ^ '\udeda' ^ 1150273589 ^ '뽁' ^ 2092691 ^ '년' ^ -74464987), new Color(9889 ^ -28084 ^ 2769 ^ -16842, 308 ^ -27051 ^ 12703 ^ -22796, 2213 ^ -6792 ^ 30046 ^ -26487, 3876 ^ -24744 ^ 14616 ^ -22022), getC(16239 ^ -31632 ^ 23741 ^ -6836));
            0eg var10000;
            String var10001;
            double var10002;
            double var10003;
            int[] var10004;
            if (m.isModuleState()) {
               var10000 = 9oaC3w1nYG();
               var10001 = m.getModuleName().toUpperCase();
               var10002 = (double)(x + Float.intBitsToFloat(248169 ^ 8306 ^ 249219 ^ -1964390610 ^ 2882 ^ 250251 ^ 244025 ^ -936017850));
               var10003 = (double)(y + Float.intBitsToFloat('귭' ^ '젊' ^ 16965 ^ -196522560 ^ '\ufdd0' ^ '骃' ^ 9464 ^ -1236981303) + (float)offset + SiytoswYaQ(this));
               var10004 = new int[5517 ^ -30242 ^ 3763 ^ -27932];
               var10004[6304 ^ -14620 ^ 21834 ^ -29938] = getC(27267 ^ -2001 ^ 25768 ^ -2556).getRGB();
               var10004[11217 ^ -18661 ^ 22550 ^ -15139] = getC(27951 ^ -27230 ^ 21544 ^ -21409).getRGB();
               var10004[30428 ^ -18503 ^ 9805 ^ -6358] = getC(15621 ^ -29905 ^ 15020 ^ -29080).getRGB();
               var10004[22470 ^ -32070 ^ 26904 ^ -17305] = getC(6707 ^ -7770 ^ 18504 ^ -20427).getRGB();
               var10000.drawGradientString(var10001, var10002, var10003, var10004, (boolean)(24973 ^ -18291 ^ 18623 ^ -28226));
            } else {
               ANxTGRWT2b().drawString(m.getModuleName().toUpperCase(), x + Float.intBitsToFloat(263 ^ '螟' ^ '\ufff9' ^ 246618569 ^ '릷' ^ 17133 ^ '덨' ^ 1282353306), y + Float.intBitsToFloat('뀡' ^ '屮' ^ 26489 ^ -1560919262 ^ 7841 ^ '\ue7a9' ^ '씥' ^ -520465813) + (float)offset + Qh4V7YJj6a(this), (new Color(10961 ^ -28641 ^ 6885 ^ -24364, 9363 ^ -24920 ^ 11252 ^ -28368, 14766 ^ -26479 ^ 9413 ^ -31483)).getRGB());
            }

            if (Aq9efhBo4a(m)) {
               var16 = m.getSetting().iterator();

               while(true) {
                  while(true) {
                     do {
                        if (!var16.hasNext()) {
                           continue label211;
                        }

                        setting = (0bC)var16.next();
                     } while(!setting.isVisible());

                     if (setting instanceof 0bv) {
                        0bv booleanSetting = (0bv)setting;
                        9vR2BibFOI().drawString(VAbDMar1BW(booleanSetting), x + Float.intBitsToFloat(28441 ^ 'ꑶ' ^ 7914 ^ 1028803510 ^ 12361 ^ '蛉' ^ 4731 ^ 2140027592), y + Float.intBitsToFloat(129834 ^ 106597 ^ 16347 ^ -2106685258 ^ 113893 ^ 110626 ^ 20599 ^ -1071730542) + (float)offset1 + (float)offset + asYxbyKlBW(this), (new Color(284 ^ -4726 ^ 6907 ^ -2331, '耚' ^ -23746 ^ 'ﶓ' ^ -8659, 3985 ^ -7169 ^ 23933 ^ -20044, 24614 ^ -22631 ^ 21549 ^ -27795)).getRGB());
                        0ex.drawRound(x + Float.intBitsToFloat('봏' ^ 124153 ^ '쌪' ^ 931575935 ^ 21439 ^ 252033 ^ 17583 ^ 1959128370), y + Float.intBitsToFloat(514798 ^ 514288 ^ 5265 ^ 87810570 ^ 4168660 ^ 4182624 ^ 16274 ^ 1197441699) + (float)offset1 + (float)offset + XyQcTfRHOi(this), Float.intBitsToFloat('쾭' ^ 3115 ^ '왯' ^ -414822225 ^ '\uf815' ^ '菚' ^ 12482 ^ -1495916981), Float.intBitsToFloat(25919 ^ '\ue080' ^ 5442 ^ 594943957 ^ 27665 ^ 'ꥣ' ^ 20838 ^ 1649810236), Float.intBitsToFloat(25437 ^ 108279 ^ 122955 ^ 1648922225 ^ 31452 ^ '觃' ^ 25098 ^ 575158917), new Color(26444 ^ -16008 ^ 14341 ^ -25053, 9330 ^ -31654 ^ 27625 ^ -13354, 13758 ^ -25077 ^ 15499 ^ -26859, 7727 ^ -24194 ^ 2756 ^ -19094));
                        if (oyQyjagWJO(booleanSetting)) {
                           0ex.drawRound(x + Float.intBitsToFloat(8769 ^ 106489 ^ 4977 ^ -1497303616 ^ 10710 ^ '裧' ^ '쒫' ^ -443859309), y + Float.intBitsToFloat(238 ^ 519440 ^ 25855 ^ -1952728513 ^ '먓' ^ 19225 ^ '쯄' ^ -906753552) + (float)offset1 + (float)offset + qDWmxAgQwc(this), Float.intBitsToFloat(27957 ^ 33534396 ^ 5804 ^ 1694490259 ^ 13912 ^ 134186636 ^ 24091 ^ 603978361), Float.intBitsToFloat(500431 ^ 32470 ^ 502244 ^ 1520142204 ^ 496887 ^ 503683 ^ 18657 ^ 463175956), Float.intBitsToFloat(113559 ^ 111710 ^ 18425 ^ 897754496 ^ 117704 ^ 129447 ^ 6515 ^ 1971504812), getC(32682 ^ -17457 ^ 20613 ^ -27122));
                        } else {
                           0ex.drawRound(x + Float.intBitsToFloat(15278 ^ 505140 ^ 521415 ^ -1851539684 ^ 255011 ^ '뽆' ^ 245915 ^ -756982081), y + Float.intBitsToFloat(128909 ^ 101189 ^ 14335 ^ 96115697 ^ 16356 ^ 30466 ^ 12726 ^ 1204988310) + (float)offset1 + (float)offset + t3v1FGSmdb(this), Float.intBitsToFloat(510442 ^ 481426 ^ 7514 ^ 1797479112 ^ 515567 ^ 485067 ^ 12381 ^ 706960787), Float.intBitsToFloat('ꨍ' ^ 22291 ^ '륏' ^ 278341252 ^ '떚' ^ 20097 ^ '눲' ^ 1368861692), Float.intBitsToFloat(24352 ^ 219403 ^ 260098 ^ 1376867398 ^ 16534 ^ 236948 ^ 259147 ^ 303137574), new Color(29350 ^ -18611 ^ 21352 ^ -26905, 5745 ^ -13693 ^ 23576 ^ -32626, 4445 ^ -21107 ^ 16097 ^ -32171));
                        }

                        offset += 14;
                     } else if (setting instanceof 0bz) {
                        0bz sliderSetting = (0bz)setting;
                        if (WYF1OTUnjB(sliderSetting)) {
                           kd1wOclclb(sliderSetting, MathHelper.clamp((float)((double)((float)(mouseX + (3902 ^ -12491 ^ 16117 ^ -307) + (20185 ^ -21963 ^ 12196 ^ -13372)) - x - Float.intBitsToFloat(12662 ^ 2092386 ^ 22493 ^ 231710615 ^ 11026 ^ '뵙' ^ 10018 ^ 1315710263)) * (double)(j7rAyEp1qw(sliderSetting) - WTOF49B8HS(sliderSetting)) / Double.longBitsToDouble(-3734998803942654014L ^ -8324800142930788414L) + (double)Rpdv9NLSxm(sliderSetting)), z8e8w1dz0C(sliderSetting), cj9DyFICjq(sliderSetting)));
                           4Wzhn6OoiI(sliderSetting, MathHelper.round(6iYcmI8qH6(sliderSetting), Q7TixFv7ev(sliderSetting)));
                        }

                        zrWrwwKyxA().drawString(TwHPU1Z1og(sliderSetting), x + Float.intBitsToFloat(17017 ^ '졻' ^ 12152 ^ -55364548 ^ 3428 ^ '陋' ^ 9766 ^ -1100004523), y + Float.intBitsToFloat('뛣' ^ 'ꤞ' ^ 17577 ^ -2042350980 ^ '캶' ^ 1007216 ^ 3174 ^ -1004014200) + (float)offset1 + (float)offset + RITcHyiLZe(this), (new Color(18355 ^ -30332 ^ 9160 ^ -4762, 25976 ^ -3854 ^ 3455 ^ -26559, 23657 ^ -31360 ^ 15615 ^ -6741, 30262 ^ -19977 ^ 3844 ^ -14278)).getRGB());
                        0ex.drawRound(x + Float.intBitsToFloat(10575 ^ '혶' ^ 2813 ^ -1995014361 ^ 8894 ^ '\ufdc9' ^ 7038 ^ -875934038), y + Float.intBitsToFloat(9860 ^ '쎴' ^ 27661 ^ -784131745 ^ 1146 ^ 26470 ^ 31247 ^ -1825339023) + (float)offset1 + (float)offset + SNaJYi99gQ(this) + Float.intBitsToFloat(31560 ^ 18072 ^ 3504 ^ 1020794663 ^ 27388 ^ 1015165 ^ '컥' ^ 2107112995), Float.intBitsToFloat(129482 ^ 98400 ^ 1795 ^ -403010328 ^ 51 ^ 120400 ^ 129802 ^ -1519591640), Float.intBitsToFloat(10284 ^ 244322 ^ 12275 ^ -1208895901 ^ 28682 ^ 225720 ^ '\uf086' ^ -139330838), Float.intBitsToFloat(249742 ^ 207825 ^ 26051 ^ -328112218 ^ 257749 ^ 30897 ^ 254695 ^ -328101191), getC(17814 ^ -22857 ^ 10773 ^ -13350));
                        double widthFormule = (double)((TRTbYGA0a0(sliderSetting) - XoCtkKF6W0(sliderSetting)) / (5g6rclwOuq(sliderSetting) - TyHL9QOl6n(sliderSetting)) * Float.intBitsToFloat(515199 ^ 25522 ^ 518246 ^ -549746319 ^ 249857 ^ 252908 ^ 2031 ^ -1649552680));
                        9Eo9ghrqhO(sliderSetting, MathHelper.lerp((double)GkOyASUy4l(sliderSetting), widthFormule, Double.longBitsToDouble(8213639252938743839L ^ 5638372941808620332L)));
                        au5SdYe7rx(sliderSetting, MathHelper.lerp((double)2Ij9cikFIW(sliderSetting), (double)p1rXFrgCqV(sliderSetting), Double.longBitsToDouble(-2791702170206299043L ^ -1836674139665354898L)));
                        0ex.drawRound(x + Float.intBitsToFloat(14125 ^ 219613 ^ '팱' ^ -2080773350 ^ '\udf21' ^ 207007 ^ 22547 ^ -1054475914) + dOlviLk0mq(sliderSetting), y + Float.intBitsToFloat(9514 ^ 214554 ^ 247280 ^ 1818062750 ^ 260154 ^ 25285 ^ 251837 ^ 774997020) + (float)offset1 + (float)offset + M9O6w7CeOb(this) + Float.intBitsToFloat('\udb84' ^ 'ꊣ' ^ 10383 ^ -1538326105 ^ 152 ^ '諻' ^ '\ue718' ^ -451986060), Float.intBitsToFloat('ꮳ' ^ 126865 ^ '쬡' ^ 261405927 ^ 27347 ^ 112657 ^ 5974 ^ 1339356784), Float.intBitsToFloat('裤' ^ 32343 ^ '\uec0c' ^ 1469391466 ^ '训' ^ 101083 ^ '쟬' ^ 389348943), Float.intBitsToFloat(30564 ^ 12641 ^ 28183 ^ -1832778263 ^ 17716 ^ 235510 ^ 2819 ^ -1832782790), new Color(25387 ^ -19454 ^ 29030 ^ -22864, 28027 ^ -15320 ^ 18924 ^ -8128, 18981 ^ -16186 ^ 8119 ^ -27221, 18651 ^ -26746 ^ 31134 ^ -22980));
                        DtaGWjd2P9(sliderSetting, (int)MathHelper.lerp((double)c1VzH2NFrS(sliderSetting), zbOdDj57yv(sliderSetting) ? Double.longBitsToDouble(-4622717301218751699L ^ -21727331906368723L) : Double.longBitsToDouble(-5432218032147701113L ^ -808991539675176313L), Double.longBitsToDouble(7845063562000008875L ^ 6014234214458105649L)));
                        l79jixNzET().drawString(String.valueOf(MathHelper.round(I2RY6VQDly(sliderSetting), tKw9wqo16T(sliderSetting))), x + Float.intBitsToFloat(243395 ^ 221829 ^ 12258 ^ -1749726725 ^ 19379 ^ '묵' ^ 7295 ^ -720019802) + Float.intBitsToFloat('끛' ^ 509842 ^ '\ue6ca' ^ 589543179 ^ 'ꄉ' ^ 1016459 ^ 'ﭗ' ^ 1643642589), y + Float.intBitsToFloat(31284 ^ 'ꛥ' ^ 17484 ^ -1516233691 ^ 100296 ^ '\udf1a' ^ 119462 ^ -405795124) + (float)offset1 + (float)offset + qoLrbfvIKZ(this) + Float.intBitsToFloat(31188 ^ 231866 ^ 256072 ^ 1331277268 ^ 237284 ^ 29159 ^ 257691 ^ 236565610), (new Color(23433 ^ -6668 ^ 31925 ^ -15817, 23201 ^ -22688 ^ 22560 ^ -23266, 22272 ^ -12086 ^ 30275 ^ -3722, 29620 ^ -294 ^ 26626 ^ -6765)).getRGB());
                        offset += 28;
                     } else {
                        int i;
                        int textLength;
                        if (setting instanceof 0bw) {
                           0bw colorSetting = (0bw)setting;
                           sqV4FRtt5t().drawString(8vQrNwSCTj(colorSetting), x + Float.intBitsToFloat(15790 ^ '갩' ^ '쐫' ^ 1657538152 ^ 9183 ^ '뚯' ^ '\uf455' ^ 537932513), y + Float.intBitsToFloat(31143 ^ '\uef66' ^ 14358 ^ -827839982 ^ 11354 ^ '酞' ^ 14310 ^ -1931997657) + (float)offset1 + (float)offset + VralTeBRth(this), (new Color(6264 ^ -28761 ^ 27269 ^ -573, 9299 ^ -28580 ^ 14126 ^ -31851, 31234 ^ -7973 ^ 31753 ^ -6547, 31926 ^ -14844 ^ 9191 ^ -26198)).getRGB());
                           i = (int)((float)mouseX - (x + Float.intBitsToFloat(21351 ^ 102483 ^ 32065 ^ 2054465428 ^ 13737 ^ 113204 ^ 25060 ^ 950597528)));
                           textLength = (int)((float)mouseY - (y + Float.intBitsToFloat('꣗' ^ '뛨' ^ 26842 ^ 1050281492 ^ '\uf420' ^ 209892 ^ 20265 ^ 2081814556) + (float)offset1 + (float)offset + I3VeG44nVb(this)));
                           0ew.drawImage(new ResourceLocation(ddMGSqgMhv("ѸѳѹѡѷѤѳйѿѻѷѱѳѥйѵѹѺѹѤѦѿѵѽѳѤиѦѸѱ")), x + Float.intBitsToFloat(518344 ^ 487809 ^ 4849 ^ 1492344725 ^ 2081607 ^ 2059800 ^ 1262 ^ 439319964), y + Float.intBitsToFloat(26542 ^ 232121 ^ 15803 ^ 1113444604 ^ 22158 ^ 237151 ^ 32087 ^ 13743574) + (float)offset1 + (float)offset + eJB9D21S5R(this), Float.intBitsToFloat(28089 ^ '鉉' ^ 9789 ^ -1022860235 ^ 107304 ^ 81974 ^ 15190 ^ -2120325712), Float.intBitsToFloat(30077 ^ 238041 ^ 753 ^ 1889388677 ^ '芴' ^ 249770 ^ '\uf858' ^ 834513814), new Color(30142 ^ -23296 ^ 2255 ^ -10098, 9286 ^ -28844 ^ 22429 ^ -912, 7825 ^ -11952 ^ 1796 ^ -14278));
                           0ew.drawRect(x + Float.intBitsToFloat(99949 ^ 125068 ^ 13334 ^ 948741319 ^ 2073672 ^ 2071925 ^ 23045 ^ 2077203720), y + Float.intBitsToFloat(31783 ^ 218931 ^ 248192 ^ 71389523 ^ 28633 ^ '\uf843' ^ 4726 ^ 1187853867) + (float)offset1 + (float)offset + XVwXFblX0Y(this), Float.intBitsToFloat(105333 ^ 107854 ^ 32009 ^ 1902163134 ^ 116914 ^ 117139 ^ 14512 ^ 809553437), Float.intBitsToFloat(27215 ^ 'ꦘ' ^ 20212 ^ -240841189 ^ 10551 ^ 'ꖈ' ^ 6049 ^ -1333454810), colorSetting.getColorc());
                           if (Mouse.isButtonDown(25316 ^ -17033 ^ 1648 ^ -9757) && i >= 0 && textLength >= 0 && i < lQPO4b9IVM().getWidth() && textLength < djbe2btm1v().getHeight()) {
                              ylKlaQUn7q(colorSetting, getColor(giFeKt02fg(), i, textLength).getRGB());
                              WEOlQWFK6V(colorSetting, i);
                           }

                           0ew.drawRect(x + Float.intBitsToFloat(5247 ^ 67086781 ^ 26038 ^ -704653829 ^ '薵' ^ 503603 ^ '\uf021' ^ -1797531864) + (float)qDeiYjM6lg(colorSetting), y + Float.intBitsToFloat(516305 ^ 12595 ^ 517445 ^ -462172943 ^ 234686 ^ 241901 ^ 17350 ^ -1493590077) + (float)offset1 + (float)offset + tAsagT6nqI(this), Float.intBitsToFloat('莙' ^ 1007421 ^ 28901 ^ -1495793708 ^ 16240 ^ 14612 ^ 5844 ^ -422034651), Float.intBitsToFloat('쩺' ^ '뭙' ^ 26313 ^ 361525014 ^ '뾞' ^ 'ﭢ' ^ 6104 ^ 1422666712), new Color(30817 ^ -24356 ^ 10890 ^ -3384, 9115 ^ -6757 ^ 25740 ^ -23949, 9586 ^ -4400 ^ 10591 ^ -7678));
                           offset += 39;
                        } else {
                           String mode;
                           if (setting instanceof 0bA) {
                              0bA textSetting = (0bA)setting;
                              gTtyK4QQp9().drawString(pqIa1F2Hfc(textSetting), x + Float.intBitsToFloat(22019 ^ 100199 ^ 14123 ^ 575076039 ^ 30433 ^ 78953 ^ '\uef94' ^ 1620753812), y + Float.intBitsToFloat('蹜' ^ 20437 ^ '集' ^ -2050942288 ^ 12331 ^ 24317 ^ 2741 ^ -944690788) + (float)offset1 + (float)offset + W2iT7c6sAY(this), (new Color(30341 ^ -266 ^ 5039 ^ -25787, 19915 ^ -17200 ^ 30725 ^ -30294, 18942 ^ -32133 ^ 4173 ^ -9355, 26443 ^ -25913 ^ 4295 ^ -4684)).getRGB());
                              0ex.drawRoundOutline(x + Float.intBitsToFloat(231687 ^ '铭' ^ 255580 ^ 1705861979 ^ 12451 ^ 992908 ^ 1026428 ^ 646391742), y + Float.intBitsToFloat(30134 ^ 115418 ^ 32518 ^ -481633772 ^ 9730 ^ 124411 ^ 452 ^ -1591029693) + (float)offset1 + (float)offset + 2N3nhqiQPL(this), Float.intBitsToFloat(101668 ^ '혞' ^ 118056 ^ 921443449 ^ 19554 ^ 'ﰹ' ^ 32413 ^ 1959806125), Float.intBitsToFloat(25226 ^ '靨' ^ 8160 ^ 1236297375 ^ 7812 ^ '\ue2e0' ^ 27576 ^ 148904769), Float.intBitsToFloat('鹳' ^ 28347 ^ '숦' ^ -242742255 ^ '鶛' ^ 1010777 ^ 2428 ^ -1316501951), Float.intBitsToFloat(12333 ^ '\uefcb' ^ '났' ^ 1415326337 ^ 247895 ^ 230580 ^ 615 ^ -356150571), new Color(24680 ^ -11956 ^ 14728 ^ -30551, 663 ^ -19401 ^ 13772 ^ -31900, 32558 ^ -20034 ^ 13751 ^ -1240), new Color(4465 ^ -399 ^ 29629 ^ -25425, 5016 ^ -10625 ^ 858 ^ -14678, 28395 ^ -14192 ^ 11508 ^ -30044, 31086 ^ -7141 ^ 11663 ^ -20475));
                              mode = o8hE2enCyt(textSetting);
                              textLength = mode.length();
                              Nw4SNIqKP2().drawString(textLength <= (22982 ^ -29692 ^ 31755 ^ -22079) ? mode : mode.substring(textLength - (1540 ^ -9604 ^ 21463 ^ -28762), textLength) + (YsSYeynhM6(textSetting) && DYwN42oW8l(this) ? ddMGSqgMhv("щ") : ddMGSqgMhv("")), x + Float.intBitsToFloat(24683 ^ 'Ꞑ' ^ '칂' ^ -6085932 ^ 18078 ^ 515688 ^ 17186 ^ -1129582407) + Float.intBitsToFloat(114255 ^ 100101 ^ 2209 ^ -1958702898 ^ 6353 ^ '蘽' ^ 1637 ^ -894420564), y + Float.intBitsToFloat(14231 ^ 104020 ^ 22848 ^ -698686658 ^ 1394 ^ 90104 ^ '訮' ^ -1809134823) + (float)offset1 + (float)offset + mJw9tQqo9E(this), w7qx4bge96(textSetting) ? getC(22450 ^ 21469 ^ 1800 ^ 393).getRGB() : (new Color(12854 ^ -10986 ^ 31769 ^ -25696, 18007 ^ -4473 ^ 13929 ^ -25075, 28362 ^ -11889 ^ 22596 ^ -6212, 16380 ^ -24267 ^ 19829 ^ -11453)).getRGB());
                              offset += 15;
                           } else if (setting instanceof 0bB) {
                              0bB themeSetting = (0bB)setting;
                              0eu.drawGlowGradientFIX(x + Float.intBitsToFloat(21511 ^ 123881 ^ 9217 ^ -46746128 ^ 4725 ^ 108365 ^ 22373 ^ -1076837310), y + Float.intBitsToFloat(1039 ^ 111601 ^ 99763 ^ -1104378895 ^ 106728 ^ '낪' ^ 131066 ^ -61055484) + (float)offset1 + (float)offset + da7l1V2tGd(this), Float.intBitsToFloat('褼' ^ '辈' ^ 26327 ^ 2097560396 ^ '雱' ^ 1029328 ^ 'ꞎ' ^ 1069014656), Float.intBitsToFloat('썒' ^ '\udb64' ^ 1030 ^ 296313063 ^ '쒊' ^ '퐅' ^ 12749 ^ 1348558229), 19208 ^ -4970 ^ 5305 ^ -19675, lkMOPu05b7(themeSetting).getRGB(), ndrFYNFaw7(themeSetting).getRGB(), e9tYna4KA0(themeSetting).getRGB(), cIQRcUpJFi(themeSetting).getRGB());
                              offset += 30;
                           } else if (setting instanceof 0by) {
                              modeSetting = (0by)setting;
                              if (szyiknQ3je(modeSetting)) {
                                 0ex.drawRoundOutline(x + Float.intBitsToFloat('ꅴ' ^ '\ueefe' ^ 26463 ^ -1299555048 ^ '\uf70f' ^ '햂' ^ 18344 ^ -242937624) - Float.intBitsToFloat('쁚' ^ 92473 ^ 9119 ^ 1265028399 ^ 7432 ^ '鰤' ^ '챽' ^ 180816514) + Float.intBitsToFloat(28694 ^ 116723 ^ 493 ^ -842090307 ^ 15568 ^ 122812 ^ 16477 ^ -1942049404), y + Float.intBitsToFloat(29796 ^ 258496 ^ 6194 ^ 1632746943 ^ 25043 ^ 248445 ^ 14822 ^ 589410913) + Float.intBitsToFloat(6983 ^ '麬' ^ 8899 ^ 988098548 ^ 233025 ^ '馇' ^ 253697 ^ 2051358747) + (float)offset1 + (float)offset + 3LJ0OIlrCp(this), Float.intBitsToFloat(245223 ^ 250591 ^ 22282 ^ -1218589636 ^ 235388 ^ 245923 ^ 16405 ^ -182066236), (float)IiNJ4DdSvo(modeSetting).size() * Float.intBitsToFloat(28442 ^ 12243 ^ 28480 ^ 1014108835 ^ 23477 ^ 125715 ^ 18250 ^ 2103507445) + Float.intBitsToFloat(100061 ^ 88528 ^ 15718 ^ -103785185 ^ 114310 ^ 88320 ^ 6236 ^ -1203748690), Float.intBitsToFloat(29953 ^ '짶' ^ 16378 ^ -1504179425 ^ 13998 ^ 25034 ^ 23706 ^ -1713863700), Float.intBitsToFloat('센' ^ '\ue555' ^ 20568 ^ -1610809301 ^ 109924 ^ 21109 ^ 110098 ^ 560249987), new Color(11301 ^ -27748 ^ 5830 ^ -22150, 4664 ^ -16580 ^ 27447 ^ -14789, 6272 ^ -12123 ^ 19376 ^ -31846), new Color(14505 ^ -6157 ^ 29722 ^ -21678, 22089 ^ -9165 ^ 14115 ^ -17074, 27397 ^ -10649 ^ 30003 ^ -14214));

                                 for(i = 2710 ^ -27579 ^ 26804 ^ -2457; i < ombBeQF3GG(modeSetting).size(); ++i) {
                                    String mode1 = (String)s7Kv5nt5j7(modeSetting).get(i);
                                    if (i == bdutXUeFJB(modeSetting)) {
                                       var10000 = IjElgQfJir();
                                       var10002 = (double)(x + Float.intBitsToFloat('\uf598' ^ 234701 ^ '줄' ^ 1399187517 ^ 5805 ^ '芼' ^ '죜' ^ 275979425) - Float.intBitsToFloat(28077 ^ 205083 ^ '\ud97a' ^ -2044348772 ^ '臯' ^ '襀' ^ 13024 ^ -947513057) + Float.intBitsToFloat('퓗' ^ '낱' ^ 11222 ^ 726736617 ^ 10508 ^ 90087 ^ '\uf42e' ^ 1788924828));
                                       var10003 = (double)(y + Float.intBitsToFloat(257304 ^ 233659 ^ 911 ^ 465146920 ^ 180 ^ 23136 ^ 4638 ^ 1497342670) + Float.intBitsToFloat('융' ^ 90679 ^ 1695 ^ -1783434127 ^ '첊' ^ 29887 ^ '\uf4ca' ^ -720168173) + Float.intBitsToFloat(1226 ^ 247416 ^ 257310 ^ 1610735309 ^ 854 ^ 259771 ^ 253311 ^ 541186547) - Float.intBitsToFloat(236031 ^ 243022 ^ 31356 ^ -1127895765 ^ 243213 ^ 232900 ^ 1894 ^ -35272887) + Float.intBitsToFloat(237250 ^ '\uaa38' ^ 258288 ^ 1581088152 ^ 18585 ^ 2580 ^ 19544 ^ 513652551) + (float)offset1 + (float)offset + JlC1oB7C0O(this) + Float.intBitsToFloat(25 ^ '재' ^ 'ꑪ' ^ 1864765201 ^ 4476 ^ 13115 ^ 4436 ^ 791044061) + (float)(i * (8376 ^ -22888 ^ 14397 ^ -16873)));
                                       var10004 = new int[22306 ^ -8381 ^ 7714 ^ -27065];
                                       var10004[20756 ^ -6529 ^ 5386 ^ -23967] = getC(8501 ^ -26803 ^ 14822 ^ -28770).getRGB();
                                       var10004[11469 ^ -4658 ^ 3292 ^ -12834] = getC(4431 ^ 29877 ^ 26095 ^ 239).getRGB();
                                       var10004[32106 ^ -17086 ^ 8374 ^ -8036] = getC(14926 ^ -3917 ^ 29725 ^ -17394).getRGB();
                                       var10004[25477 ^ -10386 ^ 31708 ^ -12492] = getC(26768 ^ -32331 ^ 8174 ^ -2781).getRGB();
                                       var10000.drawGradientString(mode1, var10002, var10003, var10004, (boolean)(31550 ^ -5287 ^ 27691 ^ -947));
                                    } else {
                                       Fq1B4wog2J().drawString(mode1, x + Float.intBitsToFloat(19993 ^ 502606 ^ 509607 ^ 1702595129 ^ 111378 ^ '쓔' ^ 127074 ^ 644755053) - Float.intBitsToFloat(114797 ^ 103182 ^ 22887 ^ 2036920473 ^ 111368 ^ 91166 ^ 21059 ^ 952664008) + Float.intBitsToFloat(3596 ^ 217548 ^ 246729 ^ 1128881882 ^ 247757 ^ '耘' ^ 249811 ^ 45697749), y + Float.intBitsToFloat(26126 ^ 241685 ^ 7418 ^ -1419392289 ^ 31977 ^ 4162828 ^ 9690 ^ -371205119) + Float.intBitsToFloat(26845 ^ '\ud971' ^ 16071 ^ -1179449162 ^ 9845 ^ 12936 ^ 5445 ^ -116160923) + Float.intBitsToFloat(21283 ^ 2225 ^ 23309 ^ 428310572 ^ 22296 ^ '骍' ^ 471 ^ 1506233585) - Float.intBitsToFloat(30507 ^ 25689 ^ 930 ^ 1269834790 ^ '\uaa38' ^ 2061615 ^ 4059 ^ 177202234) + Float.intBitsToFloat(31020 ^ '鳓' ^ 25400 ^ -1230557852 ^ 22832 ^ 1013984 ^ 1041199 ^ -167286436) + (float)offset1 + (float)offset + 6Tk6XnJHKF(this) + Float.intBitsToFloat('ꯑ' ^ 105027 ^ '\uf19a' ^ -602473966 ^ '\ua7e7' ^ 16945 ^ '\uf348' ^ -1676202876) + (float)(i * (28047 ^ -10734 ^ 4579 ^ -21900)), (new Color(4634 ^ -2325 ^ 11200 ^ -12375, 15091 ^ -32571 ^ 29475 ^ -13894, 10810 ^ -3092 ^ 25503 ^ -17675)).getRGB());
                                    }
                                 }
                              }

                              ZBpKFpb5xt().drawString(Y29ySyIWeZ(modeSetting), x + Float.intBitsToFloat(28786 ^ 126679 ^ 13975 ^ 117875879 ^ 16338 ^ 106970 ^ 29914 ^ 1168565831) + Float.intBitsToFloat(11350 ^ 120073 ^ 24387 ^ -586505000 ^ 17787 ^ 103725 ^ 7115 ^ -1661286055), y + Float.intBitsToFloat(29042 ^ 511769 ^ 11919 ^ -432828221 ^ '耹' ^ 505128 ^ '쏬' ^ -1538001190) + Float.intBitsToFloat(239897 ^ 229572 ^ 1408 ^ -1143895804 ^ 30446 ^ '나' ^ 5334 ^ -76449799) + (float)offset1 + (float)offset + TaqWAFQHZ7(this), (new Color(8989 ^ -9449 ^ 17674 ^ -16999, 29249 ^ -4548 ^ 16590 ^ -9209, 30510 ^ -24766 ^ 20462 ^ -22721, 20596 ^ -4457 ^ 29414 ^ -13062)).getRGB());
                              0ex.drawRoundOutline(x + Float.intBitsToFloat('믒' ^ '띳' ^ 7711 ^ -403099719 ^ 'ꢪ' ^ 222994 ^ 4115 ^ -1527389524) - Float.intBitsToFloat(126875 ^ 101358 ^ 18114 ^ -962930619 ^ 120546 ^ 108655 ^ 18944 ^ -2026189185) + Float.intBitsToFloat('핛' ^ 107336 ^ '\uf2bb' ^ 1155849950 ^ '铂' ^ 18574 ^ '럝' ^ 85275111), y + Float.intBitsToFloat('뤃' ^ 249946 ^ '꒧' ^ 327805628 ^ 26443 ^ 241912 ^ 921 ^ 1373238120) + Float.intBitsToFloat('\udd7a' ^ '黰' ^ 23410 ^ 1178815592 ^ 3107 ^ 84135 ^ 'ﲱ' ^ 115535013) + (float)offset1 + (float)offset + 5bD10N6J4i(this), Float.intBitsToFloat(18907 ^ '\ue765' ^ 10788 ^ 653333807 ^ 234803 ^ 27795 ^ 254392 ^ 1686735277), Float.intBitsToFloat(5424 ^ '\udf24' ^ 21334 ^ 1770207111 ^ 495074 ^ 460158 ^ 2453 ^ 684928972), Float.intBitsToFloat(16829 ^ 110902 ^ 949 ^ -1015875392 ^ '腃' ^ 80419 ^ 2830 ^ -51201136), Float.intBitsToFloat(10211 ^ '\ufaef' ^ 11488 ^ 1676961635 ^ 20551 ^ 118530 ^ 15033 ^ -580080919), new Color(29949 ^ -3673 ^ 22701 ^ -8718, 1242 ^ -21659 ^ 10081 ^ -30506, 10168 ^ -11982 ^ 29742 ^ -32085), new Color(6516 ^ -1640 ^ 21861 ^ -19045, 26661 ^ -19196 ^ 30103 ^ -22367, 5466 ^ -19502 ^ 16843 ^ -6296, 1797 ^ -14405 ^ 23515 ^ -25702));
                              mode = (String)awqsQzOOEa(modeSetting).get(EIiDnWymk9(modeSetting));
                              var10000 = 64YVtGPgaZ();
                              var10001 = cutString(mode);
                              var10002 = (double)(x + Float.intBitsToFloat(24496 ^ '返' ^ '\ud9e9' ^ 1225795094 ^ '臈' ^ 97939 ^ 10555 ^ 168098299) - Float.intBitsToFloat(28154 ^ '묳' ^ 8306 ^ -1483839570 ^ 17232 ^ '躍' ^ 14436 ^ -433167188) + Float.intBitsToFloat(16507 ^ 239249 ^ 12158 ^ -633740636 ^ 'ꌍ' ^ 206440 ^ 34 ^ -1681281417));
                              var10003 = (double)(y + Float.intBitsToFloat(102088 ^ '윅' ^ 122789 ^ 306199468 ^ 112911 ^ 14886 ^ 110303 ^ 1343782962) + Float.intBitsToFloat(26579 ^ 76705 ^ '\uf16c' ^ 1001563271 ^ 14378 ^ 103361 ^ 22057 ^ 2064835675) + (float)offset1 + (float)offset + deEDDyoNh1(this) + Float.intBitsToFloat(126198 ^ 130448 ^ 18198 ^ 1753131976 ^ 101221 ^ 100703 ^ 25761 ^ 679385891));
                              var10004 = new int[5595 ^ -20810 ^ 3362 ^ -18869];
                              var10004[14633 ^ -21667 ^ 19878 ^ -8238] = getC(11404 ^ -1065 ^ 17876 ^ -28017).getRGB();
                              var10004[27643 ^ -3540 ^ 3790 ^ -26856] = getC(15533 ^ -16931 ^ 23963 ^ -9199).getRGB();
                              var10004[4567 ^ -31568 ^ 18903 ^ -9038] = getC(3727 ^ -23947 ^ 13451 ^ -25953).getRGB();
                              var10004[2941 ^ -16841 ^ 10993 ^ -24648] = getC(28405 ^ -14761 ^ 13592 ^ -25006).getRGB();
                              var10000.drawGradientString(var10001, var10002, var10003, var10004, (boolean)(30927 ^ -19955 ^ 23969 ^ -26782));
                              var10000 = w814lorcKf();
                              var10001 = RoYaIQG8Qi(modeSetting) ? ddMGSqgMhv("Ѡ") : ddMGSqgMhv("Ш");
                              var10002 = (double)(x + Float.intBitsToFloat(4208 ^ 21562 ^ 8109 ^ 1988410306 ^ 4461 ^ 239587 ^ 246056 ^ 898733955) - Float.intBitsToFloat(10046 ^ '\uf3e5' ^ '\ud92a' ^ -1912450878 ^ 8940 ^ '좐' ^ '﹝' ^ -811448046) + Float.intBitsToFloat('\uf5e2' ^ 99798 ^ '픟' ^ -375463093 ^ 9170 ^ 112636 ^ 15503 ^ -1469124927) + Float.intBitsToFloat(5728 ^ '꺇' ^ 27227 ^ 1190032552 ^ 26027 ^ 10196 ^ 27979 ^ 78547232));
                              var10003 = (double)(y + Float.intBitsToFloat(103059 ^ 22745 ^ 114216 ^ 111977771 ^ 118839 ^ '걳' ^ 128598 ^ 1156864859) + Float.intBitsToFloat(12301 ^ 74026 ^ '류' ^ -462753353 ^ 10597 ^ 79271 ^ '땷' ^ -1530204035) + (float)offset1 + (float)offset + Cc9Te4jrLH(this) + Float.intBitsToFloat(17748 ^ 31027 ^ 2535 ^ -153910925 ^ 20352 ^ 24477 ^ 14030 ^ -1227648480));
                              var10004 = new int[12309 ^ -31218 ^ 9727 ^ -27680];
                              var10004[27618 ^ -17178 ^ 4317 ^ -14375] = getC(27045 ^ -17284 ^ 14523 ^ -4766).getRGB();
                              var10004[4896 ^ -27495 ^ 17573 ^ -15587] = getC(30508 ^ -5487 ^ 9186 ^ -16731).getRGB();
                              var10004[27201 ^ -13210 ^ 3755 ^ -22386] = getC(19911 ^ -23972 ^ 31983 ^ -28262).getRGB();
                              var10004[9242 ^ -23677 ^ 8605 ^ -23033] = getC(15881 ^ -29032 ^ 32 ^ -19623).getRGB();
                              var10000.drawGradientString(var10001, var10002, var10003, var10004, (boolean)(29468 ^ -22421 ^ 13638 ^ -4560));
                              offset = (int)((double)offset + Double.longBitsToDouble(1045615379245316828L ^ 5670249246601394908L) + (krtHBPIMOj(modeSetting) ? Double.longBitsToDouble(-1686298735308407269L ^ -6288302323783034755L) * (double)QSY32LaOFd(modeSetting).size() + Double.longBitsToDouble(-7610868649664466714L ^ -3007063930584977178L) : Double.longBitsToDouble(1486725640722050399L ^ 1486725640722050399L)));
                           } else if (3LRwgAnTXo(jKLI22F6qD()) && setting instanceof 0bx) {
                              0bx infoSetting = (0bx)setting;
                              i = 17979 ^ -20527 ^ 16501 ^ -22113;
                              String[] var20 = infoSetting.getLines();
                              int var21 = var20.length;

                              for(int var22 = 13691 ^ -30422 ^ 4637 ^ -20916; var22 < var21; ++var22) {
                                 String line = var20[var22];
                                 rwVaOTa5S1().drawString(line, x + Float.intBitsToFloat(3123 ^ 120417 ^ 16766 ^ -1077233260 ^ 16705 ^ 17634 ^ 10599 ^ -43315588) + Float.intBitsToFloat(31442 ^ 28130 ^ 6759 ^ -954695361 ^ 24834 ^ 78085 ^ 126323 ^ -2031603428), y + Float.intBitsToFloat(29346 ^ '첮' ^ 32178 ^ 1761296660 ^ 30060 ^ '莂' ^ 17360 ^ 714815380) + Float.intBitsToFloat(12553 ^ '턠' ^ '뎚' ^ -1812850630 ^ '覌' ^ 9878 ^ 'Ꞑ' ^ -739097853) + (float)offset1 + (float)offset + (float)i + pW9gGIenNJ(this), (new Color(21055 ^ -17850 ^ 23230 ^ -19935, 12251 ^ -8776 ^ 1120 ^ -2331, 27673 ^ -13138 ^ 15604 ^ -25435, 13965 ^ -11989 ^ 5987 ^ -4038)).getRGB());
                                 i += 10;
                              }

                              offset += ((0bx)setting).getLines().length * (7554 ^ -16623 ^ 218 ^ -23997);
                           }
                        }
                     }
                  }
               }
            }
         }
      }

   }

   private static int cVgkBYSQMe(0br var0) {
      return var0.mousey;
   }

   private static 0bV FgWytSv4j1() {
      return selectedCategory;
   }

   private static int qDeiYjM6lg(0bw var0) {
      return var0.picker;
   }

   private static float JlC1oB7C0O(0br var0) {
      return var0.animScroll;
   }

   private static boolean eGQSFwWXiF(0cB var0) {
      return var0.opened;
   }

   private static void aQz2SevFWI(0bA var0, boolean var1) {
      var0.typing = var1;
   }

   private static boolean vF9KeEYYgC(0bv var0) {
      return var0.value;
   }

   private static void _Wzhn6OoiI/* $FF was: 4Wzhn6OoiI*/(0bz var0, float var1) {
      var0.value = var1;
   }

   private static float I3VeG44nVb(0br var0) {
      return var0.animScroll;
   }

   private static String V4QqIo3WZT(0br var0) {
      return var0.currentMessage;
   }

   private static float t3v1FGSmdb(0br var0) {
      return var0.animScroll;
   }

   private static String ijkcbJQnpp(0bA var0) {
      return var0.text;
   }

   private static boolean grpiayONt4(0bv var0) {
      return var0.value;
   }

   private static float jGBdgQKWzs() {
      return x;
   }

   private static void rUbKdW7oyT(0br var0, boolean var1) {
      var0.dragging = var1;
   }

   private static void Ba4ke40bDJ(0br var0, float var1) {
      var0.scroll = var1;
   }

   private static ArrayList dXDWetBSqO(0br var0) {
      return var0.effectList;
   }

   private static boolean gtkbOIuWNC(0bA var0) {
      return var0.typing;
   }

   private static 0bN _jSQP3JvG4/* $FF was: 2jSQP3JvG4*/(0bK var0) {
      return var0.moduleManager;
   }

   private static 0eg sqV4FRtt5t() {
      return 0eh.mnstb_14;
   }

   private static float Y2YGnD69zd() {
      return y;
   }

   private static 0eg gTtyK4QQp9() {
      return 0eh.mnstb_14;
   }

   private static void ittwrTbia3(0br var0, String var1) {
      var0.currentMessage = var1;
   }

   private static void cUdjXXhST7(float var0) {
      prevX = var0;
   }

   private static boolean jFnqd24DbE(0bv var0) {
      return var0.value;
   }

   private static float _LJ0OIlrCp/* $FF was: 3LJ0OIlrCp*/(0br var0) {
      return var0.animScroll;
   }

   private static float qoLrbfvIKZ(0br var0) {
      return var0.animScroll;
   }

   private static float pjT4oyaE9W() {
      return y;
   }

   private static String _8glOYHWDE/* $FF was: 88glOYHWDE*/(0br var0) {
      return var0.currentMessage;
   }

   private static 0bN d1sdy7n51B(0bK var0) {
      return var0.moduleManager;
   }

   private static float iS1uJn5IZJ(0br var0) {
      return var0.animScroll;
   }

   private static void ddg2ZoLWJB(0bV var0) {
      selectedCategory = var0;
   }

   private static float _Ij9cikFIW/* $FF was: 2Ij9cikFIW*/(0bz var0) {
      return var0.printAnimated;
   }

   private static float tAsagT6nqI(0br var0) {
      return var0.animScroll;
   }

   public boolean doesGuiPauseGame() {
      return (boolean)(29108 ^ -27049 ^ 14937 ^ -8774);
   }

   private static void yMJyWDd3Do(0bA var0, String var1) {
      var0.text = var1;
   }

   private static String cutString(String str) {
      return str.length() > (2854 ^ -16154 ^ 4662 ^ -9729) ? str.substring(12789 ^ -18511 ^ 13675 ^ -19665, 24348 ^ -3373 ^ 13069 ^ -24885) + ddMGSqgMhv("ии") : str;
   }

   private static float deEDDyoNh1(0br var0) {
      return var0.animScroll;
   }

   private static String _7R16twVNa/* $FF was: 27R16twVNa*/(0bA var0) {
      return var0.text;
   }

   private static float Jh3L9FwnWY() {
      return y;
   }

   private static float _TD6hg49Sn/* $FF was: 0TD6hg49Sn*/() {
      return y;
   }

   private static boolean _uutrlql8v/* $FF was: 7uutrlql8v*/(0by var0) {
      return var0.opened;
   }

   private static float LpxsE4dUdA() {
      return y;
   }

   private static String WG3IzXQyEr(0br var0) {
      return var0.currentMessage;
   }

   private static void lU62c1QIak(float var0) {
      x = var0;
   }

   private static float Hir4xbD2eM() {
      return y;
   }

   private static float PN23FQH2aU() {
      return y;
   }

   private static float IvLTwqCein() {
      return y;
   }

   private static float ireDKvoqJS(0br var0) {
      return var0.animScroll;
   }

   private static float _uDv29IOb4/* $FF was: 9uDv29IOb4*/() {
      return height;
   }

   public _br/* $FF was: 0br*/() {
      if (0cg.snow.value) {
         Random random = new Random();

         for(int i = 1014 ^ -11587 ^ 27773 ^ -17098; i < (32017 ^ -10010 ^ 17293 ^ -6586); ++i) {
            for(int y = 6457 ^ -30490 ^ 27168 ^ -1025; y < (8181 ^ -11201 ^ 17018 ^ -30285); ++y) {
               0bs snow = new 0bs((27566 ^ -23967 ^ 29570 ^ -17836) * i, y * (-10873 ^ -10821 ^ 6054 ^ -6060), random.nextInt(10805 ^ -13235 ^ 2498 ^ -4167) + (2347 ^ -27350 ^ 15856 ^ -24080), random.nextInt(17840 ^ -17397 ^ 11405 ^ -10956) + (27748 ^ -17001 ^ 10194 ^ -2528));
               this.effectList.add(snow);
            }
         }
      }

   }

   private static float da7l1V2tGd(0br var0) {
      return var0.animScroll;
   }

   private static float OViBlYyT4o() {
      return y;
   }

   private static boolean _LRwgAnTXo/* $FF was: 3LRwgAnTXo*/(0bv var0) {
      return var0.value;
   }

   private static float _KttYXKCWo/* $FF was: 4KttYXKCWo*/() {
      return height;
   }

   private static float eVTNZ8byA9() {
      return y;
   }

   private static void LDCldkjWTq(0br var0, float var1) {
      var0.animScroll = var1;
   }

   private static boolean W6TkFFq4UN(0br var0) {
      return var0.cursor;
   }

   private static 0bv _1TtBJr2uN/* $FF was: 51TtBJr2uN*/() {
      return 0cg.wiki;
   }

   private static float janTrrEaO6() {
      return x;
   }

   private static 0eg _gqQyi8Md4/* $FF was: 7gqQyi8Md4*/() {
      return 0eh.icons22;
   }

   private static ArrayList ombBeQF3GG(0by var0) {
      return var0.modes;
   }

   private static float W2iT7c6sAY(0br var0) {
      return var0.animScroll;
   }

   private static boolean qrzv1iW3U7(0cB var0) {
      return var0.opened;
   }

   private static void ylKlaQUn7q(0bw var0, int var1) {
      var0.color = var1;
   }

   private static float rCzwQBDqt9() {
      return width;
   }

   private static float ntL4q7QniF() {
      return height;
   }

   private static void _XSOGI67Oj/* $FF was: 7XSOGI67Oj*/(0br var0, float var1) {
      var0.openedCategoryAnim = var1;
   }

   private static float Xp8dnJQ1SI() {
      return y;
   }

   private static 0bN _tyHgoPkpJ/* $FF was: 9tyHgoPkpJ*/(0bK var0) {
      return var0.moduleManager;
   }

   private static float _N3nhqiQPL/* $FF was: 2N3nhqiQPL*/(0br var0) {
      return var0.animScroll;
   }

   private static 0ek sbk99bfdX2(0br var0) {
      return var0.timer;
   }

   private static float eJB9D21S5R(0br var0) {
      return var0.animScroll;
   }

   private static boolean krtHBPIMOj(0by var0) {
      return var0.opened;
   }

   private static ArrayList NdpGAitVPI(0by var0) {
      return var0.modes;
   }

   private static float cj9DyFICjq(0bz var0) {
      return var0.max;
   }

   private static float ne6Oyo5yIV(0br var0) {
      return var0.animScroll;
   }

   private static float yzZwocTFJD() {
      return height;
   }

   private static boolean w7qx4bge96(0bA var0) {
      return var0.typing;
   }

   public boolean isHovered(int mouseX, int mouseY, double x, double y, double width, double height) {
      return (boolean)((double)mouseX >= x && (double)mouseX <= x + width && (double)mouseY >= y && (double)mouseY <= y + height ? 3582 ^ -27394 ^ 8064 ^ -31103 : 26276 ^ -16779 ^ 29491 ^ -21534);
   }

   private static float Cc9Te4jrLH(0br var0) {
      return var0.animScroll;
   }

   private static int EIiDnWymk9(0by var0) {
      return var0.index;
   }

   private static float dOdm7LrToQ() {
      return y;
   }

   private static String Y29ySyIWeZ(0by var0) {
      return var0.name;
   }

   private static float _g6rclwOuq/* $FF was: 5g6rclwOuq*/(0bz var0) {
      return var0.max;
   }

   private static float zzqOofrJLG() {
      return x;
   }

   private static float RITcHyiLZe(0br var0) {
      return var0.animScroll;
   }

   public static Color getColor(BufferedImage img, int x, int y) {
      int clr = img.getRGB(x, y);
      int red = (clr & (2207 ^ 116924 ^ 25292 ^ 16687855)) >> (12523 ^ -25770 ^ 22357 ^ -776);
      int green = (clr & (109235 ^ 101478 ^ 18355 ^ '鹦')) >> (12845 ^ -20421 ^ 26450 ^ -6836);
      int blue = clr & (18722 ^ -4213 ^ 1554 ^ -24508);
      return new Color(red, green, blue);
   }

   private static float SiytoswYaQ(0br var0) {
      return var0.animScroll;
   }

   public void handleMouseInput() throws IOException {
      if (Mouse.hasWheel() && this.isHovered(wtEKeqTj0Y(this), cVgkBYSQMe(this), (double)(ag6tIHgtM1() + Float.intBitsToFloat(16022 ^ '膸' ^ 24806 ^ -904707183 ^ 31795 ^ '\ue797' ^ 21857 ^ -2001513828)), (double)r4q0tseO72(), (double)27YrhRJe8m(), (double)IdMLkVb1PS())) {
         int mouse = Mouse.getDWheel();
         if (mouse > 0) {
            DvynfvNVN1(this, moyxjJo72o(this) + Float.intBitsToFloat(14364 ^ 229421 ^ 27828 ^ -1563304781 ^ 1304 ^ 211907 ^ '赅' ^ -526795864));
         } else if (mouse < 0) {
            Ba4ke40bDJ(this, mBGYYJH88W(this) - Float.intBitsToFloat('虷' ^ '脒' ^ 32071 ^ 818625888 ^ 11703 ^ 19356 ^ 13547 ^ 1921193346));
         }
      }

      super.handleMouseInput();
   }

   private static 0eg WHuzONo4Yr() {
      return 0eh.mnstb_16;
   }

   private static float SywOxZrNnz() {
      return x;
   }

   private static void mFeoHctY8Y(0br var0, float var1) {
      var0.scroll = var1;
   }

   private static float _9SbIDV2g4/* $FF was: 99SbIDV2g4*/() {
      return y;
   }

   private static float LnY4Ge9RXW() {
      return x;
   }

   private static float XyQcTfRHOi(0br var0) {
      return var0.animScroll;
   }

   private static float YuPF4dnimh() {
      return x;
   }

   private static 0eg _ulQbyArwS/* $FF was: 9ulQbyArwS*/() {
      return 0eh.mnstb_16;
   }

   private static 0bV VykYAQBzyG() {
      return 0bV.Themes;
   }

   private static String iGGRQBr7Mq(0br var0) {
      return var0.currentMessage;
   }

   private static int c1VzH2NFrS(0bz var0) {
      return var0.alphaText;
   }

   private static String o8hE2enCyt(0bA var0) {
      return var0.text;
   }

   private static 0eg t4vVbgol29() {
      return 0eh.mnstb_16;
   }

   private static float DQ2Qto4dOc() {
      return y;
   }

   private static float LW4dDc7MSo(0br var0) {
      return var0.scroll;
   }

   private static 0bN rLL1I5utio(0bK var0) {
      return var0.moduleManager;
   }

   private static float rVzV9hIoyl(0br var0) {
      return var0.animScroll;
   }

   private static boolean oyQyjagWJO(0bv var0) {
      return var0.value;
   }

   private static float Q7TixFv7ev(0bz var0) {
      return var0.increment;
   }

   private static boolean DYwN42oW8l(0br var0) {
      return var0.cursor;
   }

   private static void _qyLiA2WIr/* $FF was: 6qyLiA2WIr*/(float var0) {
      y = var0;
   }

   private static float YhjAGcl9r3() {
      return y;
   }

   private static boolean Aq9efhBo4a(0cB var0) {
      return var0.opened;
   }

   private static void auhaTg6ge2(0br var0, float var1) {
      var0.scroll = var1;
   }

   private static float Tbmi4I2i6r() {
      return x;
   }

   private static String _CDL1yaoBQ/* $FF was: 4CDL1yaoBQ*/(0bA var0) {
      return var0.text;
   }

   private static boolean RoYaIQG8Qi(0by var0) {
      return var0.opened;
   }

   private static float kwvUc2FDFU() {
      return height;
   }

   private static void _j7jevIc2Y/* $FF was: 7j7jevIc2Y*/(float var0) {
      prevY = var0;
   }

   private static float p1rXFrgCqV(0bz var0) {
      return var0.value;
   }

   private static String _IdTJHITiM/* $FF was: 0IdTJHITiM*/(0bA var0) {
      return var0.text;
   }

   private static float Yw3Fvrtrlw() {
      return x;
   }

   private static boolean szyiknQ3je(0by var0) {
      return var0.opened;
   }

   private static float hwdtVXVqEi() {
      return x;
   }

   private static String _0AyXty6M6/* $FF was: 90AyXty6M6*/(0br var0) {
      return var0.currentMessage;
   }

   private static 0eg l79jixNzET() {
      return 0eh.sfbolt12;
   }

   private static float WTOF49B8HS(0bz var0) {
      return var0.min;
   }

   private static 0eg _o8ynTFedE/* $FF was: 1o8ynTFedE*/() {
      return 0eh.mnstb_16;
   }

   private static String S2WPCOZei4(0br var0) {
      return var0.currentMessage;
   }

   private static ArrayList QSY32LaOFd(0by var0) {
      return var0.modes;
   }

   private static float GkOyASUy4l(0bz var0) {
      return var0.widthAnimated;
   }

   private static boolean G69jkFNwkz(0by var0) {
      return var0.opened;
   }

   private static float bbpOVAOFrD() {
      return y;
   }

   private static void CcFNu7j0XG(0bv var0, boolean var1) {
      var0.value = var1;
   }

   private static float jjnYetxiaQ() {
      return prevX;
   }

   private static 0bV gnOKWyHrxo() {
      return selectedCategory;
   }

   private static boolean jrabjbADyg(0by var0) {
      return var0.opened;
   }

   private static String VAbDMar1BW(0bv var0) {
      return var0.name;
   }

   private static float JymL9V8OHn() {
      return y;
   }

   private static float _jtzreSgXq/* $FF was: 9jtzreSgXq*/() {
      return y;
   }

   private static float M9O6w7CeOb(0br var0) {
      return var0.animScroll;
   }

   private static 0bv _LgkilQp4J/* $FF was: 6LgkilQp4J*/() {
      return 0cg.wiki;
   }

   private static 0bV HDOZGQMJYN() {
      return selectedCategory;
   }

   private static BufferedImage giFeKt02fg() {
      return colorpicker;
   }

   private static float ag6tIHgtM1() {
      return x;
   }

   private static float qDWmxAgQwc(0br var0) {
      return var0.animScroll;
   }

   private static float mJw9tQqo9E(0br var0) {
      return var0.animScroll;
   }

   private static float gVeR6gz1s4() {
      return x;
   }

   private static void _naqJqhWA9/* $FF was: 1naqJqhWA9*/(0br var0, float var1) {
      var0.openedCategoryAnim = var1;
   }

   private static 0eg OJ8ABWgMMB() {
      return 0eh.mnstb_16;
   }

   private static float UscCvliqmr() {
      return y;
   }

   private static float moyxjJo72o(0br var0) {
      return var0.scroll;
   }

   public static Color getC(int index) {
      return 0em.TwoColorEffect(9T9jBAZ2GG().getOneColor(), PFIxLh0Kla().getTwoColor(), Double.longBitsToDouble(4075142574000421280L ^ 8690206292148337056L), index);
   }

   private static float n2AINFKF5g() {
      return y;
   }

   private static float Rpdv9NLSxm(0bz var0) {
      return var0.max;
   }

   private static 0bV _YVLPTieHY/* $FF was: 7YVLPTieHY*/() {
      return selectedCategory;
   }

   private static 0eg rwVaOTa5S1() {
      return 0eh.mnstb_10;
   }

   private static 0eA _T9jBAZ2GG/* $FF was: 9T9jBAZ2GG*/() {
      return currentTheme;
   }

   private static float d8leRhydPn(0br var0) {
      return var0.animScroll;
   }

   private static float TyHL9QOl6n(0bz var0) {
      return var0.min;
   }

   private static float u7BqAiRI7u() {
      return x;
   }

   private static float lkt2yxF52S() {
      return width;
   }

   private static float _T9DQ1S67l/* $FF was: 1T9DQ1S67l*/() {
      return y;
   }

   private static float _bD10N6J4i/* $FF was: 5bD10N6J4i*/(0br var0) {
      return var0.animScroll;
   }

   private static float _iYcmI8qH6/* $FF was: 6iYcmI8qH6*/(0bz var0) {
      return var0.value;
   }

   private static 0eg Nw4SNIqKP2() {
      return 0eh.mnstb_14;
   }

   private static void sXeSFMhRke(0bA var0, String var1) {
      var0.text = var1;
   }

   private static float dOlviLk0mq(0bz var0) {
      return var0.widthAnimated;
   }

   private static boolean WYF1OTUnjB(0bz var0) {
      return var0.pressed;
   }

   private static float r4q0tseO72() {
      return y;
   }

   private static 0ek kipB0zYz5A(0br var0) {
      return var0.timer;
   }

   private static float VralTeBRth(0br var0) {
      return var0.animScroll;
   }

   private static void _gybHAe8TN/* $FF was: 2gybHAe8TN*/(0bz var0, boolean var1) {
      var0.pressed = var1;
   }

   private static void wLC137Eodv(0br var0, float var1) {
      var0.animScroll = var1;
   }

   private static float nATaqWTX1G(0br var0) {
      return var0.scroll;
   }

   private static void kd1wOclclb(0bz var0, float var1) {
      var0.value = var1;
   }

   private static 0bN ZOhOGfqNmy(0bK var0) {
      return var0.moduleManager;
   }

   private static 0bV IEd91BDMt9() {
      return selectedCategory;
   }

   private static float TRTbYGA0a0(0bz var0) {
      return var0.value;
   }

   protected void mouseReleased(int mouseX, int mouseY, int state) {
      super.mouseReleased(mouseX, mouseY, state);
      Iterator var4 = VkTfdggt7b(0bK.getInstance()).getModulesForCategory(BySXWtAQA9()).iterator();

      while(var4.hasNext()) {
         0cB module = (0cB)var4.next();
         Iterator var6 = module.getSetting().iterator();

         while(var6.hasNext()) {
            0bC setting = (0bC)var6.next();
            if (setting.isVisible() && setting instanceof 0bz) {
               0bz sliderSetting = (0bz)setting;
               2gybHAe8TN(sliderSetting, (boolean)(22503 ^ -24337 ^ 15652 ^ -13780));
            }
         }
      }

   }

   private static float XoCtkKF6W0(0bz var0) {
      return var0.min;
   }

   private static 0bN VkTfdggt7b(0bK var0) {
      return var0.moduleManager;
   }

   private static ArrayList IiNJ4DdSvo(0by var0) {
      return var0.modes;
   }

   private static String bSrSOMV2Ab(0bA var0) {
      return var0.text;
   }

   private static float MS2J9rHiHN(0br var0) {
      return var0.animScroll;
   }

   private static String tAb91d7jLL(0br var0) {
      return var0.currentMessage;
   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      SbAU419pFO(this, mouseX);
      mdGFwEWL4g(this, mouseY);
      if (LW4dDc7MSo(this) >= Float.intBitsToFloat('씬' ^ 2058404 ^ 14291 ^ -508569087 ^ 1173 ^ 24961 ^ 9733 ^ -508558517)) {
         mFeoHctY8Y(this, Float.intBitsToFloat(108864 ^ '醼' ^ 119926 ^ -247443480 ^ 7804 ^ '軾' ^ 29288 ^ -247447160));
      }

      LDCldkjWTq(this, 0dJ.animation(g3XrDDFVuW(this), nATaqWTX1G(this), Float.intBitsToFloat(31210 ^ '\uf6f8' ^ 5526 ^ -1772080887 ^ '넚' ^ 1027231 ^ '\ue56a' ^ -1596465210)));
      0eu.drawGlowGradientFIX(Q16dbOsAJH() - Float.intBitsToFloat(4179940 ^ 4180086 ^ 10836 ^ 119525998 ^ 4192830 ^ 4185648 ^ 26172 ^ 1201649050), xXSr52BFDB() - Float.intBitsToFloat(11950 ^ 27845 ^ 13684 ^ 2125510966 ^ 124159 ^ 112275 ^ 7755 ^ 1043366414), rCzwQBDqt9() + Float.intBitsToFloat(30299 ^ 121526 ^ 27461 ^ 1893001737 ^ 31097 ^ 79351 ^ '\ud9ff' ^ 838121680), ntL4q7QniF() + Float.intBitsToFloat(7674 ^ '鴈' ^ 2207 ^ -2018571413 ^ 25853 ^ '\ue2fd' ^ 260 ^ -963705854), 21359 ^ -32467 ^ 24505 ^ -29196, getC(32162 ^ -12233 ^ 31064 ^ -11059).getRGB(), getC(13800 ^ -18545 ^ 28230 ^ -4901).getRGB(), getC(14259 ^ -7324 ^ 16568 ^ -27007).getRGB(), getC(11876 ^ -31833 ^ 18182 ^ -5843).getRGB());
      0ex.drawRound(etnzdCIF2Z() + Float.intBitsToFloat(30999 ^ 18851 ^ 3432 ^ -1911058808 ^ 19596 ^ '\uf4f4' ^ 12407 ^ -1315488933), LpxsE4dUdA() + Float.intBitsToFloat(25466 ^ 223076 ^ 'ﰔ' ^ -1520841213 ^ 17410 ^ 29694 ^ 6380 ^ -447080167), Q4FpMtD3va(), BbyFVrIb2V(), Float.intBitsToFloat(4170585 ^ 23004 ^ 4180752 ^ -337671905 ^ 4168809 ^ 4146296 ^ 4018 ^ -1411420375), new Color(10167 ^ -25817 ^ 5009 ^ -20720, 24964 ^ -31714 ^ 16817 ^ -23494, 31269 ^ -6172 ^ 29234 ^ -4126, 9270 ^ -31213 ^ 24362 ^ -528));
      0ez.initStencilToWrite();
      0ez.readStencilBuffer(8662 ^ -21179 ^ 5736 ^ -25862);
      0ew.drawBlurredShadow(LnY4Ge9RXW() + Float.intBitsToFloat('욒' ^ 110972 ^ '﹦' ^ 639293505 ^ '섟' ^ '뻞' ^ 5988 ^ 1697724780), CWLLsdnK23(), lkt2yxF52S() - Float.intBitsToFloat(13048 ^ 241682 ^ 249892 ^ 1902484707 ^ 252246 ^ 5018 ^ 246599 ^ 843433894), 9uDv29IOb4(), 15878 ^ -31734 ^ 12821 ^ -30692, new Color(3615 ^ -31152 ^ 32250 ^ -2742, 8020 ^ -18424 ^ 13966 ^ -28206, 27049 ^ -10862 ^ 24708 ^ -9025, 25894 ^ -7827 ^ 3411 ^ -30233));
      0ew.drawBlurredShadow(LUD6q8OMQj() + Float.intBitsToFloat(121646 ^ 105089 ^ 1487 ^ 867081731 ^ 249726 ^ 251704 ^ 18059 ^ 1887536302), dOdm7LrToQ(), Float.intBitsToFloat('\udc91' ^ 27922 ^ 'ﻄ' ^ -673117400 ^ 5509 ^ 28149 ^ 11650 ^ -396291683), kwvUc2FDFU(), 8481 ^ -8611 ^ 28673 ^ -28803, new Color(26533 ^ -13824 ^ 14871 ^ -26702, 110 ^ -10127 ^ 32050 ^ -23279, 11012 ^ -4212 ^ 30333 ^ -19958, 21427 ^ -15918 ^ 17979 ^ -11099));
      0ew.drawBlurredShadow(4RJz63IOtT() + Float.intBitsToFloat(3052 ^ 'ꑪ' ^ 313 ^ 662716303 ^ 24005 ^ '\udb53' ^ 24528 ^ 1703430262), CgOSNGDLND() + Float.intBitsToFloat(21720 ^ 4635 ^ 17456 ^ -1961810816 ^ '떧' ^ 16592 ^ '줍' ^ -929687031), Float.intBitsToFloat(4810 ^ '遥' ^ 19589 ^ 204498482 ^ 18256 ^ 113094 ^ 127246 ^ 1329375104), Float.intBitsToFloat(22450 ^ 234760 ^ 23842 ^ 257006822 ^ 18308 ^ 254642 ^ 16416 ^ 819062376), 12822 ^ -15314 ^ 2743 ^ -881, new Color(13281 ^ -15723 ^ 17242 ^ -19775, 23047 ^ -6199 ^ 23782 ^ -7904, 31880 ^ -18148 ^ 25316 ^ -22641, 24240 ^ 6466 ^ 18257 ^ 92));
      0ez.initStencilToWrite();
      0ew.drawFCircle(M7w7Sxy2mD() + Float.intBitsToFloat(22384 ^ 102661 ^ 129263 ^ 1554553196 ^ 7513 ^ 107082 ^ 114663 ^ 489727746), DQ2Qto4dOc() + Float.intBitsToFloat(232117 ^ '蚛' ^ 251525 ^ 165761960 ^ 31026 ^ 17974 ^ 13801 ^ 1245873134), Float.intBitsToFloat(18647 ^ 18235 ^ 2445 ^ 1137482179 ^ 23858 ^ '킪' ^ 8503 ^ 1137455885), Float.intBitsToFloat(21655 ^ 111085 ^ 2658 ^ -734070506 ^ 24388 ^ 90473 ^ '캄' ^ -1752504665), Float.intBitsToFloat(28913 ^ 225721 ^ '\uf6a5' ^ 491649648 ^ 12362 ^ 214494 ^ '뿶' ^ 1549649919), (boolean)(25530 ^ -28210 ^ 7788 ^ -5095), new Color(10529 ^ -30540 ^ 22201 ^ -2250, 25373 ^ -22532 ^ 22075 ^ -28022, 10618 ^ -22483 ^ 29576 ^ -3428, 8946 ^ -23456 ^ 24331 ^ -9882));
      0ez.readStencilBuffer(23546 ^ -30402 ^ 32171 ^ -20626);
      0ew.drawCircle(zzqOofrJLG() + Float.intBitsToFloat('궟' ^ 16774804 ^ 'ꏶ' ^ 1803538228 ^ '뚮' ^ 25307 ^ '\ue716' ^ 699139242), UscCvliqmr() + Float.intBitsToFloat(12852 ^ 85841 ^ '\uda66' ^ -638391430 ^ 'ﬁ' ^ 110068 ^ '쬒' ^ -1703226978), Float.intBitsToFloat(27193 ^ 102448 ^ 4965 ^ -1730227881 ^ '镂' ^ 108914 ^ '쬚' ^ -1730228463), Float.intBitsToFloat('ꏠ' ^ 77251 ^ 3879 ^ -2087277719 ^ 20794 ^ 92031 ^ '\udedb' ^ -1071463693), Float.intBitsToFloat(115301 ^ 109042 ^ 4639 ^ 1512217338 ^ 109049 ^ 103830 ^ 5150 ^ 1512229635), (new Color(3918 ^ -4463 ^ 25852 ^ -31453, 10882 ^ -26361 ^ 16299 ^ -29650, 15697 ^ -6606 ^ 15149 ^ -8114, 22471 ^ -14036 ^ 25699 ^ -1417)).getRGB(), 11865 ^ -14090 ^ 7048 ^ -731);
      0ez.initStencilToWrite();
      0ex.drawRound(hwdtVXVqEi(), bc1cX8sJwI() + Float.intBitsToFloat(4096 ^ 234804 ^ 246279 ^ 706316676 ^ 257736 ^ 215951 ^ 955 ^ 1780049995), 2bnQ1MQNLa(), giNoqljOt4(), Float.intBitsToFloat(24734 ^ 7400 ^ 28581 ^ -1250416793 ^ 3330 ^ 31644 ^ 24695 ^ -1250418083), (boolean)(6252 ^ -16399 ^ 30097 ^ -11764), new Color(2723 ^ -15009 ^ 9679 ^ -5581, 31838 ^ -17434 ^ 19399 ^ -29569, 13938 ^ -9376 ^ 21007 ^ -16611, 7987 ^ -21329 ^ 13521 ^ -30798));
      0ez.readStencilBuffer(19087 ^ -19681 ^ 30531 ^ -28974);
      0ex.drawRound(SywOxZrNnz() + Float.intBitsToFloat(28134 ^ '\ue3c9' ^ 26208 ^ 726948852 ^ 106899 ^ '霾' ^ 125169 ^ 349466087), 1T9DQ1S67l() + Float.intBitsToFloat(31432 ^ 113434 ^ 3600 ^ 640992164 ^ 19620 ^ 110956 ^ 15510 ^ 1714736440), Float.intBitsToFloat(16251 ^ '\ue3b2' ^ '뒄' ^ 93139635 ^ 14300 ^ '랚' ^ '\uf79d' ^ 1145384229), yzZwocTFJD(), Float.intBitsToFloat(19198 ^ 19297 ^ 8368 ^ -392687107 ^ '郓' ^ 1010205 ^ 173 ^ -1466447183), new Color(11303 ^ -6307 ^ 12102 ^ -7131, 15479 ^ -26794 ^ 5543 ^ -16737, 15858 ^ -23164 ^ 17716 ^ -8869, 24010 ^ -27427 ^ 10632 ^ -8096));
      0ex.drawRound(bLzmXBDb64() + Float.intBitsToFloat(27387 ^ 103506 ^ 28376 ^ -712992737 ^ 29637 ^ 18899 ^ 12555 ^ -1801387149), 0TD6hg49Sn() + Float.intBitsToFloat(126684 ^ 123818 ^ 17058 ^ -1796165343 ^ 128980 ^ 110590 ^ 19398 ^ -722408167), Float.intBitsToFloat('ퟡ' ^ 89424 ^ 2915 ^ 238605643 ^ 15224 ^ '融' ^ '\udc73' ^ 1285108767), 4KttYXKCWo(), Float.intBitsToFloat(29224 ^ 111522 ^ 10795 ^ -1659187636 ^ 29767 ^ 26035 ^ 11191 ^ -1659166802), new Color(10025 ^ -24107 ^ 22130 ^ -12137, 11784 ^ -28509 ^ 32183 ^ -15611, 28496 ^ -9286 ^ 2920 ^ -16485, 22979 ^ -29385 ^ 148 ^ -11105));
      0ew.drawImage(new ResourceLocation(ddMGSqgMhv("ѸѳѹѡѷѤѳйѿѻѷѱѳѥйѸѳѹѡѷѤѳиѦѸѱ")), ne262nsGr8() + Float.intBitsToFloat('\ue1cc' ^ 218464 ^ 14987 ^ 1015144344 ^ 'ﳟ' ^ '訩' ^ 4438 ^ 2107782687), tJFY37VrD1() + Float.intBitsToFloat(102846 ^ 75947 ^ 319 ^ -996382982 ^ 120879 ^ 13367 ^ 123021 ^ -2051222971), Float.intBitsToFloat('붍' ^ 9301 ^ '냎' ^ -716813065 ^ '뱊' ^ 'ꊠ' ^ 20577 ^ -1796854934), Float.intBitsToFloat('踫' ^ 247252 ^ '\uf4e0' ^ 1688577268 ^ 19009 ^ 242286 ^ 4903 ^ 621146339), new Color(8101 ^ -18030 ^ 1861 ^ -24179, 3861 ^ -7181 ^ 7254 ^ -4017, 22416 ^ -9707 ^ 4360 ^ -25486));
      0eg var10000 = IZ4PeFoTqj();
      String var10001 = ddMGSqgMhv("јѳѹсѷѤѳ");
      double var10002 = (double)(sD1cRfcS9q() + Float.intBitsToFloat('襄' ^ '鐨' ^ 18628 ^ -1023723113 ^ '켌' ^ '\udfd1' ^ 24649 ^ -2132075349));
      double var10003 = (double)(99SbIDV2g4() + Float.intBitsToFloat(2087 ^ '鸼' ^ '\ue827' ^ -1735455866 ^ '쨀' ^ 96437 ^ 12760 ^ -639699241));
      int[] var10004 = new int[26043 ^ -31836 ^ 23210 ^ -17231];
      var10004[2523 ^ -25156 ^ 10228 ^ -19565] = getC(21864 ^ -11931 ^ 27563 ^ -4186).getRGB();
      var10004[19670 ^ -17853 ^ 22269 ^ -24471] = getC(1356 ^ -24050 ^ 24112 ^ -1656).getRGB();
      var10004[10566 ^ -16260 ^ 14232 ^ -8544] = getC(17285 ^ -943 ^ 8619 ^ -25455).getRGB();
      var10004[14959 ^ -25857 ^ 8379 ^ -32728] = getC(3394 ^ -10533 ^ 13144 ^ -5335).getRGB();
      var10000.drawGradientString(var10001, var10002, var10003, var10004, (boolean)(9530 ^ -5828 ^ 9768 ^ -5585));
      1H6UeoJ0X0().drawString(ddMGSqgMhv("Ѡ") + dOuIvZsKWg() + ddMGSqgMhv("жѤѳѵѹѲѳ"), YuPF4dnimh() + Float.intBitsToFloat('끊' ^ 20766 ^ '쫦' ^ 503845203 ^ 1678 ^ 1029119 ^ 22539 ^ 1545066395), aBhJAW2gOW() + Float.intBitsToFloat(105364 ^ 123437 ^ 10115 ^ -275863773 ^ 115917 ^ 122397 ^ 15519 ^ -1372135594), (new Color(28983 ^ -10381 ^ 16758 ^ -6195, 32625 ^ -11410 ^ 21502 ^ -226, 19294 ^ -22030 ^ 15417 ^ -8598, 17268 ^ -8654 ^ 19335 ^ -10690)).getRGB());
      int offset1 = 9193 ^ -29623 ^ 9482 ^ -30038;
      0bV[] var5 = 0bV.values();
      int var6 = var5.length;

      for(int var7 = 3186 ^ -2368 ^ 3387 ^ -2167; var7 < var6; ++var7) {
         0bV category = var5[var7];
         if (Dad8W4FF2n() == category) {
            var10000 = WHuzONo4Yr();
            var10001 = category.name();
            var10002 = (double)(YEwj3U6Bfz() + Float.intBitsToFloat(10418 ^ 1038455 ^ 1046772 ^ -1752705745 ^ 12744 ^ 471426 ^ 505407 ^ -712283285));
            var10003 = (double)(QAmSIHgDtg() + Float.intBitsToFloat(110455 ^ 30504 ^ 127943 ^ 750555878 ^ 32110 ^ 18201 ^ 432 ^ 1849328313) + (float)offset1);
            var10004 = new int[24779 ^ -415 ^ 18151 ^ -10167];
            var10004[17263 ^ -1782 ^ 27514 ^ -12001] = getC(29336 ^ -4811 ^ 15983 ^ -24126).getRGB();
            var10004[5524 ^ -616 ^ 32515 ^ -26866] = getC(1142 ^ -25465 ^ 21042 ^ -13767).getRGB();
            var10004[435 ^ -11091 ^ 25117 ^ -18687] = getC(5601 ^ -15306 ^ 2439 ^ -9538).getRGB();
            var10004[15670 ^ -5746 ^ 7963 ^ -13408] = getC(16145 ^ -11024 ^ 7788 ^ -2459).getRGB();
            var10000.drawGradientString(var10001, var10002, var10003, var10004, (boolean)(31677 ^ -3424 ^ 11159 ^ -23925));
         } else {
            9ulQbyArwS().drawString(category.name(), 33N3ioeLNd() + Float.intBitsToFloat(8873 ^ 237551 ^ 257398 ^ -1981443571 ^ 4103 ^ 261882 ^ 257296 ^ -873872944), IvLTwqCein() + Float.intBitsToFloat(115254 ^ 104171 ^ 11575 ^ -2044296489 ^ 114899 ^ 107353 ^ 8231 ^ -996099952) + (float)offset1, (new Color(22792 ^ -5914 ^ 25888 ^ -11215, 17558 ^ -2395 ^ 4234 ^ -23994, 15929 ^ -4242 ^ 23978 ^ -29694, 10206 ^ -8092 ^ 14668 ^ -503)).getRGB());
         }

         7gqQyi8Md4().drawString(category.getIcon(), gVeR6gz1s4() + Float.intBitsToFloat(8221 ^ '뷍' ^ '쑫' ^ -152629742 ^ 9544 ^ '駪' ^ '킪' ^ -1215874143), PN23FQH2aU() + Float.intBitsToFloat('ꪭ' ^ 'ꀛ' ^ 14596 ^ 1490161297 ^ 29493 ^ 32405 ^ 7779 ^ 441724640) + (float)offset1, category.getColor().getRGB());
         offset1 += category.getOffset();
      }

      1naqJqhWA9(this, (float)0dJ.Interpolate((double)GySQaeiJRl(this), Double.longBitsToDouble(-7412335309038342677L ^ -7412335309038342677L), Double.longBitsToDouble(-4595907433815670434L ^ -35581211450080930L)));
      this.renderModules(mouseX, mouseY, LgOjT1GFe1(), ZHMFffLOkA());
      if (7YVLPTieHY() == Nl2v6Aa6c4()) {
         0ex.drawRound(2gBQgdA94L() + Float.intBitsToFloat(1437 ^ 'ꯧ' ^ 2785 ^ 734929929 ^ 20561 ^ 'ꉿ' ^ 17350 ^ 1762799994), eVTNZ8byA9() + Float.intBitsToFloat('ﮠ' ^ '\ue268' ^ 27269 ^ 1627849050 ^ '\ued9a' ^ '\ua9ce' ^ 775 ^ 539440452), Float.intBitsToFloat(10955 ^ '\ue378' ^ 29584 ^ -953865638 ^ 27610 ^ '廓' ^ 26721 ^ -2069403191), Float.intBitsToFloat(10439 ^ '퀊' ^ '븕' ^ -1021632865 ^ '\ued5d' ^ '\ueaa3' ^ 30842 ^ -2139281469), Float.intBitsToFloat(32721 ^ 2091875 ^ 6556 ^ -1481663379 ^ 3607 ^ 2041453 ^ '韰' ^ -1481658679), new Color(3270 ^ -28958 ^ 5995 ^ -27313, 8742 ^ -28949 ^ 22613 ^ -2920, 19086 ^ -8619 ^ 1803 ^ -27696, 13677 ^ -23081 ^ 24086 ^ -12717));
         0ex.drawRound(rJ2vyABBeL() + Float.intBitsToFloat(246598 ^ '钸' ^ 247532 ^ -928941063 ^ 261494 ^ 245984 ^ 21078 ^ -1971485397), 1bOriXi9bq() + Float.intBitsToFloat(11521 ^ 95036 ^ '\uebc9' ^ -669322028 ^ '\uf6ac' ^ 21826 ^ '퉴' ^ -1687473990), Float.intBitsToFloat(13303 ^ '鶑' ^ 879 ^ -209027770 ^ 116190 ^ 121717 ^ 23842 ^ -1341615162), Float.intBitsToFloat(253705 ^ 246309 ^ 1767 ^ 1129967246 ^ 4005 ^ 233425 ^ 255324 ^ 36278381), Float.intBitsToFloat(256444 ^ 230194 ^ 7658 ^ 540120254 ^ 3074 ^ 32764 ^ 2017 ^ 540121029), new Color(17422 ^ -26981 ^ 4448 ^ -15380, 15612 ^ -30197 ^ 9808 ^ -28482, 17838 ^ -20462 ^ 9499 ^ -12098, 1275 ^ -24755 ^ 5319 ^ -28786));
         1o8ynTFedE().drawString((GSnfB4z4tV(this).length() > (8545 ^ -21621 ^ 22580 ^ -11530) ? WG3IzXQyEr(this).substring(iGGRQBr7Mq(this).length() - (15085 ^ -18392 ^ 6164 ^ -25863)) : 90AyXty6M6(this)) + (cFFRRNdrwg(this) ? (4GghNbqWaK(this) ? ddMGSqgMhv("щ") : ddMGSqgMhv("")) : ddMGSqgMhv("")), ooVTwAlKOu() + Float.intBitsToFloat(26656 ^ 223395 ^ 259460 ^ 64055303 ^ 9882 ^ '謹' ^ 1715 ^ 1094141846), JymL9V8OHn() + Float.intBitsToFloat(10745 ^ 481386 ^ '\uf66f' ^ 212589965 ^ '衰' ^ '텿' ^ 6724 ^ 1339627834), (new Color(32372 ^ -15081 ^ 31387 ^ -16121, 15592 ^ -8863 ^ 27790 ^ -29192, 818 ^ -5566 ^ 4353 ^ -1906, 16539 ^ 1740 ^ 17950 ^ 182)).getRGB());
         int offset = 29935 ^ -1804 ^ 8549 ^ -21122;
         if (0dL.getResponse() != null) {
            JSONObject response = 0dL.getResponse();
            if (response.getJSONArray(ddMGSqgMhv("ѻѳѥѥѷѱѳѥ")) != null) {
               JSONArray messages = response.getJSONArray(ddMGSqgMhv("ѻѳѥѥѷѱѳѥ"));

               for(int index = 18630 ^ -32491 ^ 9790 ^ -4115; index < messages.length(); ++index) {
                  JSONObject message = messages.getJSONObject(index);
                  OJ8ABWgMMB().drawString(message.getString(ddMGSqgMhv("ѸѿѵѽѸѷѻѳ")) + (message.getString(ddMGSqgMhv("ѸѿѵѽѸѷѻѳ")).isEmpty() ? ddMGSqgMhv("") : ddMGSqgMhv("Ьж")) + message.getString(ddMGSqgMhv("ѻѳѥѥѷѱѳ")), jovxejwoVT() + Float.intBitsToFloat('꿚' ^ 94848 ^ 19924 ^ -564070198 ^ 'ﺥ' ^ '틹' ^ 30718 ^ -1668860954), Y2YGnD69zd() + Float.intBitsToFloat(3247 ^ '꽊' ^ 29828 ^ -709208940 ^ 2510 ^ '홉' ^ 30327 ^ -1798690299) + (float)offset, (new Color(31579 ^ -28094 ^ 1571 ^ -4155, 11513 ^ -14470 ^ 32713 ^ -27467, 24371 ^ -6797 ^ 11578 ^ -26747, 1177 ^ -14537 ^ 11342 ^ -4321)).getRGB());
                  offset += 8;
               }
            }
         } else {
            t4vVbgol29().drawString(ddMGSqgMhv("7#V$#V&жјѳѹсѷѤѳж+#\"(WTU)+]иж\t()V('U/T#ж)(! #"), Yw3Fvrtrlw() + Float.intBitsToFloat('\uf486' ^ '表' ^ 5642 ^ 1634732898 ^ '똘' ^ '\uab00' ^ 19411 ^ 597047117), Od0NNTPAIW() + Float.intBitsToFloat(26183 ^ 481237 ^ '\ue28f' ^ -1436300107 ^ 19448 ^ 508492 ^ 24849 ^ -351013107) + (float)offset, (new Color(26328 ^ -3770 ^ 11735 ^ -17738, 1441 ^ -18378 ^ 16997 ^ -39, 26914 ^ -8233 ^ 19834 ^ -1116, 10160 ^ -8486 ^ 23766 ^ -23229)).getRGB());
         }

         if (kipB0zYz5A(this).hasReached(Double.longBitsToDouble(1400567933324419709L ^ 6045749473980375677L))) {
            sbk99bfdX2(this).reset();
            iTEOtT1k7D(this, (boolean)(!W6TkFFq4UN(this) ? 30714 ^ -637 ^ 25796 ^ -4420 : 14035 ^ -21328 ^ 17024 ^ -10013));
         }
      }

      0ez.uninitStencilBuffer();
      if (JoCbA6DVod(this) && Mouse.isButtonDown(4457 ^ -13071 ^ 10156 ^ -1484)) {
         lU62c1QIak((float)mouseX + jjnYetxiaQ());
         6qyLiA2WIr((float)mouseY + nBJ10tjZbH());
      } else {
         b1XD6u9K41(this, (boolean)(23659 ^ -18633 ^ 9788 ^ -12960));
      }

      ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
      if (vF9KeEYYgC(jxVQnQdYA1()) && !Ff1lhftSDq(this).isEmpty()) {
         dXDWetBSqO(this).forEach((snow) -> {
            snow.update(sr);
         });
      }

   }

   private static float IdMLkVb1PS() {
      return height;
   }

   private static float pW9gGIenNJ(0br var0) {
      return var0.animScroll;
   }

   private static boolean YsSYeynhM6(0bA var0) {
      return var0.typing;
   }

   private static 0bV yilUIAnlbe() {
      return 0bV.Chat;
   }

   private static float YvusvsVxNI() {
      return x;
   }

   public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
      if (this.isHovered(mouseX, mouseY, (double)AJAIQinFvq(), (double)CnDGMGP4Bb(), (double)LvNUjFjVbD(), (double)lrG3TvqlCF())) {
         if (this.isHovered(mouseX, mouseY, (double)Dw0MibJ6bm(), (double)Xp8dnJQ1SI(), Double.longBitsToDouble(-5040431432790280237L ^ -398135010645608493L), Double.longBitsToDouble(5880491191760849418L ^ 1270775498170436106L)) && mouseButton == 0) {
            CWxMoSpZg1(this, (boolean)(22965 ^ -20718 ^ 26967 ^ -24591));
            YHN6yqbEqA(u7BqAiRI7u() - (float)mouseX);
            7j7jevIc2Y(n2AINFKF5g() - (float)mouseY);
         }

         if (this.isHovered(mouseX, mouseY, (double)jNd91tFl6R(), (double)pjT4oyaE9W(), Double.longBitsToDouble(-8991540994228760899L ^ -4376688382313378115L), Double.longBitsToDouble(-6086329148891068439L ^ -1463665606371964951L)) && mouseButton == 0) {
            rUbKdW7oyT(this, (boolean)(14841 ^ -16631 ^ 13236 ^ -19131));
            cUdjXXhST7(jGBdgQKWzs() - (float)mouseX);
            LyMpjtnelA(Hir4xbD2eM() - (float)mouseY);
         }

         tYn86jAjDs(this, (boolean)(this.isHovered(mouseX, mouseY, (double)(Qm3412j13J() + Float.intBitsToFloat(123857 ^ 21064 ^ 123946 ^ -800547655 ^ 117160 ^ 24096 ^ 127723 ^ -1835750295)), (double)(9jtzreSgXq() + Float.intBitsToFloat(2091733 ^ 2093946 ^ 7546 ^ -1578123257 ^ 4162928 ^ '辵' ^ 4171082 ^ -492868771)), Double.longBitsToDouble(7352021946359000629L ^ 2771931494067383861L), Double.longBitsToDouble(-5353098247603779576L ^ -748730578570868728L)) && mouseButton == 0 ? 9476 ^ -5979 ^ 17351 ^ -29081 : 7685 ^ -168 ^ 5218 ^ -2753));
         ArrayList<0cB> allModules = iVLtUwO9Y4(0bK.getInstance()).getModulesForCategory(FgWytSv4j1());
         int modulesSize = allModules.size();
         ArrayList<ArrayList<0cB>> modulesl = new ArrayList();
         modulesl.add(new ArrayList(9tyHgoPkpJ(0bK.getInstance()).getModulesForCategory(dby9stjLtW()).subList(16933 ^ -25588 ^ 10849 ^ -3000, modulesSize / (5202 ^ -19796 ^ 8184 ^ -18172))));
         modulesl.add(new ArrayList(qCuoH99aPj(0bK.getInstance()).getModulesForCategory(6WOnmgWjoj()).subList(modulesSize / (30262 ^ -12328 ^ 27371 ^ -11513), modulesSize)));
         int modulesline = 1791 ^ -4328 ^ 20261 ^ -22846;
         int offset1 = 70 ^ -4055 ^ 9533 ^ -10926;
         Iterator var10 = modulesl.iterator();

         int offset;
         while(var10.hasNext()) {
            ArrayList<0cB> modules = (ArrayList)var10.next();
            ++modulesline;
            if (modulesline == (3334 ^ -19441 ^ 4637 ^ -21738)) {
               offset1 = 1276 ^ -3137 ^ 4261 ^ -6242;
            }

            offset = 14866 ^ -20775 ^ 5814 ^ -32131;

            label179:
            for(Iterator var12 = modules.iterator(); var12.hasNext(); offset += 50) {
               0cB module = (0cB)var12.next();
               if (this.isHovered(mouseX, mouseY, (double)(Tbmi4I2i6r() + Float.intBitsToFloat(126004 ^ 104482 ^ 5102 ^ -464616940 ^ 123277 ^ 127817 ^ 4836 ^ -1499666996) + (float)offset1), (double)(YhjAGcl9r3() + Float.intBitsToFloat(108892 ^ 130649 ^ 6818 ^ -218451203 ^ 130317 ^ 101215 ^ 6085 ^ -1289052467) + (float)offset + rVzV9hIoyl(this)), Double.longBitsToDouble(-7527205926862925290L ^ -2894338916438060522L), Double.longBitsToDouble(3514216765740337078L ^ 8141383907886811062L))) {
                  if (mouseButton == 0) {
                     module.toggle();
                  } else if (mouseButton == (8737 ^ -7698 ^ 29457 ^ -20257) && !module.getModuleCategory().equals(VykYAQBzyG())) {
                     1YWih83QVp(module, (boolean)(!qrzv1iW3U7(module) ? 19259 ^ -8134 ^ 18471 ^ -7385 : 14446 ^ -5109 ^ 28967 ^ -23230));
                  }
               }

               if (eGQSFwWXiF(module)) {
                  Iterator var14 = module.getSetting().iterator();

                  while(true) {
                     while(true) {
                        0bC setting;
                        do {
                           if (!var14.hasNext()) {
                              continue label179;
                           }

                           setting = (0bC)var14.next();
                        } while(!setting.isVisible());

                        if (setting instanceof 0bv) {
                           0bv booleanSetting = (0bv)setting;
                           if (this.isHovered(mouseX, mouseY, (double)(tLr7OH7NJ8() + Float.intBitsToFloat(15758 ^ 1021252 ^ 5321 ^ -519593570 ^ 17465 ^ 22738 ^ 53 ^ -1572273853) + (float)offset1), (double)(nD3YiIOVod() + Float.intBitsToFloat(237513 ^ '霪' ^ 254379 ^ 1089303662 ^ 15262 ^ '虷' ^ 443 ^ 42541428) + (float)offset + ne6Oyo5yIV(this)), Double.longBitsToDouble(6789825121403755L ^ 4623542393129583467L), Double.longBitsToDouble(4758173267170684872L ^ 156620347904880584L))) {
                              CcFNu7j0XG(booleanSetting, (boolean)(!jFnqd24DbE(booleanSetting) ? 19566 ^ -22815 ^ 14993 ^ -12257 : 23679 ^ -10070 ^ 30461 ^ -3544));
                           }

                           offset += 14;
                        } else if (setting instanceof 0bz) {
                           0bz sliderSetting = (0bz)setting;
                           if (this.isHovered(mouseX, mouseY, (double)(nbsu4hFwBl() + Float.intBitsToFloat(3251 ^ 32128 ^ 16883 ^ 156042994 ^ '뛟' ^ 4608 ^ '렪' ^ 1267804871) + (float)offset1), (double)(LJBngnE7W9() + Float.intBitsToFloat(126293 ^ 27946 ^ 117792 ^ 1746436393 ^ 116512 ^ 7336 ^ 122120 ^ 710200310) + (float)offset + d8leRhydPn(this) + Float.intBitsToFloat(12283 ^ 4144665 ^ '뗩' ^ -627052384 ^ '\uf38e' ^ 4170983 ^ '\udb8a' ^ -1679828920)), Double.longBitsToDouble(-8735518547958767173L ^ -4136569272227536453L), Double.longBitsToDouble(-5569989567308594033L ^ -969562547949632369L))) {
                              f1ydg12ml7(sliderSetting, (boolean)(14621 ^ -22103 ^ 25235 ^ -3546));
                           }

                           offset += 28;
                        } else if (setting instanceof 0bw) {
                           offset += 39;
                        } else if (setting instanceof 0bB) {
                           0bB themeSetting = (0bB)setting;
                           if (this.isHovered(mouseX, mouseY, (double)(janTrrEaO6() + Float.intBitsToFloat(1047264 ^ 1024690 ^ 7909 ^ -281573327 ^ 16185 ^ 19216 ^ 3563 ^ -1377454268) + (float)offset1), (double)(gkv8FLTdNm() + Float.intBitsToFloat(126664 ^ 107316 ^ 12493 ^ -539716541 ^ 111024 ^ 116037 ^ 16938 ^ -1643847763) + (float)offset + oP6Acrwfee(this)), Double.longBitsToDouble(7105428915083454437L ^ 2506057426887157733L), Double.longBitsToDouble(-7069060581438367103L ^ -2470814993148913023L)) && mouseButton == 0) {
                              hMsE5fzQDb(themeSetting.getTheme());
                           }

                           offset += 30;
                        } else if (setting instanceof 0bA) {
                           0bA textSetting = (0bA)setting;
                           if (this.isHovered(mouseX, mouseY, (double)(TlJWSYOGiz() + Float.intBitsToFloat(32329 ^ '\ue78c' ^ 12496 ^ -289004348 ^ 11037 ^ '\udf5b' ^ 1471 ^ -1379238872) + (float)offset1), (double)(9S1tSiruwA() + Float.intBitsToFloat(13726 ^ 206983 ^ '렄' ^ 155576974 ^ '\uf2c9' ^ 231498 ^ '\ue053' ^ 1261819203) + (float)offset + e8G1ljAi2Q(this)), Double.longBitsToDouble(-8216577968789383270L ^ -3626072942359472230L), Double.longBitsToDouble(1150895927594376975L ^ 5751885896906759951L))) {
                              GuullTbGxM(textSetting, (boolean)(!gtkbOIuWNC(textSetting) ? 14106 ^ -10703 ^ 8926 ^ -15372 : 15443 ^ -9181 ^ 27970 ^ -29390));
                           } else {
                              aQz2SevFWI(textSetting, (boolean)(14521 ^ -17063 ^ 29002 ^ -2902));
                           }

                           offset += 15;
                        } else if (setting instanceof 0by) {
                           0by modeSetting = (0by)setting;
                           if (this.isHovered(mouseX, mouseY, (double)(YvusvsVxNI() + Float.intBitsToFloat(23458 ^ '읶' ^ 5129 ^ 267455982 ^ 16413 ^ '쒢' ^ 21952 ^ 1289835596) - Float.intBitsToFloat(1792 ^ 230521 ^ 20397 ^ -91107380 ^ 30666 ^ 240451 ^ 20978 ^ -1154380189) + (float)offset1 + Float.intBitsToFloat(8905 ^ '躼' ^ 8175 ^ -93310094 ^ 9243 ^ '\udb63' ^ 24922 ^ -1149231414)), (double)(OViBlYyT4o() + Float.intBitsToFloat('ꫳ' ^ 102905 ^ '\uf70a' ^ -1484197599 ^ 29576 ^ 28593 ^ 2553 ^ -440063775) + Float.intBitsToFloat(2084114 ^ 2069252 ^ 3449 ^ -978341449 ^ 2088993 ^ 2039837 ^ 18478 ^ -2062587190) + Float.intBitsToFloat(3400 ^ 497995 ^ 26178 ^ 1576260901 ^ 4493 ^ 491869 ^ 30187 ^ 492028511) - Float.intBitsToFloat(109119 ^ 105904 ^ 31504 ^ 1331215006 ^ 107767 ^ 118669 ^ 18538 ^ 265868561) + (float)offset + MS2J9rHiHN(this)), Double.longBitsToDouble(-8016885708073424927L ^ -3389155615973529631L), Double.longBitsToDouble(-4182294180287613145L ^ -8800735598156056793L))) {
                              vEWGn9daAi(modeSetting, (boolean)(!jrabjbADyg(modeSetting) ? 32103 ^ -13078 ^ 705 ^ -19635 : 29745 ^ -11704 ^ 30302 ^ -12249));
                           }

                           if (7uutrlql8v(modeSetting)) {
                              for(int i = 25719 ^ -6219 ^ 15767 ^ -16811; i < KI7knwBZoE(modeSetting).size(); ++i) {
                                 if (this.isHovered(mouseX, mouseY, (double)(O12c7J1GS6() + Float.intBitsToFloat('\uf0ba' ^ 'ꉚ' ^ 25688 ^ 1137132950 ^ '\ua7cc' ^ 6910 ^ 'ꨱ' ^ 14115885) - Float.intBitsToFloat(8710 ^ 'ꆺ' ^ '\ue486' ^ 1188838736 ^ '馬' ^ 24802 ^ 'ꟾ' ^ 125568218) + (float)offset1 + Float.intBitsToFloat('\ue582' ^ 16334 ^ '\ud9da' ^ 663507453 ^ '영' ^ 463417 ^ 23806 ^ 1719392941)), (double)(bbpOVAOFrD() + Float.intBitsToFloat(342 ^ 114009 ^ 17073 ^ 1153887310 ^ 2548 ^ 111444 ^ 24148 ^ 105838084) + Float.intBitsToFloat(107001 ^ 109132 ^ 1811 ^ -70604960 ^ 20958 ^ '톔' ^ 5344 ^ -1150664852) + Float.intBitsToFloat(250343 ^ 22474 ^ 260019 ^ 1774560086 ^ 252064 ^ '跦' ^ 257541 ^ 694512011) - Float.intBitsToFloat(30692 ^ 19077 ^ 8470 ^ 1349780787 ^ 13825 ^ 22556 ^ 28458 ^ 290723955) + (float)offset + iS1uJn5IZJ(this) + (float)(i * (12666 ^ -28166 ^ 25575 ^ -15507))), Double.longBitsToDouble(-8521687078917377162L ^ -3893956986817481866L), Double.longBitsToDouble(1492481487467683248L ^ 6094034406733487536L))) {
                                    1d1iAazPqL(modeSetting, i);
                                 }
                              }
                           }

                           offset = (int)((double)offset + Double.longBitsToDouble(-5877793058797909526L ^ -1270047690044470806L) + (MNZsKh2Gh6(modeSetting) ? Double.longBitsToDouble(-4441533176256279396L ^ -9043536474883511558L) * (double)Tav4jh4DA4(modeSetting).size() + Double.longBitsToDouble(1092594302758998009L ^ 5696399021838487545L) : Double.longBitsToDouble(556273709924704790L ^ 556273709924704790L)));
                        } else if (9YOkXh2vrM(51TtBJr2uN()) && setting instanceof 0bx) {
                           offset += ((0bx)setting).getLines().length * (7964 ^ -3393 ^ 31668 ^ -27107);
                        }
                     }
                  }
               }
            }
         }

         offset = 8160 ^ -13785 ^ 12602 ^ -6915;
         0bV[] var18 = 0bV.values();
         int var19 = var18.length;

         for(int var20 = 29022 ^ -21703 ^ 7350 ^ -14639; var20 < var19; ++var20) {
            0bV category = var18[var20];
            if (this.isHovered(mouseX, mouseY, (double)(ExgNOVa9BY() - Float.intBitsToFloat(22857 ^ '숩' ^ 24121 ^ -1509394566 ^ 103381 ^ 94703 ^ 13812 ^ -1719114259)), (double)(Jh3L9FwnWY() + Float.intBitsToFloat(7778 ^ 19643 ^ 8289 ^ -913197935 ^ 256738 ^ 225957 ^ 3693 ^ -1961799677) + (float)offset), Double.longBitsToDouble(8314673839940388288L ^ 3690602922537731520L), Double.longBitsToDouble(7664206767810375544L ^ 3057305823987068792L)) && mouseButton == 0 && category != be9hdwVh6a()) {
               ddg2ZoLWJB(category);
               if (category == yilUIAnlbe()) {
                  0dL.runSync();
               }

               jfIoMhddil(this, Float.intBitsToFloat(16835 ^ '髥' ^ 6536 ^ -202543976 ^ 28865 ^ 482236 ^ 510805 ^ -1336717794));
               auhaTg6ge2(this, Float.intBitsToFloat(3454 ^ '黴' ^ '뿑' ^ 2105428158 ^ '\ue60d' ^ '\udf77' ^ 7016 ^ 2105427703));
               wLC137Eodv(this, Float.intBitsToFloat(495355 ^ 492040 ^ 21755 ^ 1078695606 ^ 522186 ^ 28540 ^ 511106 ^ 1078696586));
            }

            offset += category.getOffset();
         }

      }
   }

   private static 0bV _WOnmgWjoj/* $FF was: 6WOnmgWjoj*/() {
      return selectedCategory;
   }

   private static 0eg _H6UeoJ0X0/* $FF was: 1H6UeoJ0X0*/() {
      return 0eh.mnstb_14;
   }

   private static float jovxejwoVT() {
      return x;
   }

   private static ArrayList KI7knwBZoE(0by var0) {
      return var0.modes;
   }

   private static float Od0NNTPAIW() {
      return y;
   }

   private static void hMsE5fzQDb(0eA var0) {
      currentTheme = var0;
   }

   private static String pqIa1F2Hfc(0bA var0) {
      return var0.name;
   }

   private static 0bV Nl2v6Aa6c4() {
      return 0bV.Chat;
   }

   private static 0eg w814lorcKf() {
      return 0eh.mnstb_14;
   }

   private static boolean _GghNbqWaK/* $FF was: 4GghNbqWaK*/(0br var0) {
      return var0.cursor;
   }

   private static float CnDGMGP4Bb() {
      return y;
   }

   private static 0eg ANxTGRWT2b() {
      return 0eh.mnstb_16;
   }

   private static float _bnQ1MQNLa/* $FF was: 2bnQ1MQNLa*/() {
      return width;
   }

   private static String mVlyNotosd(0br var0) {
      return var0.currentMessage;
   }

   private static float QAmSIHgDtg() {
      return y;
   }

   private static 0bN iVLtUwO9Y4(0bK var0) {
      return var0.moduleManager;
   }

   private static float bLzmXBDb64() {
      return x;
   }

   private static 0eg zrWrwwKyxA() {
      return 0eh.mnstb_14;
   }

   private static void iTEOtT1k7D(0br var0, boolean var1) {
      var0.cursor = var1;
   }

   private static void vEWGn9daAi(0by var0, boolean var1) {
      var0.opened = var1;
   }

   private static float tLr7OH7NJ8() {
      return x;
   }

   private static ArrayList Tav4jh4DA4(0by var0) {
      return var0.modes;
   }

   private static boolean cFFRRNdrwg(0br var0) {
      return var0.chatTyping;
   }

   private static Color cIQRcUpJFi(0bB var0) {
      return var0.twoColor;
   }

   private static float nD3YiIOVod() {
      return y;
   }

   private static float lrG3TvqlCF() {
      return height;
   }

   private static float e8G1ljAi2Q(0br var0) {
      return var0.animScroll;
   }

   private static ArrayList Ff1lhftSDq(0br var0) {
      return var0.effectList;
   }

   private static float LJBngnE7W9() {
      return y;
   }

   private static Color ndrFYNFaw7(0bB var0) {
      return var0.oneColor;
   }

   private static float LUD6q8OMQj() {
      return x;
   }

   private static boolean MNZsKh2Gh6(0by var0) {
      return var0.opened;
   }

   private static float LgOjT1GFe1() {
      return x;
   }

   private static void _Eo9ghrqhO/* $FF was: 9Eo9ghrqhO*/(0bz var0, float var1) {
      var0.widthAnimated = var1;
   }

   private static void _YWih83QVp/* $FF was: 1YWih83QVp*/(0cB var0, boolean var1) {
      var0.opened = var1;
   }

   private static boolean Ri2Goyotjs(0br var0) {
      return var0.chatTyping;
   }

   private static float bc1cX8sJwI() {
      return y;
   }

   private static void GuullTbGxM(0bA var0, boolean var1) {
      var0.typing = var1;
   }

   private static 0bV dby9stjLtW() {
      return selectedCategory;
   }

   private static void _d1iAazPqL/* $FF was: 1d1iAazPqL*/(0by var0, int var1) {
      var0.index = var1;
   }

   private static 0eg ZBpKFpb5xt() {
      return 0eh.mnstb_14;
   }

   private static float I2RY6VQDly(0bz var0) {
      return var0.printAnimated;
   }

   private static float BbyFVrIb2V() {
      return height;
   }

   private static float Qm3412j13J() {
      return x;
   }

   private static void t9U5LStTod(0bA var0, String var1) {
      var0.text = var1;
   }

   private static 0bv jxVQnQdYA1() {
      return 0cg.snow;
   }

   private static String dOuIvZsKWg() {
      return 0bK.VERSION_TYPE;
   }

   private static String TbADS2nSWm(0br var0) {
      return var0.currentMessage;
   }

   private static float Q4FpMtD3va() {
      return width;
   }

   private static float CgOSNGDLND() {
      return y;
   }

   private static BufferedImage djbe2btm1v() {
      return colorpicker;
   }

   private static float Dw0MibJ6bm() {
      return x;
   }

   private static float Q16dbOsAJH() {
      return x;
   }

   private static 0eg _4YVtGPgaZ/* $FF was: 64YVtGPgaZ*/() {
      return 0eh.mnstb_14;
   }

   private static float ooVTwAlKOu() {
      return x;
   }

   private static void LyMpjtnelA(float var0) {
      prevY = var0;
   }

   private static 0bN qCuoH99aPj(0bK var0) {
      return var0.moduleManager;
   }

   static {
      selectedCategory = 0bV.Bots;
      x = Float.intBitsToFloat(110710 ^ 97428 ^ 21080 ^ -1079607482 ^ 4498 ^ 128192 ^ 117716 ^ -47919238);
      y = Float.intBitsToFloat(6160 ^ 24023 ^ 11660 ^ -146005959 ^ '\uf537' ^ '\uaaca' ^ 2745 ^ -1209262794);
      width = Float.intBitsToFloat(662 ^ '뿌' ^ 18813 ^ 266171500 ^ 5464 ^ '솶' ^ 19128 ^ 1281629725);
      height = Float.intBitsToFloat(11589 ^ '꠲' ^ 2786 ^ 2010533408 ^ '쬉' ^ 24471 ^ '\ude09' ^ 877792034);

      try {
         colorpicker = ImageIO.read(Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation(ddMGSqgMhv("ѸѳѹѡѷѤѳйѿѻѷѱѳѥйѵѹѺѹѤѦѿѵѽѳѤиѦѸѱ"))).getInputStream());
      } catch (IOException var1) {
         IOException e = var1;
         e.printStackTrace();
      }

   }

   private static float ExgNOVa9BY() {
      return x;
   }

   private static float ne262nsGr8() {
      return x;
   }

   private static void CWxMoSpZg1(0br var0, boolean var1) {
      var0.dragging = var1;
   }

   private static 0eg _vR2BibFOI/* $FF was: 9vR2BibFOI*/() {
      return 0eh.mnstb_14;
   }

   private static BufferedImage lQPO4b9IVM() {
      return colorpicker;
   }

   private static String GSnfB4z4tV(0br var0) {
      return var0.currentMessage;
   }

   private static 0eg IZ4PeFoTqj() {
      return 0eh.mnstb_14;
   }

   private static 0bV be9hdwVh6a() {
      return selectedCategory;
   }

   private static float tKw9wqo16T(0bz var0) {
      return var0.increment;
   }

   private static void _tWT8ayAq9/* $FF was: 7tWT8ayAq9*/(0br var0, String var1) {
      var0.currentMessage = var1;
   }

   private static 0bv jKLI22F6qD() {
      return 0cg.wiki;
   }

   private static float ZHMFffLOkA() {
      return y;
   }

   private static float nbsu4hFwBl() {
      return x;
   }

   private static float sD1cRfcS9q() {
      return x;
   }

   private static float LvNUjFjVbD() {
      return width;
   }

   private static float rJ2vyABBeL() {
      return x;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String ddMGSqgMhv(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 13073 ^ -22836 ^ 26183 ^ -3174; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 29881 ^ -10373 ^ 1427 ^ -23993));
      }

      return var1.toString();
   }

   private static float CWLLsdnK23() {
      return y;
   }

   private static 0eA PFIxLh0Kla() {
      return currentTheme;
   }

   private static void f1ydg12ml7(0bz var0, boolean var1) {
      var0.pressed = var1;
   }

   private static Color lkMOPu05b7(0bB var0) {
      return var0.oneColor;
   }

   private static void jfIoMhddil(0br var0, float var1) {
      var0.openedCategoryAnim = var1;
   }

   private static void DvynfvNVN1(0br var0, float var1) {
      var0.scroll = var1;
   }

   private static String _vQrNwSCTj/* $FF was: 8vQrNwSCTj*/(0bw var0) {
      return var0.name;
   }

   private static float jNd91tFl6R() {
      return x;
   }

   private static void WEOlQWFK6V(0bw var0, int var1) {
      var0.picker = var1;
   }

   private static float nBJ10tjZbH() {
      return prevY;
   }

   private static float oP6Acrwfee(0br var0) {
      return var0.animScroll;
   }

   private static void FJ4XuIjB1Y(0br var0, String var1) {
      var0.currentMessage = var1;
   }

   private static float giNoqljOt4() {
      return height;
   }

   private static float xXSr52BFDB() {
      return y;
   }

   public void keyTyped(char c, int key) throws IOException {
      super.keyTyped(c, key);
      if (Ri2Goyotjs(this)) {
         if (ChatAllowedCharacters.isAllowedCharacter(c)) {
            Yfdu04gfDj(this, 88glOYHWDE(this) + c);
         }

         if (key == (32411 ^ -31465 ^ 7427 ^ -6527) && mVlyNotosd(this).length() > 0) {
            7tWT8ayAq9(this, NwFejVPC1p(this).substring(106 ^ -8844 ^ 25348 ^ -16870, TbADS2nSWm(this).length() - (1751 ^ -8598 ^ 22370 ^ -28706)));
         }

         if (Keyboard.isKeyDown(27432 ^ -16891 ^ 27871 ^ -17937) && Keyboard.isKeyDown(15248 ^ -26369 ^ 26281 ^ -14871)) {
            FJ4XuIjB1Y(this, tAb91d7jLL(this) + getClipboardString());
         }

         if (key == (7173 ^ -32569 ^ 20921 ^ -12953) && !V4QqIo3WZT(this).equals(ddMGSqgMhv(""))) {
            0dL.sendMessage(S2WPCOZei4(this));
            ittwrTbia3(this, ddMGSqgMhv(""));
         }
      }

      Iterator var3 = ZOhOGfqNmy(0bK.getInstance()).getModulesForCategory(IEd91BDMt9()).iterator();

      label80:
      while(var3.hasNext()) {
         0cB module = (0cB)var3.next();
         Iterator var5 = module.getSetting().iterator();

         while(true) {
            0bA textSetting;
            do {
               0bC setting;
               do {
                  do {
                     if (!var5.hasNext()) {
                        continue label80;
                     }

                     setting = (0bC)var5.next();
                  } while(!setting.isVisible());
               } while(!(setting instanceof 0bA));

               textSetting = (0bA)setting;
               if (5lWJTBLJPJ(textSetting)) {
                  Keyboard.enableRepeatEvents((boolean)(32035 ^ -27800 ^ 23128 ^ -19438));
                  if (ChatAllowedCharacters.isAllowedCharacter(c)) {
                     sXeSFMhRke((0bA)setting, 27R16twVNa((0bA)setting) + c);
                  }

                  if (key == (6506 ^ -17640 ^ 26226 ^ -15346) && 4CDL1yaoBQ((0bA)setting).length() > 0) {
                     yMJyWDd3Do((0bA)setting, 0IdTJHITiM((0bA)setting).substring(12831 ^ -14983 ^ 17604 ^ -19550, ijkcbJQnpp((0bA)setting).length() - (8241 ^ -6829 ^ 26063 ^ -24404)));
                  }

                  if (Keyboard.isKeyDown(11728 ^ -17619 ^ 21307 ^ -14885) && Keyboard.isKeyDown(12254 ^ -18925 ^ 9125 ^ -17849)) {
                     t9U5LStTod((0bA)setting, bSrSOMV2Ab((0bA)setting) + getClipboardString());
                  }
               }
            } while(key != (20499 ^ -16127 ^ 22084 ^ -14505) && key != (8924 ^ -14874 ^ 16073 ^ -9745));

            V2nY6Gh2NQ(textSetting, (boolean)(31373 ^ -14864 ^ 24752 ^ -8243));
         }
      }

   }

   private static float YEwj3U6Bfz() {
      return x;
   }

   private static ArrayList s7Kv5nt5j7(0by var0) {
      return var0.modes;
   }

   private static float AJAIQinFvq() {
      return x;
   }

   private static String TwHPU1Z1og(0bz var0) {
      return var0.name;
   }

   private static boolean _YOkXh2vrM/* $FF was: 9YOkXh2vrM*/(0bv var0) {
      return var0.value;
   }

   private static void DtaGWjd2P9(0bz var0, int var1) {
      var0.alphaText = var1;
   }

   private static String NwFejVPC1p(0br var0) {
      return var0.currentMessage;
   }

   private static float GySQaeiJRl(0br var0) {
      return var0.openedCategoryAnim;
   }

   private static float TlJWSYOGiz() {
      return x;
   }

   private static boolean JoCbA6DVod(0br var0) {
      return var0.dragging;
   }

   private static void mdGFwEWL4g(0br var0, int var1) {
      var0.mousey = var1;
   }

   private static float _3N3ioeLNd/* $FF was: 33N3ioeLNd*/() {
      return x;
   }

   private static float _bOriXi9bq/* $FF was: 1bOriXi9bq*/() {
      return y;
   }

   private static boolean zbOdDj57yv(0bz var0) {
      return var0.pressed;
   }

   private static void YHN6yqbEqA(float var0) {
      prevX = var0;
   }

   private static void SbAU419pFO(0br var0, int var1) {
      var0.mousex = var1;
   }

   private static int wtEKeqTj0Y(0br var0) {
      return var0.mousex;
   }

   private static int bdutXUeFJB(0by var0) {
      return var0.index;
   }

   private static 0eg IjElgQfJir() {
      return 0eh.mnstb_14;
   }

   private static 0bV Dad8W4FF2n() {
      return selectedCategory;
   }

   private static float Qh4V7YJj6a(0br var0) {
      return var0.animScroll;
   }

   private static void au5SdYe7rx(0bz var0, float var1) {
      var0.printAnimated = var1;
   }

   private static boolean FIWlcjo5EG(0cB var0) {
      return var0.opened;
   }

   private static float gkv8FLTdNm() {
      return y;
   }

   private static 0bV BySXWtAQA9() {
      return selectedCategory;
   }

   private static float _7YrhRJe8m/* $FF was: 27YrhRJe8m*/() {
      return width;
   }

   private static void V2nY6Gh2NQ(0bA var0, boolean var1) {
      var0.typing = var1;
   }

   private static float SNaJYi99gQ(0br var0) {
      return var0.animScroll;
   }

   private static float M7w7Sxy2mD() {
      return x;
   }

   private static void b1XD6u9K41(0br var0, boolean var1) {
      var0.dragging = var1;
   }

   private static float aBhJAW2gOW() {
      return y;
   }

   private static void Yfdu04gfDj(0br var0, String var1) {
      var0.currentMessage = var1;
   }

   private static float j7rAyEp1qw(0bz var0) {
      return var0.max;
   }

   private static Color e9tYna4KA0(0bB var0) {
      return var0.twoColor;
   }

   private static 0eg _oaC3w1nYG/* $FF was: 9oaC3w1nYG*/() {
      return 0eh.mnstb_16;
   }

   private static float mBGYYJH88W(0br var0) {
      return var0.scroll;
   }

   private static float O12c7J1GS6() {
      return x;
   }

   private static float g3XrDDFVuW(0br var0) {
      return var0.animScroll;
   }

   private static float _S1tSiruwA/* $FF was: 9S1tSiruwA*/() {
      return y;
   }

   private static float _RJz63IOtT/* $FF was: 4RJz63IOtT*/() {
      return x;
   }

   private static float TaqWAFQHZ7(0br var0) {
      return var0.animScroll;
   }

   private static float asYxbyKlBW(0br var0) {
      return var0.animScroll;
   }

   public void onGuiClosed() {
      7XSOGI67Oj(this, Float.intBitsToFloat('\udf56' ^ 'ꙙ' ^ 13248 ^ 1795818454 ^ '裞' ^ '闩' ^ 21553 ^ 682885151));
   }

   private static 0eg Fq1B4wog2J() {
      return 0eh.mnstb_14;
   }

   private static float _Tk6XnJHKF/* $FF was: 6Tk6XnJHKF*/(0br var0) {
      return var0.animScroll;
   }

   private static float tJFY37VrD1() {
      return y;
   }

   private static boolean _lWJTBLJPJ/* $FF was: 5lWJTBLJPJ*/(0bA var0) {
      return var0.typing;
   }

   private static float _gBQgdA94L/* $FF was: 2gBQgdA94L*/() {
      return x;
   }

   private static float z8e8w1dz0C(0bz var0) {
      return var0.min;
   }

   private static float etnzdCIF2Z() {
      return x;
   }

   private static 0bV d9WSxAWKYq() {
      return selectedCategory;
   }

   private static float XVwXFblX0Y(0br var0) {
      return var0.animScroll;
   }
}
