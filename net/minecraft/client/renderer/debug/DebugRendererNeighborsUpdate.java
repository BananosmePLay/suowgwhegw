package net.minecraft.client.renderer.debug;

import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DebugRendererNeighborsUpdate implements DebugRenderer.IDebugRenderer {
   private final Minecraft minecraft;
   private final Map<Long, Map<BlockPos, Integer>> lastUpdate = Maps.newTreeMap((Comparator)Ordering.natural().reverse());

   DebugRendererNeighborsUpdate(Minecraft minecraftIn) {
      this.minecraft = minecraftIn;
   }

   public void addUpdate(long worldTime, BlockPos pos) {
      Map<BlockPos, Integer> map = (Map)this.lastUpdate.get(worldTime);
      if (map == null) {
         map = Maps.newHashMap();
         this.lastUpdate.put(worldTime, map);
      }

      Integer integer = (Integer)((Map)map).get(pos);
      if (integer == null) {
         integer = 0;
      }

      ((Map)map).put(pos, integer + 1);
   }

   public void render(float partialTicks, long finishTimeNano) {
      long i = this.minecraft.world.getTotalWorldTime();
      Minecraft var10000 = this.minecraft;
      EntityPlayer entityplayer = Minecraft.player;
      double d0 = entityplayer.lastTickPosX + (entityplayer.posX - entityplayer.lastTickPosX) * (double)partialTicks;
      double d1 = entityplayer.lastTickPosY + (entityplayer.posY - entityplayer.lastTickPosY) * (double)partialTicks;
      double d2 = entityplayer.lastTickPosZ + (entityplayer.posZ - entityplayer.lastTickPosZ) * (double)partialTicks;
      var10000 = this.minecraft;
      World world = Minecraft.player.world;
      GlStateManager.enableBlend();
      GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
      GlStateManager.glLineWidth(2.0F);
      GlStateManager.disableTexture2D();
      GlStateManager.depthMask(false);
      int j = true;
      double d3 = 0.0025;
      Set<BlockPos> set = Sets.newHashSet();
      Map<BlockPos, Integer> map = Maps.newHashMap();
      Iterator<Map.Entry<Long, Map<BlockPos, Integer>>> iterator = this.lastUpdate.entrySet().iterator();

      while(true) {
         while(iterator.hasNext()) {
            Map.Entry<Long, Map<BlockPos, Integer>> entry = (Map.Entry)iterator.next();
            Long olong = (Long)entry.getKey();
            Map<BlockPos, Integer> map1 = (Map)entry.getValue();
            long k = i - olong;
            if (k > 200L) {
               iterator.remove();
            } else {
               Iterator var25 = map1.entrySet().iterator();

               while(var25.hasNext()) {
                  Map.Entry<BlockPos, Integer> entry1 = (Map.Entry)var25.next();
                  BlockPos blockpos = (BlockPos)entry1.getKey();
                  Integer integer = (Integer)entry1.getValue();
                  if (set.add(blockpos)) {
                     RenderGlobal.drawSelectionBoundingBox((new AxisAlignedBB(BlockPos.ORIGIN)).grow(0.002).shrink(0.0025 * (double)k).offset((double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ()).offset(-d0, -d1, -d2), 1.0F, 1.0F, 1.0F, 1.0F);
                     map.put(blockpos, integer);
                  }
               }
            }
         }

         Iterator var32 = map.entrySet().iterator();

         while(var32.hasNext()) {
            Map.Entry<BlockPos, Integer> entry2 = (Map.Entry)var32.next();
            BlockPos blockpos1 = (BlockPos)entry2.getKey();
            Integer integer1 = (Integer)entry2.getValue();
            DebugRenderer.renderDebugText(String.valueOf(integer1), blockpos1.getX(), blockpos1.getY(), blockpos1.getZ(), partialTicks, -1);
         }

         GlStateManager.depthMask(true);
         GlStateManager.enableTexture2D();
         GlStateManager.disableBlend();
         return;
      }
   }
}
