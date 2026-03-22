package net.minecraft.entity.projectile;

import java.util.Iterator;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityEvokerFangs extends Entity {
   private int warmupDelayTicks;
   private boolean sentSpikeEvent;
   private int lifeTicks;
   private boolean clientSideAttackStarted;
   private EntityLivingBase caster;
   private UUID casterUuid;

   public EntityEvokerFangs(World worldIn) {
      super(worldIn);
      this.lifeTicks = 22;
      this.setSize(0.5F, 0.8F);
   }

   public EntityEvokerFangs(World worldIn, double x, double y, double z, float p_i47276_8_, int p_i47276_9_, EntityLivingBase casterIn) {
      this(worldIn);
      this.warmupDelayTicks = p_i47276_9_;
      this.setCaster(casterIn);
      this.rotationYaw = p_i47276_8_ * 57.295776F;
      this.setPosition(x, y, z);
   }

   protected void entityInit() {
   }

   public void setCaster(@Nullable EntityLivingBase p_190549_1_) {
      this.caster = p_190549_1_;
      this.casterUuid = p_190549_1_ == null ? null : p_190549_1_.getUniqueID();
   }

   @Nullable
   public EntityLivingBase getCaster() {
      if (this.caster == null && this.casterUuid != null && this.world instanceof WorldServer) {
         Entity entity = ((WorldServer)this.world).getEntityFromUuid(this.casterUuid);
         if (entity instanceof EntityLivingBase) {
            this.caster = (EntityLivingBase)entity;
         }
      }

      return this.caster;
   }

   protected void readEntityFromNBT(NBTTagCompound compound) {
      this.warmupDelayTicks = compound.getInteger("Warmup");
      this.casterUuid = compound.getUniqueId("OwnerUUID");
   }

   protected void writeEntityToNBT(NBTTagCompound compound) {
      compound.setInteger("Warmup", this.warmupDelayTicks);
      if (this.casterUuid != null) {
         compound.setUniqueId("OwnerUUID", this.casterUuid);
      }

   }

   public void onUpdate() {
      super.onUpdate();
      if (this.world.isRemote) {
         if (this.clientSideAttackStarted) {
            --this.lifeTicks;
            if (this.lifeTicks == 14) {
               for(int i = 0; i < 12; ++i) {
                  double d0 = this.posX + (this.rand.nextDouble() * 2.0 - 1.0) * (double)this.width * 0.5;
                  double d1 = this.posY + 0.05 + this.rand.nextDouble() * 1.0;
                  double d2 = this.posZ + (this.rand.nextDouble() * 2.0 - 1.0) * (double)this.width * 0.5;
                  double d3 = (this.rand.nextDouble() * 2.0 - 1.0) * 0.3;
                  double d4 = 0.3 + this.rand.nextDouble() * 0.3;
                  double d5 = (this.rand.nextDouble() * 2.0 - 1.0) * 0.3;
                  this.world.spawnParticle(EnumParticleTypes.CRIT, d0, d1 + 1.0, d2, d3, d4, d5);
               }
            }
         }
      } else if (--this.warmupDelayTicks < 0) {
         if (this.warmupDelayTicks == -8) {
            Iterator var14 = this.world.getEntitiesWithinAABB(EntityLivingBase.class, this.getEntityBoundingBox().grow(0.2, 0.0, 0.2)).iterator();

            while(var14.hasNext()) {
               EntityLivingBase entitylivingbase = (EntityLivingBase)var14.next();
               this.damage(entitylivingbase);
            }
         }

         if (!this.sentSpikeEvent) {
            this.world.setEntityState(this, (byte)4);
            this.sentSpikeEvent = true;
         }

         if (--this.lifeTicks < 0) {
            this.setDead();
         }
      }

   }

   private void damage(EntityLivingBase p_190551_1_) {
      EntityLivingBase entitylivingbase = this.getCaster();
      if (p_190551_1_.isEntityAlive() && !p_190551_1_.getIsInvulnerable() && p_190551_1_ != entitylivingbase) {
         if (entitylivingbase == null) {
            p_190551_1_.attackEntityFrom(DamageSource.MAGIC, 6.0F);
         } else {
            if (entitylivingbase.isOnSameTeam(p_190551_1_)) {
               return;
            }

            p_190551_1_.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this, entitylivingbase), 6.0F);
         }
      }

   }

   public void handleStatusUpdate(byte id) {
      super.handleStatusUpdate(id);
      if (id == 4) {
         this.clientSideAttackStarted = true;
         if (!this.isSilent()) {
            this.world.playSound(this.posX, this.posY, this.posZ, SoundEvents.EVOCATION_FANGS_ATTACK, this.getSoundCategory(), 1.0F, this.rand.nextFloat() * 0.2F + 0.85F, false);
         }
      }

   }

   public float getAnimationProgress(float partialTicks) {
      if (!this.clientSideAttackStarted) {
         return 0.0F;
      } else {
         int i = this.lifeTicks - 2;
         return i <= 0 ? 1.0F : 1.0F - ((float)i - partialTicks) / 20.0F;
      }
   }
}
