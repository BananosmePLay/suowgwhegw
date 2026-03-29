package neo;

public class kK extends jK {
   private final Bi enumOptions;

   public kK(int p_i45011_1_, int p_i45011_2_, int p_i45011_3_, String p_i45011_4_) {
      this(p_i45011_1_, p_i45011_2_, p_i45011_3_, (Bi)null, p_i45011_4_);
   }

   public kK(int p_i45013_1_, int p_i45013_2_, int p_i45013_3_, Bi p_i45013_4_, String p_i45013_5_) {
      super(p_i45013_1_, p_i45013_2_, p_i45013_3_, 150, 20, p_i45013_5_);
      this.enumOptions = p_i45013_4_;
   }

   public Bi getOption() {
      return this.enumOptions;
   }
}
