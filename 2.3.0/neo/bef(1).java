package neo;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;

abstract class bef extends bdB {
   public bef() {
   }

   protected bef(int p_i2054_1_) {
      super(p_i2054_1_);
   }

   protected void readStructureFromNBT(QQ tagCompound, bfL p_143011_2_) {
   }

   protected void writeStructureToNBT(QQ tagCompound) {
   }

   private int getTotalWeight(List<beg> p_74960_1_) {
      boolean flag = false;
      int i = 0;

      beg structurenetherbridgepieces$pieceweight;
      for(Iterator var4 = p_74960_1_.iterator(); var4.hasNext(); i += structurenetherbridgepieces$pieceweight.weight) {
         structurenetherbridgepieces$pieceweight = (beg)var4.next();
         if (structurenetherbridgepieces$pieceweight.maxPlaceCount > 0 && structurenetherbridgepieces$pieceweight.placeCount < structurenetherbridgepieces$pieceweight.maxPlaceCount) {
            flag = true;
         }
      }

      return flag ? i : -1;
   }

   private bef generatePiece(bei p_175871_1_, List<beg> p_175871_2_, List<bdB> p_175871_3_, Random p_175871_4_, int p_175871_5_, int p_175871_6_, int p_175871_7_, EnumFacing p_175871_8_, int p_175871_9_) {
      int i = this.getTotalWeight(p_175871_2_);
      boolean flag = i > 0 && p_175871_9_ <= 30;
      int j = 0;

      while(j < 5 && flag) {
         ++j;
         int k = p_175871_4_.nextInt(i);
         Iterator var14 = p_175871_2_.iterator();

         while(var14.hasNext()) {
            beg structurenetherbridgepieces$pieceweight = (beg)var14.next();
            k -= structurenetherbridgepieces$pieceweight.weight;
            if (k < 0) {
               if (!structurenetherbridgepieces$pieceweight.doPlace(p_175871_9_) || structurenetherbridgepieces$pieceweight == p_175871_1_.lastPlaced && !structurenetherbridgepieces$pieceweight.allowInRow) {
                  break;
               }

               bef structurenetherbridgepieces$piece = bel.access$000(structurenetherbridgepieces$pieceweight, p_175871_3_, p_175871_4_, p_175871_5_, p_175871_6_, p_175871_7_, p_175871_8_, p_175871_9_);
               if (structurenetherbridgepieces$piece != null) {
                  ++structurenetherbridgepieces$pieceweight.placeCount;
                  p_175871_1_.lastPlaced = structurenetherbridgepieces$pieceweight;
                  if (!structurenetherbridgepieces$pieceweight.isValid()) {
                     p_175871_2_.remove(structurenetherbridgepieces$pieceweight);
                  }

                  return structurenetherbridgepieces$piece;
               }
            }
         }
      }

      return bec.createPiece(p_175871_3_, p_175871_4_, p_175871_5_, p_175871_6_, p_175871_7_, p_175871_8_, p_175871_9_);
   }

   private bdB generateAndAddPiece(bei p_175870_1_, List<bdB> p_175870_2_, Random p_175870_3_, int p_175870_4_, int p_175870_5_, int p_175870_6_, @Nullable EnumFacing p_175870_7_, int p_175870_8_, boolean p_175870_9_) {
      if (Math.abs(p_175870_4_ - p_175870_1_.getBoundingBox().minX) <= 112 && Math.abs(p_175870_6_ - p_175870_1_.getBoundingBox().minZ) <= 112) {
         List<beg> list = p_175870_1_.primaryWeights;
         if (p_175870_9_) {
            list = p_175870_1_.secondaryWeights;
         }

         bdB structurecomponent = this.generatePiece(p_175870_1_, list, p_175870_2_, p_175870_3_, p_175870_4_, p_175870_5_, p_175870_6_, p_175870_7_, p_175870_8_ + 1);
         if (structurecomponent != null) {
            p_175870_2_.add(structurecomponent);
            p_175870_1_.pendingChildren.add(structurecomponent);
         }

         return structurecomponent;
      } else {
         return bec.createPiece(p_175870_2_, p_175870_3_, p_175870_4_, p_175870_5_, p_175870_6_, p_175870_7_, p_175870_8_);
      }
   }

