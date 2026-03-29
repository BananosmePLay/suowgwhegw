package neo;

import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import net.minecraft.util.LowerStringMap;

public class FV extends FU {
   private final Set<FZ> dirtyInstances = Sets.newHashSet();
   protected final Map<String, FZ> instancesByName = new LowerStringMap();

   public FV() {
   }

   public Ga getAttributeInstance(FY attribute) {
      return (Ga)super.getAttributeInstance(attribute);
   }

   public Ga getAttributeInstanceByName(String attributeName) {
      FZ iattributeinstance = super.getAttributeInstanceByName(attributeName);
      if (iattributeinstance == null) {
         iattributeinstance = (FZ)this.instancesByName.get(attributeName);
      }

      return (Ga)iattributeinstance;
   }

   public FZ registerAttribute(FY attribute) {
      FZ iattributeinstance = super.registerAttribute(attribute);
      if (attribute instanceof Gc && ((Gc)attribute).getDescription() != null) {
         this.instancesByName.put(((Gc)attribute).getDescription(), iattributeinstance);
      }

      return iattributeinstance;
   }

   protected FZ createInstance(FY attribute) {
      return new Ga(this, attribute);
   }

   public void onAttributeModified(FZ instance) {
      if (instance.getAttribute().getShouldWatch()) {
         this.dirtyInstances.add(instance);
      }

      Iterator var2 = this.descendantsByParent.get(instance.getAttribute()).iterator();

      while(var2.hasNext()) {
         FY iattribute = (FY)var2.next();
         Ga modifiableattributeinstance = this.getAttributeInstance(iattribute);
         if (modifiableattributeinstance != null) {
            modifiableattributeinstance.flagForUpdate();
         }
      }

   }

   public Set<FZ> getDirtyInstances() {
      return this.dirtyInstances;
   }

   public Collection<FZ> getWatchedAttributes() {
      Set<FZ> set = Sets.newHashSet();
      Iterator var2 = this.getAllAttributes().iterator();

      while(var2.hasNext()) {
         FZ iattributeinstance = (FZ)var2.next();
         if (iattributeinstance.getAttribute().getShouldWatch()) {
            set.add(iattributeinstance);
         }
      }

      return set;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public FZ getAttributeInstanceByName(String var1) {
      return this.getAttributeInstanceByName(var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public FZ getAttributeInstance(FY var1) {
      return this.getAttributeInstance(var1);
   }
}
