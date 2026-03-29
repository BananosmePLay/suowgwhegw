package neo;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

public class bqw {
   private static Deque<bqv<Object>> dequeIterators = new ArrayDeque();

   public bqw() {
   }

   public static Iterator<Object> getReadOnly(List list) {
      synchronized(dequeIterators) {
         bqv<Object> iteratorreusable = (bqv)dequeIterators.pollFirst();
         if (iteratorreusable == null) {
            iteratorreusable = new bqu();
         }

         ((bqv)iteratorreusable).setList(list);
         return (Iterator)iteratorreusable;
      }
   }

   private static void finished(bqv<Object> iterator) {
      synchronized(dequeIterators) {
         if (dequeIterators.size() <= 1000) {
            iterator.setList((List)null);
            dequeIterators.addLast(iterator);
         }

      }
   }

   // $FF: synthetic method
   static void access$000(bqv x0) {
      finished(x0);
   }

   static {
      for(int i = 0; i < 1000; ++i) {
         bqu iteratorcache$iteratorreadonly = new bqu();
         dequeIterators.add(iteratorcache$iteratorreadonly);
      }

   }
}
