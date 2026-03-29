package neo;

import net.minecraft.inventory.Container;
import net.minecraft.util.text.ITextComponent;

public class rD implements bga {
   private final String guiID;
   private final ITextComponent displayName;

   public rD(String guiIdIn, ITextComponent displayNameIn) {
      this.guiID = guiIdIn;
      this.displayName = displayNameIn;
   }

   public Container createContainer(MJ playerInventory, ME playerIn) {
      throw new UnsupportedOperationException();
   }

   public String getName() {
      return this.displayName.getUnformattedText();
   }

   public boolean hasCustomName() {
      return true;
   }

   public String getGuiID() {
      return this.guiID;
   }

   public ITextComponent getDisplayName() {
      return this.displayName;
   }
}
