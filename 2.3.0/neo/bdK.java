package neo;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.util.Rotation;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;

public class bdK {
   private static final bfD OVERWRITE = (new bfD()).setIgnoreEntities(true);
   private static final bfD INSERT;
   private static final bdJ HOUSE_TOWER_GENERATOR;
   private static final List<Tuple<Rotation, BlockPos>> TOWER_BRIDGES;
   private static final bdJ TOWER_GENERATOR;
   private static final bdJ TOWER_BRIDGE_GENERATOR;
   private static final List<Tuple<Rotation, BlockPos>> FAT_TOWER_BRIDGES;
   private static final bdJ FAT_TOWER_GENERATOR;

   public bdK() {
   }

   public static void registerPieces() {
      bdt.registerStructureComponent(bdI.class, "ECP");
   }

   private static bdI addPiece(bfL p_191090_0_, bdI p_191090_1_, BlockPos p_191090_2_, String p_191090_3_, Rotation p_191090_4_, boolean owerwrite) {
      bdI structureendcitypieces$citytemplate = new bdI(p_191090_0_, p_191090_3_, p_191090_1_.templatePosition, p_191090_4_, owerwrite);
      BlockPos blockpos = p_191090_1_.template.calculateConnectedPos(p_191090_1_.placeSettings, p_191090_2_, structureendcitypieces$citytemplate.placeSettings, BlockPos.ORIGIN);
      structureendcitypieces$citytemplate.offset(blockpos.getX(), blockpos.getY(), blockpos.getZ());
      return structureendcitypieces$citytemplate;
   }

   public static void startHouseTower(bfL p_191087_0_, BlockPos p_191087_1_, Rotation p_191087_2_, List<bdB> p_191087_3_, Random p_191087_4_) {
      FAT_TOWER_GENERATOR.init();
      HOUSE_TOWER_GENERATOR.init();
      TOWER_BRIDGE_GENERATOR.init();
      TOWER_GENERATOR.init();
      bdI structureendcitypieces$citytemplate = addHelper(p_191087_3_, new bdI(p_191087_0_, "base_floor", p_191087_1_, p_191087_2_, true));
      structureendcitypieces$citytemplate = addHelper(p_191087_3_, addPiece(p_191087_0_, structureendcitypieces$citytemplate, new BlockPos(-1, 0, -1), "second_floor", p_191087_2_, false));
      structureendcitypieces$citytemplate = addHelper(p_191087_3_, addPiece(p_191087_0_, structureendcitypieces$citytemplate, new BlockPos(-1, 4, -1), "third_floor", p_191087_2_, false));
      structureendcitypieces$citytemplate = addHelper(p_191087_3_, addPiece(p_191087_0_, structureendcitypieces$citytemplate, new BlockPos(-1, 8, -1), "third_roof", p_191087_2_, true));
      recursiveChildren(p_191087_0_, TOWER_GENERATOR, 1, structureendcitypieces$citytemplate, (BlockPos)null, p_191087_3_, p_191087_4_);
   }

   private static bdI addHelper(List<bdB> p_189935_0_, bdI p_189935_1_) {
      p_189935_0_.add(p_189935_1_);
      return p_189935_1_;
   }

   private static boolean recursiveChildren(bfL p_191088_0_, bdJ p_191088_1_, int p_191088_2_, bdI p_191088_3_, BlockPos p_191088_4_, List<bdB> p_191088_5_, Random p_191088_6_) {
      if (p_191088_2_ > 8) {
         return false;
      } else {
         List<bdB> list = Lists.newArrayList();
         if (p_191088_1_.generate(p_191088_0_, p_191088_2_, p_191088_3_, p_191088_4_, list, p_191088_6_)) {
            boolean flag = false;
            int i = p_191088_6_.nextInt();
            Iterator var10 = list.iterator();

            while(var10.hasNext()) {
               bdB structurecomponent = (bdB)var10.next();
               structurecomponent.componentType = i;
               bdB structurecomponent1 = bdB.findIntersecting(p_191088_5_, structurecomponent.getBoundingBox());
               if (structurecomponent1 != null && structurecomponent1.componentType != p_191088_3_.componentType) {
                  flag = true;
                  break;
               }
            }

            if (!flag) {
               p_191088_5_.addAll(list);
               return true;
            }
         }

         return false;
      }
   }

