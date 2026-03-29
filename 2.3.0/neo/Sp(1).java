package neo;

import com.google.common.collect.Queues;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.connection.UserConnectionImpl;
import com.viaversion.viaversion.protocol.ProtocolPipelineImpl;
import de.florianmichael.vialoadingbase.ViaLoadingBase;
import de.florianmichael.vialoadingbase.netty.event.CompressionReorderEvent;
import de.florianmichael.viamcp.MCPVLBPipeline;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.local.LocalChannel;
import io.netty.channel.local.LocalEventLoopGroup;
import io.netty.channel.local.LocalServerChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.proxy.ProxyHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.TimeoutException;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.annotation.Nullable;
import javax.crypto.SecretKey;
import net.minecraft.util.CryptManager;
import net.minecraft.util.ITickable;
import net.minecraft.util.LazyLoadBase;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class Sp extends SimpleChannelInboundHandler<Sz<?>> {
   private static final Logger LOGGER = LogManager.getLogger();
   public static final Marker NETWORK_MARKER = MarkerManager.getMarker("NETWORK");
   public static final Marker NETWORK_PACKETS_MARKER;
   public static final AttributeKey<RB> PROTOCOL_ATTRIBUTE_KEY;
   public static final LazyLoadBase<NioEventLoopGroup> CLIENT_NIO_EVENTLOOP;
   public static final LazyLoadBase<EpollEventLoopGroup> CLIENT_EPOLL_EVENTLOOP;
   public static final LazyLoadBase<LocalEventLoopGroup> CLIENT_LOCAL_EVENTLOOP;
   private final RC direction;
   private final Queue<So> outboundPacketsQueue = Queues.newConcurrentLinkedQueue();
   private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
   private Channel channel;
   private SocketAddress socketAddress;
   private RH packetListener;
   private ITextComponent terminationReason;
   private boolean isEncrypted;
   private boolean disconnected;

   public Sp(RC packetDirection) {
      this.direction = packetDirection;
   }

   public void channelActive(ChannelHandlerContext p_channelActive_1_) throws Exception {
      super.channelActive(p_channelActive_1_);
      this.channel = p_channelActive_1_.channel();
      this.socketAddress = this.channel.remoteAddress();

      try {
         this.setConnectionState(RB.HANDSHAKING);
      } catch (Throwable var3) {
         Throwable throwable = var3;
         LOGGER.fatal(throwable);
      }

   }

   public void setConnectionState(RB newState) {
      this.channel.attr(PROTOCOL_ATTRIBUTE_KEY).set(newState);
      this.channel.config().setAutoRead(true);
      LOGGER.debug("Enabled auto read");
   }

   public void channelInactive(ChannelHandlerContext p_channelInactive_1_) throws Exception {
      this.closeChannel(new TextComponentTranslation("disconnect.endOfStream", new Object[0]));
   }

   public void exceptionCaught(ChannelHandlerContext p_exceptionCaught_1_, Throwable p_exceptionCaught_2_) throws Exception {
      TextComponentTranslation textcomponenttranslation;
      if (p_exceptionCaught_2_ instanceof TimeoutException) {
         textcomponenttranslation = new TextComponentTranslation("disconnect.timeout", new Object[0]);
      } else {
         textcomponenttranslation = new TextComponentTranslation("disconnect.genericReason", new Object[]{"Internal Exception: " + p_exceptionCaught_2_});
      }

      LOGGER.debug(textcomponenttranslation.getUnformattedText(), p_exceptionCaught_2_);
      this.closeChannel(textcomponenttranslation);
   }

   protected void channelRead0(ChannelHandlerContext p_channelRead0_1_, Sz<?> p_channelRead0_2_) throws Exception {
      if (this.channel.isOpen()) {
         0dg eventPacket = new 0dg(p_channelRead0_2_);
         0bz.method_Qm().method_Qn().post(eventPacket);
         if (eventPacket.method_bzU()) {
            return;
         }

         try {
            p_channelRead0_2_.processPacket(this.packetListener);
         } catch (VE var5) {
         }
      }

   }

   public void setNetHandler(RH handler) {
      Validate.notNull(handler, "packetListener", new Object[0]);
      LOGGER.debug("Set listener of {} to {}", this, handler);
      this.packetListener = handler;
   }

   public void sendPacket(Sz<?> packetIn) {
      0dg eventPacket = new 0dg(packetIn);
      0bz.method_Qm().method_Qn().post(eventPacket);
      if (!eventPacket.method_bzU()) {
         if (this.isChannelOpen()) {
            this.flushOutboundQueue();
            this.dispatchPacket(packetIn, (GenericFutureListener[])null);
         } else {
            this.readWriteLock.writeLock().lock();

            try {
               this.outboundPacketsQueue.add(new So(packetIn, new GenericFutureListener[0]));
            } finally {
               this.readWriteLock.writeLock().unlock();
            }
         }

      }
   }

   public void sendPacket(Sz<?> packetIn, GenericFutureListener<? extends Future<? super Void>> listener, GenericFutureListener<? extends Future<? super Void>>... listeners) {
      if (this.isChannelOpen()) {
         this.flushOutboundQueue();
         this.dispatchPacket(packetIn, (GenericFutureListener[])((GenericFutureListener[])ArrayUtils.add(listeners, 0, listener)));
      } else {
         this.readWriteLock.writeLock().lock();

         try {
            this.outboundPacketsQueue.add(new So(packetIn, (GenericFutureListener[])((GenericFutureListener[])ArrayUtils.add(listeners, 0, listener))));
         } finally {
            this.readWriteLock.writeLock().unlock();
         }
      }

   }

   private void dispatchPacket(final Sz<?> inPacket, @Nullable final GenericFutureListener<? extends Future<? super Void>>[] futureListeners) {
      final RB enumconnectionstate = RB.getFromPacket(inPacket);
      final RB enumconnectionstate1 = (RB)this.channel.attr(PROTOCOL_ATTRIBUTE_KEY).get();
      if (enumconnectionstate1 != enumconnectionstate) {
         LOGGER.debug("Disabled auto read");
         this.channel.config().setAutoRead(false);
      }

      if (this.channel.eventLoop().inEventLoop()) {
         if (enumconnectionstate != enumconnectionstate1) {
            this.setConnectionState(enumconnectionstate);
         }

         ChannelFuture channelfuture = this.channel.writeAndFlush(inPacket);
         if (futureListeners != null) {
            channelfuture.addListeners(futureListeners);
         }

         channelfuture.addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
      } else {
         this.channel.eventLoop().execute(new Runnable() {
            public void run() {
               if (enumconnectionstate != enumconnectionstate1) {
                  Sp.this.setConnectionState(enumconnectionstate);
               }

               ChannelFuture channelfuture1 = Sp.this.channel.writeAndFlush(inPacket);
               if (futureListeners != null) {
                  channelfuture1.addListeners(futureListeners);
               }

               channelfuture1.addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
            }
         });
      }

   }

   private void flushOutboundQueue() {
      if (this.channel != null && this.channel.isOpen()) {
         this.readWriteLock.readLock().lock();

         try {
            while(!this.outboundPacketsQueue.isEmpty()) {
               So networkmanager$inboundhandlertuplepacketlistener = (So)this.outboundPacketsQueue.poll();
               this.dispatchPacket(So.access$100(networkmanager$inboundhandlertuplepacketlistener), So.access$200(networkmanager$inboundhandlertuplepacketlistener));
            }
         } finally {
            this.readWriteLock.readLock().unlock();
         }
      }

   }

   public void processReceivedPackets() {
      this.flushOutboundQueue();
      if (this.packetListener instanceof ITickable) {
         ((ITickable)this.packetListener).update();
      }

      if (this.channel != null) {
         this.channel.flush();
      }

   }

   public SocketAddress getRemoteAddress() {
      return this.socketAddress;
   }

   public void closeChannel(ITextComponent message) {
      if (this.channel.isOpen()) {
         this.channel.close().awaitUninterruptibly();
         this.terminationReason = message;
      }

   }

   public boolean isLocalChannel() {
      return this.channel instanceof LocalChannel || this.channel instanceof LocalServerChannel;
   }

   public static Sp createNetworkManagerAndConnect(InetAddress address, int serverPort, boolean useNativeTransport, int timeout) {
      final Sp networkmanager = new Sp(RC.CLIENTBOUND);
      Class oclass;
      LazyLoadBase lazyloadbase;
      if (Epoll.isAvailable() && useNativeTransport) {
         oclass = EpollSocketChannel.class;
         lazyloadbase = CLIENT_EPOLL_EVENTLOOP;
      } else {
         oclass = NioSocketChannel.class;
         lazyloadbase = CLIENT_NIO_EVENTLOOP;
      }

      ((Bootstrap)((Bootstrap)((Bootstrap)(new Bootstrap()).group((EventLoopGroup)lazyloadbase.getValue())).handler(new ChannelInitializer<Channel>() {
         protected void initChannel(Channel p_initChannel_1_) {
            try {
               p_initChannel_1_.config().setOption(ChannelOption.TCP_NODELAY, Boolean.TRUE);
            } catch (ChannelException var3) {
            }

            if (0cz.method_btR() != null) {
               ProxyHandler proxyHandler = 0cz.method_btR().method_bBT();
               if (proxyHandler != null) {
                  proxyHandler.setConnectTimeoutMillis(9500L);
                  p_initChannel_1_.pipeline().addLast(new ChannelHandler[]{proxyHandler});
               }
            }

            p_initChannel_1_.pipeline().addLast("timeout", new ReadTimeoutHandler(30)).addLast("splitter", new Sg()).addLast("decoder", new Se(RC.CLIENTBOUND)).addLast("prepender", new Sh()).addLast("encoder", new Sf(RC.SERVERBOUND)).addLast("packet_handler", networkmanager);
            if (p_initChannel_1_ instanceof SocketChannel && ViaLoadingBase.getInstance().getTargetVersion().getVersion() != 340) {
               UserConnection user = new UserConnectionImpl(p_initChannel_1_, true);
               new ProtocolPipelineImpl(user);
               p_initChannel_1_.pipeline().addLast(new ChannelHandler[]{new MCPVLBPipeline(user)});
            }

         }
      })).channel(oclass)).connect(address, serverPort).syncUninterruptibly();
      return networkmanager;
   }

   public static Sp provideLocalClient(SocketAddress address) {
      final Sp networkmanager = new Sp(RC.CLIENTBOUND);
      ((Bootstrap)((Bootstrap)((Bootstrap)(new Bootstrap()).group((EventLoopGroup)CLIENT_LOCAL_EVENTLOOP.getValue())).handler(new ChannelInitializer<Channel>() {
         protected void initChannel(Channel p_initChannel_1_) throws Exception {
            p_initChannel_1_.pipeline().addLast("packet_handler", networkmanager);
         }
      })).channel(LocalChannel.class)).connect(address).syncUninterruptibly();
      return networkmanager;
   }

   public void enableEncryption(SecretKey key) {
      this.isEncrypted = true;
      this.channel.pipeline().addBefore("splitter", "decrypt", new Sb(CryptManager.createNetCipherInstance(2, key)));
      this.channel.pipeline().addBefore("prepender", "encrypt", new Sc(CryptManager.createNetCipherInstance(1, key)));
   }

   public boolean isEncrypted() {
      return this.isEncrypted;
   }

   public boolean isChannelOpen() {
      return this.channel != null && this.channel.isOpen();
   }

   public boolean hasNoChannel() {
      return this.channel == null;
   }

   public RH getNetHandler() {
      return this.packetListener;
   }

   public ITextComponent getExitMessage() {
      return this.terminationReason;
   }

   public void disableAutoRead() {
      this.channel.config().setAutoRead(false);
   }

   public void setCompressionThreshold(int threshold) {
      if (threshold >= 0) {
         if (this.channel.pipeline().get("decompress") instanceof RZ) {
            ((RZ)this.channel.pipeline().get("decompress")).setCompressionThreshold(threshold);
         } else {
            this.channel.pipeline().addBefore("decoder", "decompress", new RZ(threshold));
         }

         if (this.channel.pipeline().get("compress") instanceof Sa) {
            ((Sa)this.channel.pipeline().get("compress")).setCompressionThreshold(threshold);
         } else {
            this.channel.pipeline().addBefore("encoder", "compress", new Sa(threshold));
         }
      } else {
         if (this.channel.pipeline().get("decompress") instanceof RZ) {
            this.channel.pipeline().remove("decompress");
         }

         if (this.channel.pipeline().get("compress") instanceof Sa) {
            this.channel.pipeline().remove("compress");
         }
      }

      this.channel.pipeline().fireUserEventTriggered(new CompressionReorderEvent());
   }

   public void handleDisconnection() {
      if (this.channel != null && !this.channel.isOpen()) {
         if (this.disconnected) {
            LOGGER.warn("handleDisconnection() called twice");
         } else {
            this.disconnected = true;
            if (this.getExitMessage() != null) {
               this.getNetHandler().onDisconnect(this.getExitMessage());
            } else if (this.getNetHandler() != null) {
               this.getNetHandler().onDisconnect(new TextComponentTranslation("multiplayer.disconnect.generic", new Object[0]));
            }
         }
      }

   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void channelRead0(ChannelHandlerContext var1, Object var2) throws Exception {
      this.channelRead0(var1, (Sz)var2);
   }

   static {
      NETWORK_PACKETS_MARKER = MarkerManager.getMarker("NETWORK_PACKETS", NETWORK_MARKER);
      PROTOCOL_ATTRIBUTE_KEY = AttributeKey.valueOf("protocol");
      CLIENT_NIO_EVENTLOOP = new LazyLoadBase<NioEventLoopGroup>() {
         protected NioEventLoopGroup load() {
            return new NioEventLoopGroup(0, (new ThreadFactoryBuilder()).setNameFormat("Netty Client IO #%d").setDaemon(true).build());
         }

         // $FF: synthetic method
         // $FF: bridge method
         protected Object load() {
            return this.load();
         }
      };
      CLIENT_EPOLL_EVENTLOOP = new LazyLoadBase<EpollEventLoopGroup>() {
         protected EpollEventLoopGroup load() {
            return new EpollEventLoopGroup(0, (new ThreadFactoryBuilder()).setNameFormat("Netty Epoll Client IO #%d").setDaemon(true).build());
         }

         // $FF: synthetic method
         // $FF: bridge method
         protected Object load() {
            return this.load();
         }
      };
      CLIENT_LOCAL_EVENTLOOP = new LazyLoadBase<LocalEventLoopGroup>() {
         protected LocalEventLoopGroup load() {
            return new LocalEventLoopGroup(0, (new ThreadFactoryBuilder()).setNameFormat("Netty Local Client IO #%d").setDaemon(true).build());
         }

         // $FF: synthetic method
         // $FF: bridge method
         protected Object load() {
            return this.load();
         }
      };
   }
}
