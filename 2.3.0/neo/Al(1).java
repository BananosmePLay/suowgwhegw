package neo;

import net.minecraft.util.text.ITextComponent;

public class Al implements Ad {
   private final ITextComponent packDescription;
   private final int packFormat;

   public Al(ITextComponent packDescriptionIn, int packFormatIn) {
      this.packDescription = packDescriptionIn;
      this.packFormat = packFormatIn;
   }

   public ITextComponent getPackDescription() {
      return this.packDescription;
   }

   public int getPackFormat() {
      return this.packFormat;
   }
}
