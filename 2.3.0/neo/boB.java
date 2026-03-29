package neo;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class boB extends bou {
   private static final Pattern PATTERN_VARIABLE = Pattern.compile("^\\s*#define\\s+(\\w+)\\s+(-?[0-9\\.Ff]+|\\w+)\\s*(//.*)?$");

   public boB(String name, String description, String value, String[] values, String path) {
      super(name, description, value, values, value, path);
      this.setVisible(this.getValues().length > 1);
   }

   public String getSourceLine() {
      return "#define " + this.getName() + " " + this.getValue() + " // Shader option " + this.getValue();
   }

   public String getValueText(String val) {
      String s = bpq.translate("prefix." + this.getName(), "");
      String s1 = super.getValueText(val);
      String s2 = bpq.translate("suffix." + this.getName(), "");
      String s3 = s + s1 + s2;
      return s3;
   }

   public String getValueColor(String val) {
      String s = val.toLowerCase();
      return !s.equals("false") && !s.equals("off") ? "§a" : "§c";
   }

   public boolean matchesLine(String line) {
      Matcher matcher = PATTERN_VARIABLE.matcher(line);
      if (!matcher.matches()) {
         return false;
      } else {
         String s = matcher.group(1);
         return s.matches(this.getName());
      }
   }

   public static bou parseOption(String line, String path) {
      Matcher matcher = PATTERN_VARIABLE.matcher(line);
      if (!matcher.matches()) {
         return null;
      } else {
         String s = matcher.group(1);
         String s1 = matcher.group(2);
         String s2 = matcher.group(3);
         String s3 = bqP.getSegment(s2, "[", "]");
         if (s3 != null && s3.length() > 0) {
            s2 = s2.replace(s3, "").trim();
         }

         String[] astring = parseValues(s1, s3);
         if (s != null && s.length() > 0) {
            path = bqP.removePrefix(path, "/shaders/");
            bou shaderoption = new boB(s, s2, s1, astring, path);
            return shaderoption;
         } else {
            return null;
         }
      }
   }

   public static String[] parseValues(String value, String valuesStr) {
      String[] astring = new String[]{value};
      if (valuesStr == null) {
         return astring;
      } else {
         valuesStr = valuesStr.trim();
         valuesStr = bqP.removePrefix(valuesStr, "[");
         valuesStr = bqP.removeSuffix(valuesStr, "]");
         valuesStr = valuesStr.trim();
         if (valuesStr.length() <= 0) {
            return astring;
         } else {
            String[] astring1 = XH.tokenize(valuesStr, " ");
            if (astring1.length <= 0) {
               return astring;
            } else {
               if (!Arrays.asList(astring1).contains(value)) {
                  astring1 = (String[])((String[])XH.addObjectToArray(astring1, value, 0));
               }

               return astring1;
            }
         }
      }
   }
}
