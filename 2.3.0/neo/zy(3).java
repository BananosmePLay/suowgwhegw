package neo;

import net.minecraft.util.math.BlockPos;

public class zy extends zF<YK> {
   private final tJ blockRenderer = nC.getMinecraft().getBlockRendererDispatcher();

   public zy() {
   }

   public void render(YK te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
      BlockPos blockpos = te.getPos();
      in iblockstate = te.getPistonState();
      co block = iblockstate.getBlock();
      if (iblockstate.getMaterial() != hM.AIR && te.getProgress(partialTicks) < 1.0F) {
         yN tessellator = yN.getInstance();
         tN bufferbuilder = tessellator.getBuffer();
         this.bindTexture(zj.LOCATION_BLOCKS_TEXTURE);
         yz.disableStandardItemLighting();
         yh.blendFunc(ya.SRC_ALPHA, xR.ONE_MINUS_SRC_ALPHA);
         yh.enableBlend();
         yh.disableCull();
         if (nC.isAmbientOcclusionEnabled()) {
            yh.shadeModel(7425);
         } else {
            yh.shadeModel(7424);
         }

         bufferbuilder.begin(7, zK.BLOCK);
         bufferbuilder.setTranslation(x - (double)blockpos.getX() + (double)te.getOffsetX(partialTicks), y - (double)blockpos.getY() + (double)te.getOffsetY(partialTicks), z - (double)blockpos.getZ() + (double)te.getOffsetZ(partialTicks));
         bij world = this.getWorld();
         if (block == Nk.PISTON_HEAD && te.getProgress(partialTicks) <= 0.25F) {
            iblockstate = iblockstate.withProperty(fi.SHORT, true);
            this.renderStateModel(blockpos, iblockstate, bufferbuilder, world, true);
         } else if (te.shouldPistonHeadBeRendered() && !te.isExtending()) {
            fh blockpistonextension$enumpistontype = block == Nk.STICKY_PISTON ? fh.STICKY : fh.DEFAULT;
            in iblockstate1 = Nk.PISTON_HEAD.getDefaultState().withProperty(fi.TYPE, blockpistonextension$enumpistontype).withProperty(fi.FACING, iblockstate.getValue(ff.FACING));
            iblockstate1 = iblockstate1.withProperty(fi.SHORT, te.getProgress(partialTicks) >= 0.5F);
            this.renderStateModel(blockpos, iblockstate1, bufferbuilder, world, true);
            bufferbuilder.setTranslation(x - (double)blockpos.getX(), y - (double)blockpos.getY(), z - (double)blockpos.getZ());
            iblockstate = iblockstate.withProperty(ff.EXTENDED, true);
            this.renderStateModel(blockpos, iblockstate, bufferbuilder, world, true);
         } else {
            this.renderStateModel(blockpos, iblockstate, bufferbuilder, world, false);
         }

         bufferbuilder.setTranslation(0.0, 0.0, 0.0);
         tessellator.draw();
         yz.enableStandardItemLighting();
      }

   }

   private boolean renderStateModel(BlockPos pos, in state, tN buffer, bij p_188186_4_, boolean checkSides) {
      return this.blockRenderer.getBlockModelRenderer().renderModel(p_188186_4_, this.blockRenderer.getModelForState(state), state, pos, buffer, checkSides);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void render(Yg var1, double var2, double var4, double var6, float var8, int var9, float var10) {
      this.render((YK)var1, var2, var4, var6, var8, var9, var10);
   }
}
