package neo;

public class tO {
   public tO() {
   }

   public void renderChestBrightness(co blockIn, float color) {
      yh.color(color, color, color, 1.0F);
      yh.rotate(90.0F, 0.0F, 1.0F, 0.0F);
      zw.instance.renderByItem(new Qy(blockIn));
   }
}
