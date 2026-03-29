package neo;

import java.util.UUID;
import net.minecraft.util.math.BlockPos;

public class bqt {
   public bqt() {
   }

   public static bis getWorldServer() {
      nC minecraft = XH.getMinecraft();
      bij world = minecraft.world;
      if (world == null) {
         return null;
      } else if (!minecraft.isIntegratedServerRunning()) {
         return null;
      } else {
         WK integratedserver = minecraft.getIntegratedServer();
         if (integratedserver == null) {
            return null;
         } else {
            bil worldprovider = world.provider;
            if (worldprovider == null) {
               return null;
            } else {
               baM dimensiontype = worldprovider.getDimensionType();

               try {
                  bis worldserver = integratedserver.getWorld(dimensiontype.getId());
                  return worldserver;
               } catch (NullPointerException var6) {
                  return null;
               }
            }
         }
      }
   }

   public static Ig getEntity(UUID uuid) {
      bis worldserver = getWorldServer();
      if (worldserver == null) {
         return null;
      } else {
         Ig entity = worldserver.getEntityFromUuid(uuid);
         return entity;
      }
   }

   public static Yg getTileEntity(BlockPos pos) {
      bis worldserver = getWorldServer();
      if (worldserver == null) {
         return null;
      } else {
         bam chunk = worldserver.getChunkProvider().getLoadedChunk(pos.getX() >> 4, pos.getZ() >> 4);
         if (chunk == null) {
            return null;
         } else {
            Yg tileentity = chunk.getTileEntity(pos, bal.CHECK);
            return tileentity;
         }
      }
   }
}
