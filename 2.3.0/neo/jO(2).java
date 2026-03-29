package neo;

import java.util.Iterator;
import javax.annotation.Nullable;
import net.minecraft.util.TabCompleter;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentString;

public class jO extends TabCompleter {
   private final nC client = nC.getMinecraft();

   public jO(lE p_i46749_1_) {
      super(p_i46749_1_, false);
   }

   public void complete() {
      super.complete();
      if (this.completions.size() > 1) {
         StringBuilder stringbuilder = new StringBuilder();

         String s;
         for(Iterator var2 = this.completions.iterator(); var2.hasNext(); stringbuilder.append(s)) {
            s = (String)var2.next();
            if (stringbuilder.length() > 0) {
               stringbuilder.append(", ");
            }
         }

         this.client.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion(new TextComponentString(stringbuilder.toString()), 1);
      }

   }

   @Nullable
   public BlockPos getTargetBlockPos() {
      BlockPos blockpos = null;
      if (this.client.objectMouseOver != null && this.client.objectMouseOver.typeOfHit == RayTraceResult.Type.BLOCK) {
         blockpos = this.client.objectMouseOver.getBlockPos();
      }

      return blockpos;
   }
}
