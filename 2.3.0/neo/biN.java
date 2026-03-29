package neo;

import com.google.common.collect.AbstractIterator;
import java.util.Iterator;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class biN extends BlockPos {
   private int mx;
   private int my;
   private int mz;
   private int level;
   private biN[] facings;
   private boolean needsUpdate;

   public biN(int x, int y, int z) {
      this(x, y, z, 0);
   }

   public biN(double xIn, double yIn, double zIn) {
      this(MathHelper.floor(xIn), MathHelper.floor(yIn), MathHelper.floor(zIn));
   }

   public biN(int x, int y, int z, int level) {
      super(0, 0, 0);
      this.mx = x;
      this.my = y;
      this.mz = z;
      this.level = level;
   }

   public int getX() {
      return this.mx;
   }

   public int getY() {
      return this.my;
   }

   public int getZ() {
      return this.mz;
   }

   public void setXyz(int x, int y, int z) {
      this.mx = x;
      this.my = y;
      this.mz = z;
      this.needsUpdate = true;
   }

   public void setXyz(double xIn, double yIn, double zIn) {
      this.setXyz(MathHelper.floor(xIn), MathHelper.floor(yIn), MathHelper.floor(zIn));
   }

   public BlockPos offset(EnumFacing facing) {
      if (this.level <= 0) {
         return super.offset(facing, 1).toImmutable();
      } else {
         if (this.facings == null) {
            this.facings = new biN[EnumFacing.VALUES.length];
         }

         if (this.needsUpdate) {
            this.update();
         }

         int i = facing.getIndex();
         biN blockposm = this.facings[i];
         if (blockposm == null) {
            int j = this.mx + facing.getXOffset();
            int k = this.my + facing.getYOffset();
            int l = this.mz + facing.getZOffset();
            blockposm = new biN(j, k, l, this.level - 1);
            this.facings[i] = blockposm;
         }

         return blockposm;
      }
   }

   public BlockPos offset(EnumFacing facing, int n) {
      return n == 1 ? this.offset(facing) : super.offset(facing, n).toImmutable();
   }

   private void update() {
      for(int i = 0; i < 6; ++i) {
         biN blockposm = this.facings[i];
         if (blockposm != null) {
            EnumFacing enumfacing = EnumFacing.VALUES[i];
            int j = this.mx + enumfacing.getXOffset();
            int k = this.my + enumfacing.getYOffset();
            int l = this.mz + enumfacing.getZOffset();
            blockposm.setXyz(j, k, l);
         }
      }

      this.needsUpdate = false;
   }

   public BlockPos toImmutable() {
      return new BlockPos(this.mx, this.my, this.mz);
   }

   public static Iterable getAllInBoxMutable(BlockPos from, BlockPos to) {
      final BlockPos blockpos = new BlockPos(Math.min(from.getX(), to.getX()), Math.min(from.getY(), to.getY()), Math.min(from.getZ(), to.getZ()));
      final BlockPos blockpos1 = new BlockPos(Math.max(from.getX(), to.getX()), Math.max(from.getY(), to.getY()), Math.max(from.getZ(), to.getZ()));
      return new Iterable() {
         public Iterator iterator() {
            return new AbstractIterator() {
               private biN theBlockPosM = null;

               protected biN computeNext0() {
                  if (this.theBlockPosM == null) {
                     this.theBlockPosM = new biN(blockpos.getX(), blockpos.getY(), blockpos.getZ(), 3);
                     return this.theBlockPosM;
                  } else if (this.theBlockPosM.equals(blockpos1)) {
                     return (biN)this.endOfData();
                  } else {
                     int i = this.theBlockPosM.getX();
                     int j = this.theBlockPosM.getY();
                     int k = this.theBlockPosM.getZ();
                     if (i < blockpos1.getX()) {
                        ++i;
                     } else if (j < blockpos1.getY()) {
                        i = blockpos.getX();
                        ++j;
                     } else if (k < blockpos1.getZ()) {
                        i = blockpos.getX();
                        j = blockpos.getY();
                        ++k;
                     }

                     this.theBlockPosM.setXyz(i, j, k);
                     return this.theBlockPosM;
                  }
               }

               protected Object computeNext() {
                  return this.computeNext0();
               }
            };
         }
      };
   }
}
