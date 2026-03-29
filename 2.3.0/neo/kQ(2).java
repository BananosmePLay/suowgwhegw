package neo;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.opengl.Display;

public class kQ extends jI {
   private final nC mc;
   private final jH fontRenderer;
   private String debugOF = null;
   private List<String> debugInfoLeft = null;
   private List<String> debugInfoRight = null;
   private long updateInfoLeftTimeMs = 0L;
   private long updateInfoRightTimeMs = 0L;

   public kQ(nC mc) {
      this.mc = mc;
      this.fontRenderer = mc.fontRenderer;
   }

   public void renderDebugInfo(mC scaledResolutionIn) {
      this.mc.profiler.startSection("debug");
      yh.pushMatrix();
      this.renderDebugInfoLeft();
      this.renderDebugInfoRight(scaledResolutionIn);
      yh.popMatrix();
      nC var10000 = this.mc;
      if (nC.gameSettings.showLagometer) {
         this.renderLagometer();
      }

      this.mc.profiler.endSection();
   }

   protected void renderDebugInfoLeft() {
      List<String> list = this.debugInfoLeft;
      if (list == null || System.currentTimeMillis() > this.updateInfoLeftTimeMs) {
         list = this.call();
         list.add("");
         StringBuilder var10001 = (new StringBuilder()).append("Debug: Pie [shift]: ");
         nC var10002 = this.mc;
         var10001 = var10001.append(nC.gameSettings.showDebugProfilerChart ? "visible" : "hidden").append(" FPS [alt]: ");
         var10002 = this.mc;
         list.add(var10001.append(nC.gameSettings.showLagometer ? "visible" : "hidden").toString());
         list.add("For help: press F3 + Q");
         this.debugInfoLeft = list;
         this.updateInfoLeftTimeMs = System.currentTimeMillis() + 100L;
      }

      for(int i = 0; i < list.size(); ++i) {
         String s = (String)list.get(i);
         if (!Strings.isNullOrEmpty(s)) {
            int j = this.fontRenderer.FONT_HEIGHT;
            int k = this.fontRenderer.getStringWidth(s);
            int l = true;
            int i1 = 2 + j * i;
            drawRect(1, i1 - 1, 2 + k + 1, i1 + j - 1, -1873784752);
            this.fontRenderer.drawString(s, 2, i1, 14737632);
         }
      }

   }

   protected void renderDebugInfoRight(mC scaledRes) {
      List<String> list = this.debugInfoRight;
      if (list == null || System.currentTimeMillis() > this.updateInfoRightTimeMs) {
         list = this.getDebugInfoRight();
         this.debugInfoRight = list;
         this.updateInfoRightTimeMs = System.currentTimeMillis() + 100L;
      }

      for(int i = 0; i < list.size(); ++i) {
         String s = (String)list.get(i);
         if (!Strings.isNullOrEmpty(s)) {
            int j = this.fontRenderer.FONT_HEIGHT;
            int k = this.fontRenderer.getStringWidth(s);
            int l = scaledRes.getScaledWidth() - 2 - k;
            int i1 = 2 + j * i;
            drawRect(l - 1, i1 - 1, l + k + 1, i1 + j - 1, -1873784752);
            this.fontRenderer.drawString(s, l, i1, 14737632);
         }
      }

   }

