package neo;

public interface RM extends RH {
   void handleEncryptionRequest(RS var1);

   void handleLoginSuccess(RT var1);

   void handleDisconnect(RQ var1);

   void handleEnableCompression(RR var1);
}
