package neo;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

class 0bh {
   public final GenericFutureListener<? extends Future<? super Void>>[] listener;
   public final Sz<?> packet;
   private static String _ _;

   private static Sz yBBqwb65G9(0bh var0) {
      return var0.packet;
   }

   // $FF: synthetic method
   static Sz access$000(0bh a) {
      return yBBqwb65G9(a);
   }

   private static GenericFutureListener[] Wi3Q28Atw1(0bh var0) {
      return var0.listener;
   }

   @SafeVarargs
   public _bh/* $FF was: 0bh*/(Sz<?> a, GenericFutureListener<? extends Future<? super Void>>... b) {
      this.packet = a;
      this.listener = b;
   }

   // $FF: synthetic method
   static GenericFutureListener[] access$100(0bh a) {
      return Wi3Q28Atw1(a);
   }
}
