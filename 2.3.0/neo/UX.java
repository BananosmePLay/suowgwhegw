package neo;

import java.util.Locale;

public enum UX {
   TITLE,
   SUBTITLE,
   ACTIONBAR,
   TIMES,
   CLEAR,
   RESET;

   private UX() {
   }

   public static UX byName(String name) {
      UX[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         UX spackettitle$type = var1[var3];
         if (spackettitle$type.name().equalsIgnoreCase(name)) {
            return spackettitle$type;
         }
      }

      return TITLE;
   }

   public static String[] getNames() {
      String[] astring = new String[values().length];
      int i = 0;
      UX[] var2 = values();
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         UX spackettitle$type = var2[var4];
         astring[i++] = spackettitle$type.name().toLowerCase(Locale.ROOT);
      }

      return astring;
   }
}
