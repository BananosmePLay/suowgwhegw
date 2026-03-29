package neo;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;

class bfT {
   private final bfL templateManager;
   private final Random random;
   private int startX;
   private int startY;

   public bfT(bfL p_i47361_1_, Random p_i47361_2_) {
      this.templateManager = p_i47361_1_;
      this.random = p_i47361_2_;
   }

   public void createMansion(BlockPos p_191125_1_, Rotation p_191125_2_, List<bfR> p_191125_3_, bfQ p_191125_4_) {
      bfS woodlandmansionpieces$placementdata = new bfS();
      woodlandmansionpieces$placementdata.position = p_191125_1_;
      woodlandmansionpieces$placementdata.rotation = p_191125_2_;
      woodlandmansionpieces$placementdata.wallType = "wall_flat";
      bfS woodlandmansionpieces$placementdata1 = new bfS();
      this.entrance(p_191125_3_, woodlandmansionpieces$placementdata);
      woodlandmansionpieces$placementdata1.position = woodlandmansionpieces$placementdata.position.up(8);
      woodlandmansionpieces$placementdata1.rotation = woodlandmansionpieces$placementdata.rotation;
      woodlandmansionpieces$placementdata1.wallType = "wall_window";
      if (!p_191125_3_.isEmpty()) {
      }

      bfW woodlandmansionpieces$simplegrid = bfQ.access$400(p_191125_4_);
      bfW woodlandmansionpieces$simplegrid1 = bfQ.access$500(p_191125_4_);
      this.startX = bfQ.access$600(p_191125_4_) + 1;
      this.startY = bfQ.access$700(p_191125_4_) + 1;
      int i = bfQ.access$600(p_191125_4_) + 1;
      int j = bfQ.access$700(p_191125_4_);
      this.traverseOuterWalls(p_191125_3_, woodlandmansionpieces$placementdata, woodlandmansionpieces$simplegrid, EnumFacing.SOUTH, this.startX, this.startY, i, j);
      this.traverseOuterWalls(p_191125_3_, woodlandmansionpieces$placementdata1, woodlandmansionpieces$simplegrid, EnumFacing.SOUTH, this.startX, this.startY, i, j);
      bfS woodlandmansionpieces$placementdata2 = new bfS();
      woodlandmansionpieces$placementdata2.position = woodlandmansionpieces$placementdata.position.up(19);
      woodlandmansionpieces$placementdata2.rotation = woodlandmansionpieces$placementdata.rotation;
      woodlandmansionpieces$placementdata2.wallType = "wall_window";
      boolean flag = false;

      int l2;
      for(int k = 0; k < bfW.access$200(woodlandmansionpieces$simplegrid1) && !flag; ++k) {
         for(l2 = bfW.access$100(woodlandmansionpieces$simplegrid1) - 1; l2 >= 0 && !flag; --l2) {
            if (bfQ.isHouse(woodlandmansionpieces$simplegrid1, l2, k)) {
               woodlandmansionpieces$placementdata2.position = woodlandmansionpieces$placementdata2.position.offset(p_191125_2_.rotate(EnumFacing.SOUTH), 8 + (k - this.startY) * 8);
               woodlandmansionpieces$placementdata2.position = woodlandmansionpieces$placementdata2.position.offset(p_191125_2_.rotate(EnumFacing.EAST), (l2 - this.startX) * 8);
               this.traverseWallPiece(p_191125_3_, woodlandmansionpieces$placementdata2);
               this.traverseOuterWalls(p_191125_3_, woodlandmansionpieces$placementdata2, woodlandmansionpieces$simplegrid1, EnumFacing.SOUTH, l2, k, l2, k);
               flag = true;
            }
         }
      }

      this.createRoof(p_191125_3_, p_191125_1_.up(16), p_191125_2_, woodlandmansionpieces$simplegrid, woodlandmansionpieces$simplegrid1);
      this.createRoof(p_191125_3_, p_191125_1_.up(27), p_191125_2_, woodlandmansionpieces$simplegrid1, (bfW)null);
      if (!p_191125_3_.isEmpty()) {
      }

      bfU[] awoodlandmansionpieces$roomcollection = new bfU[]{new bfP(), new bfV(), new bfX()};

      for(l2 = 0; l2 < 3; ++l2) {
         BlockPos blockpos = p_191125_1_.up(8 * l2 + (l2 == 2 ? 3 : 0));
         bfW woodlandmansionpieces$simplegrid2 = bfQ.access$1100(p_191125_4_)[l2];
         bfW woodlandmansionpieces$simplegrid3 = l2 == 2 ? woodlandmansionpieces$simplegrid1 : woodlandmansionpieces$simplegrid;
         String s = l2 == 0 ? "carpet_south" : "carpet_south_2";
         String s1 = l2 == 0 ? "carpet_west" : "carpet_west_2";

         for(int i1 = 0; i1 < bfW.access$200(woodlandmansionpieces$simplegrid3); ++i1) {
            for(int j1 = 0; j1 < bfW.access$100(woodlandmansionpieces$simplegrid3); ++j1) {
               if (woodlandmansionpieces$simplegrid3.get(j1, i1) == 1) {
                  BlockPos blockpos1 = blockpos.offset(p_191125_2_.rotate(EnumFacing.SOUTH), 8 + (i1 - this.startY) * 8);
                  blockpos1 = blockpos1.offset(p_191125_2_.rotate(EnumFacing.EAST), (j1 - this.startX) * 8);
                  p_191125_3_.add(new bfR(this.templateManager, "corridor_floor", blockpos1, p_191125_2_));
                  if (woodlandmansionpieces$simplegrid3.get(j1, i1 - 1) == 1 || (woodlandmansionpieces$simplegrid2.get(j1, i1 - 1) & 8388608) == 8388608) {
                     p_191125_3_.add(new bfR(this.templateManager, "carpet_north", blockpos1.offset(p_191125_2_.rotate(EnumFacing.EAST), 1).up(), p_191125_2_));
                  }

                  if (woodlandmansionpieces$simplegrid3.get(j1 + 1, i1) == 1 || (woodlandmansionpieces$simplegrid2.get(j1 + 1, i1) & 8388608) == 8388608) {
                     p_191125_3_.add(new bfR(this.templateManager, "carpet_east", blockpos1.offset(p_191125_2_.rotate(EnumFacing.SOUTH), 1).offset(p_191125_2_.rotate(EnumFacing.EAST), 5).up(), p_191125_2_));
                  }

                  if (woodlandmansionpieces$simplegrid3.get(j1, i1 + 1) == 1 || (woodlandmansionpieces$simplegrid2.get(j1, i1 + 1) & 8388608) == 8388608) {
                     p_191125_3_.add(new bfR(this.templateManager, s, blockpos1.offset(p_191125_2_.rotate(EnumFacing.SOUTH), 5).offset(p_191125_2_.rotate(EnumFacing.WEST), 1), p_191125_2_));
                  }

                  if (woodlandmansionpieces$simplegrid3.get(j1 - 1, i1) == 1 || (woodlandmansionpieces$simplegrid2.get(j1 - 1, i1) & 8388608) == 8388608) {
                     p_191125_3_.add(new bfR(this.templateManager, s1, blockpos1.offset(p_191125_2_.rotate(EnumFacing.WEST), 1).offset(p_191125_2_.rotate(EnumFacing.NORTH), 1), p_191125_2_));
                  }
               }
            }
         }

         String s2 = l2 == 0 ? "indoors_wall" : "indoors_wall_2";
         String s3 = l2 == 0 ? "indoors_door" : "indoors_door_2";
         List<EnumFacing> list = Lists.newArrayList();

         for(int k1 = 0; k1 < bfW.access$200(woodlandmansionpieces$simplegrid3); ++k1) {
            for(int l1 = 0; l1 < bfW.access$100(woodlandmansionpieces$simplegrid3); ++l1) {
               boolean flag1 = l2 == 2 && woodlandmansionpieces$simplegrid3.get(l1, k1) == 3;
               if (woodlandmansionpieces$simplegrid3.get(l1, k1) == 2 || flag1) {
                  int i2 = woodlandmansionpieces$simplegrid2.get(l1, k1);
                  int j2 = i2 & 983040;
                  int k2 = i2 & '\uffff';
                  flag1 = flag1 && (i2 & 8388608) == 8388608;
                  list.clear();
                  if ((i2 & 2097152) == 2097152) {
                     EnumFacing[] var29 = EnumFacing.Plane.HORIZONTAL.facings();
                     int var30 = var29.length;

                     for(int var31 = 0; var31 < var30; ++var31) {
                        EnumFacing enumfacing = var29[var31];
                        if (woodlandmansionpieces$simplegrid3.get(l1 + enumfacing.getXOffset(), k1 + enumfacing.getZOffset()) == 1) {
                           list.add(enumfacing);
                        }
                     }
                  }

                  EnumFacing enumfacing1 = null;
                  if (!list.isEmpty()) {
                     enumfacing1 = (EnumFacing)list.get(this.random.nextInt(list.size()));
                  } else if ((i2 & 1048576) == 1048576) {
                     enumfacing1 = EnumFacing.UP;
                  }

                  BlockPos blockpos2 = blockpos.offset(p_191125_2_.rotate(EnumFacing.SOUTH), 8 + (k1 - this.startY) * 8);
                  blockpos2 = blockpos2.offset(p_191125_2_.rotate(EnumFacing.EAST), -1 + (l1 - this.startX) * 8);
                  if (bfQ.isHouse(woodlandmansionpieces$simplegrid3, l1 - 1, k1) && !p_191125_4_.isRoomId(woodlandmansionpieces$simplegrid3, l1 - 1, k1, l2, k2)) {
                     p_191125_3_.add(new bfR(this.templateManager, enumfacing1 == EnumFacing.WEST ? s3 : s2, blockpos2, p_191125_2_));
                  }

                  BlockPos blockpos5;
                  if (woodlandmansionpieces$simplegrid3.get(l1 + 1, k1) == 1 && !flag1) {
                     blockpos5 = blockpos2.offset(p_191125_2_.rotate(EnumFacing.EAST), 8);
                     p_191125_3_.add(new bfR(this.templateManager, enumfacing1 == EnumFacing.EAST ? s3 : s2, blockpos5, p_191125_2_));
                  }

                  if (bfQ.isHouse(woodlandmansionpieces$simplegrid3, l1, k1 + 1) && !p_191125_4_.isRoomId(woodlandmansionpieces$simplegrid3, l1, k1 + 1, l2, k2)) {
                     blockpos5 = blockpos2.offset(p_191125_2_.rotate(EnumFacing.SOUTH), 7);
                     blockpos5 = blockpos5.offset(p_191125_2_.rotate(EnumFacing.EAST), 7);
                     p_191125_3_.add(new bfR(this.templateManager, enumfacing1 == EnumFacing.SOUTH ? s3 : s2, blockpos5, p_191125_2_.add(Rotation.CLOCKWISE_90)));
                  }

                  if (woodlandmansionpieces$simplegrid3.get(l1, k1 - 1) == 1 && !flag1) {
                     blockpos5 = blockpos2.offset(p_191125_2_.rotate(EnumFacing.NORTH), 1);
                     blockpos5 = blockpos5.offset(p_191125_2_.rotate(EnumFacing.EAST), 7);
                     p_191125_3_.add(new bfR(this.templateManager, enumfacing1 == EnumFacing.NORTH ? s3 : s2, blockpos5, p_191125_2_.add(Rotation.CLOCKWISE_90)));
                  }

                  if (j2 == 65536) {
                     this.addRoom1x1(p_191125_3_, blockpos2, p_191125_2_, enumfacing1, awoodlandmansionpieces$roomcollection[l2]);
                  } else {
                     EnumFacing enumfacing2;
                     if (j2 == 131072 && enumfacing1 != null) {
                        enumfacing2 = p_191125_4_.get1x2RoomDirection(woodlandmansionpieces$simplegrid3, l1, k1, l2, k2);
                        boolean flag2 = (i2 & 4194304) == 4194304;
                        this.addRoom1x2(p_191125_3_, blockpos2, p_191125_2_, enumfacing2, enumfacing1, awoodlandmansionpieces$roomcollection[l2], flag2);
                     } else if (j2 == 262144 && enumfacing1 != null && enumfacing1 != EnumFacing.UP) {
                        enumfacing2 = enumfacing1.rotateY();
                        if (!p_191125_4_.isRoomId(woodlandmansionpieces$simplegrid3, l1 + enumfacing2.getXOffset(), k1 + enumfacing2.getZOffset(), l2, k2)) {
                           enumfacing2 = enumfacing2.getOpposite();
                        }

                        this.addRoom2x2(p_191125_3_, blockpos2, p_191125_2_, enumfacing2, enumfacing1, awoodlandmansionpieces$roomcollection[l2]);
                     } else if (j2 == 262144 && enumfacing1 == EnumFacing.UP) {
                        this.addRoom2x2Secret(p_191125_3_, blockpos2, p_191125_2_, awoodlandmansionpieces$roomcollection[l2]);
                     }
                  }
               }
            }
         }
      }

   }

