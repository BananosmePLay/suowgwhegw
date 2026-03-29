package neo;

import java.util.Iterator;
import javax.annotation.Nullable;
import net.minecraft.util.NonNullList;

public abstract class EN {
   public static final EN[] CREATIVE_TAB_ARRAY = new EN[12];
   public static final EN BUILDING_BLOCKS = new EN(0, "buildingBlocks") {
      public Qy createIcon() {
         return new Qy(OL.getItemFromBlock(Nk.BRICK_BLOCK));
      }
   };
   public static final EN DECORATIONS = new EN(1, "decorations") {
      public Qy createIcon() {
         return new Qy(OL.getItemFromBlock(Nk.DOUBLE_PLANT), 1, dq.PAEONIA.getMeta());
      }
   };
   public static final EN REDSTONE = new EN(2, "redstone") {
      public Qy createIcon() {
         return new Qy(NK.REDSTONE);
      }
   };
   public static final EN TRANSPORTATION = new EN(3, "transportation") {
      public Qy createIcon() {
         return new Qy(OL.getItemFromBlock(Nk.GOLDEN_RAIL));
      }
   };
   public static final EN MISC = new EN(6, "misc") {
      public Qy createIcon() {
         return new Qy(NK.LAVA_BUCKET);
      }
   };
   public static final EN SEARCH = (new EN(5, "search") {
      public Qy createIcon() {
         return new Qy(NK.COMPASS);
      }
   }).setBackgroundImageName("item_search.png");
   public static final EN FOOD = new EN(7, "food") {
      public Qy createIcon() {
         return new Qy(NK.APPLE);
      }
   };
   public static final EN TOOLS;
   public static final EN COMBAT;
   public static final EN BREWING;
   public static final EN MATERIALS;
   public static final EN HOTBAR;
   public static final EN INVENTORY;
   private final int index;
   private final String tabLabel;
   private String backgroundTexture = "items.png";
   private boolean hasScrollbar = true;
   private boolean drawTitle = true;
   private FS[] enchantmentTypes = new FS[0];
   private Qy icon;

   public EN(int index, String label) {
      this.index = index;
      this.tabLabel = label;
      this.icon = Qy.EMPTY;
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

   public Qy getIcon() {
      if (this.icon.isEmpty()) {
         this.icon = this.createIcon();
      }

      return this.icon;
   }

   public abstract Qy createIcon();

   public String getBackgroundImageName() {
      return this.backgroundTexture;
   }

   public EN setBackgroundImageName(String texture) {
      this.backgroundTexture = texture;
      return this;
   }

   public boolean drawInForegroundOfTab() {
      return this.drawTitle;
   }

   public EN setNoTitle() {
      this.drawTitle = false;
      return this;
   }

   public boolean hasScrollbar() {
      return this.hasScrollbar;
   }

   public EN setNoScrollbar() {
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

   public FS[] getRelevantEnchantmentTypes() {
      return this.enchantmentTypes;
   }

   public EN setRelevantEnchantmentTypes(FS... types) {
      this.enchantmentTypes = types;
      return this;
   }

   public boolean hasRelevantEnchantmentType(@Nullable FS enchantmentType) {
      if (enchantmentType != null) {
         FS[] var2 = this.enchantmentTypes;
         int var3 = var2.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            FS enumenchantmenttype = var2[var4];
            if (enumenchantmenttype == enchantmentType) {
               return true;
            }
         }
      }

      return false;
   }

   public void displayAllRelevantItems(NonNullList<Qy> p_78018_1_) {
      Iterator var2 = OL.REGISTRY.iterator();

      while(var2.hasNext()) {
         OL item = (OL)var2.next();
         item.getSubItems(this, p_78018_1_);
      }

   }

   static {
      TOOLS = (new EN(8, "tools") {
         public Qy createIcon() {
            return new Qy(NK.IRON_AXE);
         }
      }).setRelevantEnchantmentTypes(new FS[]{FS.ALL, FS.DIGGER, FS.FISHING_ROD, FS.BREAKABLE});
      COMBAT = (new EN(9, "combat") {
         public Qy createIcon() {
            return new Qy(NK.GOLDEN_SWORD);
         }
      }).setRelevantEnchantmentTypes(new FS[]{FS.ALL, FS.ARMOR, FS.ARMOR_FEET, FS.ARMOR_HEAD, FS.ARMOR_LEGS, FS.ARMOR_CHEST, FS.BOW, FS.WEAPON, FS.WEARABLE, FS.BREAKABLE});
      BREWING = new EN(10, "brewing") {
         public Qy createIcon() {
            return Wg.addPotionToItemStack(new Qy(NK.POTIONITEM), NN.WATER);
         }
      };
      MATERIALS = MISC;
      HOTBAR = new EN(4, "hotbar") {
         public Qy createIcon() {
            return new Qy(Nk.BOOKSHELF);
         }

         public void displayAllRelevantItems(NonNullList<Qy> p_78018_1_) {
            throw new RuntimeException("Implement exception client-side.");
         }

         public boolean isAlignedRight() {
            return true;
         }
      };
      INVENTORY = (new EN(11, "inventory") {
         public Qy createIcon() {
            return new Qy(OL.getItemFromBlock(Nk.CHEST));
         }
      }).setBackgroundImageName("inventory.png").setNoScrollbar().setNoTitle();
   }
}
