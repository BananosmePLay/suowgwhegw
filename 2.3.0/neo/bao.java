package neo;

import com.google.common.base.Predicate;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class bao extends bam {
   public bao(bij worldIn, int x, int z) {
      super(worldIn, x, z);
   }

   public boolean isAtLocation(int x, int z) {
      return x == this.x && z == this.z;
   }

   public int getHeightValue(int x, int z) {
      return 0;
   }

   public void generateHeightMap() {
   }

   public void generateSkylightMap() {
   }

   public in getBlockState(BlockPos pos) {
      return Nk.AIR.getDefaultState();
   }

   public int getBlockLightOpacity(BlockPos pos) {
      return 255;
   }

   public int getLightFor(baW type, BlockPos pos) {
      return type.defaultLightValue;
   }

   public void setLightFor(baW type, BlockPos pos, int value) {
   }

   public int getLightSubtracted(BlockPos pos, int amount) {
      return 0;
   }

   public void addEntity(Ig entityIn) {
   }

   public void removeEntity(Ig entityIn) {
   }

   public void removeEntityAtIndex(Ig entityIn, int index) {
   }

   public boolean canSeeSky(BlockPos pos) {
      return false;
   }

   @Nullable
   public Yg getTileEntity(BlockPos pos, bal creationMode) {
      return null;
   }

   public void addTileEntity(Yg tileEntityIn) {
   }

   public void addTileEntity(BlockPos pos, Yg tileEntityIn) {
   }

   public void removeTileEntity(BlockPos pos) {
   }

   public void onLoad() {
   }

   public void onUnload() {
   }

   public void markDirty() {
   }

   public void getEntitiesWithinAABBForEntity(@Nullable Ig entityIn, AxisAlignedBB aabb, List<Ig> listToFill, Predicate<? super Ig> filter) {
   }

   public <T extends Ig> void getEntitiesOfTypeWithinAABB(Class<? extends T> entityClass, AxisAlignedBB aabb, List<T> listToFill, Predicate<? super T> filter) {
   }

   public boolean needsSaving(boolean p_76601_1_) {
      return false;
   }

   public Random getRandomWithSeed(long seed) {
      return new Random(this.getWorld().getSeed() + (long)(this.x * this.x * 4987142) + (long)(this.x * 5947611) + (long)(this.z * this.z) * 4392871L + (long)(this.z * 389711) ^ seed);
   }

   public boolean isEmpty() {
      return true;
   }

   public boolean isEmptyBetween(int startY, int endY) {
      return true;
   }
}
