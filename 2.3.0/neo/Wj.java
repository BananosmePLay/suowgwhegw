package neo;

public final class Wj implements Comparable<Wj> {
   public double usePercentage;
   public double totalUsePercentage;
   public String profilerName;

   public Wj(String profilerName, double usePercentage, double totalUsePercentage) {
      this.profilerName = profilerName;
      this.usePercentage = usePercentage;
      this.totalUsePercentage = totalUsePercentage;
   }

   public int compareTo(Wj p_compareTo_1_) {
      if (p_compareTo_1_.usePercentage < this.usePercentage) {
         return -1;
      } else {
         return p_compareTo_1_.usePercentage > this.usePercentage ? 1 : p_compareTo_1_.profilerName.compareTo(this.profilerName);
      }
   }

   public int getColor() {
      return (this.profilerName.hashCode() & 11184810) + 4473924;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public int compareTo(Object var1) {
      return this.compareTo((Wj)var1);
   }
}
