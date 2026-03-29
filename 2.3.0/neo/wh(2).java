package neo;

import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class wh extends vI<IW> {
   public wh(wC renderManagerIn) {
      super(renderManagerIn);
      this.shadowSize = 0.5F;
   }

   public void doRender(IW entity, double x, double y, double z, float entityYaw, float partialTicks) {
      if (entity.getBlock() != null) {
         in iblockstate = entity.getBlock();
         if (iblockstate.getRenderType() == EnumBlockRenderType.MODEL) {
            bij world = entity.getWorldObj();
            if (iblockstate != world.getBlockState(new BlockPos(entity)) && iblockstate.getRenderType() != EnumBlockRenderType.INVISIBLE) {
               this.bindTexture(zj.LOCATION_BLOCKS_TEXTURE);
               yh.pushMatrix();
               yh.disableLighting();
               yN tessellator = yN.getInstance();
               tN bufferbuilder = tessellator.getBuffer();
               if (this.renderOutlines) {
                  yh.enableColorMaterial();
                  yh.enableOutlineMode(this.getTeamColor(entity));
               }

               bufferbuilder.begin(7, zK.BLOCK);
               BlockPos blockpos = new BlockPos(entity.posX, entity.getEntityBoundingBox().maxY, entity.posZ);
               yh.translate((float)(x - (double)blockpos.getX() - 0.5), (float)(y - (double)blockpos.getY()), (float)(z - (double)blockpos.getZ() - 0.5));
               tJ blockrendererdispatcher = nC.getMinecraft().getBlockRendererDispatcher();
               blockrendererdispatcher.getBlockModelRenderer().renderModel(world, blockrendererdispatcher.getModelForState(iblockstate), iblockstate, blockpos, bufferbuilder, false, MathHelper.getPositionRandom(entity.getOrigin()));
               tessellator.draw();
               if (this.renderOutlines) {
                  yh.disableOutlineMode();
                  yh.disableColorMaterial();
               }

               yh.enableLighting();
               yh.popMatrix();
               super.doRender(entity, x, y, z, entityYaw, partialTicks);
            }
         }
      }

   }

   protected ResourceLocation getEntityTexture(IW entity) {
      return zj.LOCATION_BLOCKS_TEXTURE;
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((IW)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Ig var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((IW)var1, var2, var4, var6, var8, var9);
   }
}
