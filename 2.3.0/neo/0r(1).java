package neo;

import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.util.NonNullList;

public class 0r {
   private static int _DSC GG NEOWARECLIENT _;

   private static float method_ck(IZ var0) {
      return var0.rotationYaw;
   }

   private static PT method_bZ() {
      return NK.FILLED_MAP;
   }

   private static double method_ci(IZ var0) {
      return var0.posY;
   }

   public static 0cW method_bW(0a n) {
      if (method_bY(n) == null) {
         return null;
      } else {
         int o = 0X.findItem(n, method_bZ());
         BufferedImage l;
         if ((o < 0 || o >= (19820 ^ -24387 ^ 20159 ^ -23705)) && o != (20244 ^ -5325 ^ 235 ^ -23327)) {
            List<IZ> j = 0o.method_U(n, (boolean)(246 ^ -15811 ^ 8200 ^ -7486));
            if (j.size() == 0) {
               j = 0o.method_U(n, (boolean)(17884 ^ -20125 ^ 9341 ^ -12094));
            }

            0cY k = 0o.method_W(j);
            if (k.method_bzs() <= 0 && k.method_bzt() <= 0) {
               return null;
            } else {
               l = new BufferedImage(k.method_bzs() * (2398 ^ -27234 ^ 10009 ^ -17575), k.method_bzt() * (20494 ^ -2656 ^ 3169 ^ -22193), 1095 ^ -26193 ^ 12203 ^ -19902);
               ArrayList<0cX> m = new ArrayList();
               Iterator var6 = j.iterator();

               while(var6.hasNext()) {
                  IZ h = (IZ)var6.next();
                  double d = k.method_bzp() ? method_cg(h) - k.method_bzq() : method_ch(h) - k.method_bzq();
                  double e = method_ci(h) - k.method_bzr();
                  if (method_cj(h) == Float.intBitsToFloat(118910 ^ 121119 ^ 8765 ^ -1351825401 ^ 7169 ^ 32665 ^ 3011 ^ -1351841024) || method_ck(h) == Float.intBitsToFloat(16255 ^ 2565 ^ 14315 ^ 83624328 ^ 16873 ^ 1045747 ^ 15377 ^ 1179093522)) {
                     d = (double)k.method_bzs() - d - Double.longBitsToDouble(8649507123982430724L ^ 5186239010534519300L);
                  }

                  if (h.getDisplayedItem().getItem().equals(method_cl())) {
                     bhE f = ((PT)h.getDisplayedItem().getItem()).getMapData(h.getDisplayedItem(), method_cm(n));
                     if (f == null) {
                        return null;
                     }

                     BufferedImage g = 0o.method_Z(method_cn(f));
                     if (g == null) {
                        return null;
                     }

                     m.add(new 0cX(h.getEntityId(), (int)((double)k.method_bzs() - d - Double.longBitsToDouble(-5354249405115587694L ^ -8484251146138082414L)), (int)((double)k.method_bzt() - e - Double.longBitsToDouble(-5975309519959521840L ^ -7862317763827759664L))));
                     l.createGraphics().drawImage(h.getRotation() != 0 ? 0o.method_Y(g, (double)(h.getRotation() * (32218 ^ -7241 ^ 31155 ^ -6268))) : g, (int)((double)k.method_bzs() - d - Double.longBitsToDouble(-2329399395913817565L ^ -2279859800012742109L)) * (24867 ^ -20598 ^ 6246 ^ -10673), (int)((double)k.method_bzt() - e - Double.longBitsToDouble(-6408924591540651157L ^ -7422234507699012757L)) * (31422 ^ -13304 ^ 19459 ^ -1483), (ImageObserver)null);
                  }
               }

               if (method_co().method_bna()) {
                  if (n.getParameter(method_bX("֢֢֭֬֔֯")) == null) {
                     n.setParameter(method_bX("֢֢֭֬֔֯"), 7063 ^ -18301 ^ 14868 ^ -26368);
                  }

                  int i = (Integer)n.getParameter(method_bX("֢֢֭֬֔֯"));
                  if ((float)i < method_cp().method_bnH()) {
                     if (n.getParameter(method_bX("ֹ֢֪֦֭֭֮֬֔")) == null) {
                        n.setParameter(method_bX("ֹ֢֪֦֭֭֮֬֔"), l);
                     } else {
                        n.setParameter(method_bX("ֹ֢֪֦֭֭֮֬֔"), 0o.method_bd((BufferedImage)n.getParameter(method_bX("ֹ֢֪֦֭֭֮֬֔")), l));
                     }

                     n.setParameter(method_bX("֢֢֭֬֔֯"), i + (13321 ^ -13298 ^ 20889 ^ -22113));
                     return null;
                  }

                  l = (BufferedImage)n.getParameter(method_bX("ֹ֢֪֦֭֭֮֬֔"));
                  n.setParameter(method_bX("֢֢֭֬֔֯"), (Object)null);
                  n.setParameter(method_bX("ֹ֢֪֦֭֭֮֬֔"), (Object)null);
               }

               return new 0cW(l, m, n);
            }
         } else {
            OL a = o == (1650 ^ -7024 ^ 13179 ^ -11852) ? method_ca(n).getHeldItemOffhand().getItem() : ((Qy)method_cd(method_cc(method_cb(n))).get(o)).getItem();
            bhE b = ((PT)a).getMapData(a.getDefaultInstance(), method_ce(n));
            if (b == null) {
               return null;
            } else {
               l = 0o.method_Z(method_cf(b));
               return l == null ? null : new 0cW(l, new ArrayList(), n);
            }
         }
      }
   }

   private static float method_cj(IZ var0) {
      return var0.rotationYaw;
   }

   private static MJ method_cc(0f var0) {
      return var0.inventory;
   }

   private static PT method_cl() {
      return NK.FILLED_MAP;
   }

   private static 0f method_ca(0a var0) {
      return var0.player;
   }

   private static byte[] method_cf(bhE var0) {
      return var0.colors;
   }

   private static NonNullList method_cd(MJ var0) {
      return var0.mainInventory;
   }

   public _r/* $FF was: 0r*/() {
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_bX(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 2514 ^ -29073 ^ 29316 ^ -2759; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 12516 ^ -30122 ^ 2834 ^ -19349));
      }

      return var1.toString();
   }

   private static double method_cg(IZ var0) {
      return var0.posZ;
   }

   private static 0cp method_co() {
      return 0bI.field_o;
   }

   private static 0bo method_ce(0a var0) {
      return var0.world;
   }

   private static 0bo method_bY(0a var0) {
      return var0.world;
   }

   private static byte[] method_cn(bhE var0) {
      return var0.colors;
   }

   private static 0f method_cb(0a var0) {
      return var0.player;
   }

   private static double method_ch(IZ var0) {
      return var0.posX;
   }

   private static 0bo method_cm(0a var0) {
      return var0.world;
   }

   private static 0ct method_cp() {
      return 0bI.field_h;
   }
}
