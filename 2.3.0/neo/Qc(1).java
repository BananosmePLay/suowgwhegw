package neo;

import com.google.common.collect.Sets;
import java.util.Set;

public class Qc extends QB {
   private static final Set<co> EFFECTIVE_ON;

   protected Qc(OK material) {
      super(1.0F, -2.8F, material, EFFECTIVE_ON);
   }

   public boolean canHarvestBlock(in blockIn) {
      co block = blockIn.getBlock();
      if (block == Nk.OBSIDIAN) {
         return this.toolMaterial.getHarvestLevel() == 3;
      } else if (block != Nk.DIAMOND_BLOCK && block != Nk.DIAMOND_ORE) {
         if (block != Nk.EMERALD_ORE && block != Nk.EMERALD_BLOCK) {
            if (block != Nk.GOLD_BLOCK && block != Nk.GOLD_ORE) {
               if (block != Nk.IRON_BLOCK && block != Nk.IRON_ORE) {
                  if (block != Nk.LAPIS_BLOCK && block != Nk.LAPIS_ORE) {
                     if (block != Nk.REDSTONE_ORE && block != Nk.LIT_REDSTONE_ORE) {
                        hM material = blockIn.getMaterial();
                        if (material == hM.ROCK) {
                           return true;
                        } else if (material == hM.IRON) {
                           return true;
                        } else {
                           return material == hM.ANVIL;
                        }
                     } else {
                        return this.toolMaterial.getHarvestLevel() >= 2;
                     }
                  } else {
                     return this.toolMaterial.getHarvestLevel() >= 1;
                  }
               } else {
                  return this.toolMaterial.getHarvestLevel() >= 1;
               }
            } else {
               return this.toolMaterial.getHarvestLevel() >= 2;
            }
         } else {
            return this.toolMaterial.getHarvestLevel() >= 2;
         }
      } else {
         return this.toolMaterial.getHarvestLevel() >= 2;
      }
   }

   public float getDestroySpeed(Qy stack, in state) {
      hM material = state.getMaterial();
      return material != hM.IRON && material != hM.ANVIL && material != hM.ROCK ? super.getDestroySpeed(stack, state) : this.efficiency;
   }

   static {
      EFFECTIVE_ON = Sets.newHashSet(new co[]{Nk.ACTIVATOR_RAIL, Nk.COAL_ORE, Nk.COBBLESTONE, Nk.DETECTOR_RAIL, Nk.DIAMOND_BLOCK, Nk.DIAMOND_ORE, Nk.DOUBLE_STONE_SLAB, Nk.GOLDEN_RAIL, Nk.GOLD_BLOCK, Nk.GOLD_ORE, Nk.ICE, Nk.IRON_BLOCK, Nk.IRON_ORE, Nk.LAPIS_BLOCK, Nk.LAPIS_ORE, Nk.LIT_REDSTONE_ORE, Nk.MOSSY_COBBLESTONE, Nk.NETHERRACK, Nk.PACKED_ICE, Nk.RAIL, Nk.REDSTONE_ORE, Nk.SANDSTONE, Nk.RED_SANDSTONE, Nk.STONE, Nk.STONE_SLAB, Nk.STONE_BUTTON, Nk.STONE_PRESSURE_PLATE});
   }
}
