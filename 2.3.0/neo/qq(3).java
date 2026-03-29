package neo;

import net.minecraft.util.EnumParticleTypes;

public class qq extends pM {
   private int timeSinceStart;
   private final int maximumTime = 8;

   protected qq(bij worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double p_i1214_8_, double p_i1214_10_, double p_i1214_12_) {
      super(worldIn, xCoordIn, yCoordIn, zCoordIn, 0.0, 0.0, 0.0);
   }

   public void renderParticle(tN buffer, Ig entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
   }

   public void onUpdate() {
      for(int i = 0; i < 6; ++i) {
         double d0 = this.posX + (this.rand.nextDouble() - this.rand.nextDouble()) * 4.0;
         double d1 = this.posY + (this.rand.nextDouble() - this.rand.nextDouble()) * 4.0;
         double d2 = this.posZ + (this.rand.nextDouble() - this.rand.nextDouble()) * 4.0;
         bij var10000 = this.world;
         EnumParticleTypes var10001 = EnumParticleTypes.EXPLOSION_LARGE;
         float var10005 = (float)this.timeSinceStart;
         this.getClass();
         var10000.spawnParticle(var10001, d0, d1, d2, (double)(var10005 / 8.0F), 0.0, 0.0);
      }

      ++this.timeSinceStart;
      int var8 = this.timeSinceStart;
      this.getClass();
      if (var8 == 8) {
         this.setExpired();
      }

   }

   public int getFXLayer() {
      return 1;
   }
}
