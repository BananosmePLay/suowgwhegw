package net.minecraft.entity.item;

import net.minecraft.block.BlockFurnace;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityMinecartFurnace extends EntityMinecart {
   private static final DataParameter<Boolean> POWERED;
   private int fuel;
   public double pushX;
   public double pushZ;

   public EntityMinecartFurnace(World worldIn) {
      super(worldIn);
   }

   public EntityMinecartFurnace(World worldIn, double x, double y, double z) {
      super(worldIn, x, y, z);
   }

   public static void registerFixesMinecartFurnace(DataFixer fixer) {
      EntityMinecart.registerFixesMinecart(fixer, EntityMinecartFurnace.class);
   }

   public EntityMinecart.Type getType() {
      return EntityMinecart.Type.FURNACE;
   }

   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(POWERED, false);
   }

   public void onUpdate() {
      super.onUpdate();
      if (this.fuel > 0) {
         --this.fuel;
      }

      if (this.fuel <= 0) {
         this.pushX = 0.0;
         this.pushZ = 0.0;
      }

      this.setMinecartPowered(this.fuel > 0);
      if (this.isMinecartPowered() && this.rand.nextInt(4) == 0) {
         this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX, this.posY + 0.8, this.posZ, 0.0, 0.0, 0.0);
      }

   }

   protected double getMaximumSpeed() {
      return 0.2;
   }

   public void killMinecart(DamageSource source) {
      super.killMinecart(source);
      if (!source.isExplosion() && this.world.getGameRules().getBoolean("doEntityDrops")) {
         this.entityDropItem(new ItemStack(Blocks.FURNACE, 1), 0.0F);
      }

   }

   protected void moveAlongTrack(BlockPos pos, IBlockState state) {
      super.moveAlongTrack(pos, state);
      double d0 = this.pushX * this.pushX + this.pushZ * this.pushZ;
      if (d0 > 1.0E-4 && this.motionX * this.motionX + this.motionZ * this.motionZ > 0.001) {
         d0 = (double)MathHelper.sqrt(d0);
         this.pushX /= d0;
         this.pushZ /= d0;
         if (this.pushX * this.motionX + this.pushZ * this.motionZ < 0.0) {
            this.pushX = 0.0;
            this.pushZ = 0.0;
         } else {
            double d1 = d0 / this.getMaximumSpeed();
            this.pushX *= d1;
            this.pushZ *= d1;
         }
      }

   }

   protected void applyDrag() {
      double d0 = this.pushX * this.pushX + this.pushZ * this.pushZ;
      if (d0 > 1.0E-4) {
         d0 = (double)MathHelper.sqrt(d0);
         this.pushX /= d0;
         this.pushZ /= d0;
         double d1 = 1.0;
         this.motionX *= 0.800000011920929;
         this.motionY *= 0.0;
         this.motionZ *= 0.800000011920929;
         this.motionX += this.pushX * 1.0;
         this.motionZ += this.pushZ * 1.0;
      } else {
         this.motionX *= 0.9800000190734863;
         this.motionY *= 0.0;
         this.motionZ *= 0.9800000190734863;
      }

      super.applyDrag();
   }

   public boolean processInitialInteract(EntityPlayer player, EnumHand hand) {
      ItemStack itemstack = player.getHeldItem(hand);
      if (itemstack.getItem() == Items.COAL && this.fuel + 3600 <= 32000) {
         if (!player.capabilities.isCreativeMode) {
            itemstack.shrink(1);
         }

         this.fuel += 3600;
      }

      this.pushX = this.posX - player.posX;
      this.pushZ = this.posZ - player.posZ;
      return true;
   }

   protected void writeEntityToNBT(NBTTagCompound compound) {
      super.writeEntityToNBT(compound);
      compound.setDouble("PushX", this.pushX);
      compound.setDouble("PushZ", this.pushZ);
      compound.setShort("Fuel", (short)this.fuel);
   }

   protected void readEntityFromNBT(NBTTagCompound compound) {
      super.readEntityFromNBT(compound);
      this.pushX = compound.getDouble("PushX");
      this.pushZ = compound.getDouble("PushZ");
      this.fuel = compound.getShort("Fuel");
   }

   protected boolean isMinecartPowered() {
      return (Boolean)this.dataManager.get(POWERED);
   }

   protected void setMinecartPowered(boolean p_94107_1_) {
      this.dataManager.set(POWERED, p_94107_1_);
   }

   public IBlockState getDefaultDisplayTile() {
      return (this.isMinecartPowered() ? Blocks.LIT_FURNACE : Blocks.FURNACE).getDefaultState().withProperty(BlockFurnace.FACING, EnumFacing.NORTH);
   }

   static {
      POWERED = EntityDataManager.createKey(EntityMinecartFurnace.class, DataSerializers.BOOLEAN);
   }
}