   private void traverseOuterWalls(List<bfR> p_191130_1_, bfS p_191130_2_, bfW p_191130_3_, EnumFacing p_191130_4_, int p_191130_5_, int p_191130_6_, int p_191130_7_, int p_191130_8_) {
      int i = p_191130_5_;
      int j = p_191130_6_;
      EnumFacing enumfacing = p_191130_4_;

      do {
         if (!bfQ.isHouse(p_191130_3_, i + p_191130_4_.getXOffset(), j + p_191130_4_.getZOffset())) {
            this.traverseTurn(p_191130_1_, p_191130_2_);
            p_191130_4_ = p_191130_4_.rotateY();
            if (i != p_191130_7_ || j != p_191130_8_ || enumfacing != p_191130_4_) {
               this.traverseWallPiece(p_191130_1_, p_191130_2_);
            }
         } else if (bfQ.isHouse(p_191130_3_, i + p_191130_4_.getXOffset(), j + p_191130_4_.getZOffset()) && bfQ.isHouse(p_191130_3_, i + p_191130_4_.getXOffset() + p_191130_4_.rotateYCCW().getXOffset(), j + p_191130_4_.getZOffset() + p_191130_4_.rotateYCCW().getZOffset())) {
            this.traverseInnerTurn(p_191130_1_, p_191130_2_);
            i += p_191130_4_.getXOffset();
            j += p_191130_4_.getZOffset();
            p_191130_4_ = p_191130_4_.rotateYCCW();
         } else {
            i += p_191130_4_.getXOffset();
            j += p_191130_4_.getZOffset();
            if (i != p_191130_7_ || j != p_191130_8_ || enumfacing != p_191130_4_) {
               this.traverseWallPiece(p_191130_1_, p_191130_2_);
            }
         }
      } while(i != p_191130_7_ || j != p_191130_8_ || enumfacing != p_191130_4_);

   }

