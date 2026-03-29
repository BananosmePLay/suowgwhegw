package neo;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.util.LowerStringMap;

public abstract class FU {
   protected final Map<FY, FZ> attributes = Maps.newHashMap();
   protected final Map<String, FZ> attributesByName = new LowerStringMap();
   protected final Multimap<FY, FY> descendantsByParent = HashMultimap.create();

   public FU() {
   }

   public FZ getAttributeInstance(FY attribute) {
      return (FZ)this.attributes.get(attribute);
   }

   @Nullable
   public FZ getAttributeInstanceByName(String attributeName) {
      return (FZ)this.attributesByName.get(attributeName);
   }

   public FZ registerAttribute(FY attribute) {
      if (this.attributesByName.containsKey(attribute.getName())) {
         throw new IllegalArgumentException("Attribute is already registered!");
      } else {
         FZ iattributeinstance = this.createInstance(attribute);
         this.attributesByName.put(attribute.getName(), iattributeinstance);
         this.attributes.put(attribute, iattributeinstance);

         for(FY iattribute = attribute.getParent(); iattribute != null; iattribute = iattribute.getParent()) {
            this.descendantsByParent.put(iattribute, attribute);
         }

         return iattributeinstance;
      }
   }

   protected abstract FZ createInstance(FY var1);

   public Collection<FZ> getAllAttributes() {
      return this.attributesByName.values();
   }

   public void onAttributeModified(FZ instance) {
   }

   public void removeAttributeModifiers(Multimap<String, FW> modifiers) {
      Iterator var2 = modifiers.entries().iterator();

      while(var2.hasNext()) {
         Map.Entry<String, FW> entry = (Map.Entry)var2.next();
         FZ iattributeinstance = this.getAttributeInstanceByName((String)entry.getKey());
         if (iattributeinstance != null) {
            iattributeinstance.removeModifier((FW)entry.getValue());
         }
      }

   }

   public void applyAttributeModifiers(Multimap<String, FW> modifiers) {
      Iterator var2 = modifiers.entries().iterator();

      while(var2.hasNext()) {
         Map.Entry<String, FW> entry = (Map.Entry)var2.next();
         FZ iattributeinstance = this.getAttributeInstanceByName((String)entry.getKey());
         if (iattributeinstance != null) {
            iattributeinstance.removeModifier((FW)entry.getValue());
            iattributeinstance.applyModifier((FW)entry.getValue());
         }
      }

   }
}
