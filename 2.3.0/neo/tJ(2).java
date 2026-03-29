package neo;

import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.ReportedException;
import net.minecraft.util.math.BlockPos;

public class tJ implements AB {
   private final tH blockModelShapes;
   private final ty blockModelRenderer;
   private final tO chestRenderer = new tO();
   private final ts fluidRenderer;

   public tJ(tH p_i46577_1_, uy p_i46577_2_) {
      this.blockModelShapes = p_i46577_1_;
      this.blockModelRenderer = new ty(p_i46577_2_);
      this.fluidRenderer = new ts(p_i46577_2_);
   }

   public tH getBlockModelShapes() {
      return this.blockModelShapes;
   }

   public void renderBlockDamage(in state, BlockPos pos, zd texture, bfZ blockAccess) {
      if (state.getRenderType() == EnumBlockRenderType.MODEL) {
         state = state.getActualState(blockAccess, pos);
         sc ibakedmodel = this.blockModelShapes.getModelForState(state);
         sc ibakedmodel1 = (new tb(state, ibakedmodel, texture, pos)).makeBakedModel();
         this.blockModelRenderer.renderModel(blockAccess, ibakedmodel1, state, pos, yN.getInstance().getBuffer(), true);
      }

   }

   public boolean renderBlock(in state, BlockPos pos, bfZ blockAccess, tN bufferBuilderIn) {
      0bz.method_Qm().method_Qn().post(new 0dp(state, pos, blockAccess, bufferBuilderIn));

      try {
         EnumBlockRenderType enumblockrendertype = state.getRenderType();
         if (enumblockrendertype == EnumBlockRenderType.INVISIBLE) {
            return false;
         } else {
            if (blockAccess.getWorldType() != bix.DEBUG_ALL_BLOCK_STATES) {
               try {
                  state = state.getActualState(blockAccess, pos);
               } catch (Exception var8) {
               }
            }

            switch (enumblockrendertype) {
               case MODEL:
                  return this.blockModelRenderer.renderModel(blockAccess, this.getModelForState(state), state, pos, bufferBuilderIn, true);
               case ENTITYBLOCK_ANIMATED:
                  return false;
               case LIQUID:
                  return this.fluidRenderer.renderFluid(blockAccess, state, pos, bufferBuilderIn);
               default:
                  return false;
            }
         }
      } catch (Throwable var9) {
         Throwable throwable = var9;
         Er crashreport = Er.makeCrashReport(throwable, "Tesselating block in world");
         Ey crashreportcategory = crashreport.makeCategory("Block being tesselated");
         Ey.addBlockInfo(crashreportcategory, pos, state.getBlock(), state.getBlock().getMetaFromState(state));
         throw new ReportedException(crashreport);
      }
   }

   public ty getBlockModelRenderer() {
      return this.blockModelRenderer;
   }

   public sc getModelForState(in state) {
      return this.blockModelShapes.getModelForState(state);
   }

   public void renderBlockBrightness(in state, float brightness) {
      EnumBlockRenderType enumblockrendertype = state.getRenderType();
      if (enumblockrendertype != EnumBlockRenderType.INVISIBLE) {
         switch (enumblockrendertype) {
            case MODEL:
               sc ibakedmodel = this.getModelForState(state);
               this.blockModelRenderer.renderModelBrightness(ibakedmodel, state, brightness, true);
               break;
            case ENTITYBLOCK_ANIMATED:
               this.chestRenderer.renderChestBrightness(state.getBlock(), brightness);
            case LIQUID:
         }
      }

   }

   public void onResourceManagerReload(AA resourceManager) {
      this.fluidRenderer.initAtlasSprites();
   }
}
