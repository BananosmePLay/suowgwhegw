package neo;

import java.util.Collection;
import java.util.Iterator;
import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;

public class JB extends Kl {
   private static final Rd<Integer> STATE;
   private static final Rd<Boolean> POWERED;
   private static final Rd<Boolean> IGNITED;
   private int lastActiveTime;
   private int timeSinceIgnited;
   private int fuseTime = 30;
   private int explosionRadius = 3;
   private int droppedSkulls;

   public JB(bij worldIn) {
      super(worldIn);
      this.setSize(0.6F, 1.7F);
   }

   protected void initEntityAI() {
      this.tasks.addTask(1, new He(this));
      this.tasks.addTask(2, new Gl(this));
      this.tasks.addTask(3, new Gh(this, LN.class, 6.0F, 1.0, 1.2));
      this.tasks.addTask(4, new Gd(this, 1.0, false));
      this.tasks.addTask(5, new Ho(this, 0.8));
      this.tasks.addTask(6, new Hq(this, ME.class, 8.0F));
      this.tasks.addTask(6, new GH(this));
      this.targetTasks.addTask(1, new GR(this, ME.class, true));
      this.targetTasks.addTask(2, new GB(this, false, new Class[0]));
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(Ni.MOVEMENT_SPEED).setBaseValue(0.25);
   }

   public int getMaxFallHeight() {
      return this.getAttackTarget() == null ? 3 : 3 + (int)(this.getHealth() - 1.0F);
   }

