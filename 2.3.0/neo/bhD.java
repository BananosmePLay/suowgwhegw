package neo;

import javax.annotation.Nullable;

public class bhD {
   public final ME player;
   private boolean isDirty;
   private int minX;
   private int minY;
   private int maxX;
   private int maxY;
   private int tick;
   public int step;
   // $FF: synthetic field
   final bhE this$0;

   public bhD(bhE this$0, ME player) {
      this.this$0 = this$0;
      this.isDirty = true;
      this.maxX = 127;
      this.maxY = 127;
      this.player = player;
   }

   @Nullable
   public Sz<?> getPacket(Qy stack) {
      if (this.isDirty) {
         this.isDirty = false;
         return new Uk(stack.getMetadata(), this.this$0.scale, this.this$0.trackingPosition, this.this$0.mapDecorations.values(), this.this$0.colors, this.minX, this.minY, this.maxX + 1 - this.minX, this.maxY + 1 - this.minY);
      } else {
         return this.tick++ % 5 == 0 ? new Uk(stack.getMetadata(), this.this$0.scale, this.this$0.trackingPosition, this.this$0.mapDecorations.values(), this.this$0.colors, 0, 0, 0, 0) : null;
      }
   }

   public void update(int x, int y) {
      if (this.isDirty) {
         this.minX = Math.min(this.minX, x);
         this.minY = Math.min(this.minY, y);
         this.maxX = Math.max(this.maxX, x);
         this.maxY = Math.max(this.maxY, y);
      } else {
         this.isDirty = true;
         this.minX = x;
         this.minY = y;
         this.maxX = x;
         this.maxY = y;
      }

   }
}
