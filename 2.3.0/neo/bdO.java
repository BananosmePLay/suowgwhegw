package neo;

abstract class bdO extends bdB {
   protected bdg mineShaftType;

   public bdO() {
   }

   public bdO(int p_i47138_1_, bdg p_i47138_2_) {
      super(p_i47138_1_);
      this.mineShaftType = p_i47138_2_;
   }

   protected void writeStructureToNBT(QQ tagCompound) {
      tagCompound.setInteger("MST", this.mineShaftType.ordinal());
   }

   protected void readStructureFromNBT(QQ tagCompound, bfL p_143011_2_) {
      this.mineShaftType = bdg.byId(tagCompound.getInteger("MST"));
   }

   protected in getPlanksBlock() {
      switch (this.mineShaftType) {
         case NORMAL:
         default:
            return Nk.PLANKS.getDefaultState();
         case MESA:
            return Nk.PLANKS.getDefaultState().withProperty(fl.VARIANT, fk.DARK_OAK);
      }
   }

   protected in getFenceBlock() {
      switch (this.mineShaftType) {
         case NORMAL:
         default:
            return Nk.OAK_FENCE.getDefaultState();
         case MESA:
            return Nk.DARK_OAK_FENCE.getDefaultState();
      }
   }

   protected boolean isSupportingBox(bij p_189918_1_, bdy p_189918_2_, int p_189918_3_, int p_189918_4_, int p_189918_5_, int p_189918_6_) {
      for(int i = p_189918_3_; i <= p_189918_4_; ++i) {
         if (this.getBlockStateFromPos(p_189918_1_, i, p_189918_5_ + 1, p_189918_6_, p_189918_2_).getMaterial() == hM.AIR) {
            return false;
         }
      }

      return true;
   }
}
