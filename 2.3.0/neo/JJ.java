package neo;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Sets;
import java.util.Set;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class JJ extends Kl {
   private static final UUID ATTACKING_SPEED_BOOST_ID = UUID.fromString("020E0DFB-87AE-4653-9556-831010E291A0");
   private static final FW ATTACKING_SPEED_BOOST;
   private static final Set<co> CARRIABLE_BLOCKS;
   private static final Rd<Optional<in>> CARRIED_BLOCK;
   private static final Rd<Boolean> SCREAMING;
   private int lastCreepySound;
   private int targetChangeTime;

   public JJ(bij worldIn) {
      super(worldIn);
      this.setSize(0.6F, 2.9F);
      this.stepHeight = 1.0F;
      this.setPathPriority(VQ.WATER, -1.0F);
   }

   protected void initEntityAI() {
      this.tasks.addTask(0, new He(this));
      this.tasks.addTask(2, new Gd(this, 1.0, false));
      this.tasks.addTask(7, new Ho(this, 1.0, 0.0F));
      this.tasks.addTask(8, new Hq(this, ME.class, 8.0F));
      this.tasks.addTask(8, new GH(this));
      this.tasks.addTask(10, new JH(this));
      this.tasks.addTask(11, new JI(this));
      this.targetTasks.addTask(1, new JG(this));
      this.targetTasks.addTask(2, new GB(this, false, new Class[0]));
      this.targetTasks.addTask(3, new GR(this, JK.class, 10, true, false, new Predicate<JK>() {
         public boolean apply(@Nullable JK p_apply_1_) {
            return p_apply_1_.isSpawnedByPlayer();
         }

         // $FF: synthetic method
         // $FF: bridge method
         public boolean apply(@Nullable Object var1) {
            return this.apply((JK)var1);
         }
      }));
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(Ni.MAX_HEALTH).setBaseValue(40.0);
      this.getEntityAttribute(Ni.MOVEMENT_SPEED).setBaseValue(0.30000001192092896);
      this.getEntityAttribute(Ni.ATTACK_DAMAGE).setBaseValue(7.0);
      this.getEntityAttribute(Ni.FOLLOW_RANGE).setBaseValue(64.0);
   }

   public void setAttackTarget(@Nullable Iw entitylivingbaseIn) {
      super.setAttackTarget(entitylivingbaseIn);
      FZ iattributeinstance = this.getEntityAttribute(Ni.MOVEMENT_SPEED);
      if (entitylivingbaseIn == null) {
         this.targetChangeTime = 0;
         this.dataManager.set(SCREAMING, false);
         iattributeinstance.removeModifier(ATTACKING_SPEED_BOOST);
      } else {
         this.targetChangeTime = this.ticksExisted;
         this.dataManager.set(SCREAMING, true);
         if (!iattributeinstance.hasModifier(ATTACKING_SPEED_BOOST)) {
            iattributeinstance.applyModifier(ATTACKING_SPEED_BOOST);
         }
      }

   }

   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(CARRIED_BLOCK, Optional.absent());
      this.dataManager.register(SCREAMING, false);
   }

   public void playEndermanSound() {
      if (this.ticksExisted >= this.lastCreepySound + 400) {
         this.lastCreepySound = this.ticksExisted;
         if (!this.isSilent()) {
            this.world.playSound(this.posX, this.posY + (double)this.getEyeHeight(), this.posZ, NO.ENTITY_ENDERMEN_STARE, this.getSoundCategory(), 2.5F, 1.0F, false);
         }
      }

   }

   public void notifyDataManagerChange(Rd<?> key) {
      if (SCREAMING.equals(key) && this.isScreaming() && this.world.isRemote) {
         this.playEndermanSound();
      }

      super.notifyDataManagerChange(key);
   }

   public static void registerFixesEnderman(DataFixer fixer) {
      Iu.registerFixesMob(fixer, JJ.class);
   }

   public void writeEntityToNBT(QQ compound) {
      super.writeEntityToNBT(compound);
      in iblockstate = this.getHeldBlockState();
      if (iblockstate != null) {
         compound.setShort("carried", (short)co.getIdFromBlock(iblockstate.getBlock()));
         compound.setShort("carriedData", (short)iblockstate.getBlock().getMetaFromState(iblockstate));
      }

   }

   public void readEntityFromNBT(QQ compound) {
      super.readEntityFromNBT(compound);
      in iblockstate;
      if (compound.hasKey("carried", 8)) {
         iblockstate = co.getBlockFromName(compound.getString("carried")).getStateFromMeta(compound.getShort("carriedData") & '\uffff');
      } else {
         iblockstate = co.getBlockById(compound.getShort("carried")).getStateFromMeta(compound.getShort("carriedData") & '\uffff');
      }

      if (iblockstate == null || iblockstate.getBlock() == null || iblockstate.getMaterial() == hM.AIR) {
         iblockstate = null;
      }

      this.setHeldBlockState(iblockstate);
   }

   private boolean shouldAttackPlayer(ME player) {
      Qy itemstack = (Qy)player.inventory.armorInventory.get(3);
      if (itemstack.getItem() == OL.getItemFromBlock(Nk.PUMPKIN)) {
         return false;
      } else {
         Vec3d vec3d = player.getLook(1.0F).normalize();
         Vec3d vec3d1 = new Vec3d(this.posX - player.posX, this.getEntityBoundingBox().minY + (double)this.getEyeHeight() - (player.posY + (double)player.getEyeHeight()), this.posZ - player.posZ);
         double d0 = vec3d1.length();
         vec3d1 = vec3d1.normalize();
         double d1 = vec3d.dotProduct(vec3d1);
         return d1 > 1.0 - 0.025 / d0 ? player.canEntityBeSeen(this) : false;
      }
   }

   public float getEyeHeight() {
      return 2.55F;
   }

   public void onLivingUpdate() {
      if (this.world.isRemote) {
         for(int i = 0; i < 2; ++i) {
            this.world.spawnParticle(EnumParticleTypes.PORTAL, this.posX + (this.rand.nextDouble() - 0.5) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height - 0.25, this.posZ + (this.rand.nextDouble() - 0.5) * (double)this.width, (this.rand.nextDouble() - 0.5) * 2.0, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5) * 2.0);
         }
      }

      this.isJumping = false;
      super.onLivingUpdate();
   }

   protected void updateAITasks() {
      if (this.isWet()) {
         this.attackEntityFrom(DamageSource.DROWN, 1.0F);
      }

      if (this.world.isDaytime() && this.ticksExisted >= this.targetChangeTime + 600) {
         float f = this.getBrightness();
         if (f > 0.5F && this.world.canSeeSky(new BlockPos(this)) && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F) {
            this.setAttackTarget((Iw)null);
            this.teleportRandomly();
         }
      }

      super.updateAITasks();
   }

   protected boolean teleportRandomly() {
      double d0 = this.posX + (this.rand.nextDouble() - 0.5) * 64.0;
      double d1 = this.posY + (double)(this.rand.nextInt(64) - 32);
      double d2 = this.posZ + (this.rand.nextDouble() - 0.5) * 64.0;
      return this.teleportTo(d0, d1, d2);
   }

   protected boolean teleportToEntity(Ig p_70816_1_) {
      Vec3d vec3d = new Vec3d(this.posX - p_70816_1_.posX, this.getEntityBoundingBox().minY + (double)(this.height / 2.0F) - p_70816_1_.posY + (double)p_70816_1_.getEyeHeight(), this.posZ - p_70816_1_.posZ);
      vec3d = vec3d.normalize();
      double d0 = 16.0;
      double d1 = this.posX + (this.rand.nextDouble() - 0.5) * 8.0 - vec3d.x * 16.0;
      double d2 = this.posY + (double)(this.rand.nextInt(16) - 8) - vec3d.y * 16.0;
      double d3 = this.posZ + (this.rand.nextDouble() - 0.5) * 8.0 - vec3d.z * 16.0;
      return this.teleportTo(d1, d2, d3);
   }

   private boolean teleportTo(double x, double y, double z) {
      boolean flag = this.attemptTeleport(x, y, z);
      if (flag) {
         this.world.playSound((ME)null, this.prevPosX, this.prevPosY, this.prevPosZ, NO.ENTITY_ENDERMEN_TELEPORT, this.getSoundCategory(), 1.0F, 1.0F);
         this.playSound(NO.ENTITY_ENDERMEN_TELEPORT, 1.0F, 1.0F);
      }

      return flag;
   }

   protected SoundEvent getAmbientSound() {
      return this.isScreaming() ? NO.ENTITY_ENDERMEN_SCREAM : NO.ENTITY_ENDERMEN_AMBIENT;
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return NO.ENTITY_ENDERMEN_HURT;
   }

   protected SoundEvent getDeathSound() {
      return NO.ENTITY_ENDERMEN_DEATH;
   }

   protected void dropEquipment(boolean wasRecentlyHit, int lootingModifier) {
      super.dropEquipment(wasRecentlyHit, lootingModifier);
      in iblockstate = this.getHeldBlockState();
      if (iblockstate != null) {
         OL item = OL.getItemFromBlock(iblockstate.getBlock());
         int i = item.getHasSubtypes() ? iblockstate.getBlock().getMetaFromState(iblockstate) : 0;
         this.entityDropItem(new Qy(item, 1, i), 0.0F);
      }

   }

   @Nullable
   protected ResourceLocation getLootTable() {
      return bhq.ENTITIES_ENDERMAN;
   }

   public void setHeldBlockState(@Nullable in state) {
      this.dataManager.set(CARRIED_BLOCK, Optional.fromNullable(state));
   }

   @Nullable
   public in getHeldBlockState() {
      return (in)((Optional)this.dataManager.get(CARRIED_BLOCK)).orNull();
   }

   public boolean attackEntityFrom(DamageSource source, float amount) {
      if (this.isEntityInvulnerable(source)) {
         return false;
      } else if (source instanceof EntityDamageSourceIndirect) {
         for(int i = 0; i < 64; ++i) {
            if (this.teleportRandomly()) {
               return true;
            }
         }

         return false;
      } else {
         boolean flag = super.attackEntityFrom(source, amount);
         if (source.isUnblockable() && this.rand.nextInt(10) != 0) {
            this.teleportRandomly();
         }

         return flag;
      }
   }

   public boolean isScreaming() {
      return (Boolean)this.dataManager.get(SCREAMING);
   }

   // $FF: synthetic method
   static boolean access$100(JJ x0, ME x1) {
      return x0.shouldAttackPlayer(x1);
   }

   // $FF: synthetic method
   static Set access$200() {
      return CARRIABLE_BLOCKS;
   }

   static {
      ATTACKING_SPEED_BOOST = (new FW(ATTACKING_SPEED_BOOST_ID, "Attacking speed boost", 0.15000000596046448, 0)).setSaved(false);
      CARRIABLE_BLOCKS = Sets.newIdentityHashSet();
      CARRIED_BLOCK = Rv.createKey(JJ.class, Rt.OPTIONAL_BLOCK_STATE);
      SCREAMING = Rv.createKey(JJ.class, Rt.BOOLEAN);
      CARRIABLE_BLOCKS.add(Nk.GRASS);
      CARRIABLE_BLOCKS.add(Nk.DIRT);
      CARRIABLE_BLOCKS.add(Nk.SAND);
      CARRIABLE_BLOCKS.add(Nk.GRAVEL);
      CARRIABLE_BLOCKS.add(Nk.YELLOW_FLOWER);
      CARRIABLE_BLOCKS.add(Nk.RED_FLOWER);
      CARRIABLE_BLOCKS.add(Nk.BROWN_MUSHROOM);
      CARRIABLE_BLOCKS.add(Nk.RED_MUSHROOM);
      CARRIABLE_BLOCKS.add(Nk.TNT);
      CARRIABLE_BLOCKS.add(Nk.CACTUS);
      CARRIABLE_BLOCKS.add(Nk.CLAY);
      CARRIABLE_BLOCKS.add(Nk.PUMPKIN);
      CARRIABLE_BLOCKS.add(Nk.MELON_BLOCK);
      CARRIABLE_BLOCKS.add(Nk.MYCELIUM);
      CARRIABLE_BLOCKS.add(Nk.NETHERRACK);
   }
}
