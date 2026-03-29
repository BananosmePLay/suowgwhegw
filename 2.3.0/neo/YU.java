package neo;

import net.minecraft.util.IStringSerializable;

public enum YU implements IStringSerializable {
   SAVE("save", 0),
   LOAD("load", 1),
   CORNER("corner", 2),
   DATA("data", 3);

   private static final YU[] MODES = new YU[values().length];
   private final String modeName;
   private final int modeId;

   private YU(String modeNameIn, int modeIdIn) {
      this.modeName = modeNameIn;
      this.modeId = modeIdIn;
   }

   public String getName() {
      return this.modeName;
   }

   public int getModeId() {
      return this.modeId;
   }

   public static YU getById(int id) {
      return id >= 0 && id < MODES.length ? MODES[id] : MODES[0];
   }

   // $FF: synthetic method
   static String access$200(YU x0) {
      return x0.modeName;
   }

   static {
      YU[] var0 = values();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         YU tileentitystructure$mode = var0[var2];
         MODES[tileentitystructure$mode.getModeId()] = tileentitystructure$mode;
      }

   }
}
