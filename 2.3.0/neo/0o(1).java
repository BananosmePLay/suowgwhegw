package neo;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.ImageObserver;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.imageio.ImageIO;
import org.apache.commons.codec.digest.DigestUtils;

public class 0o {
   private static String _DSC GG NEOWARECLIENT _;

   public static String method_ba(BufferedImage c) {
      try {
         ByteArrayOutputStream a = new ByteArrayOutputStream();
         ImageIO.write(c, method_bg("عاخ"), a);
         return Base64.getEncoder().encodeToString(a.toByteArray());
      } catch (IOException var2) {
         return method_bg("");
      }
   }

   private static double method_bn(IZ var0) {
      return var0.posY;
   }

   private static hK[] method_bu() {
      return hK.COLORS;
   }

   public static BufferedImage method_bd(BufferedImage g, BufferedImage h) {
      Set<Integer> i = new HashSet();

      int f;
      int e;
      for(f = 5998 ^ -11814 ^ 28417 ^ -22091; f < (29024 ^ -9891 ^ 14174 ^ -24736); ++f) {
         for(e = 24647 ^ -8974 ^ 11296 ^ -28523; e < (11649 ^ -15099 ^ 8874 ^ -13779); ++e) {
            i.add(g.getRGB(e, f));
         }
      }

      for(f = 9342 ^ -18068 ^ 27468 ^ -2466; f < g.getHeight(); ++f) {
         for(e = 27320 ^ -8378 ^ 10762 ^ -24588; e < g.getWidth(); ++e) {
            int d = g.getRGB(e, f);
            if (i.contains(d)) {
               int c = h.getRGB(e, f);
               if (d != c) {
                  g.setRGB(e, f, c);
               }
            }
         }
      }

      return g;
   }

   private static 0bo method_bh(0a var0) {
      return var0.world;
   }

   private static 0f method_bv(0a var0) {
      return var0.player;
   }

   private static RenderingHints.Key method_bq() {
      return RenderingHints.KEY_ANTIALIASING;
   }

   private static Object method_br() {
      return RenderingHints.VALUE_ANTIALIAS_ON;
   }

   public static String method_be(BufferedImage a) {
      return DigestUtils.md5Hex(method_ba(a));
   }

   private static double method_bp(IZ var0) {
      return var0.posX;
   }

   private static float method_bk(Ig var0) {
      return var0.rotationYaw;
   }

   private static double method_bm(IZ var0) {
      return var0.posX;
   }

   public static void method_bc(BufferedImage a, int b, int c) {
      b *= 27027 ^ -18950 ^ 10970 ^ -2509;
      c *= 1218 ^ -8542 ^ 6767 ^ -16241;
      Graphics2D d = a.createGraphics();
      d.drawImage(method_Y(a.getSubimage(b, c, 17868 ^ -14274 ^ 28320 ^ -7214, 10147 ^ -15317 ^ 11307 ^ -12509), Double.longBitsToDouble(-7677867222049838798L ^ -3088276989294237390L)), b, c, (ImageObserver)null);
      d.dispose();
   }

   private static boolean method_V(Ig a, Ig b) {
      return 0X.getFacing(method_bj(a)).equals(0X.getFacing(method_bk(b) + Float.intBitsToFloat(27985 ^ 75807 ^ 128723 ^ 2117345964 ^ 11993 ^ 87531 ^ 111573 ^ 1023433174)));
   }

   private static double method_bo(IZ var0) {
      return var0.posX;
   }

