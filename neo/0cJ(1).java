package neo;

import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.login.INetHandlerLoginClient;
import net.minecraft.network.login.server.SPacketDisconnect;
import net.minecraft.network.login.server.SPacketEnableCompression;
import net.minecraft.network.login.server.SPacketEncryptionRequest;
import net.minecraft.network.login.server.SPacketLoginSuccess;
import net.minecraft.util.text.ITextComponent;

public class 0cJ implements INetHandlerLoginClient {
   public final 0cP networkManager;
   public final 0cC pbot;

   private static 0cP jd9VfUn8JV(0cJ var0) {
      return var0.networkManager;
   }

   public void handleLoginSuccess(SPacketLoginSuccess packetIn) {
      jd9VfUn8JV(this).setConnectionState(192724iQjq());
      zod6O3ytit(this).setNetHandler(new 0cL(bcNweitIGO(this)));
   }

   private static EnumConnectionState _92724iQjq/* $FF was: 192724iQjq*/() {
      return EnumConnectionState.PLAY;
   }

   private static 0cC CTRWA6MFDH(0cJ var0) {
      return var0.pbot;
   }

   private static boolean _pUsriw54L/* $FF was: 7pUsriw54L*/(0bv var0) {
      return var0.value;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String BTOGN5dyyy(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 28103 ^ -2044 ^ 25074 ^ -3023; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 21639 ^ -48520 ^ '텃' ^ -15569));
      }

      return var1.toString();
   }

   public _cJ/* $FF was: 0cJ*/(0cP networkManagerIn, 0cC pbot) {
      this.networkManager = networkManagerIn;
      this.pbot = pbot;
   }

   private static 0cP bREWjrvWJy(0cJ var0) {
      return var0.networkManager;
   }

   public void onDisconnect(ITextComponent reason) {
   }

   private static 0bv SPDi9GWGYs() {
      return 0cc.disconnect;
   }

   private static 0bv rIpqYGdq6n() {
      return 0cc.disconnect;
   }

   private static 0cP zod6O3ytit(0cJ var0) {
      return var0.networkManager;
   }

   public void handleEnableCompression(SPacketEnableCompression packetIn) {
      if (!bREWjrvWJy(this).isLocalChannel()) {
         wQOy2X9YcQ(this).setCompressionThreshold(packetIn.getCompressionThreshold());
      }

   }

   private static 0cC bcNweitIGO(0cJ var0) {
      return var0.pbot;
   }

   private static 0cC Jt0aaRAwdn(0cJ var0) {
      return var0.pbot;
   }

   private static boolean _4riOejTWl/* $FF was: 94riOejTWl*/(0bv var0) {
      return var0.value;
   }

   public void handleEncryptionRequest(SPacketEncryptionRequest packetIn) {
      if (94riOejTWl(SPDi9GWGYs())) {
         0dK.formatMsg(BTOGN5dyyy("\u008dÑ©¨ÝÔ¦®«¦ҳҵӷҵӿ") + Jt0aaRAwdn(this).getNickname() + BTOGN5dyyy("ҵӵҵӿҳӚӽӥӲӿӺӷҳӠӶӠӠӺӼӽҳһӇӡӪҳӡӶӠӧӲӡӧӺӽӴҳӪӼӦӡҳӴӲӾӶҳӲӽӷҳӧӻӶҳӿӲӦӽӰӻӶӡҺ"));
      }

   }

   private static 0cP wQOy2X9YcQ(0cJ var0) {
      return var0.networkManager;
   }

   public void handleDisconnect(SPacketDisconnect packetIn) {
      if (7pUsriw54L(rIpqYGdq6n())) {
         0dK.formatMsg(BTOGN5dyyy("\u008dÑ©¨ÝÔ¦®«¦ҳҵӷҵӿ") + CTRWA6MFDH(this).getNickname() + BTOGN5dyyy("ҵӵҵӿҳ") + packetIn.getReason().getFormattedText());
      }

      4i2dRoxrBe(this).closeChannel(packetIn.getReason());
   }

   private static 0cP _i2dRoxrBe/* $FF was: 4i2dRoxrBe*/(0cJ var0) {
      return var0.networkManager;
   }
}
