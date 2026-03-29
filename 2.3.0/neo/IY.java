package neo;

import java.util.Iterator;
import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackData;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.translation.I18n;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IY extends Ig {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final Rd<Qy> ITEM;
   private int age;
   private int pickupDelay;
   private int health;
   private String thrower;
   private String owner;
   public float hoverStart;

   public IY(bij worldIn, double x, double y, double z) {
      super(worldIn);
      this.health = 5;
      this.hoverStart = (float)(Math.random() * Math.PI * 2.0);
      this.setSize(0.25F, 0.25F);
      this.setPosition(x, y, z);
      this.rotationYaw = (float)(Math.random() * 360.0);
      this.motionX = (double)((float)(Math.random() * 0.20000000298023224 - 0.10000000149011612));
      this.motionY = 0.20000000298023224;
      this.motionZ = (double)((float)(Math.random() * 0.20000000298023224 - 0.10000000149011612));
   }

   public IY(bij worldIn, double x, double y, double z, Qy stack) {
      this(worldIn, x, y, z);
      this.setItem(stack);
   }

   protected boolean canTriggerWalking() {
      return false;
   }

   public IY(bij worldIn) {
      super(worldIn);
      this.health = 5;
      this.hoverStart = (float)(Math.random() * Math.PI * 2.0);
      this.setSize(0.25F, 0.25F);
      this.setItem(Qy.EMPTY);
   }

   protected void entityInit() {
      this.getDataManager().register(ITEM, Qy.EMPTY);
   }

   public void onUpdate() {
      if (this.getItem().isEmpty()) {
         this.setDead();
      } else {
         super.onUpdate();
         if (this.pickupDelay > 0 && this.pickupDelay != 32767) {
            --this.pickupDelay;
         }

         this.prevPosX = this.posX;
         this.prevPosY = this.posY;
         this.prevPosZ = this.posZ;
         double d0 = this.motionX;
         double d1 = this.motionY;
         double d2 = this.motionZ;
         if (!this.hasNoGravity()) {
            this.motionY -= 0.03999999910593033;
         }

         if (this.world.isRemote) {
            this.noClip = false;
         } else {
            this.noClip = this.pushOutOfBlocks(this.posX, (this.getEntityBoundingBox().minY + this.getEntityBoundingBox().maxY) / 2.0, this.posZ);
         }

         this.move(Lq.SELF, this.motionX, this.motionY, this.motionZ);
         boolean flag = (int)this.prevPosX != (int)this.posX || (int)this.prevPosY != (int)this.posY || (int)this.prevPosZ != (int)this.posZ;
         if (flag || this.ticksExisted % 25 == 0) {
            if (this.world.getBlockState(new BlockPos(this)).getMaterial() == hM.LAVA) {
               this.motionY = 0.20000000298023224;
               this.motionX = (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
               this.motionZ = (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
               this.playSound(NO.ENTITY_GENERIC_BURN, 0.4F, 2.0F + this.rand.nextFloat() * 0.4F);
            }

            if (!this.world.isRemote) {
               this.searchForOtherItemsNearby();
            }
         }

         float f = 0.98F;
         if (this.onGround) {
            f = this.world.getBlockState(new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.getEntityBoundingBox().minY) - 1, MathHelper.floor(this.posZ))).getBlock().slipperiness * 0.98F;
         }

         this.motionX *= (double)f;
         this.motionY *= 0.9800000190734863;
         this.motionZ *= (double)f;
         if (this.onGround) {
            this.motionY *= -0.5;
         }

         if (this.age != -32768) {
            ++this.age;
         }

         this.handleWaterMovement();
         if (!this.world.isRemote) {
            double d3 = this.motionX - d0;
            double d4 = this.motionY - d1;
            double d5 = this.motionZ - d2;
            double d6 = d3 * d3 + d4 * d4 + d5 * d5;
            if (d6 > 0.01) {
               this.isAirBorne = true;
            }
         }

         if (!this.world.isRemote && this.age >= 6000) {
            this.setDead();
         }
      }

   }

   private void searchForOtherItemsNearby() {
      Iterator var1 = this.world.getEntitiesWithinAABB(IY.class, this.getEntityBoundingBox().grow(0.5, 0.0, 0.5)).iterator();

      while(var1.hasNext()) {
         IY entityitem = (IY)var1.next();
         this.combineItems(entityitem);
      }

   }

   private boolean combineItems(IY other) {
      if (other == this) {
         return false;
      } else if (other.isEntityAlive() && this.isEntityAlive()) {
         Qy itemstack = this.getItem();
         Qy itemstack1 = other.getItem();
         if (this.pickupDelay != 32767 && other.pickupDelay != 32767) {
            if (this.age != -32768 && other.age != -32768) {
               if (itemstack1.getItem() != itemstack.getItem()) {
                  return false;
               } else if (itemstack1.hasTagCompound() ^ itemstack.hasTagCompound()) {
                  return false;
               } else if (itemstack1.hasTagCompound() && !itemstack1.getTagCompound().equals(itemstack.getTagCompound())) {
                  return false;
               } else if (itemstack1.getItem() == null) {
                  return false;
               } else if (itemstack1.getItem().getHasSubtypes() && itemstack1.getMetadata() != itemstack.getMetadata()) {
                  return false;
               } else if (itemstack1.getCount() < itemstack.getCount()) {
                  return other.combineItems(this);
               } else if (itemstack1.getCount() + itemstack.getCount() > itemstack1.getMaxStackSize()) {
                  return false;
               } else {
                  itemstack1.grow(itemstack.getCount());
                  other.pickupDelay = Math.max(other.pickupDelay, this.pickupDelay);
                  other.age = Math.min(other.age, this.age);
                  other.setItem(itemstack1);
                  this.setDead();
                  return true;
               }
            } else {
               return false;
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public void setAgeToCreativeDespawnTime() {
      this.age = 4800;
   }

   public boolean handleWaterMovement() {
      if (this.world.handleMaterialAcceleration(this.getEntityBoundingBox(), hM.WATER, this)) {
         if (!this.inWater && !this.firstUpdate) {
            this.doWaterSplashEffect();
         }

         this.inWater = true;
      } else {
         this.inWater = false;
      }

      return this.inWater;
   }

   protected void dealFireDamage(int amount) {
      this.attackEntityFrom(DamageSource.IN_FIRE, (float)amount);
   }

   public boolean attackEntityFrom(DamageSource source, float amount) {
      if (this.isEntityInvulnerable(source)) {
         return false;
      } else if (!this.getItem().isEmpty() && this.getItem().getItem() == NK.NETHER_STAR && source.isExplosion()) {
         return false;
      } else {
         this.markVelocityChanged();
         this.health = (int)((float)this.health - amount);
         if (this.health <= 0) {
            this.setDead();
         }

         return false;
      }
   }

   public static void registerFixesItem(DataFixer fixer) {
      fixer.registerWalker(FixTypes.ENTITY, new ItemStackData(IY.class, new String[]{"Item"}));
   }

   public void writeEntityToNBT(QQ compound) {
      compound.setShort("Health", (short)this.health);
      compound.setShort("Age", (short)this.age);
      compound.setShort("PickupDelay", (short)this.pickupDelay);
      if (this.getThrower() != null) {
         compound.setString("Thrower", this.thrower);
      }

      if (this.getOwner() != null) {
         compound.setString("Owner", this.owner);
      }

      if (!this.getItem().isEmpty()) {
         compound.setTag("Item", this.getItem().writeToNBT(new QQ()));
      }

   }

   public void readEntityFromNBT(QQ compound) {
      this.health = compound.getShort("Health");
      this.age = compound.getShort("Age");
      if (compound.hasKey("PickupDelay")) {
         this.pickupDelay = compound.getShort("PickupDelay");
      }

      if (compound.hasKey("Owner")) {
         this.owner = compound.getString("Owner");
      }

      if (compound.hasKey("Thrower")) {
         this.thrower = compound.getString("Thrower");
      }

      QQ nbttagcompound = compound.getCompoundTag("Item");
      this.setItem(new Qy(nbttagcompound));
      if (this.getItem().isEmpty()) {
         this.setDead();
      }

   }

   public void onCollideWithPlayer(ME entityIn) {
      if (!this.world.isRemote) {
         Qy itemstack = this.getItem();
         OL item = itemstack.getItem();
         int i = itemstack.getCount();
         if (this.pickupDelay == 0 && (this.owner == null || 6000 - this.age <= 200 || this.owner.equals(entityIn.getName())) && entityIn.inventory.addItemStackToInventory(itemstack)) {
            entityIn.onItemPickup(this, i);
            if (itemstack.isEmpty()) {
               this.setDead();
               itemstack.setCount(i);
            }

            entityIn.addStat(XV.getObjectsPickedUpStats(item), i);
         }
      }

   }

   public String getName() {
      return this.hasCustomName() ? this.getCustomNameTag() : I18n.translateToLocal("item." + this.getItem().getTranslationKey());
   }

   public boolean canBeAttackedWithItem() {
      return false;
   }

   @Nullable
   public Ig changeDimension(int dimensionIn) {
      Ig entity = super.changeDimension(dimensionIn);
      if (!this.world.isRemote && entity instanceof IY) {
         ((IY)entity).searchForOtherItemsNearby();
      }

      return entity;
   }

   public Qy getItem() {
      return (Qy)this.getDataManager().get(ITEM);
   }

   public void setItem(Qy stack) {
      this.getDataManager().set(ITEM, stack);
      this.getDataManager().setDirty(ITEM);
   }

   public String getOwner() {
      return this.owner;
   }

   public void setOwner(String owner) {
      this.owner = owner;
   }

   public String getThrower() {
      return this.thrower;
   }

   public void setThrower(String thrower) {
      this.thrower = thrower;
   }

   public int getAge() {
      return this.age;
   }

   public void setDefaultPickupDelay() {
      this.pickupDelay = 10;
   }

   public void setNoPickupDelay() {
      this.pickupDelay = 0;
   }

   public void setInfinitePickupDelay() {
      this.pickupDelay = 32767;
   }

   public void setPickupDelay(int ticks) {
      this.pickupDelay = ticks;
   }

   public boolean cannotPickup() {
      return this.pickupDelay > 0;
   }

   public void setNoDespawn() {
      this.age = -6000;
   }

   public void makeFakeItem() {
      this.setInfinitePickupDelay();
      this.age = 5999;
   }

   static {
      ITEM = Rv.createKey(IY.class, Rt.ITEM_STACK);
   }
}
