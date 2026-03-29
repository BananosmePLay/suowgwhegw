package neo;

import net.minecraft.inventory.EntityEquipmentSlot;

public class Fg extends Fa {
   private static final String[] DAMAGE_NAMES = new String[]{"all", "undead", "arthropods"};
   private static final int[] MIN_COST = new int[]{1, 5, 5};
   private static final int[] LEVEL_COST = new int[]{11, 8, 8};
   private static final int[] LEVEL_COST_SPAN = new int[]{20, 20, 20};
   public final int damageType;

   public Fg(EZ rarityIn, int damageTypeIn, EntityEquipmentSlot... slots) {
      super(rarityIn, FS.WEAPON, slots);
      this.damageType = damageTypeIn;
   }

   public int getMinEnchantability(int enchantmentLevel) {
      return MIN_COST[this.damageType] + (enchantmentLevel - 1) * LEVEL_COST[this.damageType];
   }

   public int getMaxEnchantability(int enchantmentLevel) {
      return this.getMinEnchantability(enchantmentLevel) + LEVEL_COST_SPAN[this.damageType];
   }

   public int getMaxLevel() {
      return 5;
   }

   public float calcDamageByCreature(int level, IB creatureType) {
      if (this.damageType == 0) {
         return 1.0F + (float)Math.max(0, level - 1) * 0.5F;
      } else if (this.damageType == 1 && creatureType == IB.UNDEAD) {
         return (float)level * 2.5F;
      } else {
         return this.damageType == 2 && creatureType == IB.ARTHROPOD ? (float)level * 2.5F : 0.0F;
      }
   }

   public String getName() {
      return "enchantment.damage." + DAMAGE_NAMES[this.damageType];
   }

   public boolean canApplyTogether(Fa ench) {
      return !(ench instanceof Fg);
   }

   public boolean canApply(Qy stack) {
      return stack.getItem() instanceof OU ? true : super.canApply(stack);
   }

   public void onEntityDamaged(Iw user, Ig target, int level) {
      if (target instanceof Iw) {
         Iw entitylivingbase = (Iw)target;
         if (this.damageType == 2 && entitylivingbase.getCreatureAttribute() == IB.ARTHROPOD) {
            int i = 20 + user.getRNG().nextInt(10 * level);
            entitylivingbase.addPotionEffect(new VZ(NL.SLOWNESS, i, 3));
         }
      }

   }
}
