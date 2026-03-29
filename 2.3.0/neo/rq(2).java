package neo;

public class rq extends qW {
   protected rq(bij worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
      super(worldIn, xCoordIn, yCoordIn, zCoordIn);
      this.particleGravity = 0.04F;
      this.nextTextureIndexX();
      if (ySpeedIn == 0.0 && (xSpeedIn != 0.0 || zSpeedIn != 0.0)) {
         this.motionX = xSpeedIn;
         this.motionY = ySpeedIn + 0.1;
         this.motionZ = zSpeedIn;
      }

   }
}
