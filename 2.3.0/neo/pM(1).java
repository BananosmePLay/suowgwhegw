package neo;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class pM {
   private static final AxisAlignedBB EMPTY_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
   protected bij world;
   protected double prevPosX;
   protected double prevPosY;
   protected double prevPosZ;
   protected double posX;
   protected double posY;
   protected double posZ;
   protected double motionX;
   protected double motionY;
   protected double motionZ;
   private AxisAlignedBB boundingBox;
   protected boolean onGround;
   protected boolean canCollide;
   protected boolean isExpired;
   protected float width;
   protected float height;
   protected Random rand;
   protected int particleTextureIndexX;
   protected int particleTextureIndexY;
   protected float particleTextureJitterX;
   protected float particleTextureJitterY;
   protected int particleAge;
   protected int particleMaxAge;
   protected float particleScale;
   protected float particleGravity;
   protected float particleRed;
   protected float particleGreen;
   protected float particleBlue;
   protected float particleAlpha;
   protected zd particleTexture;
   protected float particleAngle;
   protected float prevParticleAngle;
   public static double interpPosX;
   public static double interpPosY;
   public static double interpPosZ;
   public static Vec3d cameraViewDir;

   protected pM(bij worldIn, double posXIn, double posYIn, double posZIn) {
      this.boundingBox = EMPTY_AABB;
      this.width = 0.6F;
      this.height = 1.8F;
      this.rand = new Random();
      this.particleAlpha = 1.0F;
      this.world = worldIn;
      this.setSize(0.2F, 0.2F);
      this.setPosition(posXIn, posYIn, posZIn);
      this.prevPosX = posXIn;
      this.prevPosY = posYIn;
      this.prevPosZ = posZIn;
      this.particleRed = 1.0F;
      this.particleGreen = 1.0F;
      this.particleBlue = 1.0F;
      this.particleTextureJitterX = this.rand.nextFloat() * 3.0F;
      this.particleTextureJitterY = this.rand.nextFloat() * 3.0F;
      this.particleScale = (this.rand.nextFloat() * 0.5F + 0.5F) * 2.0F;
      this.particleMaxAge = (int)(4.0F / (this.rand.nextFloat() * 0.9F + 0.1F));
      this.particleAge = 0;
      this.canCollide = true;
   }

   public pM(bij worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
      this(worldIn, xCoordIn, yCoordIn, zCoordIn);
      this.motionX = xSpeedIn + (Math.random() * 2.0 - 1.0) * 0.4000000059604645;
      this.motionY = ySpeedIn + (Math.random() * 2.0 - 1.0) * 0.4000000059604645;
      this.motionZ = zSpeedIn + (Math.random() * 2.0 - 1.0) * 0.4000000059604645;
      float f = (float)(Math.random() + Math.random() + 1.0) * 0.15F;
      float f1 = MathHelper.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
      this.motionX = this.motionX / (double)f1 * (double)f * 0.4000000059604645;
      this.motionY = this.motionY / (double)f1 * (double)f * 0.4000000059604645 + 0.10000000149011612;
      this.motionZ = this.motionZ / (double)f1 * (double)f * 0.4000000059604645;
   }

   public pM multiplyVelocity(float multiplier) {
      this.motionX *= (double)multiplier;
      this.motionY = (this.motionY - 0.10000000149011612) * (double)multiplier + 0.10000000149011612;
      this.motionZ *= (double)multiplier;
      return this;
   }

   public pM multipleParticleScaleBy(float scale) {
      this.setSize(0.2F * scale, 0.2F * scale);
      this.particleScale *= scale;
      return this;
   }

   public void setRBGColorF(float particleRedIn, float particleGreenIn, float particleBlueIn) {
      this.particleRed = particleRedIn;
      this.particleGreen = particleGreenIn;
      this.particleBlue = particleBlueIn;
   }

   public void setAlphaF(float alpha) {
      this.particleAlpha = alpha;
   }

   public boolean shouldDisableDepth() {
      return false;
   }

   public float getRedColorF() {
      return this.particleRed;
   }

   public float getGreenColorF() {
      return this.particleGreen;
   }

   public float getBlueColorF() {
      return this.particleBlue;
   }

   public void setMaxAge(int particleLifeTime) {
      this.particleMaxAge = particleLifeTime;
   }

   public void onUpdate() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      if (this.particleAge++ >= this.particleMaxAge) {
         this.setExpired();
      }

      this.motionY -= 0.04 * (double)this.particleGravity;
      this.move(this.motionX, this.motionY, this.motionZ);
      this.motionX *= 0.9800000190734863;
      this.motionY *= 0.9800000190734863;
      this.motionZ *= 0.9800000190734863;
      if (this.onGround) {
         this.motionX *= 0.699999988079071;
         this.motionZ *= 0.699999988079071;
      }

   }

   public void renderParticle(tN buffer, Ig entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
      float f = (float)this.particleTextureIndexX / 16.0F;
      float f1 = f + 0.0624375F;
      float f2 = (float)this.particleTextureIndexY / 16.0F;
      float f3 = f2 + 0.0624375F;
      float f4 = 0.1F * this.particleScale;
      if (this.particleTexture != null) {
         f = this.particleTexture.getMinU();
         f1 = this.particleTexture.getMaxU();
         f2 = this.particleTexture.getMinV();
         f3 = this.particleTexture.getMaxV();
      }

      float f5 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)partialTicks - interpPosX);
      float f6 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)partialTicks - interpPosY);
      float f7 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)partialTicks - interpPosZ);
      int i = this.getBrightnessForRender(partialTicks);
      int j = i >> 16 & '\uffff';
      int k = i & '\uffff';
      Vec3d[] avec3d = new Vec3d[]{new Vec3d((double)(-rotationX * f4 - rotationXY * f4), (double)(-rotationZ * f4), (double)(-rotationYZ * f4 - rotationXZ * f4)), new Vec3d((double)(-rotationX * f4 + rotationXY * f4), (double)(rotationZ * f4), (double)(-rotationYZ * f4 + rotationXZ * f4)), new Vec3d((double)(rotationX * f4 + rotationXY * f4), (double)(rotationZ * f4), (double)(rotationYZ * f4 + rotationXZ * f4)), new Vec3d((double)(rotationX * f4 - rotationXY * f4), (double)(-rotationZ * f4), (double)(rotationYZ * f4 - rotationXZ * f4))};
      if (this.particleAngle != 0.0F) {
         float f8 = this.particleAngle + (this.particleAngle - this.prevParticleAngle) * partialTicks;
         float f9 = MathHelper.cos(f8 * 0.5F);
         float f10 = MathHelper.sin(f8 * 0.5F) * (float)cameraViewDir.x;
         float f11 = MathHelper.sin(f8 * 0.5F) * (float)cameraViewDir.y;
         float f12 = MathHelper.sin(f8 * 0.5F) * (float)cameraViewDir.z;
         Vec3d vec3d = new Vec3d((double)f10, (double)f11, (double)f12);

         for(int l = 0; l < 4; ++l) {
            avec3d[l] = vec3d.scale(2.0 * avec3d[l].dotProduct(vec3d)).add(avec3d[l].scale((double)(f9 * f9) - vec3d.dotProduct(vec3d))).add(vec3d.crossProduct(avec3d[l]).scale((double)(2.0F * f9)));
         }
      }

      buffer.pos((double)f5 + avec3d[0].x, (double)f6 + avec3d[0].y, (double)f7 + avec3d[0].z).tex((double)f1, (double)f3).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
      buffer.pos((double)f5 + avec3d[1].x, (double)f6 + avec3d[1].y, (double)f7 + avec3d[1].z).tex((double)f1, (double)f2).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
      buffer.pos((double)f5 + avec3d[2].x, (double)f6 + avec3d[2].y, (double)f7 + avec3d[2].z).tex((double)f, (double)f2).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
      buffer.pos((double)f5 + avec3d[3].x, (double)f6 + avec3d[3].y, (double)f7 + avec3d[3].z).tex((double)f, (double)f3).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
   }

   public int getFXLayer() {
      return 0;
   }

   public void setParticleTexture(zd texture) {
      int i = this.getFXLayer();
      if (i == 1) {
         this.particleTexture = texture;
      } else {
         throw new RuntimeException("Invalid call to Particle.setTex, use coordinate methods");
      }
   }

   public void setParticleTextureIndex(int particleTextureIndex) {
      if (this.getFXLayer() != 0) {
         throw new RuntimeException("Invalid call to Particle.setMiscTex");
      } else {
         this.particleTextureIndexX = particleTextureIndex % 16;
         this.particleTextureIndexY = particleTextureIndex / 16;
      }
   }

   public void nextTextureIndexX() {
      ++this.particleTextureIndexX;
   }

   public String toString() {
      return this.getClass().getSimpleName() + ", Pos (" + this.posX + "," + this.posY + "," + this.posZ + "), RGBA (" + this.particleRed + "," + this.particleGreen + "," + this.particleBlue + "," + this.particleAlpha + "), Age " + this.particleAge;
   }

   public void setExpired() {
      this.isExpired = true;
   }

   protected void setSize(float particleWidth, float particleHeight) {
      if (particleWidth != this.width || particleHeight != this.height) {
         this.width = particleWidth;
         this.height = particleHeight;
         AxisAlignedBB axisalignedbb = this.getBoundingBox();
         this.setBoundingBox(new AxisAlignedBB(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ, axisalignedbb.minX + (double)this.width, axisalignedbb.minY + (double)this.height, axisalignedbb.minZ + (double)this.width));
      }

   }

   public void setPosition(double x, double y, double z) {
      this.posX = x;
      this.posY = y;
      this.posZ = z;
      float f = this.width / 2.0F;
      float f1 = this.height;
      this.setBoundingBox(new AxisAlignedBB(x - (double)f, y, z - (double)f, x + (double)f, y + (double)f1, z + (double)f));
   }

   public void move(double x, double y, double z) {
      double d0 = y;
      if (this.canCollide) {
         List<AxisAlignedBB> list = this.world.getCollisionBoxes((Ig)null, this.getBoundingBox().expand(x, y, z));

         Iterator var10;
         AxisAlignedBB axisalignedbb2;
         for(var10 = list.iterator(); var10.hasNext(); y = axisalignedbb2.calculateYOffset(this.getBoundingBox(), y)) {
            axisalignedbb2 = (AxisAlignedBB)var10.next();
         }

         this.setBoundingBox(this.getBoundingBox().offset(0.0, y, 0.0));

         for(var10 = list.iterator(); var10.hasNext(); x = axisalignedbb2.calculateXOffset(this.getBoundingBox(), x)) {
            axisalignedbb2 = (AxisAlignedBB)var10.next();
         }

         this.setBoundingBox(this.getBoundingBox().offset(x, 0.0, 0.0));

         for(var10 = list.iterator(); var10.hasNext(); z = axisalignedbb2.calculateZOffset(this.getBoundingBox(), z)) {
            axisalignedbb2 = (AxisAlignedBB)var10.next();
         }

         this.setBoundingBox(this.getBoundingBox().offset(0.0, 0.0, z));
      } else {
         this.setBoundingBox(this.getBoundingBox().offset(x, y, z));
      }

      this.resetPositionToBB();
      this.onGround = y != y && d0 < 0.0;
      if (x != x) {
         this.motionX = 0.0;
      }

      if (z != z) {
         this.motionZ = 0.0;
      }

   }

   protected void resetPositionToBB() {
      AxisAlignedBB axisalignedbb = this.getBoundingBox();
      this.posX = (axisalignedbb.minX + axisalignedbb.maxX) / 2.0;
      this.posY = axisalignedbb.minY;
      this.posZ = (axisalignedbb.minZ + axisalignedbb.maxZ) / 2.0;
   }

   public int getBrightnessForRender(float partialTick) {
      BlockPos blockpos = new BlockPos(this.posX, this.posY, this.posZ);
      return this.world.isBlockLoaded(blockpos) ? this.world.getCombinedLight(blockpos, 0) : 0;
   }

   public boolean isAlive() {
      return !this.isExpired;
   }

   public AxisAlignedBB getBoundingBox() {
      return this.boundingBox;
   }

   public void setBoundingBox(AxisAlignedBB bb) {
      this.boundingBox = bb;
   }
}
