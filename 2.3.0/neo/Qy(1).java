package neo;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.BlockEntityTag;
import net.minecraft.util.datafix.walkers.EntityTag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraft.util.text.translation.I18n;

public final class Qy {
   public static final Qy EMPTY = new Qy((OL)null);
   public static final DecimalFormat DECIMALFORMAT = new DecimalFormat("#.##");
   public int stackSize;
   private int animationsToGo;
   private final OL item;
   public boolean onFinish;
   private QQ stackTagCompound;
   private boolean isEmpty;
   private int itemDamage;
   private IZ itemFrame;
   private co canDestroyCacheBlock;
   private boolean canDestroyCacheResult;
   private co canPlaceOnCacheBlock;
   private boolean canPlaceOnCacheResult;

   public Qy(co blockIn) {
      this((co)blockIn, 1);
   }

   public Qy(co blockIn, int amount) {
      this((co)blockIn, amount, 0);
   }

   public Qy(co blockIn, int amount, int meta) {
      this(OL.getItemFromBlock(blockIn), amount, meta);
   }

   public Qy(OL itemIn) {
      this((OL)itemIn, 1);
   }

   public Qy(OL itemIn, int amount) {
      this((OL)itemIn, amount, 0);
   }

   public Qy(OL itemIn, int amount, int meta) {
      this.item = itemIn;
      this.itemDamage = meta;
      this.stackSize = amount;
      if (this.itemDamage < 0) {
         this.itemDamage = 0;
      }

      this.updateEmptyState();
   }

   private void updateEmptyState() {
      this.isEmpty = this.isEmpty();
   }

   public Qy(QQ compound) {
      this.item = OL.getByNameOrId(compound.getString("id"));
      this.stackSize = compound.getByte("Count");
      this.itemDamage = Math.max(0, compound.getShort("Damage"));
      if (compound.hasKey("tag", 10)) {
         this.stackTagCompound = compound.getCompoundTag("tag");
         if (this.item != null) {
            this.item.updateItemStackNBT(compound);
         }
      }

      this.updateEmptyState();
   }

   public boolean isEmpty() {
      if (this == EMPTY) {
         return true;
      } else if (this.item != null && this.item != OL.getItemFromBlock(Nk.AIR)) {
         if (this.stackSize <= 0) {
            return true;
         } else {
            return this.itemDamage < -32768 || this.itemDamage > 65535;
         }
      } else {
         return true;
      }
   }

   public float getStrVsBlock(in blockIn) {
      return this.getItem().getStrVsBlock(this, blockIn);
   }

   public static void registerFixes(DataFixer fixer) {
      fixer.registerWalker(FixTypes.ITEM_INSTANCE, new BlockEntityTag());
      fixer.registerWalker(FixTypes.ITEM_INSTANCE, new EntityTag());
   }

   public Qy splitStack(int amount) {
      int i = Math.min(amount, this.stackSize);
      Qy itemstack = this.copy();
      itemstack.setCount(i);
      this.shrink(i);
      return itemstack;
   }

   public OL getItem() {
      return this.isEmpty ? OL.getItemFromBlock(Nk.AIR) : this.item;
   }

   public EnumActionResult onItemUse(ME playerIn, bij worldIn, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
      EnumActionResult enumactionresult = this.getItem().onItemUse(playerIn, worldIn, pos, hand, side, hitX, hitY, hitZ);
      if (enumactionresult == EnumActionResult.SUCCESS) {
         playerIn.addStat(XV.getObjectUseStats(this.item));
      }

      return enumactionresult;
   }

   public float getDestroySpeed(in blockIn) {
      return this.getItem().getDestroySpeed(this, blockIn);
   }

   public ActionResult<Qy> useItemRightClick(bij worldIn, ME playerIn, EnumHand hand) {
      return this.getItem().onItemRightClick(worldIn, playerIn, hand);
   }

   public Qy onItemUseFinish(bij worldIn, Iw entityLiving) {
      return this.getItem().onItemUseFinish(this, worldIn, entityLiving);
   }

