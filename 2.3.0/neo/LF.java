package neo;

import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackData;

public class LF extends Lw {
   private static final UUID ARMOR_MODIFIER_UUID = UUID.fromString("556E1665-8B10-40C8-8F9D-CF9B1667F295");
   private static final Rd<Integer> HORSE_VARIANT;
   private static final Rd<Integer> HORSE_ARMOR;
   private static final String[] HORSE_TEXTURES;
   private static final String[] HORSE_TEXTURES_ABBR;
   private static final String[] HORSE_MARKING_TEXTURES;
   private static final String[] HORSE_MARKING_TEXTURES_ABBR;
   private String texturePrefix;
   private final String[] horseTexturesArray = new String[3];

   public LF(bij worldIn) {
      super(worldIn);
   }

   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(HORSE_VARIANT, 0);
      this.dataManager.register(HORSE_ARMOR, Mw.NONE.getOrdinal());
   }

   public static void registerFixesHorse(DataFixer fixer) {
      Lw.registerFixesAbstractHorse(fixer, LF.class);
      fixer.registerWalker(FixTypes.ENTITY, new ItemStackData(LF.class, new String[]{"ArmorItem"}));
   }

   public void writeEntityToNBT(QQ compound) {
      super.writeEntityToNBT(compound);
      compound.setInteger("Variant", this.getHorseVariant());
      if (!this.horseChest.getStackInSlot(1).isEmpty()) {
         compound.setTag("ArmorItem", this.horseChest.getStackInSlot(1).writeToNBT(new QQ()));
      }

   }

   public void readEntityFromNBT(QQ compound) {
      super.readEntityFromNBT(compound);
      this.setHorseVariant(compound.getInteger("Variant"));
      if (compound.hasKey("ArmorItem", 10)) {
         Qy itemstack = new Qy(compound.getCompoundTag("ArmorItem"));
         if (!itemstack.isEmpty() && Mw.isHorseArmor(itemstack.getItem())) {
            this.horseChest.setInventorySlotContents(1, itemstack);
         }
      }

      this.updateHorseSlots();
   }

   public void setHorseVariant(int variant) {
      this.dataManager.set(HORSE_VARIANT, variant);
      this.resetTexturePrefix();
   }

   public int getHorseVariant() {
      return (Integer)this.dataManager.get(HORSE_VARIANT);
   }

   private void resetTexturePrefix() {
      this.texturePrefix = null;
   }

   private void setHorseTexturePaths() {
      int i = this.getHorseVariant();
      int j = (i & 255) % 7;
      int k = ((i & '\uff00') >> 8) % 5;
      Mw horsearmortype = this.getHorseArmorType();
      this.horseTexturesArray[0] = HORSE_TEXTURES[j];
      this.horseTexturesArray[1] = HORSE_MARKING_TEXTURES[k];
      this.horseTexturesArray[2] = horsearmortype.getTextureName();
      this.texturePrefix = "horse/" + HORSE_TEXTURES_ABBR[j] + HORSE_MARKING_TEXTURES_ABBR[k] + horsearmortype.getHash();
   }

   public String getHorseTexture() {
      if (this.texturePrefix == null) {
         this.setHorseTexturePaths();
      }

      return this.texturePrefix;
   }

   public String[] getVariantTexturePaths() {
      if (this.texturePrefix == null) {
         this.setHorseTexturePaths();
      }

      return this.horseTexturesArray;
   }

   protected void updateHorseSlots() {
      super.updateHorseSlots();
      this.setHorseArmorStack(this.horseChest.getStackInSlot(1));
   }

   public void setHorseArmorStack(Qy itemStackIn) {
      Mw horsearmortype = Mw.getByItemStack(itemStackIn);
      this.dataManager.set(HORSE_ARMOR, horsearmortype.getOrdinal());
      this.resetTexturePrefix();
      if (!this.world.isRemote) {
         this.getEntityAttribute(Ni.ARMOR).removeModifier(ARMOR_MODIFIER_UUID);
         int i = horsearmortype.getProtection();
         if (i != 0) {
            this.getEntityAttribute(Ni.ARMOR).applyModifier((new FW(ARMOR_MODIFIER_UUID, "Horse armor bonus", (double)i, 0)).setSaved(false));
         }
      }

   }

   public Mw getHorseArmorType() {
      return Mw.getByOrdinal((Integer)this.dataManager.get(HORSE_ARMOR));
   }

   public void onInventoryChanged(IInventory invBasic) {
      Mw horsearmortype = this.getHorseArmorType();
      super.onInventoryChanged(invBasic);
      Mw horsearmortype1 = this.getHorseArmorType();
      if (this.ticksExisted > 20 && horsearmortype != horsearmortype1 && horsearmortype1 != Mw.NONE) {
         this.playSound(NO.ENTITY_HORSE_ARMOR, 0.5F, 1.0F);
      }

   }

   protected void playGallopSound(ia p_190680_1_) {
      super.playGallopSound(p_190680_1_);
      if (this.rand.nextInt(10) == 0) {
         this.playSound(NO.ENTITY_HORSE_BREATHE, p_190680_1_.getVolume() * 0.6F, p_190680_1_.getPitch());
      }

   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(Ni.MAX_HEALTH).setBaseValue((double)this.getModifiedMaxHealth());
      this.getEntityAttribute(Ni.MOVEMENT_SPEED).setBaseValue(this.getModifiedMovementSpeed());
      this.getEntityAttribute(JUMP_STRENGTH).setBaseValue(this.getModifiedJumpStrength());
   }

   public void onUpdate() {
      super.onUpdate();
      if (this.world.isRemote && this.dataManager.isDirty()) {
         this.dataManager.setClean();
         this.resetTexturePrefix();
      }

   }

   protected SoundEvent getAmbientSound() {
      super.getAmbientSound();
      return NO.ENTITY_HORSE_AMBIENT;
   }

   protected SoundEvent getDeathSound() {
      super.getDeathSound();
      return NO.ENTITY_HORSE_DEATH;
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      super.getHurtSound(damageSourceIn);
      return NO.ENTITY_HORSE_HURT;
   }

   protected SoundEvent getAngrySound() {
      super.getAngrySound();
      return NO.ENTITY_HORSE_ANGRY;
   }

   protected ResourceLocation getLootTable() {
      return bhq.ENTITIES_HORSE;
   }

   public boolean processInteract(ME player, EnumHand hand) {
      Qy itemstack = player.getHeldItem(hand);
      boolean flag = !itemstack.isEmpty();
      if (flag && itemstack.getItem() == NK.SPAWN_EGG) {
         return super.processInteract(player, hand);
      } else {
         if (!this.isChild()) {
            if (this.isTame() && player.isSneaking()) {
               this.openGUI(player);
               return true;
            }

            if (this.isBeingRidden()) {
               return super.processInteract(player, hand);
            }
         }

         if (flag) {
            if (this.handleEating(player, itemstack)) {
               if (!player.capabilities.isCreativeMode) {
                  itemstack.shrink(1);
               }

               return true;
            }

            if (itemstack.interactWithEntity(player, this, hand)) {
               return true;
            }

            if (!this.isTame()) {
               this.makeMad();
               return true;
            }

            boolean flag1 = Mw.getByItemStack(itemstack) != Mw.NONE;
            boolean flag2 = !this.isChild() && !this.isHorseSaddled() && itemstack.getItem() == NK.SADDLE;
            if (flag1 || flag2) {
               this.openGUI(player);
               return true;
            }
         }

         if (this.isChild()) {
            return super.processInteract(player, hand);
         } else {
            this.mountTo(player);
            return true;
         }
      }
   }

   public boolean canMateWith(Ly otherAnimal) {
      if (otherAnimal == this) {
         return false;
      } else if (!(otherAnimal instanceof LC) && !(otherAnimal instanceof LF)) {
         return false;
      } else {
         return this.canMate() && ((Lw)otherAnimal).canMate();
      }
   }

   public Ih createChild(Ih ageable) {
      Object abstracthorse;
      if (ageable instanceof LC) {
         abstracthorse = new LM(this.world);
      } else {
         LF entityhorse = (LF)ageable;
         abstracthorse = new LF(this.world);
         int j = this.rand.nextInt(9);
         int i;
         if (j < 4) {
            i = this.getHorseVariant() & 255;
         } else if (j < 8) {
            i = entityhorse.getHorseVariant() & 255;
         } else {
            i = this.rand.nextInt(7);
         }

         int k = this.rand.nextInt(5);
         if (k < 2) {
            i |= this.getHorseVariant() & '\uff00';
         } else if (k < 4) {
            i |= entityhorse.getHorseVariant() & '\uff00';
         } else {
            i |= this.rand.nextInt(5) << 8 & '\uff00';
         }

         ((LF)abstracthorse).setHorseVariant(i);
      }

      this.setOffspringAttributes(ageable, (Lw)abstracthorse);
      return (Ih)abstracthorse;
   }

   public boolean wearsArmor() {
      return true;
   }

   public boolean isArmor(Qy stack) {
      return Mw.isHorseArmor(stack.getItem());
   }

   @Nullable
   public ID onInitialSpawn(baL difficulty, @Nullable ID livingdata) {
      ID livingdata = super.onInitialSpawn(difficulty, livingdata);
      int i;
      if (livingdata instanceof LE) {
         i = ((LE)livingdata).variant;
      } else {
         i = this.rand.nextInt(7);
         livingdata = new LE(i);
      }

      this.setHorseVariant(i | this.rand.nextInt(5) << 8);
      return (ID)livingdata;
   }

   static {
      HORSE_VARIANT = Rv.createKey(LF.class, Rt.VARINT);
      HORSE_ARMOR = Rv.createKey(LF.class, Rt.VARINT);
      HORSE_TEXTURES = new String[]{"textures/entity/horse/horse_white.png", "textures/entity/horse/horse_creamy.png", "textures/entity/horse/horse_chestnut.png", "textures/entity/horse/horse_brown.png", "textures/entity/horse/horse_black.png", "textures/entity/horse/horse_gray.png", "textures/entity/horse/horse_darkbrown.png"};
      HORSE_TEXTURES_ABBR = new String[]{"hwh", "hcr", "hch", "hbr", "hbl", "hgr", "hdb"};
      HORSE_MARKING_TEXTURES = new String[]{null, "textures/entity/horse/horse_markings_white.png", "textures/entity/horse/horse_markings_whitefield.png", "textures/entity/horse/horse_markings_whitedots.png", "textures/entity/horse/horse_markings_blackdots.png"};
      HORSE_MARKING_TEXTURES_ABBR = new String[]{"", "wo_", "wmo", "wdo", "bdo"};
   }
}
