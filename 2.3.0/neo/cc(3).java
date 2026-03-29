package neo;

import net.minecraft.util.text.TextFormatting;

public enum cc {
   TASK("task", 0, TextFormatting.GREEN),
   CHALLENGE("challenge", 26, TextFormatting.DARK_PURPLE),
   GOAL("goal", 52, TextFormatting.GREEN);

   private final String name;
   private final int icon;
   private final TextFormatting format;

   private cc(String nameIn, int iconIn, TextFormatting formatIn) {
      this.name = nameIn;
      this.icon = iconIn;
      this.format = formatIn;
   }

   public String getName() {
      return this.name;
   }

   public int getIcon() {
      return this.icon;
   }

   public static cc byName(String nameIn) {
      cc[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         cc frametype = var1[var3];
         if (frametype.name.equals(nameIn)) {
            return frametype;
         }
      }

      throw new IllegalArgumentException("Unknown frame type '" + nameIn + "'");
   }

   public TextFormatting getFormat() {
      return this.format;
   }
}