   public QQ writeToNBT(QQ nbt) {
      ResourceLocation resourcelocation = (ResourceLocation)OL.REGISTRY.getNameForObject(this.item);
      nbt.setString("id", resourcelocation == null ? "minecraft:air" : resourcelocation.toString());
      nbt.setByte("Count", (byte)this.stackSize);
      nbt.setShort("Damage", (short)this.itemDamage);
      if (this.stackTagCompound != null) {
         nbt.setTag("tag", this.stackTagCompound);
      }

      return nbt;
   }

   public int getMaxStackSize() {
      return this.getItem().getItemStackLimit();
   }

   public boolean isStackable() {
      return this.getMaxStackSize() > 1 && (!this.isItemStackDamageable() || !this.isItemDamaged());
   }

   public boolean isItemStackDamageable() {
      if (this.isEmpty) {
         return false;
      } else if (this.item.getMaxDamage() <= 0) {
         return false;
      } else {
         return !this.hasTagCompound() || !this.getTagCompound().getBoolean("Unbreakable");
      }
   }

   public boolean getHasSubtypes() {
      return this.getItem().getHasSubtypes();
   }

   public boolean isItemDamaged() {
      return this.isItemStackDamageable() && this.itemDamage > 0;
   }

   public int getItemDamage() {
      return this.itemDamage;
   }

   public int getMetadata() {
      return this.itemDamage;
   }

   public void setItemDamage(int meta) {
      this.itemDamage = meta;
      if (this.itemDamage < 0) {
         this.itemDamage = 0;
      }

   }

   public int getMaxDamage() {
      return this.getItem().getMaxDamage();
   }

   public boolean attemptDamageItem(int amount, Random rand, @Nullable MG damager) {
      if (!this.isItemStackDamageable()) {
         return false;
      } else {
         if (amount > 0) {
            int i = Ft.getEnchantmentLevel(NJ.UNBREAKING, this);
            int j = 0;

            for(int k = 0; i > 0 && k < amount; ++k) {
               if (Fj.negateDamage(this, i, rand)) {
                  ++j;
               }
            }

            amount -= j;
            if (amount <= 0) {
               return false;
            }
         }

         if (damager != null && amount != 0) {
            bY.ITEM_DURABILITY_CHANGED.trigger(damager, this, this.itemDamage + amount);
         }

         this.itemDamage += amount;
         return this.itemDamage > this.getMaxDamage();
      }
   }

   public void damageItem(int amount, Iw entityIn) {
      if ((!(entityIn instanceof ME) || !((ME)entityIn).capabilities.isCreativeMode) && this.isItemStackDamageable() && this.attemptDamageItem(amount, entityIn.getRNG(), entityIn instanceof MG ? (MG)entityIn : null)) {
         entityIn.renderBrokenItemStack(this);
         this.shrink(1);
         if (entityIn instanceof ME) {
            ME entityplayer = (ME)entityIn;
            entityplayer.addStat(XV.getObjectBreakStats(this.item));
         }

         this.itemDamage = 0;
      }

   }

   public void hitEntity(Iw entityIn, ME playerIn) {
      boolean flag = this.item.hitEntity(this, entityIn, playerIn);
      if (flag) {
         playerIn.addStat(XV.getObjectUseStats(this.item));
      }

   }

   public void onBlockDestroyed(bij worldIn, in blockIn, BlockPos pos, ME playerIn) {
      boolean flag = this.getItem().onBlockDestroyed(this, worldIn, blockIn, pos, playerIn);
      if (flag) {
         playerIn.addStat(XV.getObjectUseStats(this.item));
      }

   }

   public boolean canHarvestBlock(in blockIn) {
      return this.getItem().canHarvestBlock(blockIn);
   }

   public boolean interactWithEntity(ME playerIn, Iw entityIn, EnumHand hand) {
      return this.getItem().itemInteractionForEntity(this, playerIn, entityIn, hand);
   }

   public Qy copy() {
      Qy itemstack = new Qy(this.item, this.stackSize, this.itemDamage);
      itemstack.setAnimationsToGo(this.getAnimationsToGo());
      if (this.stackTagCompound != null) {
         itemstack.stackTagCompound = this.stackTagCompound.copy();
      }

      return itemstack;
   }

