package net.minecraft.inventory;

import java.util.Random;
import neo.IY;
import neo.Ig;
import neo.Qy;
import neo.bij;
import net.minecraft.util.math.BlockPos;

public class InventoryHelper {
   private static final Random RANDOM = new Random();

   public InventoryHelper() {
   }

   public static void dropInventoryItems(bij worldIn, BlockPos pos, IInventory inventory) {
      dropInventoryItems(worldIn, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), inventory);
   }

   public static void dropInventoryItems(bij worldIn, Ig entityAt, IInventory inventory) {
      dropInventoryItems(worldIn, entityAt.posX, entityAt.posY, entityAt.posZ, inventory);
   }

   private static void dropInventoryItems(bij worldIn, double x, double y, double z, IInventory inventory) {
      for(int i = 0; i < inventory.getSizeInventory(); ++i) {
         Qy itemstack = inventory.getStackInSlot(i);
         if (!itemstack.isEmpty()) {
            spawnItemStack(worldIn, x, y, z, itemstack);
         }
      }

   }

   public static void spawnItemStack(bij worldIn, double x, double y, double z, Qy stack) {
      float f = RANDOM.nextFloat() * 0.8F + 0.1F;
      float f1 = RANDOM.nextFloat() * 0.8F + 0.1F;
      float f2 = RANDOM.nextFloat() * 0.8F + 0.1F;

      while(!stack.isEmpty()) {
         IY entityitem = new IY(worldIn, x + (double)f, y + (double)f1, z + (double)f2, stack.splitStack(RANDOM.nextInt(21) + 10));
         float f3 = 0.05F;
         entityitem.motionX = RANDOM.nextGaussian() * 0.05000000074505806;
         entityitem.motionY = RANDOM.nextGaussian() * 0.05000000074505806 + 0.20000000298023224;
         entityitem.motionZ = RANDOM.nextGaussian() * 0.05000000074505806;
         worldIn.spawnEntity(entityitem);
      }

   }
}
