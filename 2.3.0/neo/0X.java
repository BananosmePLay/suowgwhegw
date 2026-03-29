package neo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import javax.vecmath.Vector2f;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class 0X implements 0cD {
   public static final Pattern COLOR_PATTERN = Pattern.compile(wFEyerBjXV("ʼʫ˽ʽȳˏʤʹʭ˕ʹ˒˟ʹ˛ˆˉ"));
   private static int _DSC GG NEOWARECLIENT _;

   private static 0f T5HGywTstL(0a var0) {
      return var0.player;
   }

   public static EnumFacing getFacing(float a) {
      a = MathHelper.wrapDegrees(a);
      if (a >= Float.intBitsToFloat(8777 ^ 'ꁓ' ^ 12977 ^ -953993459 ^ 18450 ^ '뭡' ^ 31091 ^ 85396902) && a < Float.intBitsToFloat(12992 ^ '긛' ^ 11666 ^ -655871033 ^ 8767 ^ 'ﭾ' ^ 28085 ^ -1696844166)) {
         return qtSFn7e3km();
      } else if (a >= Float.intBitsToFloat(14608 ^ 103899 ^ 4755 ^ 524597918 ^ 18131 ^ 126922 ^ 6005 ^ 1567668906) && a < Float.intBitsToFloat(120931 ^ 98334 ^ 28883 ^ 311476318 ^ 1045554 ^ 25982 ^ 1041658 ^ 1368890694)) {
         return ILJQqn0vFI();
      } else if (a >= Float.intBitsToFloat(5645 ^ '\udd83' ^ '쨐' ^ -461935363 ^ '쥏' ^ '\uf59a' ^ 18588 ^ -1485825750) && a <= Float.intBitsToFloat('ꁧ' ^ '컫' ^ 18791 ^ -218020330 ^ '뙷' ^ '\uf3c6' ^ 24586 ^ -1338686394)) {
         return T61dQuK7GA();
      } else if (a >= Float.intBitsToFloat(31872 ^ '襚' ^ 29263 ^ -1185400923 ^ 112554 ^ 96948 ^ 10515 ^ 2053919805) && a < Float.intBitsToFloat(23208 ^ 1024568 ^ 15944 ^ -521671341 ^ 20473 ^ 24876 ^ 6587 ^ 602873061)) {
         return GSPybYN2Pb();
      } else {
         return a >= Float.intBitsToFloat(249087 ^ 28532 ^ 261810 ^ 1972733940 ^ 12392 ^ 81528 ^ 110649 ^ -1231919900) && a < Float.intBitsToFloat(22811 ^ '鱵' ^ 25723 ^ -1230690029 ^ 11128 ^ '\ue1ca' ^ 12212 ^ 1955687680) ? uI14JJLwcq() : lgEkLniIbs();
      }
   }

   private static EnumFacing qtSFn7e3km() {
      return EnumFacing.SOUTH;
   }

   private static EnumFacing ILJQqn0vFI() {
      return EnumFacing.WEST;
   }

   public static Ig getByName(String b) {
      Iterator var1 = D0B66164Td(NV1R6KlGb3(b7SBtpVY2h())).iterator();

      Ig a;
      do {
         if (!var1.hasNext()) {
            return null;
         }

         a = (Ig)var1.next();
      } while(!a.getName().contains(b));

      return a;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String wFEyerBjXV(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 8144 ^ -17165 ^ 12993 ^ -28190; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 19498 ^ -7852 ^ 2352 ^ -22822));
      }

      return var1.toString();
   }

   private static double _levNOrhlQ/* $FF was: 4levNOrhlQ*/(0f var0) {
      return var0.posX;
   }

   private static EnumFacing uI14JJLwcq() {
      return EnumFacing.EAST;
   }

   private static 0f _NIm5OBnW6/* $FF was: 2NIm5OBnW6*/(0a var0) {
      return var0.player;
   }

   private static nC b7SBtpVY2h() {
      return mc;
   }

   public static int findItem(0a b, OL c) {
      if (d5iOQVMlny(b).getHeldItemOffhand().getItem().equals(c)) {
         return 8492 ^ -28740 ^ 24607 ^ -12638;
      } else {
         int d = 18636 ^ -17740 ^ 30916 ^ -30020;

         for(Iterator var3 = I2tnI9qikW(yM7PYOhubZ(2NIm5OBnW6(b))).iterator(); var3.hasNext(); ++d) {
            Qy a = (Qy)var3.next();
            if (a.getItem().equals(c)) {
               return d;
            }
         }

         return -32042 ^ -4964 ^ 18698 ^ -10049;
      }
   }

   private static 0f LnstkdyYpo(0a var0) {
      return var0.player;
   }

   private static int VRJFoiiFRT(OR var0) {
      return var0.damageReduceAmount;
   }

   public static Vector2f getBlockAngles(double a, double b, double c, double d, double e, double f) {
      double g = a - d;
      double h = c - f;
      double i = b - e - Double.longBitsToDouble(4699886940998071132L ^ 9135932573958009692L);
      double j = (double)MathHelper.sqrt(g * g + h * h);
      float k = (float)Math.toDegrees(-Math.atan(g / h));
      float l = (float)(-Math.toDegrees(Math.atan(i / j)));
      double m = Math.toDegrees(Math.atan(h / g));
      if (h < Double.longBitsToDouble(-7516976041011177714L ^ -7516976041011177714L) && g < Double.longBitsToDouble(8283038498768549491L ^ 8283038498768549491L)) {
         k = (float)(Double.longBitsToDouble(6656547843008954165L ^ 2032899138071363381L) + m);
      } else if (h < Double.longBitsToDouble(7130641077838269491L ^ 7130641077838269491L) && g > Double.longBitsToDouble(3570640964550129806L ^ 3570640964550129806L)) {
         k = (float)(Double.longBitsToDouble(867975848325052158L ^ -3720769959500417282L) + m);
      }

      return new Vector2f(l, normalizeYaw(k));
   }

   private static MJ yM7PYOhubZ(0f var0) {
      return var0.inventory;
   }

   public static void setGoto(0a a, int b, int c, int d) {
      a.getFunction().method_cR(new 0z(a, new BlockPos(b, c, d)));
   }

   private static double _zVIXQt6lj/* $FF was: 0zVIXQt6lj*/(0f var0) {
      return var0.posZ;
   }

   public static double getDistance(double a, double b, double c, double d, double e, double f) {
      double g = a - d;
      double h = b - e;
      double i = c - f;
      return (double)MathHelper.sqrt(g * g + h * h + i * i);
   }

   public _X/* $FF was: 0X*/() {
   }

   private static List D0B66164Td(pm var0) {
      return var0.loadedEntityList;
   }

   private static pm NV1R6KlGb3(nC var0) {
      return var0.world;
   }

   public static int getArmorValue(OR c, Qy d, 0a e) {
      int f = VRJFoiiFRT(c);
      int g = 20639 ^ -7488 ^ 7428 ^ -20645;
      int h = (int)EwvGTeStS1(c);
      int i = c.getArmorMaterial().getDamageReductionAmount(tbeezqt7rg());
      Fa j = kIjXhSHNqB();
      if (j != null) {
         int a = Ft.getEnchantmentLevel(j, d);
         DamageSource b = DamageSource.causePlayerDamage(LnstkdyYpo(e));
         g = j.calcModifierDamage(a, b);
      }

      return f * (16381 ^ -2311 ^ 6625 ^ -12064) + g * (16500 ^ -21561 ^ 31396 ^ -28396) + h + i;
   }

   public static float getDistance(0a a, double b, double c) {
      float d = (float)(qrbcQDIIo2(T5HGywTstL(a)) - b);
      float e = (float)(5eFBMpYaFh(UHDeVCAnI9(a)) - c);
      return MathHelper.sqrt(d * d + e * e);
   }

   private static EnumFacing lgEkLniIbs() {
      return EnumFacing.SOUTH;
   }

   private static 0f aV9x8OWrr6(0a var0) {
      return var0.player;
   }

   private static 0f OiD9PmZD1u(0a var0) {
      return var0.player;
   }

   private static 0f e7ZajlgyTr(0a var0) {
      return var0.player;
   }

   private static Fa kIjXhSHNqB() {
      return NJ.PROTECTION;
   }

   public static float normalizeYaw(float a) {
      while(a > Float.intBitsToFloat(241977 ^ 244526 ^ 5539 ^ 825077294 ^ 234118 ^ 236946 ^ 9458 ^ 1922669180)) {
         a -= Float.intBitsToFloat('셕' ^ '\ue2ae' ^ 22102 ^ 372517850 ^ '\ue160' ^ '듅' ^ 8368 ^ 1434463074);
      }

      while(a < Float.intBitsToFloat(27005 ^ 22105 ^ 9270 ^ -1671256508 ^ 106964 ^ 92775 ^ 14968 ^ -1671279459)) {
         a += Float.intBitsToFloat(242149 ^ 227143 ^ 5285 ^ 1575843759 ^ 241051 ^ '뉚' ^ 257782 ^ 509170847);
      }

      return a;
   }

   public static ArrayList<BlockPos> getAllInBox(BlockPos d, BlockPos e) {
      ArrayList<BlockPos> f = new ArrayList();
      BlockPos g = new BlockPos(Math.min(d.getX(), e.getX()), Math.min(d.getY(), e.getY()), Math.min(d.getZ(), e.getZ()));
      BlockPos h = new BlockPos(Math.max(d.getX(), e.getX()), Math.max(d.getY(), e.getY()), Math.max(d.getZ(), e.getZ()));

      for(int c = g.getX(); c <= h.getX(); ++c) {
         for(int b = g.getY(); b <= h.getY(); ++b) {
            for(int a = g.getZ(); a <= h.getZ(); ++a) {
               f.add(new BlockPos(c, b, a));
            }
         }
      }

      return f;
   }

   public static float getDistance(0a a, double b, double c, double d) {
      float e = (float)(4levNOrhlQ(e7ZajlgyTr(a)) - b);
      float f = (float)(NN0IOSnoOe(OiD9PmZD1u(a)) - c);
      float g = (float)(0zVIXQt6lj(aV9x8OWrr6(a)) - d);
      return MathHelper.sqrt(e * e + f * f + g * g);
   }

   private static 0f UHDeVCAnI9(0a var0) {
      return var0.player;
   }

   private static 0f d5iOQVMlny(0a var0) {
      return var0.player;
   }

   private static EntityEquipmentSlot tbeezqt7rg() {
      return EntityEquipmentSlot.LEGS;
   }

   private static float EwvGTeStS1(OR var0) {
      return var0.toughness;
   }

   public static float normalizePitch(float a) {
      while(a > Float.intBitsToFloat(22711 ^ 520166 ^ 9703 ^ 76264929 ^ 28875 ^ 1019522 ^ 247 ^ 1178065385)) {
         a -= Float.intBitsToFloat(25858 ^ 26863 ^ 9331 ^ -314408636 ^ 22104 ^ '\udda8' ^ 21587 ^ -1342802055);
      }

      while(a < Float.intBitsToFloat(23942 ^ '빉' ^ 19454 ^ 874586792 ^ 8289 ^ '\ueec2' ^ 20472 ^ -158004286)) {
         a += Float.intBitsToFloat(20054 ^ 120169 ^ 3091 ^ -1945324267 ^ 7905 ^ 127323 ^ 1664 ^ -826750205);
      }

      return a;
   }

   public static String stripColor(String a) {
      return lg2rzk7u6V().matcher(a).replaceAll(wFEyerBjXV(""));
   }

   private static double _eFBMpYaFh/* $FF was: 5eFBMpYaFh*/(0f var0) {
      return var0.posZ;
   }

   private static EnumFacing T61dQuK7GA() {
      return EnumFacing.NORTH;
   }

   public static boolean isNullOrEmpty(Qy a) {
      return (boolean)(a != null && !a.isEmpty() ? 10543 ^ -1599 ^ 32465 ^ -20929 : 19618 ^ -2316 ^ 24515 ^ -6764);
   }

   private static NonNullList I2tnI9qikW(MJ var0) {
      return var0.mainInventory;
   }

   private static double qrbcQDIIo2(0f var0) {
      return var0.posX;
   }

   private static double NN0IOSnoOe(0f var0) {
      return var0.posY;
   }

   private static Pattern lg2rzk7u6V() {
      return COLOR_PATTERN;
   }

   private static EnumFacing GSPybYN2Pb() {
      return EnumFacing.NORTH;
   }
}