   public static boolean areItemStackTagsEqual(Qy stackA, Qy stackB) {
      if (stackA.isEmpty() && stackB.isEmpty()) {
         return true;
      } else if (!stackA.isEmpty() && !stackB.isEmpty()) {
         if (stackA.stackTagCompound == null && stackB.stackTagCompound != null) {
            return false;
         } else {
            return stackA.stackTagCompound == null || stackA.stackTagCompound.equals(stackB.stackTagCompound);
         }
      } else {
         return false;
      }
   }

   public static boolean areItemStacksEqual(Qy stackA, Qy stackB) {
      if (stackA.isEmpty() && stackB.isEmpty()) {
         return true;
      } else {
         return !stackA.isEmpty() && !stackB.isEmpty() ? stackA.isItemStackEqual(stackB) : false;
      }
   }

   private boolean isItemStackEqual(Qy other) {
      if (this.stackSize != other.stackSize) {
         return false;
      } else if (this.getItem() != other.getItem()) {
         return false;
      } else if (this.itemDamage != other.itemDamage) {
         return false;
      } else if (this.stackTagCompound == null && other.stackTagCompound != null) {
         return false;
      } else {
         return this.stackTagCompound == null || this.stackTagCompound.equals(other.stackTagCompound);
      }
   }

   public static boolean areItemsEqual(Qy stackA, Qy stackB) {
      if (stackA == stackB) {
         return true;
      } else {
         return !stackA.isEmpty() && !stackB.isEmpty() ? stackA.isItemEqual(stackB) : false;
      }
   }

   public static boolean areItemsEqualIgnoreDurability(Qy stackA, Qy stackB) {
      if (stackA == stackB) {
         return true;
      } else {
         return !stackA.isEmpty() && !stackB.isEmpty() ? stackA.isItemEqualIgnoreDurability(stackB) : false;
      }
   }

   public boolean isItemEqual(Qy other) {
      return !other.isEmpty() && this.item == other.item && this.itemDamage == other.itemDamage;
   }

   public boolean isItemEqualIgnoreDurability(Qy stack) {
      if (!this.isItemStackDamageable()) {
         return this.isItemEqual(stack);
      } else {
         return !stack.isEmpty() && this.item == stack.item;
      }
   }

   public String getTranslationKey() {
      return this.getItem().getTranslationKey(this);
   }

   public String toString() {
      return this.stackSize + "x" + this.getItem().getTranslationKey() + "@" + this.itemDamage;
   }

   public void updateAnimation(bij worldIn, Ig entityIn, int inventorySlot, boolean isCurrentItem) {
      if (this.animationsToGo > 0) {
         --this.animationsToGo;
      }

      if (this.item != null) {
         this.item.onUpdate(this, worldIn, entityIn, inventorySlot, isCurrentItem);
      }

   }

   public void onCrafting(bij worldIn, ME playerIn, int amount) {
      playerIn.addStat(XV.getCraftStats(this.item), amount);
      this.getItem().onCreated(this, worldIn, playerIn);
   }

   public int getMaxItemUseDuration() {
      return this.getItem().getMaxItemUseDuration(this);
   }

   public Ol getItemUseAction() {
      return this.getItem().getItemUseAction(this);
   }

   public void onPlayerStoppedUsing(bij worldIn, Iw entityLiving, int timeLeft) {
      this.getItem().onPlayerStoppedUsing(this, worldIn, entityLiving, timeLeft);
   }

   public boolean hasTagCompound() {
      return !this.isEmpty && this.stackTagCompound != null;
   }

   @Nullable
   public QQ getTagCompound() {
      return this.stackTagCompound;
   }

   public QQ getOrCreateSubCompound(String key) {
      if (this.stackTagCompound != null && this.stackTagCompound.hasKey(key, 10)) {
         return this.stackTagCompound.getCompoundTag(key);
      } else {
         QQ nbttagcompound = new QQ();
         this.setTagInfo(key, nbttagcompound);
         return nbttagcompound;
      }
   }

   @Nullable
   public QQ getSubCompound(String key) {
      return this.stackTagCompound != null && this.stackTagCompound.hasKey(key, 10) ? this.stackTagCompound.getCompoundTag(key) : null;
   }

