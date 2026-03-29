package neo;

import java.util.Iterator;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class uY implements uQ {
   private final nC minecraft;
   private ME player;
   private double xo;
   private double yo;
   private double zo;

   public uY(nC minecraftIn) {
      this.minecraft = minecraftIn;
   }

   public void render(float partialTicks, long finishTimeNano) {
      nC var10001 = this.minecraft;
      this.player = nC.player;
      this.xo = this.player.lastTickPosX + (this.player.posX - this.player.lastTickPosX) * (double)partialTicks;
      this.yo = this.player.lastTickPosY + (this.player.posY - this.player.lastTickPosY) * (double)partialTicks;
      this.zo = this.player.lastTickPosZ + (this.player.posZ - this.player.lastTickPosZ) * (double)partialTicks;
      nC var10000 = this.minecraft;
      BlockPos blockpos = nC.player.getPosition();
      var10000 = this.minecraft;
      bij world = nC.player.world;
      yh.enableBlend();
      yh.tryBlendFuncSeparate(ya.SRC_ALPHA, xR.ONE_MINUS_SRC_ALPHA, ya.ONE, xR.ZERO);
      yh.color(0.0F, 1.0F, 0.0F, 0.75F);
      yh.disableTexture2D();
      yh.glLineWidth(6.0F);
      Iterator var6 = BlockPos.getAllInBox(blockpos.add(-10, -10, -10), blockpos.add(10, 10, 10)).iterator();

      while(true) {
         BlockPos blockpos2;
         in iblockstate1;
         do {
            if (!var6.hasNext()) {
               var6 = BlockPos.getAllInBox(blockpos.add(-10, -10, -10), blockpos.add(10, 10, 10)).iterator();

               while(true) {
                  do {
                     if (!var6.hasNext()) {
                        yh.enableTexture2D();
                        yh.disableBlend();
                        return;
                     }

                     blockpos2 = (BlockPos)var6.next();
                     iblockstate1 = world.getBlockState(blockpos2);
                  } while(iblockstate1.getBlock() != Nk.WATER && iblockstate1.getBlock() != Nk.FLOWING_WATER);

                  Integer integer = (Integer)iblockstate1.getValue(eB.LEVEL);
                  double d1 = integer > 7 ? 0.9 : 1.0 - 0.11 * (double)integer;
                  String s = iblockstate1.getBlock() == Nk.FLOWING_WATER ? "f" : "s";
                  uR.renderDebugText(s + " " + integer, (double)blockpos2.getX() + 0.5, (double)blockpos2.getY() + d1, (double)blockpos2.getZ() + 0.5, partialTicks, -16777216);
               }
            }

            blockpos2 = (BlockPos)var6.next();
            iblockstate1 = world.getBlockState(blockpos2);
         } while(iblockstate1.getBlock() != Nk.WATER && iblockstate1.getBlock() != Nk.FLOWING_WATER);

         double d0 = (double)eB.getLiquidHeight(iblockstate1, world, blockpos2);
         yy.renderFilledBox((new AxisAlignedBB((double)((float)blockpos2.getX() + 0.01F), (double)((float)blockpos2.getY() + 0.01F), (double)((float)blockpos2.getZ() + 0.01F), (double)((float)blockpos2.getX() + 0.99F), d0, (double)((float)blockpos2.getZ() + 0.99F))).offset(-this.xo, -this.yo, -this.zo), 1.0F, 1.0F, 1.0F, 0.2F);
      }
   }
}
