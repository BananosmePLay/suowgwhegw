package neo;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Tuple;

class bfQ {
   private final Random random;
   private final bfW baseGrid;
   private final bfW thirdFloorGrid;
   private final bfW[] floorRooms;
   private final int entranceX;
   private final int entranceY;

   public bfQ(Random randomIn) {
      this.random = randomIn;
      int i = true;
      this.entranceX = 7;
      this.entranceY = 4;
      this.baseGrid = new bfW(11, 11, 5);
      this.baseGrid.set(this.entranceX, this.entranceY, this.entranceX + 1, this.entranceY + 1, 3);
      this.baseGrid.set(this.entranceX - 1, this.entranceY, this.entranceX - 1, this.entranceY + 1, 2);
      this.baseGrid.set(this.entranceX + 2, this.entranceY - 2, this.entranceX + 3, this.entranceY + 3, 5);
      this.baseGrid.set(this.entranceX + 1, this.entranceY - 2, this.entranceX + 1, this.entranceY - 1, 1);
      this.baseGrid.set(this.entranceX + 1, this.entranceY + 2, this.entranceX + 1, this.entranceY + 3, 1);
      this.baseGrid.set(this.entranceX - 1, this.entranceY - 1, 1);
      this.baseGrid.set(this.entranceX - 1, this.entranceY + 2, 1);
      this.baseGrid.set(0, 0, 11, 1, 5);
      this.baseGrid.set(0, 9, 11, 11, 5);
      this.recursiveCorridor(this.baseGrid, this.entranceX, this.entranceY - 2, EnumFacing.WEST, 6);
      this.recursiveCorridor(this.baseGrid, this.entranceX, this.entranceY + 3, EnumFacing.WEST, 6);
      this.recursiveCorridor(this.baseGrid, this.entranceX - 2, this.entranceY - 1, EnumFacing.WEST, 3);
      this.recursiveCorridor(this.baseGrid, this.entranceX - 2, this.entranceY + 2, EnumFacing.WEST, 3);

      while(this.cleanEdges(this.baseGrid)) {
      }

      this.floorRooms = new bfW[3];
      this.floorRooms[0] = new bfW(11, 11, 5);
      this.floorRooms[1] = new bfW(11, 11, 5);
      this.floorRooms[2] = new bfW(11, 11, 5);
      this.identifyRooms(this.baseGrid, this.floorRooms[0]);
      this.identifyRooms(this.baseGrid, this.floorRooms[1]);
      this.floorRooms[0].set(this.entranceX + 1, this.entranceY, this.entranceX + 1, this.entranceY + 1, 8388608);
      this.floorRooms[1].set(this.entranceX + 1, this.entranceY, this.entranceX + 1, this.entranceY + 1, 8388608);
      this.thirdFloorGrid = new bfW(bfW.access$100(this.baseGrid), bfW.access$200(this.baseGrid), 5);
      this.setupThirdFloor();
      this.identifyRooms(this.thirdFloorGrid, this.floorRooms[2]);
   }

   public static boolean isHouse(bfW p_191109_0_, int p_191109_1_, int p_191109_2_) {
      int i = p_191109_0_.get(p_191109_1_, p_191109_2_);
      return i == 1 || i == 2 || i == 3 || i == 4;
   }

   public boolean isRoomId(bfW p_191114_1_, int p_191114_2_, int p_191114_3_, int p_191114_4_, int p_191114_5_) {
      return (this.floorRooms[p_191114_4_].get(p_191114_2_, p_191114_3_) & '\uffff') == p_191114_5_;
   }

   @Nullable
   public EnumFacing get1x2RoomDirection(bfW p_191113_1_, int p_191113_2_, int p_191113_3_, int p_191113_4_, int p_191113_5_) {
      EnumFacing[] var6 = EnumFacing.Plane.HORIZONTAL.facings();
      int var7 = var6.length;

      for(int var8 = 0; var8 < var7; ++var8) {
         EnumFacing enumfacing = var6[var8];
         if (this.isRoomId(p_191113_1_, p_191113_2_ + enumfacing.getXOffset(), p_191113_3_ + enumfacing.getZOffset(), p_191113_4_, p_191113_5_)) {
            return enumfacing;
         }
      }

      return null;
   }