   // $FF: synthetic method
   static bfD access$900() {
      return OVERWRITE;
   }

   // $FF: synthetic method
   static bfD access$1000() {
      return INSERT;
   }

   static {
      INSERT = (new bfD()).setIgnoreEntities(true).setReplacedBlock(Nk.AIR);
      HOUSE_TOWER_GENERATOR = new bdJ() {
         public void init() {
         }

         public boolean generate(bfL p_191086_1_, int p_191086_2_, bdI p_191086_3_, BlockPos p_191086_4_, List<bdB> p_191086_5_, Random p_191086_6_) {
            if (p_191086_2_ > 8) {
               return false;
            } else {
               Rotation rotation = p_191086_3_.placeSettings.getRotation();
               bdI structureendcitypieces$citytemplate = bdK.addHelper(p_191086_5_, bdK.addPiece(p_191086_1_, p_191086_3_, p_191086_4_, "base_floor", rotation, true));
               int i = p_191086_6_.nextInt(3);
               if (i == 0) {
                  bdK.addHelper(p_191086_5_, bdK.addPiece(p_191086_1_, structureendcitypieces$citytemplate, new BlockPos(-1, 4, -1), "base_roof", rotation, true));
               } else if (i == 1) {
                  structureendcitypieces$citytemplate = bdK.addHelper(p_191086_5_, bdK.addPiece(p_191086_1_, structureendcitypieces$citytemplate, new BlockPos(-1, 0, -1), "second_floor_2", rotation, false));
                  structureendcitypieces$citytemplate = bdK.addHelper(p_191086_5_, bdK.addPiece(p_191086_1_, structureendcitypieces$citytemplate, new BlockPos(-1, 8, -1), "second_roof", rotation, false));
                  bdK.recursiveChildren(p_191086_1_, bdK.TOWER_GENERATOR, p_191086_2_ + 1, structureendcitypieces$citytemplate, (BlockPos)null, p_191086_5_, p_191086_6_);
               } else if (i == 2) {
                  structureendcitypieces$citytemplate = bdK.addHelper(p_191086_5_, bdK.addPiece(p_191086_1_, structureendcitypieces$citytemplate, new BlockPos(-1, 0, -1), "second_floor_2", rotation, false));
                  structureendcitypieces$citytemplate = bdK.addHelper(p_191086_5_, bdK.addPiece(p_191086_1_, structureendcitypieces$citytemplate, new BlockPos(-1, 4, -1), "third_floor_c", rotation, false));
                  structureendcitypieces$citytemplate = bdK.addHelper(p_191086_5_, bdK.addPiece(p_191086_1_, structureendcitypieces$citytemplate, new BlockPos(-1, 8, -1), "third_roof", rotation, true));
                  bdK.recursiveChildren(p_191086_1_, bdK.TOWER_GENERATOR, p_191086_2_ + 1, structureendcitypieces$citytemplate, (BlockPos)null, p_191086_5_, p_191086_6_);
               }

               return true;
            }
         }
      };
      TOWER_BRIDGES = Lists.newArrayList(new Tuple[]{new Tuple(Rotation.NONE, new BlockPos(1, -1, 0)), new Tuple(Rotation.CLOCKWISE_90, new BlockPos(6, -1, 1)), new Tuple(Rotation.COUNTERCLOCKWISE_90, new BlockPos(0, -1, 5)), new Tuple(Rotation.CLOCKWISE_180, new BlockPos(5, -1, 6))});
      TOWER_GENERATOR = new bdJ() {
         public void init() {
         }

         public boolean generate(bfL p_191086_1_, int p_191086_2_, bdI p_191086_3_, BlockPos p_191086_4_, List<bdB> p_191086_5_, Random p_191086_6_) {
            Rotation rotation = p_191086_3_.placeSettings.getRotation();
            bdI lvt_8_1_ = bdK.addHelper(p_191086_5_, bdK.addPiece(p_191086_1_, p_191086_3_, new BlockPos(3 + p_191086_6_.nextInt(2), -3, 3 + p_191086_6_.nextInt(2)), "tower_base", rotation, true));
            lvt_8_1_ = bdK.addHelper(p_191086_5_, bdK.addPiece(p_191086_1_, lvt_8_1_, new BlockPos(0, 7, 0), "tower_piece", rotation, true));
            bdI structureendcitypieces$citytemplate1 = p_191086_6_.nextInt(3) == 0 ? lvt_8_1_ : null;
            int i = 1 + p_191086_6_.nextInt(3);

            for(int j = 0; j < i; ++j) {
               lvt_8_1_ = bdK.addHelper(p_191086_5_, bdK.addPiece(p_191086_1_, lvt_8_1_, new BlockPos(0, 4, 0), "tower_piece", rotation, true));
               if (j < i - 1 && p_191086_6_.nextBoolean()) {
                  structureendcitypieces$citytemplate1 = lvt_8_1_;
               }
            }

            if (structureendcitypieces$citytemplate1 != null) {
               Iterator var14 = bdK.TOWER_BRIDGES.iterator();

               while(var14.hasNext()) {
                  Tuple<Rotation, BlockPos> tuple = (Tuple)var14.next();
                  if (p_191086_6_.nextBoolean()) {
                     bdI structureendcitypieces$citytemplate2 = bdK.addHelper(p_191086_5_, bdK.addPiece(p_191086_1_, structureendcitypieces$citytemplate1, (BlockPos)tuple.getSecond(), "bridge_end", rotation.add((Rotation)tuple.getFirst()), true));
                     bdK.recursiveChildren(p_191086_1_, bdK.TOWER_BRIDGE_GENERATOR, p_191086_2_ + 1, structureendcitypieces$citytemplate2, (BlockPos)null, p_191086_5_, p_191086_6_);
                  }
               }

               bdK.addHelper(p_191086_5_, bdK.addPiece(p_191086_1_, lvt_8_1_, new BlockPos(-1, 4, -1), "tower_top", rotation, true));
            } else {
               if (p_191086_2_ != 7) {
                  return bdK.recursiveChildren(p_191086_1_, bdK.FAT_TOWER_GENERATOR, p_191086_2_ + 1, lvt_8_1_, (BlockPos)null, p_191086_5_, p_191086_6_);
               }

               bdK.addHelper(p_191086_5_, bdK.addPiece(p_191086_1_, lvt_8_1_, new BlockPos(-1, 4, -1), "tower_top", rotation, true));
            }

            return true;
         }
      };
      TOWER_BRIDGE_GENERATOR = new bdJ() {
         public boolean shipCreated;

         public void init() {
            this.shipCreated = false;
         }

         public boolean generate(bfL p_191086_1_, int p_191086_2_, bdI p_191086_3_, BlockPos p_191086_4_, List<bdB> p_191086_5_, Random p_191086_6_) {
            Rotation rotation = p_191086_3_.placeSettings.getRotation();
            int i = p_191086_6_.nextInt(4) + 1;
            bdI structureendcitypieces$citytemplate = bdK.addHelper(p_191086_5_, bdK.addPiece(p_191086_1_, p_191086_3_, new BlockPos(0, 0, -4), "bridge_piece", rotation, true));
            structureendcitypieces$citytemplate.componentType = -1;
            int j = 0;

            for(int k = 0; k < i; ++k) {
               if (p_191086_6_.nextBoolean()) {
                  structureendcitypieces$citytemplate = bdK.addHelper(p_191086_5_, bdK.addPiece(p_191086_1_, structureendcitypieces$citytemplate, new BlockPos(0, j, -4), "bridge_piece", rotation, true));
                  j = 0;
               } else {
                  if (p_191086_6_.nextBoolean()) {
                     structureendcitypieces$citytemplate = bdK.addHelper(p_191086_5_, bdK.addPiece(p_191086_1_, structureendcitypieces$citytemplate, new BlockPos(0, j, -4), "bridge_steep_stairs", rotation, true));
                  } else {
                     structureendcitypieces$citytemplate = bdK.addHelper(p_191086_5_, bdK.addPiece(p_191086_1_, structureendcitypieces$citytemplate, new BlockPos(0, j, -8), "bridge_gentle_stairs", rotation, true));
                  }

                  j = 4;
               }
            }

            if (!this.shipCreated && p_191086_6_.nextInt(10 - p_191086_2_) == 0) {
               bdK.addHelper(p_191086_5_, bdK.addPiece(p_191086_1_, structureendcitypieces$citytemplate, new BlockPos(-8 + p_191086_6_.nextInt(8), j, -70 + p_191086_6_.nextInt(10)), "ship", rotation, true));
               this.shipCreated = true;
            } else if (!bdK.recursiveChildren(p_191086_1_, bdK.HOUSE_TOWER_GENERATOR, p_191086_2_ + 1, structureendcitypieces$citytemplate, new BlockPos(-3, j + 1, -11), p_191086_5_, p_191086_6_)) {
               return false;
            }

            structureendcitypieces$citytemplate = bdK.addHelper(p_191086_5_, bdK.addPiece(p_191086_1_, structureendcitypieces$citytemplate, new BlockPos(4, j, 0), "bridge_end", rotation.add(Rotation.CLOCKWISE_180), true));
            structureendcitypieces$citytemplate.componentType = -1;
            return true;
         }
      };
      FAT_TOWER_BRIDGES = Lists.newArrayList(new Tuple[]{new Tuple(Rotation.NONE, new BlockPos(4, -1, 0)), new Tuple(Rotation.CLOCKWISE_90, new BlockPos(12, -1, 4)), new Tuple(Rotation.COUNTERCLOCKWISE_90, new BlockPos(0, -1, 8)), new Tuple(Rotation.CLOCKWISE_180, new BlockPos(8, -1, 12))});
      FAT_TOWER_GENERATOR = new bdJ() {
         public void init() {
         }

         public boolean generate(bfL p_191086_1_, int p_191086_2_, bdI p_191086_3_, BlockPos p_191086_4_, List<bdB> p_191086_5_, Random p_191086_6_) {
            Rotation rotation = p_191086_3_.placeSettings.getRotation();
            bdI structureendcitypieces$citytemplate = bdK.addHelper(p_191086_5_, bdK.addPiece(p_191086_1_, p_191086_3_, new BlockPos(-3, 4, -3), "fat_tower_base", rotation, true));
            structureendcitypieces$citytemplate = bdK.addHelper(p_191086_5_, bdK.addPiece(p_191086_1_, structureendcitypieces$citytemplate, new BlockPos(0, 4, 0), "fat_tower_middle", rotation, true));

            for(int i = 0; i < 2 && p_191086_6_.nextInt(3) != 0; ++i) {
               structureendcitypieces$citytemplate = bdK.addHelper(p_191086_5_, bdK.addPiece(p_191086_1_, structureendcitypieces$citytemplate, new BlockPos(0, 8, 0), "fat_tower_middle", rotation, true));
               Iterator var10 = bdK.FAT_TOWER_BRIDGES.iterator();

               while(var10.hasNext()) {
                  Tuple<Rotation, BlockPos> tuple = (Tuple)var10.next();
                  if (p_191086_6_.nextBoolean()) {
                     bdI structureendcitypieces$citytemplate1 = bdK.addHelper(p_191086_5_, bdK.addPiece(p_191086_1_, structureendcitypieces$citytemplate, (BlockPos)tuple.getSecond(), "bridge_end", rotation.add((Rotation)tuple.getFirst()), true));
                     bdK.recursiveChildren(p_191086_1_, bdK.TOWER_BRIDGE_GENERATOR, p_191086_2_ + 1, structureendcitypieces$citytemplate1, (BlockPos)null, p_191086_5_, p_191086_6_);
                  }
               }
            }

            bdK.addHelper(p_191086_5_, bdK.addPiece(p_191086_1_, structureendcitypieces$citytemplate, new BlockPos(-2, 8, -2), "fat_tower_top", rotation, true));
            return true;
         }
      };
   }
}
