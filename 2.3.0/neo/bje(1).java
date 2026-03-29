package neo;

public class bje {
   private bjd[] ranges = new bjd[0];

   public bje() {
   }

   public bje(bjd ri) {
      this.addRange(ri);
   }

   public void addRange(bjd ri) {
      this.ranges = (bjd[])((bjd[])XH.addObjectToArray(this.ranges, ri));
   }

   public boolean isInRange(int val) {
      for(int i = 0; i < this.ranges.length; ++i) {
         bjd rangeint = this.ranges[i];
         if (rangeint.isInRange(val)) {
            return true;
         }
      }

      return false;
   }

   public int getCountRanges() {
      return this.ranges.length;
   }

   public bjd getRange(int i) {
      return this.ranges[i];
   }

   public String toString() {
      StringBuffer stringbuffer = new StringBuffer();
      stringbuffer.append("[");

      for(int i = 0; i < this.ranges.length; ++i) {
         bjd rangeint = this.ranges[i];
         if (i > 0) {
            stringbuffer.append(", ");
         }

         stringbuffer.append(rangeint.toString());
      }

      stringbuffer.append("]");
      return stringbuffer.toString();
   }
}
