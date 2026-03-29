package neo;

import [Ljava.lang.Object;;

public class bqm {
   private Object[] keys;
   private int hashcode;

   public bqm(Object[] keys) {
      this.hashcode = 0;
      this.keys = (Object[])((Object;)keys).clone();
   }

   public bqm(Object k1, Object k2) {
      this(new Object[]{k1, k2});
   }

   public bqm(Object k1, Object k2, Object k3) {
      this(new Object[]{k1, k2, k3});
   }

   public int hashCode() {
      if (this.hashcode == 0) {
         this.hashcode = 7;

         for(int i = 0; i < this.keys.length; ++i) {
            Object object = this.keys[i];
            if (object != null) {
               this.hashcode = 31 * this.hashcode + object.hashCode();
            }
         }
      }

      return this.hashcode;
   }

   public boolean equals(Object obj) {
      if (obj == null) {
         return false;
      } else if (obj == this) {
         return true;
      } else if (!(obj instanceof bqm)) {
         return false;
      } else {
         bqm compoundkey = (bqm)obj;
         Object[] aobject = compoundkey.getKeys();
         if (aobject.length != this.keys.length) {
            return false;
         } else {
            for(int i = 0; i < this.keys.length; ++i) {
               if (!compareKeys(this.keys[i], aobject[i])) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   private static boolean compareKeys(Object key1, Object key2) {
      if (key1 == key2) {
         return true;
      } else if (key1 == null) {
         return false;
      } else {
         return key2 == null ? false : key1.equals(key2);
      }
   }

   private Object[] getKeys() {
      return this.keys;
   }

   public Object[] getKeysCopy() {
      return (Object[])this.keys.clone();
   }

   public String toString() {
      return "[" + XH.arrayToString(this.keys) + "]";
   }
}