   public void fall(float distance, float damageMultiplier) {
      super.fall(distance, damageMultiplier);
      this.timeSinceIgnited = (int)((float)this.timeSinceIgnited + distance * 1.5F);
      if (this.timeSinceIgnited > this.fuseTime - 5) {
         this.timeSinceIgnited = this.fuseTime - 5;
      }

   }

   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(STATE, -1);
      this.dataManager.register(POWERED, false);
      this.dataManager.register(IGNITED, false);
   }

   public static void registerFixesCreeper(DataFixer fixer) {
      Iu.registerFixesMob(fixer, JB.class);
   }

   public void writeEntityToNBT(QQ compound) {
      super.writeEntityToNBT(compound);
      if ((Boolean)this.dataManager.get(POWERED)) {
         compound.setBoolean("powered", true);
      }

      compound.setShort("Fuse", (short)this.fuseTime);
      compound.setByte("ExplosionRadius", (byte)this.explosionRadius);
      compound.setBoolean("ignited", this.hasIgnited());
   }

   public void readEntityFromNBT(QQ compound) {
      super.readEntityFromNBT(compound);
      this.dataManager.set(POWERED, compound.getBoolean("powered"));
      if (compound.hasKey("Fuse", 99)) {
         this.fuseTime = compound.getShort("Fuse");
      }

      if (compound.hasKey("ExplosionRadius", 99)) {
         this.explosionRadius = compound.getByte("ExplosionRadius");
      }

      if (compound.getBoolean("ignited")) {
         this.ignite();
      }

   }

   public void onUpdate() {
      if (this.isEntityAlive()) {
         this.lastActiveTime = this.timeSinceIgnited;
         if (this.hasIgnited()) {
            this.setCreeperState(1);
         }

         int i = this.getCreeperState();
         if (i > 0 && this.timeSinceIgnited == 0) {
            this.playSound(NO.ENTITY_CREEPER_PRIMED, 1.0F, 0.5F);
         }

         this.timeSinceIgnited += i;
         if (this.timeSinceIgnited < 0) {
            this.timeSinceIgnited = 0;
         }

         if (this.timeSinceIgnited >= this.fuseTime) {
            this.timeSinceIgnited = this.fuseTime;
            this.explode();
         }
      }

      super.onUpdate();
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return NO.ENTITY_CREEPER_HURT;
   }

   protected SoundEvent getDeathSound() {
      return NO.ENTITY_CREEPER_DEATH;
   }

   public void onDeath(DamageSource cause) {
      super.onDeath(cause);
      if (this.world.getGameRules().getBoolean("doMobLoot")) {
         if (cause.getTrueSource() instanceof KH) {
            int i = OL.getIdFromItem(NK.RECORD_13);
            int j = OL.getIdFromItem(NK.RECORD_WAIT);
            int k = i + this.rand.nextInt(j - i + 1);
            this.dropItem(OL.getItemById(k), 1);
         } else if (cause.getTrueSource() instanceof JB && cause.getTrueSource() != this && ((JB)cause.getTrueSource()).getPowered() && ((JB)cause.getTrueSource()).ableToCauseSkullDrop()) {
            ((JB)cause.getTrueSource()).incrementDroppedSkulls();
            this.entityDropItem(new Qy(NK.SKULL, 1, 4), 0.0F);
         }
      }

   }

   public boolean attackEntityAsMob(Ig entityIn) {
      return true;
   }

   public boolean getPowered() {
      return (Boolean)this.dataManager.get(POWERED);
   }

   public float getCreeperFlashIntensity(float p_70831_1_) {
      return ((float)this.lastActiveTime + (float)(this.timeSinceIgnited - this.lastActiveTime) * p_70831_1_) / (float)(this.fuseTime - 2);
   }

   @Nullable
   protected ResourceLocation getLootTable() {
      return bhq.ENTITIES_CREEPER;
   }

   public int getCreeperState() {
      return (Integer)this.dataManager.get(STATE);
   }

   public void setCreeperState(int state) {
      this.dataManager.set(STATE, state);
   }

   public void onStruckByLightning(HX lightningBolt) {
      super.onStruckByLightning(lightningBolt);
      this.dataManager.set(POWERED, true);
   }

   protected boolean processInteract(ME player, EnumHand hand) {
      Qy itemstack = player.getHeldItem(hand);
      if (itemstack.getItem() == NK.FLINT_AND_STEEL) {
         this.world.playSound(player, this.posX, this.posY, this.posZ, NO.ITEM_FLINTANDSTEEL_USE, this.getSoundCategory(), 1.0F, this.rand.nextFloat() * 0.4F + 0.8F);
         player.swingArm(hand);
         if (!this.world.isRemote) {
            this.ignite();
            itemstack.damageItem(1, player);
            return true;
         }
      }

      return super.processInteract(player, hand);
   }

   private void explode() {
      if (!this.world.isRemote) {
         boolean flag = this.world.getGameRules().getBoolean("mobGriefing");
         float f = this.getPowered() ? 2.0F : 1.0F;
         this.dead = true;
         this.world.createExplosion(this, this.posX, this.posY, this.posZ, (float)this.explosionRadius * f, flag);
         this.setDead();
         this.spawnLingeringCloud();
      }

   }

   private void spawnLingeringCloud() {
      Collection<VZ> collection = this.getActivePotionEffects();
      if (!collection.isEmpty()) {
         Ii entityareaeffectcloud = new Ii(this.world, this.posX, this.posY, this.posZ);
         entityareaeffectcloud.setRadius(2.5F);
         entityareaeffectcloud.setRadiusOnUse(-0.5F);
         entityareaeffectcloud.setWaitTime(10);
         entityareaeffectcloud.setDuration(entityareaeffectcloud.getDuration() / 2);
         entityareaeffectcloud.setRadiusPerTick(-entityareaeffectcloud.getRadius() / (float)entityareaeffectcloud.getDuration());
         Iterator var3 = collection.iterator();

         while(var3.hasNext()) {
            VZ potioneffect = (VZ)var3.next();
            entityareaeffectcloud.addEffect(new VZ(potioneffect));
         }

         this.world.spawnEntity(entityareaeffectcloud);
      }

   }

   public boolean hasIgnited() {
      return (Boolean)this.dataManager.get(IGNITED);
   }

   public void ignite() {
      this.dataManager.set(IGNITED, true);
   }

   public boolean ableToCauseSkullDrop() {
      return this.droppedSkulls < 1 && this.world.getGameRules().getBoolean("doMobLoot");
   }

   public void incrementDroppedSkulls() {
      ++this.droppedSkulls;
   }

   static {
      STATE = Rv.createKey(JB.class, Rt.VARINT);
      POWERED = Rv.createKey(JB.class, Rt.BOOLEAN);
      IGNITED = Rv.createKey(JB.class, Rt.BOOLEAN);
   }
}
