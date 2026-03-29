package neo;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class iQ implements iD<iP> {
   private final List<iD<iP>> accessorList = Lists.newArrayList();
   private final Random rnd = new Random();
   private final ResourceLocation location;
   private final ITextComponent subtitle;

   public iQ(ResourceLocation locationIn, @Nullable String subtitleIn) {
      this.location = locationIn;
      this.subtitle = subtitleIn == null ? null : new TextComponentTranslation(subtitleIn, new Object[0]);
   }

   public int getWeight() {
      int i = 0;

      iD isoundeventaccessor;
      for(Iterator var2 = this.accessorList.iterator(); var2.hasNext(); i += isoundeventaccessor.getWeight()) {
         isoundeventaccessor = (iD)var2.next();
      }

      return i;
   }

   public iP cloneEntry() {
      int i = this.getWeight();
      if (!this.accessorList.isEmpty() && i != 0) {
         int j = this.rnd.nextInt(i);
         Iterator var3 = this.accessorList.iterator();

         iD isoundeventaccessor;
         do {
            if (!var3.hasNext()) {
               return iU.MISSING_SOUND;
            }

            isoundeventaccessor = (iD)var3.next();
            j -= isoundeventaccessor.getWeight();
         } while(j >= 0);

         return (iP)isoundeventaccessor.cloneEntry();
      } else {
         return iU.MISSING_SOUND;
      }
   }

   public void addSound(iD<iP> p_188715_1_) {
      this.accessorList.add(p_188715_1_);
   }

   public ResourceLocation getLocation() {
      return this.location;
   }

   @Nullable
   public ITextComponent getSubtitle() {
      return this.subtitle;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public Object cloneEntry() {
      return this.cloneEntry();
   }
}
