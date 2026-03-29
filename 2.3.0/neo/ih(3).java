package neo;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;

class ih extends ie {
   private final co block;
   private final ImmutableMap<hT<?>, Comparable<?>> properties;
   private ImmutableTable<hT<?>, Comparable<?>, in> propertyValueTable;

   private ih(co blockIn, ImmutableMap<hT<?>, Comparable<?>> propertiesIn) {
      this.block = blockIn;
      this.properties = propertiesIn;
   }

   protected ih(co p_i0_1_, ImmutableMap<hT<?>, Comparable<?>> p_i0_2_, ImmutableTable<hT<?>, Comparable<?>, in> p_i0_3_) {
      this.block = p_i0_1_;
      this.properties = p_i0_2_;
      this.propertyValueTable = p_i0_3_;
   }

   public Collection<hT<?>> getPropertyKeys() {
      return Collections.unmodifiableCollection(this.properties.keySet());
   }

   public <T extends Comparable<T>> T getValue(hT<T> property) {
      Comparable<?> comparable = (Comparable)this.properties.get(property);
      if (comparable == null) {
         throw new IllegalArgumentException("Cannot get property " + property + " as it does not exist in " + this.block.getBlockState());
      } else {
         return (Comparable)property.getValueClass().cast(comparable);
      }
   }

   public <T extends Comparable<T>, V extends T> in withProperty(hT<T> property, V value) {
      Comparable<?> comparable = (Comparable)this.properties.get(property);
      if (comparable == null) {
         throw new IllegalArgumentException("Cannot set property " + property + " as it does not exist in " + this.block.getBlockState());
      } else if (comparable == value) {
         return this;
      } else {
         in iblockstate = (in)this.propertyValueTable.get(property, value);
         if (iblockstate == null) {
            throw new IllegalArgumentException("Cannot set property " + property + " to " + value + " on block " + co.REGISTRY.getNameForObject(this.block) + ", it is not an allowed value");
         } else {
            return iblockstate;
         }
      }
   }

   public ImmutableMap<hT<?>, Comparable<?>> getProperties() {
      return this.properties;
   }

   public co getBlock() {
      return this.block;
   }

   public boolean equals(Object p_equals_1_) {
      return this == p_equals_1_;
   }

   public int hashCode() {
      return this.properties.hashCode();
   }

   public void buildPropertyValueTable(Map<Map<hT<?>, Comparable<?>>, ih> map) {
      if (this.propertyValueTable != null) {
         throw new IllegalStateException();
      } else {
         Table<hT<?>, Comparable<?>, in> table = HashBasedTable.create();
         UnmodifiableIterator unmodifiableiterator = this.properties.entrySet().iterator();

         while(unmodifiableiterator.hasNext()) {
            Map.Entry<hT<?>, Comparable<?>> entry = (Map.Entry)unmodifiableiterator.next();
            hT<?> iproperty = (hT)entry.getKey();
            Iterator var6 = iproperty.getAllowedValues().iterator();

            while(var6.hasNext()) {
               Comparable<?> comparable = (Comparable)var6.next();
               if (comparable != entry.getValue()) {
                  table.put(iproperty, comparable, map.get(this.getPropertiesWithValue(iproperty, comparable)));
               }
            }
         }

         this.propertyValueTable = ImmutableTable.copyOf(table);
      }
   }

   private Map<hT<?>, Comparable<?>> getPropertiesWithValue(hT<?> property, Comparable<?> value) {
      Map<hT<?>, Comparable<?>> map = Maps.newHashMap(this.properties);
      map.put(property, value);
      return map;
   }

   public hM getMaterial() {
      return this.block.getMaterial(this);
   }

   public boolean isFullBlock() {
      return this.block.isFullBlock(this);
   }

   public boolean canEntitySpawn(Ig entityIn) {
      return this.block.canEntitySpawn(this, entityIn);
   }

   public int getLightOpacity() {
      return this.block.getLightOpacity(this);
   }

   public int getLightValue() {
      return this.block.getLightValue(this);
   }

   public boolean isTranslucent() {
      return this.block.isTranslucent(this);
   }

   public boolean useNeighborBrightness() {
      return this.block.getUseNeighborBrightness(this);
   }

   public hK getMapColor(bfZ p_185909_1_, BlockPos p_185909_2_) {
      return this.block.getMapColor(this, p_185909_1_, p_185909_2_);
   }

   public in withRotation(Rotation rot) {
      return this.block.withRotation(this, rot);
   }

   public in withMirror(Mirror mirrorIn) {
      return this.block.withMirror(this, mirrorIn);
   }

   public boolean isFullCube() {
      return this.block.isFullCube(this);
   }

   public boolean hasCustomBreakingProgress() {
      return this.block.hasCustomBreakingProgress(this);
   }

   public EnumBlockRenderType getRenderType() {
      return this.block.getRenderType(this);
   }

   public int getPackedLightmapCoords(bfZ source, BlockPos pos) {
      return this.block.getPackedLightmapCoords(this, source, pos);
   }

   public float getAmbientOcclusionLightValue() {
      return this.block.getAmbientOcclusionLightValue(this);
   }

   public boolean isBlockNormalCube() {
      return this.block.isBlockNormalCube(this);
   }

   public boolean isNormalCube() {
      return this.block.isNormalCube(this);
   }

   public boolean canProvidePower() {
      return this.block.canProvidePower(this);
   }

   public int getWeakPower(bfZ blockAccess, BlockPos pos, EnumFacing side) {
      return this.block.getWeakPower(this, blockAccess, pos, side);
   }

