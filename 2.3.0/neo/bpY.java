package neo;

import java.util.Iterator;
import java.util.List;
import net.minecraft.util.ResourceLocation;

public class bpY {
   private static int countDependenciesTotal;

   public bpY() {
   }

   public static zd resolveDependencies(List<zd> listRegisteredSprites, int ix, zj textureMap) {
      zd textureatlassprite;
      for(textureatlassprite = (zd)listRegisteredSprites.get(ix); resolveOne(listRegisteredSprites, ix, textureatlassprite, textureMap); textureatlassprite = (zd)listRegisteredSprites.get(ix)) {
      }

      textureatlassprite.isDependencyParent = false;
      return textureatlassprite;
   }

   private static boolean resolveOne(List<zd> listRegisteredSprites, int ix, zd sprite, zj textureMap) {
      int i = 0;
      Iterator var5 = sprite.getDependencies().iterator();

      while(var5.hasNext()) {
         ResourceLocation resourcelocation = (ResourceLocation)var5.next();
         XH.detail("Sprite dependency: " + sprite.getIconName() + " <- " + resourcelocation);
         ++countDependenciesTotal;
         zd textureatlassprite = textureMap.getRegisteredSprite(resourcelocation);
         if (textureatlassprite == null) {
            textureatlassprite = textureMap.registerSprite(resourcelocation);
         } else {
            int j = listRegisteredSprites.indexOf(textureatlassprite);
            if (j <= ix + i) {
               continue;
            }

            if (textureatlassprite.isDependencyParent) {
               String s = "circular dependency: " + sprite.getIconName() + " -> " + textureatlassprite.getIconName();
               ResourceLocation resourcelocation1 = textureMap.getResourceLocation(sprite);
               bnQ.FMLClientHandler_trackBrokenTexture(resourcelocation1, s);
               break;
            }

            listRegisteredSprites.remove(j);
         }

         sprite.isDependencyParent = true;
         listRegisteredSprites.add(ix + i, textureatlassprite);
         ++i;
      }

      return i > 0;
   }

   public static void reset() {
      countDependenciesTotal = 0;
   }

   public static int getCountDependencies() {
      return countDependenciesTotal;
   }
}
