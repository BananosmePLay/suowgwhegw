package neo;

import java.util.Objects;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;

public class 0bU extends 0dr {
   private static String _DSC GG NEOWARECLIENT _;

   private static OL method_Wz() {
      return NK.WRITABLE_BOOK;
   }

   private static int method_WE(Container var0) {
      return var0.windowId;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_Wy(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 8624 ^ -18750 ^ 4411 ^ -31159; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 10877 ^ -19078 ^ 22365 ^ -13572));
      }

      return var1.toString();
   }

   private static Container method_WD(jh var0) {
      return var0.openContainer;
   }

   public _bU/* $FF was: 0bU*/() {
      super(method_Wy("ˠˏˁ˓˔˃˕"), 0dz.field_d, 15939 ^ -25119 ^ 894 ^ -24356);
   }

   private static jh method_WC() {
      return nC.player;
   }

   private static ClickType method_WF() {
      return ClickType.PICKUP;
   }

   private static nC method_WA() {
      return mc;
   }

   private static nC method_WB() {
      return mc;
   }

   public void method_bAU() {
      super.method_bAU();
      (new Thread(() -> {
         Qy d = new Qy(method_Wz());
         QW e = new QW();
         QQ f = new QQ();

         int c;
         for(c = 21193 ^ -2705 ^ 30094 ^ -11736; c < (22974 ^ -23753 ^ 20741 ^ -21570); ++c) {
            Ra a = new Ra(0ec.randomString(16448 ^ -22394 ^ 28042 ^ -30951));
            e.appendTag(a);
         }

         f.setString(method_Wy("ˇ˓˒ˎˉ˔"), method_WA().getSession().getUsername());
         f.setString(method_Wy("˒ˏ˒ˊ˃"), method_Wy("˲ˏ˒ˊ˃"));
         f.setTag(method_Wy("˖ˇˁ˃˕"), e);
         d.setTagInfo(method_Wy("˖ˇˁ˃˕"), e);
         d.setTagCompound(f);

         for(c = 13393 ^ -11658 ^ 26265 ^ -32578; c < (5447 ^ -22275 ^ 23719 ^ -8051); ++c) {
            ((py)Objects.requireNonNull(method_WB().getConnection())).sendPacket(new SF(method_WE(method_WD(method_WC())), 20122 ^ -16095 ^ 14264 ^ -18429, 23695 ^ -30386 ^ 31423 ^ -20610, method_WF(), d, (short)(12955 ^ -11478 ^ 8976 ^ -15711)));
            0eh.method_bFu(4687 ^ -3739 ^ 26840 ^ -29698);
         }

      })).start();
      this.method_bBi();
   }
}
