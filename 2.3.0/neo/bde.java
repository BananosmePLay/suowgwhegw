package neo;

import java.util.Random;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;

public class bde extends beM {
   private boolean isSizeable;

   public bde() {
   }

   public bde(bij worldIn, bbd chunkProvider, Random random, int chunkX, int chunkZ) {
      super(chunkX, chunkZ);
      this.create(worldIn, chunkProvider, random, chunkX, chunkZ);
   }

   private void create(bij worldIn, bbd chunkProvider, Random rnd, int chunkX, int chunkZ) {
      Random random = new Random((long)(chunkX + chunkZ * 10387313));
      Rotation rotation = Rotation.values()[random.nextInt(Rotation.values().length)];
      int i = bdf.access$000(chunkX, chunkZ, chunkProvider);
      if (i < 60) {
         this.isSizeable = false;
      } else {
         BlockPos blockpos = new BlockPos(chunkX * 16 + 8, i, chunkZ * 16 + 8);
         bdK.startHouseTower(worldIn.getSaveHandler().getStructureTemplateManager(), blockpos, rotation, this.components, rnd);
         this.updateBoundingBox();
         this.isSizeable = true;
      }

   }

   public boolean isSizeableStructure() {
      return this.isSizeable;
   }
}
