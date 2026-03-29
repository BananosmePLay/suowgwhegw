package neo;

public class AD implements Comparable<AD> {
   private final String languageCode;
   private final String region;
   private final String name;
   private final boolean bidirectional;

   public AD(String languageCodeIn, String regionIn, String nameIn, boolean bidirectionalIn) {
      this.languageCode = languageCodeIn;
      this.region = regionIn;
      this.name = nameIn;
      this.bidirectional = bidirectionalIn;
   }

   public String getLanguageCode() {
      return this.languageCode;
   }

   public String getName() {
      return this.name;
   }

   public String getRegion() {
      return this.region;
   }

   public boolean isBidirectional() {
      return this.bidirectional;
   }

   public String toString() {
      return String.format("%s (%s)", this.name, this.region);
   }

   public boolean equals(Object p_equals_1_) {
      if (this == p_equals_1_) {
         return true;
      } else {
         return !(p_equals_1_ instanceof AD) ? false : this.languageCode.equals(((AD)p_equals_1_).languageCode);
      }
   }

   public int hashCode() {
      return this.languageCode.hashCode();
   }

   public int compareTo(AD p_compareTo_1_) {
      return this.languageCode.compareTo(p_compareTo_1_.languageCode);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public int compareTo(Object var1) {
      return this.compareTo((AD)var1);
   }
}
