package neo;

import java.util.Iterator;
import java.util.List;

public class blk extends blh {
   public blk() {
      super(Mb.class, "sheep_wool", 0.7F);
   }

   public nH makeModel() {
      return new ox();
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      vI render = (vI)rendermanager.getEntityRenderMap().get(Mb.class);
      if (!(render instanceof wQ)) {
         XH.warn("Not a RenderSheep: " + render);
         return null;
      } else {
         wQ rendersheep1;
         if (((vI)render).getEntityClass() == null) {
            rendersheep1 = new wQ(rendermanager);
            rendersheep1.mainModel = new oy();
            rendersheep1.shadowSize = 0.7F;
            render = rendersheep1;
         }

         rendersheep1 = (wQ)render;
         List<vw<Mb>> list = rendersheep1.getLayerRenderers();
         Iterator iterator = list.iterator();

         while(iterator.hasNext()) {
            vw layerrenderer = (vw)iterator.next();
            if (layerrenderer instanceof vy) {
               iterator.remove();
            }
         }

         vy layersheepwool = new vy(rendersheep1);
         layersheepwool.sheepModel = (ox)modelBase;
         rendersheep1.addLayer(layersheepwool);
         return rendersheep1;
      }
   }
}
