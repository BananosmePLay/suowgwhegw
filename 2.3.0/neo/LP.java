package neo;

import com.google.common.base.Predicate;
import com.google.common.collect.Sets;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class LP extends Mc implements LD {
   private static final Rd<Integer> VARIANT;
   private static final Predicate<Iu> CAN_MIMIC;
   private static final OL DEADLY_ITEM;
   private static final Set<OL> TAME_ITEMS;
   private static final Int2ObjectMap<SoundEvent> IMITATION_SOUND_EVENTS;
   public float flap;
   public float flapSpeed;
   public float oFlapSpeed;
   public float oFlap;
   public float flapping = 1.0F;
   private boolean partyParrot;
   private BlockPos jukeboxPosition;

   public LP(bij worldIn) {
      super(worldIn);
      this.setSize(0.5F, 0.9F);
      this.moveHelper = new Ht(this);
   }

   @Nullable
   public ID onInitialSpawn(baL difficulty, @Nullable ID livingdata) {
      this.setVariant(this.rand.nextInt(5));
      return super.onInitialSpawn(difficulty, livingdata);
   }

   protected void initEntityAI() {
      this.aiSit = new Hc(this);
      this.tasks.addTask(0, new GX(this, 1.25));
      this.tasks.addTask(0, new He(this));
      this.tasks.addTask(1, new Hq(this, ME.class, 8.0F));
      this.tasks.addTask(2, this.aiSit);
      this.tasks.addTask(2, new Gy(this, 1.0, 5.0F, 1.0F));
      this.tasks.addTask(2, new Hp(this, 1.0));
      this.tasks.addTask(3, new GC(this));
      this.tasks.addTask(3, new Gv(this, 1.0, 3.0F, 7.0F));
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getAttributeMap().registerAttribute(Ni.FLYING_SPEED);
      this.getEntityAttribute(Ni.MAX_HEALTH).setBaseValue(6.0);
      this.getEntityAttribute(Ni.FLYING_SPEED).setBaseValue(0.4000000059604645);
      this.getEntityAttribute(Ni.MOVEMENT_SPEED).setBaseValue(0.20000000298023224);
   }

   protected VL createNavigator(bij worldIn) {
      VN pathnavigateflying = new VN(this, worldIn);
      pathnavigateflying.setCanOpenDoors(false);
      pathnavigateflying.setCanFloat(true);
      pathnavigateflying.setCanEnterDoors(true);
      return pathnavigateflying;
   }

   public float getEyeHeight() {
      return this.height * 0.6F;
   }

   public void onLivingUpdate() {
      playMimicSound(this.world, this);
      if (this.jukeboxPosition == null || this.jukeboxPosition.distanceSq(this.posX, this.posY, this.posZ) > 12.0 || this.world.getBlockState(this.jukeboxPosition).getBlock() != Nk.JUKEBOX) {
         this.partyParrot = false;
         this.jukeboxPosition = null;
      }

      super.onLivingUpdate();
      this.calculateFlapping();
   }

   public void setPartying(BlockPos pos, boolean isPartying) {
      this.jukeboxPosition = pos;
      this.partyParrot = isPartying;
   }

   public boolean isPartying() {
      return this.partyParrot;
   }

   private void calculateFlapping() {
      this.oFlap = this.flap;
      this.oFlapSpeed = this.flapSpeed;
      this.flapSpeed = (float)((double)this.flapSpeed + (double)(this.onGround ? -1 : 4) * 0.3);
      this.flapSpeed = MathHelper.clamp(this.flapSpeed, 0.0F, 1.0F);
      if (!this.onGround && this.flapping < 1.0F) {
         this.flapping = 1.0F;
      }

      this.flapping = (float)((double)this.flapping * 0.9);
      if (!this.onGround && this.motionY < 0.0) {
         this.motionY *= 0.6;
      }

      this.flap += this.flapping * 2.0F;
   }

   private static boolean playMimicSound(bij worldIn, Ig parrotIn) {
      if (!parrotIn.isSilent() && worldIn.rand.nextInt(50) == 0) {
         List<Iu> list = worldIn.getEntitiesWithinAABB(Iu.class, parrotIn.getEntityBoundingBox().grow(20.0), CAN_MIMIC);
         if (!list.isEmpty()) {
            Iu entityliving = (Iu)list.get(worldIn.rand.nextInt(list.size()));
            if (!entityliving.isSilent()) {
               SoundEvent soundevent = getImitatedSound(Ir.REGISTRY.getIDForObject(entityliving.getClass()));
               worldIn.playSound((ME)null, parrotIn.posX, parrotIn.posY, parrotIn.posZ, soundevent, parrotIn.getSoundCategory(), 0.7F, getPitch(worldIn.rand));
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   public boolean processInteract(ME player, EnumHand hand) {
      Qy itemstack = player.getHeldItem(hand);
      if (!this.isTamed() && TAME_ITEMS.contains(itemstack.getItem())) {
         if (!player.capabilities.isCreativeMode) {
            itemstack.shrink(1);
         }

         if (!this.isSilent()) {
            this.world.playSound((ME)null, this.posX, this.posY, this.posZ, NO.ENTITY_PARROT_EAT, this.getSoundCategory(), 1.0F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
         }

         if (!this.world.isRemote) {
            if (this.rand.nextInt(10) == 0) {
               this.setTamedBy(player);
               this.playTameEffect(true);
               this.world.setEntityState(this, (byte)7);
            } else {
               this.playTameEffect(false);
               this.world.setEntityState(this, (byte)6);
            }
         }

         return true;
      } else if (itemstack.getItem() == DEADLY_ITEM) {
         if (!player.capabilities.isCreativeMode) {
            itemstack.shrink(1);
         }

         this.addPotionEffect(new VZ(NL.POISON, 900));
         if (player.isCreative() || !this.getIsInvulnerable()) {
            this.attackEntityFrom(DamageSource.causePlayerDamage(player), Float.MAX_VALUE);
         }

         return true;
      } else {
         if (!this.world.isRemote && !this.isFlying() && this.isTamed() && this.isOwner(player)) {
            this.aiSit.setSitting(!this.isSitting());
         }

         return super.processInteract(player, hand);
      }
   }

   public boolean isBreedingItem(Qy stack) {
      return false;
   }

   public boolean getCanSpawnHere() {
      int i = MathHelper.floor(this.posX);
      int j = MathHelper.floor(this.getEntityBoundingBox().minY);
      int k = MathHelper.floor(this.posZ);
      BlockPos blockpos = new BlockPos(i, j, k);
      co block = this.world.getBlockState(blockpos.down()).getBlock();
      return block instanceof ew || block == Nk.GRASS || block instanceof eE || block == Nk.AIR && this.world.getLight(blockpos) > 8 && super.getCanSpawnHere();
   }

   public void fall(float distance, float damageMultiplier) {
   }

   protected void updateFallState(double y, boolean onGroundIn, in state, BlockPos pos) {
   }

   public boolean canMateWith(Ly otherAnimal) {
      return false;
   }

   @Nullable
   public Ih createChild(Ih ageable) {
      return null;
   }

   public static void playAmbientSound(bij worldIn, Ig parrotIn) {
      if (!parrotIn.isSilent() && !playMimicSound(worldIn, parrotIn) && worldIn.rand.nextInt(200) == 0) {
         worldIn.playSound((ME)null, parrotIn.posX, parrotIn.posY, parrotIn.posZ, getAmbientSound(worldIn.rand), parrotIn.getSoundCategory(), 1.0F, getPitch(worldIn.rand));
      }

   }

   public boolean attackEntityAsMob(Ig entityIn) {
      return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0F);
   }

   @Nullable
   public SoundEvent getAmbientSound() {
      return getAmbientSound(this.rand);
   }

   private static SoundEvent getAmbientSound(Random random) {
      if (random.nextInt(1000) == 0) {
         List<Integer> list = new ArrayList(IMITATION_SOUND_EVENTS.keySet());
         return getImitatedSound((Integer)list.get(random.nextInt(list.size())));
      } else {
         return NO.ENTITY_PARROT_AMBIENT;
      }
   }

   public static SoundEvent getImitatedSound(int p_191999_0_) {
      return IMITATION_SOUND_EVENTS.containsKey(p_191999_0_) ? (SoundEvent)IMITATION_SOUND_EVENTS.get(p_191999_0_) : NO.ENTITY_PARROT_AMBIENT;
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return NO.ENTITY_PARROT_HURT;
   }

   protected SoundEvent getDeathSound() {
      return NO.ENTITY_PARROT_DEATH;
   }

   protected void playStepSound(BlockPos pos, co blockIn) {
      this.playSound(NO.ENTITY_PARROT_STEP, 0.15F, 1.0F);
   }

   protected float playFlySound(float p_191954_1_) {
      this.playSound(NO.ENTITY_PARROT_FLY, 0.15F, 1.0F);
      return p_191954_1_ + this.flapSpeed / 2.0F;
   }

   protected boolean makeFlySound() {
      return true;
   }

   protected float getSoundPitch() {
      return getPitch(this.rand);
   }

   private static float getPitch(Random random) {
      return (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F;
   }

   public SoundCategory getSoundCategory() {
      return SoundCategory.NEUTRAL;
   }

   public boolean canBePushed() {
      return true;
   }

   protected void collideWithEntity(Ig entityIn) {
      if (!(entityIn instanceof ME)) {
         super.collideWithEntity(entityIn);
      }

   }

   public boolean attackEntityFrom(DamageSource source, float amount) {
      if (this.isEntityInvulnerable(source)) {
         return false;
      } else {
         if (this.aiSit != null) {
            this.aiSit.setSitting(false);
         }

         return super.attackEntityFrom(source, amount);
      }
   }

   public int getVariant() {
      return MathHelper.clamp((Integer)this.dataManager.get(VARIANT), 0, 4);
   }

   public void setVariant(int variantIn) {
      this.dataManager.set(VARIANT, variantIn);
   }

   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(VARIANT, 0);
   }

   public void writeEntityToNBT(QQ compound) {
      super.writeEntityToNBT(compound);
      compound.setInteger("Variant", this.getVariant());
   }

   public void readEntityFromNBT(QQ compound) {
      super.readEntityFromNBT(compound);
      this.setVariant(compound.getInteger("Variant"));
   }

   @Nullable
   protected ResourceLocation getLootTable() {
      return bhq.ENTITIES_PARROT;
   }

   public boolean isFlying() {
      return !this.onGround;
   }

   static {
      VARIANT = Rv.createKey(LP.class, Rt.VARINT);
      CAN_MIMIC = new Predicate<Iu>() {
         public boolean apply(@Nullable Iu p_apply_1_) {
            return p_apply_1_ != null && LP.IMITATION_SOUND_EVENTS.containsKey(Ir.REGISTRY.getIDForObject(p_apply_1_.getClass()));
         }

         // $FF: synthetic method
         // $FF: bridge method
         public boolean apply(@Nullable Object var1) {
            return this.apply((Iu)var1);
         }
      };
      DEADLY_ITEM = NK.COOKIE;
      TAME_ITEMS = Sets.newHashSet(new OL[]{NK.WHEAT_SEEDS, NK.MELON_SEEDS, NK.PUMPKIN_SEEDS, NK.BEETROOT_SEEDS});
      IMITATION_SOUND_EVENTS = new Int2ObjectOpenHashMap(32);
      IMITATION_SOUND_EVENTS.put(Ir.REGISTRY.getIDForObject(Jz.class), NO.E_PARROT_IM_BLAZE);
      IMITATION_SOUND_EVENTS.put(Ir.REGISTRY.getIDForObject(JA.class), NO.E_PARROT_IM_SPIDER);
      IMITATION_SOUND_EVENTS.put(Ir.REGISTRY.getIDForObject(JB.class), NO.E_PARROT_IM_CREEPER);
      IMITATION_SOUND_EVENTS.put(Ir.REGISTRY.getIDForObject(JD.class), NO.E_PARROT_IM_ELDER_GUARDIAN);
      IMITATION_SOUND_EVENTS.put(Ir.REGISTRY.getIDForObject(HS.class), NO.E_PARROT_IM_ENDERDRAGON);
      IMITATION_SOUND_EVENTS.put(Ir.REGISTRY.getIDForObject(JJ.class), NO.E_PARROT_IM_ENDERMAN);
      IMITATION_SOUND_EVENTS.put(Ir.REGISTRY.getIDForObject(JK.class), NO.E_PARROT_IM_ENDERMITE);
      IMITATION_SOUND_EVENTS.put(Ir.REGISTRY.getIDForObject(JR.class), NO.E_PARROT_IM_EVOCATION_ILLAGER);
      IMITATION_SOUND_EVENTS.put(Ir.REGISTRY.getIDForObject(JW.class), NO.E_PARROT_IM_GHAST);
      IMITATION_SOUND_EVENTS.put(Ir.REGISTRY.getIDForObject(Kd.class), NO.E_PARROT_IM_HUSK);
      IMITATION_SOUND_EVENTS.put(Ir.REGISTRY.getIDForObject(Kh.class), NO.E_PARROT_IM_ILLUSION_ILLAGER);
      IMITATION_SOUND_EVENTS.put(Ir.REGISTRY.getIDForObject(Kk.class), NO.E_PARROT_IM_MAGMACUBE);
      IMITATION_SOUND_EVENTS.put(Ir.REGISTRY.getIDForObject(Ko.class), NO.E_PARROT_IM_ZOMBIE_PIGMAN);
      IMITATION_SOUND_EVENTS.put(Ir.REGISTRY.getIDForObject(Kv.class), NO.E_PARROT_IM_POLAR_BEAR);
      IMITATION_SOUND_EVENTS.put(Ir.REGISTRY.getIDForObject(KD.class), NO.E_PARROT_IM_SHULKER);
      IMITATION_SOUND_EVENTS.put(Ir.REGISTRY.getIDForObject(KG.class), NO.E_PARROT_IM_SILVERFISH);
      IMITATION_SOUND_EVENTS.put(Ir.REGISTRY.getIDForObject(KH.class), NO.E_PARROT_IM_SKELETON);
      IMITATION_SOUND_EVENTS.put(Ir.REGISTRY.getIDForObject(KN.class), NO.E_PARROT_IM_SLIME);
      IMITATION_SOUND_EVENTS.put(Ir.REGISTRY.getIDForObject(KW.class), NO.E_PARROT_IM_SPIDER);
      IMITATION_SOUND_EVENTS.put(Ir.REGISTRY.getIDForObject(KX.class), NO.E_PARROT_IM_STRAY);
      IMITATION_SOUND_EVENTS.put(Ir.REGISTRY.getIDForObject(Lc.class), NO.E_PARROT_IM_VEX);
      IMITATION_SOUND_EVENTS.put(Ir.REGISTRY.getIDForObject(Lf.class), NO.E_PARROT_IM_VINDICATION_ILLAGER);
      IMITATION_SOUND_EVENTS.put(Ir.REGISTRY.getIDForObject(Lg.class), NO.E_PARROT_IM_WITCH);
      IMITATION_SOUND_EVENTS.put(Ir.REGISTRY.getIDForObject(HV.class), NO.E_PARROT_IM_WITHER);
      IMITATION_SOUND_EVENTS.put(Ir.REGISTRY.getIDForObject(Lh.class), NO.E_PARROT_IM_WITHER_SKELETON);
      IMITATION_SOUND_EVENTS.put(Ir.REGISTRY.getIDForObject(Mu.class), NO.E_PARROT_IM_WOLF);
      IMITATION_SOUND_EVENTS.put(Ir.REGISTRY.getIDForObject(Lk.class), NO.E_PARROT_IM_ZOMBIE);
      IMITATION_SOUND_EVENTS.put(Ir.REGISTRY.getIDForObject(Ll.class), NO.E_PARROT_IM_ZOMBIE_VILLAGER);
   }
}