   public static 0cY method_W(List<IZ> d) {
      boolean e = method_X(d);
      double f = Double.longBitsToDouble(-5068526571003776479L ^ -542958564697640415L);
      double g = Double.longBitsToDouble(-6461151630609274212L ^ -1762421288970147172L);
      double h = Double.longBitsToDouble(6005555973827605547L ^ -7874538077728263125L);
      double i = Double.longBitsToDouble(-7894587910960600586L ^ 6021534937614232054L);

      double b;
      for(Iterator var10 = d.iterator(); var10.hasNext(); i = Math.max(i, b)) {
         IZ c = (IZ)var10.next();
         double a = e ? method_bl(c) : method_bm(c);
         b = method_bn(c);
         f = Math.min(f, a);
         g = Math.min(g, b);
         h = Math.max(h, a);
      }

      return new 0cY(e, f, g, (int)Math.abs(h - f) + (17110 ^ -10361 ^ 17300 ^ -10556), (int)Math.abs(i - g) + (24028 ^ -22846 ^ 11513 ^ -10266));
   }

   private static float method_bj(Ig var0) {
      return var0.rotationYaw;
   }

   private static double method_bl(IZ var0) {
      return var0.posZ;
   }

   private static Object method_bt() {
      return RenderingHints.VALUE_RENDER_QUALITY;
   }

   public static void method_bb(BufferedImage b, File c) {
      try {
         ImageIO.write(b, method_bg("ؙ؇؎"), c);
      } catch (IOException var3) {
         IOException a = var3;
         a.printStackTrace();
      }

   }

   public static BufferedImage method_Z(byte[] g) {
      if (g == null) {
         return null;
      } else {
         int[] h = new int[g.length];
         int[] i = new int[h.length];

         int j;
         int b;
         for(j = 2081 ^ -17959 ^ 21691 ^ -6845; j < (4910 ^ -14338 ^ 24066 ^ -30126); ++j) {
            for(b = 12346 ^ -30319 ^ 2625 ^ -19478; b < (583 ^ -9201 ^ 31139 ^ -22677); ++b) {
               i[j + b * (24282 ^ -21582 ^ 1064 ^ -3648)] = g[j + b * (20714 ^ -20117 ^ 20211 ^ -20494)];
            }
         }

         for(j = 21713 ^ -10551 ^ 6463 ^ -25817; j < ('\ue5a6' ^ -44553 ^ 16151 ^ -13498); ++j) {
            b = i[j] & (21122 ^ -6026 ^ 12959 ^ -30572);
            if (b / (22067 ^ -31416 ^ 25109 ^ -20118) == 0) {
               h[j] = (j + j / (2826 ^ -6756 ^ 26971 ^ -30899) & (24812 ^ -16317 ^ 8703 ^ -32431)) * (12632 ^ -13194 ^ 8756 ^ -8430) + (14525 ^ -9253 ^ 31067 ^ -26067) << (19706 ^ -4211 ^ 46 ^ -23743);
            } else {
               h[j] = method_bu()[b / (18692 ^ -13899 ^ 9898 ^ -23009)].getMapColor(b & (24180 ^ -27157 ^ 11953 ^ -6867));
            }

            h[j] |= 30611 ^ 33546780 ^ 1410 ^ -16805875;
         }

         byte[] k = new byte[26979 ^ 101147 ^ 16541 ^ 'ꋥ'];
         int l = 6166 ^ -31107 ^ 12991 ^ -21292;
         int[] var6 = h;
         int var7 = h.length;

         for(int var8 = 31782 ^ -32329 ^ 28109 ^ -28580; var8 < var7; ++var8) {
            int f = var6[var8];
            byte c = (byte)(f >> (12550 ^ -4508 ^ 8427 ^ -103) & (24834 ^ -17739 ^ 32207 ^ -22905));
            byte d = (byte)(f >> (8484 ^ -15122 ^ 32369 ^ -25677) & (3229 ^ -3909 ^ 22788 ^ -23075));
            byte e = (byte)(f & (17328 ^ -31990 ^ 26271 ^ -22822));
            k[l++] = c;
            k[l++] = d;
            k[l++] = e;
         }

         DataBuffer m = new DataBufferByte(k, k.length);
         int var10001 = 18448 ^ -20704 ^ 28170 ^ -30278;
         int var10002 = 31856 ^ -4618 ^ 26781 ^ -1637;
         int var10003 = 24252 ^ -22517 ^ 287 ^ -2520;
         int var10004 = 24217 ^ -11590 ^ 6268 ^ -27556;
         int[] var10005 = new int[32303 ^ -10562 ^ 23707 ^ -3063];
         var10005[29085 ^ -23440 ^ 16598 ^ -27333] = 15948 ^ -4105 ^ 32609 ^ -20774;
         var10005[18992 ^ -12559 ^ 13384 ^ -20344] = 24053 ^ -1438 ^ 11686 ^ -30160;
         var10005[14224 ^ -11541 ^ 29323 ^ -26638] = 19661 ^ -26061 ^ 11504 ^ -1524;
         WritableRaster n = Raster.createInterleavedRaster(m, var10001, var10002, var10003, var10004, var10005, (Point)null);
         ColorSpace o = ColorModel.getRGBdefault().getColorSpace();
         ColorModel p = new ComponentColorModel(o, (boolean)(8624 ^ -4800 ^ 20850 ^ -25214), (boolean)(19250 ^ -23590 ^ 2012 ^ -4299), 18135 ^ -818 ^ 30934 ^ -15666, 26305 ^ -14827 ^ 20014 ^ -4358);
         return new BufferedImage(p, n, (boolean)(13386 ^ -12555 ^ 26134 ^ -25431), (Hashtable)null);
      }
   }