   public void removeSubCompound(String key) {
      if (this.stackTagCompound != null && this.stackTagCompound.hasKey(key, 10)) {
         this.stackTagCompound.removeTag(key);
      }

   }

   public QW getEnchantmentTagList() {
      return this.stackTagCompound != null ? this.stackTagCompound.getTagList("ench", 10) : new QW();
   }

   public void setTagCompound(@Nullable QQ nbt) {
      this.stackTagCompound = nbt;
   }

   public String getDisplayName() {
      QQ nbttagcompound = this.getSubCompound("display");
      if (nbttagcompound != null) {
         if (nbttagcompound.hasKey("Name", 8)) {
            return nbttagcompound.getString("Name");
         }

         if (nbttagcompound.hasKey("LocName", 8)) {
            return I18n.translateToLocal(nbttagcompound.getString("LocName"));
         }
      }

      return this.getItem().getItemStackDisplayName(this);
   }

   public Qy setTranslatableName(String p_190924_1_) {
      this.getOrCreateSubCompound("display").setString("LocName", p_190924_1_);
      return this;
   }

   public Qy setStackDisplayName(String displayName) {
      this.getOrCreateSubCompound("display").setString("Name", displayName);
      return this;
   }

   public void clearCustomName() {
      QQ nbttagcompound = this.getSubCompound("display");
      if (nbttagcompound != null) {
         nbttagcompound.removeTag("Name");
         if (nbttagcompound.isEmpty()) {
            this.removeSubCompound("display");
         }
      }

      if (this.stackTagCompound != null && this.stackTagCompound.isEmpty()) {
         this.stackTagCompound = null;
      }

   }

   public boolean hasDisplayName() {
      QQ nbttagcompound = this.getSubCompound("display");
      return nbttagcompound != null && nbttagcompound.hasKey("Name", 8);
   }

