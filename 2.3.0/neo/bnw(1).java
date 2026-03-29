package neo;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class bnw implements vw {
   private wM renderPlayer = null;

   public bnw(wM renderPlayer) {
      this.renderPlayer = renderPlayer;
   }

   public void doRenderLayer(Iw entityLiving, float limbSwing, float limbSwingAmount, float partialTicks, float ticksExisted, float headYaw, float rotationPitch, float scale) {
      this.renderEquippedItems(entityLiving, scale, partialTicks);
   }

   protected void renderEquippedItems(Iw entityLiving, float scale, float partialTicks) {
      if (XH.isShowCapes() && entityLiving instanceof jf) {
         jf abstractclientplayer = (jf)entityLiving;
         yh.color(1.0F, 1.0F, 1.0F, 1.0F);
         yh.disableRescaleNormal();
         yh.enableCull();
         nM modelbiped = this.renderPlayer.getMainModel();
         bns.renderPlayerItems(modelbiped, abstractclientplayer, scale, partialTicks);
         yh.disableCull();
      }

   }

   public boolean shouldCombineTextures() {
      return false;
   }

   public static void register(Map renderPlayerMap) {
      Set set = renderPlayerMap.keySet();
      boolean flag = false;
      Iterator var3 = set.iterator();

      while(var3.hasNext()) {
         Object object = var3.next();
         Object object1 = renderPlayerMap.get(object);
         if (object1 instanceof wM) {
            wM renderplayer = (wM)object1;
            renderplayer.addLayer(new bnw(renderplayer));
            flag = true;
         }
      }

      if (!flag) {
         XH.warn("PlayerItemsLayer not registered");
      }

   }
}
