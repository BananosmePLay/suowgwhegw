package neo;

import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;

public class JX extends Kl {
   public JX(bij worldIn) {
      super(worldIn);
      this.setSize(this.width * 6.0F, this.height * 6.0F);
   }

   public static void registerFixesGiantZombie(DataFixer fixer) {
      Iu.registerFixesMob(fixer, JX.class);
   }

   public float getEyeHeight() {
      return 10.440001F;
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(Ni.MAX_HEALTH).setBaseValue(100.0);
      this.getEntityAttribute(Ni.MOVEMENT_SPEED).setBaseValue(0.5);
      this.getEntityAttribute(Ni.ATTACK_DAMAGE).setBaseValue(50.0);
   }

   public float getBlockPathWeight(BlockPos pos) {
      return this.world.getLightBrightness(pos) - 0.5F;
   }

   @Nullable
   protected ResourceLocation getLootTable() {
      return bhq.ENTITIES_GIANT;
   }
}
