package neo;

import com.google.common.eventbus.Subscribe;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.util.EnumHand;

public class 0cG implements 0cD {
   public final 0k field_b;
   public final 0L field_e;
   public final 0W field_g;
   public static 0cG field_h;
   public final 0be field_f;
   public final 0K field_d;
   public boolean field_c;
   public final 0cQ field_a;
   public final 0cH field_i;
   private static String _DSC GG NEOWARECLIENT _;

   private static 0cQ method_bwV(0cG var0) {
      return var0.field_a;
   }

   public 0k method_bwg() {
      return method_bwU(this);
   }

   private static ArrayList method_bwB() {
      return 0bC.field_c;
   }

   @Subscribe
   public void method_bwb(0df a) {
      if (a.method_bAb() == (14944 ^ -25933 ^ 14296 ^ -26870)) {
         Ig b = method_bwA(method_bwz());
         String var10000;
         Object[] var10001;
         if (b != null) {
            if (0bz.method_Qm().method_Qs().method_bxY(0bC.class).method_bBh()) {
               method_bwB().add(new 0bA(method_bwC(), b.getEntityId()));
               return;
            }

            if (method_bwD(this).method_bxe() == (-12442 ^ -13110 ^ 10532 ^ -10890)) {
               this.method_bwk().method_bxf(b.getEntityId());
               var10000 = method_bwv("ơƿƬǏ֮ב֪֯ןגךגǎǏƦƫǕǏǊƋ");
               var10001 = new Object[12955 ^ -26435 ^ 18690 ^ -7387];
               var10001[28363 ^ -9333 ^ 32567 ^ -13705] = method_bwE(this).method_bxe();
               0ek.addMessage(String.format(var10000, var10001), method_bwv("ǁƍƀƛƜǏƁƟƌǏƟƃƎƖ"), 0cT.method_byX(method_bwv("ƌƃƆƊƁƛǁƌƀƂƂƎƁƋǁƇƀƙƊƝ")));
               return;
            }

            if (this.method_bwm() && 0e.get(b.getName()) != null) {
               if (method_bwF(this).contains(b.getName())) {
                  method_bwG(this).remove(b.getName());
               } else {
                  method_bwH(this).add(b.getName());
               }
            }

            ((List)0e.getOnline().stream().filter((ax) -> {
               return ax.getFunction().method_cS() instanceof 0A;
            }).collect(Collectors.toList())).forEach((bx) -> {
               method_bxb(bx).interactWithEntity(b.getEntityId(), method_bxc());
            });
         }

         int c = method_bwK(method_bwJ(method_bwI()));
         if (method_bwL().method_bna()) {
            var10000 = method_bwv("ƼƃƀƛǏǊƋǏƓǏǁƍƀƛƜǏƇƀƛƍƎƝǏǊƋ");
            var10001 = new Object[24503 ^ -22248 ^ 16160 ^ -13939];
            var10001[14470 ^ -30374 ^ 28135 ^ -9157] = c;
            var10001[9830 ^ -28741 ^ 19813 ^ -6983] = c;
            var10000 = String.format(var10000, var10001);
            String var4 = method_bwv("ǁƍƀƛƜǏƇƀƛƍƎƝǏǊƋ");
            Object[] var10002 = new Object[14600 ^ -30128 ^ 30052 ^ -14787];
            var10002[10409 ^ -15306 ^ 21962 ^ -18091] = c;
            0ek.addMessage(var10000, String.format(var4, var10002), 0cT.method_byX(method_bwv("ƌƃƆƊƁƛǁƌƀƂƂƎƁƋǁƇƀƙƊƝ")));
         }

         if (0bz.method_Qm().method_Qs().method_bxY(0bC.class).method_bBh()) {
            method_bwM().add(new 0bA(method_bwN(), c));
         } else {
            ((List)0e.getOnline().stream().filter((ax) -> {
               return ax.getFunction().method_cS() instanceof 0A;
            }).collect(Collectors.toList())).forEach((bx) -> {
               bx.changeSlot(c);
               bx.useItem();
            });
         }
      }
   }

   private static 0i method_bxd(0a var0) {
      return var0.controller;
   }

   private static nC method_bwz() {
      return mc;
   }

   private static 0cG method_bwR() {
      return field_h;
   }

   private static pm method_bwP(nC var0) {
      return var0.world;
   }

   private static nC method_bwO() {
      return mc;
   }

   private static void method_bwZ(0cG var0, boolean var1) {
      var0.field_c = var1;
   }

   private static ArrayList method_bwx() {
      return 0bC.field_c;
   }

   public 0L method_bwe() {
      return method_bwS(this);
   }

   private static MJ method_bwJ(jh var0) {
      return var0.inventory;
   }

   public void method_bwl(boolean a) {
      method_bwZ(this, a);
   }

   private static 0cH method_bwG(0cG var0) {
      return var0.field_i;
   }

   private static Ig method_bwA(nC var0) {
      return var0.pointedEntity;
   }

   private static List method_bwQ(pm var0) {
      return var0.loadedEntityList;
   }

   private static int method_bwK(MJ var0) {
      return var0.currentItem;
   }

