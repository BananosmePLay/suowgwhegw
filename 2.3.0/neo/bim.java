package neo;

import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class bim extends bil {
   private baN dragonFightManager;

   public bim() {
   }

   public void init() {
      this.biomeProvider = new ZM(Nj.SKY);
      QQ nbttagcompound = this.world.getWorldInfo().getDimensionData(baM.THE_END);
      this.dragonFightManager = this.world instanceof bis ? new baN((bis)this.world, nbttagcompound.getCompoundTag("DragonFight")) : null;
   }

   public bcn createChunkGenerator() {
      return new bbd(this.world, this.world.getWorldInfo().isMapFeaturesEnabled(), this.world.getSeed(), this.getSpawnCoordinate());
   }

   public float calculateCelestialAngle(long worldTime, float partialTicks) {
      return 0.0F;
   }

   @Nullable
   public float[] calcSunriseSunsetColors(float celestialAngle, float partialTicks) {
      return null;
   }

   public Vec3d getFogColor(float p_76562_1_, float p_76562_2_) {
      int i = 10518688;
      float f = MathHelper.cos(p_76562_1_ * 6.2831855F) * 2.0F + 0.5F;
      f = MathHelper.clamp(f, 0.0F, 1.0F);
      float f1 = 0.627451F;
      float f2 = 0.5019608F;
      float f3 = 0.627451F;
      f1 *= f * 0.0F + 0.15F;
      f2 *= f * 0.0F + 0.15F;
      f3 *= f * 0.0F + 0.15F;
      return new Vec3d((double)f1, (double)f2, (double)f3);
   }

   public boolean isSkyColored() {
      return false;
   }

   public boolean canRespawnHere() {
      return false;
   }

   public boolean isSurfaceWorld() {
      return false;
   }

   public float getCloudHeight() {
      return 8.0F;
   }

   public boolean canCoordinateBeSpawn(int x, int z) {
      return this.world.getGroundAboveSeaLevel(new BlockPos(x, 0, z)).getMaterial().blocksMovement();
   }

   public BlockPos getSpawnCoordinate() {
      return new BlockPos(100, 50, 0);
   }

   public int getAverageGroundLevel() {
      return 50;
   }

   public boolean doesXZShowFog(int x, int z) {
      return false;
   }

   public baM getDimensionType() {
      return baM.THE_END;
   }

   public void onWorldSave() {
      QQ nbttagcompound = new QQ();
      if (this.dragonFightManager != null) {
         nbttagcompound.setTag("DragonFight", this.dragonFightManager.getCompound());
      }

      this.world.getWorldInfo().setDimensionData(baM.THE_END, nbttagcompound);
   }

   public void onWorldUpdateEntities() {
      if (this.dragonFightManager != null) {
         this.dragonFightManager.tick();
      }

   }

   @Nullable
   public baN getDragonFightManager() {
      return this.dragonFightManager;
   }
}
