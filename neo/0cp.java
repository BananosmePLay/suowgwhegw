package neo;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Mouse;

public class 0cp extends 0cB {
   public static float zoom = Float.intBitsToFloat('땸' ^ '褠' ^ 4647 ^ 1529552445 ^ 28341 ^ 96179 ^ '욛' ^ 464187359);

   private static KeyBinding qK4pKUvt2A(GameSettings var0) {
      return var0.ofKeyBindZoom;
   }

   private static void T97LeGa2gN(float var0) {
      zoom = var0;
   }

   private static void Ibwu1bqMAf(float var0) {
      zoom = var0;
   }

   private static void _BYBVSiUDd/* $FF was: 5BYBVSiUDd*/(float var0) {
      zoom = var0;
   }

   private static float avuIjUI7rQ() {
      return zoom;
   }

   private static float L6mXsoQTKN() {
      return zoom;
   }

   public _cp/* $FF was: 0cp*/() {
      super(akjJBwe2B2("˽˖ˑː˜ˊ˓˞ˍ"), 0bV.Player);
   }

   private static GameSettings PTeMHILBSc() {
      return Minecraft.gameSettings;
   }

   private static float FMrIJnrXGA() {
      return zoom;
   }

   @0X
   public void onEventUpdate(0K e) {
      int kek = Mouse.getDWheel();
      if (GameSettings.isKeyDown(qK4pKUvt2A(PTeMHILBSc()))) {
         if (kek >= (27917 ^ -15367 ^ 27919 ^ -15366)) {
            JuXDL3VjhZ((float)((double)avuIjUI7rQ() + (Mouse.isButtonDown(1777 ^ -6125 ^ 17464 ^ -21800) ? Double.longBitsToDouble(-6809101620227999059L ^ -2188408402545870163L) : Double.longBitsToDouble(2480277874247536049L ^ 2126312605282767915L))));
         }

         if (Mouse.getDWheel() > kek) {
            UJS6gJbIy9((float)((double)L6mXsoQTKN() - (Mouse.isButtonDown(8403 ^ -590 ^ 16226 ^ -7679) ? Double.longBitsToDouble(2995730710129278774L ^ 7616423927811407670L) : Double.longBitsToDouble(5258604620755656709L ^ 8580422248954329503L))));
         }

         if (FMrIJnrXGA() < Float.intBitsToFloat(27830 ^ 23096 ^ 7705 ^ 1752997228 ^ 17064 ^ 26468 ^ 2674 ^ 679254597)) {
            Ibwu1bqMAf(Float.intBitsToFloat('黴' ^ '\ua95d' ^ 4061 ^ -1545580576 ^ '젊' ^ '텉' ^ 1186 ^ -471831947));
         }

         if (S3lbp67DYY() > Float.intBitsToFloat(524282 ^ 502522 ^ 6903 ^ -1662277851 ^ 123864 ^ 126169 ^ 21417 ^ -549145478)) {
            T97LeGa2gN(Float.intBitsToFloat(492250 ^ 28640 ^ 502437 ^ 226200687 ^ 12063 ^ 32338 ^ 7190 ^ 1322550955));
         }
      }

   }

   private static void JuXDL3VjhZ(float var0) {
      zoom = var0;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String akjJBwe2B2(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 14731 ^ -28863 ^ 23338 ^ -4640; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 11807 ^ -6952 ^ 19415 ^ -31825));
      }

      return var1.toString();
   }

   private static void UJS6gJbIy9(float var0) {
      zoom = var0;
   }

   private static float S3lbp67DYY() {
      return zoom;
   }

   public void onDisable() {
      super.onDisable();
      5BYBVSiUDd(Float.intBitsToFloat('鬇' ^ '웭' ^ 397 ^ 860686068 ^ '앾' ^ '鞑' ^ 12045 ^ 1942824817));
   }

   public void onEnable() {
      super.onEnable();
   }
}
