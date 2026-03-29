package neo;

import com.google.common.collect.Sets;
import java.util.Set;

public class OU extends QB {
   private static final Set<co> EFFECTIVE_ON;
   private static final float[] ATTACK_DAMAGES;
   private static final float[] ATTACK_SPEEDS;

   protected OU(OK material) {
      super(material, EFFECTIVE_ON);
      this.attackDamage = ATTACK_DAMAGES[material.ordinal()];
      this.attackSpeed = ATTACK_SPEEDS[material.ordinal()];
   }

   public float getDestroySpeed(Qy stack, in state) {
      hM material = state.getMaterial();
      return material != hM.WOOD && material != hM.PLANTS && material != hM.VINE ? super.getDestroySpeed(stack, state) : this.efficiency;
   }

   static {
      EFFECTIVE_ON = Sets.newHashSet(new co[]{Nk.PLANKS, Nk.BOOKSHELF, Nk.LOG, Nk.LOG2, Nk.CHEST, Nk.PUMPKIN, Nk.LIT_PUMPKIN, Nk.MELON_BLOCK, Nk.LADDER, Nk.WOODEN_BUTTON, Nk.WOODEN_PRESSURE_PLATE});
      ATTACK_DAMAGES = new float[]{6.0F, 8.0F, 8.0F, 8.0F, 6.0F};
      ATTACK_SPEEDS = new float[]{-3.2F, -3.2F, -3.1F, -3.0F, -3.0F};
   }
}
