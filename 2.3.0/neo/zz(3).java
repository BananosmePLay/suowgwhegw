package neo;

import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.util.ReportedException;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;

public class zz {
   public final Map<Class, zF> renderers = Maps.newHashMap();
   public static zz instance = new zz();
   public jH fontRenderer;
   public static double staticPlayerX;
   public static double staticPlayerY;
   public static double staticPlayerZ;
   public zf renderEngine;
   public bij world;
   public Ig entity;
   public float entityYaw;
   public float entityPitch;
   public RayTraceResult cameraHitResult;
   public double entityX;
   public double entityY;
   public double entityZ;
   public Yg tileEntityRendered;
   private yN batchBuffer = new yN(2097152);
   private boolean drawingBatch = false;

   private zz() {
      this.renderers.put(YQ.class, new zC());
      this.renderers.put(YG.class, new zx());
      this.renderers.put(YK.class, new zy());
      this.renderers.put(Yn.class, new zr());
      this.renderers.put(Yw.class, new zt());
      this.renderers.put(Yv.class, new zs());
      this.renderers.put(Yy.class, new zv());
      this.renderers.put(Yx.class, new zu());
      this.renderers.put(Yj.class, new zp());
      this.renderers.put(YR.class, new zE());
      this.renderers.put(Yh.class, new zo());
      this.renderers.put(YV.class, new zH());
      this.renderers.put(YN.class, new zB(new oA()));
      this.renderers.put(Yk.class, new zq());
      Iterator var1 = this.renderers.values().iterator();

      while(var1.hasNext()) {
         zF<?> tileentityspecialrenderer = (zF)var1.next();
         tileentityspecialrenderer.setRendererDispatcher(this);
      }

   }

   public <T extends Yg> zF<T> getRenderer(Class<? extends Yg> teClass) {
      zF<T> tileentityspecialrenderer = (zF)this.renderers.get(teClass);
      if (tileentityspecialrenderer == null && teClass != Yg.class) {
         tileentityspecialrenderer = this.getRenderer(teClass.getSuperclass());
         this.renderers.put(teClass, tileentityspecialrenderer);
      }

      return tileentityspecialrenderer;
   }

   @Nullable
   public <T extends Yg> zF<T> getRenderer(@Nullable Yg tileEntityIn) {
      return tileEntityIn != null && !tileEntityIn.isInvalid() ? this.getRenderer(tileEntityIn.getClass()) : null;
   }

   public void prepare(bij worldIn, zf renderEngineIn, jH fontRendererIn, Ig entityIn, RayTraceResult cameraHitResultIn, float p_190056_6_) {
      if (this.world != worldIn) {
         this.setWorld(worldIn);
      }

      this.renderEngine = renderEngineIn;
      this.entity = entityIn;
      this.fontRenderer = fontRendererIn;
      this.cameraHitResult = cameraHitResultIn;
      this.entityYaw = entityIn.prevRotationYaw + (entityIn.rotationYaw - entityIn.prevRotationYaw) * p_190056_6_;
      this.entityPitch = entityIn.prevRotationPitch + (entityIn.rotationPitch - entityIn.prevRotationPitch) * p_190056_6_;
      this.entityX = entityIn.lastTickPosX + (entityIn.posX - entityIn.lastTickPosX) * (double)p_190056_6_;
      this.entityY = entityIn.lastTickPosY + (entityIn.posY - entityIn.lastTickPosY) * (double)p_190056_6_;
      this.entityZ = entityIn.lastTickPosZ + (entityIn.posZ - entityIn.lastTickPosZ) * (double)p_190056_6_;
   }

