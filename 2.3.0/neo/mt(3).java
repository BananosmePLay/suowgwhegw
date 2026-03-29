package neo;

import java.util.Iterator;
import java.util.List;

public class mt extends jN {
   private final EN category;
   private float animationTime;

   public mt(int p_i47588_1_, EN p_i47588_2_) {
      super(p_i47588_1_, 0, 0, 35, 27, false);
      this.category = p_i47588_2_;
      this.initTextureValues(153, 2, 35, 0, mu.RECIPE_BOOK);
   }

   public void startAnimation(nC p_193918_1_) {
      XK recipebook = nC.player.getRecipeBook();
      Iterator var3 = ((List)BP.RECIPES_BY_TAB.get(this.category)).iterator();

      while(var3.hasNext()) {
         mB recipelist = (mB)var3.next();
         Iterator iterator = recipelist.getRecipes(recipebook.isFilteringCraftable()).iterator();

         while(iterator.hasNext()) {
            NT irecipe = (NT)iterator.next();
            if (recipebook.isNew(irecipe)) {
               this.animationTime = 15.0F;
               return;
            }
         }
      }

   }

   public void drawButton(nC mc, int mouseX, int mouseY, float partialTicks) {
      if (this.visible) {
         if (this.animationTime > 0.0F) {
            float f = 1.0F + 0.1F * (float)Math.sin((double)(this.animationTime / 15.0F * 3.1415927F));
            yh.pushMatrix();
            yh.translate((float)(this.x + 8), (float)(this.y + 12), 0.0F);
            yh.scale(1.0F, f, 1.0F);
            yh.translate((float)(-(this.x + 8)), (float)(-(this.y + 12)), 0.0F);
         }

         this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
         mc.getTextureManager().bindTexture(this.resourceLocation);
         yh.disableDepth();
         int k = this.xTexStart;
         int i = this.yTexStart;
         if (this.stateTriggered) {
            k += this.xDiffTex;
         }

         if (this.hovered) {
            i += this.yDiffTex;
         }

         int j = this.x;
         if (this.stateTriggered) {
            j -= 2;
         }

         yh.color(1.0F, 1.0F, 1.0F, 1.0F);
         this.drawTexturedModalRect(j, this.y, k, i, this.width, this.height);
         yh.enableDepth();
         yz.enableGUIStandardItemLighting();
         yh.disableLighting();
         this.renderIcon(mc.getRenderItem());
         yh.enableLighting();
         yz.disableStandardItemLighting();
         if (this.animationTime > 0.0F) {
            yh.popMatrix();
            this.animationTime -= partialTicks;
         }
      }

   }

   private void renderIcon(yK p_193920_1_) {
      Qy itemstack = this.category.getIcon();
      if (this.category == EN.TOOLS) {
         p_193920_1_.renderItemAndEffectIntoGUI(itemstack, this.x + 3, this.y + 5);
         p_193920_1_.renderItemAndEffectIntoGUI(EN.COMBAT.getIcon(), this.x + 14, this.y + 5);
      } else if (this.category == EN.MISC) {
         p_193920_1_.renderItemAndEffectIntoGUI(itemstack, this.x + 3, this.y + 5);
         p_193920_1_.renderItemAndEffectIntoGUI(EN.FOOD.getIcon(), this.x + 14, this.y + 5);
      } else {
         p_193920_1_.renderItemAndEffectIntoGUI(itemstack, this.x + 9, this.y + 5);
      }

   }

   public EN getCategory() {
      return this.category;
   }

   public boolean updateVisibility() {
      List<mB> list = (List)BP.RECIPES_BY_TAB.get(this.category);
      this.visible = false;
      Iterator var2 = list.iterator();

      while(var2.hasNext()) {
         mB recipelist = (mB)var2.next();
         if (recipelist.isNotEmpty() && recipelist.containsValidRecipes()) {
            this.visible = true;
            break;
         }
      }

      return this.visible;
   }
}