   private void createRoof(List<bfR> p_191123_1_, BlockPos p_191123_2_, Rotation p_191123_3_, bfW p_191123_4_, @Nullable bfW p_191123_5_) {
      int k;
      int i1;
      BlockPos blockpos3;
      boolean flag2;
      BlockPos blockpos15;
      for(k = 0; k < bfW.access$200(p_191123_4_); ++k) {
         for(i1 = 0; i1 < bfW.access$100(p_191123_4_); ++i1) {
            blockpos3 = p_191123_2_.offset(p_191123_3_.rotate(EnumFacing.SOUTH), 8 + (k - this.startY) * 8);
            blockpos3 = blockpos3.offset(p_191123_3_.rotate(EnumFacing.EAST), (i1 - this.startX) * 8);
            flag2 = p_191123_5_ != null && bfQ.isHouse(p_191123_5_, i1, k);
            if (bfQ.isHouse(p_191123_4_, i1, k) && !flag2) {
               p_191123_1_.add(new bfR(this.templateManager, "roof", blockpos3.up(3), p_191123_3_));
               if (!bfQ.isHouse(p_191123_4_, i1 + 1, k)) {
                  blockpos15 = blockpos3.offset(p_191123_3_.rotate(EnumFacing.EAST), 6);
                  p_191123_1_.add(new bfR(this.templateManager, "roof_front", blockpos15, p_191123_3_));
               }

               if (!bfQ.isHouse(p_191123_4_, i1 - 1, k)) {
                  blockpos15 = blockpos3.offset(p_191123_3_.rotate(EnumFacing.EAST), 0);
                  blockpos15 = blockpos15.offset(p_191123_3_.rotate(EnumFacing.SOUTH), 7);
                  p_191123_1_.add(new bfR(this.templateManager, "roof_front", blockpos15, p_191123_3_.add(Rotation.CLOCKWISE_180)));
               }

               if (!bfQ.isHouse(p_191123_4_, i1, k - 1)) {
                  blockpos15 = blockpos3.offset(p_191123_3_.rotate(EnumFacing.WEST), 1);
                  p_191123_1_.add(new bfR(this.templateManager, "roof_front", blockpos15, p_191123_3_.add(Rotation.COUNTERCLOCKWISE_90)));
               }

               if (!bfQ.isHouse(p_191123_4_, i1, k + 1)) {
                  blockpos15 = blockpos3.offset(p_191123_3_.rotate(EnumFacing.EAST), 6);
                  blockpos15 = blockpos15.offset(p_191123_3_.rotate(EnumFacing.SOUTH), 6);
                  p_191123_1_.add(new bfR(this.templateManager, "roof_front", blockpos15, p_191123_3_.add(Rotation.CLOCKWISE_90)));
               }
            }
         }
      }

      if (p_191123_5_ != null) {
         for(k = 0; k < bfW.access$200(p_191123_4_); ++k) {
            for(i1 = 0; i1 < bfW.access$100(p_191123_4_); ++i1) {
               blockpos3 = p_191123_2_.offset(p_191123_3_.rotate(EnumFacing.SOUTH), 8 + (k - this.startY) * 8);
               blockpos3 = blockpos3.offset(p_191123_3_.rotate(EnumFacing.EAST), (i1 - this.startX) * 8);
               flag2 = bfQ.isHouse(p_191123_5_, i1, k);
               if (bfQ.isHouse(p_191123_4_, i1, k) && flag2) {
                  if (!bfQ.isHouse(p_191123_4_, i1 + 1, k)) {
                     blockpos15 = blockpos3.offset(p_191123_3_.rotate(EnumFacing.EAST), 7);
                     p_191123_1_.add(new bfR(this.templateManager, "small_wall", blockpos15, p_191123_3_));
                  }

                  if (!bfQ.isHouse(p_191123_4_, i1 - 1, k)) {
                     blockpos15 = blockpos3.offset(p_191123_3_.rotate(EnumFacing.WEST), 1);
                     blockpos15 = blockpos15.offset(p_191123_3_.rotate(EnumFacing.SOUTH), 6);
                     p_191123_1_.add(new bfR(this.templateManager, "small_wall", blockpos15, p_191123_3_.add(Rotation.CLOCKWISE_180)));
                  }

                  if (!bfQ.isHouse(p_191123_4_, i1, k - 1)) {
                     blockpos15 = blockpos3.offset(p_191123_3_.rotate(EnumFacing.WEST), 0);
                     blockpos15 = blockpos15.offset(p_191123_3_.rotate(EnumFacing.NORTH), 1);
                     p_191123_1_.add(new bfR(this.templateManager, "small_wall", blockpos15, p_191123_3_.add(Rotation.COUNTERCLOCKWISE_90)));
                  }

                  if (!bfQ.isHouse(p_191123_4_, i1, k + 1)) {
                     blockpos15 = blockpos3.offset(p_191123_3_.rotate(EnumFacing.EAST), 6);
                     blockpos15 = blockpos15.offset(p_191123_3_.rotate(EnumFacing.SOUTH), 7);
                     p_191123_1_.add(new bfR(this.templateManager, "small_wall", blockpos15, p_191123_3_.add(Rotation.CLOCKWISE_90)));
                  }

                  if (!bfQ.isHouse(p_191123_4_, i1 + 1, k)) {
                     if (!bfQ.isHouse(p_191123_4_, i1, k - 1)) {
                        blockpos15 = blockpos3.offset(p_191123_3_.rotate(EnumFacing.EAST), 7);
                        blockpos15 = blockpos15.offset(p_191123_3_.rotate(EnumFacing.NORTH), 2);
                        p_191123_1_.add(new bfR(this.templateManager, "small_wall_corner", blockpos15, p_191123_3_));
                     }

                     if (!bfQ.isHouse(p_191123_4_, i1, k + 1)) {
                        blockpos15 = blockpos3.offset(p_191123_3_.rotate(EnumFacing.EAST), 8);
                        blockpos15 = blockpos15.offset(p_191123_3_.rotate(EnumFacing.SOUTH), 7);
                        p_191123_1_.add(new bfR(this.templateManager, "small_wall_corner", blockpos15, p_191123_3_.add(Rotation.CLOCKWISE_90)));
                     }
                  }

                  if (!bfQ.isHouse(p_191123_4_, i1 - 1, k)) {
                     if (!bfQ.isHouse(p_191123_4_, i1, k - 1)) {
                        blockpos15 = blockpos3.offset(p_191123_3_.rotate(EnumFacing.WEST), 2);
                        blockpos15 = blockpos15.offset(p_191123_3_.rotate(EnumFacing.NORTH), 1);
                        p_191123_1_.add(new bfR(this.templateManager, "small_wall_corner", blockpos15, p_191123_3_.add(Rotation.COUNTERCLOCKWISE_90)));
                     }

                     if (!bfQ.isHouse(p_191123_4_, i1, k + 1)) {
                        blockpos15 = blockpos3.offset(p_191123_3_.rotate(EnumFacing.WEST), 1);
                        blockpos15 = blockpos15.offset(p_191123_3_.rotate(EnumFacing.SOUTH), 8);
                        p_191123_1_.add(new bfR(this.templateManager, "small_wall_corner", blockpos15, p_191123_3_.add(Rotation.CLOCKWISE_180)));
                     }
                  }
               }
            }
         }
      }

      for(k = 0; k < bfW.access$200(p_191123_4_); ++k) {
         for(i1 = 0; i1 < bfW.access$100(p_191123_4_); ++i1) {
            blockpos3 = p_191123_2_.offset(p_191123_3_.rotate(EnumFacing.SOUTH), 8 + (k - this.startY) * 8);
            blockpos3 = blockpos3.offset(p_191123_3_.rotate(EnumFacing.EAST), (i1 - this.startX) * 8);
            flag2 = p_191123_5_ != null && bfQ.isHouse(p_191123_5_, i1, k);
            if (bfQ.isHouse(p_191123_4_, i1, k) && !flag2) {
               BlockPos blockpos22;
               if (!bfQ.isHouse(p_191123_4_, i1 + 1, k)) {
                  blockpos15 = blockpos3.offset(p_191123_3_.rotate(EnumFacing.EAST), 6);
                  if (!bfQ.isHouse(p_191123_4_, i1, k + 1)) {
                     blockpos22 = blockpos15.offset(p_191123_3_.rotate(EnumFacing.SOUTH), 6);
                     p_191123_1_.add(new bfR(this.templateManager, "roof_corner", blockpos22, p_191123_3_));
                  } else if (bfQ.isHouse(p_191123_4_, i1 + 1, k + 1)) {
                     blockpos22 = blockpos15.offset(p_191123_3_.rotate(EnumFacing.SOUTH), 5);
                     p_191123_1_.add(new bfR(this.templateManager, "roof_inner_corner", blockpos22, p_191123_3_));
                  }

                  if (!bfQ.isHouse(p_191123_4_, i1, k - 1)) {
                     p_191123_1_.add(new bfR(this.templateManager, "roof_corner", blockpos15, p_191123_3_.add(Rotation.COUNTERCLOCKWISE_90)));
                  } else if (bfQ.isHouse(p_191123_4_, i1 + 1, k - 1)) {
                     blockpos22 = blockpos3.offset(p_191123_3_.rotate(EnumFacing.EAST), 9);
                     blockpos22 = blockpos22.offset(p_191123_3_.rotate(EnumFacing.NORTH), 2);
                     p_191123_1_.add(new bfR(this.templateManager, "roof_inner_corner", blockpos22, p_191123_3_.add(Rotation.CLOCKWISE_90)));
                  }
               }

               if (!bfQ.isHouse(p_191123_4_, i1 - 1, k)) {
                  blockpos15 = blockpos3.offset(p_191123_3_.rotate(EnumFacing.EAST), 0);
                  blockpos15 = blockpos15.offset(p_191123_3_.rotate(EnumFacing.SOUTH), 0);
                  if (!bfQ.isHouse(p_191123_4_, i1, k + 1)) {
                     blockpos22 = blockpos15.offset(p_191123_3_.rotate(EnumFacing.SOUTH), 6);
                     p_191123_1_.add(new bfR(this.templateManager, "roof_corner", blockpos22, p_191123_3_.add(Rotation.CLOCKWISE_90)));
                  } else if (bfQ.isHouse(p_191123_4_, i1 - 1, k + 1)) {
                     blockpos22 = blockpos15.offset(p_191123_3_.rotate(EnumFacing.SOUTH), 8);
                     blockpos22 = blockpos22.offset(p_191123_3_.rotate(EnumFacing.WEST), 3);
                     p_191123_1_.add(new bfR(this.templateManager, "roof_inner_corner", blockpos22, p_191123_3_.add(Rotation.COUNTERCLOCKWISE_90)));
                  }

                  if (!bfQ.isHouse(p_191123_4_, i1, k - 1)) {
                     p_191123_1_.add(new bfR(this.templateManager, "roof_corner", blockpos15, p_191123_3_.add(Rotation.CLOCKWISE_180)));
                  } else if (bfQ.isHouse(p_191123_4_, i1 - 1, k - 1)) {
                     blockpos22 = blockpos15.offset(p_191123_3_.rotate(EnumFacing.SOUTH), 1);
                     p_191123_1_.add(new bfR(this.templateManager, "roof_inner_corner", blockpos22, p_191123_3_.add(Rotation.CLOCKWISE_180)));
                  }
               }
            }
         }
      }

   }

