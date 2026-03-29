package neo;

import com.google.common.collect.Multimap;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;

public class PN extends OL {
   private final float speed;
   protected OK toolMaterial;

   public PN(OK material) {
      this.toolMaterial = material;
      this.maxStackSize = 1;
      this.setMaxDamage(material.getMaxUses());
      this.setCreativeTab(EN.TOOLS);
      this.speed = material.getAttackDamage() + 1.0F;
   }

   public EnumActionResult onItemUse(ME player, bij worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      Qy itemstack = player.getHeldItem(hand);
      if (!player.canPlayerEdit(pos.offset(facing), facing, itemstack)) {
         return EnumActionResult.FAIL;
      } else {
         in iblockstate = worldIn.getBlockState(pos);
         co block = iblockstate.getBlock();
         if (facing != EnumFacing.DOWN && worldIn.getBlockState(pos.up()).getMaterial() == hM.AIR) {
            if (block == Nk.GRASS || block == Nk.GRASS_PATH) {
               this.setBlock(itemstack, player, worldIn, pos, Nk.FARMLAND.getDefaultState());
               return EnumActionResult.SUCCESS;
            }

            if (block == Nk.DIRT) {
               switch ((di)iblockstate.getValue(dj.VARIANT)) {
                  case DIRT:
                     this.setBlock(itemstack, player, worldIn, pos, Nk.FARMLAND.getDefaultState());
                     return EnumActionResult.SUCCESS;
                  case COARSE_DIRT:
                     this.setBlock(itemstack, player, worldIn, pos, Nk.DIRT.getDefaultState().withProperty(dj.VARIANT, di.DIRT));
                     return EnumActionResult.SUCCESS;
               }
            }
         }

         return EnumActionResult.PASS;
      }
   }

   public boolean hitEntity(Qy stack, Iw target, Iw attacker) {
      stack.damageItem(1, attacker);
      return true;
   }

   protected void setBlock(Qy stack, ME player, bij worldIn, BlockPos pos, in state) {
      worldIn.playSound(player, pos, NO.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
      if (!worldIn.isRemote) {
         worldIn.setBlockState(pos, state, 11);
         stack.damageItem(1, player);
      }

   }

   public boolean isFull3D() {
      return true;
   }

   public String getMaterialName() {
      return this.toolMaterial.toString();
   }

   public Multimap<String, FW> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
      Multimap<String, FW> multimap = super.getItemAttributeModifiers(equipmentSlot);
      if (equipmentSlot == EntityEquipmentSlot.MAINHAND) {
         multimap.put(Ni.ATTACK_DAMAGE.getName(), new FW(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 0.0, 0));
         multimap.put(Ni.ATTACK_SPEED.getName(), new FW(ATTACK_SPEED_MODIFIER, "Weapon modifier", (double)(this.speed - 4.0F), 0));
      }

      return multimap;
   }
}
