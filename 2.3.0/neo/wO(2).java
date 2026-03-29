package neo;

public class wO extends wZ<MY> {
   public wO(wC renderManagerIn, yK itemRendererIn) {
      super(renderManagerIn, NK.POTIONITEM, itemRendererIn);
   }

   public Qy getStackToRender(MY entityIn) {
      return entityIn.getPotion();
   }

   // $FF: synthetic method
   // $FF: bridge method
   public Qy getStackToRender(Ig var1) {
      return this.getStackToRender((MY)var1);
   }
}
