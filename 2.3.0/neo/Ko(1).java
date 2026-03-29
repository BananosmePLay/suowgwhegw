package neo;

import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;

public class Ko extends Lk {
   private static final UUID ATTACK_SPEED_BOOST_MODIFIER_UUID = UUID.fromString("49455A49-7EC5-45BA-B886-3B90B23A1718");
   private static final FW ATTACK_SPEED_BOOST_MODIFIER;
   private int angerLevel;
   private int randomSoundDelay;
   private UUID angerTargetUUID;

   public Ko(bij worldIn) {
      super(worldIn);
      this.isImmuneToFire = true;
   }

   public void setRevengeTarget(@Nullable Iw livingBase) {
      super.setRevengeTarget(livingBase);
      if (livingBase != null) {
         this.angerTargetUUID = livingBase.getUniqueID();
      }

   }

   protected void applyEntityAI() {
      this.targetTasks.addTask(1, new Km(this));
      this.targetTasks.addTask(2, new Kn(this));
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SPAWN_REINFORCEMENTS_CHANCE).setBaseValue(0.0);
      this.getEntityAttribute(Ni.MOVEMENT_SPEED).setBaseValue(0.23000000417232513);
      this.getEntityAttribute(Ni.ATTACK_DAMAGE).setBaseValue(5.0);
   }

   protected void updateAITasks() {
      FZ iattributeinstance = this.getEntityAttribute(Ni.MOVEMENT_SPEED);
      if (this.isAngry()) {
         if (!this.isChild() && !iattributeinstance.hasModifier(ATTACK_SPEED_BOOST_MODIFIER)) {
            iattributeinstance.applyModifier(ATTACK_SPEED_BOOST_MODIFIER);
         }

         --this.angerLevel;
      } else if (iattributeinstance.hasModifier(ATTACK_SPEED_BOOST_MODIFIER)) {
         iattributeinstance.removeModifier(ATTACK_SPEED_BOOST_MODIFIER);
      }

      if (this.randomSoundDelay > 0 && --this.randomSoundDelay == 0) {
         this.playSound(NO.ENTITY_ZOMBIE_PIG_ANGRY, this.getSoundVolume() * 2.0F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 1.8F);
      }

      if (this.angerLevel > 0 && this.angerTargetUUID != null && this.getRevengeTarget() == null) {
         ME entityplayer = this.world.getPlayerEntityByUUID(this.angerTargetUUID);
         this.setRevengeTarget(entityplayer);
         this.attackingPlayer = entityplayer;
         this.recentlyHit = this.getRevengeTimer();
      }

      super.updateAITasks();
   }

   public boolean getCanSpawnHere() {
      return this.world.getDifficulty() != baV.PEACEFUL;
   }

   public boolean isNotColliding() {
      return this.world.checkNoEntityCollision(this.getEntityBoundingBox(), this) && this.world.getCollisionBoxes(this, this.getEntityBoundingBox()).isEmpty() && !this.world.containsAnyLiquid(this.getEntityBoundingBox());
   }

   public static void registerFixesPigZombie(DataFixer fixer) {
      Iu.registerFixesMob(fixer, Ko.class);
   }

   public void writeEntityToNBT(QQ compound) {
      super.writeEntityToNBT(compound);
      compound.setShort("Anger", (short)this.angerLevel);
      if (this.angerTargetUUID != null) {
         compound.setString("HurtBy", this.angerTargetUUID.toString());
      } else {
         compound.setString("HurtBy", "");
      }

   }

   public void readEntityFromNBT(QQ compound) {
      super.readEntityFromNBT(compound);
      this.angerLevel = compound.getShort("Anger");
      String s = compound.getString("HurtBy");
      if (!s.isEmpty()) {
         this.angerTargetUUID = UUID.fromString(s);
         ME entityplayer = this.world.getPlayerEntityByUUID(this.angerTargetUUID);
         this.setRevengeTarget(entityplayer);
         if (entityplayer != null) {
            this.attackingPlayer = entityplayer;
            this.recentlyHit = this.getRevengeTimer();
         }
      }

   }

   public boolean attackEntityFrom(DamageSource source, float amount) {
      if (this.isEntityInvulnerable(source)) {
         return false;
      } else {
         Ig entity = source.getTrueSource();
         if (entity instanceof ME) {
            this.becomeAngryAt(entity);
         }

         return super.attackEntityFrom(source, amount);
      }
   }

   private void becomeAngryAt(Ig p_70835_1_) {
      this.angerLevel = 400 + this.rand.nextInt(400);
      this.randomSoundDelay = this.rand.nextInt(40);
      if (p_70835_1_ instanceof Iw) {
         this.setRevengeTarget((Iw)p_70835_1_);
      }

   }

   public boolean isAngry() {
      return this.angerLevel > 0;
   }

   protected SoundEvent getAmbientSound() {
      return NO.ENTITY_ZOMBIE_PIG_AMBIENT;
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return NO.ENTITY_ZOMBIE_PIG_HURT;
   }

   protected SoundEvent getDeathSound() {
      return NO.ENTITY_ZOMBIE_PIG_DEATH;
   }

   @Nullable
   protected ResourceLocation getLootTable() {
      return bhq.ENTITIES_ZOMBIE_PIGMAN;
   }

   public boolean processInteract(ME player, EnumHand hand) {
      return false;
   }

   protected void setEquipmentBasedOnDifficulty(baL difficulty) {
      this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new Qy(NK.GOLDEN_SWORD));
   }

   protected Qy getSkullDrop() {
      return Qy.EMPTY;
   }

   public boolean isPreventingPlayerRest(ME playerIn) {
      return this.isAngry();
   }

   // $FF: synthetic method
   static void access$000(Ko x0, Ig x1) {
      x0.becomeAngryAt(x1);
   }

   static {
      ATTACK_SPEED_BOOST_MODIFIER = (new FW(ATTACK_SPEED_BOOST_MODIFIER_UUID, "Attacking speed boost", 0.05, 0)).setSaved(false);
   }
}
