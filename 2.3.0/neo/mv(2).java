package neo;

import java.util.Iterator;
import net.minecraft.util.math.MathHelper;

class mv extends jK {
   private final NT recipe;
   private final boolean isCraftable;
   // $FF: synthetic field
   final mw this$0;

   public mv(mw this$0, int p_i47594_2_, int p_i47594_3_, NT p_i47594_4_, boolean p_i47594_5_) {
      super(0, p_i47594_2_, p_i47594_3_, "");
      this.this$0 = this$0;
      this.width = 24;
      this.height = 24;
      this.recipe = p_i47594_4_;
      this.isCraftable = p_i47594_5_;
   }

   public void drawButton(nC mc, int mouseX, int mouseY, float partialTicks) {
      yz.enableGUIStandardItemLighting();
      yh.enableAlpha();
      mc.getTextureManager().bindTexture(mw.access$100());
      this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
      int i = 152;
      if (!this.isCraftable) {
         i += 26;
      }

      int j = 78;
      if (this.hovered) {
         j += 26;
      }

      this.drawTexturedModalRect(this.x, this.y, i, j, this.width, this.height);
      int k = 3;
      int l = 3;
      if (this.recipe instanceof Of) {
         Of shapedrecipes = (Of)this.recipe;
         k = shapedrecipes.getWidth();
         l = shapedrecipes.getHeight();
      }

      Iterator<NS> iterator = this.recipe.getIngredients().iterator();

      for(int i1 = 0; i1 < l; ++i1) {
         int j1 = 3 + i1 * 7;

         for(int k1 = 0; k1 < k; ++k1) {
            if (iterator.hasNext()) {
               Qy[] aitemstack = ((NS)iterator.next()).getMatchingStacks();
               if (aitemstack.length != 0) {
                  int l1 = 3 + k1 * 7;
                  yh.pushMatrix();
                  float f = 0.42F;
                  int i2 = (int)((float)(this.x + l1) / 0.42F - 3.0F);
                  int j2 = (int)((float)(this.y + j1) / 0.42F - 3.0F);
                  yh.scale(0.42F, 0.42F, 1.0F);
                  yh.enableLighting();
                  mc.getRenderItem().renderItemAndEffectIntoGUI(aitemstack[MathHelper.floor(mw.access$200(this.this$0) / 30.0F) % aitemstack.length], i2, j2);
                  yh.disableLighting();
                  yh.popMatrix();
               }
            }
         }
      }

      yh.disableAlpha();
      yz.disableStandardItemLighting();
   }

   // $FF: synthetic method
   static NT access$000(mv x0) {
      return x0.recipe;
   }
}
