package net.minecraft.block;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import java.util.Collection;
import javax.annotation.Nullable;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public abstract class BlockFlower extends BlockBush {
   protected PropertyEnum<EnumFlowerType> type;

   protected BlockFlower() {
      this.setDefaultState(this.blockState.getBaseState().withProperty(this.getTypeProperty(), this.getBlockType() == BlockFlower.EnumFlowerColor.RED ? BlockFlower.EnumFlowerType.POPPY : BlockFlower.EnumFlowerType.DANDELION));
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      return super.getBoundingBox(state, source, pos).offset(state.getOffset(source, pos));
   }

   public int damageDropped(IBlockState state) {
      return ((EnumFlowerType)state.getValue(this.getTypeProperty())).getMeta();
   }

   public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
      EnumFlowerType[] var3 = BlockFlower.EnumFlowerType.getTypes(this.getBlockType());
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         EnumFlowerType blockflower$enumflowertype = var3[var5];
         items.add(new ItemStack(this, 1, blockflower$enumflowertype.getMeta()));
      }

   }

   public IBlockState getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(this.getTypeProperty(), BlockFlower.EnumFlowerType.getType(this.getBlockType(), meta));
   }

   public abstract EnumFlowerColor getBlockType();

   public IProperty<EnumFlowerType> getTypeProperty() {
      if (this.type == null) {
         this.type = PropertyEnum.create("type", EnumFlowerType.class, new Predicate<EnumFlowerType>() {
            public boolean apply(@Nullable EnumFlowerType p_apply_1_) {
               return p_apply_1_.getBlockType() == BlockFlower.this.getBlockType();
            }

            // $FF: synthetic method
            // $FF: bridge method
            public boolean apply(@Nullable Object var1) {
               return this.apply((EnumFlowerType)var1);
            }
         });
      }

      return this.type;
   }

   public int getMetaFromState(IBlockState state) {
      return ((EnumFlowerType)state.getValue(this.getTypeProperty())).getMeta();
   }

   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, new IProperty[]{this.getTypeProperty()});
   }

   public Block.EnumOffsetType getOffsetType() {
      return Block.EnumOffsetType.XZ;
   }

   public static enum EnumFlowerType implements IStringSerializable {
      DANDELION(BlockFlower.EnumFlowerColor.YELLOW, 0, "dandelion"),
      POPPY(BlockFlower.EnumFlowerColor.RED, 0, "poppy"),
      BLUE_ORCHID(BlockFlower.EnumFlowerColor.RED, 1, "blue_orchid", "blueOrchid"),
      ALLIUM(BlockFlower.EnumFlowerColor.RED, 2, "allium"),
      HOUSTONIA(BlockFlower.EnumFlowerColor.RED, 3, "houstonia"),
      RED_TULIP(BlockFlower.EnumFlowerColor.RED, 4, "red_tulip", "tulipRed"),
      ORANGE_TULIP(BlockFlower.EnumFlowerColor.RED, 5, "orange_tulip", "tulipOrange"),
      WHITE_TULIP(BlockFlower.EnumFlowerColor.RED, 6, "white_tulip", "tulipWhite"),
      PINK_TULIP(BlockFlower.EnumFlowerColor.RED, 7, "pink_tulip", "tulipPink"),
      OXEYE_DAISY(BlockFlower.EnumFlowerColor.RED, 8, "oxeye_daisy", "oxeyeDaisy");

      private static final EnumFlowerType[][] TYPES_FOR_BLOCK = new EnumFlowerType[BlockFlower.EnumFlowerColor.values().length][];
      private final EnumFlowerColor blockType;
      private final int meta;
      private final String name;
      private final String translationKey;

      private EnumFlowerType(EnumFlowerColor blockType, int meta, String name) {
         this(blockType, meta, name, name);
      }

      private EnumFlowerType(EnumFlowerColor blockType, int meta, String name, String unlocalizedName) {
         this.blockType = blockType;
         this.meta = meta;
         this.name = name;
         this.translationKey = unlocalizedName;
      }

      public EnumFlowerColor getBlockType() {
         return this.blockType;
      }

      public int getMeta() {
         return this.meta;
      }

      public static EnumFlowerType getType(EnumFlowerColor blockType, int meta) {
         EnumFlowerType[] ablockflower$enumflowertype = TYPES_FOR_BLOCK[blockType.ordinal()];
         if (meta < 0 || meta >= ablockflower$enumflowertype.length) {
            meta = 0;
         }

         return ablockflower$enumflowertype[meta];
      }

      public static EnumFlowerType[] getTypes(EnumFlowerColor flowerColor) {
         return TYPES_FOR_BLOCK[flowerColor.ordinal()];
      }

      public String toString() {
         return this.name;
      }

      public String getName() {
         return this.name;
      }

      public String getTranslationKey() {
         return this.translationKey;
      }

      static {
         EnumFlowerColor[] var0 = BlockFlower.EnumFlowerColor.values();
         int var1 = var0.length;

         for(int var2 = 0; var2 < var1; ++var2) {
            final EnumFlowerColor blockflower$enumflowercolor = var0[var2];
            Collection<EnumFlowerType> collection = Collections2.filter(Lists.newArrayList((Object[])values()), new Predicate<EnumFlowerType>() {
               public boolean apply(@Nullable EnumFlowerType p_apply_1_) {
                  return p_apply_1_.getBlockType() == blockflower$enumflowercolor;
               }

               // $FF: synthetic method
               // $FF: bridge method
               public boolean apply(@Nullable Object var1) {
                  return this.apply((EnumFlowerType)var1);
               }
            });
            TYPES_FOR_BLOCK[blockflower$enumflowercolor.ordinal()] = (EnumFlowerType[])((EnumFlowerType[])collection.toArray(new EnumFlowerType[collection.size()]));
         }

      }
   }

   public static enum EnumFlowerColor {
      YELLOW,
      RED;

      private EnumFlowerColor() {
      }

      public BlockFlower getBlock() {
         return this == YELLOW ? Blocks.YELLOW_FLOWER : Blocks.RED_FLOWER;
      }
   }
}
