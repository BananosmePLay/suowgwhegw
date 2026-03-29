package neo;

import java.util.Iterator;
import java.util.List;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class ms extends jK {
   private static final ResourceLocation RECIPE_BOOK = new ResourceLocation("textures/gui/recipe_book.png");
   private XK book;
   private mB list;
   private float time;
   private float animationTime;
   private int currentIndex;

   public ms() {
      super(0, 0, 0, 25, 25, "");
   }

   public void init(mB p_193928_1_, mA p_193928_2_, XK p_193928_3_) {
      this.list = p_193928_1_;
      this.book = p_193928_3_;
      List<NT> list = p_193928_1_.getRecipes(p_193928_3_.isFilteringCraftable());
      Iterator var5 = list.iterator();

      while(var5.hasNext()) {
         NT irecipe = (NT)var5.next();
         if (p_193928_3_.isNew(irecipe)) {
            p_193928_2_.recipesShown(list);
            this.animationTime = 15.0F;
            break;
         }
      }

   }

   public mB getList() {
      return this.list;
   }

   public void setPosition(int p_191770_1_, int p_191770_2_) {
      this.x = p_191770_1_;
      this.y = p_191770_2_;
   }

   public void drawButton(nC mc, int mouseX, int mouseY, float partialTicks) {
      if (this.visible) {
         if (!lg.isCtrlKeyDown()) {
            this.time += partialTicks;
         }

         this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
         yz.enableGUIStandardItemLighting();
         mc.getTextureManager().bindTexture(RECIPE_BOOK);
         yh.disableLighting();
         int i = 29;
         if (!this.list.containsCraftableRecipes()) {
            i += 25;
         }

         int j = 206;
         if (this.list.getRecipes(this.book.isFilteringCraftable()).size() > 1) {
            j += 25;
         }

         boolean flag = this.animationTime > 0.0F;
         if (flag) {
            float f = 1.0F + 0.1F * (float)Math.sin((double)(this.animationTime / 15.0F * 3.1415927F));
            yh.pushMatrix();
            yh.translate((float)(this.x + 8), (float)(this.y + 12), 0.0F);
            yh.scale(f, f, 1.0F);
            yh.translate((float)(-(this.x + 8)), (float)(-(this.y + 12)), 0.0F);
            this.animationTime -= partialTicks;
         }

         this.drawTexturedModalRect(this.x, this.y, i, j, this.width, this.height);
         List<NT> list = this.getOrderedRecipes();
         this.currentIndex = MathHelper.floor(this.time / 30.0F) % list.size();
         Qy itemstack = ((NT)list.get(this.currentIndex)).getRecipeOutput();
         int k = 4;
         if (this.list.hasSingleResultItem() && this.getOrderedRecipes().size() > 1) {
            mc.getRenderItem().renderItemAndEffectIntoGUI(itemstack, this.x + k + 1, this.y + k + 1);
            --k;
         }

         mc.getRenderItem().renderItemAndEffectIntoGUI(itemstack, this.x + k, this.y + k);
         if (flag) {
            yh.popMatrix();
         }

         yh.enableLighting();
         yz.disableStandardItemLighting();
      }

   }

   private List<NT> getOrderedRecipes() {
      List<NT> list = this.list.getDisplayRecipes(true);
      if (!this.book.isFilteringCraftable()) {
         list.addAll(this.list.getDisplayRecipes(false));
      }

      return list;
   }

   public boolean isOnlyOption() {
      return this.getOrderedRecipes().size() == 1;
   }

   public NT getRecipe() {
      List<NT> list = this.getOrderedRecipes();
      return (NT)list.get(this.currentIndex);
   }

   public List<String> getToolTipText(lg p_191772_1_) {
      Qy itemstack = ((NT)this.getOrderedRecipes().get(this.currentIndex)).getRecipeOutput();
      List<String> list = p_191772_1_.getItemToolTip(itemstack);
      if (this.list.getRecipes(this.book.isFilteringCraftable()).size() > 1) {
         list.add(Ax.format("gui.recipebook.moreRecipes"));
      }

      return list;
   }

   public int getButtonWidth() {
      return 25;
   }
}
