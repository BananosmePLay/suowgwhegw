package neo;

import net.minecraft.client.Minecraft;
import net.minecraft.util.math.MathHelper;

public class 0dI {
   public final double animationSpeed;
   public double animationState;
   public double prevAnimationState;

   public _dI/* $FF was: 0dI*/() {
      this(Double.longBitsToDouble(-87568548465763700L ^ -87568548465763700L));
   }

   private static double zVrghfVOFI(0dI var0) {
      return var0.animationSpeed;
   }

   public _dI/* $FF was: 0dI*/(double animationSpeed) {
      this.animationSpeed = Double.longBitsToDouble(540750600225786711L ^ 4053823494532282468L) + animationSpeed;
   }

   private static double SeBMFsa4gr(0dI var0) {
      return var0.animationState;
   }

   public void reset() {
      PnRTEy1qnI(this, Double.longBitsToDouble(-7974620634059329941L ^ -7974620634059329941L));
      lmEb6GTsI6(this, Double.longBitsToDouble(6743355208661947911L ^ 6743355208661947911L));
   }

   private static void v77oaZFWFB(0dI var0, double var1) {
      var0.prevAnimationState = var1;
   }

   private static double MSSH0eJUCn(0dI var0) {
      return var0.animationState;
   }

   private static void PnRTEy1qnI(0dI var0, double var1) {
      var0.prevAnimationState = var1;
   }

   public void set(double animation) {
      ivoeHcdNT7(this, animation);
      TCJwnH0eIr(this, animation);
   }

   public double dropAnimation(double value) {
      double c1 = Double.longBitsToDouble(-3623546487250272455L ^ -986945822531957160L);
      double c3 = c1 + Double.longBitsToDouble(-1832401726226507680L ^ -2782661247601682336L);
      return Double.longBitsToDouble(5662501745785707358L ^ 8171006738231073630L) + c3 * Math.pow(value - Double.longBitsToDouble(2591428298638665853L ^ 2019471145962612861L), Double.longBitsToDouble(6297017393528901941L ^ 1687583174915199285L)) + c1 * Math.pow(value - Double.longBitsToDouble(2039298397669669095L ^ 2575226753326758119L), Double.longBitsToDouble(2341442773852259384L ^ 6953128792279647288L));
   }

   private static double fw5bd6PjSc(0dI var0) {
      return var0.prevAnimationState;
   }

   public void update(boolean add) {
      v77oaZFWFB(this, SeBMFsa4gr(this));
      3lQiI2DHES(this, MathHelper.clamp(DNbFwhTS7o(this) + (add ? 1caloSd6Fd(this) : -zVrghfVOFI(this)), Double.longBitsToDouble(8224379492727934684L ^ 8224379492727934684L), Double.longBitsToDouble(-458800702309186638L ^ -4156255996380363854L)));
   }

   private static void _lQiI2DHES/* $FF was: 3lQiI2DHES*/(0dI var0, double var1) {
      var0.animationState = var1;
   }

   private static void TCJwnH0eIr(0dI var0, double var1) {
      var0.prevAnimationState = var1;
   }

   public double createAnimation(double value) {
      return Math.sqrt(Double.longBitsToDouble(-6158711388250067660L ^ -7676424462673924812L) - Math.pow(value - Double.longBitsToDouble(8614152987627872583L ^ 5222942468217889095L), Double.longBitsToDouble(8306510734363227785L ^ 3694824715935839881L)));
   }

   private static double DNbFwhTS7o(0dI var0) {
      return var0.animationState;
   }

   private static double _caloSd6Fd/* $FF was: 1caloSd6Fd*/(0dI var0) {
      return var0.animationSpeed;
   }

   private static void lmEb6GTsI6(0dI var0, double var1) {
      var0.animationState = var1;
   }

   private static double ItzgMo6RCU(0dI var0) {
      return var0.animationState;
   }

   private static double yrKPmFPPiQ(0dI var0) {
      return var0.prevAnimationState;
   }

   public double getDrop() {
      return this.dropAnimation(oDGRrreZAw(this) + (ItzgMo6RCU(this) - lAKSrBV6na(this)) * (double)Minecraft.getMinecraft().getRenderPartialTicks());
   }

   private static double oDGRrreZAw(0dI var0) {
      return var0.prevAnimationState;
   }

   public double get() {
      return this.createAnimation(fw5bd6PjSc(this) + (MSSH0eJUCn(this) - yrKPmFPPiQ(this)) * (double)Minecraft.getMinecraft().getRenderPartialTicks());
   }

   private static double lAKSrBV6na(0dI var0) {
      return var0.prevAnimationState;
   }

   private static void ivoeHcdNT7(0dI var0, double var1) {
      var0.animationState = var1;
   }
}
