package neo;

import com.google.common.collect.Multimap;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.math.BlockPos;

public class Qz extends OL {
   private final float attackDamage;
   private final OK material;

   public Qz(OK material) {
      this.material = material;
      this.maxStackSize = 1;
      this.setMaxDamage(material.getMaxUses());
      this.setCreativeTab(EN.COMBAT);
      this.attackDamage = 3.0F + material.getAttackDamage();
   }

   public float getAttackDamage() {
      return this.material.getAttackDamage();
   }

   public float getDestroySpeed(Qy stack, in state) {
      co block = state.getBlock();
      if (block == Nk.WEB) {
         return 15.0F;
      } else {
         hM material = state.getMaterial();
         return material != hM.PLANTS && material != hM.VINE && material != hM.CORAL && material != hM.LEAVES && material != hM.GOURD ? 1.0F : 1.5F;
      }
   }

   public boolean hitEntity(Qy stack, Iw target, Iw attacker) {
      stack.damageItem(1, attacker);
      return true;
   }

   public boolean onBlockDestroyed(Qy stack, bij worldIn, in state, BlockPos pos, Iw entityLiving) {
      if ((double)state.getBlockHardness(worldIn, pos) != 0.0) {
         stack.damageItem(2, entityLiving);
      }

      return true;
   }

   public boolean isFull3D() {
      return true;
   }

   public boolean canHarvestBlock(in blockIn) {
      return blockIn.getBlock() == Nk.WEB;
   }

   public int getItemEnchantability() {
      return this.material.getEnchantability();
   }

   public String getToolMaterialName() {
      return this.material.toString();
   }

   public boolean getIsRepairable(Qy toRepair, Qy repair) {
      return this.material.getRepairItem() == repair.getItem() ? true : super.getIsRepairable(toRepair, repair);
   }

   public Multimap<String, FW> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
      Multimap<String, FW> multimap = super.getItemAttributeModifiers(equipmentSlot);
      if (equipmentSlot == EntityEquipmentSlot.MAINHAND) {
         multimap.put(Ni.ATTACK_DAMAGE.getName(), new FW(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double)this.attackDamage, 0));
         multimap.put(Ni.ATTACK_SPEED.getName(), new FW(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.4000000953674316, 0));
      }

      return multimap;
   }
}
