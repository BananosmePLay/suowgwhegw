package neo;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

public class 0cu extends 0cB {
   public float coldwnLast;
   public static 0bw color = new 0bw(0OOoGmEbaP("ŎŚŝĪ"), (new Color(31258 ^ -10934 ^ 17000 ^ -4777, 27762 ^ -24639 ^ 10778 ^ -9741, 21869 ^ -11469 ^ 18581 ^ -12746)).getRGB());

   @0X
   public void onRender2D(0P event) {
      int crosshairColor = nJL6gZ1aqA(Yw7nh07qeO());
      float screenWidth = (float)event.getResolution().getScaledWidth();
      float screenHeight = (float)event.getResolution().getScaledHeight();
      float width = screenWidth / Float.intBitsToFloat(25837 ^ 91930 ^ 117672 ^ 2026660948 ^ 110698 ^ '腧' ^ 127117 ^ 952921483);
      float height = screenHeight / Float.intBitsToFloat(8148 ^ 114460 ^ 22589 ^ 1557066798 ^ '豚' ^ 120890 ^ '럳' ^ 483323720);
      yqvoy6bJyp();
      double cinc = (double)(Ob4qO0WLBB().getCooledAttackStrength(Float.intBitsToFloat(130087 ^ 101302 ^ 2136 ^ 1258856267 ^ 114792 ^ 15605 ^ 120276 ^ 1258867147)) * Float.intBitsToFloat(10726 ^ 246554 ^ 259145 ^ -2020227514 ^ 13103 ^ '\uf259' ^ '뺜' ^ -1004132583));
      oMvMkByCpn(this, 0ei.lerp((double)pro3d99ETn(this), (double)((float)cinc), Double.longBitsToDouble(8564883690303475836L ^ 5264610542442729596L)));
      0ew.drawCircle(width, height, Float.intBitsToFloat(14407 ^ 205629 ^ 'ꖂ' ^ -1253972992 ^ '逎' ^ 30835 ^ '꒙' ^ -1966991844), Float.intBitsToFloat('霷' ^ 245146 ^ '톬' ^ 2010236678 ^ '顊' ^ 29612 ^ '샯' ^ 879105806), Float.intBitsToFloat(15278 ^ 257798 ^ 251602 ^ 600427630 ^ 4415 ^ 214151 ^ 256824 ^ 1665757332), (new Color(25106 ^ -27990 ^ 21257 ^ -23675, 31451 ^ -15092 ^ 22707 ^ -6320, 28525 ^ -9231 ^ 20455 ^ -1201, 31059 ^ -24140 ^ 9946 ^ -381)).hashCode(), 22975 ^ -18142 ^ 12054 ^ -12408);
      0ew.drawCircle(width, height, Float.intBitsToFloat(19554 ^ 98859 ^ 15281 ^ 37414900 ^ 25973 ^ 114271 ^ 28602 ^ 1035642524), Float.intBitsToFloat(112385 ^ '관' ^ 121238 ^ -1872203780 ^ 18971 ^ 20061 ^ 12243 ^ -1343715330) + yzEfMJTgYC(this), Float.intBitsToFloat('\uf2bf' ^ '鯡' ^ 22183 ^ -1345303900 ^ 3840 ^ 18303 ^ 49 ^ -279954157), crosshairColor, 27086 ^ -3508 ^ 24620 ^ -1107);
   }

   public _cu/* $FF was: 0cu*/() {
      super(0OOoGmEbaP("ԫԚԇԛԛԀԉԁԚ"), 0bV.Render);
      0bC[] var10001 = new 0bC[31826 ^ -8489 ^ 20677 ^ -3519];
      var10001[17382 ^ -15278 ^ 19763 ^ -13689] = color;
      this.addSetting(var10001);
   }

   private static 0bw Yw7nh07qeO() {
      return color;
   }

   private static float pro3d99ETn(0cu var0) {
      return var0.coldwnLast;
   }

   private static void oMvMkByCpn(0cu var0, float var1) {
      var0.coldwnLast = var1;
   }

   private static float yzEfMJTgYC(0cu var0) {
      return var0.coldwnLast;
   }

   private static EntityPlayerSP Ob4qO0WLBB() {
      return Minecraft.player;
   }

   private static Minecraft yqvoy6bJyp() {
      return 0eB.mc;
   }

   private static int nJL6gZ1aqA(0bw var0) {
      return var0.color;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String _OOoGmEbaP/* $FF was: 0OOoGmEbaP*/(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 11337 ^ -20075 ^ 26668 ^ -2576; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 18585 ^ -50363 ^ '좣' ^ -16873));
      }

      return var1.toString();
   }
}
