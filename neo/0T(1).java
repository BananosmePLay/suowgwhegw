package neo;

import net.minecraft.entity.player.EntityPlayer;

public class 0T implements 0p {
   public boolean canceled;
   public EntityPlayer player;
   public Runnable callback;

   public boolean isCanceled() {
      return yuhFxvp4F6(this);
   }

   private static void gfQEdWimyL(0T var0, boolean var1) {
      var0.canceled = var1;
   }

   public _T/* $FF was: 0T*/(EntityPlayer player, Runnable callback) {
      this.player = player;
      this.callback = callback;
   }

   private static boolean yuhFxvp4F6(0T var0) {
      return var0.canceled;
   }

   public EntityPlayer getPlayer() {
      return iXehwwADlF(this);
   }

   private static EntityPlayer iXehwwADlF(0T var0) {
      return var0.player;
   }

   private static Runnable FGUTJuSQ4t(0T var0) {
      return var0.callback;
   }

   public void setCanceled() {
      gfQEdWimyL(this, (boolean)(17442 ^ -14150 ^ 9982 ^ -21913));
   }

   public void draw() {
      FGUTJuSQ4t(this).run();
   }
}
