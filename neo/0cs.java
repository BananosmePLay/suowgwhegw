package neo;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

public class 0cs extends 0cB {
   private static EntityPlayerSP QNZQTbnBnt() {
      return Minecraft.player;
   }

   public void onDisable() {
      HFQmrblMDd().setSprinting((boolean)(12523 ^ -28806 ^ 1331 ^ -17758));
      super.onDisable();
   }

   private static EntityPlayerSP HFQmrblMDd() {
      return Minecraft.player;
   }

   @0X
   public void onUpdate(0K eventUpdate) {
      if (QNZQTbnBnt().getFoodStats().getFoodLevel() / (23183 ^ -18667 ^ 27862 ^ -32434) > (6521 ^ -12431 ^ 32552 ^ -22237)) {
         hdotk18MIS().setSprinting(0eo.isMoving());
      }

   }

   private static EntityPlayerSP hdotk18MIS() {
      return Minecraft.player;
   }

   public _cs/* $FF was: 0cs*/() {
      super(6bTIBvFsWo("ݤ݇݅ݞݙ݃"), 0bV.Player);
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String _bTIBvFsWo/* $FF was: 6bTIBvFsWo*/(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 17177 ^ -13771 ^ 1893 ^ -29111; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 12664 ^ -3346 ^ 24934 ^ -23097));
      }

      return var1.toString();
   }
}
