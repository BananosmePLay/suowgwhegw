package neo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class 0be extends ArrayList<0ba> implements 0cE<0ba>, Runnable {
   public static final Logger field_b = LogManager.getLogger();
   public final Deque<0da> field_a = new ArrayDeque();
   private static String _DSC GG NEOWARECLIENT _;

   public void method_LG() {
      try {
         File a = 0ed.method_bFf(method_LJ("ǬƍƦƬƔƢƱƦǬƀƫƱƬƮƦǬ"));
         FileUtils.deleteDirectory(a);
         if (!a.exists()) {
            a.mkdirs();
         }
      } catch (IOException var2) {
         IOException b = var2;
         b.printStackTrace();
      }

   }

   public 0ba method_LF(String a) {
      return (0ba)this.stream().filter((b) -> {
         return b.method_Lu().equalsIgnoreCase(a);
      }).findAny().orElse((Object)null);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void register(Object var1) {
      this.method_LE((0ba)var1);
   }

   private static 0ct method_LN() {
      return 0bJ.field_g;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_LJ(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 21869 ^ -1180 ^ 29490 ^ -8901; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 32029 ^ -5331 ^ 14258 ^ -24511));
      }

      return var1.toString();
   }

   private static Deque method_LK(0be var0) {
      return var0.field_a;
   }

   private static Deque method_LL(0be var0) {
      return var0.field_a;
   }

   public _be/* $FF was: 0be*/() {
      this.init();
   }

   private static Logger method_LO() {
      return field_b;
   }

   public void run() {
      while(true) {
         0da c = (0da)method_LL(this).pollFirst();
         if (c != null) {
            0ba b = this.method_LF(method_LM().method_bnq());
            if (b != null) {
               Thread a = new Thread(() -> {
                  try {
                     b.method_Lt(c);
                  } catch (IOException var3) {
                     IOException a = var3;
                     method_LO().error(method_LJ("ƖƭƢơƯƦǣƷƬǣƬƳƦƭǣƴƦơ"), a);
                  }

               });
               String var10001 = method_LJ("ƔƦơƎƢƭƢƤƦƱǣƷƢưƨǣǠǦư");
               Object[] var10002 = new Object[2420 ^ -12525 ^ 32734 ^ -17992];
               var10002[2039 ^ -3250 ^ 18233 ^ -19584] = c.hashCode();
               a.setName(String.format(var10001, var10002));
               a.start();
            }
         }

         0eh.method_bFt((long)method_LN().method_bnH());
      }
   }

   private static 0cs method_LM() {
      return 0bJ.field_a;
   }

   public void method_LE(0ba a) {
      this.add(a);
   }

   public void init() {
      this.method_LE(new 0bb());
      this.method_LE(new 0bc());
      this.method_LE(new 0bd());
      Thread a = new Thread(this);
      a.setName(method_LJ("ƔƦơƎƢƭƢƤƦƱǣƗƢưƨ"));
      a.start();
      this.method_LG();
   }

   public void method_LD(0da a) {
      method_LK(this).addLast(a);
   }
}