   private void entrance(List<bfR> p_191133_1_, bfS p_191133_2_) {
      EnumFacing enumfacing = p_191133_2_.rotation.rotate(EnumFacing.WEST);
      p_191133_1_.add(new bfR(this.templateManager, "entrance", p_191133_2_.position.offset(enumfacing, 9), p_191133_2_.rotation));
      p_191133_2_.position = p_191133_2_.position.offset(p_191133_2_.rotation.rotate(EnumFacing.SOUTH), 16);
   }

   private void traverseWallPiece(List<bfR> p_191131_1_, bfS p_191131_2_) {
      p_191131_1_.add(new bfR(this.templateManager, p_191131_2_.wallType, p_191131_2_.position.offset(p_191131_2_.rotation.rotate(EnumFacing.EAST), 7), p_191131_2_.rotation));
      p_191131_2_.position = p_191131_2_.position.offset(p_191131_2_.rotation.rotate(EnumFacing.SOUTH), 8);
   }

   private void traverseTurn(List<bfR> p_191124_1_, bfS p_191124_2_) {
      p_191124_2_.position = p_191124_2_.position.offset(p_191124_2_.rotation.rotate(EnumFacing.SOUTH), -1);
      p_191124_1_.add(new bfR(this.templateManager, "wall_corner", p_191124_2_.position, p_191124_2_.rotation));
      p_191124_2_.position = p_191124_2_.position.offset(p_191124_2_.rotation.rotate(EnumFacing.SOUTH), -7);
      p_191124_2_.position = p_191124_2_.position.offset(p_191124_2_.rotation.rotate(EnumFacing.WEST), -6);
      p_191124_2_.rotation = p_191124_2_.rotation.add(Rotation.CLOCKWISE_90);
   }

