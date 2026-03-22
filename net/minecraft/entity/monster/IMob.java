package net.minecraft.entity.monster;

import com.google.common.base.Predicate;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.IAnimals;

public interface IMob extends IAnimals {
   Predicate<Entity> MOB_SELECTOR = new Predicate<Entity>() {
      public boolean apply(@Nullable Entity p_apply_1_) {
         return p_apply_1_ instanceof IMob;
      }

      // $FF: synthetic method
      // $FF: bridge method
      public boolean apply(@Nullable Object var1) {
         return this.apply((Entity)var1);
      }
   };
   Predicate<Entity> VISIBLE_MOB_SELECTOR = new Predicate<Entity>() {
      public boolean apply(@Nullable Entity p_apply_1_) {
         return p_apply_1_ instanceof IMob && !p_apply_1_.isInvisible();
      }

      // $FF: synthetic method
      // $FF: bridge method
      public boolean apply(@Nullable Object var1) {
         return this.apply((Entity)var1);
      }
   };
}
