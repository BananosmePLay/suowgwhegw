package neo;

import net.minecraft.util.math.BlockPos;

public class bgg implements Comparable<bgg> {
   private static long nextTickEntryID;
   private final co block;
   public final BlockPos position;
   public long scheduledTime;
   public int priority;
   private final long tickEntryID;

   public bgg(BlockPos positionIn, co blockIn) {
      this.tickEntryID = (long)(nextTickEntryID++);
      this.position = positionIn.toImmutable();
      this.block = blockIn;
   }

   public boolean equals(Object p_equals_1_) {
      if (!(p_equals_1_ instanceof bgg)) {
         return false;
      } else {
         bgg nextticklistentry = (bgg)p_equals_1_;
         return this.position.equals(nextticklistentry.position) && co.isEqualTo(this.block, nextticklistentry.block);
      }
   }

   public int hashCode() {
      return this.position.hashCode();
   }

   public bgg setScheduledTime(long scheduledTimeIn) {
      this.scheduledTime = scheduledTimeIn;
      return this;
   }

   public void setPriority(int priorityIn) {
      this.priority = priorityIn;
   }

   public int compareTo(bgg p_compareTo_1_) {
      if (this.scheduledTime < p_compareTo_1_.scheduledTime) {
         return -1;
      } else if (this.scheduledTime > p_compareTo_1_.scheduledTime) {
         return 1;
      } else if (this.priority != p_compareTo_1_.priority) {
         return this.priority - p_compareTo_1_.priority;
      } else if (this.tickEntryID < p_compareTo_1_.tickEntryID) {
         return -1;
      } else {
         return this.tickEntryID > p_compareTo_1_.tickEntryID ? 1 : 0;
      }
   }

   public String toString() {
      return co.getIdFromBlock(this.block) + ": " + this.position + ", " + this.scheduledTime + ", " + this.priority + ", " + this.tickEntryID;
   }

   public co getBlock() {
      return this.block;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public int compareTo(Object var1) {
      return this.compareTo((bgg)var1);
   }
}
