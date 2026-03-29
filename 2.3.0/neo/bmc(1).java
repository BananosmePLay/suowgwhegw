package neo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class bmc implements bma {
   private blN[] first;
   private blN[] repeat;
   private blN[] last;
   private int maxCount;
   private static final blN[] EMPTY = new blN[0];

   public bmc() {
      this((blN[])null, (blN[])null, (blN[])null);
   }

   public bmc(blN[] first, blN[] repeat, blN[] last) {
      this(first, repeat, last, Integer.MAX_VALUE);
   }

   public bmc(blN[] first, blN[] repeat, blN[] last, int maxCount) {
      this.maxCount = Integer.MAX_VALUE;
      this.first = normalize(first);
      this.repeat = normalize(repeat);
      this.last = normalize(last);
      this.maxCount = maxCount;
   }

   private static blN[] normalize(blN[] exprs) {
      return exprs == null ? EMPTY : exprs;
   }

   public blN[] getFirst() {
      return this.first;
   }

   public blN[] getRepeat() {
      return this.repeat;
   }

   public blN[] getLast() {
      return this.last;
   }

   public int getCountRepeat() {
      return this.first == null ? 0 : this.first.length;
   }

   public blN[] getParameterTypes(blU[] arguments) {
      int i = this.first.length + this.last.length;
      int j = arguments.length - i;
      int k = 0;

      for(int l = 0; l + this.repeat.length <= j && i + l + this.repeat.length <= this.maxCount; l += this.repeat.length) {
         ++k;
      }

      List<blN> list = new ArrayList();
      list.addAll(Arrays.asList(this.first));

      for(int i1 = 0; i1 < k; ++i1) {
         list.addAll(Arrays.asList(this.repeat));
      }

      list.addAll(Arrays.asList(this.last));
      blN[] aexpressiontype = (blN[])((blN[])list.toArray(new blN[list.size()]));
      return aexpressiontype;
   }

   public bmc first(blN... first) {
      return new bmc(first, this.repeat, this.last);
   }

   public bmc repeat(blN... repeat) {
      return new bmc(this.first, repeat, this.last);
   }

   public bmc last(blN... last) {
      return new bmc(this.first, this.repeat, last);
   }

   public bmc maxCount(int maxCount) {
      return new bmc(this.first, this.repeat, this.last, maxCount);
   }
}
