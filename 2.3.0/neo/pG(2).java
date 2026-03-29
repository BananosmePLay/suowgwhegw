package neo;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.base64.Base64;
import java.awt.image.BufferedImage;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class pG {
   private static final Splitter PING_RESPONSE_SPLITTER = Splitter.on('\u0000').limit(6);
   private static final Logger LOGGER = LogManager.getLogger();
   private final List<Sp> pingDestinations = Collections.synchronizedList(Lists.newArrayList());

   public pG() {
   }

   public void ping(final pf server, final boolean monitoring) throws UnknownHostException {
      pd serveraddress = pd.fromString(server.serverIP);
      final Sp networkmanager = Sp.createNetworkManagerAndConnect(InetAddress.getByName(serveraddress.getIP()), serveraddress.getPort(), false, 10);
      this.pingDestinations.add(networkmanager);
      server.serverMOTD = Ax.format("multiplayer.status.pinging");
      server.pingToServer = -1L;
      server.playerList = null;
      networkmanager.setNetHandler(new Vy() {
         private boolean successful;
         private boolean receivedStatus;
         private long pingSentAt;

         public void handleServerInfo(VD packetIn) {
            if (this.receivedStatus) {
               networkmanager.closeChannel(new TextComponentTranslation("multiplayer.status.unrequested", new Object[0]));
            } else {
               this.receivedStatus = true;
               Vu serverstatusresponse = packetIn.getResponse();
               if (serverstatusresponse.getServerDescription() != null) {
                  server.serverMOTD = serverstatusresponse.getServerDescription().getFormattedText();
               } else {
                  server.serverMOTD = "";
               }

               if (serverstatusresponse.getVersion() != null) {
                  server.gameVersion = serverstatusresponse.getVersion().getName();
                  server.version = serverstatusresponse.getVersion().getProtocol();
               } else {
                  server.gameVersion = Ax.format("multiplayer.status.old");
                  server.version = 0;
               }

               if (serverstatusresponse.getPlayers() == null) {
                  server.populationInfo = TextFormatting.DARK_GRAY + Ax.format("multiplayer.status.unknown");
               } else {
                  server.populationInfo = TextFormatting.GRAY + "" + serverstatusresponse.getPlayers().getOnlinePlayerCount() + "" + TextFormatting.DARK_GRAY + "/" + TextFormatting.GRAY + serverstatusresponse.getPlayers().getMaxPlayers();
                  if (ArrayUtils.isNotEmpty(serverstatusresponse.getPlayers().getPlayers())) {
                     StringBuilder stringbuilder = new StringBuilder();
                     GameProfile[] var4 = serverstatusresponse.getPlayers().getPlayers();
                     int var5 = var4.length;

                     for(int var6 = 0; var6 < var5; ++var6) {
                        GameProfile gameprofile = var4[var6];
                        if (stringbuilder.length() > 0) {
                           stringbuilder.append("\n");
                        }

                        stringbuilder.append(gameprofile.getName());
                     }

                     if (serverstatusresponse.getPlayers().getPlayers().length < serverstatusresponse.getPlayers().getOnlinePlayerCount()) {
                        if (stringbuilder.length() > 0) {
                           stringbuilder.append("\n");
                        }

                        stringbuilder.append(Ax.format("multiplayer.status.and_more", serverstatusresponse.getPlayers().getOnlinePlayerCount() - serverstatusresponse.getPlayers().getPlayers().length));
                     }

                     server.playerList = stringbuilder.toString();
                  }
               }

               if (serverstatusresponse.getFavicon() != null) {
                  String s = serverstatusresponse.getFavicon();
                  if (s.startsWith("data:image/png;base64,")) {
                     if (monitoring) {
                        ByteBuf bytebuf = Unpooled.copiedBuffer(s.substring("data:image/png;base64,".length()), StandardCharsets.UTF_8);

                        try {
                           BufferedImage bufferedimage = zk.readBufferedImage(new ByteBufInputStream(Base64.decode(bytebuf)));
                           Validate.validState(bufferedimage.getWidth() == 64, "Must be 64 pixels wide", new Object[0]);
                           Validate.validState(bufferedimage.getHeight() == 64, "Must be 64 pixels high", new Object[0]);
                           server.iconRender = bufferedimage;
                        } catch (Throwable var11) {
                           pG.LOGGER.error("Invalid icon for server {} ({})", server.serverName, server.serverIP, var11);
                        } finally {
                           bytebuf.release();
                        }
                     }

                     server.setBase64EncodedIconData(s.substring("data:image/png;base64,".length()));
                  } else {
                     pG.LOGGER.error("Invalid server icon (unknown format)");
                  }
               } else {
                  server.setBase64EncodedIconData((String)null);
               }

               this.pingSentAt = nC.getSystemTime();
               networkmanager.sendPacket(new Vv(this.pingSentAt));
               this.successful = true;
            }

         }

         public void handlePong(VC packetIn) {
            long i = this.pingSentAt;
            long j = nC.getSystemTime();
            server.pingToServer = j - i;
            networkmanager.closeChannel(new TextComponentString("Finished"));
         }

         public void onDisconnect(ITextComponent reason) {
            if (!this.successful) {
               pG.LOGGER.error("Can't ping {}: {}", server.serverIP, reason.getUnformattedText());
               server.serverMOTD = TextFormatting.DARK_RED + Ax.format("multiplayer.status.cannot_connect");
               server.populationInfo = "";
               pG.this.tryCompatibilityPing(server);
            }

         }
      });

      try {
         networkmanager.sendPacket(new RD(serveraddress.getIP(), serveraddress.getPort(), RB.STATUS));
         networkmanager.sendPacket(new Vw());
      } catch (Throwable var6) {
         Throwable throwable = var6;
         LOGGER.error(throwable);
      }

   }

   private void tryCompatibilityPing(final pf server) {
      final pd serveraddress = pd.fromString(server.serverIP);
      ((Bootstrap)((Bootstrap)((Bootstrap)(new Bootstrap()).group((EventLoopGroup)Sp.CLIENT_NIO_EVENTLOOP.getValue())).handler(new ChannelInitializer<Channel>() {
         protected void initChannel(Channel p_initChannel_1_) throws Exception {
            try {
               p_initChannel_1_.config().setOption(ChannelOption.TCP_NODELAY, true);
            } catch (ChannelException var3) {
            }

            p_initChannel_1_.pipeline().addLast(new ChannelHandler[]{new SimpleChannelInboundHandler<ByteBuf>() {
               public void channelActive(ChannelHandlerContext p_channelActive_1_) throws Exception {
                  super.channelActive(p_channelActive_1_);
                  ByteBuf bytebuf = Unpooled.buffer();

                  try {
                     bytebuf.writeByte(254);
                     bytebuf.writeByte(1);
                     bytebuf.writeByte(250);
                     char[] achar = "MC|PingHost".toCharArray();
                     bytebuf.writeShort(achar.length);
                     char[] var4 = achar;
                     int var5 = achar.length;

                     int var6;
                     char c1;
                     for(var6 = 0; var6 < var5; ++var6) {
                        c1 = var4[var6];
                        bytebuf.writeChar(c1);
                     }

                     bytebuf.writeShort(7 + 2 * serveraddress.getIP().length());
                     bytebuf.writeByte(127);
                     achar = serveraddress.getIP().toCharArray();
                     bytebuf.writeShort(achar.length);
                     var4 = achar;
                     var5 = achar.length;

                     for(var6 = 0; var6 < var5; ++var6) {
                        c1 = var4[var6];
                        bytebuf.writeChar(c1);
                     }

                     bytebuf.writeInt(serveraddress.getPort());
                     p_channelActive_1_.channel().writeAndFlush(bytebuf).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
                  } finally {
                     bytebuf.release();
                  }
               }

               protected void channelRead0(ChannelHandlerContext p_channelRead0_1_, ByteBuf p_channelRead0_2_) throws Exception {
                  short short1 = p_channelRead0_2_.readUnsignedByte();
                  if (short1 == 255) {
                     String s = new String(p_channelRead0_2_.readBytes(p_channelRead0_2_.readShort() * 2).array(), StandardCharsets.UTF_16BE);
                     String[] astring = (String[])((String[])Iterables.toArray(pG.PING_RESPONSE_SPLITTER.split(s), String.class));
                     if ("§1".equals(astring[0])) {
                        int i = MathHelper.getInt(astring[1], 0);
                        String s1 = astring[2];
                        String s2 = astring[3];
                        int j = MathHelper.getInt(astring[4], -1);
                        int k = MathHelper.getInt(astring[5], -1);
                        server.version = -1;
                        server.gameVersion = s1;
                        server.serverMOTD = s2;
                        server.populationInfo = TextFormatting.GRAY + "" + j + "" + TextFormatting.DARK_GRAY + "/" + TextFormatting.GRAY + k;
                     }
                  }

                  p_channelRead0_1_.close();
               }

               public void exceptionCaught(ChannelHandlerContext p_exceptionCaught_1_, Throwable p_exceptionCaught_2_) throws Exception {
                  p_exceptionCaught_1_.close();
               }

               // $FF: synthetic method
               // $FF: bridge method
               protected void channelRead0(ChannelHandlerContext var1, Object var2) throws Exception {
                  this.channelRead0(var1, (ByteBuf)var2);
               }
            }});
         }
      })).channel(NioSocketChannel.class)).connect(serveraddress.getIP(), serveraddress.getPort());
   }

   public void pingPendingNetworks() {
      synchronized(this.pingDestinations) {
         Iterator<Sp> iterator = this.pingDestinations.iterator();

         while(iterator.hasNext()) {
            Sp networkmanager = (Sp)iterator.next();
            if (networkmanager.isChannelOpen()) {
               networkmanager.processReceivedPackets();
            } else {
               iterator.remove();
               networkmanager.handleDisconnection();
            }
         }

      }
   }

   public void clearPendingNetworks() {
      synchronized(this.pingDestinations) {
         Iterator<Sp> iterator = this.pingDestinations.iterator();

         while(iterator.hasNext()) {
            Sp networkmanager = (Sp)iterator.next();
            if (networkmanager.isChannelOpen()) {
               iterator.remove();
               networkmanager.closeChannel(new TextComponentTranslation("multiplayer.status.cancelled", new Object[0]));
            }
         }

      }
   }
}
