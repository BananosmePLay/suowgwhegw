package neo;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class hE implements bga {
   private final bij world;
   private final BlockPos position;

   public hE(bij worldIn, BlockPos pos) {
      this.world = worldIn;
      this.position = pos;
   }

   public String getName() {
      return "crafting_table";
   }

   public boolean hasCustomName() {
      return false;
   }

   public ITextComponent getDisplayName() {
      return new TextComponentTranslation(Nk.CRAFTING_TABLE.getTranslationKey() + ".name", new Object[0]);
   }

   public Container createContainer(MJ playerInventory, ME playerIn) {
      return new ContainerWorkbench(playerInventory, this.world, this.position);
   }

   public String getGuiID() {
      return "minecraft:crafting_table";
   }
}
