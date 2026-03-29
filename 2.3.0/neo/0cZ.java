package neo;

import net.minecraft.inventory.ClickType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class 0cZ extends 0cR {
   private static int _DSC GG NEOWARECLIENT _;

   public static BlockPos getBlockPos(int a, int b, int c) {
      return new BlockPos(a, b, c);
   }

   public static EnumFacing getEnumFacing(String a) {
      return EnumFacing.byName(a);
   }

   public static String stripColor(String a) {
      return 0X.stripColor(a);
   }

   public static void sendMessage(String a) {
      method_byH(AS4QVwbUQh("ϣϓςϙπτϱϠϹ"), AS4QVwbUQh("σϕϞϔϽϕσσϑϗϕΘϣτςϙϞϗΙ"));
      0ek.addMessage(a);
   }

   public static float getDistance(0a a, double b, double c, double d) {
      method_byH(AS4QVwbUQh("ϣϓςϙπτϱϠϹ"), AS4QVwbUQh("ϗϕτϴϙστϑϞϓϕΘϲϟτΜΐϔϟυϒϜϕΜΐϔϟυϒϜϕΜΐϔϟυϒϜϕΙ"));
      return 0X.getDistance(a, b, c, d);
   }

   public static ClickType getClickType(String a) {
      return ClickType.valueOf(a);
   }

   public static float normalizePitch(float a) {
      method_byH(AS4QVwbUQh("ϣϓςϙπτϱϠϹ"), AS4QVwbUQh("ϞϟςϝϑϜϙϊϕϠϙτϓϘΘϖϜϟϑτΙ"));
      return 0X.normalizePitch(a);
   }

   public _cZ/* $FF was: 0cZ*/() {
   }

   public static double getDistance(double a, double b, double c, double d, double e, double f) {
      method_byH(AS4QVwbUQh("ϣϓςϙπτϱϠϹ"), AS4QVwbUQh("ϗϕτϴϙστϑϞϓϕΘϔϟυϒϜϕΜΐϔϟυϒϜϕΜΐϔϟυϒϜϕΜΐϔϟυϒϜϕΜΐϔϟυϒϜϕΜΐϔϟυϒϜϕΙ"));
      return 0X.getDistance(a, b, c, d, e, f);
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String AS4QVwbUQh(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 14519 ^ -8826 ^ 5910 ^ -3545; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 17350 ^ -11212 ^ 28193 ^ -1437));
      }

      return var1.toString();
   }

   public static String getSession() throws Exception {
      return 0dI.method_bDA();
   }

   public static String parsePlaceholders(String a) {
      method_byH(AS4QVwbUQh("ϣϓςϙπτϱϠϹ"), AS4QVwbUQh("πϑςσϕϠϜϑϓϕϘϟϜϔϕςσΘϣτςϙϞϗΙ"));
      return 0V.format(a);
   }

   public static 0a[] getBots() {
      0a[] a = new 0a[0e.getBots().size()];
      return (0a[])0e.getBots().toArray(a);
   }

   public static String translateItemKey(String a) {
      return 0Z.method_kY(a);
   }

   public static float normalizeYaw(float a) {
      method_byH(AS4QVwbUQh("ϣϓςϙπτϱϠϹ"), AS4QVwbUQh("ϞϟςϝϑϜϙϊϕϩϑχϖϜϟϑτΘΙ"));
      return 0X.normalizeYaw(a);
   }
}