   protected List<String> call() {
      BlockPos blockpos = new BlockPos(this.mc.getRenderViewEntity().posX, this.mc.getRenderViewEntity().getEntityBoundingBox().minY, this.mc.getRenderViewEntity().posZ);
      if (this.mc.debug != this.debugOF) {
         StringBuffer stringbuffer = new StringBuffer(this.mc.debug);
         int i = XH.getFpsMin();
         int j = this.mc.debug.indexOf(" fps ");
         if (j >= 0) {
            stringbuffer.insert(j, "/" + i);
         }

         if (XH.isSmoothFps()) {
            stringbuffer.append(" sf");
         }

         if (XH.isFastRender()) {
            stringbuffer.append(" fr");
         }

         if (XH.isAnisotropicFiltering()) {
            stringbuffer.append(" af");
         }

         if (XH.isAntialiasing()) {
            stringbuffer.append(" aa");
         }

         if (XH.isRenderRegions()) {
            stringbuffer.append(" reg");
         }

         if (XH.isShaders()) {
            stringbuffer.append(" sh");
         }

         this.mc.debug = stringbuffer.toString();
         this.debugOF = this.mc.debug;
      }

      StringBuilder stringbuilder = new StringBuilder();
      zj texturemap = XH.getTextureMap();
      stringbuilder.append(", A: ");
      if (bpW.isActive()) {
         stringbuilder.append(texturemap.getCountAnimationsActive() + bqf.getCountAnimationsActive());
         stringbuilder.append("/");
      }

      stringbuilder.append(texturemap.getCountAnimations() + bqf.getCountAnimations());
      String s1 = stringbuilder.toString();
      Ig entity = this.mc.getRenderViewEntity();
      EnumFacing enumfacing = entity.getHorizontalFacing();
      String s = "Invalid";
      switch (enumfacing) {
         case NORTH:
            s = "Towards negative Z";
            break;
         case SOUTH:
            s = "Towards positive Z";
            break;
         case WEST:
            s = "Towards negative X";
            break;
         case EAST:
            s = "Towards positive X";
      }

      List<String> list = Lists.newArrayList(new String[]{"Minecraft 1.12.2 (" + this.mc.getVersion() + "/" + je.getClientModName() + ("release".equalsIgnoreCase(this.mc.getVersionType()) ? "" : "/" + this.mc.getVersionType()) + ")", this.mc.debug, this.mc.renderGlobal.getDebugInfoRenders(), this.mc.renderGlobal.getDebugInfoEntities(), "P: " + this.mc.effectRenderer.getStatistics() + ". T: " + this.mc.world.getDebugLoadedEntities() + s1, this.mc.world.getProviderName(), "", String.format("XYZ: %.3f / %.5f / %.3f", this.mc.getRenderViewEntity().posX, this.mc.getRenderViewEntity().getEntityBoundingBox().minY, this.mc.getRenderViewEntity().posZ), String.format("Block: %d %d %d", blockpos.getX(), blockpos.getY(), blockpos.getZ()), String.format("Chunk: %d %d %d in %d %d %d", blockpos.getX() & 15, blockpos.getY() & 15, blockpos.getZ() & 15, blockpos.getX() >> 4, blockpos.getY() >> 4, blockpos.getZ() >> 4), String.format("Facing: %s (%s) (%.1f / %.1f)", enumfacing, s, MathHelper.wrapDegrees(entity.rotationYaw), MathHelper.wrapDegrees(entity.rotationPitch))});
      if (this.mc.world != null) {
         bam chunk = this.mc.world.getChunk(blockpos);
         if (this.mc.world.isBlockLoaded(blockpos) && blockpos.getY() >= 0 && blockpos.getY() < 256) {
            if (!chunk.isEmpty()) {
               list.add("Biome: " + chunk.getBiome(blockpos, this.mc.world.getBiomeProvider()).getBiomeName());
               list.add("Light: " + chunk.getLightSubtracted(blockpos, 0) + " (" + chunk.getLightFor(baW.SKY, blockpos) + " sky, " + chunk.getLightFor(baW.BLOCK, blockpos) + " block)");
               baL difficultyinstance = this.mc.world.getDifficultyForLocation(blockpos);
               if (this.mc.isIntegratedServerRunning() && this.mc.getIntegratedServer() != null) {
                  WY var10000 = this.mc.getIntegratedServer().getPlayerList();
                  nC var10001 = this.mc;
                  MG entityplayermp = var10000.getPlayerByUUID(nC.player.getUniqueID());
                  if (entityplayermp != null) {
                     baL difficultyinstance1 = this.mc.getIntegratedServer().getDifficultyAsync(entityplayermp.world, new BlockPos(entityplayermp));
                     if (difficultyinstance1 != null) {
                        difficultyinstance = difficultyinstance1;
                     }
                  }
               }

               list.add(String.format("Local Difficulty: %.2f // %.2f (Day %d)", difficultyinstance.getAdditionalDifficulty(), difficultyinstance.getClampedAdditionalDifficulty(), this.mc.world.getWorldTime() / 24000L));
            } else {
               list.add("Waiting for chunk...");
            }
         } else {
            list.add("Outside of world...");
         }
      }

      if (this.mc.entityRenderer != null && this.mc.entityRenderer.isShaderActive()) {
         list.add("Shader: " + this.mc.entityRenderer.getShaderGroup().getShaderGroupName());
      }

      if (this.mc.objectMouseOver != null && this.mc.objectMouseOver.typeOfHit == RayTraceResult.Type.BLOCK && this.mc.objectMouseOver.getBlockPos() != null) {
         BlockPos blockpos1 = this.mc.objectMouseOver.getBlockPos();
         list.add(String.format("Looking at: %d %d %d", blockpos1.getX(), blockpos1.getY(), blockpos1.getZ()));
      }

      return list;
   }

