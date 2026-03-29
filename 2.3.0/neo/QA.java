package neo;

import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.translation.I18n;

public class QA extends OT {
   public QA() {
   }

   public Qy getDefaultInstance() {
      return Wg.addPotionToItemStack(super.getDefaultInstance(), NN.POISON);
   }

   public MO createArrow(bij worldIn, Qy stack, Iw shooter) {
      Ne entitytippedarrow = new Ne(worldIn, shooter);
      entitytippedarrow.setPotionEffect(stack);
      return entitytippedarrow;
   }

   public void getSubItems(EN tab, NonNullList<Qy> items) {
      if (this.isInCreativeTab(tab)) {
         Iterator var3 = Wf.REGISTRY.iterator();

         while(var3.hasNext()) {
            Wf potiontype = (Wf)var3.next();
            if (!potiontype.getEffects().isEmpty()) {
               items.add(Wg.addPotionToItemStack(new Qy(this), potiontype));
            }
         }
      }

   }

   public void addInformation(Qy stack, @Nullable bij worldIn, List<String> tooltip, BJ flagIn) {
      Wg.addPotionTooltip(stack, tooltip, 0.125F);
   }

   public String getItemStackDisplayName(Qy stack) {
      return I18n.translateToLocal(Wg.getPotionFromItem(stack).getNamePrefixed("tipped_arrow.effect."));
   }
}
