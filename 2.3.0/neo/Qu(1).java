package neo;

public class Qu extends PI {
   public Qu(int healAmount) {
      super(healAmount, false);
      this.setMaxStackSize(1);
   }

   public Qy onItemUseFinish(Qy stack, bij worldIn, Iw entityLiving) {
      super.onItemUseFinish(stack, worldIn, entityLiving);
      return new Qy(NK.BOWL);
   }
}
