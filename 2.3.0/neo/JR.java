package neo;

import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;

public class JR extends KS {
   private Mb wololoTarget;

   public JR(bij worldIn) {
      super(worldIn);
      this.setSize(0.6F, 1.95F);
      this.experienceValue = 10;
   }

   protected void initEntityAI() {
      super.initEntityAI();
      this.tasks.addTask(0, new He(this));
      this.tasks.addTask(1, new JN(this));
      this.tasks.addTask(2, new Gh(this, ME.class, 8.0F, 0.6, 1.0));
      this.tasks.addTask(4, new JO(this));
      this.tasks.addTask(5, new JM(this));
      this.tasks.addTask(6, new JQ(this));
      this.tasks.addTask(8, new Hn(this, 0.6));
      this.tasks.addTask(9, new Hq(this, ME.class, 3.0F, 1.0F));
      this.tasks.addTask(10, new Hq(this, Iu.class, 8.0F));
      this.targetTasks.addTask(1, new GB(this, true, new Class[]{JR.class}));
      this.targetTasks.addTask(2, (new GR(this, ME.class, true)).setUnseenMemoryTicks(300));
      this.targetTasks.addTask(3, (new GR(this, Mq.class, false)).setUnseenMemoryTicks(300));
      this.targetTasks.addTask(3, new GR(this, Kj.class, false));
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(Ni.MOVEMENT_SPEED).setBaseValue(0.5);
      this.getEntityAttribute(Ni.FOLLOW_RANGE).setBaseValue(12.0);
      this.getEntityAttribute(Ni.MAX_HEALTH).setBaseValue(24.0);
   }

   protected void entityInit() {
      super.entityInit();
   }

   public static void registerFixesEvoker(DataFixer fixer) {
      Iu.registerFixesMob(fixer, JR.class);
   }

   public void readEntityFromNBT(QQ compound) {
      super.readEntityFromNBT(compound);
   }

   public void writeEntityToNBT(QQ compound) {
      super.writeEntityToNBT(compound);
   }

   protected ResourceLocation getLootTable() {
      return bhq.ENTITIES_EVOCATION_ILLAGER;
   }

   protected void updateAITasks() {
      super.updateAITasks();
   }

   public void onUpdate() {
      super.onUpdate();
   }

   public boolean isOnSameTeam(Ig entityIn) {
      if (entityIn == null) {
         return false;
      } else if (entityIn == this) {
         return true;
      } else if (super.isOnSameTeam(entityIn)) {
         return true;
      } else if (entityIn instanceof Lc) {
         return this.isOnSameTeam(((Lc)entityIn).getOwner());
      } else if (entityIn instanceof Iw && ((Iw)entityIn).getCreatureAttribute() == IB.ILLAGER) {
         return this.getTeam() == null && entityIn.getTeam() == null;
      } else {
         return false;
      }
   }

   protected SoundEvent getAmbientSound() {
      return NO.ENTITY_EVOCATION_ILLAGER_AMBIENT;
   }

   protected SoundEvent getDeathSound() {
      return NO.EVOCATION_ILLAGER_DEATH;
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return NO.ENTITY_EVOCATION_ILLAGER_HURT;
   }

   private void setWololoTarget(@Nullable Mb wololoTargetIn) {
      this.wololoTarget = wololoTargetIn;
   }

   @Nullable
   private Mb getWololoTarget() {
      return this.wololoTarget;
   }

   protected SoundEvent getSpellSound() {
      return NO.EVOCATION_ILLAGER_CAST_SPELL;
   }

   // $FF: synthetic method
   static Mb access$300(JR x0) {
      return x0.getWololoTarget();
   }

   // $FF: synthetic method
   static Random access$400(JR x0) {
      return x0.rand;
   }

   // $FF: synthetic method
   static Random access$500(JR x0) {
      return x0.rand;
   }

   // $FF: synthetic method
   static Random access$600(JR x0) {
      return x0.rand;
   }

   // $FF: synthetic method
   static Random access$700(JR x0) {
      return x0.rand;
   }

   // $FF: synthetic method
   static Random access$800(JR x0) {
      return x0.rand;
   }

   // $FF: synthetic method
   static void access$900(JR x0, Mb x1) {
      x0.setWololoTarget(x1);
   }
}
