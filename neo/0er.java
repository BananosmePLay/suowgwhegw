package neo;

import java.util.Objects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerAddress;
import net.minecraft.client.multiplayer.ServerData;

public class 0er {
   public _er/* $FF was: 0er*/() {
   }

   private static String lgUNr8PTeC(ServerData var0) {
      return var0.serverIP;
   }

   private static String v6Aep35rw1(ServerData var0) {
      return var0.serverIP;
   }

   public static int getPort() {
      return ServerAddress.fromString(v6Aep35rw1((ServerData)Objects.requireNonNull(Minecraft.getMinecraft().getCurrentServerData()))).getPort();
   }

   public static String getIp() {
      return ServerAddress.fromString(lgUNr8PTeC((ServerData)Objects.requireNonNull(Minecraft.getMinecraft().getCurrentServerData()))).getIP();
   }
}
