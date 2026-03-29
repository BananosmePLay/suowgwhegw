package neo;

import java.util.Comparator;

public class bjH implements Comparator {
   public bjH() {
   }

   public int compare(Object o1, Object o2) {
      bjE customitemproperties = (bjE)o1;
      bjE customitemproperties1 = (bjE)o2;
      if (customitemproperties.weight != customitemproperties1.weight) {
         return customitemproperties1.weight - customitemproperties.weight;
      } else {
         return !XH.equals(customitemproperties.basePath, customitemproperties1.basePath) ? customitemproperties.basePath.compareTo(customitemproperties1.basePath) : customitemproperties.name.compareTo(customitemproperties1.name);
      }
   }
}
