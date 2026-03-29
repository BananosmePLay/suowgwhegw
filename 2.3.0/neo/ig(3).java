package neo;

import com.google.common.collect.Lists;
import java.util.List;

public class ig {
   private final co block;
   private final List<hT<?>> listed = Lists.newArrayList();
   private final List<biF<?>> unlisted = Lists.newArrayList();

   public ig(co p_i1_1_) {
      this.block = p_i1_1_;
   }

   public ig add(hT<?>... p_add_1_) {
      hT[] var2 = p_add_1_;
      int var3 = p_add_1_.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         hT<?> iproperty = var2[var4];
         this.listed.add(iproperty);
      }

      return this;
   }

   public ig add(biF<?>... p_add_1_) {
      biF[] var2 = p_add_1_;
      int var3 = p_add_1_.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         biF<?> iunlistedproperty = var2[var4];
         this.unlisted.add(iunlistedproperty);
      }

      return this;
   }

   public ii build() {
      hT<?>[] iproperty = new hT[this.listed.size()];
      iproperty = (hT[])((hT[])this.listed.toArray(iproperty));
      if (this.unlisted.size() == 0) {
         return new ii(this.block, iproperty);
      } else {
         biF<?>[] iunlistedproperty = new biF[this.unlisted.size()];
         iunlistedproperty = (biF[])((biF[])this.unlisted.toArray(iunlistedproperty));
         return (ii)bnK.newInstance(bnK.ExtendedBlockState_Constructor, this.block, iproperty, iunlistedproperty);
      }
   }
}
