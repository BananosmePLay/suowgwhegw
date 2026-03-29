package neo;

import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;

public class JK extends Kl {
   private int lifetime;
   private boolean playerSpawned;

   public JK(bij worldIn) {
      super(worldIn);
      this.experienceValue = 3;
      this.setSize(0.4F, 0.3F);
   }

   protected void initEntityAI() {
      this.tasks.addTask(1, new He(this));
      this.tasks.addTask(2, new Gd(this, 1.0, false));
      this.tasks.addTask(3, new Ho(this, 1.0));
      this.tasks.addTask(7, new Hq(this, ME.class, 8.0F));
      this.tasks.addTask(8, new GH(this));
      this.targetTasks.addTask(1, new GB(this, true, new Class[0]));
      this.targetTasks.addTask(2, new GR(this, ME.class, true));
   }

   public float getEyeHeight() {
      return 0.1F;
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(Ni.MAX_HEALTH).setBaseValue(8.0);
      this.getEntityAttribute(Ni.MOVEMENT_SPEED).setBaseValue(0.25);
      this.getEntityAttribute(Ni.ATTACK_DAMAGE).setBaseValue(2.0);
   }

   protected boolean canTriggerWalking() {
      return false;
   }

   protected SoundEvent getAmbientSound() {
      return NO.ENTITY_ENDERMITE_AMBIENT;
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return NO.ENTITY_ENDERMITE_HURT;
   }

   protected SoundEvent getDeathSound() {
      return NO.ENTITY_ENDERMITE_DEATH;
   }

   protected void playStepSound(BlockPos pos, co blockIn) {
      this.playSound(NO.ENTITY_ENDERMITE_STEP, 0.15F, 1.0F);
   }

   @Nullable
   protected ResourceLocation getLootTable() {
      return bhq.ENTITIES_ENDERMITE;
   }

   public static void registerFixesEndermite(DataFixer fixer) {
      Iu.registerFixesMob(fixer, JK.class);
   }

   public void readEntityFromNBT(QQ compound) {
      super.readEntityFromNBT(compound);
      this.lifetime = compound.getInteger("Lifetime");
      this.playerSpawned = compound.getBoolean("PlayerSpawned");
   }

   public void writeEntityToNBT(QQ compound) {
      super.writeEntityToNBT(compound);
      compound.setInteger("Lifetime", this.lifetime);
      compound.setBoolean("PlayerSpawned", this.playerSpawned);
   }

   public void onUpdate() {
      this.renderYawOffset = this.rotationYaw;
      super.onUpdate();
   }

   public void setRenderYawOffset(float offset) {
      this.rotationYaw = offset;
      super.setRenderYawOffset(offset);
   }

   public double getYOffset() {
      return 0.1;
   }

   public boolean isSpawnedByPlayer() {
      return this.playerSpawned;
   }

   public void setSpawnedByPlayer(boolean spawnedByPlayer) {
      this.playerSpawned = spawnedByPlayer;
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      if (this.world.isRemote) {
         for(int i = 0; i < 2; ++i) {
            this.world.spawnParticle(EnumParticleTypes.PORTAL, this.posX + (this.rand.nextDouble() - 0.5) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5) * (double)this.width, (this.rand.nextDouble() - 0.5) * 2.0, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5) * 2.0);
         }
      } else {
         if (!this.isNoDespawnRequired()) {
            ++this.lifetime;
         }

         if (this.lifetime >= 2400) {
            this.setDead();
         }
      }

   }

   protected boolean isValidLightLevel() {
      return true;
   }

   public boolean getCanSpawnHere() {
      if (super.getCanSpawnHere()) {
         ME entityplayer = this.world.getClosestPlayerToEntity(this, 5.0);
         return entityplayer == null;
      } else {
         return false;
      }
   }

   public IB getCreatureAttribute() {
      return IB.ARTHROPOD;
   }
}
