package neo;

import net.minecraft.block.Block;

public class 0y extends 0n implements 0p {
   public Block block;
   public boolean canceled;

   public void setBlock(Block block) {
      EufYWQy4Tx(this, block);
   }

   private static void EufYWQy4Tx(0y var0, Block var1) {
      var0.block = var1;
   }

   public _y/* $FF was: 0y*/(Block block) {
      this.block = block;
   }

   private static boolean _TygeW6nIr/* $FF was: 7TygeW6nIr*/(0y var0) {
      return var0.canceled;
   }

   public boolean isCanceled() {
      return 7TygeW6nIr(this);
   }

   public void setCanceled() {
      LsitAzjSYZ(this, (boolean)(23197 ^ -876 ^ 26732 ^ -12700));
   }

   public Block getBlock() {
      return orqYtOWIpe(this);
   }

   private static void LsitAzjSYZ(0y var0, boolean var1) {
      var0.canceled = var1;
   }

   private static Block orqYtOWIpe(0y var0) {
      return var0.block;
   }
}
