package neo;

import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import javax.vecmath.Vector2f;
import net.minecraft.inventory.ClickType;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;

public class 0bs extends 0cV {
   public static boolean field_b;
   public int field_a = 29527 ^ -11619 ^ 9303 ^ -31313;
   private static int _DSC GG NEOWARECLIENT _;

   private static 0g method_OC(0a var0) {
      return var0.keyboard;
   }

   private static 0i method_PE(0a var0) {
      return var0.controller;
   }

   private static 0g method_NZ(0a var0) {
      return var0.keyboard;
   }

   private void method_Mg(String a) {
      a = method_MO("Ú") + a;
      int b = this.method_Mh().size();
      if (b == 0e.getOnline().size()) {
         0ek.addMessage(0cT.method_byX(method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0099\u0095\u008f\u0094\u008e\u009f\u0088Ë")) + a);
      } else {
         StringBuilder var10000 = new StringBuilder();
         String var10001 = 0eg.method_bFq(b, method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0099\u0095\u008f\u0094\u008e\u009f\u0088Î"), method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0099\u0095\u008f\u0094\u008e\u009f\u0088É"), method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0099\u0095\u008f\u0094\u008e\u009f\u0088È"));
         Object[] var10002 = new Object[28258 ^ -3953 ^ 28557 ^ -3743];
         var10002[14559 ^ -15112 ^ 6451 ^ -6892] = b;
         0ek.addMessage(var10000.append(0cT.method_byW(var10001, var10002)).append(a).toString());
      }

   }

   private static int method_OV(0bs var0) {
      return var0.field_a;
   }

   private static void method_Pr(0f var0, float var1) {
      var0.rotationPitch = var1;
   }

   private static 0cG method_NA() {
      return botManager;
   }

   private static 0g method_NX(0a var0) {
      return var0.keyboard;
   }

   private static void method_OQ(0g var0, boolean var1) {
      var0.keyBindForward = var1;
   }

   private static void method_Nh(boolean var0) {
      field_b = var0;
   }

   private static int method_OR(0bs var0) {
      return var0.field_a;
   }

   private static 0cG method_NH() {
      return botManager;
   }

   private static 0g method_OO(0a var0) {
      return var0.keyboard;
   }

   private static void method_Ni(0bs var0, int var1) {
      var0.field_a = var1;
   }

   private static 0f method_Po(0a var0) {
      return var0.player;
   }

   private static double method_Pl(0f var0) {
      return var0.posZ;
   }

   public void method_bze(String[] I) {
      if (I.length == 0) {
         this.method_bzf();
      } else {
         0L J = method_MP().method_bwe();
         0K K = method_MQ().method_bwf();
         String n;
         int x;
         int F;
         String var10000;
         Object[] var10001;
         if (!I[3101 ^ -901 ^ 11514 ^ -9060].equalsIgnoreCase(method_MO("\u0099\u0095\u0094\u0094\u009f\u0099\u008e")) && !I[16588 ^ -682 ^ 4620 ^ -20586].equalsIgnoreCase(method_MO("\u0090\u0095\u0093\u0094"))) {
            if (I[2584 ^ -16743 ^ 2609 ^ -16720].equalsIgnoreCase(method_MO("\u0099\u0092\u009b\u008e"))) {
               if (I.length > (7723 ^ -1371 ^ 4032 ^ -5297)) {
                  n = String.join(method_MO("Ú"), (CharSequence[])Arrays.copyOfRange(I, 25271 ^ -13997 ^ 11470 ^ -30933, I.length));
                  this.method_Mg(0cT.method_byX(method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0099\u0092\u009b\u008eÔ\u0093\u0094\u009c\u0095")));
                  this.method_Mh().forEach((b) -> {
                     if (method_PI(b) != null) {
                        method_PJ(b).sendChatMessage(0V.format(n));
                        0eh.method_bFu(method_PK(this));
                     }

                  });
               } else {
                  var10000 = method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0099\u0092\u009b\u008eÔ\u0089\u0083\u0094\u008e\u009b\u0082");
                  var10001 = new Object[13272 ^ -31914 ^ 22284 ^ -6269];
                  var10001[9710 ^ -25865 ^ 8928 ^ -25095] = this.method_bzi();
                  0ek.addMessage(0cT.method_byW(var10000, var10001));
               }
            } else {
               Object[] var10002;
               String var27;
               if (I[3034 ^ -17796 ^ 3369 ^ -17265].equalsIgnoreCase(method_MO("\u0092\u0095\u008e\u0098\u009b\u0088"))) {
                  if (I.length == (25116 ^ -26854 ^ 32515 ^ -30201)) {
                     var27 = method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0092\u0095\u008e\u0098\u009b\u0088Ô\u0093\u0094\u009c\u0095");
                     var10002 = new Object[15539 ^ -21237 ^ 7168 ^ -29255];
                     var10002[3189 ^ -30994 ^ 758 ^ -30611] = I[30374 ^ -28459 ^ 11993 ^ -14165];
                     this.method_Mg(0cT.method_byW(var27, var10002));
                     this.method_Mh().forEach((b) -> {
                        b.changeSlot(0eb.parseInt(I[30145 ^ -3307 ^ 7497 ^ -25700], 9616 ^ -31058 ^ 25470 ^ -16320));
                        b.useItem();
                        0eh.method_bFu(method_PH(this));
                     });
                  } else {
                     var10000 = method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0092\u0095\u008e\u0098\u009b\u0088Ô\u0089\u0083\u0094\u008e\u009b\u0082");
                     var10001 = new Object[7478 ^ -24709 ^ 18589 ^ -13615];
                     var10001[175 ^ -6447 ^ 31716 ^ -25190] = this.method_bzi();
                     0ek.addMessage(0cT.method_byW(var10000, var10001));
                  }
               } else if (I[6368 ^ -13599 ^ 22361 ^ -31400].equalsIgnoreCase(method_MO("\u0093\u0094\u008c\u0099\u0096\u0093\u0099\u0091"))) {
                  if (I.length == (25766 ^ -13536 ^ 9973 ^ -30351)) {
                     var27 = method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0093\u0094\u008c\u0099\u0096\u0093\u0099\u0091Ô\u0093\u0094\u009c\u0095");
                     var10002 = new Object[29724 ^ -13767 ^ 16034 ^ -32634];
                     var10002[25021 ^ -11915 ^ 31517 ^ -13355] = I[3951 ^ -29023 ^ 27112 ^ -6105];
                     this.method_Mg(0cT.method_byW(var27, var10002));
                     this.method_Mh().forEach((b) -> {
                        method_PE(b).windowClick(0eb.parseInt(I[17468 ^ -16961 ^ 31295 ^ -31811], 4837 ^ -15811 ^ 32660 ^ -20660), 1927 ^ -28261 ^ 21904 ^ -15476, method_PF());
                        0eh.method_bFu(method_PG(this));
                     });
                  } else {
                     var10000 = method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0093\u0094\u008c\u0099\u0096\u0093\u0099\u0091Ô\u0089\u0083\u0094\u008e\u009b\u0082");
                     var10001 = new Object[6845 ^ -10650 ^ 2069 ^ -15153];
                     var10001[11794 ^ -17805 ^ 30578 ^ -7405] = this.method_bzi();
                     0ek.addMessage(0cT.method_byW(var10000, var10001));
                  }
               } else {
                  String[] f;
                  if (I[28116 ^ -12694 ^ 14718 ^ -25920].equalsIgnoreCase(method_MO("\u0089\u008a\u0093\u0094"))) {
                     if (I.length == (26205 ^ -155 ^ 28011 ^ -2985)) {
                        if (0F.getByName(I[31969 ^ -27408 ^ 15512 ^ -11126]) == null) {
                           var10000 = method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0089\u008a\u0093\u0094Ô\u009e\u0093\u0088\u009f\u0099\u008e\u0093\u0095\u0094Ô\u009f\u0088\u0088\u0095\u0088");
                           var10001 = new Object[8392 ^ -6531 ^ 27097 ^ -20627];
                           var10001[11928 ^ -2017 ^ 30122 ^ -23763] = I[26850 ^ -28124 ^ 32174 ^ -30869];
                           0ek.addMessage(0cT.method_byW(var10000, var10001));
                           return;
                        }

                        var27 = method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0089\u008a\u0093\u0094Ô\u0093\u0094\u009c\u0095");
                        var10002 = new Object[27792 ^ -23464 ^ 29577 ^ -17600];
                        var10002[80 ^ -18380 ^ 18829 ^ -3607] = I[7736 ^ -9417 ^ 2551 ^ -13063];
                        this.method_Mg(0cT.method_byW(var27, var10002));
                        method_MV().println(I[5046 ^ -4554 ^ 15951 ^ -15411].replaceAll(method_MO("¡¤Ê×ÃÖ§"), method_MO("")));
                        f = I[12219 ^ -22173 ^ 21278 ^ -10812].replaceAll(method_MO("¡¤Ê×ÃÖ§"), method_MO("")).split(method_MO("Ö"));
                        x = 8750 ^ -31794 ^ 30184 ^ -11256;
                        Iterator var13 = this.method_Mh().iterator();

                        while(var13.hasNext()) {
                           0a e = (0a)var13.next();
                           e.getFunction().method_cR(new 0G(e, I[28147 ^ -4835 ^ 17555 ^ -15236], 0eb.parseInt(f[x], 28380 ^ -9982 ^ 5331 ^ -23794), 0F.getByName(I[16163 ^ -589 ^ 1829 ^ -14922])));
                           0eh.method_bFu(method_MW(this));
                           ++x;
                           if (x >= f.length) {
                              x = 10908 ^ -21445 ^ 7562 ^ -25811;
                           }
                        }
                     } else {
                        var10000 = method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0089\u008a\u0093\u0094Ô\u0089\u0083\u0094\u008e\u009b\u0082");
                        var10001 = new Object[2975 ^ -29937 ^ 5130 ^ -27493];
                        var10001[23757 ^ -20956 ^ 753 ^ -4072] = this.method_bzi();
                        0ek.addMessage(0cT.method_byW(var10000, var10001));
                     }
                  } else if (I[23997 ^ -20029 ^ 1377 ^ -5857].equalsIgnoreCase(method_MO("\u009e\u0093\u0089\u0099\u0095\u0094\u0094\u009f\u0099\u008e"))) {
                     0ek.addMessage(0cT.method_byX(method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u009e\u0093\u0089\u0099\u0095\u0094\u0094\u009f\u0099\u008eÔ\u0093\u0094\u009c\u0095")));
                     method_MX((boolean)(4263 ^ -17071 ^ 22403 ^ -1419));
                     0e.getBots().forEach((a) -> {
                        a.setParameter(method_MO("\u009e\u009f\u0096\u009f\u008e\u009f\u009e"), Boolean.valueOf((boolean)(11498 ^ -31977 ^ 28621 ^ -16335)));
                        a.stopBot();
                     });
                     method_MY().method_bwg().method_j().method_bN();
                     0bq.method_LU();
                     method_MZ().clear();
                  } else if (I[16563 ^ -22574 ^ 10492 ^ -12387].equalsIgnoreCase(method_MO("\u0099\u0096\u009f\u009b\u0088"))) {
                     0ek.addMessage(0cT.method_byX(method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0099\u0096\u009f\u009b\u0088Ô\u0093\u0094\u009c\u0095")));
                     0e.removeAll((Collection)0e.getBots().stream().filter((a) -> {
                        return (boolean)(!a.isOnline() ? 12858 ^ -29542 ^ 26913 ^ -10368 : 27321 ^ -25024 ^ 12134 ^ -9313);
                     }).collect(Collectors.toList()));
                     method_Na().method_bwg().method_j().method_bN();
                     0bq.method_LU();
                     method_Nb().clear();
                  } else if (I[13858 ^ -6221 ^ 28351 ^ -16594].equalsIgnoreCase(method_MO("\u0096\u0095\u009b\u009e\u008a\u0088\u0095\u0082\u0083"))) {
                     if (I.length == (30516 ^ -18523 ^ 27919 ^ -21091)) {
                        method_Nc().method_bwe().method_ke(I[2778 ^ -17136 ^ 30656 ^ -16373], I[26284 ^ -31116 ^ 4696 ^ -3454]);
                     } else {
                        var10000 = method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0096\u0095\u009b\u009e\u008a\u0088\u0095\u0082\u0083Ô\u0089\u0083\u0094\u008e\u009b\u0082");
                        var10001 = new Object[12329 ^ -26247 ^ 16093 ^ -26740];
                        var10001[5144 ^ -31725 ^ 13397 ^ -23458] = this.method_bzi();
                        0ek.addMessage(0cT.method_byW(var10000, var10001));
                     }
                  } else if (I[2682 ^ -1288 ^ 13073 ^ -15469].equalsIgnoreCase(method_MO("\u0096\u0095\u009b\u009e\u0094\u0093\u0099\u0091\u0089"))) {
                     method_Nd().method_bwf().method_jV();
                  } else if (I[6405 ^ -31426 ^ 21408 ^ -12389].equalsIgnoreCase(method_MO("\u0097\u0093\u0088\u0088\u0095\u0088"))) {
                     this.method_Mg(0cT.method_byX(method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0097\u0093\u0088\u0088\u0095\u0088Ô\u0093\u0094\u009c\u0095")));
                     this.method_Mh().forEach((a) -> {
                        a.getFunction().method_cR(new 0A(a));
                        0eh.method_bFu(method_PD(this));
                     });
                  } else if (I[24428 ^ -16120 ^ 26363 ^ -1889].equalsIgnoreCase(method_MO("\u0088\u009b\u0094\u009e\u0095\u0097\u0097\u0095\u008c\u009f"))) {
                     if (I.length == (23355 ^ -7091 ^ 17538 ^ -1034)) {
                        if (0C.getByName(I[25862 ^ -21447 ^ 26297 ^ -20601]) == null) {
                           var10000 = method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0088\u009b\u0094\u009e\u0095\u0097\u0097\u0095\u008c\u009fÔ\u008e\u0083\u008a\u009fÔ\u009f\u0088\u0088\u0095\u0088");
                           var10001 = new Object[4869 ^ -4968 ^ 29315 ^ -29409];
                           var10001[27134 ^ -5937 ^ 18939 ^ -14134] = I[26257 ^ -23994 ^ 4 ^ -15150];
                           0ek.addMessage(0cT.method_byW(var10000, var10001));
                           return;
                        }

                        this.method_Mg(0cT.method_byX(method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0088\u009b\u0094\u009e\u0095\u0097\u0097\u0095\u008c\u009fÔ\u0093\u0094\u009c\u0095")));
                        this.method_Mh().forEach((b) -> {
                           b.getFunction().method_cR(new 0D(b, 0C.getByName(I[4067 ^ -20261 ^ 5947 ^ -22526])));
                           0eh.method_bFu(method_PC(this));
                        });
                     } else {
                        var10000 = method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0088\u009b\u0094\u009e\u0095\u0097\u0097\u0095\u008c\u009fÔ\u0089\u0083\u0094\u008e\u009b\u0082");
                        var10001 = new Object[26927 ^ -23211 ^ 13398 ^ -2003];
                        var10001[15350 ^ -17523 ^ 13455 ^ -19212] = this.method_bzi();
                        0ek.addMessage(0cT.method_byW(var10000, var10001));
                     }
                  } else {
                     int D;
                     if (I[16824 ^ -29368 ^ 2018 ^ -13550].equalsIgnoreCase(method_MO("\u0093\u0094\u008c\u0099\u0096\u009f\u009b\u0088"))) {
                        this.method_Mg(0cT.method_byX(method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0093\u0094\u008c\u0099\u0096\u009f\u009b\u0088Ô\u0093\u0094\u009c\u0095")));

                        for(D = 2672 ^ -14876 ^ 8451 ^ -4457; D < (30320 ^ -9850 ^ 16725 ^ -4467); ++D) {
                           Iterator var15 = this.method_Mh().iterator();

                           while(var15.hasNext()) {
                              0a h = (0a)var15.next();
                              method_Ne(h).windowClick(D, 29709 ^ -18413 ^ 23854 ^ -28367, method_Nf());
                              0eh.method_bFu(method_Ng(this));
                           }
                        }
                     } else if (I[2931 ^ -19853 ^ 10339 ^ -28317].equalsIgnoreCase(method_MO("\u009b\u008e\u008e\u009b\u0099\u0091"))) {
                        if (I.length == (31266 ^ -20320 ^ 17861 ^ -28859)) {
                           var27 = method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u009b\u008e\u008e\u009b\u0099\u0091Ô\u0093\u0094\u009c\u0095");
                           var10002 = new Object[5306 ^ -15270 ^ 27446 ^ -17449];
                           var10002[27596 ^ -27625 ^ 18783 ^ -18812] = I[21548 ^ -10383 ^ 25656 ^ -6300];
                           this.method_Mg(0cT.method_byW(var27, var10002));
                           this.method_Mh().forEach((b) -> {
                              b.getFunction().method_cR(new 0v(b, I[19513 ^ -29691 ^ 6388 ^ -10039]));
                              0eh.method_bFu(method_PB(this));
                           });
                        } else {
                           var10000 = method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u009b\u008e\u008e\u009b\u0099\u0091Ô\u0089\u0083\u0094\u008e\u009b\u0082");
                           var10001 = new Object[7251 ^ -2153 ^ 30353 ^ -25260];
                           var10001[38 ^ -16402 ^ 6141 ^ -22475] = this.method_bzi();
                           0ek.addMessage(0cT.method_byW(var10000, var10001));
                        }
                     } else if (I[15702 ^ -30868 ^ 17309 ^ -1625].equalsIgnoreCase(method_MO("\u0089\u008e\u0095\u008a\u0090\u0095\u0093\u0094"))) {
                        0ek.addMessage(0cT.method_byX(method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0089\u008e\u0095\u008a\u0090\u0095\u0093\u0094Ô\u0093\u0094\u009c\u0095")));
                        method_Nh((boolean)(21363 ^ -1759 ^ 7892 ^ -19322));
                     } else {
                        BlockPos G;
                        if (I[18800 ^ -9196 ^ 10553 ^ -17315].equalsIgnoreCase(method_MO("\u0099\u0096\u0093\u0099\u0091\u0098\u0096\u0095\u0099\u0091"))) {
                           if (I.length == (2421 ^ -3405 ^ 28572 ^ -27557)) {
                              this.method_Mg(0cT.method_byX(method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0099\u0096\u0093\u0099\u0091\u0098\u0096\u0095\u0099\u0091Ô\u0099\u0096\u0093\u0099\u0091")));
                              this.method_Mh().forEach((a) -> {
                                 method_Pz(a).rightClickMouse();
                                 0eh.method_bFu(method_PA(this));
                              });
                           } else if (I.length == (24542 ^ -28452 ^ 28737 ^ -16569)) {
                              D = 0eb.parseInt(I[984 ^ -1513 ^ 22766 ^ -24288], 27057 ^ -31639 ^ 20413 ^ -23963);
                              x = 0eb.parseInt(I[9777 ^ -3311 ^ 4275 ^ -14959], 25417 ^ -5438 ^ 4754 ^ -25831);
                              F = 0eb.parseInt(I[28706 ^ -26420 ^ 16394 ^ -22297], 12907 ^ -7767 ^ 1417 ^ -10677);
                              var27 = method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0099\u0096\u0093\u0099\u0091\u0098\u0096\u0095\u0099\u0091Ô\u0093\u0094\u009c\u0095");
                              var10002 = new Object[26500 ^ -11457 ^ 6570 ^ -21230];
                              var10002[7471 ^ -23243 ^ 3882 ^ -18640] = D;
                              var10002[25843 ^ -10698 ^ 213 ^ -19951] = x;
                              var10002[13678 ^ -4558 ^ 15782 ^ -6408] = F;
                              this.method_Mg(0cT.method_byW(var27, var10002));
                              G = new BlockPos(D, x, F);
                              this.method_Mh().forEach((ex) -> {
                                 if (!method_Pe(ex).getBlockState(G).getBlock().equals(method_Pf())) {
                                    Vector2f a = 0X.getBlockAngles((double)G.getX(), (double)G.getY(), (double)G.getZ(), method_Ph(method_Pg(ex)), method_Pj(method_Pi(ex)), method_Pl(method_Pk(ex)));
                                    float b = 0X.normalizeYaw(method_Pm(a));
                                    float c = 0X.normalizePitch(method_Pn(a));
                                    if (!Float.isNaN(b) && !Float.isNaN(c)) {
                                       method_Pp(method_Po(ex), b);
                                       method_Pr(method_Pq(ex), c);
                                    }

                                    0eh.method_bFu(17662 ^ -4489 ^ 25263 ^ -14280);
                                    method_Ps(ex).sendPacket(new Td(G, 0X.getFacing(method_Pu(method_Pt(ex))), method_Pv(), 0ec.floatRandom(Float.intBitsToFloat(29974 ^ 129473 ^ 11852 ^ 316714098 ^ 7390 ^ 102419 ^ 18634 ^ 316721902), Float.intBitsToFloat('떩' ^ '颣' ^ 2037 ^ -1075897490 ^ '븊' ^ 119384 ^ '똴' ^ -2141262857)), 0ec.floatRandom(Float.intBitsToFloat('ꡝ' ^ 20504 ^ '쨽' ^ -601553446 ^ 20995 ^ 18263 ^ 9499 ^ -601552915), Float.intBitsToFloat('ꎑ' ^ 31158 ^ 'ﰸ' ^ 1454966990 ^ 27996 ^ 121950 ^ 9596 ^ 1765325487)), 0ec.floatRandom(Float.intBitsToFloat('ﬤ' ^ 27546 ^ '\udb55' ^ -2088435027 ^ '\ud8ec' ^ 109365 ^ '\uda5c' ^ -2088443709), Float.intBitsToFloat(3070 ^ 1026161 ^ 10766 ^ -1166554310 ^ 6605 ^ '뉦' ^ '\uf07f' ^ -2047338129))));
                                    method_Pw(ex).swingArm(method_Px());
                                 }

                                 0eh.method_bFu(method_Py(this));
                              });
                           } else {
                              var10000 = method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0099\u0096\u0093\u0099\u0091\u0098\u0096\u0095\u0099\u0091Ô\u0089\u0083\u0094\u008e\u009b\u0082");
                              var10001 = new Object[32241 ^ -28018 ^ 5268 ^ -1046];
                              var10001[15422 ^ -31889 ^ 7316 ^ -23611] = this.method_bzi();
                              0ek.addMessage(0cT.method_byW(var10000, var10001));
                           }
                        } else if (I[8105 ^ -6912 ^ 7995 ^ -6766].equalsIgnoreCase(method_MO("\u0089\u008a\u009b\u0097\u0097\u009f\u0088"))) {
                           if (I.length == (22664 ^ -14208 ^ 5047 ^ -31811) && I[2928 ^ -25801 ^ 16158 ^ -20648].equalsIgnoreCase(method_MO("\u0095\u009c\u009c"))) {
                              0e.getBots().forEach((a) -> {
                                 a.setParameter(method_MO("\u0089\u008a\u009b\u0097¥\u008e\u009f\u0082\u008e"), (Object)null);
                                 a.setParameter(method_MO("\u0089\u008a\u009b\u0097¥\u009e\u009f\u0096\u009b\u0083"), (Object)null);
                              });
                              0ek.addMessage(0cT.method_byX(method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0089\u008a\u009b\u0097\u0097\u009f\u0088Ô\u0089\u008e\u0095\u008a")));
                           } else if (I.length >= (18615 ^ -21799 ^ 17527 ^ -23014)) {
                              n = String.join(method_MO("Ú"), (CharSequence[])Arrays.copyOfRange(I, 27970 ^ -29409 ^ 22256 ^ -18769, I.length));
                              x = 0eb.parseInt(I[23134 ^ -19873 ^ 9002 ^ -13526], 14281 ^ -21779 ^ 5322 ^ -30202);
                              this.method_Mh().forEach((c) -> {
                                 c.setParameter(method_MO("\u0089\u008a\u009b\u0097¥\u008e\u009f\u0082\u008e"), n);
                                 c.setParameter(method_MO("\u0089\u008a\u009b\u0097¥\u009e\u009f\u0096\u009b\u0083"), x);
                              });
                              var10000 = method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0089\u008a\u009b\u0097\u0097\u009f\u0088Ô\u0093\u0094\u009c\u0095");
                              var10001 = new Object[31815 ^ -6999 ^ 4305 ^ -30659];
                              var10001[29451 ^ -4131 ^ 29382 ^ -4592] = x;
                              var10001[1328 ^ -20383 ^ 7407 ^ -22081] = n;
                              0ek.addMessage(0cT.method_byW(var10000, var10001));
                           } else {
                              var10000 = method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0089\u008a\u009b\u0097\u0097\u009f\u0088Ô\u0089\u0083\u0094\u008e\u009b\u0082");
                              var10001 = new Object[17466 ^ -17024 ^ 10677 ^ -12274];
                              var10001[31732 ^ -3525 ^ 17493 ^ -12902] = this.method_bzi();
                              0ek.addMessage(0cT.method_byW(var10000, var10001));
                           }
                        } else if (I[28453 ^ -32000 ^ 31275 ^ -27122].equalsIgnoreCase(method_MO("\u0089\u009f\u008e\u009e\u009f\u0096\u009b\u0083"))) {
                           if (I.length == (21960 ^ -27129 ^ 17155 ^ -32562)) {
                              method_Ni(this, 0eb.parseInt(I[7994 ^ -15241 ^ 22247 ^ -29269], 30485 ^ -18543 ^ 3495 ^ -13039));
                              var10000 = method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0089\u009f\u008e\u009e\u009f\u0096\u009b\u0083Ô\u0093\u0094\u009c\u0095");
                              var10001 = new Object[12654 ^ -3544 ^ 13005 ^ -3702];
                              var10001[25120 ^ -16736 ^ 647 ^ -8697] = method_Nj(this);
                              0ek.addMessage(0cT.method_byW(var10000, var10001));
                           } else {
                              var10000 = method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0089\u009f\u008e\u009e\u009f\u0096\u009b\u0083Ô\u0089\u0083\u0094\u008e\u009b\u0082");
                              var10001 = new Object[19148 ^ -30282 ^ 30109 ^ -18714];
                              var10001[10442 ^ -20306 ^ 21706 ^ -13138] = this.method_bzi();
                              0ek.addMessage(0cT.method_byW(var10000, var10001));
                           }
                        } else if (I[29858 ^ -427 ^ 14585 ^ -19954].equalsIgnoreCase(method_MO("\u0096\u0095\u0095\u0091"))) {
                           if (I.length == (8988 ^ -24530 ^ 24986 ^ -7509)) {
                              float p = 0X.normalizeYaw((float)0eb.parseInt(I[12643 ^ -29590 ^ 31018 ^ -15326], 29027 ^ -26006 ^ 18619 ^ -23630));
                              float q = 0X.normalizePitch((float)0eb.parseInt(I[30217 ^ -12575 ^ 26517 ^ -8321], 25962 ^ -13334 ^ 31064 ^ -10280));
                              var27 = method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0096\u0095\u0095\u0091Ô\u0093\u0094\u009c\u0095");
                              var10002 = new Object[32115 ^ -1491 ^ 22563 ^ -8321];
                              var10002[16355 ^ -964 ^ 12334 ^ -3087] = p;
                              var10002[29924 ^ -14645 ^ 9383 ^ -26999] = q;
                              this.method_Mg(0cT.method_byW(var27, var10002));
                              this.method_Mh().forEach((c) -> {
                                 method_Pa(method_OZ(c), p);
                                 method_Pc(method_Pb(c), q);
                                 0eh.method_bFu(method_Pd(this));
                              });
                           } else {
                              var10000 = method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0096\u0095\u0095\u0091Ô\u0089\u0083\u0094\u008e\u009b\u0082");
                              var10001 = new Object[15257 ^ -1735 ^ 15336 ^ -1719];
                              var10001[22269 ^ -17423 ^ 20663 ^ -16965] = this.method_bzi();
                              0ek.addMessage(0cT.method_byW(var10000, var10001));
                           }
                        } else if (I[8479 ^ -25999 ^ 5943 ^ -21415].equalsIgnoreCase(method_MO("\u009b\u008f\u008e\u0092"))) {
                           this.method_Mh().forEach((a) -> {
                              if (!a.getBooleanParameter(method_MO("\u009b\u008f\u008e\u0092\u0095\u0088\u0093\u0080\u009b\u008e\u0093\u0095\u0094"))) {
                                 a.auth();
                                 a.setParameter(method_MO("\u009b\u008f\u008e\u0092\u0095\u0088\u0093\u0080\u009b\u008e\u0093\u0095\u0094"), Boolean.valueOf((boolean)(27127 ^ -5031 ^ 11136 ^ -20945)));
                                 0eh.method_bFu(method_OY(this));
                              }

                           });
                        } else if (I[31594 ^ -7281 ^ 1739 ^ -25042].equalsIgnoreCase(method_MO("\u0088\u009f\u0090\u0095\u0093\u0094\u009b\u0096\u0096"))) {
                           this.method_Mg(0cT.method_byX(method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0088\u009f\u0090\u0095\u0093\u0094\u009b\u0096\u0096Ô\u0093\u0094\u009c\u0095")));
                           this.method_Mh().forEach(0a::reconnect);
                        } else if (I[32078 ^ -3051 ^ 3387 ^ -31648].equalsIgnoreCase(method_MO("\u0094\u008a\u0099"))) {
                           if (I.length >= (27360 ^ -20460 ^ 27215 ^ -20295)) {
                              n = I[15346 ^ -423 ^ 29423 ^ -18619].toLowerCase();
                              x = -1950 ^ -19055 ^ 1643 ^ -19353;
                              switch (n.hashCode()) {
                                 case 112784:
                                    if (n.equals(method_MO("\u0088\u009f\u0099"))) {
                                       x = 17939 ^ -16602 ^ 17246 ^ -17813;
                                    }
                                    break;
                                 case 3443508:
                                    if (n.equals(method_MO("\u008a\u0096\u009b\u0083"))) {
                                       x = 4000 ^ -6661 ^ 28010 ^ -30925;
                                    }
                                    break;
                                 case 3540994:
                                    if (n.equals(method_MO("\u0089\u008e\u0095\u008a"))) {
                                       x = 19307 ^ -23516 ^ 1812 ^ -6054;
                                    }
                              }

                              switch (x) {
                                 case 0:
                                    0ek.addMessage(0cT.method_byX(method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0094\u008a\u0099Ô\u0088\u009f\u0099")));
                                    method_Nk().method_bwk().method_bxf(-4170 ^ -22838 ^ 23117 ^ -4913);
                                    break;
                                 case 1:
                                    0ek.addMessage(0cT.method_byX(method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0094\u008a\u0099Ô\u0089\u008e\u0095\u008a")));
                                    method_Nl().method_bwk().method_bxf(-8334 ^ -22503 ^ 15654 ^ -19022);
                                    break;
                                 case 2:
                                    F = method_Nm().method_bwk().method_bxe();
                                    if (F == (-5709 ^ -1440 ^ 19630 ^ -24445)) {
                                       0ek.addMessage(0cT.method_byX(method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0094\u008a\u0099Ô\u008a\u0096\u009b\u0083Ô\u009b\u0096\u0088\u009f\u009b\u009e\u0083")));
                                       return;
                                    }

                                    if (F == (-14026 ^ -31894 ^ 27614 ^ -8579)) {
                                       0ek.addMessage(0cT.method_byX(method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0094\u008a\u0099Ô\u008a\u0096\u009b\u0083Ô\u009f\u0097\u008a\u008e\u0083")));
                                       return;
                                    }

                                    this.method_Mg(0cT.method_byX(method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0094\u008a\u0099Ô\u008a\u0096\u009b\u0083Ô\u0093\u0094\u009c\u0095")));
                                    this.method_Mh().forEach((b) -> {
                                       method_OW(b).interactWithEntity(F, method_OX());
                                    });
                                    break;
                                 default:
                                    var10000 = method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0094\u008a\u0099Ô\u0089\u0083\u0094\u008e\u009b\u0082");
                                    var10001 = new Object[27144 ^ -11524 ^ 5178 ^ -21297];
                                    var10001[9571 ^ -12142 ^ 9503 ^ -12050] = this.method_bzi();
                                    0ek.addMessage(0cT.method_byW(var10000, var10001));
                              }
                           }
                        } else if (I[30481 ^ -22008 ^ 1004 ^ -8459].equalsIgnoreCase(method_MO("\u009b\u008f\u008e\u0095\u009c\u0093\u0089\u0092"))) {
                           this.method_Mg(0cT.method_byX(method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u009b\u008f\u008e\u0095\u009c\u0093\u0089\u0092Ô\u0093\u0094\u009c\u0095")));
                           this.method_Mh().forEach((a) -> {
                              a.getFunction().method_cR(new 0w(a));
                              0eh.method_bFu(method_OV(this));
                           });
                        } else if (I[31090 ^ -21346 ^ 21737 ^ -32507].equalsIgnoreCase(method_MO("\u009c\u0095\u0096\u0096\u0095\u008d"))) {
                           if (I.length == (9445 ^ -3800 ^ 25050 ^ -19435)) {
                              var27 = method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u009c\u0095\u0096\u0096\u0095\u008dÔ\u0093\u0094\u009c\u0095");
                              var10002 = new Object[11292 ^ -24144 ^ 17044 ^ -12487];
                              var10002[11195 ^ -5664 ^ 25418 ^ -24303] = I[13683 ^ -15566 ^ 31438 ^ -29554];
                              this.method_Mg(0cT.method_byW(var27, var10002));
                              this.method_Mh().forEach((b) -> {
                                 b.getFunction().method_cR(new 0y(b, I[10457 ^ -21381 ^ 23857 ^ -9838]));
                                 0eh.method_bFu(method_OU(this));
                              });
                           } else {
                              var10000 = method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u009c\u0095\u0096\u0096\u0095\u008dÔ\u0089\u0083\u0094\u008e\u009b\u0082");
                              var10001 = new Object[15288 ^ -12461 ^ 10297 ^ -9005];
                              var10001[232 ^ -9654 ^ 8303 ^ -1331] = this.method_bzi();
                              0ek.addMessage(0cT.method_byW(var10000, var10001));
                           }
                        } else if (I[24394 ^ -21943 ^ 11880 ^ -9365].equalsIgnoreCase(method_MO("\u0098\u0089\u008e\u0095\u008a"))) {
                           this.method_Mg(0cT.method_byX(method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0098\u0089\u008e\u0095\u008aÔ\u0093\u0094\u009c\u0095")));
                           this.method_Mh().forEach((a) -> {
                              a.getFunction().method_cR((0u)null);
                              0eh.method_bFu(method_OT(this));
                           });
                        } else if (I[19594 ^ -18795 ^ 24334 ^ -23279].equalsIgnoreCase(method_MO("\u0088\u009f\u008a\u009f\u009b\u008e"))) {
                           this.method_Mg(0cT.method_byX(method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0088\u009f\u008a\u009f\u009b\u008eÔ\u0093\u0094\u009c\u0095")));
                           this.method_Mh().forEach((a) -> {
                              a.getFunction().method_cR(new 0E(a));
                              0eh.method_bFu(method_OS(this));
                           });
                        } else if (I[8217 ^ -26322 ^ 6223 ^ -24200].equalsIgnoreCase(method_MO("\u009c\u0093\u009d\u008f\u0088\u009f"))) {
                           if (I.length >= (32373 ^ -29635 ^ 2384 ^ -1254)) {
                              if (I[30378 ^ -15963 ^ 12024 ^ -26122].equalsIgnoreCase(method_MO("\u0096\u0093\u0089\u008e"))) {
                                 0ek.addMessage(0cT.method_byX(method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u009c\u0093\u009d\u008f\u0088\u009fÔ\u0096\u0093\u0089\u008e")));
                                 f = 0ee.listFiles(new File(method_No(method_Nn()), method_MO("Õ´\u009f\u0095\u00ad\u009b\u0088\u009fÕ\u009c\u0093\u009d\u008f\u0088\u009f\u0089Õ")));
                                 x = f.length;

                                 for(F = 11103 ^ -10016 ^ 6781 ^ -5694; F < x; ++F) {
                                    String s = f[F];
                                    0ek.addMessage(s.replace(method_MO("Ô\u0090\u0089\u0095\u0094"), method_MO("")));
                                 }
                              } else {
                                 BlockPos[] w = 0H.method_jN(I[12756 ^ -21958 ^ 11525 ^ -18710], this.method_Mh().size());
                                 if (w == null) {
                                    var10000 = method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u009c\u0093\u009d\u008f\u0088\u009fÔ\u009f\u0088\u0088\u0095\u0088");
                                    var10001 = new Object[31566 ^ -8921 ^ 30816 ^ -8696];
                                    var10001[31607 ^ -30116 ^ 14171 ^ -14736] = I[24430 ^ -30666 ^ 8745 ^ -2704];
                                    0ek.addMessage(0cT.method_byW(var10000, var10001));
                                    return;
                                 }

                                 var27 = method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u009c\u0093\u009d\u008f\u0088\u009fÔ\u0093\u0094\u009c\u0095");
                                 var10002 = new Object[10329 ^ -15001 ^ 3545 ^ -7962];
                                 var10002[26688 ^ -18700 ^ 31256 ^ -23380] = I[4644 ^ -13231 ^ 11880 ^ -4068];
                                 this.method_Mg(0cT.method_byW(var27, var10002));
                                 x = 27725 ^ -6017 ^ 26724 ^ -5034;
                                 BlockPos y = method_Np().getPosition();

                                 for(Iterator var19 = this.method_Mh().iterator(); var19.hasNext(); 0eh.method_bFu(method_Ns(this))) {
                                    0a v = (0a)var19.next();
                                    BlockPos t = w[x];
                                    BlockPos u = new BlockPos((double)(y.getX() + t.getX()), method_Nr(method_Nq(v)), (double)(y.getZ() + t.getZ()));
                                    v.getFunction().method_cR(new 0z(v, u));
                                    ++x;
                                    if (x >= w.length) {
                                       x = 12182 ^ -8983 ^ 32090 ^ -29147;
                                    }
                                 }
                              }
                           } else {
                              var10000 = method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u009c\u0093\u009d\u008f\u0088\u009fÔ\u0089\u0083\u0094\u008e\u009b\u0082");
                              var10001 = new Object[143 ^ -22438 ^ 5852 ^ -16888];
                              var10001[11201 ^ -11245 ^ 19485 ^ -19505] = this.method_bzi();
                              0ek.addMessage(0cT.method_byW(var10000, var10001));
                           }
                        } else if (I[23518 ^ -20146 ^ 11769 ^ -14487].equalsIgnoreCase(method_MO("\u009b\u008f\u008e\u0095\u008a\u0093\u0096\u0095\u008e"))) {
                           if (I.length >= (25578 ^ -15870 ^ 19851 ^ -5023)) {
                              BlockPos B;
                              if (I.length == (4649 ^ -5042 ^ 28351 ^ -28454) && I[3577 ^ -10756 ^ 6058 ^ -12370].equalsIgnoreCase(method_MO("º\u0097\u009f"))) {
                                 B = new BlockPos(method_Nu(method_Nt()), method_Nw(method_Nv()), method_Ny(method_Nx()));
                              } else {
                                 if (I.length != (8953 ^ -21055 ^ 15616 ^ -19908)) {
                                    var10000 = method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u009b\u008f\u008e\u0095\u008a\u0093\u0096\u0095\u008eÔ\u0089\u0083\u0094\u008e\u009b\u0082");
                                    var10001 = new Object[569 ^ -3845 ^ 26676 ^ -25865];
                                    var10001[15389 ^ -28813 ^ 1361 ^ -18881] = this.method_bzi();
                                    0ek.addMessage(0cT.method_byW(var10000, var10001));
                                    return;
                                 }

                                 B = new BlockPos(0eb.parseInt(I[5088 ^ -6033 ^ 22163 ^ -21219], 30325 ^ -3083 ^ 23182 ^ -8434), 0eb.parseInt(I[16986 ^ -26966 ^ 28768 ^ -23406], 24689 ^ -15981 ^ 28750 ^ -11860), 0eb.parseInt(I[28873 ^ -12556 ^ 12362 ^ -29068], 6695 ^ -4408 ^ 13627 ^ -15916));
                              }

                              this.method_Mg(0cT.method_byX(method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u009b\u008f\u008e\u0095\u008a\u0093\u0096\u0095\u008eÔ\u0093\u0094\u009c\u0095")));
                              this.method_Mh().forEach((b) -> {
                                 b.getFunction().method_cR(new 0x(b, B));
                                 0eh.method_bFu(method_OR(this));
                              });
                           } else {
                              var10000 = method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u009b\u008f\u008e\u0095\u008a\u0093\u0096\u0095\u008eÔ\u0089\u0083\u0094\u008e\u009b\u0082");
                              var10001 = new Object[24711 ^ -26733 ^ 14849 ^ -13036];
                              var10001[12731 ^ -322 ^ 31310 ^ -19125] = this.method_bzi();
                              0ek.addMessage(0cT.method_byW(var10000, var10001));
                           }
                        } else if (I[22602 ^ -129 ^ 11718 ^ -29965].equalsIgnoreCase(method_MO("\u0099\u0095\u0094\u008e\u0088\u0095\u0096"))) {
                           if (I.length >= (22766 ^ -11101 ^ 26291 ^ -5380)) {
                              n = I[1127 ^ -17485 ^ 9150 ^ -25493].toLowerCase();
                              x = -21310 ^ -12222 ^ 5486 ^ -27119;
                              switch (n.hashCode()) {
                                 case -1081415738:
                                    if (n.equals(method_MO("\u0097\u009b\u0094\u008f\u009b\u0096"))) {
                                       x = 1671 ^ -28101 ^ 30943 ^ -5017;
                                    }
                                    break;
                                 case -934610812:
                                    if (n.equals(method_MO("\u0088\u009f\u0097\u0095\u008c\u009f"))) {
                                       x = 21166 ^ -21427 ^ 14218 ^ -13974;
                                    }
                                    break;
                                 case 96417:
                                    if (n.equals(method_MO("\u009b\u009e\u009e"))) {
                                       x = 17510 ^ -22565 ^ 23907 ^ -16676;
                                    }
                                    break;
                                 case 3322014:
                                    if (n.equals(method_MO("\u0096\u0093\u0089\u008e"))) {
                                       x = 6191 ^ -11734 ^ 18972 ^ -32743;
                                    }
                                    break;
                                 case 94746189:
                                    if (n.equals(method_MO("\u0099\u0096\u009f\u009b\u0088"))) {
                                       x = 12750 ^ -29788 ^ 8180 ^ -23137;
                                    }
                              }

                              switch (x) {
                                 case 0:
                                    0ek.addMessage(0cT.method_byX(method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0099\u0095\u0094\u008e\u0088\u0095\u0096Ô\u0096\u0093\u0089\u008e")));
                                    if (method_Nz().method_bwk().size() > 0) {
                                       String C = String.join(method_MO("ÖÚ"), (CharSequence[])method_NA().method_bwk().toArray(new String[14440 ^ -29099 ^ 19255 ^ -758]));
                                       0ek.addMessage(C);
                                    } else {
                                       0ek.addMessage(method_MO("Ð"));
                                    }
                                    break;
                                 case 1:
                                    0ek.addMessage(0cT.method_byX(method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0099\u0095\u0094\u008e\u0088\u0095\u0096Ô\u0099\u0096\u009f\u009b\u0088")));
                                    method_NB().method_bwk().clear();
                                    break;
                                 case 2:
                                    if (I.length < (21779 ^ -21464 ^ 26973 ^ -28571)) {
                                       0ek.addMessage(0cT.method_byX(method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0099\u0095\u0094\u008e\u0088\u0095\u0096Ô\u009b\u009e\u009eÔ\u009f\u0097\u008a\u008e\u0083")));
                                       return;
                                    }

                                    if (I[14577 ^ -26985 ^ 26154 ^ -14258].equals(method_MO("Ð"))) {
                                       0ek.addMessage(0cT.method_byX(method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0099\u0095\u0094\u008e\u0088\u0095\u0096Ô\u009b\u009e\u009eÔ\u009b\u0096\u0096")));
                                       method_NC().method_bwk().clear();
                                       return;
                                    }

                                    if (0e.get(I[13183 ^ -32654 ^ 11235 ^ -26388]) == null) {
                                       var10000 = method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0099\u0095\u0094\u008e\u0088\u0095\u0096Ô\u009b\u009e\u009eÔ\u008f\u0094\u0091\u0094\u0095\u008d\u0094");
                                       var10001 = new Object[10804 ^ -13303 ^ 23975 ^ -17509];
                                       var10001[14804 ^ -7862 ^ 2247 ^ -12199] = I[14243 ^ -3449 ^ 19686 ^ -30272];
                                       0ek.addMessage(0cT.method_byW(var10000, var10001));
                                       return;
                                    }

                                    if (method_ND().method_bwk().contains(I[19376 ^ -1188 ^ 16265 ^ -28825])) {
                                       var10000 = method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0099\u0095\u0094\u008e\u0088\u0095\u0096Ô\u009b\u009e\u009eÔ\u009b\u0096\u0088\u009f\u009b\u009e\u0083");
                                       var10001 = new Object[3819 ^ -12903 ^ 11395 ^ -4112];
                                       var10001[6826 ^ -9197 ^ 23993 ^ -25856] = I[6265 ^ -3755 ^ 7548 ^ -2990];
                                       0ek.addMessage(0cT.method_byW(var10000, var10001));
                                       return;
                                    }

                                    var10000 = method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0099\u0095\u0094\u008e\u0088\u0095\u0096Ô\u009b\u009e\u009eÔ\u0093\u0094\u009c\u0095");
                                    var10001 = new Object[25931 ^ -11341 ^ 30599 ^ -16002];
                                    var10001[15460 ^ -11355 ^ 7973 ^ -3868] = I[9415 ^ -30999 ^ 27774 ^ -12718];
                                    0ek.addMessage(0cT.method_byW(var10000, var10001));
                                    method_NE().method_bwk().add(I[20109 ^ -9368 ^ 23024 ^ -13289]);
                                    break;
                                 case 3:
                                    if (I.length < (12119 ^ -27842 ^ 31861 ^ -16353)) {
                                       0ek.addMessage(0cT.method_byX(method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0099\u0095\u0094\u008e\u0088\u0095\u0096Ô\u0088\u009f\u0097\u0095\u008c\u009fÔ\u009f\u0097\u008a\u008e\u0083")));
                                       return;
                                    }

                                    if (I[19274 ^ -26607 ^ 8823 ^ -3794].equals(method_MO("Ð"))) {
                                       0ek.addMessage(0cT.method_byX(method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0099\u0095\u0094\u008e\u0088\u0095\u0096Ô\u0088\u009f\u0097\u0095\u008c\u009fÔ\u009b\u0096\u0096")));
                                       method_NF().method_bwk().clear();
                                       return;
                                    }

                                    if (!method_NG().method_bwk().contains(I[17984 ^ -26158 ^ 16782 ^ -25058])) {
                                       var10000 = method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0099\u0095\u0094\u008e\u0088\u0095\u0096Ô\u0088\u009f\u0097\u0095\u008c\u009fÔ\u008f\u0094\u0091\u0094\u0095\u008d\u0094");
                                       var10001 = new Object[14753 ^ -19677 ^ 29661 ^ -1698];
                                       var10001[24987 ^ -2604 ^ 15084 ^ -20829] = I[6987 ^ -20350 ^ 29935 ^ -8412];
                                       0ek.addMessage(0cT.method_byW(var10000, var10001));
                                       return;
                                    }

                                    var10000 = method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0099\u0095\u0094\u008e\u0088\u0095\u0096Ô\u0088\u009f\u0097\u0095\u008c\u009fÔ\u0093\u0094\u009c\u0095");
                                    var10001 = new Object[28936 ^ -18812 ^ 19824 ^ -29955];
                                    var10001[13802 ^ -6297 ^ 20188 ^ -25519] = I[19390 ^ -1579 ^ 15236 ^ -30227];
                                    0ek.addMessage(0cT.method_byW(var10000, var10001));
                                    method_NH().method_bwk().remove(I[13244 ^ -32559 ^ 18346 ^ -2875]);
                                    break;
                                 case 4:
                                    method_NI().method_bwl((boolean)(!method_NJ().method_bwm() ? 22144 ^ -7420 ^ 9 ^ -19060 : 29852 ^ -23250 ^ 5588 ^ -15258));
                                    if (method_NK().method_bwm()) {
                                       0ek.addMessage(0cT.method_byX(method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0099\u0095\u0094\u008e\u0088\u0095\u0096Ô\u0097\u009b\u0094\u008f\u009b\u0096Ô\u0093\u0094\u009c\u0095")));
                                    }
                              }
                           } else {
                              var10000 = method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0099\u0095\u0094\u008e\u0088\u0095\u0096Ô\u0089\u0083\u0094\u008e\u009b\u0082");
                              var10001 = new Object[3732 ^ -18917 ^ 8505 ^ -26185];
                              var10001[9499 ^ -9203 ^ 2321 ^ -4089] = this.method_bzi();
                              0ek.addMessage(0cT.method_byW(var10000, var10001));
                           }
                        } else if (I[19582 ^ -20117 ^ 31674 ^ -31057].equalsIgnoreCase(method_MO("\u0091\u009f\u0083\u0098\u0095\u009b\u0088\u009e"))) {
                           if (I.length == (22387 ^ -26205 ^ 30667 ^ -18151)) {
                              n = I[32232 ^ -10508 ^ 20336 ^ -7059].toLowerCase();
                              x = -4305 ^ -32643 ^ 28259 ^ -306;
                              switch (n.hashCode()) {
                                 case -1384188692:
                                    if (n.equals(method_MO("\u0088\u0093\u009d\u0092\u008e\u0099\u0096\u0093\u0099\u0091"))) {
                                       x = 6868 ^ -17584 ^ 27519 ^ -13570;
                                    }
                                    break;
                                 case -895679974:
                                    if (n.equals(method_MO("\u0089\u008a\u0088\u0093\u0094\u008e"))) {
                                       x = 9165 ^ -10998 ^ 8600 ^ -10409;
                                    }
                                    break;
                                 case -677145915:
                                    if (n.equals(method_MO("\u009c\u0095\u0088\u008d\u009b\u0088\u009e"))) {
                                       x = 3027 ^ -994 ^ 24029 ^ -22000;
                                    }
                                    break;
                                 case 3273774:
                                    if (n.equals(method_MO("\u0090\u008f\u0097\u008a"))) {
                                       x = 22643 ^ -22839 ^ 4641 ^ -4963;
                                    }
                                    break;
                                 case 3317767:
                                    if (n.equals(method_MO("\u0096\u009f\u009c\u008e"))) {
                                       x = 3956 ^ -17001 ^ 24883 ^ -11310;
                                    }
                                    break;
                                 case 108404047:
                                    if (n.equals(method_MO("\u0088\u009f\u0089\u009f\u008e"))) {
                                       x = 32525 ^ -29766 ^ 2413 ^ -557;
                                    }
                                    break;
                                 case 108511772:
                                    if (n.equals(method_MO("\u0088\u0093\u009d\u0092\u008e"))) {
                                       x = 13336 ^ -22098 ^ 6190 ^ -31333;
                                    }
                                    break;
                                 case 109582100:
                                    if (n.equals(method_MO("\u0089\u0094\u009f\u009b\u0091"))) {
                                       x = 12993 ^ -14484 ^ 28988 ^ -31594;
                                    }
                                    break;
                                 case 1745424865:
                                    if (n.equals(method_MO("\u0096\u009f\u009c\u008e\u0099\u0096\u0093\u0099\u0091"))) {
                                       x = 26029 ^ -1412 ^ 31703 ^ -7166;
                                    }
                                    break;
                                 case 2121976803:
                                    if (n.equals(method_MO("\u0098\u009b\u0099\u0091\u008d\u009b\u0088\u009e"))) {
                                       x = 7471 ^ -19130 ^ 19858 ^ -6662;
                                    }
                              }

                              switch (x) {
                                 case 0:
                                    this.method_Mh().forEach((a) -> {
                                       method_OQ(method_ON(a), (boolean)(!method_OP(method_OO(a)) ? 20561 ^ -15316 ^ 21472 ^ -14436 : 29486 ^ -20235 ^ 4481 ^ -11686));
                                    });
                                    break;
                                 case 1:
                                    this.method_Mh().forEach((a) -> {
                                       method_OM(method_OJ(a), (boolean)(!method_OL(method_OK(a)) ? 15583 ^ -26283 ^ 15701 ^ -26402 : 29996 ^ -31776 ^ 29488 ^ -31236));
                                    });
                                    break;
                                 case 2:
                                    this.method_Mh().forEach((a) -> {
                                       method_OI(method_OF(a), (boolean)(!method_OH(method_OG(a)) ? 19019 ^ -9826 ^ 12135 ^ -17229 : 10812 ^ -12972 ^ 18420 ^ -24420));
                                    });
                                    break;
                                 case 3:
                                    this.method_Mh().forEach((a) -> {
                                       method_OE(method_OB(a), (boolean)(!method_OD(method_OC(a)) ? 21998 ^ -18895 ^ 2905 ^ -6009 : 17782 ^ -12488 ^ 17459 ^ -12675));
                                    });
                                    break;
                                 case 4:
                                    this.method_Mh().forEach((a) -> {
                                       method_OA(method_Ox(a), (boolean)(!method_Oz(method_Oy(a)) ? 12448 ^ -22266 ^ 26471 ^ -320 : 14981 ^ -21928 ^ 6491 ^ -30330));
                                    });
                                    break;
                                 case 5:
                                    this.method_Mh().forEach((a) -> {
                                       method_Ow(method_Ot(a), (boolean)(!method_Ov(method_Ou(a)) ? 11230 ^ -24833 ^ 11223 ^ -24841 : 24629 ^ -28630 ^ 24172 ^ -20877));
                                    });
                                    break;
                                 case 6:
                                    this.method_Mh().forEach((a) -> {
                                       method_Os(method_Op(a), (boolean)(!method_Or(method_Oq(a)) ? 13909 ^ -14190 ^ 1056 ^ -1306 : 2996 ^ -16825 ^ 28949 ^ -15130));
                                    });
                                    break;
                                 case 7:
                                    this.method_Mh().forEach((a) -> {
                                       method_Oo(method_Ol(a), (boolean)(!method_On(method_Om(a)) ? 24863 ^ -13246 ^ 30946 ^ -10818 : 1281 ^ -2566 ^ 26019 ^ -27304));
                                    });
                                    break;
                                 case 8:
                                    this.method_Mh().forEach((a) -> {
                                       method_Ok(method_Oh(a), (boolean)(!method_Oj(method_Oi(a)) ? 16097 ^ -25910 ^ 20350 ^ -5292 : 20228 ^ -27615 ^ 8965 ^ -2016));
                                    });
                                    break;
                                 case 9:
                                    this.method_Mh().forEach((a) -> {
                                       method_NQ(method_NP(a), (boolean)(8047 ^ -17459 ^ 23817 ^ -1621));
                                       method_NS(method_NR(a), (boolean)(22520 ^ -31450 ^ 5735 ^ -15175));
                                       method_NU(method_NT(a), (boolean)(22526 ^ -18409 ^ 10038 ^ -14113));
                                       method_NW(method_NV(a), (boolean)(21212 ^ -15619 ^ 6745 ^ -30088));
                                       method_NY(method_NX(a), (boolean)(15864 ^ -13637 ^ 21690 ^ -23559));
                                       method_Oa(method_NZ(a), (boolean)(30743 ^ -8071 ^ 31337 ^ -7673));
                                       method_Oc(method_Ob(a), (boolean)(20653 ^ -4370 ^ 10558 ^ -26755));
                                       method_Oe(method_Od(a), (boolean)(10265 ^ -18737 ^ 22362 ^ -13940));
                                       method_Og(method_Of(a), (boolean)(8411 ^ -20415 ^ 11191 ^ -17619));
                                    });
                                    this.method_Mg(0cT.method_byX(method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0091\u009f\u0083\u0098\u0095\u009b\u0088\u009eÔ\u0088\u009f\u0089\u009f\u008e")));
                                    return;
                                 default:
                                    var27 = method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0091\u009f\u0083\u0098\u0095\u009b\u0088\u009eÔ\u0089\u0083\u0094\u008e\u009b\u0082");
                                    var10002 = new Object[28096 ^ -5100 ^ 26401 ^ -6412];
                                    var10002[31335 ^ -16846 ^ 22197 ^ -27936] = this.method_bzi();
                                    this.method_Mg(0cT.method_byW(var27, var10002));
                                    return;
                              }

                              var27 = method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0091\u009f\u0083\u0098\u0095\u009b\u0088\u009eÔ\u0093\u0094\u009c\u0095");
                              var10002 = new Object[27535 ^ -27876 ^ 19799 ^ -19003];
                              var10002[1059 ^ -27060 ^ 31382 ^ -5895] = I[8928 ^ -4715 ^ 19241 ^ -31651];
                              this.method_Mg(0cT.method_byW(var27, var10002));
                           } else {
                              var27 = method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0091\u009f\u0083\u0098\u0095\u009b\u0088\u009eÔ\u0089\u0083\u0094\u008e\u009b\u0082");
                              var10002 = new Object[2726 ^ -1594 ^ 18827 ^ -17686];
                              var10002[27989 ^ -17207 ^ 6119 ^ -14725] = this.method_bzi();
                              this.method_Mg(0cT.method_byW(var27, var10002));
                           }
                        } else if (I[3293 ^ -2296 ^ 3149 ^ -2152].equalsIgnoreCase(method_MO("\u009d\u0095\u008e\u0095"))) {
                           if (I.length == (8233 ^ -25751 ^ 13391 ^ -28917)) {
                              D = 0eb.parseInt(I[15897 ^ -13901 ^ 8957 ^ -10922], 12153 ^ -16987 ^ 29366 ^ -8086);
                              x = 0eb.parseInt(I[30363 ^ -3979 ^ 8099 ^ -26289], 28301 ^ -25910 ^ 26275 ^ -27932);
                              F = 0eb.parseInt(I[18070 ^ -6159 ^ 30894 ^ -9782], 14966 ^ -16175 ^ 20678 ^ -21919);
                              var27 = method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u009d\u0095\u008e\u0095Ô\u0093\u0094\u009c\u0095");
                              var10002 = new Object[13930 ^ -31974 ^ 14712 ^ -29685];
                              var10002[22143 ^ -24126 ^ 27228 ^ -25119] = D;
                              var10002[31832 ^ -27580 ^ 1609 ^ -4524] = x;
                              var10002[11741 ^ -12926 ^ 18501 ^ -22504] = F;
                              this.method_Mg(0cT.method_byW(var27, var10002));
                              G = new BlockPos(D, x, F);
                              this.method_Mh().forEach((b) -> {
                                 b.getFunction().method_cR(new 0z(b, G));
                                 0eh.method_bFu(method_NO(this));
                              });
                           } else {
                              var10000 = method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u009d\u0095\u008e\u0095Ô\u0089\u0083\u0094\u008e\u009b\u0082");
                              var10001 = new Object[17057 ^ -5152 ^ 8327 ^ -30265];
                              var10001[30880 ^ -9751 ^ 20164 ^ -4211] = this.method_bzi();
                              0ek.addMessage(0cT.method_byW(var10000, var10001));
                           }
                        } else if (I[14235 ^ -2877 ^ 12283 ^ -4957].equalsIgnoreCase(method_MO("\u008e\u0092\u0088\u009f\u009b\u009e\u0089"))) {
                           Iterator var25 = Thread.getAllStackTraces().keySet().iterator();

                           while(var25.hasNext()) {
                              Thread H = (Thread)var25.next();
                              var10000 = method_MO("ß\u0089ÚÚß\u0089");
                              var10001 = new Object[32705 ^ -6378 ^ 10195 ^ -16634];
                              var10001[421 ^ -14835 ^ 17673 ^ -32095] = H.getClass().getSimpleName();
                              var10001[29927 ^ -9689 ^ 18943 ^ -6338] = H.getName();
                              0ek.addMessage(String.format(var10000, var10001));
                           }
                        } else {
                           if (method_NL().method_bwh().method_byu(I[29501 ^ -26155 ^ 31197 ^ -27851], I)) {
                              return;
                           }

                           this.method_bzf();
                        }
                     }
                  }
               }
            }
         } else if (I.length >= (24732 ^ -25042 ^ 19431 ^ -19114)) {
            if (method_MR().method_bna() && J.size() == 0) {
               0ek.addMessage(0cT.method_byX(method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0099\u0095\u0094\u0094\u009f\u0099\u008eÔ\u008a\u0088\u0095\u0082\u0083Ô\u009f\u0097\u008a\u008e\u0083")));
               return;
            }

            n = null;
            x = 306 ^ -5606 ^ 2502 ^ -7442;
            if (I.length >= (8509 ^ -4675 ^ 21359 ^ -24597)) {
               if (I[17721 ^ -19893 ^ 9844 ^ -12027].contains(method_MO("À"))) {
                  n = I[14463 ^ -5701 ^ 10520 ^ -1825].split(method_MO("À"))[4891 ^ -1792 ^ 15600 ^ -10517];
                  x = Integer.parseInt(I[22740 ^ -5993 ^ 8447 ^ -28481].split(method_MO("À"))[13406 ^ -10347 ^ 5222 ^ -2132]);
               } else {
                  n = I[15923 ^ -21721 ^ 26620 ^ -3349];
                  x = '펫' ^ '엽' ^ 8390 ^ 21837;
               }
            }

            method_MS((boolean)(4446 ^ -19510 ^ 21841 ^ -2108));
            var10000 = method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0099\u0095\u0094\u0094\u009f\u0099\u008eÔ\u0093\u0094\u009c\u0095");
            var10001 = new Object[12434 ^ -11686 ^ 31395 ^ -26518];
            var10001[3145 ^ -26621 ^ 21031 ^ -14739] = I[7681 ^ -1903 ^ 5545 ^ -3272];
            0ek.addMessage(0cT.method_byW(var10000, var10001));

            for(F = 8377 ^ -32670 ^ 6967 ^ -17428; F < 0eb.parseInt(I[19089 ^ -11511 ^ 3955 ^ -26902], 11298 ^ -17493 ^ 18055 ^ -12017); ++F) {
               if (method_MT()) {
                  0a.runBot(K.method_jU(), method_MU().method_bna() ? J.method_kg() : 0dC.method_bBI(), n == null ? 0ef.method_bFj() : n, n == null ? 0ef.method_bFi() : x);
                  0eh.method_bFu(Math.abs(0eb.parseInt(I[19542 ^ -16573 ^ 12772 ^ -15629], 17838 ^ -8481 ^ 15885 ^ -23198)));
               }
            }
         } else {
            var10000 = method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u0099\u0095\u0094\u0094\u009f\u0099\u008eÔ\u0089\u0083\u0094\u008e\u009b\u0082");
            var10001 = new Object[4500 ^ -30938 ^ 28861 ^ -6642];
            var10001[27502 ^ -14130 ^ 23342 ^ -1906] = this.method_bzi();
            0ek.addMessage(0cT.method_byW(var10000, var10001));
         }

      }
   }

   private static double method_Nr(0f var0) {
      return var0.posY;
   }

   private static int method_NO(0bs var0) {
      return var0.field_a;
   }

   private static 0cG method_NE() {
      return botManager;
   }

   private static 0cp method_MU() {
      return 0bH.field_a;
   }

   private static 0g method_Of(0a var0) {
      return var0.keyboard;
   }

   private static int method_Ns(0bs var0) {
      return var0.field_a;
   }

   private static 0f method_OZ(0a var0) {
      return var0.player;
   }

   private static 0g method_OF(0a var0) {
      return var0.keyboard;
   }

   private static void method_OE(0g var0, boolean var1) {
      var0.keyBindRight = var1;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_MO(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 14612 ^ -9869 ^ 13899 ^ -10708; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 24150 ^ -29975 ^ 21956 ^ -32383));
      }

      return var1.toString();
   }

   private static void method_Og(0g var0, boolean var1) {
      var0.keyBindSprint = var1;
   }

   private static void method_OI(0g var0, boolean var1) {
      var0.keyBindLeft = var1;
   }

   private static 0g method_NR(0a var0) {
      return var0.keyboard;
   }

   private static 0cG method_NM() {
      return botManager;
   }

   private static boolean method_MT() {
      return field_b;
   }

   private static jh method_Nt() {
      return nC.player;
   }

   private static float method_Pn(Vector2f var0) {
      return var0.x;
   }

   private static 0g method_Ou(0a var0) {
      return var0.keyboard;
   }

   private static boolean method_Oj(0g var0) {
      return var0.keyBindSprint;
   }

   private static 0cG method_MY() {
      return botManager;
   }

   private static int method_MW(0bs var0) {
      return var0.field_a;
   }

   private static float method_Pm(Vector2f var0) {
      return var0.y;
   }

   private static 0f method_PJ(0a var0) {
      return var0.player;
   }

   private static int method_Py(0bs var0) {
      return var0.field_a;
   }

   private static 0g method_Ob(0a var0) {
      return var0.keyboard;
   }

   public _bs/* $FF was: 0bs*/() {
      super(method_MO("\u0098\u0095\u008e\u0089"), method_MO("\u0099\u0095\u0097\u0097\u009b\u0094\u009eÔ\u0098\u0095\u008e\u0089Ô\u009e\u009f\u0089\u0099\u0088\u0093\u008a\u008e\u0093\u0095\u0094"));
   }

   private static 0g method_ON(0a var0) {
      return var0.keyboard;
   }

   private static void method_OA(0g var0, boolean var1) {
      var0.keyBindAttack = var1;
   }

   private static 0cG method_NN() {
      return botManager;
   }

   private static 0g method_Od(0a var0) {
      return var0.keyboard;
   }

   private static boolean method_Ov(0g var0) {
      return var0.keyBindUseItem;
   }

   private static double method_Ny(jh var0) {
      return var0.posZ;
   }

   private static void method_Ok(0g var0, boolean var1) {
      var0.keyBindSprint = var1;
   }

   private static 0g method_Oi(0a var0) {
      return var0.keyboard;
   }

   private static int method_Pd(0bs var0) {
      return var0.field_a;
   }

   private static 0cG method_Nm() {
      return botManager;
   }

   private static void method_MS(boolean var0) {
      field_b = var0;
   }

   private static int method_PC(0bs var0) {
      return var0.field_a;
   }

   private static 0g method_OB(0a var0) {
      return var0.keyboard;
   }

   private static 0i method_OW(0a var0) {
      return var0.controller;
   }

   private static void method_Os(0g var0, boolean var1) {
      var0.keyBindJump = var1;
   }

   private static float method_Pu(0f var0) {
      return var0.rotationYaw;
   }

   private static PrintStream method_MV() {
      return System.out;
   }

   private static 0f method_Pt(0a var0) {
      return var0.player;
   }

   private static int method_Nj(0bs var0) {
      return var0.field_a;
   }

   private static 0f method_Nq(0a var0) {
      return var0.player;
   }

   private static EnumHand method_Pv() {
      return EnumHand.MAIN_HAND;
   }

   private static EnumHand method_OX() {
      return EnumHand.MAIN_HAND;
   }

   private static jh method_Nx() {
      return nC.player;
   }

   private static 0g method_NT(0a var0) {
      return var0.keyboard;
   }

   private static 0g method_OG(0a var0) {
      return var0.keyboard;
   }

   private static File method_No(nC var0) {
      return var0.gameDir;
   }

   private static void method_NU(0g var0, boolean var1) {
      var0.keyBindForward = var1;
   }

   private static 0g method_OJ(0a var0) {
      return var0.keyboard;
   }

   private static 0g method_NV(0a var0) {
      return var0.keyboard;
   }

   private static 0g method_NP(0a var0) {
      return var0.keyboard;
   }

   private static int method_PA(0bs var0) {
      return var0.field_a;
   }

   private static 0cG method_Nd() {
      return botManager;
   }

   private static boolean method_OD(0g var0) {
      return var0.keyBindRight;
   }

   private static co method_Pf() {
      return Nk.AIR;
   }

   private static 0f method_Pi(0a var0) {
      return var0.player;
   }

   private static int method_PD(0bs var0) {
      return var0.field_a;
   }

   private static jh method_Nv() {
      return nC.player;
   }

   private static 0g method_Oy(0a var0) {
      return var0.keyboard;
   }

   private static HashMap method_Nb() {
      return 0en.field_a;
   }

   private static 0cG method_ND() {
      return botManager;
   }

   private static int method_PH(0bs var0) {
      return var0.field_a;
   }

   private static jh method_Np() {
      return nC.player;
   }

   private static 0g method_OK(0a var0) {
      return var0.keyboard;
   }

   private static int method_OT(0bs var0) {
      return var0.field_a;
   }

   private static void method_MX(boolean var0) {
      field_b = var0;
   }

   private static boolean method_On(0g var0) {
      return var0.keyBindSneak;
   }

   private static 0cG method_NL() {
      return botManager;
   }

   private static 0g method_Oq(0a var0) {
      return var0.keyboard;
   }

   private static void method_Pc(0f var0, float var1) {
      var0.rotationPitch = var1;
   }

   private static 0cG method_NC() {
      return botManager;
   }

   private static 0g method_Oh(0a var0) {
      return var0.keyboard;
   }

   private static 0cG method_NB() {
      return botManager;
   }

   private static void method_Pa(0f var0, float var1) {
      var0.rotationYaw = var1;
   }

   private static 0cG method_Na() {
      return botManager;
   }

   private static 0cG method_Nl() {
      return botManager;
   }

   private static boolean method_Or(0g var0) {
      return var0.keyBindJump;
   }

   private static boolean method_OH(0g var0) {
      return var0.keyBindLeft;
   }

   private static 0f method_Pb(0a var0) {
      return var0.player;
   }

   private static 0cG method_Nz() {
      return botManager;
   }

   private static 0cG method_NF() {
      return botManager;
   }

   private static 0cG method_NG() {
      return botManager;
   }

   private static 0g method_Ot(0a var0) {
      return var0.keyboard;
   }

   private static 0g method_Ol(0a var0) {
      return var0.keyboard;
   }

   private static void method_Ow(0g var0, boolean var1) {
      var0.keyBindUseItem = var1;
   }

   private static 0cG method_Nc() {
      return botManager;
   }

   private static 0f method_PI(0a var0) {
      return var0.player;
   }

   private static int method_OY(0bs var0) {
      return var0.field_a;
   }

   private static void method_NW(0g var0, boolean var1) {
      var0.keyBindBack = var1;
   }

   private static boolean method_Oz(0g var0) {
      return var0.keyBindAttack;
   }

   private static void method_Oa(0g var0, boolean var1) {
      var0.keyBindRight = var1;
   }

   private static 0g method_Om(0a var0) {
      return var0.keyboard;
   }

   private static int method_Ng(0bs var0) {
      return var0.field_a;
   }

   private static nC method_Nn() {
      return mc;
   }

   private static ClickType method_Nf() {
      return ClickType.THROW;
   }

   private static void method_Oe(0g var0, boolean var1) {
      var0.keyBindSneak = var1;
   }

   private static void method_Oo(0g var0, boolean var1) {
      var0.keyBindSneak = var1;
   }

   private static 0cG method_NK() {
      return botManager;
   }

   private static 0bi method_Ps(0a var0) {
      return var0.networkManager;
   }

   private static 0f method_Pk(0a var0) {
      return var0.player;
   }

   private static void method_NQ(0g var0, boolean var1) {
      var0.keyBindUseItem = var1;
   }

   private static double method_Nw(jh var0) {
      return var0.posY;
   }

   private static 0cG method_MP() {
      return botManager;
   }

   private static double method_Ph(0f var0) {
      return var0.posX;
   }

   private static 0g method_Op(0a var0) {
      return var0.keyboard;
   }

   private static int method_OS(0bs var0) {
      return var0.field_a;
   }

   private static 0g method_Ox(0a var0) {
      return var0.keyboard;
   }

   private static ClickType method_PF() {
      return ClickType.PICKUP;
   }

   private static 0d method_Pz(0a var0) {
      return var0.mc;
   }

   private static 0f method_Pw(0a var0) {
      return var0.player;
   }

   private static 0cG method_NI() {
      return botManager;
   }

   private List<0a> method_Mh() {
      return (List)0e.getOnline().stream().filter((a) -> {
         return (boolean)(!method_NM().method_bwk().contains(a.getNickname()) && method_NN().method_bwk().size() != 0 ? 29635 ^ -17171 ^ 1808 ^ -14274 : 26776 ^ -28152 ^ 21436 ^ -22227);
      }).collect(Collectors.toList());
   }

   private static 0bo method_Pe(0a var0) {
      return var0.world;
   }

   private static 0f method_Pq(0a var0) {
      return var0.player;
   }

   private static void method_NS(0g var0, boolean var1) {
      var0.keyBindAttack = var1;
   }

   private static int method_PB(0bs var0) {
      return var0.field_a;
   }

   private static void method_NY(0g var0, boolean var1) {
      var0.keyBindLeft = var1;
   }

   private static 0cG method_MQ() {
      return botManager;
   }

   private static double method_Pj(0f var0) {
      return var0.posY;
   }

   private static int method_OU(0bs var0) {
      return var0.field_a;
   }

   private static void method_Oc(0g var0, boolean var1) {
      var0.keyBindJump = var1;
   }

   private static HashMap method_MZ() {
      return 0en.field_a;
   }

   private static 0cp method_MR() {
      return 0bH.field_a;
   }

   private static int method_PG(0bs var0) {
      return var0.field_a;
   }

   private static void method_OM(0g var0, boolean var1) {
      var0.keyBindBack = var1;
   }

   private static EnumHand method_Px() {
      return EnumHand.MAIN_HAND;
   }

   private static void method_Pp(0f var0, float var1) {
      var0.rotationYaw = var1;
   }

   private static 0i method_Ne(0a var0) {
      return var0.controller;
   }

   private static double method_Nu(jh var0) {
      return var0.posX;
   }

   private static int method_PK(0bs var0) {
      return var0.field_a;
   }

   private static 0cG method_NJ() {
      return botManager;
   }

   private static boolean method_OL(0g var0) {
      return var0.keyBindBack;
   }

   private static boolean method_OP(0g var0) {
      return var0.keyBindForward;
   }

   private static 0cG method_Nk() {
      return botManager;
   }

   private static 0f method_Pg(0a var0) {
      return var0.player;
   }
}
