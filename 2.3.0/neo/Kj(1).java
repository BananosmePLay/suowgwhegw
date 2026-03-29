package neo;

import com.google.common.base.Predicate;
import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class Kj extends JY {
   protected static final Rd<Byte> PLAYER_CREATED;
   private int homeCheckTimer;
   @Nullable
   Za village;
   private int attackTimer;
   private int holdRoseTick;

   public Kj(bij worldIn) {
      super(worldIn);
      this.setSize(1.4F, 2.7F);
   }

   protected void initEntityAI() {
      this.tasks.addTask(1, new Gd(this, 1.0, true));
      this.tasks.addTask(2, new GN(this, 0.9, 32.0F));
      this.tasks.addTask(3, new GK(this, 0.6, true));
      this.tasks.addTask(4, new GM(this, 1.0));
      this.tasks.addTask(5, new GG(this));
      this.tasks.addTask(6, new Ho(this, 0.6));
      this.tasks.addTask(7, new Hq(this, ME.class, 6.0F));
      this.tasks.addTask(8, new GH(this));
      this.targetTasks.addTask(1, new Gm(this));
      this.targetTasks.addTask(2, new GB(this, false, new Class[0]));
      this.targetTasks.addTask(3, new GR(this, Iu.class, 10, false, true, new Predicate<Iu>() {
         public boolean apply(@Nullable Iu p_apply_1_) {
            return p_apply_1_ != null && Lo.VISIBLE_MOB_SELECTOR.apply(p_apply_1_) && !(p_apply_1_ instanceof JB);
         }

         // $FF: synthetic method
         // $FF: bridge method
         public boolean apply(@Nullable Object var1) {
            return this.apply((Iu)var1);
         }
      }));
   }

   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(PLAYER_CREATED, (byte)0);
   }

   protected void updateAITasks() {
      if (--this.homeCheckTimer <= 0) {
         this.homeCheckTimer = 70 + this.rand.nextInt(50);
         this.village = this.world.getVillageCollection().getNearestVillage(new BlockPos(this), 32);
         if (this.village == null) {
            this.detachHome();
         } else {
            BlockPos blockpos = this.village.getCenter();
            this.setHomePosAndDistance(blockpos, (int)((float)this.village.getVillageRadius() * 0.6F));
         }
      }

      super.updateAITasks();
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(Ni.MAX_HEALTH).setBaseValue(100.0);
      this.getEntityAttribute(Ni.MOVEMENT_SPEED).setBaseValue(0.25);
      this.getEntityAttribute(Ni.KNOCKBACK_RESISTANCE).setBaseValue(1.0);
   }

   protected int decreaseAirSupply(int air) {
      return air;
   }

   protected void collideWithEntity(Ig entityIn) {
      if (entityIn instanceof Lo && !(entityIn instanceof JB) && this.getRNG().nextInt(20) == 0) {
         this.setAttackTarget((Iw)entityIn);
      }

      super.collideWithEntity(entityIn);
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      if (this.attackTimer > 0) {
         --this.attackTimer;
      }

      if (this.holdRoseTick > 0) {
         --this.holdRoseTick;
      }

      if (this.motionX * this.motionX + this.motionZ * this.motionZ > 2.500000277905201E-7 && this.rand.nextInt(5) == 0) {
         int i = MathHelper.floor(this.posX);
         int j = MathHelper.floor(this.posY - 0.20000000298023224);
         int k = MathHelper.floor(this.posZ);
         in iblockstate = this.world.getBlockState(new BlockPos(i, j, k));
         if (iblockstate.getMaterial() != hM.AIR) {
            this.world.spawnParticle(EnumParticleTypes.BLOCK_CRACK, this.posX + ((double)this.rand.nextFloat() - 0.5) * (double)this.width, this.getEntityBoundingBox().minY + 0.1, this.posZ + ((double)this.rand.nextFloat() - 0.5) * (double)this.width, 4.0 * ((double)this.rand.nextFloat() - 0.5), 0.5, ((double)this.rand.nextFloat() - 0.5) * 4.0, co.getStateId(iblockstate));
         }
      }

   }

   public boolean canAttackClass(Class<? extends Iw> cls) {
      if (this.isPlayerCreated() && ME.class.isAssignableFrom(cls)) {
         return false;
      } else {
         return cls == JB.class ? false : super.canAttackClass(cls);
      }
   }

   public static void registerFixesIronGolem(DataFixer fixer) {
      Iu.registerFixesMob(fixer, Kj.class);
   }

   public void writeEntityToNBT(QQ compound) {
      super.writeEntityToNBT(compound);
      compound.setBoolean("PlayerCreated", this.isPlayerCreated());
   }

   public void readEntityFromNBT(QQ compound) {
      super.readEntityFromNBT(compound);
      this.setPlayerCreated(compound.getBoolean("PlayerCreated"));
   }

   public boolean attackEntityAsMob(Ig entityIn) {
      this.attackTimer = 10;
      this.world.setEntityState(this, (byte)4);
      boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float)(7 + this.rand.nextInt(15)));
      if (flag) {
         entityIn.motionY += 0.4000000059604645;
         this.applyEnchantments(this, entityIn);
      }

      this.playSound(NO.ENTITY_IRONGOLEM_ATTACK, 1.0F, 1.0F);
      return flag;
   }

   public void handleStatusUpdate(byte id) {
      if (id == 4) {
         this.attackTimer = 10;
         this.playSound(NO.ENTITY_IRONGOLEM_ATTACK, 1.0F, 1.0F);
      } else if (id == 11) {
         this.holdRoseTick = 400;
      } else if (id == 34) {
         this.holdRoseTick = 0;
      } else {
         super.handleStatusUpdate(id);
      }

   }

   public Za getVillage() {
      return this.village;
   }

   public int getAttackTimer() {
      return this.attackTimer;
   }

   public void setHoldingRose(boolean p_70851_1_) {
      if (p_70851_1_) {
         this.holdRoseTick = 400;
         this.world.setEntityState(this, (byte)11);
      } else {
         this.holdRoseTick = 0;
         this.world.setEntityState(this, (byte)34);
      }

   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return NO.ENTITY_IRONGOLEM_HURT;
   }

   protected SoundEvent getDeathSound() {
      return NO.ENTITY_IRONGOLEM_DEATH;
   }

   protected void playStepSound(BlockPos pos, co blockIn) {
      this.playSound(NO.ENTITY_IRONGOLEM_STEP, 1.0F, 1.0F);
   }

   @Nullable
   protected ResourceLocation getLootTable() {
      return bhq.ENTITIES_IRON_GOLEM;
   }

   public int getHoldRoseTick() {
      return this.holdRoseTick;
   }

   public boolean isPlayerCreated() {
      return ((Byte)this.dataManager.get(PLAYER_CREATED) & 1) != 0;
   }

   public void setPlayerCreated(boolean playerCreated) {
      byte b0 = (Byte)this.dataManager.get(PLAYER_CREATED);
      if (playerCreated) {
         this.dataManager.set(PLAYER_CREATED, (byte)(b0 | 1));
      } else {
         this.dataManager.set(PLAYER_CREATED, (byte)(b0 & -2));
      }

   }

   public void onDeath(DamageSource cause) {
      if (!this.isPlayerCreated() && this.attackingPlayer != null && this.village != null) {
         this.village.modifyPlayerReputation(this.attackingPlayer.getName(), -5);
      }

      super.onDeath(cause);
   }

   static {
      PLAYER_CREATED = Rv.createKey(Kj.class, Rt.BYTE);
   }
}
