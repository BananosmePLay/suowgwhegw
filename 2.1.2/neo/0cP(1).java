package neo;

import com.google.common.collect.Queues;
import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.connection.UserConnectionImpl;
import com.viaversion.viaversion.protocol.ProtocolPipelineImpl;
import de.florianmichael.vialoadingbase.ViaLoadingBase;
import de.florianmichael.vialoadingbase.netty.event.CompressionReorderEvent;
import de.florianmichael.viamcp.MCPVLBPipeline;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.local.LocalChannel;
import io.netty.channel.local.LocalServerChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.proxy.HttpProxyHandler;
import io.netty.handler.proxy.Socks4ProxyHandler;
import io.netty.handler.proxy.Socks5ProxyHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.TimeoutException;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.GenericFutureListener;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.annotation.Nullable;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.EnumPacketDirection;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NettyCompressionDecoder;
import net.minecraft.network.NettyCompressionEncoder;
import net.minecraft.network.NettyPacketDecoder;
import net.minecraft.network.NettyPacketEncoder;
import net.minecraft.network.NettyVarint21FrameDecoder;
import net.minecraft.network.NettyVarint21FrameEncoder;
import net.minecraft.network.Packet;
import net.minecraft.network.ThreadQuickExitException;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class 0cP extends SimpleChannelInboundHandler<Packet<?>> {
   public static final EventLoopGroup group = Epoll.isAvailable() ? new EpollEventLoopGroup(4275 ^ -29082 ^ 11379 ^ -19808) : new NioEventLoopGroup(30381 ^ -20265 ^ 22053 ^ -28583);
   public INetHandler packetListener;
   public Channel channel;
   public final Queue<0cO> outboundPacketsQueue = Queues.newConcurrentLinkedQueue();
   public static final Logger LOGGER = LogManager.getLogger();
   public static final AttributeKey<EnumConnectionState> PROTOCOL_ATTRIBUTE_KEY = AttributeKey.valueOf(QwW82x8AMW("ƖƔƉƒƉƅƉƊ"));
   public ITextComponent terminationReason;
   public final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
   public final 0cC pbot;
   public static final Class<? extends Channel> channelClass = Epoll.isAvailable() ? EpollSocketChannel.class : NioSocketChannel.class;

   private static ReentrantReadWriteLock boMbXjxz5d(0cP var0) {
      return var0.readWriteLock;
   }

   private static boolean _1aOnBq7uw/* $FF was: 71aOnBq7uw*/(0bv var0) {
      return var0.value;
   }

   private static Channel Xo6G1prauf(0cP var0) {
      return var0.channel;
   }

   public ITextComponent getExitMessage() {
      return O6TvWB3u2j(this);
   }

   private static 0cC jFSxdhd695(0cP var0) {
      return var0.pbot;
   }

   private static Channel N4xa91pwSE(0cP var0) {
      return var0.channel;
   }

   private static Logger XqB2DB4PLY() {
      return LOGGER;
   }

   private static Logger dzbbGOVicq() {
      return LOGGER;
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void channelRead0(ChannelHandlerContext var1, Object var2) throws Exception {
      this.channelRead0(var1, (Packet)var2);
   }

   private static Queue X1S6vvsFt9(0cP var0) {
      return var0.outboundPacketsQueue;
   }

   private static Logger oYv69xDa7V() {
      return LOGGER;
   }

   private static Channel THkyteDltn(0cP var0) {
      return var0.channel;
   }

   public static 0cP createNetworkManagerAndConnect(InetAddress address, int serverPort, 0cC pBot, final 0eq proxy) {
      final 0cP networkmanager = new 0cP(pBot);
      ((Bootstrap)((Bootstrap)((Bootstrap)((Bootstrap)(new Bootstrap()).group(QJZwGq4F6j())).channel(pDoTFrGOb7())).option(X0uSqtW2d3(), Boolean.valueOf((boolean)(23841 ^ -14174 ^ 22796 ^ -13170)))).handler(new ChannelInitializer<Channel>() {
         private static 0bv gvvv9YF1oF() {
            return 0cd.useProxy;
         }

         private static 0eq dqaqaWOR5r(Object var0) {
            return proxy;
         }

         private static 0eq bKTqv7YeHo(Object var0) {
            return proxy;
         }

         private static 0eq y4OVt0d6wu(Object var0) {
            return proxy;
         }

         // $FF: synthetic method
         // $FF: bridge method
         private static String _tj1WgJOWr/* $FF was: 6tj1WgJOWr*/(String var0) {
            StringBuilder var1 = new StringBuilder();

            for(int var2 = 12924 ^ -26657 ^ 22751 ^ -644; var2 < var0.length(); ++var2) {
               var1.append((char)(var0.charAt(var2) ^ 15658 ^ -6615 ^ 20723 ^ -30418));
            }

            return var1.toString();
         }

         private static 0ep fqcjrLdyxi() {
            return 0ep.HTTP;
         }

         private static 0eq ATQ0Mlp7wH(Object var0) {
            return proxy;
         }

         private static 0eq pgC9kKRg6n(Object var0) {
            return proxy;
         }

         private static 0eq _E91ZYvOBr/* $FF was: 3E91ZYvOBr*/(Object var0) {
            return proxy;
         }

         private static 0cP yYr7NwKQD7(Object var0) {
            return networkmanager;
         }

         protected void initChannel(Channel ctx) {
            ChannelPipeline var10000;
            ChannelHandler[] var10001;
            if (TunN7TDOpr(gvvv9YF1oF())) {
               if (bKTqv7YeHo(this).getType().equals(fqcjrLdyxi())) {
                  HttpProxyHandler http = new HttpProxyHandler(new InetSocketAddress(dqaqaWOR5r(this).getProxy().split(6tj1WgJOWr("ˤ"))[14521 ^ -29213 ^ 21749 ^ -7761], Integer.parseInt(pgC9kKRg6n(this).getProxy().split(6tj1WgJOWr("ˤ"))[22455 ^ -24993 ^ 31464 ^ -19711])));
                  http.setConnectTimeoutMillis(9500L);
                  var10000 = ctx.pipeline();
                  var10001 = new ChannelHandler[7954 ^ -8366 ^ 2142 ^ -14305];
                  var10001[20239 ^ -32685 ^ 20409 ^ -32539] = http;
                  var10000.addLast(var10001);
               } else if (DJQ3g7xFNP(this).getType().equals(xxdq3B86Y2())) {
                  Socks4ProxyHandler socks4 = new Socks4ProxyHandler(new InetSocketAddress(wbgwvNsUQC(this).getProxy().split(6tj1WgJOWr("ˤ"))[3968 ^ -6234 ^ 13703 ^ -8799], Integer.parseInt(wSWL7FIwPo(this).getProxy().split(6tj1WgJOWr("ˤ"))[20280 ^ -19110 ^ 10848 ^ -12285])));
                  socks4.setConnectTimeoutMillis(9500L);
                  var10000 = ctx.pipeline();
                  var10001 = new ChannelHandler[8701 ^ -17113 ^ 12069 ^ -19458];
                  var10001[24891 ^ -13217 ^ 30775 ^ -10925] = socks4;
                  var10000.addLast(var10001);
               } else if (rqGlVTbJf3(this).getType().equals(nuAiFRGLoK())) {
                  Socks5ProxyHandler socks5;
                  if (L2SQk6qvBf(this).isAuthenticated()) {
                     socks5 = new Socks5ProxyHandler(new InetSocketAddress(shwLe7DNnh(this).getProxy().split(6tj1WgJOWr("ˤ"))[28535 ^ -13787 ^ 19486 ^ -5812], Integer.parseInt(q1gPDgTJgR(this).getProxy().split(6tj1WgJOWr("ˤ"))[22138 ^ -27104 ^ 13394 ^ -3063])), mAVf41vT10(this).getUsername(), ATQ0Mlp7wH(this).getPassword());
                     socks5.setConnectTimeoutMillis(9500L);
                     var10000 = ctx.pipeline();
                     var10001 = new ChannelHandler[23521 ^ -26304 ^ 19298 ^ -30270];
                     var10001[21903 ^ -9604 ^ 26115 ^ -5648] = socks5;
                     var10000.addLast(var10001);
                  } else {
                     socks5 = new Socks5ProxyHandler(new InetSocketAddress(y4OVt0d6wu(this).getProxy().split(6tj1WgJOWr("ˤ"))[4178 ^ -19755 ^ 299 ^ -23636], Integer.parseInt(3E91ZYvOBr(this).getProxy().split(6tj1WgJOWr("ˤ"))[25358 ^ -7996 ^ 29706 ^ -2111])));
                     socks5.setConnectTimeoutMillis(9500L);
                     var10000 = ctx.pipeline();
                     var10001 = new ChannelHandler[12400 ^ -17618 ^ 10130 ^ -21299];
                     var10001[14668 ^ -27154 ^ 16117 ^ -28073] = socks5;
                     var10000.addLast(var10001);
                  }
               }
            }

            ctx.pipeline().addLast(6tj1WgJOWr("ʪʷʳʻʱʫʪ"), new ReadTimeoutHandler(32316 ^ -4991 ^ 2824 ^ -26197)).addLast(6tj1WgJOWr("ʭʮʲʷʪʪʻʬ"), new NettyVarint21FrameDecoder()).addLast(6tj1WgJOWr("ʺʻʽʱʺʻʬ"), new NettyPacketDecoder(M19W9kXXYp())).addLast(6tj1WgJOWr("ʮʬʻʮʻʰʺʻʬ"), new NettyVarint21FrameEncoder()).addLast(6tj1WgJOWr("ʻʰʽʱʺʻʬ"), new NettyPacketEncoder(OtdBwh2n1H())).addLast(6tj1WgJOWr("ʮʿʽʵʻʪʁʶʿʰʺʲʻʬ"), yYr7NwKQD7(this));
            if (ctx instanceof SocketChannel && ViaLoadingBase.getInstance().getTargetVersion().getVersion() != (2790 ^ -10577 ^ 11848 ^ -3243)) {
               UserConnection user = new UserConnectionImpl(ctx, (boolean)(4734 ^ -2697 ^ 22240 ^ -19992));
               new ProtocolPipelineImpl(user);
               var10000 = ctx.pipeline();
               var10001 = new ChannelHandler[27510 ^ -13898 ^ 20701 ^ -3556];
               var10001[11946 ^ -15391 ^ 22709 ^ -18946] = new MCPVLBPipeline(user);
               var10000.addLast(var10001);
            }

         }

         private static 0eq wbgwvNsUQC(Object var0) {
            return proxy;
         }

         private static boolean TunN7TDOpr(0bv var0) {
            return var0.value;
         }

         private static 0eq DJQ3g7xFNP(Object var0) {
            return proxy;
         }

         private static 0eq mAVf41vT10(Object var0) {
            return proxy;
         }

         private static 0ep xxdq3B86Y2() {
            return 0ep.SOCKS4;
         }

         private static EnumPacketDirection OtdBwh2n1H() {
            return EnumPacketDirection.SERVERBOUND;
         }

         private static 0eq shwLe7DNnh(Object var0) {
            return proxy;
         }

         private static 0eq rqGlVTbJf3(Object var0) {
            return proxy;
         }

         private static EnumPacketDirection M19W9kXXYp() {
            return EnumPacketDirection.CLIENTBOUND;
         }

         private static 0eq L2SQk6qvBf(Object var0) {
            return proxy;
         }

         private static 0eq wSWL7FIwPo(Object var0) {
            return proxy;
         }

         private static 0eq q1gPDgTJgR(Object var0) {
            return proxy;
         }

         private static 0ep nuAiFRGLoK() {
            return 0ep.SOCKS5;
         }
      })).connect(address, serverPort);
      return networkmanager;
   }

   private static ReentrantReadWriteLock ldqq68QlcW(0cP var0) {
      return var0.readWriteLock;
   }

   private static ReentrantReadWriteLock _tcGc3o94T/* $FF was: 3tcGc3o94T*/(0cP var0) {
      return var0.readWriteLock;
   }

   private static Channel woNL7DNS0w(0cP var0) {
      return var0.channel;
   }

   public boolean isChannelOpen() {
      return (boolean)(6LYtVN7tyb(this) != null && 4qO1c4g2zR(this).isOpen() ? 19461 ^ -7284 ^ 948 ^ -21444 : 31638 ^ -29475 ^ 11271 ^ -9396);
   }

   private static void sM39j8AivZ(0cP var0, Channel var1) {
      var0.channel = var1;
   }

   private static void usN62XWoL5(0cP var0, INetHandler var1) {
      var0.packetListener = var1;
   }

   private static Class pDoTFrGOb7() {
      return channelClass;
   }

   public void sendPacket(Packet<?> packetIn) {
      if (this.isChannelOpen()) {
         this.flushOutboundQueue();
         this.dispatchPacket(packetIn, (GenericFutureListener[])null);
      } else {
         boMbXjxz5d(this).writeLock().lock();
         boolean var4 = false;

         try {
            var4 = true;
            lBHbNRvOkV(this).add(new 0cO(packetIn, new GenericFutureListener[18735 ^ -25907 ^ 19687 ^ -24827]));
            var4 = false;
         } finally {
            if (var4) {
               3tcGc3o94T(this).writeLock().unlock();
            }
         }

         pbVALnOqO5(this).writeLock().unlock();
      }

   }

   private static Logger A8wb4oVwKI() {
      return LOGGER;
   }

   private static ReentrantReadWriteLock Sj4MvFqb8V(0cP var0) {
      return var0.readWriteLock;
   }

   public void handleDisconnection() {
      if (c3oVo4am3o(this) != null && !aICBdJPalo(this).isOpen()) {
         if (this.getExitMessage() != null) {
            this.getNetHandler().onDisconnect(this.getExitMessage());
         } else if (this.getNetHandler() != null) {
            this.getNetHandler().onDisconnect(new TextComponentTranslation(QwW82x8AMW("ƋƓƊƒƏƖƊƇƟƃƔǈƂƏƕƅƉƈƈƃƅƒǈƁƃƈƃƔƏƅ"), new Object[15058 ^ -403 ^ 2151 ^ -13096]));
         }
      }

   }

   private static AttributeKey GnTUoSxRb2() {
      return PROTOCOL_ATTRIBUTE_KEY;
   }

   private static AttributeKey _v8trlnlyy/* $FF was: 1v8trlnlyy*/() {
      return PROTOCOL_ATTRIBUTE_KEY;
   }

   private static Channel ZIZvFwTxQK(0cP var0) {
      return var0.channel;
   }

   public void processReceivedPackets() {
      this.flushOutboundQueue();
      if (Bj9u1r6waG(this) instanceof ITickable) {
         ((ITickable)gBBSJJFnsm(this)).update();
      }

      if (ad9n1TUlOq(this) != null) {
         4tCN9wPdAo(this).flush();
      }

   }

   public void channelInactive(ChannelHandlerContext p_channelInactive_1_) {
      this.closeChannel(new TextComponentTranslation(QwW82x8AMW("ƂƏƕƅƉƈƈƃƅƒǈƃƈƂƩƀƵƒƔƃƇƋ"), new Object[14818 ^ -4531 ^ 2804 ^ -8869]));
   }

   private static INetHandler gBBSJJFnsm(0cP var0) {
      return var0.packetListener;
   }

   public void closeChannel(ITextComponent message) {
      if (AgtnTMalDj(this) != null && cTOmtiLvSo(this).isOpen()) {
         YCvTWQZtFR(this).close();
         N7I1Vj6B1q(this, message);
      }

   }

   private static Channel fVFgDItoIX(0cP var0) {
      return var0.channel;
   }

   private static Channel Se7aDWdGeQ(0cP var0) {
      return var0.channel;
   }

   private static Channel D78nRaJ9Fo(0cP var0) {
      return var0.channel;
   }

   private static Channel EMRvY6dEe7(0cP var0) {
      return var0.channel;
   }

   private static ReentrantReadWriteLock pbVALnOqO5(0cP var0) {
      return var0.readWriteLock;
   }

   private static Channel ylPAs8ByGA(0cP var0) {
      return var0.channel;
   }

   private static Channel j3NU0VEe93(0cP var0) {
      return var0.channel;
   }

   private static Channel jrve43sAlr(0cP var0) {
      return var0.channel;
   }

   private static Channel aICBdJPalo(0cP var0) {
      return var0.channel;
   }

   private static Channel c3oVo4am3o(0cP var0) {
      return var0.channel;
   }

   public void channelActive(ChannelHandlerContext p_channelActive_1_) throws Exception {
      super.channelActive(p_channelActive_1_);
      sM39j8AivZ(this, p_channelActive_1_.channel());

      try {
         this.setConnectionState(A6TGbIkNoj());
      } catch (Throwable var3) {
         Throwable throwable = var3;
         dzbbGOVicq().fatal(throwable);
      }

   }

   public void setConnectionState(EnumConnectionState newState) {
      woNL7DNS0w(this).attr(1v8trlnlyy()).set(newState);
      j05ki7R6eA(this).config().setAutoRead((boolean)(1850 ^ -3607 ^ 15736 ^ -13398));
      A8wb4oVwKI().debug(QwW82x8AMW("ƣƈƇƄƊƃƂǆƇƓƒƉǆƔƃƇƂ"));
   }

   private static Channel YCvTWQZtFR(0cP var0) {
      return var0.channel;
   }

   private static ChannelOption X0uSqtW2d3() {
      return ChannelOption.TCP_NODELAY;
   }

   private static void N7I1Vj6B1q(0cP var0, ITextComponent var1) {
      var0.terminationReason = var1;
   }

   private static Channel B6BQU29LwJ(0cP var0) {
      return var0.channel;
   }

   public _cP/* $FF was: 0cP*/(0cC pbot) {
      this.pbot = pbot;
   }

   private static Channel lGbqBPqela(0cP var0) {
      return var0.channel;
   }

   private static Channel gnOyBw050U(0cP var0) {
      return var0.channel;
   }

   public INetHandler getNetHandler() {
      return ds3LYaSjj2(this);
   }

   private static INetHandler ds3LYaSjj2(0cP var0) {
      return var0.packetListener;
   }

   private static ChannelFutureListener FN5BpQJaO0() {
      return ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE;
   }

   public void setNetHandler(INetHandler handler) {
      Validate.notNull(handler, QwW82x8AMW("ƖƇƅƍƃƒƪƏƕƒƃƈƃƔ"), new Object[24644 ^ -20193 ^ 24234 ^ -28687]);
      oYv69xDa7V().debug(QwW82x8AMW("ƵƃƒǆƊƏƕƒƃƈƃƔǆƉƀǆƝƛǆƒƉǆƝƛ"), this, handler);
      usN62XWoL5(this, handler);
   }

   private static 0cC eg80HGtiTQ(0cP var0) {
      return var0.pbot;
   }

   private static Channel cCYwpS5nS7(0cP var0) {
      return var0.channel;
   }

   private static Channel cTOmtiLvSo(0cP var0) {
      return var0.channel;
   }

   private static INetHandler JxIqLcDjT4(0cP var0) {
      return var0.packetListener;
   }

   private static Queue lBHbNRvOkV(0cP var0) {
      return var0.outboundPacketsQueue;
   }

   private static Channel _tCN9wPdAo/* $FF was: 4tCN9wPdAo*/(0cP var0) {
      return var0.channel;
   }

   private static ReentrantReadWriteLock YEf22FTlUa(0cP var0) {
      return var0.readWriteLock;
   }

   private static Channel _qO1c4g2zR/* $FF was: 4qO1c4g2zR*/(0cP var0) {
      return var0.channel;
   }

   private static INetHandler Bj9u1r6waG(0cP var0) {
      return var0.packetListener;
   }

   protected void channelRead0(ChannelHandlerContext p_channelRead0_1_, Packet<?> p_channelRead0_2_) {
      if (Xo6G1prauf(this).isOpen()) {
         try {
            p_channelRead0_2_.processPacket(JxIqLcDjT4(this));
         } catch (ThreadQuickExitException var4) {
         }
      }

   }

   public boolean isLocalChannel() {
      return (boolean)(!(yxGTZw9tnn(this) instanceof LocalChannel) && !(j3NU0VEe93(this) instanceof LocalServerChannel) ? 10218 ^ -8391 ^ 9525 ^ -8730 : 22316 ^ -19675 ^ 21382 ^ -18546);
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String QwW82x8AMW(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 22283 ^ -19510 ^ 4245 ^ -2988; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 7422 ^ 14821 ^ 9352 ^ 117));
      }

      return var1.toString();
   }

   private static 0bv ORcJTnpQBo() {
      return 0cc.internalErrors;
   }

   private void dispatchPacket(final Packet<?> inPacket, @Nullable final GenericFutureListener[] futureListeners) {
      final EnumConnectionState enumconnectionstate = EnumConnectionState.getFromPacket(inPacket);
      final EnumConnectionState enumconnectionstate1 = (EnumConnectionState)fjGtoiHrlN(this).attr(GnTUoSxRb2()).get();
      if (enumconnectionstate1 != enumconnectionstate) {
         4b7mBqWim7().debug(QwW82x8AMW("ƢƏƕƇƄƊƃƂǆƇƓƒƉǆƔƃƇƂ"));
         gnOyBw050U(this).config().setAutoRead((boolean)(20950 ^ -26423 ^ 10045 ^ -4574));
      }

      if (lGbqBPqela(this).eventLoop().inEventLoop()) {
         if (enumconnectionstate != enumconnectionstate1) {
            this.setConnectionState(enumconnectionstate);
         }

         ChannelFuture channelfuture = 0RM0DDnfBY(this).writeAndFlush(inPacket);
         if (futureListeners != null) {
            channelfuture.addListeners(futureListeners);
         }

         channelfuture.addListener(FN5BpQJaO0());
      } else {
         ZIZvFwTxQK(this).eventLoop().execute(new Runnable() {
            private static EnumConnectionState pTfUvOMyYG(Object var0) {
               return enumconnectionstate;
            }

            private static Packet CSL6bl1Iy0(Object var0) {
               return inPacket;
            }

            private static Channel qdyIcc61qu(0cP var0) {
               return var0.channel;
            }

            private static 0cP V276a2s3q2(Object var0) {
               return 0cP.this;
            }

            private static GenericFutureListener[] anOCO4OiIS(Object var0) {
               return futureListeners;
            }

            private static ChannelFutureListener UFwSTLhSm6() {
               return ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE;
            }

            private static 0cP Zrgkn4WAwI(Object var0) {
               return 0cP.this;
            }

            private static EnumConnectionState aFHgL7PLtN(Object var0) {
               return enumconnectionstate;
            }

            private static GenericFutureListener[] R3rn6NFpJI(Object var0) {
               return futureListeners;
            }

            public void run() {
               if (pTfUvOMyYG(this) != yDdXJONAtB(this)) {
                  V276a2s3q2(this).setConnectionState(aFHgL7PLtN(this));
               }

               ChannelFuture channelfuture1 = qdyIcc61qu(Zrgkn4WAwI(this)).writeAndFlush(CSL6bl1Iy0(this));
               if (R3rn6NFpJI(this) != null) {
                  channelfuture1.addListeners(anOCO4OiIS(this));
               }

               channelfuture1.addListener(UFwSTLhSm6());
            }

            private static EnumConnectionState yDdXJONAtB(Object var0) {
               return enumconnectionstate1;
            }
         });
      }

   }

   private static Channel _rTNPWDRWe/* $FF was: 7rTNPWDRWe*/(0cP var0) {
      return var0.channel;
   }

   private static Channel fjGtoiHrlN(0cP var0) {
      return var0.channel;
   }

   public void setCompressionThreshold(int threshold) {
      if (threshold >= 0) {
         if (jrve43sAlr(this).pipeline().get(QwW82x8AMW("ƂƃƅƉƋƖƔƃƕƕ")) instanceof NettyCompressionDecoder) {
            ((NettyCompressionDecoder)N4xa91pwSE(this).pipeline().get(QwW82x8AMW("ƂƃƅƉƋƖƔƃƕƕ"))).setCompressionThreshold(threshold);
         } else {
            ylPAs8ByGA(this).pipeline().addBefore(QwW82x8AMW("ƂƃƅƉƂƃƔ"), QwW82x8AMW("ƂƃƅƉƋƖƔƃƕƕ"), new NettyCompressionDecoder(threshold));
         }

         if (7rTNPWDRWe(this).pipeline().get(QwW82x8AMW("ƅƉƋƖƔƃƕƕ")) instanceof NettyCompressionEncoder) {
            ((NettyCompressionEncoder)THkyteDltn(this).pipeline().get(QwW82x8AMW("ƅƉƋƖƔƃƕƕ"))).setCompressionThreshold(threshold);
         } else {
            EMRvY6dEe7(this).pipeline().addBefore(QwW82x8AMW("ƃƈƅƉƂƃƔ"), QwW82x8AMW("ƅƉƋƖƔƃƕƕ"), new NettyCompressionEncoder(threshold));
         }
      } else {
         if (cCYwpS5nS7(this).pipeline().get(QwW82x8AMW("ƂƃƅƉƋƖƔƃƕƕ")) instanceof NettyCompressionDecoder) {
            D78nRaJ9Fo(this).pipeline().remove(QwW82x8AMW("ƂƃƅƉƋƖƔƃƕƕ"));
         }

         if (Se7aDWdGeQ(this).pipeline().get(QwW82x8AMW("ƅƉƋƖƔƃƕƕ")) instanceof NettyCompressionEncoder) {
            qjJNlIfiSB(this).pipeline().remove(QwW82x8AMW("ƅƉƋƖƔƃƕƕ"));
         }
      }

      B6BQU29LwJ(this).pipeline().fireUserEventTriggered(new CompressionReorderEvent());
   }

   private static ITextComponent O6TvWB3u2j(0cP var0) {
      return var0.terminationReason;
   }

   private static Channel _LYtVN7tyb/* $FF was: 6LYtVN7tyb*/(0cP var0) {
      return var0.channel;
   }

   private void flushOutboundQueue() {
      if (fVFgDItoIX(this) != null && rWLMRNIbJ9(this).isOpen()) {
         YEf22FTlUa(this).readLock().lock();

         while(true) {
            boolean var4 = false;

            try {
               var4 = true;
               if (drrsbCetX3(this).isEmpty()) {
                  var4 = false;
                  break;
               }

               0cO networkmanager$inboundhandlertuplepacketlistener = (0cO)X1S6vvsFt9(this).poll();
               this.dispatchPacket(0cO.access$000(networkmanager$inboundhandlertuplepacketlistener), 0cO.access$100(networkmanager$inboundhandlertuplepacketlistener));
            } finally {
               if (var4) {
                  Sj4MvFqb8V(this).readLock().unlock();
               }
            }
         }

         ldqq68QlcW(this).readLock().unlock();
      }

   }

   private static Channel AgtnTMalDj(0cP var0) {
      return var0.channel;
   }

   private static Channel j05ki7R6eA(0cP var0) {
      return var0.channel;
   }

   private static EnumConnectionState A6TGbIkNoj() {
      return EnumConnectionState.HANDSHAKING;
   }

   private static Channel yxGTZw9tnn(0cP var0) {
      return var0.channel;
   }

   public void exceptionCaught(ChannelHandlerContext p_exceptionCaught_1_, Throwable p_exceptionCaught_2_) {
      if (!p_exceptionCaught_2_.toString().contains(QwW82x8AMW("ƈƃƒƒƟǈ")) && !p_exceptionCaught_2_.toString().contains(QwW82x8AMW("ƌƇƐƇǈƈƏƉǈƅƎƇƈƈƃƊƕǈƥƊƉƕƃƂƥƎƇƈƈƃƊƣƞƅƃƖƒƏƉƈ"))) {
         if (71aOnBq7uw(ORcJTnpQBo())) {
            0dK.formatMsg(QwW82x8AMW("\u05f8֤לם֨֡דכמדǆǀƂǀƊ") + jFSxdhd695(this).getNickname() + QwW82x8AMW("ǆǀƅ") + p_exceptionCaught_2_);
            StackTraceElement[] var4 = p_exceptionCaught_2_.getStackTrace();
            int var5 = var4.length;

            for(int var6 = 12761 ^ -14017 ^ 27647 ^ -27879; var6 < var5; ++var6) {
               StackTraceElement element = var4[var6];
               0dK.defaultMsg(QwW82x8AMW("ǀƅǆƇƒǆǀƅ") + element.toString());
            }
         }

         eg80HGtiTQ(this).reconnect((boolean)(21066 ^ -23602 ^ 11402 ^ -8946));
      }

      TextComponentTranslation textcomponenttranslation;
      if (p_exceptionCaught_2_ instanceof TimeoutException) {
         textcomponenttranslation = new TextComponentTranslation(QwW82x8AMW("ƂƏƕƅƉƈƈƃƅƒǈƒƏƋƃƉƓƒ"), new Object[17929 ^ -17544 ^ 21004 ^ -20611]);
      } else {
         String var10002 = QwW82x8AMW("ƂƏƕƅƉƈƈƃƅƒǈƁƃƈƃƔƏƅƴƃƇƕƉƈ");
         Object[] var10003 = new Object[24566 ^ -27770 ^ 16725 ^ -29404];
         var10003[9147 ^ -30149 ^ 11716 ^ -31676] = QwW82x8AMW("ƯƈƒƃƔƈƇƊǆƣƞƅƃƖƒƏƉƈǜǆ") + p_exceptionCaught_2_;
         textcomponenttranslation = new TextComponentTranslation(var10002, var10003);
      }

      XqB2DB4PLY().debug(textcomponenttranslation.getUnformattedText(), p_exceptionCaught_2_);
      this.closeChannel(textcomponenttranslation);
   }

   private static Channel ad9n1TUlOq(0cP var0) {
      return var0.channel;
   }

   private static Channel _RM0DDnfBY/* $FF was: 0RM0DDnfBY*/(0cP var0) {
      return var0.channel;
   }

   private static EventLoopGroup QJZwGq4F6j() {
      return group;
   }

   private static Channel rWLMRNIbJ9(0cP var0) {
      return var0.channel;
   }

   private static Logger _b7mBqWim7/* $FF was: 4b7mBqWim7*/() {
      return LOGGER;
   }

   private static Queue drrsbCetX3(0cP var0) {
      return var0.outboundPacketsQueue;
   }

   private static Channel qjJNlIfiSB(0cP var0) {
      return var0.channel;
   }
}
