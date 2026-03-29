package neo;

import net.minecraft.util.math.Vec3d;

public class bio extends bil {
   public bio() {
   }

   public void init() {
      this.biomeProvider = new ZM(Nj.HELL);
      this.doesWaterVaporize = true;
      this.nether = true;
   }

   public Vec3d getFogColor(float p_76562_1_, float p_76562_2_) {
      return new Vec3d(0.20000000298023224, 0.029999999329447746, 0.029999999329447746);
   }

   protected void generateLightBrightnessTable() {
      float f = 0.1F;

      for(int i = 0; i <= 15; ++i) {
         float f1 = 1.0F - (float)i / 15.0F;
         this.lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * 0.9F + 0.1F;
      }

   }

   public bcn createChunkGenerator() {
      return new bbf(this.world, this.world.getWorldInfo().isMapFeaturesEnabled(), this.world.getSeed());
   }

   public boolean isSurfaceWorld() {
      return false;
   }

   public boolean canCoordinateBeSpawn(int x, int z) {
      return false;
   }

   public float calculateCelestialAngle(long worldTime, float partialTicks) {
      return 0.5F;
   }

   public boolean canRespawnHere() {
      return false;
   }

   public boolean doesXZShowFog(int x, int z) {
      return true;
   }

   public bab createWorldBorder() {
      return new bab() {
         public double getCenterX() {
            return super.getCenterX() / 8.0;
         }

         public double getCenterZ() {
            return super.getCenterZ() / 8.0;
         }
      };
   }

   public baM getDimensionType() {
      return baM.NETHER;
   }
}