   protected <T extends Comparable<T>> List<String> getDebugInfoRight() {
      long i = Runtime.getRuntime().maxMemory();
      long j = Runtime.getRuntime().totalMemory();
      long k = Runtime.getRuntime().freeMemory();
      long l = j - k;
      List<String> list = Lists.newArrayList(new String[]{String.format("Java: %s %dbit", System.getProperty("java.version"), this.mc.isJava64bit() ? 64 : 32), String.format("Mem: % 2d%% %03d/%03dMB", l * 100L / i, bytesToMb(l), bytesToMb(i)), String.format("Allocated: % 2d%% %03dMB", j * 100L / i, bytesToMb(j)), "", String.format("CPU: %s", ys.getCpu()), "", String.format("Display: %dx%d (%s)", Display.getWidth(), Display.getHeight(), yh.glGetString(7936)), yh.glGetString(7937), yh.glGetString(7938)});
      long i1 = bqJ.getBufferAllocated();
      long j1 = bqJ.getBufferMaximum();
      String s = "Native: " + bytesToMb(i1) + "/" + bytesToMb(j1) + "MB";
      list.add(4, s);
      list.set(5, "GC: " + bqH.getAllocationRateMb() + "MB/s");
      if (bnK.FMLCommonHandler_getBrandings.exists()) {
         Object object = bnK.call(bnK.FMLCommonHandler_instance);
         list.add("");
         list.addAll((Collection)bnK.call(object, bnK.FMLCommonHandler_getBrandings, false));
      }

      if (this.mc.isReducedDebug()) {
         return list;
      } else {
         if (this.mc.objectMouseOver != null && this.mc.objectMouseOver.typeOfHit == RayTraceResult.Type.BLOCK && this.mc.objectMouseOver.getBlockPos() != null) {
            BlockPos blockpos = this.mc.objectMouseOver.getBlockPos();
            in iblockstate = this.mc.world.getBlockState(blockpos);
            if (this.mc.world.getWorldType() != bix.DEBUG_ALL_BLOCK_STATES) {
               iblockstate = iblockstate.getActualState(this.mc.world, blockpos);
            }

            list.add("");
            list.add(String.valueOf(co.REGISTRY.getNameForObject(iblockstate.getBlock())));

            hT iproperty;
            String s1;
            for(UnmodifiableIterator unmodifiableiterator = iblockstate.getProperties().entrySet().iterator(); unmodifiableiterator.hasNext(); list.add(iproperty.getName() + ": " + s1)) {
               Map.Entry<hT<?>, Comparable<?>> entry = (Map.Entry)unmodifiableiterator.next();
               iproperty = (hT)entry.getKey();
               T t = (Comparable)entry.getValue();
               s1 = iproperty.getName(t);
               if (Boolean.TRUE.equals(t)) {
                  s1 = TextFormatting.GREEN + s1;
               } else if (Boolean.FALSE.equals(t)) {
                  s1 = TextFormatting.RED + s1;
               }
            }
         }

         return list;
      }
   }

   private void renderLagometer() {
   }

   private int getFrameColor(int p_181552_1_, int p_181552_2_, int p_181552_3_, int p_181552_4_) {
      return p_181552_1_ < p_181552_3_ ? this.blendColors(-16711936, -256, (float)p_181552_1_ / (float)p_181552_3_) : this.blendColors(-256, -65536, (float)(p_181552_1_ - p_181552_3_) / (float)(p_181552_4_ - p_181552_3_));
   }

   private int blendColors(int p_181553_1_, int p_181553_2_, float p_181553_3_) {
      int i = p_181553_1_ >> 24 & 255;
      int j = p_181553_1_ >> 16 & 255;
      int k = p_181553_1_ >> 8 & 255;
      int l = p_181553_1_ & 255;
      int i1 = p_181553_2_ >> 24 & 255;
      int j1 = p_181553_2_ >> 16 & 255;
      int k1 = p_181553_2_ >> 8 & 255;
      int l1 = p_181553_2_ & 255;
      int i2 = MathHelper.clamp((int)((float)i + (float)(i1 - i) * p_181553_3_), 0, 255);
      int j2 = MathHelper.clamp((int)((float)j + (float)(j1 - j) * p_181553_3_), 0, 255);
      int k2 = MathHelper.clamp((int)((float)k + (float)(k1 - k) * p_181553_3_), 0, 255);
      int l2 = MathHelper.clamp((int)((float)l + (float)(l1 - l) * p_181553_3_), 0, 255);
      return i2 << 24 | j2 << 16 | k2 << 8 | l2;
   }

   private static long bytesToMb(long bytes) {
      return bytes / 1024L / 1024L;
   }
}
