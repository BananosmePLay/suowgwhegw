package neo;

import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;

public interface IH {
   void setCustomer(ME var1);

   @Nullable
   ME getCustomer();

   @Nullable
   YX getRecipes(ME var1);

   void setRecipes(YX var1);

   void useRecipe(YW var1);

   void verifySellingItem(Qy var1);

   ITextComponent getDisplayName();

   bij getWorld();

   BlockPos getPos();
}
