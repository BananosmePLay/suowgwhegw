package neo;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.exceptions.AuthenticationUnavailableException;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.util.concurrent.Future;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;
import javax.crypto.SecretKey;
import net.minecraft.util.CryptManager;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class XD implements RN, ITickable {
   private static final AtomicInteger AUTHENTICATOR_THREAD_ID = new AtomicInteger(0);
   private static final Logger LOGGER = LogManager.getLogger();
   private static final Random RANDOM = new Random();
   private final byte[] verifyToken = new byte[4];
   private final Xx server;
   public final Sp networkManager;
   private XC currentLoginState;
   private int connectionTimer;
   private GameProfile loginGameProfile;
   private final String serverId;
   private SecretKey secretKey;
   private MG player;

   public XD(Xx serverIn, Sp networkManagerIn) {
      this.currentLoginState = XC.HELLO;
      this.serverId = "";
      this.server = serverIn;
      this.networkManager = networkManagerIn;
      RANDOM.nextBytes(this.verifyToken);
   }

   public void update() {
      if (this.currentLoginState == XC.READY_TO_ACCEPT) {
         this.tryAcceptPlayer();
      } else if (this.currentLoginState == XC.DELAY_ACCEPT) {
         MG entityplayermp = this.server.getPlayerList().getPlayerByUUID(this.loginGameProfile.getId());
         if (entityplayermp == null) {
            this.currentLoginState = XC.READY_TO_ACCEPT;
            this.server.getPlayerList().initializeConnectionToPlayer(this.networkManager, this.player);
            this.player = null;
         }
      }

      if (this.connectionTimer++ == 600) {
         this.disconnect(new TextComponentTranslation("multiplayer.disconnect.slow_login", new Object[0]));
      }

   }

   public void disconnect(ITextComponent reason) {
      try {
         LOGGER.info("Disconnecting {}: {}", this.getConnectionInfo(), reason.getUnformattedText());
         this.networkManager.sendPacket(new RQ(reason));
         this.networkManager.closeChannel(reason);
      } catch (Exception var3) {
         Exception exception = var3;
         LOGGER.error("Error whilst disconnecting player", exception);
      }

   }

   public void tryAcceptPlayer() {
      if (!this.loginGameProfile.isComplete()) {
         this.loginGameProfile = this.getOfflineProfile(this.loginGameProfile);
      }

      String s = this.server.getPlayerList().allowUserToConnect(this.networkManager.getRemoteAddress(), this.loginGameProfile);
      if (s != null) {
         this.disconnect(new TextComponentTranslation(s, new Object[0]));
      } else {
         this.currentLoginState = XC.ACCEPTED;
         if (this.server.getNetworkCompressionThreshold() >= 0 && !this.networkManager.isLocalChannel()) {
            this.networkManager.sendPacket(new RR(this.server.getNetworkCompressionThreshold()), new ChannelFutureListener() {
               public void operationComplete(ChannelFuture p_operationComplete_1_) throws Exception {
                  XD.this.networkManager.setCompressionThreshold(XD.this.server.getNetworkCompressionThreshold());
               }

               // $FF: synthetic method
               // $FF: bridge method
               public void operationComplete(Future var1) throws Exception {
                  this.operationComplete((ChannelFuture)var1);
               }
            });
         }

         this.networkManager.sendPacket(new RT(this.loginGameProfile));
         MG entityplayermp = this.server.getPlayerList().getPlayerByUUID(this.loginGameProfile.getId());
         if (entityplayermp != null) {
            this.currentLoginState = XC.DELAY_ACCEPT;
            this.player = this.server.getPlayerList().createPlayerForUser(this.loginGameProfile);
         } else {
            this.server.getPlayerList().initializeConnectionToPlayer(this.networkManager, this.server.getPlayerList().createPlayerForUser(this.loginGameProfile));
         }
      }

   }

   public void onDisconnect(ITextComponent reason) {
      LOGGER.info("{} lost connection: {}", this.getConnectionInfo(), reason.getUnformattedText());
   }

   public String getConnectionInfo() {
      return this.loginGameProfile != null ? this.loginGameProfile + " (" + this.networkManager.getRemoteAddress() + ")" : String.valueOf(this.networkManager.getRemoteAddress());
   }

   public void processLoginStart(RK packetIn) {
      Validate.validState(this.currentLoginState == XC.HELLO, "Unexpected hello packet", new Object[0]);
      this.loginGameProfile = packetIn.getProfile();
      if (this.server.isServerInOnlineMode() && !this.networkManager.isLocalChannel()) {
         this.currentLoginState = XC.KEY;
         this.networkManager.sendPacket(new RS("", this.server.getKeyPair().getPublic(), this.verifyToken));
      } else {
         this.currentLoginState = XC.READY_TO_ACCEPT;
      }

   }

   public void processEncryptionResponse(RJ packetIn) {
      Validate.validState(this.currentLoginState == XC.KEY, "Unexpected key packet", new Object[0]);
      PrivateKey privatekey = this.server.getKeyPair().getPrivate();
      if (!Arrays.equals(this.verifyToken, packetIn.getVerifyToken(privatekey))) {
         throw new IllegalStateException("Invalid nonce!");
      } else {
         this.secretKey = packetIn.getSecretKey(privatekey);
         this.currentLoginState = XC.AUTHENTICATING;
         this.networkManager.enableEncryption(this.secretKey);
         (new Thread("User Authenticator #" + AUTHENTICATOR_THREAD_ID.incrementAndGet()) {
            public void run() {
               GameProfile gameprofile = XD.this.loginGameProfile;

               try {
                  String s = (new BigInteger(CryptManager.getServerIdHash("", XD.this.server.getKeyPair().getPublic(), XD.this.secretKey))).toString(16);
                  XD.this.loginGameProfile = XD.this.server.getMinecraftSessionService().hasJoinedServer(new GameProfile((UUID)null, gameprofile.getName()), s, this.getAddress());
                  if (XD.this.loginGameProfile != null) {
                     XD.LOGGER.info("UUID of player {} is {}", XD.this.loginGameProfile.getName(), XD.this.loginGameProfile.getId());
                     XD.this.currentLoginState = XC.READY_TO_ACCEPT;
                  } else if (XD.this.server.isSinglePlayer()) {
                     XD.LOGGER.warn("Failed to verify username but will let them in anyway!");
                     XD.this.loginGameProfile = XD.this.getOfflineProfile(gameprofile);
                     XD.this.currentLoginState = XC.READY_TO_ACCEPT;
                  } else {
                     XD.this.disconnect(new TextComponentTranslation("multiplayer.disconnect.unverified_username", new Object[0]));
                     XD.LOGGER.error("Username '{}' tried to join with an invalid session", gameprofile.getName());
                  }
               } catch (AuthenticationUnavailableException var3) {
                  if (XD.this.server.isSinglePlayer()) {
                     XD.LOGGER.warn("Authentication servers are down but will let them in anyway!");
                     XD.this.loginGameProfile = XD.this.getOfflineProfile(gameprofile);
                     XD.this.currentLoginState = XC.READY_TO_ACCEPT;
                  } else {
                     XD.this.disconnect(new TextComponentTranslation("multiplayer.disconnect.authservers_down", new Object[0]));
                     XD.LOGGER.error("Couldn't verify username because servers are unavailable");
                  }
               }

            }

            @Nullable
            private InetAddress getAddress() {
               SocketAddress socketaddress = XD.this.networkManager.getRemoteAddress();
               return XD.this.server.getPreventProxyConnections() && socketaddress instanceof InetSocketAddress ? ((InetSocketAddress)socketaddress).getAddress() : null;
            }
         }).start();
      }
   }

   protected GameProfile getOfflineProfile(GameProfile original) {
      UUID uuid = UUID.nameUUIDFromBytes(("OfflinePlayer:" + original.getName()).getBytes(StandardCharsets.UTF_8));
      return new GameProfile(uuid, original.getName());
   }
}
