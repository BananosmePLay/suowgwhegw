package net.minecraft.util;

import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class LowerStringMap<V> implements Map<String, V> {
   private final Map<String, V> internalMap = Maps.newLinkedHashMap();

   public LowerStringMap() {
   }

   public int size() {
      return this.internalMap.size();
   }

   public boolean isEmpty() {
      return this.internalMap.isEmpty();
   }

   public boolean containsKey(Object p_containsKey_1_) {
      return this.internalMap.containsKey(p_containsKey_1_.toString().toLowerCase(Locale.ROOT));
   }

   public boolean containsValue(Object p_containsValue_1_) {
      return this.internalMap.containsKey(p_containsValue_1_);
   }

   public V get(Object p_get_1_) {
      return this.internalMap.get(p_get_1_.toString().toLowerCase(Locale.ROOT));
   }

   public V put(String p_put_1_, V p_put_2_) {
      return this.internalMap.put(p_put_1_.toLowerCase(Locale.ROOT), p_put_2_);
   }

   public V remove(Object p_remove_1_) {
      return this.internalMap.remove(p_remove_1_.toString().toLowerCase(Locale.ROOT));
   }

   public void putAll(Map<? extends String, ? extends V> p_putAll_1_) {
      Iterator var2 = p_putAll_1_.entrySet().iterator();

      while(var2.hasNext()) {
         Map.Entry<? extends String, ? extends V> entry = (Map.Entry)var2.next();
         this.put((String)entry.getKey(), entry.getValue());
      }

   }

   public void clear() {
      this.internalMap.clear();
   }

   public Set<String> keySet() {
      return this.internalMap.keySet();
   }

   public Collection<V> values() {
      return this.internalMap.values();
   }

   public Set<Map.Entry<String, V>> entrySet() {
      return this.internalMap.entrySet();
   }

   // $FF: synthetic method
   // $FF: bridge method
   public Object put(Object var1, Object var2) {
      return this.put((String)var1, var2);
   }
}
