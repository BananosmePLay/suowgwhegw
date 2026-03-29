package neo;

import java.lang.reflect.Constructor;
import java.util.Arrays;

public class HK<T extends HB> {
   private static HK<?>[] phases = new HK[0];
   public static final HK<HG> HOLDING_PATTERN = create(HG.class, "HoldingPattern");
   public static final HK<HQ> STRAFE_PLAYER = create(HQ.class, "StrafePlayer");
   public static final HK<HJ> LANDING_APPROACH = create(HJ.class, "LandingApproach");
   public static final HK<HI> LANDING = create(HI.class, "Landing");
   public static final HK<HR> TAKEOFF = create(HR.class, "Takeoff");
   public static final HK<HO> SITTING_FLAMING = create(HO.class, "SittingFlaming");
   public static final HK<HP> SITTING_SCANNING = create(HP.class, "SittingScanning");
   public static final HK<HM> SITTING_ATTACKING = create(HM.class, "SittingAttacking");
   public static final HK<HE> CHARGING_PLAYER = create(HE.class, "ChargingPlayer");
   public static final HK<HF> DYING = create(HF.class, "Dying");
   public static final HK<HH> HOVER = create(HH.class, "Hover");
   private final Class<? extends HB> clazz;
   private final int id;
   private final String name;

   private HK(int idIn, Class<? extends HB> clazzIn, String nameIn) {
      this.id = idIn;
      this.clazz = clazzIn;
      this.name = nameIn;
   }

   public HB createPhase(HS dragon) {
      try {
         Constructor<? extends HB> constructor = this.getConstructor();
         return (HB)constructor.newInstance(dragon);
      } catch (Exception var3) {
         Exception exception = var3;
         throw new Error(exception);
      }
   }

   protected Constructor<? extends HB> getConstructor() throws NoSuchMethodException {
      return this.clazz.getConstructor(HS.class);
   }

   public int getId() {
      return this.id;
   }

   public String toString() {
      return this.name + " (#" + this.id + ")";
   }

   public static HK<?> getById(int idIn) {
      return idIn >= 0 && idIn < phases.length ? phases[idIn] : HOLDING_PATTERN;
   }

   public static int getTotalPhases() {
      return phases.length;
   }

   private static <T extends HB> HK<T> create(Class<T> phaseIn, String nameIn) {
      HK<T> phaselist = new HK(phases.length, phaseIn, nameIn);
      phases = (HK[])((HK[])Arrays.copyOf(phases, phases.length + 1));
      phases[phaselist.getId()] = phaselist;
      return phaselist;
   }
}
