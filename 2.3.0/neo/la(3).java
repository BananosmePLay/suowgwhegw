package neo;

import com.google.common.collect.ComparisonChain;
import java.util.Comparator;

class la implements Comparator<pB> {
   private la() {
   }

   public int compare(pB p_compare_1_, pB p_compare_2_) {
      WA scoreplayerteam = p_compare_1_.getPlayerTeam();
      WA scoreplayerteam1 = p_compare_2_.getPlayerTeam();
      return ComparisonChain.start().compareTrueFirst(p_compare_1_.getGameType() != bbb.SPECTATOR, p_compare_2_.getGameType() != bbb.SPECTATOR).compare(scoreplayerteam != null ? scoreplayerteam.getRegisteredName() : "", scoreplayerteam1 != null ? scoreplayerteam1.getRegisteredName() : "").compare(p_compare_1_.getGameProfile().getName(), p_compare_2_.getGameProfile().getName()).result();
   }

   // $FF: synthetic method
   // $FF: bridge method
   public int compare(Object var1, Object var2) {
      return this.compare((pB)var1, (pB)var2);
   }

   // $FF: synthetic method
   la(Object x0) {
      this();
   }
}
