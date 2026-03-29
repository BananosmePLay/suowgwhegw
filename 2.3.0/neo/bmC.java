package neo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class bmC extends bmB {
   public bmC() {
   }

   public String[] getTooltipLines(jK btn, int width) {
      if (!(btn instanceof boR)) {
         return null;
      } else {
         boR guibuttonshaderoption = (boR)btn;
         bou shaderoption = guibuttonshaderoption.getShaderOption();
         String[] astring = this.makeTooltipLines(shaderoption, width);
         return astring;
      }
   }

   private String[] makeTooltipLines(bou so, int width) {
      String s = so.getNameText();
      String s1 = XH.normalize(so.getDescriptionText()).trim();
      String[] astring = this.splitDescription(s1);
      Bj gamesettings = XH.getGameSettings();
      String s2 = null;
      if (!s.equals(so.getName()) && gamesettings.advancedItemTooltips) {
         s2 = "§8" + bmW.get("of.general.id") + ": " + so.getName();
      }

      String s3 = null;
      if (so.getPaths() != null && gamesettings.advancedItemTooltips) {
         s3 = "§8" + bmW.get("of.general.from") + ": " + XH.arrayToString((Object[])so.getPaths());
      }

      String s4 = null;
      if (so.getValueDefault() != null && gamesettings.advancedItemTooltips) {
         String s5 = so.isEnabled() ? so.getValueText(so.getValueDefault()) : bmW.get("of.general.ambiguous");
         s4 = "§8" + bmW.getDefault() + ": " + s5;
      }

      List<String> list = new ArrayList();
      list.add(s);
      list.addAll(Arrays.asList(astring));
      if (s2 != null) {
         list.add(s2);
      }

      if (s3 != null) {
         list.add(s3);
      }

      if (s4 != null) {
         list.add(s4);
      }

      String[] astring1 = this.makeTooltipLines(width, list);
      return astring1;
   }

   private String[] splitDescription(String desc) {
      if (desc.length() <= 0) {
         return new String[0];
      } else {
         desc = bqP.removePrefix(desc, "//");
         String[] astring = desc.split("\\. ");

         for(int i = 0; i < astring.length; ++i) {
            astring[i] = "- " + astring[i].trim();
            astring[i] = bqP.removeSuffix(astring[i], ".");
         }

         return astring;
      }
   }

   private String[] makeTooltipLines(int width, List<String> args) {
      jH fontrenderer = XH.getMinecraft().fontRenderer;
      List<String> list = new ArrayList();

      for(int i = 0; i < args.size(); ++i) {
         String s = (String)args.get(i);
         if (s != null && s.length() > 0) {
            Iterator var7 = fontrenderer.listFormattedStringToWidth(s, width).iterator();

            while(var7.hasNext()) {
               String s1 = (String)var7.next();
               list.add(s1);
            }
         }
      }

      String[] astring = (String[])((String[])list.toArray(new String[list.size()]));
      return astring;
   }
}
