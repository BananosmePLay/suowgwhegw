package neo;

import java.util.Map;

public class Ax {
   private static AH i18nLocale;

   public Ax() {
   }

   static void setLocale(AH i18nLocaleIn) {
      i18nLocale = i18nLocaleIn;
   }

   public static String format(String translateKey, Object... parameters) {
      return i18nLocale.formatMessage(translateKey, parameters);
   }

   public static boolean hasKey(String key) {
      return i18nLocale.hasKey(key);
   }

   public static Map getLocaleProperties() {
      return i18nLocale.properties;
   }
}
