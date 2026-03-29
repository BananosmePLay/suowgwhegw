package neo;

import com.google.common.collect.Queues;
import de.florianmichael.vialoadingbase.netty.event.CompressionReorderEvent;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.local.LocalChannel;
import io.netty.channel.local.LocalServerChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.net.InetAddress;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.annotation.Nullable;
import net.minecraft.util.ITickable;
import net.minecraft.util.LazyLoadBase;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;

public class 0bi extends SimpleChannelInboundHandler<Sz<?>> {
   public final ReentrantReadWriteLock readWriteLock;
   public RH packetListener;
   public final 0a bot;
   public Channel channel;
   public final Queue<0bh> outboundPacketsQueue;
   private static String _DSC GG NEOWARECLIENT _;

   private static 0cp LvTobrG0d1() {
      return 0bF.field_a;
   }

   public void exceptionCaught(ChannelHandlerContext a, Throwable b) {
      if (!b.toString().contains(24Kvy4iHVQ("6=,,!v")) && !b.toString().contains(24Kvy4iHVQ("29.9v617v;0966=4+v\u001b47+=<\u001b0966=4\u001d ;=(,176"))) {
         0cG.method_bwd().method_bwg().method_j().method_bL(7FbT0uF6GA(this));
         if (LvTobrG0d1().method_bna()) {
            String var10000 = 24Kvy4iHVQ(":7,v(49!v<1+;766=;,");
            Object[] var10001 = new Object[17920 ^ -30170 ^ 8485 ^ -4862];
            var10001[8312 ^ -16026 ^ 13447 ^ -10855] = 9ujXIQvFdf(this).getNickname();
            0ek.addMessage(0cT.method_byW(var10000, var10001));
            0ek.addException((Exception)b);
         }

         b.printStackTrace();
         Y4in1risHG(this).disconnect();
      }

   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String _4Kvy4iHVQ/* $FF was: 24Kvy4iHVQ*/(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 24130 ^ -23643 ^ 12505 ^ -12994; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 20128 ^ -23459 ^ 17541 ^ -20960));
      }

      return var1.toString();
   }

   public void processReceivedPackets() {
      this.flushOutboundQueue();
      if (380GyJuR22(this) instanceof ITickable) {
         ((ITickable)Cs6QyDVUEV(this)).update();
      }

      if (xlQhA1J7hI(this) != null) {
         keB4wyIhK7(this).flush();
      }

   }

   public void sendPacket(Sz<?> a) {
      if (this.isChannelOpen()) {
         this.flushOutboundQueue();
         this.dispatchPacket(a, (GenericFutureListener[])null);
      } else {
         DURjHIbVOQ(this).writeLock().lock();
         boolean var4 = false;

         try {
            var4 = true;
            v2JSR9HwXa(this).add(new 0bh(a, new GenericFutureListener[30066 ^ -31627 ^ 30949 ^ -30238]));
            var4 = false;
         } finally {
            if (var4) {
               nsaPtyWliF(this).writeLock().unlock();
            }
         }

         Qw3vIQab5t(this).writeLock().unlock();
      }

   }

   private static ReentrantReadWriteLock AoaDeWWoWL(0bi var0) {
      return var0.readWriteLock;
   }

   private static Channel fdNOa4o2rI(0bi var0) {
      return var0.channel;
   }

   private static Channel P0MonXEWZA(0bi var0) {
      return var0.channel;
   }

   private static Channel xlQhA1J7hI(0bi var0) {
      return var0.channel;
   }

   private static Queue _ZWqg4tjei/* $FF was: 0ZWqg4tjei*/(0bi var0) {
      return var0.outboundPacketsQueue;
   }

   private static Channel z9xxCWMBAT(0bi var0) {
      return var0.channel;
   }

   public boolean hasNoChannel() {
      return (boolean)(knKXR6OnAF(this) == null ? 1984 ^ -23482 ^ 1121 ^ -22554 : 5810 ^ -20941 ^ 20495 ^ -6002);
   }

   private static void grwXJ1m1F0(0bi var0, Channel var1) {
      var0.channel = var1;
   }

   private static ReentrantReadWriteLock nsaPtyWliF(0bi var0) {
      return var0.readWriteLock;
   }

   private static Channel w9VCAhVVmt(0bi var0) {
      return var0.channel;
   }

   private void dispatchPacket(Sz<?> b, @Nullable GenericFutureListener<? extends Future<? super Void>>[] c) {
      RB d = RB.getFromPacket(b);
      RB e = (RB)tnbFRv8NLL(this).attr(Ox6JUtSwNf()).get();
      if (e != d) {
         w9VCAhVVmt(this).config().setAutoRead((boolean)(6421 ^ -10280 ^ 445 ^ -12432));
      }

      if (uxyj8RZRqg(this).eventLoop().inEventLoop()) {
         if (d != e) {
            this.setConnectionState(d);
         }

         ChannelFuture a = z9xxCWMBAT(this).writeAndFlush(b);
         if (c != null) {
            a.addListeners(c);
         }

         a.addListener(YtGhiPVVOV());
      } else {
         TEYbe54Bgr(this).eventLoop().execute(() -> {
            if (d != e) {
               this.setConnectionState(d);
            }

            ChannelFuture ex = frt47EsTcM(this).writeAndFlush(b);
            if (c != null) {
               ex.addListeners(c);
            }

            ex.addListener(yae920TlQV());
         });
      }

   }

   private static RB _WOBCrOggs/* $FF was: 9WOBCrOggs*/() {
      return RB.HANDSHAKING;
   }

   public void closeChannel() {
      if (letdolwWGT(this).isOpen()) {
         try {
            try {
               lLf2G2QYF0(this).close().sync();
            } catch (Exception var2) {
               R4woD0BYn6(this).close();
            }
         } catch (Throwable var3) {
         }
      }

   }

   private static Channel uxyj8RZRqg(0bi var0) {
      return var0.channel;
   }

   private static Channel MO7y2aKiWX(0bi var0) {
      return var0.channel;
   }

   private static Channel _Ngfx9adOJ/* $FF was: 1Ngfx9adOJ*/(0bi var0) {
      return var0.channel;
   }

   @SafeVarargs
   public final void sendPacket(Sz<?> a, GenericFutureListener<? extends Future<? super Void>> b, GenericFutureListener<? extends Future<? super Void>>... c) {
      if (this.isChannelOpen()) {
         this.flushOutboundQueue();
         this.dispatchPacket(a, (GenericFutureListener[])ArrayUtils.add(c, 760 ^ -23562 ^ 12902 ^ -27800, b));
      } else {
         AoaDeWWoWL(this).writeLock().lock();
         boolean var6 = false;

         try {
            var6 = true;
            0ZWqg4tjei(this).add(new 0bh(a, (GenericFutureListener[])ArrayUtils.add(c, 14827 ^ -26419 ^ 24228 ^ -126, b)));
            var6 = false;
         } finally {
            if (var6) {
               exw3PbQVGB(this).writeLock().unlock();
            }
         }

         NN1uyFT8CB(this).writeLock().unlock();
      }

   }

   private static Channel Fec9AEP0pn(0bi var0) {
      return var0.channel;
   }

   private static Channel _YbeyG4qt8/* $FF was: 6YbeyG4qt8*/(0bi var0) {
      return var0.channel;
   }

   private static AttributeKey M2WqJfxaX4() {
      return Sp.PROTOCOL_ATTRIBUTE_KEY;
   }

   private static Channel TEYbe54Bgr(0bi var0) {
      return var0.channel;
   }

   public boolean isChannelOpen() {
      return (boolean)(YDyngiLTou(this) != null && 6ml0QeYl0e(this).isOpen() ? 7187 ^ -29221 ^ 16547 ^ -11926 : 31453 ^ -2433 ^ 254 ^ -29604);
   }

   private static Channel FMG6wcNbTb(0bi var0) {
      return var0.channel;
   }

   private static Channel _eeTxjO2k9/* $FF was: 9eeTxjO2k9*/(0bi var0) {
      return var0.channel;
   }

   private static Channel DnD7F5gVTR(0bi var0) {
      return var0.channel;
   }

   public _bi/* $FF was: 0bi*/(0a a) {
      this.bot = a;
      this.outboundPacketsQueue = Queues.newConcurrentLinkedQueue();
      this.readWriteLock = new ReentrantReadWriteLock();
   }

   private static 0a _ujXIQvFdf/* $FF was: 9ujXIQvFdf*/(0bi var0) {
      return var0.bot;
   }

   private static Channel PDFln2dSp7(0bi var0) {
      return var0.channel;
   }

   private void flushOutboundQueue() {
      if (MO7y2aKiWX(this) != null && QEIowQ9IoF(this).isOpen()) {
         yB1pCAvJt8(this).readLock().lock();

         while(true) {
            boolean var4 = false;

            try {
               var4 = true;
               if (TAi8YITd21(this).isEmpty()) {
                  var4 = false;
                  break;
               }

               0bh a = (0bh)AsdZWQw4hf(this).poll();
               this.dispatchPacket(0bh.access$000(a), 0bh.access$100(a));
            } finally {
               if (var4) {
                  8LWELRlkb6(this).readLock().unlock();
               }
            }
         }

         wdJnDSDt5T(this).readLock().unlock();
      }

   }

   private static Channel YDyngiLTou(0bi var0) {
      return var0.channel;
   }

   public void setCompressionThreshold(int a) {
      if (a >= 0) {
         if (BOjDRTMyZW(this).pipeline().get(24Kvy4iHVQ("<=;75(*=++")) instanceof RZ) {
            ((RZ)glwJNtGQNj(this).pipeline().get(24Kvy4iHVQ("<=;75(*=++"))).setCompressionThreshold(a);
         } else {
            9eeTxjO2k9(this).pipeline().addBefore(24Kvy4iHVQ("<=;7<=*"), 24Kvy4iHVQ("<=;75(*=++"), new RZ(a));
         }

         if (DnD7F5gVTR(this).pipeline().get(24Kvy4iHVQ(";75(*=++")) instanceof Sa) {
            ((Sa)79RjmI0PQY(this).pipeline().get(24Kvy4iHVQ(";75(*=++"))).setCompressionThreshold(a);
         } else {
            9TE9iQNYoa(this).pipeline().addBefore(24Kvy4iHVQ("=6;7<=*"), 24Kvy4iHVQ(";75(*=++"), new Sa(a));
         }
      } else {
         if (6YbeyG4qt8(this).pipeline().get(24Kvy4iHVQ("<=;75(*=++")) instanceof RZ) {
            PDFln2dSp7(this).pipeline().remove(24Kvy4iHVQ("<=;75(*=++"));
         }

         if (Fec9AEP0pn(this).pipeline().get(24Kvy4iHVQ(";75(*=++")) instanceof Sa) {
            a9VYiJJH7z(this).pipeline().remove(24Kvy4iHVQ(";75(*=++"));
         }
      }

      P0MonXEWZA(this).pipeline().fireUserEventTriggered(new CompressionReorderEvent());
   }

   private static RH _80GyJuR22/* $FF was: 380GyJuR22*/(0bi var0) {
      return var0.packetListener;
   }

   private static ReentrantReadWriteLock DURjHIbVOQ(0bi var0) {
      return var0.readWriteLock;
   }

   private static Queue AsdZWQw4hf(0bi var0) {
      return var0.outboundPacketsQueue;
   }

   private static ReentrantReadWriteLock Qw3vIQab5t(0bi var0) {
      return var0.readWriteLock;
   }

   private static Channel iiW9cSkMga(0bi var0) {
      return var0.channel;
   }

   private static Channel keB4wyIhK7(0bi var0) {
      return var0.channel;
   }

   private static Queue v2JSR9HwXa(0bi var0) {
      return var0.outboundPacketsQueue;
   }

   private static Channel _TE9iQNYoa/* $FF was: 9TE9iQNYoa*/(0bi var0) {
      return var0.channel;
   }

   private static ReentrantReadWriteLock _LWELRlkb6/* $FF was: 8LWELRlkb6*/(0bi var0) {
      return var0.readWriteLock;
   }

   private static Channel letdolwWGT(0bi var0) {
      return var0.channel;
   }

   public void channelActive(ChannelHandlerContext a) throws Exception {
      super.channelActive(a);
      grwXJ1m1F0(this, a.channel());

      try {
         this.setConnectionState(9WOBCrOggs());
      } catch (Throwable var3) {
      }

   }

   private static Channel BOjDRTMyZW(0bi var0) {
      return var0.channel;
   }

   private static ChannelFutureListener yae920TlQV() {
      return ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE;
   }

   private static RH _vB1sbJq3v/* $FF was: 0vB1sbJq3v*/(0bi var0) {
      return var0.packetListener;
   }

   private static 0a _FbT0uF6GA/* $FF was: 7FbT0uF6GA*/(0bi var0) {
      return var0.bot;
   }

   public boolean isLocalChannel() {
      return (boolean)(!(1Ngfx9adOJ(this) instanceof LocalChannel) && !(FMG6wcNbTb(this) instanceof LocalServerChannel) ? 9511 ^ -24336 ^ 30519 ^ -3360 : 30525 ^ -458 ^ 29678 ^ -1308);
   }

   private static LazyLoadBase _4HlXDeEeS/* $FF was: 04HlXDeEeS*/() {
      return Sp.CLIENT_NIO_EVENTLOOP;
   }

   private static ReentrantReadWriteLock exw3PbQVGB(0bi var0) {
      return var0.readWriteLock;
   }

   private static ReentrantReadWriteLock NN1uyFT8CB(0bi var0) {
      return var0.readWriteLock;
   }

   private static Channel R4woD0BYn6(0bi var0) {
      return var0.channel;
   }

   public void channelInactive(ChannelHandlerContext a) {
   }

   private static Channel _ml0QeYl0e/* $FF was: 6ml0QeYl0e*/(0bi var0) {
      return var0.channel;
   }

   private static Channel O7FKSgBl06(0bi var0) {
      return var0.channel;
   }

   private static Channel frt47EsTcM(0bi var0) {
      return var0.channel;
   }

   private static ChannelFutureListener YtGhiPVVOV() {
      return ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE;
   }

   public void setConnectionState(RB a) {
      O7FKSgBl06(this).attr(M2WqJfxaX4()).set(a);
      fdNOa4o2rI(this).config().setAutoRead((boolean)(8566 ^ -22980 ^ 15676 ^ -17801));
   }

   private static void vjrl90x4Oe(0bi var0, RH var1) {
      var0.packetListener = var1;
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void channelRead0(ChannelHandlerContext var1, Object var2) throws Exception {
      this.channelRead0(var1, (Sz)var2);
   }

   public static 0bi createNetworkManagerAndConnect(0a a, InetAddress b, int c) {
      0bi d = new 0bi(a);
      ((Bootstrap)((Bootstrap)((Bootstrap)(new Bootstrap()).group((EventLoopGroup)04HlXDeEeS().getValue())).handler(new 0bg(a, d))).channel(NioSocketChannel.class)).connect(b, c);
      return d;
   }

   private static Channel lLf2G2QYF0(0bi var0) {
      return var0.channel;
   }

   private static Channel a9VYiJJH7z(0bi var0) {
      return var0.channel;
   }

   private static AttributeKey Ox6JUtSwNf() {
      return Sp.PROTOCOL_ATTRIBUTE_KEY;
   }

   private static ReentrantReadWriteLock yB1pCAvJt8(0bi var0) {
      return var0.readWriteLock;
   }

   protected void channelRead0(ChannelHandlerContext a, Sz<?> b) {
      if (iiW9cSkMga(this).isOpen()) {
         try {
            b.processPacket(0vB1sbJq3v(this));
         } catch (VE var4) {
         }
      }

   }

   private static RH Cs6QyDVUEV(0bi var0) {
      return var0.packetListener;
   }

   private static Channel glwJNtGQNj(0bi var0) {
      return var0.channel;
   }

   private static ReentrantReadWriteLock wdJnDSDt5T(0bi var0) {
      return var0.readWriteLock;
   }

   private static Channel QEIowQ9IoF(0bi var0) {
      return var0.channel;
   }

   private static Queue TAi8YITd21(0bi var0) {
      return var0.outboundPacketsQueue;
   }

   private static 0a Y4in1risHG(0bi var0) {
      return var0.bot;
   }

   private static Channel _9RjmI0PQY/* $FF was: 79RjmI0PQY*/(0bi var0) {
      return var0.channel;
   }

   public void setNetHandler(RH a) {
      Validate.notNull(a, 24Kvy4iHVQ("(9;3=,\u00141+,=6=*"), new Object[12044 ^ -7910 ^ 29595 ^ -17011]);
      vjrl90x4Oe(this, a);
   }

   private static Channel knKXR6OnAF(0bi var0) {
      return var0.channel;
   }

   private static Channel tnbFRv8NLL(0bi var0) {
      return var0.channel;
   }
}
