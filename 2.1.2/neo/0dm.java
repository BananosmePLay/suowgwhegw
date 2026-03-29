package neo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.vecmath.Vector2f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector3i;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class 0dm {
   public static Vector2f getBlockAngles(double d, double d2, double d3, double d4, double d5, double d6) {
      double d7 = d - d4;
      double d8 = d3 - d6;
      double d9 = d2 - d5 - Double.longBitsToDouble(-6972534820749504599L ^ -6859944830065242199L);
      double d10 = (double)MathHelper.sqrt(d7 * d7 + d8 * d8);
      float f = (float)Math.toDegrees(-Math.atan(d7 / d8));
      float f2 = (float)(-Math.toDegrees(Math.atan(d9 / d10)));
      double d11 = Math.toDegrees(Math.atan(d8 / d7));
      if (d8 < Double.longBitsToDouble(-2387289547458424896L ^ -2387289547458424896L) && d7 < Double.longBitsToDouble(1630854131410393601L ^ 1630854131410393601L)) {
         f = (float)(Double.longBitsToDouble(-1352372322339442247L ^ -5949843854442942023L) + d11);
      } else if (d8 < Double.longBitsToDouble(-4665086164201760717L ^ -4665086164201760717L) && d7 > Double.longBitsToDouble(7122499265589591937L ^ 7122499265589591937L)) {
         f = (float)(Double.longBitsToDouble(8197104903472759026L ^ -5649915838319607566L) + d11);
      }

      return new Vector2f(f2, normalizeYaw(f));
   }

   private static int aSg87JJvv4(Vector3i var0) {
      return var0.y;
   }

   public static float getDistance(0cC pbot, double targetX, double targetY, double targetZ) {
      float f = (float)(wcWNEj5WKM(re8d3S6euG(pbot)) - targetX);
      float f1 = (float)(R67bqA5RF0(iY8P7rt0Up(pbot)) - targetY);
      float f2 = (float)(ulH1xA6LaE(SqVmAF2aaF(pbot)) - targetZ);
      return MathHelper.sqrt(f * f + f1 * f1 + f2 * f2);
   }

   private static float gD0DzvNO3B(Vector3f var0) {
      return var0.z;
   }

   public static boolean isNullOrEmpty(ItemStack stack) {
      return (boolean)(stack != null && !stack.isEmpty() ? 2087 ^ -5709 ^ 32656 ^ -25084 : 14775 ^ -5341 ^ 15003 ^ -6130);
   }

   private static float GJywlKwOAg(Vector3f var0) {
      return var0.x;
   }

   private static 0cD uyVVcangT4(0cC var0) {
      return var0.player;
   }

   public static BlockPos vec3i_toBlockPos(Vector3i vec) {
      return new BlockPos(jLlrO0qxPq(vec), QtAaSdbhQq(vec), gHTLeHuCEA(vec));
   }

   private static double wcWNEj5WKM(0cD var0) {
      return var0.posX;
   }

   private static NonNullList LJaooa2Ta3(InventoryPlayer var0) {
      return var0.mainInventory;
   }

   public static int getArmorValue(ItemArmor item, ItemStack stack, 0cC p) {
      int armorPoints = YAghgZDOHU(item);
      int armorToughness = (int)Naf8Ayfteb(item);
      int armorType = item.getArmorMaterial().getDamageReductionAmount(cNVYA7N1cY());
      Enchantment protection = GiDWTyw0bp();
      int prtLvl = EnchantmentHelper.getEnchantmentLevel(protection, stack);
      DamageSource dmgSource = DamageSource.causePlayerDamage(SdvjXDIoCp(p));
      int prtPoints = protection.calcModifierDamage(prtLvl, dmgSource);
      return armorPoints * (25882 ^ -7006 ^ 26300 ^ -6399) + prtPoints * (26752 ^ -7610 ^ 4872 ^ -26163) + armorToughness + armorType;
   }

   private static int jLlrO0qxPq(Vector3i var0) {
      return var0.x;
   }

   private static 0cD SdvjXDIoCp(0cC var0) {
      return var0.player;
   }

   private static float Naf8Ayfteb(ItemArmor var0) {
      return var0.toughness;
   }

   private static InventoryPlayer ZkyCqYAYtw(0cD var0) {
      return var0.inventory;
   }

   private static float JYF9eyeqo9(Vector3f var0) {
      return var0.y;
   }

   private static 0cD nAVaevAJB7(0cC var0) {
      return var0.player;
   }

   private static float _AGbNJpMbL/* $FF was: 6AGbNJpMbL*/(Vector3f var0) {
      return var0.y;
   }

   private static 0cD wT5acgsIBG(0cC var0) {
      return var0.player;
   }

   public static float get2dDistance(0cC pbot, double targetX, double targetZ) {
      float f = (float)(myirVgum9K(nAVaevAJB7(pbot)) - targetX);
      float f2 = (float)(II29zlq9Bi(V9a6tP6wRM(pbot)) - targetZ);
      return MathHelper.sqrt(f * f + f2 * f2);
   }

   private static double TzXiyURuSG(Vector3d var0) {
      return var0.y;
   }

   private static double ir1wNIVAB8(0cD var0) {
      return var0.posX;
   }

   private static double R67bqA5RF0(0cD var0) {
      return var0.posY;
   }

   public static double getDistance(Vector3i pos1, Vector3i pos2) {
      double d0 = (double)(oHGnzJ1jsO(pos1) - wbGa8EJqLT(pos2));
      double d1 = (double)(42i2bbXSdL(pos1) - aSg87JJvv4(pos2));
      double d2 = (double)(jTvywCX7hM(pos1) - zNrvH3GBOP(pos2));
      return (double)MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
   }

   private static 0cD V9a6tP6wRM(0cC var0) {
      return var0.player;
   }

   private static double II29zlq9Bi(0cD var0) {
      return var0.posZ;
   }

   private static int QtAaSdbhQq(Vector3i var0) {
      return var0.y;
   }

   private static 0cD SGGkY0aPzz(0cC var0) {
      return var0.player;
   }

   public static int findItem(0cC bot, Item item) {
      if (SGGkY0aPzz(bot).getHeldItemOffhand().getItem().equals(item)) {
         return 14049 ^ -16159 ^ 24015 ^ -21534;
      } else {
         int slot = 4876 ^ -7220 ^ 12590 ^ -15890;

         for(Iterator var3 = LJaooa2Ta3(rLr99jtmyQ(uyVVcangT4(bot))).iterator(); var3.hasNext(); ++slot) {
            ItemStack stack = (ItemStack)var3.next();
            if (stack.getItem().equals(item)) {
               return slot;
            }
         }

         return -9967 ^ -12242 ^ 10378 ^ -8630;
      }
   }

   private static float de3VdjdwB7(Vector2f var0) {
      return var0.y;
   }

   public _dm/* $FF was: 0dm*/() {
   }

   private static float BYhXzY7IWO(Vector3f var0) {
      return var0.y;
   }

   private static Enchantment GiDWTyw0bp() {
      return Enchantments.PROTECTION;
   }

   private static InventoryPlayer rLr99jtmyQ(0cD var0) {
      return var0.inventory;
   }

   public static double getDistance(Vector3f pos1, Vector3f pos2) {
      double d0 = (double)(iU0Gu3R2NZ(pos1) - 9Hw3g7oegg(pos2));
      double d1 = (double)(BYhXzY7IWO(pos1) - rLWOxGWBak(pos2));
      double d2 = (double)(NLGI2Jy1Wd(pos1) - 7QQRqKe73k(pos2));
      return (double)MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
   }

   public static int getSword(0cC bot) {
      int slot = -17643 ^ -12385 ^ 27598 ^ -8005;

      for(int i = 5163 ^ -3353 ^ 32491 ^ -26585; i < (24885 ^ -13198 ^ 14996 ^ -26662); ++i) {
         ItemStack stack = ZkyCqYAYtw(7VhHpOpVxy(bot)).getStackInSlot(i);
         if (stack.getItem() instanceof ItemSword) {
            slot = i;
         }
      }

      return slot;
   }

   public static Entity getByName(String name) {
      Iterator var1 = 1Eq4B99qQF(ywrtA3DO8B(N4xw1hcwOd())).iterator();

      Entity entity;
      do {
         if (!var1.hasNext()) {
            return null;
         }

         entity = (Entity)var1.next();
      } while(!entity.getName().equals(name));

      return entity;
   }

   private static int oHGnzJ1jsO(Vector3i var0) {
      return var0.x;
   }

   private static int gHTLeHuCEA(Vector3i var0) {
      return var0.z;
   }

   public static float normalizePitch(float pitch) {
      while(pitch > Float.intBitsToFloat(30126 ^ '艠' ^ 16086 ^ -1168410105 ^ 29728 ^ 19445 ^ 4520 ^ -118514334)) {
         pitch -= Float.intBitsToFloat(4673 ^ 233633 ^ 31223 ^ -209858231 ^ 21833 ^ 251298 ^ 21912 ^ -1312162003);
      }

      while(pitch < Float.intBitsToFloat(19180 ^ 521127 ^ 16075 ^ 364625019 ^ 28723 ^ 26256 ^ 3336 ^ -687333296)) {
         pitch += Float.intBitsToFloat(29982 ^ '첐' ^ 2292 ^ -1790432427 ^ 103588 ^ '蒋' ^ 123681 ^ -671323871);
      }

      return pitch;
   }

   private static int YAghgZDOHU(ItemArmor var0) {
      return var0.damageReduceAmount;
   }

   private static double iLKF6ywWbe(Vector3d var0) {
      return var0.x;
   }

   public static float normalizeYaw(float yaw) {
      while(yaw > Float.intBitsToFloat(17130 ^ 126413 ^ 21165 ^ 1411972161 ^ 11894 ^ 105210 ^ 17972 ^ 396161907)) {
         yaw -= Float.intBitsToFloat(25313 ^ 'ꌥ' ^ 9529 ^ 293040267 ^ 23774 ^ '\ue228' ^ 3189 ^ 1388521205);
      }

      while(yaw < Float.intBitsToFloat(115171 ^ 90348 ^ 1582 ^ -887854246 ^ 130270 ^ 118218 ^ 16274 ^ -887831811)) {
         yaw += Float.intBitsToFloat(6011 ^ 30645 ^ 16850 ^ -1897165021 ^ 7198 ^ '誆' ^ 17186 ^ -849380475);
      }

      return yaw;
   }

   private static 0cD iY8P7rt0Up(0cC var0) {
      return var0.player;
   }

   private static double myirVgum9K(0cD var0) {
      return var0.posX;
   }

   public static IBlockState getBlockStateByPos(World world, BlockPos pos) {
      if (world == null) {
         return null;
      } else {
         IBlockState blockState = world.getBlockState(pos);
         return blockState;
      }
   }

   private static int jTvywCX7hM(Vector3i var0) {
      return var0.z;
   }

   private static 0cD u8GFJaQv9J(0cC var0) {
      return var0.player;
   }

   private static EntityEquipmentSlot cNVYA7N1cY() {
      return EntityEquipmentSlot.LEGS;
   }

   private static WorldClient ywrtA3DO8B(Minecraft var0) {
      return var0.world;
   }

   private static 0cD SqVmAF2aaF(0cC var0) {
      return var0.player;
   }

   private static int zNrvH3GBOP(Vector3i var0) {
      return var0.z;
   }

   private static List _Eq4B99qQF/* $FF was: 1Eq4B99qQF*/(WorldClient var0) {
      return var0.loadedEntityList;
   }

   private static 0cD _VhHpOpVxy/* $FF was: 7VhHpOpVxy*/(0cC var0) {
      return var0.player;
   }

   public static boolean hasSword(0cC bot) {
      Iterator var1 = hlaA1N8ZMQ(wJIo7v6BLr(u8GFJaQv9J(bot))).iterator();

      ItemStack stack;
      do {
         if (!var1.hasNext()) {
            return (boolean)(18167 ^ -19489 ^ 10936 ^ -8304);
         }

         stack = (ItemStack)var1.next();
      } while(!(stack.getItem() instanceof ItemSword));

      return (boolean)(21812 ^ -9841 ^ 17765 ^ -13857);
   }

   public static float get2Distance(0cC pbot, double targetX, double targetY, double targetZ) {
      float f = (float)(ir1wNIVAB8(wT5acgsIBG(pbot)) - targetX);
      float f2 = (float)(TLlSBweyGq(FB9INYKN42(pbot)) - targetZ);
      return MathHelper.sqrt(f * f + f2 * f2);
   }

   private static double TLlSBweyGq(0cD var0) {
      return var0.posZ;
   }

   private static float D0yBnJz3n3(Vector3f var0) {
      return var0.x;
   }

   private static float f1QykdJjGn(Vector2f var0) {
      return var0.x;
   }

   private static float _Hw3g7oegg/* $FF was: 9Hw3g7oegg*/(Vector3f var0) {
      return var0.x;
   }

   private static 0cD FB9INYKN42(0cC var0) {
      return var0.player;
   }

   private static double ulH1xA6LaE(0cD var0) {
      return var0.posZ;
   }

   private static InventoryPlayer wJIo7v6BLr(0cD var0) {
      return var0.inventory;
   }

   public static double getDistance(double px1, double py1, double pz1, double px2, double py2, double pz2) {
      double d0 = px1 - px2;
      double d1 = py1 - py2;
      double d2 = pz1 - pz2;
      return (double)MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
   }

   private static NonNullList hlaA1N8ZMQ(InventoryPlayer var0) {
      return var0.mainInventory;
   }

   private static Minecraft N4xw1hcwOd() {
      return 0eB.mc;
   }

   private static double GGq8SpliO6(Vector3d var0) {
      return var0.z;
   }

   private static float iU0Gu3R2NZ(Vector3f var0) {
      return var0.x;
   }

   private static float _rYVwvapW0/* $FF was: 4rYVwvapW0*/(Vector3f var0) {
      return var0.z;
   }

   private static float NLGI2Jy1Wd(Vector3f var0) {
      return var0.z;
   }

   private static float rLWOxGWBak(Vector3f var0) {
      return var0.y;
   }

   private static int _2i2bbXSdL/* $FF was: 42i2bbXSdL*/(Vector3i var0) {
      return var0.y;
   }

   public static double getDistanceSq(Vector3f pos1, Vector3f pos2) {
      double d0 = (double)(D0yBnJz3n3(pos1) - GJywlKwOAg(pos2));
      double d1 = (double)(6AGbNJpMbL(pos1) - JYF9eyeqo9(pos2));
      double d2 = (double)(4rYVwvapW0(pos1) - gD0DzvNO3B(pos2));
      return d0 * d0 + d1 * d1 + d2 * d2;
   }

   private static double p6gQnSGByL(Vector3d var0) {
      return var0.y;
   }

   private static float _QQRqKe73k/* $FF was: 7QQRqKe73k*/(Vector3f var0) {
      return var0.z;
   }

   private static 0cD re8d3S6euG(0cC var0) {
      return var0.player;
   }

   private static double nnJt5flGgD(Vector3d var0) {
      return var0.z;
   }

   public static double getDistance(Vector3d pos1, Vector3d pos2) {
      double d0 = iLKF6ywWbe(pos1) - Nc8tLleuPn(pos2);
      double d1 = p6gQnSGByL(pos1) - TzXiyURuSG(pos2);
      double d2 = GGq8SpliO6(pos1) - nnJt5flGgD(pos2);
      return (double)MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
   }

   private static float cc1s97S3Nh(Vector2f var0) {
      return var0.y;
   }

   private static float AGQLZBka6d(Vector2f var0) {
      return var0.x;
   }

   public static double getDistance(Vector2f pos1, Vector2f pos2) {
      double d0 = (double)(f1QykdJjGn(pos1) - AGQLZBka6d(pos2));
      double d1 = (double)(cc1s97S3Nh(pos1) - de3VdjdwB7(pos2));
      return (double)MathHelper.sqrt(d0 * d0 + d1 * d1);
   }

   public static Block getBlockByPos(World world, BlockPos pos) {
      if (world == null) {
         return null;
      } else {
         IBlockState blockState = world.getBlockState(pos);
         return blockState != null ? blockState.getBlock() : null;
      }
   }

   private static int wbGa8EJqLT(Vector3i var0) {
      return var0.x;
   }

   public static ArrayList<BlockPos> getAllInBox(BlockPos from, BlockPos to) {
      ArrayList<BlockPos> blocks = new ArrayList();
      BlockPos min = new BlockPos(Math.min(from.getX(), to.getX()), Math.min(from.getY(), to.getY()), Math.min(from.getZ(), to.getZ()));
      BlockPos max = new BlockPos(Math.max(from.getX(), to.getX()), Math.max(from.getY(), to.getY()), Math.max(from.getZ(), to.getZ()));

      for(int x = min.getX(); x <= max.getX(); ++x) {
         for(int y = min.getY(); y <= max.getY(); ++y) {
            for(int z = min.getZ(); z <= max.getZ(); ++z) {
               blocks.add(new BlockPos(x, y, z));
            }
         }
      }

      return blocks;
   }

   private static double Nc8tLleuPn(Vector3d var0) {
      return var0.x;
   }
}
