package neo;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class bbc implements bcn {
   private static final List<in> ALL_VALID_STATES = Lists.newArrayList();
   private static final int GRID_WIDTH;
   private static final int GRID_HEIGHT;
   protected static final in AIR;
   protected static final in BARRIER;
   private final bij world;

   public bbc(bij worldIn) {
      this.world = worldIn;
   }

   public bam generateChunk(int x, int z) {
      ban chunkprimer = new ban();

      int i1;
      for(int i = 0; i < 16; ++i) {
         for(int j = 0; j < 16; ++j) {
            int k = x * 16 + i;
            i1 = z * 16 + j;
            chunkprimer.setBlockState(i, 60, j, BARRIER);
            in iblockstate = getBlockStateFor(k, i1);
            if (iblockstate != null) {
               chunkprimer.setBlockState(i, 70, j, iblockstate);
            }
         }
      }

      bam chunk = new bam(this.world, chunkprimer, x, z);
      chunk.generateSkylightMap();
      Zi[] abiome = this.world.getBiomeProvider().getBiomes((Zi[])null, x * 16, z * 16, 16, 16);
      byte[] abyte = chunk.getBiomeArray();

      for(i1 = 0; i1 < abyte.length; ++i1) {
         abyte[i1] = (byte)Zi.getIdForBiome(abiome[i1]);
      }

      chunk.generateSkylightMap();
      return chunk;
   }

   public static in getBlockStateFor(int p_177461_0_, int p_177461_1_) {
      in iblockstate = AIR;
      if (p_177461_0_ > 0 && p_177461_1_ > 0 && p_177461_0_ % 2 != 0 && p_177461_1_ % 2 != 0) {
         p_177461_0_ /= 2;
         p_177461_1_ /= 2;
         if (p_177461_0_ <= GRID_WIDTH && p_177461_1_ <= GRID_HEIGHT) {
            int i = MathHelper.abs(p_177461_0_ * GRID_WIDTH + p_177461_1_);
            if (i < ALL_VALID_STATES.size()) {
               iblockstate = (in)ALL_VALID_STATES.get(i);
            }
         }
      }

      return iblockstate;
   }

   public void populate(int x, int z) {
   }

   public boolean generateStructures(bam chunkIn, int x, int z) {
      return false;
   }

   public List<Zg> getPossibleCreatures(IC creatureType, BlockPos pos) {
      Zi biome = this.world.getBiome(pos);
      return biome.getSpawnableList(creatureType);
   }

   @Nullable
   public BlockPos getNearestStructurePos(bij worldIn, String structureName, BlockPos position, boolean findUnexplored) {
      return null;
   }

   public boolean isInsideStructure(bij worldIn, String structureName, BlockPos pos) {
      return false;
   }

   public void recreateStructures(bam chunkIn, int x, int z) {
   }

   static {
      AIR = Nk.AIR.getDefaultState();
      BARRIER = Nk.BARRIER.getDefaultState();
      Iterator var0 = co.REGISTRY.iterator();

      while(var0.hasNext()) {
         co block = (co)var0.next();
         ALL_VALID_STATES.addAll(block.getBlockState().getValidStates());
      }

      GRID_WIDTH = MathHelper.ceil(MathHelper.sqrt((float)ALL_VALID_STATES.size()));
      GRID_HEIGHT = MathHelper.ceil((float)ALL_VALID_STATES.size() / (float)GRID_WIDTH);
   }
}
