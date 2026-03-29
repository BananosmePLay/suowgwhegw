package neo;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import net.minecraft.network.Packet;

class 0cO {
   public final Packet<?> packet;
   public final GenericFutureListener<? extends Future<? super Void>>[] futureListeners;

   // $FF: synthetic method
   static GenericFutureListener[] access$100(0cO x0) {
      return OQH4DvbQoj(x0);
   }

   private static Packet P0YTYJTx6i(0cO var0) {
      return var0.packet;
   }

   // $FF: synthetic method
   static Packet access$000(0cO x0) {
      return P0YTYJTx6i(x0);
   }

   private static GenericFutureListener[] OQH4DvbQoj(0cO var0) {
      return var0.futureListeners;
   }

   public _cO/* $FF was: 0cO*/(Packet<?> inPacket, GenericFutureListener<? extends Future<? super Void>>... inFutureListeners) {
      this.packet = inPacket;
      this.futureListeners = inFutureListeners;
   }
}
