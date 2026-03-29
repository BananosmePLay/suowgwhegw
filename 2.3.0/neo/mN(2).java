package neo;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class mN implements mS, mQ {
   private static final Ordering<pB> PROFILE_ORDER = Ordering.from(new Comparator<pB>() {
      public int compare(pB p_compare_1_, pB p_compare_2_) {
         return ComparisonChain.start().compare(p_compare_1_.getGameProfile().getId(), p_compare_2_.getGameProfile().getId()).result();
      }

      // $FF: synthetic method
      // $FF: bridge method
      public int compare(Object var1, Object var2) {
         return this.compare((pB)var1, (pB)var2);
      }
   });
   private final List<mQ> items;

   public mN() {
      this(PROFILE_ORDER.sortedCopy(nC.getMinecraft().getConnection().getPlayerInfoMap()));
   }

   public mN(Collection<pB> profiles) {
      this.items = Lists.newArrayList();
      Iterator var2 = PROFILE_ORDER.sortedCopy(profiles).iterator();

      while(var2.hasNext()) {
         pB networkplayerinfo = (pB)var2.next();
         if (networkplayerinfo.getGameType() != bbb.SPECTATOR) {
            this.items.add(new mU(networkplayerinfo.getGameProfile()));
         }
      }

   }

   public List<mQ> getItems() {
      return this.items;
   }

   public ITextComponent getPrompt() {
      return new TextComponentTranslation("spectatorMenu.teleport.prompt", new Object[0]);
   }

   public void selectItem(mY menu) {
      menu.selectCategory(this);
   }

   public ITextComponent getSpectatorName() {
      return new TextComponentTranslation("spectatorMenu.teleport", new Object[0]);
   }

   public void renderIcon(float brightness, int alpha) {
      nC.getMinecraft().getTextureManager().bindTexture(lB.SPECTATOR_WIDGETS);
      jI.drawModalRectWithCustomSizedTexture(0, 0, 0.0F, 0.0F, 16, 16, 256.0F, 256.0F);
   }

   public boolean isEnabled() {
      return !this.items.isEmpty();
   }
}
