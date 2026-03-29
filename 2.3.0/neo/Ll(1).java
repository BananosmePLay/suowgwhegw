package neo;

import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;

public class Ll extends Lk {
   private static final Rd<Boolean> CONVERTING;
   private static final Rd<Integer> PROFESSION;
   private int conversionTime;
   private UUID converstionStarter;

   public Ll(bij worldIn) {
      super(worldIn);
   }

   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(CONVERTING, false);
      this.dataManager.register(PROFESSION, 0);
   }

   public void setProfession(int profession) {
      this.dataManager.set(PROFESSION, profession);
   }

   public int getProfession() {
      return Math.max((Integer)this.dataManager.get(PROFESSION) % 6, 0);
   }

   public static void registerFixesZombieVillager(DataFixer fixer) {
      Iu.registerFixesMob(fixer, Ll.class);
   }

   public void writeEntityToNBT(QQ compound) {
      super.writeEntityToNBT(compound);
      compound.setInteger("Profession", this.getProfession());
      compound.setInteger("ConversionTime", this.isConverting() ? this.conversionTime : -1);
      if (this.converstionStarter != null) {
         compound.setUniqueId("ConversionPlayer", this.converstionStarter);
      }

   }

   public void readEntityFromNBT(QQ compound) {
      super.readEntityFromNBT(compound);
      this.setProfession(compound.getInteger("Profession"));
      if (compound.hasKey("ConversionTime", 99) && compound.getInteger("ConversionTime") > -1) {
         this.startConverting(compound.hasUniqueId("ConversionPlayer") ? compound.getUniqueId("ConversionPlayer") : null, compound.getInteger("ConversionTime"));
      }

   }

   @Nullable
   public ID onInitialSpawn(baL difficulty, @Nullable ID livingdata) {
      this.setProfession(this.world.rand.nextInt(6));
      return super.onInitialSpawn(difficulty, livingdata);
   }

   public void onUpdate() {
      if (!this.world.isRemote && this.isConverting()) {
         int i = this.getConversionProgress();
         this.conversionTime -= i;
         if (this.conversionTime <= 0) {
            this.finishConversion();
         }
      }

      super.onUpdate();
   }

   public boolean processInteract(ME player, EnumHand hand) {
      Qy itemstack = player.getHeldItem(hand);
      if (itemstack.getItem() == NK.GOLDEN_APPLE && itemstack.getMetadata() == 0 && this.isPotionActive(NL.WEAKNESS)) {
         if (!player.capabilities.isCreativeMode) {
            itemstack.shrink(1);
         }

         if (!this.world.isRemote) {
            this.startConverting(player.getUniqueID(), this.rand.nextInt(2401) + 3600);
         }

         return true;
      } else {
         return false;
      }
   }

   protected boolean canDespawn() {
      return !this.isConverting();
   }

   public boolean isConverting() {
      return (Boolean)this.getDataManager().get(CONVERTING);
   }

   protected void startConverting(@Nullable UUID conversionStarterIn, int conversionTimeIn) {
      this.converstionStarter = conversionStarterIn;
      this.conversionTime = conversionTimeIn;
      this.getDataManager().set(CONVERTING, true);
      this.removePotionEffect(NL.WEAKNESS);
      this.addPotionEffect(new VZ(NL.STRENGTH, conversionTimeIn, Math.min(this.world.getDifficulty().getId() - 1, 0)));
      this.world.setEntityState(this, (byte)16);
   }

   public void handleStatusUpdate(byte id) {
      if (id == 16) {
         if (!this.isSilent()) {
            this.world.playSound(this.posX + 0.5, this.posY + 0.5, this.posZ + 0.5, NO.ENTITY_ZOMBIE_VILLAGER_CURE, this.getSoundCategory(), 1.0F + this.rand.nextFloat(), this.rand.nextFloat() * 0.7F + 0.3F, false);
         }
      } else {
         super.handleStatusUpdate(id);
      }

   }

   protected void finishConversion() {
      Mq entityvillager = new Mq(this.world);
      entityvillager.copyLocationAndAnglesFrom(this);
      entityvillager.setProfession(this.getProfession());
      entityvillager.finalizeMobSpawn(this.world.getDifficultyForLocation(new BlockPos(entityvillager)), (ID)null, false);
      entityvillager.setLookingForHome();
      if (this.isChild()) {
         entityvillager.setGrowingAge(-24000);
      }

      this.world.removeEntity(this);
      entityvillager.setNoAI(this.isAIDisabled());
      if (this.hasCustomName()) {
         entityvillager.setCustomNameTag(this.getCustomNameTag());
         entityvillager.setAlwaysRenderNameTag(this.getAlwaysRenderNameTag());
      }

      this.world.spawnEntity(entityvillager);
      if (this.converstionStarter != null) {
         ME entityplayer = this.world.getPlayerEntityByUUID(this.converstionStarter);
         if (entityplayer instanceof MG) {
            bY.CURED_ZOMBIE_VILLAGER.trigger((MG)entityplayer, this, entityvillager);
         }
      }

      entityvillager.addPotionEffect(new VZ(NL.NAUSEA, 200, 0));
      this.world.playEvent((ME)null, 1027, new BlockPos((int)this.posX, (int)this.posY, (int)this.posZ), 0);
   }

   protected int getConversionProgress() {
      int i = 1;
      if (this.rand.nextFloat() < 0.01F) {
         int j = 0;
         BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

         for(int k = (int)this.posX - 4; k < (int)this.posX + 4 && j < 14; ++k) {
            for(int l = (int)this.posY - 4; l < (int)this.posY + 4 && j < 14; ++l) {
               for(int i1 = (int)this.posZ - 4; i1 < (int)this.posZ + 4 && j < 14; ++i1) {
                  co block = this.world.getBlockState(blockpos$mutableblockpos.setPos(k, l, i1)).getBlock();
                  if (block == Nk.IRON_BARS || block == Nk.BED) {
                     if (this.rand.nextFloat() < 0.3F) {
                        ++i;
                     }

                     ++j;
                  }
               }
            }
         }
      }

      return i;
   }

   protected float getSoundPitch() {
      return this.isChild() ? (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 2.0F : (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F;
   }

   public SoundEvent getAmbientSound() {
      return NO.ENTITY_ZOMBIE_VILLAGER_AMBIENT;
   }

   public SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return NO.ENTITY_ZOMBIE_VILLAGER_HURT;
   }

   public SoundEvent getDeathSound() {
      return NO.ENTITY_ZOMBIE_VILLAGER_DEATH;
   }

   public SoundEvent getStepSound() {
      return NO.ENTITY_ZOMBIE_VILLAGER_STEP;
   }

   @Nullable
   protected ResourceLocation getLootTable() {
      return bhq.ENTITIES_ZOMBIE_VILLAGER;
   }

   protected Qy getSkullDrop() {
      return Qy.EMPTY;
   }

   static {
      CONVERTING = Rv.createKey(Ll.class, Rt.BOOLEAN);
      PROFESSION = Rv.createKey(Ll.class, Rt.VARINT);
   }
}
