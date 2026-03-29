package neo;

import net.minecraft.util.math.BlockPos;

public interface bmR {
   int getId();

   BlockPos getSpawnPosition();

   Zi getSpawnBiome();

   String getName();

   int getHealth();

   int getMaxHealth();
}
