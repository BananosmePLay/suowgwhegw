package neo;

import java.util.Objects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.ServerData;

class 0eb extends Thread {
   private static String mD664PMiLV() {
      return 0bK.VERSION_TYPE;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String qvAYYYlHxl(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 6016 ^ -29761 ^ 19028 ^ -10645; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 20474 ^ -1172 ^ 20795 ^ -7006));
      }

      return var1.toString();
   }

   private static 0bv ngkvzvYJQu() {
      return 0ch.hideServer;
   }

   private static EntityPlayerSP Bqoi6q6KKC() {
      return Minecraft.player;
   }

   // $FF: synthetic method
   _eb/* $FF was: 0eb*/(Object x0) {
      this();
   }

   public void run() {
      this.setName(qvAYYYlHxl("ŋŦżŬŠŽūĢŝşŌ"));

      try {
         while(true) {
            if (Bqoi6q6KKC() != null) {
               0ec.update(qvAYYYlHxl("řŪŽżŦŠšĵį") + hKCMw4awYB(), qvAYYYlHxl("ŜŪŽŹŪŽĵį") + (Minecraft.getMinecraft().isSingleplayer() ? qvAYYYlHxl("ŜŦšŨţŪſţŮŶŪŽ") : (oLnfSlDeQQ(ngkvzvYJQu()) ? qvAYYYlHxl("ŇŦūūŪš") : rJv6V7GvNi((ServerData)Objects.requireNonNull(Minecraft.getMinecraft().getCurrentServerData())))));
            } else {
               0ec.update(qvAYYYlHxl("řŪŽżŦŠšĵį") + mD664PMiLV(), qvAYYYlHxl("ņšįłŮŦšłŪšź"));
            }

            o4zjJ289AY().Discord_RunCallbacks();
            0et.sleep(5000L);
         }
      } catch (Exception var2) {
         0ec.stopRPC();
         super.run();
      }
   }

   private static boolean oLnfSlDeQQ(0bv var0) {
      return var0.value;
   }

   private static String rJv6V7GvNi(ServerData var0) {
      return var0.serverIP;
   }

   private static String hKCMw4awYB() {
      return 0bK.VERSION_TYPE;
   }

   private static 0dY o4zjJ289AY() {
      return 0dY.INSTANCE;
   }

   private _eb/* $FF was: 0eb*/() {
   }
}
