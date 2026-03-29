package neo;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.exceptions.AuthenticationUnavailableException;
import com.mojang.authlib.exceptions.InvalidCredentialsException;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.math.BigInteger;
import java.security.PublicKey;
import javax.annotation.Nullable;
import javax.crypto.SecretKey;
import net.minecraft.util.CryptManager;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class pt implements RM {
   private static final Logger LOGGER = LogManager.getLogger();
   private final nC mc;
   @Nullable
   private final lg previousGuiScreen;
   private final Sp networkManager;
   private GameProfile gameProfile;

   public pt(Sp networkManagerIn, nC mcIn, @Nullable lg previousScreenIn) {
      this.networkManager = networkManagerIn;
      this.mc = mcIn;
      this.previousGuiScreen = previousScreenIn;
   }

   public void handleEncryptionRequest(RS packetIn) {
      final SecretKey secretkey = CryptManager.createNewSharedKey();
      String s = packetIn.getServerId();
      PublicKey publickey = packetIn.getPublicKey();
      String s1 = (new BigInteger(CryptManager.getServerIdHash(s, publickey, secretkey))).toString(16);
      if (this.mc.getCurrentServerData() != null && this.mc.getCurrentServerData().isOnLAN()) {
         try {
            this.getSessionService().joinServer(this.mc.getSession().getProfile(), this.mc.getSession().getToken(), s1);
         } catch (AuthenticationException var10) {
            LOGGER.warn("Couldn't connect to auth servers but will continue to join LAN");
         }
      } else {
         try {
            this.getSessionService().joinServer(this.mc.getSession().getProfile(), this.mc.getSession().getToken(), s1);
         } catch (AuthenticationUnavailableException var7) {
            this.networkManager.closeChannel(new TextComponentTranslation("disconnect.loginFailedInfo", new Object[]{new TextComponentTranslation("disconnect.loginFailedInfo.serversUnavailable", new Object[0])}));
            return;
         } catch (InvalidCredentialsException var8) {
            this.networkManager.closeChannel(new TextComponentTranslation("disconnect.loginFailedInfo", new Object[]{new TextComponentTranslation("disconnect.loginFailedInfo.invalidSession", new Object[0])}));
            return;
         } catch (AuthenticationException var9) {
            AuthenticationException authenticationexception = var9;
            this.networkManager.closeChannel(new TextComponentTranslation("disconnect.loginFailedInfo", new Object[]{authenticationexception.getMessage()}));
            return;
         }
      }

      this.networkManager.sendPacket(new RJ(secretkey, publickey, packetIn.getVerifyToken()), new GenericFutureListener<Future<? super Void>>() {
         public void operationComplete(Future<? super Void> p_operationComplete_1_) throws Exception {
            pt.this.networkManager.enableEncryption(secretkey);
         }
      });
   }

   private MinecraftSessionService getSessionService() {
      return this.mc.getSessionService();
   }

   public void handleLoginSuccess(RT packetIn) {
      this.gameProfile = packetIn.getProfile();
      this.networkManager.setConnectionState(RB.PLAY);
      this.networkManager.setNetHandler(new py(this.mc, this.previousGuiScreen, this.networkManager, this.gameProfile));
   }

   public void onDisconnect(ITextComponent reason) {
      this.mc.displayGuiScreen(new kd(this.previousGuiScreen, "connect.failed", reason));
   }

   public void handleDisconnect(RQ packetIn) {
      this.networkManager.closeChannel(packetIn.getReason());
   }

   public void handleEnableCompression(RR packetIn) {
      if (!this.networkManager.isLocalChannel()) {
         this.networkManager.setCompressionThreshold(packetIn.getCompressionThreshold());
      }

   }
}
