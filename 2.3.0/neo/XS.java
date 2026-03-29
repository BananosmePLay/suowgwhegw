package neo;

import net.minecraft.util.text.ITextComponent;

public class XS extends XQ {
   private final OL item;

   public XS(String p_i45910_1_, String p_i45910_2_, ITextComponent statNameIn, OL p_i45910_4_) {
      super(p_i45910_1_ + p_i45910_2_, statNameIn);
      this.item = p_i45910_4_;
   }

   public OL getItem() {
      return this.item;
   }
}
