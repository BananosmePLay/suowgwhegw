package neo;

import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;

public class Yu extends Yt {
   public Yu() {
   }

   public static void registerFixesDropper(DataFixer fixer) {
      fixer.registerWalker(FixTypes.BLOCK_ENTITY, new ItemStackDataLists(Yu.class, new String[]{"Items"}));
   }

   public String getName() {
      return this.hasCustomName() ? this.customName : "container.dropper";
   }

   public String getGuiID() {
      return "minecraft:dropper";
   }
}
