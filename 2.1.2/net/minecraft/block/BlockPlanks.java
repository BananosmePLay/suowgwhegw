package net.minecraft.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockPlanks extends Block {
   public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.create("variant", EnumType.class);

   public BlockPlanks() {
      super(Material.WOOD);
      this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockPlanks.EnumType.OAK));
      this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
   }

   public int damageDropped(IBlockState state) {
      return ((EnumType)state.getValue(VARIANT)).getMetadata();
   }

   public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
      EnumType[] var3 = BlockPlanks.EnumType.values();
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         EnumType blockplanks$enumtype = var3[var5];
         items.add(new ItemStack(this, 1, blockplanks$enumtype.getMetadata()));
      }

   }

   public IBlockState getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(VARIANT, BlockPlanks.EnumType.byMetadata(meta));
   }

   /** @deprecated */
   public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
      return ((EnumType)state.getValue(VARIANT)).getMapColor();
   }

   public int getMetaFromState(IBlockState state) {
      return ((EnumType)state.getValue(VARIANT)).getMetadata();
   }

   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, new IProperty[]{VARIANT});
   }

   public static enum EnumType implements IStringSerializable {
      OAK(0, "oak", MapColor.WOOD),
      SPRUCE(1, "spruce", MapColor.OBSIDIAN),
      BIRCH(2, "birch", MapColor.SAND),
      JUNGLE(3, "jungle", MapColor.DIRT),
      ACACIA(4, "acacia", MapColor.ADOBE),
      DARK_OAK(5, "dark_oak", "big_oak", MapColor.BROWN);

      private static final EnumType[] META_LOOKUP = new EnumType[values().length];
      private final int meta;
      private final String name;
      private final String translationKey;
      private final MapColor mapColor;

      private EnumType(int metaIn, String nameIn, MapColor mapColorIn) {
         this(metaIn, nameIn, nameIn, mapColorIn);
      }

      private EnumType(int metaIn, String nameIn, String unlocalizedNameIn, MapColor mapColorIn) {
         this.meta = metaIn;
         this.name = nameIn;
         this.translationKey = unlocalizedNameIn;
         this.mapColor = mapColorIn;
      }

      public int getMetadata() {
         return this.meta;
      }

      public MapColor getMapColor() {
         return this.mapColor;
      }

      public String toString() {
         return this.name;
      }

      public static EnumType byMetadata(int meta) {
         if (meta < 0 || meta >= META_LOOKUP.length) {
            meta = 0;
         }

         return META_LOOKUP[meta];
      }

      public String getName() {
         return this.name;
      }

      public String getTranslationKey() {
         return this.translationKey;
      }

      static {
         EnumType[] var0 = values();
         int var1 = var0.length;

         for(int var2 = 0; var2 < var1; ++var2) {
            EnumType blockplanks$enumtype = var0[var2];
            META_LOOKUP[blockplanks$enumtype.getMetadata()] = blockplanks$enumtype;
         }

      }
   }
}
