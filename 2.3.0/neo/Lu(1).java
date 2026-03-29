package neo;

import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;

public abstract class Lu extends Lw {
   private static final Rd<Boolean> DATA_ID_CHEST;

   public Lu(bij worldIn) {
      super(worldIn);
      this.canGallop = false;
   }

   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(DATA_ID_CHEST, false);
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(Ni.MAX_HEALTH).setBaseValue((double)this.getModifiedMaxHealth());
      this.getEntityAttribute(Ni.MOVEMENT_SPEED).setBaseValue(0.17499999701976776);
      this.getEntityAttribute(JUMP_STRENGTH).setBaseValue(0.5);
   }

   public boolean hasChest() {
      return (Boolean)this.dataManager.get(DATA_ID_CHEST);
   }

   public void setChested(boolean chested) {
      this.dataManager.set(DATA_ID_CHEST, chested);
   }

   protected int getInventorySize() {
      return this.hasChest() ? 17 : super.getInventorySize();
   }

   public double getMountedYOffset() {
      return super.getMountedYOffset() - 0.25;
   }

   protected SoundEvent getAngrySound() {
      super.getAngrySound();
      return NO.ENTITY_DONKEY_ANGRY;
   }

   public void onDeath(DamageSource cause) {
      super.onDeath(cause);
      if (this.hasChest()) {
         if (!this.world.isRemote) {
            this.dropItem(OL.getItemFromBlock(Nk.CHEST), 1);
         }

         this.setChested(false);
      }

   }

   public static void registerFixesAbstractChestHorse(DataFixer fixer, Class<?> entityClass) {
      Lw.registerFixesAbstractHorse(fixer, entityClass);
      fixer.registerWalker(FixTypes.ENTITY, new ItemStackDataLists(entityClass, new String[]{"Items"}));
   }

   public void writeEntityToNBT(QQ compound) {
      super.writeEntityToNBT(compound);
      compound.setBoolean("ChestedHorse", this.hasChest());
      if (this.hasChest()) {
         QW nbttaglist = new QW();

         for(int i = 2; i < this.horseChest.getSizeInventory(); ++i) {
            Qy itemstack = this.horseChest.getStackInSlot(i);
            if (!itemstack.isEmpty()) {
               QQ nbttagcompound = new QQ();
               nbttagcompound.setByte("Slot", (byte)i);
               itemstack.writeToNBT(nbttagcompound);
               nbttaglist.appendTag(nbttagcompound);
            }
         }

         compound.setTag("Items", nbttaglist);
      }

   }

   public void readEntityFromNBT(QQ compound) {
      super.readEntityFromNBT(compound);
      this.setChested(compound.getBoolean("ChestedHorse"));
      if (this.hasChest()) {
         QW nbttaglist = compound.getTagList("Items", 10);
         this.initHorseChest();

         for(int i = 0; i < nbttaglist.tagCount(); ++i) {
            QQ nbttagcompound = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound.getByte("Slot") & 255;
            if (j >= 2 && j < this.horseChest.getSizeInventory()) {
               this.horseChest.setInventorySlotContents(j, new Qy(nbttagcompound));
            }
         }
      }

      this.updateHorseSlots();
   }

   public boolean replaceItemInInventory(int inventorySlot, Qy itemStackIn) {
      if (inventorySlot == 499) {
         if (this.hasChest() && itemStackIn.isEmpty()) {
            this.setChested(false);
            this.initHorseChest();
            return true;
         }

         if (!this.hasChest() && itemStackIn.getItem() == OL.getItemFromBlock(Nk.CHEST)) {
            this.setChested(true);
            this.initHorseChest();
            return true;
         }
      }

      return super.replaceItemInInventory(inventorySlot, itemStackIn);
   }

   public boolean processInteract(ME player, EnumHand hand) {
      Qy itemstack = player.getHeldItem(hand);
      if (itemstack.getItem() == NK.SPAWN_EGG) {
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

         if (!itemstack.isEmpty()) {
            boolean flag = this.handleEating(player, itemstack);
            if (!flag && !this.isTame()) {
               if (itemstack.interactWithEntity(player, this, hand)) {
                  return true;
               }

               this.makeMad();
               return true;
            }

            if (!flag && !this.hasChest() && itemstack.getItem() == OL.getItemFromBlock(Nk.CHEST)) {
               this.setChested(true);
               this.playChestEquipSound();
               flag = true;
               this.initHorseChest();
            }

            if (!flag && !this.isChild() && !this.isHorseSaddled() && itemstack.getItem() == NK.SADDLE) {
               this.openGUI(player);
               return true;
            }

            if (flag) {
               if (!player.capabilities.isCreativeMode) {
                  itemstack.shrink(1);
               }

               return true;
            }
         }

         if (this.isChild()) {
            return super.processInteract(player, hand);
         } else if (itemstack.interactWithEntity(player, this, hand)) {
            return true;
         } else {
            this.mountTo(player);
            return true;
         }
      }
   }

   protected void playChestEquipSound() {
      this.playSound(NO.ENTITY_DONKEY_CHEST, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
   }

   public int getInventoryColumns() {
      return 5;
   }

   static {
      DATA_ID_CHEST = Rv.createKey(Lu.class, Rt.BOOLEAN);
   }
}