   public List<String> getTooltip(@Nullable ME playerIn, BJ advanced) {
      List<String> list = Lists.newArrayList();
      String s = this.getDisplayName();
      if (this.hasDisplayName()) {
         s = TextFormatting.ITALIC + s;
      }

      s = s + TextFormatting.RESET;
      if (advanced.isAdvanced()) {
         String s1 = "";
         if (!s.isEmpty()) {
            s = s + " (";
            s1 = ")";
         }

         int i = OL.getIdFromItem(this.item);
         if (this.getHasSubtypes()) {
            s = s + String.format("#%04d/%d%s", i, this.itemDamage, s1);
         } else {
            s = s + String.format("#%04d%s", i, s1);
         }
      } else if (!this.hasDisplayName() && this.item == NK.FILLED_MAP) {
         s = s + " #" + this.itemDamage;
      }

      list.add(s);
      int i1 = 0;
      if (this.hasTagCompound() && this.stackTagCompound.hasKey("HideFlags", 99)) {
         i1 = this.stackTagCompound.getInteger("HideFlags");
      }

      if ((i1 & 32) == 0) {
         this.getItem().addInformation(this, playerIn == null ? null : playerIn.world, list, advanced);
      }

      int k1;
      QW nbttaglist2;
      int l1;
      if (this.hasTagCompound()) {
         if ((i1 & 1) == 0) {
            nbttaglist2 = this.getEnchantmentTagList();

            for(k1 = 0; k1 < nbttaglist2.tagCount(); ++k1) {
               QQ nbttagcompound = nbttaglist2.getCompoundTagAt(k1);
               int k = nbttagcompound.getShort("id");
               int l = nbttagcompound.getShort("lvl");
               Fa enchantment = Fa.getEnchantmentByID(k);
               if (enchantment != null) {
                  list.add(enchantment.getTranslatedName(l));
               }
            }
         }

         if (this.stackTagCompound.hasKey("display", 10)) {
            QQ nbttagcompound1 = this.stackTagCompound.getCompoundTag("display");
            if (nbttagcompound1.hasKey("color", 3)) {
               if (advanced.isAdvanced()) {
                  list.add(I18n.translateToLocalFormatted("item.color", String.format("#%06X", nbttagcompound1.getInteger("color"))));
               } else {
                  list.add(TextFormatting.ITALIC + I18n.translateToLocal("item.dyed"));
               }
            }

            if (nbttagcompound1.getTagId("Lore") == 9) {
               QW nbttaglist3 = nbttagcompound1.getTagList("Lore", 8);
               if (!nbttaglist3.isEmpty()) {
                  for(l1 = 0; l1 < nbttaglist3.tagCount(); ++l1) {
                     list.add(TextFormatting.DARK_PURPLE + "" + TextFormatting.ITALIC + nbttaglist3.getStringTagAt(l1));
                  }
               }
            }
         }
      }

      EntityEquipmentSlot[] var22 = EntityEquipmentSlot.values();
      k1 = var22.length;

      for(l1 = 0; l1 < k1; ++l1) {
         EntityEquipmentSlot entityequipmentslot = var22[l1];
         Multimap<String, FW> multimap = this.getAttributeModifiers(entityequipmentslot);
         if (!multimap.isEmpty() && (i1 & 2) == 0) {
            list.add("");
            list.add(I18n.translateToLocal("item.modifiers." + entityequipmentslot.getName()));
            Iterator var28 = multimap.entries().iterator();

            while(var28.hasNext()) {
               Map.Entry<String, FW> entry = (Map.Entry)var28.next();
               FW attributemodifier = (FW)entry.getValue();
               double d0 = attributemodifier.getAmount();
               boolean flag = false;
               if (playerIn != null) {
                  if (attributemodifier.getID() == OL.ATTACK_DAMAGE_MODIFIER) {
                     d0 += playerIn.getEntityAttribute(Ni.ATTACK_DAMAGE).getBaseValue();
                     d0 += (double)Ft.getModifierForCreature(this, IB.UNDEFINED);
                     flag = true;
                  } else if (attributemodifier.getID() == OL.ATTACK_SPEED_MODIFIER) {
                     d0 += playerIn.getEntityAttribute(Ni.ATTACK_SPEED).getBaseValue();
                     flag = true;
                  }
               }

               double d1;
               if (attributemodifier.getOperation() != 1 && attributemodifier.getOperation() != 2) {
                  d1 = d0;
               } else {
                  d1 = d0 * 100.0;
               }

               if (flag) {
                  list.add(" " + I18n.translateToLocalFormatted("attribute.modifier.equals." + attributemodifier.getOperation(), DECIMALFORMAT.format(d1), I18n.translateToLocal("attribute.name." + (String)entry.getKey())));
               } else if (d0 > 0.0) {
                  list.add(TextFormatting.BLUE + " " + I18n.translateToLocalFormatted("attribute.modifier.plus." + attributemodifier.getOperation(), DECIMALFORMAT.format(d1), I18n.translateToLocal("attribute.name." + (String)entry.getKey())));
               } else if (d0 < 0.0) {
                  d1 *= -1.0;
                  list.add(TextFormatting.RED + " " + I18n.translateToLocalFormatted("attribute.modifier.take." + attributemodifier.getOperation(), DECIMALFORMAT.format(d1), I18n.translateToLocal("attribute.name." + (String)entry.getKey())));
               }
            }
         }
      }

      if (this.hasTagCompound() && this.getTagCompound().getBoolean("Unbreakable") && (i1 & 4) == 0) {
         list.add(TextFormatting.BLUE + I18n.translateToLocal("item.unbreakable"));
      }

      co block1;
      if (this.hasTagCompound() && this.stackTagCompound.hasKey("CanDestroy", 9) && (i1 & 8) == 0) {
         nbttaglist2 = this.stackTagCompound.getTagList("CanDestroy", 8);
         if (!nbttaglist2.isEmpty()) {
            list.add("");
            list.add(TextFormatting.GRAY + I18n.translateToLocal("item.canBreak"));

            for(k1 = 0; k1 < nbttaglist2.tagCount(); ++k1) {
               block1 = co.getBlockFromName(nbttaglist2.getStringTagAt(k1));
               if (block1 != null) {
                  list.add(TextFormatting.DARK_GRAY + block1.getLocalizedName());
               } else {
                  list.add(TextFormatting.DARK_GRAY + "missingno");
               }
            }
         }
      }

      if (this.hasTagCompound() && this.stackTagCompound.hasKey("CanPlaceOn", 9) && (i1 & 16) == 0) {
         nbttaglist2 = this.stackTagCompound.getTagList("CanPlaceOn", 8);
         if (!nbttaglist2.isEmpty()) {
            list.add("");
            list.add(TextFormatting.GRAY + I18n.translateToLocal("item.canPlace"));

            for(k1 = 0; k1 < nbttaglist2.tagCount(); ++k1) {
               block1 = co.getBlockFromName(nbttaglist2.getStringTagAt(k1));
               if (block1 != null) {
                  list.add(TextFormatting.DARK_GRAY + block1.getLocalizedName());
               } else {
                  list.add(TextFormatting.DARK_GRAY + "missingno");
               }
            }
         }
      }

      if (advanced.isAdvanced()) {
         if (this.isItemDamaged()) {
            list.add(I18n.translateToLocalFormatted("item.durability", this.getMaxDamage() - this.getItemDamage(), this.getMaxDamage()));
         }

         list.add(TextFormatting.DARK_GRAY + ((ResourceLocation)OL.REGISTRY.getNameForObject(this.item)).toString());
         if (this.hasTagCompound()) {
            list.add(TextFormatting.DARK_GRAY + I18n.translateToLocalFormatted("item.nbt_tags", this.getTagCompound().getKeySet().size()));
         }
      }

      return list;
   }

