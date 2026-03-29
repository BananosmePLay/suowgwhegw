package neo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class boA extends boz {
   private static final Pattern PATTERN_CONST = Pattern.compile("^\\s*const\\s*bool\\s*([A-Za-z0-9_]+)\\s*=\\s*(true|false)\\s*;\\s*(//.*)?$");

   public boA(String name, String description, String value, String path) {
      super(name, description, value, path);
   }

   public String getSourceLine() {
      return "const bool " + this.getName() + " = " + this.getValue() + "; // Shader option " + this.getValue();
   }

   public static bou parseOption(String line, String path) {
      Matcher matcher = PATTERN_CONST.matcher(line);
      if (!matcher.matches()) {
         return null;
      } else {
         String s = matcher.group(1);
         String s1 = matcher.group(2);
         String s2 = matcher.group(3);
         if (s != null && s.length() > 0) {
            path = bqP.removePrefix(path, "/shaders/");
            bou shaderoption = new boA(s, s2, s1, path);
            ((bou)shaderoption).setVisible(false);
            return shaderoption;
         } else {
            return null;
         }
      }
   }

   public boolean matchesLine(String line) {
      Matcher matcher = PATTERN_CONST.matcher(line);
      if (!matcher.matches()) {
         return false;
      } else {
         String s = matcher.group(1);
         return s.matches(this.getName());
      }
   }

   public boolean checkUsed() {
      return false;
   }
}
