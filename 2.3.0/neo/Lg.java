package neo;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.MathHelper;

public class Lg extends Kl implements IK {
   private static final UUID MODIFIER_UUID = UUID.fromString("5CD17E52-A79A-43D3-A529-90FDE04B181E");
   private static final FW MODIFIER;
   private static final Rd<Boolean> IS_DRINKING;
   private int potionUseTimer;

   public Lg(bij worldIn) {
      super(worldIn);
      this.setSize(0.6F, 1.95F);
   }

   public static void registerFixesWitch(DataFixer fixer) {
      Iu.registerFixesMob(fixer, Lg.class);
   }

   protected void initEntityAI() {
      this.tasks.addTask(1, new He(this));
      this.tasks.addTask(2, new Ge(this, 1.0, 60, 10.0F));
      this.tasks.addTask(2, new Ho(this, 1.0));
      this.tasks.addTask(3, new Hq(this, ME.class, 8.0F));
      this.tasks.addTask(3, new GH(this));
      this.targetTasks.addTask(1, new GB(this, false, new Class[0]));
      this.targetTasks.addTask(2, new GR(this, ME.class, true));
   }

   protected void entityInit() {
      super.entityInit();
      this.getDataManager().register(IS_DRINKING, false);
   }

   protected SoundEvent getAmbientSound() {
      return NO.ENTITY_WITCH_AMBIENT;
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return NO.ENTITY_WITCH_HURT;
   }

   protected SoundEvent getDeathSound() {
      return NO.ENTITY_WITCH_DEATH;
   }

   public void setDrinkingPotion(boolean drinkingPotion) {
      this.getDataManager().set(IS_DRINKING, drinkingPotion);
   }

   public boolean isDrinkingPotion() {
      return (Boolean)this.getDataManager().get(IS_DRINKING);
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(Ni.MAX_HEALTH).setBaseValue(26.0);
      this.getEntityAttribute(Ni.MOVEMENT_SPEED).setBaseValue(0.25);
   }

   public void onLivingUpdate() {
      if (!this.world.isRemote) {
         if (this.isDrinkingPotion()) {
            if (this.potionUseTimer-- <= 0) {
               this.setDrinkingPotion(false);
               Qy itemstack = this.getHeldItemMainhand();
               this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, Qy.EMPTY);
               if (itemstack.getItem() == NK.POTIONITEM) {
                  List<VZ> list = Wg.getEffectsFromStack(itemstack);
                  if (list != null) {
                     Iterator var3 = list.iterator();

                     while(var3.hasNext()) {
                        VZ potioneffect = (VZ)var3.next();
                        this.addPotionEffect(new VZ(potioneffect));
                     }
                  }
               }

               this.getEntityAttribute(Ni.MOVEMENT_SPEED).removeModifier(MODIFIER);
            }
         } else {
            Wf potiontype = null;
            if (this.rand.nextFloat() < 0.15F && this.isInsideOfMaterial(hM.WATER) && !this.isPotionActive(NL.WATER_BREATHING)) {
               potiontype = NN.WATER_BREATHING;
            } else if (this.rand.nextFloat() < 0.15F && (this.isBurning() || this.getLastDamageSource() != null && this.getLastDamageSource().isFireDamage()) && !this.isPotionActive(NL.FIRE_RESISTANCE)) {
               potiontype = NN.FIRE_RESISTANCE;
            } else if (this.rand.nextFloat() < 0.05F && this.getHealth() < this.getMaxHealth()) {
               potiontype = NN.HEALING;
            } else if (this.rand.nextFloat() < 0.5F && this.getAttackTarget() != null && !this.isPotionActive(NL.SPEED) && this.getAttackTarget().getDistanceSq(this) > 121.0) {
               potiontype = NN.SWIFTNESS;
            }

            if (potiontype != null) {
               this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, Wg.addPotionToItemStack(new Qy(NK.POTIONITEM), potiontype));
               this.potionUseTimer = this.getHeldItemMainhand().getMaxItemUseDuration();
               this.setDrinkingPotion(true);
               this.world.playSound((ME)null, this.posX, this.posY, this.posZ, NO.ENTITY_WITCH_DRINK, this.getSoundCategory(), 1.0F, 0.8F + this.rand.nextFloat() * 0.4F);
               FZ iattributeinstance = this.getEntityAttribute(Ni.MOVEMENT_SPEED);
               iattributeinstance.removeModifier(MODIFIER);
               iattributeinstance.applyModifier(MODIFIER);
            }
         }

         if (this.rand.nextFloat() < 7.5E-4F) {
            this.world.setEntityState(this, (byte)15);
         }
      }

      super.onLivingUpdate();
   }

   public void handleStatusUpdate(byte id) {
      if (id == 15) {
         for(int i = 0; i < this.rand.nextInt(35) + 10; ++i) {
            this.world.spawnParticle(EnumParticleTypes.SPELL_WITCH, this.posX + this.rand.nextGaussian() * 0.12999999523162842, this.getEntityBoundingBox().maxY + 0.5 + this.rand.nextGaussian() * 0.12999999523162842, this.posZ + this.rand.nextGaussian() * 0.12999999523162842, 0.0, 0.0, 0.0);
         }
      } else {
         super.handleStatusUpdate(id);
      }

   }

   protected float applyPotionDamageCalculations(DamageSource source, float damage) {
      damage = super.applyPotionDamageCalculations(source, damage);
      if (source.getTrueSource() == this) {
         damage = 0.0F;
      }

      if (source.isMagicDamage()) {
         damage = (float)((double)damage * 0.15);
      }

      return damage;
   }

   @Nullable
   protected ResourceLocation getLootTable() {
      return bhq.ENTITIES_WITCH;
   }

   public void attackEntityWithRangedAttack(Iw target, float distanceFactor) {
      if (!this.isDrinkingPotion()) {
         double d0 = target.posY + (double)target.getEyeHeight() - 1.100000023841858;
         double d1 = target.posX + target.motionX - this.posX;
         double d2 = d0 - this.posY;
         double d3 = target.posZ + target.motionZ - this.posZ;
         float f = MathHelper.sqrt(d1 * d1 + d3 * d3);
         Wf potiontype = NN.HARMING;
         if (f >= 8.0F && !target.isPotionActive(NL.SLOWNESS)) {
            potiontype = NN.SLOWNESS;
         } else if (target.getHealth() >= 8.0F && !target.isPotionActive(NL.POISON)) {
            potiontype = NN.POISON;
         } else if (f <= 3.0F && !target.isPotionActive(NL.WEAKNESS) && this.rand.nextFloat() < 0.25F) {
            potiontype = NN.WEAKNESS;
         }

         MY entitypotion = new MY(this.world, this, Wg.addPotionToItemStack(new Qy(NK.SPLASH_POTION), potiontype));
         entitypotion.rotationPitch -= -20.0F;
         entitypotion.shoot(d1, d2 + (double)(f * 0.2F), d3, 0.75F, 8.0F);
         this.world.playSound((ME)null, this.posX, this.posY, this.posZ, NO.ENTITY_WITCH_THROW, this.getSoundCategory(), 1.0F, 0.8F + this.rand.nextFloat() * 0.4F);
         this.world.spawnEntity(entitypotion);
      }

   }

   public float getEyeHeight() {
      return 1.62F;
   }

   public void setSwingingArms(boolean swingingArms) {
   }

   static {
      MODIFIER = (new FW(MODIFIER_UUID, "Drinking speed penalty", -0.25, 0)).setSaved(false);
      IS_DRINKING = Rv.createKey(Lg.class, Rt.BOOLEAN);
   }
}
