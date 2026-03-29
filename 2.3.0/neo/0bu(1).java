package neo;

import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.event.HoverEvent;

public class 0bu extends 0cV {
   private static String _ _;

   private static 0bz method_PS() {
      return client;
   }

   private static HoverEvent.Action method_PT() {
      return HoverEvent.Action.SHOW_TEXT;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_PQ(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 30581 ^ -19465 ^ 4314 ^ -11176; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 28925 ^ -30401 ^ 3635 ^ -2667));
      }

      return var1.toString();
   }

   public _bu/* $FF was: 0bu*/() {
      super(method_PQ("ȌȁȈȔ"), method_PQ("ȇȋȉȉȅȊȀɊȌȁȈȔɊȀȁȗȇȖȍȔȐȍȋȊ"));
   }

   public void method_bze(String[] a) {
      0ek.addMessage(0cT.method_byX(method_PQ("ȇȋȉȉȅȊȀɊȌȁȈȔɊȍȊȂȋ")));
      method_PR().method_Qr().stream().filter((ax) -> {
         return (boolean)(ax.getClass() != 0bu.class ? 18867 ^ -6502 ^ 22286 ^ -2010 : 21855 ^ -3007 ^ 32027 ^ -9211);
      }).forEach((ax) -> {
         TextComponentString var10000 = new TextComponentString(method_PQ("Ʉ") + method_PS().method_Qr().method_bxu() + ax.method_bzg() + method_PQ("ɄɉɄ") + ax.method_bzh());
         Style var10001 = new Style();
         HoverEvent.Action var10004 = method_PT();
         String var10007 = method_PQ("ɮ");
         CharSequence[] var10008 = new CharSequence[29217 ^ -3928 ^ 7615 ^ -24777];
         var10008[17800 ^ -2500 ^ 1181 ^ -18647] = ax.method_bzh();
         0ek.addMessage(var10000.setStyle(var10001.setHoverEvent(new HoverEvent(var10004, new TextComponentString(String.join(var10007, var10008))))));
      });
   }

   private static 0bz method_PR() {
      return client;
   }
}