   private void traverseInnerTurn(List<bfR> p_191126_1_, bfS p_191126_2_) {
      p_191126_2_.position = p_191126_2_.position.offset(p_191126_2_.rotation.rotate(EnumFacing.SOUTH), 6);
      p_191126_2_.position = p_191126_2_.position.offset(p_191126_2_.rotation.rotate(EnumFacing.EAST), 8);
      p_191126_2_.rotation = p_191126_2_.rotation.add(Rotation.COUNTERCLOCKWISE_90);
   }

   private void addRoom1x1(List<bfR> p_191129_1_, BlockPos p_191129_2_, Rotation p_191129_3_, EnumFacing p_191129_4_, bfU p_191129_5_) {
      Rotation rotation = Rotation.NONE;
      String s = p_191129_5_.get1x1(this.random);
      if (p_191129_4_ != EnumFacing.EAST) {
         if (p_191129_4_ == EnumFacing.NORTH) {
            rotation = rotation.add(Rotation.COUNTERCLOCKWISE_90);
         } else if (p_191129_4_ == EnumFacing.WEST) {
            rotation = rotation.add(Rotation.CLOCKWISE_180);
         } else if (p_191129_4_ == EnumFacing.SOUTH) {
            rotation = rotation.add(Rotation.CLOCKWISE_90);
         } else {
            s = p_191129_5_.get1x1Secret(this.random);
         }
      }

      BlockPos blockpos = bfK.getZeroPositionWithTransform(new BlockPos(1, 0, 0), Mirror.NONE, rotation, 7, 7);
      rotation = rotation.add(p_191129_3_);
      blockpos = blockpos.rotate(p_191129_3_);
      BlockPos blockpos1 = p_191129_2_.add(blockpos.getX(), 0, blockpos.getZ());
      p_191129_1_.add(new bfR(this.templateManager, s, blockpos1, rotation));
   }

