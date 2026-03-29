package neo;

import java.util.List;
import java.util.Random;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;

public class bfY {
   public bfY() {
   }

   public static void registerWoodlandMansionPieces() {
      bdt.registerStructureComponent(bfR.class, "WMP");
   }

   public static void generateMansion(bfL p_191152_0_, BlockPos p_191152_1_, Rotation p_191152_2_, List<bfR> p_191152_3_, Random p_191152_4_) {
      bfQ woodlandmansionpieces$grid = new bfQ(p_191152_4_);
      bfT woodlandmansionpieces$placer = new bfT(p_191152_0_, p_191152_4_);
      woodlandmansionpieces$placer.createMansion(p_191152_1_, p_191152_2_, p_191152_3_, woodlandmansionpieces$grid);
   }
}
