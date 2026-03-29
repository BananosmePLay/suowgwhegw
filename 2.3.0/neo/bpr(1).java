package neo;

import java.nio.IntBuffer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class bpr {
   private static final ResourceLocation END_PORTAL_TEXTURE = new ResourceLocation("textures/entity/end_portal.png");

   public bpr() {
   }

   public static void setFrustrumPosition(uO frustum, double x, double y, double z) {
      frustum.setPosition(x, y, z);
   }

   public static void setupTerrain(yy renderGlobal, Ig viewEntity, double partialTicks, uO camera, int frameCount, boolean playerSpectator) {
      renderGlobal.setupTerrain(viewEntity, partialTicks, camera, frameCount, playerSpectator);
   }

   public static void beginTerrainSolid() {
      if (bpq.isRenderingWorld) {
         bpq.fogEnabled = true;
         bpq.useProgram(bpq.ProgramTerrain);
      }

   }

   public static void beginTerrainCutoutMipped() {
      if (bpq.isRenderingWorld) {
         bpq.useProgram(bpq.ProgramTerrain);
      }

   }

   public static void beginTerrainCutout() {
      if (bpq.isRenderingWorld) {
         bpq.useProgram(bpq.ProgramTerrain);
      }

   }

   public static void endTerrain() {
      if (bpq.isRenderingWorld) {
         bpq.useProgram(bpq.ProgramTexturedLit);
      }

   }

   public static void beginTranslucent() {
      if (bpq.isRenderingWorld) {
         if (bpq.usedDepthBuffers >= 2) {
            yh.setActiveTexture(33995);
            bpq.checkGLError("pre copy depth");
            GL11.glCopyTexSubImage2D(3553, 0, 0, 0, 0, 0, bpq.renderWidth, bpq.renderHeight);
            bpq.checkGLError("copy depth");
            yh.setActiveTexture(33984);
         }

         bpq.useProgram(bpq.ProgramWater);
      }

   }

   public static void endTranslucent() {
      if (bpq.isRenderingWorld) {
         bpq.useProgram(bpq.ProgramTexturedLit);
      }

   }

   public static void renderHand0(xz er, float par1, int par2) {
      if (!bpq.isShadowPass) {
         boolean flag = bpq.isItemToRenderMainTranslucent();
         boolean flag1 = bpq.isItemToRenderOffTranslucent();
         if (!flag || !flag1) {
            bpq.readCenterDepth();
            bpq.beginHand(false);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            bpq.setSkipRenderHands(flag, flag1);
            er.renderHand(par1, par2, true, false, false);
            bpq.endHand();
            bpq.setHandsRendered(!flag, !flag1);
            bpq.setSkipRenderHands(false, false);
         }
      }

   }

   public static void renderHand1(xz er, float par1, int par2) {
      if (!bpq.isShadowPass && !bpq.isBothHandsRendered()) {
         bpq.readCenterDepth();
         yh.enableBlend();
         bpq.beginHand(true);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         bpq.setSkipRenderHands(bpq.isHandRenderedMain(), bpq.isHandRenderedOff());
         er.renderHand(par1, par2, true, false, true);
         bpq.endHand();
         bpq.setHandsRendered(true, true);
         bpq.setSkipRenderHands(false, false);
      }

   }

   public static void renderItemFP(yo itemRenderer, float par1, boolean renderTranslucent) {
      bpq.setRenderingFirstPersonHand(true);
      yh.depthMask(true);
      if (renderTranslucent) {
         yh.depthFunc(519);
         GL11.glPushMatrix();
         IntBuffer intbuffer = bpq.activeDrawBuffers;
         bpq.setDrawBuffers(bpq.drawBuffersNone);
         bpq.renderItemKeepDepthMask = true;
         itemRenderer.renderItemInFirstPerson(par1);
         bpq.renderItemKeepDepthMask = false;
         bpq.setDrawBuffers(intbuffer);
         GL11.glPopMatrix();
      }

      yh.depthFunc(515);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      itemRenderer.renderItemInFirstPerson(par1);
      bpq.setRenderingFirstPersonHand(false);
   }

   public static void renderFPOverlay(xz er, float par1, int par2) {
      if (!bpq.isShadowPass) {
         bpq.beginFPOverlay();
         er.renderHand(par1, par2, false, true, false);
         bpq.endFPOverlay();
      }

   }

   public static void beginBlockDamage() {
      if (bpq.isRenderingWorld) {
         bpq.useProgram(bpq.ProgramDamagedBlock);
         if (bpq.ProgramDamagedBlock.getId() == bpq.ProgramTerrain.getId()) {
            bpq.setDrawBuffers(bpq.drawBuffersColorAtt0);
            yh.depthMask(false);
         }
      }

   }

   public static void endBlockDamage() {
      if (bpq.isRenderingWorld) {
         yh.depthMask(true);
         bpq.useProgram(bpq.ProgramTexturedLit);
      }

   }

   public static void renderShadowMap(xz entityRenderer, int pass, float partialTicks, long finishTimeNano) {
      if (bpq.usedShadowDepthBuffers > 0 && --bpq.shadowPassCounter <= 0) {
         nC minecraft = nC.getMinecraft();
         minecraft.profiler.endStartSection("shadow pass");
         yy renderglobal = minecraft.renderGlobal;
         bpq.isShadowPass = true;
         bpq.shadowPassCounter = bpq.shadowPassInterval;
         bpq.preShadowPassThirdPersonView = nC.gameSettings.thirdPersonView;
         nC.gameSettings.thirdPersonView = 1;
         bpq.checkGLError("pre shadow");
         GL11.glMatrixMode(5889);
         GL11.glPushMatrix();
         GL11.glMatrixMode(5888);
         GL11.glPushMatrix();
         minecraft.profiler.endStartSection("shadow clear");
         EXTFramebufferObject.glBindFramebufferEXT(36160, bpq.sfb);
         bpq.checkGLError("shadow bind sfb");
         minecraft.profiler.endStartSection("shadow camera");
         entityRenderer.setupCameraTransform(partialTicks, 2);
         bpq.setCameraShadow(partialTicks);
         bpq.checkGLError("shadow camera");
         bpq.useProgram(bpq.ProgramShadow);
         GL20.glDrawBuffers(bpq.sfbDrawBuffers);
         bpq.checkGLError("shadow drawbuffers");
         GL11.glReadBuffer(0);
         bpq.checkGLError("shadow readbuffer");
         EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36096, 3553, bpq.sfbDepthTextures.get(0), 0);
         if (bpq.usedShadowColorBuffers != 0) {
            EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36064, 3553, bpq.sfbColorTextures.get(0), 0);
         }

         bpq.checkFramebufferStatus("shadow fb");
         GL11.glClearColor(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glClear(bpq.usedShadowColorBuffers != 0 ? 16640 : 256);
         bpq.checkGLError("shadow clear");
         minecraft.profiler.endStartSection("shadow frustum");
         uL clippinghelper = bof.getInstance();
         minecraft.profiler.endStartSection("shadow culling");
         uN frustum = new uN(clippinghelper);
         Ig entity = minecraft.getRenderViewEntity();
         double d0 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)partialTicks;
         double d1 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)partialTicks;
         double d2 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)partialTicks;
         frustum.setPosition(d0, d1, d2);
         yh.shadeModel(7425);
         yh.enableDepth();
         yh.depthFunc(515);
         yh.depthMask(true);
         yh.colorMask(true, true, true, true);
         yh.disableCull();
         minecraft.profiler.endStartSection("shadow prepareterrain");
         minecraft.getTextureManager().bindTexture(zj.LOCATION_BLOCKS_TEXTURE);
         minecraft.profiler.endStartSection("shadow setupterrain");
         int i = false;
         int i = entityRenderer.frameCount++;
         renderglobal.setupTerrain(entity, (double)partialTicks, frustum, i, nC.player.isSpectator());
         minecraft.profiler.endStartSection("shadow updatechunks");
         minecraft.profiler.endStartSection("shadow terrain");
         yh.matrixMode(5888);
         yh.pushMatrix();
         yh.disableAlpha();
         renderglobal.renderBlockLayer(BlockRenderLayer.SOLID, (double)partialTicks, 2, entity);
         bpq.checkGLError("shadow terrain solid");
         yh.enableAlpha();
         renderglobal.renderBlockLayer(BlockRenderLayer.CUTOUT_MIPPED, (double)partialTicks, 2, entity);
         bpq.checkGLError("shadow terrain cutoutmipped");
         minecraft.getTextureManager().getTexture(zj.LOCATION_BLOCKS_TEXTURE).setBlurMipmap(false, false);
         renderglobal.renderBlockLayer(BlockRenderLayer.CUTOUT, (double)partialTicks, 2, entity);
         bpq.checkGLError("shadow terrain cutout");
         minecraft.getTextureManager().getTexture(zj.LOCATION_BLOCKS_TEXTURE).restoreLastBlurMipmap();
         yh.shadeModel(7424);
         yh.alphaFunc(516, 0.1F);
         yh.matrixMode(5888);
         yh.popMatrix();
         yh.pushMatrix();
         minecraft.profiler.endStartSection("shadow entities");
         if (bnK.ForgeHooksClient_setRenderPass.exists()) {
            bnK.callVoid(bnK.ForgeHooksClient_setRenderPass, 0);
         }

         renderglobal.renderEntities(entity, frustum, partialTicks);
         bpq.checkGLError("shadow entities");
         yh.matrixMode(5888);
         yh.popMatrix();
         yh.depthMask(true);
         yh.disableBlend();
         yh.enableCull();
         yh.tryBlendFuncSeparate(770, 771, 1, 0);
         yh.alphaFunc(516, 0.1F);
         if (bpq.usedShadowDepthBuffers >= 2) {
            yh.setActiveTexture(33989);
            bpq.checkGLError("pre copy shadow depth");
            GL11.glCopyTexSubImage2D(3553, 0, 0, 0, 0, 0, bpq.shadowMapWidth, bpq.shadowMapHeight);
            bpq.checkGLError("copy shadow depth");
            yh.setActiveTexture(33984);
         }

         yh.disableBlend();
         yh.depthMask(true);
         minecraft.getTextureManager().bindTexture(zj.LOCATION_BLOCKS_TEXTURE);
         yh.shadeModel(7425);
         bpq.checkGLError("shadow pre-translucent");
         GL20.glDrawBuffers(bpq.sfbDrawBuffers);
         bpq.checkGLError("shadow drawbuffers pre-translucent");
         bpq.checkFramebufferStatus("shadow pre-translucent");
         if (bpq.isRenderShadowTranslucent()) {
            minecraft.profiler.endStartSection("shadow translucent");
            renderglobal.renderBlockLayer(BlockRenderLayer.TRANSLUCENT, (double)partialTicks, 2, entity);
            bpq.checkGLError("shadow translucent");
         }

         if (bnK.ForgeHooksClient_setRenderPass.exists()) {
            yz.enableStandardItemLighting();
            bnK.call(bnK.ForgeHooksClient_setRenderPass, 1);
            renderglobal.renderEntities(entity, frustum, partialTicks);
            bnK.call(bnK.ForgeHooksClient_setRenderPass, -1);
            yz.disableStandardItemLighting();
            bpq.checkGLError("shadow entities 1");
         }

         yh.shadeModel(7424);
         yh.depthMask(true);
         yh.enableCull();
         yh.disableBlend();
         GL11.glFlush();
         bpq.checkGLError("shadow flush");
         bpq.isShadowPass = false;
         nC.gameSettings.thirdPersonView = bpq.preShadowPassThirdPersonView;
         minecraft.profiler.endStartSection("shadow postprocess");
         if (bpq.hasGlGenMipmap) {
            if (bpq.usedShadowDepthBuffers >= 1) {
               if (bpq.shadowMipmapEnabled[0]) {
                  yh.setActiveTexture(33988);
                  yh.bindTexture(bpq.sfbDepthTextures.get(0));
                  GL30.glGenerateMipmap(3553);
                  GL11.glTexParameteri(3553, 10241, bpq.shadowFilterNearest[0] ? 9984 : 9987);
               }

               if (bpq.usedShadowDepthBuffers >= 2 && bpq.shadowMipmapEnabled[1]) {
                  yh.setActiveTexture(33989);
                  yh.bindTexture(bpq.sfbDepthTextures.get(1));
                  GL30.glGenerateMipmap(3553);
                  GL11.glTexParameteri(3553, 10241, bpq.shadowFilterNearest[1] ? 9984 : 9987);
               }

               yh.setActiveTexture(33984);
            }

            if (bpq.usedShadowColorBuffers >= 1) {
               if (bpq.shadowColorMipmapEnabled[0]) {
                  yh.setActiveTexture(33997);
                  yh.bindTexture(bpq.sfbColorTextures.get(0));
                  GL30.glGenerateMipmap(3553);
                  GL11.glTexParameteri(3553, 10241, bpq.shadowColorFilterNearest[0] ? 9984 : 9987);
               }

               if (bpq.usedShadowColorBuffers >= 2 && bpq.shadowColorMipmapEnabled[1]) {
                  yh.setActiveTexture(33998);
                  yh.bindTexture(bpq.sfbColorTextures.get(1));
                  GL30.glGenerateMipmap(3553);
                  GL11.glTexParameteri(3553, 10241, bpq.shadowColorFilterNearest[1] ? 9984 : 9987);
               }

               yh.setActiveTexture(33984);
            }
         }

         bpq.checkGLError("shadow postprocess");
         EXTFramebufferObject.glBindFramebufferEXT(36160, bpq.dfb);
         GL11.glViewport(0, 0, bpq.renderWidth, bpq.renderHeight);
         bpq.activeDrawBuffers = null;
         minecraft.getTextureManager().bindTexture(zj.LOCATION_BLOCKS_TEXTURE);
         bpq.useProgram(bpq.ProgramTerrain);
         GL11.glMatrixMode(5888);
         GL11.glPopMatrix();
         GL11.glMatrixMode(5889);
         GL11.glPopMatrix();
         GL11.glMatrixMode(5888);
         bpq.checkGLError("shadow end");
      }

   }

   public static void preRenderChunkLayer(BlockRenderLayer blockLayerIn) {
      if (bpq.isRenderBackFace(blockLayerIn)) {
         yh.disableCull();
      }

      if (ys.useVbo()) {
         GL11.glEnableClientState(32885);
         GL20.glEnableVertexAttribArray(bpq.midTexCoordAttrib);
         GL20.glEnableVertexAttribArray(bpq.tangentAttrib);
         GL20.glEnableVertexAttribArray(bpq.entityAttrib);
      }

   }

   public static void postRenderChunkLayer(BlockRenderLayer blockLayerIn) {
      if (ys.useVbo()) {
         GL11.glDisableClientState(32885);
         GL20.glDisableVertexAttribArray(bpq.midTexCoordAttrib);
         GL20.glDisableVertexAttribArray(bpq.tangentAttrib);
         GL20.glDisableVertexAttribArray(bpq.entityAttrib);
      }

      if (bpq.isRenderBackFace(blockLayerIn)) {
         yh.enableCull();
      }

   }

   public static void setupArrayPointersVbo() {
      int i = true;
      GL11.glVertexPointer(3, 5126, 56, 0L);
      GL11.glColorPointer(4, 5121, 56, 12L);
      GL11.glTexCoordPointer(2, 5126, 56, 16L);
      ys.setClientActiveTexture(ys.lightmapTexUnit);
      GL11.glTexCoordPointer(2, 5122, 56, 24L);
      ys.setClientActiveTexture(ys.defaultTexUnit);
      GL11.glNormalPointer(5120, 56, 28L);
      GL20.glVertexAttribPointer(bpq.midTexCoordAttrib, 2, 5126, false, 56, 32L);
      GL20.glVertexAttribPointer(bpq.tangentAttrib, 4, 5122, false, 56, 40L);
      GL20.glVertexAttribPointer(bpq.entityAttrib, 3, 5122, false, 56, 48L);
   }

   public static void beaconBeamBegin() {
      bpq.useProgram(bpq.ProgramBeaconBeam);
   }

   public static void beaconBeamStartQuad1() {
   }

   public static void beaconBeamStartQuad2() {
   }

   public static void beaconBeamDraw1() {
   }

   public static void beaconBeamDraw2() {
      yh.disableBlend();
   }

   public static void renderEnchantedGlintBegin() {
      bpq.useProgram(bpq.ProgramArmorGlint);
   }

   public static void renderEnchantedGlintEnd() {
      if (bpq.isRenderingWorld) {
         if (bpq.isRenderingFirstPersonHand() && bpq.isRenderBothHands()) {
            bpq.useProgram(bpq.ProgramHand);
         } else {
            bpq.useProgram(bpq.ProgramEntities);
         }
      } else {
         bpq.useProgram(bpq.ProgramNone);
      }

   }

   public static boolean renderEndPortal(Yy te, double x, double y, double z, float partialTicks, int destroyStage, float offset) {
      if (!bpq.isShadowPass && bpq.activeProgram.getId() == 0) {
         return false;
      } else {
         yh.disableLighting();
         XH.getTextureManager().bindTexture(END_PORTAL_TEXTURE);
         yN tessellator = yN.getInstance();
         tN bufferbuilder = tessellator.getBuffer();
         bufferbuilder.begin(7, zK.BLOCK);
         float f = 0.5F;
         float f1 = f * 0.15F;
         float f2 = f * 0.3F;
         float f3 = f * 0.4F;
         float f4 = 0.0F;
         float f5 = 0.2F;
         float f6 = (float)(System.currentTimeMillis() % 100000L) / 100000.0F;
         int i = 240;
         if (te.shouldRenderFace(EnumFacing.SOUTH)) {
            bufferbuilder.pos(x, y, z + 1.0).color(f1, f2, f3, 1.0F).tex((double)(f4 + f6), (double)(f4 + f6)).lightmap(i, i).endVertex();
            bufferbuilder.pos(x + 1.0, y, z + 1.0).color(f1, f2, f3, 1.0F).tex((double)(f4 + f6), (double)(f5 + f6)).lightmap(i, i).endVertex();
            bufferbuilder.pos(x + 1.0, y + 1.0, z + 1.0).color(f1, f2, f3, 1.0F).tex((double)(f5 + f6), (double)(f5 + f6)).lightmap(i, i).endVertex();
            bufferbuilder.pos(x, y + 1.0, z + 1.0).color(f1, f2, f3, 1.0F).tex((double)(f5 + f6), (double)(f4 + f6)).lightmap(i, i).endVertex();
         }

         if (te.shouldRenderFace(EnumFacing.NORTH)) {
            bufferbuilder.pos(x, y + 1.0, z).color(f1, f2, f3, 1.0F).tex((double)(f5 + f6), (double)(f5 + f6)).lightmap(i, i).endVertex();
            bufferbuilder.pos(x + 1.0, y + 1.0, z).color(f1, f2, f3, 1.0F).tex((double)(f5 + f6), (double)(f4 + f6)).lightmap(i, i).endVertex();
            bufferbuilder.pos(x + 1.0, y, z).color(f1, f2, f3, 1.0F).tex((double)(f4 + f6), (double)(f4 + f6)).lightmap(i, i).endVertex();
            bufferbuilder.pos(x, y, z).color(f1, f2, f3, 1.0F).tex((double)(f4 + f6), (double)(f5 + f6)).lightmap(i, i).endVertex();
         }

         if (te.shouldRenderFace(EnumFacing.EAST)) {
            bufferbuilder.pos(x + 1.0, y + 1.0, z).color(f1, f2, f3, 1.0F).tex((double)(f5 + f6), (double)(f5 + f6)).lightmap(i, i).endVertex();
            bufferbuilder.pos(x + 1.0, y + 1.0, z + 1.0).color(f1, f2, f3, 1.0F).tex((double)(f5 + f6), (double)(f4 + f6)).lightmap(i, i).endVertex();
            bufferbuilder.pos(x + 1.0, y, z + 1.0).color(f1, f2, f3, 1.0F).tex((double)(f4 + f6), (double)(f4 + f6)).lightmap(i, i).endVertex();
            bufferbuilder.pos(x + 1.0, y, z).color(f1, f2, f3, 1.0F).tex((double)(f4 + f6), (double)(f5 + f6)).lightmap(i, i).endVertex();
         }

         if (te.shouldRenderFace(EnumFacing.WEST)) {
            bufferbuilder.pos(x, y, z).color(f1, f2, f3, 1.0F).tex((double)(f4 + f6), (double)(f4 + f6)).lightmap(i, i).endVertex();
            bufferbuilder.pos(x, y, z + 1.0).color(f1, f2, f3, 1.0F).tex((double)(f4 + f6), (double)(f5 + f6)).lightmap(i, i).endVertex();
            bufferbuilder.pos(x, y + 1.0, z + 1.0).color(f1, f2, f3, 1.0F).tex((double)(f5 + f6), (double)(f5 + f6)).lightmap(i, i).endVertex();
            bufferbuilder.pos(x, y + 1.0, z).color(f1, f2, f3, 1.0F).tex((double)(f5 + f6), (double)(f4 + f6)).lightmap(i, i).endVertex();
         }

         if (te.shouldRenderFace(EnumFacing.DOWN)) {
            bufferbuilder.pos(x, y, z).color(f1, f2, f3, 1.0F).tex((double)(f4 + f6), (double)(f4 + f6)).lightmap(i, i).endVertex();
            bufferbuilder.pos(x + 1.0, y, z).color(f1, f2, f3, 1.0F).tex((double)(f4 + f6), (double)(f5 + f6)).lightmap(i, i).endVertex();
            bufferbuilder.pos(x + 1.0, y, z + 1.0).color(f1, f2, f3, 1.0F).tex((double)(f5 + f6), (double)(f5 + f6)).lightmap(i, i).endVertex();
            bufferbuilder.pos(x, y, z + 1.0).color(f1, f2, f3, 1.0F).tex((double)(f5 + f6), (double)(f4 + f6)).lightmap(i, i).endVertex();
         }

         if (te.shouldRenderFace(EnumFacing.UP)) {
            bufferbuilder.pos(x, y + (double)offset, z + 1.0).color(f1, f2, f3, 1.0F).tex((double)(f4 + f6), (double)(f4 + f6)).lightmap(i, i).endVertex();
            bufferbuilder.pos(x + 1.0, y + (double)offset, z + 1.0).color(f1, f2, f3, 1.0F).tex((double)(f4 + f6), (double)(f5 + f6)).lightmap(i, i).endVertex();
            bufferbuilder.pos(x + 1.0, y + (double)offset, z).color(f1, f2, f3, 1.0F).tex((double)(f5 + f6), (double)(f5 + f6)).lightmap(i, i).endVertex();
            bufferbuilder.pos(x, y + (double)offset, z).color(f1, f2, f3, 1.0F).tex((double)(f5 + f6), (double)(f4 + f6)).lightmap(i, i).endVertex();
         }

         tessellator.draw();
         yh.enableLighting();
         return true;
      }
   }
}
