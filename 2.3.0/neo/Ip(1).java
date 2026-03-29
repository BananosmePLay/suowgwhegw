package neo;

import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class Ip extends Io {
   public Ip(bij worldIn) {
      super(worldIn);
   }

   public Ip(bij worldIn, BlockPos hangingPositionIn) {
      super(worldIn, hangingPositionIn);
      this.setPosition((double)hangingPositionIn.getX() + 0.5, (double)hangingPositionIn.getY() + 0.5, (double)hangingPositionIn.getZ() + 0.5);
      float f = 0.125F;
      float f1 = 0.1875F;
      float f2 = 0.25F;
      this.setEntityBoundingBox(new AxisAlignedBB(this.posX - 0.1875, this.posY - 0.25 + 0.125, this.posZ - 0.1875, this.posX + 0.1875, this.posY + 0.25 + 0.125, this.posZ + 0.1875));
      this.forceSpawn = true;
   }

   public void setPosition(double x, double y, double z) {
      super.setPosition((double)MathHelper.floor(x) + 0.5, (double)MathHelper.floor(y) + 0.5, (double)MathHelper.floor(z) + 0.5);
   }

   protected void updateBoundingBox() {
      this.posX = (double)this.hangingPosition.getX() + 0.5;
      this.posY = (double)this.hangingPosition.getY() + 0.5;
      this.posZ = (double)this.hangingPosition.getZ() + 0.5;
   }

   public void updateFacingWithBoundingBox(EnumFacing facingDirectionIn) {
   }

   public int getWidthPixels() {
      return 9;
   }

   public int getHeightPixels() {
      return 9;
   }

   public float getEyeHeight() {
      return -0.0625F;
   }

   public boolean isInRangeToRenderDist(double distance) {
      return distance < 1024.0;
   }

   public void onBroken(@Nullable Ig brokenEntity) {
      this.playSound(NO.ENTITY_LEASHKNOT_BREAK, 1.0F, 1.0F);
   }

   public boolean writeToNBTOptional(QQ compound) {
      return false;
   }

   public void writeEntityToNBT(QQ compound) {
   }

   public void readEntityFromNBT(QQ compound) {
   }

   public boolean processInitialInteract(ME player, EnumHand hand) {
      if (this.world.isRemote) {
         return true;
      } else {
         boolean flag = false;
         double d0 = 7.0;
         List<Iu> list = this.world.getEntitiesWithinAABB(Iu.class, new AxisAlignedBB(this.posX - 7.0, this.posY - 7.0, this.posZ - 7.0, this.posX + 7.0, this.posY + 7.0, this.posZ + 7.0));
         Iterator var7 = list.iterator();

         Iu entityliving1;
         while(var7.hasNext()) {
            entityliving1 = (Iu)var7.next();
            if (entityliving1.getLeashed() && entityliving1.getLeashHolder() == player) {
               entityliving1.setLeashHolder(this, true);
               flag = true;
            }
         }

         if (!flag) {
            this.setDead();
            if (player.capabilities.isCreativeMode) {
               var7 = list.iterator();

               while(var7.hasNext()) {
                  entityliving1 = (Iu)var7.next();
                  if (entityliving1.getLeashed() && entityliving1.getLeashHolder() == this) {
                     entityliving1.clearLeashed(true, false);
                  }
               }
            }
         }

         return true;
      }
   }

   public boolean onValidSurface() {
      return this.world.getBlockState(this.hangingPosition).getBlock() instanceof dL;
   }

   public static Ip createKnot(bij worldIn, BlockPos fence) {
      Ip entityleashknot = new Ip(worldIn, fence);
      worldIn.spawnEntity(entityleashknot);
      entityleashknot.playPlaceSound();
      return entityleashknot;
   }

   @Nullable
   public static Ip getKnotForPosition(bij worldIn, BlockPos pos) {
      int i = pos.getX();
      int j = pos.getY();
      int k = pos.getZ();
      Iterator var5 = worldIn.getEntitiesWithinAABB(Ip.class, new AxisAlignedBB((double)i - 1.0, (double)j - 1.0, (double)k - 1.0, (double)i + 1.0, (double)j + 1.0, (double)k + 1.0)).iterator();

      Ip entityleashknot;
      do {
         if (!var5.hasNext()) {
            return null;
         }

         entityleashknot = (Ip)var5.next();
      } while(!entityleashknot.getHangingPosition().equals(pos));

      return entityleashknot;
   }

   public void playPlaceSound() {
      this.playSound(NO.ENTITY_LEASHKNOT_PLACE, 1.0F, 1.0F);
   }
}
