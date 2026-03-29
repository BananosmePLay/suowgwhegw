package neo;

import java.io.IOException;
import java.security.PublicKey;
import net.minecraft.util.CryptManager;

public class RS implements Sz<RM> {
   private String hashedServerId;
   private PublicKey publicKey;
   private byte[] verifyToken;

   public RS() {
   }

   public RS(String serverIdIn, PublicKey publicKeyIn, byte[] verifyTokenIn) {
      this.hashedServerId = serverIdIn;
      this.publicKey = publicKeyIn;
      this.verifyToken = verifyTokenIn;
   }

   public void readPacketData(SA buf) throws IOException {
      this.hashedServerId = buf.readString(20);
      this.publicKey = CryptManager.decodePublicKey(buf.readByteArray());
      this.verifyToken = buf.readByteArray();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeString(this.hashedServerId);
      buf.writeByteArray(this.publicKey.getEncoded());
      buf.writeByteArray(this.verifyToken);
   }

   public void processPacket(RM handler) {
      handler.handleEncryptionRequest(this);
   }

   public String getServerId() {
      return this.hashedServerId;
   }

   public PublicKey getPublicKey() {
      return this.publicKey;
   }

   public byte[] getVerifyToken() {
      return this.verifyToken;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((RM)var1);
   }
}
