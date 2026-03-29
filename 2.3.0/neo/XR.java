package neo;

import net.minecraft.util.text.ITextComponent;

public class XR extends XQ {
   public XR(String statIdIn, ITextComponent statNameIn, XI typeIn) {
      super(statIdIn, statNameIn, typeIn);
   }

   public XR(String statIdIn, ITextComponent statNameIn) {
      super(statIdIn, statNameIn);
   }

   public XQ registerStat() {
      super.registerStat();
      XV.BASIC_STATS.add(this);
      return this;
   }
}
