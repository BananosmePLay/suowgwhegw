package neo;

import net.minecraft.util.text.TextFormatting;

public enum On {
   COMMON(TextFormatting.WHITE, "Common"),
   UNCOMMON(TextFormatting.YELLOW, "Uncommon"),
   RARE(TextFormatting.AQUA, "Rare"),
   EPIC(TextFormatting.LIGHT_PURPLE, "Epic");

   public final TextFormatting color;
   public final String rarityName;

   private On(TextFormatting color, String name) {
      this.color = color;
      this.rarityName = name;
   }
}
