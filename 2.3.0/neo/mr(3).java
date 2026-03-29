package neo;

import com.google.common.collect.Lists;
import java.util.List;
import javax.annotation.Nullable;

public class mr {
   private NT recipe;
   private final List<mq> ingredients = Lists.newArrayList();
   private float time;

   public mr() {
   }

   public void clear() {
      this.recipe = null;
      this.ingredients.clear();
      this.time = 0.0F;
   }

   public void addIngredient(NS p_194187_1_, int p_194187_2_, int p_194187_3_) {
      this.ingredients.add(new mq(this, p_194187_1_, p_194187_2_, p_194187_3_));
   }

   public mq get(int p_192681_1_) {
      return (mq)this.ingredients.get(p_192681_1_);
   }

   public int size() {
      return this.ingredients.size();
   }

   @Nullable
   public NT getRecipe() {
      return this.recipe;
   }

   public void setRecipe(NT p_192685_1_) {
      this.recipe = p_192685_1_;
   }

   public void render(nC p_194188_1_, int p_194188_2_, int p_194188_3_, boolean p_194188_4_, float p_194188_5_) {
      if (!lg.isCtrlKeyDown()) {
         this.time += p_194188_5_;
      }

      yz.enableGUIStandardItemLighting();
      yh.disableLighting();

      for(int i = 0; i < this.ingredients.size(); ++i) {
         mq ghostrecipe$ghostingredient = (mq)this.ingredients.get(i);
         int j = ghostrecipe$ghostingredient.getX() + p_194188_2_;
         int k = ghostrecipe$ghostingredient.getY() + p_194188_3_;
         if (i == 0 && p_194188_4_) {
            jI.drawRect(j - 4, k - 4, j + 20, k + 20, 822018048);
         } else {
            jI.drawRect(j, k, j + 16, k + 16, 822018048);
         }

         yh.disableLighting();
         Qy itemstack = ghostrecipe$ghostingredient.getItem();
         yK renderitem = p_194188_1_.getRenderItem();
         renderitem.renderItemAndEffectIntoGUI(nC.player, itemstack, j, k);
         yh.depthFunc(516);
         jI.drawRect(j, k, j + 16, k + 16, 822083583);
         yh.depthFunc(515);
         if (i == 0) {
            renderitem.renderItemOverlays(p_194188_1_.fontRenderer, itemstack, j, k);
         }

         yh.enableLighting();
      }

      yz.disableStandardItemLighting();
   }

   // $FF: synthetic method
   static float access$000(mr x0) {
      return x0.time;
   }
}
