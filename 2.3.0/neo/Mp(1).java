package neo;

import java.util.Locale;
import java.util.Random;
import net.minecraft.util.math.BlockPos;

class Mp implements Mk {
   public Mo value;
   public String destination;
   public bhF destinationType;

   public Mp(Mo p_i47340_1_, String p_i47340_2_, bhF p_i47340_3_) {
      this.value = p_i47340_1_;
      this.destination = p_i47340_2_;
      this.destinationType = p_i47340_3_;
   }

   public void addMerchantRecipe(IH merchant, YX recipeList, Random random) {
      int i = this.value.getPrice(random);
      bij world = merchant.getWorld();
      BlockPos blockpos = world.findNearestStructure(this.destination, merchant.getPos(), true);
      if (blockpos != null) {
         Qy itemstack = PT.setupNewMap(world, (double)blockpos.getX(), (double)blockpos.getZ(), (byte)2, true, true);
         PT.renderBiomePreviewMap(world, itemstack);
         bhE.addTargetDecoration(itemstack, blockpos, "+", this.destinationType);
         itemstack.setTranslatableName("filled_map." + this.destination.toLowerCase(Locale.ROOT));
         recipeList.add(new YW(new Qy(NK.EMERALD, i), new Qy(NK.COMPASS), itemstack));
      }

   }
}
