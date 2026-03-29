package neo;

import com.google.common.collect.Multimap;
import java.util.Set;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.math.BlockPos;

public class QB extends OL {
   private final Set<co> effectiveBlocks;
   protected float efficiency;
   protected float attackDamage;
   protected float attackSpeed;
   protected OK toolMaterial;

   protected QB(float attackDamageIn, float attackSpeedIn, OK materialIn, Set<co> effectiveBlocksIn) {
      this.efficiency = 4.0F;
      this.toolMaterial = materialIn;
      this.effectiveBlocks = effectiveBlocksIn;
      this.maxStackSize = 1;
      this.setMaxDamage(materialIn.getMaxUses());
      this.efficiency = materialIn.getEfficiency();
      this.attackDamage = attackDamageIn + materialIn.getAttackDamage();
      this.attackSpeed = attackSpeedIn;
      this.setCreativeTab(EN.TOOLS);
   }

   protected QB(OK materialIn, Set<co> effectiveBlocksIn) {
      this(0.0F, 0.0F, materialIn, effectiveBlocksIn);
   }

   public float getDestroySpeed(Qy stack, in state) {
      return this.effectiveBlocks.contains(state.getBlock()) ? this.efficiency : 1.0F;
   }

   public boolean hitEntity(Qy stack, Iw target, Iw attacker) {
      stack.damageItem(2, attacker);
      return true;
   }

   public boolean onBlockDestroyed(Qy stack, bij worldIn, in state, BlockPos pos, Iw entityLiving) {
      if (!worldIn.isRemote && (double)state.getBlockHardness(worldIn, pos) != 0.0) {
         stack.damageItem(1, entityLiving);
      }

      return true;
   }

   public boolean isFull3D() {
      return true;
   }

   public int getItemEnchantability() {
      return this.toolMaterial.getEnchantability();
   }

   public String getToolMaterialName() {
      return this.toolMaterial.toString();
   }

   public boolean getIsRepairable(Qy toRepair, Qy repair) {
      return this.toolMaterial.getRepairItem() == repair.getItem() ? true : super.getIsRepairable(toRepair, repair);
   }

   public Multimap<String, FW> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
      Multimap<String, FW> multimap = super.getItemAttributeModifiers(equipmentSlot);
      if (equipmentSlot == EntityEquipmentSlot.MAINHAND) {
         multimap.put(Ni.ATTACK_DAMAGE.getName(), new FW(ATTACK_DAMAGE_MODIFIER, "Tool modifier", (double)this.attackDamage, 0));
         multimap.put(Ni.ATTACK_SPEED.getName(), new FW(ATTACK_SPEED_MODIFIER, "Tool modifier", (double)this.attackSpeed, 0));
      }

      return multimap;
   }
}