   private static 0K method_bwT(0cG var0) {
      return var0.field_d;
   }

   private static 0cH method_bwH(0cG var0) {
      return var0.field_i;
   }

   private static 0L method_bwS(0cG var0) {
      return var0.field_e;
   }

   private static 0bB method_bwy() {
      return 0bB.field_e;
   }

   private static EnumHand method_bxc() {
      return EnumHand.MAIN_HAND;
   }

   private static 0cp method_bww() {
      return 0bF.field_i;
   }

   private static jh method_bwI() {
      return nC.player;
   }

   @Subscribe
   public void method_bwc(0do a) {
      if (this.method_bwm()) {
         ((List)method_bwQ(method_bwP(method_bwO())).stream().filter((ax) -> {
            return this.method_bwk().contains(ax.getName());
         }).collect(Collectors.toList())).forEach((ax) -> {
            0en.method_bGj(ax, new Color(9216 ^ -30221 ^ 5864 ^ -17637, 19209 ^ -23997 ^ 21305 ^ -17733, 13348 ^ -18529 ^ 12115 ^ -21272), Float.intBitsToFloat(21670 ^ 26148 ^ 4073 ^ -928512851 ^ 31650 ^ '맺' ^ '퇄' ^ -1973409190));
         });
      }

   }

   private static 0cH method_bwE(0cG var0) {
      return var0.field_i;
   }

   private static 0i method_bxb(0a var0) {
      return var0.controller;
   }

   private static ArrayList method_bwM() {
      return 0bC.field_c;
   }

   private static 0bB method_bwN() {
      return 0bB.field_c;
   }

   private static 0cp method_bwL() {
      return 0bF.field_i;
   }

   public static 0cG method_bwd() {
      return method_bwR();
   }

   private static 0cH method_bwD(0cG var0) {
      return var0.field_i;
   }

   public 0be method_bwj() {
      return method_bwX(this);
   }

   public 0W method_bwi() {
      return method_bwW(this);
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_bwv(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 19144 ^ -8937 ^ 5562 ^ -32155; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 29599 ^ -24298 ^ 3666 ^ -8908));
      }

      return var1.toString();
   }

   public 0cH method_bwk() {
      return method_bwY(this);
   }

   public 0K method_bwf() {
      return method_bwT(this);
   }

   private static 0cH method_bwY(0cG var0) {
      return var0.field_i;
   }

   public 0cQ method_bwh() {
      return method_bwV(this);
   }

   private static 0k method_bwU(0cG var0) {
      return var0.field_b;
   }

   private static 0be method_bwX(0cG var0) {
      return var0.field_f;
   }

   public _cG/* $FF was: 0cG*/() {
      field_h = this;
      this.field_e = new 0L();
      this.field_d = new 0K();
      this.field_b = new 0k();
      this.field_a = new 0cQ();
      this.field_g = new 0W();
      this.field_f = new 0be();
      this.field_i = new 0cH();
      new 0J();
      0bz.method_Qm().method_Qn().register(this);
   }

   private static 0W method_bwW(0cG var0) {
      return var0.field_g;
   }

   public boolean method_bwm() {
      return method_bxa(this);
   }

   @Subscribe
   public void method_bwa(0dj a) {
      int b = a.method_bAp();
      if (method_bww().method_bna()) {
         String var10000 = method_bwv("ƼƃƀƛǏǊƋǏƓǏǁƍƀƛƜǏƆƁƙƌƃƆƌƄǏǊƋ");
         Object[] var10001 = new Object[17208 ^ -29062 ^ 31711 ^ -18785];
         var10001[20933 ^ -8788 ^ 7129 ^ -26704] = b;
         var10001[30256 ^ -5128 ^ 27864 ^ -3823] = b;
         var10000 = String.format(var10000, var10001);
         String var3 = method_bwv("ǁƍƀƛƜǏƆƁƙƌƃƆƌƄǏǊƋ");
         Object[] var10002 = new Object[1850 ^ -16740 ^ 30387 ^ -12524];
         var10002[27338 ^ -27078 ^ 10141 ^ -9363] = b;
         0ek.addMessage(var10000, String.format(var3, var10002), 0cT.method_byX(method_bwv("ƌƃƆƊƁƛǁƌƀƂƂƎƁƋǁƇƀƙƊƝ")));
      }

      if (0bz.method_Qm().method_Qs().method_bxY(0bC.class).method_bBh()) {
         method_bwx().add(new 0bA(method_bwy(), b));
      } else {
         ((List)0e.getOnline().stream().filter((ax) -> {
            return ax.getFunction().method_cS() instanceof 0A;
         }).collect(Collectors.toList())).forEach((c) -> {
            method_bxd(c).windowClick(b, 6660 ^ -30351 ^ 11603 ^ -16858, a.method_bAq());
         });
      }
   }

   private static boolean method_bxa(0cG var0) {
      return var0.field_c;
   }

   private static 0bB method_bwC() {
      return 0bB.field_b;
   }

   private static 0cH method_bwF(0cG var0) {
      return var0.field_i;
   }
}
