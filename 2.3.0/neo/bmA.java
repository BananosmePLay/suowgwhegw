package neo;

import java.awt.Rectangle;

public class bmA implements bmz {
   public bmA() {
   }

   public Rectangle getTooltipBounds(lg guiScreen, int x, int y) {
      int i = guiScreen.width - 450;
      int j = 35;
      if (i < 10) {
         i = 10;
      }

      if (y <= j + 94) {
         j += 100;
      }

      int k = i + 150 + 150;
      int l = j + 84 + 10;
      return new Rectangle(i, j, k - i, l - j);
   }

   public boolean isRenderBorder() {
      return true;
   }

   public String[] getTooltipLines(jK btn, int width) {
      if (btn instanceof boO) {
         return bmB.getTooltipLines("of.options.shaders.DOWNLOAD");
      } else if (!(btn instanceof boQ)) {
         return null;
      } else {
         boQ guibuttonenumshaderoption = (boQ)btn;
         bog enumshaderoption = guibuttonenumshaderoption.getEnumShaderOption();
         String[] astring = this.getTooltipLines(enumshaderoption);
         return astring;
      }
   }

   private String[] getTooltipLines(bog option) {
      return bmB.getTooltipLines(option.getResourceKey());
   }
}
