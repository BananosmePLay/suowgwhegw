package neo;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

class So {
   private final Sz<?> packet;
   private final GenericFutureListener<? extends Future<? super Void>>[] futureListeners;

   public So(Sz<?> inPacket, GenericFutureListener<? extends Future<? super Void>>... inFutureListeners) {
      this.packet = inPacket;
      this.futureListeners = inFutureListeners;
   }

   // $FF: synthetic method
   static Sz access$100(So x0) {
      return x0.packet;
   }

   // $FF: synthetic method
   static GenericFutureListener[] access$200(So x0) {
      return x0.futureListeners;
   }
}
