package neo;

import java.util.Calendar;
import net.minecraft.util.ResourceLocation;

public class zr extends zF<Yn> {
   private static final ResourceLocation TEXTURE_TRAPPED_DOUBLE = new ResourceLocation("textures/entity/chest/trapped_double.png");
   private static final ResourceLocation TEXTURE_CHRISTMAS_DOUBLE = new ResourceLocation("textures/entity/chest/christmas_double.png");
   private static final ResourceLocation TEXTURE_NORMAL_DOUBLE = new ResourceLocation("textures/entity/chest/normal_double.png");
   private static final ResourceLocation TEXTURE_TRAPPED = new ResourceLocation("textures/entity/chest/trapped.png");
   private static final ResourceLocation TEXTURE_CHRISTMAS = new ResourceLocation("textures/entity/chest/christmas.png");
   private static final ResourceLocation TEXTURE_NORMAL = new ResourceLocation("textures/entity/chest/normal.png");
   private final nR simpleChest = new nR();
   private final nR largeChest = new oi();
   private boolean isChristmas;

   public zr() {
      Calendar calendar = Calendar.getInstance();
      if (calendar.get(2) + 1 == 12 && calendar.get(5) >= 24 && calendar.get(5) <= 26) {
         this.isChristmas = true;
      }

   }

   public void render(Yn te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
      yh.enableDepth();
      yh.depthFunc(515);
      yh.depthMask(true);
      int i;
      if (te.hasWorld()) {
         co block = te.getBlockType();
         i = te.getBlockMetadata();
         if (block instanceof cT && i == 0) {
            ((cT)block).checkForSurroundingChests(te.getWorld(), te.getPos(), te.getWorld().getBlockState(te.getPos()));
            i = te.getBlockMetadata();
         }

         te.checkForAdjacentChests();
      } else {
         i = 0;
      }

      if (te.adjacentChestZNeg == null && te.adjacentChestXNeg == null) {
         nR modelchest;
         if (te.adjacentChestXPos == null && te.adjacentChestZPos == null) {
            modelchest = this.simpleChest;
            if (destroyStage >= 0) {
               this.bindTexture(DESTROY_STAGES[destroyStage]);
               yh.matrixMode(5890);
               yh.pushMatrix();
               yh.scale(4.0F, 4.0F, 1.0F);
               yh.translate(0.0625F, 0.0625F, 0.0625F);
               yh.matrixMode(5888);
            } else if (this.isChristmas) {
               this.bindTexture(TEXTURE_CHRISTMAS);
            } else if (te.getChestType() == cS.TRAP) {
               this.bindTexture(TEXTURE_TRAPPED);
            } else {
               this.bindTexture(TEXTURE_NORMAL);
            }
         } else {
            modelchest = this.largeChest;
            if (destroyStage >= 0) {
               this.bindTexture(DESTROY_STAGES[destroyStage]);
               yh.matrixMode(5890);
               yh.pushMatrix();
               yh.scale(8.0F, 4.0F, 1.0F);
               yh.translate(0.0625F, 0.0625F, 0.0625F);
               yh.matrixMode(5888);
            } else if (this.isChristmas) {
               this.bindTexture(TEXTURE_CHRISTMAS_DOUBLE);
            } else if (te.getChestType() == cS.TRAP) {
               this.bindTexture(TEXTURE_TRAPPED_DOUBLE);
            } else {
               this.bindTexture(TEXTURE_NORMAL_DOUBLE);
            }
         }

         yh.pushMatrix();
         yh.enableRescaleNormal();
         if (destroyStage < 0) {
            yh.color(1.0F, 1.0F, 1.0F, alpha);
         }

         yh.translate((float)x, (float)y + 1.0F, (float)z + 1.0F);
         yh.scale(1.0F, -1.0F, -1.0F);
         yh.translate(0.5F, 0.5F, 0.5F);
         int j = 0;
         if (i == 2) {
            j = 180;
         }

         if (i == 3) {
            j = 0;
         }

         if (i == 4) {
            j = 90;
         }

         if (i == 5) {
            j = -90;
         }

         if (i == 2 && te.adjacentChestXPos != null) {
            yh.translate(1.0F, 0.0F, 0.0F);
         }

         if (i == 5 && te.adjacentChestZPos != null) {
            yh.translate(0.0F, 0.0F, -1.0F);
         }

         yh.rotate((float)j, 0.0F, 1.0F, 0.0F);
         yh.translate(-0.5F, -0.5F, -0.5F);
         float f = te.prevLidAngle + (te.lidAngle - te.prevLidAngle) * partialTicks;
         float f2;
         if (te.adjacentChestZNeg != null) {
            f2 = te.adjacentChestZNeg.prevLidAngle + (te.adjacentChestZNeg.lidAngle - te.adjacentChestZNeg.prevLidAngle) * partialTicks;
            if (f2 > f) {
               f = f2;
            }
         }

         if (te.adjacentChestXNeg != null) {
            f2 = te.adjacentChestXNeg.prevLidAngle + (te.adjacentChestXNeg.lidAngle - te.adjacentChestXNeg.prevLidAngle) * partialTicks;
            if (f2 > f) {
               f = f2;
            }
         }

         f = 1.0F - f;
         f = 1.0F - f * f * f;
         modelchest.chestLid.rotateAngleX = -(f * 1.5707964F);
         modelchest.renderAll();
         yh.disableRescaleNormal();
         yh.popMatrix();
         yh.color(1.0F, 1.0F, 1.0F, 1.0F);
         if (destroyStage >= 0) {
            yh.matrixMode(5890);
            yh.popMatrix();
            yh.matrixMode(5888);
         }
      }

   }

   // $FF: synthetic method
   // $FF: bridge method
   public void render(Yg var1, double var2, double var4, double var6, float var8, int var9, float var10) {
      this.render((Yn)var1, var2, var4, var6, var8, var9, var10);
   }
}
