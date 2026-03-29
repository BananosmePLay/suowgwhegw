package neo;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;

public class bex extends beB {
   private beC sourceRoom;
   private beC coreRoom;
   private final List<beB> childPieces = Lists.newArrayList();

   public bex() {
   }

   public bex(Random p_i45599_1_, int p_i45599_2_, int p_i45599_3_, EnumFacing p_i45599_4_) {
      super(0);
      this.setCoordBaseMode(p_i45599_4_);
      EnumFacing enumfacing = this.getCoordBaseMode();
      if (enumfacing.getAxis() == EnumFacing.Axis.Z) {
         this.boundingBox = new bdy(p_i45599_2_, 39, p_i45599_3_, p_i45599_2_ + 58 - 1, 61, p_i45599_3_ + 58 - 1);
      } else {
         this.boundingBox = new bdy(p_i45599_2_, 39, p_i45599_3_, p_i45599_2_ + 58 - 1, 61, p_i45599_3_ + 58 - 1);
      }

      List<beC> list = this.generateRoomGraph(p_i45599_1_);
      this.sourceRoom.claimed = true;
      this.childPieces.add(new beu(enumfacing, this.sourceRoom));
      this.childPieces.add(new bey(enumfacing, this.coreRoom, p_i45599_1_));
      List<bez> list1 = Lists.newArrayList();
      list1.add(new beH());
      list1.add(new beJ());
      list1.add(new beK());
      list1.add(new beG());
      list1.add(new beI());
      list1.add(new bew());
      list1.add(new bev());
      Iterator var8 = list.iterator();

      while(true) {
         while(true) {
            beC structureoceanmonumentpieces$roomdefinition;
            do {
               do {
                  if (!var8.hasNext()) {
                     int j = this.boundingBox.minY;
                     int k = this.getXWithOffset(9, 22);
                     int l = this.getZWithOffset(9, 22);
                     Iterator var18 = this.childPieces.iterator();

                     while(var18.hasNext()) {
                        beB structureoceanmonumentpieces$piece = (beB)var18.next();
                        structureoceanmonumentpieces$piece.getBoundingBox().offset(k, j, l);
                     }

                     bdy structureboundingbox1 = bdy.createProper(this.getXWithOffset(1, 1), this.getYWithOffset(1), this.getZWithOffset(1, 1), this.getXWithOffset(23, 21), this.getYWithOffset(8), this.getZWithOffset(23, 21));
                     bdy structureboundingbox2 = bdy.createProper(this.getXWithOffset(34, 1), this.getYWithOffset(1), this.getZWithOffset(34, 1), this.getXWithOffset(56, 21), this.getYWithOffset(8), this.getZWithOffset(56, 21));
                     bdy structureboundingbox = bdy.createProper(this.getXWithOffset(22, 22), this.getYWithOffset(13), this.getZWithOffset(22, 22), this.getXWithOffset(35, 35), this.getYWithOffset(17), this.getZWithOffset(35, 35));
                     int i = p_i45599_1_.nextInt();
                     this.childPieces.add(new beF(enumfacing, structureboundingbox1, i++));
                     this.childPieces.add(new beF(enumfacing, structureboundingbox2, i++));
                     this.childPieces.add(new beA(enumfacing, structureboundingbox));
                     return;
                  }

                  structureoceanmonumentpieces$roomdefinition = (beC)var8.next();
               } while(structureoceanmonumentpieces$roomdefinition.claimed);
            } while(structureoceanmonumentpieces$roomdefinition.isSpecial());

            Iterator lvt_10_1_ = list1.iterator();

            while(lvt_10_1_.hasNext()) {
               bez structureoceanmonumentpieces$monumentroomfithelper = (bez)lvt_10_1_.next();
               if (structureoceanmonumentpieces$monumentroomfithelper.fits(structureoceanmonumentpieces$roomdefinition)) {
                  this.childPieces.add(structureoceanmonumentpieces$monumentroomfithelper.create(enumfacing, structureoceanmonumentpieces$roomdefinition, p_i45599_1_));
                  break;
               }
            }
         }
      }
   }

