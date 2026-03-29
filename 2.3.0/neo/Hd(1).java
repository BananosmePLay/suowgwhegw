package neo;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.math.BlockPos;

public class Hd extends Gi {
   private final Md horse;

   public Hd(Md horseIn) {
      this.horse = horseIn;
   }

   public boolean shouldExecute() {
      return this.horse.world.isAnyPlayerWithinRangeAt(this.horse.posX, this.horse.posY, this.horse.posZ, 10.0);
   }

   public void updateTask() {
      baL difficultyinstance = this.horse.world.getDifficultyForLocation(new BlockPos(this.horse));
      this.horse.setTrap(false);
      this.horse.setHorseTamed(true);
      this.horse.setGrowingAge(0);
      this.horse.world.addWeatherEffect(new HX(this.horse.world, this.horse.posX, this.horse.posY, this.horse.posZ, true));
      KH entityskeleton = this.createSkeleton(difficultyinstance, this.horse);
      entityskeleton.startRiding(this.horse);

      for(int i = 0; i < 3; ++i) {
         Lw abstracthorse = this.createHorse(difficultyinstance);
         KH entityskeleton1 = this.createSkeleton(difficultyinstance, abstracthorse);
         entityskeleton1.startRiding(abstracthorse);
         abstracthorse.addVelocity(this.horse.getRNG().nextGaussian() * 0.5, 0.0, this.horse.getRNG().nextGaussian() * 0.5);
      }

   }

   private Lw createHorse(baL p_188515_1_) {
      Md entityskeletonhorse = new Md(this.horse.world);
      entityskeletonhorse.onInitialSpawn(p_188515_1_, (ID)null);
      entityskeletonhorse.setPosition(this.horse.posX, this.horse.posY, this.horse.posZ);
      entityskeletonhorse.hurtResistantTime = 60;
      entityskeletonhorse.enablePersistence();
      entityskeletonhorse.setHorseTamed(true);
      entityskeletonhorse.setGrowingAge(0);
      entityskeletonhorse.world.spawnEntity(entityskeletonhorse);
      return entityskeletonhorse;
   }

   private KH createSkeleton(baL p_188514_1_, Lw p_188514_2_) {
      KH entityskeleton = new KH(p_188514_2_.world);
      entityskeleton.onInitialSpawn(p_188514_1_, (ID)null);
      entityskeleton.setPosition(p_188514_2_.posX, p_188514_2_.posY, p_188514_2_.posZ);
      entityskeleton.hurtResistantTime = 60;
      entityskeleton.enablePersistence();
      if (entityskeleton.getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty()) {
         entityskeleton.setItemStackToSlot(EntityEquipmentSlot.HEAD, new Qy(NK.IRON_HELMET));
      }

      entityskeleton.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, Ft.addRandomEnchantment(entityskeleton.getRNG(), entityskeleton.getHeldItemMainhand(), (int)(5.0F + p_188514_1_.getClampedAdditionalDifficulty() * (float)entityskeleton.getRNG().nextInt(18)), false));
      entityskeleton.setItemStackToSlot(EntityEquipmentSlot.HEAD, Ft.addRandomEnchantment(entityskeleton.getRNG(), entityskeleton.getItemStackFromSlot(EntityEquipmentSlot.HEAD), (int)(5.0F + p_188514_1_.getClampedAdditionalDifficulty() * (float)entityskeleton.getRNG().nextInt(18)), false));
      entityskeleton.world.spawnEntity(entityskeleton);
      return entityskeleton;
   }
}
