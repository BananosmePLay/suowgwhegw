package neo;

import java.util.Iterator;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class uX implements uQ {
   private final nC minecraft;

   public uX(nC minecraftIn) {
      this.minecraft = minecraftIn;
   }

   public void render(float partialTicks, long finishTimeNano) {
      nC var10000 = this.minecraft;
      ME entityplayer = nC.player;
      double d0 = entityplayer.lastTickPosX + (entityplayer.posX - entityplayer.lastTickPosX) * (double)partialTicks;
      double d1 = entityplayer.lastTickPosY + (entityplayer.posY - entityplayer.lastTickPosY) * (double)partialTicks;
      double d2 = entityplayer.lastTickPosZ + (entityplayer.posZ - entityplayer.lastTickPosZ) * (double)partialTicks;
      var10000 = this.minecraft;
      bij world = nC.player.world;
      Iterable<BlockPos> iterable = BlockPos.getAllInBox(MathHelper.floor(entityplayer.posX - 6.0), MathHelper.floor(entityplayer.posY - 6.0), MathHelper.floor(entityplayer.posZ - 6.0), MathHelper.floor(entityplayer.posX + 6.0), MathHelper.floor(entityplayer.posY + 6.0), MathHelper.floor(entityplayer.posZ + 6.0));
      yh.enableBlend();
      yh.tryBlendFuncSeparate(ya.SRC_ALPHA, xR.ONE_MINUS_SRC_ALPHA, ya.ONE, xR.ZERO);
      yh.glLineWidth(2.0F);
      yh.disableTexture2D();
      yh.depthMask(false);
      Iterator var13 = iterable.iterator();

      while(var13.hasNext()) {
         BlockPos blockpos = (BlockPos)var13.next();
         in iblockstate = world.getBlockState(blockpos);
         if (iblockstate.getBlock() != Nk.AIR) {
            AxisAlignedBB axisalignedbb = iblockstate.getSelectedBoundingBox(world, blockpos).grow(0.002).offset(-d0, -d1, -d2);
            double d3 = axisalignedbb.minX;
            double d4 = axisalignedbb.minY;
            double d5 = axisalignedbb.minZ;
            double d6 = axisalignedbb.maxX;
            double d7 = axisalignedbb.maxY;
            double d8 = axisalignedbb.maxZ;
            float f = 1.0F;
            float f1 = 0.0F;
            float f2 = 0.0F;
            float f3 = 0.5F;
            yN tessellator5;
            tN bufferbuilder5;
            if (iblockstate.getBlockFaceShape(world, blockpos, EnumFacing.WEST) == ib.SOLID) {
               tessellator5 = yN.getInstance();
               bufferbuilder5 = tessellator5.getBuffer();
               bufferbuilder5.begin(5, zK.POSITION_COLOR);
               bufferbuilder5.pos(d3, d4, d5).color(1.0F, 0.0F, 0.0F, 0.5F).endVertex();
               bufferbuilder5.pos(d3, d4, d8).color(1.0F, 0.0F, 0.0F, 0.5F).endVertex();
               bufferbuilder5.pos(d3, d7, d5).color(1.0F, 0.0F, 0.0F, 0.5F).endVertex();
               bufferbuilder5.pos(d3, d7, d8).color(1.0F, 0.0F, 0.0F, 0.5F).endVertex();
               tessellator5.draw();
            }

            if (iblockstate.getBlockFaceShape(world, blockpos, EnumFacing.SOUTH) == ib.SOLID) {
               tessellator5 = yN.getInstance();
               bufferbuilder5 = tessellator5.getBuffer();
               bufferbuilder5.begin(5, zK.POSITION_COLOR);
               bufferbuilder5.pos(d3, d7, d8).color(1.0F, 0.0F, 0.0F, 0.5F).endVertex();
               bufferbuilder5.pos(d3, d4, d8).color(1.0F, 0.0F, 0.0F, 0.5F).endVertex();
               bufferbuilder5.pos(d6, d7, d8).color(1.0F, 0.0F, 0.0F, 0.5F).endVertex();
               bufferbuilder5.pos(d6, d4, d8).color(1.0F, 0.0F, 0.0F, 0.5F).endVertex();
               tessellator5.draw();
            }

            if (iblockstate.getBlockFaceShape(world, blockpos, EnumFacing.EAST) == ib.SOLID) {
               tessellator5 = yN.getInstance();
               bufferbuilder5 = tessellator5.getBuffer();
               bufferbuilder5.begin(5, zK.POSITION_COLOR);
               bufferbuilder5.pos(d6, d4, d8).color(1.0F, 0.0F, 0.0F, 0.5F).endVertex();
               bufferbuilder5.pos(d6, d4, d5).color(1.0F, 0.0F, 0.0F, 0.5F).endVertex();
               bufferbuilder5.pos(d6, d7, d8).color(1.0F, 0.0F, 0.0F, 0.5F).endVertex();
               bufferbuilder5.pos(d6, d7, d5).color(1.0F, 0.0F, 0.0F, 0.5F).endVertex();
               tessellator5.draw();
            }

            if (iblockstate.getBlockFaceShape(world, blockpos, EnumFacing.NORTH) == ib.SOLID) {
               tessellator5 = yN.getInstance();
               bufferbuilder5 = tessellator5.getBuffer();
               bufferbuilder5.begin(5, zK.POSITION_COLOR);
               bufferbuilder5.pos(d6, d7, d5).color(1.0F, 0.0F, 0.0F, 0.5F).endVertex();
               bufferbuilder5.pos(d6, d4, d5).color(1.0F, 0.0F, 0.0F, 0.5F).endVertex();
               bufferbuilder5.pos(d3, d7, d5).color(1.0F, 0.0F, 0.0F, 0.5F).endVertex();
               bufferbuilder5.pos(d3, d4, d5).color(1.0F, 0.0F, 0.0F, 0.5F).endVertex();
               tessellator5.draw();
            }

            if (iblockstate.getBlockFaceShape(world, blockpos, EnumFacing.DOWN) == ib.SOLID) {
               tessellator5 = yN.getInstance();
               bufferbuilder5 = tessellator5.getBuffer();
               bufferbuilder5.begin(5, zK.POSITION_COLOR);
               bufferbuilder5.pos(d3, d4, d5).color(1.0F, 0.0F, 0.0F, 0.5F).endVertex();
               bufferbuilder5.pos(d6, d4, d5).color(1.0F, 0.0F, 0.0F, 0.5F).endVertex();
               bufferbuilder5.pos(d3, d4, d8).color(1.0F, 0.0F, 0.0F, 0.5F).endVertex();
               bufferbuilder5.pos(d6, d4, d8).color(1.0F, 0.0F, 0.0F, 0.5F).endVertex();
               tessellator5.draw();
            }

            if (iblockstate.getBlockFaceShape(world, blockpos, EnumFacing.UP) == ib.SOLID) {
               tessellator5 = yN.getInstance();
               bufferbuilder5 = tessellator5.getBuffer();
               bufferbuilder5.begin(5, zK.POSITION_COLOR);
               bufferbuilder5.pos(d3, d7, d5).color(1.0F, 0.0F, 0.0F, 0.5F).endVertex();
               bufferbuilder5.pos(d3, d7, d8).color(1.0F, 0.0F, 0.0F, 0.5F).endVertex();
               bufferbuilder5.pos(d6, d7, d5).color(1.0F, 0.0F, 0.0F, 0.5F).endVertex();
               bufferbuilder5.pos(d6, d7, d8).color(1.0F, 0.0F, 0.0F, 0.5F).endVertex();
               tessellator5.draw();
            }
         }
      }

      yh.depthMask(true);
      yh.enableTexture2D();
      yh.disableBlend();
   }
}
