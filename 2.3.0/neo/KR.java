package neo;

public enum KR {
   NONE(0, 0.0, 0.0, 0.0),
   SUMMON_VEX(1, 0.7, 0.7, 0.8),
   FANGS(2, 0.4, 0.3, 0.35),
   WOLOLO(3, 0.7, 0.5, 0.2),
   DISAPPEAR(4, 0.3, 0.3, 0.8),
   BLINDNESS(5, 0.1, 0.1, 0.2);

   private final int id;
   private final double[] particleSpeed;

   private KR(int idIn, double xParticleSpeed, double yParticleSpeed, double zParticleSpeed) {
      this.id = idIn;
      this.particleSpeed = new double[]{xParticleSpeed, yParticleSpeed, zParticleSpeed};
   }

   public static KR getFromId(int idIn) {
      KR[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         KR entityspellcasterillager$spelltype = var1[var3];
         if (idIn == entityspellcasterillager$spelltype.id) {
            return entityspellcasterillager$spelltype;
         }
      }

      return NONE;
   }

   // $FF: synthetic method
   static int access$000(KR x0) {
      return x0.id;
   }

   // $FF: synthetic method
   static double[] access$100(KR x0) {
      return x0.particleSpeed;
   }
}
