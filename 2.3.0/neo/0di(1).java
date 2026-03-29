package neo;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class 0di extends 0dc {
   public final EnumFacing field_a;
   public final BlockPos field_b;
   private static String _ _;

   private static EnumFacing method_bAo(0di var0) {
      return var0.field_a;
   }

   public _di/* $FF was: 0di*/(BlockPos a, EnumFacing b) {
      this.field_b = a;
      this.field_a = b;
   }

   private static BlockPos method_bAn(0di var0) {
      return var0.field_b;
   }

   public EnumFacing method_bAm() {
      return method_bAo(this);
   }

   public BlockPos method_bAl() {
      return method_bAn(this);
   }
}
