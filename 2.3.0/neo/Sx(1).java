package neo;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.local.LocalAddress;
import io.netty.channel.local.LocalEventLoopGroup;
import io.netty.channel.local.LocalServerChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.util.LazyLoadBase;
import net.minecraft.util.ReportedException;
import net.minecraft.util.text.TextComponentString;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Sx {
   private static final Logger LOGGER = LogManager.getLogger();
   public static final LazyLoadBase<NioEventLoopGroup> SERVER_NIO_EVENTLOOP = new LazyLoadBase<NioEventLoopGroup>() {
      protected NioEventLoopGroup load() {
         return new NioEventLoopGroup(0, (new ThreadFactoryBuilder()).setNameFormat("Netty Server IO #%d").setDaemon(true).build());
      }

      // $FF: synthetic method
      // $FF: bridge method
      protected Object load() {
         return this.load();
      }
   };
   public static final LazyLoadBase<EpollEventLoopGroup> SERVER_EPOLL_EVENTLOOP = new LazyLoadBase<EpollEventLoopGroup>() {
      protected EpollEventLoopGroup load() {
         return new EpollEventLoopGroup(0, (new ThreadFactoryBuilder()).setNameFormat("Netty Epoll Server IO #%d").setDaemon(true).build());
      }

      // $FF: synthetic method
      // $FF: bridge method
      protected Object load() {
         return this.load();
      }
   };
   public static final LazyLoadBase<LocalEventLoopGroup> SERVER_LOCAL_EVENTLOOP = new LazyLoadBase<LocalEventLoopGroup>() {
      protected LocalEventLoopGroup load() {
         return new LocalEventLoopGroup(0, (new ThreadFactoryBuilder()).setNameFormat("Netty Local Server IO #%d").setDaemon(true).build());
      }

      // $FF: synthetic method
      // $FF: bridge method
      protected Object load() {
         return this.load();
      }
   };
   private final Xx server;
   public volatile boolean isAlive;
   private final List<ChannelFuture> endpoints = Collections.synchronizedList(Lists.newArrayList());
   private final List<Sp> networkManagers = Collections.synchronizedList(Lists.newArrayList());

   public Sx(Xx server) {
      this.server = server;
      this.isAlive = true;
   }

   public void addEndpoint(InetAddress address, int port) throws IOException {
      synchronized(this.endpoints) {
         Class oclass;
         LazyLoadBase lazyloadbase;
         if (Epoll.isAvailable() && this.server.shouldUseNativeTransport()) {
            oclass = EpollServerSocketChannel.class;
            lazyloadbase = SERVER_EPOLL_EVENTLOOP;
            LOGGER.info("Using epoll channel type");
         } else {
            oclass = NioServerSocketChannel.class;
            lazyloadbase = SERVER_NIO_EVENTLOOP;
            LOGGER.info("Using default channel type");
         }

         this.endpoints.add(((ServerBootstrap)((ServerBootstrap)(new ServerBootstrap()).channel(oclass)).childHandler(new ChannelInitializer<Channel>() {
            protected void initChannel(Channel p_initChannel_1_) throws Exception {
               try {
                  p_initChannel_1_.config().setOption(ChannelOption.TCP_NODELAY, true);
               } catch (ChannelException var3) {
               }

               p_initChannel_1_.pipeline().addLast("timeout", new ReadTimeoutHandler(30)).addLast("legacy_query", new RI(Sx.this)).addLast("splitter", new Sg()).addLast("decoder", new Se(RC.SERVERBOUND)).addLast("prepender", new Sh()).addLast("encoder", new Sf(RC.CLIENTBOUND));
               Sp networkmanager = new Sp(RC.SERVERBOUND);
               Sx.this.networkManagers.add(networkmanager);
               p_initChannel_1_.pipeline().addLast("packet_handler", networkmanager);
               networkmanager.setNetHandler(new Xz(Sx.this.server, networkmanager));
            }
         }).group((EventLoopGroup)lazyloadbase.getValue()).localAddress(address, port)).bind().syncUninterruptibly());
      }
   }

   public SocketAddress addLocalEndpoint() {
      ChannelFuture channelfuture;
      synchronized(this.endpoints) {
         channelfuture = ((ServerBootstrap)((ServerBootstrap)(new ServerBootstrap()).channel(LocalServerChannel.class)).childHandler(new ChannelInitializer<Channel>() {
            protected void initChannel(Channel p_initChannel_1_) throws Exception {
               Sp networkmanager = new Sp(RC.SERVERBOUND);
               networkmanager.setNetHandler(new pr(Sx.this.server, networkmanager));
               Sx.this.networkManagers.add(networkmanager);
               p_initChannel_1_.pipeline().addLast("packet_handler", networkmanager);
            }
         }).group((EventLoopGroup)SERVER_NIO_EVENTLOOP.getValue()).localAddress(LocalAddress.ANY)).bind().syncUninterruptibly();
         this.endpoints.add(channelfuture);
      }

      return channelfuture.channel().localAddress();
   }

   public void terminateEndpoints() {
      this.isAlive = false;
      Iterator var1 = this.endpoints.iterator();

      while(var1.hasNext()) {
         ChannelFuture channelfuture = (ChannelFuture)var1.next();

         try {
            channelfuture.channel().close().sync();
         } catch (InterruptedException var4) {
            LOGGER.error("Interrupted whilst closing channel");
         }
      }

   }

   public void networkTick() {
      synchronized(this.networkManagers) {
         Iterator<Sp> iterator = this.networkManagers.iterator();

         while(true) {
            while(true) {
               final Sp networkmanager;
               do {
                  if (!iterator.hasNext()) {
                     return;
                  }

                  networkmanager = (Sp)iterator.next();
               } while(networkmanager.hasNoChannel());

               if (networkmanager.isChannelOpen()) {
                  try {
                     networkmanager.processReceivedPackets();
                  } catch (Exception var8) {
                     Exception exception = var8;
                     if (networkmanager.isLocalChannel()) {
                        Er crashreport = Er.makeCrashReport(exception, "Ticking memory connection");
                        Ey crashreportcategory = crashreport.makeCategory("Ticking connection");
                        crashreportcategory.addDetail("Connection", new Ez<String>() {
                           public String call() throws Exception {
                              return networkmanager.toString();
                           }

                           // $FF: synthetic method
                           // $FF: bridge method
                           public Object call() throws Exception {
                              return this.call();
                           }
                        });
                        throw new ReportedException(crashreport);
                     }

                     LOGGER.warn("Failed to handle packet for {}", networkmanager.getRemoteAddress(), exception);
                     final TextComponentString textcomponentstring = new TextComponentString("Internal server error");
                     networkmanager.sendPacket(new TP(textcomponentstring), new GenericFutureListener<Future<? super Void>>() {
                        public void operationComplete(Future<? super Void> p_operationComplete_1_) throws Exception {
                           networkmanager.closeChannel(textcomponentstring);
                        }
                     });
                     networkmanager.disableAutoRead();
                  }
               } else {
                  iterator.remove();
                  networkmanager.handleDisconnection();
               }
            }
         }
      }
   }

   public Xx getServer() {
      return this.server;
   }
}
