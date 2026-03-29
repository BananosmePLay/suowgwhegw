package neo;

import net.minecraft.item.ItemStack;

public class 0W extends 0n {
   public final int x;
   public final int y;
   public final ItemStack stack;

   public ItemStack getStack() {
      return bowRlujY6y(this);
   }

   private static int DK6meu1JfN(0W var0) {
      return var0.y;
   }

   public int getX() {
      return yBQg6vaa7n(this);
   }

   public int getY() {
      return DK6meu1JfN(this);
   }

   public _W/* $FF was: 0W*/(ItemStack stack, int x, int y) {
      this.stack = stack;
      this.x = x;
      this.y = y;
   }

   private static ItemStack bowRlujY6y(0W var0) {
      return var0.stack;
   }

   private static int yBQg6vaa7n(0W var0) {
      return var0.x;
   }
}
