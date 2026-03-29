package neo;

import com.google.common.base.Predicate;
import javax.annotation.Nullable;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;

public class Lf extends Jv {
   private boolean johnny;
   private static final Predicate<Ig> JOHNNY_SELECTOR = new Predicate<Ig>() {
      public boolean apply(@Nullable Ig p_apply_1_) {
         return p_apply_1_ instanceof Iw && ((Iw)p_apply_1_).attackable();
      }

      // $FF: synthetic method
      // $FF: bridge method
      public boolean apply(@Nullable Object var1) {
         return this.apply((Ig)var1);
      }
   };

   public Lf(bij worldIn) {
      super(worldIn);
      this.setSize(0.6F, 1.95F);
   }

   public static void registerFixesVindicator(DataFixer fixer) {
      Iu.registerFixesMob(fixer, Lf.class);
   }

   protected void initEntityAI() {
      super.initEntityAI();
      this.tasks.addTask(0, new He(this));
      this.tasks.addTask(4, new Gd(this, 1.0, false));
      this.tasks.addTask(8, new Hn(this, 0.6));
      this.tasks.addTask(9, new Hq(this, ME.class, 3.0F, 1.0F));
      this.tasks.addTask(10, new Hq(this, Iu.class, 8.0F));
      this.targetTasks.addTask(1, new GB(this, true, new Class[]{Lf.class}));
      this.targetTasks.addTask(2, new GR(this, ME.class, true));
      this.targetTasks.addTask(3, new GR(this, Mq.class, true));
      this.targetTasks.addTask(3, new GR(this, Kj.class, true));
      this.targetTasks.addTask(4, new Le(this));
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(Ni.MOVEMENT_SPEED).setBaseValue(0.3499999940395355);
      this.getEntityAttribute(Ni.FOLLOW_RANGE).setBaseValue(12.0);
      this.getEntityAttribute(Ni.MAX_HEALTH).setBaseValue(24.0);
      this.getEntityAttribute(Ni.ATTACK_DAMAGE).setBaseValue(5.0);
   }

   protected void entityInit() {
      super.entityInit();
   }

   protected ResourceLocation getLootTable() {
      return bhq.ENTITIES_VINDICATION_ILLAGER;
   }

   public boolean isAggressive() {
      return this.isAggressive(1);
   }

   public void setAggressive(boolean p_190636_1_) {
      this.setAggressive(1, p_190636_1_);
   }

   public void writeEntityToNBT(QQ compound) {
      super.writeEntityToNBT(compound);
      if (this.johnny) {
         compound.setBoolean("Johnny", true);
      }

   }

   public Ju getArmPose() {
      return this.isAggressive() ? Ju.ATTACKING : Ju.CROSSED;
   }

   public void readEntityFromNBT(QQ compound) {
      super.readEntityFromNBT(compound);
      if (compound.hasKey("Johnny", 99)) {
         this.johnny = compound.getBoolean("Johnny");
      }

   }

   @Nullable
   public ID onInitialSpawn(baL difficulty, @Nullable ID livingdata) {
      ID ientitylivingdata = super.onInitialSpawn(difficulty, livingdata);
      this.setEquipmentBasedOnDifficulty(difficulty);
      this.setEnchantmentBasedOnDifficulty(difficulty);
      return ientitylivingdata;
   }

   protected void setEquipmentBasedOnDifficulty(baL difficulty) {
      this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new Qy(NK.IRON_AXE));
   }

   protected void updateAITasks() {
      super.updateAITasks();
      this.setAggressive(this.getAttackTarget() != null);
   }

   public boolean isOnSameTeam(Ig entityIn) {
      if (super.isOnSameTeam(entityIn)) {
         return true;
      } else if (entityIn instanceof Iw && ((Iw)entityIn).getCreatureAttribute() == IB.ILLAGER) {
         return this.getTeam() == null && entityIn.getTeam() == null;
      } else {
         return false;
      }
   }

   public void setCustomNameTag(String name) {
      super.setCustomNameTag(name);
      if (!this.johnny && "Johnny".equals(name)) {
         this.johnny = true;
      }

   }

   protected SoundEvent getAmbientSound() {
      return NO.VINDICATION_ILLAGER_AMBIENT;
   }

   protected SoundEvent getDeathSound() {
      return NO.VINDICATION_ILLAGER_DEATH;
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return NO.ENTITY_VINDICATION_ILLAGER_HURT;
   }

   // $FF: synthetic method
   static Predicate access$000() {
      return JOHNNY_SELECTOR;
   }

   // $FF: synthetic method
   static boolean access$100(Lf x0) {
      return x0.johnny;
   }
}
