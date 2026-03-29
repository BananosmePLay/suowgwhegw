package neo;

import com.google.common.collect.Maps;
import java.util.Collections;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ReportedException;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class wC {
   private final Map<Class, vI> entityRenderMap = Maps.newHashMap();
   private final Map<String, wM> skinMap = Maps.newHashMap();
   private final wM playerRenderer;
   private jH textRenderer;
   public static double renderPosX;
   public static double renderPosY;
   public static double renderPosZ;
   public zf renderEngine;
   public bij world;
   public Ig renderViewEntity;
   public Ig pointedEntity;
   public float playerViewY;
   public float playerViewX;
   public Bj options;
   public double viewerPosX;
   public double viewerPosY;
   public double viewerPosZ;
   private boolean renderOutlines;
   private boolean renderShadow = true;
   private boolean debugBoundingBox;
   public vI renderRender = null;

   public wC(zf renderEngineIn, yK itemRendererIn) {
      this.renderEngine = renderEngineIn;
      this.entityRenderMap.put(JA.class, new vS(this));
      this.entityRenderMap.put(KW.class, new xc(this));
      this.entityRenderMap.put(LQ.class, new wJ(this));
      this.entityRenderMap.put(Mb.class, new wQ(this));
      this.entityRenderMap.put(LB.class, new vU(this));
      this.entityRenderMap.put(LL.class, new wF(this));
      this.entityRenderMap.put(Mu.class, new xq(this));
      this.entityRenderMap.put(LA.class, new vT(this));
      this.entityRenderMap.put(LN.class, new wG(this));
      this.entityRenderMap.put(LY.class, new wP(this));
      this.entityRenderMap.put(LP.class, new wI(this));
      this.entityRenderMap.put(KG.class, new wV(this));
      this.entityRenderMap.put(JK.class, new wb(this));
      this.entityRenderMap.put(JB.class, new vV(this));
      this.entityRenderMap.put(JJ.class, new wa(this));
      this.entityRenderMap.put(KO.class, new xa(this));
      this.entityRenderMap.put(KH.class, new wX(this));
      this.entityRenderMap.put(Lh.class, new xo(this));
      this.entityRenderMap.put(KX.class, new xe(this));
      this.entityRenderMap.put(Lg.class, new xm(this));
      this.entityRenderMap.put(Jz.class, new vQ(this));
      this.entityRenderMap.put(Ko.class, new wL(this));
      this.entityRenderMap.put(Lk.class, new xt(this));
      this.entityRenderMap.put(Ll.class, new xu(this));
      this.entityRenderMap.put(Kd.class, new wp(this));
      this.entityRenderMap.put(KN.class, new wY(this));
      this.entityRenderMap.put(Kk.class, new wB(this));
      this.entityRenderMap.put(JX.class, new wm(this, 6.0F));
      this.entityRenderMap.put(JW.class, new wk(this));
      this.entityRenderMap.put(Mf.class, new xd(this));
      this.entityRenderMap.put(Mq.class, new xj(this));
      this.entityRenderMap.put(Kj.class, new ws(this));
      this.entityRenderMap.put(Lz.class, new vO(this));
      this.entityRenderMap.put(Kc.class, new wn(this));
      this.entityRenderMap.put(JD.class, new vY(this));
      this.entityRenderMap.put(KD.class, new wT(this));
      this.entityRenderMap.put(Kv.class, new wN(this));
      this.entityRenderMap.put(JR.class, new wf(this));
      this.entityRenderMap.put(Lf.class, new xl(this));
      this.entityRenderMap.put(Lc.class, new xi(this));
      this.entityRenderMap.put(Kh.class, new wr(this));
      this.entityRenderMap.put(HS.class, new vW(this));
      this.entityRenderMap.put(IS.class, new vZ(this));
      this.entityRenderMap.put(HV.class, new xn(this));
      this.entityRenderMap.put(Ig.class, new wc(this));
      this.entityRenderMap.put(Jq.class, new wH(this));
      this.entityRenderMap.put(IZ.class, new wt(this, itemRendererIn));
      this.entityRenderMap.put(Ip.class, new wu(this));
      this.entityRenderMap.put(Ne.class, new xf(this));
      this.entityRenderMap.put(Nc.class, new xb(this));
      this.entityRenderMap.put(Nb.class, new wZ(this, NK.SNOWBALL, itemRendererIn));
      this.entityRenderMap.put(IU.class, new wZ(this, NK.ENDER_PEARL, itemRendererIn));
      this.entityRenderMap.put(IT.class, new wZ(this, NK.ENDER_EYE, itemRendererIn));
      this.entityRenderMap.put(MQ.class, new wZ(this, NK.EGG, itemRendererIn));
      this.entityRenderMap.put(MY.class, new wO(this, itemRendererIn));
      this.entityRenderMap.put(IV.class, new wZ(this, NK.EXPERIENCE_BOTTLE, itemRendererIn));
      this.entityRenderMap.put(IX.class, new wZ(this, NK.FIREWORKS, itemRendererIn));
      this.entityRenderMap.put(MV.class, new wi(this, 2.0F));
      this.entityRenderMap.put(Na.class, new wi(this, 0.5F));
      this.entityRenderMap.put(MP.class, new vX(this));
      this.entityRenderMap.put(Nf.class, new xp(this));
      this.entityRenderMap.put(MZ.class, new wU(this));
      this.entityRenderMap.put(IY.class, new wd(this, itemRendererIn));
      this.entityRenderMap.put(Js.class, new xr(this));
      this.entityRenderMap.put(Jr.class, new xh(this));
      this.entityRenderMap.put(IW.class, new wh(this));
      this.entityRenderMap.put(IN.class, new vM(this));
      this.entityRenderMap.put(MR.class, new wg(this));
      this.entityRenderMap.put(Jo.class, new xg(this));
      this.entityRenderMap.put(Jn.class, new wE(this));
      this.entityRenderMap.put(Jc.class, new wD(this));
      this.entityRenderMap.put(IR.class, new vR(this));
      this.entityRenderMap.put(MU.class, new wj(this));
      this.entityRenderMap.put(Ii.class, new vK(this));
      this.entityRenderMap.put(LF.class, new wo(this));
      this.entityRenderMap.put(Md.class, new vJ(this));
      this.entityRenderMap.put(Mv.class, new vJ(this));
      this.entityRenderMap.put(LM.class, new vJ(this, 0.92F));
      this.entityRenderMap.put(LC.class, new vJ(this, 0.87F));
      this.entityRenderMap.put(LK.class, new wz(this));
      this.entityRenderMap.put(MW.class, new wA(this));
      this.entityRenderMap.put(HX.class, new wv(this));
      this.playerRenderer = new wM(this);
      this.skinMap.put("default", this.playerRenderer);
      this.skinMap.put("slim", new wM(this, true));
      bnw.register(this.skinMap);
      if (bnK.RenderingRegistry_loadEntityRenderers.exists()) {
         bnK.call(bnK.RenderingRegistry_loadEntityRenderers, this, this.entityRenderMap);
      }

   }

   public void setRenderPosition(double renderPosXIn, double renderPosYIn, double renderPosZIn) {
      renderPosX = renderPosXIn;
      renderPosY = renderPosYIn;
      renderPosZ = renderPosZIn;
   }

   public <T extends Ig> vI<T> getEntityClassRenderObject(Class<? extends Ig> entityClass) {
      vI<T> render = (vI)this.entityRenderMap.get(entityClass);
      if (render == null && entityClass != Ig.class) {
         render = this.getEntityClassRenderObject(entityClass.getSuperclass());
         this.entityRenderMap.put(entityClass, render);
      }

      return render;
   }

   @Nullable
   public <T extends Ig> vI<T> getEntityRenderObject(Ig entityIn) {
      if (entityIn instanceof jf) {
         String s = ((jf)entityIn).getSkinType();
         wM renderplayer = (wM)this.skinMap.get(s);
         return renderplayer != null ? renderplayer : this.playerRenderer;
      } else {
         return this.getEntityClassRenderObject(entityIn.getClass());
      }
   }

   public void cacheActiveRenderInfo(bij worldIn, jH textRendererIn, Ig livingPlayerIn, Ig pointedEntityIn, Bj optionsIn, float partialTicks) {
      this.world = worldIn;
      this.options = optionsIn;
      this.renderViewEntity = livingPlayerIn;
      this.pointedEntity = pointedEntityIn;
      this.textRenderer = textRendererIn;
      if (livingPlayerIn instanceof Iw && ((Iw)livingPlayerIn).isPlayerSleeping()) {
         in iblockstate = worldIn.getBlockState(new BlockPos(livingPlayerIn));
         co block = iblockstate.getBlock();
         if (bnK.callBoolean(block, bnK.ForgeBlock_isBed, iblockstate, worldIn, new BlockPos(livingPlayerIn), (Iw)livingPlayerIn)) {
            EnumFacing enumfacing = (EnumFacing)bnK.call(block, bnK.ForgeBlock_getBedDirection, iblockstate, worldIn, new BlockPos(livingPlayerIn));
            int i = enumfacing.getHorizontalIndex();
            this.playerViewY = (float)(i * 90 + 180);
            this.playerViewX = 0.0F;
         } else if (block == Nk.BED) {
            int j = ((EnumFacing)iblockstate.getValue(cC.FACING)).getHorizontalIndex();
            this.playerViewY = (float)(j * 90 + 180);
            this.playerViewX = 0.0F;
         }
      } else {
         this.playerViewY = livingPlayerIn.prevRotationYaw + (livingPlayerIn.rotationYaw - livingPlayerIn.prevRotationYaw) * partialTicks;
         this.playerViewX = livingPlayerIn.prevRotationPitch + (livingPlayerIn.rotationPitch - livingPlayerIn.prevRotationPitch) * partialTicks;
      }

      if (optionsIn.thirdPersonView == 2) {
         this.playerViewY += 180.0F;
      }

      this.viewerPosX = livingPlayerIn.lastTickPosX + (livingPlayerIn.posX - livingPlayerIn.lastTickPosX) * (double)partialTicks;
      this.viewerPosY = livingPlayerIn.lastTickPosY + (livingPlayerIn.posY - livingPlayerIn.lastTickPosY) * (double)partialTicks;
      this.viewerPosZ = livingPlayerIn.lastTickPosZ + (livingPlayerIn.posZ - livingPlayerIn.lastTickPosZ) * (double)partialTicks;
   }

   public void setPlayerViewY(float playerViewYIn) {
      this.playerViewY = playerViewYIn;
   }

   public boolean isRenderShadow() {
      return this.renderShadow;
   }

   public void setRenderShadow(boolean renderShadowIn) {
      this.renderShadow = renderShadowIn;
   }

   public void setDebugBoundingBox(boolean debugBoundingBoxIn) {
      this.debugBoundingBox = debugBoundingBoxIn;
   }

   public boolean isDebugBoundingBox() {
      return this.debugBoundingBox;
   }

   public boolean isRenderMultipass(Ig entityIn) {
      return this.getEntityRenderObject(entityIn).isMultipass();
   }

   public boolean shouldRender(Ig entityIn, uO camera, double camX, double camY, double camZ) {
      vI<Ig> render = this.getEntityRenderObject(entityIn);
      return render != null && render.shouldRender(entityIn, camera, camX, camY, camZ);
   }

   public void renderEntityStatic(Ig entityIn, float partialTicks, boolean p_188388_3_) {
      if (entityIn.ticksExisted == 0) {
         entityIn.lastTickPosX = entityIn.posX;
         entityIn.lastTickPosY = entityIn.posY;
         entityIn.lastTickPosZ = entityIn.posZ;
      }

      double d0 = entityIn.lastTickPosX + (entityIn.posX - entityIn.lastTickPosX) * (double)partialTicks;
      double d1 = entityIn.lastTickPosY + (entityIn.posY - entityIn.lastTickPosY) * (double)partialTicks;
      double d2 = entityIn.lastTickPosZ + (entityIn.posZ - entityIn.lastTickPosZ) * (double)partialTicks;
      float f = entityIn.prevRotationYaw + (entityIn.rotationYaw - entityIn.prevRotationYaw) * partialTicks;
      int i = entityIn.getBrightnessForRender();
      if (entityIn.isBurning()) {
         i = 15728880;
      }

      int j = i % 65536;
      int k = i / 65536;
      ys.setLightmapTextureCoords(ys.lightmapTexUnit, (float)j, (float)k);
      yh.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.renderEntity(entityIn, d0 - renderPosX, d1 - renderPosY, d2 - renderPosZ, f, partialTicks, p_188388_3_);
   }

   public void renderEntity(Ig entityIn, double x, double y, double z, float yaw, float partialTicks, boolean p_188391_10_) {
      vI<Ig> render = null;

      Throwable throwable;
      try {
         render = this.getEntityRenderObject(entityIn);
         if (render != null && this.renderEngine != null) {
            try {
               render.setRenderOutlines(this.renderOutlines);
               if (bki.isActive()) {
                  this.renderRender = render;
               }

               render.doRender(entityIn, x, y, z, yaw, partialTicks);
            } catch (Throwable var18) {
               throwable = var18;
               throw new ReportedException(Er.makeCrashReport(throwable, "Rendering entity in world"));
            }

            try {
               if (!this.renderOutlines) {
                  render.doRenderShadowAndFire(entityIn, x, y, z, yaw, partialTicks);
               }
            } catch (Throwable var17) {
               throwable = var17;
               throw new ReportedException(Er.makeCrashReport(throwable, "Post-rendering entity in world"));
            }

            if (this.debugBoundingBox && !entityIn.isInvisible() && !p_188391_10_ && !nC.getMinecraft().isReducedDebug()) {
               try {
                  this.renderDebugBoundingBox(entityIn, x, y, z, yaw, partialTicks);
               } catch (Throwable var16) {
                  throwable = var16;
                  throw new ReportedException(Er.makeCrashReport(throwable, "Rendering entity hitbox in world"));
               }
            }
         }

      } catch (Throwable var19) {
         throwable = var19;
         Er crashreport = Er.makeCrashReport(throwable, "Rendering entity in world");
         Ey crashreportcategory = crashreport.makeCategory("Entity being rendered");
         entityIn.addEntityCrashInfo(crashreportcategory);
         Ey crashreportcategory1 = crashreport.makeCategory("Renderer details");
         crashreportcategory1.addCrashSection("Assigned renderer", render);
         crashreportcategory1.addCrashSection("Location", Ey.getCoordinateInfo(x, y, z));
         crashreportcategory1.addCrashSection("Rotation", yaw);
         crashreportcategory1.addCrashSection("Delta", partialTicks);
         throw new ReportedException(crashreport);
      }
   }

   public void renderMultipass(Ig entityIn, float partialTicks) {
      if (entityIn.ticksExisted == 0) {
         entityIn.lastTickPosX = entityIn.posX;
         entityIn.lastTickPosY = entityIn.posY;
         entityIn.lastTickPosZ = entityIn.posZ;
      }

      double d0 = entityIn.lastTickPosX + (entityIn.posX - entityIn.lastTickPosX) * (double)partialTicks;
      double d1 = entityIn.lastTickPosY + (entityIn.posY - entityIn.lastTickPosY) * (double)partialTicks;
      double d2 = entityIn.lastTickPosZ + (entityIn.posZ - entityIn.lastTickPosZ) * (double)partialTicks;
      float f = entityIn.prevRotationYaw + (entityIn.rotationYaw - entityIn.prevRotationYaw) * partialTicks;
      int i = entityIn.getBrightnessForRender();
      if (entityIn.isBurning()) {
         i = 15728880;
      }

      int j = i % 65536;
      int k = i / 65536;
      ys.setLightmapTextureCoords(ys.lightmapTexUnit, (float)j, (float)k);
      yh.color(1.0F, 1.0F, 1.0F, 1.0F);
      vI<Ig> render = this.getEntityRenderObject(entityIn);
      if (render != null && this.renderEngine != null) {
         render.renderMultipass(entityIn, d0 - renderPosX, d1 - renderPosY, d2 - renderPosZ, f, partialTicks);
      }

   }

   private void renderDebugBoundingBox(Ig entityIn, double x, double y, double z, float entityYaw, float partialTicks) {
      if (!bpq.isShadowPass) {
         yh.depthMask(false);
         yh.disableTexture2D();
         yh.disableLighting();
         yh.disableCull();
         yh.disableBlend();
         float f = entityIn.width / 2.0F;
         AxisAlignedBB axisalignedbb = entityIn.getEntityBoundingBox();
         yy.drawBoundingBox(axisalignedbb.minX - entityIn.posX + x, axisalignedbb.minY - entityIn.posY + y, axisalignedbb.minZ - entityIn.posZ + z, axisalignedbb.maxX - entityIn.posX + x, axisalignedbb.maxY - entityIn.posY + y, axisalignedbb.maxZ - entityIn.posZ + z, 1.0F, 1.0F, 1.0F, 1.0F);
         Ig[] aentity = entityIn.getParts();
         if (aentity != null) {
            Ig[] var13 = aentity;
            int var14 = aentity.length;

            for(int var15 = 0; var15 < var14; ++var15) {
               Ig entity = var13[var15];
               double d0 = (entity.posX - entity.prevPosX) * (double)partialTicks;
               double d1 = (entity.posY - entity.prevPosY) * (double)partialTicks;
               double d2 = (entity.posZ - entity.prevPosZ) * (double)partialTicks;
               AxisAlignedBB axisalignedbb1 = entity.getEntityBoundingBox();
               yy.drawBoundingBox(axisalignedbb1.minX - renderPosX + d0, axisalignedbb1.minY - renderPosY + d1, axisalignedbb1.minZ - renderPosZ + d2, axisalignedbb1.maxX - renderPosX + d0, axisalignedbb1.maxY - renderPosY + d1, axisalignedbb1.maxZ - renderPosZ + d2, 0.25F, 1.0F, 0.0F, 1.0F);
            }
         }

         if (entityIn instanceof Iw) {
            float f1 = 0.01F;
            yy.drawBoundingBox(x - (double)f, y + (double)entityIn.getEyeHeight() - 0.009999999776482582, z - (double)f, x + (double)f, y + (double)entityIn.getEyeHeight() + 0.009999999776482582, z + (double)f, 1.0F, 0.0F, 0.0F, 1.0F);
         }

         yN tessellator = yN.getInstance();
         tN bufferbuilder = tessellator.getBuffer();
         Vec3d vec3d = entityIn.getLook(partialTicks);
         bufferbuilder.begin(3, zK.POSITION_COLOR);
         bufferbuilder.pos(x, y + (double)entityIn.getEyeHeight(), z).color(0, 0, 255, 255).endVertex();
         bufferbuilder.pos(x + vec3d.x * 2.0, y + (double)entityIn.getEyeHeight() + vec3d.y * 2.0, z + vec3d.z * 2.0).color(0, 0, 255, 255).endVertex();
         tessellator.draw();
         yh.enableTexture2D();
         yh.enableLighting();
         yh.enableCull();
         yh.disableBlend();
         yh.depthMask(true);
      }

   }

   public void setWorld(@Nullable bij worldIn) {
      this.world = worldIn;
      if (worldIn == null) {
         this.renderViewEntity = null;
      }

   }

   public double getDistanceToCamera(double x, double y, double z) {
      double d0 = x - this.viewerPosX;
      double d1 = y - this.viewerPosY;
      double d2 = z - this.viewerPosZ;
      return d0 * d0 + d1 * d1 + d2 * d2;
   }

   public jH getFontRenderer() {
      return this.textRenderer;
   }

   public void setRenderOutlines(boolean renderOutlinesIn) {
      this.renderOutlines = renderOutlinesIn;
   }

   public Map<Class, vI> getEntityRenderMap() {
      return this.entityRenderMap;
   }

   public Map<String, wM> getSkinMap() {
      return Collections.unmodifiableMap(this.skinMap);
   }
}
