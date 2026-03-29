package net.minecraft.util;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import javax.annotation.Nullable;
import neo.IN;
import neo.Ig;
import neo.Iu;
import neo.Iw;
import neo.ME;
import neo.Qy;
import neo.WC;
import neo.WE;
import net.minecraft.inventory.IInventory;

public final class EntitySelectors {
   public static final Predicate<Ig> IS_ALIVE = new Predicate<Ig>() {
      public boolean apply(@Nullable Ig p_apply_1_) {
         return p_apply_1_.isEntityAlive();
      }

      // $FF: synthetic method
      // $FF: bridge method
      public boolean apply(@Nullable Object var1) {
         return this.apply((Ig)var1);
      }
   };
   public static final Predicate<Ig> IS_STANDALONE = new Predicate<Ig>() {
      public boolean apply(@Nullable Ig p_apply_1_) {
         return p_apply_1_.isEntityAlive() && !p_apply_1_.isBeingRidden() && !p_apply_1_.isRiding();
      }

      // $FF: synthetic method
      // $FF: bridge method
      public boolean apply(@Nullable Object var1) {
         return this.apply((Ig)var1);
      }
   };
   public static final Predicate<Ig> HAS_INVENTORY = new Predicate<Ig>() {
      public boolean apply(@Nullable Ig p_apply_1_) {
         return p_apply_1_ instanceof IInventory && p_apply_1_.isEntityAlive();
      }

      // $FF: synthetic method
      // $FF: bridge method
      public boolean apply(@Nullable Object var1) {
         return this.apply((Ig)var1);
      }
   };
   public static final Predicate<Ig> CAN_AI_TARGET = new Predicate<Ig>() {
      public boolean apply(@Nullable Ig p_apply_1_) {
         return !(p_apply_1_ instanceof ME) || !((ME)p_apply_1_).isSpectator() && !((ME)p_apply_1_).isCreative();
      }

      // $FF: synthetic method
      // $FF: bridge method
      public boolean apply(@Nullable Object var1) {
         return this.apply((Ig)var1);
      }
   };
   public static final Predicate<Ig> NOT_SPECTATING = new Predicate<Ig>() {
      public boolean apply(@Nullable Ig p_apply_1_) {
         return !(p_apply_1_ instanceof ME) || !((ME)p_apply_1_).isSpectator();
      }

      // $FF: synthetic method
      // $FF: bridge method
      public boolean apply(@Nullable Object var1) {
         return this.apply((Ig)var1);
      }
   };

   public EntitySelectors() {
   }

   public static <T extends Ig> Predicate<T> withinRange(final double x, final double y, final double z, double range) {
      final double d0 = range * range;
      return new Predicate<T>() {
         public boolean apply(@Nullable T p_apply_1_) {
            return p_apply_1_ != null && p_apply_1_.getDistanceSq(x, y, z) <= d0;
         }

         // $FF: synthetic method
         // $FF: bridge method
         public boolean apply(@Nullable Object var1) {
            return this.apply((Ig)var1);
         }
      };
   }

   public static <T extends Ig> Predicate<T> getTeamCollisionPredicate(final Ig entityIn) {
      final WE team = entityIn.getTeam();
      final WC team$collisionrule = team == null ? WC.ALWAYS : team.getCollisionRule();
      Predicate<?> ret = team$collisionrule == WC.NEVER ? Predicates.alwaysFalse() : Predicates.and(NOT_SPECTATING, new Predicate<Ig>() {
         public boolean apply(@Nullable Ig p_apply_1_) {
            if (!p_apply_1_.canBePushed()) {
               return false;
            } else if (entityIn.world.isRemote && (!(p_apply_1_ instanceof ME) || !((ME)p_apply_1_).isUser())) {
               return false;
            } else {
               WE team1 = p_apply_1_.getTeam();
               WC team$collisionrule1 = team1 == null ? WC.ALWAYS : team1.getCollisionRule();
               if (team$collisionrule1 == WC.NEVER) {
                  return false;
               } else {
                  boolean flag = team != null && team.isSameTeam(team1);
                  if ((team$collisionrule == WC.HIDE_FOR_OWN_TEAM || team$collisionrule1 == WC.HIDE_FOR_OWN_TEAM) && flag) {
                     return false;
                  } else {
                     return team$collisionrule != WC.HIDE_FOR_OTHER_TEAMS && team$collisionrule1 != WC.HIDE_FOR_OTHER_TEAMS || flag;
                  }
               }
            }
         }

         // $FF: synthetic method
         // $FF: bridge method
         public boolean apply(@Nullable Object var1) {
            return this.apply((Ig)var1);
         }
      });
      return ret;
   }

   public static Predicate<Ig> notRiding(final Ig p_191324_0_) {
      return new Predicate<Ig>() {
         public boolean apply(@Nullable Ig p_apply_1_) {
            while(true) {
               if (p_apply_1_.isRiding()) {
                  p_apply_1_ = p_apply_1_.getRidingEntity();
                  if (p_apply_1_ != p_191324_0_) {
                     continue;
                  }

                  return false;
               }

               return true;
            }
         }

         // $FF: synthetic method
         // $FF: bridge method
         public boolean apply(@Nullable Object var1) {
            return this.apply((Ig)var1);
         }
      };
   }

   public static class ArmoredMob implements Predicate<Ig> {
      private final Qy armor;

      public ArmoredMob(Qy armor) {
         this.armor = armor;
      }

      public boolean apply(@Nullable Ig p_apply_1_) {
         if (!p_apply_1_.isEntityAlive()) {
            return false;
         } else if (!(p_apply_1_ instanceof Iw)) {
            return false;
         } else {
            Iw entitylivingbase = (Iw)p_apply_1_;
            if (!entitylivingbase.getItemStackFromSlot(Iu.getSlotForItemStack(this.armor)).isEmpty()) {
               return false;
            } else if (entitylivingbase instanceof Iu) {
               return ((Iu)entitylivingbase).canPickUpLoot();
            } else {
               return entitylivingbase instanceof IN ? true : entitylivingbase instanceof ME;
            }
         }
      }

      // $FF: synthetic method
      // $FF: bridge method
      public boolean apply(@Nullable Object var1) {
         return this.apply((Ig)var1);
      }
   }
}