   private void recursiveCorridor(bfW p_191110_1_, int p_191110_2_, int p_191110_3_, EnumFacing p_191110_4_, int p_191110_5_) {
      if (p_191110_5_ > 0) {
         p_191110_1_.set(p_191110_2_, p_191110_3_, 1);
         p_191110_1_.setIf(p_191110_2_ + p_191110_4_.getXOffset(), p_191110_3_ + p_191110_4_.getZOffset(), 0, 1);

         EnumFacing enumfacing;
         for(int i = 0; i < 8; ++i) {
            enumfacing = EnumFacing.byHorizontalIndex(this.random.nextInt(4));
            if (enumfacing != p_191110_4_.getOpposite() && (enumfacing != EnumFacing.EAST || !this.random.nextBoolean())) {
               int j = p_191110_2_ + p_191110_4_.getXOffset();
               int k = p_191110_3_ + p_191110_4_.getZOffset();
               if (p_191110_1_.get(j + enumfacing.getXOffset(), k + enumfacing.getZOffset()) == 0 && p_191110_1_.get(j + enumfacing.getXOffset() * 2, k + enumfacing.getZOffset() * 2) == 0) {
                  this.recursiveCorridor(p_191110_1_, p_191110_2_ + p_191110_4_.getXOffset() + enumfacing.getXOffset(), p_191110_3_ + p_191110_4_.getZOffset() + enumfacing.getZOffset(), enumfacing, p_191110_5_ - 1);
                  break;
               }
            }
         }

         EnumFacing enumfacing1 = p_191110_4_.rotateY();
         enumfacing = p_191110_4_.rotateYCCW();
         p_191110_1_.setIf(p_191110_2_ + enumfacing1.getXOffset(), p_191110_3_ + enumfacing1.getZOffset(), 0, 2);
         p_191110_1_.setIf(p_191110_2_ + enumfacing.getXOffset(), p_191110_3_ + enumfacing.getZOffset(), 0, 2);
         p_191110_1_.setIf(p_191110_2_ + p_191110_4_.getXOffset() + enumfacing1.getXOffset(), p_191110_3_ + p_191110_4_.getZOffset() + enumfacing1.getZOffset(), 0, 2);
         p_191110_1_.setIf(p_191110_2_ + p_191110_4_.getXOffset() + enumfacing.getXOffset(), p_191110_3_ + p_191110_4_.getZOffset() + enumfacing.getZOffset(), 0, 2);
         p_191110_1_.setIf(p_191110_2_ + p_191110_4_.getXOffset() * 2, p_191110_3_ + p_191110_4_.getZOffset() * 2, 0, 2);
         p_191110_1_.setIf(p_191110_2_ + enumfacing1.getXOffset() * 2, p_191110_3_ + enumfacing1.getZOffset() * 2, 0, 2);
         p_191110_1_.setIf(p_191110_2_ + enumfacing.getXOffset() * 2, p_191110_3_ + enumfacing.getZOffset() * 2, 0, 2);
      }

   }

   private boolean cleanEdges(bfW p_191111_1_) {
      boolean flag = false;

      for(int i = 0; i < bfW.access$200(p_191111_1_); ++i) {
         for(int j = 0; j < bfW.access$100(p_191111_1_); ++j) {
            if (p_191111_1_.get(j, i) == 0) {
               int k = 0;
               k += isHouse(p_191111_1_, j + 1, i) ? 1 : 0;
               k += isHouse(p_191111_1_, j - 1, i) ? 1 : 0;
               k += isHouse(p_191111_1_, j, i + 1) ? 1 : 0;
               k += isHouse(p_191111_1_, j, i - 1) ? 1 : 0;
               if (k >= 3) {
                  p_191111_1_.set(j, i, 2);
                  flag = true;
               } else if (k == 2) {
                  int l = 0;
                  l += isHouse(p_191111_1_, j + 1, i + 1) ? 1 : 0;
                  l += isHouse(p_191111_1_, j - 1, i + 1) ? 1 : 0;
                  l += isHouse(p_191111_1_, j + 1, i - 1) ? 1 : 0;
                  l += isHouse(p_191111_1_, j - 1, i - 1) ? 1 : 0;
                  if (l <= 1) {
                     p_191111_1_.set(j, i, 2);
                     flag = true;
                  }
               }
            }
         }
      }

      return flag;
   }

