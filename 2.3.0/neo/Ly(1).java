package neo;

import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public abstract class Ly extends Ih implements Mx {
   protected co spawnableBlock;
   private int inLove;
   private UUID playerInLove;

   public Ly(bij worldIn) {
      super(worldIn);
      this.spawnableBlock = Nk.GRASS;
   }

   protected void updateAITasks() {
      if (this.getGrowingAge() != 0) {
         this.inLove = 0;
      }

      super.updateAITasks();
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      if (this.getGrowingAge() != 0) {
         this.inLove = 0;
      }

      if (this.inLove > 0) {
         --this.inLove;
         if (this.inLove % 10 == 0) {
            double d0 = this.rand.nextGaussian() * 0.02;
            double d1 = this.rand.nextGaussian() * 0.02;
            double d2 = this.rand.nextGaussian() * 0.02;
            this.world.spawnParticle(EnumParticleTypes.HEART, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5 + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);
         }
      }

   }

   public boolean attackEntityFrom(DamageSource source, float amount) {
      if (this.isEntityInvulnerable(source)) {
         return false;
      } else {
         this.inLove = 0;
         return super.attackEntityFrom(source, amount);
      }
   }

   public float getBlockPathWeight(BlockPos pos) {
      return this.world.getBlockState(pos.down()).getBlock() == this.spawnableBlock ? 10.0F : this.world.getLightBrightness(pos) - 0.5F;
   }

   public void writeEntityToNBT(QQ compound) {
      super.writeEntityToNBT(compound);
      compound.setInteger("InLove", this.inLove);
      if (this.playerInLove != null) {
         compound.setUniqueId("LoveCause", this.playerInLove);
      }

   }

   public double getYOffset() {
      return 0.14;
   }

   public void readEntityFromNBT(QQ compound) {
      super.readEntityFromNBT(compound);
      this.inLove = compound.getInteger("InLove");
      this.playerInLove = compound.hasUniqueId("LoveCause") ? compound.getUniqueId("LoveCause") : null;
   }

   public boolean getCanSpawnHere() {
      int i = MathHelper.floor(this.posX);
      int j = MathHelper.floor(this.getEntityBoundingBox().minY);
      int k = MathHelper.floor(this.posZ);
      BlockPos blockpos = new BlockPos(i, j, k);
      return this.world.getBlockState(blockpos.down()).getBlock() == this.spawnableBlock && this.world.getLight(blockpos) > 8 && super.getCanSpawnHere();
   }

   public int getTalkInterval() {
      return 120;
   }

   protected boolean canDespawn() {
      return false;
   }

   protected int getExperiencePoints(ME player) {
      return 1 + this.world.rand.nextInt(3);
   }

   public boolean isBreedingItem(Qy stack) {
      return stack.getItem() == NK.WHEAT;
   }

   public boolean processInteract(ME player, EnumHand hand) {
      Qy itemstack = player.getHeldItem(hand);
      if (!itemstack.isEmpty()) {
         if (this.isBreedingItem(itemstack) && this.getGrowingAge() == 0 && this.inLove <= 0) {
            this.consumeItemFromStack(player, itemstack);
            this.setInLove(player);
            return true;
         }

         if (this.isChild() && this.isBreedingItem(itemstack)) {
            this.consumeItemFromStack(player, itemstack);
            this.ageUp((int)((float)(-this.getGrowingAge() / 20) * 0.1F), true);
            return true;
         }
      }

      return super.processInteract(player, hand);
   }

   protected void consumeItemFromStack(ME player, Qy stack) {
      if (!player.capabilities.isCreativeMode) {
         stack.shrink(1);
      }

   }

   public void setInLove(@Nullable ME player) {
      this.inLove = 600;
      if (player != null) {
         this.playerInLove = player.getUniqueID();
      }

      this.world.setEntityState(this, (byte)18);
   }

   @Nullable
   public MG getLoveCause() {
      if (this.playerInLove == null) {
         return null;
      } else {
         ME entityplayer = this.world.getPlayerEntityByUUID(this.playerInLove);
         return entityplayer instanceof MG ? (MG)entityplayer : null;
      }
   }

   public boolean isInLove() {
      return this.inLove > 0;
   }

   public void resetInLove() {
      this.inLove = 0;
   }

   public boolean canMateWith(Ly otherAnimal) {
      if (otherAnimal == this) {
         return false;
      } else if (otherAnimal.getClass() != this.getClass()) {
         return false;
      } else {
         return this.isInLove() && otherAnimal.isInLove();
      }
   }

   public void handleStatusUpdate(byte id) {
      if (id == 18) {
         for(int i = 0; i < 7; ++i) {
            double d0 = this.rand.nextGaussian() * 0.02;
            double d1 = this.rand.nextGaussian() * 0.02;
            double d2 = this.rand.nextGaussian() * 0.02;
            this.world.spawnParticle(EnumParticleTypes.HEART, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5 + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);
         }
      } else {
         super.handleStatusUpdate(id);
      }

   }
}
