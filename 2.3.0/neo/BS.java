package neo;

import com.google.common.collect.AbstractIterator;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import java.util.Iterator;

class BS<T> extends AbstractIterator<T> {
   private final Iterator<T> leftItr;
   private final Iterator<T> rightItr;
   private final Object2IntMap<T> numbers;
   private T left;
   private T right;

   public BS(Iterator<T> leftIn, Iterator<T> rightIn, Object2IntMap<T> numbersIn) {
      this.leftItr = leftIn;
      this.rightItr = rightIn;
      this.numbers = numbersIn;
      this.left = leftIn.hasNext() ? leftIn.next() : null;
      this.right = rightIn.hasNext() ? rightIn.next() : null;
   }

   protected T computeNext() {
      if (this.left == null && this.right == null) {
         return this.endOfData();
      } else {
         int i;
         if (this.left == this.right) {
            i = 0;
         } else if (this.left == null) {
            i = 1;
         } else if (this.right == null) {
            i = -1;
         } else {
            i = Integer.compare(this.numbers.getInt(this.left), this.numbers.getInt(this.right));
         }

         T t = i <= 0 ? this.left : this.right;
         if (i <= 0) {
            this.left = this.leftItr.hasNext() ? this.leftItr.next() : null;
         }

         if (i >= 0) {
            this.right = this.rightItr.hasNext() ? this.rightItr.next() : null;
         }

         return t;
      }
   }
}
