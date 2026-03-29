package neo;

import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;

public class IV extends Nd {
   public IV(bij worldIn) {
      super(worldIn);
   }

   public IV(bij worldIn, Iw throwerIn) {
      super(worldIn, throwerIn);
   }

   public IV(bij worldIn, double x, double y, double z) {
      super(worldIn, x, y, z);
   }

   public static void registerFixesExpBottle(DataFixer fixer) {
      Nd.registerFixesThrowable(fixer, "ThrowableExpBottle");
   }

   protected float getGravityVelocity() {
      return 0.07F;
   }

   protected void onImpact(RayTraceResult result) {
      if (!this.world.isRemote) {
         this.world.playEvent(2002, new BlockPos(this), Wg.getPotionColor(NN.WATER));
         int i = 3 + this.world.rand.nextInt(5) + this.world.rand.nextInt(5);

         while(i > 0) {
            int j = Js.getXPSplit(i);
            i -= j;
            this.world.spawnEntity(new Js(this.world, this.posX, this.posY, this.posZ, j));
         }

         this.setDead();
      }

   }
}