   @Nullable
   protected bdB getNextComponentNormal(bei p_74963_1_, List<bdB> p_74963_2_, Random p_74963_3_, int p_74963_4_, int p_74963_5_, boolean p_74963_6_) {
      EnumFacing enumfacing = this.getCoordBaseMode();
      if (enumfacing != null) {
         switch (enumfacing) {
            case NORTH:
               return this.generateAndAddPiece(p_74963_1_, p_74963_2_, p_74963_3_, this.boundingBox.minX + p_74963_4_, this.boundingBox.minY + p_74963_5_, this.boundingBox.minZ - 1, enumfacing, this.getComponentType(), p_74963_6_);
            case SOUTH:
               return this.generateAndAddPiece(p_74963_1_, p_74963_2_, p_74963_3_, this.boundingBox.minX + p_74963_4_, this.boundingBox.minY + p_74963_5_, this.boundingBox.maxZ + 1, enumfacing, this.getComponentType(), p_74963_6_);
            case WEST:
               return this.generateAndAddPiece(p_74963_1_, p_74963_2_, p_74963_3_, this.boundingBox.minX - 1, this.boundingBox.minY + p_74963_5_, this.boundingBox.minZ + p_74963_4_, enumfacing, this.getComponentType(), p_74963_6_);
            case EAST:
               return this.generateAndAddPiece(p_74963_1_, p_74963_2_, p_74963_3_, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74963_5_, this.boundingBox.minZ + p_74963_4_, enumfacing, this.getComponentType(), p_74963_6_);
         }
      }

      return null;
   }

   @Nullable
   protected bdB getNextComponentX(bei p_74961_1_, List<bdB> p_74961_2_, Random p_74961_3_, int p_74961_4_, int p_74961_5_, boolean p_74961_6_) {
      EnumFacing enumfacing = this.getCoordBaseMode();
      if (enumfacing != null) {
         switch (enumfacing) {
            case NORTH:
               return this.generateAndAddPiece(p_74961_1_, p_74961_2_, p_74961_3_, this.boundingBox.minX - 1, this.boundingBox.minY + p_74961_4_, this.boundingBox.minZ + p_74961_5_, EnumFacing.WEST, this.getComponentType(), p_74961_6_);
            case SOUTH:
               return this.generateAndAddPiece(p_74961_1_, p_74961_2_, p_74961_3_, this.boundingBox.minX - 1, this.boundingBox.minY + p_74961_4_, this.boundingBox.minZ + p_74961_5_, EnumFacing.WEST, this.getComponentType(), p_74961_6_);
            case WEST:
               return this.generateAndAddPiece(p_74961_1_, p_74961_2_, p_74961_3_, this.boundingBox.minX + p_74961_5_, this.boundingBox.minY + p_74961_4_, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType(), p_74961_6_);
            case EAST:
               return this.generateAndAddPiece(p_74961_1_, p_74961_2_, p_74961_3_, this.boundingBox.minX + p_74961_5_, this.boundingBox.minY + p_74961_4_, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType(), p_74961_6_);
         }
      }

      return null;
   }

   @Nullable
   protected bdB getNextComponentZ(bei p_74965_1_, List<bdB> p_74965_2_, Random p_74965_3_, int p_74965_4_, int p_74965_5_, boolean p_74965_6_) {
      EnumFacing enumfacing = this.getCoordBaseMode();
      if (enumfacing != null) {
         switch (enumfacing) {
            case NORTH:
               return this.generateAndAddPiece(p_74965_1_, p_74965_2_, p_74965_3_, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74965_4_, this.boundingBox.minZ + p_74965_5_, EnumFacing.EAST, this.getComponentType(), p_74965_6_);
            case SOUTH:
               return this.generateAndAddPiece(p_74965_1_, p_74965_2_, p_74965_3_, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74965_4_, this.boundingBox.minZ + p_74965_5_, EnumFacing.EAST, this.getComponentType(), p_74965_6_);
            case WEST:
               return this.generateAndAddPiece(p_74965_1_, p_74965_2_, p_74965_3_, this.boundingBox.minX + p_74965_5_, this.boundingBox.minY + p_74965_4_, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType(), p_74965_6_);
            case EAST:
               return this.generateAndAddPiece(p_74965_1_, p_74965_2_, p_74965_3_, this.boundingBox.minX + p_74965_5_, this.boundingBox.minY + p_74965_4_, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType(), p_74965_6_);
         }
      }

      return null;
   }

   protected static boolean isAboveGround(bdy p_74964_0_) {
      return p_74964_0_ != null && p_74964_0_.minY > 10;
   }
}