   public boolean hasEffect() {
      return this.getItem().hasEffect(this);
   }

   public On getRarity() {
      return this.getItem().getRarity(this);
   }

   public boolean isItemEnchantable() {
      if (!this.getItem().isEnchantable(this)) {
         return false;
      } else {
         return !this.isItemEnchanted();
      }
   }

   public void addEnchantment(Fa ench, int level) {
      if (this.stackTagCompound == null) {
         this.setTagCompound(new QQ());
      }

      if (!this.stackTagCompound.hasKey("ench", 9)) {
         this.stackTagCompound.setTag("ench", new QW());
      }

      QW nbttaglist = this.stackTagCompound.getTagList("ench", 10);
      QQ nbttagcompound = new QQ();
      nbttagcompound.setShort("id", (short)Fa.getEnchantmentID(ench));
      nbttagcompound.setShort("lvl", (short)((byte)level));
      nbttaglist.appendTag(nbttagcompound);
   }

   public boolean isItemEnchanted() {
      if (this.stackTagCompound != null && this.stackTagCompound.hasKey("ench", 9)) {
         return !this.stackTagCompound.getTagList("ench", 10).isEmpty();
      } else {
         return false;
      }
   }

   public void setTagInfo(String key, QH value) {
      if (this.stackTagCompound == null) {
         this.setTagCompound(new QQ());
      }

      this.stackTagCompound.setTag(key, value);
   }

   public boolean canEditBlocks() {
      return this.getItem().canItemEditBlocks();
   }

   public boolean isOnItemFrame() {
      return this.itemFrame != null;
   }

   public void setItemFrame(IZ frame) {
      this.itemFrame = frame;
   }

   @Nullable
   public IZ getItemFrame() {
      return this.isEmpty ? null : this.itemFrame;
   }

   public int getRepairCost() {
      return this.hasTagCompound() && this.stackTagCompound.hasKey("RepairCost", 3) ? this.stackTagCompound.getInteger("RepairCost") : 0;
   }

   public void setRepairCost(int cost) {
      if (!this.hasTagCompound()) {
         this.stackTagCompound = new QQ();
      }

      this.stackTagCompound.setInteger("RepairCost", cost);
   }

