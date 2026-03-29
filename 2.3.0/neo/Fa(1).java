package neo;

import com.google.common.collect.Lists;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.RegistryNamespaced;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;

public abstract class Fa {
   public static final RegistryNamespaced<ResourceLocation, Fa> REGISTRY = new RegistryNamespaced();
   private final EntityEquipmentSlot[] applicableEquipmentTypes;
   private final EZ rarity;
   @Nullable
   public FS type;
   protected String name;

   @Nullable
   public static Fa getEnchantmentByID(int id) {
      return (Fa)REGISTRY.getObjectById(id);
   }

   public static int getEnchantmentID(Fa enchantmentIn) {
      return REGISTRY.getIDForObject(enchantmentIn);
   }

   @Nullable
   public static Fa getEnchantmentByLocation(String location) {
      return (Fa)REGISTRY.getObject(new ResourceLocation(location));
   }

   protected Fa(EZ rarityIn, FS typeIn, EntityEquipmentSlot[] slots) {
      this.rarity = rarityIn;
      this.type = typeIn;
      this.applicableEquipmentTypes = slots;
   }

   public List<Qy> getEntityEquipment(Iw entityIn) {
      List<Qy> list = Lists.newArrayList();
      EntityEquipmentSlot[] var3 = this.applicableEquipmentTypes;
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         EntityEquipmentSlot entityequipmentslot = var3[var5];
         Qy itemstack = entityIn.getItemStackFromSlot(entityequipmentslot);
         if (!itemstack.isEmpty()) {
            list.add(itemstack);
         }
      }

      return list;
   }

   public EZ getRarity() {
      return this.rarity;
   }

   public int getMinLevel() {
      return 1;
   }

   public int getMaxLevel() {
      return 1;
   }

   public int getMinEnchantability(int enchantmentLevel) {
      return 1 + enchantmentLevel * 10;
   }

   public int getMaxEnchantability(int enchantmentLevel) {
      return this.getMinEnchantability(enchantmentLevel) + 5;
   }

   public int calcModifierDamage(int level, DamageSource source) {
      return 0;
   }

   public float calcDamageByCreature(int level, IB creatureType) {
      return 0.0F;
   }

   public final boolean isCompatibleWith(Fa p_191560_1_) {
      return this.canApplyTogether(p_191560_1_) && p_191560_1_.canApplyTogether(this);
   }

   protected boolean canApplyTogether(Fa ench) {
      return this != ench;
   }

   public Fa setName(String enchName) {
      this.name = enchName;
      return this;
   }

   public String getName() {
      return "enchantment." + this.name;
   }

   public String getTranslatedName(int level) {
      String s = I18n.translateToLocal(this.getName());
      if (this.isCurse()) {
         s = TextFormatting.RED + s;
      }

      return level == 1 && this.getMaxLevel() == 1 ? s : s + " " + I18n.translateToLocal("enchantment.level." + level);
   }

   public boolean canApply(Qy stack) {
      return this.type.canEnchantItem(stack.getItem());
   }

   public void onEntityDamaged(Iw user, Ig target, int level) {
   }

   public void onUserHurt(Iw user, Ig attacker, int level) {
   }

   public boolean isTreasureEnchantment() {
      return false;
   }

   public boolean isCurse() {
      return false;
   }

   public static void registerEnchantments() {
      EntityEquipmentSlot[] aentityequipmentslot = new EntityEquipmentSlot[]{EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET};
      REGISTRY.register(0, new ResourceLocation("protection"), new Fz(EZ.COMMON, Fy.ALL, aentityequipmentslot));
      REGISTRY.register(1, new ResourceLocation("fire_protection"), new Fz(EZ.UNCOMMON, Fy.FIRE, aentityequipmentslot));
      REGISTRY.register(2, new ResourceLocation("feather_falling"), new Fz(EZ.UNCOMMON, Fy.FALL, aentityequipmentslot));
      REGISTRY.register(3, new ResourceLocation("blast_protection"), new Fz(EZ.RARE, Fy.EXPLOSION, aentityequipmentslot));
      REGISTRY.register(4, new ResourceLocation("projectile_protection"), new Fz(EZ.UNCOMMON, Fy.PROJECTILE, aentityequipmentslot));
      REGISTRY.register(5, new ResourceLocation("respiration"), new Fx(EZ.RARE, aentityequipmentslot));
      REGISTRY.register(6, new ResourceLocation("aqua_affinity"), new FF(EZ.RARE, aentityequipmentslot));
      REGISTRY.register(7, new ResourceLocation("thorns"), new FB(EZ.VERY_RARE, aentityequipmentslot));
      REGISTRY.register(8, new ResourceLocation("depth_strider"), new FE(EZ.RARE, aentityequipmentslot));
      REGISTRY.register(9, new ResourceLocation("frost_walker"), new Fm(EZ.RARE, new EntityEquipmentSlot[]{EntityEquipmentSlot.FEET}));
      REGISTRY.register(10, new ResourceLocation("binding_curse"), new Ff(EZ.VERY_RARE, aentityequipmentslot));
      REGISTRY.register(16, new ResourceLocation("sharpness"), new Fg(EZ.COMMON, 0, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}));
      REGISTRY.register(17, new ResourceLocation("smite"), new Fg(EZ.UNCOMMON, 1, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}));
      REGISTRY.register(18, new ResourceLocation("bane_of_arthropods"), new Fg(EZ.UNCOMMON, 2, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}));
      REGISTRY.register(19, new ResourceLocation("knockback"), new Fu(EZ.UNCOMMON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}));
      REGISTRY.register(20, new ResourceLocation("fire_aspect"), new Fk(EZ.RARE, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}));
      REGISTRY.register(21, new ResourceLocation("looting"), new Fv(EZ.RARE, FS.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}));
      REGISTRY.register(22, new ResourceLocation("sweeping"), new FA(EZ.RARE, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}));
      REGISTRY.register(32, new ResourceLocation("efficiency"), new Fi(EZ.COMMON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}));
      REGISTRY.register(33, new ResourceLocation("silk_touch"), new FC(EZ.VERY_RARE, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}));
      REGISTRY.register(34, new ResourceLocation("unbreaking"), new Fj(EZ.UNCOMMON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}));
      REGISTRY.register(35, new ResourceLocation("fortune"), new Fv(EZ.RARE, FS.DIGGER, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}));
      REGISTRY.register(48, new ResourceLocation("power"), new Fb(EZ.COMMON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}));
      REGISTRY.register(49, new ResourceLocation("punch"), new Fe(EZ.RARE, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}));
      REGISTRY.register(50, new ResourceLocation("flame"), new Fc(EZ.RARE, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}));
      REGISTRY.register(51, new ResourceLocation("infinity"), new Fd(EZ.VERY_RARE, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}));
      REGISTRY.register(61, new ResourceLocation("luck_of_the_sea"), new Fv(EZ.RARE, FS.FISHING_ROD, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}));
      REGISTRY.register(62, new ResourceLocation("lure"), new Fl(EZ.RARE, FS.FISHING_ROD, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}));
      REGISTRY.register(70, new ResourceLocation("mending"), new Fw(EZ.RARE, EntityEquipmentSlot.values()));
      REGISTRY.register(71, new ResourceLocation("vanishing_curse"), new FD(EZ.VERY_RARE, EntityEquipmentSlot.values()));
   }
}
