package neo;

import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;

public class bnl extends pc {
   private boolean acting = false;
   private BlockPos lastClickBlockPos = null;
   private Ig lastClickEntity = null;

   public bnl(nC mcIn, py netHandler) {
      super(mcIn, netHandler);
   }

   public boolean clickBlock(BlockPos loc, EnumFacing face) {
      this.acting = true;
      this.lastClickBlockPos = loc;
      boolean flag = super.clickBlock(loc, face);
      this.acting = false;
      return flag;
   }

   public boolean onPlayerDamageBlock(BlockPos posBlock, EnumFacing directionFacing) {
      this.acting = true;
      this.lastClickBlockPos = posBlock;
      boolean flag = super.onPlayerDamageBlock(posBlock, directionFacing);
      this.acting = false;
      return flag;
   }

   public EnumActionResult processRightClick(ME player, bij worldIn, EnumHand hand) {
      this.acting = true;
      EnumActionResult enumactionresult = super.processRightClick(player, worldIn, hand);
      this.acting = false;
      return enumactionresult;
   }

   public EnumActionResult processRightClickBlock(jh player, pm worldIn, BlockPos pos, EnumFacing facing, Vec3d vec, EnumHand hand) {
      this.acting = true;
      this.lastClickBlockPos = pos;
      EnumActionResult enumactionresult = super.processRightClickBlock(player, worldIn, pos, facing, vec, hand);
      this.acting = false;
      return enumactionresult;
   }

   public EnumActionResult interactWithEntity(ME player, Ig target, EnumHand hand) {
      this.lastClickEntity = target;
      return super.interactWithEntity(player, target, hand);
   }

   public EnumActionResult interactWithEntity(ME player, Ig target, RayTraceResult ray, EnumHand hand) {
      this.lastClickEntity = target;
      return super.interactWithEntity(player, target, ray, hand);
   }

   public boolean isActing() {
      return this.acting;
   }

   public BlockPos getLastClickBlockPos() {
      return this.lastClickBlockPos;
   }

   public Ig getLastClickEntity() {
      return this.lastClickEntity;
   }
}
