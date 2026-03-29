package neo;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.Set;

public enum Ux {
   X(0),
   Y(1),
   Z(2),
   Y_ROT(3),
   X_ROT(4);

   private final int bit;

   private Ux(int bitIn) {
      this.bit = bitIn;
   }

   private int getMask() {
      return 1 << this.bit;
   }

   private boolean isSet(int flags) {
      return (flags & this.getMask()) == this.getMask();
   }

   public static Set<Ux> unpack(int flags) {
      Set<Ux> set = EnumSet.noneOf(Ux.class);
      Ux[] var2 = values();
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         Ux spacketplayerposlook$enumflags = var2[var4];
         if (spacketplayerposlook$enumflags.isSet(flags)) {
            set.add(spacketplayerposlook$enumflags);
         }
      }

      return set;
   }

   public static int pack(Set<Ux> flags) {
      int i = 0;

      Ux spacketplayerposlook$enumflags;
      for(Iterator var2 = flags.iterator(); var2.hasNext(); i |= spacketplayerposlook$enumflags.getMask()) {
         spacketplayerposlook$enumflags = (Ux)var2.next();
      }

      return i;
   }
}