   private void addRoom1x2(List<bfR> p_191132_1_, BlockPos p_191132_2_, Rotation p_191132_3_, EnumFacing p_191132_4_, EnumFacing p_191132_5_, bfU p_191132_6_, boolean p_191132_7_) {
      BlockPos blockpos;
      if (p_191132_5_ == EnumFacing.EAST && p_191132_4_ == EnumFacing.SOUTH) {
         blockpos = p_191132_2_.offset(p_191132_3_.rotate(EnumFacing.EAST), 1);
         p_191132_1_.add(new bfR(this.templateManager, p_191132_6_.get1x2SideEntrance(this.random, p_191132_7_), blockpos, p_191132_3_));
      } else if (p_191132_5_ == EnumFacing.EAST && p_191132_4_ == EnumFacing.NORTH) {
         blockpos = p_191132_2_.offset(p_191132_3_.rotate(EnumFacing.EAST), 1);
         blockpos = blockpos.offset(p_191132_3_.rotate(EnumFacing.SOUTH), 6);
         p_191132_1_.add(new bfR(this.templateManager, p_191132_6_.get1x2SideEntrance(this.random, p_191132_7_), blockpos, p_191132_3_, Mirror.LEFT_RIGHT));
      } else if (p_191132_5_ == EnumFacing.WEST && p_191132_4_ == EnumFacing.NORTH) {
         blockpos = p_191132_2_.offset(p_191132_3_.rotate(EnumFacing.EAST), 7);
         blockpos = blockpos.offset(p_191132_3_.rotate(EnumFacing.SOUTH), 6);
         p_191132_1_.add(new bfR(this.templateManager, p_191132_6_.get1x2SideEntrance(this.random, p_191132_7_), blockpos, p_191132_3_.add(Rotation.CLOCKWISE_180)));
      } else if (p_191132_5_ == EnumFacing.WEST && p_191132_4_ == EnumFacing.SOUTH) {
         blockpos = p_191132_2_.offset(p_191132_3_.rotate(EnumFacing.EAST), 7);
         p_191132_1_.add(new bfR(this.templateManager, p_191132_6_.get1x2SideEntrance(this.random, p_191132_7_), blockpos, p_191132_3_, Mirror.FRONT_BACK));
      } else if (p_191132_5_ == EnumFacing.SOUTH && p_191132_4_ == EnumFacing.EAST) {
         blockpos = p_191132_2_.offset(p_191132_3_.rotate(EnumFacing.EAST), 1);
         p_191132_1_.add(new bfR(this.templateManager, p_191132_6_.get1x2SideEntrance(this.random, p_191132_7_), blockpos, p_191132_3_.add(Rotation.CLOCKWISE_90), Mirror.LEFT_RIGHT));
      } else if (p_191132_5_ == EnumFacing.SOUTH && p_191132_4_ == EnumFacing.WEST) {
         blockpos = p_191132_2_.offset(p_191132_3_.rotate(EnumFacing.EAST), 7);
         p_191132_1_.add(new bfR(this.templateManager, p_191132_6_.get1x2SideEntrance(this.random, p_191132_7_), blockpos, p_191132_3_.add(Rotation.CLOCKWISE_90)));
      } else if (p_191132_5_ == EnumFacing.NORTH && p_191132_4_ == EnumFacing.WEST) {
         blockpos = p_191132_2_.offset(p_191132_3_.rotate(EnumFacing.EAST), 7);
         blockpos = blockpos.offset(p_191132_3_.rotate(EnumFacing.SOUTH), 6);
         p_191132_1_.add(new bfR(this.templateManager, p_191132_6_.get1x2SideEntrance(this.random, p_191132_7_), blockpos, p_191132_3_.add(Rotation.CLOCKWISE_90), Mirror.FRONT_BACK));
      } else if (p_191132_5_ == EnumFacing.NORTH && p_191132_4_ == EnumFacing.EAST) {
         blockpos = p_191132_2_.offset(p_191132_3_.rotate(EnumFacing.EAST), 1);
         blockpos = blockpos.offset(p_191132_3_.rotate(EnumFacing.SOUTH), 6);
         p_191132_1_.add(new bfR(this.templateManager, p_191132_6_.get1x2SideEntrance(this.random, p_191132_7_), blockpos, p_191132_3_.add(Rotation.COUNTERCLOCKWISE_90)));
      } else if (p_191132_5_ == EnumFacing.SOUTH && p_191132_4_ == EnumFacing.NORTH) {
         blockpos = p_191132_2_.offset(p_191132_3_.rotate(EnumFacing.EAST), 1);
         blockpos = blockpos.offset(p_191132_3_.rotate(EnumFacing.NORTH), 8);
         p_191132_1_.add(new bfR(this.templateManager, p_191132_6_.get1x2FrontEntrance(this.random, p_191132_7_), blockpos, p_191132_3_));
      } else if (p_191132_5_ == EnumFacing.NORTH && p_191132_4_ == EnumFacing.SOUTH) {
         blockpos = p_191132_2_.offset(p_191132_3_.rotate(EnumFacing.EAST), 7);
         blockpos = blockpos.offset(p_191132_3_.rotate(EnumFacing.SOUTH), 14);
         p_191132_1_.add(new bfR(this.templateManager, p_191132_6_.get1x2FrontEntrance(this.random, p_191132_7_), blockpos, p_191132_3_.add(Rotation.CLOCKWISE_180)));
      } else if (p_191132_5_ == EnumFacing.WEST && p_191132_4_ == EnumFacing.EAST) {
         blockpos = p_191132_2_.offset(p_191132_3_.rotate(EnumFacing.EAST), 15);
         p_191132_1_.add(new bfR(this.templateManager, p_191132_6_.get1x2FrontEntrance(this.random, p_191132_7_), blockpos, p_191132_3_.add(Rotation.CLOCKWISE_90)));
      } else if (p_191132_5_ == EnumFacing.EAST && p_191132_4_ == EnumFacing.WEST) {
         blockpos = p_191132_2_.offset(p_191132_3_.rotate(EnumFacing.WEST), 7);
         blockpos = blockpos.offset(p_191132_3_.rotate(EnumFacing.SOUTH), 6);
         p_191132_1_.add(new bfR(this.templateManager, p_191132_6_.get1x2FrontEntrance(this.random, p_191132_7_), blockpos, p_191132_3_.add(Rotation.COUNTERCLOCKWISE_90)));
      } else if (p_191132_5_ == EnumFacing.UP && p_191132_4_ == EnumFacing.EAST) {
         blockpos = p_191132_2_.offset(p_191132_3_.rotate(EnumFacing.EAST), 15);
         p_191132_1_.add(new bfR(this.templateManager, p_191132_6_.get1x2Secret(this.random), blockpos, p_191132_3_.add(Rotation.CLOCKWISE_90)));
      } else if (p_191132_5_ == EnumFacing.UP && p_191132_4_ == EnumFacing.SOUTH) {
         blockpos = p_191132_2_.offset(p_191132_3_.rotate(EnumFacing.EAST), 1);
         blockpos = blockpos.offset(p_191132_3_.rotate(EnumFacing.NORTH), 0);
         p_191132_1_.add(new bfR(this.templateManager, p_191132_6_.get1x2Secret(this.random), blockpos, p_191132_3_));
      }

   }