   private void setupThirdFloor() {
      List<Tuple<Integer, Integer>> list = Lists.newArrayList();
      bfW woodlandmansionpieces$simplegrid = this.floorRooms[1];

      int l1;
      int i2;
      for(int i = 0; i < bfW.access$200(this.thirdFloorGrid); ++i) {
         for(l1 = 0; l1 < bfW.access$100(this.thirdFloorGrid); ++l1) {
            int k = woodlandmansionpieces$simplegrid.get(l1, i);
            i2 = k & 983040;
            if (i2 == 131072 && (k & 2097152) == 2097152) {
               list.add(new Tuple(l1, i));
            }
         }
      }

      if (list.isEmpty()) {
         this.thirdFloorGrid.set(0, 0, bfW.access$100(this.thirdFloorGrid), bfW.access$200(this.thirdFloorGrid), 5);
      } else {
         Tuple<Integer, Integer> tuple = (Tuple)list.get(this.random.nextInt(list.size()));
         l1 = woodlandmansionpieces$simplegrid.get((Integer)tuple.getFirst(), (Integer)tuple.getSecond());
         woodlandmansionpieces$simplegrid.set((Integer)tuple.getFirst(), (Integer)tuple.getSecond(), l1 | 4194304);
         EnumFacing enumfacing1 = this.get1x2RoomDirection(this.baseGrid, (Integer)tuple.getFirst(), (Integer)tuple.getSecond(), 1, l1 & '\uffff');
         i2 = (Integer)tuple.getFirst() + enumfacing1.getXOffset();
         int i1 = (Integer)tuple.getSecond() + enumfacing1.getZOffset();

         for(int j1 = 0; j1 < bfW.access$200(this.thirdFloorGrid); ++j1) {
            for(int k1 = 0; k1 < bfW.access$100(this.thirdFloorGrid); ++k1) {
               if (!isHouse(this.baseGrid, k1, j1)) {
                  this.thirdFloorGrid.set(k1, j1, 5);
               } else if (k1 == (Integer)tuple.getFirst() && j1 == (Integer)tuple.getSecond()) {
                  this.thirdFloorGrid.set(k1, j1, 3);
               } else if (k1 == i2 && j1 == i1) {
                  this.thirdFloorGrid.set(k1, j1, 3);
                  this.floorRooms[2].set(k1, j1, 8388608);
               }
            }
         }

         List<EnumFacing> list1 = Lists.newArrayList();
         EnumFacing[] var16 = EnumFacing.Plane.HORIZONTAL.facings();
         int var10 = var16.length;

         for(int var11 = 0; var11 < var10; ++var11) {
            EnumFacing enumfacing = var16[var11];
            if (this.thirdFloorGrid.get(i2 + enumfacing.getXOffset(), i1 + enumfacing.getZOffset()) == 0) {
               list1.add(enumfacing);
            }
         }

         if (list1.isEmpty()) {
            this.thirdFloorGrid.set(0, 0, bfW.access$100(this.thirdFloorGrid), bfW.access$200(this.thirdFloorGrid), 5);
            woodlandmansionpieces$simplegrid.set((Integer)tuple.getFirst(), (Integer)tuple.getSecond(), l1);
         } else {
            EnumFacing enumfacing2 = (EnumFacing)list1.get(this.random.nextInt(list1.size()));
            this.recursiveCorridor(this.thirdFloorGrid, i2 + enumfacing2.getXOffset(), i1 + enumfacing2.getZOffset(), enumfacing2, 4);

            while(true) {
               if (this.cleanEdges(this.thirdFloorGrid)) {
                  continue;
               }
            }
         }
      }

   }

