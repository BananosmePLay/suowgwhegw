package net.minecraft.creativetab;

import java.util.Iterator;
import javax.annotation.Nullable;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.NonNullList;

public abstract class CreativeTabs {
   public static final CreativeTabs[] CREATIVE_TAB_ARRAY = new CreativeTabs[12];
   public static final CreativeTabs BUILDING_BLOCKS = new CreativeTabs(0, "buildingBlocks") {
      public ItemStack createIcon() {
         return new ItemStack(Item.getItemFromBlock(Blocks.BRICK_BLOCK));
      }
   };
   public static final CreativeTabs DECORATIONS = new CreativeTabs(1, "decorations") {
      public ItemStack createIcon() {
         return new ItemStack(Item.getItemFromBlock(Blocks.DOUBLE_PLANT), 1, BlockDoublePlant.EnumPlantType.PAEONIA.getMeta());
      }
   };
   public static final CreativeTabs REDSTONE = new CreativeTabs(2, "redstone") {
      public ItemStack createIcon() {
         return new ItemStack(Items.REDSTONE);
      }
   };
   public static final CreativeTabs TRANSPORTATION = new CreativeTabs(3, "transportation") {
      public ItemStack createIcon() {
         return new ItemStack(Item.getItemFromBlock(Blocks.GOLDEN_RAIL));
      }
   };
   public static final CreativeTabs MISC = new CreativeTabs(6, "misc") {
      public ItemStack createIcon() {
         return new ItemStack(Items.LAVA_BUCKET);
      }
   };
   public static final CreativeTabs SEARCH = (new CreativeTabs(5, "search") {
      public ItemStack createIcon() {
         return new ItemStack(Items.COMPASS);
      }
   }).setBackgroundImageName("item_search.png");
   public static final CreativeTabs FOOD = new CreativeTabs(7, "food") {
      public ItemStack createIcon() {
         return new ItemStack(Items.APPLE);
      }
   };
   public static final CreativeTabs TOOLS;
   public static final CreativeTabs COMBAT;
   public static final CreativeTabs BREWING;
   public static final CreativeTabs MATERIALS;
   public static final CreativeTabs HOTBAR;
   public static final CreativeTabs INVENTORY;
   private final int index;
   private final String tabLabel;
   private String backgroundTexture = "items.png";
   private boolean hasScrollbar = true;
   private boolean drawTitle = true;
   private EnumEnchantmentType[] enchantmentTypes = new EnumEnchantmentType[0];
   private ItemStack icon;

   public CreativeTabs(int index, String label) {
      this.index = index;
      this.tabLabel = label;
      this.icon = ItemStack.EMPTY;
      CREATIVE_TAB_ARRAY[index] = this;
   }

   public int getIndex() {
      return this.index;
   }

   public String getTabLabel() {
      return this.tabLabel;
   }

   public String getTranslationKey() {
      return "itemGroup." + this.getTabLabel();
   }

   public ItemStack getIcon() {
      if (this.icon.isEmpty()) {
         this.icon = this.createIcon();
      }

      return this.icon;
   }

   public abstract ItemStack createIcon();

   public String getBackgroundImageName() {
      return this.backgroundTexture;
   }

   public CreativeTabs setBackgroundImageName(String texture) {
      this.backgroundTexture = texture;
      return this;
   }

   public boolean drawInForegroundOfTab() {
      return this.drawTitle;
   }

   public CreativeTabs setNoTitle() {
      this.drawTitle = false;
      return this;
   }

   public boolean hasScrollbar() {
      return this.hasScrollbar;
   }

   public CreativeTabs setNoScrollbar() {
      this.hasScrollbar = false;
      return this;
   }

   public int getColumn() {
      return this.index % 6;
   }

   public boolean isOnTopRow() {
      return this.index < 6;
   }

   public boolean isAlignedRight() {
      return this.getColumn() == 5;
   }

   public EnumEnchantmentType[] getRelevantEnchantmentTypes() {
      return this.enchantmentTypes;
   }

   public CreativeTabs setRelevantEnchantmentTypes(EnumEnchantmentType... types) {
      this.enchantmentTypes = types;
      return this;
   }

   public boolean hasRelevantEnchantmentType(@Nullable EnumEnchantmentType enchantmentType) {
      if (enchantmentType != null) {
         EnumEnchantmentType[] var2 = this.enchantmentTypes;
         int var3 = var2.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            EnumEnchantmentType enumenchantmenttype = var2[var4];
            if (enumenchantmenttype == enchantmentType) {
               return true;
            }
         }
      }

      return false;
   }

   public void displayAllRelevantItems(NonNullList<ItemStack> p_78018_1_) {
      Iterator var2 = Item.REGISTRY.iterator();

      while(var2.hasNext()) {
         Item item = (Item)var2.next();
         item.getSubItems(this, p_78018_1_);
      }

   }

   static {
      TOOLS = (new CreativeTabs(8, "tools") {
         public ItemStack createIcon() {
            return new ItemStack(Items.IRON_AXE);
         }
      }).setRelevantEnchantmentTypes(new EnumEnchantmentType[]{EnumEnchantmentType.ALL, EnumEnchantmentType.DIGGER, EnumEnchantmentType.FISHING_ROD, EnumEnchantmentType.BREAKABLE});
      COMBAT = (new CreativeTabs(9, "combat") {
         public ItemStack createIcon() {
            return new ItemStack(Items.GOLDEN_SWORD);
         }
      }).setRelevantEnchantmentTypes(new EnumEnchantmentType[]{EnumEnchantmentType.ALL, EnumEnchantmentType.ARMOR, EnumEnchantmentType.ARMOR_FEET, EnumEnchantmentType.ARMOR_HEAD, EnumEnchantmentType.ARMOR_LEGS, EnumEnchantmentType.ARMOR_CHEST, EnumEnchantmentType.BOW, EnumEnchantmentType.WEAPON, EnumEnchantmentType.WEARABLE, EnumEnchantmentType.BREAKABLE});
      BREWING = new CreativeTabs(10, "brewing") {
         public ItemStack createIcon() {
            return PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.WATER);
         }
      };
      MATERIALS = MISC;
      HOTBAR = new CreativeTabs(4, "hotbar") {
         public ItemStack createIcon() {
            return new ItemStack(Blocks.BOOKSHELF);
         }

         public void displayAllRelevantItems(NonNullList<ItemStack> p_78018_1_) {
            throw new RuntimeException("Implement exception client-side.");
         }

         public boolean isAlignedRight() {
            return true;
         }
      };
      INVENTORY = (new CreativeTabs(11, "inventory") {
         public ItemStack createIcon() {
            return new ItemStack(Item.getItemFromBlock(Blocks.CHEST));
         }
      }).setBackgroundImageName("inventory.png").setNoScrollbar().setNoTitle();
   }
}
