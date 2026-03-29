package neo;

import com.google.common.collect.Lists;
import java.util.List;

public class kO extends ky {
   private final List<kN> options = Lists.newArrayList();

   public kO(nC mcIn, int p_i45015_2_, int p_i45015_3_, int p_i45015_4_, int p_i45015_5_, int p_i45015_6_, Bi... p_i45015_7_) {
      super(mcIn, p_i45015_2_, p_i45015_3_, p_i45015_4_, p_i45015_5_, p_i45015_6_);
      this.centerListVertically = false;

      for(int i = 0; i < p_i45015_7_.length; i += 2) {
         Bi gamesettings$options = p_i45015_7_[i];
         Bi gamesettings$options1 = i < p_i45015_7_.length - 1 ? p_i45015_7_[i + 1] : null;
         jK guibutton = this.createButton(mcIn, p_i45015_2_ / 2 - 155, 0, gamesettings$options);
         jK guibutton1 = this.createButton(mcIn, p_i45015_2_ / 2 - 155 + 160, 0, gamesettings$options1);
         this.options.add(new kN(guibutton, guibutton1));
      }

   }

   private jK createButton(nC mcIn, int p_148182_2_, int p_148182_3_, Bi options) {
      if (options == null) {
         return null;
      } else {
         int i = options.getOrdinal();
         return (jK)(options.isFloat() ? new kM(i, p_148182_2_, p_148182_3_, options) : new kK(i, p_148182_2_, p_148182_3_, options, nC.gameSettings.getKeyBinding(options)));
      }
   }

   public kN getListEntry(int index) {
      return (kN)this.options.get(index);
   }

   protected int getSize() {
      return this.options.size();
   }

   public int getListWidth() {
      return 400;
   }

   protected int getScrollBarX() {
      return super.getScrollBarX() + 32;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public kx getListEntry(int var1) {
      return this.getListEntry(var1);
   }
}
