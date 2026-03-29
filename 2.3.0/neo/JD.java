package neo;

import com.google.common.base.Predicate;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;

public class JD extends Kc {
   public JD(bij worldIn) {
      super(worldIn);
      this.setSize(this.width * 2.35F, this.height * 2.35F);
      this.enablePersistence();
      if (this.wander != null) {
         this.wander.setExecutionChance(400);
      }

   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(Ni.MOVEMENT_SPEED).setBaseValue(0.30000001192092896);
      this.getEntityAttribute(Ni.ATTACK_DAMAGE).setBaseValue(8.0);
      this.getEntityAttribute(Ni.MAX_HEALTH).setBaseValue(80.0);
   }

   public static void registerFixesElderGuardian(DataFixer fixer) {
      Iu.registerFixesMob(fixer, JD.class);
   }

   @Nullable
   protected ResourceLocation getLootTable() {
      return bhq.ENTITIES_ELDER_GUARDIAN;
   }

   public int getAttackDuration() {
      return 60;
   }

   public void setGhost() {
      this.clientSideSpikesAnimation = 1.0F;
      this.clientSideSpikesAnimationO = this.clientSideSpikesAnimation;
   }

   protected SoundEvent getAmbientSound() {
      return this.isInWater() ? NO.ENTITY_ELDER_GUARDIAN_AMBIENT : NO.ENTITY_ELDERGUARDIAN_AMBIENTLAND;
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return this.isInWater() ? NO.ENTITY_ELDER_GUARDIAN_HURT : NO.ENTITY_ELDER_GUARDIAN_HURT_LAND;
   }

   protected SoundEvent getDeathSound() {
      return this.isInWater() ? NO.ENTITY_ELDER_GUARDIAN_DEATH : NO.ENTITY_ELDER_GUARDIAN_DEATH_LAND;
   }

   protected SoundEvent getFlopSound() {
      return NO.ENTITY_ELDER_GUARDIAN_FLOP;
   }

   protected void updateAITasks() {
      super.updateAITasks();
      int i = true;
      if ((this.ticksExisted + this.getEntityId()) % 1200 == 0) {
         VW potion = NL.MINING_FATIGUE;
         List<MG> list = this.world.getPlayers(MG.class, new Predicate<MG>() {
            public boolean apply(@Nullable MG p_apply_1_) {
               return JD.this.getDistanceSq(p_apply_1_) < 2500.0 && p_apply_1_.interactionManager.survivalOrAdventure();
            }

            // $FF: synthetic method
            // $FF: bridge method
            public boolean apply(@Nullable Object var1) {
               return this.apply((MG)var1);
            }
         });
         int j = true;
         int k = true;
         int l = true;
         Iterator var7 = list.iterator();

         label28:
         while(true) {
            MG entityplayermp;
            do {
               if (!var7.hasNext()) {
                  break label28;
               }

               entityplayermp = (MG)var7.next();
            } while(entityplayermp.isPotionActive(potion) && entityplayermp.getActivePotionEffect(potion).getAmplifier() >= 2 && entityplayermp.getActivePotionEffect(potion).getDuration() >= 1200);

            entityplayermp.connection.sendPacket(new TC(10, 0.0F));
            entityplayermp.addPotionEffect(new VZ(potion, 6000, 2));
         }
      }

      if (!this.hasHome()) {
         this.setHomePosAndDistance(new BlockPos(this), 16);
      }

   }
}
