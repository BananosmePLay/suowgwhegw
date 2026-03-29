package neo;

import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public interface DB {
   String getName();

   default ITextComponent getDisplayName() {
      return new TextComponentString(this.getName());
   }

   default void sendMessage(ITextComponent component) {
   }

   boolean canUseCommand(int var1, String var2);

   default BlockPos getPosition() {
      return BlockPos.ORIGIN;
   }

   default Vec3d getPositionVector() {
      return Vec3d.ZERO;
   }

   bij getEntityWorld();

   @Nullable
   default Ig getCommandSenderEntity() {
      return null;
   }

   default boolean sendCommandFeedback() {
      return false;
   }

   default void setCommandStat(CK type, int amount) {
   }

   @Nullable
   Xx getServer();
}
