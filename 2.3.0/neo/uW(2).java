package neo;

import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;

public class uW implements uQ {
   private final nC minecraft;
   private final Map<Integer, VI> pathMap = Maps.newHashMap();
   private final Map<Integer, Float> pathMaxDistance = Maps.newHashMap();
   private final Map<Integer, Long> creationMap = Maps.newHashMap();
   private ME player;
   private double xo;
   private double yo;
   private double zo;

   public uW(nC minecraftIn) {
      this.minecraft = minecraftIn;
   }

   public void addPath(int eid, VI pathIn, float distance) {
      this.pathMap.put(eid, pathIn);
      this.creationMap.put(eid, System.currentTimeMillis());
      this.pathMaxDistance.put(eid, distance);
   }

   public void render(float partialTicks, long finishTimeNano) {
      if (!this.pathMap.isEmpty()) {
         long i = System.currentTimeMillis();
         nC var10001 = this.minecraft;
         this.player = nC.player;
         this.xo = this.player.lastTickPosX + (this.player.posX - this.player.lastTickPosX) * (double)partialTicks;
         this.yo = this.player.lastTickPosY + (this.player.posY - this.player.lastTickPosY) * (double)partialTicks;
         this.zo = this.player.lastTickPosZ + (this.player.posZ - this.player.lastTickPosZ) * (double)partialTicks;
         yh.pushMatrix();
         yh.enableBlend();
         yh.tryBlendFuncSeparate(ya.SRC_ALPHA, xR.ONE_MINUS_SRC_ALPHA, ya.ONE, xR.ZERO);
         yh.color(0.0F, 1.0F, 0.0F, 0.75F);
         yh.disableTexture2D();
         yh.glLineWidth(6.0F);
         Iterator var6 = this.pathMap.keySet().iterator();

         while(true) {
            VI path1;
            float f;
            VR pathpoint2;
            int j;
            VR pathpoint4;
            do {
               Integer integer1;
               if (!var6.hasNext()) {
                  var6 = this.pathMap.keySet().iterator();

                  while(var6.hasNext()) {
                     integer1 = (Integer)var6.next();
                     path1 = (VI)this.pathMap.get(integer1);
                     VR[] var18 = path1.getClosedSet();
                     int var20 = var18.length;

                     for(j = 0; j < var20; ++j) {
                        pathpoint4 = var18[j];
                        if (this.addDistanceToPlayer(pathpoint4) <= 40.0F) {
                           uR.renderDebugText(String.format("%s", pathpoint4.nodeType), (double)pathpoint4.x + 0.5, (double)pathpoint4.y + 0.75, (double)pathpoint4.z + 0.5, partialTicks, -65536);
                           uR.renderDebugText(String.format("%.2f", pathpoint4.costMalus), (double)pathpoint4.x + 0.5, (double)pathpoint4.y + 0.25, (double)pathpoint4.z + 0.5, partialTicks, -65536);
                        }
                     }

                     var18 = path1.getOpenSet();
                     var20 = var18.length;

                     for(j = 0; j < var20; ++j) {
                        pathpoint4 = var18[j];
                        if (this.addDistanceToPlayer(pathpoint4) <= 40.0F) {
                           uR.renderDebugText(String.format("%s", pathpoint4.nodeType), (double)pathpoint4.x + 0.5, (double)pathpoint4.y + 0.75, (double)pathpoint4.z + 0.5, partialTicks, -16776961);
                           uR.renderDebugText(String.format("%.2f", pathpoint4.costMalus), (double)pathpoint4.x + 0.5, (double)pathpoint4.y + 0.25, (double)pathpoint4.z + 0.5, partialTicks, -16776961);
                        }
                     }

                     for(int k = 0; k < path1.getCurrentPathLength(); ++k) {
                        pathpoint2 = path1.getPathPointFromIndex(k);
                        if (this.addDistanceToPlayer(pathpoint2) <= 40.0F) {
                           uR.renderDebugText(String.format("%s", pathpoint2.nodeType), (double)pathpoint2.x + 0.5, (double)pathpoint2.y + 0.75, (double)pathpoint2.z + 0.5, partialTicks, -1);
                           uR.renderDebugText(String.format("%.2f", pathpoint2.costMalus), (double)pathpoint2.x + 0.5, (double)pathpoint2.y + 0.25, (double)pathpoint2.z + 0.5, partialTicks, -1);
                        }
                     }
                  }

                  Integer[] var15 = (Integer[])((Integer[])this.creationMap.keySet().toArray(new Integer[0]));
                  int var16 = var15.length;

                  for(int var17 = 0; var17 < var16; ++var17) {
                     Integer integer2 = var15[var17];
                     if (i - (Long)this.creationMap.get(integer2) > 20000L) {
                        this.pathMap.remove(integer2);
                        this.creationMap.remove(integer2);
                     }
                  }

                  yh.enableTexture2D();
                  yh.disableBlend();
                  yh.popMatrix();
                  return;
               }

               integer1 = (Integer)var6.next();
               path1 = (VI)this.pathMap.get(integer1);
               f = (Float)this.pathMaxDistance.get(integer1);
               this.renderPathLine(partialTicks, path1);
               pathpoint2 = path1.getTarget();
            } while(!(this.addDistanceToPlayer(pathpoint2) <= 40.0F));

            yy.renderFilledBox((new AxisAlignedBB((double)((float)pathpoint2.x + 0.25F), (double)((float)pathpoint2.y + 0.25F), (double)pathpoint2.z + 0.25, (double)((float)pathpoint2.x + 0.75F), (double)((float)pathpoint2.y + 0.75F), (double)((float)pathpoint2.z + 0.75F))).offset(-this.xo, -this.yo, -this.zo), 0.0F, 1.0F, 0.0F, 0.5F);

            for(j = 0; j < path1.getCurrentPathLength(); ++j) {
               pathpoint4 = path1.getPathPointFromIndex(j);
               if (this.addDistanceToPlayer(pathpoint4) <= 40.0F) {
                  float f1 = j == path1.getCurrentPathIndex() ? 1.0F : 0.0F;
                  float f2 = j == path1.getCurrentPathIndex() ? 0.0F : 1.0F;
                  yy.renderFilledBox((new AxisAlignedBB((double)((float)pathpoint4.x + 0.5F - f), (double)((float)pathpoint4.y + 0.01F * (float)j), (double)((float)pathpoint4.z + 0.5F - f), (double)((float)pathpoint4.x + 0.5F + f), (double)((float)pathpoint4.y + 0.25F + 0.01F * (float)j), (double)((float)pathpoint4.z + 0.5F + f))).offset(-this.xo, -this.yo, -this.zo), f1, 0.0F, f2, 0.5F);
               }
            }
         }
      }
   }

