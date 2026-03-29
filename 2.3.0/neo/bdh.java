package neo;

import java.util.Iterator;
import java.util.Map;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class bdh extends bdr {
   private double chance = 0.004;

   public bdh() {
   }

   public String getStructureName() {
      return "Mineshaft";
   }

   public bdh(Map<String, String> p_i2034_1_) {
      Iterator var2 = p_i2034_1_.entrySet().iterator();

      while(var2.hasNext()) {
         Map.Entry<String, String> entry = (Map.Entry)var2.next();
         if (((String)entry.getKey()).equals("chance")) {
            this.chance = MathHelper.getDouble((String)entry.getValue(), this.chance);
         }
      }

   }

   protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
      return this.rand.nextDouble() < this.chance && this.rand.nextInt(80) < Math.max(Math.abs(chunkX), Math.abs(chunkZ));
   }

   public BlockPos getNearestStructurePos(bij worldIn, BlockPos pos, boolean findUnexplored) {
      int i = true;
      int j = pos.getX() >> 4;
      int k = pos.getZ() >> 4;

      for(int l = 0; l <= 1000; ++l) {
         for(int i1 = -l; i1 <= l; ++i1) {
            boolean flag = i1 == -l || i1 == l;

            for(int j1 = -l; j1 <= l; ++j1) {
               boolean flag1 = j1 == -l || j1 == l;
               if (flag || flag1) {
                  int k1 = j + i1;
                  int l1 = k + j1;
                  this.rand.setSeed((long)(k1 ^ l1) ^ worldIn.getSeed());
                  this.rand.nextInt();
                  if (this.canSpawnStructureAtCoords(k1, l1) && (!findUnexplored || !worldIn.isChunkGeneratedAt(k1, l1))) {
                     return new BlockPos((k1 << 4) + 8, 64, (l1 << 4) + 8);
                  }
               }
            }
         }
      }

      return null;
   }

   protected beM getStructureStart(int chunkX, int chunkZ) {
      Zi biome = this.world.getBiome(new BlockPos((chunkX << 4) + 8, 64, (chunkZ << 4) + 8));
      bdg mapgenmineshaft$type = biome instanceof ZH ? bdg.MESA : bdg.NORMAL;
      return new bdS(this.world, this.rand, chunkX, chunkZ, mapgenmineshaft$type);
   }
}
