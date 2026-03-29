package neo;

import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;

public class LB extends Ly {
   public LB(bij worldIn) {
      super(worldIn);
      this.setSize(0.9F, 1.4F);
   }

   public static void registerFixesCow(DataFixer fixer) {
      Iu.registerFixesMob(fixer, LB.class);
   }

   protected void initEntityAI() {
      this.tasks.addTask(0, new He(this));
      this.tasks.addTask(1, new GX(this, 2.0));
      this.tasks.addTask(2, new GI(this, 1.0));
      this.tasks.addTask(3, new Hj(this, 1.25, NK.WHEAT, false));
      this.tasks.addTask(4, new Gz(this, 1.25));
      this.tasks.addTask(5, new Ho(this, 1.0));
      this.tasks.addTask(6, new Hq(this, ME.class, 6.0F));
      this.tasks.addTask(7, new GH(this));
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(Ni.MAX_HEALTH).setBaseValue(10.0);
      this.getEntityAttribute(Ni.MOVEMENT_SPEED).setBaseValue(0.20000000298023224);
   }

   protected SoundEvent getAmbientSound() {
      return NO.ENTITY_COW_AMBIENT;
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return NO.ENTITY_COW_HURT;
   }

   protected SoundEvent getDeathSound() {
      return NO.ENTITY_COW_DEATH;
   }

   protected void playStepSound(BlockPos pos, co blockIn) {
      this.playSound(NO.ENTITY_COW_STEP, 0.15F, 1.0F);
   }

   protected float getSoundVolume() {
      return 0.4F;
   }

   @Nullable
   protected ResourceLocation getLootTable() {
      return bhq.ENTITIES_COW;
   }

   public boolean processInteract(ME player, EnumHand hand) {
      Qy itemstack = player.getHeldItem(hand);
      if (itemstack.getItem() == NK.BUCKET && !player.capabilities.isCreativeMode && !this.isChild()) {
         player.playSound(NO.ENTITY_COW_MILK, 1.0F, 1.0F);
         itemstack.shrink(1);
         if (itemstack.isEmpty()) {
            player.setHeldItem(hand, new Qy(NK.MILK_BUCKET));
         } else if (!player.inventory.addItemStackToInventory(new Qy(NK.MILK_BUCKET))) {
            player.dropItem(new Qy(NK.MILK_BUCKET), false);
         }

         return true;
      } else {
         return super.processInteract(player, hand);
      }
   }

   public LB createChild(Ih ageable) {
      return new LB(this.world);
   }

   public float getEyeHeight() {
      return this.isChild() ? this.height : 1.3F;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public Ih createChild(Ih var1) {
      return this.createChild(var1);
   }
}
