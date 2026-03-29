package neo;

import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.SecretKey;
import net.minecraft.util.CryptManager;

public class RJ implements Sz<RN> {
   private byte[] secretKeyEncrypted = new byte[0];
   private byte[] verifyTokenEncrypted = new byte[0];

   public RJ() {
   }

   public RJ(SecretKey secret, PublicKey key, byte[] verifyToken) {
      this.secretKeyEncrypted = CryptManager.encryptData(key, secret.getEncoded());
      this.verifyTokenEncrypted = CryptManager.encryptData(key, verifyToken);
   }

   public void readPacketData(SA buf) throws IOException {
      this.secretKeyEncrypted = buf.readByteArray();
      this.verifyTokenEncrypted = buf.readByteArray();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeByteArray(this.secretKeyEncrypted);
      buf.writeByteArray(this.verifyTokenEncrypted);
   }

   public void processPacket(RN handler) {
      handler.processEncryptionResponse(this);
   }

   public SecretKey getSecretKey(PrivateKey key) {
      return CryptManager.decryptSharedKey(key, this.secretKeyEncrypted);
   }

   public byte[] getVerifyToken(PrivateKey key) {
      return key == null ? this.verifyTokenEncrypted : CryptManager.decryptData(key, this.verifyTokenEncrypted);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((RN)var1);
   }
}
