package neo;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.datafix.DataFixer;

public class Jd extends Jh {
   public Jd(bij worldIn) {
      super(worldIn);
   }

   public Jd(bij worldIn, double x, double y, double z) {
      super(worldIn, x, y, z);
   }

   public static void registerFixesMinecartChest(DataFixer fixer) {
      Jh.addDataFixers(fixer, Jd.class);
   }

   public void killMinecart(DamageSource source) {
      super.killMinecart(source);
      if (this.world.getGameRules().getBoolean("doEntityDrops")) {
         this.dropItemWithOffset(OL.getItemFromBlock(Nk.CHEST), 1, 0.0F);
      }

   }

   public int getSizeInventory() {
      return 27;
   }

   public Jb getType() {
      return Jb.CHEST;
   }

   public in getDefaultDisplayTile() {
      return Nk.CHEST.getDefaultState().withProperty(cT.FACING, EnumFacing.NORTH);
   }

   public int getDefaultDisplayTileOffset() {
      return 8;
   }

   public String getGuiID() {
      return "minecraft:chest";
   }

   public Container createContainer(MJ playerInventory, ME playerIn) {
      this.addLoot(playerIn);
      return new ContainerChest(playerInventory, this, playerIn);
   }
}
