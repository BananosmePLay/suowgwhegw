package neo;

import java.net.InetAddress;
import java.net.UnknownHostException;
import net.minecraft.client.Minecraft;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.status.INetHandlerStatusClient;
import net.minecraft.network.status.client.CPacketPing;
import net.minecraft.network.status.client.CPacketServerQuery;
import net.minecraft.network.status.server.SPacketPong;
import net.minecraft.network.status.server.SPacketServerInfo;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import org.jetbrains.annotations.NotNull;

public class 0cT {
   public _cT/* $FF was: 0cT*/() {
   }

   private static EnumConnectionState NBAn5MvJXO() {
      return EnumConnectionState.STATUS;
   }

   public void ping(0cC pbot) throws UnknownHostException {
      InetAddress serveraddress = InetAddress.getByName(pbot.getHost());
      final 0cP networkmanager = 0cP.createNetworkManagerAndConnect(serveraddress, pbot.getPort(), pbot, pbot.getProxy());
      networkmanager.setNetHandler(new INetHandlerStatusClient() {
         // $FF: synthetic method
         // $FF: bridge method
         private static String blzNAqtyn0(String var0) {
            StringBuilder var1 = new StringBuilder();

            for(int var2 = 196 ^ -7945 ^ 32294 ^ -25067; var2 < var0.length(); ++var2) {
               var1.append((char)(var0.charAt(var2) ^ 9634 ^ -5966 ^ 21009 ^ -25448));
            }

            return var1.toString();
         }

         private static 0cP vlG44TYqXn(Object var0) {
            return networkmanager;
         }

         private static 0cP _7POdmoVaZ/* $FF was: 47POdmoVaZ*/(Object var0) {
            return networkmanager;
         }

         public void handleServerInfo(@NotNull SPacketServerInfo packetIn) {
            47POdmoVaZ(this).sendPacket(new CPacketPing(Minecraft.getSystemTime()));
         }

         public void handlePong(@NotNull SPacketPong packetIn) {
            vlG44TYqXn(this).closeChannel(new TextComponentString(blzNAqtyn0("ϟϰϷϰϪϱϼϽ")));
         }

         public void onDisconnect(@NotNull ITextComponent reason) {
         }
      });

      try {
         networkmanager.sendPacket(new C00Handshake(pbot.getHost(), pbot.getPort(), NBAn5MvJXO()));
         networkmanager.sendPacket(new CPacketServerQuery());
      } catch (Throwable var5) {
         Throwable throwable = var5;
         throwable.printStackTrace();
      }

   }
}
