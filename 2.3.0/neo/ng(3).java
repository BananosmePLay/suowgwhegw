package neo;

import com.google.common.collect.Lists;
import java.util.List;

public class ng implements ne {
   private final List<Qy> recipesOutputs = Lists.newArrayList();
   private long firstDrawTime;
   private boolean hasNewOutputs;

   public ng(Qy p_i47489_1_) {
      this.recipesOutputs.add(p_i47489_1_);
   }

   public nd draw(nc toastGui, long delta) {
      if (this.hasNewOutputs) {
         this.firstDrawTime = delta;
         this.hasNewOutputs = false;
      }

      if (this.recipesOutputs.isEmpty()) {
         return nd.HIDE;
      } else {
         toastGui.getMinecraft().getTextureManager().bindTexture(TEXTURE_TOASTS);
         yh.color(1.0F, 1.0F, 1.0F);
         toastGui.drawTexturedModalRect(0, 0, 0, 32, 160, 32);
         toastGui.getMinecraft().fontRenderer.drawString(Ax.format("recipe.toast.title"), 30, 7, -11534256);
         toastGui.getMinecraft().fontRenderer.drawString(Ax.format("recipe.toast.description"), 30, 18, -16777216);
         yz.enableGUIStandardItemLighting();
         toastGui.getMinecraft().getRenderItem().renderItemAndEffectIntoGUI((Iw)null, (Qy)this.recipesOutputs.get((int)(delta / (5000L / (long)this.recipesOutputs.size()) % (long)this.recipesOutputs.size())), 8, 8);
         return delta - this.firstDrawTime >= 5000L ? nd.HIDE : nd.SHOW;
      }
   }

   public void addRecipeOutput(Qy output) {
      if (this.recipesOutputs.add(output)) {
         this.hasNewOutputs = true;
      }

   }

   public static void addOrUpdate(nc p_193665_0_, NT p_193665_1_) {
      ng recipetoast = (ng)p_193665_0_.getToast(ng.class, NO_TOKEN);
      if (recipetoast == null) {
         p_193665_0_.add(new ng(p_193665_1_.getRecipeOutput()));
      } else {
         recipetoast.addRecipeOutput(p_193665_1_.getRecipeOutput());
      }

   }
}
