package neo;

import com.google.common.collect.Maps;
import java.util.Map;

public enum Wn {
   INTEGER("integer"),
   HEARTS("hearts");

   private static final Map<String, Wn> BY_NAME = Maps.newHashMap();
   private final String renderType;

   private Wn(String renderTypeIn) {
      this.renderType = renderTypeIn;
   }

   public String getRenderType() {
      return this.renderType;
   }

   public static Wn getByName(String name) {
      Wn iscorecriteria$enumrendertype = (Wn)BY_NAME.get(name);
      return iscorecriteria$enumrendertype == null ? INTEGER : iscorecriteria$enumrendertype;
   }

   static {
      Wn[] var0 = values();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         Wn iscorecriteria$enumrendertype = var0[var2];
         BY_NAME.put(iscorecriteria$enumrendertype.getRenderType(), iscorecriteria$enumrendertype);
      }

   }
}