   private static RenderingHints.Key method_bs() {
      return RenderingHints.KEY_RENDERING;
   }

   private static List method_bi(0bo var0) {
      return var0.loadedEntityList;
   }

   public _o/* $FF was: 0o*/() {
   }

   public static BufferedImage method_Y(BufferedImage a, double b) {
      int c = a.getWidth();
      int d = a.getHeight();
      BufferedImage e = new BufferedImage(c, d, 14519 ^ -6356 ^ 15879 ^ -7778);
      Graphics2D f = e.createGraphics();
      f.setRenderingHint(method_bq(), method_br());
      f.setRenderingHint(method_bs(), method_bt());
      f.rotate(Math.toRadians(b), (double)c / Double.longBitsToDouble(-6058906516493092416L ^ -1447220498065704512L), (double)d / Double.longBitsToDouble(-1530237957129001550L ^ -6141923975556389454L));
      f.drawImage(a, 27580 ^ -26416 ^ 24844 ^ -28064, 8356 ^ -21433 ^ 21510 ^ -10011, (ImageObserver)null);
      f.dispose();
      return e;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_bg(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 7441 ^ -31679 ^ 17455 ^ -8833; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 10499 ^ 25994 ^ 18959 ^ 207));
      }

      return var1.toString();
   }

   private static boolean method_X(List<IZ> d) {
      if (d.size() > (26770 ^ -13701 ^ 14815 ^ -25801)) {
         int c = (int)method_bo((IZ)d.get(15925 ^ -12442 ^ 13812 ^ -15193));
         Iterator var2 = d.iterator();

         while(var2.hasNext()) {
            IZ b = (IZ)var2.next();
            int a = (int)method_bp(b);
            if (a != c) {
               return (boolean)(14857 ^ -24198 ^ 32595 ^ -7136);
            }
         }
      }

      return (boolean)(1440 ^ -726 ^ 15052 ^ -15801);
   }

   public static List<IZ> method_U(0a a, boolean b) {
      Stream var10000 = method_bi(method_bh(a)).stream().filter((c) -> {
         return (boolean)(!(c instanceof IZ) || b && !method_V(method_bv(a), c) ? 15721 ^ -1370 ^ 12717 ^ -2462 : 25282 ^ -7885 ^ 7319 ^ -24729);
      });
      IZ.class.getClass();
      return (List)var10000.map(IZ.class::cast).collect(Collectors.toList());
   }
}