   public void renderPathLine(float finishTimeNano, VI pathIn) {
      yN tessellator = yN.getInstance();
      tN bufferbuilder = tessellator.getBuffer();
      bufferbuilder.begin(3, zK.POSITION_COLOR);

      for(int i = 0; i < pathIn.getCurrentPathLength(); ++i) {
         VR pathpoint = pathIn.getPathPointFromIndex(i);
         if (this.addDistanceToPlayer(pathpoint) <= 40.0F) {
            float f = (float)i / (float)pathIn.getCurrentPathLength() * 0.33F;
            int j = i == 0 ? 0 : MathHelper.hsvToRGB(f, 0.9F, 0.9F);
            int k = j >> 16 & 255;
            int l = j >> 8 & 255;
            int i1 = j & 255;
            bufferbuilder.pos((double)pathpoint.x - this.xo + 0.5, (double)pathpoint.y - this.yo + 0.5, (double)pathpoint.z - this.zo + 0.5).color(k, l, i1, 255).endVertex();
         }
      }

      tessellator.draw();
   }

   private float addDistanceToPlayer(VR point) {
      return (float)(Math.abs((double)point.x - this.player.posX) + Math.abs((double)point.y - this.player.posY) + Math.abs((double)point.z - this.player.posZ));
   }
}