   public Multimap<String, FW> getAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
      Object multimap;
      if (this.hasTagCompound() && this.stackTagCompound.hasKey("AttributeModifiers", 9)) {
         multimap = HashMultimap.create();
         QW nbttaglist = this.stackTagCompound.getTagList("AttributeModifiers", 10);

         for(int i = 0; i < nbttaglist.tagCount(); ++i) {
            QQ nbttagcompound = nbttaglist.getCompoundTagAt(i);
            FW attributemodifier = Ni.readAttributeModifierFromNBT(nbttagcompound);
            if (attributemodifier != null && (!nbttagcompound.hasKey("Slot", 8) || nbttagcompound.getString("Slot").equals(equipmentSlot.getName())) && attributemodifier.getID().getLeastSignificantBits() != 0L && attributemodifier.getID().getMostSignificantBits() != 0L) {
               ((Multimap)multimap).put(nbttagcompound.getString("AttributeName"), attributemodifier);
            }
         }
      } else {
         multimap = this.getItem().getItemAttributeModifiers(equipmentSlot);
      }

      return (Multimap)multimap;
   }

   public void addAttributeModifier(String attributeName, FW modifier, @Nullable EntityEquipmentSlot equipmentSlot) {
      if (this.stackTagCompound == null) {
         this.stackTagCompound = new QQ();
      }

      if (!this.stackTagCompound.hasKey("AttributeModifiers", 9)) {
         this.stackTagCompound.setTag("AttributeModifiers", new QW());
      }

      QW nbttaglist = this.stackTagCompound.getTagList("AttributeModifiers", 10);
      QQ nbttagcompound = Ni.writeAttributeModifierToNBT(modifier);
      nbttagcompound.setString("AttributeName", attributeName);
      if (equipmentSlot != null) {
         nbttagcompound.setString("Slot", equipmentSlot.getName());
      }

      nbttaglist.appendTag(nbttagcompound);
   }

   public ITextComponent getTextComponent() {
      TextComponentString textcomponentstring = new TextComponentString(this.getDisplayName());
      if (this.hasDisplayName()) {
         textcomponentstring.getStyle().setItalic(true);
      }

      ITextComponent itextcomponent = (new TextComponentString("[")).appendSibling(textcomponentstring).appendText("]");
      if (!this.isEmpty) {
         QQ nbttagcompound = this.writeToNBT(new QQ());
         itextcomponent.getStyle().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ITEM, new TextComponentString(nbttagcompound.toString())));
         itextcomponent.getStyle().setColor(this.getRarity().color);
      }

      return itextcomponent;
   }

   public boolean canDestroy(co blockIn) {
      if (blockIn == this.canDestroyCacheBlock) {
         return this.canDestroyCacheResult;
      } else {
         this.canDestroyCacheBlock = blockIn;
         if (this.hasTagCompound() && this.stackTagCompound.hasKey("CanDestroy", 9)) {
            QW nbttaglist = this.stackTagCompound.getTagList("CanDestroy", 8);

            for(int i = 0; i < nbttaglist.tagCount(); ++i) {
               co block = co.getBlockFromName(nbttaglist.getStringTagAt(i));
               if (block == blockIn) {
                  this.canDestroyCacheResult = true;
                  return true;
               }
            }
         }

         this.canDestroyCacheResult = false;
         return false;
      }
   }

   public boolean canPlaceOn(co blockIn) {
      if (blockIn == this.canPlaceOnCacheBlock) {
         return this.canPlaceOnCacheResult;
      } else {
         this.canPlaceOnCacheBlock = blockIn;
         if (this.hasTagCompound() && this.stackTagCompound.hasKey("CanPlaceOn", 9)) {
            QW nbttaglist = this.stackTagCompound.getTagList("CanPlaceOn", 8);

            for(int i = 0; i < nbttaglist.tagCount(); ++i) {
               co block = co.getBlockFromName(nbttaglist.getStringTagAt(i));
               if (block == blockIn) {
                  this.canPlaceOnCacheResult = true;
                  return true;
               }
            }
         }

         this.canPlaceOnCacheResult = false;
         return false;
      }
   }

   public int getAnimationsToGo() {
      return this.animationsToGo;
   }

   public void setAnimationsToGo(int animations) {
      this.animationsToGo = animations;
   }

   public int getCount() {
      return this.isEmpty ? 0 : this.stackSize;
   }

   public void setCount(int size) {
      this.stackSize = size;
      this.updateEmptyState();
   }

   public void grow(int quantity) {
      this.setCount(this.stackSize + quantity);
   }

   public void shrink(int quantity) {
      this.grow(-quantity);
   }
}
