package neo;

import java.util.Iterator;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;

public class MR extends Ig {
   private int warmupDelayTicks;
   private boolean sentSpikeEvent;
   private int lifeTicks;
   private boolean clientSideAttackStarted;
   private Iw caster;
   private UUID casterUuid;

   public MR(bij worldIn) {
      super(worldIn);
      this.lifeTicks = 22;
      this.setSize(0.5F, 0.8F);
   }

   public MR(bij worldIn, double x, double y, double z, float p_i47276_8_, int p_i47276_9_, Iw casterIn) {
      this(worldIn);
      this.warmupDelayTicks = p_i47276_9_;
      this.setCaster(casterIn);
      this.rotationYaw = p_i47276_8_ * 57.295776F;
      this.setPosition(x, y, z);
   }

   protected void entityInit() {
   }

   public void setCaster(@Nullable Iw p_190549_1_) {
      this.caster = p_190549_1_;
      this.casterUuid = p_190549_1_ == null ? null : p_190549_1_.getUniqueID();
   }

   @Nullable
   public Iw getCaster() {
      if (this.caster == null && this.casterUuid != null && this.world instanceof bis) {
         Ig entity = ((bis)this.world).getEntityFromUuid(this.casterUuid);
         if (entity instanceof Iw) {
            this.caster = (Iw)entity;
         }
      }

      return this.caster;
   }

   protected void readEntityFromNBT(QQ compound) {
      this.warmupDelayTicks = compound.getInteger("Warmup");
      this.casterUuid = compound.getUniqueId("OwnerUUID");
   }

   protected void writeEntityToNBT(QQ compound) {
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
            Iterator var14 = this.world.getEntitiesWithinAABB(Iw.class, this.getEntityBoundingBox().grow(0.2, 0.0, 0.2)).iterator();

            while(var14.hasNext()) {
               Iw entitylivingbase = (Iw)var14.next();
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

   private void damage(Iw p_190551_1_) {
      Iw entitylivingbase = this.getCaster();
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
            this.world.playSound(this.posX, this.posY, this.posZ, NO.EVOCATION_FANGS_ATTACK, this.getSoundCategory(), 1.0F, this.rand.nextFloat() * 0.2F + 0.85F, false);
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
