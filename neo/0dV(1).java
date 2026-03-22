package neo;

import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;

public class 0dV extends Structure {
   public 0dS ready;
   public 0dR joinRequest;
   public 0dT spectateGame;
   public 0dO disconnected;
   public 0dP errored;
   public 0dQ joinGame;

   // $FF: synthetic method
   // $FF: bridge method
   private static String wOjtLAtAn5(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 14949 ^ -310 ^ 8348 ^ -7117; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 10940 ^ -3915 ^ 29408 ^ -22411));
      }

      return var1.toString();
   }

   public _dV/* $FF was: 0dV*/() {
   }

   protected List<String> getFieldOrder() {
      String[] var10000 = new String[16325 ^ -11329 ^ 21380 ^ -16392];
      var10000[18852 ^ -16011 ^ 13631 ^ -16914] = wOjtLAtAn5("îùýøå");
      var10000[18779 ^ -20953 ^ 11550 ^ -13725] = wOjtLAtAn5("øõïÿóòòùÿèùø");
      var10000[30371 ^ -24593 ^ 12762 ^ -10092] = wOjtLAtAn5("ùîîóîùø");
      var10000[29891 ^ -21313 ^ 11296 ^ -2977] = wOjtLAtAn5("öóõòÛýñù");
      var10000[10283 ^ -15937 ^ 8131 ^ -2477] = wOjtLAtAn5("ïìùÿèýèùÛýñù");
      var10000[1189 ^ -28908 ^ 10682 ^ -24050] = wOjtLAtAn5("öóõòÎùíéùïè");
      return Arrays.asList(var10000);
   }
}