   public boolean hasComparatorInputOverride() {
      return this.block.hasComparatorInputOverride(this);
   }

   public int getComparatorInputOverride(bij worldIn, BlockPos pos) {
      return this.block.getComparatorInputOverride(this, worldIn, pos);
   }

   public float getBlockHardness(bij worldIn, BlockPos pos) {
      return this.block.getBlockHardness(this, worldIn, pos);
   }

   public float getPlayerRelativeBlockHardness(ME player, bij worldIn, BlockPos pos) {
      return this.block.getPlayerRelativeBlockHardness(this, player, worldIn, pos);
   }

   public int getStrongPower(bfZ blockAccess, BlockPos pos, EnumFacing side) {
      return this.block.getStrongPower(this, blockAccess, pos, side);
   }

   public hJ getPushReaction() {
      return this.block.getPushReaction(this);
   }

   public in getActualState(bfZ blockAccess, BlockPos pos) {
      return this.block.getActualState(this, blockAccess, pos);
   }

   public AxisAlignedBB getSelectedBoundingBox(bij worldIn, BlockPos pos) {
      return this.block.getSelectedBoundingBox(this, worldIn, pos);
   }

   public boolean shouldSideBeRendered(bfZ blockAccess, BlockPos pos, EnumFacing facing) {
      return this.block.shouldSideBeRendered(this, blockAccess, pos, facing);
   }

   public boolean isOpaqueCube() {
      return this.block.isOpaqueCube(this);
   }

   @Nullable
   public AxisAlignedBB getCollisionBoundingBox(bfZ worldIn, BlockPos pos) {
      return this.block.getCollisionBoundingBox(this, worldIn, pos);
   }

   public void addCollisionBoxToList(bij worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Ig entityIn, boolean p_185908_6_) {
      this.block.addCollisionBoxToList(this, worldIn, pos, entityBox, collidingBoxes, entityIn, p_185908_6_);
   }

   public AxisAlignedBB getBoundingBox(bfZ blockAccess, BlockPos pos) {
      cn block$enumoffsettype = this.block.getOffsetType();
      if (block$enumoffsettype != cn.NONE && !(this.block instanceof dS)) {
         AxisAlignedBB axisalignedbb = this.block.getBoundingBox(this, blockAccess, pos);
         axisalignedbb = bnc.getOffsetBoundingBox(axisalignedbb, block$enumoffsettype, pos);
         return axisalignedbb;
      } else {
         return this.block.getBoundingBox(this, blockAccess, pos);
      }
   }

   public RayTraceResult collisionRayTrace(bij worldIn, BlockPos pos, Vec3d start, Vec3d end) {
      return this.block.collisionRayTrace(this, worldIn, pos, start, end);
   }

   public boolean isTopSolid() {
      return this.block.isTopSolid(this);
   }

   public Vec3d getOffset(bfZ access, BlockPos pos) {
      return this.block.getOffset(this, access, pos);
   }

   public boolean onBlockEventReceived(bij worldIn, BlockPos pos, int id, int param) {
      return this.block.eventReceived(this, worldIn, pos, id, param);
   }

   public void neighborChanged(bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      this.block.neighborChanged(this, worldIn, pos, blockIn, fromPos);
   }

   public boolean causesSuffocation() {
      return this.block.causesSuffocation(this);
   }

   public ImmutableTable<hT<?>, Comparable<?>, in> getPropertyValueTable() {
      return this.propertyValueTable;
   }

   public int getLightOpacity(bfZ p_getLightOpacity_1_, BlockPos p_getLightOpacity_2_) {
      return bnK.callInt(this.block, bnK.ForgeBlock_getLightOpacity, this, p_getLightOpacity_1_, p_getLightOpacity_2_);
   }

   public int getLightValue(bfZ p_getLightValue_1_, BlockPos p_getLightValue_2_) {
      return bnK.callInt(this.block, bnK.ForgeBlock_getLightValue, this, p_getLightValue_1_, p_getLightValue_2_);
   }

   public boolean isSideSolid(bfZ p_isSideSolid_1_, BlockPos p_isSideSolid_2_, EnumFacing p_isSideSolid_3_) {
      return bnK.callBoolean(this.block, bnK.ForgeBlock_isSideSolid, this, p_isSideSolid_1_, p_isSideSolid_2_, p_isSideSolid_3_);
   }

   public boolean doesSideBlockChestOpening(bfZ p_doesSideBlockChestOpening_1_, BlockPos p_doesSideBlockChestOpening_2_, EnumFacing p_doesSideBlockChestOpening_3_) {
      return bnK.callBoolean(this.block, bnK.ForgeBlock_doesSideBlockChestOpening, this, p_doesSideBlockChestOpening_1_, p_doesSideBlockChestOpening_2_, p_doesSideBlockChestOpening_3_);
   }

   public boolean doesSideBlockRendering(bfZ p_doesSideBlockRendering_1_, BlockPos p_doesSideBlockRendering_2_, EnumFacing p_doesSideBlockRendering_3_) {
      return bnK.callBoolean(this.block, bnK.ForgeBlock_doesSideBlockRendering, this, p_doesSideBlockRendering_1_, p_doesSideBlockRendering_2_, p_doesSideBlockRendering_3_);
   }

   public ib getBlockFaceShape(bfZ worldIn, BlockPos pos, EnumFacing facing) {
      return this.block.getBlockFaceShape(worldIn, this, pos, facing);
   }

   // $FF: synthetic method
   ih(co x0, ImmutableMap x1, Object x2) {
      this(x0, x1);
   }
}
