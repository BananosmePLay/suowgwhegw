package neo;

import net.minecraft.util.math.MathHelper;

public class mq {
   private final NS ingredient;
   private final int x;
   private final int y;
   // $FF: synthetic field
   final mr this$0;

   public mq(mr this$0, NS p_i47604_2_, int p_i47604_3_, int p_i47604_4_) {
      this.this$0 = this$0;
      this.ingredient = p_i47604_2_;
      this.x = p_i47604_3_;
      this.y = p_i47604_4_;
   }

   public int getX() {
      return this.x;
   }

   public int getY() {
      return this.y;
   }

   public Qy getItem() {
      Qy[] aitemstack = this.ingredient.getMatchingStacks();
      return aitemstack[MathHelper.floor(mr.access$000(this.this$0) / 30.0F) % aitemstack.length];
   }
}