   private void addRoom2x2(List<bfR> p_191127_1_, BlockPos p_191127_2_, Rotation p_191127_3_, EnumFacing p_191127_4_, EnumFacing p_191127_5_, bfU p_191127_6_) {
      int i = 0;
      int j = 0;
      Rotation rotation = p_191127_3_;
      Mirror mirror = Mirror.NONE;
      if (p_191127_5_ == EnumFacing.EAST && p_191127_4_ == EnumFacing.SOUTH) {
         i = -7;
      } else if (p_191127_5_ == EnumFacing.EAST && p_191127_4_ == EnumFacing.NORTH) {
         i = -7;
         j = 6;
         mirror = Mirror.LEFT_RIGHT;
      } else if (p_191127_5_ == EnumFacing.NORTH && p_191127_4_ == EnumFacing.EAST) {
         i = 1;
         j = 14;
         rotation = p_191127_3_.add(Rotation.COUNTERCLOCKWISE_90);
      } else if (p_191127_5_ == EnumFacing.NORTH && p_191127_4_ == EnumFacing.WEST) {
         i = 7;
         j = 14;
         rotation = p_191127_3_.add(Rotation.COUNTERCLOCKWISE_90);
         mirror = Mirror.LEFT_RIGHT;
      } else if (p_191127_5_ == EnumFacing.SOUTH && p_191127_4_ == EnumFacing.WEST) {
         i = 7;
         j = -8;
         rotation = p_191127_3_.add(Rotation.CLOCKWISE_90);
      } else if (p_191127_5_ == EnumFacing.SOUTH && p_191127_4_ == EnumFacing.EAST) {
         i = 1;
         j = -8;
         rotation = p_191127_3_.add(Rotation.CLOCKWISE_90);
         mirror = Mirror.LEFT_RIGHT;
      } else if (p_191127_5_ == EnumFacing.WEST && p_191127_4_ == EnumFacing.NORTH) {
         i = 15;
         j = 6;
         rotation = p_191127_3_.add(Rotation.CLOCKWISE_180);
      } else if (p_191127_5_ == EnumFacing.WEST && p_191127_4_ == EnumFacing.SOUTH) {
         i = 15;
         mirror = Mirror.FRONT_BACK;
      }

      BlockPos blockpos = p_191127_2_.offset(p_191127_3_.rotate(EnumFacing.EAST), i);
      blockpos = blockpos.offset(p_191127_3_.rotate(EnumFacing.SOUTH), j);
      p_191127_1_.add(new bfR(this.templateManager, p_191127_6_.get2x2(this.random), blockpos, rotation, mirror));
   }

   private void addRoom2x2Secret(List<bfR> p_191128_1_, BlockPos p_191128_2_, Rotation p_191128_3_, bfU p_191128_4_) {
      BlockPos blockpos = p_191128_2_.offset(p_191128_3_.rotate(EnumFacing.EAST), 1);
      p_191128_1_.add(new bfR(this.templateManager, p_191128_4_.get2x2Secret(this.random), blockpos, p_191128_3_, Mirror.NONE));
   }
}
