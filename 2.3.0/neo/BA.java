package neo;

import net.minecraft.util.MouseHelper;
import net.minecraft.util.MovementInput;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;

public interface BA {
   default void onStop() {
   }

   default void update() {
   }

   default void handleMovement(MovementInput input) {
   }

   default void handleMouse(MouseHelper mouseHelperIn) {
   }

   default void onMouseHover(pm worldIn, RayTraceResult result) {
   }

   default void onHitBlock(pm worldIn, BlockPos pos, in state, float diggingStage) {
   }

   default void openInventory() {
   }

   default void handleSetSlot(Qy stack) {
   }
}