   public void render(Yg tileentityIn, float partialTicks, int destroyStage) {
      if (tileentityIn.getDistanceSq(this.entityX, this.entityY, this.entityZ) < tileentityIn.getMaxRenderDistanceSquared()) {
         boolean flag = true;
         if (bnK.ForgeTileEntity_hasFastRenderer.exists()) {
            flag = !this.drawingBatch || !bnK.callBoolean(tileentityIn, bnK.ForgeTileEntity_hasFastRenderer);
         }

         if (flag) {
            yz.enableStandardItemLighting();
            int i = this.world.getCombinedLight(tileentityIn.getPos(), 0);
            int j = i % 65536;
            int k = i / 65536;
            ys.setLightmapTextureCoords(ys.lightmapTexUnit, (float)j, (float)k);
            yh.color(1.0F, 1.0F, 1.0F, 1.0F);
         }

         BlockPos blockpos = tileentityIn.getPos();
         if (!this.world.isBlockLoaded(blockpos, false)) {
            return;
         }

         if (bjR.isActive()) {
            bjR.beginRender();
         }

         this.render(tileentityIn, (double)blockpos.getX() - staticPlayerX, (double)blockpos.getY() - staticPlayerY, (double)blockpos.getZ() - staticPlayerZ, partialTicks, destroyStage, 1.0F);
         if (bjR.isActive()) {
            if (bjR.hasEmissive()) {
               bjR.beginRenderEmissive();
               this.render(tileentityIn, (double)blockpos.getX() - staticPlayerX, (double)blockpos.getY() - staticPlayerY, (double)blockpos.getZ() - staticPlayerZ, partialTicks, destroyStage, 1.0F);
               bjR.endRenderEmissive();
            }

            bjR.endRender();
         }
      }

   }

   public void render(Yg tileEntityIn, double x, double y, double z, float partialTicks) {
      this.render(tileEntityIn, x, y, z, partialTicks, 1.0F);
   }

   public void render(Yg p_192855_1_, double p_192855_2_, double p_192855_4_, double p_192855_6_, float p_192855_8_, float p_192855_9_) {
      this.render(p_192855_1_, p_192855_2_, p_192855_4_, p_192855_6_, p_192855_8_, -1, p_192855_9_);
   }

   public void render(Yg tileEntityIn, double x, double y, double z, float partialTicks, int destroyStage, float p_192854_10_) {
      zF<Yg> tileentityspecialrenderer = this.getRenderer(tileEntityIn);
      if (tileentityspecialrenderer != null) {
         try {
            this.tileEntityRendered = tileEntityIn;
            if (this.drawingBatch && bnK.callBoolean(tileEntityIn, bnK.ForgeTileEntity_hasFastRenderer)) {
               tileentityspecialrenderer.renderTileEntityFast(tileEntityIn, x, y, z, partialTicks, destroyStage, p_192854_10_, this.batchBuffer.getBuffer());
            } else {
               tileentityspecialrenderer.render(tileEntityIn, x, y, z, partialTicks, destroyStage, p_192854_10_);
            }

            this.tileEntityRendered = null;
         } catch (Throwable var15) {
            Throwable throwable = var15;
            Er crashreport = Er.makeCrashReport(throwable, "Rendering Block Entity");
            Ey crashreportcategory = crashreport.makeCategory("Block Entity Details");
            tileEntityIn.addInfoToCrashReport(crashreportcategory);
            throw new ReportedException(crashreport);
         }
      }

   }

   public void setWorld(@Nullable bij worldIn) {
      this.world = worldIn;
      if (worldIn == null) {
         this.entity = null;
      }

   }

   public jH getFontRenderer() {
      return this.fontRenderer;
   }

   public void preDrawBatch() {
      this.batchBuffer.getBuffer().begin(7, zK.BLOCK);
      this.drawingBatch = true;
   }

   public void drawBatch(int p_drawBatch_1_) {
      this.renderEngine.bindTexture(zj.LOCATION_BLOCKS_TEXTURE);
      yz.disableStandardItemLighting();
      yh.blendFunc(770, 771);
      yh.enableBlend();
      yh.disableCull();
      if (nC.isAmbientOcclusionEnabled()) {
         yh.shadeModel(7425);
      } else {
         yh.shadeModel(7424);
      }

      if (p_drawBatch_1_ > 0) {
         Vec3d vec3d = (Vec3d)bnK.call(bnK.ActiveRenderInfo_getCameraPosition);
         if (vec3d != null) {
            this.batchBuffer.getBuffer().sortVertexData((float)vec3d.x, (float)vec3d.y, (float)vec3d.z);
         } else {
            this.batchBuffer.getBuffer().sortVertexData(0.0F, 0.0F, 0.0F);
         }
      }

      this.batchBuffer.draw();
      yz.enableStandardItemLighting();
      this.drawingBatch = false;
   }
}
