package neo;

public enum xS {
   LINEAR(9729),
   EXP(2048),
   EXP2(2049);

   public final int capabilityId;

   private xS(int capabilityIn) {
      this.capabilityId = capabilityIn;
   }
}
