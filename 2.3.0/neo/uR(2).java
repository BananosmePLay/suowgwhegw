package neo;

public class uR {
   public final uQ pathfinding;
   public final uQ water;
   public final uQ chunkBorder;
   public final uQ heightMap;
   public final uQ collisionBox;
   public final uQ neighborsUpdate;
   public final uQ solidFace;
   private boolean chunkBorderEnabled;
   private boolean pathfindingEnabled;
   private boolean waterEnabled;
   private boolean heightMapEnabled;
   private boolean collisionBoxEnabled;
   private boolean neighborsUpdateEnabled;
   private boolean solidFaceEnabled;

   public uR(nC clientIn) {
      this.pathfinding = new uW(clientIn);
      this.water = new uY(clientIn);
      this.chunkBorder = new uS(clientIn);
      this.heightMap = new uU(clientIn);
      this.collisionBox = new uT(clientIn);
      this.neighborsUpdate = new uV(clientIn);
      this.solidFace = new uX(clientIn);
   }

   public boolean shouldRender() {
      return this.chunkBorderEnabled || this.pathfindingEnabled || this.waterEnabled || this.heightMapEnabled || this.collisionBoxEnabled || this.neighborsUpdateEnabled || this.solidFaceEnabled;
   }

   public boolean toggleChunkBorders() {
      this.chunkBorderEnabled = !this.chunkBorderEnabled;
      return this.chunkBorderEnabled;
   }

   public void renderDebug(float partialTicks, long finishTimeNano) {
      if (this.pathfindingEnabled) {
         this.pathfinding.render(partialTicks, finishTimeNano);
      }

      if (this.chunkBorderEnabled && !nC.getMinecraft().isReducedDebug()) {
         this.chunkBorder.render(partialTicks, finishTimeNano);
      }

      if (this.waterEnabled) {
         this.water.render(partialTicks, finishTimeNano);
      }

      if (this.heightMapEnabled) {
         this.heightMap.render(partialTicks, finishTimeNano);
      }

      if (this.collisionBoxEnabled) {
         this.collisionBox.render(partialTicks, finishTimeNano);
      }

      if (this.neighborsUpdateEnabled) {
         this.neighborsUpdate.render(partialTicks, finishTimeNano);
      }

      if (this.solidFaceEnabled) {
         this.solidFace.render(partialTicks, finishTimeNano);
      }

   }

   public static void renderDebugText(String str, int x, int y, int z, float partialTicks, int color) {
      renderDebugText(str, (double)x + 0.5, (double)y + 0.5, (double)z + 0.5, partialTicks, color);
   }

   public static void renderDebugText(String str, double x, double y, double z, float partialTicks, int color) {
      nC minecraft = nC.getMinecraft();
      if (nC.player != null && minecraft.getRenderManager() != null && minecraft.getRenderManager().options != null) {
         jH fontrenderer = minecraft.fontRenderer;
         ME entityplayer = nC.player;
         double d0 = entityplayer.lastTickPosX + (entityplayer.posX - entityplayer.lastTickPosX) * (double)partialTicks;
         double d1 = entityplayer.lastTickPosY + (entityplayer.posY - entityplayer.lastTickPosY) * (double)partialTicks;
         double d2 = entityplayer.lastTickPosZ + (entityplayer.posZ - entityplayer.lastTickPosZ) * (double)partialTicks;
         yh.pushMatrix();
         yh.translate((float)(x - d0), (float)(y - d1) + 0.07F, (float)(z - d2));
         yh.glNormal3f(0.0F, 1.0F, 0.0F);
         yh.scale(0.02F, -0.02F, 0.02F);
         wC rendermanager = minecraft.getRenderManager();
         yh.rotate(-rendermanager.playerViewY, 0.0F, 1.0F, 0.0F);
         yh.rotate((float)(rendermanager.options.thirdPersonView == 2 ? 1 : -1) * rendermanager.playerViewX, 1.0F, 0.0F, 0.0F);
         yh.disableLighting();
         yh.enableTexture2D();
         yh.enableDepth();
         yh.depthMask(true);
         yh.scale(-1.0F, 1.0F, 1.0F);
         fontrenderer.drawString(str, -fontrenderer.getStringWidth(str) / 2, 0, color);
         yh.enableLighting();
         yh.color(1.0F, 1.0F, 1.0F, 1.0F);
         yh.popMatrix();
      }

   }
}
