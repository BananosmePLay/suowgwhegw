package neo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class boz extends bou {
   private static final Pattern PATTERN_DEFINE = Pattern.compile("^\\s*(//)?\\s*#define\\s+([A-Za-z0-9_]+)\\s*(//.*)?$");
   private static final Pattern PATTERN_IFDEF = Pattern.compile("^\\s*#if(n)?def\\s+([A-Za-z0-9_]+)(\\s*)?$");

   public boz(String name, String description, String value, String path) {
      super(name, description, value, new String[]{"false", "true"}, value, path);
   }

   public String getSourceLine() {
      return isTrue(this.getValue()) ? "#define " + this.getName() + " // Shader option ON" : "//#define " + this.getName() + " // Shader option OFF";
   }

   public String getValueText(String val) {
      String s = super.getValueText(val);
      if (s != val) {
         return s;
      } else {
         return isTrue(val) ? bmW.getOn() : bmW.getOff();
      }
   }

   public String getValueColor(String val) {
      return isTrue(val) ? "§a" : "§c";
   }

   public static bou parseOption(String line, String path) {
      Matcher matcher = PATTERN_DEFINE.matcher(line);
      if (!matcher.matches()) {
         return null;
      } else {
         String s = matcher.group(1);
         String s1 = matcher.group(2);
         String s2 = matcher.group(3);
         if (s1 != null && s1.length() > 0) {
            boolean flag = XH.equals(s, "//");
            boolean flag1 = !flag;
            path = bqP.removePrefix(path, "/shaders/");
            bou shaderoption = new boz(s1, s2, String.valueOf(flag1), path);
            return shaderoption;
         } else {
            return null;
         }
      }
   }

   public boolean matchesLine(String line) {
      Matcher matcher = PATTERN_DEFINE.matcher(line);
      if (!matcher.matches()) {
         return false;
      } else {
         String s = matcher.group(2);
         return s.matches(this.getName());
      }
   }

   public boolean checkUsed() {
      return true;
   }

   public boolean isUsedInLine(String line) {
      Matcher matcher = PATTERN_IFDEF.matcher(line);
      if (matcher.matches()) {
         String s = matcher.group(2);
         if (s.equals(this.getName())) {
            return true;
         }
      }

      return false;
   }

   public static boolean isTrue(String val) {
      return Boolean.valueOf(val);
   }
}
