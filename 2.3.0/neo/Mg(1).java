package neo;

import com.google.common.base.Optional;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;

public abstract class Mg extends Ly implements IF {
   protected static final Rd<Byte> TAMED;
   protected static final Rd<Optional<UUID>> OWNER_UNIQUE_ID;
   protected Hc aiSit;

   public Mg(bij worldIn) {
      super(worldIn);
      this.setupTamedAI();
   }

   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(TAMED, (byte)0);
      this.dataManager.register(OWNER_UNIQUE_ID, Optional.absent());
   }

   public void writeEntityToNBT(QQ compound) {
      super.writeEntityToNBT(compound);
      if (this.getOwnerId() == null) {
         compound.setString("OwnerUUID", "");
      } else {
         compound.setString("OwnerUUID", this.getOwnerId().toString());
      }

      compound.setBoolean("Sitting", this.isSitting());
   }

   public void readEntityFromNBT(QQ compound) {
      super.readEntityFromNBT(compound);
      String s;
      if (compound.hasKey("OwnerUUID", 8)) {
         s = compound.getString("OwnerUUID");
      } else {
         String s1 = compound.getString("Owner");
         s = Xg.convertMobOwnerIfNeeded(this.getServer(), s1);
      }

      if (!s.isEmpty()) {
         try {
            this.setOwnerId(UUID.fromString(s));
            this.setTamed(true);
         } catch (Throwable var4) {
            this.setTamed(false);
         }
      }

      if (this.aiSit != null) {
         this.aiSit.setSitting(compound.getBoolean("Sitting"));
      }

      this.setSitting(compound.getBoolean("Sitting"));
   }

   public boolean canBeLeashedTo(ME player) {
      return !this.getLeashed();
   }

   protected void playTameEffect(boolean play) {
      EnumParticleTypes enumparticletypes = EnumParticleTypes.HEART;
      if (!play) {
         enumparticletypes = EnumParticleTypes.SMOKE_NORMAL;
      }

      for(int i = 0; i < 7; ++i) {
         double d0 = this.rand.nextGaussian() * 0.02;
         double d1 = this.rand.nextGaussian() * 0.02;
         double d2 = this.rand.nextGaussian() * 0.02;
         this.world.spawnParticle(enumparticletypes, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5 + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);
      }

   }

   public void handleStatusUpdate(byte id) {
      if (id == 7) {
         this.playTameEffect(true);
      } else if (id == 6) {
         this.playTameEffect(false);
      } else {
         super.handleStatusUpdate(id);
      }

   }

   public boolean isTamed() {
      return ((Byte)this.dataManager.get(TAMED) & 4) != 0;
   }

   public void setTamed(boolean tamed) {
      byte b0 = (Byte)this.dataManager.get(TAMED);
      if (tamed) {
         this.dataManager.set(TAMED, (byte)(b0 | 4));
      } else {
         this.dataManager.set(TAMED, (byte)(b0 & -5));
      }

      this.setupTamedAI();
   }

   protected void setupTamedAI() {
   }

   public boolean isSitting() {
      return ((Byte)this.dataManager.get(TAMED) & 1) != 0;
   }

   public void setSitting(boolean sitting) {
      byte b0 = (Byte)this.dataManager.get(TAMED);
      if (sitting) {
         this.dataManager.set(TAMED, (byte)(b0 | 1));
      } else {
         this.dataManager.set(TAMED, (byte)(b0 & -2));
      }

   }

   @Nullable
   public UUID getOwnerId() {
      return (UUID)((Optional)this.dataManager.get(OWNER_UNIQUE_ID)).orNull();
   }

   public void setOwnerId(@Nullable UUID p_184754_1_) {
      this.dataManager.set(OWNER_UNIQUE_ID, Optional.fromNullable(p_184754_1_));
   }

   public void setTamedBy(ME player) {
      this.setTamed(true);
      this.setOwnerId(player.getUniqueID());
      if (player instanceof MG) {
         bY.TAME_ANIMAL.trigger((MG)player, this);
      }

   }

   @Nullable
   public Iw getOwner() {
      try {
         UUID uuid = this.getOwnerId();
         return uuid == null ? null : this.world.getPlayerEntityByUUID(uuid);
      } catch (IllegalArgumentException var2) {
         return null;
      }
   }

   public boolean isOwner(Iw entityIn) {
      return entityIn == this.getOwner();
   }

   public Hc getAISit() {
      return this.aiSit;
   }

   public boolean shouldAttackEntity(Iw target, Iw owner) {
      return true;
   }

   public WE getTeam() {
      if (this.isTamed()) {
         Iw entitylivingbase = this.getOwner();
         if (entitylivingbase != null) {
            return entitylivingbase.getTeam();
         }
      }

      return super.getTeam();
   }

   public boolean isOnSameTeam(Ig entityIn) {
      if (this.isTamed()) {
         Iw entitylivingbase = this.getOwner();
         if (entityIn == entitylivingbase) {
            return true;
         }

         if (entitylivingbase != null) {
            return entitylivingbase.isOnSameTeam(entityIn);
         }
      }

      return super.isOnSameTeam(entityIn);
   }

   public void onDeath(DamageSource cause) {
      if (!this.world.isRemote && this.world.getGameRules().getBoolean("showDeathMessages") && this.getOwner() instanceof MG) {
         this.getOwner().sendMessage(this.getCombatTracker().getDeathMessage());
      }

      super.onDeath(cause);
   }

   // $FF: synthetic method
   // $FF: bridge method
   @Nullable
   public Ig getOwner() {
      return this.getOwner();
   }

   static {
      TAMED = Rv.createKey(Mg.class, Rt.BYTE);
      OWNER_UNIQUE_ID = Rv.createKey(Mg.class, Rt.OPTIONAL_UNIQUE_ID);
   }
}
