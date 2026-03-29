package neo;

import net.minecraft.util.EnumFacing;

public class zB extends zF<YN> {
   private final oA model;

   public zB(oA modelIn) {
      this.model = modelIn;
   }

   public void render(YN te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
      EnumFacing enumfacing = EnumFacing.UP;
      if (te.hasWorld()) {
         in iblockstate = this.getWorld().getBlockState(te.getPos());
         if (iblockstate.getBlock() instanceof gr) {
            enumfacing = (EnumFacing)iblockstate.getValue(gr.FACING);
         }
      }

      yh.enableDepth();
      yh.depthFunc(515);
      yh.depthMask(true);
      yh.disableCull();
      if (destroyStage >= 0) {
         this.bindTexture(DESTROY_STAGES[destroyStage]);
         yh.matrixMode(5890);
         yh.pushMatrix();
         yh.scale(4.0F, 4.0F, 1.0F);
         yh.translate(0.0625F, 0.0625F, 0.0625F);
         yh.matrixMode(5888);
      } else {
         this.bindTexture(wT.SHULKER_ENDERGOLEM_TEXTURE[te.getColor().getMetadata()]);
      }

      yh.pushMatrix();
      yh.enableRescaleNormal();
      if (destroyStage < 0) {
         yh.color(1.0F, 1.0F, 1.0F, alpha);
      }

      yh.translate((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
      yh.scale(1.0F, -1.0F, -1.0F);
      yh.translate(0.0F, 1.0F, 0.0F);
      float f = 0.9995F;
      yh.scale(0.9995F, 0.9995F, 0.9995F);
      yh.translate(0.0F, -1.0F, 0.0F);
      switch (enumfacing) {
         case DOWN:
            yh.translate(0.0F, 2.0F, 0.0F);
            yh.rotate(180.0F, 1.0F, 0.0F, 0.0F);
         case UP:
         default:
            break;
         case NORTH:
            yh.translate(0.0F, 1.0F, 1.0F);
            yh.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            yh.rotate(180.0F, 0.0F, 0.0F, 1.0F);
            break;
         case SOUTH:
            yh.translate(0.0F, 1.0F, -1.0F);
            yh.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            break;
         case WEST:
            yh.translate(-1.0F, 1.0F, 0.0F);
            yh.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            yh.rotate(-90.0F, 0.0F, 0.0F, 1.0F);
            break;
         case EAST:
            yh.translate(1.0F, 1.0F, 0.0F);
            yh.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            yh.rotate(90.0F, 0.0F, 0.0F, 1.0F);
      }

      this.model.base.render(0.0625F);
      yh.translate(0.0F, -te.getProgress(partialTicks) * 0.5F, 0.0F);
      yh.rotate(270.0F * te.getProgress(partialTicks), 0.0F, 1.0F, 0.0F);
      this.model.lid.render(0.0625F);
      yh.enableCull();
      yh.disableRescaleNormal();
      yh.popMatrix();
      yh.color(1.0F, 1.0F, 1.0F, 1.0F);
      if (destroyStage >= 0) {
         yh.matrixMode(5890);
         yh.popMatrix();
         yh.matrixMode(5888);
      }

   }

   // $FF: synthetic method
   // $FF: bridge method
   public void render(Yg var1, double var2, double var4, double var6, float var8, int var9, float var10) {
      this.render((YN)var1, var2, var4, var6, var8, var9, var10);
   }
}
