package neo;

import java.util.Iterator;
import javax.annotation.Nullable;

enum Cb {
   GRANT("grant") {
      protected boolean perform(MG p_193537_1_, b p_193537_2_) {
         h advancementprogress = p_193537_1_.getAdvancements().getProgress(p_193537_2_);
         if (advancementprogress.isDone()) {
            return false;
         } else {
            Iterator var4 = advancementprogress.getRemaningCriteria().iterator();

            while(var4.hasNext()) {
               String s = (String)var4.next();
               p_193537_1_.getAdvancements().grantCriterion(p_193537_2_, s);
            }

            return true;
         }
      }

      protected boolean performCriterion(MG p_193535_1_, b p_193535_2_, String p_193535_3_) {
         return p_193535_1_.getAdvancements().grantCriterion(p_193535_2_, p_193535_3_);
      }
   },
   REVOKE("revoke") {
      protected boolean perform(MG p_193537_1_, b p_193537_2_) {
         h advancementprogress = p_193537_1_.getAdvancements().getProgress(p_193537_2_);
         if (!advancementprogress.hasProgress()) {
            return false;
         } else {
            Iterator var4 = advancementprogress.getCompletedCriteria().iterator();

            while(var4.hasNext()) {
               String s = (String)var4.next();
               p_193537_1_.getAdvancements().revokeCriterion(p_193537_2_, s);
            }

            return true;
         }
      }

      protected boolean performCriterion(MG p_193535_1_, b p_193535_2_, String p_193535_3_) {
         return p_193535_1_.getAdvancements().revokeCriterion(p_193535_2_, p_193535_3_);
      }
   };

   final String name;
   final String baseTranslationKey;

   private Cb(String nameIn) {
      this.name = nameIn;
      this.baseTranslationKey = "commands.advancement." + nameIn;
   }

   @Nullable
   static Cb byName(String nameIn) {
      Cb[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         Cb advancementcommand$actiontype = var1[var3];
         if (advancementcommand$actiontype.name.equals(nameIn)) {
            return advancementcommand$actiontype;
         }
      }

      return null;
   }

   Ct wrongUsage() {
      return new Ct(this.baseTranslationKey + ".usage", new Object[0]);
   }

   public int perform(MG p_193532_1_, Iterable<b> p_193532_2_) {
      int i = 0;
      Iterator var4 = p_193532_2_.iterator();

      while(var4.hasNext()) {
         b advancement = (b)var4.next();
         if (this.perform(p_193532_1_, advancement)) {
            ++i;
         }
      }

      return i;
   }

   protected abstract boolean perform(MG var1, b var2);

   protected abstract boolean performCriterion(MG var1, b var2, String var3);

   // $FF: synthetic method
   Cb(String x2, Object x3) {
      this(x2);
   }
}
