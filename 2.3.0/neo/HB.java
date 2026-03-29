package neo;

import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public interface HB {
   boolean getIsStationary();

   void doClientRenderEffects();

   void doLocalUpdate();

   void onCrystalDestroyed(IS var1, BlockPos var2, DamageSource var3, ME var4);

   void initPhase();

   void removeAreaEffect();

   float getMaxRiseOrFall();

   float getYawFactor();

   HK<? extends HB> getType();

   @Nullable
   Vec3d getTargetLocation();

   float getAdjustedDamage(Lr var1, DamageSource var2, float var3);
}
