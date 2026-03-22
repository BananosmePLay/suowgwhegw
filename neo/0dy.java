package neo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.network.NetworkPlayerInfo;

public class 0dy implements 0eB {
   public static String format(String message) {
      return message.replace(lm6nz1RLJY("ݤܱܭܠܸܤܳܲ"), getRandomPlayer()).replace(lm6nz1RLJY("ݤܲܶܤܠܳ"), 0es.getRandom()).replace(lm6nz1RLJY("ݤܲ"), 0ej.randomString(23273 ^ -32138 ^ 19676 ^ -27579)).replace(lm6nz1RLJY("ݤܯ"), 0ej.randomNumber(23820 ^ -4300 ^ 13112 ^ -32504)).replace(lm6nz1RLJY("ݤܢܩܨܯܠ"), 0ej.randomChineseString(2443 ^ -32744 ^ 22744 ^ -11933)).replace(lm6nz1RLJY("ݤܨܭܨܭ"), 0ej.randomStringByPattern(2150 ^ -21152 ^ 25231 ^ -14459, lm6nz1RLJY("ܭ܈"))).replace(lm6nz1RLJY("ݤܮݱܮݱ"), 0ej.randomStringByPattern(6310 ^ -23683 ^ 6609 ^ -24058, lm6nz1RLJY("ݱܮ\u070e")));
   }

   private static String getRandomPlayer() {
      try {
         ArrayList<String> players = new ArrayList();
         Iterator var1 = ((NetHandlerPlayClient)Objects.requireNonNull(OcvYJTlJIm().getConnection())).getPlayerInfoMap().iterator();

         while(var1.hasNext()) {
            NetworkPlayerInfo player = (NetworkPlayerInfo)var1.next();
            String nickname = player.getGameProfile().getName();
            if (0cC.getBot(nickname) == null && player.getResponseTime() > 0) {
               players.add(player.getGameProfile().getName());
            }
         }

         return (String)players.get(7yewqNgP2j().nextInt(players.size()));
      } catch (Exception var4) {
         return lm6nz1RLJY("ܤܬܱܸܵ");
      }
   }

   public _dy/* $FF was: 0dy*/() {
   }

   private static Minecraft OcvYJTlJIm() {
      return mc;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String lm6nz1RLJY(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 11924 ^ -29294 ^ 23904 ^ -410; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 23227 ^ -22768 ^ 25928 ^ -24670));
      }

      return var1.toString();
   }

   private static Random _yewqNgP2j/* $FF was: 7yewqNgP2j*/() {
      return random;
   }
}
