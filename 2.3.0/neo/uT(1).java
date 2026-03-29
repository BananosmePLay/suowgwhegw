package neo;

import java.util.Iterator;
import java.util.List;
import net.minecraft.util.math.AxisAlignedBB;

public class uT implements uQ {
   private final nC minecraft;
   private ME player;
   private double renderPosX;
   private double renderPosY;
   private double renderPosZ;

   public uT(nC minecraftIn) {
      this.minecraft = minecraftIn;
   }

   public void render(float partialTicks, long finishTimeNano) {
      nC var10001 = this.minecraft;
      this.player = nC.player;
      this.renderPosX = this.player.lastTickPosX + (this.player.posX - this.player.lastTickPosX) * (double)partialTicks;
      this.renderPosY = this.player.lastTickPosY + (this.player.posY - this.player.lastTickPosY) * (double)partialTicks;
      this.renderPosZ = this.player.lastTickPosZ + (this.player.posZ - this.player.lastTickPosZ) * (double)partialTicks;
      nC var10000 = this.minecraft;
      bij world = nC.player.world;
      List<AxisAlignedBB> list = world.getCollisionBoxes(this.player, this.player.getEntityBoundingBox().grow(6.0));
      yh.enableBlend();
      yh.tryBlendFuncSeparate(ya.SRC_ALPHA, xR.ONE_MINUS_SRC_ALPHA, ya.ONE, xR.ZERO);
      yh.glLineWidth(2.0F);
      yh.disableTexture2D();
      yh.depthMask(false);
      Iterator var6 = list.iterator();

      while(var6.hasNext()) {
         AxisAlignedBB axisalignedbb = (AxisAlignedBB)var6.next();
         yy.drawSelectionBoundingBox(axisalignedbb.grow(0.002).offset(-this.renderPosX, -this.renderPosY, -this.renderPosZ), 1.0F, 1.0F, 1.0F, 1.0F);
      }

      yh.depthMask(true);
      yh.enableTexture2D();
      yh.disableBlend();
   }
}
