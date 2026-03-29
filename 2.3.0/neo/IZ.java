package neo;

import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackData;
import net.minecraft.util.math.BlockPos;

public class IZ extends Io {
   private static final Rd<Qy> ITEM;
   private static final Rd<Integer> ROTATION;
   private float itemDropChance = 1.0F;

   public IZ(bij worldIn) {
      super(worldIn);
   }

   public IZ(bij worldIn, BlockPos p_i45852_2_, EnumFacing p_i45852_3_) {
      super(worldIn, p_i45852_2_);
      this.updateFacingWithBoundingBox(p_i45852_3_);
   }

   protected void entityInit() {
      this.getDataManager().register(ITEM, Qy.EMPTY);
      this.getDataManager().register(ROTATION, 0);
   }

   public float getCollisionBorderSize() {
      return 0.0F;
   }

   public boolean attackEntityFrom(DamageSource source, float amount) {
      if (this.isEntityInvulnerable(source)) {
         return false;
      } else if (!source.isExplosion() && !this.getDisplayedItem().isEmpty()) {
         if (!this.world.isRemote) {
            this.dropItemOrSelf(source.getTrueSource(), false);
            this.playSound(NO.ENTITY_ITEMFRAME_REMOVE_ITEM, 1.0F, 1.0F);
            this.setDisplayedItem(Qy.EMPTY);
         }

         return true;
      } else {
         return super.attackEntityFrom(source, amount);
      }
   }

   public int getWidthPixels() {
      return 12;
   }

   public int getHeightPixels() {
      return 12;
   }

   public boolean isInRangeToRenderDist(double distance) {
      double d0 = 16.0;
      d0 = d0 * 64.0 * getRenderDistanceWeight();
      return distance < d0 * d0;
   }

   public void onBroken(@Nullable Ig brokenEntity) {
      this.playSound(NO.ENTITY_ITEMFRAME_BREAK, 1.0F, 1.0F);
      this.dropItemOrSelf(brokenEntity, true);
   }

   public void playPlaceSound() {
      this.playSound(NO.ENTITY_ITEMFRAME_PLACE, 1.0F, 1.0F);
   }

   public void dropItemOrSelf(@Nullable Ig entityIn, boolean p_146065_2_) {
      if (this.world.getGameRules().getBoolean("doEntityDrops")) {
         Qy itemstack = this.getDisplayedItem();
         if (entityIn instanceof ME) {
            ME entityplayer = (ME)entityIn;
            if (entityplayer.capabilities.isCreativeMode) {
               this.removeFrameFromMap(itemstack);
               return;
            }
         }

         if (p_146065_2_) {
            this.entityDropItem(new Qy(NK.ITEM_FRAME), 0.0F);
         }

         if (!itemstack.isEmpty() && this.rand.nextFloat() < this.itemDropChance) {
            itemstack = itemstack.copy();
            this.removeFrameFromMap(itemstack);
            this.entityDropItem(itemstack, 0.0F);
         }
      }

   }

   private void removeFrameFromMap(Qy stack) {
      if (!stack.isEmpty()) {
         if (stack.getItem() == NK.FILLED_MAP) {
            bhE mapdata = ((PT)stack.getItem()).getMapData(stack, this.world);
            mapdata.mapDecorations.remove("frame-" + this.getEntityId());
         }

         stack.setItemFrame((IZ)null);
      }

   }

   public Qy getDisplayedItem() {
      return (Qy)this.getDataManager().get(ITEM);
   }

   public void setDisplayedItem(Qy stack) {
      this.setDisplayedItemWithUpdate(stack, true);
   }

   private void setDisplayedItemWithUpdate(Qy stack, boolean p_174864_2_) {
      if (!stack.isEmpty()) {
         stack = stack.copy();
         stack.setCount(1);
         stack.setItemFrame(this);
      }

      this.getDataManager().set(ITEM, stack);
      this.getDataManager().setDirty(ITEM);
      if (!stack.isEmpty()) {
         this.playSound(NO.ENTITY_ITEMFRAME_ADD_ITEM, 1.0F, 1.0F);
      }

      if (p_174864_2_ && this.hangingPosition != null) {
         this.world.updateComparatorOutputLevel(this.hangingPosition, Nk.AIR);
      }

   }

   public void notifyDataManagerChange(Rd<?> key) {
      if (key.equals(ITEM)) {
         Qy itemstack = this.getDisplayedItem();
         if (!itemstack.isEmpty() && itemstack.getItemFrame() != this) {
            itemstack.setItemFrame(this);
         }
      }

   }

   public int getRotation() {
      return (Integer)this.getDataManager().get(ROTATION);
   }

   public void setItemRotation(int rotationIn) {
      this.setRotation(rotationIn, true);
   }

   private void setRotation(int rotationIn, boolean p_174865_2_) {
      this.getDataManager().set(ROTATION, rotationIn % 8);
      if (p_174865_2_ && this.hangingPosition != null) {
         this.world.updateComparatorOutputLevel(this.hangingPosition, Nk.AIR);
      }

   }

   public static void registerFixesItemFrame(DataFixer fixer) {
      fixer.registerWalker(FixTypes.ENTITY, new ItemStackData(IZ.class, new String[]{"Item"}));
   }

   public void writeEntityToNBT(QQ compound) {
      if (!this.getDisplayedItem().isEmpty()) {
         compound.setTag("Item", this.getDisplayedItem().writeToNBT(new QQ()));
         compound.setByte("ItemRotation", (byte)this.getRotation());
         compound.setFloat("ItemDropChance", this.itemDropChance);
      }

      super.writeEntityToNBT(compound);
   }

   public void readEntityFromNBT(QQ compound) {
      QQ nbttagcompound = compound.getCompoundTag("Item");
      if (nbttagcompound != null && !nbttagcompound.isEmpty()) {
         this.setDisplayedItemWithUpdate(new Qy(nbttagcompound), false);
         this.setRotation(compound.getByte("ItemRotation"), false);
         if (compound.hasKey("ItemDropChance", 99)) {
            this.itemDropChance = compound.getFloat("ItemDropChance");
         }
      }

      super.readEntityFromNBT(compound);
   }

   public boolean processInitialInteract(ME player, EnumHand hand) {
      Qy itemstack = player.getHeldItem(hand);
      if (!this.world.isRemote) {
         if (this.getDisplayedItem().isEmpty()) {
            if (!itemstack.isEmpty()) {
               this.setDisplayedItem(itemstack);
               if (!player.capabilities.isCreativeMode) {
                  itemstack.shrink(1);
               }
            }
         } else {
            this.playSound(NO.ENTITY_ITEMFRAME_ROTATE_ITEM, 1.0F, 1.0F);
            this.setItemRotation(this.getRotation() + 1);
         }
      }

      return true;
   }

   public int getAnalogOutput() {
      return this.getDisplayedItem().isEmpty() ? 0 : this.getRotation() % 8 + 1;
   }

   static {
      ITEM = Rv.createKey(IZ.class, Rt.ITEM_STACK);
      ROTATION = Rv.createKey(IZ.class, Rt.VARINT);
   }
}
