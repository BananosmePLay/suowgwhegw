package neo;

import com.google.common.collect.Lists;
import java.util.List;

public class kt extends jI {
   protected int width = 200;
   protected int height = 20;
   public int x;
   public int y;
   private final List<String> labels;
   public int id;
   private boolean centered;
   public boolean visible = true;
   private boolean labelBgEnabled;
   private final int textColor;
   private int backColor;
   private int ulColor;
   private int brColor;
   private final jH fontRenderer;
   private int border;

   public kt(jH fontRendererObj, int labelId, int xIn, int yIn, int widthIn, int heightIn, int colorIn) {
      this.fontRenderer = fontRendererObj;
      this.id = labelId;
      this.x = xIn;
      this.y = yIn;
      this.width = widthIn;
      this.height = heightIn;
      this.labels = Lists.newArrayList();
      this.centered = false;
      this.labelBgEnabled = false;
      this.textColor = colorIn;
      this.backColor = -1;
      this.ulColor = -1;
      this.brColor = -1;
      this.border = 0;
   }

   public void addLine(String p_175202_1_) {
      this.labels.add(Ax.format(p_175202_1_));
   }

   public kt setCentered() {
      this.centered = true;
      return this;
   }

   public void drawLabel(nC mc, int mouseX, int mouseY) {
      if (this.visible) {
         yh.enableBlend();
         yh.tryBlendFuncSeparate(ya.SRC_ALPHA, xR.ONE_MINUS_SRC_ALPHA, ya.ONE, xR.ZERO);
         this.drawLabelBackground(mc, mouseX, mouseY);
         int i = this.y + this.height / 2 + this.border / 2;
         int j = i - this.labels.size() * 10 / 2;

         for(int k = 0; k < this.labels.size(); ++k) {
            if (this.centered) {
               this.drawCenteredString(this.fontRenderer, (String)this.labels.get(k), this.x + this.width / 2, j + k * 10, this.textColor);
            } else {
               this.drawString(this.fontRenderer, (String)this.labels.get(k), this.x, j + k * 10, this.textColor);
            }
         }
      }

   }

   protected void drawLabelBackground(nC mcIn, int mouseX, int mouseY) {
      if (this.labelBgEnabled) {
         int i = this.width + this.border * 2;
         int j = this.height + this.border * 2;
         int k = this.x - this.border;
         int l = this.y - this.border;
         drawRect(k, l, k + i, l + j, this.backColor);
         this.drawHorizontalLine(k, k + i, l, this.ulColor);
         this.drawHorizontalLine(k, k + i, l + j, this.brColor);
         this.drawVerticalLine(k, l, l + j, this.ulColor);
         this.drawVerticalLine(k + i, l, l + j, this.brColor);
      }

   }
}
