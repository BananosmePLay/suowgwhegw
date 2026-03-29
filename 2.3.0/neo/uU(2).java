package neo;

import java.util.Iterator;
import net.minecraft.util.math.BlockPos;

public class uU implements uQ {
   private final nC minecraft;

   public uU(nC minecraftIn) {
      this.minecraft = minecraftIn;
   }

   public void render(float partialTicks, long finishTimeNano) {
      nC var10000 = this.minecraft;
      ME entityplayer = nC.player;
      bij world = this.minecraft.world;
      double d0 = entityplayer.lastTickPosX + (entityplayer.posX - entityplayer.lastTickPosX) * (double)partialTicks;
      double d1 = entityplayer.lastTickPosY + (entityplayer.posY - entityplayer.lastTickPosY) * (double)partialTicks;
      double d2 = entityplayer.lastTickPosZ + (entityplayer.posZ - entityplayer.lastTickPosZ) * (double)partialTicks;
      yh.pushMatrix();
      yh.enableBlend();
      yh.tryBlendFuncSeparate(ya.SRC_ALPHA, xR.ONE_MINUS_SRC_ALPHA, ya.ONE, xR.ZERO);
      yh.disableTexture2D();
      BlockPos blockpos = new BlockPos(entityplayer.posX, 0.0, entityplayer.posZ);
      Iterable<BlockPos> iterable = BlockPos.getAllInBox(blockpos.add(-40, 0, -40), blockpos.add(40, 0, 40));
      yN tessellator = yN.getInstance();
      tN bufferbuilder = tessellator.getBuffer();
      bufferbuilder.begin(5, zK.POSITION_COLOR);
      Iterator var16 = iterable.iterator();

      while(var16.hasNext()) {
         BlockPos blockpos1 = (BlockPos)var16.next();
         int i = ((bij)world).getHeight(blockpos1.getX(), blockpos1.getZ());
         if (((bij)world).getBlockState(blockpos1.add(0, i, 0).down()) == Nk.AIR.getDefaultState()) {
            yy.addChainedFilledBoxVertices(bufferbuilder, (double)((float)blockpos1.getX() + 0.25F) - d0, (double)i - d1, (double)((float)blockpos1.getZ() + 0.25F) - d2, (double)((float)blockpos1.getX() + 0.75F) - d0, (double)i + 0.09375 - d1, (double)((float)blockpos1.getZ() + 0.75F) - d2, 0.0F, 0.0F, 1.0F, 0.5F);
         } else {
            yy.addChainedFilledBoxVertices(bufferbuilder, (double)((float)blockpos1.getX() + 0.25F) - d0, (double)i - d1, (double)((float)blockpos1.getZ() + 0.25F) - d2, (double)((float)blockpos1.getX() + 0.75F) - d0, (double)i + 0.09375 - d1, (double)((float)blockpos1.getZ() + 0.75F) - d2, 0.0F, 1.0F, 0.0F, 0.5F);
         }
      }

      tessellator.draw();
      yh.enableTexture2D();
      yh.popMatrix();
   }
}
