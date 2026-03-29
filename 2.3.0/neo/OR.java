package neo;

import com.google.common.base.Predicates;
import com.google.common.collect.Multimap;
import java.util.List;
import java.util.UUID;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class OR extends OL {
   private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
   private static final UUID[] ARMOR_MODIFIERS = new UUID[]{UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"), UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"), UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"), UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")};
   public static final String[] EMPTY_SLOT_NAMES = new String[]{"minecraft:items/empty_armor_slot_boots", "minecraft:items/empty_armor_slot_leggings", "minecraft:items/empty_armor_slot_chestplate", "minecraft:items/empty_armor_slot_helmet"};
   public static final ES DISPENSER_BEHAVIOR = new EP() {
      protected Qy dispenseStack(ET source, Qy stack) {
         Qy itemstack = OR.dispenseArmor(source, stack);
         return itemstack.isEmpty() ? super.dispenseStack(source, stack) : itemstack;
      }
   };
   public final EntityEquipmentSlot armorType;
   public final int damageReduceAmount;
   public final float toughness;
   public final int renderIndex;
   private final OQ material;

   public static Qy dispenseArmor(ET blockSource, Qy stack) {
      BlockPos blockpos = blockSource.getBlockPos().offset((EnumFacing)blockSource.getBlockState().getValue(dk.FACING));
      List<Iw> list = blockSource.getWorld().getEntitiesWithinAABB(Iw.class, new AxisAlignedBB(blockpos), Predicates.and(EntitySelectors.NOT_SPECTATING, new EntitySelectors.ArmoredMob(stack)));
      if (list.isEmpty()) {
         return Qy.EMPTY;
      } else {
         Iw entitylivingbase = (Iw)list.get(0);
         EntityEquipmentSlot entityequipmentslot = Iu.getSlotForItemStack(stack);
         Qy itemstack = stack.splitStack(1);
         entitylivingbase.setItemStackToSlot(entityequipmentslot, itemstack);
         if (entitylivingbase instanceof Iu) {
            ((Iu)entitylivingbase).setDropChance(entityequipmentslot, 2.0F);
         }

         return stack;
      }
   }

   public OR(OQ materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
      this.material = materialIn;
      this.armorType = equipmentSlotIn;
      this.renderIndex = renderIndexIn;
      this.damageReduceAmount = materialIn.getDamageReductionAmount(equipmentSlotIn);
      this.setMaxDamage(materialIn.getDurability(equipmentSlotIn));
      this.toughness = materialIn.getToughness();
      this.maxStackSize = 1;
      this.setCreativeTab(EN.COMBAT);
      dk.DISPENSE_BEHAVIOR_REGISTRY.putObject(this, DISPENSER_BEHAVIOR);
   }

   public EntityEquipmentSlot getEquipmentSlot() {
      return this.armorType;
   }

   public int getItemEnchantability() {
      return this.material.getEnchantability();
   }

   public OQ getArmorMaterial() {
      return this.material;
   }

   public boolean hasColor(Qy stack) {
      if (this.material != OQ.LEATHER) {
         return false;
      } else {
         QQ nbttagcompound = stack.getTagCompound();
         return nbttagcompound != null && nbttagcompound.hasKey("display", 10) ? nbttagcompound.getCompoundTag("display").hasKey("color", 3) : false;
      }
   }

   public int getColor(Qy stack) {
      if (this.material != OQ.LEATHER) {
         return 16777215;
      } else {
         QQ nbttagcompound = stack.getTagCompound();
         if (nbttagcompound != null) {
            QQ nbttagcompound1 = nbttagcompound.getCompoundTag("display");
            if (nbttagcompound1 != null && nbttagcompound1.hasKey("color", 3)) {
               return nbttagcompound1.getInteger("color");
            }
         }

         return 10511680;
      }
   }

   public void removeColor(Qy stack) {
      if (this.material == OQ.LEATHER) {
         QQ nbttagcompound = stack.getTagCompound();
         if (nbttagcompound != null) {
            QQ nbttagcompound1 = nbttagcompound.getCompoundTag("display");
            if (nbttagcompound1.hasKey("color")) {
               nbttagcompound1.removeTag("color");
            }
         }
      }

   }

   public void setColor(Qy stack, int color) {
      if (this.material != OQ.LEATHER) {
         throw new UnsupportedOperationException("Can't dye non-leather!");
      } else {
         QQ nbttagcompound = stack.getTagCompound();
         if (nbttagcompound == null) {
            nbttagcompound = new QQ();
            stack.setTagCompound(nbttagcompound);
         }

         QQ nbttagcompound1 = nbttagcompound.getCompoundTag("display");
         if (!nbttagcompound.hasKey("display", 10)) {
            nbttagcompound.setTag("display", nbttagcompound1);
         }

         nbttagcompound1.setInteger("color", color);
      }
   }

   public boolean getIsRepairable(Qy toRepair, Qy repair) {
      return this.material.getRepairItem() == repair.getItem() ? true : super.getIsRepairable(toRepair, repair);
   }

   public ActionResult<Qy> onItemRightClick(bij worldIn, ME playerIn, EnumHand handIn) {
      Qy itemstack = playerIn.getHeldItem(handIn);
      EntityEquipmentSlot entityequipmentslot = Iu.getSlotForItemStack(itemstack);
      Qy itemstack1 = playerIn.getItemStackFromSlot(entityequipmentslot);
      if (itemstack1.isEmpty()) {
         playerIn.setItemStackToSlot(entityequipmentslot, itemstack.copy());
         itemstack.setCount(0);
         return new ActionResult(EnumActionResult.SUCCESS, itemstack);
      } else {
         return new ActionResult(EnumActionResult.FAIL, itemstack);
      }
   }

   public Multimap<String, FW> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
      Multimap<String, FW> multimap = super.getItemAttributeModifiers(equipmentSlot);
      if (equipmentSlot == this.armorType) {
         multimap.put(Ni.ARMOR.getName(), new FW(ARMOR_MODIFIERS[equipmentSlot.getIndex()], "Armor modifier", (double)this.damageReduceAmount, 0));
         multimap.put(Ni.ARMOR_TOUGHNESS.getName(), new FW(ARMOR_MODIFIERS[equipmentSlot.getIndex()], "Armor toughness", (double)this.toughness, 0));
      }

      return multimap;
   }

   // $FF: synthetic method
   static int[] access$000() {
      return MAX_DAMAGE_ARRAY;
   }
}
