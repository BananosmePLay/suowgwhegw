package neo;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerRepair;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class cq implements bga {
   private final bij world;
   private final BlockPos position;

   public cq(bij worldIn, BlockPos pos) {
      this.world = worldIn;
      this.position = pos;
   }

   public String getName() {
      return "anvil";
   }

   public boolean hasCustomName() {
      return false;
   }

   public ITextComponent getDisplayName() {
      return new TextComponentTranslation(Nk.ANVIL.getTranslationKey() + ".name", new Object[0]);
   }

   public Container createContainer(MJ playerInventory, ME playerIn) {
      return new ContainerRepair(playerInventory, this.world, this.position, playerIn);
   }

   public String getGuiID() {
      return "minecraft:anvil";
   }
}
