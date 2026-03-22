package net.minecraft.client.renderer.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;

public class TileEntityMobSpawnerRenderer extends TileEntitySpecialRenderer<TileEntityMobSpawner> {
   public TileEntityMobSpawnerRenderer() {
   }

   public void render(TileEntityMobSpawner te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
      GlStateManager.pushMatrix();
      GlStateManager.translate((float)x + 0.5F, (float)y, (float)z + 0.5F);
      renderMob(te.getSpawnerBaseLogic(), x, y, z, partialTicks);
      GlStateManager.popMatrix();
   }

   public static void renderMob(MobSpawnerBaseLogic mobSpawnerLogic, double posX, double posY, double posZ, float partialTicks) {
      Entity entity = mobSpawnerLogic.getCachedEntity();
      if (entity != null) {
         float f = 0.53125F;
         float f1 = Math.max(entity.width, entity.height);
         if ((double)f1 > 1.0) {
            f /= f1;
         }

         GlStateManager.translate(0.0F, 0.4F, 0.0F);
         GlStateManager.rotate((float)(mobSpawnerLogic.getPrevMobRotation() + (mobSpawnerLogic.getMobRotation() - mobSpawnerLogic.getPrevMobRotation()) * (double)partialTicks) * 10.0F, 0.0F, 1.0F, 0.0F);
         GlStateManager.translate(0.0F, -0.2F, 0.0F);
         GlStateManager.rotate(-30.0F, 1.0F, 0.0F, 0.0F);
         GlStateManager.scale(f, f, f);
         entity.setLocationAndAngles(posX, posY, posZ, 0.0F, 0.0F);
         Minecraft.getMinecraft().getRenderManager().renderEntity(entity, 0.0, 0.0, 0.0, 0.0F, partialTicks, false);
      }

   }

   // $FF: synthetic method
   // $FF: bridge method
   public void render(TileEntity var1, double var2, double var4, double var6, float var8, int var9, float var10) {
      this.render((TileEntityMobSpawner)var1, var2, var4, var6, var8, var9, var10);
   }
}
