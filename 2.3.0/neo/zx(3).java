package neo;

public class zx extends zF<YG> {
   public zx() {
   }

   public void render(YG te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
      yh.pushMatrix();
      yh.translate((float)x + 0.5F, (float)y, (float)z + 0.5F);
      renderMob(te.getSpawnerBaseLogic(), x, y, z, partialTicks);
      yh.popMatrix();
   }

   public static void renderMob(Yb mobSpawnerLogic, double posX, double posY, double posZ, float partialTicks) {
      Ig entity = mobSpawnerLogic.getCachedEntity();
      if (entity != null) {
         float f = 0.53125F;
         float f1 = Math.max(entity.width, entity.height);
         if ((double)f1 > 1.0) {
            f /= f1;
         }

         yh.translate(0.0F, 0.4F, 0.0F);
         yh.rotate((float)(mobSpawnerLogic.getPrevMobRotation() + (mobSpawnerLogic.getMobRotation() - mobSpawnerLogic.getPrevMobRotation()) * (double)partialTicks) * 10.0F, 0.0F, 1.0F, 0.0F);
         yh.translate(0.0F, -0.2F, 0.0F);
         yh.rotate(-30.0F, 1.0F, 0.0F, 0.0F);
         yh.scale(f, f, f);
         entity.setLocationAndAngles(posX, posY, posZ, 0.0F, 0.0F);
         nC.getMinecraft().getRenderManager().renderEntity(entity, 0.0, 0.0, 0.0, 0.0F, partialTicks, false);
      }

   }

   // $FF: synthetic method
   // $FF: bridge method
   public void render(Yg var1, double var2, double var4, double var6, float var8, int var9, float var10) {
      this.render((YG)var1, var2, var4, var6, var8, var9, var10);
   }
}
