package net.minecraft.entity.ai.attributes;

import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import net.minecraft.util.LowerStringMap;

public class AttributeMap extends AbstractAttributeMap {
   private final Set<IAttributeInstance> dirtyInstances = Sets.newHashSet();
   protected final Map<String, IAttributeInstance> instancesByName = new LowerStringMap();

   public AttributeMap() {
   }

   public ModifiableAttributeInstance getAttributeInstance(IAttribute attribute) {
      return (ModifiableAttributeInstance)super.getAttributeInstance(attribute);
   }

   public ModifiableAttributeInstance getAttributeInstanceByName(String attributeName) {
      IAttributeInstance iattributeinstance = super.getAttributeInstanceByName(attributeName);
      if (iattributeinstance == null) {
         iattributeinstance = (IAttributeInstance)this.instancesByName.get(attributeName);
      }

      return (ModifiableAttributeInstance)iattributeinstance;
   }

   public IAttributeInstance registerAttribute(IAttribute attribute) {
      IAttributeInstance iattributeinstance = super.registerAttribute(attribute);
      if (attribute instanceof RangedAttribute && ((RangedAttribute)attribute).getDescription() != null) {
         this.instancesByName.put(((RangedAttribute)attribute).getDescription(), iattributeinstance);
      }

      return iattributeinstance;
   }

   protected IAttributeInstance createInstance(IAttribute attribute) {
      return new ModifiableAttributeInstance(this, attribute);
   }

   public void onAttributeModified(IAttributeInstance instance) {
      if (instance.getAttribute().getShouldWatch()) {
         this.dirtyInstances.add(instance);
      }

      Iterator var2 = this.descendantsByParent.get(instance.getAttribute()).iterator();

      while(var2.hasNext()) {
         IAttribute iattribute = (IAttribute)var2.next();
         ModifiableAttributeInstance modifiableattributeinstance = this.getAttributeInstance(iattribute);
         if (modifiableattributeinstance != null) {
            modifiableattributeinstance.flagForUpdate();
         }
      }

   }

   public Set<IAttributeInstance> getDirtyInstances() {
      return this.dirtyInstances;
   }

   public Collection<IAttributeInstance> getWatchedAttributes() {
      Set<IAttributeInstance> set = Sets.newHashSet();
      Iterator var2 = this.getAllAttributes().iterator();

      while(var2.hasNext()) {
         IAttributeInstance iattributeinstance = (IAttributeInstance)var2.next();
         if (iattributeinstance.getAttribute().getShouldWatch()) {
            set.add(iattributeinstance);
         }
      }

      return set;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public IAttributeInstance getAttributeInstanceByName(String var1) {
      return this.getAttributeInstanceByName(var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public IAttributeInstance getAttributeInstance(IAttribute var1) {
      return this.getAttributeInstance(var1);
   }
}
