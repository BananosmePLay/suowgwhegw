package neo;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class mw extends jI {
   private static final ResourceLocation RECIPE_BOOK_TEXTURE = new ResourceLocation("textures/gui/recipe_book.png");
   private final List<mv> buttonList = Lists.newArrayList();
   private boolean visible;
   private int x;
   private int y;
   private nC mc;
   private mB recipeList;
   private NT lastRecipeClicked;
   private float time;

   public mw() {
   }

   public void init(nC mcIn, mB recipeListIn, int p_191845_3_, int p_191845_4_, int p_191845_5_, int p_191845_6_, float p_191845_7_, XK p_191845_8_) {
      this.mc = mcIn;
      this.recipeList = recipeListIn;
      boolean flag = p_191845_8_.isFilteringCraftable();
      List<NT> list = recipeListIn.getDisplayRecipes(true);
      List<NT> list1 = flag ? Collections.emptyList() : recipeListIn.getDisplayRecipes(false);
      int i = list.size();
      int j = i + list1.size();
      int k = j <= 16 ? 4 : 5;
      int l = (int)Math.ceil((double)((float)j / (float)k));
      this.x = p_191845_3_;
      this.y = p_191845_4_;
      int i1 = true;
      float f = (float)(this.x + Math.min(j, k) * 25);
      float f1 = (float)(p_191845_5_ + 50);
      if (f > f1) {
         this.x = (int)((float)this.x - p_191845_7_ * (float)((int)((f - f1) / p_191845_7_)));
      }

      float f2 = (float)(this.y + l * 25);
      float f3 = (float)(p_191845_6_ + 50);
      if (f2 > f3) {
         this.y = (int)((float)this.y - p_191845_7_ * (float)MathHelper.ceil((f2 - f3) / p_191845_7_));
      }

      float f4 = (float)this.y;
      float f5 = (float)(p_191845_6_ - 100);
      if (f4 < f5) {
         this.y = (int)((float)this.y - p_191845_7_ * (float)MathHelper.ceil((f4 - f5) / p_191845_7_));
      }

      this.visible = true;
      this.buttonList.clear();

      for(int j1 = 0; j1 < j; ++j1) {
         boolean flag1 = j1 < i;
         this.buttonList.add(new mv(this, this.x + 4 + 25 * (j1 % k), this.y + 5 + 25 * (j1 / k), flag1 ? (NT)list.get(j1) : (NT)list1.get(j1 - i), flag1));
      }

      this.lastRecipeClicked = null;
   }

   public mB getRecipeList() {
      return this.recipeList;
   }

   public NT getLastRecipeClicked() {
      return this.lastRecipeClicked;
   }

   public boolean buttonClicked(int p_193968_1_, int p_193968_2_, int p_193968_3_) {
      if (p_193968_3_ != 0) {
         return false;
      } else {
         Iterator var4 = this.buttonList.iterator();

         mv guirecipeoverlay$button;
         do {
            if (!var4.hasNext()) {
               return false;
            }

            guirecipeoverlay$button = (mv)var4.next();
         } while(!guirecipeoverlay$button.mousePressed(this.mc, p_193968_1_, p_193968_2_));

         this.lastRecipeClicked = mv.access$000(guirecipeoverlay$button);
         return true;
      }
   }

   public void render(int p_191842_1_, int p_191842_2_, float p_191842_3_) {
      if (this.visible) {
         this.time += p_191842_3_;
         yz.enableGUIStandardItemLighting();
         yh.enableBlend();
         yh.color(1.0F, 1.0F, 1.0F, 1.0F);
         this.mc.getTextureManager().bindTexture(RECIPE_BOOK_TEXTURE);
         yh.pushMatrix();
         yh.translate(0.0F, 0.0F, 170.0F);
         int i = this.buttonList.size() <= 16 ? 4 : 5;
         int j = Math.min(this.buttonList.size(), i);
         int k = MathHelper.ceil((float)this.buttonList.size() / (float)i);
         int l = true;
         int i1 = true;
         int j1 = true;
         int k1 = true;
         this.nineInchSprite(j, k, 24, 4, 82, 208);
         yh.disableBlend();
         yz.disableStandardItemLighting();
         Iterator var11 = this.buttonList.iterator();

         while(var11.hasNext()) {
            mv guirecipeoverlay$button = (mv)var11.next();
            guirecipeoverlay$button.drawButton(this.mc, p_191842_1_, p_191842_2_, p_191842_3_);
         }

         yh.popMatrix();
      }

   }

   private void nineInchSprite(int p_191846_1_, int p_191846_2_, int p_191846_3_, int p_191846_4_, int p_191846_5_, int p_191846_6_) {
      this.drawTexturedModalRect(this.x, this.y, p_191846_5_, p_191846_6_, p_191846_4_, p_191846_4_);
      this.drawTexturedModalRect(this.x + p_191846_4_ * 2 + p_191846_1_ * p_191846_3_, this.y, p_191846_5_ + p_191846_3_ + p_191846_4_, p_191846_6_, p_191846_4_, p_191846_4_);
      this.drawTexturedModalRect(this.x, this.y + p_191846_4_ * 2 + p_191846_2_ * p_191846_3_, p_191846_5_, p_191846_6_ + p_191846_3_ + p_191846_4_, p_191846_4_, p_191846_4_);
      this.drawTexturedModalRect(this.x + p_191846_4_ * 2 + p_191846_1_ * p_191846_3_, this.y + p_191846_4_ * 2 + p_191846_2_ * p_191846_3_, p_191846_5_ + p_191846_3_ + p_191846_4_, p_191846_6_ + p_191846_3_ + p_191846_4_, p_191846_4_, p_191846_4_);

      for(int i = 0; i < p_191846_1_; ++i) {
         this.drawTexturedModalRect(this.x + p_191846_4_ + i * p_191846_3_, this.y, p_191846_5_ + p_191846_4_, p_191846_6_, p_191846_3_, p_191846_4_);
         this.drawTexturedModalRect(this.x + p_191846_4_ + (i + 1) * p_191846_3_, this.y, p_191846_5_ + p_191846_4_, p_191846_6_, p_191846_4_, p_191846_4_);

         for(int j = 0; j < p_191846_2_; ++j) {
            if (i == 0) {
               this.drawTexturedModalRect(this.x, this.y + p_191846_4_ + j * p_191846_3_, p_191846_5_, p_191846_6_ + p_191846_4_, p_191846_4_, p_191846_3_);
               this.drawTexturedModalRect(this.x, this.y + p_191846_4_ + (j + 1) * p_191846_3_, p_191846_5_, p_191846_6_ + p_191846_4_, p_191846_4_, p_191846_4_);
            }

            this.drawTexturedModalRect(this.x + p_191846_4_ + i * p_191846_3_, this.y + p_191846_4_ + j * p_191846_3_, p_191846_5_ + p_191846_4_, p_191846_6_ + p_191846_4_, p_191846_3_, p_191846_3_);
            this.drawTexturedModalRect(this.x + p_191846_4_ + (i + 1) * p_191846_3_, this.y + p_191846_4_ + j * p_191846_3_, p_191846_5_ + p_191846_4_, p_191846_6_ + p_191846_4_, p_191846_4_, p_191846_3_);
            this.drawTexturedModalRect(this.x + p_191846_4_ + i * p_191846_3_, this.y + p_191846_4_ + (j + 1) * p_191846_3_, p_191846_5_ + p_191846_4_, p_191846_6_ + p_191846_4_, p_191846_3_, p_191846_4_);
            this.drawTexturedModalRect(this.x + p_191846_4_ + (i + 1) * p_191846_3_ - 1, this.y + p_191846_4_ + (j + 1) * p_191846_3_ - 1, p_191846_5_ + p_191846_4_, p_191846_6_ + p_191846_4_, p_191846_4_ + 1, p_191846_4_ + 1);
            if (i == p_191846_1_ - 1) {
               this.drawTexturedModalRect(this.x + p_191846_4_ * 2 + p_191846_1_ * p_191846_3_, this.y + p_191846_4_ + j * p_191846_3_, p_191846_5_ + p_191846_3_ + p_191846_4_, p_191846_6_ + p_191846_4_, p_191846_4_, p_191846_3_);
               this.drawTexturedModalRect(this.x + p_191846_4_ * 2 + p_191846_1_ * p_191846_3_, this.y + p_191846_4_ + (j + 1) * p_191846_3_, p_191846_5_ + p_191846_3_ + p_191846_4_, p_191846_6_ + p_191846_4_, p_191846_4_, p_191846_4_);
            }
         }

         this.drawTexturedModalRect(this.x + p_191846_4_ + i * p_191846_3_, this.y + p_191846_4_ * 2 + p_191846_2_ * p_191846_3_, p_191846_5_ + p_191846_4_, p_191846_6_ + p_191846_3_ + p_191846_4_, p_191846_3_, p_191846_4_);
         this.drawTexturedModalRect(this.x + p_191846_4_ + (i + 1) * p_191846_3_, this.y + p_191846_4_ * 2 + p_191846_2_ * p_191846_3_, p_191846_5_ + p_191846_4_, p_191846_6_ + p_191846_3_ + p_191846_4_, p_191846_4_, p_191846_4_);
      }

   }

   public void setVisible(boolean p_192999_1_) {
      this.visible = p_192999_1_;
   }

   public boolean isVisible() {
      return this.visible;
   }

   // $FF: synthetic method
   static ResourceLocation access$100() {
      return RECIPE_BOOK_TEXTURE;
   }

   // $FF: synthetic method
   static float access$200(mw x0) {
      return x0.time;
   }
}