   private List<beC> generateRoomGraph(Random p_175836_1_) {
      beC[] astructureoceanmonumentpieces$roomdefinition = new beC[75];

      int k2;
      int j3;
      boolean l3;
      int l4;
      for(k2 = 0; k2 < 5; ++k2) {
         for(j3 = 0; j3 < 4; ++j3) {
            l3 = false;
            l4 = getRoomIndex(k2, 0, j3);
            astructureoceanmonumentpieces$roomdefinition[l4] = new beC(l4);
         }
      }

      for(k2 = 0; k2 < 5; ++k2) {
         for(j3 = 0; j3 < 4; ++j3) {
            l3 = true;
            l4 = getRoomIndex(k2, 1, j3);
            astructureoceanmonumentpieces$roomdefinition[l4] = new beC(l4);
         }
      }

      for(k2 = 1; k2 < 4; ++k2) {
         for(j3 = 0; j3 < 2; ++j3) {
            l3 = true;
            l4 = getRoomIndex(k2, 2, j3);
            astructureoceanmonumentpieces$roomdefinition[l4] = new beC(l4);
         }
      }

      this.sourceRoom = astructureoceanmonumentpieces$roomdefinition[GRIDROOM_SOURCE_INDEX];

      int var8;
      int var9;
      int i1;
      int l5;
      int k1;
      for(k2 = 0; k2 < 5; ++k2) {
         for(j3 = 0; j3 < 5; ++j3) {
            for(int i4 = 0; i4 < 3; ++i4) {
               l4 = getRoomIndex(k2, i4, j3);
               if (astructureoceanmonumentpieces$roomdefinition[l4] != null) {
                  EnumFacing[] var7 = EnumFacing.values();
                  var8 = var7.length;

                  for(var9 = 0; var9 < var8; ++var9) {
                     EnumFacing enumfacing = var7[var9];
                     i1 = k2 + enumfacing.getXOffset();
                     l5 = i4 + enumfacing.getYOffset();
                     k1 = j3 + enumfacing.getZOffset();
                     if (i1 >= 0 && i1 < 5 && k1 >= 0 && k1 < 5 && l5 >= 0 && l5 < 3) {
                        int l1 = getRoomIndex(i1, l5, k1);
                        if (astructureoceanmonumentpieces$roomdefinition[l1] != null) {
                           if (k1 == j3) {
                              astructureoceanmonumentpieces$roomdefinition[l4].setConnection(enumfacing, astructureoceanmonumentpieces$roomdefinition[l1]);
                           } else {
                              astructureoceanmonumentpieces$roomdefinition[l4].setConnection(enumfacing.getOpposite(), astructureoceanmonumentpieces$roomdefinition[l1]);
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      beC structureoceanmonumentpieces$roomdefinition = new beC(1003);
      beC structureoceanmonumentpieces$roomdefinition1 = new beC(1001);
      beC structureoceanmonumentpieces$roomdefinition2 = new beC(1002);
      astructureoceanmonumentpieces$roomdefinition[GRIDROOM_TOP_CONNECT_INDEX].setConnection(EnumFacing.UP, structureoceanmonumentpieces$roomdefinition);
      astructureoceanmonumentpieces$roomdefinition[GRIDROOM_LEFTWING_CONNECT_INDEX].setConnection(EnumFacing.SOUTH, structureoceanmonumentpieces$roomdefinition1);
      astructureoceanmonumentpieces$roomdefinition[GRIDROOM_RIGHTWING_CONNECT_INDEX].setConnection(EnumFacing.SOUTH, structureoceanmonumentpieces$roomdefinition2);
      structureoceanmonumentpieces$roomdefinition.claimed = true;
      structureoceanmonumentpieces$roomdefinition1.claimed = true;
      structureoceanmonumentpieces$roomdefinition2.claimed = true;
      this.sourceRoom.isSource = true;
      this.coreRoom = astructureoceanmonumentpieces$roomdefinition[getRoomIndex(p_175836_1_.nextInt(4), 0, 2)];
      this.coreRoom.claimed = true;
      this.coreRoom.connections[EnumFacing.EAST.getIndex()].claimed = true;
      this.coreRoom.connections[EnumFacing.NORTH.getIndex()].claimed = true;
      this.coreRoom.connections[EnumFacing.EAST.getIndex()].connections[EnumFacing.NORTH.getIndex()].claimed = true;
      this.coreRoom.connections[EnumFacing.UP.getIndex()].claimed = true;
      this.coreRoom.connections[EnumFacing.EAST.getIndex()].connections[EnumFacing.UP.getIndex()].claimed = true;
      this.coreRoom.connections[EnumFacing.NORTH.getIndex()].connections[EnumFacing.UP.getIndex()].claimed = true;
      this.coreRoom.connections[EnumFacing.EAST.getIndex()].connections[EnumFacing.NORTH.getIndex()].connections[EnumFacing.UP.getIndex()].claimed = true;
      List<beC> list = Lists.newArrayList();
      beC[] var20 = astructureoceanmonumentpieces$roomdefinition;
      var8 = astructureoceanmonumentpieces$roomdefinition.length;

      for(var9 = 0; var9 < var8; ++var9) {
         beC structureoceanmonumentpieces$roomdefinition4 = var20[var9];
         if (structureoceanmonumentpieces$roomdefinition4 != null) {
            structureoceanmonumentpieces$roomdefinition4.updateOpenings();
            list.add(structureoceanmonumentpieces$roomdefinition4);
         }
      }

      structureoceanmonumentpieces$roomdefinition.updateOpenings();
      Collections.shuffle(list, p_175836_1_);
      int i5 = 1;
      Iterator var22 = list.iterator();

      label93:
      while(var22.hasNext()) {
         beC structureoceanmonumentpieces$roomdefinition3 = (beC)var22.next();
         int j5 = 0;
         i1 = 0;

         while(true) {
            while(true) {
               do {
                  if (j5 >= 2 || i1 >= 5) {
                     continue label93;
                  }

                  ++i1;
                  l5 = p_175836_1_.nextInt(6);
               } while(!structureoceanmonumentpieces$roomdefinition3.hasOpening[l5]);

               k1 = EnumFacing.byIndex(l5).getOpposite().getIndex();
               structureoceanmonumentpieces$roomdefinition3.hasOpening[l5] = false;
               structureoceanmonumentpieces$roomdefinition3.connections[l5].hasOpening[k1] = false;
               if (structureoceanmonumentpieces$roomdefinition3.findSource(i5++) && structureoceanmonumentpieces$roomdefinition3.connections[l5].findSource(i5++)) {
                  ++j5;
               } else {
                  structureoceanmonumentpieces$roomdefinition3.hasOpening[l5] = true;
                  structureoceanmonumentpieces$roomdefinition3.connections[l5].hasOpening[k1] = true;
               }
            }
         }
      }

      list.add(structureoceanmonumentpieces$roomdefinition);
      list.add(structureoceanmonumentpieces$roomdefinition1);
      list.add(structureoceanmonumentpieces$roomdefinition2);
      return list;
   }

   public boolean addComponentParts(bij worldIn, Random randomIn, bdy structureBoundingBoxIn) {
      int i = Math.max(worldIn.getSeaLevel(), 64) - this.boundingBox.minY;
      this.generateWaterBox(worldIn, structureBoundingBoxIn, 0, 0, 0, 58, i, 58, false);
      this.generateWing(false, 0, worldIn, randomIn, structureBoundingBoxIn);
      this.generateWing(true, 33, worldIn, randomIn, structureBoundingBoxIn);
      this.generateEntranceArchs(worldIn, randomIn, structureBoundingBoxIn);
      this.generateEntranceWall(worldIn, randomIn, structureBoundingBoxIn);
      this.generateRoofPiece(worldIn, randomIn, structureBoundingBoxIn);
      this.generateLowerWall(worldIn, randomIn, structureBoundingBoxIn);
      this.generateMiddleWall(worldIn, randomIn, structureBoundingBoxIn);
      this.generateUpperWall(worldIn, randomIn, structureBoundingBoxIn);

      int j;
      label72:
      for(j = 0; j < 7; ++j) {
         int k = 0;

         while(true) {
            while(true) {
               if (k >= 7) {
                  continue label72;
               }

               if (k == 0 && j == 3) {
                  k = 6;
               }

               int l = j * 9;
               int i1 = k * 9;

               for(int j1 = 0; j1 < 4; ++j1) {
                  for(int k1 = 0; k1 < 4; ++k1) {
                     this.setBlockState(worldIn, BRICKS_PRISMARINE, l + j1, 0, i1 + k1, structureBoundingBoxIn);
                     this.replaceAirAndLiquidDownwards(worldIn, BRICKS_PRISMARINE, l + j1, -1, i1 + k1, structureBoundingBoxIn);
                  }
               }

               if (j != 0 && j != 6) {
                  k += 6;
               } else {
                  ++k;
               }
            }
         }
      }

      for(j = 0; j < 5; ++j) {
         this.generateWaterBox(worldIn, structureBoundingBoxIn, -1 - j, 0 + j * 2, -1 - j, -1 - j, 23, 58 + j, false);
         this.generateWaterBox(worldIn, structureBoundingBoxIn, 58 + j, 0 + j * 2, -1 - j, 58 + j, 23, 58 + j, false);
         this.generateWaterBox(worldIn, structureBoundingBoxIn, 0 - j, 0 + j * 2, -1 - j, 57 + j, 23, -1 - j, false);
         this.generateWaterBox(worldIn, structureBoundingBoxIn, 0 - j, 0 + j * 2, 58 + j, 57 + j, 23, 58 + j, false);
      }

      Iterator var11 = this.childPieces.iterator();

      while(var11.hasNext()) {
         beB structureoceanmonumentpieces$piece = (beB)var11.next();
         if (structureoceanmonumentpieces$piece.getBoundingBox().intersectsWith(structureBoundingBoxIn)) {
            structureoceanmonumentpieces$piece.addComponentParts(worldIn, randomIn, structureBoundingBoxIn);
         }
      }

      return true;
   }

   private void generateWing(boolean p_175840_1_, int p_175840_2_, bij worldIn, Random p_175840_4_, bdy p_175840_5_) {
      int i = true;
      if (this.doesChunkIntersect(p_175840_5_, p_175840_2_, 0, p_175840_2_ + 23, 20)) {
         this.fillWithBlocks(worldIn, p_175840_5_, p_175840_2_ + 0, 0, 0, p_175840_2_ + 24, 0, 20, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.generateWaterBox(worldIn, p_175840_5_, p_175840_2_ + 0, 1, 0, p_175840_2_ + 24, 10, 20, false);

         int j1;
         for(j1 = 0; j1 < 4; ++j1) {
            this.fillWithBlocks(worldIn, p_175840_5_, p_175840_2_ + j1, j1 + 1, j1, p_175840_2_ + j1, j1 + 1, 20, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(worldIn, p_175840_5_, p_175840_2_ + j1 + 7, j1 + 5, j1 + 7, p_175840_2_ + j1 + 7, j1 + 5, 20, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(worldIn, p_175840_5_, p_175840_2_ + 17 - j1, j1 + 5, j1 + 7, p_175840_2_ + 17 - j1, j1 + 5, 20, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(worldIn, p_175840_5_, p_175840_2_ + 24 - j1, j1 + 1, j1, p_175840_2_ + 24 - j1, j1 + 1, 20, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(worldIn, p_175840_5_, p_175840_2_ + j1 + 1, j1 + 1, j1, p_175840_2_ + 23 - j1, j1 + 1, j1, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(worldIn, p_175840_5_, p_175840_2_ + j1 + 8, j1 + 5, j1 + 7, p_175840_2_ + 16 - j1, j1 + 5, j1 + 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         }

         this.fillWithBlocks(worldIn, p_175840_5_, p_175840_2_ + 4, 4, 4, p_175840_2_ + 6, 4, 20, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.fillWithBlocks(worldIn, p_175840_5_, p_175840_2_ + 7, 4, 4, p_175840_2_ + 17, 4, 6, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.fillWithBlocks(worldIn, p_175840_5_, p_175840_2_ + 18, 4, 4, p_175840_2_ + 20, 4, 20, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.fillWithBlocks(worldIn, p_175840_5_, p_175840_2_ + 11, 8, 11, p_175840_2_ + 13, 8, 20, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.setBlockState(worldIn, DOT_DECO_DATA, p_175840_2_ + 12, 9, 12, p_175840_5_);
         this.setBlockState(worldIn, DOT_DECO_DATA, p_175840_2_ + 12, 9, 15, p_175840_5_);
         this.setBlockState(worldIn, DOT_DECO_DATA, p_175840_2_ + 12, 9, 18, p_175840_5_);
         j1 = p_175840_2_ + (p_175840_1_ ? 19 : 5);
         int k = p_175840_2_ + (p_175840_1_ ? 5 : 19);

         int l1;
         for(l1 = 20; l1 >= 5; l1 -= 3) {
            this.setBlockState(worldIn, DOT_DECO_DATA, j1, 5, l1, p_175840_5_);
         }

         for(l1 = 19; l1 >= 7; l1 -= 3) {
            this.setBlockState(worldIn, DOT_DECO_DATA, k, 5, l1, p_175840_5_);
         }

         for(l1 = 0; l1 < 4; ++l1) {
            int i1 = p_175840_1_ ? p_175840_2_ + (24 - (17 - l1 * 3)) : p_175840_2_ + 17 - l1 * 3;
            this.setBlockState(worldIn, DOT_DECO_DATA, i1, 5, 5, p_175840_5_);
         }

         this.setBlockState(worldIn, DOT_DECO_DATA, k, 5, 5, p_175840_5_);
         this.fillWithBlocks(worldIn, p_175840_5_, p_175840_2_ + 11, 1, 12, p_175840_2_ + 13, 7, 12, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.fillWithBlocks(worldIn, p_175840_5_, p_175840_2_ + 12, 1, 11, p_175840_2_ + 12, 7, 13, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
      }

   }

   private void generateEntranceArchs(bij worldIn, Random p_175839_2_, bdy p_175839_3_) {
      if (this.doesChunkIntersect(p_175839_3_, 22, 5, 35, 17)) {
         this.generateWaterBox(worldIn, p_175839_3_, 25, 0, 0, 32, 8, 20, false);

         for(int i = 0; i < 4; ++i) {
            this.fillWithBlocks(worldIn, p_175839_3_, 24, 2, 5 + i * 4, 24, 4, 5 + i * 4, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(worldIn, p_175839_3_, 22, 4, 5 + i * 4, 23, 4, 5 + i * 4, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.setBlockState(worldIn, BRICKS_PRISMARINE, 25, 5, 5 + i * 4, p_175839_3_);
            this.setBlockState(worldIn, BRICKS_PRISMARINE, 26, 6, 5 + i * 4, p_175839_3_);
            this.setBlockState(worldIn, SEA_LANTERN, 26, 5, 5 + i * 4, p_175839_3_);
            this.fillWithBlocks(worldIn, p_175839_3_, 33, 2, 5 + i * 4, 33, 4, 5 + i * 4, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(worldIn, p_175839_3_, 34, 4, 5 + i * 4, 35, 4, 5 + i * 4, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.setBlockState(worldIn, BRICKS_PRISMARINE, 32, 5, 5 + i * 4, p_175839_3_);
            this.setBlockState(worldIn, BRICKS_PRISMARINE, 31, 6, 5 + i * 4, p_175839_3_);
            this.setBlockState(worldIn, SEA_LANTERN, 31, 5, 5 + i * 4, p_175839_3_);
            this.fillWithBlocks(worldIn, p_175839_3_, 27, 6, 5 + i * 4, 30, 6, 5 + i * 4, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         }
      }

   }

   private void generateEntranceWall(bij worldIn, Random p_175837_2_, bdy p_175837_3_) {
      if (this.doesChunkIntersect(p_175837_3_, 15, 20, 42, 21)) {
         this.fillWithBlocks(worldIn, p_175837_3_, 15, 0, 21, 42, 0, 21, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.generateWaterBox(worldIn, p_175837_3_, 26, 1, 21, 31, 3, 21, false);
         this.fillWithBlocks(worldIn, p_175837_3_, 21, 12, 21, 36, 12, 21, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.fillWithBlocks(worldIn, p_175837_3_, 17, 11, 21, 40, 11, 21, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.fillWithBlocks(worldIn, p_175837_3_, 16, 10, 21, 41, 10, 21, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.fillWithBlocks(worldIn, p_175837_3_, 15, 7, 21, 42, 9, 21, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.fillWithBlocks(worldIn, p_175837_3_, 16, 6, 21, 41, 6, 21, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.fillWithBlocks(worldIn, p_175837_3_, 17, 5, 21, 40, 5, 21, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.fillWithBlocks(worldIn, p_175837_3_, 21, 4, 21, 36, 4, 21, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.fillWithBlocks(worldIn, p_175837_3_, 22, 3, 21, 26, 3, 21, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.fillWithBlocks(worldIn, p_175837_3_, 31, 3, 21, 35, 3, 21, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.fillWithBlocks(worldIn, p_175837_3_, 23, 2, 21, 25, 2, 21, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.fillWithBlocks(worldIn, p_175837_3_, 32, 2, 21, 34, 2, 21, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.fillWithBlocks(worldIn, p_175837_3_, 28, 4, 20, 29, 4, 21, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.setBlockState(worldIn, BRICKS_PRISMARINE, 27, 3, 21, p_175837_3_);
         this.setBlockState(worldIn, BRICKS_PRISMARINE, 30, 3, 21, p_175837_3_);
         this.setBlockState(worldIn, BRICKS_PRISMARINE, 26, 2, 21, p_175837_3_);
         this.setBlockState(worldIn, BRICKS_PRISMARINE, 31, 2, 21, p_175837_3_);
         this.setBlockState(worldIn, BRICKS_PRISMARINE, 25, 1, 21, p_175837_3_);
         this.setBlockState(worldIn, BRICKS_PRISMARINE, 32, 1, 21, p_175837_3_);

         int k;
         for(k = 0; k < 7; ++k) {
            this.setBlockState(worldIn, DARK_PRISMARINE, 28 - k, 6 + k, 21, p_175837_3_);
            this.setBlockState(worldIn, DARK_PRISMARINE, 29 + k, 6 + k, 21, p_175837_3_);
         }

         for(k = 0; k < 4; ++k) {
            this.setBlockState(worldIn, DARK_PRISMARINE, 28 - k, 9 + k, 21, p_175837_3_);
            this.setBlockState(worldIn, DARK_PRISMARINE, 29 + k, 9 + k, 21, p_175837_3_);
         }

         this.setBlockState(worldIn, DARK_PRISMARINE, 28, 12, 21, p_175837_3_);
         this.setBlockState(worldIn, DARK_PRISMARINE, 29, 12, 21, p_175837_3_);

         for(k = 0; k < 3; ++k) {
            this.setBlockState(worldIn, DARK_PRISMARINE, 22 - k * 2, 8, 21, p_175837_3_);
            this.setBlockState(worldIn, DARK_PRISMARINE, 22 - k * 2, 9, 21, p_175837_3_);
            this.setBlockState(worldIn, DARK_PRISMARINE, 35 + k * 2, 8, 21, p_175837_3_);
            this.setBlockState(worldIn, DARK_PRISMARINE, 35 + k * 2, 9, 21, p_175837_3_);
         }

         this.generateWaterBox(worldIn, p_175837_3_, 15, 13, 21, 42, 15, 21, false);
         this.generateWaterBox(worldIn, p_175837_3_, 15, 1, 21, 15, 6, 21, false);
         this.generateWaterBox(worldIn, p_175837_3_, 16, 1, 21, 16, 5, 21, false);
         this.generateWaterBox(worldIn, p_175837_3_, 17, 1, 21, 20, 4, 21, false);
         this.generateWaterBox(worldIn, p_175837_3_, 21, 1, 21, 21, 3, 21, false);
         this.generateWaterBox(worldIn, p_175837_3_, 22, 1, 21, 22, 2, 21, false);
         this.generateWaterBox(worldIn, p_175837_3_, 23, 1, 21, 24, 1, 21, false);
         this.generateWaterBox(worldIn, p_175837_3_, 42, 1, 21, 42, 6, 21, false);
         this.generateWaterBox(worldIn, p_175837_3_, 41, 1, 21, 41, 5, 21, false);
         this.generateWaterBox(worldIn, p_175837_3_, 37, 1, 21, 40, 4, 21, false);
         this.generateWaterBox(worldIn, p_175837_3_, 36, 1, 21, 36, 3, 21, false);
         this.generateWaterBox(worldIn, p_175837_3_, 33, 1, 21, 34, 1, 21, false);
         this.generateWaterBox(worldIn, p_175837_3_, 35, 1, 21, 35, 2, 21, false);
      }

   }

   private void generateRoofPiece(bij worldIn, Random p_175841_2_, bdy p_175841_3_) {
      if (this.doesChunkIntersect(p_175841_3_, 21, 21, 36, 36)) {
         this.fillWithBlocks(worldIn, p_175841_3_, 21, 0, 22, 36, 0, 36, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.generateWaterBox(worldIn, p_175841_3_, 21, 1, 22, 36, 23, 36, false);

         for(int i = 0; i < 4; ++i) {
            this.fillWithBlocks(worldIn, p_175841_3_, 21 + i, 13 + i, 21 + i, 36 - i, 13 + i, 21 + i, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(worldIn, p_175841_3_, 21 + i, 13 + i, 36 - i, 36 - i, 13 + i, 36 - i, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(worldIn, p_175841_3_, 21 + i, 13 + i, 22 + i, 21 + i, 13 + i, 35 - i, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(worldIn, p_175841_3_, 36 - i, 13 + i, 22 + i, 36 - i, 13 + i, 35 - i, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         }

         this.fillWithBlocks(worldIn, p_175841_3_, 25, 16, 25, 32, 16, 32, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.fillWithBlocks(worldIn, p_175841_3_, 25, 17, 25, 25, 19, 25, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(worldIn, p_175841_3_, 32, 17, 25, 32, 19, 25, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(worldIn, p_175841_3_, 25, 17, 32, 25, 19, 32, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(worldIn, p_175841_3_, 32, 17, 32, 32, 19, 32, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.setBlockState(worldIn, BRICKS_PRISMARINE, 26, 20, 26, p_175841_3_);
         this.setBlockState(worldIn, BRICKS_PRISMARINE, 27, 21, 27, p_175841_3_);
         this.setBlockState(worldIn, SEA_LANTERN, 27, 20, 27, p_175841_3_);
         this.setBlockState(worldIn, BRICKS_PRISMARINE, 26, 20, 31, p_175841_3_);
         this.setBlockState(worldIn, BRICKS_PRISMARINE, 27, 21, 30, p_175841_3_);
         this.setBlockState(worldIn, SEA_LANTERN, 27, 20, 30, p_175841_3_);
         this.setBlockState(worldIn, BRICKS_PRISMARINE, 31, 20, 31, p_175841_3_);
         this.setBlockState(worldIn, BRICKS_PRISMARINE, 30, 21, 30, p_175841_3_);
         this.setBlockState(worldIn, SEA_LANTERN, 30, 20, 30, p_175841_3_);
         this.setBlockState(worldIn, BRICKS_PRISMARINE, 31, 20, 26, p_175841_3_);
         this.setBlockState(worldIn, BRICKS_PRISMARINE, 30, 21, 27, p_175841_3_);
         this.setBlockState(worldIn, SEA_LANTERN, 30, 20, 27, p_175841_3_);
         this.fillWithBlocks(worldIn, p_175841_3_, 28, 21, 27, 29, 21, 27, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.fillWithBlocks(worldIn, p_175841_3_, 27, 21, 28, 27, 21, 29, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.fillWithBlocks(worldIn, p_175841_3_, 28, 21, 30, 29, 21, 30, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.fillWithBlocks(worldIn, p_175841_3_, 30, 21, 28, 30, 21, 29, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
      }

   }

   private void generateLowerWall(bij worldIn, Random p_175835_2_, bdy p_175835_3_) {
      int k;
      if (this.doesChunkIntersect(p_175835_3_, 0, 21, 6, 58)) {
         this.fillWithBlocks(worldIn, p_175835_3_, 0, 0, 21, 6, 0, 57, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.generateWaterBox(worldIn, p_175835_3_, 0, 1, 21, 6, 7, 57, false);
         this.fillWithBlocks(worldIn, p_175835_3_, 4, 4, 21, 6, 4, 53, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);

         for(k = 0; k < 4; ++k) {
            this.fillWithBlocks(worldIn, p_175835_3_, k, k + 1, 21, k, k + 1, 57 - k, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         }

         for(k = 23; k < 53; k += 3) {
            this.setBlockState(worldIn, DOT_DECO_DATA, 5, 5, k, p_175835_3_);
         }

         this.setBlockState(worldIn, DOT_DECO_DATA, 5, 5, 52, p_175835_3_);

         for(k = 0; k < 4; ++k) {
            this.fillWithBlocks(worldIn, p_175835_3_, k, k + 1, 21, k, k + 1, 57 - k, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         }

         this.fillWithBlocks(worldIn, p_175835_3_, 4, 1, 52, 6, 3, 52, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.fillWithBlocks(worldIn, p_175835_3_, 5, 1, 51, 5, 3, 53, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
      }

      if (this.doesChunkIntersect(p_175835_3_, 51, 21, 58, 58)) {
         this.fillWithBlocks(worldIn, p_175835_3_, 51, 0, 21, 57, 0, 57, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.generateWaterBox(worldIn, p_175835_3_, 51, 1, 21, 57, 7, 57, false);
         this.fillWithBlocks(worldIn, p_175835_3_, 51, 4, 21, 53, 4, 53, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);

         for(k = 0; k < 4; ++k) {
            this.fillWithBlocks(worldIn, p_175835_3_, 57 - k, k + 1, 21, 57 - k, k + 1, 57 - k, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         }

         for(k = 23; k < 53; k += 3) {
            this.setBlockState(worldIn, DOT_DECO_DATA, 52, 5, k, p_175835_3_);
         }

         this.setBlockState(worldIn, DOT_DECO_DATA, 52, 5, 52, p_175835_3_);
         this.fillWithBlocks(worldIn, p_175835_3_, 51, 1, 52, 53, 3, 52, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.fillWithBlocks(worldIn, p_175835_3_, 52, 1, 51, 52, 3, 53, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
      }

      if (this.doesChunkIntersect(p_175835_3_, 0, 51, 57, 57)) {
         this.fillWithBlocks(worldIn, p_175835_3_, 7, 0, 51, 50, 0, 57, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.generateWaterBox(worldIn, p_175835_3_, 7, 1, 51, 50, 10, 57, false);

         for(k = 0; k < 4; ++k) {
            this.fillWithBlocks(worldIn, p_175835_3_, k + 1, k + 1, 57 - k, 56 - k, k + 1, 57 - k, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         }
      }

   }

   private void generateMiddleWall(bij worldIn, Random p_175842_2_, bdy p_175842_3_) {
      int j1;
      if (this.doesChunkIntersect(p_175842_3_, 7, 21, 13, 50)) {
         this.fillWithBlocks(worldIn, p_175842_3_, 7, 0, 21, 13, 0, 50, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.generateWaterBox(worldIn, p_175842_3_, 7, 1, 21, 13, 10, 50, false);
         this.fillWithBlocks(worldIn, p_175842_3_, 11, 8, 21, 13, 8, 53, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);

         for(j1 = 0; j1 < 4; ++j1) {
            this.fillWithBlocks(worldIn, p_175842_3_, j1 + 7, j1 + 5, 21, j1 + 7, j1 + 5, 54, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         }

         for(j1 = 21; j1 <= 45; j1 += 3) {
            this.setBlockState(worldIn, DOT_DECO_DATA, 12, 9, j1, p_175842_3_);
         }
      }

      if (this.doesChunkIntersect(p_175842_3_, 44, 21, 50, 54)) {
         this.fillWithBlocks(worldIn, p_175842_3_, 44, 0, 21, 50, 0, 50, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.generateWaterBox(worldIn, p_175842_3_, 44, 1, 21, 50, 10, 50, false);
         this.fillWithBlocks(worldIn, p_175842_3_, 44, 8, 21, 46, 8, 53, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);

         for(j1 = 0; j1 < 4; ++j1) {
            this.fillWithBlocks(worldIn, p_175842_3_, 50 - j1, j1 + 5, 21, 50 - j1, j1 + 5, 54, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         }

         for(j1 = 21; j1 <= 45; j1 += 3) {
            this.setBlockState(worldIn, DOT_DECO_DATA, 45, 9, j1, p_175842_3_);
         }
      }

      if (this.doesChunkIntersect(p_175842_3_, 8, 44, 49, 54)) {
         this.fillWithBlocks(worldIn, p_175842_3_, 14, 0, 44, 43, 0, 50, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.generateWaterBox(worldIn, p_175842_3_, 14, 1, 44, 43, 10, 50, false);

         for(j1 = 12; j1 <= 45; j1 += 3) {
            this.setBlockState(worldIn, DOT_DECO_DATA, j1, 9, 45, p_175842_3_);
            this.setBlockState(worldIn, DOT_DECO_DATA, j1, 9, 52, p_175842_3_);
            if (j1 == 12 || j1 == 18 || j1 == 24 || j1 == 33 || j1 == 39 || j1 == 45) {
               this.setBlockState(worldIn, DOT_DECO_DATA, j1, 9, 47, p_175842_3_);
               this.setBlockState(worldIn, DOT_DECO_DATA, j1, 9, 50, p_175842_3_);
               this.setBlockState(worldIn, DOT_DECO_DATA, j1, 10, 45, p_175842_3_);
               this.setBlockState(worldIn, DOT_DECO_DATA, j1, 10, 46, p_175842_3_);
               this.setBlockState(worldIn, DOT_DECO_DATA, j1, 10, 51, p_175842_3_);
               this.setBlockState(worldIn, DOT_DECO_DATA, j1, 10, 52, p_175842_3_);
               this.setBlockState(worldIn, DOT_DECO_DATA, j1, 11, 47, p_175842_3_);
               this.setBlockState(worldIn, DOT_DECO_DATA, j1, 11, 50, p_175842_3_);
               this.setBlockState(worldIn, DOT_DECO_DATA, j1, 12, 48, p_175842_3_);
               this.setBlockState(worldIn, DOT_DECO_DATA, j1, 12, 49, p_175842_3_);
            }
         }

         for(j1 = 0; j1 < 3; ++j1) {
            this.fillWithBlocks(worldIn, p_175842_3_, 8 + j1, 5 + j1, 54, 49 - j1, 5 + j1, 54, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         }

         this.fillWithBlocks(worldIn, p_175842_3_, 11, 8, 54, 46, 8, 54, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(worldIn, p_175842_3_, 14, 8, 44, 43, 8, 53, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
      }

   }

   private void generateUpperWall(bij worldIn, Random p_175838_2_, bdy p_175838_3_) {
      int j1;
      if (this.doesChunkIntersect(p_175838_3_, 14, 21, 20, 43)) {
         this.fillWithBlocks(worldIn, p_175838_3_, 14, 0, 21, 20, 0, 43, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.generateWaterBox(worldIn, p_175838_3_, 14, 1, 22, 20, 14, 43, false);
         this.fillWithBlocks(worldIn, p_175838_3_, 18, 12, 22, 20, 12, 39, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.fillWithBlocks(worldIn, p_175838_3_, 18, 12, 21, 20, 12, 21, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);

         for(j1 = 0; j1 < 4; ++j1) {
            this.fillWithBlocks(worldIn, p_175838_3_, j1 + 14, j1 + 9, 21, j1 + 14, j1 + 9, 43 - j1, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         }

         for(j1 = 23; j1 <= 39; j1 += 3) {
            this.setBlockState(worldIn, DOT_DECO_DATA, 19, 13, j1, p_175838_3_);
         }
      }

      if (this.doesChunkIntersect(p_175838_3_, 37, 21, 43, 43)) {
         this.fillWithBlocks(worldIn, p_175838_3_, 37, 0, 21, 43, 0, 43, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.generateWaterBox(worldIn, p_175838_3_, 37, 1, 22, 43, 14, 43, false);
         this.fillWithBlocks(worldIn, p_175838_3_, 37, 12, 22, 39, 12, 39, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.fillWithBlocks(worldIn, p_175838_3_, 37, 12, 21, 39, 12, 21, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);

         for(j1 = 0; j1 < 4; ++j1) {
            this.fillWithBlocks(worldIn, p_175838_3_, 43 - j1, j1 + 9, 21, 43 - j1, j1 + 9, 43 - j1, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         }

         for(j1 = 23; j1 <= 39; j1 += 3) {
            this.setBlockState(worldIn, DOT_DECO_DATA, 38, 13, j1, p_175838_3_);
         }
      }

      if (this.doesChunkIntersect(p_175838_3_, 15, 37, 42, 43)) {
         this.fillWithBlocks(worldIn, p_175838_3_, 21, 0, 37, 36, 0, 43, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.generateWaterBox(worldIn, p_175838_3_, 21, 1, 37, 36, 14, 43, false);
         this.fillWithBlocks(worldIn, p_175838_3_, 21, 12, 37, 36, 12, 39, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);

         for(j1 = 0; j1 < 4; ++j1) {
            this.fillWithBlocks(worldIn, p_175838_3_, 15 + j1, j1 + 9, 43 - j1, 42 - j1, j1 + 9, 43 - j1, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         }

         for(j1 = 21; j1 <= 36; j1 += 3) {
            this.setBlockState(worldIn, DOT_DECO_DATA, j1, 13, 38, p_175838_3_);
         }
      }

   }
}