   private void identifyRooms(bfW p_191116_1_, bfW p_191116_2_) {
      List<Tuple<Integer, Integer>> list = Lists.newArrayList();

      int k3;
      for(k3 = 0; k3 < bfW.access$200(p_191116_1_); ++k3) {
         for(int j = 0; j < bfW.access$100(p_191116_1_); ++j) {
            if (p_191116_1_.get(j, k3) == 2) {
               list.add(new Tuple(j, k3));
            }
         }
      }

      Collections.shuffle(list, this.random);
      k3 = 10;
      Iterator var19 = list.iterator();

      while(true) {
         int k;
         int l;
         do {
            if (!var19.hasNext()) {
               return;
            }

            Tuple<Integer, Integer> tuple = (Tuple)var19.next();
            k = (Integer)tuple.getFirst();
            l = (Integer)tuple.getSecond();
         } while(p_191116_2_.get(k, l) != 0);

         int i1 = k;
         int j1 = k;
         int k1 = l;
         int l1 = l;
         int i2 = 65536;
         if (p_191116_2_.get(k + 1, l) == 0 && p_191116_2_.get(k, l + 1) == 0 && p_191116_2_.get(k + 1, l + 1) == 0 && p_191116_1_.get(k + 1, l) == 2 && p_191116_1_.get(k, l + 1) == 2 && p_191116_1_.get(k + 1, l + 1) == 2) {
            j1 = k + 1;
            l1 = l + 1;
            i2 = 262144;
         } else if (p_191116_2_.get(k - 1, l) == 0 && p_191116_2_.get(k, l + 1) == 0 && p_191116_2_.get(k - 1, l + 1) == 0 && p_191116_1_.get(k - 1, l) == 2 && p_191116_1_.get(k, l + 1) == 2 && p_191116_1_.get(k - 1, l + 1) == 2) {
            i1 = k - 1;
            l1 = l + 1;
            i2 = 262144;
         } else if (p_191116_2_.get(k - 1, l) == 0 && p_191116_2_.get(k, l - 1) == 0 && p_191116_2_.get(k - 1, l - 1) == 0 && p_191116_1_.get(k - 1, l) == 2 && p_191116_1_.get(k, l - 1) == 2 && p_191116_1_.get(k - 1, l - 1) == 2) {
            i1 = k - 1;
            k1 = l - 1;
            i2 = 262144;
         } else if (p_191116_2_.get(k + 1, l) == 0 && p_191116_1_.get(k + 1, l) == 2) {
            j1 = k + 1;
            i2 = 131072;
         } else if (p_191116_2_.get(k, l + 1) == 0 && p_191116_1_.get(k, l + 1) == 2) {
            l1 = l + 1;
            i2 = 131072;
         } else if (p_191116_2_.get(k - 1, l) == 0 && p_191116_1_.get(k - 1, l) == 2) {
            i1 = k - 1;
            i2 = 131072;
         } else if (p_191116_2_.get(k, l - 1) == 0 && p_191116_1_.get(k, l - 1) == 2) {
            k1 = l - 1;
            i2 = 131072;
         }

         int j2 = this.random.nextBoolean() ? i1 : j1;
         int k2 = this.random.nextBoolean() ? k1 : l1;
         int l2 = 2097152;
         if (!p_191116_1_.edgesTo(j2, k2, 1)) {
            j2 = j2 == i1 ? j1 : i1;
            k2 = k2 == k1 ? l1 : k1;
            if (!p_191116_1_.edgesTo(j2, k2, 1)) {
               k2 = k2 == k1 ? l1 : k1;
               if (!p_191116_1_.edgesTo(j2, k2, 1)) {
                  j2 = j2 == i1 ? j1 : i1;
                  k2 = k2 == k1 ? l1 : k1;
                  if (!p_191116_1_.edgesTo(j2, k2, 1)) {
                     l2 = 0;
                     j2 = i1;
                     k2 = k1;
                  }
               }
            }
         }

         for(int i3 = k1; i3 <= l1; ++i3) {
            for(int j3 = i1; j3 <= j1; ++j3) {
               if (j3 == j2 && i3 == k2) {
                  p_191116_2_.set(j3, i3, 1048576 | l2 | i2 | k3);
               } else {
                  p_191116_2_.set(j3, i3, i2 | k3);
               }
            }
         }

         ++k3;
      }
   }

   // $FF: synthetic method
   static bfW access$400(bfQ x0) {
      return x0.baseGrid;
   }

   // $FF: synthetic method
   static bfW access$500(bfQ x0) {
      return x0.thirdFloorGrid;
   }

   // $FF: synthetic method
   static int access$600(bfQ x0) {
      return x0.entranceX;
   }

   // $FF: synthetic method
   static int access$700(bfQ x0) {
      return x0.entranceY;
   }

   // $FF: synthetic method
   static bfW[] access$1100(bfQ x0) {
      return x0.floorRooms;
   }
}
