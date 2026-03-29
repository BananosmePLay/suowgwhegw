package neo;

public class OT extends OL {
   public OT() {
      this.setCreativeTab(EN.COMBAT);
   }

   public MO createArrow(bij worldIn, Qy stack, Iw shooter) {
      Ne entitytippedarrow = new Ne(worldIn, shooter);
      entitytippedarrow.setPotionEffect(stack);
      return entitytippedarrow;
   }
}
