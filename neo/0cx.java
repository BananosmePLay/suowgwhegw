package neo;

import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;

public class 0cx extends 0cB {
   public static 0bv cameraClip = new 0bv(dk4xUD6YSl("ҴҨҧҨҪҠ°ҪҠҬҥӐӛ"), (boolean)(13720 ^ -368 ^ 30031 ^ -16826));
   public static 0bv antiTotem = new 0bv(dk4xUD6YSl("ҀҭҨҬҠӖҨӟ°ӒҮӒҥҬҠ"), (boolean)(8862 ^ -29715 ^ 3643 ^ -22712));
   public static 0bv noBossBar = new 0bv(dk4xUD6YSl("ҎӒҪҫ¾°ҡҮӑӑҡҠӐ"), (boolean)(8751 ^ -25483 ^ 28771 ^ -12743));
   public static 0bv noHurtCam = new 0bv(dk4xUD6YSl("ұҤҢҨңҨ°ҪҠҬҥӐӛ"), (boolean)(25545 ^ -21370 ^ 29246 ^ -17040));
   public static 0bv blindness = new 0bv(dk4xUD6YSl("ұҫҥүҮӒҠ"), (boolean)(21118 ^ -31627 ^ 24950 ^ -18564));
   public static 0bv noArmorStand = new 0bv(dk4xUD6YSl("ұӒҥҭҤӛ°Ҥҫӟ°ҡӐҮҭҨ"), (boolean)(7462 ^ -32647 ^ 30264 ^ -5273));
   public static 0bv rain = new 0bv(dk4xUD6YSl("҄ҮҦҤӜ"), (boolean)(13809 ^ -28752 ^ 28839 ^ -13593));
   public static 0bv noFire = new 0bv(dk4xUD6YSl("ҎӒҪҫ¾°ҮҢҥӐҫҥҩ°Үңҭӟ"), (boolean)(5696 ^ -31756 ^ 29079 ^ -7133));

   public _cx/* $FF was: 0cx*/() {
      super(dk4xUD6YSl("ÞÿÂõþôõâ"), 0bV.Render);
      0bC[] var10001 = new 0bC[11545 ^ -7025 ^ 27772 ^ -23070];
      var10001[2754 ^ -3885 ^ 22326 ^ -21209] = rain;
      var10001[32506 ^ -8512 ^ 5546 ^ -19055] = noArmorStand;
      var10001[18921 ^ -25280 ^ 1417 ^ -11998] = noHurtCam;
      var10001[9359 ^ -25398 ^ 12352 ^ -30714] = antiTotem;
      var10001[18574 ^ -15726 ^ 30836 ^ -3476] = noFire;
      var10001[23644 ^ -1940 ^ 18283 ^ -7330] = blindness;
      var10001[26552 ^ -7325 ^ 12783 ^ -19150] = noBossBar;
      var10001[13193 ^ -25553 ^ 22320 ^ -1903] = cameraClip;
      this.addSetting(var10001);
   }

   private static EntityPlayerSP Nv1rn69d29() {
      return Minecraft.player;
   }

   private static Potion weWpGUDZth() {
      return MobEffects.NAUSEA;
   }

   private static WorldClient DfqY9wcaR3(Minecraft var0) {
      return var0.world;
   }

   private static WorldClient yWu3NV4mFC(Minecraft var0) {
      return var0.world;
   }

   private static boolean I0AiQttdTg(0bv var0) {
      return var0.value;
   }

   private static Potion _9A7TZ78Q6/* $FF was: 69A7TZ78Q6*/() {
      return MobEffects.BLINDNESS;
   }

   private static boolean UrVWIuXeuA(0bv var0) {
      return var0.value;
   }

   private static 0bv FL1gYDZY5A() {
      return noArmorStand;
   }

   @0X
   public void onUpdate(0K event) {
      if (UrVWIuXeuA(j1OEeNLj77()) && DfqY9wcaR3(nyA9PiwmlF()).isRaining()) {
         t9IANk077S(R4RDsVWn67()).setRainStrength(Float.intBitsToFloat(7991 ^ 19931 ^ 23301 ^ 458401675 ^ 8187 ^ 88383 ^ 105060 ^ 458388162));
         LPgGdv8rpI(YLrSDSrtVy()).setThunderStrength(Float.intBitsToFloat(26628 ^ 211310 ^ '웚' ^ 355057210 ^ '襍' ^ '謃' ^ 8402 ^ 355076886));
      }

      if (I0AiQttdTg(WiznJD2ILY()) && gQWX4DYHfL().isPotionActive(69A7TZ78Q6()) || lTzNVXLSOb().isPotionActive(TwiIOAmhll())) {
         Nv1rn69d29().removePotionEffect(weWpGUDZth());
         URp9S1EyZQ().removePotionEffect(Y7fjbYg3Wt());
      }

      if (fyonanX9QT(FL1gYDZY5A())) {
         if (AQYSt4OCG8() == null || QdlY7Arbw4(FBFLCzfdnl()) == null) {
            return;
         }

         Iterator var2 = zVrUGJwni9(v4IcwVNtZy(QJBXqVYSmQ())).iterator();

         while(var2.hasNext()) {
            Entity entity = (Entity)var2.next();
            if (entity != null && entity instanceof EntityArmorStand) {
               yWu3NV4mFC(SHAoRE1p9p()).removeEntity(entity);
            }
         }
      }

   }

   private static WorldClient t9IANk077S(Minecraft var0) {
      return var0.world;
   }

   private static EntityPlayerSP lTzNVXLSOb() {
      return Minecraft.player;
   }

   private static Potion TwiIOAmhll() {
      return MobEffects.NAUSEA;
   }

   private static 0bv WiznJD2ILY() {
      return blindness;
   }

   private static Minecraft FBFLCzfdnl() {
      return mc;
   }

   private static List zVrUGJwni9(WorldClient var0) {
      return var0.loadedEntityList;
   }

   private static Minecraft SHAoRE1p9p() {
      return mc;
   }

   private static WorldClient v4IcwVNtZy(Minecraft var0) {
      return var0.world;
   }

   private static Minecraft QJBXqVYSmQ() {
      return mc;
   }

   private static EntityPlayerSP AQYSt4OCG8() {
      return Minecraft.player;
   }

   private static Minecraft R4RDsVWn67() {
      return mc;
   }

   private static boolean fyonanX9QT(0bv var0) {
      return var0.value;
   }

   private static EntityPlayerSP gQWX4DYHfL() {
      return Minecraft.player;
   }

   private static 0bv j1OEeNLj77() {
      return rain;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String dk4xUD6YSl(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 26797 ^ -10299 ^ 31766 ^ -15490; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 27098 ^ -4592 ^ 24843 ^ -6575));
      }

      return var1.toString();
   }

   private static EntityPlayerSP URp9S1EyZQ() {
      return Minecraft.player;
   }

   private static Minecraft YLrSDSrtVy() {
      return mc;
   }

   private static WorldClient QdlY7Arbw4(Minecraft var0) {
      return var0.world;
   }

   private static Minecraft nyA9PiwmlF() {
      return mc;
   }

   private static Potion Y7fjbYg3Wt() {
      return MobEffects.BLINDNESS;
   }

   private static WorldClient LPgGdv8rpI(Minecraft var0) {
      return var0.world;
   }
}
