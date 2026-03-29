package neo;

import com.google.common.base.MoreObjects;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import java.util.HashMap;
import net.minecraft.util.math.ChunkPos;
import org.apache.commons.codec.digest.DigestUtils;

public class 0bq {
   public static final HashMap<String, Long2ObjectMap<bam>> field_b = new HashMap();
   public static bam field_a = null;
   private static String _ _;

   private static HashMap method_Md() {
      return field_b;
   }

   public static bam method_LT() {
      return method_Mc();
   }

   public static bam method_LQ(0bo a, int b, int c) {
      String d = method_LV(a);
      return (bam)((Long2ObjectMap)method_Ma().get(d)).get(ChunkPos.asLong(b, c));
   }

   private static bam method_LY() {
      return field_a;
   }

   public static bam method_LR(0bo a, int b, int c) {
      return (bam)MoreObjects.firstNonNull(method_LQ(a, b, c), method_LT());
   }

   private static String method_LV(0bo a) {
      return DigestUtils.md5Hex(a.getDimension().getName() + a.getMaxPlayers());
   }

   private static bam method_Mc() {
      return field_a;
   }

   private static void method_LZ(bam var0) {
      field_a = var0;
   }

   private static HashMap method_Ma() {
      return field_b;
   }

   public static void method_LS(0bo a, int b, int c) {
      bam d = new bam(a, b, c);
      ((Long2ObjectMap)method_Mb().get(method_LV(a))).put(ChunkPos.asLong(b, c), d);
      d.markLoaded((boolean)(3845 ^ -18788 ^ 14171 ^ -28989));
   }

   private static HashMap method_LX() {
      return field_b;
   }

   private static HashMap method_LW() {
      return field_b;
   }

   public _bq/* $FF was: 0bq*/() {
   }

   public static void method_LP(0bo b) {
      String c = method_LV(b);
      if (!method_LW().containsKey(c)) {
         Long2ObjectMap<bam> a = new 0bp(2637 ^ -32737 ^ 6309 ^ -19721);
         method_LX().put(c, a);
      }

      if (method_LY() == null) {
         method_LZ(new bao(b, 14049 ^ -8060 ^ 6975 ^ -12966, 23015 ^ -17402 ^ 15032 ^ -8359));
      }

   }

   private static HashMap method_Mb() {
      return field_b;
   }

   public static void method_LU() {
      method_Md().clear();
   }
}
