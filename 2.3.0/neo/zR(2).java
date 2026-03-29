package neo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class zR {
   private static final Logger LOGGER = LogManager.getLogger();
   private final zP type;
   private final zQ usage;
   private final int index;
   private final int elementCount;

   public zR(int indexIn, zP typeIn, zQ usageIn, int count) {
      if (this.isFirstOrUV(indexIn, usageIn)) {
         this.usage = usageIn;
      } else {
         LOGGER.warn("Multiple vertex elements of the same type other than UVs are not supported. Forcing type to UV.");
         this.usage = zQ.UV;
      }

      this.type = typeIn;
      this.index = indexIn;
      this.elementCount = count;
   }

   private final boolean isFirstOrUV(int p_177372_1_, zQ p_177372_2_) {
      return p_177372_1_ == 0 || p_177372_2_ == zQ.UV;
   }

   public final zP getType() {
      return this.type;
   }

   public final zQ getUsage() {
      return this.usage;
   }

   public final int getElementCount() {
      return this.elementCount;
   }

   public final int getIndex() {
      return this.index;
   }

   public String toString() {
      return this.elementCount + "," + this.usage.getDisplayName() + "," + this.type.getDisplayName();
   }

   public final int getSize() {
      return this.type.getSize() * this.elementCount;
   }

   public final boolean isPositionElement() {
      return this.usage == zQ.POSITION;
   }

   public boolean equals(Object p_equals_1_) {
      if (this == p_equals_1_) {
         return true;
      } else if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
         zR vertexformatelement = (zR)p_equals_1_;
         if (this.elementCount != vertexformatelement.elementCount) {
            return false;
         } else if (this.index != vertexformatelement.index) {
            return false;
         } else if (this.type != vertexformatelement.type) {
            return false;
         } else {
            return this.usage == vertexformatelement.usage;
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      int i = this.type.hashCode();
      i = 31 * i + this.usage.hashCode();
      i = 31 * i + this.index;
      i = 31 * i + this.elementCount;
      return i;
   }
}
