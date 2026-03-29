package neo;

import javax.annotation.Nullable;

enum Cc {
   ONLY("only", false, false),
   THROUGH("through", true, true),
   FROM("from", false, true),
   UNTIL("until", true, false),
   EVERYTHING("everything", true, true);

   static final String[] NAMES = new String[values().length];
   final String name;
   final boolean parents;
   final boolean children;

   private Cc(String p_i47556_3_, boolean p_i47556_4_, boolean p_i47556_5_) {
      this.name = p_i47556_3_;
      this.parents = p_i47556_4_;
      this.children = p_i47556_5_;
   }

   Ct fail(Cb p_193543_1_, Object... p_193543_2_) {
      return new Ct(p_193543_1_.baseTranslationKey + "." + this.name + ".failed", p_193543_2_);
   }

   Ct usage(Cb p_193544_1_) {
      return new Ct(p_193544_1_.baseTranslationKey + "." + this.name + ".usage", new Object[0]);
   }

   void success(DB sender, Cd p_193546_2_, Cb type, Object... args) {
      Ch.notifyCommandListener(sender, p_193546_2_, type.baseTranslationKey + "." + this.name + ".success", args);
   }

   @Nullable
   static Cc byName(String nameIn) {
      Cc[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         Cc advancementcommand$mode = var1[var3];
         if (advancementcommand$mode.name.equals(nameIn)) {
            return advancementcommand$mode;
         }
      }

      return null;
   }

   static {
      for(int i = 0; i < values().length; ++i) {
         NAMES[i] = values()[i].name;
      }

   }
}
