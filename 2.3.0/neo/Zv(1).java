package neo;

import com.google.common.cache.CacheLoader;
import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class Zv extends CacheLoader<Long, bcc[]> {
   private Zv() {
   }

   public bcc[] load(Long p_load_1_) throws Exception {
      List<Integer> list = Lists.newArrayList(ContiguousSet.create(Range.closedOpen(0, 10), DiscreteDomain.integers()));
      Collections.shuffle(list, new Random(p_load_1_));
      bcc[] aworldgenspikes$endspike = new bcc[10];

      for(int i = 0; i < 10; ++i) {
         int j = (int)(42.0 * Math.cos(2.0 * (-3.141592653589793 + 0.3141592653589793 * (double)i)));
         int k = (int)(42.0 * Math.sin(2.0 * (-3.141592653589793 + 0.3141592653589793 * (double)i)));
         int l = (Integer)list.get(i);
         int i1 = 2 + l / 3;
         int j1 = 76 + l * 3;
         boolean flag = l == 1 || l == 2;
         aworldgenspikes$endspike[i] = new bcc(j, k, i1, j1, flag);
      }

      return aworldgenspikes$endspike;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public Object load(Object var1) throws Exception {
      return this.load((Long)var1);
   }

   // $FF: synthetic method
   Zv(Object x0) {
      this();
   }
}
